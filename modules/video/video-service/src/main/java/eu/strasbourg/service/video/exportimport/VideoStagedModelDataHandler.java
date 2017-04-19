package eu.strasbourg.service.video.exportimport;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class VideoStagedModelDataHandler
	extends BaseStagedModelDataHandler<Video> {

	public static final String[] CLASS_NAMES = { Video.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Video video = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (video != null) {
			deleteStagedModel(video);
		}
	}

	@Override
	public void deleteStagedModel(Video stagedModel) throws PortalException {
		_videoLocalService.removeVideo(stagedModel.getVideoId());
	}

	@Override
	public List<Video> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._videoLocalService.getVideosByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Video stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux galeries
		for (VideoGallery gallery : stagedModel.getVideoGalleries()) {
			if (gallery.isApproved()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, gallery,
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}
		}

		try {
			// Ajout référence à l'image
			FileEntry image = DLAppLocalServiceUtil
				.getFileEntry(stagedModel.getImageId());
			if (GroupLocalServiceUtil.getGroup(image.getGroupId())
				.isStagingGroup()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, image,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}

			// Ajout référence au fichier de transcription
			FileEntry transcription = DLAppLocalServiceUtil
				.getFileEntry(stagedModel.getTranscriptionFileId());
			if (GroupLocalServiceUtil.getGroup(transcription.getGroupId())
				.isStagingGroup()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, transcription,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}
		} catch (Exception e) {
			_log.error(e);
		}

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Video stagedModel) throws Exception {

		/**
		 * Création d'une vidéo ou récupération de l'existante
		 */
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Video importedVideo = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Video existingVideo = this._videoLocalService
				.fetchVideoByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingVideo == null) {
				importedVideo = this._videoLocalService.createVideo(sc);
			} else {
				importedVideo = existingVideo;
			}

		} else {
			importedVideo = this._videoLocalService.createVideo(sc);
		}

		// Set des champs de l'entité
		importedVideo.setUuid(stagedModel.getUuid());
		importedVideo.setTitle(stagedModel.getTitle());
		importedVideo.setDescription(stagedModel.getDescription());
		importedVideo.setCopyright(stagedModel.getCopyright());
		importedVideo.setSource(stagedModel.getSource());
		importedVideo.setPublicationDate(stagedModel.getPublicationDate());
		importedVideo.setStatus(stagedModel.getStatus());

		/**
		 * Gestion des champs représentant des relations avec d'autres entités
		 */

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedVideo);

		// On update les ids des fichiers et des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedVideo.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));
		importedVideo.setTranscriptionFileId(FileEntryHelper.getLiveFileEntryId(
			stagedModel.getTranscriptionFileId(), newIdsMap));

		// On update la vidéo
		this._videoLocalService.updateVideo(importedVideo, sc);

		// On lie la vidéo à ses galeries
		for (VideoGallery oldGallery : importedVideo.getVideoGalleries()) {
			_videoLocalService.deleteVideoGalleryVideo(
				oldGallery.getGalleryId(), importedVideo);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> galleriesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(VideoGallery.class);
		for (Map.Entry<Long, Long> galleryIdMapEntry : galleriesIdsMap
			.entrySet()) {
			if (stagedModel.getVideoGalleriesIds()
				.contains(String.valueOf(galleryIdMapEntry.getKey()))) {
				_videoLocalService.addVideoGalleryVideo(
					galleryIdMapEntry.getValue(), importedVideo);
			}
		}

	}

	@Reference(unbind = "-")
	protected void setVideoLocalService(VideoLocalService videoLocalService) {
		this._videoLocalService = videoLocalService;
	}

	private VideoLocalService _videoLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

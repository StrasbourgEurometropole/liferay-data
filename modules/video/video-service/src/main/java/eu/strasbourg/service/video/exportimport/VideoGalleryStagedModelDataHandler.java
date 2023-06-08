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
import eu.strasbourg.service.video.service.VideoGalleryLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class VideoGalleryStagedModelDataHandler
	extends BaseStagedModelDataHandler<VideoGallery> {

	public static final String[] CLASS_NAMES = { VideoGallery.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		VideoGallery VideoGallery = fetchStagedModelByUuidAndGroupId(uuid,
			groupId);
		if (VideoGallery != null) {
			deleteStagedModel(VideoGallery);
		}
	}

	@Override
	public void deleteStagedModel(VideoGallery stagedModel)
		throws PortalException {
		_videoGalleryLocalService.removeGallery(stagedModel.getGalleryId());
	}

	@Override
	public List<VideoGallery> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._videoGalleryLocalService
			.getVideoGalleriesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		VideoGallery stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout références aux vidéos
		for (Video video : stagedModel.getVideos()) {
			if (video.isApproved()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, video,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}
		}

		// Ajout référence à l'image
		try {
			FileEntry image = DLAppLocalServiceUtil
				.getFileEntry(stagedModel.getImageId());
			if (GroupLocalServiceUtil.getGroup(image.getGroupId())
				.isStagingGroup()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, image,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		VideoGallery stagedModel) throws Exception {

		/**
		 * Création d'une galerie ou récupération de l'existante
		 */
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		VideoGallery importedVideoGallery = null;
		if (portletDataContext.isDataStrategyMirror()) {
			VideoGallery existingVideoGallery = this._videoGalleryLocalService
				.fetchVideoGalleryByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingVideoGallery == null) {
				importedVideoGallery = this._videoGalleryLocalService
					.createVideoGallery(sc);
			} else {
				importedVideoGallery = existingVideoGallery;
			}

		} else {
			importedVideoGallery = this._videoGalleryLocalService
				.createVideoGallery(sc);
		}

		// Set des champs de l'entité
		importedVideoGallery.setUuid(stagedModel.getUuid());
		importedVideoGallery.setTitle(stagedModel.getTitle());
		importedVideoGallery.setDescription(stagedModel.getDescription());
		importedVideoGallery.setPublicationDate(stagedModel.getPublicationDate());
		importedVideoGallery.setStatus(stagedModel.getStatus());

		/**
		 * Gestion des champs représentant des relations avec d'autres entités
		 */

		// Import de l'asset, tags, catégories, et des éléments liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedVideoGallery);

		// On update l'id de l'image avec le nouveau
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedVideoGallery.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update la galerie
		this._videoGalleryLocalService.updateVideoGallery(importedVideoGallery,
			sc);

		// On lie la galerie à ses vidéos
		for (Video oldVideo : importedVideoGallery.getVideos()) {
			_videoGalleryLocalService.deleteVideoVideoGallery(
				oldVideo.getVideoId(), importedVideoGallery);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> videosIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Video.class);
		for (Map.Entry<Long, Long> videoIdMapEntry : videosIdsMap.entrySet()) {
			if (stagedModel.getVideosIds()
				.contains(String.valueOf(videoIdMapEntry.getKey()))) {
				_videoGalleryLocalService.addVideoVideoGallery(
					videoIdMapEntry.getValue(), importedVideoGallery);
			}
		}

	}

	@Reference(unbind = "-")
	protected void setVideoGalleryLocalService(
		VideoGalleryLocalService videoGalleryLocalService) {
		this._videoGalleryLocalService = videoGalleryLocalService;
	}

	private VideoGalleryLocalService _videoGalleryLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

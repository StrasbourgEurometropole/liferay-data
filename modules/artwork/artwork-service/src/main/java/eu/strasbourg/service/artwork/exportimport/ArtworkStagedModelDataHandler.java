package eu.strasbourg.service.artwork.exportimport;

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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class ArtworkStagedModelDataHandler
	extends BaseStagedModelDataHandler<Artwork> {

	public static final String[] CLASS_NAMES = { Artwork.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Artwork artwork = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (artwork != null) {
			deleteStagedModel(artwork);
		}
	}

	@Override
	public void deleteStagedModel(Artwork stagedModel) throws PortalException {
		this._artworkLocalService.removeArtwork(stagedModel.getArtworkId());
	}

	@Override
	public List<Artwork> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._artworkLocalService.getArtworksByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Artwork stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux galeries
		for (ArtworkCollection artworkCollection : stagedModel
			.getArtworkCollections()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, artworkCollection,
				PortletDataContext.REFERENCE_TYPE_PARENT);
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

			// Et aux images secondaires
			for (String imageIdStr : stagedModel.getImagesIds().split(",")) {
				if (Validator.isNotNull(imageIdStr)) {
					Long imageId = Long.parseLong(imageIdStr);
					FileEntry otherImage = DLAppLocalServiceUtil
						.getFileEntry(imageId);
					if (GroupLocalServiceUtil.getGroup(image.getGroupId())
						.isStagingGroup()) {
						StagedModelDataHandlerUtil.exportReferenceStagedModel(
							portletDataContext, stagedModel, otherImage,
							PortletDataContext.REFERENCE_TYPE_WEAK);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Artwork stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Artwork importedArtwork = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Artwork existingArtwork = this._artworkLocalService
				.fetchArtworkByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingArtwork == null) {
				importedArtwork = this._artworkLocalService.createArtwork(sc);
			} else {
				importedArtwork = existingArtwork;
			}

		} else {
			importedArtwork = this._artworkLocalService.createArtwork(sc);
		}

		importedArtwork.setUuid(stagedModel.getUuid());
		importedArtwork.setTitle(stagedModel.getTitle());
		importedArtwork.setDescription(stagedModel.getDescription());
		importedArtwork
			.setTechnicalInformation(stagedModel.getTechnicalInformation());
		importedArtwork.setNoticeLink(stagedModel.getNoticeLink());
		importedArtwork.setArtistName(stagedModel.getArtistName());
		importedArtwork.setCreationYear(stagedModel.getCreationYear());
		importedArtwork.setOrigin(stagedModel.getOrigin());
		importedArtwork.setExhibitionName(stagedModel.getExhibitionName());
		importedArtwork.setExhibitionPlace(stagedModel.getExhibitionPlace());
		importedArtwork.setLoanPeriod(stagedModel.getLoanPeriod());
		importedArtwork.setLinkName(stagedModel.getLinkName());
		importedArtwork.setLink(stagedModel.getLink());

		importedArtwork.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des éléments liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedArtwork);

		// On update l'id des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedArtwork.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));
		String otherImagesIds = stagedModel.getImagesIds();
		for (Map.Entry<Long, Long> oldId_newId : newIdsMap.entrySet()) {
			otherImagesIds.replace(String.valueOf(oldId_newId.getKey()),
				String.valueOf(oldId_newId.getValue()));
		}
		importedArtwork.setImagesIds(otherImagesIds);

		// On update l'oeuvre
		this._artworkLocalService.updateArtwork(importedArtwork, sc);

		// On lie l'oeuvre à ses collections
		for (ArtworkCollection oldCollection : importedArtwork
			.getArtworkCollections()) {
			_artworkLocalService.deleteArtworkCollectionArtwork(
				oldCollection.getCollectionId(), importedArtwork);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> collectionsIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(ArtworkCollection.class);
		for (Map.Entry<Long, Long> collectionIdMapEntry : collectionsIdsMap
			.entrySet()) {
			if (stagedModel.getArtworkCollectionsIds()
				.contains(String.valueOf(collectionIdMapEntry.getKey()))) {
				_artworkLocalService.addArtworkCollectionArtwork(
					collectionIdMapEntry.getValue(), importedArtwork);
			}
		}

	}

	@Reference(unbind = "-")
	protected void setArtworkLocalService(
		ArtworkLocalService artworkLocalService) {
		this._artworkLocalService = artworkLocalService;
	}

	private ArtworkLocalService _artworkLocalService;

}

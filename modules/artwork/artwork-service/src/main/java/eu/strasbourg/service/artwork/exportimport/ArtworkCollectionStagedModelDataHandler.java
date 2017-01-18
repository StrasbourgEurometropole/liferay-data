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
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class ArtworkCollectionStagedModelDataHandler
	extends BaseStagedModelDataHandler<ArtworkCollection> {

	public static final String[] CLASS_NAMES = {
		ArtworkCollection.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		ArtworkCollection artworkCollection = this.fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (artworkCollection != null) {
			deleteStagedModel(artworkCollection);
		}
	}

	@Override
	public void deleteStagedModel(ArtworkCollection stagedModel)
		throws PortalException {
		this._artworkCollectionLocalService.removeArtworkCollection(stagedModel.getCollectionId());
	}

	@Override
	public List<ArtworkCollection> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {
		return this._artworkCollectionLocalService
			.getArtworkCollectionsByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		ArtworkCollection stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux oeuvres
		for (Artwork artwork : stagedModel.getArtworks()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, artwork,
				PortletDataContext.REFERENCE_TYPE_WEAK);
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		ArtworkCollection stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		ArtworkCollection importedArtworkCollection = null;
		if (portletDataContext.isDataStrategyMirror()) {
			ArtworkCollection existingArtworkCollection = this._artworkCollectionLocalService
				.fetchArtworkCollectionByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingArtworkCollection == null) {
				importedArtworkCollection = this._artworkCollectionLocalService
					.createArtworkCollection(sc);
			} else {
				importedArtworkCollection = existingArtworkCollection;
			}

		} else {
			importedArtworkCollection = this._artworkCollectionLocalService
				.createArtworkCollection(sc);
		}

		importedArtworkCollection.setUuid(stagedModel.getUuid());
		importedArtworkCollection.setTitle(stagedModel.getTitle());
		importedArtworkCollection.setDescription(stagedModel.getDescription());
		importedArtworkCollection.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des éléments liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedArtworkCollection);

		// On update l'id de l'image avec le nouveau
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedArtworkCollection.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update l'oeuvre
		this._artworkCollectionLocalService
			.updateArtworkCollection(importedArtworkCollection, sc);

		// On lie la collection à ses oeuvres
		for (Artwork oldArtwork : importedArtworkCollection.getArtworks()) {
			_artworkCollectionLocalService.deleteArtworkArtworkCollection(
				oldArtwork.getArtworkId(), importedArtworkCollection);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> artworksIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Artwork.class);
		for (Map.Entry<Long, Long> artworkIdMapEntry : artworksIdsMap
			.entrySet()) {
			_artworkCollectionLocalService.addArtworkArtworkCollection(
				artworkIdMapEntry.getValue(), importedArtworkCollection);
		}

	}

	@Reference(unbind = "-")
	protected void setArtworkCollectionLocalService(
		ArtworkCollectionLocalService artworkCollectionLocalService) {
		this._artworkCollectionLocalService = artworkCollectionLocalService;
	}

	private ArtworkCollectionLocalService _artworkCollectionLocalService;

}

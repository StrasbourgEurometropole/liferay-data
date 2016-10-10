package eu.strasbourg.service.edition.exportimport;

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

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class EditionGalleryStagedModelDataHandler
	extends BaseStagedModelDataHandler<EditionGallery> {

	public static final String[] CLASS_NAMES = {
		EditionGallery.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteStagedModel(EditionGallery stagedModel)
		throws PortalException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<EditionGallery> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._editionGalleryLocalService
			.getEditionGalleriesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		EditionGallery stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout références aux éditions
		for (Edition edition : stagedModel.getEditions()) {
			if (edition.isApproved()) {
    			StagedModelDataHandlerUtil.exportReferenceStagedModel(
    				portletDataContext, stagedModel, edition,
    				PortletDataContext.REFERENCE_TYPE_WEAK);
    		}
		}

		// Ajout référence à l'image
		FileEntry image = DLAppLocalServiceUtil
			.getFileEntry(stagedModel.getImageId());
		if (GroupLocalServiceUtil.getGroup(image.getGroupId())
			.isStagingGroup()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, image,
				PortletDataContext.REFERENCE_TYPE_WEAK);
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		EditionGallery stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		EditionGallery importedEditionGallery = null;
		if (portletDataContext.isDataStrategyMirror()) {
			EditionGallery existingEditionGallery = this._editionGalleryLocalService
				.fetchEditionGalleryByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingEditionGallery == null) {
				importedEditionGallery = this._editionGalleryLocalService
					.createEditionGallery(sc);
			} else {
				importedEditionGallery = existingEditionGallery;
			}

		} else {
			importedEditionGallery = this._editionGalleryLocalService
				.createEditionGallery(sc);
		}

		importedEditionGallery.setUuid(stagedModel.getUuid());
		importedEditionGallery.setTitle(stagedModel.getTitle());
		importedEditionGallery.setImageId(stagedModel.getImageId());
		importedEditionGallery.setDescription(stagedModel.getDescription());
		importedEditionGallery
			.setPublicationDate(stagedModel.getPublicationDate());
		importedEditionGallery.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des éléments liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedEditionGallery);

		// On update l'id de l'image avec le nouveau
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedEditionGallery.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update la galerie
		this._editionGalleryLocalService
			.updateEditionGallery(importedEditionGallery, sc);

		// On lie la galerie à ses éditions
		for (Edition oldEdition : importedEditionGallery.getEditions()) {
			_editionGalleryLocalService.deleteEditionEditionGallery(
				oldEdition.getEditionId(), importedEditionGallery);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> editionsIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Edition.class);
		for (Map.Entry<Long, Long> editionIdMapEntry : editionsIdsMap
			.entrySet()) {
			_editionGalleryLocalService.addEditionEditionGallery(
				editionIdMapEntry.getValue(), importedEditionGallery);
		}

	}

	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(
		EditionGalleryLocalService editionGalleryLocalService) {
		this._editionGalleryLocalService = editionGalleryLocalService;
	}

	private EditionGalleryLocalService _editionGalleryLocalService;

}

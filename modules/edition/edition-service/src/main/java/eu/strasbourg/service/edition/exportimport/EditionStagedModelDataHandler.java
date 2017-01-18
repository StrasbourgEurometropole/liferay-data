package eu.strasbourg.service.edition.exportimport;

import java.util.List;
import java.util.Locale;
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
import eu.strasbourg.service.edition.service.EditionLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class EditionStagedModelDataHandler
	extends BaseStagedModelDataHandler<Edition> {

	public static final String[] CLASS_NAMES = { Edition.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Edition edition = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (edition != null) {
			deleteStagedModel(edition);
		}
	}

	@Override
	public void deleteStagedModel(Edition stagedModel) throws PortalException {
		_editionLocalService.removeEdition(stagedModel.getEditionId());
	}

	@Override
	public List<Edition> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._editionLocalService.getEditionsByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Edition stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux galeries
		for (EditionGallery gallery : stagedModel.getEditionGalleries()) {
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

			// Ajout référence au fichier
			for (Map.Entry<Locale, String> locale_fileId : stagedModel
				.getFileIdMap().entrySet()) {
				long fileId = Long.parseLong(locale_fileId.getValue());
				FileEntry file = DLAppLocalServiceUtil.getFileEntry(fileId);
				if (GroupLocalServiceUtil.getGroup(file.getGroupId())
					.isStagingGroup()) {
					StagedModelDataHandlerUtil.exportReferenceStagedModel(
						portletDataContext, stagedModel, file,
						PortletDataContext.REFERENCE_TYPE_WEAK);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Edition stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Edition importedEdition = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Edition existingEdition = this._editionLocalService
				.fetchEditionByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingEdition == null) {
				importedEdition = this._editionLocalService.createEdition(sc);
			} else {
				importedEdition = existingEdition;
			}

		} else {
			importedEdition = this._editionLocalService.createEdition(sc);
		}

		importedEdition.setUuid(stagedModel.getUuid());
		importedEdition.setTitle(stagedModel.getTitle());
		importedEdition.setSubtitle(stagedModel.getSubtitle());
		importedEdition.setImageId(stagedModel.getImageId());
		importedEdition.setDescription(stagedModel.getDescription());
		importedEdition.setAuthor(stagedModel.getAuthor());
		importedEdition.setEditor(stagedModel.getEditor());
		importedEdition.setURL(stagedModel.getURL());
		importedEdition.setFileId(stagedModel.getFileId());
		importedEdition.setDistribution(stagedModel.getDistribution());
		importedEdition.setISBN(stagedModel.getISBN());
		importedEdition.setPrice(stagedModel.getPrice());
		importedEdition
			.setAvailableForExchange(stagedModel.isAvailableForExchange());
		importedEdition.setInStock(stagedModel.isInStock());
		importedEdition.setDiffusionDateMonth(stagedModel.getDiffusionDateMonth());
		importedEdition.setDiffusionDateYear(stagedModel.getDiffusionDateYear());
		importedEdition.setPageNumber(stagedModel.getPageNumber());
		importedEdition.setPictureNumber(stagedModel.getPictureNumber());
		importedEdition.setPublicationDate(stagedModel.getPublicationDate());
		importedEdition.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedEdition);

		// On update les ids des fichiers et des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedEdition.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));
		importedEdition.setFileIdMap(FileEntryHelper
			.getLiveFileEntryIdMap(stagedModel.getFileIdMap(), newIdsMap));

		// On update l'édition
		this._editionLocalService.updateEdition(importedEdition, sc);

		// On lie l'édition à ses galeries
		for (EditionGallery oldGallery : importedEdition
			.getEditionGalleries()) {
			_editionLocalService.deleteEditionGalleryEdition(
				oldGallery.getGalleryId(), importedEdition);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> galleriesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(EditionGallery.class);
		for (Map.Entry<Long, Long> galleryIdMapEntry : galleriesIdsMap
			.entrySet()) {
			_editionLocalService.addEditionGalleryEdition(
				galleryIdMapEntry.getValue(), importedEdition);
		}

	}

	@Reference(unbind = "-")
	protected void setEditionLocalService(
		EditionLocalService editionLocalService) {
		this._editionLocalService = editionLocalService;
	}

	private EditionLocalService _editionLocalService;

}

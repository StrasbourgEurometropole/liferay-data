package eu.strasbourg.service.official.exportimport;

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

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class OfficialStagedModelDataHandler
	extends BaseStagedModelDataHandler<Official> {

	public static final String[] CLASS_NAMES = { Official.class.getName() };
	
	@Reference
	private OfficialLocalService officialLocalService;

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Official official = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (official != null) {
			deleteStagedModel(official);
		}
	}

	@Override
	public void deleteStagedModel(Official stagedModel) throws PortalException {
		officialLocalService.removeOfficial(stagedModel.getOfficialId());
	}

	@Override
	public List<Official> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return officialLocalService.getOfficialsByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
			Official stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

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
		} catch (Exception e) {
			_log.error(e);
		}

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
			Official stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Official importedOfficial = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Official existingOfficial = officialLocalService
				.fetchOfficialByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingOfficial == null) {
				importedOfficial = officialLocalService.createOfficial(sc);
			} else {
				importedOfficial = existingOfficial;
			}

		} else {
			importedOfficial = officialLocalService.createOfficial(sc);
		}

		importedOfficial.setUuid(stagedModel.getUuid());
		importedOfficial.setGender(stagedModel.getGender());
		importedOfficial.setLastName(stagedModel.getLastName());
		importedOfficial.setFirstName(stagedModel.getFirstName());
		importedOfficial.setThematicDelegation(stagedModel.getThematicDelegation());
		importedOfficial.setMissions(stagedModel.getMissions());
		importedOfficial.setWasMinister(stagedModel.getWasMinister());
		importedOfficial.setContact(stagedModel.getContact());
//		TODO on garde ?
		importedOfficial.setImageId(stagedModel.getImageId());
		importedOfficial.setStatus(stagedModel.getStatus());
		
		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedOfficial);

		// On update les ids des fichiers et des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedOfficial.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update l'élu
		officialLocalService.updateOfficial(importedOfficial, sc);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

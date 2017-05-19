package eu.strasbourg.service.activity.exportimport;

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

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalService;
import eu.strasbourg.utils.FileEntryHelper;

// @Component(immediate = true, service = StagedModelDataHandler.class)
public class ActivityOrganizerStagedModelDataHandler
	extends BaseStagedModelDataHandler<ActivityOrganizer> {

	private ActivityOrganizerLocalService activityOrganizerLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Reference(unbind = "-")
	public void setActivityOrganizerLocalService(
		ActivityOrganizerLocalService activityOrganizerLocalService) {
		this.activityOrganizerLocalService = activityOrganizerLocalService;
	}

	public static final String[] CLASS_NAMES = {
		ActivityOrganizer.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		ActivityOrganizer activityOrganizer = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);
		if (activityOrganizer != null) {
			deleteStagedModel(activityOrganizer);
		}
	}

	@Override
	public void deleteStagedModel(ActivityOrganizer stagedModel)
		throws PortalException {
		activityOrganizerLocalService
			.removeActivityOrganizer(stagedModel.getActivityOrganizerId());
	}

	@Override
	public List<ActivityOrganizer> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {
		return this.activityOrganizerLocalService
			.getActivityOrganizersByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		ActivityOrganizer stagedModel) throws Exception {

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
		ActivityOrganizer stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		ActivityOrganizer importedActivityOrganizer = null;
		if (portletDataContext.isDataStrategyMirror()) {
			ActivityOrganizer existingActivityOrganizer = this.activityOrganizerLocalService
				.fetchActivityOrganizerByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingActivityOrganizer == null) {
				importedActivityOrganizer = this.activityOrganizerLocalService
					.createActivityOrganizer(sc);
			} else {
				importedActivityOrganizer = existingActivityOrganizer;
			}

		} else {
			importedActivityOrganizer = this.activityOrganizerLocalService
				.createActivityOrganizer(sc);
		}

		importedActivityOrganizer.setUuid(stagedModel.getUuid());
		importedActivityOrganizer.setName(stagedModel.getName());
		importedActivityOrganizer
			.setContactInformation(stagedModel.getContactInformation());
		importedActivityOrganizer.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedActivityOrganizer);
		

		// On update l'id des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedActivityOrganizer.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update l'entité
		this.activityOrganizerLocalService
			.updateActivityOrganizer(importedActivityOrganizer, sc);

	}
}

package eu.strasbourg.service.activity.exportimport;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.service.ActivityCourseScheduleLocalService;

// @Component(immediate = true, service = StagedModelDataHandler.class)
public class ActivityCourseScheduleStagedModelDataHandler
	extends BaseStagedModelDataHandler<ActivityCourseSchedule> {

	private ActivityCourseScheduleLocalService activityCourseScheduleLocalService;

	@Reference(unbind = "-")
	public void setActivityCourseScheduleLocalService(
		ActivityCourseScheduleLocalService activityCourseScheduleLocalService) {
		this.activityCourseScheduleLocalService = activityCourseScheduleLocalService;
	}

	public static final String[] CLASS_NAMES = {
		ActivityCourseSchedule.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		ActivityCourseSchedule activityCourseSchedule = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);
		if (activityCourseSchedule != null) {
			deleteStagedModel(activityCourseSchedule);
		}
	}

	@Override
	public void deleteStagedModel(ActivityCourseSchedule stagedModel)
		throws PortalException {
		activityCourseScheduleLocalService.removeActivityCourseSchedule(
			stagedModel.getActivityCourseScheduleId());
	}

	@Override
	public List<ActivityCourseSchedule> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {
		return this.activityCourseScheduleLocalService
			.getActivityCourseSchedulesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		ActivityCourseSchedule stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		ActivityCourseSchedule stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		ActivityCourseSchedule importedActivityCourseSchedule = null;
		if (portletDataContext.isDataStrategyMirror()) {
			ActivityCourseSchedule existingActivityCourseSchedule = this.activityCourseScheduleLocalService
				.fetchActivityCourseScheduleByUuidAndGroupId(
					stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingActivityCourseSchedule == null) {
				importedActivityCourseSchedule = this.activityCourseScheduleLocalService
					.createActivityCourseSchedule(sc);
			} else {
				importedActivityCourseSchedule = existingActivityCourseSchedule;
			}

		} else {
			importedActivityCourseSchedule = this.activityCourseScheduleLocalService
				.createActivityCourseSchedule(sc);
		}

		importedActivityCourseSchedule.setUuid(stagedModel.getUuid());
		importedActivityCourseSchedule.setStartTime(stagedModel.getStartTime());
		importedActivityCourseSchedule.setEndTime(stagedModel.getEndTime());
		importedActivityCourseSchedule.setDays(stagedModel.getDays());
		importedActivityCourseSchedule.setComments(stagedModel.getComments());
		// TODO : pas sûr, id de staging
		importedActivityCourseSchedule.setPeriodsIds(stagedModel.getPeriodsIds());

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedActivityCourseSchedule);

		// On update l'entité
		this.activityCourseScheduleLocalService
			.updateActivityCourseSchedule(importedActivityCourseSchedule, sc);

	}
}

package eu.strasbourg.service.activity.exportimport;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalService;
import eu.strasbourg.service.activity.service.ActivityCourseScheduleLocalService;

// @Component(immediate = true, service = StagedModelDataHandler.class)
public class ActivityCoursePlaceStagedModelDataHandler
	extends BaseStagedModelDataHandler<ActivityCoursePlace> {

	private ActivityCoursePlaceLocalService activityCoursePlaceLocalService;

	private ActivityCourseScheduleLocalService activityCourseScheduleLocalService;

	@Reference(unbind = "-")
	public void setActivityCoursePlaceLocalService(
		ActivityCoursePlaceLocalService activityCoursePlaceLocalService) {
		this.activityCoursePlaceLocalService = activityCoursePlaceLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCourseScheduleLocalService(
		ActivityCourseScheduleLocalService activityCourseScheduleLocalService) {
		this.activityCourseScheduleLocalService = activityCourseScheduleLocalService;
	}

	public static final String[] CLASS_NAMES = {
		ActivityCoursePlace.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		ActivityCoursePlace activityCoursePlace = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);
		if (activityCoursePlace != null) {
			deleteStagedModel(activityCoursePlace);
		}
	}

	@Override
	public void deleteStagedModel(ActivityCoursePlace stagedModel)
		throws PortalException {
		activityCoursePlaceLocalService
			.removeActivityCoursePlace(stagedModel.getActivityCoursePlaceId());
	}

	@Override
	public List<ActivityCoursePlace> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {
		return this.activityCoursePlaceLocalService
			.getActivityCoursePlacesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		ActivityCoursePlace stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux horaires
		for (ActivityCourseSchedule activityCourseSchedule : stagedModel
			.getActivityCourseSchedules()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, activityCourseSchedule,
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		ActivityCoursePlace stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		ActivityCoursePlace importedActivityCoursePlace = null;
		if (portletDataContext.isDataStrategyMirror()) {
			ActivityCoursePlace existingActivityCoursePlace = this.activityCoursePlaceLocalService
				.fetchActivityCoursePlaceByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingActivityCoursePlace == null) {
				importedActivityCoursePlace = this.activityCoursePlaceLocalService
					.createActivityCoursePlace(sc);
			} else {
				importedActivityCoursePlace = existingActivityCoursePlace;
			}

		} else {
			importedActivityCoursePlace = this.activityCoursePlaceLocalService
				.createActivityCoursePlace(sc);
		}

		importedActivityCoursePlace.setUuid(stagedModel.getUuid());
		importedActivityCoursePlace.setPlaceName(stagedModel.getPlaceName());
		importedActivityCoursePlace
			.setPlaceCityId(stagedModel.getPlaceCityId());
		importedActivityCoursePlace
			.setPlaceStreetNumber(stagedModel.getPlaceStreetNumber());
		importedActivityCoursePlace
			.setPlaceStreetName(stagedModel.getPlaceStreetName());
		importedActivityCoursePlace
			.setPlaceZipCode(stagedModel.getPlaceZipCode());
		importedActivityCoursePlace.setPlaceSIGId(stagedModel.getPlaceSIGId());

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedActivityCoursePlace);

		// On lie les horaires au lieu
		@SuppressWarnings("unchecked")
		Map<Long, Long> activityCourseSchedulesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(ActivityCoursePlace.class);
		for (Map.Entry<Long, Long> activityCourseScheduleIdMapEntry : activityCourseSchedulesIdsMap
			.entrySet()) {
			long activityCourseScheduleId = activityCourseScheduleIdMapEntry
				.getValue();
			ActivityCourseSchedule activityCourseSchedule = this.activityCourseScheduleLocalService
				.fetchActivityCourseSchedule(activityCourseScheduleId);
			activityCourseSchedule.setActivityCoursePlaceId(
				importedActivityCoursePlace.getActivityCoursePlaceId());
			this.activityCourseScheduleLocalService
				.updateActivityCourseSchedule(activityCourseSchedule);
		}

		// On update l'entité
		this.activityCoursePlaceLocalService
			.updateActivityCoursePlace(importedActivityCoursePlace, sc);

	}

}

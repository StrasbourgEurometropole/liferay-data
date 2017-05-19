package eu.strasbourg.service.activity.exportimport;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalService;

// @Component(immediate = true, service = StagedModelDataHandler.class)
public class ActivityCourseStagedModelDataHandler
	extends BaseStagedModelDataHandler<ActivityCourse> {

	private ActivityCourseLocalService activityCourseLocalService;

	private ActivityCoursePlaceLocalService activityCoursePlaceLocalService;

	@Reference(unbind = "-")
	public void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {
		this.activityCourseLocalService = activityCourseLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCoursePlaceLocalService(
		ActivityCoursePlaceLocalService activityCoursePlaceLocalService) {
		this.activityCoursePlaceLocalService = activityCoursePlaceLocalService;
	}

	public static final String[] CLASS_NAMES = {
		ActivityCourse.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		ActivityCourse activityCourse = fetchStagedModelByUuidAndGroupId(uuid,
			groupId);
		if (activityCourse != null) {
			deleteStagedModel(activityCourse);
		}
	}

	@Override
	public void deleteStagedModel(ActivityCourse stagedModel)
		throws PortalException {
		activityCourseLocalService
			.removeActivityCourse(stagedModel.getActivityCourseId());
	}

	@Override
	public List<ActivityCourse> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this.activityCourseLocalService
			.getActivityCoursesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		ActivityCourse stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux lieux
		for (ActivityCoursePlace activityCoursePlace : stagedModel
			.getActivityCoursePlaces()) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, activityCoursePlace,
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		// Et à l'organizer
		ActivityOrganizer activityOrganizer = stagedModel
			.getActivityOrganizer();
		if (activityOrganizer != null) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, stagedModel, activityOrganizer,
				PortletDataContext.REFERENCE_TYPE_WEAK);
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		ActivityCourse stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		ActivityCourse importedActivityCourse = null;
		if (portletDataContext.isDataStrategyMirror()) {
			ActivityCourse existingActivityCourse = this.activityCourseLocalService
				.fetchActivityCourseByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingActivityCourse == null) {
				importedActivityCourse = this.activityCourseLocalService
					.createActivityCourse(sc);
			} else {
				importedActivityCourse = existingActivityCourse;
			}

		} else {
			importedActivityCourse = this.activityCourseLocalService
				.createActivityCourse(sc);
		}

		importedActivityCourse.setUuid(stagedModel.getUuid());
		importedActivityCourse.setName(stagedModel.getName());
		importedActivityCourse.setArrangements(stagedModel.getArrangements());
		importedActivityCourse.setPrice(stagedModel.getPrice());
		// TODO : pas sûr, ID de staging
		importedActivityCourse.setServiceId(stagedModel.getServiceId());
		importedActivityCourse.setStatus(stagedModel.getStatus());

		// Id de l'organisateur
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(ActivityOrganizer.class);
		long organizerId = newIdsMap.get(stagedModel.getOrganizerId());
		importedActivityCourse.setOrganizerId(organizerId);

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedActivityCourse);

		// On lie les lieux au cours
		@SuppressWarnings("unchecked")
		Map<Long, Long> activityCoursePlacesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(ActivityCourse.class);
		for (Map.Entry<Long, Long> activityCoursePlaceIdMapEntry : activityCoursePlacesIdsMap
			.entrySet()) {
			long activityCoursePlaceId = activityCoursePlaceIdMapEntry
				.getValue();
			ActivityCoursePlace activityCoursePlace = this.activityCoursePlaceLocalService
				.fetchActivityCoursePlace(activityCoursePlaceId);
			activityCoursePlace.setActivityCourseId(
				importedActivityCourse.getActivityCourseId());
			this.activityCoursePlaceLocalService
				.updateActivityCoursePlace(activityCoursePlace);
		}

		// On update l'entité
		this.activityCourseLocalService
			.updateActivityCourse(importedActivityCourse, sc);

	}

}

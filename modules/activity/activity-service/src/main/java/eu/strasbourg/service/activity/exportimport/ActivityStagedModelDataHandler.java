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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalService;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class ActivityStagedModelDataHandler
	extends BaseStagedModelDataHandler<Activity> {

	private ActivityLocalService activityLocalService;

	private ActivityCourseLocalService activityCourseLocalService;

	private VideoLocalService videoLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Reference(unbind = "-")
	public void setActivityLocalService(
		ActivityLocalService activityLocalService) {
		this.activityLocalService = activityLocalService;
	}

	@Reference(unbind = "-")
	public void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {
		this.activityCourseLocalService = activityCourseLocalService;
	}

	@Reference(unbind = "-")
	public void setVideoLocalService(VideoLocalService videoLocalService) {
		this.videoLocalService = videoLocalService;
	}

	public static final String[] CLASS_NAMES = { Activity.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Activity activity = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (activity != null) {
			deleteStagedModel(activity);
		}
	}

	@Override
	public void deleteStagedModel(Activity stagedModel) throws PortalException {
		activityLocalService.removeActivity(stagedModel.getActivityId());
	}

	@Override
	public List<Activity> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this.activityLocalService.getActivitiesByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Activity stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux cours
		for (ActivityCourse activityCourse : stagedModel.getActivityCourses()) {
			if (activityCourse.isApproved()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, activityCourse,
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}
		}

		try {
			// Aux vidéos
			for (String videoIdStr : stagedModel.getVideosIds().split(",")) {
				if (Validator.isNotNull(videoIdStr)) {
					Long videoId = Long.parseLong(videoIdStr);
					Video video = videoLocalService.fetchVideo(videoId);
					if (video != null && GroupLocalServiceUtil
						.getGroup(video.getGroupId()).isStagingGroup()) {
						StagedModelDataHandlerUtil.exportReferenceStagedModel(
							portletDataContext, stagedModel, video,
							PortletDataContext.REFERENCE_TYPE_WEAK);
					}
				}
			}

			// Aux images
			for (String imageIdStr : stagedModel.getImagesIds().split(",")) {
				if (Validator.isNotNull(imageIdStr)) {
					Long imageId = Long.parseLong(imageIdStr);
					FileEntry image = DLAppLocalServiceUtil
						.getFileEntry(imageId);
					if (GroupLocalServiceUtil.getGroup(image.getGroupId())
						.isStagingGroup()) {
						StagedModelDataHandlerUtil.exportReferenceStagedModel(
							portletDataContext, stagedModel, image,
							PortletDataContext.REFERENCE_TYPE_WEAK);
					}
				}
			}

			// Et aux fichiers
			for (String fileIdStr : stagedModel.getFilesIds().split(",")) {
				if (Validator.isNotNull(fileIdStr)) {
					Long fileId = Long.parseLong(fileIdStr);
					FileEntry file = DLAppLocalServiceUtil.getFileEntry(fileId);
					if (GroupLocalServiceUtil.getGroup(file.getGroupId())
						.isStagingGroup()) {
						StagedModelDataHandlerUtil.exportReferenceStagedModel(
							portletDataContext, stagedModel, file,
							PortletDataContext.REFERENCE_TYPE_WEAK);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Activity stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Activity importedActivity = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Activity existingActivity = this.activityLocalService
				.fetchActivityByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingActivity == null) {
				importedActivity = this.activityLocalService.createActivity(sc);
			} else {
				importedActivity = existingActivity;
			}

		} else {
			importedActivity = this.activityLocalService.createActivity(sc);
		}

		importedActivity.setUuid(stagedModel.getUuid());
		importedActivity.setTitle(stagedModel.getTitle());
		importedActivity.setDescription(stagedModel.getDescription());
		importedActivity.setStatus(stagedModel.getStatus());

		// On update l'id des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newImagesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		String imagesIds = stagedModel.getImagesIds();
		for (Map.Entry<Long, Long> oldId_newId : newImagesIdsMap.entrySet()) {
			imagesIds.replace(String.valueOf(oldId_newId.getKey()),
				String.valueOf(oldId_newId.getValue()));
		}
		importedActivity.setImagesIds(imagesIds);

		// Idem pour les vidéos
		@SuppressWarnings("unchecked")
		Map<Long, Long> newVideosIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Video.class);
		String videosIds = stagedModel.getVideosIds();
		for (Map.Entry<Long, Long> oldId_newId : newVideosIdsMap.entrySet()) {
			videosIds.replace(String.valueOf(oldId_newId.getKey()),
				String.valueOf(oldId_newId.getValue()));
		}
		importedActivity.setVideosIds(videosIds);

		// Et les fichiers
		@SuppressWarnings("unchecked")
		Map<Long, Long> newFilesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(FileEntry.class);
		String filesIds = stagedModel.getFilesIds();
		for (Map.Entry<Long, Long> oldId_newId : newFilesIdsMap.entrySet()) {
			filesIds.replace(String.valueOf(oldId_newId.getKey()),
				String.valueOf(oldId_newId.getValue()));
		}
		importedActivity.setFilesIds(filesIds);

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedActivity);

		// On update l'entité
		this.activityLocalService.updateActivity(importedActivity, sc);

		// On lie les cours à l'activité
		@SuppressWarnings("unchecked")
		Map<Long, Long> activityCoursesIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(ActivityCourse.class);
		for (Map.Entry<Long, Long> activityCourseIdMapEntry : activityCoursesIdsMap
			.entrySet()) {
			long activityCourseId = activityCourseIdMapEntry.getValue();
			ActivityCourse activityCourse = this.activityCourseLocalService
				.fetchActivityCourse(activityCourseId);
			activityCourse.setActivityId(importedActivity.getActivityId());
			this.activityCourseLocalService
				.updateActivityCourse(activityCourse);
		}

	}

}

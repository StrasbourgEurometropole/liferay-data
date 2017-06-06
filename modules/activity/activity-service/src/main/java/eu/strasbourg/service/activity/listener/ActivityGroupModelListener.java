package eu.strasbourg.service.activity.listener;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;

@Component(immediate = true, service = ModelListener.class)
public class ActivityGroupModelListener extends BaseModelListener<Group> {
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private ActivityLocalService activityLocalService;

	private ActivityCourseLocalService activityCourseLocalService;

	public void setActivityLocalService(
		ActivityLocalService activityLocalService) {
		this.activityLocalService = activityLocalService;
	}

	public void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {
		this.activityCourseLocalService = activityCourseLocalService;
	}

	/**
	 * A la suppression d'un groupe, on supprime les entités rattachées à ce
	 * groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {

		// Activités
		List<Activity> activitys = ActivityLocalServiceUtil
			.getByGroupId(model.getGroupId());
		for (Activity activity : activitys) {
			try {
				this.activityLocalService
					.removeActivity(activity.getPrimaryKey());
			} catch (PortalException e) {
				_log.error(e);
			}
		}

		// Cours
		List<ActivityCourse> activityCourses = ActivityCourseLocalServiceUtil
			.getByGroupId(model.getGroupId());
		for (ActivityCourse activityCourse : activityCourses) {
			try {
				this.activityCourseLocalService
					.removeActivityCourse(activityCourse.getPrimaryKey());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
	}

}
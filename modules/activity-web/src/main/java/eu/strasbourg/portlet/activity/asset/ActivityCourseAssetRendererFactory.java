package eu.strasbourg.portlet.activity.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO},
	service = AssetRendererFactory.class
)
public class ActivityCourseAssetRendererFactory extends BaseAssetRendererFactory<ActivityCourse> {
	
	public static final String TYPE = "activityCourse";

	public ActivityCourseAssetRendererFactory() {
		setClassName(ActivityCourse.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<ActivityCourse> getAssetRenderer(long classPK, int type)
		throws PortalException {
		ActivityCourse entry = _activityCourseLocalService.getActivityCourse(classPK);

		ActivityCourseAssetRenderer activityCourseAssetRenderer =
			new ActivityCourseAssetRenderer(entry);

		activityCourseAssetRenderer.setAssetRendererType(type);

		return activityCourseAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private ActivityCourseLocalService _activityCourseLocalService;

	@Reference(unbind = "-")
	protected void setActivityCourseLocalService(ActivityCourseLocalService activityCourseLocalService) {
		_activityCourseLocalService = activityCourseLocalService;
	}
}

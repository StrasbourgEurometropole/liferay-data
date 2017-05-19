package eu.strasbourg.portlet.activity.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO},
	service = AssetRendererFactory.class
)
public class ActivityAssetRendererFactory extends BaseAssetRendererFactory<Activity> {
	
	public static final String TYPE = "activity";

	public ActivityAssetRendererFactory() {
		setClassName(Activity.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Activity> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Activity entry = _activityLocalService.getActivity(classPK);

		ActivityAssetRenderer activityAssetRenderer =
			new ActivityAssetRenderer(entry);

		activityAssetRenderer.setAssetRendererType(type);

		return activityAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private ActivityLocalService _activityLocalService;

	@Reference(unbind = "-")
	protected void setActivityLocalService(ActivityLocalService activityLocalService) {
		_activityLocalService = activityLocalService;
	}
}

package eu.strasbourg.portlet.activity.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB},
	service = AssetRendererFactory.class
)
public class ActivityOrganizerAssetRendererFactory extends BaseAssetRendererFactory<ActivityOrganizer> {
	
	public static final String TYPE = "activityOrganizer";

	public ActivityOrganizerAssetRendererFactory() {
		setClassName(ActivityOrganizer.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<ActivityOrganizer> getAssetRenderer(long classPK, int type)
		throws PortalException {
		ActivityOrganizer entry = _activityOrganizerLocalService.getActivityOrganizer(classPK);

		ActivityOrganizerAssetRenderer activityOrganizerAssetRenderer =
			new ActivityOrganizerAssetRenderer(entry);

		activityOrganizerAssetRenderer.setAssetRendererType(type);

		return activityOrganizerAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private ActivityOrganizerLocalService _activityOrganizerLocalService;

	@Reference(unbind = "-")
	protected void setActivityOrganizerLocalService(ActivityOrganizerLocalService activityOrganizerLocalService) {
		_activityOrganizerLocalService = activityOrganizerLocalService;
	}
}

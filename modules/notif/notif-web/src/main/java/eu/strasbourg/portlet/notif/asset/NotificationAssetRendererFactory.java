package eu.strasbourg.portlet.notif.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.service.NotificationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.NOTIF_WEB},
	service = AssetRendererFactory.class
)
public class NotificationAssetRendererFactory extends BaseAssetRendererFactory<Notification> {

	private NotificationLocalService _notificationLocalService;

	public static final String TYPE = "notification";

	public NotificationAssetRendererFactory() {
		setClassName(Notification.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.NOTIF_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Notification> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Notification entry = _notificationLocalService.getNotification(classPK);

		eu.strasbourg.portlet.notif.asset.NotificationAssetRenderer offerAssetRenderer =
			new eu.strasbourg.portlet.notif.asset.NotificationAssetRenderer(entry);

		offerAssetRenderer.setAssetRendererType(type);

		return offerAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	


	@Reference(unbind = "-")
	protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {
		_notificationLocalService = notificationLocalService;
	}
}

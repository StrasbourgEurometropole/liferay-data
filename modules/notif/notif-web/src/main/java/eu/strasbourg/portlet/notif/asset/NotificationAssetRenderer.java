package eu.strasbourg.portlet.notif.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.notif.constants.BroadcastChannel;
import eu.strasbourg.service.notif.constants.TypeBroadcast;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class NotificationAssetRenderer extends BaseJSPAssetRenderer<Notification> {
	
	public static final String TYPE = "notification";
	private Notification _entry;
	
	public NotificationAssetRenderer(Notification entry) {
		_entry = entry;
	}
	
	@Override
	public Notification getAssetObject() {
		return _entry;
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _entry.getUuid();
	}

	@Override
	public String getClassName() {
		return Notification.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getNotificationId();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
				template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/notification/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	public boolean include(HttpServletRequest request,
						   HttpServletResponse response, String template) throws Exception {

		String serviceName = "";
		ServiceNotif serviceNotif = ServiceNotifLocalServiceUtil.fetchServiceNotif(this._entry.getServiceId());
		if(Validator.isNotNull(serviceNotif))
			serviceName = serviceNotif.getName();

		String natureName = "";
		NatureNotif natureNotif = NatureNotifLocalServiceUtil.fetchNatureNotif(this._entry.getNatureId());
		if(Validator.isNotNull(natureNotif))
			natureName = natureNotif.getName(Locale.FRANCE);

		String broadcastType = "";
		TypeBroadcast typeBroadcast = TypeBroadcast.get(this._entry.getTypeBroadcast());
		if(Validator.isNotNull(typeBroadcast))
			broadcastType = typeBroadcast.getLabel();

		String broadcastChannels = "";
		for(String id : this._entry.getBroadcastChannels().split(",")){
			BroadcastChannel broadcastChannel = BroadcastChannel.get(Long.parseLong(id));
			if(Validator.isNotNull(broadcastChannel)) {
				if(Validator.isNotNull(broadcastChannels))
					broadcastChannels += ", ";
				broadcastChannels += broadcastChannel.getLabel();
			}
		}

		request.setAttribute("entry", this._entry);
		request.setAttribute("service", serviceName);
		request.setAttribute("nature", natureName);
		request.setAttribute("broadcastType", broadcastType);
		request.setAttribute("broadcastChannels", broadcastChannels);
		request.setAttribute("detailPortletName", StrasbourgPortletKeys.ENTITY_DETAIL_WEB);

		return super.include(request, response, template);
	}

	public Notification getNotification() {
		return this._entry;
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return "Name: " + _entry.getTitle(portletRequest.getLocale());
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle(locale);
	}

}

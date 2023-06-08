package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.notif.constants.NotifConstants;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil;
import eu.strasbourg.service.notif.service.NotificationLocalServiceUtil;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.constants.RoleNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewNotificationsDisplayContext
	extends ViewListBaseDisplayContext<ServiceNotif> {
	private List<Notification> notifications;
	private ThemeDisplay themeDisplay;
	private String filter;
	private long[] serviceIds;

	public ViewNotificationsDisplayContext(RenderRequest request,
										   RenderResponse response,
										   String filter) {
		super(ServiceNotif.class, request, response);
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		this.filter = filter;
	}

	@SuppressWarnings("unused")
	public List<Notification> getNotifications() throws PortalException {

		int countResults = 0;

		if (this.notifications == null) {
			if(isAdminNotification())
				this.notifications = NotificationLocalServiceUtil.getNotifications(
						this.getSearchContainer().getStart(),
						this.getSearchContainer().getEnd());
			else{
				if(getServicesId().length > 0) {
					this.notifications = NotificationLocalServiceUtil.getByServiceIds(getServicesId());
				}
			}

			countResults = ServiceNotifLocalServiceUtil.getServiceNotifs(-1, -1).size();
		}
		this.getSearchContainer().setTotal(countResults);
		return this.notifications;
	}

	@SuppressWarnings("unused")
	public List<Notification> getInProgressNotifications() {
		return NotificationLocalServiceUtil.getInProgressNotifications();
	}

	@SuppressWarnings("unused")
	public List<Notification> getToComeNotifications() {
		return NotificationLocalServiceUtil.getToComeNotifications();
	}

	@SuppressWarnings("unused")
	public List<Notification> getPastNotifications() {
		return NotificationLocalServiceUtil.getPastNotifications();
	}

	public long[] getServicesId() throws PortalException{
		if(Validator.isNull(this.serviceIds)) {
			long[] organisationIds = themeDisplay.getUser().getOrganizationIds();
			if (organisationIds.length > 0) {
				List<ServiceNotif> services = ServiceNotifLocalServiceUtil.getByOrganisationIds(organisationIds);
				this.serviceIds = services.stream().mapToLong(ServiceNotif::getServiceId).toArray();
			}
		}
		return this.serviceIds;
	}

	@SuppressWarnings("unused")
	public boolean hasMultipleServices() throws PortalException{
		return getServicesId().length > 1;
	}

	@SuppressWarnings("unused")
	public String getService(long serviceId){
		ServiceNotif service = ServiceNotifLocalServiceUtil.fetchServiceNotif(serviceId);
		if(Validator.isNotNull(service))
			return service.getName();
		return "";
	}

	@SuppressWarnings("unused")
	public String getNature(long natureId){
		NatureNotif nature = NatureNotifLocalServiceUtil.fetchNatureNotif(natureId);
		if(Validator.isNotNull(nature))
			return nature.getName();
		return "";
	}

	@SuppressWarnings("unused")
	public boolean canUpdateOrDeleteNotification(long createUserId){
		if(isContribOnly()) {
			return this.themeDisplay.getUserId() == createUserId;
		}
		return true;
	}

	public boolean isAdminNotification(){
		try {
			Role siteAdministrator = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.ADMINISTRATEUR_NOTIFICATION);
			if(themeDisplay.getPermissionChecker().isOmniadmin()
				|| UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), siteAdministrator.getRoleId()))
				return true;
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return false;
	}


	public boolean isRespNotification(){
		try {
			Role  responsableNotification = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.RESPONSABLE_NOTIFICATION);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), responsableNotification.getRoleId());
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return false;
	}


	public boolean isContribOnly(){
		try {
			if(isAdminNotification() ||isRespNotification())
				return false;

			Role contributorNotification = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.CONTRIBUTEUR_NOTIFICATION);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), contributorNotification.getRoleId());
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return false;
	}

	@SuppressWarnings("unused")
	public String getFilter() {
		return filter;
	}

	@SuppressWarnings("unused")
	public String getALL() {
		return NotifConstants.ALL;
	}

	@SuppressWarnings("unused")
	public String getIN_PROGRESS() {
		return NotifConstants.IN_PROGRESS;
	}

	@SuppressWarnings("unused")
	public String getTO_COME() {
		return NotifConstants.TO_COME;
	}

	@SuppressWarnings("unused")
	public String getPAST() {
		return NotifConstants.PAST;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	@SuppressWarnings("unused")
	public boolean hasPermission(String actionId) {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.NOTIF_BO, StrasbourgPortletKeys.NOTIF_BO,
			actionId);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}

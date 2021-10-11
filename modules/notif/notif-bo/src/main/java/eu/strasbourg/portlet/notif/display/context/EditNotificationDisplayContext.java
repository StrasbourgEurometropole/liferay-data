package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.notif.constants.BroadcastChannel;
import eu.strasbourg.service.notif.constants.SendStatus;
import eu.strasbourg.service.notif.constants.TypeBroadcast;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.MessageLocalServiceUtil;
import eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil;
import eu.strasbourg.service.notif.service.NotificationLocalServiceUtil;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.constants.RoleNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.List;

public class EditNotificationDisplayContext {

    private Notification notification;
    private List<ServiceNotif> services;
    private List<NatureNotif> natures;
    private List<Message> messages;
    private List<TypeBroadcast> broadcastTypes;
    private List<BroadcastChannel> broadcastChannels;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditNotificationDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    @SuppressWarnings("unused")
    public Notification getNotification() {
        if (this.notification == null) {
            long notificationId = ParamUtil.getLong(this.request, "notificationId");
            if (notificationId > 0) {
                this.notification = NotificationLocalServiceUtil.fetchNotification(notificationId);
            }
            String isDuplication = ParamUtil.getString(this.request, "isDuplication");
            if (isDuplication.equals("true")) {
                this.notification.setNew(true);
                this.notification.setStatus(WorkflowConstants.STATUS_DRAFT);
            }
        }
        return notification;
    }

    @SuppressWarnings("unused")
    public List<ServiceNotif> getServices() {
        if (this.services == null) {
            try {
                long[] organisationIds = themeDisplay.getUser().getOrganizationIds();
                if(Validator.isNotNull(organisationIds) && organisationIds.length > 0)
                    this.services = ServiceNotifLocalServiceUtil.getByOrganisationIds(organisationIds);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        return services;
    }

    @SuppressWarnings("unused")
    public List<NatureNotif> getNatures() {
        if (this.natures == null) {
            this.natures = NatureNotifLocalServiceUtil.getNatureNotifs(-1, -1);
        }
        return natures;
    }

    @SuppressWarnings("unused")
    public List<Message> getMessages() {
        if (this.messages == null) {
            this.messages = MessageLocalServiceUtil.getMessages(-1, -1);
        }
        return messages;
    }

    @SuppressWarnings("unused")
    public List<TypeBroadcast> getBroadcastTypes() {
        if (this.broadcastTypes == null) {
            this.broadcastTypes = TypeBroadcast.getAll();
        }
        return broadcastTypes;
    }

    @SuppressWarnings("unused")
    public List<BroadcastChannel> getBroadcastChannels() {
        if (this.broadcastChannels == null) {
            this.broadcastChannels = BroadcastChannel.getAll();
        }
        return broadcastChannels;
    }

    @SuppressWarnings("unused")
    public String getStatusByField(String field) {
        String status = "";

        long statusId = 0;
        switch (field){
           case "sendStatusCsmap":
               statusId = this.notification.getSendStatusCsmap();
           case "sendStatusTwitter":
               statusId = this.notification.getSendStatusTwitter();
           case "sendStatusMonst":
               statusId = this.notification.getSendStatusMonst();
           case "sendStatusMail":
               statusId = this.notification.getSendStatusMail();
           case "sendStatusSegur":
               statusId = this.notification.getSendStatusSegur();
        }

        SendStatus sendStatus = SendStatus.get(statusId);
        if(Validator.isNotNull(sendStatus))
            status = sendStatus.getLabel();

        return status;
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
            Role siteAdministrator = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.SITE_ADMLINISTRATOR);
            if(themeDisplay.getPermissionChecker().isOmniadmin()
                    || UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), siteAdministrator.getRoleId()))
                return true;
        } catch (PortalException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isRespNotification(){
        try {
            Role  responsableNotification = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.RESPONSABLE_NOTIFICATION);
            return UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), responsableNotification.getRoleId());
        } catch (PortalException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                ServiceNotif.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return this.themeDisplay.getPermissionChecker().hasPermission(
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.NOTIF_BO,
                StrasbourgPortletKeys.NOTIF_BO, actionId);
    }
}

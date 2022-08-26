package eu.strasbourg.portlet.notif.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.RoleNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.Date;
import java.util.List;

public class EditNotificationDisplayContext {

    private Notification notification;
    private Boolean isOnlyView;
    private List<ServiceNotif> services;
    private List<NatureNotif> natures;
    private List<Message> messages;
    private List<TypeBroadcast> broadcastTypes;
    private List<AssetCategory> districts;
    private List<BroadcastChannel> broadcastChannels;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditNotificationDisplayContext(RenderRequest request, Notification notification, List<ServiceNotif> services,
          List<NatureNotif> natures, List<Message> messages) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        this.notification = notification;
        this.services = services;
        this.natures = natures;
        this.messages = messages;
        String isDuplication = ParamUtil.getString(this.request, "isDuplication");
        if (isDuplication.equals("true")) {
            this.notification.setNew(true);
            this.notification.setUserId(this.themeDisplay.getUserId());
            this.notification.setBroadcastDate(null);
            this.notification.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
    }

    @SuppressWarnings("unused")
    public Notification getNotification() {
        return this.notification;
    }

    @SuppressWarnings("unused")
    public List<ServiceNotif> getServices() {
        return this.services;
    }

    @SuppressWarnings("unused")
    public List<NatureNotif> getNatures() {
        return this.natures;
    }

    @SuppressWarnings("unused")
    public List<Message> getMessages() {
        return this.messages;
    }

    @SuppressWarnings("unused")
    public List<TypeBroadcast> getBroadcastTypes() {
        if (this.broadcastTypes == null) {
            this.broadcastTypes = TypeBroadcast.getAll();
        }
        return broadcastTypes;
    }

    @SuppressWarnings("unused")
    public List<AssetCategory> getDistricts() {
        if (this.districts == null) {
            String CITY_NAME = "Strasbourg";
            this.districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
        }
        return districts;
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
               break;
           case "sendStatusTwitter":
               statusId = this.notification.getSendStatusTwitter();
               break;
           case "sendStatusMonst":
               statusId = this.notification.getSendStatusMonst();
               break;
           case "sendStatusMail":
               statusId = this.notification.getSendStatusMail();
               break;
           case "sendStatusSegur":
               statusId = this.notification.getSendStatusSegur();
               break;
        }

        SendStatus sendStatus = SendStatus.get(statusId);
        if(Validator.isNotNull(sendStatus))
            status = sendStatus.getLabel();

        return status;
    }

    @SuppressWarnings("unused")
    public Boolean isOnlyView() {
        if (this.isOnlyView == null) {
            this.isOnlyView = false;
            if(Validator.isNotNull(this.notification) && Validator.isNotNull(this.notification.getBroadcastDate()))
                if(!canUpdateOrDeleteNotification() || this.notification.getBroadcastDate().before(new Date()))
                    this.isOnlyView = true;
        }
        return this.isOnlyView;
    }

    @SuppressWarnings("unused")
    public boolean canUpdateOrDeleteNotification(){
        if(isContribOnly()) {
            return this.themeDisplay.getUserId() == this.notification.getUserId();
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

    private final Log _log = LogFactoryUtil.getLog(this.getClass());
}

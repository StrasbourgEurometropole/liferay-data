package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.MessageLocalService;
import eu.strasbourg.service.notif.service.NatureNotifLocalService;
import eu.strasbourg.service.notif.service.ServiceNotifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import java.util.List;

public class EditServiceDisplayContext {

    private ServiceNotif service;
    private List<Organization> organizations;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditServiceDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public ServiceNotif getService() {
        if (this.service == null) {
            long serviceId = ParamUtil.getLong(this.request, "serviceId");
            if (serviceId > 0) {
                this.service = _serviceNotifLocalService.fetchServiceNotif(serviceId);
            }
        }
        return service;
    }

    public List<Organization> getOrganizations() {
        if (this.organizations == null) {
            this.organizations = _organizationLocalService.getOrganizations(-1, -1);
        }
        return organizations;
    }

    public String getDefaultNatureIndexes() {
        if(this.service != null){
            String indexes = "0";
            List<NatureNotif> natures = _natureNotifLocalService.getByServiceid(this.service.getServiceId());
            for (int i = 1; i < natures.size(); i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
    }

    public String getDefaultMessageIndexes() {
        if(this.service != null){
            String indexes = "0";
            List<Message> messages = _messageLocalService.getByServiceid(this.service.getServiceId());
            for (int i = 1; i < messages.size(); i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
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

    private OrganizationLocalService _organizationLocalService;

    @Reference(unbind = "-")
    protected void setOrganizationLocalService(OrganizationLocalService organizationLocalService) {
        _organizationLocalService = organizationLocalService;
    }

    private ServiceNotifLocalService _serviceNotifLocalService;

    @Reference(unbind = "-")
    protected void setServiceNotifLocalService(ServiceNotifLocalService serviceNotifLocalService) {
        _serviceNotifLocalService = serviceNotifLocalService;
    }

    private NatureNotifLocalService _natureNotifLocalService;

    @Reference(unbind = "-")
    protected void setNatureNotifLocalService(NatureNotifLocalService natureNotifLocalService) {
        _natureNotifLocalService = natureNotifLocalService;
    }

    private MessageLocalService _messageLocalService;

    @Reference(unbind = "-")
    protected void setMessageLocalService(MessageLocalService messageLocalService) {
        _messageLocalService = messageLocalService;
    }
}

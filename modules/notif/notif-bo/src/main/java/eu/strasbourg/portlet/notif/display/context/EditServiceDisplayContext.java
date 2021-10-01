package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.MessageLocalServiceUtil;
import eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.constants.OrganizationNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

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
                this.service = ServiceNotifLocalServiceUtil.fetchServiceNotif(serviceId);
            }
        }
        return service;
    }

    @SuppressWarnings("unused")
    public List<Organization> getOrganizations() {
        if (this.organizations == null) {
            try {
                Organization parentOrganization = OrganizationLocalServiceUtil.getOrganization(themeDisplay.getCompanyId(), OrganizationNames.SERVICES_NOTIFICATION);
                this.organizations = parentOrganization.getSuborganizations();
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        return organizations;
    }

    @SuppressWarnings("unused")
    public String getDefaultNaturesIndexes() {
        if(this.service != null){
            StringBuilder indexes = new StringBuilder("0");
            List<NatureNotif> natures = NatureNotifLocalServiceUtil.getByServiceid(this.service.getServiceId());
            for (int i = 1; i < natures.size(); i++) {
                indexes.append(",").append(i);
            }
            return indexes.toString();
        }
        return "";
    }

    @SuppressWarnings("unused")
    public String getDefaultMessagesIndexes() {
        if(this.service != null){
            StringBuilder indexes = new StringBuilder("0");
            List<Message> messages = MessageLocalServiceUtil.getByServiceid(this.service.getServiceId());
            for (int i = 1; i < messages.size(); i++) {
                indexes.append(",").append(i);
            }
            return indexes.toString();
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
}

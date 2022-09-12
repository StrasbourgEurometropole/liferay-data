package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.constants.OrganizationNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.List;

public class EditServiceDisplayContext {

    private ServiceNotif service;
    private List<Organization> organizations;
    private List<NatureNotif> natures;
    private List<Message> messages;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditServiceDisplayContext(RenderRequest request, ServiceNotif service, List<NatureNotif> natures,
                             List<Message> messages ) {
        this.request = request;
        this.service = service;
        this.natures = natures;
        this.messages = messages;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public ServiceNotif getService() {
        return this.service;
    }

    @SuppressWarnings("unused")
    public List<Organization> getOrganizations() {
        if (this.organizations == null) {
            try {
                Organization parentOrganization = OrganizationLocalServiceUtil.getOrganization(themeDisplay.getCompanyId(), OrganizationNames.SERVICES_NOTIFICATION);
                this.organizations = parentOrganization.getSuborganizations();
            } catch (PortalException e) {
                _log.error(e.getMessage(), e);
            }
        }
        return organizations;
    }

    @SuppressWarnings("unused")
    public String getDefaultNaturesIndexes() {
        if(this.service != null){
            StringBuilder indexes = new StringBuilder("0");
            for (int i = 1; i < this.natures.size(); i++) {
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
            for (int i = 1; i < this.messages.size(); i++) {
                indexes.append(",").append(i);
            }
            return indexes.toString();
        }
        return "";
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

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

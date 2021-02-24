package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class EditHelpRequestDisplayContext {

    private HelpRequest helpRequest;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditHelpRequestDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    @SuppressWarnings("unused")
    public HelpRequest getHelpRequest() {
        long helpRequestId = ParamUtil.getLong(request, "helpRequestId");
        if (helpRequest == null && helpRequestId > 0) {
            helpRequest = HelpRequestLocalServiceUtil.fetchHelpRequest(helpRequestId);
        }
        return helpRequest;
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                themeDisplay.getCompanyId(), themeDisplay.getCompanyGroupId(),
                HelpRequest.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return themeDisplay.getPermissionChecker().hasPermission(
                themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.HELP_BO, StrasbourgPortletKeys.HELP_BO,
                actionId);
    }

}

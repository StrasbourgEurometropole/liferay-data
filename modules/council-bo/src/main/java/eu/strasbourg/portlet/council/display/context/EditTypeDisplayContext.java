package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;

public class EditTypeDisplayContext {

    private Type type;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditTypeDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Type getType() {
        long typeId = ParamUtil.getLong(this.request, "typeId");
        if (this.type == null && typeId > 0) {
            this.type = TypeLocalServiceUtil.fetchType(typeId);
        }
        return type;
    }

    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                CouncilSession.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return this.themeDisplay.getPermissionChecker().hasPermission(
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.COUNCIL_BO,
                StrasbourgPortletKeys.COUNCIL_BO, actionId);
    }

}

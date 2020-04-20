package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class EditCouncilSessionDisplayContext {

    private CouncilSession councilSession;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditCouncilSessionDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public CouncilSession getCouncilSession() {
        long councilSessionId = ParamUtil.getLong(this.request, "councilSessionId");
        if (councilSession == null && councilSessionId > 0) {
            councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
        }
        return councilSession;
    }

    @SuppressWarnings("unused")
    public String getDefaultProcurationIndexes() {
        if (this.getCouncilSession() != null) {
            List<Procuration> procurations = this.getCouncilSession().getProcurations();
            String indexes = "0";
            for (int i = 1; i <= procurations.size(); i++) {
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

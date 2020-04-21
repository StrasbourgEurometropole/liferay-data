package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class EditDeliberationDisplayContext {

    private Deliberation deliberation;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditDeliberationDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Deliberation getDeliberation() {
        long deliberationId = ParamUtil.getLong(this.request, "deliberationId");
        if (deliberation == null && deliberationId > 0) {
            deliberation = DeliberationLocalServiceUtil.fetchDeliberation(deliberationId);
        }
        return deliberation;
    }

    /**
     * Récupère la liste de tous les conseils à venir (et on rajoute le conseil déjà enregistré pour la délib à la liste)
     * @return
     */
    public List<CouncilSession> getAvailableCouncilSessions() {
        List<CouncilSession> availableSessions = new ArrayList<>();

        return availableSessions;
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

package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.constants.ProcurationPresentialEnum;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.ArrayList;
import java.util.List;

public class ManageProcurationsDisplayContext {

    private List<Procuration> procuration;
    private CouncilSession councilSession;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public ManageProcurationsDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public CouncilSession getCouncilSession() {
        long councilSessionId = ParamUtil.getLong(this.request, "councilSessionId");
        if (this.councilSession == null && councilSessionId > 0) {
            this.councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
        }
        return councilSession;
    }

    /**
     * Renvoie les types de conseil enfonction des droit de l'utilisateur
     */
    @SuppressWarnings("unused")
    public List<Type> getAuthorizedTypes() {
        List<Type> authorizedTypes = new ArrayList<>();
        List<Type> alltypes = TypeLocalServiceUtil.getTypes(-1, -1);
        List<Long> authorizedId = UserRoleType.typeIdsForUser(themeDisplay);

        for (Type type: alltypes) {
            if(authorizedId.contains(type.getTypeId())) {
                authorizedTypes.add(type);
            }
        }

        return authorizedTypes;
    }

    /**
     * Renvoie tous les élus actifs
     */
    @SuppressWarnings("unused")
    public List<Official> getAllActiveOfficials() {
        return OfficialLocalServiceUtil.findByGroupIdAndIsActive(this.themeDisplay.getSiteGroupId(), true);
    }

    /**
     * Recherche s'il existe une procuration dans la session courrante pour l'élu donné
     * @return la procuration ou null
     */
    @SuppressWarnings("unused")
    public Procuration findAssociatedProcuration(long officialId) {
        List<Procuration> procurations = new ArrayList<>();

        if(this.getCouncilSession() != null)
            procurations = this.getCouncilSession().getProcurations();

        return procurations.stream()
                .filter(procuration -> procuration.getOfficialUnavailableId() == officialId)
                .findFirst()
                .orElse(null);
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

    /**
     * Recherche le type de presentiel
     */
    @SuppressWarnings("unused")
    public String getProcurationPresential(int presential) {
        return ProcurationPresentialEnum.get(presential).getName();
    }

}

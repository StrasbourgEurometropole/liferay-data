package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialTypeCouncilLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.List;

public class EditOfficialDisplayContext {

    private Official official;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditOfficialDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Official getOfficial() {
        long officialId = ParamUtil.getLong(this.request, "officialId");
        if (this.official == null && officialId > 0) {
            this.official = OfficialLocalServiceUtil.fetchOfficial(officialId);
        }
        return official;
    }

    public List<Type> getTypes() {
        return TypeLocalServiceUtil.getTypes(-1,-1);
    }

    public Boolean hasTypeCouncil(long typeId) {
        OfficialTypeCouncil type = null;
        if(official != null) {
            type = OfficialTypeCouncilLocalServiceUtil.findByTypeIdandOfficialId(typeId, official.getOfficialId());
        }
        Boolean hasType = Validator.isNotNull(type);
        return hasType;

    }

    public List<Long> getTypeIdsForUser() {
        return UserRoleType.typeIdsForUser(themeDisplay);
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                Official.class.getName());
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

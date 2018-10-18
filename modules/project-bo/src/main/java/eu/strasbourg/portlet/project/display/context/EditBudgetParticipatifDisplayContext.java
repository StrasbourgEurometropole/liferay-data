package eu.strasbourg.portlet.project.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alexandre.quere
 */
public class EditBudgetParticipatifDisplayContext {
    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private BudgetParticipatif _budgetParticipatif;
    private List<AssetCategory> _cities;
    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    public EditBudgetParticipatifDisplayContext(RenderRequest request, RenderResponse response) {
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public BudgetParticipatif getBudgetParticipatif() {
        long budgetParticipatifId = ParamUtil.getLong(_request, "budgetParticipatifId");
        if (_budgetParticipatif == null && budgetParticipatifId > 0)
            _budgetParticipatif = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(budgetParticipatifId);
        return _budgetParticipatif;
    }

    /**
     * Retourne la liste des villes
     */
    public List<AssetCategory> getCities() throws PortalException {
        if (Validator.isNull(this._cities)) {
            AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.TERRITORY);
            if (territoriesVocabulary != null) {
                this._cities = territoriesVocabulary.getCategories().stream().filter(c -> {
                    try {
                        return c.getAncestors().size() == 1;
                    } catch (Exception e) {
                        _log.error("le budget : " + _budgetParticipatif,e);
                        return false;
                    }
                }).collect(Collectors.toList());
            }
        }
        return this._cities;
    }

    public Locale[] getAvailableLocales() {
        Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
        return availableLocalesSet
                .toArray(new Locale[availableLocalesSet.size()]);
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                _themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
                Petition.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
                actionId);
    }

}

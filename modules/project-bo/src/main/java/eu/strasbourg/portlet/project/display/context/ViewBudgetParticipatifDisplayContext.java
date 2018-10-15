package eu.strasbourg.portlet.project.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandre.quere
 */
public class ViewBudgetParticipatifDisplayContext extends ViewListBaseDisplayContext<BudgetParticipatif> {

    private List<BudgetParticipatif> _budgetParticipatifs;

    public ViewBudgetParticipatifDisplayContext(RenderRequest request, RenderResponse response) {
        super(BudgetParticipatif.class, request, response);
    }

    public List<BudgetParticipatif> getBudgetParticipatifs()throws PortalException{
        if (this._budgetParticipatifs==null){
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());
            this._budgetParticipatifs = createObjectList(hits);
        }
        return this._budgetParticipatifs;
    }

    private List<BudgetParticipatif> createObjectList(Hits hits) {
        //création de la liste d'objet
        List<BudgetParticipatif> results = new ArrayList<>();
        if (hits != null){
            for (Document document : hits.getDocs()){
                BudgetParticipatif budget = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (budget != null)
                    results.add(budget);
            }
        }
        return results;
    }
    /**
     * Retourne la liste des PK de tous les budgets
     * @return liste de PK (ex: "1,5,7,8")
     */
    public String getBudgetParticipatifsIds() throws PortalException {
        StringBuilder budgetIds = new StringBuilder();
        for (BudgetParticipatif budgetParticipatif : this.getBudgetParticipatifs()) {
            if (budgetIds.length() > 0) {
                budgetIds.append(",");
            }
            budgetIds.append(budgetParticipatif.getBudgetParticipatifId());
        }
        return budgetIds.toString();
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

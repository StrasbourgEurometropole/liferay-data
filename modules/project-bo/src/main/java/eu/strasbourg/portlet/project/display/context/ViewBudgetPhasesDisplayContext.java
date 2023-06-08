package eu.strasbourg.portlet.project.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewBudgetPhasesDisplayContext extends ViewListBaseDisplayContext<BudgetPhase> {

	private List <BudgetPhase> _budgetPhases;
	
	public ViewBudgetPhasesDisplayContext(RenderRequest request, RenderResponse response) {
		super(BudgetPhase.class, request, response);
	}

	public List<BudgetPhase> getBudgetPhases() throws PortalException {
		if (this._budgetPhases == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<BudgetPhase> results = new ArrayList<BudgetPhase>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					BudgetPhase budgetPhase = BudgetPhaseLocalServiceUtil.fetchBudgetPhase(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (budgetPhase != null) {
						results.add(budgetPhase);
					}
				}
			}
			this._budgetPhases = results;
		}
		return this._budgetPhases;
	}
	
	
	/**
	 * Retourne la liste des phases correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<BudgetPhase> getAllBudgetPhases() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());
		
		// Création de la liste d'objet
		List<BudgetPhase> results = new ArrayList<BudgetPhase>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				BudgetPhase budgetPhase = BudgetPhaseLocalServiceUtil.fetchBudgetPhase(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (budgetPhase != null) {
					results.add(budgetPhase);
				}
			}
		}
		return results;
	}
	
	/**
	 * Retourne la liste des PK de toutes les phases
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllBudgetPhaseIds() throws PortalException {
		String budgetPhaseIds = "";
		for (BudgetPhase budgetPhase : this.getAllBudgetPhases()) {
			if (budgetPhaseIds.length() > 0) {
				budgetPhaseIds += ",";
			}
			budgetPhaseIds += budgetPhase.getBudgetPhaseId();
		}
		return budgetPhaseIds;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PROJECT_BO, 
			StrasbourgPortletKeys.PROJECT_BO,
			actionId);
	}
	
}

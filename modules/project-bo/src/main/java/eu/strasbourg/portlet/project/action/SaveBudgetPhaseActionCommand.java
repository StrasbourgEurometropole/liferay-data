package eu.strasbourg.portlet.project.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetPhaseLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { 
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=saveBudgetPhase"
	},
	service = MVCActionCommand.class
)
public class SaveBudgetPhaseActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {
		
		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			
			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request
					.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request,
					portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
				returnURL.setParameter("tab", request.getParameter("tab"));
				
				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("cmd", "editBudgetPhase");
				response.setRenderParameter("mvcPath","/project-bo-edit-budget-phase.jsp");
				return false;
			}

			// Edition du projet
			long budgetPhaseId = ParamUtil.getLong(request, "budgetPhaseId");
			BudgetPhase budgetPhase;
			if (budgetPhaseId == 0) {
				budgetPhase = _budgetPhaseLocalService.createBudgetPhase(sc);
			} else {
				budgetPhase = _budgetPhaseLocalService.getBudgetPhase(budgetPhaseId);
			}
			
			// Défini le format de date à utiliser pour les champs temporels 
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			String title = ParamUtil.getString(request, "title");
			budgetPhase.setTitle(title);

			// Description
			String description = ParamUtil.getString(request, "description");
			budgetPhase.setDescription(description);

			// ---------------------------------------------------------------
			// -------------------------- Gestion de la phase ----------------
			// ---------------------------------------------------------------
			
			// Label
			Long numberOfVote = ParamUtil.getLong(request, "numberOfVote");
			budgetPhase.setNumberOfVote(numberOfVote);
			
			// Est active ?
			Boolean isActive = ParamUtil.getBoolean(request, "isActive");
			budgetPhase.setIsActive(isActive);
			
			// ---------------------------------------------------------------
			// -------------------------- Période de dépôt -------------------
			// ---------------------------------------------------------------
			
			// Date de début 
			Date beginDate = ParamUtil.getDate(request, "beginDate", dateFormat);
			budgetPhase.setBeginDate(beginDate);
			
			// Date de fin
			Date endDate = ParamUtil.getDate(request, "endDate", dateFormat);
			budgetPhase.setEndDate(endDate);
			
			// ---------------------------------------------------------------
			// -------------------------- Période de vote --------------------
			// ---------------------------------------------------------------
			
			// Date de début
			Date beginVoteDate = ParamUtil.getDate(request, "beginVoteDate", dateFormat);
			budgetPhase.setBeginVoteDate(beginVoteDate);
			
			// Date de fin
			Date endVoteDate = ParamUtil.getDate(request, "endVoteDate", dateFormat);
			budgetPhase.setEndVoteDate(endVoteDate);

			_budgetPhaseLocalService.updateBudgetPhase(budgetPhase, sc);

		} catch (PortalException e) {
			_log.error(e);
		}
        return true;
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = new Long(themeDisplay.getLayout().getGroupId());
		long budgetPhaseId = ParamUtil.getLong(request, "budgetPhaseId");
		
		boolean isValid = true;
		// Défini le format de date à utiliser pour les champs temporels 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Nom de la phase
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}
		
		// Description 
		if (Validator.isNull(ParamUtil.getString(request, "description"))) {
			SessionErrors.add(request, "description-error");
			isValid = false;
		}
		
		Date beginDate = ParamUtil.getDate(request, "beginDate", dateFormat);
		Date endDate = ParamUtil.getDate(request, "endDate", dateFormat);
		Date beginVoteDate = ParamUtil.getDate(request, "beginVoteDate", dateFormat);
		Date endVoteDate = ParamUtil.getDate(request, "endVoteDate", dateFormat);
		
		// Dates de depot (1: non-null, 2: debuts avant fins)
		if (Validator.isNull(beginDate) || Validator.isNull(endDate) 
				|| Validator.isNull(beginVoteDate) || Validator.isNull(endVoteDate)) {
			SessionErrors.add(request, "dates-error");
			isValid = false;
		} else if (beginDate.compareTo(endDate) >= 0 || beginVoteDate.compareTo(endVoteDate) >= 0){
			SessionErrors.add(request, "dates-error");
			isValid = false;
		}
		
		// Verifie si il n'y a pas une autre phase en statut actif si celle courante l'est
		Boolean isActive = ParamUtil.getBoolean(request, "isActive");
		if (isActive) {
			List<BudgetPhase> activeBudgets = _budgetPhaseLocalService.getByIsActiveAndGroupId(isActive, groupId);
			
			if (activeBudgets.size() > 0) {
				// Si c'est une nouvelle phase
				if (budgetPhaseId == 0) {
					SessionErrors.add(request, "is-active-error");
					isValid = false;
				}
				// Sinon, s'il y'a plus de une phase active ou que la premiere trouvee ne correspond pas 
				// a la courrante, il y'a un souci de coherance de l'action menee
				else if (activeBudgets.size() > 1 || activeBudgets.get(0).getBudgetPhaseId() != budgetPhaseId) {
						SessionErrors.add(request, "is-active-error");
						isValid = false;
				}
			}
		}
		
		return isValid;
	}

    @Reference(unbind = "-")
	protected void setBudgetPhaseLocalService(BudgetPhaseLocalService budgetPhaseLocalService) {
		_budgetPhaseLocalService = budgetPhaseLocalService;
	}
	
	private BudgetPhaseLocalService _budgetPhaseLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

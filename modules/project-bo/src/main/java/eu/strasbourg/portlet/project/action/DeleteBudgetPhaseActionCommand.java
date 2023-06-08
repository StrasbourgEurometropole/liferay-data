package eu.strasbourg.portlet.project.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.project.service.BudgetPhaseLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=deleteBudgetPhase"
	},
	service = MVCActionCommand.class
)
public class DeleteBudgetPhaseActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		try {
			long budgetPhaseId = ParamUtil.getLong(request, "budgetPhaseId");
			_budgetPhaseLocalService.removeBudgetPhase(budgetPhaseId);
		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}

	@Reference(unbind = "-")
	protected void setBudgetPhaseLocalService(BudgetPhaseLocalService budgetPhaseLocalService) {
		_budgetPhaseLocalService = budgetPhaseLocalService;
	}
	
	private BudgetPhaseLocalService _budgetPhaseLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

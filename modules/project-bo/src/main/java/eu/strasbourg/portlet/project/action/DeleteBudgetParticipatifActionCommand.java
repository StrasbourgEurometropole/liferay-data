package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
                "mvc.command.name=deleteBudgetParticipatif"
        },
        service = MVCActionCommand.class
)
public class DeleteBudgetParticipatifActionCommand implements MVCActionCommand  {

    private BudgetParticipatifLocalService _budgetParticipatifLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            long budgetParticipatifId = ParamUtil.getLong(request, "budgetParticipatifId");
            _budgetParticipatifLocalService.removeBudgetParticipatif(budgetParticipatifId);
        } catch (PortalException e) {
            _log.error(e);
        }
        return true;
    }

    @Reference(unbind = "-")
    protected void setBudgetParticipatifLocalService(BudgetParticipatifLocalService budgetParticipatifLocalService) {
        _budgetParticipatifLocalService = budgetParticipatifLocalService;
    }

}

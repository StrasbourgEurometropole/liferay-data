package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.constants.ParticiperCategories;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
                "mvc.command.name=selectionBudgetParticipatifAction"
        },
        service = MVCActionCommand.class
)
public class SelectionBudgetParticipatifActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest,
                                 ActionResponse actionResponse) throws PortletException {

        try {
            long[] selectionIds = StringUtil
                    .split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);
            
            ServiceContext sc = ServiceContextFactory.getInstance(actionRequest);
            
            for (long entryId : selectionIds) {
            	
            	BudgetParticipatif budgetParticipatif = _budgetParticipatifLocalService.getBudgetParticipatif(entryId);
            	
                switch (ParamUtil.getString(actionRequest, "cmd")) {
                    case "acceptable":
                            budgetParticipatif.setBPStatus(budgetParticipatif, ParticiperCategories.BP_ACCEPTABLE, sc.getScopeGroupId());
                        break;
                    case "feasible":
                        budgetParticipatif.setBPStatus(budgetParticipatif, ParticiperCategories.BP_FEASIBLE, sc.getScopeGroupId());
                        break;
                    case "realized":
                        budgetParticipatif.setBPStatus(budgetParticipatif, ParticiperCategories.BP_REALIZED, sc.getScopeGroupId());
                        break;
                    case "laureat":
                        budgetParticipatif.setBPStatus(budgetParticipatif, ParticiperCategories.BP_LAUREAT, sc.getScopeGroupId());
                        break;
                }
                _budgetParticipatifLocalService.updateBudgetParticipatif(budgetParticipatif);
            }
        } catch (PortalException e) {
            _log.error(e);
        }
        return false;
    }

    @Reference(unbind = "-")
    protected void setBudgetParticipatifLocalService(BudgetParticipatifLocalService budgetParticipatifLocalService) {
        _budgetParticipatifLocalService = budgetParticipatifLocalService;
    }

    private BudgetParticipatifLocalService _budgetParticipatifLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

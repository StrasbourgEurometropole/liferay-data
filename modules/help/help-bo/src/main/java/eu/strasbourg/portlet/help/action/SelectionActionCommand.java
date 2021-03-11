package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=selectionAction"
        },
        service = MVCActionCommand.class
)
public class SelectionActionCommand implements MVCActionCommand {
    private static final String HELPS = "helpProposals";

    @Override
    public boolean processAction(ActionRequest actionRequest,
                                 ActionResponse actionResponse) throws PortletException {

        String tab = ParamUtil.getString(actionRequest, "tab");

        try {
            long[] selectionIds = StringUtil
                    .split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

            for (long entryId : selectionIds) {
                switch (ParamUtil.getString(actionRequest, "cmd")) {
                    case "delete":
                        if (tab.equals(HELPS)) {
                            _helpProposalLocalService.removeHelpProposal(entryId);
                        }
                        break;
                    case "publish":
                        if (tab.equals(HELPS)) {
                            HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(entryId);
                            _helpProposalLocalService.updateStatus(helpProposal, WorkflowConstants.STATUS_APPROVED);
                        }
                        break;
                    case "unpublish":
                        if (tab.equals(HELPS)) {
                            HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(entryId);
                            _helpProposalLocalService.updateStatus(helpProposal, WorkflowConstants.STATUS_DRAFT);
                        }
                        break;
                }
            }
        } catch (PortalException e) {
            _log.error(e);
        }
        return false;
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

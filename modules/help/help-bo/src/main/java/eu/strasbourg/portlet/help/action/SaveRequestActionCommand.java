package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
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
                "mvc.command.name=saveHelpRequest"
        },
        service = MVCActionCommand.class
)
public class SaveRequestActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse actionResponse) throws PortletException {

        try {
            // Edition de la demande
            long helpRequestId = ParamUtil.getLong(request, "helpRequestId");
            HelpRequest helpRequest = _helpRequestLocalService.getHelpRequest(helpRequestId);
            Long imageId = ParamUtil.getLong(request, "seeker-proof");
            helpRequest.setStudentCardImageId(imageId);

            _helpRequestLocalService.updateHelpRequest(helpRequest);
            return true;

        } catch (PortalException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    @Reference(unbind = "-")
    protected void setHelpRequestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private HelpRequestLocalService _helpRequestLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
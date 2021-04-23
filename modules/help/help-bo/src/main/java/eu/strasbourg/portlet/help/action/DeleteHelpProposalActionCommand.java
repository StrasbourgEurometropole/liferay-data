package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
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
		"mvc.command.name=deleteHelpProposal"
	},
	service = MVCActionCommand.class
)
public class DeleteHelpProposalActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			long helpProposalId = ParamUtil.getLong(request, "helpProposalId");
			_helpProposalLocalService.removeHelpProposal(helpProposalId);
		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}

	@Reference(unbind = "-")
	protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
		_helpProposalLocalService = helpProposalLocalService;
	}
	
	private HelpProposalLocalService _helpProposalLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

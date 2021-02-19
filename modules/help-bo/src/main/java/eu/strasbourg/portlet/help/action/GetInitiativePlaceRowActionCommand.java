package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
			"mvc.command.name=getInitiativePlaceRow"
		},
		service = MVCActionCommand.class
	)
public class GetInitiativePlaceRowActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {
		request.setAttribute("fromAjaxInitiative", true);
		return true;
	}

}

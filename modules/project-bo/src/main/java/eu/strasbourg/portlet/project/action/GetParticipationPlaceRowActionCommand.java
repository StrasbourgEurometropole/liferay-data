package eu.strasbourg.portlet.project.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
			"mvc.command.name=getParticipationPlaceRow"
		},
		service = MVCActionCommand.class
	)
public class GetParticipationPlaceRowActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {
		request.setAttribute("fromAjaxParticipation", true);
		return true;
	}

}

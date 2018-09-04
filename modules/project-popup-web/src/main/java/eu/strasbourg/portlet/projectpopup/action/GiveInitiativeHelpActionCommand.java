package eu.strasbourg.portlet.projectpopup.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = { 
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
			"mvc.command.name=giveInitiativeHelp"
		},
		service = MVCActionCommand.class
	)
public class GiveInitiativeHelpActionCommand implements MVCActionCommand {

	private static final String SHARED_ASSET_ID = "LIFERAY_SHARED_assetEntryID";
	
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {
		

		
		return true;
	}
}
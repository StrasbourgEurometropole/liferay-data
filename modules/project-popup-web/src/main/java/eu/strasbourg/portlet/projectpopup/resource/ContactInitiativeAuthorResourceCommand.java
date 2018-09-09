package eu.strasbourg.portlet.projectpopup.resource;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
	    	"mvc.command.name=ContactInitiativeAuthor"
	    },
	    service = MVCResourceCommand.class
	)
public class ContactInitiativeAuthorResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
			String initiative = "Ini";
		
		return false;
	}

}

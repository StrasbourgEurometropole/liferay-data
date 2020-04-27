package eu.strasbourg.portlet.council.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO
	},
	service = ControlPanelEntry.class
)
public class CouncilBOControlPanelEntry extends BaseControlPanelEntry {

}

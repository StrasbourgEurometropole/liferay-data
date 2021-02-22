package eu.strasbourg.portlet.help.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

/**
 * @author Cédric Henry
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO
	},
	service = ControlPanelEntry.class
)
public class HelpBOControlPanelEntry extends BaseControlPanelEntry {
	
}

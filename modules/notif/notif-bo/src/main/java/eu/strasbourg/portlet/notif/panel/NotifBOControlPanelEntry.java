package eu.strasbourg.portlet.notif.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.NOTIF_BO
	},
	service = ControlPanelEntry.class
)
public class NotifBOControlPanelEntry extends BaseControlPanelEntry {

}

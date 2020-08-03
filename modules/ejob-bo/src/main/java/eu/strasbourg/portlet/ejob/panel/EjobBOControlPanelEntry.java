package eu.strasbourg.portlet.ejob.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.EJOB_BO
	},
	service = ControlPanelEntry.class
)
public class EjobBOControlPanelEntry extends BaseControlPanelEntry {

}

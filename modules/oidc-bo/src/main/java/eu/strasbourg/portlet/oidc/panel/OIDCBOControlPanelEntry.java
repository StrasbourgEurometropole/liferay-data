package eu.strasbourg.portlet.oidc.panel;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author Cédric Henry
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO
	},
	service = ControlPanelEntry.class
)
public class OIDCBOControlPanelEntry extends BaseControlPanelEntry {

}

package eu.strasbourg.portlet.objtp.panel;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.OBJTP_BO
		},
		service = ControlPanelEntry.class
	)
	public class ObjtpBOControlPanelEntry
		extends BaseControlPanelEntry  {
	}
package eu.strasbourg.portlet.project.panel;

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
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO
	},
	service = ControlPanelEntry.class
)
public class ProjectBOControlPanelEntry extends BaseControlPanelEntry {
	
}

package eu.strasbourg.portlet.agenda.csmap.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA
	},
	service = ControlPanelEntry.class
)
public class CsmapBOAgendaControlPanelEntry extends BaseControlPanelEntry {

}

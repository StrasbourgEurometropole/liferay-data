package eu.strasbourg.portlet.agendaExport.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO
        },
        service = ControlPanelEntry.class

)
public class AgendaExportBOControlPanelEntry extends BaseControlPanelEntry {

}

package eu.strasbourg.portlet.agenda.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.office.exporter.api.EventParticipationsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export des participants a un evenement
 */
@Component(
	immediate = true,
	property = { 
		"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=exportEventParticipationsXlsx" 
	}, 
	service = MVCResourceCommand.class
)
public class ExportEventParticipationsToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse){
		
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=EventParticipations.xlsx");
		Long eventId = ParamUtil.getLong(resourceRequest, "eventId");
		
		try {
			_eventParticipationsXlsExporter.exportEventParticipations(resourceResponse.getPortletOutputStream(), eventId);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}

		return true;
	}
	
	@Reference(unbind = "-")
	public void setEventParticipationsXlsExporter(EventParticipationsXlsxExporter eventParticipationsXlsxExporter) {
		this._eventParticipationsXlsExporter = eventParticipationsXlsxExporter;
	}
	
	private EventParticipationsXlsxExporter _eventParticipationsXlsExporter;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

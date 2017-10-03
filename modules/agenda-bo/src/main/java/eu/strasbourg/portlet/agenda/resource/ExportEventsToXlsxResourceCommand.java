package eu.strasbourg.portlet.agenda.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.office.exporter.EventsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=exportXlsx" }, service = MVCResourceCommand.class)
public class ExportEventsToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=Events.xlsx");
		String eventIds = ParamUtil.getString(resourceRequest, "eventIds");

		try {
			EventsXlsxExporter.exportEvents(resourceResponse.getPortletOutputStream(), eventIds);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}

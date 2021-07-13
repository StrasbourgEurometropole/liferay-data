package eu.strasbourg.portlet.ejob.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import eu.strasbourg.service.office.exporter.api.OffersXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.EJOB_BO,
		"mvc.command.name=exportXlsx" }, service = MVCResourceCommand.class)
public class ExportOffersToXlsxResourceCommand implements MVCResourceCommand {
	
	private OffersXlsxExporter offersXlsExporter;
	
	@Reference(unbind = "-")
	public void setOffersXlsExporter(OffersXlsxExporter offersXlsExporter) {
		this.offersXlsExporter = offersXlsExporter;
	}

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=Offres.xlsx");

		try {
			offersXlsExporter.exportPublishedOffers(resourceResponse.getPortletOutputStream());
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}

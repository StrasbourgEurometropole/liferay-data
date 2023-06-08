package eu.strasbourg.portlet.oidc.resource;

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

import eu.strasbourg.service.office.exporter.api.HistoricPublikUserTextExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO,
		"mvc.command.name=exportHistoricText" }, service = MVCResourceCommand.class)
public class ExportHistoricToTextResourceCommand implements MVCResourceCommand {

	private HistoricPublikUserTextExporter historicPublikUserTextExporter;

	@Reference(unbind = "-")
	public void setHistoricPublikUserTextExporter(HistoricPublikUserTextExporter historicPublikUserTextExporter) {
		this.historicPublikUserTextExporter = historicPublikUserTextExporter;
	}

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=Historique.txt");
		long publikUserLiferayId = ParamUtil.getLong(resourceRequest, "publikUserLiferayId");

		try {
			historicPublikUserTextExporter.exportHistoric(resourceResponse.getPortletOutputStream(),
					publikUserLiferayId);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}

		return true;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

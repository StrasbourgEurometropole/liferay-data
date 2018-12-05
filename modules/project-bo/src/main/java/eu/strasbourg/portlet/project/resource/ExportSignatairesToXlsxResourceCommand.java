package eu.strasbourg.portlet.project.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.office.exporter.api.SignatairesXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=exportSignatairesXlsx"
	},
	service = MVCResourceCommand.class
)
public class ExportSignatairesToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=Signataires.xlsx");
		Long petitionId = ParamUtil.getLong(resourceRequest, "petitionId");
		
		try {
			_signatairesXlsxExporter.exportSignataires(resourceResponse.getPortletOutputStream(), petitionId);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error("Probleme lors de l'écriture en fichier : ", e);
		}

		return true;
	}
	
	private SignatairesXlsxExporter _signatairesXlsxExporter;
	
    @Reference(unbind = "-")
    public void setSignatairesXlsxExporter(SignatairesXlsxExporter signatairesXlsxExporter) {
        this._signatairesXlsxExporter = signatairesXlsxExporter;
    }
    
    /**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

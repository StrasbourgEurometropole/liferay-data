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

import eu.strasbourg.service.office.exporter.api.BudgetSupportsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=exportBudgetSupportsXlsx"
	},
	service = MVCResourceCommand.class
)
public class ExportBudgetSupportsToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=BudgetParticipatifs.xlsx");
		Long budgetParticipatifId = ParamUtil.getLong(resourceRequest, "budgetParticipatifId");
		
		try {
			_budgetSupportsXlsxExporter.exportBudgetSupports(resourceResponse.getPortletOutputStream(), budgetParticipatifId);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error("Probleme lors de l'écriture en fichier : ", e);
		}
		
		return true;
	}
	
	private BudgetSupportsXlsxExporter _budgetSupportsXlsxExporter;
	
    @Reference(unbind = "-")
    public void setBudgetSupportsXlsxExporter(BudgetSupportsXlsxExporter budgetSupportsXlsxExporter) {
        this._budgetSupportsXlsxExporter = budgetSupportsXlsxExporter;
    }
    
    /**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

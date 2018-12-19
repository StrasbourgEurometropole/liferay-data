package eu.strasbourg.portlet.project.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.office.exporter.api.BudgetsParticipatifsXlsxExporter;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=exportBudgetsXlsx"
	},
	service = MVCResourceCommand.class
)
public class ExportBudgetsToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		resourceResponse.setContentType("application/force-download");
        resourceResponse.setProperty("content-disposition","attachment; filename=BudgetsParticipatifs.xlsx");
        try {
        	List<BudgetParticipatif> budgetsParticipatifs = BudgetParticipatifLocalServiceUtil.getBudgetParticipatifs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        	budgetsParticipatifsXlsxExporter.exportBudgetsParticipatifs(resourceResponse.getPortletOutputStream(), budgetsParticipatifs);
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
			_log.error("probleme lors de l'extraction des Budgets participatifs : ",e);
        }
        return true;
	}

	private BudgetsParticipatifsXlsxExporter budgetsParticipatifsXlsxExporter;

    @Reference(unbind = "-")
    public void setBudgetsParticipatifsXlsxExporter(BudgetsParticipatifsXlsxExporter budgetsParticipatifsXlsxExporter) {
        this.budgetsParticipatifsXlsxExporter = budgetsParticipatifsXlsxExporter;
    }
    
    /**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

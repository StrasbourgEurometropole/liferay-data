package eu.strasbourg.portlet.project.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.office.exporter.api.BudgetsParticipatifsXlsxExporter;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=exportBudgetsXlsx"
	},
	service = MVCResourceCommand.class
)
public class ExportBudgetsParticipatifsToXlsxResourceCommand implements MVCResourceCommand {

	/**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private BudgetsParticipatifsXlsxExporter budgetsParticipatifsXlsxExporter;

    @Reference(unbind = "-")
    public void setBudgetsParticipatifsXlsxExporter(BudgetsParticipatifsXlsxExporter budgetsParticipatifsXlsxExporter) {
        this.budgetsParticipatifsXlsxExporter = budgetsParticipatifsXlsxExporter;
    }

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		
		resourceResponse.setContentType("application/force-download");
        resourceResponse.setProperty("content-disposition","attachment; filename=BudgetsParticipatifs.xlsx");
        
        String budgetsParticipatifsIds = ParamUtil.getString(resourceRequest,"budgetsParticipatifsIds");
        
        List<BudgetParticipatif> budgetsParticipatifs = new ArrayList<>();
        
        // Recupertation des budgets associes
        for (String budgetParticipatifId : budgetsParticipatifsIds.split(",")) {
        	BudgetParticipatif budgetParticipatif;
        	
			try {
				budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(Long.parseLong(budgetParticipatifId));
				budgetsParticipatifs.add(budgetParticipatif);
			} catch (NumberFormatException | PortalException e) {
				_log.error("probleme lors de l'écriture en fichier : ",e);
			}
		}
        
        try {
        	budgetsParticipatifsXlsxExporter.exportBudgetsParticipatifs(resourceResponse.getPortletOutputStream(), budgetsParticipatifs);
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
			_log.error("probleme lors de l'écriture : ",e);
        }
        return true;
	}

}

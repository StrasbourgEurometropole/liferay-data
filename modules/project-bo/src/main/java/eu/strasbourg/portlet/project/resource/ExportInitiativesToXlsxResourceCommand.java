package eu.strasbourg.portlet.project.resource;

import java.io.IOException;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import eu.strasbourg.service.office.exporter.api.InitiativesXlsxExporter;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
			"mvc.command.name=exportInitiativesXlsx"
		},
		service = MVCResourceCommand.class
	)
public class ExportInitiativesToXlsxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		resourceResponse.setContentType("application/force-download");
        resourceResponse.setProperty("content-disposition","attachment; filename=Initiatives.xlsx");
        
        try {
        	List<Initiative> initiatives = InitiativeLocalServiceUtil.getInitiatives(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        	initiativesXlsxExporter.exportInitiatives(resourceResponse.getPortletOutputStream(), initiatives);
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
			_log.error("probleme lors de l'extraction des Budgets participatifs : ",e);
        }
        
        return true;
	}

	private InitiativesXlsxExporter initiativesXlsxExporter;

    @Reference(unbind = "-")
    public void setInitiativesXlsxExporter(InitiativesXlsxExporter initiativesXlsxExporter) {
        this.initiativesXlsxExporter = initiativesXlsxExporter;
    }
    
    /**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
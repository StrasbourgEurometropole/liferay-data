package eu.strasbourg.portlet.oidc.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.office.exporter.api.PetitionsXlsxExporter;
import eu.strasbourg.service.office.exporter.api.PublikUsersXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * @author angelique zunino champougny
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.OIDC_BO,
        "mvc.command.name=exportXlsx"
        },
        service = MVCResourceCommand.class)
public class ExportToXlsxResourceCommand implements MVCResourceCommand {

    private PublikUsersXlsxExporter xlsxExporter;

    @Reference(unbind = "-")
    public void setXlsxExporter(PublikUsersXlsxExporter xlsxExporter) {
        this.xlsxExporter = xlsxExporter;
    }

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        resourceResponse.setContentType("application/force-download");
        resourceResponse.setProperty("content-disposition","attachment; filename=Petitions.xlsx");
        try {
            xlsxExporter.export(resourceResponse.getPortletOutputStream());
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

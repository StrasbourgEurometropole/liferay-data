package eu.strasbourg.portlet.oidc.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import eu.strasbourg.service.office.exporter.api.PactSigningPublikUsersXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ExportPactSigningPublikUsersToXlsxResourceCommand implements MVCResourceCommand {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private PactSigningPublikUsersXlsxExporter xlsxExporter;

    @Reference(unbind = "-")
    public void setXlsxExporter(PactSigningPublikUsersXlsxExporter xlsxExporter) {
        this.xlsxExporter = xlsxExporter;
    }

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        resourceResponse.setContentType("application/force-download");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        resourceResponse.setProperty("content-disposition","attachment; filename=Signataires_Pacte_"+dateFormat.format(new Date())+".xlsx");
        try {
            xlsxExporter.export(resourceResponse.getPortletOutputStream());
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
            _log.error(e);
        }
        return true;
    }
}

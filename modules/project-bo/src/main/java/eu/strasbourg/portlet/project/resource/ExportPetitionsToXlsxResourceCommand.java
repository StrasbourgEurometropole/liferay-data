package eu.strasbourg.portlet.project.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.office.exporter.api.PetitionsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
        "mvc.command.name=exportPetitionsXlsx"
        },
        service = MVCResourceCommand.class)
public class ExportPetitionsToXlsxResourceCommand implements MVCResourceCommand {

    private PetitionsXlsxExporter petitionsXlsxExporter;

    @Reference(unbind = "-")
    public void setPetitionsXlsxExporter(PetitionsXlsxExporter petitionsXlsxExporter) {
        this.petitionsXlsxExporter = petitionsXlsxExporter;
    }

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        resourceResponse.setContentType("application/force-download");
        resourceResponse.setProperty("content-disposition","attachment; filename=Petitions.xlsx");
        String petitionIds = ParamUtil.getString(resourceRequest,"petitionIds");
        try {
            petitionsXlsxExporter.exportPetitions(resourceResponse.getPortletOutputStream(),petitionIds);
            resourceResponse.getPortletOutputStream().flush();
        } catch (IOException e) {
            _log.error(e.getMessage(), e);
        }
        return true;
    }

    private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

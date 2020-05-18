package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalService;
import eu.strasbourg.service.office.exporter.api.CouncilOfficialsXlsxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=exportOfficials"
        },
        service = MVCResourceCommand.class
)
public class ExportOfficialsResourceCommand implements MVCResourceCommand {

    /** Log */
    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Service **/
    private OfficialLocalService officialLocalService;
    private CouncilOfficialsXlsxExporter councilOfficialsXlsxExporter;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {
        // Parametrage des propriétés de réponse
        response.setContentType("application/force-download");
        response.setProperty("content-disposition","attachment; filename=Elus_conseils.xlsx");

        // Recupération de tous les élus
        List<Official> officials = this.officialLocalService.getOfficials(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        try {
            // Génération de l'excel/CSV des résultats
            this.councilOfficialsXlsxExporter.exportOfficials(response.getPortletOutputStream(), officials);
            // Envoie du document dans le flux de réponse
            response.getPortletOutputStream().flush();
        } catch (IOException e) {
            this.log.error("Probleme lors de l'extraction des élus (Conseils) : ", e);
        }

        return true;
    }

    @Reference(unbind = "-")
    public void setCouncilOfficialsXlsxExporter(CouncilOfficialsXlsxExporter councilOfficialsXlsxExporter) {
        this.councilOfficialsXlsxExporter = councilOfficialsXlsxExporter;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

}

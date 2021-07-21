package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.council.utils.PrintProcurationsPDF;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveProcurationDynamic"
        },
        service = MVCResourceCommand.class
)
public class SaveProcurationResourceCommand implements MVCResourceCommand{

    /** Service */
    private CouncilSessionLocalService councilSessionLocalService;

    /** Params */
    private long councilSessionId;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {

        this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");

        // Récupération de la session à traiter
        CouncilSession councilSession = this.councilSessionLocalService.fetchCouncilSession(this.councilSessionId);

        if (councilSession != null) {
            File pdfFile;
            try {
                pdfFile = PrintProcurationsPDF.printPDFs(this.councilSessionId);

                // Définition d'un téléchargement dans le content-type
                response.setContentType("application/force-download");

                // Récupération des paramètres
                this.loadParameters(request);

                response.setProperty("content-disposition","attachment; filename=" + pdfFile.getName());

                // Fermeture des outputStreams
                Files.copy(pdfFile.toPath(), response.getPortletOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Récupération des paramètre de la requête
     */
    private void loadParameters(ResourceRequest request) {
        this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

}
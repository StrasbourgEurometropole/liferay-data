package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.council.utils.PrintPDF;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.ZipHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=exportCouncilSessionResults"
        },
        service = MVCResourceCommand.class
)
public class ExportCouncilSessionResultsResourceCommand implements MVCResourceCommand {

    /** Log */
    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Service */
    private CouncilSessionLocalService councilSessionLocalService;

    /** Params */
    private long councilSessionId;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {

        this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");
        try {
            String repository = PrintPDF.printPDFs(this.councilSessionId);

            // Définition d'un téléchargement dans le content-type
            response.setContentType("application/force-download");

            // Récupération des paramètres
            this.loadParameters(request);

            // Récupération de la session à traiter
            CouncilSession councilSession = this.councilSessionLocalService.fetchCouncilSession(this.councilSessionId);

            if (councilSession != null) {
                String zipFileName = councilSession.getTitle().replace(" ", "_");

                response.setProperty("content-disposition","attachment; filename=" + zipFileName + ".zip");

                try {
                    // Récupération de l'ouputStream de la réponse et création du zipOutputStream
                    OutputStream os = response.getPortletOutputStream();

                    ZipHelper.zipDirectoryinOutputStream(repository, os);

                    os.flush();

                    // Fermeture des outputStreams
                    os.close();

                } catch (IOException e) {
                    this.log.error("Une erreur est survenu lors de l'extraction de résultat de la session ", e);
                }
            }
        } catch (IOException e) {
            _log.error(e.getMessage() + " : " + this.councilSessionId);
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

    private final Log _log = LogFactoryUtil.getLog(this.getClass());

}

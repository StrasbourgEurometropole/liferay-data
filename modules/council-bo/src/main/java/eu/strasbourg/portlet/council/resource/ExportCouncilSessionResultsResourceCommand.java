package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
                OutputStream out = response.getPortletOutputStream();
                ZipOutputStream zos = new ZipOutputStream(out);

                // Récupération du fichier
                File testFile1 = new File("C:/Users/cedric.henry/Documents/test 1.pdf");
                File testFile2 = new File("C:/Users/cedric.henry/Documents/test 2.pdf");

                // Ecriture du fichier 1 dans le zip
                zos.putNextEntry(new ZipEntry(testFile1.getName()));
                byte[] testFileContent1 = Files.readAllBytes(testFile1.toPath());
                zos.write(testFileContent1, 0, testFileContent1.length);
                zos.closeEntry();

                // Ecriture du fichier 2 dans le zip
                zos.putNextEntry(new ZipEntry(testFile2.getName()));
                byte[] testFileContent2 = Files.readAllBytes(testFile2.toPath());
                zos.write(testFileContent2, 0, testFileContent2.length);
                zos.closeEntry();

                // Envoie des données
                zos.flush();
                out.flush();

                // Fermeture des outputStreams
                zos.close();
                out.flush();

            } catch (IOException e) {
                this.log.error("Une erreur est survenu lors de l'extraction de résultat de la session ", e);
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

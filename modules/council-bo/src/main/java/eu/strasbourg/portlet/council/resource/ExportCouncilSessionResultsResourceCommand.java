package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.council.action.PrintPDF;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        long councilSessionId = ParamUtil.getLong(request, "councilSessionId");
        try {
            String repository = PrintPDF.printPDFs(councilSessionId);

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

                    // TODO remettre celui de utils
                    zipDirectoryinOutputStream(repository, os);

                    os.flush();

                    // Fermeture des outputStreams
                    os.close();

                } catch (IOException e) {
                    this.log.error("Une erreur est survenu lors de l'extraction de résultat de la session ", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Compresse un répertoire local et l'écrit dans la sortie donnée
     * @implNote Ne prend pas en compte l'arborescence ("[directoryPath]/rep/ex.txt" devient "[zipName]/ex.txt")
     * @param directoryPath Le chemin du dossier à compresser
     * @param os Le flux dans lequel sera écrit le zip
     */
    public static void zipDirectoryinOutputStream(String directoryPath, OutputStream os) throws IOException {
        // Création du flux
        ZipOutputStream zos = new ZipOutputStream(os);

        // Création de l'objet permettant le parcours du répertoire
        Path pp = Paths.get(directoryPath);

        // Parcours de l'arborescence et écriture de tous les fichiers dans le flux zip
        Files.walk(pp)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                    try {
                        zos.putNextEntry(zipEntry);
                        Files.copy(path, zos);
                        zos.closeEntry();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        // Envoie et fermeture
        zos.flush();
        zos.close();
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

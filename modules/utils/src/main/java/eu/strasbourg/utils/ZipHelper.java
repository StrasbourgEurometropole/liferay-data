package eu.strasbourg.utils;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Fournit des méthodes utiles pour la gestion de fichiers .zip
 */
public class ZipHelper {

    /**
     * Compresse un fichier local et l'écrit dans la sortie donnée
     * @param filePath Le chemin du fichier à compresser
     * @param os Le flux dans lequel sera écrit le zip
     */
    public static void zipFileinOutputStream(String filePath, OutputStream os) throws IOException {
        // Création du flux
        ZipOutputStream zos = new ZipOutputStream(os);

        // Récupération du fichier
        File file = new File(filePath);

        // Ecriture du fichier dans le zip
        zos.putNextEntry(new ZipEntry(file.getName()));
        byte[] fileContent = Files.readAllBytes(file.toPath());
        zos.write(fileContent, 0, fileContent.length);
        zos.closeEntry();

        // Envoie et fermeture du flux
        zos.flush();
        zos.close();
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

}

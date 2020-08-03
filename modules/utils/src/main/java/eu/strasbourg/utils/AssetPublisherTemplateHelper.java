package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Normalizer;

public class AssetPublisherTemplateHelper {

    private static final String COLLECTION = "collection";
    private static final String OEUVRE = "oeuvre";

    private static final String CABINET_DES_ESTAMPES = "Cabinet des Estampes et des Dessins";
    private static final String URL_MUSEE_COLLECTION_CABINET_DES_ESTAMPES = "cabinet-estampes-dessins";
    private static final String URL_MUSEE_OEUVRE_CABINET_DES_ESTAMPES = "du-cabinet-des-estampes";

    private static final String MUSEE_OEUVRE_NOTRE_DAME = "Musee de l'Œuvre Notre-Dame";
    private static final String URL_MUSEE_COLLECTION_MUSEE_OEUVRE_NOTRE_DAME = "du-musee-oeuvre-notre-dame";
    private static final String URL_MUSEE_OEUVRE_MUSEE_OEUVRE_NOTRE_DAME = "musee-oeuvre-notre-dame";

    private static final String MUSEE_BEAUX_ARTS = "Musee des Beaux-Arts";
    private static final String URL_MUSEE_COLLECTION_MUSEE_BEAUX_ARTS = "du-musee-des-beaux-arts";
    private static final String URL_MUSEE_OEUVRE_MUSEE_BEAUX_ARTS = "musee-des-beaux-arts";

    private static final String MAMCS = "MAMCS";
    private static final String URL_TYPE_OEUVRE_MAMCS = "-uvre-";

    /**
     * Retourne la largeur/hauteur d'une image
     */
    public static String getImageWidthHeight(String filePath) {

        String taille = "";
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedImage image = ImageIO.read(fis);
            if(Validator.isNotNull(image)) {
                int width = image.getWidth();
                int height = image.getHeight();
                taille = width + "," + height;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taille;
    }

    /**
     * Récupère l'URL d'une image à partir des données fournies par la Structure d'un WebContent
     */
    public static String getDocumentUrl(String documentStructure){

        String documentUrl = "";

        try {
            // Parse les données JSON
            JSONObject documentJSONObject = JSONFactoryUtil.createJSONObject(documentStructure);

            // Ce qui permet de récupèrer l'URL de l'image
            documentUrl = FileEntryHelper.getFileEntryURL(documentJSONObject.getLong("fileEntryId"));
        } catch (PortalException e) {
            // _log.error("Une erreur est survenue lors de la récupération de l'URL d'un document : ", e);
        }

        return documentUrl;
    }

    /**
     * Enleve les acccents, lowercase, remplace l'espace par un '-' pour utiliser le string dans un URL
     */
    public static String slugify(String s){
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll(" ","-");
        s = s.toLowerCase();
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    /**
     * Enleve les acccents
     */
    public static String simpleSlugify(String s){
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    /**
     * Remplace le renderUrl de liferay pour le site des musees
     */
    public static String createRenderUrlMusee(String type, String musee){
        String url = type + "-";
        String slugifiedMusee = simpleSlugify(musee);
        String museeUrl ="";

        switch(slugifiedMusee) {
            case CABINET_DES_ESTAMPES:
                if(type.equals(COLLECTION)){
                    museeUrl = URL_MUSEE_COLLECTION_CABINET_DES_ESTAMPES;
                }else{
                    museeUrl = URL_MUSEE_OEUVRE_CABINET_DES_ESTAMPES;
                }
                break;
            case MUSEE_OEUVRE_NOTRE_DAME:
                if(type.equals(COLLECTION)){
                    museeUrl = URL_MUSEE_COLLECTION_MUSEE_OEUVRE_NOTRE_DAME;
                }else{
                    museeUrl = URL_MUSEE_OEUVRE_MUSEE_OEUVRE_NOTRE_DAME;
                }
                break;
            case MUSEE_BEAUX_ARTS:
                if(type.equals(COLLECTION)){
                    museeUrl = URL_MUSEE_COLLECTION_MUSEE_BEAUX_ARTS;
                }else{
                    museeUrl = URL_MUSEE_OEUVRE_MUSEE_BEAUX_ARTS;
                }
                break;
            case MAMCS:
                if(type.equals(OEUVRE)){
                    url = URL_TYPE_OEUVRE_MAMCS;
                }
                break;
            default:
                museeUrl = slugify(musee);
        }

        url += museeUrl;
        return url;
    }


}

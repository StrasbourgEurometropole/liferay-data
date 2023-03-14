package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ResourceBundle;

public class AssetPublisherTemplateHelper {

    private static ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            AssetPublisherTemplateHelper.class.getClassLoader());

    private static final String COLLECTION = LanguageUtil.get(bundle, "eu.ejob.collection");
    private static final String OEUVRE = LanguageUtil.get(bundle, "eu.ejob.oeuvre");

    private static final String CABINET_DES_ESTAMPES = LanguageUtil.get(bundle, "eu.ejob.cabinet_des_estampes");
    private static final String URL_MUSEE_COLLECTION_CABINET_DES_ESTAMPES = LanguageUtil.get(bundle, "eu.ejob.url_musee_collection_cabinet_des_estampes");
    private static final String URL_MUSEE_OEUVRE_CABINET_DES_ESTAMPES = LanguageUtil.get(bundle, "eu.ejob.url_musee_oeuvre_cabinet_des_estampes");

    private static final String MUSEE_OEUVRE_NOTRE_DAME = LanguageUtil.get(bundle, "eu.ejob.musee_oeuvre_notre_dame");
    private static final String URL_MUSEE_COLLECTION_MUSEE_OEUVRE_NOTRE_DAME = LanguageUtil.get(bundle, "eu.ejob.url_musee_collection_musee_oeuvre_notre_dame");
    private static final String URL_MUSEE_OEUVRE_MUSEE_OEUVRE_NOTRE_DAME = LanguageUtil.get(bundle, "eu.ejob.url_musee_oeuvre_musee_oeuvre_notre_dame");

    private static final String MUSEE_BEAUX_ARTS = LanguageUtil.get(bundle, "eu.ejob.musee_beaux_arts");
    private static final String URL_MUSEE_COLLECTION_MUSEE_BEAUX_ARTS = LanguageUtil.get(bundle, "eu.ejob.url_musee_collection_musee_beaux_arts");
    private static final String URL_MUSEE_OEUVRE_MUSEE_BEAUX_ARTS = LanguageUtil.get(bundle, "eu.ejob.url_musee_oeuvre_musee_beaux_arts");

    private static final String MAMCS = LanguageUtil.get(bundle, "eu.ejob.mamcs");
    private static final String URL_TYPE_OEUVRE_MAMCS = LanguageUtil.get(bundle, "eu.ejob.url_type_oeuvre_mamcs");

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
            _log.error(e.getMessage(), e);
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
        s = s.toLowerCase();
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

        if(slugifiedMusee.equals(CABINET_DES_ESTAMPES) && type.equals(COLLECTION)){
            museeUrl = URL_MUSEE_COLLECTION_CABINET_DES_ESTAMPES;
        }
        else if(slugifiedMusee.equals(CABINET_DES_ESTAMPES) && type.equals(OEUVRE)){
            museeUrl = URL_MUSEE_OEUVRE_CABINET_DES_ESTAMPES;
        }
        else if(slugifiedMusee.equals(MUSEE_OEUVRE_NOTRE_DAME) && type.equals(COLLECTION)){
            museeUrl = URL_MUSEE_COLLECTION_MUSEE_OEUVRE_NOTRE_DAME;
        }
        else if(slugifiedMusee.equals(MUSEE_OEUVRE_NOTRE_DAME) && type.equals(OEUVRE)){
            museeUrl = URL_MUSEE_OEUVRE_MUSEE_OEUVRE_NOTRE_DAME;
        }
        else if(slugifiedMusee.equals(MUSEE_BEAUX_ARTS) && type.equals(COLLECTION)){
            museeUrl = URL_MUSEE_COLLECTION_MUSEE_BEAUX_ARTS;
        }
        else if(slugifiedMusee.equals(MUSEE_BEAUX_ARTS) && type.equals(OEUVRE)){
            museeUrl = URL_MUSEE_OEUVRE_MUSEE_BEAUX_ARTS;
        }
        else if(slugifiedMusee.equals(MAMCS) && type.equals(OEUVRE)){
            url = URL_TYPE_OEUVRE_MAMCS;
            museeUrl = slugify(musee);
        }
        else{
            museeUrl = slugify(musee);
        }

        url += museeUrl;
        return url;
    }

    private static final Log _log = LogFactoryUtil.getLog(AssetPublisherTemplateHelper.class);


}

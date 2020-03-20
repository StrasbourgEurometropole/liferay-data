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

public class AssetPublisherTemplateHelper {

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


}

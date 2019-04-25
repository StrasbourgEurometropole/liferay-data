package eu.strasbourg.utils;

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

}

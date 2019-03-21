package eu.strasbourg.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AssetPublisherTemplateHelper {

    /**
     * Retourne la largeur/hauteur d'une image
     */
    public static String getImageWidthHeight(String filePath) {

        String taille = "";
        try {
            Image img = ImageIO.read(new URL(filePath));
            int width = ((BufferedImage) img).getWidth();
            int height = ((BufferedImage) img).getHeight();
            taille = width + "," + height;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taille;
    }

}

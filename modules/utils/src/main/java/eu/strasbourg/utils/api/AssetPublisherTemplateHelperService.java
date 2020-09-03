package eu.strasbourg.utils.api;

import com.liferay.portal.kernel.theme.ThemeDisplay;

public interface AssetPublisherTemplateHelperService {

    /**
     * Retourne la largeur/hauteur d'une image
     */
    String getImageWidthHeight(String filePath);

    /**
     * Récupère l'URL d'une image à partir des données fournies par la Structure d'un WebContent
     */
    String getDocumentUrl(String documentStructure);

    /**
     * Enleve les acccents, lowercase, remplace l'espace par un '-' pour utiliser le string dans un URL
     */
    String slugify(String s);

    /**
     * Remplace le renderUrl de liferay pour le site des musees
     */
    String createRenderUrlMusee(String type, String musee);
}

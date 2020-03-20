package eu.strasbourg.utils.api;

import com.liferay.portal.kernel.theme.ThemeDisplay;

public interface AssetPublisherTemplateHelperService {

    /**
     * Retourne la largeur/hauteur d'une image
     */
    String getImageWidthHeight(String filePath);

    String getDocumentUrl(String documentStructure);
}

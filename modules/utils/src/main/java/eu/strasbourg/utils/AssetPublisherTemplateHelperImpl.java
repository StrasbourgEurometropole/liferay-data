package eu.strasbourg.utils;

import eu.strasbourg.utils.api.AssetPublisherTemplateHelperService;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {},
        service = AssetPublisherTemplateHelperService.class)
public class AssetPublisherTemplateHelperImpl implements AssetPublisherTemplateHelperService {

    /**
     * Retourne la largeur/hauteur d'une image
     */
    @Override
    public String getImageWidthHeight(String filePath) {
        return AssetPublisherTemplateHelper.getImageWidthHeight(filePath);
    }
}

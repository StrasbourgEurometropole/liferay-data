package eu.strasbourg.portlet.place.csmap.display.context;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.model.PlaceCategories;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditCsmapPlaceCategoriesDisplayContext {
    private PlaceCategories placeCategories;
    private final AssetVocabularyAccessor _assetVocabularyAccessor;

    public EditCsmapPlaceCategoriesDisplayContext() {
        _assetVocabularyAccessor = new AssetVocabularyAccessor();
    }
    public PlaceCategories getPlaceCategories() {
        placeCategories = PlaceCategoriesLocalServiceUtil.getPlaceCategories();
        if(Validator.isNull(placeCategories)){
            placeCategories = PlaceCategoriesLocalServiceUtil.createPlaceCategories();
        }
        return placeCategories;
    }

    public String getClassName(){
        return Place.class.getName();
    }

    public String getTypeVocabularyId(){
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
            if(Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + VocabularyNames.PLACE_TYPE);
        }
        return null;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

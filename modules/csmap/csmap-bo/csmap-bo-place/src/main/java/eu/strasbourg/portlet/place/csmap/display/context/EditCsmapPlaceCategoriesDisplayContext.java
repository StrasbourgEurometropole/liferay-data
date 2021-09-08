package eu.strasbourg.portlet.place.csmap.display.context;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.model.PlaceCategories;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;

import java.util.LinkedList;
import java.util.List;

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

    public  List<AssetVocabulary> getPlaceVocabularies() {
        List<AssetVocabulary> vocabularies = new LinkedList<>();
        vocabularies.add(_assetVocabularyAccessor.getPlaceTypes());
        return vocabularies;
    }

    public Boolean verifId(int id) {
        return placeCategories.getCategoriesIds().contains(String.valueOf(id));
    }
}

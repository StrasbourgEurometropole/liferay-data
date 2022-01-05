package eu.strasbourg.portlet.place.csmap.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.model.PlaceCategories;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_PLACE,
                "mvc.command.name=savePlaceCategories"},
        service = MVCActionCommand.class)
public class SaveCsmapPlaceCategoriesActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) {
        long placeCategoriesId = ParamUtil.getLong(request, "placeCategoriesId");
        PlaceCategories placeCategories;
        if (placeCategoriesId != 0) {
            try {
                placeCategories = _placeCategoriesLocalService.getPlaceCategories(placeCategoriesId);
            } catch (Exception e) {
                placeCategories = _placeCategoriesLocalService.createPlaceCategories(placeCategoriesId);
            }
        } else {
            placeCategories = _placeCategoriesLocalService.createPlaceCategories();
        }

        StringBuilder placeTypes = new StringBuilder();
        long[] placeTypesIds = ParamUtil.getLongValues(request, "Vocabulary_" + getTypeVocabularyId());
        List<Long> placeTypesIdsList = Arrays.stream(placeTypesIds).boxed().collect(Collectors.toList());
        for (long placeTypesId : placeTypesIds) {
            if (placeTypes.toString().equals("")) {
                placeTypes = new StringBuilder(String.valueOf(placeTypesId));
            } else {
                placeTypes.append(",").append(placeTypesId);
            }
            try {
                AssetCategory placeTypeCategory = _assetCategoryLocalService.getAssetCategory(placeTypesId);
                AssetCategory parentCategory = placeTypeCategory.getParentCategory();
                if (Validator.isNotNull(parentCategory)) {
                    long parentId = parentCategory.getCategoryId();
                    if (!placeTypesIdsList.contains(parentId)) {
                        placeTypes.append(",").append(parentId);
                    }
                }
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        placeCategories.setCategoriesIds(placeTypes.toString());

        _placeCategoriesLocalService.updatePlaceCategories(placeCategories);
    }

    private PlaceCategoriesLocalService _placeCategoriesLocalService;
    private AssetCategoryLocalService _assetCategoryLocalService;

    @Reference(unbind = "-")
    protected void setPlaceCategoriesLocalService(PlaceCategoriesLocalService placeCategoriesLocalService) {
        _placeCategoriesLocalService = placeCategoriesLocalService;
    }

    @Reference(unbind = "-")
    protected void setAssetCategoryLocalService(AssetCategoryLocalService assetCategoryLocalService) {
        _assetCategoryLocalService = assetCategoryLocalService;
    }

    private String getTypeVocabularyId() {
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
            if (Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            e.printStackTrace();
        }
        return null;
    }
}

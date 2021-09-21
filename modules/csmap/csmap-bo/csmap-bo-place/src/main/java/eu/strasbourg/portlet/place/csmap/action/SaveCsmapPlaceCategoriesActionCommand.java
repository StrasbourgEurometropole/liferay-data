package eu.strasbourg.portlet.place.csmap.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
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
        for (long placeTypesId : placeTypesIds) {
            if (placeTypes.toString().equals("")) {
                placeTypes = new StringBuilder(String.valueOf(placeTypesId));
            } else {
                placeTypes.append(",").append(placeTypesId);
            }
        }
        placeCategories.setCategoriesIds(placeTypes.toString());

        _placeCategoriesLocalService.updatePlaceCategories(placeCategories);
    }

    private PlaceCategoriesLocalService _placeCategoriesLocalService;

    @Reference(unbind = "-")
    protected void setAgendaExportLocalService(PlaceCategoriesLocalService placeCategoriesLocalService) {

        _placeCategoriesLocalService = placeCategoriesLocalService;
    }

    private String getTypeVocabularyId(){
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
            if(Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            e.printStackTrace();
        }
        return null;
    }
}

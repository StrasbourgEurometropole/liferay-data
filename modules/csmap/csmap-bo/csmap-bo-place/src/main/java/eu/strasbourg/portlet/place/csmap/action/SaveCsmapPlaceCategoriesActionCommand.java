package eu.strasbourg.portlet.place.csmap.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.csmap.model.PlaceCategories;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
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
            placeCategories = PlaceCategoriesLocalServiceUtil.createPlaceCategories();
        }

        int vocabularyNumber = ParamUtil.getInteger(request, "vocabulary_number");
        StringBuilder categories = new StringBuilder();
        for (int i = 0; i < vocabularyNumber; i++) {
            long[] categoryIds = ParamUtil.getLongValues(request, "vocabulary_" + i + "_select");
            if (categoryIds.length == 0) {
                continue;
            }
            for (long categoryId : categoryIds) {
                if (categories.toString().equals("")) {
                    categories = new StringBuilder(String.valueOf(categoryId));
                } else {
                    categories.append(",").append(categoryId);
                }
            }
        }
        placeCategories.setCategoriesIds(categories.toString());
        _placeCategoriesLocalService.updatePlaceCategories(placeCategories);
    }

    private PlaceCategoriesLocalService _placeCategoriesLocalService;

    @Reference(unbind = "-")
    protected void setAgendaExportLocalService(PlaceCategoriesLocalService placeCategoriesLocalService) {

        _placeCategoriesLocalService = placeCategoriesLocalService;
    }
}

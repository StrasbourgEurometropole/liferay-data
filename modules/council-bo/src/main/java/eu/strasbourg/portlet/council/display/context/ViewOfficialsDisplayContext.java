package eu.strasbourg.portlet.council.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewOfficialsDisplayContext extends ViewListBaseDisplayContext<Official> {

    private List<Official> officials;

    public static final String CATEGORY_ACTIVE = "Actif";

    public ViewOfficialsDisplayContext(RenderRequest request, RenderResponse response) {
        super(Official.class, request, response);
    }

    @SuppressWarnings("unused")
    public List<Official> getOfficials() throws PortalException {
        if (this.officials == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<Official> results = new ArrayList<>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    Official official = OfficialLocalServiceUtil.fetchOfficial(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (official != null)
                        results.add(official);
                }
            }
            this.officials = results;
        }
        return this.officials;
    }

    /**
     * Retourne la liste des élus correspondant à la recherche lancée en ignorant la pagination
     */
    private List<Official> getAllOfficials() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<Official> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Official official = OfficialLocalServiceUtil.fetchOfficial(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (official != null)
                    results.add(official);
            }
        }
        return results;
    }

    @Override
    public String getOrderByCol() {
        return ParamUtil.getString(this._request, "orderByCol", "full-name");
    }

    @Override
    public String getOrderByType() {
        return ParamUtil.getString(this._request, "orderByType", "asc");
    }

    /**
     * Surcharge le mappage des champs sur lesquelles trier
     */
    @Override
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "full-name":
                return "localized_title_fr_FR_sortable";
            default:
                return "modified_sortable";
        }
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COUNCIL_BO, StrasbourgPortletKeys.COUNCIL_BO,
                actionId);
    }

    /**
     * Retourne la liste des IDs des catégories sur lesquels on doit filtrer les
     * éditions Liste sous forme de string qui se présente comme suit :
     * ",categoryId1,categoryId2,categoryId3," Si le paramètre
     * "vocabularyToRemove" est présent, on enlève les catégories appartenant à
     * ce vocabulaire de la liste Si le paramètre "categoryToAdd" est présent,
     * on ajoute ladite catégorie à la liste
     *
     */
    public String getFilterCategoriesIds() throws PortalException {
        if (Validator.isNotNull(_filterCategoriesIds)) {
            return _filterCategoriesIds;
        }
        _filterCategoriesIds = ParamUtil.getString(_request,
                "filterCategoriesIds");
        if (_filterCategoriesIds.length() == 0) {
            _filterCategoriesIds = ",";
        }
        Long vocabularyToRemove = ParamUtil.getLong(_request,
                "vocabularyToRemove");
        if (vocabularyToRemove > 0) {
            AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
                    .getVocabulary(vocabularyToRemove);
            List<AssetCategory> categories = vocabulary.getCategories();
            for (AssetCategory category : categories) {
                if (_filterCategoriesIds
                        .contains(String.valueOf(category.getCategoryId()))) {
                    _filterCategoriesIds = _filterCategoriesIds
                            .replace("," + category.getCategoryId(), "");
                }
            }
            _filterCategoriesIds = _filterCategoriesIds
                    .replace(vocabularyToRemove + ",", "");
        }
        AssetCategory catActive = AssetVocabularyHelper.getCategory(CATEGORY_ACTIVE, this._themeDisplay.getScopeGroupId());
        String categoryToAdd = ParamUtil.getString(
                this._request, "categoryToAdd", catActive != null ? Long.toString(catActive.getCategoryId()) : "");
        if (Validator.isNotNull(categoryToAdd)) {
            _filterCategoriesIds += categoryToAdd + ",";
        }
        return _filterCategoriesIds;
    }

}

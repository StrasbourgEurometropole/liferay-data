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
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewDeliberationsDisplayContext extends ViewListBaseDisplayContext<Deliberation> {

    private List<Deliberation> deliberations;
    private String sessionCategoryToAdd;

    public ViewDeliberationsDisplayContext(RenderRequest request, RenderResponse response, String categoryToAdd) {
        super(Deliberation.class, request, response);
        this.sessionCategoryToAdd=categoryToAdd;
    }

    @SuppressWarnings("unused")
    public List<Deliberation> getDeliberations() throws PortalException {
        if (this.deliberations == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<Deliberation> results = new ArrayList<>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    Deliberation deliberation = DeliberationLocalServiceUtil.fetchDeliberation(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (deliberation != null) {
                        results.add(deliberation);
                    }
                }
            }
            this.deliberations = results;
        }
        return this.deliberations;
    }


    /**
     * Retourne la liste des délibérations correspondant à la recherche lancée en ignorant la pagination
     */
    private List<Deliberation> getAllDeliberations() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<Deliberation> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Deliberation deliberation = DeliberationLocalServiceUtil
                        .fetchDeliberation(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (deliberation != null) {
                    results.add(deliberation);
                }
            }
        }
        return results;
    }

    /**
     * Retourne la liste des PK de toutes les délibérations
     * @return liste de PK (ex: "1,5,7,8")
     */
    @SuppressWarnings("unused")
    public String getAllDeliberationIds() throws PortalException {
        StringBuilder deliberationsIds = new StringBuilder();
        for (Deliberation deliberation : this.getAllDeliberations()) {
            if (deliberationsIds.length() > 0) {
                deliberationsIds.append(",");
            }
            deliberationsIds.append(deliberation.getDeliberationId());
        }
        return deliberationsIds.toString();
    }

    @Override
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "title":
            case "alias":
                return "localized_title_fr_FR_sortable";
            case "modified-date":
                return "modified_sortable";
            case "publication-date":
                return "publishDate_sortable";
            case "status":
                return "status_sortable";
            case "order":
                return "order_sortable";
            default:
                return "modified_sortable";
        }
    }

    @Override
    public String getOrderByCol() {
        return ParamUtil.getString(this._request, "orderByCol",
                "order");
    }

    @Override
    public String getOrderByType() {
        return ParamUtil.getString(this._request, "orderByType", "asc");
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

    public String getStageDeliberationName(long id) {
        return StageDeliberation.get(id).getName();
    }

    /**
     * Class CSS de la couleur du Statut
     * @param deliberation
     * @return
     */
    public String getCSSClassColor(Deliberation deliberation) {
        String cssClass="";
        if(deliberation.isAdopte()) {
            cssClass="green";
        } else if (deliberation.isRejete()) {
            cssClass="red";
        }

        return cssClass;
    }

    @Override
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
        String categoryToAdd =sessionCategoryToAdd;

        if (Validator.isNotNull(categoryToAdd)) {
            _filterCategoriesIds += categoryToAdd + ",";
        }
        return _filterCategoriesIds;
    }
}

package eu.strasbourg.portlet.council.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    @Override
    public List<ManagementBarFilterItem> getManagementBarFilterItems(
            AssetVocabulary vocabulary) throws PortalException {
        List<ManagementBarFilterItem> managementBarFilterItems = new ArrayList<>();

        String tab = ParamUtil.getString(this._request, "tab");
        String orderByCol = this.getOrderByCol();
        String orderByType = this.getOrderByType();
        String filterCategoriesIds = this.getFilterCategoriesIds();
        String keywords = this.getKeywords();
        ThemeDisplay themeDisplay = (ThemeDisplay) this._request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) this._request
                .getAttribute(WebKeys.PORTLET_ID);
        PortletURL filterURL = PortletURLFactoryUtil.create(this._request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        int delta = this.getSearchContainer().getDelta();
        long vocabularyToRemove = vocabulary.getVocabularyId();
        filterURL.setParameter("tab", tab);
        filterURL.setParameter("orderByCol", orderByCol);
        filterURL.setParameter("orderByType", orderByType);
        filterURL.setParameter("filterCategoriesIds", filterCategoriesIds);
        filterURL.setParameter("keywords", keywords);
        filterURL.setParameter("delta", String.valueOf(delta));
        filterURL.setParameter("vocabularyToRemove",
                String.valueOf(vocabularyToRemove));

        ManagementBarFilterItem allItemsFilter = new ManagementBarFilterItem(
                false, vocabulary.getName() + " : "
                + LanguageUtil.get(Locale.FRENCH, "any"),
                filterURL.toString());
        managementBarFilterItems.add(allItemsFilter);

        List<AssetCategory> rootCategories = vocabulary.getCategories().stream()
                .filter(c -> c.isRootCategory()).collect(Collectors.toList());

        AssetVocabulary conseilVocab = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, themeDisplay.getScopeGroupId());
        if(conseilVocab != null && conseilVocab.getVocabularyId() == vocabulary.getVocabularyId()) {
            List<AssetCategory> authorizedRootCategories = new ArrayList<>();
            for (AssetCategory typeCouncilCat : UserRoleType.typeCategoriesForUser(themeDisplay)) {
                if(rootCategories.contains(typeCouncilCat)) {
                    authorizedRootCategories.add(typeCouncilCat);
                }
            }
            for (AssetCategory category : authorizedRootCategories) {
                populateManagementBar(managementBarFilterItems, category,
                        filterURL);
            }
        } else {
            for (AssetCategory category : rootCategories) {
                populateManagementBar(managementBarFilterItems, category,
                        filterURL);
            }
        }

        return managementBarFilterItems;
    }

    @Override
    protected List<ManagementBarFilterItem> populateManagementBar(
            List<ManagementBarFilterItem> managementBarFilterItems,
            AssetCategory category, PortletURL filterURL) throws PortalException {

        ManagementBarFilterItem managementBarFilterItem = getCategoryBarFilterItem(
                category, filterURL);
        managementBarFilterItems.add(managementBarFilterItem);

        // On a supprimé la recherche des enfants de catégories pour ne garder que les premières catégories parentes
        // Ainsi on a que les catégories de Type de conseil pour le filtre par Conseil

        return managementBarFilterItems;
    }

}

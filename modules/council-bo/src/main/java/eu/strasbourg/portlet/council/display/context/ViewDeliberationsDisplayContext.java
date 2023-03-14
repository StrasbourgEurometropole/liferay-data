package eu.strasbourg.portlet.council.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.constants.CouncilConstants;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
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

public class ViewDeliberationsDisplayContext extends ViewListBaseDisplayContext<Deliberation> {

    private List<Deliberation> deliberations;
    private String sessionCategoryToAdd;

    public ViewDeliberationsDisplayContext(RenderRequest request, RenderResponse response, String categoryToAdd) {
        super(Deliberation.class, request, response);
        this.sessionCategoryToAdd=categoryToAdd;
        try {
            // Hack : forçage du delta du SearchContainer
            // TODO : Changer le ViewListBaseDisplayContext pour mettre en place la prise en compte du delta par default
            this.getSearchContainer().setDelta(100);
        } catch (PortalException e) {
            _log.error(e.getMessage(), e);
        }
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

    @SuppressWarnings("unused")
    public String getStageDeliberationName(long id) {
        return StageDeliberation.get(id).getName();
    }

    /**
     * Class CSS de la couleur du Statut
     */
    @SuppressWarnings("unused")
    public String getCSSClass(Deliberation deliberation) {
        String cssClass="";
        if(deliberation.isAdopte()) {
            cssClass="green";
        } else if (deliberation.isRejete()) {
            cssClass="red";
        } else if (deliberation.isAffichageEnCours() || deliberation.isVoteOuvert()) {
            cssClass="focus-stage";
        }

        return cssClass;
    }

    @Override
    public String getFilterCategoriesIds() throws PortalException {

        if (Validator.isNotNull(_filterCategoriesIds)) {
            return _filterCategoriesIds;
        }

        _filterCategoriesIds = ParamUtil.getString(_request, "filterCategoriesIds");
        if (_filterCategoriesIds.length() == 0) {
            _filterCategoriesIds = ",";
        }

        Long vocabularyToRemove = ParamUtil.getLong(_request,"vocabularyToRemove");
        if (vocabularyToRemove > 0) {
            AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(vocabularyToRemove);
            List<AssetCategory> categories = vocabulary.getCategories();

            for (AssetCategory category : categories) {
                if (_filterCategoriesIds.contains(String.valueOf(category.getCategoryId()))) {
                    _filterCategoriesIds = _filterCategoriesIds.replace("," + category.getCategoryId(), "");
                }
            }
            _filterCategoriesIds = _filterCategoriesIds.replace(vocabularyToRemove + ",", "");
        }

        String categoryToAdd = sessionCategoryToAdd;
        if (Validator.isNotNull(categoryToAdd)) {
            _filterCategoriesIds += categoryToAdd + ",";
        }

        return _filterCategoriesIds;
    }

    @Override
    public List<ManagementBarFilterItem> getManagementBarFilterItems(AssetVocabulary vocabulary) throws PortalException {

        List<ManagementBarFilterItem> managementBarFilterItems = new ArrayList<>();

        String tab = ParamUtil.getString(this._request, "tab");
        String orderByCol = this.getOrderByCol();
        String orderByType = this.getOrderByType();
        String filterCategoriesIds = this.getFilterCategoriesIds();
        String keywords = this.getKeywords();
        String portletName = (String) this._request.getAttribute(WebKeys.PORTLET_ID);
        int delta = this.getSearchContainer().getDelta();
        long vocabularyToRemove = vocabulary.getVocabularyId();

        ThemeDisplay themeDisplay = (ThemeDisplay) this._request.getAttribute(WebKeys.THEME_DISPLAY);
        PortletURL filterURL = PortletURLFactoryUtil.create(this._request, portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

        filterURL.setParameter("tab", tab);
        filterURL.setParameter("orderByCol", orderByCol);
        filterURL.setParameter("orderByType", orderByType);
        filterURL.setParameter("filterCategoriesIds", filterCategoriesIds);
        filterURL.setParameter("keywords", keywords);
        filterURL.setParameter("delta", String.valueOf(delta));
        filterURL.setParameter("vocabularyToRemove", String.valueOf(vocabularyToRemove));

        String label = vocabulary.getName() + " : " + LanguageUtil.get(Locale.FRENCH, "any");
        ManagementBarFilterItem allItemsFilter = new ManagementBarFilterItem(false, label, filterURL.toString());
        managementBarFilterItems.add(allItemsFilter);

        List<AssetCategory> rootCategories = vocabulary.getCategories().stream()
                .filter(c -> c.isRootCategory()).collect(Collectors.toList());

        AssetVocabulary conseilVocab = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, themeDisplay.getScopeGroupId());

        if(conseilVocab != null && conseilVocab.getVocabularyId() == vocabulary.getVocabularyId()) {
            List<AssetCategory> authorizedRootCategories = new ArrayList<>();

            addCategorieAucunConseilSelectionne(themeDisplay, authorizedRootCategories);

            for (AssetCategory typeCouncilCat : UserRoleType.typeCategoriesForUser(themeDisplay)) {
                if(rootCategories.contains(typeCouncilCat)) {
                    authorizedRootCategories.add(typeCouncilCat);
                }
            }
            for (AssetCategory category : authorizedRootCategories) {
                populateManagementBar(managementBarFilterItems, category, filterURL);
            }
        } else {
            for (AssetCategory category : rootCategories) {
                populateManagementBar(managementBarFilterItems, category, filterURL);
            }
        }
        return managementBarFilterItems;
    }

    /**
     * Récupère et ajoute à la liste la categorie correspondant à aucun conseil sélectionné
     */
    private void addCategorieAucunConseilSelectionne(ThemeDisplay themeDisplay, List<AssetCategory> authorizedRootCategories) {

        long groupId = themeDisplay.getScopeGroupId();
        AssetCategory categoryAucunConseil = AssetVocabularyHelper.getCategory(CouncilConstants.NO_COUNCIL_CATEGORY_NAME, groupId);
        authorizedRootCategories.add(categoryAucunConseil);
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass());
}

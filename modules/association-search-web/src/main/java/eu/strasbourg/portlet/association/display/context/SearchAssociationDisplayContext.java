package eu.strasbourg.portlet.association.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.association.configuration.SearchAssociationConfiguration;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.PracticeLocalServiceUtil;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SearchAssociationDisplayContext {

    public SearchAssociationDisplayContext(RenderRequest request, RenderResponse response) throws PortalException {

        this._response = response;
        this._request = request;
        this._themeDisplay = (ThemeDisplay) _request.getAttribute(WebKeys.THEME_DISPLAY);
        this._configuration = this._themeDisplay.getPortletDisplay()
                .getPortletInstanceConfiguration(SearchAssociationConfiguration.class);
        this.initSearchContainer();
        if (this.isUserSearch() || ParamUtil.getBoolean(this._request, "paginate")) {
            this.initEntries();
        } else {
            this._entries = new ArrayList<AssetEntry>();
        }
        // Gestion du log
        if (this.isUserSearch()) {
            this.logSearch();
        }
        long logSearchId = ParamUtil.getLong(request, "searchLogId");
        if (logSearchId > 0) {
            request.setAttribute("logSearchId", logSearchId);
        }
    }

    private void initSearchContainer() {
        PortletURL iteratorURL = this._response.createRenderURL();
        iteratorURL.setParameter("orderByCol", "modified_sortable");
        iteratorURL.setParameter("orderByType", "desc");
        int i = 0;
        for (Long[] categoriesIds : this.getFilterCategoriesIds()) {
            iteratorURL.setParameter("vocabulary_" + i, ArrayUtil.toStringArray(categoriesIds));
            i++;
        }
        iteratorURL.setParameter("paginate", String.valueOf(true));
        iteratorURL.setParameter("vocabulariesCount", String.valueOf(i));

        iteratorURL.setParameter("className", Practice.class.getName());

        if (this._searchContainer == null) {
            this._searchContainer = new SearchContainer<AssetEntry>(this._request, iteratorURL, null,
                    "no-entries-were-found");
            this._searchContainer.getIteratorURL().setParameter("delta", String.valueOf(this.getDelta()));

            this._searchContainer.setDelta((int) this.getDelta());
        }
    }

    /**
     * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
     * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
     */
    private List<Long[]> getFilterCategoriesIds() {
        if (_filterCategoriesIds == null) {
            List<Long[]> filterCategoriesIds = new ArrayList<Long[]>();

            // On récupère les catégories des vocabulaire autre que pratique
            long vocabulariesCount = ParamUtil.getLong(this._request, "vocabulariesCount");
            for (long i = 0; i < vocabulariesCount; i++) {
                List<Long> categoriesIds = new ArrayList<Long>();
                Long[] categoriesIdsForVoc;
                categoriesIdsForVoc = ArrayUtil.toLongArray(ParamUtil.getLongValues(this._request, "vocabulary_" + i));
                for (long categoryIdForVoc : categoriesIdsForVoc) {
                    if (categoryIdForVoc > 0) {
                        categoriesIds.add(categoryIdForVoc);
                    }
                }
                if (categoriesIds.size() > 0) {
                    filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
                }
            }

            // on ajoute les catégories de la sous-sous-spécialité si elle existe
            Long[] practiceCategoriesIdsForVoc = ArrayUtil.toLongArray(ParamUtil.getLongValues(this._request, "subSubSpeciality"));
            if(practiceCategoriesIdsForVoc.length > 0)
                filterCategoriesIds.add(practiceCategoriesIdsForVoc);
            else{
                // sinon on ajoute les catégories de la sous-spécialité si elle existe
                practiceCategoriesIdsForVoc = ArrayUtil.toLongArray(ParamUtil.getLongValues(this._request, "subSpeciality"));
                if(practiceCategoriesIdsForVoc.length > 0)
                    filterCategoriesIds.add(practiceCategoriesIdsForVoc);
                else{
                    // sinon on ajoute les catégories de la spécialité
                    practiceCategoriesIdsForVoc = ArrayUtil.toLongArray(ParamUtil.getLongValues(this._request, "speciality"));
                    if(practiceCategoriesIdsForVoc.length > 0)
                        filterCategoriesIds.add(practiceCategoriesIdsForVoc);
                }
            }

            this._filterCategoriesIds = filterCategoriesIds;
        }
        return this._filterCategoriesIds;
    }

    /**
     * Retourne le nombre d'items par page à afficher
     */
    public long getDelta() {
        long deltaFromParam = ParamUtil.getLong(this._request, "delta");
        if (deltaFromParam > 0) {
            return deltaFromParam;
        }
        if (this._configuration.delta() > 0) {
            return this._configuration.delta();
        }
        return 12;
    }

    public boolean isUserSearch() {
        return GetterUtil.getBoolean(this._request.getAttribute("userSearch"));
    }

    /**
     * Effectue concrètement la recherche
     */
    private void initEntries() throws PortalException {
        HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(_request);

        SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

        // ClassNames de la configuration
        String[] classNames = {Practice.class.getName()};

        // scope global
        long globalGroupId = this._themeDisplay.getCompanyGroupId();

        // Group ID courant
        long groupId = this._themeDisplay.getScopeGroupId();

        // Catégories sélectionnées par l'utilisateur
        List<Long[]> categoriesIds = this.getFilterCategoriesIds();

        // Permet de remonter la hiérarchie des Request
        HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);


        // Recherche
        this._hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, false,
                "", false, "", null, null, categoriesIds, new ArrayList<Long[]>(),
                new String[]{}, "", false, this._themeDisplay.getLocale(), getSearchContainer().getStart(),
                getSearchContainer().getEnd(), "modified_sortable", true);

        List<AssetEntry> results = new ArrayList<AssetEntry>();
        if (this._hits != null) {
            int i = 0;
            for (float s : this._hits.getScores()) {
                _log.info(GetterUtil.getString(this._hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
                i++;
                if (i > 10)
                    break;
            }

            List<Long> associationList = new ArrayList<Long>();
            for (Document document : this._hits.getDocs()) {
                AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
                        GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));

                // on récupère l'association liée à la pratique si elle n'existe pas déjà dans les resultats
                Practice practice = PracticeLocalServiceUtil.fetchPractice(entry.getClassPK());
                if(Validator.isNotNull(practice) && !associationList.contains(practice.getAssociationId())){
                    entry = AssetEntryLocalServiceUtil.fetchEntry(Association.class.getName(), practice.getAssociationId());
                    results.add(entry);
                    associationList.add(practice.getAssociationId());
                }
            }
            this.getSearchContainer().setTotal(results.size());
        }

        this._entries = results;
    }

    /**
     * Retourne le searchContainer des entités
     */
    public SearchContainer<AssetEntry> getSearchContainer() {
        return this._searchContainer;
    }

    private void logSearch() throws PortalException {
        ServiceContext sc = ServiceContextFactory.getInstance(this._request);
        AssetEntry result1 = this.getEntries().size() > 0 ? this.getEntries().get(0) : null;
        AssetEntry result2 = this.getEntries().size() > 1 ? this.getEntries().get(1) : null;
        AssetEntry result3 = this.getEntries().size() > 2 ? this.getEntries().get(2) : null;
        long searchTime = (long) (this._hits.getSearchTime() * 1000);
        SearchLog searchLog = SearchLogLocalServiceUtil.addSearchLog(sc, "",
                this.getSearchContainer().getTotal(), result1, result2, result3, null, searchTime);
        this.getSearchContainer().getIteratorURL().setParameter("searchLogId",
                String.valueOf(searchLog.getSearchLogId()));
        this._request.setAttribute("searchLogId", searchLog.getSearchLogId());
    }

    /**
     * Retourne les résultats de la recherche
     */
    public List<AssetEntry> getEntries() throws PortalException {
        return this._entries;
    }

    /**
     * On récupère les préférences de catégories et on les envoie à la JSP C'est
     * une liste d'AssetVocabulary, qui correspond aux vocabulaires pour
     * lesquels on souhaite afficher une liste déroulante dans le moteur de
     * recherche
     */
    public AssetVocabulary getDomainVocabulary() throws PortalException {
        if(Validator.isNull(_domainVocabulary)) {
            AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
            AssetVocabulary domainsVocabulary = assetVocabularyAccessor.getPractice(this._themeDisplay.getScopeGroupId());
            _domainVocabulary = domainsVocabulary;
        }

        return _domainVocabulary;
    }

    /**
     * On récupère les préférences de catégories et on les envoie à la JSP C'est
     * une liste d'AssetVocabulary, qui correspond aux vocabulaires pour
     * lesquels on souhaite afficher une liste déroulante dans le moteur de
     * recherche
     */
    public List<AssetVocabulary> getVocabularies() throws PortalException {
        if (this._vocabularies == null) {
            this._vocabularies = new ArrayList<AssetVocabulary>();

            AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();

            AssetVocabulary publicsVocabulary = assetVocabularyAccessor.gePracticePublic(this._themeDisplay.getScopeGroupId());
            if (publicsVocabulary != null) {
                _vocabularies.add(publicsVocabulary);
            }

            AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.TERRITORY);
            if (territoriesVocabulary != null) {
                _vocabularies.add(territoriesVocabulary);
            }

            AssetVocabulary accessibiliestyVocabulary = assetVocabularyAccessor.getAccessibility(this._themeDisplay.getScopeGroupId());
            if (publicsVocabulary != null) {
                _vocabularies.add(accessibiliestyVocabulary);
            }
        }
        return this._vocabularies;
    }

    /**
     * Retourne la liste des catégories du vocabulaire passé en paramètre,
     * triées par la valeur de la propriété "order" de
     * chaque catégorie
     */
    public List<AssetCategory> getSortedCategories(AssetVocabulary vocabulary) {
        List<AssetCategory> sortedCategories = new ArrayList<AssetCategory>();

        if(Validator.isNotNull(vocabulary)) {
            // Toutes les catégories du vocabulaire
            List<AssetCategory> categories = this.getDropdownRootCategories(vocabulary);

            // trie des catégories par la propriété order si elle existe
            Map<String, AssetCategory> order_category = new HashMap<String, AssetCategory>();
            List<AssetCategory> categoriesWithoutOrder = new ArrayList<AssetCategory>();
            for (AssetCategory assetCategory : categories) {
                if (assetCategory != null) {
                    String orderString = AssetVocabularyHelper.getCategoryProperty(assetCategory.getCategoryId(), "order");
                    if (orderString.equals("")) {
                        categoriesWithoutOrder.add(assetCategory);
                    } else {
                        order_category.put(orderString, assetCategory);
                    }
                }
            }

            for (AssetCategory assetCategory : order_category.values()) {
                sortedCategories.add(assetCategory);
            }
            sortedCategories.addAll(categoriesWithoutOrder);
        }

        return sortedCategories;
    }

    /**
     * Retourne la liste des catégories du vocabulaire passé en paramètre,
     * triées par la valeur de la propriété "order" de
     * chaque catégorie
     */
    public List<AssetCategory> getSortedCategories(AssetVocabulary vocabulary, long parentCategoryId) {
        List<AssetCategory> sortedCategories = new ArrayList<AssetCategory>();

        if(Validator.isNotNull(vocabulary)) {
            // récupère les catégoies ayant pour parent parentCategoryId
            List<AssetCategory> categories = AssetVocabularyHelper.getChild(parentCategoryId);

            // trie des catégories par la propriété order si elle existe
            Map<String, AssetCategory> order_category = new HashMap<String, AssetCategory>();
            List<AssetCategory> categoriesWithoutOrder = new ArrayList<AssetCategory>();
            for (AssetCategory assetCategory : categories) {
                if (assetCategory != null) {
                    String orderString = AssetVocabularyHelper.getCategoryProperty(assetCategory.getCategoryId(), "order");
                    if (orderString.equals("")) {
                        categoriesWithoutOrder.add(assetCategory);
                    } else {
                        order_category.put(orderString, assetCategory);
                    }
                }
            }

            for (AssetCategory assetCategory : order_category.values()) {
                sortedCategories.add(assetCategory);
            }
            sortedCategories.addAll(categoriesWithoutOrder);
        }

        return sortedCategories;
    }

    /**
     * Renvoie la liste des catégories d'un vocabulaire à afficher en front. Si
     * on est dans le vocabulaire territoire, on renvoie
     * les catégories enfants de strasbourg. Sinon toutes les catégories
     */
    public List<AssetCategory> getDropdownRootCategories(AssetVocabulary vocabulary) {
        // Toutes les catégories du vocabulaire
        List<AssetCategory> allCategories = vocabulary.getCategories();

        if(vocabulary.getName().toLowerCase().equals(VocabularyNames.TERRITORY)) {

            // On récupère la catégorie strasbourg
            AssetCategory strasbourgCategory = AssetVocabularyHelper.getCategory("Strasbourg", _themeDisplay.getCompanyGroupId());

            List<AssetCategory> districtCategoriesForVocabulary = allCategories.stream()
                    .filter(c -> c.getParentCategoryId() == strasbourgCategory.getCategoryId())
                    .collect(Collectors.toList());

            return districtCategoriesForVocabulary;
        }

        // Sinon on renvoie les catégories racines du vocabulaire
        return allCategories.stream().filter(c -> c.isRootCategory()).collect(Collectors.toList());
    }

    public String getFilterCategoriesIdsString() {
        if (Validator.isNull(this._filterCategoriesIdString)) {
            String filterCategoriesIdsString = "";
            for (Long[] filterCategoriesForVoc : this.getFilterCategoriesIds()) {
                for (long filterCategoryId : filterCategoriesForVoc) {
                    if (filterCategoriesIdsString.length() > 0) {
                        filterCategoriesIdsString += ",";
                    }
                    filterCategoriesIdsString += filterCategoryId;
                }
            }
            this._filterCategoriesIdString = filterCategoriesIdsString;
        }
        return this._filterCategoriesIdString;
    }

    public Map<String, Object> getTemplateContextObjects(AssetEntry entry) {
        Map<String, Object> contextObjects = new HashMap<String, Object>();
        if (entry.getAssetRenderer() != null) {
            contextObjects.put("entry", entry.getAssetRenderer().getAssetObject());
        }
        return contextObjects;
    }

    /**
     * Retourne une map avec comme clés les classNames des entités retournées et
     * comme valeurs les ids des ADT
     */
    public String getDisplayStyle() throws NumberFormatException, PortalException {
        if (this._displayStyle == null) {
            String templateKey = this._configuration.templateKey();
            if (Validator.isNotNull(templateKey)) {
                this._displayStyle = "ddmTemplate_" + templateKey;
            }
        }
        return this._displayStyle;
    }

    public List<Object> getTemplateEntries() {
        return new ArrayList<Object>();
    }

    /**
     * Retourne l'URL sur laquelle aller pour avoir X items par page
     */
    public String getURLForDelta(long delta) {
        PortletURL url = this.getSearchContainer().getIteratorURL();
        url.setParameter("delta", String.valueOf(delta));
        String valueToReturn = url.toString();
        url.setParameter("delta", String.valueOf(this.getDelta()));
        return valueToReturn;
    }

    public Pager getPager() {
        return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(), this.getSearchContainer().getCur());
    }

    /**
     * Retourne l'URL sur laquelle aller pour accéder à la Xième page
     */
    public String getURLForPage(long pageIndex) {
        PortletURL url = this.getSearchContainer().getIteratorURL();
        url.setParameter("cur", String.valueOf(pageIndex));
        String valueToReturn = url.toString();
        url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
        return valueToReturn;
    }

    /**
     * Retourne l'URL de la page d'accueil
     */
    public String getHomeURL() {
        if (this._themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname() != null
                || this._themeDisplay.getScopeGroup().isStagingGroup()) {
            return "/web" + this._themeDisplay.getScopeGroup().getFriendlyURL() + "/";
        } else {
            return "/";
        }

    }

    private static Log _log = LogFactoryUtil.getLog(SearchAssociationDisplayContext.class);

    private final RenderRequest _request;
    private final RenderResponse _response;
    private final ThemeDisplay _themeDisplay;
    private SearchAssociationConfiguration _configuration;

    private SearchContainer<AssetEntry> _searchContainer;
    private List<AssetEntry> _entries;
    private List<AssetVocabulary> _vocabularies;
    private AssetVocabulary _domainVocabulary;
    private List<Long[]> _filterCategoriesIds;
    private String _filterCategoriesIdString;
    private String _displayStyle;
    private Hits _hits;

}
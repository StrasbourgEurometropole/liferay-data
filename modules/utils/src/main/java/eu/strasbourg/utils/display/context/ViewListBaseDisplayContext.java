package eu.strasbourg.utils.display.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.SearchHelper;

public abstract class ViewListBaseDisplayContext<T> extends BaseDisplayContext {
	protected String _filterCategoriesIds;
	protected String _keywords;
	protected SearchContainer<T> _searchContainer;
	protected List<AssetVocabulary> _vocabularies;
	private final Class<T> tClass;

	public ViewListBaseDisplayContext(Class<T> tClass, RenderRequest request,
		RenderResponse response) {
		super(request, response);
		this.tClass = tClass;
	}

	/**
	 * Retourne le SearchContainer
	 */
	public SearchContainer<T> getSearchContainer() throws PortalException {

		if (this._searchContainer == null) {
			PortletURL iteratorURL = this._response.createRenderURL();
			iteratorURL.setParameter("tab", "events");
			iteratorURL.setParameter("orderByCol", this.getOrderByCol());
			iteratorURL.setParameter("orderByType", this.getOrderByType());
			iteratorURL.setParameter("filterCategoriesIds",
				this.getFilterCategoriesIds());
			iteratorURL.setParameter("keywords", this.getKeywords());

			this._searchContainer = new SearchContainer<T>(this._request,
				iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
				.setRowChecker(new EmptyOnClickRowChecker(this._response));
			this._searchContainer.setOrderByColParam("orderByCol");
			this._searchContainer.setOrderByTypeParam("orderByType");
		}
		return _searchContainer;
	}

	/**
	 * Retourne les Hits de recherche
	 */
	protected Hits getHits(long groupId) throws PortalException {
		HttpServletRequest servletRequest = PortalUtil
			.getHttpServletRequest(_request);
		SearchContext searchContext = SearchContextFactory
			.getInstance(servletRequest);

		// Recherche des hits
		String keywords = ParamUtil.getString(servletRequest, "keywords");
		Hits hits = SearchHelper.getBOSearchHits(searchContext,
			this.getSearchContainer().getStart(),
			this.getSearchContainer().getEnd(), tClass.getName(), groupId,
			this.getFilterCategoriesIds(), keywords,
			this.getOrderByColSearchField(),
			"desc".equals(this.getOrderByType()));

		// Total
		int count = (int) SearchHelper.getBOSearchCount(searchContext,
			tClass.getName(), groupId,
			this.getFilterCategoriesIds(), keywords);
		this.getSearchContainer().setTotal(count);

		return hits;
	}

	/**
	 * Retourne les mots clés de recherche saisis
	 */
	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(_request, "keywords");
		}
		return _keywords;
	}

	/**
	 * Renvoie la colonne sur laquelle on fait le tri
	 * 
	 * @return
	 */
	public String getOrderByCol() {
		return ParamUtil.getString(this._request, "orderByCol",
			"modified-date");
	}

	/**
	 * Renvoie le nom de la colonne sur laquelle on fait le tri pour
	 * ElasticSearch
	 * 
	 * @return
	 */
	public String getOrderByColSearchField() {
		switch (this.getOrderByCol()) {
		case "title":
			return "localized_title_fr_FR_sortable";
		case "modified-date":
			return "modified_sortable";
		case "publication-date":
			return "publishDate_sortable";
		case "status":
			return "status_sortable";
		default:
			return "modified_sortable";
		}
	}

	/**
	 * Retourne le type de tri (desc ou asc)
	 */
	public String getOrderByType() {
		return ParamUtil.getString(this._request, "orderByType", "desc");
	}

	/**
	 * Retourne la liste des colonnes sur lesquelles on peut faire le tri
	 * 
	 * @return
	 */
	public String[] getOrderColumns() {
		return new String[] { "title", "modified-date", "publication-date",
			"status" };
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
			"filterCategoriesIds", ",");
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
		String categoryToAdd = ParamUtil.getString(_request, "categoryToAdd");
		if (Validator.isNotNull(categoryToAdd)) {
			_filterCategoriesIds += categoryToAdd + ",";
		}
		return _filterCategoriesIds;
	}

	/**
	 * Retourne le nom à afficher pour un filtre "Vocabulaire" - Si aucune
	 * catégorie du vocabulaire n'a été sélectionnée, le nom du vocabulaire - Si
	 * une catégorie du vocabulaire a été sélectionnée, le nom de la catégorie
	 */
	public String getVocabularyFilterLabel(AssetVocabulary vocabulary)
		throws PortalException {
		List<AssetCategory> categories = vocabulary.getCategories();
		for (AssetCategory category : categories) {
			if (this.getFilterCategoriesIds()
				.contains(String.valueOf(category.getCategoryId()))) {
				return category.getName();
			}
		}
		return vocabulary.getName();
	}

	public List<AssetVocabulary> getGlobalVocabularies() {
		if (this._vocabularies == null) {
			this._vocabularies = getAttachedVocabularies(
				this._themeDisplay.getCompanyGroupId());
		}
		return this._vocabularies;
	}

	public List<AssetVocabulary> getVocabularies() {
		if (this._vocabularies == null) {
			this._vocabularies = getAttachedVocabularies(
				this._themeDisplay.getScopeGroupId());
		}
		return this._vocabularies;
	}

	private List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		long companyGroupId = this._themeDisplay.getCompanyGroupId();
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(tClass);
		for (AssetVocabulary vocabulary : vocabularies) {
			if ((vocabulary.getGroupId() == groupId || vocabulary.getGroupId() == companyGroupId)
				&& LongStream.of(vocabulary.getSelectedClassNameIds())
					.anyMatch(c -> c == classNameId)) {
				attachedVocabularies.add(vocabulary);
			}
		}
		return attachedVocabularies;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
			tClass.getName());
	}
	
	/**
	 * Affichage des filtres par catégories
	 */
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
		for (AssetCategory category : rootCategories) {
			populateManagementBar(managementBarFilterItems, category,
				filterURL);
		}

		return managementBarFilterItems;
	}

	private List<ManagementBarFilterItem> populateManagementBar(
		List<ManagementBarFilterItem> managementBarFilterItems,
		AssetCategory category, PortletURL filterURL) throws PortalException {

		ManagementBarFilterItem managementBarFilterItem = getCategoryBarFilterItem(
			category, filterURL);
		managementBarFilterItems.add(managementBarFilterItem);

		for (AssetCategory childCategory : AssetVocabularyHelper
			.getChild(category.getCategoryId())) {
			populateManagementBar(managementBarFilterItems, childCategory, filterURL);
		}

		return managementBarFilterItems;
	}

	private ManagementBarFilterItem getCategoryBarFilterItem(
		AssetCategory category, PortletURL filterURL) throws PortalException {
		boolean isActive = this.getFilterCategoriesIds()
			.contains(String.valueOf(category.getCategoryId()));

		String prefix = "";
		for (int i = 0; i < category.getAncestors().size(); i++) {
			prefix += " - ";
		}
		String label = prefix + category.getName();

		long categoryToAdd = category.getCategoryId();

		filterURL.setParameter("categoryToAdd", String.valueOf(categoryToAdd));
		String url = filterURL.toString();

		return new ManagementBarFilterItem(isActive, label, url);
	}


}

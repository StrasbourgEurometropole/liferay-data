package eu.strasbourg.portlet.search_asset.display.context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;

public class SearchAssetDisplayContext {

	public SearchAssetDisplayContext(RenderRequest request,
		RenderResponse response) throws PortalException {

		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) _request
			.getAttribute(WebKeys.THEME_DISPLAY);
		this._configuration = this._themeDisplay.getPortletDisplay()
			.getPortletInstanceConfiguration(SearchAssetConfiguration.class);
		this.initSearchContainer();
		if (!this._configuration.hideResultsBeforeSearch()
			|| this.isUserSearch()
			|| ParamUtil.getBoolean(this._request, "paginate")) {
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
		iteratorURL.setParameter("orderByCol", this.getSortField());
		iteratorURL.setParameter("orderByType", this.getSortType());
		int i = 0;
		for (Long[] categoriesIds : this.getFilterCategoriesIds()) {
			iteratorURL.setParameter("vocabulary_" + i,
				ArrayUtil.toStringArray(categoriesIds));
			i++;
		}
		iteratorURL.setParameter("paginate", String.valueOf(true));
		iteratorURL.setParameter("vocabulariesCount", String.valueOf(i));

		iteratorURL.setParameter("className", this.getFilterClassNames());

		iteratorURL.setParameter("keywords",
			String.valueOf(this.getKeywords()));
		if (this._configuration.dateField()) {
			iteratorURL.setParameter("fromDay",
				String.valueOf(this.getFromDay()));
			iteratorURL.setParameter("fromMonth",
				String.valueOf(this.getFromMonthIndex()));
			iteratorURL.setParameter("fromYear",
				String.valueOf(this.getFromYear()));
			iteratorURL.setParameter("toDay", String.valueOf(this.getToDay()));
			iteratorURL.setParameter("toMonth",
				String.valueOf(this.getToMonthIndex()));
			iteratorURL.setParameter("toYear",
				String.valueOf(this.getToYear()));
		}

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<AssetEntry>(
				this._request, iteratorURL, null, "no-entries-were-found");

			this._searchContainer
				.setDelta((int) (this._configuration.delta() > 0
					? this._configuration.delta() : 12));
		}
	}

	/**
	 * Effectue concrètement la recherche
	 */
	private void initEntries() throws PortalException {
		HttpServletRequest servletRequest = PortalUtil
			.getHttpServletRequest(_request);

		SearchContext searchContext = SearchContextFactory
			.getInstance(servletRequest);

		// Mots clés
		String keywords = ParamUtil.getString(this._request, "keywords");

		// ClassNames de la configuration
		String[] classNames = this.getFilterClassNames();

		// Inclusion ou non du scope global
		boolean globalScope = this._configuration.globalScope();
		long globalGroupId = this._themeDisplay.getCompanyGroupId();

		// Group ID courant
		long groupId = this._themeDisplay.getScopeGroupId();

		// Catégories sélectionnées par l'utilisateur
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();

		// Préfiltre catégories
		String prefilterCategoriesIdsString = this._configuration
			.prefilterCategoriesIds();
		List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
		for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString
			.split(";")) {
			Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
				.toLongArray(StringUtil
					.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
			prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
		}

		// Préfiltre tags
		String prefilterTagsNamesString = this._configuration
			.prefilterTagsNames();
		String[] prefilterTagsNames = StringUtil
			.split(prefilterTagsNamesString);

		// Champ date
		boolean dateField = this._configuration.dateField();
		String dateFieldName = this._configuration.defaultSortField();
		LocalDate fromDate = LocalDate.of(this.getFromYear(),
			this.getFromMonthValue(), this.getFromDay());
		LocalDate toDate = LocalDate.of(this.getToYear(), this.getToMonthValue(),
			this.getToDay());

		// Ordre
		String sortField = this.getSortField();
		boolean isSortDesc = "desc".equals(this.getSortType());

		// Recherche
		this._hits = SearchHelper.getGlobalSearchHits(searchContext, classNames,
			groupId, globalGroupId, globalScope, keywords, dateField,
			dateFieldName, fromDate, toDate, categoriesIds,
			prefilterCategoriesIds, prefilterTagsNames,
			this._themeDisplay.getLocale(), getSearchContainer().getStart(),
			getSearchContainer().getEnd(), sortField, isSortDesc);
		List<AssetEntry> results = new ArrayList<AssetEntry>();
		if (this._hits != null) {
			int i = 0;
			for (float s : this._hits.getScores()) {
				_log.info(GetterUtil.getString(
					this._hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
				i++;
				if (i > 10)
					break;
			}

			for (Document document : this._hits.getDocs()) {
				AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
					GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (entry != null) {
					results.add(entry);
				}
			}
			long count = SearchHelper.getGlobalSearchCount(searchContext,
				classNames, groupId, globalGroupId, globalScope, keywords,
				dateField, dateFieldName, fromDate, toDate, categoriesIds,
				prefilterCategoriesIds, prefilterTagsNames,
				this._themeDisplay.getLocale());
			this.getSearchContainer().setTotal((int) count);
		}

		this._entries = results;
	}

	/**
	 * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
	 * entries
	 */
	private List<Long[]> getFilterCategoriesIds() {
		if (_filterCategoriesIds == null) {
			List<Long[]> filterCategoriesIds = new ArrayList<Long[]>();
			long vocabulariesCount = ParamUtil.getLong(this._request,
				"vocabulariesCount");
			for (long i = 0; i < vocabulariesCount; i++) {
				List<Long> categoriesIds = new ArrayList<Long>();
				Long[] categoriesIdsForVoc;
				categoriesIdsForVoc = ArrayUtil.toLongArray(
					ParamUtil.getLongValues(this._request, "vocabulary_" + i));
				for (long categoryIdForVoc : categoriesIdsForVoc) {
					if (categoryIdForVoc > 0) {
						categoriesIds.add(categoryIdForVoc);
					}
				}
				if (categoriesIds.size() > 0) {
					filterCategoriesIds.add(ArrayUtil.toLongArray(
						categoriesIds.stream().mapToLong(l -> l).toArray()));
				}
			}
			this._filterCategoriesIds = filterCategoriesIds;
		}
		return this._filterCategoriesIds;
	}

	public String getFilterCategoriesIdsString() {
		if (Validator.isNull(this._filterCategoriesIdString)) {
			String filterCategoriesIdsString = "";
			for (Long[] filterCategoriesForVoc : this
				.getFilterCategoriesIds()) {
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

	/**
	 * Renvoie la liste des types d'entités sur lesquels on souhaite rechercher
	 * les entries
	 */
	private String[] getFilterClassNames() {
		if (_filterClassNames == null) {
			this._filterClassNames = ParamUtil.getStringValues(this._request,
				"className");
		}
		// Si la liste est vide, on renvoie la liste complète paramétrée via la
		// configuration (on ne recherche pas sur rien !)
		if (this._filterClassNames.length == 0) {
			this._filterClassNames = ArrayUtil
				.toStringArray(this.getClassNames());
		}
		return this._filterClassNames;
	}

	/**
	 * Retourne la liste des types d'entités sur lesquels on souhaite rechercher
	 * les entries, sous forme de String
	 */
	public String getFilterClassNamesString() {
		return StringUtil.merge(getFilterClassNames());
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

			String vocabularyIdsString = this._configuration.vocabulariesIds();
			if (Validator.isNotNull(vocabularyIdsString)) {
				long[] vocabularyIds = Arrays
					.stream(vocabularyIdsString.split(","))
					.mapToLong(Long::parseLong).toArray();
				for (long vocabularyId : vocabularyIds) {
					AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
						.fetchAssetVocabulary(vocabularyId);
					if (vocabulary != null) {
						this._vocabularies.add(vocabulary);
					}
				}
			}
		}
		return this._vocabularies;
	}

	/**
	 * Retourne la liste des types de contrôles pour les vocabulaires
	 */
	public String[] getVocabulariesControlTypes() {
		String vocabulariesControlTypesString = this._configuration
			.vocabulariesControlTypes();
		if (vocabulariesControlTypesString != null) {
			return vocabulariesControlTypesString.split(",");
		} else {
			return null;
		}
	}

	/**
	 * Renvoie la liste des catégories d'un vocabulaire à afficher en front. Si
	 * un(des) préfiltre(s) est sélectionné pour ce vocabulaire, on renvoie
	 * ce(s) préfiltre(s). Sinon on ne renvoie que les catégories racines, la
	 * JSP se chargeant d'afficher l'arbre des enfants
	 */
	public List<AssetCategory> getDropdownRootCategories(
		AssetVocabulary vocabulary) {
		// Toutes les catégories du vocabulaire
		List<AssetCategory> allCategories = vocabulary.getCategories();

		// String contenant les IDs des catégories des préfiltres, séparés par
		// des "," et des ";"
		String prefilterCategoriesIdsString = this._configuration
			.prefilterCategoriesIds();
		// Si ce préfiltre a du contenu
		if (prefilterCategoriesIdsString.length() > 0) {
			// On récupère un array de long
			long[] prefilterCategoriesIds = Arrays
				.stream(prefilterCategoriesIdsString.split("(,)|(;)"))
				.mapToLong(Long::valueOf).toArray();

			// Et on fait l'interersection avec la liste de toutes les
			// catégories du vocabulaire
			List<AssetCategory> prefilteredCategoriesForVocabulary = allCategories
				.stream()
				.filter(c -> LongStream.of(prefilterCategoriesIds)
					.anyMatch(x -> x == c.getCategoryId()))
				.collect(Collectors.toList());

			// Si cette intersection a du contenu on la renvoie
			if (prefilteredCategoriesForVocabulary.size() > 0) {
				return prefilteredCategoriesForVocabulary;
			}
		}

		// Sinon on renvoie les catégories racines du vocabulaire
		return allCategories.stream().filter(c -> c.isRootCategory())
			.collect(Collectors.toList());
	}

	/**
	 * Retourne les mots-clés de recherche
	 */
	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(_request, "keywords");
		}
		return _keywords;
	}

	/**
	 * Retourne le searchContainer des entités
	 */
	public SearchContainer<AssetEntry> getSearchContainer() {
		return this._searchContainer;
	}

	/**
	 * Retourne les résultats de la recherche
	 */
	public List<AssetEntry> getEntries() throws PortalException {
		return this._entries;
	}

	/**
	 * Retourne le champ sur lequel on classe les résultats
	 */
	public String getSortField() {
		String sortFieldFromParam = ParamUtil.getString(this._request,
			"sortFieldAndType");
		if (Validator.isNull(sortFieldFromParam)) {
			if (Validator.isNull(this.getKeywords())) {
				return Validator
					.isNotNull(this._configuration.defaultSortField())
						? this._configuration.defaultSortField()
						: "modified_sortable";
			} else {
				return "score";
			}
		} else {
			return sortFieldFromParam.split(",")[0];
		}
	}

	/**
	 * Retourne le type de classement des résultats (croissant ou décroissant)
	 */
	public String getSortType() {
		if (this.getSortField() == "score") {
			return "desc";
		} else {
			String sortTypeFromParam = ParamUtil.getString(this._request,
				"sortFieldAndType");
			if (Validator.isNull(sortTypeFromParam)) {
				return Validator
					.isNotNull(this._configuration.defaultSortType())
						? this._configuration.defaultSortType() : "desc";
			} else {
				return sortTypeFromParam.split(",")[1];
			}
		}
	}

	/**
	 * Retourne une map avec comme clés les classNames des entités retournées et
	 * comme valeurs les ids des ADT
	 */
	public Map<String, String> getTemplatesMap()
		throws NumberFormatException, PortalException {
		if (this._templatesMap == null) {
			Map<String, String> templatesMap = new HashMap<String, String>();
			String templatesKeys = this._configuration.templatesKeys();
			int i = 0;
			for (String templateKey : templatesKeys.split(",")) {
				if (Validator.isNotNull(templateKey)) {
					String className = this._configuration.assetClassNames()
						.split(",")[i];
					templatesMap.put(className, "ddmTemplate_" + templateKey);
				}
				i++;
			}
			if (this._configuration.searchJournalArticle()) {
				templatesMap.put("com.liferay.journal.model.JournalArticle",
					"ddmTemplate_"
						+ this._configuration.journalArticleTemplateKey());
			}
			if (this._configuration.searchDocument()) {
				templatesMap.put(DLFileEntry.class.getName(),
					"ddmTemplate_" + this._configuration.documentTemplateKey());
			}
			this._templatesMap = templatesMap;
		}
		return this._templatesMap;
	}

	/**
	 * Retourne la liste des class names sur lesquelles on recherche
	 */
	public List<String> getClassNames() {
		if (this._classNames == null) {
			List<String> classNames = new ArrayList<String>();
			for (String className : this._configuration.assetClassNames()
				.split(",")) {
				if (Validator.isNotNull(className)) {
					classNames.add(className);
				}
			}
			if (this._configuration.searchJournalArticle()) {
				classNames.add("com.liferay.journal.model.JournalArticle");
			}
			if (this._configuration.searchDocument()) {
				classNames.add(DLFileEntry.class.getName());
			}
			this._classNames = classNames;
		}
		return this._classNames;
	}

	/**
	 * Retourne true si les champs dates doivent être affichés
	 */
	public boolean isDateField() {
		return this._configuration.dateField();
	}

	public int getFromDay() {
		int fromParam = ParamUtil.getInteger(this._request, "fromDay");
		if (fromParam > 0) {
			return fromParam;
		} else {
			if (this._configuration.defaultDateRange() < 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange())
					.getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}

	}
	
	public int getFromMonthIndex() {
		return getFromMonthValue() - 1;
	}

	public int getFromMonthValue() {
		String fromMonthString = ParamUtil.getString(this._request,
			"fromMonth");
		if (Validator.isNull(fromMonthString)) {
			if (this._configuration.defaultDateRange() < 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange())
					.getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return ParamUtil.getInteger(this._request, "fromMonth") + 1;
		}
	}

	public int getFromYear() {
		int fromParam = ParamUtil.getInteger(this._request, "fromYear");
		if (fromParam > 0) {
			return fromParam;
		} else {
			if (this._configuration.defaultDateRange() < 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	public int getToDay() {
		int toParam = ParamUtil.getInteger(this._request, "toDay");
		if (toParam > 0) {
			return toParam;
		} else {
			if (this._configuration.defaultDateRange() > 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange())
					.getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}
	}
	
	public int getToMonthIndex() {
		return getToMonthValue() - 1;
	}

	public int getToMonthValue() {
		String toMonthString = ParamUtil.getString(this._request, "toMonth");
		if (Validator.isNull(toMonthString)) {
			if (this._configuration.defaultDateRange() > 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange())
					.getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return ParamUtil.getInteger(this._request, "toMonth") + 1;
		}
	}

	public int getToYear() {
		int toParam = ParamUtil.getInteger(this._request, "toYear");
		if (toParam > 0) {
			return toParam;
		} else {
			if (this._configuration.defaultDateRange() > 0) {
				return LocalDate.now()
					.plusDays(this._configuration.defaultDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	public boolean getHideResultsBeforeSearch() {
		return this._configuration.hideResultsBeforeSearch();
	}

	public boolean isUserSearch() {
		return GetterUtil.getBoolean(this._request.getAttribute("userSearch"));
	}

	private void logSearch() throws PortalException {
		ServiceContext sc = ServiceContextFactory.getInstance(this._request);
		AssetEntry result1 = this.getEntries().size() > 0
			? this.getEntries().get(0) : null;
		AssetEntry result2 = this.getEntries().size() > 1
			? this.getEntries().get(1) : null;
		AssetEntry result3 = this.getEntries().size() > 2
			? this.getEntries().get(2) : null;
		long searchTime = (long) (this._hits.getSearchTime() * 1000);
		SearchLog searchLog = SearchLogLocalServiceUtil.addSearchLog(sc,
			this.getKeywords(), this.getSearchContainer().getTotal(), result1,
			result2, result3, null, searchTime);
		this.getSearchContainer().getIteratorURL().setParameter("searchLogId",
			String.valueOf(searchLog.getSearchLogId()));
		this._request.setAttribute("searchLogId", searchLog.getSearchLogId());
	}

	public Map<String, Object> getTemplateContextObjects(AssetEntry entry) {
		Map<String, Object> contextObjects = new HashMap<String, Object>();
		if (entry.getAssetRenderer() != null) {
			contextObjects.put("entry",
				entry.getAssetRenderer().getAssetObject());

			boolean isFeatured = this.isEntryFeatured(entry);
			contextObjects.put("isFeatured", isFeatured);
		}
		return contextObjects;
	}

	public boolean isEntryFeatured(AssetEntry entry) {
		String[] boostTagsNames = StringUtil
			.split(this.getConfiguration().boostTagsNames());
		return entry.getTags().stream()
			.anyMatch(t -> ArrayUtil.contains(boostTagsNames, t.getName()));
	}

	public List<Object> getTemplateEntries() {
		return new ArrayList<Object>();
	}

	public SearchAssetConfiguration getConfiguration() {
		return this._configuration;
	}

	public String getSearchForm() {
		return Validator.isNotNull(this._configuration.searchForm())
			? this._configuration.searchForm() : "museum";
	}

	private static Log _log = LogFactoryUtil.getLog("eu.strasbourg");

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
	private SearchAssetConfiguration _configuration;

	private SearchContainer<AssetEntry> _searchContainer;
	private List<AssetEntry> _entries;
	private List<AssetVocabulary> _vocabularies;
	private String _keywords;
	private List<Long[]> _filterCategoriesIds;
	private String _filterCategoriesIdString;
	private String[] _filterClassNames;
	private Map<String, String> _templatesMap;
	private List<String> _classNames;
	private Hits _hits;

}

package eu.strasbourg.portlet.search_asset.display.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
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
		if (!this._configuration.hideResultsBeforeSearch() || this.isUserSearch()) {
			this.initEntries();
		} else {
			this._entries = new ArrayList<AssetEntry>();
		}
		
	}

	private void initSearchContainer() {
		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("orderByCol", this.getOrderByCol());
		iteratorURL.setParameter("orderByType", this.getOrderByType());
		int i = 0;
		for (Long[] categoriesIds : this.getFilterCategoriesIds()) {
			iteratorURL.setParameter("vocabulary_" + i,
				ArrayUtil.toStringArray(categoriesIds));
			i++;
		}

		iteratorURL.setParameter("vocabulariesCount", String.valueOf(i));

		iteratorURL.setParameter("className_", this.getFilterClassNames());

		iteratorURL.setParameter("keywords",
			String.valueOf(this.getKeywords()));
		if (this._configuration.dateField()) {
			iteratorURL.setParameter("fromDay",
				String.valueOf(this.getFromDay()));
			iteratorURL.setParameter("fromMonth",
				String.valueOf(this.getFromMonth()));
			iteratorURL.setParameter("fromYear",
				String.valueOf(this.getFromYear()));
			iteratorURL.setParameter("toDay", String.valueOf(this.getToDay()));
			iteratorURL.setParameter("toMonth",
				String.valueOf(this.getToMonth()));
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

	private void initEntries() throws PortalException {
		HttpServletRequest servletRequest = PortalUtil
			.getHttpServletRequest(_request);

		SearchContext searchContext = SearchContextFactory
			.getInstance(servletRequest);

		String keywords = ParamUtil.getString(this._request, "keywords");
		String[] classNames = this.getFilterClassNames();
		boolean globalScope = this._configuration.globalScope();
		long groupId = this._themeDisplay.getScopeGroupId();
		long globalGroupId = this._themeDisplay.getCompanyGroupId();
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();

		String prefilterCategoriesIdsString = this._configuration
			.prefilterCategoriesIds();
		List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
		for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString
			.split(";")) {
			Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
				.toLongArray(StringUtil
					.split(prefilterCategoriesIdsGroupByVocabulary, ";", 0));
			prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
		}

		String prefilterTagsIdsString = this._configuration.prefilterTagsIds();
		Long[] prefilterTagsIds = ArrayUtil
			.toLongArray(StringUtil.split(prefilterTagsIdsString, 0));

		boolean dateField = this._configuration.dateField();
		String fromDate = String.valueOf(this.getFromYear())
			+ String.valueOf(this.getFromMonth())
			+ String.valueOf(this.getFromDay()) + "000000";
		String toDate = String.valueOf(this.getToYear())
			+ String.valueOf(this.getToMonth())
			+ String.valueOf(this.getToDay()) + "000000";

		String sortField = this.getOrderByColSearchField();
		boolean isSortDesc = "desc".equals(this.getOrderByType());

		// Recherche
		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames,
			groupId, globalGroupId, globalScope, keywords, dateField, fromDate,
			toDate, categoriesIds, prefilterCategoriesIds, prefilterTagsIds,
			this._themeDisplay.getLocale(), getSearchContainer().getStart(),
			getSearchContainer().getEnd(), sortField, isSortDesc);
		List<AssetEntry> results = new ArrayList<AssetEntry>();
		if (hits != null) {
			/*
			 * for (float s : hits.getScores()) { System.out.println(s); }
			 * System.out.println();
			 */
			for (Document document : hits.getDocs()) {
				AssetEntry entry = AssetEntryLocalServiceUtil.getEntry(
					GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (entry != null) {
					results.add(entry);
				}
			}
			long count = SearchHelper.getGlobalSearchCount(searchContext,
				classNames, groupId, globalGroupId, globalScope, keywords,
				dateField, fromDate, toDate, categoriesIds,
				prefilterCategoriesIds, prefilterTagsIds,
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
		String filterCategoriesIdsString = "";
		for (Long[] filterCategoriesForVoc : this.getFilterCategoriesIds()) {
			for (long filterCategoryId : filterCategoriesForVoc) {
				if (filterCategoriesIdsString.length() > 0) {
					filterCategoriesIdsString += ",";
				}
				filterCategoriesIdsString += filterCategoryId;
			}
		}
		return filterCategoriesIdsString;
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
			this._filterClassNames = ArrayUtil.toStringArray(this.getClassNames());
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
	 * Renvoie des données nécessaires au search container
	 */
	public String getOrderByColSearchField() {
		switch (this.getOrderByCol()) {
		case "title":
			return "localized_title_" + this._themeDisplay.getLocale()
				+ "_sortable";
		case "modified-date":
			return "modified_sortable";
		case "dates":
			return "dates_Number_sortable";
		case "score":
			return "_score";
		default:
			return "modified_sortable";
		}
	}

	/**
	 * Retourne le champ sur lequel on classe les résultats. Par défaut on
	 * classe par date de modification. Si le champ date est affiché, on classe
	 * par défaut par date
	 */
	public String getOrderByCol() {
		if (this.isDateField()) {
			return ParamUtil.getString(this._request, "orderByCol", "dates");
		} else {
			return ParamUtil.getString(this._request, "orderByCol",
				"modified-date");
		}
	}

	public String getOrderByType() {
		if (this.isDateField()) {
			return ParamUtil.getString(this._request, "orderByType", "asc");
		} else {
			return ParamUtil.getString(this._request, "orderByType", "desc");
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
		return fromParam > 0 ? fromParam
			: getTodayCalendar().get(Calendar.DAY_OF_MONTH);
	}

	public int getFromMonth() {
		int fromParam = ParamUtil.getInteger(this._request, "fromMonth");
		return fromParam > 0 ? fromParam
			: getTodayCalendar().get(Calendar.MONTH);
	}

	public int getFromYear() {
		int fromParam = ParamUtil.getInteger(this._request, "fromYear");
		return fromParam > 0 ? fromParam
			: getTodayCalendar().get(Calendar.YEAR);
	}

	public int getToDay() {
		int toParam = ParamUtil.getInteger(this._request, "toDay");
		return toParam > 0 ? toParam
			: getOneMonthLaterCalendar().get(Calendar.DAY_OF_MONTH);
	}

	public int getToMonth() {
		int toParam = ParamUtil.getInteger(this._request, "toMonth");
		return toParam > 0 ? toParam
			: getOneMonthLaterCalendar().get(Calendar.MONTH);
	}

	public int getToYear() {
		int toParam = ParamUtil.getInteger(this._request, "toYear");
		return toParam > 0 ? toParam
			: getOneMonthLaterCalendar().get(Calendar.YEAR);
	}
	
	public boolean getHideResultsBeforeSearch() {
		return this._configuration.hideResultsBeforeSearch();
	}
	
	public boolean isUserSearch() {
		return GetterUtil.getBoolean(this._request.getAttribute("userSearch"));
	}

	private Calendar getTodayCalendar() {
		return CalendarFactoryUtil.getCalendar(new Date().getTime());
	}

	private Calendar getOneMonthLaterCalendar() {
		Calendar calendar = CalendarFactoryUtil
			.getCalendar(new Date().getTime());
		calendar.add(Calendar.MONTH, 1);
		return calendar;
	}

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
	private SearchAssetConfiguration _configuration;

	private SearchContainer<AssetEntry> _searchContainer;
	private List<AssetEntry> _entries;
	private List<AssetVocabulary> _vocabularies;
	private String _keywords;
	private List<Long[]> _filterCategoriesIds;
	private String[] _filterClassNames;
	private Map<String, String> _templatesMap;
	private List<String> _classNames;

}

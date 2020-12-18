package eu.strasbourg.portlet.search_asset_v2.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationAssetData;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationAssetPrefilterData;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationData;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;
import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.service.SearchLogLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.SearchHelper;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class SearchAssetDisplayContext {

	public SearchAssetDisplayContext(RenderRequest request, RenderResponse response) throws PortalException {

		this._response = response;
		this._request = request;
		this._themeDisplay = (ThemeDisplay) _request.getAttribute(WebKeys.THEME_DISPLAY);
		this.initSearchContainer();
		if (!getConfigurationData().isHideResultsBeforeSearch() || this.isUserSearch()
				|| ParamUtil.getBoolean(this._request, "paginate")) {
			this.initEntries();
		} else {
			this._entries = new ArrayList<>();
			this._entriesCount = 0;
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

	private void initSearchContainer() throws ConfigurationException {
		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("orderByCol", this.getSortFieldsAndTypes().keySet().toArray(new String[this.getSortFieldsAndTypes().keySet().size()]));
		iteratorURL.setParameter("orderByType", this.getSortFieldsAndTypes().values().toArray(new String[this.getSortFieldsAndTypes().values().size()]));
		int i = 0;
		for (Long[] categoriesIds : this.getFilterCategoriesIds()) {
			iteratorURL.setParameter("vocabulary_" + i, ArrayUtil.toStringArray(categoriesIds));
			i++;
		}
		iteratorURL.setParameter("paginate", String.valueOf(true));
		iteratorURL.setParameter("vocabulariesCount", String.valueOf(i));

		iteratorURL.setParameter("className", this.getFilterClassNames());

		iteratorURL.setParameter("keywords", String.valueOf(this.getKeywords()));

		if (getConfigurationData().isDisplayDateField()) {
			iteratorURL.setParameter("fromDay", String.valueOf(this.getFromDay()));
			iteratorURL.setParameter("fromMonth", String.valueOf(this.getFromMonthIndex()));
			iteratorURL.setParameter("fromYear", String.valueOf(this.getFromYear()));
			iteratorURL.setParameter("toDay", String.valueOf(this.getToDay()));
			iteratorURL.setParameter("toMonth", String.valueOf(this.getToMonthIndex()));
			iteratorURL.setParameter("toYear", String.valueOf(this.getToYear()));
		}

		//OPS workaround
		if(!ParamUtil.getString(this._request, "fromMonthLoop").equals("")) {
			this._request.setAttribute("fromMonth", String.valueOf(getMonthNumber(ParamUtil.getInteger(this._request, "fromMonthLoop"))));
			this._request.setAttribute("fromYear", String.valueOf(getYearNumber(ParamUtil.getInteger(this._request, "fromMonthLoop"))));
		}

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<>(this._request, iteratorURL, null,
					"no-entries-were-found");
			this._searchContainer.getIteratorURL().setParameter("delta", String.valueOf(this.getDelta()));

			this._searchContainer.setDelta((int) this.getDelta());
		}
	}


	/**
	 * Retourne le champ sur lequel on classe les résultats
	 */
	public Map<String, String> getSortFieldsAndTypes() throws ConfigurationException {
		Map fieldsAndTypes = new HashMap<>();
		String sortFieldAndTypeFromParam = ParamUtil.getString(this._request, "sortFieldAndType");
		if (Validator.isNull(sortFieldAndTypeFromParam)) {
			if (Validator.isNull(this.getKeywords())) {
				if(getConfigurationData().getGroupBy() == -1){
					// ajout du tri par type d'asset
					String assetTypeSort = Field.ENTRY_CLASS_NAME;
					fieldsAndTypes.put(assetTypeSort, "desc");
				}
				if(!getConfigurationData().isRandomSort()) {
					String firstSortingField = Validator.isNotNull(getConfigurationData().getFirstSortingField())
							? getConfigurationData().getFirstSortingField() : "modified_sortable";
					String firstSortingType = Validator.isNotNull(getConfigurationData().getFirstSortingType())
							? getConfigurationData().getFirstSortingType() : "desc";
					fieldsAndTypes.put(firstSortingField, firstSortingType);
					String secondSortingField = Validator.isNotNull(getConfigurationData().getSecondSortingField())
							? getConfigurationData().getSecondSortingField() : "modified_sortable";
					if (!firstSortingField.equals(secondSortingField)) {
						String secondSortingType = Validator.isNotNull(getConfigurationData().getSecondSortingType())
								? getConfigurationData().getSecondSortingType() : "desc";
						fieldsAndTypes.put(secondSortingField, secondSortingType);
					}
				}else{
					fieldsAndTypes.put("tot0", "titi");
				}
			} else {
				fieldsAndTypes.put("score", "desc");
			}
		}else{
			fieldsAndTypes.put(sortFieldAndTypeFromParam.split(",")[0], sortFieldAndTypeFromParam.split(",")[1]);
		}
		return fieldsAndTypes;
	}

	/**
	 * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
	 * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
	 */
	private List<Long[]> getFilterCategoriesIds() {
		if (_filterCategoriesIds == null) {
			List<Long[]> filterCategoriesIds = new ArrayList<>();

			// Soit depuis un paramètre "categoriesIds" de l'URL
			String categoriesIdsString = ParamUtil.getString(this._request, "categoriesIds");
			if (Validator.isNotNull(categoriesIdsString)) {
				for (String prefilterCategoriesIdsAnd : categoriesIdsString.split(";")) {
					Long[] categoriesIdsOr = ArrayUtil.toLongArray(StringUtil.split(prefilterCategoriesIdsAnd, ",", 0));
					filterCategoriesIds.add(categoriesIdsOr);
				}
				return filterCategoriesIds;
			}

			// Soit depuis les paramètres passés par la validation du formulaire
			long vocabulariesCount = ParamUtil.getLong(this._request, "vocabulariesCount");
			for (long i = 0; i < vocabulariesCount; i++) {
				List<Long> categoriesIds = new ArrayList<>();
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
			this._filterCategoriesIds = filterCategoriesIds;
		}
		return this._filterCategoriesIds;
	}

	/**
	 * Renvoie la liste des types d'entités sur lesquels on souhaite rechercher
	 * les entries
	 */
	private String[] getFilterClassNames() throws ConfigurationException {
		if (_filterClassNames == null) {
			this._filterClassNames = ParamUtil.getStringValues(this._request, "className");
		}
		// Si la liste est vide, on renvoie la liste complète paramétrée via la
		// configuration (on ne recherche pas sur rien !)
		if (this._filterClassNames.length == 0) {
			this._filterClassNames = ArrayUtil.toStringArray(this.getClassNames());
		}
		return this._filterClassNames;
	}

	/**
	 * Récupère la liste des class names sur lesquels faire la recherche
	 */
	public List<String> getClassNames() throws ConfigurationException {
		_classNames = new ArrayList<>();
		for (ConfigurationAssetData assetType : getConfigurationData().getAssetTypeDataList()){
			if (Validator.isNotNull(assetType)){
				if(Validator.isNotNull(assetType.getClassName())) {
					_classNames.add(assetType.getClassName());
				}
			}
		}

		return _classNames;
	}

	/**
	 * Retourne les mots-clés de recherche
	 */
	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = HtmlUtil.escape(ParamUtil.getString(this._request, "keywords"));
		}
		return _keywords;
	}

	/**
	 * Retourne la jour du mois de la date de début de la recherche. Soit depuis
	 * les paramètres de la requête soit le réglage par défaut via la
	 * configuration (date du jour, ou si la période de recherche par défaut par
	 * défaut est négative, X jour dans le passé)
	 */
	public int getFromDay() throws ConfigurationException {
		int fromParam = ParamUtil.getInteger(this._request, "fromDay");
		if (fromParam > 0) {
			return fromParam;
		} else {
			if (getConfigurationData().getDefaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}

	}

	/**
	 * Retourne le mois de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration, dans l'interval
	 * [0;11]
	 */
	public int getFromMonthIndex() throws ConfigurationException {
		return getFromMonthValue() - 1;
	}

	/**
	 * Retourne le mois de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration, dans l'interval
	 * [1;12]
	 */
	public int getFromMonthValue() throws ConfigurationException {
		String fromMonthString;

		if(this._request.getAttribute("fromMonth") != null)
			fromMonthString = (String)this._request.getAttribute("fromMonth");
		else
			fromMonthString = ParamUtil.getString(this._request, "fromMonth");

		if (Validator.isNull(fromMonthString)) {
			if (getConfigurationData().getDefaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return Integer.parseInt(fromMonthString) + 1;
		}
	}

	/**
	 * Retourne l'année de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration
	 */
	public int getFromYear() throws ConfigurationException {
		int fromParam;

		if(this._request.getAttribute("fromYear") != null)
			fromParam = Integer.parseInt((String)this._request.getAttribute("fromYear"));
		else
			fromParam = ParamUtil.getInteger(this._request, "fromYear");

		if (fromParam > 0) {
			return fromParam;
		} else {
			if (getConfigurationData().getDefaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	/**
	 * Retourne la jour du mois de la date de fin de la recherche. Soit depuis
	 * les paramètres de la requête soit le réglage par défaut via la
	 * configuration (date du jour + config, ou si la période de recherche par
	 * défaut par défaut est négative, date du jour)
	 */
	public int getToDay() throws ConfigurationException {
		int toParam = ParamUtil.getInteger(this._request, "toDay");
		if (toParam > 0) {
			return toParam;
		} else {
			if (getConfigurationData().getDefaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}
	}

	/**
	 * Retourne le mois de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration, dans l'interval [0;11]
	 */
	public int getToMonthIndex() throws ConfigurationException {
		return getToMonthValue() - 1;
	}

	/**
	 * Retourne le mois de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration, dans l'interval [1;12]
	 */
	public int getToMonthValue() throws ConfigurationException {
		String toMonthString = ParamUtil.getString(this._request, "toMonth");
		if (Validator.isNull(toMonthString)) {
			if (getConfigurationData().getDefaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return ParamUtil.getInteger(this._request, "toMonth") + 1;
		}
	}

	/**
	 * Retourne l'année de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration
	 */
	public int getToYear() throws ConfigurationException {
		int toParam = ParamUtil.getInteger(this._request, "toYear");
		if (toParam > 0) {
			return toParam;
		} else {
			if (getConfigurationData().getDefaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(getConfigurationData().getDefaultFilterDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	/**
	 * Retourne le mois (0 à 11) à partir de l'index de la boucle du filtre des mois (OPS)
	 * */
	public int getMonthNumber(int iterationLoop) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, iterationLoop);
		return c.get(Calendar.MONTH);
	}

	/**
	 * Retourne l'année à partir de l'index de la boucle du filtre des mois (OPS)
	 * */
	public int getYearNumber(int iterationLoop) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, iterationLoop);
		return c.get(Calendar.YEAR);
	}

	/**
	 * Retourne le nombre d'items par page à afficher
	 */
	public long getDelta() throws ConfigurationException {
		long deltaFromParam = ParamUtil.getLong(this._request, "delta");
		if (deltaFromParam > 0) {
			return deltaFromParam;
		}
		if (getConfigurationData().getDelta() > 0) {
			return getConfigurationData().getDelta();
		}
		return 12;
	}

	public ConfigurationData getConfigurationData() throws ConfigurationException {
		if (this._configurationData == null) {
			this._configurationData = new ConfigurationData(getConfiguration());
		}
		return this._configurationData;
	}

	public SearchAssetConfiguration getConfiguration() throws ConfigurationException {
		if (this._configuration == null) {
			this._configuration = this._themeDisplay.getPortletDisplay().getPortletInstanceConfiguration(
					SearchAssetConfiguration.class);
		}
		return this._configuration;
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

		// Mots clés
		String keywords = HtmlUtil.escape(ParamUtil.getString(this._request, "keywords"));

		// Filtre sur les dates
		LocalDate fromDate = LocalDate.of(this.getFromYear(), this.getFromMonthValue(), this.getFromDay());
		LocalDate toDate = LocalDate.of(this.getToYear(), this.getToMonthValue(), this.getToDay());

		// Catégories sélectionnées par l'utilisateur
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();

		// Permet de remonter la hiérarchie des Request
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);

		// Lieu (pour la recherche agenda)
		String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");


		// Recherche
		this._hits = SearchHelper.getGlobalSearchHitsV2(searchContext, getConfigurationData().getAssetTypesJSON().getJSONArray(ConfigurationConstants.JSON_ASSETS_TYPES),
				getConfigurationData().isDisplayDateField(), getConfigurationData().getFilterField(),
				this.getSortFieldsAndTypes(), keywords, fromDate,
				toDate, categoriesIds, idSIGPlace, this._themeDisplay.getLocale(), getSearchContainer().getStart(), getSearchContainer().getEnd());

		List<AssetEntry> results = new ArrayList<>();
		if (this._hits != null) {
			int i = 0;
			for (float s : this._hits.getScores()) {
				_log.info(GetterUtil.getString(this._hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
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
				} else {
					// recherche de démarches (procédures)
					// les demarches n'ayant pas d'asset entry, il faut le créer pour pouvoir l'envoyer à searchContainer
					if (Validator.isNotNull(document.getField("type")) && document.getField("type").getValue().equals("procedure")) {
						AssetEntry procedureEntry = new AssetEntryImpl();
						procedureEntry.setTitle(document.getField("title").getValue());
						procedureEntry.setUrl(document.getField("url").getValue());
						procedureEntry.setDescription(document.getField("description").getValue());
						procedureEntry.setClassName("Procedure");
						results.add(procedureEntry);
					}
				}
			}
			this.getSearchContainer().setTotal(this._hits.getLength());
		}

		this._entries = results;
		this._entriesCount = this._entries.size();
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
		SearchLog searchLog = SearchLogLocalServiceUtil.addSearchLog(sc, this.getKeywords(),
				this.getSearchContainer().getTotal(), result1, result2, result3, null, searchTime);
		this.getSearchContainer().getIteratorURL().setParameter("searchLogId",
				String.valueOf(searchLog.getSearchLogId()));
		this._request.setAttribute("searchLogId", searchLog.getSearchLogId());
	}

	/**
	 * Retourne les résultats de la recherche
	 */
	public List<AssetEntry> getEntries() {
		return this._entries;
	}

	/* ******************* */
	/* utiles pour les jsp */
	/* ******************* */
	public String getSearchForm() throws ConfigurationException {
		return Validator.isNotNull(getConfigurationData().getSearchForm()) ? getConfigurationData().getSearchForm() : "museum";
	}

	/**
	 * Retourne le titre du portlet configuré dans la configuration Look And
	 * Feel s'il existe et si "utiliser le titre personnalisé" est coché, sinon
	 * à partir de la clé de traduction passée en paramètre
	 */
	public String getPortletTitle(String key) {
		String titleFromLanguageKey = LanguageUtil.get(PortalUtil.getHttpServletRequest(this._request), key);
		String useCustomPortletPreference = this._request.getPreferences().getValue("portletSetupUseCustomTitle",
				"false");
		boolean useCustomPortlet = GetterUtil.get(useCustomPortletPreference, false);
		if (useCustomPortlet) {
			String preferenceKey = "portletSetupTitle_" + this._themeDisplay.getLocale().toString();
			return this._request.getPreferences().getValue(preferenceKey, titleFromLanguageKey);
		} else {
			return titleFromLanguageKey;
		}
	}

	/**
	 * Retourne true si les champs dates doivent être affichés
	 */
	public boolean isDateField() throws ConfigurationException {
		return getConfigurationData().isDisplayDateField();
	}

	/**
	 * On récupère les préférences de catégories et on les envoie à la JSP C'est
	 * une liste d'AssetVocabulary, qui correspond aux vocabulaires pour
	 * lesquels on souhaite afficher une liste déroulante dans le moteur de
	 * recherche
	 */
	public List<AssetVocabulary> getVocabularies() throws PortalException {
		if (this._vocabularies == null) {
			this._vocabularies = new ArrayList<>();

			HashMap<String, String> vocabularyIdsMap = getConfigurationData().getVocabulariesControlTypesMap();
			if (Validator.isNotNull(vocabularyIdsMap)) {
				vocabularyIdsMap.forEach((key, value) -> {
					long vocabularyId = Long.parseLong(String.valueOf(key));
					if (Validator.isNotNull(vocabularyId)) {
						AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(vocabularyId);
						if (vocabulary != null) {
							this._vocabularies.add(vocabulary);
						}
					}
				});
			}
		}
		return this._vocabularies;
	}

	/**
	 * Retourne la liste des catégories du vocabulaire passé en paramètre, sans
	 * les catégories enfants triées par la valeur de la propriété "order" de
	 * chaque catégorie
	 */
	public List<AssetCategory> getSortedCategories(AssetVocabulary vocabulary) throws ConfigurationException {
		List<AssetCategory> categories = this.getDropdownRootCategories(vocabulary);

		// trie des catégories par la propriété order si elle existe
		Map<String, AssetCategory> order_category = new HashMap<>();
		List<AssetCategory> categoriesWithoutOrder = new ArrayList<>();
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

		List<AssetCategory> sortedCategories = new ArrayList<>();
		sortedCategories.addAll(order_category.values());
		sortedCategories.addAll(categoriesWithoutOrder);

		return sortedCategories;
	}

	/**
	 * Renvoie la liste des catégories d'un vocabulaire à afficher en front. Si
	 * un(des) préfiltre(s) est sélectionné pour ce vocabulaire, on renvoie
	 * ce(s) préfiltre(s). Sinon on ne renvoie que les catégories racines, la
	 * JSP se chargeant d'afficher l'arbre des enfants
	 */
	public List<AssetCategory> getDropdownRootCategories(AssetVocabulary vocabulary) throws ConfigurationException {
		// Toutes les catégories du vocabulaire
		List<AssetCategory> allCategories = vocabulary.getCategories();

		List<Long> categoryIds = this.getCategoryPrefilters();
		// Si ce préfiltre a du contenu
		if (categoryIds.size() > 0) {
			// on fait l'interersection avec la liste de toutes les catégories du vocabulaire
			List<AssetCategory> prefilteredCategoriesForVocabulary = allCategories.stream()
					.filter(c -> categoryIds.contains(c.getCategoryId()))
					.collect(Collectors.toList());

			// Si cette intersection a du contenu on la renvoie
			if (prefilteredCategoriesForVocabulary.size() > 0) {
				return prefilteredCategoriesForVocabulary;
			}
		}

		// Sinon on renvoie les catégories racines du vocabulaire
		return allCategories.stream().filter(AssetCategory::isRootCategory).collect(Collectors.toList());
	}

	public List<Long> getCategoryPrefilters() throws ConfigurationException {
		List<Long> categoryPrefilters = new ArrayList<>();
		for (ConfigurationAssetData assetType : getConfigurationData().getAssetTypeDataList()){
			if (Validator.isNotNull(assetType)){
				if(Validator.isNotNull(assetType.getAssetPrefilterDataList())) {
					for (ConfigurationAssetPrefilterData prefilter : assetType.getAssetPrefilterDataList()){
						if (Validator.isNotNull(prefilter)){
							if(prefilter.getType().equals("categories")) {
								categoryPrefilters.addAll(prefilter.getCategoryOrTagIdList());
							}
						}
					}
				}
			}
		}

		return categoryPrefilters;
	}

	public String getFilterCategoriesIdsString() {
		if (Validator.isNull(this._filterCategoriesIdString)) {
			StringBuilder filterCategoriesIdsString = new StringBuilder();
			for (Long[] filterCategoriesForVoc : this.getFilterCategoriesIds()) {
				for (long filterCategoryId : filterCategoriesForVoc) {
					if (filterCategoriesIdsString.length() > 0) {
						filterCategoriesIdsString.append(",");
					}
					filterCategoriesIdsString.append(filterCategoryId);
				}
			}
			this._filterCategoriesIdString = filterCategoriesIdsString.toString();
		}
		return this._filterCategoriesIdString;
	}

	/**
	 * Retourne l'URL sur laquelle aller pour avoir X items par page
	 */
	public String getURLForDelta(long delta) throws ConfigurationException {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("delta", String.valueOf(delta));
		String valueToReturn = url.toString();
		url.setParameter("delta", String.valueOf(getDelta()));
		return valueToReturn;
	}

	public Map<String, Object> getTemplateContextObjects(AssetEntry entry) {
		Map<String, Object> contextObjects = new HashMap<>();
		if (entry.getAssetRenderer() != null) {
			contextObjects.put("entry", entry.getAssetRenderer().getAssetObject());

			boolean isFeatured = this.isEntryFeatured(entry);
			contextObjects.put("isFeatured", isFeatured);
		}
		return contextObjects;
	}

	public boolean isEntryFeatured(AssetEntry entry) {
		String[] boostTagsNames = new String[0];
		try {
			boostTagsNames = StringUtil.split(this.getConfiguration().boostTagsNames());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		String[] finalBoostTagsNames = boostTagsNames;
		return entry.getTags().stream().anyMatch(t -> ArrayUtil.contains(finalBoostTagsNames, t.getName()));
	}

	/**
	 * Retourne une map avec comme clés les classNames des entités retournées et
	 * comme valeurs les ids des ADT
	 */
	public Map<String, String> getTemplatesMap() throws NumberFormatException, PortalException {
		if (this._templatesMap == null) {
			Map<String, String> templatesMap = new HashMap<>();
			List<ConfigurationAssetData> assetTypeList = getConfigurationData().getAssetTypeDataList();
			for (ConfigurationAssetData assetType : assetTypeList) {
				if (assetType.getClassName().equals("searchJournalArticle")) {
					templatesMap.put("com.liferay.journal.model.JournalArticle",
							"ddmTemplate_" + assetType.getTemplateKey());
				} else if (assetType.getClassName().equals("searchDocument")) {
					templatesMap.put(DLFileEntry.class.getName(),
							"ddmTemplate_" + assetType.getTemplateKey());
				}else if (!assetType.getClassName().equals("searchDemarche")){
					templatesMap.put(assetType.getClassName(), "ddmTemplate_" + assetType.getTemplateKey());
				}
			}
			this._templatesMap = templatesMap;
		}
		return this._templatesMap;
	}

	public List<Object> getTemplateEntries() {
		return new ArrayList<>();
	}

	/**
	 * Retourne true si l'export est activé
	 */
	public boolean getDisplayExport() throws ConfigurationException {
		return getConfigurationData().isDisplayExport();
	}

	public ResourceURL getExportResourceURL() throws PortalException {
		ResourceURL exportURL = this._response.createResourceURL();

		HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(_request);

		SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

		// Mots clés
		String keywords = HtmlUtil.escape(ParamUtil.getString(this._request, "keywords"));

		// Filtre
		LocalDate fromDate = LocalDate.of(this.getFromYear(), this.getFromMonthValue(), this.getFromDay());
		LocalDate toDate = LocalDate.of(this.getToYear(), this.getToMonthValue(), this.getToDay());

		// Catégories sélectionnées par l'utilisateur
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();

		// Recherche
		Hits hits = SearchHelper.getGlobalSearchHitsV2(searchContext, getConfigurationData().getAssetTypesJSON().getJSONArray(ConfigurationConstants.JSON_ASSETS_TYPES),
				getConfigurationData().isDisplayDateField(), getConfigurationData().getFilterField(),
				getSortFieldsAndTypes(), keywords, fromDate, toDate, categoriesIds,
				null, this._themeDisplay.getLocale(), -1, -1);

		StringBuilder ids = new StringBuilder();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
						GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (entry != null) {
					if (ids.length() >= 0) {
						ids.append(",");
					}
					ids.append(entry.getClassPK());
				}
			}
		}

		exportURL.setParameter("ids", ids.toString());
		exportURL.setParameter("classNames", this.getFilterClassNamesString());
		return exportURL;
	}

	/**
	 * Retourne la liste des types d'entités sur lesquels on souhaite rechercher
	 * les entries, sous forme de String
	 */
	public String getFilterClassNamesString() throws ConfigurationException {
		return StringUtil.merge(getFilterClassNames());
	}

	public Pager getPager() throws ConfigurationException {
		return new Pager(this.getSearchContainer().getTotal(), (int) getDelta(), this.getSearchContainer().getCur());
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

	public String getIdSIGPlace() {
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(_request));
		String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");

		return idSIGPlace;
	}

	private static Log _log = LogFactoryUtil.getLog(SearchAssetDisplayContext.class);

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
	private SearchAssetConfiguration _configuration;
	private ConfigurationData _configurationData;


	private List<String> _classNames;

	private SearchContainer<AssetEntry> _searchContainer;
	private List<AssetEntry> _entries;
	private List<AssetVocabulary> _vocabularies;
	private String _keywords;
	private List<Long[]> _filterCategoriesIds;
	private String _filterCategoriesIdString;
	private String[] _filterClassNames;
	private Map<String, String> _templatesMap;
	private Hits _hits;
	private boolean _displayExport;
	private int _entriesCount;

}

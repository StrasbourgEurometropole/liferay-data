package eu.strasbourg.portlet.search_asset_v2.configuration.bean;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfigurationAction;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;
import eu.strasbourg.utils.JSONHelper;
import org.apache.commons.lang3.ArrayUtils;

import javax.portlet.ActionRequest;
import java.util.*;

public class ConfigurationData {

    /** Contextes de récupération des données */
    private ActionRequest request;
    private SearchAssetConfiguration configuration;

    /** Données */
    private List<ConfigurationAssetData> assetTypeDataList;
    private LinkedHashMap<String, String> vocabulariesControlTypes;
    private boolean displayDateField;
    private boolean displaySorting;
    private String boostTagsNames;
    private String filterField;
    private long defaultFilterDateRange;
    private boolean randomSort;
    private String firstSortingField;
    private String firstSortingType;
    private String secondSortingField;
    private String secondSortingType;
    private long groupBy;
    private boolean hideResultsBeforeSearch;
    private long delta;
    private String searchForm;
    private boolean displayExport;
    private String exportType;

    /** Logs */
    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    public ConfigurationData(ActionRequest request) {
        this.request = request;
        this.initDataFromRequest();
    }

    public ConfigurationData(SearchAssetConfiguration configuration) {
        this.configuration = configuration;
        this.initDataFromConfiguration();
    }

    private void initDataFromRequest() {
        // Asset types
        String assetTypeIndexes = ParamUtil.getString(request, ConfigurationConstants.PARAM_ASSET_TYPES_INDEXES);
        if (Validator.isNotNull(assetTypeIndexes)) {
            this.assetTypeDataList = new ArrayList<>();
            for (String assetTypeIndex : assetTypeIndexes.split(",")) {
                List<ConfigurationAssetPrefilterData> assetPrefilterDataList = new ArrayList<>();
                if (Validator.isNotNull(assetTypeIndex)) {
                    String className = ParamUtil.getString(this.request,
                            ConfigurationConstants.PARAM_CLASSNAME + "_" + assetTypeIndex);
                    Long[] scopeGroupIDs = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                            ConfigurationConstants.PARAM_SCOPE_IDS + "_" + assetTypeIndex));
                    long structureID = ParamUtil.getLong(this.request,
                            ConfigurationConstants.PARAM_STRUCTURE_ID + "_" + assetTypeIndex);
                    String templateKey = ParamUtil.getString(this.request,
                            ConfigurationConstants.PARAM_TEMPLATE_KEY + "_" + assetTypeIndex);
                    String friendlyURL = ParamUtil.getString(this.request,
                            ConfigurationConstants.PARAM_FRIENDLY_URL + "_" + assetTypeIndex);
                    // Prefilters
                    String prefilterIndexes = ParamUtil.getString(request, ConfigurationConstants.PARAM_PREFILTERS_INDEXES + assetTypeIndex);
                    if (Validator.isNotNull(prefilterIndexes)) {
                        for (String prefilterIndex : prefilterIndexes.split(",")) {
                            if (Validator.isNotNull(prefilterIndex)) {
                                boolean contains = ParamUtil.getBoolean(this.request,
                                        ConfigurationConstants.PARAM_INCLUDE_EXCLUDE + "_" + prefilterIndex);
                                String operator =  ParamUtil.getString(this.request,
                                        ConfigurationConstants.PARAM_ALL_ANY + "_" + prefilterIndex);
                                String categoryOrTagIdList = ParamUtil.getString(this.request,
                                        ConfigurationConstants.PARAM_CATEGORIES_OR_TAGS + "_" + prefilterIndex);
                                Long[] prefilterIDs = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                                        ConfigurationConstants.PARAM_PREFILTER_SELECTION + "_" + prefilterIndex));
                                assetPrefilterDataList.add(
                                        new ConfigurationAssetPrefilterData(
                                                contains, operator, categoryOrTagIdList, Arrays.asList(prefilterIDs)
                                        )
                                );
                            }
                        }
                    }

                    this.assetTypeDataList.add(
                            new ConfigurationAssetData(
                                    className, Arrays.asList(scopeGroupIDs), structureID, templateKey, friendlyURL,
                                    assetPrefilterDataList
                            )
                    );
                }
            }
        }

        // Critères de recherche
        String vocabularyIndexes = ParamUtil.getString(request, ConfigurationConstants.PARAM_CRITERE_RECHERCHE_INDEXES);
        if (Validator.isNotNull(vocabularyIndexes)) {
            this.vocabulariesControlTypes = new LinkedHashMap<>();
            for (String vocabularyIndexe : vocabularyIndexes.split(",")) {
                if (Validator.isNotNull(vocabularyIndexe)) {
                    String vocabularyIdString = ParamUtil.getString(this.request,
                            ConfigurationConstants.PARAM_VOCABULARIES_IDS+ "_" + vocabularyIndexe);
                    boolean vocabularySelected = Validator.isNotNull(
                            vocabularyIdString) && !vocabularyIdString.equals("false");
                    if (vocabularySelected) {
                        // Mode d'affichage du vocabulaire
                        String vocabularyControlType = ParamUtil
                                .getString(this.request, ConfigurationConstants.PARAM_VOCABULARIES_CONTROL_TYPE_IDS+ "_" + vocabularyIndexe);
                        vocabulariesControlTypes.put(vocabularyIdString, vocabularyControlType);
                    }
                }
            }
        }
        this.displayDateField = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_DATE_FIELD);
        this.displaySorting = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_SORTING);

        // Boost
        this.boostTagsNames = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_BOOST_TAGS_NAMES);

        // Filtres
        this.filterField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FILTER_FIELD);
        this.defaultFilterDateRange = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DEFAULT_FILTER_DATE_RANGE);

        // Tris
        this.randomSort = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_RANDOM_SORT);
        this.firstSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_FIELD);
        this.firstSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_TYPE);
        this.secondSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_FIELD);
        this.secondSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_TYPE);

        // Groupement
        this.groupBy = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_GROUP_BY);

        // Affichage
        this.hideResultsBeforeSearch = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_HIDE_RESULTS_BEFORE_SEARCH);
        this.delta = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DELTA);
        this.searchForm = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SEARCH_FORM, "museum");

        // Export
        this.displayExport = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_EXPORT);
        this.exportType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_EXPORT_TYPE, "");

    }

    private void initDataFromConfiguration() {
        // Asset types
        this.assetTypeDataList = new ArrayList<>();
        List<ConfigurationAssetPrefilterData> assetPrefilterDataList;
        try {
            JSONObject json = JSONFactoryUtil.createJSONObject(this.configuration.assetTypes());
            JSONArray jsonAssetsTypes = json.getJSONArray(ConfigurationConstants.JSON_ASSETS_TYPES);
            if(Validator.isNotNull(jsonAssetsTypes)) {
                for (int i = 0; i < jsonAssetsTypes.length(); i++) {
                    JSONObject jsonAssetType = jsonAssetsTypes.getJSONObject(i);

                    String className = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_CLASSNAME);
                    Long[] scopeGroupIDs = ArrayUtils.toObject(JSONHelper.convertJSONArraytoLongArray(
                            jsonAssetType.getJSONArray(ConfigurationConstants.JSON_ASSET_SCOPE_IDS)));
                    long structureID = jsonAssetType.getLong(ConfigurationConstants.JSON_ASSET_STRUCTURE_ID);
                    String templateKey = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_TEMPLATE_KEY);
                    String friendlyURL = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_FRIENDLY_URL);

                    assetPrefilterDataList = new ArrayList<>();
                    JSONArray jsonPrefilters = jsonAssetType.getJSONArray(ConfigurationConstants.JSON_ASSET_PREFILTERS);
                    if(Validator.isNotNull(jsonPrefilters)) {
                        for (int j = 0; j < jsonPrefilters.length(); j++) {
                            JSONObject jsonPrefilter = jsonPrefilters.getJSONObject(j);

                            boolean contains = jsonPrefilter.getBoolean(ConfigurationConstants.JSON_ASSET_PREFILTER_INCLUDE_EXCLUDE);
                            String operator =  jsonPrefilter.getString(ConfigurationConstants.JSON_ASSET_PREFILTER_ALL_ANY);
                            String categoryOrTagIdList = jsonPrefilter.getString(ConfigurationConstants.JSON_ASSET_PREFILTER_CATEGORIES_OR_TAGS);
                            Long[] prefilterIDs = ArrayUtils.toObject(JSONHelper.convertJSONArraytoLongArray(
                                    jsonPrefilter.getJSONArray( ConfigurationConstants.JSON_ASSET_PREFILTER_SELECTION)));
                            assetPrefilterDataList.add(
                                    new ConfigurationAssetPrefilterData(
                                            contains, operator, categoryOrTagIdList, Arrays.asList(prefilterIDs)
                                    )
                            );
                        }
                    }

                    this.assetTypeDataList.add(
                            new ConfigurationAssetData(
                                    className, Arrays.asList(scopeGroupIDs), structureID, templateKey, friendlyURL,
                                    assetPrefilterDataList
                            )
                    );

                }
            }

        } catch (NullPointerException | JSONException e) {
            this.log.error(e);
        }

        // Critères de recherche
        this.vocabulariesControlTypes = new LinkedHashMap<>();
        try {
            JSONObject json = JSONFactoryUtil.createJSONObject(this.configuration.vocabulariesControlTypes());
            JSONArray jsonVocabulariesControlTypes = json.getJSONArray(ConfigurationConstants.JSON_VOCABULARIES_CONTROL_TYPES);
            if (Validator.isNotNull(jsonVocabulariesControlTypes)) {
                for (int i = 0; i < jsonVocabulariesControlTypes.length(); i++) {
                    JSONObject jsonVocabularyControlType = jsonVocabulariesControlTypes.getJSONObject(i);

                    String vocabularyId = jsonVocabularyControlType.getString(ConfigurationConstants.JSON_VOCABULARY_ID);
                    String vocabularyControlType = jsonVocabularyControlType.getString(ConfigurationConstants.JSON_VOCABULARY_CONTROL_TYPE);

                    this.vocabulariesControlTypes.put(vocabularyId, vocabularyControlType);
                }
            }
        }
        catch (JSONException e) {
            this.log.error(e);
        }
        this.displayDateField = this.configuration.displayDateField();
        this.displaySorting = this.configuration.displaySorting();

        // Boost
        this.boostTagsNames = this.configuration.boostTagsNames();

        // Filtres
        this.filterField = this.configuration.filterField();
        this.defaultFilterDateRange = this.configuration.defaultFilterDateRange();

        // Tris
        this.randomSort = this.configuration.randomSort();
        this.firstSortingField = this.configuration.firstSortingField();
        this.firstSortingType = this.configuration.firstSortingType();
        this.secondSortingField = this.configuration.secondSortingField();
        this.secondSortingType = this.configuration.secondSortingType();

        // Groupement
        this.groupBy = this.configuration.groupBy();

        // Affichage
        this.hideResultsBeforeSearch = this.configuration.hideResultsBeforeSearch();
        this.delta = this.configuration.delta();
        this.searchForm = this.configuration.searchForm();

        // Export
        this.displayExport = this.configuration.displayExport();
        this.exportType = this.configuration.exportType();
    }

    public void saveConfiguration(SearchAssetConfigurationAction configAction) {
        if(validate()) {
            // Sauvegarde dans la configuration

            // -- TYPE DE CONTENUS --
            String assetTypesJSON = this.getAssetTypesJSON().toJSONString();
            configAction.setPreference(this.request, ConfigurationConstants.JSON_ASSETS_TYPES,
                    assetTypesJSON);

            // -- CRITERES DE RECHERCHE --
            String vocabulariesControlTypesJSON = this.getVocabulariesControlTypesJSON().toJSONString();
            configAction.setPreference(this.request, ConfigurationConstants.JSON_VOCABULARIES_CONTROL_TYPES,
                    vocabulariesControlTypesJSON);
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_DISPLAY_DATE_FIELD,
                    String.valueOf(displayDateField));
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_DISPLAY_SORTING,
                    String.valueOf(displaySorting));

            // -- MISE EN AVANT --
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_BOOST_TAGS_NAMES, boostTagsNames);

            // -- FILTRES --
            // Champ sur lequel le tri est effectué
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_FILTER_FIELD, filterField);
            // Filtre par date par défaut
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_DEFAULT_FILTER_DATE_RANGE,
                    String.valueOf(defaultFilterDateRange));

            // -- TRIS --
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_RANDOM_SORT, String.valueOf(randomSort));
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_FIELD, firstSortingField);
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_TYPE, firstSortingType);
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_FIELD, secondSortingField);
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_TYPE, secondSortingType);

            // -- GROUPEMENT --
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_GROUP_BY, String.valueOf(groupBy));

            // -- AFFICHAGE --
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_HIDE_RESULTS_BEFORE_SEARCH,
                    String.valueOf(hideResultsBeforeSearch));
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_DELTA, String.valueOf(delta));
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_SEARCH_FORM, searchForm);

            // -- EXPORT --
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_DISPLAY_EXPORT,
                    String.valueOf(displayExport));
            configAction.setPreference(this.request, ConfigurationConstants.PARAM_EXPORT_TYPE, exportType);
        }
    }

    public boolean validate() {
        boolean result = true;
        // TODO Validation des données issues de la requête
        return result;
    }

    public int countAssetTypes() {
        return this.assetTypeDataList.size();
    }

    public List<ConfigurationAssetData> getAssetTypeDataList() {
        return this.assetTypeDataList;
    }

    public JSONObject getAssetTypesJSON() {

        JSONArray assetTypeArray = JSONFactoryUtil.createJSONArray();
        for (ConfigurationAssetData assetType : this.assetTypeDataList) {
            assetTypeArray.put(assetType.toJSON());
        }
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(ConfigurationConstants.JSON_ASSETS_TYPES, assetTypeArray);
        return result;
    }

    public HashMap<String, String> getVocabulariesControlTypesMap() {
        return this.vocabulariesControlTypes;
    }

    public JSONObject getVocabulariesControlTypesJSON() {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        JSONArray vocabularyControlTypes = JSONFactoryUtil.createJSONArray();
        if(this.vocabulariesControlTypes != null) {
            for (Map.Entry<String, String> entry : this.vocabulariesControlTypes.entrySet()) {
                JSONObject vocabularyControlType = JSONFactoryUtil.createJSONObject();
                vocabularyControlType.put(ConfigurationConstants.JSON_VOCABULARY_CONTROL_TYPE, entry.getValue());
                vocabularyControlType.put(ConfigurationConstants.JSON_VOCABULARY_ID, entry.getKey());
                vocabularyControlTypes.put(vocabularyControlType);
            }
        }
        result.put(ConfigurationConstants.JSON_VOCABULARIES_CONTROL_TYPES, vocabularyControlTypes);
        return result;
    }

    public boolean isDisplayDateField() {
        return displayDateField;
    }

    public boolean isDisplaySorting() {
        return displaySorting;
    }

    public String getBoostTagsNames() {
        return boostTagsNames;
    }

    public String getFilterField() {
        return filterField;
    }

    public long getDefaultFilterDateRange() {
        return defaultFilterDateRange;
    }

    public boolean isRandomSort() {
        return randomSort;
    }

    public String getFirstSortingField() {
        return firstSortingField;
    }

    public String getFirstSortingType() {
        return firstSortingType;
    }

    public String getSecondSortingField() {
        return secondSortingField;
    }

    public String getSecondSortingType() {
        return secondSortingType;
    }

    public long getGroupBy() {
        return groupBy;
    }

    public boolean isHideResultsBeforeSearch() {
        return hideResultsBeforeSearch;
    }

    public long getDelta() {
        return delta;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public boolean isDisplayExport() {
        return displayExport;
    }

    public String getExportType() {
        return exportType;
    }
}

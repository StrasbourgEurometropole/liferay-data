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
    private HashMap<String, String> vocabulariesControlTypes;
    private boolean displayDateField;
    private boolean displaySorting;
    private String boostTagsNames;
    private String filterField;
    private long defaultFilterDateRange;
    private String firstSortingField;
    private String firstSortingType;
    private String secondSortingField;
    private String secondSortingType;
    private String groupBy;
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
        // Asset types autofield
        String assetTypesIndexes = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_ASSET_TYPES_INDEXES);
        this.assetTypeDataList = new ArrayList<>();
        for (String assetTypeIndex : assetTypesIndexes.split(",")) {
            String className = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_CLASSNAME + "_" + assetTypeIndex);
            String templateKey = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_TEMPLATE_KEY + "_" + assetTypeIndex);
            String friendlyURL = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_FRIENDLY_URL + "_" + assetTypeIndex);
            Long[] scopeGroupIDs = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                    ConfigurationConstants.PARAM_SCOPE_GROUP_IDS + "_" + assetTypeIndex));

            // Asset prefilter repeatable field
            String assetPrefiltersIndexes = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_ASSET_PREFILTERS_INDEXES + "_" + assetTypeIndex);
            List<ConfigurationAssetPrefilterData> assetPrefilterDataList = new ArrayList<>();

            for (String assetPrefiltersIndex : assetPrefiltersIndexes.split(",")) {
                boolean contains = ParamUtil.getBoolean(this.request,
                        ConfigurationConstants.PARAM_CONTAINS + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
                String operator =  ParamUtil.getString(this.request,
                        ConfigurationConstants.PARAM_OPERATOR + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
                String type = ParamUtil.getString(this.request,
                        ConfigurationConstants.PARAM_TYPE + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
                Long[] categoryOrTagIdList;
                if (type.equals(ConfigurationConstants.TYPE_TAG))
                    categoryOrTagIdList = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                            ConfigurationConstants.PARAM_TAGS_IDS + "_" + assetTypeIndex + "_" + assetPrefiltersIndex));
                else
                    categoryOrTagIdList = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                            ConfigurationConstants.PARAM_CATEGORIES_IDS + "_" + assetTypeIndex + "_" + assetPrefiltersIndex));

                assetPrefilterDataList.add(
                        new ConfigurationAssetPrefilterData(
                            contains, operator, type, Arrays.asList(categoryOrTagIdList)
                        )
                );
            }

            this.assetTypeDataList.add(
                    new ConfigurationAssetData(
                            className, templateKey, friendlyURL, Arrays.asList(scopeGroupIDs), assetPrefilterDataList
                    )
            );
        }
        // Vocabularies
        this.vocabulariesControlTypes = new HashMap<>();
        long vocabulariesCount = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_VOCABULARIES_COUNT);
        for (long i = 0; i < vocabulariesCount; i++) {
            String vocabularyIdString = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_VOCABULARIES_IDS+ "_" + i);
            boolean vocabularySelected = Validator.isNotNull(
                    vocabularyIdString) && !vocabularyIdString.equals("false");
            if (vocabularySelected) {
                // Mode d'affichage du vocabulaire
                String vocabularyControlType = ParamUtil
                        .getString(this.request, ConfigurationConstants.PARAM_VOCABULARIES_CONTROL_TYPE_IDS+ "_" + i);
                vocabulariesControlTypes.put(vocabularyIdString, vocabularyControlType);
            }
        }
        this.displayDateField = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_DATE_FIELD);
        this.displaySorting = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_SORTING);
        this.boostTagsNames = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_BOOST_TAGS_NAMES);
        this.filterField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FILTER_FIELD);
        this.defaultFilterDateRange = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DEFAULT_FILTER_DATE_RANGE);
        this.firstSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_FIELD);
        this.firstSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_TYPE);
        this.secondSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_FIELD);
        this.secondSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_TYPE);
        this.groupBy = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_GROUP_BY);
        this.hideResultsBeforeSearch = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_HIDE_RESULTS_BEFORE_SEARCH);
        this.delta = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DELTA);
        this.searchForm = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SEARCH_FORM, "museum");
        this.displayExport = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_EXPORT);
        this.exportType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_EXPORT_TYPE, "");

    }

    private void initDataFromConfiguration() {
        this.assetTypeDataList = new ArrayList<>();
        List<ConfigurationAssetPrefilterData> assetPrefilterDataList;
        try {
            JSONObject json = JSONFactoryUtil.createJSONObject(this.configuration.assetTypes());
            JSONArray jsonAssetsTypes = json.getJSONArray(ConfigurationConstants.JSON_ASSETS_TYPES);
            JSONObject jsonAssetType;
            if(Validator.isNotNull(jsonAssetsTypes)) {
                for (int i = 0; i < jsonAssetsTypes.length(); i++) {
                    jsonAssetType = jsonAssetsTypes.getJSONObject(i);

                    String className = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_CLASSNAME);
                    String templateKey = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_TEMPLATE_KEY);
                    String friendlyURL = jsonAssetType.getString(ConfigurationConstants.JSON_ASSET_FRIENDLY_URL);
                    Long[] scopeGroupIDs = ArrayUtils.toObject(JSONHelper.convertJSONArraytoLongArray(
                            jsonAssetType.getJSONArray(ConfigurationConstants.JSON_ASSET_SCOPE_GROUP_IDS)));

                    assetPrefilterDataList = new ArrayList<>();
                    // TODO remplir les prefiltres

                    this.assetTypeDataList.add(
                            new ConfigurationAssetData(
                                    className, templateKey, friendlyURL, Arrays.asList(scopeGroupIDs), assetPrefilterDataList
                            )
                    );

                }
            }

        } catch (NullPointerException | JSONException e) {
            this.log.error(e);
        }

        this.vocabulariesControlTypes = new HashMap<>();
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
        this.boostTagsNames = this.configuration.boostTagsNames();
        this.filterField = this.configuration.filterField();
        this.defaultFilterDateRange = this.configuration.defaultFilterDateRange();
        this.firstSortingField = this.configuration.firstSortingField();
        this.firstSortingType = this.configuration.firstSortingType();
        this.secondSortingField = this.configuration.secondSortingField();
        this.secondSortingType = this.configuration.secondSortingType();
        this.groupBy = this.configuration.groupBy();
        this.hideResultsBeforeSearch = this.configuration.hideResultsBeforeSearch();
        this.delta = this.configuration.delta();
        this.searchForm = this.configuration.searchForm();
        this.displayExport = this.configuration.displayExport();
        this.exportType = this.configuration.exportType();
    }

    public void saveConfiguration(SearchAssetConfigurationAction configAction) {
        // Sauvegarde dans la configuration
        // -- TYPE DE CONTENUS --
        String assetTypesJSON = this.getAssetTypesJSON().toJSONString();
        configAction.setPreference(this.request, ConfigurationConstants.JSON_ASSETS_TYPES,
                assetTypesJSON);
        // -- CRITERES DE RECHERCHE --
        // Vocabulaires pour afficher la recherche
        String vocabulariesControlTypesJSON = this.getVocabulariesControlTypesJSON().toJSONString();
        configAction.setPreference(this.request, ConfigurationConstants.JSON_VOCABULARIES_CONTROL_TYPES,
                vocabulariesControlTypesJSON);
        // Afficher le filtre de recherche par date
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_DISPLAY_DATE_FIELD,
                String.valueOf(displayDateField));
        // Affichage tri utilisateur
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
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_FIELD, firstSortingField);
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_TYPE, firstSortingType);
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_FIELD, secondSortingField);
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_TYPE, secondSortingType);
        // -- GROUPEMENT --
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_GROUP_BY, groupBy);
        // -- AFFICHAGE --
        // Ne pas afficher de résultat avant une recherche utilisateur
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_HIDE_RESULTS_BEFORE_SEARCH,
                String.valueOf(hideResultsBeforeSearch));
        // Nombre d'items par page (Delta)
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_DELTA, String.valueOf(delta));
        // Formulaire à afficher
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_SEARCH_FORM, searchForm);
        // -- EXPORT --
        // Affichage bouton export
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_DISPLAY_EXPORT,
                String.valueOf(displayExport));
        // Type d'export
        configAction.setPreference(this.request, ConfigurationConstants.PARAM_EXPORT_TYPE, exportType);
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

    public JSONObject getVocabulariesControlTypesJSON() {
        JSONArray vocabularyControlTypes = JSONFactoryUtil.createJSONArray();
        for (Map.Entry<String, String> entry : this.vocabulariesControlTypes.entrySet()) {
            JSONObject vocabularyControlType = JSONFactoryUtil.createJSONObject();
            vocabularyControlType.put(ConfigurationConstants.JSON_VOCABULARY_CONTROL_TYPE, entry.getValue());
            vocabularyControlType.put(ConfigurationConstants.JSON_VOCABULARY_ID, entry.getKey());
            vocabularyControlTypes.put(vocabularyControlType);
        }
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(ConfigurationConstants.JSON_VOCABULARIES_CONTROL_TYPES, vocabularyControlTypes);
        return result;
    }
}

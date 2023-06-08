package eu.strasbourg.portlet.search_asset_v2.configuration.constants;

public class ConfigurationConstants {
    public static final String PARAM_ASSET_TYPES_INDEXES = "assetTypeIndexes";
    public static final String PARAM_CLASSNAME = "classname";
    public static final String PARAM_SCOPE_IDS = "scopeIds";
    public static final String PARAM_STRUCTURE_ID = "structure";
    public static final String PARAM_TEMPLATE_KEY = "templateKey";
    public static final String PARAM_FRIENDLY_URL = "friendlyUrl";
    public static final String PARAM_PREFILTERS_INDEXES = "prefilterIndexes";
    public static final String PARAM_INCLUDE_EXCLUDE = "includeOrExclude";
    public static final String PARAM_ALL_ANY = "allOrAny";
    public static final String PARAM_CATEGORIES_OR_TAGS = "categoriesOrTags";
    public static final String PARAM_PREFILTER_SELECTION = "prefilterIds";

    public static final String PARAM_CRITERE_RECHERCHE_INDEXES = "vocabularyIndexes";
    public static final String PARAM_VOCABULARIES_IDS = "vocabularyIds";
    public static final String PARAM_VOCABULARIES_CONTROL_TYPE_IDS = "vocabularyControlType";
    public static final String PARAM_DISPLAY_DATE_FIELD = "displayDateField";
    public static final String PARAM_DISPLAY_DATES_BUTTONS = "displayDatesButtons";
    public static final String PARAM_DISPLAY_SORTING = "displaySorting";
    public static final String PARAM_DISPLAY_ASSET_TYPE = "displayAssetType";

    public static final String PARAM_BOOST_TAGS_NAMES = "boostTagsNames";

    public static final String PARAM_FILTER_FIELD = "filterField";
    public static final String PARAM_DEFAULT_FILTER_DATE_RANGE = "defaultFilterDateRange";

    public static final String PARAM_RANDOM_SORT = "randomSort";
    public static final String PARAM_FIRST_SORTING_FIELD = "firstSortingField";
    public static final String PARAM_FIRST_SORTING_TYPE = "firstSortingType";
    public static final String PARAM_SECOND_SORTING_FIELD = "secondSortingField";
    public static final String PARAM_SECOND_SORTING_TYPE = "secondSortingType";

//    public static final String PARAM_GROUP_BY = "groupBy";

    public static final String PARAM_HIDE_RESULTS_BEFORE_SEARCH = "hideResultsBeforeSearch";
    public static final String PARAM_DELTA = "delta";
    public static final String PARAM_SEARCH_FORM = "searchForm";

    public static final String PARAM_DISPLAY_EXPORT = "displayExport";
    public static final String PARAM_EXPORT_TYPE = "exportType";

    /** JSON */
    public static final String JSON_ASSETS_TYPES = "assetTypes";
    // Configuration Asset Data
    public static final String JSON_ASSET_CLASSNAME = "classname";
    public static final String JSON_ASSET_SCOPE_IDS = "scopeIds";
    public static final String JSON_ASSET_STRUCTURE_ID = "structureId";
    public static final String JSON_ASSET_TEMPLATE_KEY = "templateKey";
    public static final String JSON_ASSET_FRIENDLY_URL = "friendlyURL";
    public static final String JSON_ASSET_PREFILTERS = "prefilters";
    // Configuration Asset Prefilter Data
    public static final String JSON_ASSET_PREFILTER_INCLUDE_EXCLUDE = "contains";
    public static final String JSON_ASSET_PREFILTER_ALL_ANY = "operator";
    public static final String JSON_ASSET_PREFILTER_CATEGORIES_OR_TAGS = "type";
    public static final String JSON_ASSET_PREFILTER_SELECTION = "selection";

    // Configuration Vocabularies Data
    public static final String JSON_VOCABULARIES_CONTROL_TYPES = "vocabulariesControlTypes";
    public static final String JSON_VOCABULARY_ID = "vocabularyId";
    public static final String JSON_VOCABULARY_CONTROL_TYPE = "vocabularyControlType";

}

package eu.strasbourg.portlet.search_asset_v2.configuration.bean;

import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;
import org.apache.commons.lang3.ArrayUtils;

import javax.portlet.ActionRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationData {

    /** Contextes de récupération des données */
    private ActionRequest request;
    private SearchAssetConfiguration configuration;

    /** Données */
    private List<ConfigurationAssetData> assetTypeDataList;
    private boolean displayDateField;
    private boolean displaySorting;
    private String boostTagsNames;
    private String filterField;
    private long defaultFilerDateRange;
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

        String className, templateKey, friendlyURL, assetPrefiltersIndexes, operator, type;
        boolean contains;
        Long[] scopeGroupIDs, categoryOrTagIdList;
        List<ConfigurationAssetPrefilterData> assetPrefilterDataList;
        this.assetTypeDataList = new ArrayList<>();

        for (String assetTypeIndex : assetTypesIndexes.split(",")) {
            className = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_CLASSNAME + "_" + assetTypeIndex);
            templateKey = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_TEMPLATE_KEY + "_" + assetTypeIndex);
            friendlyURL = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_FRIENDLY_URL + "_" + assetTypeIndex);
            scopeGroupIDs = ArrayUtils.toObject(ParamUtil.getLongValues(this.request,
                    ConfigurationConstants.PARAM_SCOPE_GROUP_IDS + "_" + assetTypeIndex));

            // Asset prefilter repeatable field
            assetPrefiltersIndexes = ParamUtil.getString(this.request,
                    ConfigurationConstants.PARAM_ASSET_PREFILTERS_INDEXES + "_" + assetTypeIndex);
            assetPrefilterDataList = new ArrayList<>();

            for (String assetPrefiltersIndex : assetPrefiltersIndexes.split(",")) {
                contains = ParamUtil.getBoolean(this.request,
                        ConfigurationConstants.PARAM_CONTAINS + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
                operator =  ParamUtil.getString(this.request,
                        ConfigurationConstants.PARAM_OPERATOR + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
                type = ParamUtil.getString(this.request,
                        ConfigurationConstants.PARAM_TYPE + "_" + assetTypeIndex + "_" + assetPrefiltersIndex);
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

                // clear
                contains = false;
                operator = type = null;
                categoryOrTagIdList = null;
            }

            this.assetTypeDataList.add(
                    new ConfigurationAssetData(
                            className, templateKey, friendlyURL, Arrays.asList(scopeGroupIDs), assetPrefilterDataList
                    )
            );

            // clear
            className = templateKey = assetPrefiltersIndexes = null;
            scopeGroupIDs = null;
        }

        this.displayDateField = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_DATE_FIELD);
        this.displaySorting = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_SORTING);
        this.boostTagsNames = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_BOOST_TAGS_NAMES);
        this.filterField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FILTER_FIELD);
        this.defaultFilerDateRange = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DEFAULT_FILTER_DATE_RANGE);
        this.firstSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_FIELD);
        this.firstSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_FIRST_SORTING_TYPE);
        this.secondSortingField = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_FIELD);
        this.secondSortingType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SECOND_SORTING_TYPE);
        this.groupBy = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_GROUP_BY);
        this.hideResultsBeforeSearch = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_HIDE_RESULTS_BEFORE_SEARCH);
        this.delta = ParamUtil.getLong(this.request, ConfigurationConstants.PARAM_DELTA);
        this.searchForm = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_SEARCH_FORM);
        this.displayExport = ParamUtil.getBoolean(this.request, ConfigurationConstants.PARAM_DISPLAY_EXPORT);
        this.exportType = ParamUtil.getString(this.request, ConfigurationConstants.PARAM_EXPORT_TYPE, "");
    }

    private void initDataFromConfiguration() {
        // TODO Récupération des données des portlet preferences de la configuration
    }

    public boolean validate() {
        boolean result = true;
        // TODO Validation des données issues de la requête
        return result;
    }

    public int countAssetTypes() {
        return this.assetTypeDataList.size();
    }

}

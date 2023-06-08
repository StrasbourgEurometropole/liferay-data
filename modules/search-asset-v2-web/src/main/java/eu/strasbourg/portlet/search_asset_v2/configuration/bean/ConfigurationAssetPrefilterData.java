package eu.strasbourg.portlet.search_asset_v2.configuration.bean;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;

import java.util.List;

/**
 * Préfiltre permettant de limiter / spécifier la recherche d'un type d'asset
 */
public class ConfigurationAssetPrefilterData {

    private boolean includeOrExclude;
    private String operator;
    private String type;
    private List<Long> categoryOrTagIdList;

    /**
     * @param operator "and" / "or"
     */
    public ConfigurationAssetPrefilterData(Boolean includeOrExclude, String operator, String type,
                                           List<Long> categoryOrTagIdList) {
        this.includeOrExclude = includeOrExclude;
        this.operator = operator;
        this.type = type;
        this.categoryOrTagIdList = categoryOrTagIdList;
    }

    public JSONObject toJSON() {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTER_INCLUDE_EXCLUDE, this.includeOrExclude);
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTER_ALL_ANY, this.operator);
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTER_CATEGORIES_OR_TAGS, this.type);
        JSONArray jsonCategoryOrTagIdList = JSONFactoryUtil.createJSONArray(this.categoryOrTagIdList);
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTER_SELECTION, jsonCategoryOrTagIdList);
        return result;
    }

    public boolean isIncludeOrExclude() {
        return includeOrExclude;
    }

    public String getOperator() {
        return operator;
    }

    public String getType() {
        return type;
    }

    public List<Long> getCategoryOrTagIdList() {
        return categoryOrTagIdList;
    }

    public JSONArray getCategoryOrTagIdsJSON() {
        JSONArray CategoryOrTagIdsjson = JSONFactoryUtil.createJSONArray();
        for (Long categoryOrTagId : this.getCategoryOrTagIdList()) {
            CategoryOrTagIdsjson.put(""+categoryOrTagId);
        }
        return CategoryOrTagIdsjson;
    }

}

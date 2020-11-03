package eu.strasbourg.portlet.search_asset_v2.configuration.bean;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;

import java.util.List;

/**
 * Type d'asset
 */
public class ConfigurationAssetData {

    private String className;
    private String templateKey;
    private String friendlyURL;
    private List<Long> scopeGroupIDs;
    private List<ConfigurationAssetPrefilterData> assetPrefilterDataList;

    public ConfigurationAssetData(String className, String templateKey, String friendlyURL, List<Long> scopeGroupIDs,
                                  List<ConfigurationAssetPrefilterData> assetPrefilterDataList) {
        this.className = className;
        this.templateKey = templateKey;
        this.friendlyURL = friendlyURL;
        this.scopeGroupIDs = scopeGroupIDs;
        this.assetPrefilterDataList = assetPrefilterDataList;
    }

    public JSONObject toJSON() {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(ConfigurationConstants.JSON_ASSET_CLASSNAME, this.className);
        result.put(ConfigurationConstants.JSON_ASSET_TEMPLATE_KEY, this.templateKey);
        result.put(ConfigurationConstants.JSON_ASSET_FRIENDLY_URL, this.friendlyURL);
        JSONArray jsonSelection = JSONFactoryUtil.createJSONArray(this.scopeGroupIDs);
        result.put(ConfigurationConstants.JSON_ASSET_SCOPE_GROUP_IDS, jsonSelection);
        JSONArray jsonPrefilters = JSONFactoryUtil.createJSONArray();
        for (ConfigurationAssetPrefilterData prefilter : this.assetPrefilterDataList)
            jsonPrefilters.put(prefilter.toJSON());
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTERS, jsonPrefilters);
        return result;
    }

}
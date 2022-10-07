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
    private List<Long> scopeGroupIDs;
    private Long structureID;
    private String templateKey;
    private String friendlyURL;
    private List<ConfigurationAssetPrefilterData> assetPrefilterDataList;

    public ConfigurationAssetData(String className, List<Long> scopeGroupIDs, Long structureID, String templateKey,
                                  String friendlyURL, List<ConfigurationAssetPrefilterData> assetPrefilterDataList) {
        this.className = className;
        this.scopeGroupIDs = scopeGroupIDs;
        this.structureID = structureID;
        this.templateKey = templateKey;
        this.friendlyURL = friendlyURL;
        this.assetPrefilterDataList = assetPrefilterDataList;
    }

    public JSONObject toJSON() {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put(ConfigurationConstants.JSON_ASSET_CLASSNAME, this.className);
        JSONArray jsonSelection = JSONFactoryUtil.createJSONArray(this.scopeGroupIDs);
        result.put(ConfigurationConstants.JSON_ASSET_SCOPE_IDS, jsonSelection);
        result.put(ConfigurationConstants.JSON_ASSET_STRUCTURE_ID, this.structureID);
        result.put(ConfigurationConstants.JSON_ASSET_TEMPLATE_KEY, this.templateKey);
        result.put(ConfigurationConstants.JSON_ASSET_FRIENDLY_URL, this.friendlyURL);
        JSONArray jsonPrefilters = JSONFactoryUtil.createJSONArray();
        for (ConfigurationAssetPrefilterData prefilter : this.assetPrefilterDataList)
            jsonPrefilters.put(prefilter.toJSON());
        result.put(ConfigurationConstants.JSON_ASSET_PREFILTERS, jsonPrefilters);
        return result;
    }

    public String getClassName() {
        return this.className;
    }

    public List<Long> getScopeGroupIDs() {
        return this.scopeGroupIDs;
    }

    public JSONArray getScopeGroupIDsJSON() {
        JSONArray ScopeGroupIdsjson = JSONFactoryUtil.createJSONArray();
        for (Long scopeGroupId : this.scopeGroupIDs) {
            ScopeGroupIdsjson.put(""+scopeGroupId);
        }
        return ScopeGroupIdsjson;
    }

    public Long getStructureID() {
        return this.structureID;
    }

    public String getTemplateKey() {
        return this.templateKey;
    }

    public String getFriendlyURL() {
        return this.friendlyURL;
    }

    public List<ConfigurationAssetPrefilterData> getAssetPrefilterDataList() {
        return this.assetPrefilterDataList;
    }
}
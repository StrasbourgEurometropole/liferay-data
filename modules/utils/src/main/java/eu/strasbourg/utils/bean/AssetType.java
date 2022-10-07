package eu.strasbourg.utils.bean;

import java.util.List;

/**
 * Type d'asset
 */
public class AssetType {

    private String className;
    private List<Long> scopeGroupIDs;
    private Long structureID;
    private List<AssetPrefilter> assetPrefilterList;

    public AssetType(String className, List<Long> scopeGroupIDs, Long structureID,
                     List<AssetPrefilter> assetPrefilterList) {
        this.className = className;
        this.scopeGroupIDs = scopeGroupIDs;
        this.structureID = structureID;
        this.assetPrefilterList = assetPrefilterList;
    }

    public String getClassName() {
        return this.className;
    }

    public List<Long> getScopeGroupIDs() {
        return this.scopeGroupIDs;
    }

    public Long getStructureID() {
        return this.structureID;
    }

    public List<AssetPrefilter> getAssetPrefilterList() {
        return this.assetPrefilterList;
    }
}
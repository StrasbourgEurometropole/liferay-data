package eu.strasbourg.utils.bean;

import java.util.List;

/**
 * Préfiltre permettant de limiter / spécifier la recherche d'un type d'asset
 */
public class AssetPrefilter {

    private boolean includeOrExclude;
    private String operator;
    private String type;
    private List<Long> categoryOrTagIdList;

    /**
     * @param operator "and" / "or"
     */
    public AssetPrefilter(Boolean includeOrExclude, String operator, String type,
                          List<Long> categoryOrTagIdList) {
        this.includeOrExclude = includeOrExclude;
        this.operator = operator;
        this.type = type;
        this.categoryOrTagIdList = categoryOrTagIdList;
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

}

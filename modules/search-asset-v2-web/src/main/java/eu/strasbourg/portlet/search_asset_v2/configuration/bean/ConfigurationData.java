package eu.strasbourg.portlet.search_asset_v2.configuration.bean;

import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;

import javax.portlet.ActionRequest;
import java.util.List;

public class ConfigurationData {

    /** Contextes de récupération des données */
    private ActionRequest request;
    private SearchAssetConfiguration configuration;

    /** Données */
    private List<ConfigurationAssetData> assetTypeDataList;

    public ConfigurationData(ActionRequest request) {
        this.request = request;
        this.initDataFromRequest();
    }

    public ConfigurationData(SearchAssetConfiguration configuration) {
        this.configuration = configuration;
        this.initDataFromConfiguration();
    }

    private void initDataFromRequest() {
        // TODO Récupération des données de la requête
    }

    private void initDataFromConfiguration() {
        // TODO Récupération des données des portlet preferences de la configuration
    }

    public boolean validate() {
        boolean result = true;
        // TODO Validation des données issues de la requête
        return result;
    }



}

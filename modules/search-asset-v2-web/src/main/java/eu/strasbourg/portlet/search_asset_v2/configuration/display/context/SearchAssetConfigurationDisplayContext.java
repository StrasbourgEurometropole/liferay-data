package eu.strasbourg.portlet.search_asset_v2.configuration.display.context;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationAssetData;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationData;
import eu.strasbourg.service.project.model.ProjectTimeline;

import javax.security.auth.login.Configuration;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAssetConfigurationDisplayContext {

    private SearchAssetConfiguration configuration;
    private final ThemeDisplay themeDisplay;
    private ConfigurationData configurationData;

    private List<AssetRendererFactory<?>> availableAssetRendererFactories;


    public SearchAssetConfigurationDisplayContext(HttpServletRequest request) throws ConfigurationException {
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.configurationData = new ConfigurationData(this.getConfiguration());
        this.availableAssetRendererFactories = ListUtil
                .filter(
                        AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
                                themeDisplay.getCompany().getCompanyId()),
                        assetRendererFactory -> assetRendererFactory.isCategorizable()
                                && assetRendererFactory.getClassName()
                                .startsWith("eu.strasbourg"));
    }

    public SearchAssetConfiguration getConfiguration() throws ConfigurationException {
        if (this.configuration == null) {
            this.configuration = this.themeDisplay.getPortletDisplay().getPortletInstanceConfiguration(
                    SearchAssetConfiguration.class);
        }
        return this.configuration;
    }

    public String getDefaultAssetTypeIndexes() throws PortalException {
        if (this.getConfiguration() != null) {
            String[] assetTypes = new String[this.configurationData.countAssetTypes()];
            String indexes = "0";
            for (int i = 1; i <= assetTypes.length; i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
    }

    public List<ConfigurationAssetData> getConfigurationAssetData() throws PortalException {
        List<ConfigurationAssetData> assetData = new ArrayList<ConfigurationAssetData>();
        if (this.getConfiguration() != null) {
        }
        return assetData;
    }

    // Retourne la liste des types de contenus disponibles
    public List<String> getAvailableAssetTypeNames() {
        List<String> availableAssetTypeNames = new ArrayList<>();
        for (AssetRendererFactory assetRendererFactory : this.availableAssetRendererFactories) {
            availableAssetTypeNames.add(assetRendererFactory.getClassName());
        }
        return availableAssetTypeNames;
    }

    // Retourne la liste des templates disponibles rangees par type de contenu
    public HashMap<String,List<DDMTemplate>> getAvailableAssetTemplates() {
        HashMap<String,List<DDMTemplate>> availableAssetTemplates = new HashMap<>();
        for (AssetRendererFactory assetRendererFactory : this.availableAssetRendererFactories) {
            String className = assetRendererFactory.getClassName();
            long classNameId = assetRendererFactory.getClassNameId();
            List<DDMTemplate> templates = DDMTemplateLocalServiceUtil
                    .getTemplates(themeDisplay.getScopeGroupId(), classNameId);
            availableAssetTemplates.put(className, templates);
        }
        return availableAssetTemplates;
    }

    public ConfigurationData getConfigurationData() {
        return this.configurationData;
    }
}

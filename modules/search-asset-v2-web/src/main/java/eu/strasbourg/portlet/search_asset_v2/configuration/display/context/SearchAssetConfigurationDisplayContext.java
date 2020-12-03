package eu.strasbourg.portlet.search_asset_v2.configuration.display.context;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationAssetData;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationData;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        availableAssetTypeNames.add("searchJournalArticle");
        availableAssetTypeNames.add("searchDocument");
        availableAssetTypeNames.add("searchDemarche");
        return availableAssetTypeNames;
    }

    // Retourne la liste des types de contenus disponibles
    public String getAvailableAssetTypeNamesString() {
        String assetTypeNames = "";
        for (String assetTypeName : getAvailableAssetTypeNames()) {
            if(Validator.isNotNull(assetTypeNames))
                assetTypeNames +=",";
            assetTypeNames += assetTypeName;
        }
        return assetTypeNames;
    }

    // Retourne la liste des sites disponibles
    public JSONArray getAvailableScopes() {
        JSONArray jsonGroups = JSONFactoryUtil.createJSONArray();
        JSONObject jsonGroupGlobal = JSONFactoryUtil.createJSONObject();
        try {
            Group group = GroupLocalServiceUtil.getFriendlyURLGroup(themeDisplay.getCompanyId(), "/global");
            jsonGroupGlobal.put("value", group.getGroupId());
            jsonGroupGlobal.put("label", "Global");
            jsonGroups.put(jsonGroupGlobal);
        }catch(PortalException e){
        }
        List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), Group.class.getName(), 0).stream().filter(g -> g.getSite()).collect(Collectors.toList());
        for (Group group : groups) {
            JSONObject jsonGroup = JSONFactoryUtil.createJSONObject();
            jsonGroup.put("value", group.getGroupId());
            jsonGroup.put("label", group.getGroupKey());
            jsonGroups.put(jsonGroup);
        }
        return jsonGroups;
    }

    // Retourne la liste des templates disponibles rangees par type de contenu
    public JSONObject getAvailableAssetTemplates() {
        JSONObject availableAssetTemplates = JSONFactoryUtil.createJSONObject();
        for (AssetRendererFactory assetRendererFactory : this.availableAssetRendererFactories) {
            String className = assetRendererFactory.getClassName();
            long classNameId = assetRendererFactory.getClassNameId();
            List<DDMTemplate> templates = DDMTemplateLocalServiceUtil
                    .getTemplates(themeDisplay.getScopeGroupId(), classNameId);
            JSONArray templatesJson = JSONFactoryUtil.createJSONArray();
            for (DDMTemplate template: templates) {
                JSONObject templateJson = JSONFactoryUtil.createJSONObject();
                templateJson.put("id", template.getTemplateId());
                templateJson.put("value", template.getName(Locale.FRANCE));
                templatesJson.put(templateJson);
            }
            availableAssetTemplates.put(className, templatesJson);
        }

        // ajoute les templates de fichiers
        long documentClassNameId = ClassNameLocalServiceUtil
                .getClassNameId(FileEntry.class);
        List<DDMTemplate> documentTemplates = DDMTemplateLocalServiceUtil
                .getTemplates(themeDisplay.getScopeGroupId(),
                        documentClassNameId);
        JSONArray templatesJson = JSONFactoryUtil.createJSONArray();
        for (DDMTemplate template: documentTemplates) {
            JSONObject templateJson = JSONFactoryUtil.createJSONObject();
            templateJson.put("id", template.getTemplateId());
            templateJson.put("value", template.getName(Locale.FRANCE));
            templatesJson.put(templateJson);
        }
        availableAssetTemplates.put("searchDocument",templatesJson);
        return availableAssetTemplates;
    }

    public ConfigurationData getConfigurationData() {
        return this.configurationData;
    }


}

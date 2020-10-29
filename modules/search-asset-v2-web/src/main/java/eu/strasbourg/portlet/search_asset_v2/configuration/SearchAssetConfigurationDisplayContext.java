package eu.strasbourg.portlet.search_asset_v2.configuration;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.ProjectTimeline;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchAssetConfigurationDisplayContext {

    private SearchAssetConfiguration _configuration;
    private final ThemeDisplay _themeDisplay;

    public SearchAssetConfigurationDisplayContext(HttpServletRequest request) {
        this._themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);


    }

    public SearchAssetConfiguration getConfiguration() throws ConfigurationException {
        if (this._configuration == null) {
            this._configuration = this._themeDisplay.getPortletDisplay().getPortletInstanceConfiguration(
                    SearchAssetConfiguration.class);
        }
        return this._configuration;
    }

    public String getDefaultAssetTypeIndexes() throws PortalException {
        if (this.getConfiguration() != null) {
            String[] assetTypes = this._configuration.assetTypes();
            String indexes = "0";
            for (int i = 1; i <= assetTypes.length; i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
    }
}

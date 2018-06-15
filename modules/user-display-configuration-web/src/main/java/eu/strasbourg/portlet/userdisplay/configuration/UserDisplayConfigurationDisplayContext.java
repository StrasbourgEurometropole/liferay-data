package eu.strasbourg.portlet.userdisplay.configuration;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LayoutTypePortletFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.PortletHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDisplayConfigurationDisplayContext {

    private ThemeDisplay themeDisplay;
    private UserDisplayConfiguration configuration;
    private List<String> portletIds;
    private List<String> portletTitles;


    public UserDisplayConfigurationDisplayContext(ThemeDisplay themeDisplay, UserDisplayConfiguration configuration) {
        this.themeDisplay = themeDisplay;
        this.configuration = configuration;
    }

    /**
     * Retourne la liste des ids de portlets de la page d'accueil de MonStrasbourg
     */
    public List<String> getPortletIds() {
        if (portletIds == null) {
            try {
                Layout layout = LayoutLocalServiceUtil
                        .getFriendlyURLLayout(themeDisplay.getScopeGroupId(),
                                false,
                                "/accueil");
                LayoutTypePortlet layoutTypePortlet = LayoutTypePortletFactoryUtil.create(layout);
                portletIds = layoutTypePortlet.getPortletIds();
            } catch (PortalException e) {
                e.printStackTrace();
                portletIds = new ArrayList();
            }
        }
        return portletIds;
    }

    /**
     * Retourne la liste des noms de portlets de la page d'accueil de MonStrasbourg
     */
    public List<String> getPortletTitles() {
        if (this.portletTitles == null) {
            portletTitles = new ArrayList<>();
            for (String portletId : this.getPortletIds()) {
                Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);
                portletTitles.add(portlet.getDisplayName());
            }
        }
        return portletTitles;
    }

    public String getPortletDisplayStatus(String portletId) {
        String displayStatus = "on_hidden";
        String configurationString = this.configuration.adminConfig();
        if (Validator.isNull(configurationString)) {
            return displayStatus;
        }
        try {
            JSONArray jsonConfig = JSONFactoryUtil.createJSONArray(configurationString);
            for (int i = 0; i < jsonConfig.length(); i++) {
                JSONObject portletConfig = jsonConfig.getJSONObject(i);
                String configPortletId = portletConfig.getString("portletId");
                if (Validator.isNotNull(configPortletId) && configPortletId.equals(portletId)) {
                    displayStatus = portletConfig.getString("status");
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return displayStatus;
    }
    
    public String getPortletDisplayTitle(String portletId) {
        String displayTitle = "";
        String configurationString = this.configuration.adminConfig();
        if (Validator.isNull(configurationString)) {
            return displayTitle;
        }
        try {
            JSONArray jsonConfig = JSONFactoryUtil.createJSONArray(configurationString);
            for (int i = 0; i < jsonConfig.length(); i++) {
                JSONObject portletConfig = jsonConfig.getJSONObject(i);
                String configPortletId = portletConfig.getString("portletId");
                if (Validator.isNotNull(configPortletId) && configPortletId.equals(portletId)) {
                    displayTitle = portletConfig.getString("title");
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return displayTitle;
    }
    
    public String getPortletDisplayDescription(String portletId){
        String displayDescription = "";
        String configurationString = this.configuration.adminConfig();
        if (Validator.isNull(configurationString)) {
            return displayDescription;
        }
        try {
            JSONArray jsonConfig = JSONFactoryUtil.createJSONArray(configurationString);
            for (int i = 0; i < jsonConfig.length(); i++) {
                JSONObject portletConfig = jsonConfig.getJSONObject(i);
                String configPortletId = portletConfig.getString("portletId");
                if (Validator.isNotNull(configPortletId) && configPortletId.equals(portletId)) {
                    displayDescription = portletConfig.getString("description");
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return displayDescription;
        
    }

    public boolean isPortletHiddenByUser(String portletId) {
        return !PortletHelper.isPortletDisplayedOnDashboard(themeDisplay, portletId);
    }
}

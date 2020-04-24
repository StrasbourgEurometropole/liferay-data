package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.portlet.council.configuration.CouncilConfiguration;

import javax.portlet.PortletPreferences;

public class CouncilDisplayContext {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay themeDisplay;
    private PortletPreferences preferences;
    private CouncilConfiguration configuration;

    /**
     * Constructeur
     */
    public CouncilDisplayContext(ThemeDisplay themeDisplay, PortletPreferences preferences) {
        this.themeDisplay = themeDisplay;
        this.preferences = preferences;
        try {
            this.configuration = themeDisplay.getPortletDisplay().getPortletInstanceConfiguration(CouncilConfiguration.class);
        } catch (PortalException e) {
            log.error(e);
        }
    }

    /**
     * Accesseur de la configuration du portlet
     */
    @SuppressWarnings("unused")
    public CouncilConfiguration getConfiguration() {
        return this.configuration;
    }


}

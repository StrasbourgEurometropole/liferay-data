package eu.strasbourg.utils.api;

import com.liferay.portal.kernel.theme.ThemeDisplay;

public interface PortalHelperService {

    /**
     * Retourne HomeURL
     */
    String getHomeURL(ThemeDisplay themeDisplay);

    /**
     * Retourne PortalURL
     */
    String getPortalURL(ThemeDisplay themeDisplay);

}

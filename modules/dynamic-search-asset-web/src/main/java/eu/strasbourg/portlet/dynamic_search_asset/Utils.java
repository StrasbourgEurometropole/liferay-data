package eu.strasbourg.portlet.dynamic_search_asset;

import com.liferay.portal.kernel.theme.ThemeDisplay;

public class Utils {
    /**
     * Récupération de l'URL de base du site pour le lien vers les pages des entités
     */
    public static String getHomeURL(ThemeDisplay themeDisplay) {
        if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty()
                || themeDisplay.getScopeGroup().isStagingGroup()) {
            return "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL() + "/";
        } else {
            return "/";
        }
    }
}
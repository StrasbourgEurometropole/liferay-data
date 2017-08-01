package eu.strasbourg.utils;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

public class PortletHelper {

	/**
	 * Retourne le titre du portlet configuré dans la configuration Look And
	 * Feel s'il existe et si "utiliser le titre personnalisé" est coché, sinon
	 * à partir de la clé de traduction passée en paramètre
	 */
	public static String getPortletTitle(String key, PortletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String titleFromLanguageKey = LanguageUtil
			.get(PortalUtil.getHttpServletRequest(request), key);
		String useCustomPortletPreference = request.getPreferences()
			.getValue("portletSetupUseCustomTitle", "false");
		boolean useCustomPortlet = GetterUtil.get(useCustomPortletPreference,
			false);
		if (useCustomPortlet) {
			String preferenceKey = "portletSetupTitle_"
				+ themeDisplay.getLocale().toString();
			return request.getPreferences().getValue(preferenceKey,
				titleFromLanguageKey);
		} else {
			return titleFromLanguageKey;
		}
	}
}

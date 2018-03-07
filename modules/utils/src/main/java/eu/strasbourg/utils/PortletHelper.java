package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

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
	
	/**
	 * Retourne un boolean indiquant si le portlet doit être affiché ou non en fonction des
	 * préférences de l'utilisateur dans le portlet user display configuration
	 * @param themeDisplay
	 * @param cssClassNames
	 * @return
	 */
	public static boolean hiddenDashboardPortlet(ThemeDisplay themeDisplay, String cssClassNames){
		
		// Récupération du publik ID avec la session
		String internalId = SessionParamUtil.getString(themeDisplay.getRequest(), "publik_internal_id");
		boolean result = true;
		
		if(internalId != null && !internalId.equals("")){
			List<String> hiddenPortlets = new ArrayList<String>(); // Les portlets à ne pas afficher
			
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
			JSONObject json;
			
			try {
				json = JSONFactoryUtil.createJSONObject(user.getDisplayConfig());
				JSONArray jsonArray = json.getJSONArray("hiddenPortlets");
				jsonArray.forEach(t -> hiddenPortlets.add((String) t));
				result = !hiddenPortlets.contains(cssClassNames);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}

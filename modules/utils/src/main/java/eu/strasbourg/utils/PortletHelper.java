package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
		String titleFromLanguageKey = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), key);
		String useCustomPortletPreference = request.getPreferences().getValue("portletSetupUseCustomTitle", "false");
		boolean useCustomPortlet = GetterUtil.get(useCustomPortletPreference, false);
		if (useCustomPortlet) {
			String preferenceKey = "portletSetupTitle_" + themeDisplay.getLocale().toString();
			return request.getPreferences().getValue(preferenceKey, titleFromLanguageKey);
		} else {
			return titleFromLanguageKey;
		}
	}

	/**
	 * Retourne un boolean indiquant si le portlet doit être affiché ou non en
	 * fonction des préférences de l'utilisateur dans le portlet user display
	 * configuration ou en fonction de la configuration si ce dernier n'a pas effectué d'action sur le portlet
	 * -> reste à true si l'utilisateur n'est pas connecté
	 */
	public static boolean isPortletDisplayedOnDashboard(ThemeDisplay themeDisplay, String portletId) {

		// Récupération du publik ID avec la session
		String internalId = SessionParamUtil.getString(themeDisplay.getRequest(), "publik_internal_id");
		boolean result = true;
        String adminStatus = getDashboardPortletStatusFromAdminConfig(themeDisplay, portletId)[0];
        if (adminStatus.equals("off_hidden")) {
			return false;
        } else if (adminStatus.equals("on_disabled")) {
            return true;
        }
		if (internalId != null && !internalId.equals("")) {
			// Portlets à ne pas afficher et à afficher
			List<String> hiddenPortlets = new ArrayList<String>();
			List<String> shownPortlets = new ArrayList<String>();
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

			try {
				JSONObject json = JSONFactoryUtil.createJSONObject(user.getDisplayConfig());
				JSONArray hiddenPortletsJsonArray = json.getJSONArray("hiddenPortlets");
				JSONArray shownPortletsJsonArray = json.getJSONArray("shownPortlets");
				if (hiddenPortletsJsonArray != null) {
					hiddenPortletsJsonArray.forEach(t -> hiddenPortlets.add((String) t));
				}
				if (shownPortletsJsonArray != null) {
					shownPortletsJsonArray.forEach(t -> shownPortlets.add((String) t));
				}
				if (shownPortlets.contains(portletId)) {
					return true;
				} else if (hiddenPortlets.contains(portletId)) {
					return false;
				} else {
					return Validator.isNull(adminStatus) || adminStatus.startsWith("on");
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return true;
			}
		} else {
        	return true;
		}
	}

	public static boolean showDeleteButtonOnDashboard(ThemeDisplay themeDisplay, String portletId) {
		String adminConfig = getDashboardPortletStatusFromAdminConfig(themeDisplay, portletId)[0];
		if (adminConfig.equals("on_disabled") || adminConfig.equals("on_hidden")) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne un boolean indiquant si le portlet doit être affiché replié ou non en
	 * fonction des préférences de l'utilisateur dans le portlet user display
	 * configuration
	 *
	 */
	public static boolean isPortletFoldedOnDashboard(ThemeDisplay themeDisplay, String portletId) {

		// Récupération du publik ID avec la session
		String internalId = SessionParamUtil.getString(themeDisplay.getRequest(), "publik_internal_id");
		String adminStatus = getDashboardPortletStatusFromAdminConfig(themeDisplay, portletId)[1];
		if (adminStatus.equals("no-retractable")) {
			return false;
		}
		boolean result = Validator.isNull(adminStatus) || adminStatus.equals("retractable-folded");
		if (internalId != null && !internalId.equals("")) {
			// Portlets à repliés et dépliés
			List<String> foldedPortlets = new ArrayList<String>();
			List<String> unfoldedPortlets = new ArrayList<String>();
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

			try {
				JSONObject json = JSONFactoryUtil.createJSONObject(user.getDisplayConfig());
				JSONArray foldedPortletsJsonArray = json.getJSONArray("foldedPortlets");
				JSONArray unfoldedPortletsJsonArray = json.getJSONArray("unfoldedPortlets");
				if (foldedPortletsJsonArray != null) {
					foldedPortletsJsonArray.forEach(t -> foldedPortlets.add((String) t));
				}
				if (unfoldedPortletsJsonArray != null) {
					unfoldedPortletsJsonArray.forEach(t -> unfoldedPortlets.add((String) t));
				}
				if (foldedPortlets.contains(portletId)) {
					return true;
				} else if (unfoldedPortlets.contains(portletId)) {
					return false;
				} else {
					return result;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return result;
			}
		} else {
			return result;
		}
	}

	public static boolean showRetractableButtonOnDashboard(ThemeDisplay themeDisplay, String portletId) {
		String adminConfig = getDashboardPortletStatusFromAdminConfig(themeDisplay, portletId)[1];
		if (adminConfig.equals("retractable-unfolded") || adminConfig.equals("retractable-folded")) {
			return true;
		}
		return false;
	}

	private static String[] getDashboardPortletStatusFromAdminConfig(ThemeDisplay themeDisplay, String portletId) {
		String[] status = {"on_hidden","no-retractable"};

		ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
		String configurationString = GetterUtil.getString(ed.getAttribute("user_display_global_config"));
		if (Validator.isNull(configurationString)) {
			return status;
		}
		try {
			JSONArray jsonConfig = JSONFactoryUtil.createJSONArray(configurationString);
			for (int i = 0; i < jsonConfig.length(); i++) {
				JSONObject portletConfig = jsonConfig.getJSONObject(i);
				String configPortletId = portletConfig.getString("portletId");
				if (Validator.isNotNull(configPortletId) && configPortletId.equals(portletId)) {
					String displayStatus = portletConfig.getString("displayStatus");
					String retractableStatus = portletConfig.getString("retractableStatus");
					status[0] = displayStatus;
					status[1] = retractableStatus;
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
    public static void showPortlet(String portletId) {
	    togglePortlet(portletId, true);
    }


    public static void hidePortlet(String portletId) {
        togglePortlet(portletId, false);
    }

	public static void togglePortlet(String portletId, boolean show) {
		// Récupération du publik ID avec la session
        HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		String internalId = SessionParamUtil.getString(request, "publik_internal_id");
		
		if (internalId != null && !internalId.equals("")) {
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
			try {
				// JSON initialisation
				String userConfigString = user.getDisplayConfig();
				if (Validator.isNull(userConfigString)) {
					userConfigString = "{\"hiddenPortlets\":[], \"shownPortlets\":[]}";
				}
				
				JSONObject json = JSONFactoryUtil.createJSONObject(user.getDisplayConfig());
				JSONArray hiddenPortletsJsonArray;
				if (json.has("hiddenPortlets")) {
					hiddenPortletsJsonArray = json.getJSONArray("hiddenPortlets");
				}  else {
					hiddenPortletsJsonArray = JSONFactoryUtil.createJSONArray();
				}

				JSONArray shownPortletsJsonArray;
				if (json.has("shownPortlets")) {
					shownPortletsJsonArray = json.getJSONArray("shownPortlets");
				} else {
					shownPortletsJsonArray = JSONFactoryUtil.createJSONArray();
				}

				List<String> hiddenPortletIds = new ArrayList<String>(); 
				hiddenPortletsJsonArray.forEach(a -> hiddenPortletIds.add((String)a));

				List<String> shownPortletIds = new ArrayList<String>();
				shownPortletsJsonArray.forEach(a -> shownPortletIds.add((String)a));

				if (show) {
					if (hiddenPortletIds.contains(portletId)) {
						hiddenPortletIds.remove(portletId);
					}
					if (!shownPortletIds.contains(portletId)) {
						shownPortletIds.add(portletId);
					}
				} else {
					if (shownPortletIds.contains(portletId)) {
						shownPortletIds.remove(portletId);
					}
					if (!hiddenPortletIds.contains(portletId)) {
						hiddenPortletIds.add(portletId);
					}
				}

				JSONArray newHiddenPortletJsonArray = JSONFactoryUtil.createJSONArray();
				hiddenPortletIds.forEach(a -> newHiddenPortletJsonArray.put(a));

				JSONArray newShownPortletJsonArray = JSONFactoryUtil.createJSONArray();
				shownPortletIds.forEach(a -> newShownPortletJsonArray.put(a));
				
				// Enregistrement des préférences utilisateur.
				json.put("hiddenPortlets", newHiddenPortletJsonArray);
				json.put("shownPortlets", newShownPortletJsonArray);

				user.setDisplayConfig(json.toJSONString());
				PublikUserLocalServiceUtil.updatePublikUser(user);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}


	public static boolean unfoldPortlet(String portletId) {
		return toggleFoldPortlet(portletId, false);
	}


	public static boolean foldPortlet(String portletId) {
		return toggleFoldPortlet(portletId, true);
	}

	public static boolean toggleFoldPortlet(String portletId, boolean fold) {
		// Récupération de l'état des portlets dans la session
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		String retractableStatus = SessionParamUtil.getString(request, "retractable_status");

		if (Validator.isNotNull(retractableStatus)) {
			try {
				JSONObject json = JSONFactoryUtil.createJSONObject(retractableStatus);
				JSONArray foldenPortletsJsonArray;
				if (json.has("foldenPortlets")) {
					foldenPortletsJsonArray = json.getJSONArray("foldenPortlets");
				}  else {
					foldenPortletsJsonArray = JSONFactoryUtil.createJSONArray();
				}

				JSONArray unfoldenPortletsJsonArray;
				if (json.has("unfoldenPortlets")) {
					unfoldenPortletsJsonArray = json.getJSONArray("unfoldenPortlets");
				} else {
					unfoldenPortletsJsonArray = JSONFactoryUtil.createJSONArray();
				}

				List<String> foldenPortletIds = new ArrayList<String>();
				foldenPortletsJsonArray.forEach(a -> foldenPortletIds.add((String)a));

				List<String> unfoldenPortletIds = new ArrayList<String>();
				unfoldenPortletsJsonArray.forEach(a -> unfoldenPortletIds.add((String)a));

				if (fold) {
					if (foldenPortletIds.contains(portletId)) {
						foldenPortletIds.remove(portletId);
					}
					if (!unfoldenPortletIds.contains(portletId)) {
						unfoldenPortletIds.add(portletId);
					}
				} else {
					if (unfoldenPortletIds.contains(portletId)) {
						unfoldenPortletIds.remove(portletId);
					}
					if (!foldenPortletIds.contains(portletId)) {
						foldenPortletIds.add(portletId);
					}
				}

				JSONArray newFoldenPortletJsonArray = JSONFactoryUtil.createJSONArray();
				foldenPortletIds.forEach(a -> newFoldenPortletJsonArray.put(a));

				JSONArray newUnfoldenPortletJsonArray = JSONFactoryUtil.createJSONArray();
				unfoldenPortletIds.forEach(a -> newUnfoldenPortletJsonArray.put(a));

				// Enregistrement des préférences utilisateur.
				json.put("foldenPortlets", newFoldenPortletJsonArray);
				json.put("unFoldenPortlets", newUnfoldenPortletJsonArray);

				request.getSession().setAttribute("retractable_status", json.toJSONString());
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}

		return true;
	}
}

package eu.strasbourg.portlet.favorites.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		configurationPid = "eu.strasbourg.portlet.favorites.configuration.FavoritesConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		immediate = true,	
		property = {
				"javax.portlet.name=" + StrasbourgPortletKeys.FAVORITES_VIEWER_WEB },
		service = ConfigurationAction.class)
public class FavoritesConfigurationAction extends DefaultConfigurationAction {

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			
			// Page voir tous
			String showAllURL = ParamUtil.getString(request, "showAllUrl");
			setPreference(request, "showAllURL", showAllURL);
			
			// Mode d'affichage
			String template = ParamUtil.getString(request, "template");
			setPreference(request, "template", template);

			// No favorites
			Map<Locale, String> noFavoritesMap = LocalizationUtil
					.getLocalizationMap(request, "noFavoritesMap");
			LocalizedValuesMap map = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : noFavoritesMap.entrySet()) {
				map.put(e.getKey(), e.getValue());
			}
			String noFavoritesXML = LocalizationUtil.getXml(map, "noFavorites");
			setPreference(request, "noFavoritesXML", noFavoritesXML);

			// No favorites selected
			Map<Locale, String> noFavoritesSelectedMap = LocalizationUtil
					.getLocalizationMap(request, "noFavoritesSelectedMap");
			LocalizedValuesMap noFavMap = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : noFavoritesSelectedMap.entrySet()) {
				noFavMap.put(e.getKey(), e.getValue());
			}
			String noFavoritesSelectedXML = LocalizationUtil.getXml(noFavMap, "noFavoritesSelected");
			setPreference(request, "noFavoritesSelectedXML", noFavoritesSelectedXML);

			// Chapô
			Map<Locale, String> textMap = LocalizationUtil
					.getLocalizationMap(request, "texteMap");
			LocalizedValuesMap textemap = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : textMap.entrySet()) {
				textemap.put(e.getKey(), e.getValue());
			}
			String texteXML = LocalizationUtil.getXml(textemap, "texte");
			setPreference(request, "texteXML", texteXML);
		}
		super.processAction(portletConfig, request, response);
	}
	
	
	/**
	 * Envoie à la JSP de configuration des informations nécessaires
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			// Pages sélectionnées
			FavoritesConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						FavoritesConfiguration.class);
			
			// Page voir tous
			request.setAttribute("showAllURL", configuration.showAllURL());
			
			// Template
			request.setAttribute("template", configuration.template());

			// Texte si pas de favoris
			request.setAttribute("noFavorites", configuration.noFavoritesXML());

			// Texte si pas de favoris sélectionné
			request.setAttribute("noFavoritesSelected", configuration.noFavoritesSelectedXML());

			// Chapô
			request.setAttribute("texte", configuration.texteXML());
			

		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/favorites-viewer-configuration.jsp";
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}

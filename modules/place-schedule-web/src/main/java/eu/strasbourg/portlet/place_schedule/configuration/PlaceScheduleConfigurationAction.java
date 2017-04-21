package eu.strasbourg.portlet.place_schedule.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(configurationPid = "eu.strasbourg.portlet.place_schedule.configuration.PlaceScheduleConfiguration", configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true, property = {
		"javax.portlet.name="
				+ StrasbourgPortletKeys.PLACE_SCHEDULE_WEB }, service = ConfigurationAction.class)
public class PlaceScheduleConfigurationAction
		extends DefaultConfigurationAction {

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
		Locale locale = themeDisplay.getLocale();

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			// Catégorie
			String categoryId = ParamUtil.getString(request, "categoryId");
			setPreference(request, "categoryId", categoryId);

			// récupère le nom de la catégorie
			String categoryTitle = "";
			if (Validator.isNotNull(categoryId)) {
				AssetCategory category = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(Long.parseLong(categoryId));
				if (Validator.isNotNull(category)) {
					categoryTitle = category.getTitle(locale);
				}
			}
			setPreference(request, "categoryTitle", categoryTitle);

			// Text
			Map<Locale, String> textMap = LocalizationUtil
					.getLocalizationMap(request, "textMap");
			LocalizedValuesMap map = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : textMap.entrySet()) {
				map.put(e.getKey(), e.getValue());
			}
			String textXML = LocalizationUtil.getXml(map, "TextSchedule");
			setPreference(request, "textScheduleXML", textXML);
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
			PlaceScheduleConfiguration configuration = themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
							PlaceScheduleConfiguration.class);
			request.setAttribute("categoryId", configuration.categoryId());
			request.setAttribute("categoryTitle",
					configuration.categoryTitle());
			request.setAttribute("textSchedule", configuration.textScheduleXML());

		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

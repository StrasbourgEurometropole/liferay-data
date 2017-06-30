package eu.strasbourg.portlet.event_viewer.configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.event_viewer.configuration.EventViewerConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.EVENT_VIEWER_WEB },
	service = ConfigurationAction.class)
public class EventViewerConfigurationAction extends DefaultConfigurationAction {

	private EventViewerConfiguration configuration;

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {
		String cmd = ParamUtil.getString(request, "cmd");
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		if (cmd.equals("update")) {
			// Catégories
			String categoriesIds = ParamUtil.getString(request,
				"categoriesIds");
			// On enregistre les ids des catégories sous forme de String
			// On sépare les catégories d'un même vocabulaire par des virgules
			// et les vocabulaires par des points-virgules
			List<Long> vocabulariesIds = new ArrayList<Long>();
			for (String categoryIdStr : categoriesIds.split(",")) {
				Long categoryId = GetterUtil.getLong(categoryIdStr);
				if (categoryId > 0) {
					AssetCategory category = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(categoryId);
					if (category != null && !vocabulariesIds
						.contains(category.getVocabularyId())) {
						vocabulariesIds.add(category.getVocabularyId());
					}
				}
			}
			String sortedCategoriesIds = "";
			for (Long vocabularyId : vocabulariesIds) {
				if (sortedCategoriesIds.length() > 0) {
					sortedCategoriesIds += ";";
				}
				for (String categoryIdStr : categoriesIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil
							.fetchAssetCategory(categoryId);
						if (category != null
							&& vocabularyId == category.getVocabularyId()) {
							if (sortedCategoriesIds.length() > 0
								&& !sortedCategoriesIds.endsWith(";")) {
								sortedCategoriesIds += ",";
							}
							sortedCategoriesIds += categoryId;
						}
					}
				}
			}
			setPreference(request, "categoriesIds", sortedCategoriesIds);

			// Tags
			String tagsNames = ParamUtil.getString(request, "tagsNames");
			setPreference(request, "tagsNames", tagsNames);

			// Dates
			int fromDay = ParamUtil.getInteger(request, "fromDay");
			int fromMonth = ParamUtil.getInteger(request, "fromMonth");
			int fromYear = ParamUtil.getInteger(request, "fromYear");
			int toDay = ParamUtil.getInteger(request, "toDay");
			int toMonth = ParamUtil.getInteger(request, "toMonth");
			int toYear = ParamUtil.getInteger(request, "toYear");
			boolean hasDates = true;
			LocalDate fromDate = null;
			LocalDate toDate = null;
			if (fromDay + fromMonth + fromYear + toDay + toMonth
				+ toYear == 0) {
				hasDates = false;
			}
			if (hasDates) {
				fromDate = LocalDate.of(fromYear, fromMonth + 1, fromDay);
				toDate = LocalDate.of(toYear, toMonth + 1, toDay);
				setPreference(request, "fromDate", fromDate.toString());
				setPreference(request, "toDate", toDate.toString());
			} else {
				setPreference(request, "fromDate", "");
				setPreference(request, "toDate", "");
			}

			// Page d'agenda
			String agendaPageUuid = ParamUtil.getString(request,
				"agendaPageUuid");
			setPreference(request, "agendaPageUuid", agendaPageUuid);

			// Texte du lien de la page d'agenda
			String agendaURLText = ParamUtil.getString(request,
				"agendaURLText");
			setPreference(request, "agendaURLText", agendaURLText);

			// URL de la page d'agenda
			String agendaURL = "";
			Layout agendaLayout = LayoutLocalServiceUtil
				.fetchLayoutByUuidAndGroupId(agendaPageUuid,
					themeDisplay.getScopeGroupId(), false);
			if (agendaLayout != null) {
				String queryString = "p_p_id=eu_strasbourg_portlet_search_asset_SearchAssetPortlet"
					+ "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_className=eu.strasbourg.service.agenda.model.Event"
					+ "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_vocabulariesCount="
					+ categoriesIds.split(",").length;

				// Catégories
				for (String categoryId : categoriesIds.split(",")) {
					if (Validator.isNotNull(categoryId)) {
						queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_vocabulary_0="
							+ categoryId;
					}
				}

				// Dates
				if (hasDates) {
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_fromDay="
						+ fromDate.getDayOfMonth();
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_fromMonth="
						+ (fromDate.getMonth().getValue() - 1);
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_fromYear="
						+ fromDate.getYear();
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_toDay="
						+ toDate.getDayOfMonth();
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_toMonth="
						+ (toDate.getMonth().getValue() - 1);
					queryString += "&_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_toYear="
						+ toDate.getYear();
				}

				agendaURL = "/web" + agendaLayout.getGroup().getFriendlyURL()
					+ agendaLayout.getFriendlyURL();
				agendaURL += "?" + queryString;
				setPreference(request, "agendaURL", agendaURL);
			}

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
			String portletResource = ParamUtil.getString(request,
				"portletResource");
			PortletPreferences preferences = PortletPreferencesFactoryUtil
				.getPortletSetup(request, portletResource);
			this.configuration = themeDisplay.getPortletDisplay()
				.getPortletInstanceConfiguration(
					EventViewerConfiguration.class);

			// Page d'agenda
			request.setAttribute("agendaPageUuid",
				configuration.agendaPageUuid());
			request.setAttribute("agendaURLText",
				configuration.agendaURLText());

			// Tout ce qui est Application Display Template
			String className = AssetEntry.class.getName();
			request.setAttribute("className", className);
			String displayStyle = GetterUtil.getString(
				preferences.getValue("displayStyle", StringPool.BLANK));
			request.setAttribute("displayStyle", displayStyle);
			long displayStyleGroupId = GetterUtil
				.getLong(preferences.getValue("displayStyleGroupId", null), 0);
			String refreshURL = PortalUtil.getCurrentURL(request);
			request.setAttribute("displayStyleGroupId", displayStyleGroupId);
			request.setAttribute("refreshURL", refreshURL);

			// Dates
			request.setAttribute("toDay", this.getToDate().getDayOfMonth());
			request.setAttribute("toMonth", this.getToDate().getMonthValue());
			request.setAttribute("toYear", this.getToDate().getYear());
			request.setAttribute("fromDay", this.getFromDate().getDayOfMonth());
			request.setAttribute("fromMonth",
				this.getFromDate().getMonthValue());
			request.setAttribute("fromYear", this.getFromDate().getYear());
			if (Validator.isNull(this.configuration.fromDate())
				&& Validator.isNull(this.configuration.toDate())) {
				request.setAttribute("hasDates", false);
			} else {
				request.setAttribute("hasDates", true);
			}

			// Catégories
			request.setAttribute("categoriesIds",
				configuration.categoriesIds().replace(";", ","));

			// Tags
			request.setAttribute("tagsNames", configuration.tagsNames());

		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private LocalDate getFromDate() {
		String dateString = this.configuration.fromDate();
		if (Validator.isNull(dateString)) {
			return LocalDate.now();
		}
		return LocalDate.parse(dateString);
	}

	private LocalDate getToDate() {
		String dateString = this.configuration.toDate();
		if (Validator.isNull(dateString)) {
			return LocalDate.now();
		}
		return LocalDate.parse(dateString);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

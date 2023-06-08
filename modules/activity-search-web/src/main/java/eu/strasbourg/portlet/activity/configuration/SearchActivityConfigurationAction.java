package eu.strasbourg.portlet.activity.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

@Component(
	configurationPid = "eu.strasbourg.portlet.activity.configuration.SearchActivityConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_SEARCH_WEB },
	service = ConfigurationAction.class)
public class SearchActivityConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		request.setAttribute("test", "test");
		return "/configuration/search-activity-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		if (ParamUtil.getString(actionRequest, "cmd").equals("update")) {

			// Affichage
			String template = ParamUtil.getString(actionRequest, "template");
			setPreference(actionRequest, "template", template);
			
			// Page de détail
			String detailPageUuid = ParamUtil.getString(actionRequest,
				"detailPageUuid");
			setPreference(actionRequest, "detailPageUuid", detailPageUuid);

			// Page de détail des cours
			String courseDetailPageUuid = ParamUtil.getString(actionRequest,
				"courseDetailPageUuid");
			setPreference(actionRequest, "courseDetailPageUuid", courseDetailPageUuid);

			// Types d'activités
			saveVocabularyPreference("activityType", actionRequest);

			// Types de cours
			saveVocabularyPreference("courseType", actionRequest);

			// Public
			saveVocabularyPreference("public", actionRequest);

			// Territoire
			saveVocabularyPreference("territory", actionRequest);

			// Texte
			Map<Locale, String> textMap = LocalizationUtil
				.getLocalizationMap(actionRequest, "text");
			LocalizedValuesMap textLocalizedValuesMap = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : textMap.entrySet()) {
				textLocalizedValuesMap.put(e.getKey(), e.getValue());
			}
			String textXML = LocalizationUtil.getXml(textLocalizedValuesMap,
				"Text");
			setPreference(actionRequest, "textXML", textXML);

		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	private void saveVocabularyPreference(String vocabularyParamName,
		ActionRequest actionRequest) {
		long[] activityTypesIds = ParamUtil.getLongValues(actionRequest,
			vocabularyParamName + "Ids");
		String[] activityTypesIdsString = new String[activityTypesIds.length];
		String[] activityTypesUuids = new String[activityTypesIds.length];
		String[] activityTypesNames = new String[activityTypesIds.length];
		for (int i = 0; i < activityTypesIds.length; i++) {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(activityTypesIds[i]);
			if (assetCategory != null) {
				activityTypesIdsString[i] = String
					.valueOf(assetCategory.getCategoryId());
				activityTypesUuids[i] = assetCategory.getUuid();
				activityTypesNames[i] = assetCategory.getName();
			}
		}
		setPreference(actionRequest, vocabularyParamName + "Uuids",
			StringUtil.merge(activityTypesUuids));
		setPreference(actionRequest, vocabularyParamName + "Names",
			StringUtil.merge(activityTypesNames, "_CATEGORY_") + "_CATEGORY_");
		setPreference(actionRequest, vocabularyParamName + "Ids",
			StringUtil.merge(activityTypesIdsString));
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			SearchActivityConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					SearchActivityConfiguration.class);

			// Page de détail
			request.setAttribute("detailPageUuid",
				configuration.detailPageUuid());
			
			// Page de détail des cours
			request.setAttribute("courseDetailPageUuid",
				configuration.courseDetailPageUuid());

			// Type d'activité
			setVocabularyAttributes("activityType",
				VocabularyNames.ACTIVITY_TYPE, configuration.activityTypeIds(),
				configuration.activityTypeNames(), request,
				themeDisplay.getScopeGroupId());
			// Type de cours
			setVocabularyAttributes("courseType",
				VocabularyNames.ACTIVITY_COURSE_TYPE,
				configuration.courseTypeIds(), configuration.courseTypeNames(),
				request, themeDisplay.getScopeGroupId());
			// Public
			setVocabularyAttributes("public",
				VocabularyNames.ACTIVITY_COURSE_PUBLIC,
				configuration.publicIds(), configuration.publicNames(), request,
				themeDisplay.getScopeGroupId());
			// Territoires
			setVocabularyAttributes("territory", VocabularyNames.TERRITORY,
				configuration.territoryIds(), configuration.territoryNames(),
				request, themeDisplay.getCompanyGroupId());

			// Texte
			request.setAttribute("textXML", configuration.textXML());

			// Affichage
			request.setAttribute("template", configuration.template());
			
			// Tout ce qui est Application Display Template
			String portletResource = ParamUtil.getString(request,
				"portletResource");
			PortletPreferences preferences = PortletPreferencesFactoryUtil
				.getPortletSetup(request, portletResource);
			String displayStyle = GetterUtil.getString(
				preferences.getValue("displayStyle", StringPool.BLANK));
			long displayStyleGroupId = GetterUtil
				.getLong(preferences.getValue("displayStyleGroupId", null), 0);
			String refreshURL = PortalUtil.getCurrentURL(request);
			request.setAttribute("displayStyle", displayStyle);
			request.setAttribute("displayStyleGroupId", displayStyleGroupId);
			request.setAttribute("refreshURL", refreshURL);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private void setVocabularyAttributes(String vocabularyParamName,
		String vocabularyName, String values, String names,
		HttpServletRequest request, long groupId) {
		request.setAttribute(vocabularyParamName + "Ids", values);
		request.setAttribute(vocabularyParamName + "Names",
			HtmlUtil.escape(names));
		AssetVocabulary vocabulary = AssetVocabularyHelper
			.getVocabulary(vocabularyName, groupId);
		if (vocabulary != null) {
			request.setAttribute(vocabularyParamName + "VocabularyId",
				vocabulary.getVocabularyId());
		}

	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

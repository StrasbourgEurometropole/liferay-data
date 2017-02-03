package eu.strasbourg.portlet.formassembly.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.formassembly.FormAssemblyForm;
import eu.strasbourg.portlet.formassembly.FormAssemblyUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=eu_strasbourg_portlet_formassembly_FormAssemblyPortlet" },
	service = ConfigurationAction.class)
public class FormAssemblyConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/formassembly-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		PortletInstance portletInstance = PortletInstance
			.fromPortletInstanceKey(StrasbourgPortletKeys.FORMASSEMBLY_WEB);
		FormAssemblyConfiguration configuration = _configurationProvider
			.getPortletInstanceConfiguration(FormAssemblyConfiguration.class,
				themeDisplay.getLayout(), portletInstance);

		Map<Locale, String> formIdMap = LocalizationUtil
			.getLocalizationMap(actionRequest, "formId");
		String formId = LocalizationUtil.updateLocalization(formIdMap,
			configuration.formId(), "formId",
			themeDisplay.getSiteDefaultLocale().toString());
		setPreference(actionRequest, "formId", formId);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			PortletInstance portletInstance = PortletInstance
				.fromPortletInstanceKey(StrasbourgPortletKeys.FORMASSEMBLY_WEB);
			FormAssemblyConfiguration configuration = _configurationProvider
				.getPortletInstanceConfiguration(
					FormAssemblyConfiguration.class, themeDisplay.getLayout(),
					portletInstance);

			// Liste des langues disponibles
			Set<Locale> availableLocalesSet = LanguageUtil
				.getAvailableLocales(themeDisplay.getScopeGroupId());
			Locale[] availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
			request.setAttribute("availableLocales", availableLocales);

			// IDs des formulaires sélectionnés, par langue
			String formIdXml = configuration.formId();
			Map<Locale, String> formIdMap = LocalizationUtil
				.getLocalizationMap(formIdXml);
			request.setAttribute("formIdMap", formIdMap);

			// Liste des formulaires
			FormAssemblyUtil formAssemblyUtil = new FormAssemblyUtil(
				configuration.path(), configuration.token());
			JSONObject webServiceResponse = formAssemblyUtil.getFormsListJson();

			List<FormAssemblyForm> forms = new ArrayList<FormAssemblyForm>();

			// Formulaires sans catégorie
			JSONArray jsonForms = webServiceResponse.getJSONArray("Forms");
			formAssemblyUtil.addFormsToListFromJsonArray(forms, jsonForms);

			// Formulaires avec catégorie
			JSONArray jsonCategories = webServiceResponse
				.getJSONArray("Category");
			if (jsonCategories != null) {
				for (int i = 0; i < jsonCategories.length(); i++) {
					JSONObject jsonCategory = jsonCategories.getJSONObject(i);

					// Formulaires sans sous-catégorie
					JSONArray jsonFormsWithCategory = jsonCategory
						.getJSONArray("Forms");
					formAssemblyUtil.addFormsToListFromJsonArray(forms,
						jsonFormsWithCategory);

					// Formulaires avec sous-catégorie
					JSONArray jsonSubCategories = jsonCategory
						.getJSONArray("Category");
					if (jsonSubCategories != null) {
						for (int j = 0; j < jsonSubCategories.length(); j++) {
							JSONObject jsonSubCategory = jsonSubCategories
								.getJSONObject(i);
							JSONArray jsonFormsWithSubCategory = jsonSubCategory
								.getJSONArray("Forms");
							formAssemblyUtil.addFormsToListFromJsonArray(forms,
								jsonFormsWithSubCategory);
						}
					}
				}
			}

			request.setAttribute("forms", forms);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	ConfigurationProvider _configurationProvider;

	@Reference
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {
		_configurationProvider = configurationProvider;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

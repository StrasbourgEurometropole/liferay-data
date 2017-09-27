package eu.strasbourg.portlet.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search.configuration.ExperimentalSearchConfiguration;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.search.configuration.ExperimentalSearchConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=experimental-search-portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/experimental-search-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ExperimentalSearchPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			ExperimentalSearchConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ExperimentalSearchConfiguration.class);

			// Template
			String template = configuration.template();
			renderRequest.setAttribute("template", template);

			// Friendly URL de la page agenda
			String agendaFriendlyURL = configuration.agendaFriendlyURL();
			renderRequest.setAttribute("agendaFriendlyURL", agendaFriendlyURL);

			// Critère 1
			String criteria1 = LocalizationUtil.getLocalization(configuration.criteria1(),
					LocaleUtil.toLanguageId(themeDisplay.getLocale()));
			renderRequest.setAttribute("criteria1", criteria1);
			List<String> criteria1Options = new ArrayList<String>();
			for (String xml : configuration.criteria1Options()) {
				String option = LocalizationUtil.getLocalization(xml,
						LocaleUtil.toLanguageId(themeDisplay.getLocale()));
				if (Validator.isNotNull(option)) {
					criteria1Options.add(option);
				}
			}
			renderRequest.setAttribute("criteria1Options", criteria1Options);

			// Critère 2
			String criteria2 = LocalizationUtil.getLocalization(configuration.criteria2(),
					LocaleUtil.toLanguageId(themeDisplay.getLocale()));
			renderRequest.setAttribute("criteria2", criteria2);

			List<String> criteria2Options = new ArrayList<String>();
			for (String xml : configuration.criteria2Options()) {
				String option = LocalizationUtil.getLocalization(xml,
						LocaleUtil.toLanguageId(themeDisplay.getLocale()));
				if (Validator.isNotNull(option)) {
					criteria2Options.add(option);
				}
			}
			renderRequest.setAttribute("criteria2Options", criteria2Options);

		} catch (ConfigurationException e) {
			log.error(e);
		}

		super.render(renderRequest, renderResponse);
	}

}
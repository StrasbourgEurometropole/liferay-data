package eu.strasbourg.portlet.contact;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.contact.configuration.ContactFormConfiguration;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.contact.configuration.ContactFormConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=contact-form-portlet", "javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.footer-portlet-javascript=https://www.google.com/recaptcha/api.js",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ContactFormPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			ContactFormConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ContactFormConfiguration.class);

			// Titre du formulaire
			String title = LocalizationUtil.getLocalization(configuration.title(),
					LocaleUtil.toLanguageId(themeDisplay.getLocale()));
			renderRequest.setAttribute("title", title);

			// Template sélectionné
			String template = configuration.template();

			// Email du destinataire
			String email = configuration.email();

			// Texte de descriptipon
			String descriptionText = LocalizationUtil.getLocalization(configuration.descriptionText(),
					LocaleUtil.toLanguageId(themeDisplay.getLocale()));
			renderRequest.setAttribute("descriptionText", descriptionText);

			// Texte de "confidentialité"
			String privacyText = LocalizationUtil.getLocalization(configuration.privacyText(),
					LocaleUtil.toLanguageId(themeDisplay.getLocale()));
			if (Validator.isNull(privacyText)) {
				privacyText = LanguageUtil.get(PortalUtil.getHttpServletRequest(renderRequest), "contact.default-privacy");
			}
			renderRequest.setAttribute("privacyText", privacyText);
			
			// Clé recaptcha
			String recaptchaKey = StrasbourgPropsUtil.getRecaptchaPublicKey();
			renderRequest.setAttribute("recaptchaKey", recaptchaKey);

			if (Validator.isNull(email) || Validator.isNull(template)) {
				include("/no-config.jsp", renderRequest, renderResponse);
			} else {
				include("/templates/" + template + ".jsp", renderRequest, renderResponse);
			}

		} catch (ConfigurationException e) {
			log.error(e);
		}
	}

}
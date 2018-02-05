package eu.strasbourg.portlet.contact.action;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.contact.configuration.ContactFormConfiguration;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.RecaptchaHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.CONTACT_FORM_WEB,
		"mvc.command.name=contact" }, service = MVCActionCommand.class)
public class ContactFormContactAction implements MVCActionCommand {

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		ContactFormConfiguration portletConfiguration = null;
		try {
			portletConfiguration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(ContactFormConfiguration.class);
		} catch (ConfigurationException e) {
			SessionErrors.add(request, "unknown-error");
			return false;
		}

		// On récupère les informations du mail à envoyer
		String emailFrom = ParamUtil.getString(request, "emailFrom");
		String emailTo = portletConfiguration.email();
		String content = ParamUtil.getString(request, "content");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");

		// Validation
		boolean hasError = false;
		String gRecaptchaResponse = ParamUtil.getString(request, "g-recaptcha-response");
		if (!RecaptchaHelper.verify(gRecaptchaResponse)) {
			// Recaptcha
			SessionErrors.add(request, "recaptcha-error");
			hasError = true;
		}
		if (Validator.isNull(emailFrom)) {
			// Email obligatoires
			SessionErrors.add(request, "email-error");
			hasError = true;
		}
		if (Validator.isNull(firstName)) {
			// Prénom obligatoires
			SessionErrors.add(request, "firstname-error");
			hasError = true;
		}
		if (Validator.isNull(lastName)) {
			// Nom obligatoires
			SessionErrors.add(request, "lastname-error");
			hasError = true;
		}
		if (Validator.isNull(content)) {
			// Message obligatoires
			SessionErrors.add(request, "content-error");
			hasError = true;
		}
		if (!Validator.isEmailAddress(emailFrom)) {
			// Validité de l'adresse mail
			SessionErrors.add(request, "invalid-mail");
			hasError = true;
		}
		if (hasError) {
			return false;
		}

		// Envoi du mail au service
		Map<String, Object> context = new HashMap<>();
		context.put("website", themeDisplay.getScopeGroup().getName(request.getLocale()));
		String formName = LocalizationUtil.getLocalization(portletConfiguration.title(),
				LocaleUtil.toLanguageId(themeDisplay.getLocale()));
		context.put("formName", formName);
		context.put("firstName", firstName);
		context.put("lastName", lastName);
		context.put("content", content);
		context.put("emailFrom", emailFrom);

		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
		context.put("date", date);
		context.put("time", time);

		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
		configuration.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
		boolean success = false;
		try {
			Template subjectTemplate = configuration.getTemplate("contact-mail-subject.ftl");
			Template bodyTemplate = configuration.getTemplate("contact-mail-body.ftl");
			StringWriter subjectWriter = new StringWriter();
			StringWriter bodyWriter = new StringWriter();
			subjectTemplate.process(context, subjectWriter);
			bodyTemplate.process(context, bodyWriter);

			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
					themeDisplay.getScopeGroup().getName(request.getLocale()));

			InternetAddress[] toAddresses = new InternetAddress[0];
			for (String toAddress : emailTo.split(",")) {
				try {
					InternetAddress address = new InternetAddress(toAddress);
					toAddresses = ArrayUtil.append(toAddresses, address);
				} catch (AddressException ex) {
					log.error(ex);
				}
			}
			success = MailHelper.sendMailWithHTML(fromAddress, toAddresses, subjectWriter.toString(), bodyWriter.toString());
		} catch (Exception e) {
			log.error(e);
		}
		if (success) {
			SessionMessages.add(request, "mail-success");
		} else {
			SessionErrors.add(request, "unknown-error");
			return false;
		}

		// Envoi du mail au destinataire
		boolean sendCopy = ParamUtil.getBoolean(request, "sendCopy");
		if (sendCopy) {
			ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
			try {
				String headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
				String footerImage = GetterUtil.getString(ed.getAttribute("image_footer_mail_contact"));
				context.put("headerImage", headerImage);
				context.put("footerImage", footerImage);
			} catch (Exception ex) {
				log.error("Missing expando field");
			}

			try {
				String locale = request.getLocale().toString();
				if (!locale.equals("fr_FR") && !locale.equals("en_US") && !locale.equals("de_DE")) {
					locale = "fr_FR";
				}
				Template subjectTemplate = configuration
						.getTemplate("contact-mail-copy-subject-" + request.getLocale().toString() + ".ftl");
				Template bodyTemplate = configuration
						.getTemplate("contact-mail-copy-body-" + request.getLocale().toString() + ".ftl");
				StringWriter subjectWriter = new StringWriter();
				StringWriter bodyWriter = new StringWriter();
				subjectTemplate.process(context, subjectWriter);
				bodyTemplate.process(context, bodyWriter);
				InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
						themeDisplay.getScopeGroup().getName(request.getLocale()));
				InternetAddress to = new InternetAddress(emailFrom);
				InternetAddress[] toAddresses = new InternetAddress[] { to };
				MailHelper.sendMailWithHTML(fromAddress, toAddresses, subjectWriter.toString(), bodyWriter.toString());

			} catch (Exception e) {
				log.error(e);
			}
		}

		// Redirection (évite double requête POST si l'utilisateur actualise sa
		// page)
		String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
		PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
		renderUrl.setParameter("mailSent", "true");
		try {
			response.sendRedirect(renderUrl.toString());
		} catch (IOException e) {
			log.error(e);
		}
		return true;
	}

}

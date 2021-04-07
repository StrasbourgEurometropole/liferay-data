/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.entity_detail.action;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.RecaptchaHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + StrasbourgPortletKeys.ENTITY_DETAIL_WEB,
				"mvc.command.name=contact"
		},
		service = MVCActionCommand.class
)
public class EntityDetailContactActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		request.setAttribute("fromContactForm", true);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		Map<String, Object> context = new HashMap<>();
		
		String type = ParamUtil.getString(request, "type");
		String email = ParamUtil.getString(request, "email");
		String to = ParamUtil.getString(request, "to");
		String title = ParamUtil.getString(request, "title");
		String message = ParamUtil.getString(request, "message");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String websiteName = themeDisplay.getScopeGroup().getName(request.getLocale());
		boolean notificationEmail = ParamUtil.getBoolean(request, "notificationEmail");
		
		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
		context.put("date", date);
		context.put("time", time);
		context.put("website", websiteName);
		context.put("firstName", firstName);
		context.put("lastName", lastName);
		context.put("content", message);
		context.put("emailFrom", email);
		context.put("type", type);
		context.put("title", title);

		TemplateResource templateResourceSubject;
		Template subjectTemplate;
		TemplateResource templateResourceBody;
		Template bodyTemplate;
		String mailSubject;
		String mailBody;

		try {
			StringWriter out = new StringWriter();

			// Chargement du template contenant le sujet du mail
			templateResourceSubject = new URLTemplateResource("0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/contact-mail-subject.ftl")));
			subjectTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

			// Traitement du template sujet
			subjectTemplate.putAll(context);
			subjectTemplate.processTemplate(out);
			mailSubject = out.toString();

			//Chargement du template contenant le corps du mail
			templateResourceBody = new URLTemplateResource("0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/contact-mail-body.ftl")));
			bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			mailBody = out.toString();

			// Validation
			String gRecaptchaResponse = ParamUtil.getString(request, "g-recaptcha-response");
			boolean hasError = false;
			if (!RecaptchaHelper.verify(gRecaptchaResponse)) { // Captcha
				SessionErrors.add(request, "recaptcha-error");
				hasError = true;
			}
			// Champs vides
			if (Validator.isNull(email) || Validator.isNull(to) || Validator.isNull(firstName)
					|| Validator.isNull(lastName) || Validator.isNull(message)) { 
				SessionErrors.add(request, "all-fields-required");
				hasError = true;
			}
			// Mail invalide
			if (!Validator.isEmailAddress(email)) {
				SessionErrors.add(request, "invalid-mail");
				hasError = true;
			}
			// Pas d'erreur
			if (!hasError) {
				SessionMessages.add(request, "mail-success");
				request.setAttribute("mailSent", true);
				boolean success = MailHelper.sendMailWithHTML("no-reply@no-reply.strasbourg.eu", websiteName, to,
						mailSubject, mailBody);
	
				// Envoi du mail à l'utilisateur
				if (success && notificationEmail) {
					ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
					String headerImage;
					String footerImage;
					try {
						headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
						footerImage = GetterUtil.getString(ed.getAttribute("image_footer_mail_contact"));
						context.put("headerImage", headerImage);
						context.put("footerImage", footerImage);
					} catch (Exception ex) {
						_log.error("Missing expando field");
					}

					// Chargement du template contenant le sujet du mail
					templateResourceSubject = new URLTemplateResource("0",
							Objects.requireNonNull(this.getClass().getClassLoader()
									.getResource("/templates/contact-mail-copy-subject.ftl")));
					subjectTemplate = TemplateManagerUtil.getTemplate(
							TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

					// Traitement du template sujet
					out = new StringWriter();
					subjectTemplate.putAll(context);
					subjectTemplate.processTemplate(out);
					mailSubject = out.toString();

					//Chargement du template contenant le corps du mail
					templateResourceBody = new URLTemplateResource("0",
							Objects.requireNonNull(this.getClass().getClassLoader()
									.getResource("/templates/contact-mail-copy-body.ftl")));
					bodyTemplate = TemplateManagerUtil.getTemplate(
							TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

					// Traitement du template corps
					out = new StringWriter();
					bodyTemplate.putAll(context);
					bodyTemplate.processTemplate(out);
					mailBody = out.toString();
					
					MailHelper.sendMailWithHTML("no-reply@no-reply.strasbourg.eu", websiteName, email,
							mailSubject, mailBody);
				}
				return success;
			}
		} catch (Exception e) {
			_log.error(e);
		}

		// On renvoie pour l'affichage
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("email", email);
		request.setAttribute("message", message);
		request.setAttribute("message", message);
		request.setAttribute("notificationEmail", notificationEmail);
		request.setAttribute("mailSent", false);

		return false;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
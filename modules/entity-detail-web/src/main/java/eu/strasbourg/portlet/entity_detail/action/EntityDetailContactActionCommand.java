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

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.RecaptchaHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.ENTITY_DETAIL_WEB,
		"mvc.command.name=contact" }, service = MVCActionCommand.class)
public class EntityDetailContactActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		request.setAttribute("fromContactForm", true);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
		configuration.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
		
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
		try {
			Template subjectTemplate = configuration.getTemplate("contact-mail-subject.ftl");
			Template bodyTemplate = configuration.getTemplate("contact-mail-body.ftl");
			StringWriter subjectWriter = new StringWriter();
			StringWriter bodyWriter = new StringWriter();
			subjectTemplate.process(context, subjectWriter);
			bodyTemplate.process(context, bodyWriter);
			
			// Validation
			String gRecaptchaResponse = ParamUtil.getString(request, "g-recaptcha-response");
			boolean hasError = false;
		/*	if (!RecaptchaHelper.verify(gRecaptchaResponse)) { // Captcha
				SessionErrors.add(request, "recaptcha-error");
				hasError = true;
			}
		*/	// Champs vides
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
				boolean success = MailHelper.sendMailWithHTML("no-reply@no-reply.strasbourg.eu", websiteName, to, subjectWriter.toString(), bodyWriter.toString());
	
				// Envoi du mail à l'utilisateur
				if (success && notificationEmail) {
					ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
					String headerImage = "";
					String footerImage = "";
					try {
						headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
						footerImage = GetterUtil.getString(ed.getAttribute("image_footer_mail_contact"));
						context.put("headerImage", headerImage);
						context.put("footerImage", footerImage);
					} catch (Exception ex) {
						_log.error("Missing expando field");
					}
	
					Template subjectCopyTemplate = configuration
							.getTemplate("contact-mail-copy-subject.ftl");
					Template bodyCopyTemplate = configuration
							.getTemplate("contact-mail-copy-body.ftl");
					StringWriter subjectCopyWriter = new StringWriter();
					StringWriter bodyCopyWriter = new StringWriter();
					subjectCopyTemplate.process(context, subjectCopyWriter);
					bodyCopyTemplate.process(context, bodyCopyWriter);
					
					MailHelper.sendMailWithHTML("no-reply@no-reply.strasbourg.eu", websiteName, email, subjectCopyWriter.toString(), bodyCopyWriter.toString());
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
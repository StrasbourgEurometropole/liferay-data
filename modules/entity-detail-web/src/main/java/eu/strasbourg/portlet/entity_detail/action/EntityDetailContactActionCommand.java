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

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.ENTITY_DETAIL_WEB,
		"mvc.command.name=contact" }, service = MVCActionCommand.class)
public class EntityDetailContactActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
		String email = ParamUtil.getString(request, "email");
		String to = ParamUtil.getString(request, "to");
		String subject = ParamUtil.getString(request, "subject");
		String message = ParamUtil.getString(request, "message");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		boolean notificationEmail = ParamUtil.getBoolean(request, "notificationEmail");
		String body = "Prenom : " + firstName + "\n";
		body += "Nom : " + lastName + "\n";
		body += "Mail : " + email + "\n";
		body += "Message : " + message;

		// Validation
		String gRecaptchaResponse = ParamUtil.getString(request, "g-recaptcha-response");
		boolean hasError = false;
		if (!RecaptchaHelper.verify(gRecaptchaResponse)) { // Captcha
			SessionErrors.add(request, "recaptcha-error");
			hasError = true;
		}
		// Champs vides
		if (Validator.isNull(email) || Validator.isNull(to) || Validator.isNull(subject) || Validator.isNull(firstName)
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
			boolean success = MailHelper.sendMailWithPlainText(email, to, subject, body);

			// Envoi du mail à l'utilisateur
			if (success && notificationEmail) {
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
				String notificationSubject = "";
				String headerImage = "";
				String headerText = "";
				String footerText = "";
				String footerImage = "";
				try {
					notificationSubject = GetterUtil.getString(ed.getAttribute("subject_mail_contact"));
					headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
					headerText = GetterUtil.getString(ed.getAttribute("text_header_mail_contact"));
					footerText = GetterUtil.getString(ed.getAttribute("text_footer_mail_contact"));
					footerImage = GetterUtil.getString(ed.getAttribute("image_footer_mail_contact"));
				} catch (Exception ex) {
					_log.error("Missing expando field");
				}

				body = "<p>" + headerText + "</p>" + body.replace("\n", "<br>") + "<p>" + footerText + "</p>";
				if (Validator.isUrl(headerImage)) {
					body = "<p><img src='" + headerImage + "' /></p>" + body;
				}
				if (Validator.isUrl(footerImage)) {
					body = body + "<p><img src='" + footerImage + "' /></p>";
				}
				MailHelper.sendMailWithHTML("no-reply@no-reply.strasbourg.eu", email, notificationSubject, body);
			}
			return success;
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
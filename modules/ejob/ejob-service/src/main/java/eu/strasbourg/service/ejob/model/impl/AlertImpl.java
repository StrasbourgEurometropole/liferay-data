/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.ejob.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The extended model implementation for the Alert service. Represents a row in the &quot;ejob_Alert&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.model.Alert<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AlertImpl extends AlertBaseImpl {

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a alert model instance should use the {@link eu.strasbourg.service.ejob.model.Alert} interface instead.
	 */
	public AlertImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Alert.class.getName(),
				this.getAlertId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
				.getAssetEntryCategories(this.getAssetEntry());
	}

	public boolean sendMail(List<Offer> listOffer, ActionRequest request, ActionResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		// On récupère les informations du mail à envoyer
		User alertUser = null;
		try {
			alertUser = UserServiceUtil.getUserById(this.getUserId());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		String emailTo = alertUser.getDisplayEmailAddress();
		String subject = "\"" + this.getName()+ "\" : " + listOffer.size() + "nouvelle(s) offre(s)";


		// Validation
		boolean hasError = false;
		if (hasError) {
			return false;
		}

		// Envoi du mail au service
		Map<String, Object> context = new HashMap<>();
		context.put("website", themeDisplay.getScopeGroup().getName(request.getLocale()));
		context.put("content", listOffer);
		if (subject != null && !subject.isEmpty())
			context.put("subject", subject);

		LocalDateTime dateTime = LocalDateTime.now();
		String date = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
		context.put("date", date);
		context.put("time", time);

		TemplateResource templateResourceSubject;
		TemplateResource templateResourceBody;
		Template subjectTemplate;
		Template bodyTemplate;
		String mailSubject;
		String mailBody;
		StringWriter out;

		boolean success = false;
		try {

			// Chargement du template contenant le sujet du mail
			templateResourceSubject = new URLTemplateResource("0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/alert-mail-subject.ftl")));
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
							.getResource("/templates/alert-mail-body.ftl")));
			bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			mailBody = out.toString();

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
			success = MailHelper.sendMailWithHTML(fromAddress, toAddresses, mailSubject, mailBody);
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

				// Chargement du template contenant le sujet du mail
				templateResourceSubject = new URLTemplateResource("0",
						Objects.requireNonNull(this.getClass().getClassLoader()
								.getResource("/templates/alert-mail-copy-subject-" + request.getLocale().toString() + ".ftl")));
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
								.getResource("/templates/alert-mail-copy-body-" + request.getLocale().toString() + ".ftl")));
				bodyTemplate = TemplateManagerUtil.getTemplate(
						TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

				// Traitement du template corps
				out = new StringWriter();
				bodyTemplate.putAll(context);
				bodyTemplate.processTemplate(out);
				mailBody = out.toString();

				InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
						themeDisplay.getScopeGroup().getName(request.getLocale()));
				InternetAddress to = new InternetAddress(emailTo);
				InternetAddress[] toAddresses = new InternetAddress[]{to};
				MailHelper.sendMailWithHTML(fromAddress, toAddresses, mailSubject, mailBody);

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
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
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import org.osgi.service.component.annotations.Reference;

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
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

	public boolean sendMail(List<Offer> listOffer) {
		Locale locale;
		if (!this.getLanguage().equals("fr") && !this.getLanguage().equals("en") && !this.getLanguage().equals("de")) {
			locale = Locale.FRANCE;
		}
		else{
			locale = LocaleUtil.fromLanguageId(this.getLanguage());
		}
		String localeString = locale.toString();

		// On récupère les informations du mail à envoyer
		PublikUser alertUser = PublikUserLocalServiceUtil.getByPublikUserId(this.getPublikUserId());
		String emailTo = alertUser.getEmail();
		String subject = "\"" + this.getName()+ "\" : " + listOffer.size() + " " + LanguageUtil.get(locale, "eu.alert-new-offers");

		// Validation
		boolean hasError = false;
		if (hasError) {
			return false;
		}

		// Envoi du mail au service
		Map<String, Object> context = new HashMap<>();
		context.put("content", listOffer);
		context.put("locale", locale);
		if (subject != null && !subject.isEmpty())
			context.put("subject", subject);

		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId() , "/strasbourg.eu");
		User admin = null;
		try {
			admin = UserLocalServiceUtil.getDefaultUser(group.getCompanyId());
		} catch (PortalException e) {
			e.printStackTrace();
		}
		PermissionChecker checker = PermissionCheckerFactoryUtil.create(admin);
		PermissionThreadLocal.setPermissionChecker(checker);
		ExpandoBridge ed = group.getExpandoBridge();
		try {
			String headerImage = GetterUtil.getString(ed.getAttribute("image_header_mail_contact"));
			String footerText = GetterUtil.getString(ed.getAttribute("text_footer_mail_contact"));
			context.put("headerImage", headerImage);
			context.put("footerText", footerText);
		} catch (Exception ex) {
			log.error("Missing expando field");
		}

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
							.getResource("/templates/alert-mail-copy-subject-" + localeString + ".ftl")));
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
							.getResource("/templates/alert-mail-copy-body-" + localeString + ".ftl")));
			bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			mailBody = out.toString();
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu");

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
		if (!success) {
			return false;
		}
		return true;
	}

}
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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The extended model implementation for the Offer service. Represents a row in the &quot;ejob_Offer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.model.Offer<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OfferImpl extends OfferBaseImpl {

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a offer model instance should use the {@link eu.strasbourg.service.ejob.model.Offer} interface instead.
	 */
	public OfferImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Offer.class.getName(),
				this.getOfferId());
	}

	/**
	 * Renvoie le type de recrutement
	 */
	@Override
	public AssetCategory getTypeRecrutement() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_TYPE_RECRUTEMENT);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AssetCategory getTypePublication(){
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_INTERNE_EXTERNE);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Retourne la direction
	 */
	@Override
	public AssetCategory getDirection() {
		for (AssetCategory category : AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_DIRECTION)) {
			if (category.getParentCategory() == null) {
				return category;
			}
		}

		return null;
	}

	/**
	 * Retourne le service
	 */
	@Override
	public AssetCategory getService() {
		for (AssetCategory category : AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_DIRECTION)) {
			if (category.getParentCategory() != null) {
				return category;
			}
		}

		return null;
	}

	/**
	 * Retourne le motif
	 */
	@Override
	public AssetCategory getMotif() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_MOTIF);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
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

	/**
	 * Renvoie les categories A, B ou C
	 */
	@Override
	public List<AssetCategory> getOfferCategories() {
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_CATEGORIES);
		Set withoutDupplicate = new HashSet(categories) ;
		return new ArrayList(withoutDupplicate) ;
	}

	/**
	 * Renvoie les grades
	 */
	@Override
	public List<AssetCategory> getGrades() {
		List<AssetCategory> grades = new ArrayList<>();
		for (AssetCategory grade : AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_FILIERES)) {
			if (grade.getParentCategory() != null && grade.getParentCategory().getParentCategory() != null) {
				grades.add(grade);
			}
		}
		return grades;
	}

	/**
	 * Récupère les catégories, filières et grades
	 */
	@SuppressWarnings("unused")
	public List<List> getGradeRanges() {
		List<List> gradeRangesList = new ArrayList<>();
		Map<Long, List> gradeRangesMap = new HashMap<>();
		for (AssetCategory grade : this.getGrades()) {
			AssetCategory categoryFiliere = grade.getParentCategory();
			List gradeRange = gradeRangesMap.remove(categoryFiliere.getCategoryId());
			if (Validator.isNotNull(gradeRange)) {
				AssetCategory firstGrade = (AssetCategory) gradeRange.get(2);
				int orderFirstGrade = 0;
				int orderLastGrade = 0;
				String orderFirstGradeString = AssetVocabularyHelper.getCategoryProperty(firstGrade.getCategoryId(), "order");
				if(Validator.isNotNull(orderFirstGradeString))
					orderFirstGrade = Integer.parseInt(orderFirstGradeString);
				String orderLastGradeString = AssetVocabularyHelper.getCategoryProperty(grade.getCategoryId(), "order");
				if(Validator.isNotNull(orderLastGradeString))
					orderLastGrade = Integer.parseInt(orderLastGradeString);
				if(orderFirstGrade > orderLastGrade)
					gradeRange.set(2, grade);
				else
					gradeRange.set(3, grade);
				gradeRangesList.add(gradeRange);
			} else {
				String categoryString = AssetVocabularyHelper.getCategoryProperty(categoryFiliere.getCategoryId(), "linked-category");
				Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId() , "/strasbourg.eu");
				AssetCategory category = AssetVocabularyHelper.getCategory(categoryString, group.getGroupId());
				gradeRange = new ArrayList();
				gradeRange.add(category);
				gradeRange.add(grade.getParentCategory().getParentCategory());
				gradeRange.add(grade);
				gradeRange.add(grade);
				gradeRangesMap.put(categoryFiliere.getCategoryId(), gradeRange);
			}
		}

		return gradeRangesList;
	}

	/**
	 * Renvoie le Niveau d'étude
	 */
	@Override
	public AssetCategory getNiveauEtude() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_NIVEAU_ETUDE);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Renvoie la Famille de métiers
	 */
	@Override
	public AssetCategory getFamille() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_FAMILLE);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Renvoie le contact RE
	 */
	@Override
	public AssetCategory getContactRE() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_CONTACT);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean sendMail() {
		// On récupère les informations du mail à envoyer
		String emailTo = this.getEmails();
		String subject = LanguageUtil.get(Locale.FRANCE, "eu.offer.subject-email-partner") + " : " + this.getPost(Locale.FRANCE);

		// Envoi du mail aux partenaires
		Map<String, Object> context = new HashMap<>();
		context.put("content", this);
		context.put("locale", Locale.FRANCE);
		context.put("urlOffer", StrasbourgPropsUtil.getEJobURLOffer());
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
							.getResource("/templates/offer-mail-subject.ftl")));
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
							.getResource("/templates/offer-mail-body.ftl")));
			bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			mailBody = out.toString();
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu");

			for (String toAddress : emailTo.split(",")) {
				boolean successSend = true;
				try {
					InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
					successSend = MailHelper.sendMailWithHTML(fromAddress, toAddresses, mailSubject, mailBody);
				} catch (AddressException ex) {
					log.error(ex);
				}
				success = success ? success : successSend;
			}
		} catch (Exception e) {
			log.error(e);
		}
		if (!success) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne la version JSON de l'offre
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonOffer = JSONFactoryUtil.createJSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		jsonOffer.put("publicationId", this.getPublicationId());
		jsonOffer.put("typeRecrutement", this.getTypeRecrutement()!=null?this.getTypeRecrutement().getTitle(Locale.getDefault()):"");
		jsonOffer.put("typePublication", this.getTypePublication()!=null?this.getTypePublication().getTitle(Locale.getDefault()):"");
		jsonOffer.put("postNumber", this.getPostNumber());
		jsonOffer.put("jobCreationDescription", this.getJobCreationDescription(Locale.getDefault()));
		String dateString = "";
		if(Validator.isNotNull(this.getStartDate()))
			dateString = sdf.format(this.getStartDate());
		jsonOffer.put("startDate", dateString);
		jsonOffer.put("motif", this.getMotif()!=null?this.getMotif().getTitle(Locale.getDefault()):"");
		jsonOffer.put("permanentDescription", this.getPermanentDescription(Locale.getDefault()));
		jsonOffer.put("duration", this.getDuration(Locale.getDefault()));
		jsonOffer.put("post", this.getPost(Locale.getDefault()));
		jsonOffer.put("direction", this.getDirection()!=null?this.getDirection().getTitle(Locale.getDefault()):"");
		jsonOffer.put("service", this.getService()!=null?this.getService().getTitle(Locale.getDefault()):"");
		jsonOffer.put("tempsTravail", this.getIsFullTime()?"complet":"non-complet");
		jsonOffer.put("description", this.getFullTimeDescription(Locale.getDefault()));
		int i = 1;
		for (List<AssetCategory> gradeRange : this.getGradeRanges()) {
			JSONArray jsonGrades = JSONFactoryUtil.createJSONArray();
			JSONObject jsonGrade = JSONFactoryUtil.createJSONObject();
			jsonGrade.put("categorie", gradeRange.get(0)!=null?gradeRange.get(0).getTitle(Locale.getDefault()):"");
			jsonGrade.put("filiere", gradeRange.get(1)!=null?gradeRange.get(1).getTitle(Locale.getDefault()):"");
			jsonGrade.put("gradeMin", gradeRange.get(2)!=null?gradeRange.get(2).getTitle(Locale.getDefault()):"");
			jsonGrade.put("gradeMax", gradeRange.get(3)!=null?gradeRange.get(3).getTitle(Locale.getDefault()):"");
			jsonGrades.put(jsonGrade);
			jsonOffer.put("grade" + i++, jsonGrades);
		}
		jsonOffer.put("niveauEtude", this.getNiveauEtude()!=null?this.getNiveauEtude().getTitle(Locale.getDefault()):"");
		jsonOffer.put("introduction", this.getIntroduction(Locale.getDefault()));
		jsonOffer.put("activities", this.getActivities(Locale.getDefault()));
		jsonOffer.put("profil", this.getProfil(Locale.getDefault()));
		jsonOffer.put("conditions", this.getConditions(Locale.getDefault()));
		jsonOffer.put("avantages", this.getAvantages(Locale.getDefault()));
		jsonOffer.put("famille", this.getFamille()!=null?this.getFamille().getTitle(Locale.getDefault()):"");
		dateString = sdf.format(this.getLimitDate());
		jsonOffer.put("limitDate", dateString);
		jsonOffer.put("nomRE", this.getContactRE()!=null?this.getContactRE().getTitle(Locale.getDefault()):"");
		jsonOffer.put("nomRRH", this.getContact());
		jsonOffer.put("emails", this.getEmails());
		jsonOffer.put("shareLinkedin", this.getShareLinkedin());
		dateString = sdf.format(this.getPublicationStartDate());
		jsonOffer.put("publicationStartDate", dateString);
		dateString = sdf.format(this.getPublicationEndDate());
		jsonOffer.put("publicationEndDate", dateString);

		return jsonOffer;
	}
}
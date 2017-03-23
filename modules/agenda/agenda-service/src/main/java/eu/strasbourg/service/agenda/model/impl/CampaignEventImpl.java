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

package eu.strasbourg.service.agenda.model.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.models.LegacyPlace;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * The extended model implementation for the CampaignEvent service. Represents a
 * row in the &quot;agenda_CampaignEvent&quot; database table, with each column
 * mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.CampaignEvent} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class CampaignEventImpl extends CampaignEventBaseImpl {

	private static final long serialVersionUID = -8073794358650836813L;
	private Map<Locale, LegacyPlace> locale_legacyPlace;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a campaign
	 * event model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.CampaignEvent} interface instead.
	 */
	public CampaignEventImpl() {
	}

	@Override
	public Campaign getCampaign() {
		return CampaignLocalServiceUtil.fetchCampaign(this.getCampaignId());
	}

	/**
	 * Retourne la liste de CampaignEventStatus, correspondant à l'historique
	 * des statuts de l'événement classé par ordre chronologique
	 */
	@Override
	public List<CampaignEventStatus> getStatusHistory() {
		List<CampaignEventStatus> statusHistory = CampaignEventStatusLocalServiceUtil
			.getByCampaignEvent(this.getCampaignEventId());
		return statusHistory.stream()
			.sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate()))
			.collect(Collectors.toList());
	}

	/**
	 * Renvoie le dernier statut en date de l'événement
	 */
	@Override
	public CampaignEventStatus getLastStatus() {
		List<CampaignEventStatus> statusHistory = this.getStatusHistory();
		if (statusHistory.size() == 0) {
			return null;
		} else {
			return statusHistory.get(statusHistory.size() - 1);
		}
	}

	@Override
	public CampaignEventStatus updateStatus(int newStatus, String comment,
		User user) throws PortalException {
		CampaignEventStatus status;
		switch (newStatus) {
		case WorkflowConstants.STATUS_DRAFT:
			status = this.setToDraft(user, comment);
			break;
		// Soumission
		case WorkflowConstants.STATUS_PENDING:
			status = this.submitForValidation(user, comment);
			break;
		// Approuvé
		case WorkflowConstants.STATUS_APPROVED:
			status = this.validate(user, comment);
			break;
		// Refusé
		case WorkflowConstants.STATUS_DENIED:
			status = this.deny(user, comment);
			break;
		// Demande de suppression
		case WorkflowConstants.STATUS_IN_TRASH:
			status = this.askForDeletion(user, comment);
			break;
		// Refus de suppression
		case WorkflowConstants.STATUS_ANY:
			status = this.setToPreviousStatus(user, comment);
			break;
		default:
			status = null;
		}
		return status;
	}

	/**
	 * Set le statut de l'événement au précédent statut qu'il avait (utilisé
	 * dans le cas où l'événement est au statut "suppression demandée")
	 */
	private CampaignEventStatus setToPreviousStatus(User user, String comment)
		throws PortalException {
		List<CampaignEventStatus> statusHistory = this.getStatusHistory();
		if (this.getStatus() != WorkflowConstants.STATUS_IN_TRASH
			|| !isUserManagerOfTheEvent(user.getUserId())
			|| statusHistory.size() < 2) {
			return null;
		}
		int newStatus = statusHistory.get(statusHistory.size() - 2).getStatus();
		this.setStatus(newStatus);
		CampaignEventStatus status = this.initNewStatus(newStatus, comment,
			user);
		status.setDeletionDenied(true);
		return status;
	}

	/**
	 * Set le statut de l'événement à "brouillon" (DRAFT)
	 */
	private CampaignEventStatus setToDraft(User user, String comment)
		throws PortalException {
		this.setStatus(WorkflowConstants.STATUS_DRAFT);
		return initNewStatus(WorkflowConstants.STATUS_DRAFT, comment, user);
	}

	/**
	 * Set le statut de l'événement à "suppression demandée" (IN_TRASH)
	 */
	private CampaignEventStatus askForDeletion(User user, String comment)
		throws PortalException {
		this.setStatus(WorkflowConstants.STATUS_IN_TRASH);
		return initNewStatus(WorkflowConstants.STATUS_IN_TRASH, comment, user);
	}

	/**
	 * Set le statut de l'événement à "validation demandée" (PENDING)
	 */
	private CampaignEventStatus submitForValidation(User user, String comment)
		throws PortalException {
		this.setStatus(WorkflowConstants.STATUS_PENDING);
		return initNewStatus(WorkflowConstants.STATUS_PENDING, comment, user);
	}

	/**
	 * Set le statut de l'événement à "refusé" (DENIED)
	 */
	private CampaignEventStatus deny(User user, String comment)
		throws PortalException {
		if (this.getStatus() != WorkflowConstants.STATUS_PENDING
			|| !this.isUserManagerOfTheEvent(user.getUserId())) {
			return null;
		}
		this.setStatus(WorkflowConstants.STATUS_DENIED);
		return initNewStatus(WorkflowConstants.STATUS_DENIED, comment, user);
	}

	/**
	 * Set le statut de l'événement à "validé" (APPROVED)
	 */
	private CampaignEventStatus validate(User user, String comment)
		throws PortalException {
		if (this.getStatus() != WorkflowConstants.STATUS_PENDING
			|| !this.isUserManagerOfTheEvent(user.getUserId())) {
			return null;
		}
		this.setStatus(WorkflowConstants.STATUS_APPROVED);
		return initNewStatus(WorkflowConstants.STATUS_APPROVED, comment, user);
	}

	/**
	 * Init un nouvel objet CampaignEventStatus, lié à l'événement en cours et
	 * au précédent statut
	 */
	private CampaignEventStatus initNewStatus(int newStatus, String comment,
		User user) throws PortalException {
		CampaignEventStatus status = CampaignEventStatusLocalServiceUtil
			.createCampaignEventStatus();
		status.setDate(new Date());
		status.setUserId(user.getUserId());
		status.setUserName(user.getFullName());
		status.setCampaignEventId(this.getCampaignEventId());
		CampaignEventStatus previousStatus = this.getLastStatus();
		if (previousStatus != null) {
			status.setPreviousStatusId(previousStatus.getStatusId());
		} else {
			status.setPreviousStatusId(0);
		}
		status.setComment(comment);
		status.setStatus(newStatus);
		return status;
	}

	/**
	 * Retourne true si l'utilisateur est manager de la campagne à laquelle
	 * appartient l'événement
	 */
	@Override
	public boolean isUserManagerOfTheEvent(long userId) {
		if (this.getCampaign() == null) {
			return false;
		} else {
			return this.getCampaign().isManagedByUser(userId);
		}
	}

	/**
	 * Retourne la catégorie thème
	 */
	@Override
	public AssetCategory getTheme() {
		long themeId = this.getThemeId();
		AssetCategory theme = AssetCategoryLocalServiceUtil
			.fetchAssetCategory(themeId);
		return theme;
	}

	/**
	 * Retourne le nom du service
	 */
	@Override
	public String getServiceName(Locale locale) {
		if (this.getServiceId() > 0) {
			AssetCategory service = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(this.getServiceId());
			if (service != null) {
				return service.getTitle(locale);
			}
		}
		return this.getService();
	}

	@Override
	public void sendDeleteMail() throws PortalException {
		// Initialisation du contexte
		String platformURL = StrasbourgPropsUtil.getAgendaPlatformURL();
		Map<String, Object> context = new HashMap<>();
		context.put("date", new Date());
		context.put("campaignTitle", this.getCampaign().getTitleCurrentValue());
		context.put("eventTitle", this.getTitleCurrentValue());
		context.put("comment", this.getLastStatus().getComment());
		context.put("username", this.getUserName());
		context.put("platformURL", platformURL);

		// Récupération de l'adresse mail
		Long userId = this.getUserId();
		User user = UserLocalServiceUtil.getUser(userId);
		String userMailAddress = user.getEmailAddress();

		try {
			sendMail("delete-subject-template.ftl", "delete-template.ftl",
				context, userMailAddress);
		} catch (IOException | TemplateException e) {
			log.error(e);
		}
	}

	@Override
	public void sendDeletionDeniedMail() throws PortalException {
		// Initialisation du contexte
		String platformURL = StrasbourgPropsUtil.getAgendaPlatformURL();
		Map<String, Object> context = new HashMap<>();
		context.put("date", new Date());
		context.put("campaignTitle", this.getCampaign().getTitleCurrentValue());
		context.put("eventTitle", this.getTitleCurrentValue());
		context.put("comment", this.getLastStatus().getComment());
		context.put("username", this.getUserName());
		context.put("platformURL", platformURL);

		// Récupération de l'adresse mail
		Long userId = this.getUserId();
		User user = UserLocalServiceUtil.getUser(userId);
		String userMailAddress = user.getEmailAddress();

		try {
			sendMail("deletion-denied-subject-template.ftl",
				"deletion-denied-template.ftl", context, userMailAddress);
		} catch (IOException | TemplateException e) {
			log.error(e);
		}
	}

	@Override
	public void sendStatusMail() throws PortalException {
		// Initialisation du contexte
		String platformURL = StrasbourgPropsUtil.getAgendaPlatformURL();
		Map<String, Object> context = new HashMap<>();
		context.put("date", new Date());
		context.put("campaignTitle", this.getCampaign().getTitleCurrentValue());
		context.put("eventTitle", this.getTitleCurrentValue());
		context.put("comment", this.getLastStatus().getComment());
		context.put("username", this.getUserName());
		context.put("platformURL", platformURL);

		// Récupération des adresses mail
		String managersMailAddresses = "";
		for (User manager : this.getCampaign().getManagers()) {
			if (managersMailAddresses.length() > 0) {
				managersMailAddresses += ",";
			}
			String mail = manager.getEmailAddress();
			managersMailAddresses += mail;
		}
		Long userId = this.getUserId();
		User user = UserLocalServiceUtil.getUser(userId);
		String userMailAddress = user.getEmailAddress();

		// Envoi des mails !
		try {
			switch (this.getStatus()) {
			case WorkflowConstants.STATUS_PENDING:
				// Mail au manager
				sendMail("submit-subject-template.ftl", "submit-template.ftl",
					context, managersMailAddresses);
				// Mail à l'utilisateur
				sendMail("submit-user-subject-template.ftl",
					"submit-user-template.ftl", context, userMailAddress);
				break;
			case WorkflowConstants.STATUS_IN_TRASH:
				// Mail au manager
				sendMail("deletion-request-subject-template.ftl",
					"deletion-request-template.ftl", context,
					managersMailAddresses);
				// Mail à l'utilisateur
				sendMail("deletion-request-user-subject-template.ftl",
					"deletion-request-user-template.ftl", context,
					userMailAddress);
				break;
			case WorkflowConstants.STATUS_APPROVED:
				// Mail à l'utilisater
				sendMail("approved-subject-template.ftl",
					"approved-template.ftl", context, userMailAddress);
				break;
			case WorkflowConstants.STATUS_DENIED:
				// Mail à l'utilisater
				sendMail("denied-subject-template.ftl", "denied-template.ftl",
					context, userMailAddress);
				break;
			}
		} catch (Exception e) {
			log.error(e);
		}

	}

	private void sendMail(String subjectTemplatePath, String bodyTemplatePath,
		Map<String, Object> context, String mail)
		throws TemplateNotFoundException, MalformedTemplateNameException,
		ParseException, IOException, TemplateException {
		Configuration configuration = new Configuration(
			Configuration.getVersion());
		configuration.setClassForTemplateLoading(this.getClass(),
			"/templates/campaign");
		configuration.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
		Template subjectTemplate = configuration
			.getTemplate(subjectTemplatePath);
		Template bodyTemplate = configuration.getTemplate(bodyTemplatePath);
		StringWriter subjectWriter = new StringWriter();
		StringWriter bodyWriter = new StringWriter();
		subjectTemplate.process(context, subjectWriter);
		bodyTemplate.process(context, bodyWriter);
		MailHelper.sendMailWithHTML("no-reply@no-replay-strasbourg.eu", mail,
			subjectWriter.toString(), bodyWriter.toString());
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getWebImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getWebImageId());
	}

	/**
	 * Retourne l'objet "LegacyPlace" correspondant au lieu de l'événement, s'il
	 * existe
	 */
	@Override
	public LegacyPlace getLegacyPlace(Locale locale) {
		if (locale_legacyPlace == null) {
			locale_legacyPlace = new HashMap<Locale, LegacyPlace>();
		}
		if (locale_legacyPlace.get(locale) == null) {
			LegacyPlace legacyPlace = LegacyPlace
				.fromSIGId(this.getPlaceSIGId(), locale);
			if (legacyPlace != null) {
				locale_legacyPlace.put(locale, legacyPlace);
			}
		}
		return locale_legacyPlace.get(locale);
	}

	/**
	 * Retourne les périodes
	 */
	@Override
	public List<EventPeriod> getPeriods() {
		return EventPeriodLocalServiceUtil
			.getByCampaignEventId(this.getCampaignEventId());
	}

	/**
	 * Retourne le territoire (la ville) de l'événement (si lieu manuel)
	 */
	@Override
	public String getCity() {
		AssetCategory cityCategory = AssetCategoryLocalServiceUtil
			.fetchAssetCategory(this.getPlaceCityId());
		if (cityCategory != null) {
			return cityCategory.getTitleCurrentValue();
		}
		return "";
	}

	/**
	 * Retourne la version JSON de l'object
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();

		// Id externe
		jsonEvent.put("externalId",
			this.getCampaign().getTitleCurrentValue().substring(0, 3) + "_"
				+ this.getCampaignEventId());

		// Titre, sous-titre, description
		jsonEvent.put("title",
			JSONHelper.getJSONFromI18nMap(this.getTitleMap()));
		if (Validator.isNotNull(this.getSubtitle())) {
			jsonEvent.put("subtitle",
				JSONHelper.getJSONFromI18nMap(this.getSubtitleMap()));
		}
		jsonEvent.put("description",
			JSONHelper.getJSONFromI18nMap(this.getDescriptionMap()));

		// Image et copyright
		if (Validator.isNotNull(this.getWebImageURL())) {
			jsonEvent.put("imageURL", this.getWebImageURL());
		}
		if (Validator.isNotNull(this.getImageOwner())) {
			jsonEvent.put("imageCopyright", this.getImageOwner());
		}

		// Lieu
		if (Validator.isNotNull(this.getPlaceSIGId())) {
			// Lieu SIG
			jsonEvent.put("placeSIGId", this.getPlaceSIGId());
		} else {
			// Lieu manuel
			JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();
			jsonPlace.put("name",
				JSONHelper.getJSONFromI18nMap(this.getPlaceNameMap()));
			if (Validator.isNotNull(this.getPlaceStreetNumber())) {
				jsonPlace.put("streetNumber", this.getPlaceStreetNumber());
			}
			if (Validator.isNotNull(this.getPlaceStreetName())) {
				jsonPlace.put("streetName", this.getPlaceStreetName());
			}
			if (Validator.isNotNull(this.getPlaceZipCode())) {
				jsonPlace.put("zipCode", this.getPlaceZipCode());
			}
			jsonPlace.put("city", this.getCity());

			jsonEvent.put("place", jsonPlace);
		}

		// Informations de contact
		if (Validator.isNotNull(this.getPromoter())) {
			jsonEvent.put("promoter", this.getPromoter());
		}
		if (Validator.isNotNull(this.getPublicEmail())) {
			jsonEvent.put("mail", this.getPublicEmail());
		}
		if (Validator.isNotNull(this.getPublicPhone())) {
			jsonEvent.put("phone", this.getPublicPhone());
		}
		if (Validator.isNotNull(this.getWebsiteURL())) {
			jsonEvent.put("websiteURL",
				JSONHelper.getJSONFromI18nMap(this.getWebsiteURLMap()));
		}

		// Tarifs
		jsonEvent.put("freeEntry", this.getFree());
		if (Validator.isNotNull(this.getPrice())) {
			jsonEvent.put("price",
				JSONHelper.getJSONFromI18nMap(this.getPriceMap()));
		}

		// Horaires
		JSONArray periodsJSON = JSONFactoryUtil.createJSONArray();
		for (EventPeriod period : this.getPeriods()) {
			JSONObject periodJSON = JSONFactoryUtil.createJSONObject();

			DateFormat dateFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd");
			periodJSON.put("startDate",
				dateFormat.format(period.getStartDate()));
			periodJSON.put("endDate", dateFormat.format(period.getEndDate()));

			if (Validator.isNotNull(period.getTimeDetail())) {
				periodJSON.put("timeDetail",
					JSONHelper.getJSONFromI18nMap(period.getTimeDetailMap()));
			}
			periodsJSON.put(periodJSON);
		}
		jsonEvent.put("periods", periodsJSON);

		// Manifestations
		JSONArray jsonManifestations = JSONFactoryUtil.createJSONArray();
		for (String manifestationId : this.getManifestationsIds().split(",")) {
			if (Validator.isNotNull(manifestationId)) {
				jsonManifestations.put(manifestationId);
			}
		}
		if (jsonManifestations.length() > 0) {
			jsonEvent.put("manifestations", jsonManifestations);
		}

		// Thème
		JSONArray jsonThemes = JSONFactoryUtil.createJSONArray();
		if (this.getThemeId() > 0) {
			jsonThemes.put(this.getThemeId());
		}
		if (jsonThemes.length() > 0) {
			jsonEvent.put("themes", jsonThemes);
		}

		// Type
		JSONArray jsonTypes = JSONFactoryUtil.createJSONArray();
		if (this.getTypeId() > 0) {
			jsonTypes.put(this.getTypeId());
		}
		if (jsonTypes.length() > 0) {
			jsonEvent.put("types", jsonTypes);
		}

		// Territoire
		JSONArray jsonTerritories = JSONFactoryUtil.createJSONArray();
		if (this.getPlaceCityId() > 0) {
			jsonTerritories.put(this.getPlaceCityId());
		}
		if (jsonTerritories.length() > 0) {
			jsonEvent.put("territories", jsonTerritories);
		}

		// Publics
		JSONArray jsonPublics = JSONFactoryUtil.createJSONArray();
		for (String publicIdStr : this.getPublicsIds().split(",")) {
			Long publicId = GetterUtil.getLong(publicIdStr);
			if (publicId > 0) {
				jsonPublics.put(publicId);
			}
		}
		if (jsonPublics.length() > 0) {
			jsonEvent.put("publics", jsonPublics);
		}

		// Services
		JSONArray jsonServices = JSONFactoryUtil.createJSONArray();
		if (this.getServiceId() > 0) {
			jsonServices.put(this.getServiceId());
		}
		if (jsonServices.length() > 0) {
			jsonEvent.put("services", jsonServices);
		}

		return jsonEvent;
	}

	private final Log log = LogFactoryUtil.getLog(this.getClass());
}

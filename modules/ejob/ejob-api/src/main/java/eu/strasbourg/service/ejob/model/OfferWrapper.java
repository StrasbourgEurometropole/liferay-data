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

package eu.strasbourg.service.ejob.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Offer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Offer
 * @generated
 */
@ProviderType
public class OfferWrapper implements Offer, ModelWrapper<Offer> {

	public OfferWrapper(Offer offer) {
		_offer = offer;
	}

	@Override
	public Class<?> getModelClass() {
		return Offer.class;
	}

	@Override
	public String getModelClassName() {
		return Offer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("offerId", getOfferId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("publicationId", getPublicationId());
		attributes.put("postNumber", getPostNumber());
		attributes.put("jobCreationDescription", getJobCreationDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("motif", getMotif());
		attributes.put("permanentDescription", getPermanentDescription());
		attributes.put("duration", getDuration());
		attributes.put("post", getPost());
		attributes.put("isFullTime", isIsFullTime());
		attributes.put("fullTimeDescription", getFullTimeDescription());
		attributes.put("introduction", getIntroduction());
		attributes.put("activities", getActivities());
		attributes.put("profil", getProfil());
		attributes.put("conditions", getConditions());
		attributes.put("avantages", getAvantages());
		attributes.put("limitDate", getLimitDate());
		attributes.put("contact", getContact());
		attributes.put("emails", getEmails());
		attributes.put("shareLinkedin", isShareLinkedin());
		attributes.put("exportTotem", getExportTotem());
		attributes.put("publicationStartDate", getPublicationStartDate());
		attributes.put("publicationEndDate", getPublicationEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long offerId = (Long)attributes.get("offerId");

		if (offerId != null) {
			setOfferId(offerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String publicationId = (String)attributes.get("publicationId");

		if (publicationId != null) {
			setPublicationId(publicationId);
		}

		String postNumber = (String)attributes.get("postNumber");

		if (postNumber != null) {
			setPostNumber(postNumber);
		}

		String jobCreationDescription = (String)attributes.get(
			"jobCreationDescription");

		if (jobCreationDescription != null) {
			setJobCreationDescription(jobCreationDescription);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String motif = (String)attributes.get("motif");

		if (motif != null) {
			setMotif(motif);
		}

		String permanentDescription = (String)attributes.get(
			"permanentDescription");

		if (permanentDescription != null) {
			setPermanentDescription(permanentDescription);
		}

		String duration = (String)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		String post = (String)attributes.get("post");

		if (post != null) {
			setPost(post);
		}

		Boolean isFullTime = (Boolean)attributes.get("isFullTime");

		if (isFullTime != null) {
			setIsFullTime(isFullTime);
		}

		String fullTimeDescription = (String)attributes.get(
			"fullTimeDescription");

		if (fullTimeDescription != null) {
			setFullTimeDescription(fullTimeDescription);
		}

		String introduction = (String)attributes.get("introduction");

		if (introduction != null) {
			setIntroduction(introduction);
		}

		String activities = (String)attributes.get("activities");

		if (activities != null) {
			setActivities(activities);
		}

		String profil = (String)attributes.get("profil");

		if (profil != null) {
			setProfil(profil);
		}

		String conditions = (String)attributes.get("conditions");

		if (conditions != null) {
			setConditions(conditions);
		}

		String avantages = (String)attributes.get("avantages");

		if (avantages != null) {
			setAvantages(avantages);
		}

		Date limitDate = (Date)attributes.get("limitDate");

		if (limitDate != null) {
			setLimitDate(limitDate);
		}

		String contact = (String)attributes.get("contact");

		if (contact != null) {
			setContact(contact);
		}

		String emails = (String)attributes.get("emails");

		if (emails != null) {
			setEmails(emails);
		}

		Boolean shareLinkedin = (Boolean)attributes.get("shareLinkedin");

		if (shareLinkedin != null) {
			setShareLinkedin(shareLinkedin);
		}

		String exportTotem = (String)attributes.get("exportTotem");

		if (exportTotem != null) {
			setExportTotem(exportTotem);
		}

		Date publicationStartDate = (Date)attributes.get(
			"publicationStartDate");

		if (publicationStartDate != null) {
			setPublicationStartDate(publicationStartDate);
		}

		Date publicationEndDate = (Date)attributes.get("publicationEndDate");

		if (publicationEndDate != null) {
			setPublicationEndDate(publicationEndDate);
		}
	}

	@Override
	public Object clone() {
		return new OfferWrapper((Offer)_offer.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.ejob.model.Offer offer) {
		return _offer.compareTo(offer);
	}

	/**
	 * Returns the activities of this offer.
	 *
	 * @return the activities of this offer
	 */
	@Override
	public String getActivities() {
		return _offer.getActivities();
	}

	/**
	 * Returns the localized activities of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized activities of this offer
	 */
	@Override
	public String getActivities(java.util.Locale locale) {
		return _offer.getActivities(locale);
	}

	/**
	 * Returns the localized activities of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized activities of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getActivities(java.util.Locale locale, boolean useDefault) {
		return _offer.getActivities(locale, useDefault);
	}

	/**
	 * Returns the localized activities of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized activities of this offer
	 */
	@Override
	public String getActivities(String languageId) {
		return _offer.getActivities(languageId);
	}

	/**
	 * Returns the localized activities of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized activities of this offer
	 */
	@Override
	public String getActivities(String languageId, boolean useDefault) {
		return _offer.getActivities(languageId, useDefault);
	}

	@Override
	public String getActivitiesCurrentLanguageId() {
		return _offer.getActivitiesCurrentLanguageId();
	}

	@Override
	public String getActivitiesCurrentValue() {
		return _offer.getActivitiesCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized activitieses of this offer.
	 *
	 * @return the locales and localized activitieses of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getActivitiesMap() {
		return _offer.getActivitiesMap();
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _offer.getAssetEntry();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _offer.getAvailableLanguageIds();
	}

	/**
	 * Returns the avantages of this offer.
	 *
	 * @return the avantages of this offer
	 */
	@Override
	public String getAvantages() {
		return _offer.getAvantages();
	}

	/**
	 * Returns the localized avantages of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized avantages of this offer
	 */
	@Override
	public String getAvantages(java.util.Locale locale) {
		return _offer.getAvantages(locale);
	}

	/**
	 * Returns the localized avantages of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized avantages of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getAvantages(java.util.Locale locale, boolean useDefault) {
		return _offer.getAvantages(locale, useDefault);
	}

	/**
	 * Returns the localized avantages of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized avantages of this offer
	 */
	@Override
	public String getAvantages(String languageId) {
		return _offer.getAvantages(languageId);
	}

	/**
	 * Returns the localized avantages of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized avantages of this offer
	 */
	@Override
	public String getAvantages(String languageId, boolean useDefault) {
		return _offer.getAvantages(languageId, useDefault);
	}

	@Override
	public String getAvantagesCurrentLanguageId() {
		return _offer.getAvantagesCurrentLanguageId();
	}

	@Override
	public String getAvantagesCurrentValue() {
		return _offer.getAvantagesCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized avantageses of this offer.
	 *
	 * @return the locales and localized avantageses of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getAvantagesMap() {
		return _offer.getAvantagesMap();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _offer.getCategories();
	}

	/**
	 * Returns the company ID of this offer.
	 *
	 * @return the company ID of this offer
	 */
	@Override
	public long getCompanyId() {
		return _offer.getCompanyId();
	}

	/**
	 * Returns the conditions of this offer.
	 *
	 * @return the conditions of this offer
	 */
	@Override
	public String getConditions() {
		return _offer.getConditions();
	}

	/**
	 * Returns the localized conditions of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized conditions of this offer
	 */
	@Override
	public String getConditions(java.util.Locale locale) {
		return _offer.getConditions(locale);
	}

	/**
	 * Returns the localized conditions of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized conditions of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getConditions(java.util.Locale locale, boolean useDefault) {
		return _offer.getConditions(locale, useDefault);
	}

	/**
	 * Returns the localized conditions of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized conditions of this offer
	 */
	@Override
	public String getConditions(String languageId) {
		return _offer.getConditions(languageId);
	}

	/**
	 * Returns the localized conditions of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized conditions of this offer
	 */
	@Override
	public String getConditions(String languageId, boolean useDefault) {
		return _offer.getConditions(languageId, useDefault);
	}

	@Override
	public String getConditionsCurrentLanguageId() {
		return _offer.getConditionsCurrentLanguageId();
	}

	@Override
	public String getConditionsCurrentValue() {
		return _offer.getConditionsCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized conditionses of this offer.
	 *
	 * @return the locales and localized conditionses of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getConditionsMap() {
		return _offer.getConditionsMap();
	}

	/**
	 * Returns the contact of this offer.
	 *
	 * @return the contact of this offer
	 */
	@Override
	public String getContact() {
		return _offer.getContact();
	}

	/**
	 * Returns the create date of this offer.
	 *
	 * @return the create date of this offer
	 */
	@Override
	public Date getCreateDate() {
		return _offer.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _offer.getDefaultLanguageId();
	}

	/**
	 * Returns the duration of this offer.
	 *
	 * @return the duration of this offer
	 */
	@Override
	public String getDuration() {
		return _offer.getDuration();
	}

	/**
	 * Returns the localized duration of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized duration of this offer
	 */
	@Override
	public String getDuration(java.util.Locale locale) {
		return _offer.getDuration(locale);
	}

	/**
	 * Returns the localized duration of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized duration of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDuration(java.util.Locale locale, boolean useDefault) {
		return _offer.getDuration(locale, useDefault);
	}

	/**
	 * Returns the localized duration of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized duration of this offer
	 */
	@Override
	public String getDuration(String languageId) {
		return _offer.getDuration(languageId);
	}

	/**
	 * Returns the localized duration of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized duration of this offer
	 */
	@Override
	public String getDuration(String languageId, boolean useDefault) {
		return _offer.getDuration(languageId, useDefault);
	}

	@Override
	public String getDurationCurrentLanguageId() {
		return _offer.getDurationCurrentLanguageId();
	}

	@Override
	public String getDurationCurrentValue() {
		return _offer.getDurationCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized durations of this offer.
	 *
	 * @return the locales and localized durations of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getDurationMap() {
		return _offer.getDurationMap();
	}

	/**
	 * Returns the emails of this offer.
	 *
	 * @return the emails of this offer
	 */
	@Override
	public String getEmails() {
		return _offer.getEmails();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _offer.getExpandoBridge();
	}

	/**
	 * Returns the export totem of this offer.
	 *
	 * @return the export totem of this offer
	 */
	@Override
	public String getExportTotem() {
		return _offer.getExportTotem();
	}

	/**
	 * Returns the full time description of this offer.
	 *
	 * @return the full time description of this offer
	 */
	@Override
	public String getFullTimeDescription() {
		return _offer.getFullTimeDescription();
	}

	/**
	 * Returns the localized full time description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized full time description of this offer
	 */
	@Override
	public String getFullTimeDescription(java.util.Locale locale) {
		return _offer.getFullTimeDescription(locale);
	}

	/**
	 * Returns the localized full time description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized full time description of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getFullTimeDescription(
		java.util.Locale locale, boolean useDefault) {

		return _offer.getFullTimeDescription(locale, useDefault);
	}

	/**
	 * Returns the localized full time description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized full time description of this offer
	 */
	@Override
	public String getFullTimeDescription(String languageId) {
		return _offer.getFullTimeDescription(languageId);
	}

	/**
	 * Returns the localized full time description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized full time description of this offer
	 */
	@Override
	public String getFullTimeDescription(
		String languageId, boolean useDefault) {

		return _offer.getFullTimeDescription(languageId, useDefault);
	}

	@Override
	public String getFullTimeDescriptionCurrentLanguageId() {
		return _offer.getFullTimeDescriptionCurrentLanguageId();
	}

	@Override
	public String getFullTimeDescriptionCurrentValue() {
		return _offer.getFullTimeDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized full time descriptions of this offer.
	 *
	 * @return the locales and localized full time descriptions of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getFullTimeDescriptionMap() {
		return _offer.getFullTimeDescriptionMap();
	}

	/**
	 * Returns the group ID of this offer.
	 *
	 * @return the group ID of this offer
	 */
	@Override
	public long getGroupId() {
		return _offer.getGroupId();
	}

	/**
	 * Returns the introduction of this offer.
	 *
	 * @return the introduction of this offer
	 */
	@Override
	public String getIntroduction() {
		return _offer.getIntroduction();
	}

	/**
	 * Returns the localized introduction of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized introduction of this offer
	 */
	@Override
	public String getIntroduction(java.util.Locale locale) {
		return _offer.getIntroduction(locale);
	}

	/**
	 * Returns the localized introduction of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized introduction of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getIntroduction(java.util.Locale locale, boolean useDefault) {
		return _offer.getIntroduction(locale, useDefault);
	}

	/**
	 * Returns the localized introduction of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized introduction of this offer
	 */
	@Override
	public String getIntroduction(String languageId) {
		return _offer.getIntroduction(languageId);
	}

	/**
	 * Returns the localized introduction of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized introduction of this offer
	 */
	@Override
	public String getIntroduction(String languageId, boolean useDefault) {
		return _offer.getIntroduction(languageId, useDefault);
	}

	@Override
	public String getIntroductionCurrentLanguageId() {
		return _offer.getIntroductionCurrentLanguageId();
	}

	@Override
	public String getIntroductionCurrentValue() {
		return _offer.getIntroductionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized introductions of this offer.
	 *
	 * @return the locales and localized introductions of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getIntroductionMap() {
		return _offer.getIntroductionMap();
	}

	/**
	 * Returns the is full time of this offer.
	 *
	 * @return the is full time of this offer
	 */
	@Override
	public boolean getIsFullTime() {
		return _offer.getIsFullTime();
	}

	/**
	 * Returns the job creation description of this offer.
	 *
	 * @return the job creation description of this offer
	 */
	@Override
	public String getJobCreationDescription() {
		return _offer.getJobCreationDescription();
	}

	/**
	 * Returns the localized job creation description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized job creation description of this offer
	 */
	@Override
	public String getJobCreationDescription(java.util.Locale locale) {
		return _offer.getJobCreationDescription(locale);
	}

	/**
	 * Returns the localized job creation description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job creation description of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getJobCreationDescription(
		java.util.Locale locale, boolean useDefault) {

		return _offer.getJobCreationDescription(locale, useDefault);
	}

	/**
	 * Returns the localized job creation description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized job creation description of this offer
	 */
	@Override
	public String getJobCreationDescription(String languageId) {
		return _offer.getJobCreationDescription(languageId);
	}

	/**
	 * Returns the localized job creation description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized job creation description of this offer
	 */
	@Override
	public String getJobCreationDescription(
		String languageId, boolean useDefault) {

		return _offer.getJobCreationDescription(languageId, useDefault);
	}

	@Override
	public String getJobCreationDescriptionCurrentLanguageId() {
		return _offer.getJobCreationDescriptionCurrentLanguageId();
	}

	@Override
	public String getJobCreationDescriptionCurrentValue() {
		return _offer.getJobCreationDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized job creation descriptions of this offer.
	 *
	 * @return the locales and localized job creation descriptions of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getJobCreationDescriptionMap() {
		return _offer.getJobCreationDescriptionMap();
	}

	/**
	 * Returns the limit date of this offer.
	 *
	 * @return the limit date of this offer
	 */
	@Override
	public Date getLimitDate() {
		return _offer.getLimitDate();
	}

	/**
	 * Returns the modified date of this offer.
	 *
	 * @return the modified date of this offer
	 */
	@Override
	public Date getModifiedDate() {
		return _offer.getModifiedDate();
	}

	/**
	 * Returns the motif of this offer.
	 *
	 * @return the motif of this offer
	 */
	@Override
	public String getMotif() {
		return _offer.getMotif();
	}

	/**
	 * Returns the localized motif of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized motif of this offer
	 */
	@Override
	public String getMotif(java.util.Locale locale) {
		return _offer.getMotif(locale);
	}

	/**
	 * Returns the localized motif of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized motif of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getMotif(java.util.Locale locale, boolean useDefault) {
		return _offer.getMotif(locale, useDefault);
	}

	/**
	 * Returns the localized motif of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized motif of this offer
	 */
	@Override
	public String getMotif(String languageId) {
		return _offer.getMotif(languageId);
	}

	/**
	 * Returns the localized motif of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized motif of this offer
	 */
	@Override
	public String getMotif(String languageId, boolean useDefault) {
		return _offer.getMotif(languageId, useDefault);
	}

	@Override
	public String getMotifCurrentLanguageId() {
		return _offer.getMotifCurrentLanguageId();
	}

	@Override
	public String getMotifCurrentValue() {
		return _offer.getMotifCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized motifs of this offer.
	 *
	 * @return the locales and localized motifs of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getMotifMap() {
		return _offer.getMotifMap();
	}

	/**
	 * Renvoie les contact RE
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferContact() {
		return _offer.getOfferContact();
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferDirection() {
		return _offer.getOfferDirection();
	}

	/**
	 * Renvoie les Famille de métiers
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferFamille() {
		return _offer.getOfferFamille();
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferFiliere() {
		return _offer.getOfferFiliere();
	}

	/**
	 * Renvoie les categories des filieres
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory
		getOfferFiliereCategorie() {

		return _offer.getOfferFiliereCategorie();
	}

	/**
	 * Renvoie les grades
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferGrade() {
		return _offer.getOfferGrade();
	}

	/**
	 * Returns the offer ID of this offer.
	 *
	 * @return the offer ID of this offer
	 */
	@Override
	public long getOfferId() {
		return _offer.getOfferId();
	}

	/**
	 * Renvoie les Niveau d'étude
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferNiveauEtude() {
		return _offer.getOfferNiveauEtude();
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getOfferService() {
		return _offer.getOfferService();
	}

	/**
	 * Renvoie les types de recrutements
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetCategory
		getOfferTypeRecrutement() {

		return _offer.getOfferTypeRecrutement();
	}

	/**
	 * Returns the permanent description of this offer.
	 *
	 * @return the permanent description of this offer
	 */
	@Override
	public String getPermanentDescription() {
		return _offer.getPermanentDescription();
	}

	/**
	 * Returns the localized permanent description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized permanent description of this offer
	 */
	@Override
	public String getPermanentDescription(java.util.Locale locale) {
		return _offer.getPermanentDescription(locale);
	}

	/**
	 * Returns the localized permanent description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized permanent description of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getPermanentDescription(
		java.util.Locale locale, boolean useDefault) {

		return _offer.getPermanentDescription(locale, useDefault);
	}

	/**
	 * Returns the localized permanent description of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized permanent description of this offer
	 */
	@Override
	public String getPermanentDescription(String languageId) {
		return _offer.getPermanentDescription(languageId);
	}

	/**
	 * Returns the localized permanent description of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized permanent description of this offer
	 */
	@Override
	public String getPermanentDescription(
		String languageId, boolean useDefault) {

		return _offer.getPermanentDescription(languageId, useDefault);
	}

	@Override
	public String getPermanentDescriptionCurrentLanguageId() {
		return _offer.getPermanentDescriptionCurrentLanguageId();
	}

	@Override
	public String getPermanentDescriptionCurrentValue() {
		return _offer.getPermanentDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized permanent descriptions of this offer.
	 *
	 * @return the locales and localized permanent descriptions of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getPermanentDescriptionMap() {
		return _offer.getPermanentDescriptionMap();
	}

	/**
	 * Returns the post of this offer.
	 *
	 * @return the post of this offer
	 */
	@Override
	public String getPost() {
		return _offer.getPost();
	}

	/**
	 * Returns the localized post of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized post of this offer
	 */
	@Override
	public String getPost(java.util.Locale locale) {
		return _offer.getPost(locale);
	}

	/**
	 * Returns the localized post of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized post of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getPost(java.util.Locale locale, boolean useDefault) {
		return _offer.getPost(locale, useDefault);
	}

	/**
	 * Returns the localized post of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized post of this offer
	 */
	@Override
	public String getPost(String languageId) {
		return _offer.getPost(languageId);
	}

	/**
	 * Returns the localized post of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized post of this offer
	 */
	@Override
	public String getPost(String languageId, boolean useDefault) {
		return _offer.getPost(languageId, useDefault);
	}

	@Override
	public String getPostCurrentLanguageId() {
		return _offer.getPostCurrentLanguageId();
	}

	@Override
	public String getPostCurrentValue() {
		return _offer.getPostCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized posts of this offer.
	 *
	 * @return the locales and localized posts of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getPostMap() {
		return _offer.getPostMap();
	}

	/**
	 * Returns the post number of this offer.
	 *
	 * @return the post number of this offer
	 */
	@Override
	public String getPostNumber() {
		return _offer.getPostNumber();
	}

	/**
	 * Returns the primary key of this offer.
	 *
	 * @return the primary key of this offer
	 */
	@Override
	public long getPrimaryKey() {
		return _offer.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offer.getPrimaryKeyObj();
	}

	/**
	 * Returns the profil of this offer.
	 *
	 * @return the profil of this offer
	 */
	@Override
	public String getProfil() {
		return _offer.getProfil();
	}

	/**
	 * Returns the localized profil of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized profil of this offer
	 */
	@Override
	public String getProfil(java.util.Locale locale) {
		return _offer.getProfil(locale);
	}

	/**
	 * Returns the localized profil of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized profil of this offer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getProfil(java.util.Locale locale, boolean useDefault) {
		return _offer.getProfil(locale, useDefault);
	}

	/**
	 * Returns the localized profil of this offer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized profil of this offer
	 */
	@Override
	public String getProfil(String languageId) {
		return _offer.getProfil(languageId);
	}

	/**
	 * Returns the localized profil of this offer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized profil of this offer
	 */
	@Override
	public String getProfil(String languageId, boolean useDefault) {
		return _offer.getProfil(languageId, useDefault);
	}

	@Override
	public String getProfilCurrentLanguageId() {
		return _offer.getProfilCurrentLanguageId();
	}

	@Override
	public String getProfilCurrentValue() {
		return _offer.getProfilCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized profils of this offer.
	 *
	 * @return the locales and localized profils of this offer
	 */
	@Override
	public Map<java.util.Locale, String> getProfilMap() {
		return _offer.getProfilMap();
	}

	/**
	 * Returns the publication end date of this offer.
	 *
	 * @return the publication end date of this offer
	 */
	@Override
	public Date getPublicationEndDate() {
		return _offer.getPublicationEndDate();
	}

	/**
	 * Returns the publication ID of this offer.
	 *
	 * @return the publication ID of this offer
	 */
	@Override
	public String getPublicationId() {
		return _offer.getPublicationId();
	}

	/**
	 * Returns the publication start date of this offer.
	 *
	 * @return the publication start date of this offer
	 */
	@Override
	public Date getPublicationStartDate() {
		return _offer.getPublicationStartDate();
	}

	/**
	 * Returns the share linkedin of this offer.
	 *
	 * @return the share linkedin of this offer
	 */
	@Override
	public boolean getShareLinkedin() {
		return _offer.getShareLinkedin();
	}

	/**
	 * Returns the start date of this offer.
	 *
	 * @return the start date of this offer
	 */
	@Override
	public Date getStartDate() {
		return _offer.getStartDate();
	}

	/**
	 * Returns the status of this offer.
	 *
	 * @return the status of this offer
	 */
	@Override
	public int getStatus() {
		return _offer.getStatus();
	}

	/**
	 * Returns the status by user ID of this offer.
	 *
	 * @return the status by user ID of this offer
	 */
	@Override
	public long getStatusByUserId() {
		return _offer.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this offer.
	 *
	 * @return the status by user name of this offer
	 */
	@Override
	public String getStatusByUserName() {
		return _offer.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this offer.
	 *
	 * @return the status by user uuid of this offer
	 */
	@Override
	public String getStatusByUserUuid() {
		return _offer.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this offer.
	 *
	 * @return the status date of this offer
	 */
	@Override
	public Date getStatusDate() {
		return _offer.getStatusDate();
	}

	/**
	 * Returns the user ID of this offer.
	 *
	 * @return the user ID of this offer
	 */
	@Override
	public long getUserId() {
		return _offer.getUserId();
	}

	/**
	 * Returns the user name of this offer.
	 *
	 * @return the user name of this offer
	 */
	@Override
	public String getUserName() {
		return _offer.getUserName();
	}

	/**
	 * Returns the user uuid of this offer.
	 *
	 * @return the user uuid of this offer
	 */
	@Override
	public String getUserUuid() {
		return _offer.getUserUuid();
	}

	/**
	 * Returns the uuid of this offer.
	 *
	 * @return the uuid of this offer
	 */
	@Override
	public String getUuid() {
		return _offer.getUuid();
	}

	@Override
	public int hashCode() {
		return _offer.hashCode();
	}

	/**
	 * Returns <code>true</code> if this offer is approved.
	 *
	 * @return <code>true</code> if this offer is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _offer.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _offer.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this offer is denied.
	 *
	 * @return <code>true</code> if this offer is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _offer.isDenied();
	}

	/**
	 * Returns <code>true</code> if this offer is a draft.
	 *
	 * @return <code>true</code> if this offer is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _offer.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _offer.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this offer is expired.
	 *
	 * @return <code>true</code> if this offer is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _offer.isExpired();
	}

	/**
	 * Returns <code>true</code> if this offer is inactive.
	 *
	 * @return <code>true</code> if this offer is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _offer.isInactive();
	}

	/**
	 * Returns <code>true</code> if this offer is incomplete.
	 *
	 * @return <code>true</code> if this offer is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _offer.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this offer is is full time.
	 *
	 * @return <code>true</code> if this offer is is full time; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsFullTime() {
		return _offer.isIsFullTime();
	}

	@Override
	public boolean isNew() {
		return _offer.isNew();
	}

	/**
	 * Returns <code>true</code> if this offer is pending.
	 *
	 * @return <code>true</code> if this offer is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _offer.isPending();
	}

	/**
	 * Returns <code>true</code> if this offer is scheduled.
	 *
	 * @return <code>true</code> if this offer is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _offer.isScheduled();
	}

	/**
	 * Returns <code>true</code> if this offer is share linkedin.
	 *
	 * @return <code>true</code> if this offer is share linkedin; <code>false</code> otherwise
	 */
	@Override
	public boolean isShareLinkedin() {
		return _offer.isShareLinkedin();
	}

	@Override
	public void persist() {
		_offer.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_offer.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_offer.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the activities of this offer.
	 *
	 * @param activities the activities of this offer
	 */
	@Override
	public void setActivities(String activities) {
		_offer.setActivities(activities);
	}

	/**
	 * Sets the localized activities of this offer in the language.
	 *
	 * @param activities the localized activities of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setActivities(String activities, java.util.Locale locale) {
		_offer.setActivities(activities, locale);
	}

	/**
	 * Sets the localized activities of this offer in the language, and sets the default locale.
	 *
	 * @param activities the localized activities of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setActivities(
		String activities, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setActivities(activities, locale, defaultLocale);
	}

	@Override
	public void setActivitiesCurrentLanguageId(String languageId) {
		_offer.setActivitiesCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized activitieses of this offer from the map of locales and localized activitieses.
	 *
	 * @param activitiesMap the locales and localized activitieses of this offer
	 */
	@Override
	public void setActivitiesMap(Map<java.util.Locale, String> activitiesMap) {
		_offer.setActivitiesMap(activitiesMap);
	}

	/**
	 * Sets the localized activitieses of this offer from the map of locales and localized activitieses, and sets the default locale.
	 *
	 * @param activitiesMap the locales and localized activitieses of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setActivitiesMap(
		Map<java.util.Locale, String> activitiesMap,
		java.util.Locale defaultLocale) {

		_offer.setActivitiesMap(activitiesMap, defaultLocale);
	}

	/**
	 * Sets the avantages of this offer.
	 *
	 * @param avantages the avantages of this offer
	 */
	@Override
	public void setAvantages(String avantages) {
		_offer.setAvantages(avantages);
	}

	/**
	 * Sets the localized avantages of this offer in the language.
	 *
	 * @param avantages the localized avantages of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setAvantages(String avantages, java.util.Locale locale) {
		_offer.setAvantages(avantages, locale);
	}

	/**
	 * Sets the localized avantages of this offer in the language, and sets the default locale.
	 *
	 * @param avantages the localized avantages of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAvantages(
		String avantages, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setAvantages(avantages, locale, defaultLocale);
	}

	@Override
	public void setAvantagesCurrentLanguageId(String languageId) {
		_offer.setAvantagesCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized avantageses of this offer from the map of locales and localized avantageses.
	 *
	 * @param avantagesMap the locales and localized avantageses of this offer
	 */
	@Override
	public void setAvantagesMap(Map<java.util.Locale, String> avantagesMap) {
		_offer.setAvantagesMap(avantagesMap);
	}

	/**
	 * Sets the localized avantageses of this offer from the map of locales and localized avantageses, and sets the default locale.
	 *
	 * @param avantagesMap the locales and localized avantageses of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAvantagesMap(
		Map<java.util.Locale, String> avantagesMap,
		java.util.Locale defaultLocale) {

		_offer.setAvantagesMap(avantagesMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_offer.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this offer.
	 *
	 * @param companyId the company ID of this offer
	 */
	@Override
	public void setCompanyId(long companyId) {
		_offer.setCompanyId(companyId);
	}

	/**
	 * Sets the conditions of this offer.
	 *
	 * @param conditions the conditions of this offer
	 */
	@Override
	public void setConditions(String conditions) {
		_offer.setConditions(conditions);
	}

	/**
	 * Sets the localized conditions of this offer in the language.
	 *
	 * @param conditions the localized conditions of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setConditions(String conditions, java.util.Locale locale) {
		_offer.setConditions(conditions, locale);
	}

	/**
	 * Sets the localized conditions of this offer in the language, and sets the default locale.
	 *
	 * @param conditions the localized conditions of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setConditions(
		String conditions, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setConditions(conditions, locale, defaultLocale);
	}

	@Override
	public void setConditionsCurrentLanguageId(String languageId) {
		_offer.setConditionsCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized conditionses of this offer from the map of locales and localized conditionses.
	 *
	 * @param conditionsMap the locales and localized conditionses of this offer
	 */
	@Override
	public void setConditionsMap(Map<java.util.Locale, String> conditionsMap) {
		_offer.setConditionsMap(conditionsMap);
	}

	/**
	 * Sets the localized conditionses of this offer from the map of locales and localized conditionses, and sets the default locale.
	 *
	 * @param conditionsMap the locales and localized conditionses of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setConditionsMap(
		Map<java.util.Locale, String> conditionsMap,
		java.util.Locale defaultLocale) {

		_offer.setConditionsMap(conditionsMap, defaultLocale);
	}

	/**
	 * Sets the contact of this offer.
	 *
	 * @param contact the contact of this offer
	 */
	@Override
	public void setContact(String contact) {
		_offer.setContact(contact);
	}

	/**
	 * Sets the create date of this offer.
	 *
	 * @param createDate the create date of this offer
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_offer.setCreateDate(createDate);
	}

	/**
	 * Sets the duration of this offer.
	 *
	 * @param duration the duration of this offer
	 */
	@Override
	public void setDuration(String duration) {
		_offer.setDuration(duration);
	}

	/**
	 * Sets the localized duration of this offer in the language.
	 *
	 * @param duration the localized duration of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setDuration(String duration, java.util.Locale locale) {
		_offer.setDuration(duration, locale);
	}

	/**
	 * Sets the localized duration of this offer in the language, and sets the default locale.
	 *
	 * @param duration the localized duration of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDuration(
		String duration, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setDuration(duration, locale, defaultLocale);
	}

	@Override
	public void setDurationCurrentLanguageId(String languageId) {
		_offer.setDurationCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized durations of this offer from the map of locales and localized durations.
	 *
	 * @param durationMap the locales and localized durations of this offer
	 */
	@Override
	public void setDurationMap(Map<java.util.Locale, String> durationMap) {
		_offer.setDurationMap(durationMap);
	}

	/**
	 * Sets the localized durations of this offer from the map of locales and localized durations, and sets the default locale.
	 *
	 * @param durationMap the locales and localized durations of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDurationMap(
		Map<java.util.Locale, String> durationMap,
		java.util.Locale defaultLocale) {

		_offer.setDurationMap(durationMap, defaultLocale);
	}

	/**
	 * Sets the emails of this offer.
	 *
	 * @param emails the emails of this offer
	 */
	@Override
	public void setEmails(String emails) {
		_offer.setEmails(emails);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_offer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_offer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_offer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the export totem of this offer.
	 *
	 * @param exportTotem the export totem of this offer
	 */
	@Override
	public void setExportTotem(String exportTotem) {
		_offer.setExportTotem(exportTotem);
	}

	/**
	 * Sets the full time description of this offer.
	 *
	 * @param fullTimeDescription the full time description of this offer
	 */
	@Override
	public void setFullTimeDescription(String fullTimeDescription) {
		_offer.setFullTimeDescription(fullTimeDescription);
	}

	/**
	 * Sets the localized full time description of this offer in the language.
	 *
	 * @param fullTimeDescription the localized full time description of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setFullTimeDescription(
		String fullTimeDescription, java.util.Locale locale) {

		_offer.setFullTimeDescription(fullTimeDescription, locale);
	}

	/**
	 * Sets the localized full time description of this offer in the language, and sets the default locale.
	 *
	 * @param fullTimeDescription the localized full time description of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFullTimeDescription(
		String fullTimeDescription, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setFullTimeDescription(
			fullTimeDescription, locale, defaultLocale);
	}

	@Override
	public void setFullTimeDescriptionCurrentLanguageId(String languageId) {
		_offer.setFullTimeDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized full time descriptions of this offer from the map of locales and localized full time descriptions.
	 *
	 * @param fullTimeDescriptionMap the locales and localized full time descriptions of this offer
	 */
	@Override
	public void setFullTimeDescriptionMap(
		Map<java.util.Locale, String> fullTimeDescriptionMap) {

		_offer.setFullTimeDescriptionMap(fullTimeDescriptionMap);
	}

	/**
	 * Sets the localized full time descriptions of this offer from the map of locales and localized full time descriptions, and sets the default locale.
	 *
	 * @param fullTimeDescriptionMap the locales and localized full time descriptions of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFullTimeDescriptionMap(
		Map<java.util.Locale, String> fullTimeDescriptionMap,
		java.util.Locale defaultLocale) {

		_offer.setFullTimeDescriptionMap(fullTimeDescriptionMap, defaultLocale);
	}

	/**
	 * Sets the group ID of this offer.
	 *
	 * @param groupId the group ID of this offer
	 */
	@Override
	public void setGroupId(long groupId) {
		_offer.setGroupId(groupId);
	}

	/**
	 * Sets the introduction of this offer.
	 *
	 * @param introduction the introduction of this offer
	 */
	@Override
	public void setIntroduction(String introduction) {
		_offer.setIntroduction(introduction);
	}

	/**
	 * Sets the localized introduction of this offer in the language.
	 *
	 * @param introduction the localized introduction of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setIntroduction(String introduction, java.util.Locale locale) {
		_offer.setIntroduction(introduction, locale);
	}

	/**
	 * Sets the localized introduction of this offer in the language, and sets the default locale.
	 *
	 * @param introduction the localized introduction of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setIntroduction(
		String introduction, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setIntroduction(introduction, locale, defaultLocale);
	}

	@Override
	public void setIntroductionCurrentLanguageId(String languageId) {
		_offer.setIntroductionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized introductions of this offer from the map of locales and localized introductions.
	 *
	 * @param introductionMap the locales and localized introductions of this offer
	 */
	@Override
	public void setIntroductionMap(
		Map<java.util.Locale, String> introductionMap) {

		_offer.setIntroductionMap(introductionMap);
	}

	/**
	 * Sets the localized introductions of this offer from the map of locales and localized introductions, and sets the default locale.
	 *
	 * @param introductionMap the locales and localized introductions of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setIntroductionMap(
		Map<java.util.Locale, String> introductionMap,
		java.util.Locale defaultLocale) {

		_offer.setIntroductionMap(introductionMap, defaultLocale);
	}

	/**
	 * Sets whether this offer is is full time.
	 *
	 * @param isFullTime the is full time of this offer
	 */
	@Override
	public void setIsFullTime(boolean isFullTime) {
		_offer.setIsFullTime(isFullTime);
	}

	/**
	 * Sets the job creation description of this offer.
	 *
	 * @param jobCreationDescription the job creation description of this offer
	 */
	@Override
	public void setJobCreationDescription(String jobCreationDescription) {
		_offer.setJobCreationDescription(jobCreationDescription);
	}

	/**
	 * Sets the localized job creation description of this offer in the language.
	 *
	 * @param jobCreationDescription the localized job creation description of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setJobCreationDescription(
		String jobCreationDescription, java.util.Locale locale) {

		_offer.setJobCreationDescription(jobCreationDescription, locale);
	}

	/**
	 * Sets the localized job creation description of this offer in the language, and sets the default locale.
	 *
	 * @param jobCreationDescription the localized job creation description of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setJobCreationDescription(
		String jobCreationDescription, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setJobCreationDescription(
			jobCreationDescription, locale, defaultLocale);
	}

	@Override
	public void setJobCreationDescriptionCurrentLanguageId(String languageId) {
		_offer.setJobCreationDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized job creation descriptions of this offer from the map of locales and localized job creation descriptions.
	 *
	 * @param jobCreationDescriptionMap the locales and localized job creation descriptions of this offer
	 */
	@Override
	public void setJobCreationDescriptionMap(
		Map<java.util.Locale, String> jobCreationDescriptionMap) {

		_offer.setJobCreationDescriptionMap(jobCreationDescriptionMap);
	}

	/**
	 * Sets the localized job creation descriptions of this offer from the map of locales and localized job creation descriptions, and sets the default locale.
	 *
	 * @param jobCreationDescriptionMap the locales and localized job creation descriptions of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setJobCreationDescriptionMap(
		Map<java.util.Locale, String> jobCreationDescriptionMap,
		java.util.Locale defaultLocale) {

		_offer.setJobCreationDescriptionMap(
			jobCreationDescriptionMap, defaultLocale);
	}

	/**
	 * Sets the limit date of this offer.
	 *
	 * @param limitDate the limit date of this offer
	 */
	@Override
	public void setLimitDate(Date limitDate) {
		_offer.setLimitDate(limitDate);
	}

	/**
	 * Sets the modified date of this offer.
	 *
	 * @param modifiedDate the modified date of this offer
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_offer.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the motif of this offer.
	 *
	 * @param motif the motif of this offer
	 */
	@Override
	public void setMotif(String motif) {
		_offer.setMotif(motif);
	}

	/**
	 * Sets the localized motif of this offer in the language.
	 *
	 * @param motif the localized motif of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setMotif(String motif, java.util.Locale locale) {
		_offer.setMotif(motif, locale);
	}

	/**
	 * Sets the localized motif of this offer in the language, and sets the default locale.
	 *
	 * @param motif the localized motif of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setMotif(
		String motif, java.util.Locale locale, java.util.Locale defaultLocale) {

		_offer.setMotif(motif, locale, defaultLocale);
	}

	@Override
	public void setMotifCurrentLanguageId(String languageId) {
		_offer.setMotifCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized motifs of this offer from the map of locales and localized motifs.
	 *
	 * @param motifMap the locales and localized motifs of this offer
	 */
	@Override
	public void setMotifMap(Map<java.util.Locale, String> motifMap) {
		_offer.setMotifMap(motifMap);
	}

	/**
	 * Sets the localized motifs of this offer from the map of locales and localized motifs, and sets the default locale.
	 *
	 * @param motifMap the locales and localized motifs of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setMotifMap(
		Map<java.util.Locale, String> motifMap,
		java.util.Locale defaultLocale) {

		_offer.setMotifMap(motifMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_offer.setNew(n);
	}

	/**
	 * Sets the offer ID of this offer.
	 *
	 * @param offerId the offer ID of this offer
	 */
	@Override
	public void setOfferId(long offerId) {
		_offer.setOfferId(offerId);
	}

	/**
	 * Sets the permanent description of this offer.
	 *
	 * @param permanentDescription the permanent description of this offer
	 */
	@Override
	public void setPermanentDescription(String permanentDescription) {
		_offer.setPermanentDescription(permanentDescription);
	}

	/**
	 * Sets the localized permanent description of this offer in the language.
	 *
	 * @param permanentDescription the localized permanent description of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setPermanentDescription(
		String permanentDescription, java.util.Locale locale) {

		_offer.setPermanentDescription(permanentDescription, locale);
	}

	/**
	 * Sets the localized permanent description of this offer in the language, and sets the default locale.
	 *
	 * @param permanentDescription the localized permanent description of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPermanentDescription(
		String permanentDescription, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setPermanentDescription(
			permanentDescription, locale, defaultLocale);
	}

	@Override
	public void setPermanentDescriptionCurrentLanguageId(String languageId) {
		_offer.setPermanentDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized permanent descriptions of this offer from the map of locales and localized permanent descriptions.
	 *
	 * @param permanentDescriptionMap the locales and localized permanent descriptions of this offer
	 */
	@Override
	public void setPermanentDescriptionMap(
		Map<java.util.Locale, String> permanentDescriptionMap) {

		_offer.setPermanentDescriptionMap(permanentDescriptionMap);
	}

	/**
	 * Sets the localized permanent descriptions of this offer from the map of locales and localized permanent descriptions, and sets the default locale.
	 *
	 * @param permanentDescriptionMap the locales and localized permanent descriptions of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPermanentDescriptionMap(
		Map<java.util.Locale, String> permanentDescriptionMap,
		java.util.Locale defaultLocale) {

		_offer.setPermanentDescriptionMap(
			permanentDescriptionMap, defaultLocale);
	}

	/**
	 * Sets the post of this offer.
	 *
	 * @param post the post of this offer
	 */
	@Override
	public void setPost(String post) {
		_offer.setPost(post);
	}

	/**
	 * Sets the localized post of this offer in the language.
	 *
	 * @param post the localized post of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setPost(String post, java.util.Locale locale) {
		_offer.setPost(post, locale);
	}

	/**
	 * Sets the localized post of this offer in the language, and sets the default locale.
	 *
	 * @param post the localized post of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPost(
		String post, java.util.Locale locale, java.util.Locale defaultLocale) {

		_offer.setPost(post, locale, defaultLocale);
	}

	@Override
	public void setPostCurrentLanguageId(String languageId) {
		_offer.setPostCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized posts of this offer from the map of locales and localized posts.
	 *
	 * @param postMap the locales and localized posts of this offer
	 */
	@Override
	public void setPostMap(Map<java.util.Locale, String> postMap) {
		_offer.setPostMap(postMap);
	}

	/**
	 * Sets the localized posts of this offer from the map of locales and localized posts, and sets the default locale.
	 *
	 * @param postMap the locales and localized posts of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPostMap(
		Map<java.util.Locale, String> postMap, java.util.Locale defaultLocale) {

		_offer.setPostMap(postMap, defaultLocale);
	}

	/**
	 * Sets the post number of this offer.
	 *
	 * @param postNumber the post number of this offer
	 */
	@Override
	public void setPostNumber(String postNumber) {
		_offer.setPostNumber(postNumber);
	}

	/**
	 * Sets the primary key of this offer.
	 *
	 * @param primaryKey the primary key of this offer
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_offer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_offer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the profil of this offer.
	 *
	 * @param profil the profil of this offer
	 */
	@Override
	public void setProfil(String profil) {
		_offer.setProfil(profil);
	}

	/**
	 * Sets the localized profil of this offer in the language.
	 *
	 * @param profil the localized profil of this offer
	 * @param locale the locale of the language
	 */
	@Override
	public void setProfil(String profil, java.util.Locale locale) {
		_offer.setProfil(profil, locale);
	}

	/**
	 * Sets the localized profil of this offer in the language, and sets the default locale.
	 *
	 * @param profil the localized profil of this offer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setProfil(
		String profil, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_offer.setProfil(profil, locale, defaultLocale);
	}

	@Override
	public void setProfilCurrentLanguageId(String languageId) {
		_offer.setProfilCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized profils of this offer from the map of locales and localized profils.
	 *
	 * @param profilMap the locales and localized profils of this offer
	 */
	@Override
	public void setProfilMap(Map<java.util.Locale, String> profilMap) {
		_offer.setProfilMap(profilMap);
	}

	/**
	 * Sets the localized profils of this offer from the map of locales and localized profils, and sets the default locale.
	 *
	 * @param profilMap the locales and localized profils of this offer
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setProfilMap(
		Map<java.util.Locale, String> profilMap,
		java.util.Locale defaultLocale) {

		_offer.setProfilMap(profilMap, defaultLocale);
	}

	/**
	 * Sets the publication end date of this offer.
	 *
	 * @param publicationEndDate the publication end date of this offer
	 */
	@Override
	public void setPublicationEndDate(Date publicationEndDate) {
		_offer.setPublicationEndDate(publicationEndDate);
	}

	/**
	 * Sets the publication ID of this offer.
	 *
	 * @param publicationId the publication ID of this offer
	 */
	@Override
	public void setPublicationId(String publicationId) {
		_offer.setPublicationId(publicationId);
	}

	/**
	 * Sets the publication start date of this offer.
	 *
	 * @param publicationStartDate the publication start date of this offer
	 */
	@Override
	public void setPublicationStartDate(Date publicationStartDate) {
		_offer.setPublicationStartDate(publicationStartDate);
	}

	/**
	 * Sets whether this offer is share linkedin.
	 *
	 * @param shareLinkedin the share linkedin of this offer
	 */
	@Override
	public void setShareLinkedin(boolean shareLinkedin) {
		_offer.setShareLinkedin(shareLinkedin);
	}

	/**
	 * Sets the start date of this offer.
	 *
	 * @param startDate the start date of this offer
	 */
	@Override
	public void setStartDate(Date startDate) {
		_offer.setStartDate(startDate);
	}

	/**
	 * Sets the status of this offer.
	 *
	 * @param status the status of this offer
	 */
	@Override
	public void setStatus(int status) {
		_offer.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this offer.
	 *
	 * @param statusByUserId the status by user ID of this offer
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_offer.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this offer.
	 *
	 * @param statusByUserName the status by user name of this offer
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_offer.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this offer.
	 *
	 * @param statusByUserUuid the status by user uuid of this offer
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_offer.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this offer.
	 *
	 * @param statusDate the status date of this offer
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_offer.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this offer.
	 *
	 * @param userId the user ID of this offer
	 */
	@Override
	public void setUserId(long userId) {
		_offer.setUserId(userId);
	}

	/**
	 * Sets the user name of this offer.
	 *
	 * @param userName the user name of this offer
	 */
	@Override
	public void setUserName(String userName) {
		_offer.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this offer.
	 *
	 * @param userUuid the user uuid of this offer
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_offer.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this offer.
	 *
	 * @param uuid the uuid of this offer
	 */
	@Override
	public void setUuid(String uuid) {
		_offer.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.ejob.model.Offer> toCacheModel() {

		return _offer.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.ejob.model.Offer toEscapedModel() {
		return new OfferWrapper(_offer.toEscapedModel());
	}

	@Override
	public String toString() {
		return _offer.toString();
	}

	@Override
	public eu.strasbourg.service.ejob.model.Offer toUnescapedModel() {
		return new OfferWrapper(_offer.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _offer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferWrapper)) {
			return false;
		}

		OfferWrapper offerWrapper = (OfferWrapper)obj;

		if (Objects.equals(_offer, offerWrapper._offer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _offer.getStagedModelType();
	}

	@Override
	public Offer getWrappedModel() {
		return _offer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _offer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _offer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_offer.resetOriginalValues();
	}

	private final Offer _offer;

}
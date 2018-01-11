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

package eu.strasbourg.service.official.model;

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
 * This class is a wrapper for {@link Official}.
 * </p>
 *
 * @author AngeliqueZUNINO
 * @see Official
 * @generated
 */
@ProviderType
public class OfficialWrapper implements Official, ModelWrapper<Official> {
	public OfficialWrapper(Official official) {
		_official = official;
	}

	@Override
	public Class<?> getModelClass() {
		return Official.class;
	}

	@Override
	public String getModelClassName() {
		return Official.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("officialId", getOfficialId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("gender", getGender());
		attributes.put("lastName", getLastName());
		attributes.put("firstName", getFirstName());
		attributes.put("thematicDelegation", getThematicDelegation());
		attributes.put("missions", getMissions());
		attributes.put("wasMinister", getWasMinister());
		attributes.put("contact", getContact());
		attributes.put("orderDeputyMayor", getOrderDeputyMayor());
		attributes.put("orderVicePresident", getOrderVicePresident());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long officialId = (Long)attributes.get("officialId");

		if (officialId != null) {
			setOfficialId(officialId);
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

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
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

		Integer gender = (Integer)attributes.get("gender");

		if (gender != null) {
			setGender(gender);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String thematicDelegation = (String)attributes.get("thematicDelegation");

		if (thematicDelegation != null) {
			setThematicDelegation(thematicDelegation);
		}

		String missions = (String)attributes.get("missions");

		if (missions != null) {
			setMissions(missions);
		}

		Boolean wasMinister = (Boolean)attributes.get("wasMinister");

		if (wasMinister != null) {
			setWasMinister(wasMinister);
		}

		String contact = (String)attributes.get("contact");

		if (contact != null) {
			setContact(contact);
		}

		Integer orderDeputyMayor = (Integer)attributes.get("orderDeputyMayor");

		if (orderDeputyMayor != null) {
			setOrderDeputyMayor(orderDeputyMayor);
		}

		Integer orderVicePresident = (Integer)attributes.get(
				"orderVicePresident");

		if (orderVicePresident != null) {
			setOrderVicePresident(orderVicePresident);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Vérifie si c'est un élu eurométropolitain
	*
	* @throws PortalException
	*/
	@Override
	public boolean getCommunalMembership()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _official.getCommunalMembership();
	}

	/**
	* Returns the was minister of this official.
	*
	* @return the was minister of this official
	*/
	@Override
	public boolean getWasMinister() {
		return _official.getWasMinister();
	}

	/**
	* Returns <code>true</code> if this official is approved.
	*
	* @return <code>true</code> if this official is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _official.isApproved();
	}

	/**
	* Vérifie si c'est un élu municipal
	*
	* @throws PortalException
	*/
	@Override
	public boolean isAssistant()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _official.isAssistant();
	}

	@Override
	public boolean isCachedModel() {
		return _official.isCachedModel();
	}

	/**
	* Vérifie si la catégorie fait parti des catégories correspondants à l'élu
	*/
	@Override
	public boolean isCategoryOfficial(long categoryId) {
		return _official.isCategoryOfficial(categoryId);
	}

	/**
	* Returns <code>true</code> if this official is denied.
	*
	* @return <code>true</code> if this official is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _official.isDenied();
	}

	/**
	* Returns <code>true</code> if this official is a draft.
	*
	* @return <code>true</code> if this official is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _official.isDraft();
	}

	/**
	* Vérifie si c'est un élu eurométropolitain
	*
	* @throws PortalException
	*/
	@Override
	public boolean isEluEurometropole()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _official.isEluEurometropole();
	}

	/**
	* Vérifie si c'est un élu municipal
	*
	* @throws PortalException
	*/
	@Override
	public boolean isEluMunicipal()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _official.isEluMunicipal();
	}

	@Override
	public boolean isEscapedModel() {
		return _official.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this official is expired.
	*
	* @return <code>true</code> if this official is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _official.isExpired();
	}

	/**
	* Returns <code>true</code> if this official is inactive.
	*
	* @return <code>true</code> if this official is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _official.isInactive();
	}

	/**
	* Returns <code>true</code> if this official is incomplete.
	*
	* @return <code>true</code> if this official is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _official.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _official.isNew();
	}

	/**
	* Returns <code>true</code> if this official is pending.
	*
	* @return <code>true</code> if this official is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _official.isPending();
	}

	/**
	* Returns <code>true</code> if this official is scheduled.
	*
	* @return <code>true</code> if this official is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _official.isScheduled();
	}

	/**
	* Returns <code>true</code> if this official is was minister.
	*
	* @return <code>true</code> if this official is was minister; <code>false</code> otherwise
	*/
	@Override
	public boolean isWasMinister() {
		return _official.isWasMinister();
	}

	/**
	* Retourne l' id catégorie Fonction ville correspondant à l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getFonctionCity() {
		return _official.getFonctionCity();
	}

	/**
	* Retourne la catégorie Fonction eurometropole correspondant à l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getFonctionEurometropole() {
		return _official.getFonctionEurometropole();
	}

	/**
	* Retourne la catégorie Fonction commune correspondant à l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getFonctionTown() {
		return _official.getFonctionTown();
	}

	/**
	* Retourne la catégorie Groupe Politique ville correspondant à l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getPoliticalGroupCity() {
		return _official.getPoliticalGroupCity();
	}

	/**
	* Retourne la catégorie Groupe Politique eurométropole correspondant à
	* l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getPoliticalGroupEurometropole() {
		return _official.getPoliticalGroupEurometropole();
	}

	/**
	* Retourne la commune correspondants à l'élu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getTown() {
		return _official.getTown();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _official.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _official.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.official.model.Official> toCacheModel() {
		return _official.toCacheModel();
	}

	/**
	* Renvoie la version live de l'élu, si il existe
	*/
	@Override
	public eu.strasbourg.service.official.model.Official getLiveVersion() {
		return _official.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.official.model.Official toEscapedModel() {
		return new OfficialWrapper(_official.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.official.model.Official toUnescapedModel() {
		return new OfficialWrapper(_official.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.official.model.Official official) {
		return _official.compareTo(official);
	}

	/**
	* Returns the gender of this official.
	*
	* @return the gender of this official
	*/
	@Override
	public int getGender() {
		return _official.getGender();
	}

	/**
	* Returns the order deputy mayor of this official.
	*
	* @return the order deputy mayor of this official
	*/
	@Override
	public int getOrderDeputyMayor() {
		return _official.getOrderDeputyMayor();
	}

	/**
	* Returns the order vice president of this official.
	*
	* @return the order vice president of this official
	*/
	@Override
	public int getOrderVicePresident() {
		return _official.getOrderVicePresident();
	}

	/**
	* Returns the status of this official.
	*
	* @return the status of this official
	*/
	@Override
	public int getStatus() {
		return _official.getStatus();
	}

	@Override
	public int hashCode() {
		return _official.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _official.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this official.
	*
	* @return the image ID of this official
	*/
	@Override
	public java.lang.Long getImageId() {
		return _official.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new OfficialWrapper((Official)_official.clone());
	}

	/**
	* Returns the contact of this official.
	*
	* @return the contact of this official
	*/
	@Override
	public java.lang.String getContact() {
		return _official.getContact();
	}

	/**
	* Returns the localized contact of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized contact of this official
	*/
	@Override
	public java.lang.String getContact(java.lang.String languageId) {
		return _official.getContact(languageId);
	}

	/**
	* Returns the localized contact of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized contact of this official
	*/
	@Override
	public java.lang.String getContact(java.lang.String languageId,
		boolean useDefault) {
		return _official.getContact(languageId, useDefault);
	}

	/**
	* Returns the localized contact of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized contact of this official
	*/
	@Override
	public java.lang.String getContact(java.util.Locale locale) {
		return _official.getContact(locale);
	}

	/**
	* Returns the localized contact of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized contact of this official. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getContact(java.util.Locale locale,
		boolean useDefault) {
		return _official.getContact(locale, useDefault);
	}

	@Override
	public java.lang.String getContactCurrentLanguageId() {
		return _official.getContactCurrentLanguageId();
	}

	@Override
	public java.lang.String getContactCurrentValue() {
		return _official.getContactCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _official.getDefaultLanguageId();
	}

	/**
	* Returns the first name of this official.
	*
	* @return the first name of this official
	*/
	@Override
	public java.lang.String getFirstName() {
		return _official.getFirstName();
	}

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _official.getImageURL();
	}

	/**
	* Returns the last name of this official.
	*
	* @return the last name of this official
	*/
	@Override
	public java.lang.String getLastName() {
		return _official.getLastName();
	}

	/**
	* Returns the missions of this official.
	*
	* @return the missions of this official
	*/
	@Override
	public java.lang.String getMissions() {
		return _official.getMissions();
	}

	/**
	* Returns the localized missions of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized missions of this official
	*/
	@Override
	public java.lang.String getMissions(java.lang.String languageId) {
		return _official.getMissions(languageId);
	}

	/**
	* Returns the localized missions of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized missions of this official
	*/
	@Override
	public java.lang.String getMissions(java.lang.String languageId,
		boolean useDefault) {
		return _official.getMissions(languageId, useDefault);
	}

	/**
	* Returns the localized missions of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized missions of this official
	*/
	@Override
	public java.lang.String getMissions(java.util.Locale locale) {
		return _official.getMissions(locale);
	}

	/**
	* Returns the localized missions of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized missions of this official. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getMissions(java.util.Locale locale,
		boolean useDefault) {
		return _official.getMissions(locale, useDefault);
	}

	@Override
	public java.lang.String getMissionsCurrentLanguageId() {
		return _official.getMissionsCurrentLanguageId();
	}

	@Override
	public java.lang.String getMissionsCurrentValue() {
		return _official.getMissionsCurrentValue();
	}

	/**
	* Retourne le nom féminin/masculin de la catégorie
	*
	* @throws PortalException
	*/
	@Override
	public java.lang.String getName(
		com.liferay.asset.kernel.model.AssetCategory category,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _official.getName(category, locale);
	}

	/**
	* Returns the status by user name of this official.
	*
	* @return the status by user name of this official
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _official.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this official.
	*
	* @return the status by user uuid of this official
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _official.getStatusByUserUuid();
	}

	/**
	* Returns the thematic delegation of this official.
	*
	* @return the thematic delegation of this official
	*/
	@Override
	public java.lang.String getThematicDelegation() {
		return _official.getThematicDelegation();
	}

	/**
	* Returns the localized thematic delegation of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized thematic delegation of this official
	*/
	@Override
	public java.lang.String getThematicDelegation(java.lang.String languageId) {
		return _official.getThematicDelegation(languageId);
	}

	/**
	* Returns the localized thematic delegation of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized thematic delegation of this official
	*/
	@Override
	public java.lang.String getThematicDelegation(java.lang.String languageId,
		boolean useDefault) {
		return _official.getThematicDelegation(languageId, useDefault);
	}

	/**
	* Returns the localized thematic delegation of this official in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized thematic delegation of this official
	*/
	@Override
	public java.lang.String getThematicDelegation(java.util.Locale locale) {
		return _official.getThematicDelegation(locale);
	}

	/**
	* Returns the localized thematic delegation of this official in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized thematic delegation of this official. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getThematicDelegation(java.util.Locale locale,
		boolean useDefault) {
		return _official.getThematicDelegation(locale, useDefault);
	}

	@Override
	public java.lang.String getThematicDelegationCurrentLanguageId() {
		return _official.getThematicDelegationCurrentLanguageId();
	}

	@Override
	public java.lang.String getThematicDelegationCurrentValue() {
		return _official.getThematicDelegationCurrentValue();
	}

	/**
	* Returns the user name of this official.
	*
	* @return the user name of this official
	*/
	@Override
	public java.lang.String getUserName() {
		return _official.getUserName();
	}

	/**
	* Returns the user uuid of this official.
	*
	* @return the user uuid of this official
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _official.getUserUuid();
	}

	/**
	* Returns the uuid of this official.
	*
	* @return the uuid of this official
	*/
	@Override
	public java.lang.String getUuid() {
		return _official.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _official.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _official.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _official.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this official.
	*
	* @return the create date of this official
	*/
	@Override
	public Date getCreateDate() {
		return _official.getCreateDate();
	}

	/**
	* Returns the last publish date of this official.
	*
	* @return the last publish date of this official
	*/
	@Override
	public Date getLastPublishDate() {
		return _official.getLastPublishDate();
	}

	/**
	* Returns the modified date of this official.
	*
	* @return the modified date of this official
	*/
	@Override
	public Date getModifiedDate() {
		return _official.getModifiedDate();
	}

	/**
	* Returns the status date of this official.
	*
	* @return the status date of this official
	*/
	@Override
	public Date getStatusDate() {
		return _official.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _official.getCategories();
	}

	/**
	* Retourne la liste des quartiers correspondants à l'élu
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistricts() {
		return _official.getDistricts();
	}

	/**
	* Retourne la catégorie Autres mandats correspondant à l'élu
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getOthersMandates() {
		return _official.getOthersMandates();
	}

	/**
	* Retourne les territoires du lieu
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories() {
		return _official.getTerritories();
	}

	/**
	* Returns a map of the locales and localized contacts of this official.
	*
	* @return the locales and localized contacts of this official
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getContactMap() {
		return _official.getContactMap();
	}

	/**
	* Returns a map of the locales and localized missionses of this official.
	*
	* @return the locales and localized missionses of this official
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getMissionsMap() {
		return _official.getMissionsMap();
	}

	/**
	* Returns a map of the locales and localized thematic delegations of this official.
	*
	* @return the locales and localized thematic delegations of this official
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getThematicDelegationMap() {
		return _official.getThematicDelegationMap();
	}

	/**
	* Returns the company ID of this official.
	*
	* @return the company ID of this official
	*/
	@Override
	public long getCompanyId() {
		return _official.getCompanyId();
	}

	/**
	* Returns the group ID of this official.
	*
	* @return the group ID of this official
	*/
	@Override
	public long getGroupId() {
		return _official.getGroupId();
	}

	/**
	* Returns the official ID of this official.
	*
	* @return the official ID of this official
	*/
	@Override
	public long getOfficialId() {
		return _official.getOfficialId();
	}

	/**
	* Returns the primary key of this official.
	*
	* @return the primary key of this official
	*/
	@Override
	public long getPrimaryKey() {
		return _official.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this official.
	*
	* @return the status by user ID of this official
	*/
	@Override
	public long getStatusByUserId() {
		return _official.getStatusByUserId();
	}

	/**
	* Returns the user ID of this official.
	*
	* @return the user ID of this official
	*/
	@Override
	public long getUserId() {
		return _official.getUserId();
	}

	@Override
	public void persist() {
		_official.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_official.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_official.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_official.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this official.
	*
	* @param companyId the company ID of this official
	*/
	@Override
	public void setCompanyId(long companyId) {
		_official.setCompanyId(companyId);
	}

	/**
	* Sets the contact of this official.
	*
	* @param contact the contact of this official
	*/
	@Override
	public void setContact(java.lang.String contact) {
		_official.setContact(contact);
	}

	/**
	* Sets the localized contact of this official in the language.
	*
	* @param contact the localized contact of this official
	* @param locale the locale of the language
	*/
	@Override
	public void setContact(java.lang.String contact, java.util.Locale locale) {
		_official.setContact(contact, locale);
	}

	/**
	* Sets the localized contact of this official in the language, and sets the default locale.
	*
	* @param contact the localized contact of this official
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContact(java.lang.String contact, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_official.setContact(contact, locale, defaultLocale);
	}

	@Override
	public void setContactCurrentLanguageId(java.lang.String languageId) {
		_official.setContactCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contacts of this official from the map of locales and localized contacts.
	*
	* @param contactMap the locales and localized contacts of this official
	*/
	@Override
	public void setContactMap(
		Map<java.util.Locale, java.lang.String> contactMap) {
		_official.setContactMap(contactMap);
	}

	/**
	* Sets the localized contacts of this official from the map of locales and localized contacts, and sets the default locale.
	*
	* @param contactMap the locales and localized contacts of this official
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContactMap(
		Map<java.util.Locale, java.lang.String> contactMap,
		java.util.Locale defaultLocale) {
		_official.setContactMap(contactMap, defaultLocale);
	}

	/**
	* Sets the create date of this official.
	*
	* @param createDate the create date of this official
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_official.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_official.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_official.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_official.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the first name of this official.
	*
	* @param firstName the first name of this official
	*/
	@Override
	public void setFirstName(java.lang.String firstName) {
		_official.setFirstName(firstName);
	}

	/**
	* Sets the gender of this official.
	*
	* @param gender the gender of this official
	*/
	@Override
	public void setGender(int gender) {
		_official.setGender(gender);
	}

	/**
	* Sets the group ID of this official.
	*
	* @param groupId the group ID of this official
	*/
	@Override
	public void setGroupId(long groupId) {
		_official.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this official.
	*
	* @param imageId the image ID of this official
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_official.setImageId(imageId);
	}

	/**
	* Sets the last name of this official.
	*
	* @param lastName the last name of this official
	*/
	@Override
	public void setLastName(java.lang.String lastName) {
		_official.setLastName(lastName);
	}

	/**
	* Sets the last publish date of this official.
	*
	* @param lastPublishDate the last publish date of this official
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_official.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the missions of this official.
	*
	* @param missions the missions of this official
	*/
	@Override
	public void setMissions(java.lang.String missions) {
		_official.setMissions(missions);
	}

	/**
	* Sets the localized missions of this official in the language.
	*
	* @param missions the localized missions of this official
	* @param locale the locale of the language
	*/
	@Override
	public void setMissions(java.lang.String missions, java.util.Locale locale) {
		_official.setMissions(missions, locale);
	}

	/**
	* Sets the localized missions of this official in the language, and sets the default locale.
	*
	* @param missions the localized missions of this official
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMissions(java.lang.String missions, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_official.setMissions(missions, locale, defaultLocale);
	}

	@Override
	public void setMissionsCurrentLanguageId(java.lang.String languageId) {
		_official.setMissionsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized missionses of this official from the map of locales and localized missionses.
	*
	* @param missionsMap the locales and localized missionses of this official
	*/
	@Override
	public void setMissionsMap(
		Map<java.util.Locale, java.lang.String> missionsMap) {
		_official.setMissionsMap(missionsMap);
	}

	/**
	* Sets the localized missionses of this official from the map of locales and localized missionses, and sets the default locale.
	*
	* @param missionsMap the locales and localized missionses of this official
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMissionsMap(
		Map<java.util.Locale, java.lang.String> missionsMap,
		java.util.Locale defaultLocale) {
		_official.setMissionsMap(missionsMap, defaultLocale);
	}

	/**
	* Sets the modified date of this official.
	*
	* @param modifiedDate the modified date of this official
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_official.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_official.setNew(n);
	}

	/**
	* Sets the official ID of this official.
	*
	* @param officialId the official ID of this official
	*/
	@Override
	public void setOfficialId(long officialId) {
		_official.setOfficialId(officialId);
	}

	/**
	* Sets the order deputy mayor of this official.
	*
	* @param orderDeputyMayor the order deputy mayor of this official
	*/
	@Override
	public void setOrderDeputyMayor(int orderDeputyMayor) {
		_official.setOrderDeputyMayor(orderDeputyMayor);
	}

	/**
	* Sets the order vice president of this official.
	*
	* @param orderVicePresident the order vice president of this official
	*/
	@Override
	public void setOrderVicePresident(int orderVicePresident) {
		_official.setOrderVicePresident(orderVicePresident);
	}

	/**
	* Sets the primary key of this official.
	*
	* @param primaryKey the primary key of this official
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_official.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_official.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this official.
	*
	* @param status the status of this official
	*/
	@Override
	public void setStatus(int status) {
		_official.setStatus(status);
	}

	/**
	* Sets the status by user ID of this official.
	*
	* @param statusByUserId the status by user ID of this official
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_official.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this official.
	*
	* @param statusByUserName the status by user name of this official
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_official.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this official.
	*
	* @param statusByUserUuid the status by user uuid of this official
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_official.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this official.
	*
	* @param statusDate the status date of this official
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_official.setStatusDate(statusDate);
	}

	/**
	* Sets the thematic delegation of this official.
	*
	* @param thematicDelegation the thematic delegation of this official
	*/
	@Override
	public void setThematicDelegation(java.lang.String thematicDelegation) {
		_official.setThematicDelegation(thematicDelegation);
	}

	/**
	* Sets the localized thematic delegation of this official in the language.
	*
	* @param thematicDelegation the localized thematic delegation of this official
	* @param locale the locale of the language
	*/
	@Override
	public void setThematicDelegation(java.lang.String thematicDelegation,
		java.util.Locale locale) {
		_official.setThematicDelegation(thematicDelegation, locale);
	}

	/**
	* Sets the localized thematic delegation of this official in the language, and sets the default locale.
	*
	* @param thematicDelegation the localized thematic delegation of this official
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setThematicDelegation(java.lang.String thematicDelegation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_official.setThematicDelegation(thematicDelegation, locale,
			defaultLocale);
	}

	@Override
	public void setThematicDelegationCurrentLanguageId(
		java.lang.String languageId) {
		_official.setThematicDelegationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized thematic delegations of this official from the map of locales and localized thematic delegations.
	*
	* @param thematicDelegationMap the locales and localized thematic delegations of this official
	*/
	@Override
	public void setThematicDelegationMap(
		Map<java.util.Locale, java.lang.String> thematicDelegationMap) {
		_official.setThematicDelegationMap(thematicDelegationMap);
	}

	/**
	* Sets the localized thematic delegations of this official from the map of locales and localized thematic delegations, and sets the default locale.
	*
	* @param thematicDelegationMap the locales and localized thematic delegations of this official
	* @param defaultLocale the default locale
	*/
	@Override
	public void setThematicDelegationMap(
		Map<java.util.Locale, java.lang.String> thematicDelegationMap,
		java.util.Locale defaultLocale) {
		_official.setThematicDelegationMap(thematicDelegationMap, defaultLocale);
	}

	/**
	* Sets the user ID of this official.
	*
	* @param userId the user ID of this official
	*/
	@Override
	public void setUserId(long userId) {
		_official.setUserId(userId);
	}

	/**
	* Sets the user name of this official.
	*
	* @param userName the user name of this official
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_official.setUserName(userName);
	}

	/**
	* Sets the user uuid of this official.
	*
	* @param userUuid the user uuid of this official
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_official.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this official.
	*
	* @param uuid the uuid of this official
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_official.setUuid(uuid);
	}

	/**
	* Sets whether this official is was minister.
	*
	* @param wasMinister the was minister of this official
	*/
	@Override
	public void setWasMinister(boolean wasMinister) {
		_official.setWasMinister(wasMinister);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficialWrapper)) {
			return false;
		}

		OfficialWrapper officialWrapper = (OfficialWrapper)obj;

		if (Objects.equals(_official, officialWrapper._official)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _official.getStagedModelType();
	}

	@Override
	public Official getWrappedModel() {
		return _official;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _official.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _official.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_official.resetOriginalValues();
	}

	private final Official _official;
}
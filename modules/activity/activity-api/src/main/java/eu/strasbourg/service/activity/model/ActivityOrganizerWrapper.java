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

package eu.strasbourg.service.activity.model;

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
 * This class is a wrapper for {@link ActivityOrganizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizer
 * @generated
 */
@ProviderType
public class ActivityOrganizerWrapper implements ActivityOrganizer,
	ModelWrapper<ActivityOrganizer> {
	public ActivityOrganizerWrapper(ActivityOrganizer activityOrganizer) {
		_activityOrganizer = activityOrganizer;
	}

	@Override
	public Class<?> getModelClass() {
		return ActivityOrganizer.class;
	}

	@Override
	public String getModelClassName() {
		return ActivityOrganizer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityOrganizerId", getActivityOrganizerId());
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
		attributes.put("name", getName());
		attributes.put("presentation", getPresentation());
		attributes.put("address", getAddress());
		attributes.put("phone", getPhone());
		attributes.put("mail", getMail());
		attributes.put("siteURL", getSiteURL());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityOrganizerId = (Long)attributes.get("activityOrganizerId");

		if (activityOrganizerId != null) {
			setActivityOrganizerId(activityOrganizerId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String presentation = (String)attributes.get("presentation");

		if (presentation != null) {
			setPresentation(presentation);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		String siteURL = (String)attributes.get("siteURL");

		if (siteURL != null) {
			setSiteURL(siteURL);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Returns <code>true</code> if this activity organizer is approved.
	*
	* @return <code>true</code> if this activity organizer is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _activityOrganizer.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _activityOrganizer.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this activity organizer is denied.
	*
	* @return <code>true</code> if this activity organizer is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _activityOrganizer.isDenied();
	}

	/**
	* Returns <code>true</code> if this activity organizer is a draft.
	*
	* @return <code>true</code> if this activity organizer is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _activityOrganizer.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _activityOrganizer.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this activity organizer is expired.
	*
	* @return <code>true</code> if this activity organizer is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _activityOrganizer.isExpired();
	}

	/**
	* Returns <code>true</code> if this activity organizer is inactive.
	*
	* @return <code>true</code> if this activity organizer is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _activityOrganizer.isInactive();
	}

	/**
	* Returns <code>true</code> if this activity organizer is incomplete.
	*
	* @return <code>true</code> if this activity organizer is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _activityOrganizer.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _activityOrganizer.isNew();
	}

	/**
	* Returns <code>true</code> if this activity organizer is pending.
	*
	* @return <code>true</code> if this activity organizer is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _activityOrganizer.isPending();
	}

	/**
	* Returns <code>true</code> if this activity organizer is scheduled.
	*
	* @return <code>true</code> if this activity organizer is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _activityOrganizer.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _activityOrganizer.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _activityOrganizer.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.ActivityOrganizer> toCacheModel() {
		return _activityOrganizer.toCacheModel();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer getLiveVersion() {
		return _activityOrganizer.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer toEscapedModel() {
		return new ActivityOrganizerWrapper(_activityOrganizer.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer toUnescapedModel() {
		return new ActivityOrganizerWrapper(_activityOrganizer.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.ActivityOrganizer activityOrganizer) {
		return _activityOrganizer.compareTo(activityOrganizer);
	}

	/**
	* Returns the status of this activity organizer.
	*
	* @return the status of this activity organizer
	*/
	@Override
	public int getStatus() {
		return _activityOrganizer.getStatus();
	}

	@Override
	public int hashCode() {
		return _activityOrganizer.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityOrganizer.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ActivityOrganizerWrapper((ActivityOrganizer)_activityOrganizer.clone());
	}

	/**
	* Returns the address of this activity organizer.
	*
	* @return the address of this activity organizer
	*/
	@Override
	public java.lang.String getAddress() {
		return _activityOrganizer.getAddress();
	}

	/**
	* Returns the localized address of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized address of this activity organizer
	*/
	@Override
	public java.lang.String getAddress(java.lang.String languageId) {
		return _activityOrganizer.getAddress(languageId);
	}

	/**
	* Returns the localized address of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized address of this activity organizer
	*/
	@Override
	public java.lang.String getAddress(java.lang.String languageId,
		boolean useDefault) {
		return _activityOrganizer.getAddress(languageId, useDefault);
	}

	/**
	* Returns the localized address of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized address of this activity organizer
	*/
	@Override
	public java.lang.String getAddress(java.util.Locale locale) {
		return _activityOrganizer.getAddress(locale);
	}

	/**
	* Returns the localized address of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized address of this activity organizer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAddress(java.util.Locale locale,
		boolean useDefault) {
		return _activityOrganizer.getAddress(locale, useDefault);
	}

	@Override
	public java.lang.String getAddressCurrentLanguageId() {
		return _activityOrganizer.getAddressCurrentLanguageId();
	}

	@Override
	public java.lang.String getAddressCurrentValue() {
		return _activityOrganizer.getAddressCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _activityOrganizer.getDefaultLanguageId();
	}

	/**
	* Retourne l'URL de l'image
	*/
	@Override
	public java.lang.String getImageURL() {
		return _activityOrganizer.getImageURL();
	}

	/**
	* Returns the mail of this activity organizer.
	*
	* @return the mail of this activity organizer
	*/
	@Override
	public java.lang.String getMail() {
		return _activityOrganizer.getMail();
	}

	/**
	* Returns the name of this activity organizer.
	*
	* @return the name of this activity organizer
	*/
	@Override
	public java.lang.String getName() {
		return _activityOrganizer.getName();
	}

	/**
	* Returns the localized name of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this activity organizer
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _activityOrganizer.getName(languageId);
	}

	/**
	* Returns the localized name of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this activity organizer
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _activityOrganizer.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this activity organizer
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _activityOrganizer.getName(locale);
	}

	/**
	* Returns the localized name of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this activity organizer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _activityOrganizer.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _activityOrganizer.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _activityOrganizer.getNameCurrentValue();
	}

	/**
	* Returns the phone of this activity organizer.
	*
	* @return the phone of this activity organizer
	*/
	@Override
	public java.lang.String getPhone() {
		return _activityOrganizer.getPhone();
	}

	/**
	* Returns the presentation of this activity organizer.
	*
	* @return the presentation of this activity organizer
	*/
	@Override
	public java.lang.String getPresentation() {
		return _activityOrganizer.getPresentation();
	}

	/**
	* Returns the localized presentation of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized presentation of this activity organizer
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId) {
		return _activityOrganizer.getPresentation(languageId);
	}

	/**
	* Returns the localized presentation of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this activity organizer
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId,
		boolean useDefault) {
		return _activityOrganizer.getPresentation(languageId, useDefault);
	}

	/**
	* Returns the localized presentation of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized presentation of this activity organizer
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale) {
		return _activityOrganizer.getPresentation(locale);
	}

	/**
	* Returns the localized presentation of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this activity organizer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale,
		boolean useDefault) {
		return _activityOrganizer.getPresentation(locale, useDefault);
	}

	@Override
	public java.lang.String getPresentationCurrentLanguageId() {
		return _activityOrganizer.getPresentationCurrentLanguageId();
	}

	@Override
	public java.lang.String getPresentationCurrentValue() {
		return _activityOrganizer.getPresentationCurrentValue();
	}

	/**
	* Returns the site url of this activity organizer.
	*
	* @return the site url of this activity organizer
	*/
	@Override
	public java.lang.String getSiteURL() {
		return _activityOrganizer.getSiteURL();
	}

	/**
	* Returns the localized site url of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized site url of this activity organizer
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId) {
		return _activityOrganizer.getSiteURL(languageId);
	}

	/**
	* Returns the localized site url of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this activity organizer
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId,
		boolean useDefault) {
		return _activityOrganizer.getSiteURL(languageId, useDefault);
	}

	/**
	* Returns the localized site url of this activity organizer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized site url of this activity organizer
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale) {
		return _activityOrganizer.getSiteURL(locale);
	}

	/**
	* Returns the localized site url of this activity organizer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this activity organizer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale,
		boolean useDefault) {
		return _activityOrganizer.getSiteURL(locale, useDefault);
	}

	@Override
	public java.lang.String getSiteURLCurrentLanguageId() {
		return _activityOrganizer.getSiteURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getSiteURLCurrentValue() {
		return _activityOrganizer.getSiteURLCurrentValue();
	}

	/**
	* Returns the status by user name of this activity organizer.
	*
	* @return the status by user name of this activity organizer
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _activityOrganizer.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this activity organizer.
	*
	* @return the status by user uuid of this activity organizer
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _activityOrganizer.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this activity organizer.
	*
	* @return the user name of this activity organizer
	*/
	@Override
	public java.lang.String getUserName() {
		return _activityOrganizer.getUserName();
	}

	/**
	* Returns the user uuid of this activity organizer.
	*
	* @return the user uuid of this activity organizer
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _activityOrganizer.getUserUuid();
	}

	/**
	* Returns the uuid of this activity organizer.
	*
	* @return the uuid of this activity organizer
	*/
	@Override
	public java.lang.String getUuid() {
		return _activityOrganizer.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _activityOrganizer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _activityOrganizer.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _activityOrganizer.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this activity organizer.
	*
	* @return the create date of this activity organizer
	*/
	@Override
	public Date getCreateDate() {
		return _activityOrganizer.getCreateDate();
	}

	/**
	* Returns the modified date of this activity organizer.
	*
	* @return the modified date of this activity organizer
	*/
	@Override
	public Date getModifiedDate() {
		return _activityOrganizer.getModifiedDate();
	}

	/**
	* Returns the status date of this activity organizer.
	*
	* @return the status date of this activity organizer
	*/
	@Override
	public Date getStatusDate() {
		return _activityOrganizer.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _activityOrganizer.getCategories();
	}

	/**
	* Returns a map of the locales and localized addresses of this activity organizer.
	*
	* @return the locales and localized addresses of this activity organizer
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAddressMap() {
		return _activityOrganizer.getAddressMap();
	}

	/**
	* Returns a map of the locales and localized names of this activity organizer.
	*
	* @return the locales and localized names of this activity organizer
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _activityOrganizer.getNameMap();
	}

	/**
	* Returns a map of the locales and localized presentations of this activity organizer.
	*
	* @return the locales and localized presentations of this activity organizer
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPresentationMap() {
		return _activityOrganizer.getPresentationMap();
	}

	/**
	* Returns a map of the locales and localized site urls of this activity organizer.
	*
	* @return the locales and localized site urls of this activity organizer
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSiteURLMap() {
		return _activityOrganizer.getSiteURLMap();
	}

	/**
	* Returns the activity organizer ID of this activity organizer.
	*
	* @return the activity organizer ID of this activity organizer
	*/
	@Override
	public long getActivityOrganizerId() {
		return _activityOrganizer.getActivityOrganizerId();
	}

	/**
	* Returns the company ID of this activity organizer.
	*
	* @return the company ID of this activity organizer
	*/
	@Override
	public long getCompanyId() {
		return _activityOrganizer.getCompanyId();
	}

	/**
	* Returns the group ID of this activity organizer.
	*
	* @return the group ID of this activity organizer
	*/
	@Override
	public long getGroupId() {
		return _activityOrganizer.getGroupId();
	}

	/**
	* Returns the image ID of this activity organizer.
	*
	* @return the image ID of this activity organizer
	*/
	@Override
	public long getImageId() {
		return _activityOrganizer.getImageId();
	}

	/**
	* Returns the primary key of this activity organizer.
	*
	* @return the primary key of this activity organizer
	*/
	@Override
	public long getPrimaryKey() {
		return _activityOrganizer.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this activity organizer.
	*
	* @return the status by user ID of this activity organizer
	*/
	@Override
	public long getStatusByUserId() {
		return _activityOrganizer.getStatusByUserId();
	}

	/**
	* Returns the user ID of this activity organizer.
	*
	* @return the user ID of this activity organizer
	*/
	@Override
	public long getUserId() {
		return _activityOrganizer.getUserId();
	}

	@Override
	public void persist() {
		_activityOrganizer.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityOrganizer.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityOrganizer.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the activity organizer ID of this activity organizer.
	*
	* @param activityOrganizerId the activity organizer ID of this activity organizer
	*/
	@Override
	public void setActivityOrganizerId(long activityOrganizerId) {
		_activityOrganizer.setActivityOrganizerId(activityOrganizerId);
	}

	/**
	* Sets the address of this activity organizer.
	*
	* @param address the address of this activity organizer
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_activityOrganizer.setAddress(address);
	}

	/**
	* Sets the localized address of this activity organizer in the language.
	*
	* @param address the localized address of this activity organizer
	* @param locale the locale of the language
	*/
	@Override
	public void setAddress(java.lang.String address, java.util.Locale locale) {
		_activityOrganizer.setAddress(address, locale);
	}

	/**
	* Sets the localized address of this activity organizer in the language, and sets the default locale.
	*
	* @param address the localized address of this activity organizer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAddress(java.lang.String address, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setAddress(address, locale, defaultLocale);
	}

	@Override
	public void setAddressCurrentLanguageId(java.lang.String languageId) {
		_activityOrganizer.setAddressCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized addresses of this activity organizer from the map of locales and localized addresses.
	*
	* @param addressMap the locales and localized addresses of this activity organizer
	*/
	@Override
	public void setAddressMap(
		Map<java.util.Locale, java.lang.String> addressMap) {
		_activityOrganizer.setAddressMap(addressMap);
	}

	/**
	* Sets the localized addresses of this activity organizer from the map of locales and localized addresses, and sets the default locale.
	*
	* @param addressMap the locales and localized addresses of this activity organizer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAddressMap(
		Map<java.util.Locale, java.lang.String> addressMap,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setAddressMap(addressMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_activityOrganizer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this activity organizer.
	*
	* @param companyId the company ID of this activity organizer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_activityOrganizer.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this activity organizer.
	*
	* @param createDate the create date of this activity organizer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_activityOrganizer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_activityOrganizer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_activityOrganizer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_activityOrganizer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this activity organizer.
	*
	* @param groupId the group ID of this activity organizer
	*/
	@Override
	public void setGroupId(long groupId) {
		_activityOrganizer.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this activity organizer.
	*
	* @param imageId the image ID of this activity organizer
	*/
	@Override
	public void setImageId(long imageId) {
		_activityOrganizer.setImageId(imageId);
	}

	/**
	* Sets the mail of this activity organizer.
	*
	* @param mail the mail of this activity organizer
	*/
	@Override
	public void setMail(java.lang.String mail) {
		_activityOrganizer.setMail(mail);
	}

	/**
	* Sets the modified date of this activity organizer.
	*
	* @param modifiedDate the modified date of this activity organizer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_activityOrganizer.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this activity organizer.
	*
	* @param name the name of this activity organizer
	*/
	@Override
	public void setName(java.lang.String name) {
		_activityOrganizer.setName(name);
	}

	/**
	* Sets the localized name of this activity organizer in the language.
	*
	* @param name the localized name of this activity organizer
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_activityOrganizer.setName(name, locale);
	}

	/**
	* Sets the localized name of this activity organizer in the language, and sets the default locale.
	*
	* @param name the localized name of this activity organizer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_activityOrganizer.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this activity organizer from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this activity organizer
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_activityOrganizer.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this activity organizer from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this activity organizer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_activityOrganizer.setNew(n);
	}

	/**
	* Sets the phone of this activity organizer.
	*
	* @param phone the phone of this activity organizer
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_activityOrganizer.setPhone(phone);
	}

	/**
	* Sets the presentation of this activity organizer.
	*
	* @param presentation the presentation of this activity organizer
	*/
	@Override
	public void setPresentation(java.lang.String presentation) {
		_activityOrganizer.setPresentation(presentation);
	}

	/**
	* Sets the localized presentation of this activity organizer in the language.
	*
	* @param presentation the localized presentation of this activity organizer
	* @param locale the locale of the language
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale) {
		_activityOrganizer.setPresentation(presentation, locale);
	}

	/**
	* Sets the localized presentation of this activity organizer in the language, and sets the default locale.
	*
	* @param presentation the localized presentation of this activity organizer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_activityOrganizer.setPresentation(presentation, locale, defaultLocale);
	}

	@Override
	public void setPresentationCurrentLanguageId(java.lang.String languageId) {
		_activityOrganizer.setPresentationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized presentations of this activity organizer from the map of locales and localized presentations.
	*
	* @param presentationMap the locales and localized presentations of this activity organizer
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap) {
		_activityOrganizer.setPresentationMap(presentationMap);
	}

	/**
	* Sets the localized presentations of this activity organizer from the map of locales and localized presentations, and sets the default locale.
	*
	* @param presentationMap the locales and localized presentations of this activity organizer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setPresentationMap(presentationMap, defaultLocale);
	}

	/**
	* Sets the primary key of this activity organizer.
	*
	* @param primaryKey the primary key of this activity organizer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_activityOrganizer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_activityOrganizer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the site url of this activity organizer.
	*
	* @param siteURL the site url of this activity organizer
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL) {
		_activityOrganizer.setSiteURL(siteURL);
	}

	/**
	* Sets the localized site url of this activity organizer in the language.
	*
	* @param siteURL the localized site url of this activity organizer
	* @param locale the locale of the language
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale) {
		_activityOrganizer.setSiteURL(siteURL, locale);
	}

	/**
	* Sets the localized site url of this activity organizer in the language, and sets the default locale.
	*
	* @param siteURL the localized site url of this activity organizer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setSiteURL(siteURL, locale, defaultLocale);
	}

	@Override
	public void setSiteURLCurrentLanguageId(java.lang.String languageId) {
		_activityOrganizer.setSiteURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized site urls of this activity organizer from the map of locales and localized site urls.
	*
	* @param siteURLMap the locales and localized site urls of this activity organizer
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap) {
		_activityOrganizer.setSiteURLMap(siteURLMap);
	}

	/**
	* Sets the localized site urls of this activity organizer from the map of locales and localized site urls, and sets the default locale.
	*
	* @param siteURLMap the locales and localized site urls of this activity organizer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap,
		java.util.Locale defaultLocale) {
		_activityOrganizer.setSiteURLMap(siteURLMap, defaultLocale);
	}

	/**
	* Sets the status of this activity organizer.
	*
	* @param status the status of this activity organizer
	*/
	@Override
	public void setStatus(int status) {
		_activityOrganizer.setStatus(status);
	}

	/**
	* Sets the status by user ID of this activity organizer.
	*
	* @param statusByUserId the status by user ID of this activity organizer
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_activityOrganizer.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this activity organizer.
	*
	* @param statusByUserName the status by user name of this activity organizer
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_activityOrganizer.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this activity organizer.
	*
	* @param statusByUserUuid the status by user uuid of this activity organizer
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_activityOrganizer.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this activity organizer.
	*
	* @param statusDate the status date of this activity organizer
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_activityOrganizer.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this activity organizer.
	*
	* @param userId the user ID of this activity organizer
	*/
	@Override
	public void setUserId(long userId) {
		_activityOrganizer.setUserId(userId);
	}

	/**
	* Sets the user name of this activity organizer.
	*
	* @param userName the user name of this activity organizer
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_activityOrganizer.setUserName(userName);
	}

	/**
	* Sets the user uuid of this activity organizer.
	*
	* @param userUuid the user uuid of this activity organizer
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_activityOrganizer.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this activity organizer.
	*
	* @param uuid the uuid of this activity organizer
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_activityOrganizer.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityOrganizerWrapper)) {
			return false;
		}

		ActivityOrganizerWrapper activityOrganizerWrapper = (ActivityOrganizerWrapper)obj;

		if (Objects.equals(_activityOrganizer,
					activityOrganizerWrapper._activityOrganizer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _activityOrganizer.getStagedModelType();
	}

	@Override
	public ActivityOrganizer getWrappedModel() {
		return _activityOrganizer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _activityOrganizer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _activityOrganizer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_activityOrganizer.resetOriginalValues();
	}

	private final ActivityOrganizer _activityOrganizer;
}
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

package eu.strasbourg.service.interest.model;

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
 * This class is a wrapper for {@link Interest}.
 * </p>
 *
 * @author BenjaminBini
 * @see Interest
 * @generated
 */
@ProviderType
public class InterestWrapper implements Interest, ModelWrapper<Interest> {
	public InterestWrapper(Interest interest) {
		_interest = interest;
	}

	@Override
	public Class<?> getModelClass() {
		return Interest.class;
	}

	@Override
	public String getModelClassName() {
		return Interest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("interestId", getInterestId());
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
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("typeId", getTypeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long interestId = (Long)attributes.get("interestId");

		if (interestId != null) {
			setInterestId(interestId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}
	}

	/**
	* Returns <code>true</code> if this interest is approved.
	*
	* @return <code>true</code> if this interest is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _interest.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _interest.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this interest is denied.
	*
	* @return <code>true</code> if this interest is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _interest.isDenied();
	}

	/**
	* Returns <code>true</code> if this interest is a draft.
	*
	* @return <code>true</code> if this interest is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _interest.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _interest.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this interest is expired.
	*
	* @return <code>true</code> if this interest is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _interest.isExpired();
	}

	/**
	* Returns <code>true</code> if this interest is inactive.
	*
	* @return <code>true</code> if this interest is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _interest.isInactive();
	}

	/**
	* Returns <code>true</code> if this interest is incomplete.
	*
	* @return <code>true</code> if this interest is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _interest.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _interest.isNew();
	}

	/**
	* Returns <code>true</code> if this interest is pending.
	*
	* @return <code>true</code> if this interest is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _interest.isPending();
	}

	/**
	* Returns <code>true</code> if this interest is scheduled.
	*
	* @return <code>true</code> if this interest is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _interest.isScheduled();
	}

	/**
	* Retourne le type du centre d'intérêt
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getType() {
		return _interest.getType();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _interest.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _interest.getExpandoBridge();
	}

	/**
	* Retourne la version JSON du centre d'intérêt
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _interest.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.interest.model.Interest> toCacheModel() {
		return _interest.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.interest.model.Interest toEscapedModel() {
		return new InterestWrapper(_interest.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.interest.model.Interest toUnescapedModel() {
		return new InterestWrapper(_interest.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.interest.model.Interest interest) {
		return _interest.compareTo(interest);
	}

	/**
	* Returns the status of this interest.
	*
	* @return the status of this interest
	*/
	@Override
	public int getStatus() {
		return _interest.getStatus();
	}

	@Override
	public int hashCode() {
		return _interest.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _interest.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new InterestWrapper((Interest)_interest.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _interest.getDefaultLanguageId();
	}

	/**
	* Returns the description of this interest.
	*
	* @return the description of this interest
	*/
	@Override
	public java.lang.String getDescription() {
		return _interest.getDescription();
	}

	/**
	* Returns the localized description of this interest in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this interest
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _interest.getDescription(languageId);
	}

	/**
	* Returns the localized description of this interest in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this interest
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _interest.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this interest in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this interest
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _interest.getDescription(locale);
	}

	/**
	* Returns the localized description of this interest in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this interest. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _interest.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _interest.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _interest.getDescriptionCurrentValue();
	}

	/**
	* Returns the status by user name of this interest.
	*
	* @return the status by user name of this interest
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _interest.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this interest.
	*
	* @return the status by user uuid of this interest
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _interest.getStatusByUserUuid();
	}

	/**
	* Returns the title of this interest.
	*
	* @return the title of this interest
	*/
	@Override
	public java.lang.String getTitle() {
		return _interest.getTitle();
	}

	/**
	* Returns the localized title of this interest in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this interest
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _interest.getTitle(languageId);
	}

	/**
	* Returns the localized title of this interest in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this interest
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _interest.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this interest in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this interest
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _interest.getTitle(locale);
	}

	/**
	* Returns the localized title of this interest in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this interest. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _interest.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _interest.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _interest.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this interest.
	*
	* @return the user name of this interest
	*/
	@Override
	public java.lang.String getUserName() {
		return _interest.getUserName();
	}

	/**
	* Returns the user uuid of this interest.
	*
	* @return the user uuid of this interest
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _interest.getUserUuid();
	}

	/**
	* Returns the uuid of this interest.
	*
	* @return the uuid of this interest
	*/
	@Override
	public java.lang.String getUuid() {
		return _interest.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _interest.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _interest.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _interest.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this interest.
	*
	* @return the create date of this interest
	*/
	@Override
	public Date getCreateDate() {
		return _interest.getCreateDate();
	}

	/**
	* Returns the last publish date of this interest.
	*
	* @return the last publish date of this interest
	*/
	@Override
	public Date getLastPublishDate() {
		return _interest.getLastPublishDate();
	}

	/**
	* Returns the modified date of this interest.
	*
	* @return the modified date of this interest
	*/
	@Override
	public Date getModifiedDate() {
		return _interest.getModifiedDate();
	}

	/**
	* Returns the status date of this interest.
	*
	* @return the status date of this interest
	*/
	@Override
	public Date getStatusDate() {
		return _interest.getStatusDate();
	}

	/**
	* Retourne la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _interest.getCategories();
	}

	/**
	* Returns a map of the locales and localized descriptions of this interest.
	*
	* @return the locales and localized descriptions of this interest
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _interest.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this interest.
	*
	* @return the locales and localized titles of this interest
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _interest.getTitleMap();
	}

	/**
	* Returns the company ID of this interest.
	*
	* @return the company ID of this interest
	*/
	@Override
	public long getCompanyId() {
		return _interest.getCompanyId();
	}

	/**
	* Returns the group ID of this interest.
	*
	* @return the group ID of this interest
	*/
	@Override
	public long getGroupId() {
		return _interest.getGroupId();
	}

	/**
	* Returns the interest ID of this interest.
	*
	* @return the interest ID of this interest
	*/
	@Override
	public long getInterestId() {
		return _interest.getInterestId();
	}

	/**
	* Returns the primary key of this interest.
	*
	* @return the primary key of this interest
	*/
	@Override
	public long getPrimaryKey() {
		return _interest.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this interest.
	*
	* @return the status by user ID of this interest
	*/
	@Override
	public long getStatusByUserId() {
		return _interest.getStatusByUserId();
	}

	/**
	* Returns the type ID of this interest.
	*
	* @return the type ID of this interest
	*/
	@Override
	public long getTypeId() {
		return _interest.getTypeId();
	}

	/**
	* Returns the user ID of this interest.
	*
	* @return the user ID of this interest
	*/
	@Override
	public long getUserId() {
		return _interest.getUserId();
	}

	@Override
	public void persist() {
		_interest.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_interest.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_interest.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_interest.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this interest.
	*
	* @param companyId the company ID of this interest
	*/
	@Override
	public void setCompanyId(long companyId) {
		_interest.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this interest.
	*
	* @param createDate the create date of this interest
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_interest.setCreateDate(createDate);
	}

	/**
	* Sets the description of this interest.
	*
	* @param description the description of this interest
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_interest.setDescription(description);
	}

	/**
	* Sets the localized description of this interest in the language.
	*
	* @param description the localized description of this interest
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_interest.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this interest in the language, and sets the default locale.
	*
	* @param description the localized description of this interest
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_interest.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_interest.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this interest from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this interest
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_interest.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this interest from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this interest
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_interest.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_interest.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_interest.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_interest.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this interest.
	*
	* @param groupId the group ID of this interest
	*/
	@Override
	public void setGroupId(long groupId) {
		_interest.setGroupId(groupId);
	}

	/**
	* Sets the interest ID of this interest.
	*
	* @param interestId the interest ID of this interest
	*/
	@Override
	public void setInterestId(long interestId) {
		_interest.setInterestId(interestId);
	}

	/**
	* Sets the last publish date of this interest.
	*
	* @param lastPublishDate the last publish date of this interest
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_interest.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this interest.
	*
	* @param modifiedDate the modified date of this interest
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_interest.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_interest.setNew(n);
	}

	/**
	* Sets the primary key of this interest.
	*
	* @param primaryKey the primary key of this interest
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_interest.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_interest.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this interest.
	*
	* @param status the status of this interest
	*/
	@Override
	public void setStatus(int status) {
		_interest.setStatus(status);
	}

	/**
	* Sets the status by user ID of this interest.
	*
	* @param statusByUserId the status by user ID of this interest
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_interest.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this interest.
	*
	* @param statusByUserName the status by user name of this interest
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_interest.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this interest.
	*
	* @param statusByUserUuid the status by user uuid of this interest
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_interest.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this interest.
	*
	* @param statusDate the status date of this interest
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_interest.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this interest.
	*
	* @param title the title of this interest
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_interest.setTitle(title);
	}

	/**
	* Sets the localized title of this interest in the language.
	*
	* @param title the localized title of this interest
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_interest.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this interest in the language, and sets the default locale.
	*
	* @param title the localized title of this interest
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_interest.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_interest.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this interest from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this interest
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_interest.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this interest from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this interest
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_interest.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the type ID of this interest.
	*
	* @param typeId the type ID of this interest
	*/
	@Override
	public void setTypeId(long typeId) {
		_interest.setTypeId(typeId);
	}

	/**
	* Sets the user ID of this interest.
	*
	* @param userId the user ID of this interest
	*/
	@Override
	public void setUserId(long userId) {
		_interest.setUserId(userId);
	}

	/**
	* Sets the user name of this interest.
	*
	* @param userName the user name of this interest
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_interest.setUserName(userName);
	}

	/**
	* Sets the user uuid of this interest.
	*
	* @param userUuid the user uuid of this interest
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_interest.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this interest.
	*
	* @param uuid the uuid of this interest
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_interest.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InterestWrapper)) {
			return false;
		}

		InterestWrapper interestWrapper = (InterestWrapper)obj;

		if (Objects.equals(_interest, interestWrapper._interest)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _interest.getStagedModelType();
	}

	@Override
	public Interest getWrappedModel() {
		return _interest;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _interest.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _interest.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_interest.resetOriginalValues();
	}

	private final Interest _interest;
}
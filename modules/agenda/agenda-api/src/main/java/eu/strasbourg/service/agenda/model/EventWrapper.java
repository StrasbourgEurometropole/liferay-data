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

package eu.strasbourg.service.agenda.model;

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
 * This class is a wrapper for {@link Event}.
 * </p>
 *
 * @author BenjaminBini
 * @see Event
 * @generated
 */
@ProviderType
public class EventWrapper implements Event, ModelWrapper<Event> {
	public EventWrapper(Event event) {
		_event = event;
	}

	@Override
	public Class<?> getModelClass() {
		return Event.class;
	}

	@Override
	public String getModelClassName() {
		return Event.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("eventId", getEventId());
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
		attributes.put("subtitle", getSubtitle());
		attributes.put("description", getDescription());
		attributes.put("displayDate", getDisplayDate());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
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

		String subtitle = (String)attributes.get("subtitle");

		if (subtitle != null) {
			setSubtitle(subtitle);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date displayDate = (Date)attributes.get("displayDate");

		if (displayDate != null) {
			setDisplayDate(displayDate);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Returns <code>true</code> if this event is approved.
	*
	* @return <code>true</code> if this event is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _event.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _event.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this event is denied.
	*
	* @return <code>true</code> if this event is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _event.isDenied();
	}

	/**
	* Returns <code>true</code> if this event is a draft.
	*
	* @return <code>true</code> if this event is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _event.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _event.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this event is expired.
	*
	* @return <code>true</code> if this event is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _event.isExpired();
	}

	/**
	* Returns <code>true</code> if this event is inactive.
	*
	* @return <code>true</code> if this event is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _event.isInactive();
	}

	/**
	* Returns <code>true</code> if this event is incomplete.
	*
	* @return <code>true</code> if this event is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _event.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _event.isNew();
	}

	/**
	* Returns <code>true</code> if this event is pending.
	*
	* @return <code>true</code> if this event is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _event.isPending();
	}

	/**
	* Returns <code>true</code> if this event is scheduled.
	*
	* @return <code>true</code> if this event is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _event.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _event.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _event.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.Event> toCacheModel() {
		return _event.toCacheModel();
	}

	/**
	* Retourne la version live de l'édition, si elle existe
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event getLiveVersion() {
		return _event.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event toEscapedModel() {
		return new EventWrapper(_event.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event toUnescapedModel() {
		return new EventWrapper(_event.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.agenda.model.Event event) {
		return _event.compareTo(event);
	}

	/**
	* Returns the status of this event.
	*
	* @return the status of this event
	*/
	@Override
	public int getStatus() {
		return _event.getStatus();
	}

	@Override
	public int hashCode() {
		return _event.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _event.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this event.
	*
	* @return the image ID of this event
	*/
	@Override
	public java.lang.Long getImageId() {
		return _event.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new EventWrapper((Event)_event.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _event.getDefaultLanguageId();
	}

	/**
	* Returns the description of this event.
	*
	* @return the description of this event
	*/
	@Override
	public java.lang.String getDescription() {
		return _event.getDescription();
	}

	/**
	* Returns the localized description of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _event.getDescription(languageId);
	}

	/**
	* Returns the localized description of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _event.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _event.getDescription(locale);
	}

	/**
	* Returns the localized description of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _event.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _event.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _event.getDescriptionCurrentValue();
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _event.getImageCopyright(locale);
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _event.getImageURL();
	}

	/**
	* Retourne la liste des IDs des manifestations auxquelles cette édition
	* appartient sous forme de String
	*/
	@Override
	public java.lang.String getManifestationsIds() {
		return _event.getManifestationsIds();
	}

	/**
	* Returns the status by user name of this event.
	*
	* @return the status by user name of this event
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _event.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this event.
	*
	* @return the status by user uuid of this event
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _event.getStatusByUserUuid();
	}

	/**
	* Returns the subtitle of this event.
	*
	* @return the subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle() {
		return _event.getSubtitle();
	}

	/**
	* Returns the localized subtitle of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId) {
		return _event.getSubtitle(languageId);
	}

	/**
	* Returns the localized subtitle of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId,
		boolean useDefault) {
		return _event.getSubtitle(languageId, useDefault);
	}

	/**
	* Returns the localized subtitle of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale) {
		return _event.getSubtitle(locale);
	}

	/**
	* Returns the localized subtitle of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subtitle of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale,
		boolean useDefault) {
		return _event.getSubtitle(locale, useDefault);
	}

	@Override
	public java.lang.String getSubtitleCurrentLanguageId() {
		return _event.getSubtitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubtitleCurrentValue() {
		return _event.getSubtitleCurrentValue();
	}

	/**
	* Returns the title of this event.
	*
	* @return the title of this event
	*/
	@Override
	public java.lang.String getTitle() {
		return _event.getTitle();
	}

	/**
	* Returns the localized title of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _event.getTitle(languageId);
	}

	/**
	* Returns the localized title of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _event.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _event.getTitle(locale);
	}

	/**
	* Returns the localized title of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _event.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _event.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _event.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this event.
	*
	* @return the user name of this event
	*/
	@Override
	public java.lang.String getUserName() {
		return _event.getUserName();
	}

	/**
	* Returns the user uuid of this event.
	*
	* @return the user uuid of this event
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _event.getUserUuid();
	}

	/**
	* Returns the uuid of this event.
	*
	* @return the uuid of this event
	*/
	@Override
	public java.lang.String getUuid() {
		return _event.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _event.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _event.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _event.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this event.
	*
	* @return the create date of this event
	*/
	@Override
	public Date getCreateDate() {
		return _event.getCreateDate();
	}

	/**
	* Returns the display date of this event.
	*
	* @return the display date of this event
	*/
	@Override
	public Date getDisplayDate() {
		return _event.getDisplayDate();
	}

	/**
	* Returns the last publish date of this event.
	*
	* @return the last publish date of this event
	*/
	@Override
	public Date getLastPublishDate() {
		return _event.getLastPublishDate();
	}

	/**
	* Returns the modified date of this event.
	*
	* @return the modified date of this event
	*/
	@Override
	public Date getModifiedDate() {
		return _event.getModifiedDate();
	}

	/**
	* Returns the status date of this event.
	*
	* @return the status date of this event
	*/
	@Override
	public Date getStatusDate() {
		return _event.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _event.getCategories();
	}

	/**
	* Retourne la liste des périodes auxquelles l'événement à lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getEventPeriods() {
		return _event.getEventPeriods();
	}

	/**
	* Retourne la liste des manifestations auxquelles cette édition appartient
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations() {
		return _event.getManifestations();
	}

	/**
	* Retourne la liste des galeries publiées
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getPublishedManifestations() {
		return _event.getPublishedManifestations();
	}

	/**
	* Returns a map of the locales and localized descriptions of this event.
	*
	* @return the locales and localized descriptions of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _event.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized subtitles of this event.
	*
	* @return the locales and localized subtitles of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSubtitleMap() {
		return _event.getSubtitleMap();
	}

	/**
	* Returns a map of the locales and localized titles of this event.
	*
	* @return the locales and localized titles of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _event.getTitleMap();
	}

	/**
	* Returns the company ID of this event.
	*
	* @return the company ID of this event
	*/
	@Override
	public long getCompanyId() {
		return _event.getCompanyId();
	}

	/**
	* Returns the event ID of this event.
	*
	* @return the event ID of this event
	*/
	@Override
	public long getEventId() {
		return _event.getEventId();
	}

	/**
	* Returns the group ID of this event.
	*
	* @return the group ID of this event
	*/
	@Override
	public long getGroupId() {
		return _event.getGroupId();
	}

	/**
	* Returns the primary key of this event.
	*
	* @return the primary key of this event
	*/
	@Override
	public long getPrimaryKey() {
		return _event.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this event.
	*
	* @return the status by user ID of this event
	*/
	@Override
	public long getStatusByUserId() {
		return _event.getStatusByUserId();
	}

	/**
	* Returns the user ID of this event.
	*
	* @return the user ID of this event
	*/
	@Override
	public long getUserId() {
		return _event.getUserId();
	}

	@Override
	public void persist() {
		_event.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_event.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_event.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_event.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this event.
	*
	* @param companyId the company ID of this event
	*/
	@Override
	public void setCompanyId(long companyId) {
		_event.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this event.
	*
	* @param createDate the create date of this event
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_event.setCreateDate(createDate);
	}

	/**
	* Sets the description of this event.
	*
	* @param description the description of this event
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_event.setDescription(description);
	}

	/**
	* Sets the localized description of this event in the language.
	*
	* @param description the localized description of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_event.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this event in the language, and sets the default locale.
	*
	* @param description the localized description of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_event.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this event from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this event
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_event.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this event from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_event.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the display date of this event.
	*
	* @param displayDate the display date of this event
	*/
	@Override
	public void setDisplayDate(Date displayDate) {
		_event.setDisplayDate(displayDate);
	}

	/**
	* Sets the event ID of this event.
	*
	* @param eventId the event ID of this event
	*/
	@Override
	public void setEventId(long eventId) {
		_event.setEventId(eventId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_event.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_event.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_event.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this event.
	*
	* @param groupId the group ID of this event
	*/
	@Override
	public void setGroupId(long groupId) {
		_event.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this event.
	*
	* @param imageId the image ID of this event
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_event.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this event.
	*
	* @param lastPublishDate the last publish date of this event
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_event.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this event.
	*
	* @param modifiedDate the modified date of this event
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_event.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_event.setNew(n);
	}

	/**
	* Sets the primary key of this event.
	*
	* @param primaryKey the primary key of this event
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_event.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_event.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this event.
	*
	* @param status the status of this event
	*/
	@Override
	public void setStatus(int status) {
		_event.setStatus(status);
	}

	/**
	* Sets the status by user ID of this event.
	*
	* @param statusByUserId the status by user ID of this event
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_event.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this event.
	*
	* @param statusByUserName the status by user name of this event
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_event.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this event.
	*
	* @param statusByUserUuid the status by user uuid of this event
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_event.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this event.
	*
	* @param statusDate the status date of this event
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_event.setStatusDate(statusDate);
	}

	/**
	* Sets the subtitle of this event.
	*
	* @param subtitle the subtitle of this event
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle) {
		_event.setSubtitle(subtitle);
	}

	/**
	* Sets the localized subtitle of this event in the language.
	*
	* @param subtitle the localized subtitle of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale) {
		_event.setSubtitle(subtitle, locale);
	}

	/**
	* Sets the localized subtitle of this event in the language, and sets the default locale.
	*
	* @param subtitle the localized subtitle of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(java.lang.String languageId) {
		_event.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subtitles of this event from the map of locales and localized subtitles.
	*
	* @param subtitleMap the locales and localized subtitles of this event
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap) {
		_event.setSubtitleMap(subtitleMap);
	}

	/**
	* Sets the localized subtitles of this event from the map of locales and localized subtitles, and sets the default locale.
	*
	* @param subtitleMap the locales and localized subtitles of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap,
		java.util.Locale defaultLocale) {
		_event.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	* Sets the title of this event.
	*
	* @param title the title of this event
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_event.setTitle(title);
	}

	/**
	* Sets the localized title of this event in the language.
	*
	* @param title the localized title of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_event.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this event in the language, and sets the default locale.
	*
	* @param title the localized title of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_event.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this event from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this event
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_event.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this event from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_event.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this event.
	*
	* @param userId the user ID of this event
	*/
	@Override
	public void setUserId(long userId) {
		_event.setUserId(userId);
	}

	/**
	* Sets the user name of this event.
	*
	* @param userName the user name of this event
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_event.setUserName(userName);
	}

	/**
	* Sets the user uuid of this event.
	*
	* @param userUuid the user uuid of this event
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_event.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this event.
	*
	* @param uuid the uuid of this event
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_event.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventWrapper)) {
			return false;
		}

		EventWrapper eventWrapper = (EventWrapper)obj;

		if (Objects.equals(_event, eventWrapper._event)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _event.getStagedModelType();
	}

	@Override
	public Event getWrappedModel() {
		return _event;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _event.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _event.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_event.resetOriginalValues();
	}

	private final Event _event;
}
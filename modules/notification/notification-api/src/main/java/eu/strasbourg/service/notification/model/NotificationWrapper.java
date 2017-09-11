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

package eu.strasbourg.service.notification.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Notification}.
 * </p>
 *
 * @author BenjaminBini
 * @see Notification
 * @generated
 */
@ProviderType
public class NotificationWrapper implements Notification,
	ModelWrapper<Notification> {
	public NotificationWrapper(Notification notification) {
		_notification = notification;
	}

	@Override
	public Class<?> getModelClass() {
		return Notification.class;
	}

	@Override
	public String getModelClassName() {
		return Notification.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationId", getNotificationId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("url", getUrl());
		attributes.put("automatic", getAutomatic());
		attributes.put("singleUser", getSingleUser());
		attributes.put("singleUserId", getSingleUserId());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("status", getStatus());
		attributes.put("typeId", getTypeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationId = (Long)attributes.get("notificationId");

		if (notificationId != null) {
			setNotificationId(notificationId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Boolean automatic = (Boolean)attributes.get("automatic");

		if (automatic != null) {
			setAutomatic(automatic);
		}

		Boolean singleUser = (Boolean)attributes.get("singleUser");

		if (singleUser != null) {
			setSingleUser(singleUser);
		}

		Long singleUserId = (Long)attributes.get("singleUserId");

		if (singleUserId != null) {
			setSingleUserId(singleUserId);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}
	}

	/**
	* Returns the automatic of this notification.
	*
	* @return the automatic of this notification
	*/
	@Override
	public boolean getAutomatic() {
		return _notification.getAutomatic();
	}

	/**
	* Returns the single user of this notification.
	*
	* @return the single user of this notification
	*/
	@Override
	public boolean getSingleUser() {
		return _notification.getSingleUser();
	}

	/**
	* Returns <code>true</code> if this notification is automatic.
	*
	* @return <code>true</code> if this notification is automatic; <code>false</code> otherwise
	*/
	@Override
	public boolean isAutomatic() {
		return _notification.isAutomatic();
	}

	@Override
	public boolean isCachedModel() {
		return _notification.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _notification.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _notification.isNew();
	}

	/**
	* Returns <code>true</code> if this notification is single user.
	*
	* @return <code>true</code> if this notification is single user; <code>false</code> otherwise
	*/
	@Override
	public boolean isSingleUser() {
		return _notification.isSingleUser();
	}

	/**
	* Retourne le type de la notification
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getType() {
		return _notification.getType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _notification.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.notification.model.Notification> toCacheModel() {
		return _notification.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notification.model.Notification toEscapedModel() {
		return new NotificationWrapper(_notification.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.notification.model.Notification toUnescapedModel() {
		return new NotificationWrapper(_notification.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notification.model.Notification notification) {
		return _notification.compareTo(notification);
	}

	/**
	* Returns the status of this notification.
	*
	* @return the status of this notification
	*/
	@Override
	public int getStatus() {
		return _notification.getStatus();
	}

	@Override
	public int hashCode() {
		return _notification.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notification.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new NotificationWrapper((Notification)_notification.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _notification.getDefaultLanguageId();
	}

	/**
	* Returns the description of this notification.
	*
	* @return the description of this notification
	*/
	@Override
	public java.lang.String getDescription() {
		return _notification.getDescription();
	}

	/**
	* Returns the localized description of this notification in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this notification
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _notification.getDescription(languageId);
	}

	/**
	* Returns the localized description of this notification in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this notification
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _notification.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this notification in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this notification
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _notification.getDescription(locale);
	}

	/**
	* Returns the localized description of this notification in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _notification.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _notification.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _notification.getDescriptionCurrentValue();
	}

	/**
	* Returns the single user uuid of this notification.
	*
	* @return the single user uuid of this notification
	*/
	@Override
	public java.lang.String getSingleUserUuid() {
		return _notification.getSingleUserUuid();
	}

	/**
	* Returns the title of this notification.
	*
	* @return the title of this notification
	*/
	@Override
	public java.lang.String getTitle() {
		return _notification.getTitle();
	}

	/**
	* Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this notification
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _notification.getTitle(languageId);
	}

	/**
	* Returns the localized title of this notification in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this notification
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _notification.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this notification
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _notification.getTitle(locale);
	}

	/**
	* Returns the localized title of this notification in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _notification.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _notification.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _notification.getTitleCurrentValue();
	}

	/**
	* Returns the url of this notification.
	*
	* @return the url of this notification
	*/
	@Override
	public java.lang.String getUrl() {
		return _notification.getUrl();
	}

	@Override
	public java.lang.String toString() {
		return _notification.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _notification.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _notification.getAvailableLanguageIds();
	}

	/**
	* Returns the expiration date of this notification.
	*
	* @return the expiration date of this notification
	*/
	@Override
	public Date getExpirationDate() {
		return _notification.getExpirationDate();
	}

	/**
	* Returns the publication date of this notification.
	*
	* @return the publication date of this notification
	*/
	@Override
	public Date getPublicationDate() {
		return _notification.getPublicationDate();
	}

	/**
	* Retourne la liste des utilisateurs concernés par la notification
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.PublikUser> getUsersToNotify() {
		return _notification.getUsersToNotify();
	}

	/**
	* Returns a map of the locales and localized descriptions of this notification.
	*
	* @return the locales and localized descriptions of this notification
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _notification.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this notification.
	*
	* @return the locales and localized titles of this notification
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _notification.getTitleMap();
	}

	/**
	* Returns the notification ID of this notification.
	*
	* @return the notification ID of this notification
	*/
	@Override
	public long getNotificationId() {
		return _notification.getNotificationId();
	}

	/**
	* Returns the primary key of this notification.
	*
	* @return the primary key of this notification
	*/
	@Override
	public long getPrimaryKey() {
		return _notification.getPrimaryKey();
	}

	/**
	* Returns the single user ID of this notification.
	*
	* @return the single user ID of this notification
	*/
	@Override
	public long getSingleUserId() {
		return _notification.getSingleUserId();
	}

	/**
	* Returns the type ID of this notification.
	*
	* @return the type ID of this notification
	*/
	@Override
	public long getTypeId() {
		return _notification.getTypeId();
	}

	@Override
	public void persist() {
		_notification.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_notification.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_notification.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets whether this notification is automatic.
	*
	* @param automatic the automatic of this notification
	*/
	@Override
	public void setAutomatic(boolean automatic) {
		_notification.setAutomatic(automatic);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notification.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this notification.
	*
	* @param description the description of this notification
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_notification.setDescription(description);
	}

	/**
	* Sets the localized description of this notification in the language.
	*
	* @param description the localized description of this notification
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_notification.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this notification in the language, and sets the default locale.
	*
	* @param description the localized description of this notification
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_notification.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_notification.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this notification from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this notification
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_notification.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this notification from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this notification
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_notification.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_notification.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_notification.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_notification.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration date of this notification.
	*
	* @param expirationDate the expiration date of this notification
	*/
	@Override
	public void setExpirationDate(Date expirationDate) {
		_notification.setExpirationDate(expirationDate);
	}

	@Override
	public void setNew(boolean n) {
		_notification.setNew(n);
	}

	/**
	* Sets the notification ID of this notification.
	*
	* @param notificationId the notification ID of this notification
	*/
	@Override
	public void setNotificationId(long notificationId) {
		_notification.setNotificationId(notificationId);
	}

	/**
	* Sets the primary key of this notification.
	*
	* @param primaryKey the primary key of this notification
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_notification.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_notification.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publication date of this notification.
	*
	* @param publicationDate the publication date of this notification
	*/
	@Override
	public void setPublicationDate(Date publicationDate) {
		_notification.setPublicationDate(publicationDate);
	}

	/**
	* Sets whether this notification is single user.
	*
	* @param singleUser the single user of this notification
	*/
	@Override
	public void setSingleUser(boolean singleUser) {
		_notification.setSingleUser(singleUser);
	}

	/**
	* Sets the single user ID of this notification.
	*
	* @param singleUserId the single user ID of this notification
	*/
	@Override
	public void setSingleUserId(long singleUserId) {
		_notification.setSingleUserId(singleUserId);
	}

	/**
	* Sets the single user uuid of this notification.
	*
	* @param singleUserUuid the single user uuid of this notification
	*/
	@Override
	public void setSingleUserUuid(java.lang.String singleUserUuid) {
		_notification.setSingleUserUuid(singleUserUuid);
	}

	/**
	* Sets the status of this notification.
	*
	* @param status the status of this notification
	*/
	@Override
	public void setStatus(int status) {
		_notification.setStatus(status);
	}

	/**
	* Sets the title of this notification.
	*
	* @param title the title of this notification
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_notification.setTitle(title);
	}

	/**
	* Sets the localized title of this notification in the language.
	*
	* @param title the localized title of this notification
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_notification.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this notification in the language, and sets the default locale.
	*
	* @param title the localized title of this notification
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_notification.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_notification.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this notification from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this notification
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_notification.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this notification from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this notification
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_notification.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the type ID of this notification.
	*
	* @param typeId the type ID of this notification
	*/
	@Override
	public void setTypeId(long typeId) {
		_notification.setTypeId(typeId);
	}

	/**
	* Sets the url of this notification.
	*
	* @param url the url of this notification
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_notification.setUrl(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationWrapper)) {
			return false;
		}

		NotificationWrapper notificationWrapper = (NotificationWrapper)obj;

		if (Objects.equals(_notification, notificationWrapper._notification)) {
			return true;
		}

		return false;
	}

	@Override
	public Notification getWrappedModel() {
		return _notification;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _notification.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _notification.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_notification.resetOriginalValues();
	}

	private final Notification _notification;
}
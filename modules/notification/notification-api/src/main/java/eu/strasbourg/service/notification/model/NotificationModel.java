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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Notification service. Represents a row in the &quot;notification_Notification&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.notification.model.impl.NotificationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.notification.model.impl.NotificationImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see Notification
 * @see eu.strasbourg.service.notification.model.impl.NotificationImpl
 * @see eu.strasbourg.service.notification.model.impl.NotificationModelImpl
 * @generated
 */
@ProviderType
public interface NotificationModel extends BaseModel<Notification>,
	LocalizedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a notification model instance should use the {@link Notification} interface instead.
	 */

	/**
	 * Returns the primary key of this notification.
	 *
	 * @return the primary key of this notification
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this notification.
	 *
	 * @param primaryKey the primary key of this notification
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the notification ID of this notification.
	 *
	 * @return the notification ID of this notification
	 */
	public long getNotificationId();

	/**
	 * Sets the notification ID of this notification.
	 *
	 * @param notificationId the notification ID of this notification
	 */
	public void setNotificationId(long notificationId);

	/**
	 * Returns the title of this notification.
	 *
	 * @return the title of this notification
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this notification
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this notification
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this notification
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this notification.
	 *
	 * @return the locales and localized titles of this notification
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this notification.
	 *
	 * @param title the title of this notification
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this notification in the language.
	 *
	 * @param title the localized title of this notification
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this notification in the language, and sets the default locale.
	 *
	 * @param title the localized title of this notification
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this notification from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this notification
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this notification from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this notification
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the url of this notification.
	 *
	 * @return the url of this notification
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this notification.
	 *
	 * @param url the url of this notification
	 */
	public void setUrl(String url);

	/**
	 * Returns the automatic of this notification.
	 *
	 * @return the automatic of this notification
	 */
	public boolean getAutomatic();

	/**
	 * Returns <code>true</code> if this notification is automatic.
	 *
	 * @return <code>true</code> if this notification is automatic; <code>false</code> otherwise
	 */
	public boolean isAutomatic();

	/**
	 * Sets whether this notification is automatic.
	 *
	 * @param automatic the automatic of this notification
	 */
	public void setAutomatic(boolean automatic);

	/**
	 * Returns the single user of this notification.
	 *
	 * @return the single user of this notification
	 */
	public boolean getSingleUser();

	/**
	 * Returns <code>true</code> if this notification is single user.
	 *
	 * @return <code>true</code> if this notification is single user; <code>false</code> otherwise
	 */
	public boolean isSingleUser();

	/**
	 * Sets whether this notification is single user.
	 *
	 * @param singleUser the single user of this notification
	 */
	public void setSingleUser(boolean singleUser);

	/**
	 * Returns the single user ID of this notification.
	 *
	 * @return the single user ID of this notification
	 */
	@AutoEscape
	public String getSingleUserId();

	/**
	 * Sets the single user ID of this notification.
	 *
	 * @param singleUserId the single user ID of this notification
	 */
	public void setSingleUserId(String singleUserId);

	/**
	 * Returns the publication date of this notification.
	 *
	 * @return the publication date of this notification
	 */
	public Date getPublicationDate();

	/**
	 * Sets the publication date of this notification.
	 *
	 * @param publicationDate the publication date of this notification
	 */
	public void setPublicationDate(Date publicationDate);

	/**
	 * Returns the expiration date of this notification.
	 *
	 * @return the expiration date of this notification
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this notification.
	 *
	 * @param expirationDate the expiration date of this notification
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the status of this notification.
	 *
	 * @return the status of this notification
	 */
	public int getStatus();

	/**
	 * Sets the status of this notification.
	 *
	 * @param status the status of this notification
	 */
	public void setStatus(int status);

	/**
	 * Returns the type ID of this notification.
	 *
	 * @return the type ID of this notification
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this notification.
	 *
	 * @param typeId the type ID of this notification
	 */
	public void setTypeId(long typeId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(
		eu.strasbourg.service.notification.model.Notification notification);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.notification.model.Notification> toCacheModel();

	@Override
	public eu.strasbourg.service.notification.model.Notification toEscapedModel();

	@Override
	public eu.strasbourg.service.notification.model.Notification toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
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

package eu.strasbourg.service.notif.model;

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
 * This class is a wrapper for {@link Notification}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Notification
 * @generated
 */
@ProviderType
public class NotificationWrapper
	implements Notification, ModelWrapper<Notification> {

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

		attributes.put("uuid", getUuid());
		attributes.put("notificationId", getNotificationId());
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
		attributes.put("serviceId", getServiceId());
		attributes.put("isAlert", getIsAlert());
		attributes.put("natureId", getNatureId());
		attributes.put("title", getTitle());
		attributes.put("subtitle", getSubtitle());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("broadcastDate", getBroadcastDate());
		attributes.put("isAutomaticMessage", getIsAutomaticMessage());
		attributes.put("content", getContent());
		attributes.put("labelUrl", getLabelUrl());
		attributes.put("url", getUrl());
		attributes.put("typeBroadcast", getTypeBroadcast());
		attributes.put("broadcastChannels", getBroadcastChannels());
		attributes.put("sendStatusCsmap", getSendStatusCsmap());
		attributes.put("sendStatusTwitter", getSendStatusTwitter());
		attributes.put("sendStatusMonst", getSendStatusMonst());
		attributes.put("sendStatusMail", getSendStatusMail());
		attributes.put("sendStatusSegur", getSendStatusSegur());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long notificationId = (Long)attributes.get("notificationId");

		if (notificationId != null) {
			setNotificationId(notificationId);
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

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		Integer isAlert = (Integer)attributes.get("isAlert");

		if (isAlert != null) {
			setIsAlert(isAlert);
		}

		Long natureId = (Long)attributes.get("natureId");

		if (natureId != null) {
			setNatureId(natureId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String subtitle = (String)attributes.get("subtitle");

		if (subtitle != null) {
			setSubtitle(subtitle);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date broadcastDate = (Date)attributes.get("broadcastDate");

		if (broadcastDate != null) {
			setBroadcastDate(broadcastDate);
		}

		Integer isAutomaticMessage = (Integer)attributes.get(
			"isAutomaticMessage");

		if (isAutomaticMessage != null) {
			setIsAutomaticMessage(isAutomaticMessage);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		String labelUrl = (String)attributes.get("labelUrl");

		if (labelUrl != null) {
			setLabelUrl(labelUrl);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Long typeBroadcast = (Long)attributes.get("typeBroadcast");

		if (typeBroadcast != null) {
			setTypeBroadcast(typeBroadcast);
		}

		String broadcastChannels = (String)attributes.get("broadcastChannels");

		if (broadcastChannels != null) {
			setBroadcastChannels(broadcastChannels);
		}

		Long sendStatusCsmap = (Long)attributes.get("sendStatusCsmap");

		if (sendStatusCsmap != null) {
			setSendStatusCsmap(sendStatusCsmap);
		}

		Long sendStatusTwitter = (Long)attributes.get("sendStatusTwitter");

		if (sendStatusTwitter != null) {
			setSendStatusTwitter(sendStatusTwitter);
		}

		Long sendStatusMonst = (Long)attributes.get("sendStatusMonst");

		if (sendStatusMonst != null) {
			setSendStatusMonst(sendStatusMonst);
		}

		Long sendStatusMail = (Long)attributes.get("sendStatusMail");

		if (sendStatusMail != null) {
			setSendStatusMail(sendStatusMail);
		}

		Long sendStatusSegur = (Long)attributes.get("sendStatusSegur");

		if (sendStatusSegur != null) {
			setSendStatusSegur(sendStatusSegur);
		}
	}

	@Override
	public Object clone() {
		return new NotificationWrapper((Notification)_notification.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notif.model.Notification notification) {

		return _notification.compareTo(notification);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _notification.getAvailableLanguageIds();
	}

	/**
	 * Returns the broadcast channels of this notification.
	 *
	 * @return the broadcast channels of this notification
	 */
	@Override
	public String getBroadcastChannels() {
		return _notification.getBroadcastChannels();
	}

	/**
	 * Returns the broadcast date of this notification.
	 *
	 * @return the broadcast date of this notification
	 */
	@Override
	public Date getBroadcastDate() {
		return _notification.getBroadcastDate();
	}

	/**
	 * Returns the company ID of this notification.
	 *
	 * @return the company ID of this notification
	 */
	@Override
	public long getCompanyId() {
		return _notification.getCompanyId();
	}

	/**
	 * Returns the content of this notification.
	 *
	 * @return the content of this notification
	 */
	@Override
	public String getContent() {
		return _notification.getContent();
	}

	/**
	 * Returns the localized content of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized content of this notification
	 */
	@Override
	public String getContent(java.util.Locale locale) {
		return _notification.getContent(locale);
	}

	/**
	 * Returns the localized content of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized content of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getContent(java.util.Locale locale, boolean useDefault) {
		return _notification.getContent(locale, useDefault);
	}

	/**
	 * Returns the localized content of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized content of this notification
	 */
	@Override
	public String getContent(String languageId) {
		return _notification.getContent(languageId);
	}

	/**
	 * Returns the localized content of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized content of this notification
	 */
	@Override
	public String getContent(String languageId, boolean useDefault) {
		return _notification.getContent(languageId, useDefault);
	}

	@Override
	public String getContentCurrentLanguageId() {
		return _notification.getContentCurrentLanguageId();
	}

	@Override
	public String getContentCurrentValue() {
		return _notification.getContentCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized contents of this notification.
	 *
	 * @return the locales and localized contents of this notification
	 */
	@Override
	public Map<java.util.Locale, String> getContentMap() {
		return _notification.getContentMap();
	}

	/**
	 * Returns the create date of this notification.
	 *
	 * @return the create date of this notification
	 */
	@Override
	public Date getCreateDate() {
		return _notification.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _notification.getDefaultLanguageId();
	}

	/**
	 * Returns the end date of this notification.
	 *
	 * @return the end date of this notification
	 */
	@Override
	public Date getEndDate() {
		return _notification.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _notification.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this notification.
	 *
	 * @return the group ID of this notification
	 */
	@Override
	public long getGroupId() {
		return _notification.getGroupId();
	}

	/**
	 * Returns the is alert of this notification.
	 *
	 * @return the is alert of this notification
	 */
	@Override
	public int getIsAlert() {
		return _notification.getIsAlert();
	}

	/**
	 * Returns the is automatic message of this notification.
	 *
	 * @return the is automatic message of this notification
	 */
	@Override
	public int getIsAutomaticMessage() {
		return _notification.getIsAutomaticMessage();
	}

	/**
	 * Returns the label url of this notification.
	 *
	 * @return the label url of this notification
	 */
	@Override
	public String getLabelUrl() {
		return _notification.getLabelUrl();
	}

	/**
	 * Returns the localized label url of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized label url of this notification
	 */
	@Override
	public String getLabelUrl(java.util.Locale locale) {
		return _notification.getLabelUrl(locale);
	}

	/**
	 * Returns the localized label url of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label url of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getLabelUrl(java.util.Locale locale, boolean useDefault) {
		return _notification.getLabelUrl(locale, useDefault);
	}

	/**
	 * Returns the localized label url of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized label url of this notification
	 */
	@Override
	public String getLabelUrl(String languageId) {
		return _notification.getLabelUrl(languageId);
	}

	/**
	 * Returns the localized label url of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label url of this notification
	 */
	@Override
	public String getLabelUrl(String languageId, boolean useDefault) {
		return _notification.getLabelUrl(languageId, useDefault);
	}

	@Override
	public String getLabelUrlCurrentLanguageId() {
		return _notification.getLabelUrlCurrentLanguageId();
	}

	@Override
	public String getLabelUrlCurrentValue() {
		return _notification.getLabelUrlCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized label urls of this notification.
	 *
	 * @return the locales and localized label urls of this notification
	 */
	@Override
	public Map<java.util.Locale, String> getLabelUrlMap() {
		return _notification.getLabelUrlMap();
	}

	/**
	 * Returns the modified date of this notification.
	 *
	 * @return the modified date of this notification
	 */
	@Override
	public Date getModifiedDate() {
		return _notification.getModifiedDate();
	}

	/**
	 * Returns the nature ID of this notification.
	 *
	 * @return the nature ID of this notification
	 */
	@Override
	public long getNatureId() {
		return _notification.getNatureId();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _notification.getPrimaryKeyObj();
	}

	/**
	 * Returns the send status csmap of this notification.
	 *
	 * @return the send status csmap of this notification
	 */
	@Override
	public long getSendStatusCsmap() {
		return _notification.getSendStatusCsmap();
	}

	/**
	 * Returns the send status mail of this notification.
	 *
	 * @return the send status mail of this notification
	 */
	@Override
	public long getSendStatusMail() {
		return _notification.getSendStatusMail();
	}

	/**
	 * Returns the send status monst of this notification.
	 *
	 * @return the send status monst of this notification
	 */
	@Override
	public long getSendStatusMonst() {
		return _notification.getSendStatusMonst();
	}

	/**
	 * Returns the send status segur of this notification.
	 *
	 * @return the send status segur of this notification
	 */
	@Override
	public long getSendStatusSegur() {
		return _notification.getSendStatusSegur();
	}

	/**
	 * Returns the send status twitter of this notification.
	 *
	 * @return the send status twitter of this notification
	 */
	@Override
	public long getSendStatusTwitter() {
		return _notification.getSendStatusTwitter();
	}

	/**
	 * Returns the service ID of this notification.
	 *
	 * @return the service ID of this notification
	 */
	@Override
	public long getServiceId() {
		return _notification.getServiceId();
	}

	/**
	 * Returns the start date of this notification.
	 *
	 * @return the start date of this notification
	 */
	@Override
	public Date getStartDate() {
		return _notification.getStartDate();
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

	/**
	 * Returns the status by user ID of this notification.
	 *
	 * @return the status by user ID of this notification
	 */
	@Override
	public long getStatusByUserId() {
		return _notification.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this notification.
	 *
	 * @return the status by user name of this notification
	 */
	@Override
	public String getStatusByUserName() {
		return _notification.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this notification.
	 *
	 * @return the status by user uuid of this notification
	 */
	@Override
	public String getStatusByUserUuid() {
		return _notification.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this notification.
	 *
	 * @return the status date of this notification
	 */
	@Override
	public Date getStatusDate() {
		return _notification.getStatusDate();
	}

	/**
	 * Returns the subtitle of this notification.
	 *
	 * @return the subtitle of this notification
	 */
	@Override
	public String getSubtitle() {
		return _notification.getSubtitle();
	}

	/**
	 * Returns the localized subtitle of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subtitle of this notification
	 */
	@Override
	public String getSubtitle(java.util.Locale locale) {
		return _notification.getSubtitle(locale);
	}

	/**
	 * Returns the localized subtitle of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getSubtitle(java.util.Locale locale, boolean useDefault) {
		return _notification.getSubtitle(locale, useDefault);
	}

	/**
	 * Returns the localized subtitle of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subtitle of this notification
	 */
	@Override
	public String getSubtitle(String languageId) {
		return _notification.getSubtitle(languageId);
	}

	/**
	 * Returns the localized subtitle of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this notification
	 */
	@Override
	public String getSubtitle(String languageId, boolean useDefault) {
		return _notification.getSubtitle(languageId, useDefault);
	}

	@Override
	public String getSubtitleCurrentLanguageId() {
		return _notification.getSubtitleCurrentLanguageId();
	}

	@Override
	public String getSubtitleCurrentValue() {
		return _notification.getSubtitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized subtitles of this notification.
	 *
	 * @return the locales and localized subtitles of this notification
	 */
	@Override
	public Map<java.util.Locale, String> getSubtitleMap() {
		return _notification.getSubtitleMap();
	}

	/**
	 * Returns the title of this notification.
	 *
	 * @return the title of this notification
	 */
	@Override
	public String getTitle() {
		return _notification.getTitle();
	}

	/**
	 * Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this notification
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
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
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _notification.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this notification
	 */
	@Override
	public String getTitle(String languageId) {
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
	public String getTitle(String languageId, boolean useDefault) {
		return _notification.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _notification.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _notification.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this notification.
	 *
	 * @return the locales and localized titles of this notification
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _notification.getTitleMap();
	}

	/**
	 * Returns the type broadcast of this notification.
	 *
	 * @return the type broadcast of this notification
	 */
	@Override
	public long getTypeBroadcast() {
		return _notification.getTypeBroadcast();
	}

	/**
	 * Returns the url of this notification.
	 *
	 * @return the url of this notification
	 */
	@Override
	public String getUrl() {
		return _notification.getUrl();
	}

	/**
	 * Returns the localized url of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized url of this notification
	 */
	@Override
	public String getUrl(java.util.Locale locale) {
		return _notification.getUrl(locale);
	}

	/**
	 * Returns the localized url of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url of this notification. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getUrl(java.util.Locale locale, boolean useDefault) {
		return _notification.getUrl(locale, useDefault);
	}

	/**
	 * Returns the localized url of this notification in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized url of this notification
	 */
	@Override
	public String getUrl(String languageId) {
		return _notification.getUrl(languageId);
	}

	/**
	 * Returns the localized url of this notification in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url of this notification
	 */
	@Override
	public String getUrl(String languageId, boolean useDefault) {
		return _notification.getUrl(languageId, useDefault);
	}

	@Override
	public String getUrlCurrentLanguageId() {
		return _notification.getUrlCurrentLanguageId();
	}

	@Override
	public String getUrlCurrentValue() {
		return _notification.getUrlCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized urls of this notification.
	 *
	 * @return the locales and localized urls of this notification
	 */
	@Override
	public Map<java.util.Locale, String> getUrlMap() {
		return _notification.getUrlMap();
	}

	/**
	 * Returns the user ID of this notification.
	 *
	 * @return the user ID of this notification
	 */
	@Override
	public long getUserId() {
		return _notification.getUserId();
	}

	/**
	 * Returns the user name of this notification.
	 *
	 * @return the user name of this notification
	 */
	@Override
	public String getUserName() {
		return _notification.getUserName();
	}

	/**
	 * Returns the user uuid of this notification.
	 *
	 * @return the user uuid of this notification
	 */
	@Override
	public String getUserUuid() {
		return _notification.getUserUuid();
	}

	/**
	 * Returns the uuid of this notification.
	 *
	 * @return the uuid of this notification
	 */
	@Override
	public String getUuid() {
		return _notification.getUuid();
	}

	@Override
	public int hashCode() {
		return _notification.hashCode();
	}

	/**
	 * Returns <code>true</code> if this notification is approved.
	 *
	 * @return <code>true</code> if this notification is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _notification.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _notification.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this notification is denied.
	 *
	 * @return <code>true</code> if this notification is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _notification.isDenied();
	}

	/**
	 * Returns <code>true</code> if this notification is a draft.
	 *
	 * @return <code>true</code> if this notification is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _notification.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _notification.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this notification is expired.
	 *
	 * @return <code>true</code> if this notification is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _notification.isExpired();
	}

	/**
	 * Returns <code>true</code> if this notification is inactive.
	 *
	 * @return <code>true</code> if this notification is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _notification.isInactive();
	}

	/**
	 * Returns <code>true</code> if this notification is incomplete.
	 *
	 * @return <code>true</code> if this notification is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _notification.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _notification.isNew();
	}

	/**
	 * Returns <code>true</code> if this notification is pending.
	 *
	 * @return <code>true</code> if this notification is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _notification.isPending();
	}

	/**
	 * Returns <code>true</code> if this notification is scheduled.
	 *
	 * @return <code>true</code> if this notification is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _notification.isScheduled();
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
	 * Sets the broadcast channels of this notification.
	 *
	 * @param broadcastChannels the broadcast channels of this notification
	 */
	@Override
	public void setBroadcastChannels(String broadcastChannels) {
		_notification.setBroadcastChannels(broadcastChannels);
	}

	/**
	 * Sets the broadcast date of this notification.
	 *
	 * @param broadcastDate the broadcast date of this notification
	 */
	@Override
	public void setBroadcastDate(Date broadcastDate) {
		_notification.setBroadcastDate(broadcastDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_notification.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this notification.
	 *
	 * @param companyId the company ID of this notification
	 */
	@Override
	public void setCompanyId(long companyId) {
		_notification.setCompanyId(companyId);
	}

	/**
	 * Sets the content of this notification.
	 *
	 * @param content the content of this notification
	 */
	@Override
	public void setContent(String content) {
		_notification.setContent(content);
	}

	/**
	 * Sets the localized content of this notification in the language.
	 *
	 * @param content the localized content of this notification
	 * @param locale the locale of the language
	 */
	@Override
	public void setContent(String content, java.util.Locale locale) {
		_notification.setContent(content, locale);
	}

	/**
	 * Sets the localized content of this notification in the language, and sets the default locale.
	 *
	 * @param content the localized content of this notification
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setContent(
		String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_notification.setContent(content, locale, defaultLocale);
	}

	@Override
	public void setContentCurrentLanguageId(String languageId) {
		_notification.setContentCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized contents of this notification from the map of locales and localized contents.
	 *
	 * @param contentMap the locales and localized contents of this notification
	 */
	@Override
	public void setContentMap(Map<java.util.Locale, String> contentMap) {
		_notification.setContentMap(contentMap);
	}

	/**
	 * Sets the localized contents of this notification from the map of locales and localized contents, and sets the default locale.
	 *
	 * @param contentMap the locales and localized contents of this notification
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setContentMap(
		Map<java.util.Locale, String> contentMap,
		java.util.Locale defaultLocale) {

		_notification.setContentMap(contentMap, defaultLocale);
	}

	/**
	 * Sets the create date of this notification.
	 *
	 * @param createDate the create date of this notification
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_notification.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this notification.
	 *
	 * @param endDate the end date of this notification
	 */
	@Override
	public void setEndDate(Date endDate) {
		_notification.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_notification.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_notification.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_notification.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this notification.
	 *
	 * @param groupId the group ID of this notification
	 */
	@Override
	public void setGroupId(long groupId) {
		_notification.setGroupId(groupId);
	}

	/**
	 * Sets the is alert of this notification.
	 *
	 * @param isAlert the is alert of this notification
	 */
	@Override
	public void setIsAlert(int isAlert) {
		_notification.setIsAlert(isAlert);
	}

	/**
	 * Sets the is automatic message of this notification.
	 *
	 * @param isAutomaticMessage the is automatic message of this notification
	 */
	@Override
	public void setIsAutomaticMessage(int isAutomaticMessage) {
		_notification.setIsAutomaticMessage(isAutomaticMessage);
	}

	/**
	 * Sets the label url of this notification.
	 *
	 * @param labelUrl the label url of this notification
	 */
	@Override
	public void setLabelUrl(String labelUrl) {
		_notification.setLabelUrl(labelUrl);
	}

	/**
	 * Sets the localized label url of this notification in the language.
	 *
	 * @param labelUrl the localized label url of this notification
	 * @param locale the locale of the language
	 */
	@Override
	public void setLabelUrl(String labelUrl, java.util.Locale locale) {
		_notification.setLabelUrl(labelUrl, locale);
	}

	/**
	 * Sets the localized label url of this notification in the language, and sets the default locale.
	 *
	 * @param labelUrl the localized label url of this notification
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLabelUrl(
		String labelUrl, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_notification.setLabelUrl(labelUrl, locale, defaultLocale);
	}

	@Override
	public void setLabelUrlCurrentLanguageId(String languageId) {
		_notification.setLabelUrlCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized label urls of this notification from the map of locales and localized label urls.
	 *
	 * @param labelUrlMap the locales and localized label urls of this notification
	 */
	@Override
	public void setLabelUrlMap(Map<java.util.Locale, String> labelUrlMap) {
		_notification.setLabelUrlMap(labelUrlMap);
	}

	/**
	 * Sets the localized label urls of this notification from the map of locales and localized label urls, and sets the default locale.
	 *
	 * @param labelUrlMap the locales and localized label urls of this notification
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLabelUrlMap(
		Map<java.util.Locale, String> labelUrlMap,
		java.util.Locale defaultLocale) {

		_notification.setLabelUrlMap(labelUrlMap, defaultLocale);
	}

	/**
	 * Sets the modified date of this notification.
	 *
	 * @param modifiedDate the modified date of this notification
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_notification.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the nature ID of this notification.
	 *
	 * @param natureId the nature ID of this notification
	 */
	@Override
	public void setNatureId(long natureId) {
		_notification.setNatureId(natureId);
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
	 * Sets the send status csmap of this notification.
	 *
	 * @param sendStatusCsmap the send status csmap of this notification
	 */
	@Override
	public void setSendStatusCsmap(long sendStatusCsmap) {
		_notification.setSendStatusCsmap(sendStatusCsmap);
	}

	/**
	 * Sets the send status mail of this notification.
	 *
	 * @param sendStatusMail the send status mail of this notification
	 */
	@Override
	public void setSendStatusMail(long sendStatusMail) {
		_notification.setSendStatusMail(sendStatusMail);
	}

	/**
	 * Sets the send status monst of this notification.
	 *
	 * @param sendStatusMonst the send status monst of this notification
	 */
	@Override
	public void setSendStatusMonst(long sendStatusMonst) {
		_notification.setSendStatusMonst(sendStatusMonst);
	}

	/**
	 * Sets the send status segur of this notification.
	 *
	 * @param sendStatusSegur the send status segur of this notification
	 */
	@Override
	public void setSendStatusSegur(long sendStatusSegur) {
		_notification.setSendStatusSegur(sendStatusSegur);
	}

	/**
	 * Sets the send status twitter of this notification.
	 *
	 * @param sendStatusTwitter the send status twitter of this notification
	 */
	@Override
	public void setSendStatusTwitter(long sendStatusTwitter) {
		_notification.setSendStatusTwitter(sendStatusTwitter);
	}

	/**
	 * Sets the service ID of this notification.
	 *
	 * @param serviceId the service ID of this notification
	 */
	@Override
	public void setServiceId(long serviceId) {
		_notification.setServiceId(serviceId);
	}

	/**
	 * Sets the start date of this notification.
	 *
	 * @param startDate the start date of this notification
	 */
	@Override
	public void setStartDate(Date startDate) {
		_notification.setStartDate(startDate);
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
	 * Sets the status by user ID of this notification.
	 *
	 * @param statusByUserId the status by user ID of this notification
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_notification.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this notification.
	 *
	 * @param statusByUserName the status by user name of this notification
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_notification.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this notification.
	 *
	 * @param statusByUserUuid the status by user uuid of this notification
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_notification.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this notification.
	 *
	 * @param statusDate the status date of this notification
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_notification.setStatusDate(statusDate);
	}

	/**
	 * Sets the subtitle of this notification.
	 *
	 * @param subtitle the subtitle of this notification
	 */
	@Override
	public void setSubtitle(String subtitle) {
		_notification.setSubtitle(subtitle);
	}

	/**
	 * Sets the localized subtitle of this notification in the language.
	 *
	 * @param subtitle the localized subtitle of this notification
	 * @param locale the locale of the language
	 */
	@Override
	public void setSubtitle(String subtitle, java.util.Locale locale) {
		_notification.setSubtitle(subtitle, locale);
	}

	/**
	 * Sets the localized subtitle of this notification in the language, and sets the default locale.
	 *
	 * @param subtitle the localized subtitle of this notification
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitle(
		String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_notification.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(String languageId) {
		_notification.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized subtitles of this notification from the map of locales and localized subtitles.
	 *
	 * @param subtitleMap the locales and localized subtitles of this notification
	 */
	@Override
	public void setSubtitleMap(Map<java.util.Locale, String> subtitleMap) {
		_notification.setSubtitleMap(subtitleMap);
	}

	/**
	 * Sets the localized subtitles of this notification from the map of locales and localized subtitles, and sets the default locale.
	 *
	 * @param subtitleMap the locales and localized subtitles of this notification
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, String> subtitleMap,
		java.util.Locale defaultLocale) {

		_notification.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	 * Sets the title of this notification.
	 *
	 * @param title the title of this notification
	 */
	@Override
	public void setTitle(String title) {
		_notification.setTitle(title);
	}

	/**
	 * Sets the localized title of this notification in the language.
	 *
	 * @param title the localized title of this notification
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
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
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		_notification.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_notification.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this notification from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this notification
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_notification.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this notification from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this notification
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		_notification.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the type broadcast of this notification.
	 *
	 * @param typeBroadcast the type broadcast of this notification
	 */
	@Override
	public void setTypeBroadcast(long typeBroadcast) {
		_notification.setTypeBroadcast(typeBroadcast);
	}

	/**
	 * Sets the url of this notification.
	 *
	 * @param url the url of this notification
	 */
	@Override
	public void setUrl(String url) {
		_notification.setUrl(url);
	}

	/**
	 * Sets the localized url of this notification in the language.
	 *
	 * @param url the localized url of this notification
	 * @param locale the locale of the language
	 */
	@Override
	public void setUrl(String url, java.util.Locale locale) {
		_notification.setUrl(url, locale);
	}

	/**
	 * Sets the localized url of this notification in the language, and sets the default locale.
	 *
	 * @param url the localized url of this notification
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setUrl(
		String url, java.util.Locale locale, java.util.Locale defaultLocale) {

		_notification.setUrl(url, locale, defaultLocale);
	}

	@Override
	public void setUrlCurrentLanguageId(String languageId) {
		_notification.setUrlCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized urls of this notification from the map of locales and localized urls.
	 *
	 * @param urlMap the locales and localized urls of this notification
	 */
	@Override
	public void setUrlMap(Map<java.util.Locale, String> urlMap) {
		_notification.setUrlMap(urlMap);
	}

	/**
	 * Sets the localized urls of this notification from the map of locales and localized urls, and sets the default locale.
	 *
	 * @param urlMap the locales and localized urls of this notification
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setUrlMap(
		Map<java.util.Locale, String> urlMap, java.util.Locale defaultLocale) {

		_notification.setUrlMap(urlMap, defaultLocale);
	}

	/**
	 * Sets the user ID of this notification.
	 *
	 * @param userId the user ID of this notification
	 */
	@Override
	public void setUserId(long userId) {
		_notification.setUserId(userId);
	}

	/**
	 * Sets the user name of this notification.
	 *
	 * @param userName the user name of this notification
	 */
	@Override
	public void setUserName(String userName) {
		_notification.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this notification.
	 *
	 * @param userUuid the user uuid of this notification
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_notification.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this notification.
	 *
	 * @param uuid the uuid of this notification
	 */
	@Override
	public void setUuid(String uuid) {
		_notification.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.notif.model.Notification> toCacheModel() {

		return _notification.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notif.model.Notification toEscapedModel() {
		return new NotificationWrapper(_notification.toEscapedModel());
	}

	@Override
	public String toString() {
		return _notification.toString();
	}

	@Override
	public eu.strasbourg.service.notif.model.Notification toUnescapedModel() {
		return new NotificationWrapper(_notification.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _notification.toXmlString();
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
	public StagedModelType getStagedModelType() {
		return _notification.getStagedModelType();
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
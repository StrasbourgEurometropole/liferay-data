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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageWrapper implements Message, ModelWrapper<Message> {

	public MessageWrapper(Message message) {
		_message = message;
	}

	@Override
	public Class<?> getModelClass() {
		return Message.class;
	}

	@Override
	public String getModelClassName() {
		return Message.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("serviceId", getServiceId());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@Override
	public Object clone() {
		return new MessageWrapper((Message)_message.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.notif.model.Message message) {
		return _message.compareTo(message);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _message.getAvailableLanguageIds();
	}

	/**
	 * Returns the content of this message.
	 *
	 * @return the content of this message
	 */
	@Override
	public String getContent() {
		return _message.getContent();
	}

	/**
	 * Returns the localized content of this message in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized content of this message
	 */
	@Override
	public String getContent(java.util.Locale locale) {
		return _message.getContent(locale);
	}

	/**
	 * Returns the localized content of this message in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized content of this message. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getContent(java.util.Locale locale, boolean useDefault) {
		return _message.getContent(locale, useDefault);
	}

	/**
	 * Returns the localized content of this message in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized content of this message
	 */
	@Override
	public String getContent(String languageId) {
		return _message.getContent(languageId);
	}

	/**
	 * Returns the localized content of this message in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized content of this message
	 */
	@Override
	public String getContent(String languageId, boolean useDefault) {
		return _message.getContent(languageId, useDefault);
	}

	@Override
	public String getContentCurrentLanguageId() {
		return _message.getContentCurrentLanguageId();
	}

	@Override
	public String getContentCurrentValue() {
		return _message.getContentCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized contents of this message.
	 *
	 * @return the locales and localized contents of this message
	 */
	@Override
	public Map<java.util.Locale, String> getContentMap() {
		return _message.getContentMap();
	}

	@Override
	public String getDefaultLanguageId() {
		return _message.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _message.getExpandoBridge();
	}

	/**
	 * Returns the message ID of this message.
	 *
	 * @return the message ID of this message
	 */
	@Override
	public long getMessageId() {
		return _message.getMessageId();
	}

	/**
	 * Returns the primary key of this message.
	 *
	 * @return the primary key of this message
	 */
	@Override
	public long getPrimaryKey() {
		return _message.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _message.getPrimaryKeyObj();
	}

	/**
	 * Returns the service ID of this message.
	 *
	 * @return the service ID of this message
	 */
	@Override
	public long getServiceId() {
		return _message.getServiceId();
	}

	@Override
	public int hashCode() {
		return _message.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _message.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _message.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _message.isNew();
	}

	@Override
	public void persist() {
		_message.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_message.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_message.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_message.setCachedModel(cachedModel);
	}

	/**
	 * Sets the content of this message.
	 *
	 * @param content the content of this message
	 */
	@Override
	public void setContent(String content) {
		_message.setContent(content);
	}

	/**
	 * Sets the localized content of this message in the language.
	 *
	 * @param content the localized content of this message
	 * @param locale the locale of the language
	 */
	@Override
	public void setContent(String content, java.util.Locale locale) {
		_message.setContent(content, locale);
	}

	/**
	 * Sets the localized content of this message in the language, and sets the default locale.
	 *
	 * @param content the localized content of this message
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setContent(
		String content, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_message.setContent(content, locale, defaultLocale);
	}

	@Override
	public void setContentCurrentLanguageId(String languageId) {
		_message.setContentCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized contents of this message from the map of locales and localized contents.
	 *
	 * @param contentMap the locales and localized contents of this message
	 */
	@Override
	public void setContentMap(Map<java.util.Locale, String> contentMap) {
		_message.setContentMap(contentMap);
	}

	/**
	 * Sets the localized contents of this message from the map of locales and localized contents, and sets the default locale.
	 *
	 * @param contentMap the locales and localized contents of this message
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setContentMap(
		Map<java.util.Locale, String> contentMap,
		java.util.Locale defaultLocale) {

		_message.setContentMap(contentMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_message.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_message.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_message.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the message ID of this message.
	 *
	 * @param messageId the message ID of this message
	 */
	@Override
	public void setMessageId(long messageId) {
		_message.setMessageId(messageId);
	}

	@Override
	public void setNew(boolean n) {
		_message.setNew(n);
	}

	/**
	 * Sets the primary key of this message.
	 *
	 * @param primaryKey the primary key of this message
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_message.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_message.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the service ID of this message.
	 *
	 * @param serviceId the service ID of this message
	 */
	@Override
	public void setServiceId(long serviceId) {
		_message.setServiceId(serviceId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.notif.model.Message> toCacheModel() {

		return _message.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notif.model.Message toEscapedModel() {
		return new MessageWrapper(_message.toEscapedModel());
	}

	@Override
	public String toString() {
		return _message.toString();
	}

	@Override
	public eu.strasbourg.service.notif.model.Message toUnescapedModel() {
		return new MessageWrapper(_message.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _message.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageWrapper)) {
			return false;
		}

		MessageWrapper messageWrapper = (MessageWrapper)object;

		if (Objects.equals(_message, messageWrapper._message)) {
			return true;
		}

		return false;
	}

	@Override
	public Message getWrappedModel() {
		return _message;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _message.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _message.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_message.resetOriginalValues();
	}

	private final Message _message;

}
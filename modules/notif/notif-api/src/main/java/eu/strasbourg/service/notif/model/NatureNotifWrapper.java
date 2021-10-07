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
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link NatureNotif}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NatureNotif
 * @generated
 */
@ProviderType
public class NatureNotifWrapper
	implements NatureNotif, ModelWrapper<NatureNotif> {

	public NatureNotifWrapper(NatureNotif natureNotif) {
		_natureNotif = natureNotif;
	}

	@Override
	public Class<?> getModelClass() {
		return NatureNotif.class;
	}

	@Override
	public String getModelClassName() {
		return NatureNotif.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("natureId", getNatureId());
		attributes.put("serviceId", getServiceId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long natureId = (Long)attributes.get("natureId");

		if (natureId != null) {
			setNatureId(natureId);
		}

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new NatureNotifWrapper((NatureNotif)_natureNotif.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notif.model.NatureNotif natureNotif) {

		return _natureNotif.compareTo(natureNotif);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _natureNotif.getAvailableLanguageIds();
	}

	@Override
	public String getDefaultLanguageId() {
		return _natureNotif.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _natureNotif.getExpandoBridge();
	}

	/**
	 * Returns the name of this nature notif.
	 *
	 * @return the name of this nature notif
	 */
	@Override
	public String getName() {
		return _natureNotif.getName();
	}

	/**
	 * Returns the localized name of this nature notif in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this nature notif
	 */
	@Override
	public String getName(java.util.Locale locale) {
		return _natureNotif.getName(locale);
	}

	/**
	 * Returns the localized name of this nature notif in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this nature notif. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _natureNotif.getName(locale, useDefault);
	}

	/**
	 * Returns the localized name of this nature notif in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this nature notif
	 */
	@Override
	public String getName(String languageId) {
		return _natureNotif.getName(languageId);
	}

	/**
	 * Returns the localized name of this nature notif in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this nature notif
	 */
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _natureNotif.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _natureNotif.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _natureNotif.getNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized names of this nature notif.
	 *
	 * @return the locales and localized names of this nature notif
	 */
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _natureNotif.getNameMap();
	}

	/**
	 * Returns the nature ID of this nature notif.
	 *
	 * @return the nature ID of this nature notif
	 */
	@Override
	public long getNatureId() {
		return _natureNotif.getNatureId();
	}

	/**
	 * Returns the primary key of this nature notif.
	 *
	 * @return the primary key of this nature notif
	 */
	@Override
	public long getPrimaryKey() {
		return _natureNotif.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _natureNotif.getPrimaryKeyObj();
	}

	/**
	 * Returns the service ID of this nature notif.
	 *
	 * @return the service ID of this nature notif
	 */
	@Override
	public long getServiceId() {
		return _natureNotif.getServiceId();
	}

	@Override
	public int hashCode() {
		return _natureNotif.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _natureNotif.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _natureNotif.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _natureNotif.isNew();
	}

	@Override
	public void persist() {
		_natureNotif.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_natureNotif.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_natureNotif.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_natureNotif.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_natureNotif.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_natureNotif.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_natureNotif.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the name of this nature notif.
	 *
	 * @param name the name of this nature notif
	 */
	@Override
	public void setName(String name) {
		_natureNotif.setName(name);
	}

	/**
	 * Sets the localized name of this nature notif in the language.
	 *
	 * @param name the localized name of this nature notif
	 * @param locale the locale of the language
	 */
	@Override
	public void setName(String name, java.util.Locale locale) {
		_natureNotif.setName(name, locale);
	}

	/**
	 * Sets the localized name of this nature notif in the language, and sets the default locale.
	 *
	 * @param name the localized name of this nature notif
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setName(
		String name, java.util.Locale locale, java.util.Locale defaultLocale) {

		_natureNotif.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_natureNotif.setNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized names of this nature notif from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this nature notif
	 */
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_natureNotif.setNameMap(nameMap);
	}

	/**
	 * Sets the localized names of this nature notif from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this nature notif
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNameMap(
		Map<java.util.Locale, String> nameMap, java.util.Locale defaultLocale) {

		_natureNotif.setNameMap(nameMap, defaultLocale);
	}

	/**
	 * Sets the nature ID of this nature notif.
	 *
	 * @param natureId the nature ID of this nature notif
	 */
	@Override
	public void setNatureId(long natureId) {
		_natureNotif.setNatureId(natureId);
	}

	@Override
	public void setNew(boolean n) {
		_natureNotif.setNew(n);
	}

	/**
	 * Sets the primary key of this nature notif.
	 *
	 * @param primaryKey the primary key of this nature notif
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_natureNotif.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_natureNotif.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the service ID of this nature notif.
	 *
	 * @param serviceId the service ID of this nature notif
	 */
	@Override
	public void setServiceId(long serviceId) {
		_natureNotif.setServiceId(serviceId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.notif.model.NatureNotif> toCacheModel() {

		return _natureNotif.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notif.model.NatureNotif toEscapedModel() {
		return new NatureNotifWrapper(_natureNotif.toEscapedModel());
	}

	@Override
	public String toString() {
		return _natureNotif.toString();
	}

	@Override
	public eu.strasbourg.service.notif.model.NatureNotif toUnescapedModel() {
		return new NatureNotifWrapper(_natureNotif.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _natureNotif.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NatureNotifWrapper)) {
			return false;
		}

		NatureNotifWrapper natureNotifWrapper = (NatureNotifWrapper)obj;

		if (Objects.equals(_natureNotif, natureNotifWrapper._natureNotif)) {
			return true;
		}

		return false;
	}

	@Override
	public NatureNotif getWrappedModel() {
		return _natureNotif;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _natureNotif.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _natureNotif.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_natureNotif.resetOriginalValues();
	}

	private final NatureNotif _natureNotif;

}
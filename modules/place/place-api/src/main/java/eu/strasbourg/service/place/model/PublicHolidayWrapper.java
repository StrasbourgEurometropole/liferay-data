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

package eu.strasbourg.service.place.model;

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
 * This class is a wrapper for {@link PublicHoliday}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PublicHoliday
 * @generated
 */
@ProviderType
public class PublicHolidayWrapper implements PublicHoliday,
	ModelWrapper<PublicHoliday> {
	public PublicHolidayWrapper(PublicHoliday publicHoliday) {
		_publicHoliday = publicHoliday;
	}

	@Override
	public Class<?> getModelClass() {
		return PublicHoliday.class;
	}

	@Override
	public String getModelClassName() {
		return PublicHoliday.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("publicHolidayId", getPublicHolidayId());
		attributes.put("name", getName());
		attributes.put("date", getDate());
		attributes.put("recurrent", getRecurrent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long publicHolidayId = (Long)attributes.get("publicHolidayId");

		if (publicHolidayId != null) {
			setPublicHolidayId(publicHolidayId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Boolean recurrent = (Boolean)attributes.get("recurrent");

		if (recurrent != null) {
			setRecurrent(recurrent);
		}
	}

	/**
	* Returns the recurrent of this public holiday.
	*
	* @return the recurrent of this public holiday
	*/
	@Override
	public boolean getRecurrent() {
		return _publicHoliday.getRecurrent();
	}

	@Override
	public boolean isCachedModel() {
		return _publicHoliday.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _publicHoliday.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _publicHoliday.isNew();
	}

	/**
	* Returns <code>true</code> if this public holiday is recurrent.
	*
	* @return <code>true</code> if this public holiday is recurrent; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecurrent() {
		return _publicHoliday.isRecurrent();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _publicHoliday.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.PublicHoliday> toCacheModel() {
		return _publicHoliday.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.PublicHoliday toEscapedModel() {
		return new PublicHolidayWrapper(_publicHoliday.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.PublicHoliday toUnescapedModel() {
		return new PublicHolidayWrapper(_publicHoliday.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.place.model.PublicHoliday publicHoliday) {
		return _publicHoliday.compareTo(publicHoliday);
	}

	@Override
	public int hashCode() {
		return _publicHoliday.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _publicHoliday.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PublicHolidayWrapper((PublicHoliday)_publicHoliday.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _publicHoliday.getDefaultLanguageId();
	}

	/**
	* Returns the name of this public holiday.
	*
	* @return the name of this public holiday
	*/
	@Override
	public java.lang.String getName() {
		return _publicHoliday.getName();
	}

	/**
	* Returns the localized name of this public holiday in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this public holiday
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _publicHoliday.getName(languageId);
	}

	/**
	* Returns the localized name of this public holiday in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this public holiday
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _publicHoliday.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this public holiday in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this public holiday
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _publicHoliday.getName(locale);
	}

	/**
	* Returns the localized name of this public holiday in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this public holiday. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _publicHoliday.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _publicHoliday.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _publicHoliday.getNameCurrentValue();
	}

	/**
	* Returns the uuid of this public holiday.
	*
	* @return the uuid of this public holiday
	*/
	@Override
	public java.lang.String getUuid() {
		return _publicHoliday.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _publicHoliday.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _publicHoliday.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _publicHoliday.getAvailableLanguageIds();
	}

	/**
	* Returns the date of this public holiday.
	*
	* @return the date of this public holiday
	*/
	@Override
	public Date getDate() {
		return _publicHoliday.getDate();
	}

	/**
	* Returns a map of the locales and localized names of this public holiday.
	*
	* @return the locales and localized names of this public holiday
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _publicHoliday.getNameMap();
	}

	/**
	* Returns the primary key of this public holiday.
	*
	* @return the primary key of this public holiday
	*/
	@Override
	public long getPrimaryKey() {
		return _publicHoliday.getPrimaryKey();
	}

	/**
	* Returns the public holiday ID of this public holiday.
	*
	* @return the public holiday ID of this public holiday
	*/
	@Override
	public long getPublicHolidayId() {
		return _publicHoliday.getPublicHolidayId();
	}

	@Override
	public void persist() {
		_publicHoliday.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_publicHoliday.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_publicHoliday.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_publicHoliday.setCachedModel(cachedModel);
	}

	/**
	* Sets the date of this public holiday.
	*
	* @param date the date of this public holiday
	*/
	@Override
	public void setDate(Date date) {
		_publicHoliday.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_publicHoliday.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_publicHoliday.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_publicHoliday.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the name of this public holiday.
	*
	* @param name the name of this public holiday
	*/
	@Override
	public void setName(java.lang.String name) {
		_publicHoliday.setName(name);
	}

	/**
	* Sets the localized name of this public holiday in the language.
	*
	* @param name the localized name of this public holiday
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_publicHoliday.setName(name, locale);
	}

	/**
	* Sets the localized name of this public holiday in the language, and sets the default locale.
	*
	* @param name the localized name of this public holiday
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_publicHoliday.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_publicHoliday.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this public holiday from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this public holiday
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_publicHoliday.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this public holiday from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this public holiday
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_publicHoliday.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_publicHoliday.setNew(n);
	}

	/**
	* Sets the primary key of this public holiday.
	*
	* @param primaryKey the primary key of this public holiday
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_publicHoliday.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_publicHoliday.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the public holiday ID of this public holiday.
	*
	* @param publicHolidayId the public holiday ID of this public holiday
	*/
	@Override
	public void setPublicHolidayId(long publicHolidayId) {
		_publicHoliday.setPublicHolidayId(publicHolidayId);
	}

	/**
	* Sets whether this public holiday is recurrent.
	*
	* @param recurrent the recurrent of this public holiday
	*/
	@Override
	public void setRecurrent(boolean recurrent) {
		_publicHoliday.setRecurrent(recurrent);
	}

	/**
	* Sets the uuid of this public holiday.
	*
	* @param uuid the uuid of this public holiday
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_publicHoliday.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublicHolidayWrapper)) {
			return false;
		}

		PublicHolidayWrapper publicHolidayWrapper = (PublicHolidayWrapper)obj;

		if (Objects.equals(_publicHoliday, publicHolidayWrapper._publicHoliday)) {
			return true;
		}

		return false;
	}

	@Override
	public PublicHoliday getWrappedModel() {
		return _publicHoliday;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _publicHoliday.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _publicHoliday.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_publicHoliday.resetOriginalValues();
	}

	private final PublicHoliday _publicHoliday;
}
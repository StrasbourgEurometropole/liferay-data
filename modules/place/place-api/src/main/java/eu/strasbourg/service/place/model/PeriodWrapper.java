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
 * This class is a wrapper for {@link Period}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Period
 * @generated
 */
@ProviderType
public class PeriodWrapper implements Period, ModelWrapper<Period> {
	public PeriodWrapper(Period period) {
		_period = period;
	}

	@Override
	public Class<?> getModelClass() {
		return Period.class;
	}

	@Override
	public String getModelClassName() {
		return Period.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("periodId", getPeriodId());
		attributes.put("name", getName());
		attributes.put("defaultPeriod", getDefaultPeriod());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("linkLabel", getLinkLabel());
		attributes.put("linkURL", getLinkURL());
		attributes.put("alwaysOpen", getAlwaysOpen());
		attributes.put("RTGreenThreshold", getRTGreenThreshold());
		attributes.put("RTOrangeThreshold", getRTOrangeThreshold());
		attributes.put("RTRedThreshold", getRTRedThreshold());
		attributes.put("RTMaxThreshold", getRTMaxThreshold());
		attributes.put("placeId", getPlaceId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long periodId = (Long)attributes.get("periodId");

		if (periodId != null) {
			setPeriodId(periodId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean defaultPeriod = (Boolean)attributes.get("defaultPeriod");

		if (defaultPeriod != null) {
			setDefaultPeriod(defaultPeriod);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String linkLabel = (String)attributes.get("linkLabel");

		if (linkLabel != null) {
			setLinkLabel(linkLabel);
		}

		String linkURL = (String)attributes.get("linkURL");

		if (linkURL != null) {
			setLinkURL(linkURL);
		}

		Boolean alwaysOpen = (Boolean)attributes.get("alwaysOpen");

		if (alwaysOpen != null) {
			setAlwaysOpen(alwaysOpen);
		}

		Long RTGreenThreshold = (Long)attributes.get("RTGreenThreshold");

		if (RTGreenThreshold != null) {
			setRTGreenThreshold(RTGreenThreshold);
		}

		Long RTOrangeThreshold = (Long)attributes.get("RTOrangeThreshold");

		if (RTOrangeThreshold != null) {
			setRTOrangeThreshold(RTOrangeThreshold);
		}

		Long RTRedThreshold = (Long)attributes.get("RTRedThreshold");

		if (RTRedThreshold != null) {
			setRTRedThreshold(RTRedThreshold);
		}

		Long RTMaxThreshold = (Long)attributes.get("RTMaxThreshold");

		if (RTMaxThreshold != null) {
			setRTMaxThreshold(RTMaxThreshold);
		}

		Long placeId = (Long)attributes.get("placeId");

		if (placeId != null) {
			setPlaceId(placeId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _period.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _period.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _period.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _period.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de la période
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _period.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.Period> toCacheModel() {
		return _period.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.Period toEscapedModel() {
		return new PeriodWrapper(_period.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.Period toUnescapedModel() {
		return new PeriodWrapper(_period.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Period period) {
		return _period.compareTo(period);
	}

	@Override
	public int hashCode() {
		return _period.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _period.getPrimaryKeyObj();
	}

	/**
	* Returns the always open of this period.
	*
	* @return the always open of this period
	*/
	@Override
	public java.lang.Boolean getAlwaysOpen() {
		return _period.getAlwaysOpen();
	}

	/**
	* Returns the default period of this period.
	*
	* @return the default period of this period
	*/
	@Override
	public java.lang.Boolean getDefaultPeriod() {
		return _period.getDefaultPeriod();
	}

	@Override
	public java.lang.Object clone() {
		return new PeriodWrapper((Period)_period.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _period.getDefaultLanguageId();
	}

	@Override
	public java.lang.String getDisplay(java.util.Locale locale) {
		return _period.getDisplay(locale);
	}

	/**
	* Returns the link label of this period.
	*
	* @return the link label of this period
	*/
	@Override
	public java.lang.String getLinkLabel() {
		return _period.getLinkLabel();
	}

	/**
	* Returns the localized link label of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized link label of this period
	*/
	@Override
	public java.lang.String getLinkLabel(java.lang.String languageId) {
		return _period.getLinkLabel(languageId);
	}

	/**
	* Returns the localized link label of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized link label of this period
	*/
	@Override
	public java.lang.String getLinkLabel(java.lang.String languageId,
		boolean useDefault) {
		return _period.getLinkLabel(languageId, useDefault);
	}

	/**
	* Returns the localized link label of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized link label of this period
	*/
	@Override
	public java.lang.String getLinkLabel(java.util.Locale locale) {
		return _period.getLinkLabel(locale);
	}

	/**
	* Returns the localized link label of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized link label of this period. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getLinkLabel(java.util.Locale locale,
		boolean useDefault) {
		return _period.getLinkLabel(locale, useDefault);
	}

	@Override
	public java.lang.String getLinkLabelCurrentLanguageId() {
		return _period.getLinkLabelCurrentLanguageId();
	}

	@Override
	public java.lang.String getLinkLabelCurrentValue() {
		return _period.getLinkLabelCurrentValue();
	}

	/**
	* Returns the link url of this period.
	*
	* @return the link url of this period
	*/
	@Override
	public java.lang.String getLinkURL() {
		return _period.getLinkURL();
	}

	/**
	* Returns the localized link url of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized link url of this period
	*/
	@Override
	public java.lang.String getLinkURL(java.lang.String languageId) {
		return _period.getLinkURL(languageId);
	}

	/**
	* Returns the localized link url of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized link url of this period
	*/
	@Override
	public java.lang.String getLinkURL(java.lang.String languageId,
		boolean useDefault) {
		return _period.getLinkURL(languageId, useDefault);
	}

	/**
	* Returns the localized link url of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized link url of this period
	*/
	@Override
	public java.lang.String getLinkURL(java.util.Locale locale) {
		return _period.getLinkURL(locale);
	}

	/**
	* Returns the localized link url of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized link url of this period. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getLinkURL(java.util.Locale locale,
		boolean useDefault) {
		return _period.getLinkURL(locale, useDefault);
	}

	@Override
	public java.lang.String getLinkURLCurrentLanguageId() {
		return _period.getLinkURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getLinkURLCurrentValue() {
		return _period.getLinkURLCurrentValue();
	}

	/**
	* Returns the name of this period.
	*
	* @return the name of this period
	*/
	@Override
	public java.lang.String getName() {
		return _period.getName();
	}

	/**
	* Returns the localized name of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this period
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _period.getName(languageId);
	}

	/**
	* Returns the localized name of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this period
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _period.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this period
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _period.getName(locale);
	}

	/**
	* Returns the localized name of this period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this period. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _period.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _period.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _period.getNameCurrentValue();
	}

	/**
	* Returns the uuid of this period.
	*
	* @return the uuid of this period
	*/
	@Override
	public java.lang.String getUuid() {
		return _period.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _period.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _period.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _period.getAvailableLanguageIds();
	}

	/**
	* Returns the end date of this period.
	*
	* @return the end date of this period
	*/
	@Override
	public Date getEndDate() {
		return _period.getEndDate();
	}

	/**
	* Returns the start date of this period.
	*
	* @return the start date of this period
	*/
	@Override
	public Date getStartDate() {
		return _period.getStartDate();
	}

	/**
	* Retourne les Slots de la période pour un lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getAllSlots() {
		return _period.getAllSlots();
	}

	/**
	* Retourne les Slots de la période pour un lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getSlots() {
		return _period.getSlots();
	}

	/**
	* Retourne les Slots de la période pour un sous-lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Slot> getSlots(
		long subPlaceId) {
		return _period.getSlots(subPlaceId);
	}

	/**
	* Retourne la liste des horaires par jour (0 = lundi, 1 = mardi, etc.)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getWeekSchedule() {
		return _period.getWeekSchedule();
	}

	/**
	* Retourne la liste des horaires par jour pour le sous lieu (0 = lundi, 1 = mardi, etc.)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getWeekSchedule(
		long subPlaceId) {
		return _period.getWeekSchedule(subPlaceId);
	}

	/**
	* Returns a map of the locales and localized link labels of this period.
	*
	* @return the locales and localized link labels of this period
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLinkLabelMap() {
		return _period.getLinkLabelMap();
	}

	/**
	* Returns a map of the locales and localized link urls of this period.
	*
	* @return the locales and localized link urls of this period
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLinkURLMap() {
		return _period.getLinkURLMap();
	}

	/**
	* Returns a map of the locales and localized names of this period.
	*
	* @return the locales and localized names of this period
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _period.getNameMap();
	}

	/**
	* Returns the period ID of this period.
	*
	* @return the period ID of this period
	*/
	@Override
	public long getPeriodId() {
		return _period.getPeriodId();
	}

	/**
	* Returns the place ID of this period.
	*
	* @return the place ID of this period
	*/
	@Override
	public long getPlaceId() {
		return _period.getPlaceId();
	}

	/**
	* Returns the primary key of this period.
	*
	* @return the primary key of this period
	*/
	@Override
	public long getPrimaryKey() {
		return _period.getPrimaryKey();
	}

	/**
	* Returns the rt green threshold of this period.
	*
	* @return the rt green threshold of this period
	*/
	@Override
	public long getRTGreenThreshold() {
		return _period.getRTGreenThreshold();
	}

	/**
	* Returns the rt max threshold of this period.
	*
	* @return the rt max threshold of this period
	*/
	@Override
	public long getRTMaxThreshold() {
		return _period.getRTMaxThreshold();
	}

	/**
	* Returns the rt orange threshold of this period.
	*
	* @return the rt orange threshold of this period
	*/
	@Override
	public long getRTOrangeThreshold() {
		return _period.getRTOrangeThreshold();
	}

	/**
	* Returns the rt red threshold of this period.
	*
	* @return the rt red threshold of this period
	*/
	@Override
	public long getRTRedThreshold() {
		return _period.getRTRedThreshold();
	}

	@Override
	public void persist() {
		_period.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_period.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_period.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the always open of this period.
	*
	* @param alwaysOpen the always open of this period
	*/
	@Override
	public void setAlwaysOpen(java.lang.Boolean alwaysOpen) {
		_period.setAlwaysOpen(alwaysOpen);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_period.setCachedModel(cachedModel);
	}

	/**
	* Sets the default period of this period.
	*
	* @param defaultPeriod the default period of this period
	*/
	@Override
	public void setDefaultPeriod(java.lang.Boolean defaultPeriod) {
		_period.setDefaultPeriod(defaultPeriod);
	}

	/**
	* Sets the end date of this period.
	*
	* @param endDate the end date of this period
	*/
	@Override
	public void setEndDate(Date endDate) {
		_period.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_period.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_period.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_period.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the link label of this period.
	*
	* @param linkLabel the link label of this period
	*/
	@Override
	public void setLinkLabel(java.lang.String linkLabel) {
		_period.setLinkLabel(linkLabel);
	}

	/**
	* Sets the localized link label of this period in the language.
	*
	* @param linkLabel the localized link label of this period
	* @param locale the locale of the language
	*/
	@Override
	public void setLinkLabel(java.lang.String linkLabel, java.util.Locale locale) {
		_period.setLinkLabel(linkLabel, locale);
	}

	/**
	* Sets the localized link label of this period in the language, and sets the default locale.
	*
	* @param linkLabel the localized link label of this period
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLinkLabel(java.lang.String linkLabel,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_period.setLinkLabel(linkLabel, locale, defaultLocale);
	}

	@Override
	public void setLinkLabelCurrentLanguageId(java.lang.String languageId) {
		_period.setLinkLabelCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized link labels of this period from the map of locales and localized link labels.
	*
	* @param linkLabelMap the locales and localized link labels of this period
	*/
	@Override
	public void setLinkLabelMap(
		Map<java.util.Locale, java.lang.String> linkLabelMap) {
		_period.setLinkLabelMap(linkLabelMap);
	}

	/**
	* Sets the localized link labels of this period from the map of locales and localized link labels, and sets the default locale.
	*
	* @param linkLabelMap the locales and localized link labels of this period
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLinkLabelMap(
		Map<java.util.Locale, java.lang.String> linkLabelMap,
		java.util.Locale defaultLocale) {
		_period.setLinkLabelMap(linkLabelMap, defaultLocale);
	}

	/**
	* Sets the link url of this period.
	*
	* @param linkURL the link url of this period
	*/
	@Override
	public void setLinkURL(java.lang.String linkURL) {
		_period.setLinkURL(linkURL);
	}

	/**
	* Sets the localized link url of this period in the language.
	*
	* @param linkURL the localized link url of this period
	* @param locale the locale of the language
	*/
	@Override
	public void setLinkURL(java.lang.String linkURL, java.util.Locale locale) {
		_period.setLinkURL(linkURL, locale);
	}

	/**
	* Sets the localized link url of this period in the language, and sets the default locale.
	*
	* @param linkURL the localized link url of this period
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLinkURL(java.lang.String linkURL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_period.setLinkURL(linkURL, locale, defaultLocale);
	}

	@Override
	public void setLinkURLCurrentLanguageId(java.lang.String languageId) {
		_period.setLinkURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized link urls of this period from the map of locales and localized link urls.
	*
	* @param linkURLMap the locales and localized link urls of this period
	*/
	@Override
	public void setLinkURLMap(
		Map<java.util.Locale, java.lang.String> linkURLMap) {
		_period.setLinkURLMap(linkURLMap);
	}

	/**
	* Sets the localized link urls of this period from the map of locales and localized link urls, and sets the default locale.
	*
	* @param linkURLMap the locales and localized link urls of this period
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLinkURLMap(
		Map<java.util.Locale, java.lang.String> linkURLMap,
		java.util.Locale defaultLocale) {
		_period.setLinkURLMap(linkURLMap, defaultLocale);
	}

	/**
	* Sets the name of this period.
	*
	* @param name the name of this period
	*/
	@Override
	public void setName(java.lang.String name) {
		_period.setName(name);
	}

	/**
	* Sets the localized name of this period in the language.
	*
	* @param name the localized name of this period
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_period.setName(name, locale);
	}

	/**
	* Sets the localized name of this period in the language, and sets the default locale.
	*
	* @param name the localized name of this period
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_period.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_period.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this period from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this period
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_period.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this period from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this period
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_period.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_period.setNew(n);
	}

	/**
	* Sets the period ID of this period.
	*
	* @param periodId the period ID of this period
	*/
	@Override
	public void setPeriodId(long periodId) {
		_period.setPeriodId(periodId);
	}

	/**
	* Sets the place ID of this period.
	*
	* @param placeId the place ID of this period
	*/
	@Override
	public void setPlaceId(long placeId) {
		_period.setPlaceId(placeId);
	}

	/**
	* Sets the primary key of this period.
	*
	* @param primaryKey the primary key of this period
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_period.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_period.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rt green threshold of this period.
	*
	* @param RTGreenThreshold the rt green threshold of this period
	*/
	@Override
	public void setRTGreenThreshold(long RTGreenThreshold) {
		_period.setRTGreenThreshold(RTGreenThreshold);
	}

	/**
	* Sets the rt max threshold of this period.
	*
	* @param RTMaxThreshold the rt max threshold of this period
	*/
	@Override
	public void setRTMaxThreshold(long RTMaxThreshold) {
		_period.setRTMaxThreshold(RTMaxThreshold);
	}

	/**
	* Sets the rt orange threshold of this period.
	*
	* @param RTOrangeThreshold the rt orange threshold of this period
	*/
	@Override
	public void setRTOrangeThreshold(long RTOrangeThreshold) {
		_period.setRTOrangeThreshold(RTOrangeThreshold);
	}

	/**
	* Sets the rt red threshold of this period.
	*
	* @param RTRedThreshold the rt red threshold of this period
	*/
	@Override
	public void setRTRedThreshold(long RTRedThreshold) {
		_period.setRTRedThreshold(RTRedThreshold);
	}

	/**
	* Sets the start date of this period.
	*
	* @param startDate the start date of this period
	*/
	@Override
	public void setStartDate(Date startDate) {
		_period.setStartDate(startDate);
	}

	/**
	* Sets the uuid of this period.
	*
	* @param uuid the uuid of this period
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_period.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PeriodWrapper)) {
			return false;
		}

		PeriodWrapper periodWrapper = (PeriodWrapper)obj;

		if (Objects.equals(_period, periodWrapper._period)) {
			return true;
		}

		return false;
	}

	@Override
	public Period getWrappedModel() {
		return _period;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _period.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _period.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_period.resetOriginalValues();
	}

	private final Period _period;
}
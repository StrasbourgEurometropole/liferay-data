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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link EventPeriod}.
 * </p>
 *
 * @author BenjaminBini
 * @see EventPeriod
 * @generated
 */
@ProviderType
public class EventPeriodWrapper implements EventPeriod,
	ModelWrapper<EventPeriod> {
	public EventPeriodWrapper(EventPeriod eventPeriod) {
		_eventPeriod = eventPeriod;
	}

	@Override
	public Class<?> getModelClass() {
		return EventPeriod.class;
	}

	@Override
	public String getModelClassName() {
		return EventPeriod.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("eventPeriodId", getEventPeriodId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("timeDetail", getTimeDetail());
		attributes.put("eventId", getEventId());
		attributes.put("campaignEventId", getCampaignEventId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long eventPeriodId = (Long)attributes.get("eventPeriodId");

		if (eventPeriodId != null) {
			setEventPeriodId(eventPeriodId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String timeDetail = (String)attributes.get("timeDetail");

		if (timeDetail != null) {
			setTimeDetail(timeDetail);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long campaignEventId = (Long)attributes.get("campaignEventId");

		if (campaignEventId != null) {
			setCampaignEventId(campaignEventId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _eventPeriod.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _eventPeriod.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _eventPeriod.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _eventPeriod.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.EventPeriod> toCacheModel() {
		return _eventPeriod.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod toEscapedModel() {
		return new EventPeriodWrapper(_eventPeriod.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod toUnescapedModel() {
		return new EventPeriodWrapper(_eventPeriod.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.EventPeriod eventPeriod) {
		return _eventPeriod.compareTo(eventPeriod);
	}

	@Override
	public int hashCode() {
		return _eventPeriod.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _eventPeriod.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EventPeriodWrapper((EventPeriod)_eventPeriod.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _eventPeriod.getDefaultLanguageId();
	}

	@Override
	public java.lang.String getDisplay(java.util.Locale locale) {
		return _eventPeriod.getDisplay(locale);
	}

	/**
	* Returns the time detail of this event period.
	*
	* @return the time detail of this event period
	*/
	@Override
	public java.lang.String getTimeDetail() {
		return _eventPeriod.getTimeDetail();
	}

	/**
	* Returns the localized time detail of this event period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized time detail of this event period
	*/
	@Override
	public java.lang.String getTimeDetail(java.lang.String languageId) {
		return _eventPeriod.getTimeDetail(languageId);
	}

	/**
	* Returns the localized time detail of this event period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized time detail of this event period
	*/
	@Override
	public java.lang.String getTimeDetail(java.lang.String languageId,
		boolean useDefault) {
		return _eventPeriod.getTimeDetail(languageId, useDefault);
	}

	/**
	* Returns the localized time detail of this event period in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized time detail of this event period
	*/
	@Override
	public java.lang.String getTimeDetail(java.util.Locale locale) {
		return _eventPeriod.getTimeDetail(locale);
	}

	/**
	* Returns the localized time detail of this event period in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized time detail of this event period. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTimeDetail(java.util.Locale locale,
		boolean useDefault) {
		return _eventPeriod.getTimeDetail(locale, useDefault);
	}

	@Override
	public java.lang.String getTimeDetailCurrentLanguageId() {
		return _eventPeriod.getTimeDetailCurrentLanguageId();
	}

	@Override
	public java.lang.String getTimeDetailCurrentValue() {
		return _eventPeriod.getTimeDetailCurrentValue();
	}

	/**
	* Returns the uuid of this event period.
	*
	* @return the uuid of this event period
	*/
	@Override
	public java.lang.String getUuid() {
		return _eventPeriod.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _eventPeriod.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _eventPeriod.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _eventPeriod.getAvailableLanguageIds();
	}

	/**
	* Returns the end date of this event period.
	*
	* @return the end date of this event period
	*/
	@Override
	public Date getEndDate() {
		return _eventPeriod.getEndDate();
	}

	/**
	* Returns the start date of this event period.
	*
	* @return the start date of this event period
	*/
	@Override
	public Date getStartDate() {
		return _eventPeriod.getStartDate();
	}

	/**
	* Returns a map of the locales and localized time details of this event period.
	*
	* @return the locales and localized time details of this event period
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTimeDetailMap() {
		return _eventPeriod.getTimeDetailMap();
	}

	/**
	* Returns the campaign event ID of this event period.
	*
	* @return the campaign event ID of this event period
	*/
	@Override
	public long getCampaignEventId() {
		return _eventPeriod.getCampaignEventId();
	}

	/**
	* Returns the event ID of this event period.
	*
	* @return the event ID of this event period
	*/
	@Override
	public long getEventId() {
		return _eventPeriod.getEventId();
	}

	/**
	* Returns the event period ID of this event period.
	*
	* @return the event period ID of this event period
	*/
	@Override
	public long getEventPeriodId() {
		return _eventPeriod.getEventPeriodId();
	}

	/**
	* Returns the primary key of this event period.
	*
	* @return the primary key of this event period
	*/
	@Override
	public long getPrimaryKey() {
		return _eventPeriod.getPrimaryKey();
	}

	@Override
	public void persist() {
		_eventPeriod.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_eventPeriod.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_eventPeriod.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_eventPeriod.setCachedModel(cachedModel);
	}

	/**
	* Sets the campaign event ID of this event period.
	*
	* @param campaignEventId the campaign event ID of this event period
	*/
	@Override
	public void setCampaignEventId(long campaignEventId) {
		_eventPeriod.setCampaignEventId(campaignEventId);
	}

	/**
	* Sets the end date of this event period.
	*
	* @param endDate the end date of this event period
	*/
	@Override
	public void setEndDate(Date endDate) {
		_eventPeriod.setEndDate(endDate);
	}

	/**
	* Sets the event ID of this event period.
	*
	* @param eventId the event ID of this event period
	*/
	@Override
	public void setEventId(long eventId) {
		_eventPeriod.setEventId(eventId);
	}

	/**
	* Sets the event period ID of this event period.
	*
	* @param eventPeriodId the event period ID of this event period
	*/
	@Override
	public void setEventPeriodId(long eventPeriodId) {
		_eventPeriod.setEventPeriodId(eventPeriodId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_eventPeriod.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_eventPeriod.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_eventPeriod.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_eventPeriod.setNew(n);
	}

	/**
	* Sets the primary key of this event period.
	*
	* @param primaryKey the primary key of this event period
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_eventPeriod.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_eventPeriod.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this event period.
	*
	* @param startDate the start date of this event period
	*/
	@Override
	public void setStartDate(Date startDate) {
		_eventPeriod.setStartDate(startDate);
	}

	/**
	* Sets the time detail of this event period.
	*
	* @param timeDetail the time detail of this event period
	*/
	@Override
	public void setTimeDetail(java.lang.String timeDetail) {
		_eventPeriod.setTimeDetail(timeDetail);
	}

	/**
	* Sets the localized time detail of this event period in the language.
	*
	* @param timeDetail the localized time detail of this event period
	* @param locale the locale of the language
	*/
	@Override
	public void setTimeDetail(java.lang.String timeDetail,
		java.util.Locale locale) {
		_eventPeriod.setTimeDetail(timeDetail, locale);
	}

	/**
	* Sets the localized time detail of this event period in the language, and sets the default locale.
	*
	* @param timeDetail the localized time detail of this event period
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTimeDetail(java.lang.String timeDetail,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_eventPeriod.setTimeDetail(timeDetail, locale, defaultLocale);
	}

	@Override
	public void setTimeDetailCurrentLanguageId(java.lang.String languageId) {
		_eventPeriod.setTimeDetailCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized time details of this event period from the map of locales and localized time details.
	*
	* @param timeDetailMap the locales and localized time details of this event period
	*/
	@Override
	public void setTimeDetailMap(
		Map<java.util.Locale, java.lang.String> timeDetailMap) {
		_eventPeriod.setTimeDetailMap(timeDetailMap);
	}

	/**
	* Sets the localized time details of this event period from the map of locales and localized time details, and sets the default locale.
	*
	* @param timeDetailMap the locales and localized time details of this event period
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTimeDetailMap(
		Map<java.util.Locale, java.lang.String> timeDetailMap,
		java.util.Locale defaultLocale) {
		_eventPeriod.setTimeDetailMap(timeDetailMap, defaultLocale);
	}

	/**
	* Sets the uuid of this event period.
	*
	* @param uuid the uuid of this event period
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_eventPeriod.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventPeriodWrapper)) {
			return false;
		}

		EventPeriodWrapper eventPeriodWrapper = (EventPeriodWrapper)obj;

		if (Objects.equals(_eventPeriod, eventPeriodWrapper._eventPeriod)) {
			return true;
		}

		return false;
	}

	@Override
	public EventPeriod getWrappedModel() {
		return _eventPeriod;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _eventPeriod.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _eventPeriod.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_eventPeriod.resetOriginalValues();
	}

	private final EventPeriod _eventPeriod;
}
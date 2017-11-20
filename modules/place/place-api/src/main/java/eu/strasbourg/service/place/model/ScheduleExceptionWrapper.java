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
 * This class is a wrapper for {@link ScheduleException}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleException
 * @generated
 */
@ProviderType
public class ScheduleExceptionWrapper implements ScheduleException,
	ModelWrapper<ScheduleException> {
	public ScheduleExceptionWrapper(ScheduleException scheduleException) {
		_scheduleException = scheduleException;
	}

	@Override
	public Class<?> getModelClass() {
		return ScheduleException.class;
	}

	@Override
	public String getModelClassName() {
		return ScheduleException.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("exceptionId", getExceptionId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("openingTimes", getOpeningTimes());
		attributes.put("comment", getComment());
		attributes.put("closed", getClosed());
		attributes.put("placeId", getPlaceId());
		attributes.put("subPlaceId", getSubPlaceId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long exceptionId = (Long)attributes.get("exceptionId");

		if (exceptionId != null) {
			setExceptionId(exceptionId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String openingTimes = (String)attributes.get("openingTimes");

		if (openingTimes != null) {
			setOpeningTimes(openingTimes);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Boolean closed = (Boolean)attributes.get("closed");

		if (closed != null) {
			setClosed(closed);
		}

		Long placeId = (Long)attributes.get("placeId");

		if (placeId != null) {
			setPlaceId(placeId);
		}

		Long subPlaceId = (Long)attributes.get("subPlaceId");

		if (subPlaceId != null) {
			setSubPlaceId(subPlaceId);
		}
	}

	/**
	* Returns the closed of this schedule exception.
	*
	* @return the closed of this schedule exception
	*/
	@Override
	public boolean getClosed() {
		return _scheduleException.getClosed();
	}

	@Override
	public boolean isCachedModel() {
		return _scheduleException.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this schedule exception is closed.
	*
	* @return <code>true</code> if this schedule exception is closed; <code>false</code> otherwise
	*/
	@Override
	public boolean isClosed() {
		return _scheduleException.isClosed();
	}

	@Override
	public boolean isEscapedModel() {
		return _scheduleException.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _scheduleException.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _scheduleException.getExpandoBridge();
	}

	/**
	* Retourne la version JSON des exceptions
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _scheduleException.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.ScheduleException> toCacheModel() {
		return _scheduleException.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.ScheduleException toEscapedModel() {
		return new ScheduleExceptionWrapper(_scheduleException.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.ScheduleException toUnescapedModel() {
		return new ScheduleExceptionWrapper(_scheduleException.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.place.model.ScheduleException scheduleException) {
		return _scheduleException.compareTo(scheduleException);
	}

	@Override
	public int hashCode() {
		return _scheduleException.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scheduleException.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ScheduleExceptionWrapper((ScheduleException)_scheduleException.clone());
	}

	/**
	* Returns the comment of this schedule exception.
	*
	* @return the comment of this schedule exception
	*/
	@Override
	public java.lang.String getComment() {
		return _scheduleException.getComment();
	}

	/**
	* Returns the localized comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized comment of this schedule exception
	*/
	@Override
	public java.lang.String getComment(java.lang.String languageId) {
		return _scheduleException.getComment(languageId);
	}

	/**
	* Returns the localized comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comment of this schedule exception
	*/
	@Override
	public java.lang.String getComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getComment(languageId, useDefault);
	}

	/**
	* Returns the localized comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized comment of this schedule exception
	*/
	@Override
	public java.lang.String getComment(java.util.Locale locale) {
		return _scheduleException.getComment(locale);
	}

	/**
	* Returns the localized comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getComment(locale, useDefault);
	}

	@Override
	public java.lang.String getCommentCurrentLanguageId() {
		return _scheduleException.getCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getCommentCurrentValue() {
		return _scheduleException.getCommentCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _scheduleException.getDefaultLanguageId();
	}

	/**
	* Returns the opening times of this schedule exception.
	*
	* @return the opening times of this schedule exception
	*/
	@Override
	public java.lang.String getOpeningTimes() {
		return _scheduleException.getOpeningTimes();
	}

	/**
	* Returns the uuid of this schedule exception.
	*
	* @return the uuid of this schedule exception
	*/
	@Override
	public java.lang.String getUuid() {
		return _scheduleException.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _scheduleException.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scheduleException.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _scheduleException.getAvailableLanguageIds();
	}

	/**
	* Retourne la Nème heure de début
	*/
	@Override
	public java.time.LocalTime getEndHour(int index) {
		return _scheduleException.getEndHour(index);
	}

	/**
	* Retourne la Nème heure de début
	*/
	@Override
	public java.time.LocalTime getStartHour(int index) {
		return _scheduleException.getStartHour(index);
	}

	/**
	* Returns the end date of this schedule exception.
	*
	* @return the end date of this schedule exception
	*/
	@Override
	public Date getEndDate() {
		return _scheduleException.getEndDate();
	}

	/**
	* Returns the start date of this schedule exception.
	*
	* @return the start date of this schedule exception
	*/
	@Override
	public Date getStartDate() {
		return _scheduleException.getStartDate();
	}

	/**
	* Retourne la liste des horaires d'ouvertures d'une exception
	*/
	@Override
	public java.util.List<eu.strasbourg.utils.models.Pair<java.time.LocalTime, java.time.LocalTime>> getOpeningLocalTimes() {
		return _scheduleException.getOpeningLocalTimes();
	}

	/**
	* Returns a map of the locales and localized comments of this schedule exception.
	*
	* @return the locales and localized comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getCommentMap() {
		return _scheduleException.getCommentMap();
	}

	/**
	* Returns the exception ID of this schedule exception.
	*
	* @return the exception ID of this schedule exception
	*/
	@Override
	public long getExceptionId() {
		return _scheduleException.getExceptionId();
	}

	/**
	* Returns the place ID of this schedule exception.
	*
	* @return the place ID of this schedule exception
	*/
	@Override
	public long getPlaceId() {
		return _scheduleException.getPlaceId();
	}

	/**
	* Returns the primary key of this schedule exception.
	*
	* @return the primary key of this schedule exception
	*/
	@Override
	public long getPrimaryKey() {
		return _scheduleException.getPrimaryKey();
	}

	/**
	* Returns the sub place ID of this schedule exception.
	*
	* @return the sub place ID of this schedule exception
	*/
	@Override
	public long getSubPlaceId() {
		return _scheduleException.getSubPlaceId();
	}

	@Override
	public void persist() {
		_scheduleException.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_scheduleException.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_scheduleException.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scheduleException.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this schedule exception is closed.
	*
	* @param closed the closed of this schedule exception
	*/
	@Override
	public void setClosed(boolean closed) {
		_scheduleException.setClosed(closed);
	}

	/**
	* Sets the comment of this schedule exception.
	*
	* @param comment the comment of this schedule exception
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_scheduleException.setComment(comment);
	}

	/**
	* Sets the localized comment of this schedule exception in the language.
	*
	* @param comment the localized comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setComment(java.lang.String comment, java.util.Locale locale) {
		_scheduleException.setComment(comment, locale);
	}

	/**
	* Sets the localized comment of this schedule exception in the language, and sets the default locale.
	*
	* @param comment the localized comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setComment(java.lang.String comment, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_scheduleException.setComment(comment, locale, defaultLocale);
	}

	@Override
	public void setCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized comments of this schedule exception from the map of locales and localized comments.
	*
	* @param commentMap the locales and localized comments of this schedule exception
	*/
	@Override
	public void setCommentMap(
		Map<java.util.Locale, java.lang.String> commentMap) {
		_scheduleException.setCommentMap(commentMap);
	}

	/**
	* Sets the localized comments of this schedule exception from the map of locales and localized comments, and sets the default locale.
	*
	* @param commentMap the locales and localized comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCommentMap(
		Map<java.util.Locale, java.lang.String> commentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setCommentMap(commentMap, defaultLocale);
	}

	/**
	* Sets the end date of this schedule exception.
	*
	* @param endDate the end date of this schedule exception
	*/
	@Override
	public void setEndDate(Date endDate) {
		_scheduleException.setEndDate(endDate);
	}

	/**
	* Sets the exception ID of this schedule exception.
	*
	* @param exceptionId the exception ID of this schedule exception
	*/
	@Override
	public void setExceptionId(long exceptionId) {
		_scheduleException.setExceptionId(exceptionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_scheduleException.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_scheduleException.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_scheduleException.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_scheduleException.setNew(n);
	}

	/**
	* Sets the opening times of this schedule exception.
	*
	* @param openingTimes the opening times of this schedule exception
	*/
	@Override
	public void setOpeningTimes(java.lang.String openingTimes) {
		_scheduleException.setOpeningTimes(openingTimes);
	}

	/**
	* Sets the place ID of this schedule exception.
	*
	* @param placeId the place ID of this schedule exception
	*/
	@Override
	public void setPlaceId(long placeId) {
		_scheduleException.setPlaceId(placeId);
	}

	/**
	* Sets the primary key of this schedule exception.
	*
	* @param primaryKey the primary key of this schedule exception
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_scheduleException.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_scheduleException.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this schedule exception.
	*
	* @param startDate the start date of this schedule exception
	*/
	@Override
	public void setStartDate(Date startDate) {
		_scheduleException.setStartDate(startDate);
	}

	/**
	* Sets the sub place ID of this schedule exception.
	*
	* @param subPlaceId the sub place ID of this schedule exception
	*/
	@Override
	public void setSubPlaceId(long subPlaceId) {
		_scheduleException.setSubPlaceId(subPlaceId);
	}

	/**
	* Sets the uuid of this schedule exception.
	*
	* @param uuid the uuid of this schedule exception
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_scheduleException.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduleExceptionWrapper)) {
			return false;
		}

		ScheduleExceptionWrapper scheduleExceptionWrapper = (ScheduleExceptionWrapper)obj;

		if (Objects.equals(_scheduleException,
					scheduleExceptionWrapper._scheduleException)) {
			return true;
		}

		return false;
	}

	@Override
	public ScheduleException getWrappedModel() {
		return _scheduleException;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _scheduleException.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _scheduleException.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_scheduleException.resetOriginalValues();
	}

	private final ScheduleException _scheduleException;
}
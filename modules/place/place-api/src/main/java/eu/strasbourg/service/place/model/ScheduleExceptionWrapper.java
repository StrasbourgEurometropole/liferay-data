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
		attributes.put("firstComment", getFirstComment());
		attributes.put("secondComment", getSecondComment());
		attributes.put("thirdComment", getThirdComment());
		attributes.put("fourthComment", getFourthComment());
		attributes.put("fifthComment", getFifthComment());
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

		String firstComment = (String)attributes.get("firstComment");

		if (firstComment != null) {
			setFirstComment(firstComment);
		}

		String secondComment = (String)attributes.get("secondComment");

		if (secondComment != null) {
			setSecondComment(secondComment);
		}

		String thirdComment = (String)attributes.get("thirdComment");

		if (thirdComment != null) {
			setThirdComment(thirdComment);
		}

		String fourthComment = (String)attributes.get("fourthComment");

		if (fourthComment != null) {
			setFourthComment(fourthComment);
		}

		String fifthComment = (String)attributes.get("fifthComment");

		if (fifthComment != null) {
			setFifthComment(fifthComment);
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
	* Returns the fifth comment of this schedule exception.
	*
	* @return the fifth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFifthComment() {
		return _scheduleException.getFifthComment();
	}

	/**
	* Returns the localized fifth comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized fifth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFifthComment(java.lang.String languageId) {
		return _scheduleException.getFifthComment(languageId);
	}

	/**
	* Returns the localized fifth comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized fifth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFifthComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getFifthComment(languageId, useDefault);
	}

	/**
	* Returns the localized fifth comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized fifth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFifthComment(java.util.Locale locale) {
		return _scheduleException.getFifthComment(locale);
	}

	/**
	* Returns the localized fifth comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized fifth comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFifthComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getFifthComment(locale, useDefault);
	}

	@Override
	public java.lang.String getFifthCommentCurrentLanguageId() {
		return _scheduleException.getFifthCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getFifthCommentCurrentValue() {
		return _scheduleException.getFifthCommentCurrentValue();
	}

	/**
	* Returns the first comment of this schedule exception.
	*
	* @return the first comment of this schedule exception
	*/
	@Override
	public java.lang.String getFirstComment() {
		return _scheduleException.getFirstComment();
	}

	/**
	* Returns the localized first comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized first comment of this schedule exception
	*/
	@Override
	public java.lang.String getFirstComment(java.lang.String languageId) {
		return _scheduleException.getFirstComment(languageId);
	}

	/**
	* Returns the localized first comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized first comment of this schedule exception
	*/
	@Override
	public java.lang.String getFirstComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getFirstComment(languageId, useDefault);
	}

	/**
	* Returns the localized first comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized first comment of this schedule exception
	*/
	@Override
	public java.lang.String getFirstComment(java.util.Locale locale) {
		return _scheduleException.getFirstComment(locale);
	}

	/**
	* Returns the localized first comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized first comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFirstComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getFirstComment(locale, useDefault);
	}

	@Override
	public java.lang.String getFirstCommentCurrentLanguageId() {
		return _scheduleException.getFirstCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getFirstCommentCurrentValue() {
		return _scheduleException.getFirstCommentCurrentValue();
	}

	/**
	* Returns the fourth comment of this schedule exception.
	*
	* @return the fourth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFourthComment() {
		return _scheduleException.getFourthComment();
	}

	/**
	* Returns the localized fourth comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized fourth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFourthComment(java.lang.String languageId) {
		return _scheduleException.getFourthComment(languageId);
	}

	/**
	* Returns the localized fourth comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized fourth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFourthComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getFourthComment(languageId, useDefault);
	}

	/**
	* Returns the localized fourth comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized fourth comment of this schedule exception
	*/
	@Override
	public java.lang.String getFourthComment(java.util.Locale locale) {
		return _scheduleException.getFourthComment(locale);
	}

	/**
	* Returns the localized fourth comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized fourth comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFourthComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getFourthComment(locale, useDefault);
	}

	@Override
	public java.lang.String getFourthCommentCurrentLanguageId() {
		return _scheduleException.getFourthCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getFourthCommentCurrentValue() {
		return _scheduleException.getFourthCommentCurrentValue();
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
	* Returns the second comment of this schedule exception.
	*
	* @return the second comment of this schedule exception
	*/
	@Override
	public java.lang.String getSecondComment() {
		return _scheduleException.getSecondComment();
	}

	/**
	* Returns the localized second comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized second comment of this schedule exception
	*/
	@Override
	public java.lang.String getSecondComment(java.lang.String languageId) {
		return _scheduleException.getSecondComment(languageId);
	}

	/**
	* Returns the localized second comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized second comment of this schedule exception
	*/
	@Override
	public java.lang.String getSecondComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getSecondComment(languageId, useDefault);
	}

	/**
	* Returns the localized second comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized second comment of this schedule exception
	*/
	@Override
	public java.lang.String getSecondComment(java.util.Locale locale) {
		return _scheduleException.getSecondComment(locale);
	}

	/**
	* Returns the localized second comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized second comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSecondComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getSecondComment(locale, useDefault);
	}

	@Override
	public java.lang.String getSecondCommentCurrentLanguageId() {
		return _scheduleException.getSecondCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getSecondCommentCurrentValue() {
		return _scheduleException.getSecondCommentCurrentValue();
	}

	/**
	* Returns the third comment of this schedule exception.
	*
	* @return the third comment of this schedule exception
	*/
	@Override
	public java.lang.String getThirdComment() {
		return _scheduleException.getThirdComment();
	}

	/**
	* Returns the localized third comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized third comment of this schedule exception
	*/
	@Override
	public java.lang.String getThirdComment(java.lang.String languageId) {
		return _scheduleException.getThirdComment(languageId);
	}

	/**
	* Returns the localized third comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized third comment of this schedule exception
	*/
	@Override
	public java.lang.String getThirdComment(java.lang.String languageId,
		boolean useDefault) {
		return _scheduleException.getThirdComment(languageId, useDefault);
	}

	/**
	* Returns the localized third comment of this schedule exception in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized third comment of this schedule exception
	*/
	@Override
	public java.lang.String getThirdComment(java.util.Locale locale) {
		return _scheduleException.getThirdComment(locale);
	}

	/**
	* Returns the localized third comment of this schedule exception in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized third comment of this schedule exception. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getThirdComment(java.util.Locale locale,
		boolean useDefault) {
		return _scheduleException.getThirdComment(locale, useDefault);
	}

	@Override
	public java.lang.String getThirdCommentCurrentLanguageId() {
		return _scheduleException.getThirdCommentCurrentLanguageId();
	}

	@Override
	public java.lang.String getThirdCommentCurrentValue() {
		return _scheduleException.getThirdCommentCurrentValue();
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
	* Retourne liste des commentaires
	*/
	@Override
	public java.lang.String[] getComments() {
		return _scheduleException.getComments();
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
	* Returns a map of the locales and localized fifth comments of this schedule exception.
	*
	* @return the locales and localized fifth comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFifthCommentMap() {
		return _scheduleException.getFifthCommentMap();
	}

	/**
	* Returns a map of the locales and localized first comments of this schedule exception.
	*
	* @return the locales and localized first comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFirstCommentMap() {
		return _scheduleException.getFirstCommentMap();
	}

	/**
	* Returns a map of the locales and localized fourth comments of this schedule exception.
	*
	* @return the locales and localized fourth comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFourthCommentMap() {
		return _scheduleException.getFourthCommentMap();
	}

	/**
	* Returns a map of the locales and localized second comments of this schedule exception.
	*
	* @return the locales and localized second comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSecondCommentMap() {
		return _scheduleException.getSecondCommentMap();
	}

	/**
	* Returns a map of the locales and localized third comments of this schedule exception.
	*
	* @return the locales and localized third comments of this schedule exception
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getThirdCommentMap() {
		return _scheduleException.getThirdCommentMap();
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

	/**
	* Sets the fifth comment of this schedule exception.
	*
	* @param fifthComment the fifth comment of this schedule exception
	*/
	@Override
	public void setFifthComment(java.lang.String fifthComment) {
		_scheduleException.setFifthComment(fifthComment);
	}

	/**
	* Sets the localized fifth comment of this schedule exception in the language.
	*
	* @param fifthComment the localized fifth comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setFifthComment(java.lang.String fifthComment,
		java.util.Locale locale) {
		_scheduleException.setFifthComment(fifthComment, locale);
	}

	/**
	* Sets the localized fifth comment of this schedule exception in the language, and sets the default locale.
	*
	* @param fifthComment the localized fifth comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFifthComment(java.lang.String fifthComment,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_scheduleException.setFifthComment(fifthComment, locale, defaultLocale);
	}

	@Override
	public void setFifthCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setFifthCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized fifth comments of this schedule exception from the map of locales and localized fifth comments.
	*
	* @param fifthCommentMap the locales and localized fifth comments of this schedule exception
	*/
	@Override
	public void setFifthCommentMap(
		Map<java.util.Locale, java.lang.String> fifthCommentMap) {
		_scheduleException.setFifthCommentMap(fifthCommentMap);
	}

	/**
	* Sets the localized fifth comments of this schedule exception from the map of locales and localized fifth comments, and sets the default locale.
	*
	* @param fifthCommentMap the locales and localized fifth comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFifthCommentMap(
		Map<java.util.Locale, java.lang.String> fifthCommentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setFifthCommentMap(fifthCommentMap, defaultLocale);
	}

	/**
	* Sets the first comment of this schedule exception.
	*
	* @param firstComment the first comment of this schedule exception
	*/
	@Override
	public void setFirstComment(java.lang.String firstComment) {
		_scheduleException.setFirstComment(firstComment);
	}

	/**
	* Sets the localized first comment of this schedule exception in the language.
	*
	* @param firstComment the localized first comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setFirstComment(java.lang.String firstComment,
		java.util.Locale locale) {
		_scheduleException.setFirstComment(firstComment, locale);
	}

	/**
	* Sets the localized first comment of this schedule exception in the language, and sets the default locale.
	*
	* @param firstComment the localized first comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFirstComment(java.lang.String firstComment,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_scheduleException.setFirstComment(firstComment, locale, defaultLocale);
	}

	@Override
	public void setFirstCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setFirstCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized first comments of this schedule exception from the map of locales and localized first comments.
	*
	* @param firstCommentMap the locales and localized first comments of this schedule exception
	*/
	@Override
	public void setFirstCommentMap(
		Map<java.util.Locale, java.lang.String> firstCommentMap) {
		_scheduleException.setFirstCommentMap(firstCommentMap);
	}

	/**
	* Sets the localized first comments of this schedule exception from the map of locales and localized first comments, and sets the default locale.
	*
	* @param firstCommentMap the locales and localized first comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFirstCommentMap(
		Map<java.util.Locale, java.lang.String> firstCommentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setFirstCommentMap(firstCommentMap, defaultLocale);
	}

	/**
	* Sets the fourth comment of this schedule exception.
	*
	* @param fourthComment the fourth comment of this schedule exception
	*/
	@Override
	public void setFourthComment(java.lang.String fourthComment) {
		_scheduleException.setFourthComment(fourthComment);
	}

	/**
	* Sets the localized fourth comment of this schedule exception in the language.
	*
	* @param fourthComment the localized fourth comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setFourthComment(java.lang.String fourthComment,
		java.util.Locale locale) {
		_scheduleException.setFourthComment(fourthComment, locale);
	}

	/**
	* Sets the localized fourth comment of this schedule exception in the language, and sets the default locale.
	*
	* @param fourthComment the localized fourth comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFourthComment(java.lang.String fourthComment,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_scheduleException.setFourthComment(fourthComment, locale, defaultLocale);
	}

	@Override
	public void setFourthCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setFourthCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized fourth comments of this schedule exception from the map of locales and localized fourth comments.
	*
	* @param fourthCommentMap the locales and localized fourth comments of this schedule exception
	*/
	@Override
	public void setFourthCommentMap(
		Map<java.util.Locale, java.lang.String> fourthCommentMap) {
		_scheduleException.setFourthCommentMap(fourthCommentMap);
	}

	/**
	* Sets the localized fourth comments of this schedule exception from the map of locales and localized fourth comments, and sets the default locale.
	*
	* @param fourthCommentMap the locales and localized fourth comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFourthCommentMap(
		Map<java.util.Locale, java.lang.String> fourthCommentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setFourthCommentMap(fourthCommentMap, defaultLocale);
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
	* Sets the second comment of this schedule exception.
	*
	* @param secondComment the second comment of this schedule exception
	*/
	@Override
	public void setSecondComment(java.lang.String secondComment) {
		_scheduleException.setSecondComment(secondComment);
	}

	/**
	* Sets the localized second comment of this schedule exception in the language.
	*
	* @param secondComment the localized second comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setSecondComment(java.lang.String secondComment,
		java.util.Locale locale) {
		_scheduleException.setSecondComment(secondComment, locale);
	}

	/**
	* Sets the localized second comment of this schedule exception in the language, and sets the default locale.
	*
	* @param secondComment the localized second comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSecondComment(java.lang.String secondComment,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_scheduleException.setSecondComment(secondComment, locale, defaultLocale);
	}

	@Override
	public void setSecondCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setSecondCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized second comments of this schedule exception from the map of locales and localized second comments.
	*
	* @param secondCommentMap the locales and localized second comments of this schedule exception
	*/
	@Override
	public void setSecondCommentMap(
		Map<java.util.Locale, java.lang.String> secondCommentMap) {
		_scheduleException.setSecondCommentMap(secondCommentMap);
	}

	/**
	* Sets the localized second comments of this schedule exception from the map of locales and localized second comments, and sets the default locale.
	*
	* @param secondCommentMap the locales and localized second comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSecondCommentMap(
		Map<java.util.Locale, java.lang.String> secondCommentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setSecondCommentMap(secondCommentMap, defaultLocale);
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
	* Sets the third comment of this schedule exception.
	*
	* @param thirdComment the third comment of this schedule exception
	*/
	@Override
	public void setThirdComment(java.lang.String thirdComment) {
		_scheduleException.setThirdComment(thirdComment);
	}

	/**
	* Sets the localized third comment of this schedule exception in the language.
	*
	* @param thirdComment the localized third comment of this schedule exception
	* @param locale the locale of the language
	*/
	@Override
	public void setThirdComment(java.lang.String thirdComment,
		java.util.Locale locale) {
		_scheduleException.setThirdComment(thirdComment, locale);
	}

	/**
	* Sets the localized third comment of this schedule exception in the language, and sets the default locale.
	*
	* @param thirdComment the localized third comment of this schedule exception
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setThirdComment(java.lang.String thirdComment,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_scheduleException.setThirdComment(thirdComment, locale, defaultLocale);
	}

	@Override
	public void setThirdCommentCurrentLanguageId(java.lang.String languageId) {
		_scheduleException.setThirdCommentCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized third comments of this schedule exception from the map of locales and localized third comments.
	*
	* @param thirdCommentMap the locales and localized third comments of this schedule exception
	*/
	@Override
	public void setThirdCommentMap(
		Map<java.util.Locale, java.lang.String> thirdCommentMap) {
		_scheduleException.setThirdCommentMap(thirdCommentMap);
	}

	/**
	* Sets the localized third comments of this schedule exception from the map of locales and localized third comments, and sets the default locale.
	*
	* @param thirdCommentMap the locales and localized third comments of this schedule exception
	* @param defaultLocale the default locale
	*/
	@Override
	public void setThirdCommentMap(
		Map<java.util.Locale, java.lang.String> thirdCommentMap,
		java.util.Locale defaultLocale) {
		_scheduleException.setThirdCommentMap(thirdCommentMap, defaultLocale);
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
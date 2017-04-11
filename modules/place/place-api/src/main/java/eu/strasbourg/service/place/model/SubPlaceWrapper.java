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
 * This class is a wrapper for {@link SubPlace}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlace
 * @generated
 */
@ProviderType
public class SubPlaceWrapper implements SubPlace, ModelWrapper<SubPlace> {
	public SubPlaceWrapper(SubPlace subPlace) {
		_subPlace = subPlace;
	}

	@Override
	public Class<?> getModelClass() {
		return SubPlace.class;
	}

	@Override
	public String getModelClassName() {
		return SubPlace.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("subPlaceId", getSubPlaceId());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("placeId", getPlaceId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long subPlaceId = (Long)attributes.get("subPlaceId");

		if (subPlaceId != null) {
			setSubPlaceId(subPlaceId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long placeId = (Long)attributes.get("placeId");

		if (placeId != null) {
			setPlaceId(placeId);
		}
	}

	/**
	* Returns <code>true</code> if this sub place is approved.
	*
	* @return <code>true</code> if this sub place is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _subPlace.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _subPlace.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this sub place is denied.
	*
	* @return <code>true</code> if this sub place is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _subPlace.isDenied();
	}

	/**
	* Returns <code>true</code> if this sub place is a draft.
	*
	* @return <code>true</code> if this sub place is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _subPlace.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _subPlace.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this sub place is expired.
	*
	* @return <code>true</code> if this sub place is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _subPlace.isExpired();
	}

	/**
	* Returns <code>true</code> if this sub place is inactive.
	*
	* @return <code>true</code> if this sub place is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _subPlace.isInactive();
	}

	/**
	* Returns <code>true</code> if this sub place is incomplete.
	*
	* @return <code>true</code> if this sub place is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _subPlace.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _subPlace.isNew();
	}

	/**
	* Returns <code>true</code> if this sub place is pending.
	*
	* @return <code>true</code> if this sub place is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _subPlace.isPending();
	}

	/**
	* Returns <code>true</code> if this sub place is scheduled.
	*
	* @return <code>true</code> if this sub place is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _subPlace.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _subPlace.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.SubPlace> toCacheModel() {
		return _subPlace.toCacheModel();
	}

	/**
	* Retourne le lieu parent du sous-lieu
	*/
	@Override
	public eu.strasbourg.service.place.model.Place getPlaceByPlaceId(
		long placeId) {
		return _subPlace.getPlaceByPlaceId(placeId);
	}

	@Override
	public eu.strasbourg.service.place.model.SubPlace toEscapedModel() {
		return new SubPlaceWrapper(_subPlace.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.SubPlace toUnescapedModel() {
		return new SubPlaceWrapper(_subPlace.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.SubPlace subPlace) {
		return _subPlace.compareTo(subPlace);
	}

	/**
	* Returns the status of this sub place.
	*
	* @return the status of this sub place
	*/
	@Override
	public int getStatus() {
		return _subPlace.getStatus();
	}

	@Override
	public int hashCode() {
		return _subPlace.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _subPlace.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SubPlaceWrapper((SubPlace)_subPlace.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _subPlace.getDefaultLanguageId();
	}

	/**
	* Returns the description of this sub place.
	*
	* @return the description of this sub place
	*/
	@Override
	public java.lang.String getDescription() {
		return _subPlace.getDescription();
	}

	/**
	* Returns the localized description of this sub place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this sub place
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _subPlace.getDescription(languageId);
	}

	/**
	* Returns the localized description of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this sub place
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _subPlace.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this sub place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this sub place
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _subPlace.getDescription(locale);
	}

	/**
	* Returns the localized description of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this sub place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _subPlace.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _subPlace.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _subPlace.getDescriptionCurrentValue();
	}

	/**
	* Returns the name of this sub place.
	*
	* @return the name of this sub place
	*/
	@Override
	public java.lang.String getName() {
		return _subPlace.getName();
	}

	/**
	* Returns the localized name of this sub place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this sub place
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _subPlace.getName(languageId);
	}

	/**
	* Returns the localized name of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this sub place
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _subPlace.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this sub place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this sub place
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _subPlace.getName(locale);
	}

	/**
	* Returns the localized name of this sub place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this sub place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _subPlace.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _subPlace.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _subPlace.getNameCurrentValue();
	}

	/**
	* Returns the status by user name of this sub place.
	*
	* @return the status by user name of this sub place
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _subPlace.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this sub place.
	*
	* @return the status by user uuid of this sub place
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _subPlace.getStatusByUserUuid();
	}

	/**
	* Returns the uuid of this sub place.
	*
	* @return the uuid of this sub place
	*/
	@Override
	public java.lang.String getUuid() {
		return _subPlace.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _subPlace.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _subPlace.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _subPlace.getAvailableLanguageIds();
	}

	/**
	* Returns the status date of this sub place.
	*
	* @return the status date of this sub place
	*/
	@Override
	public Date getStatusDate() {
		return _subPlace.getStatusDate();
	}

	/**
	* Retourne les Periods du sous-lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods() {
		return _subPlace.getPeriods();
	}

	/**
	* Retourne les ScheduleExceptions du sous-lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions() {
		return _subPlace.getScheduleExceptions();
	}

	/**
	* Returns a map of the locales and localized descriptions of this sub place.
	*
	* @return the locales and localized descriptions of this sub place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _subPlace.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized names of this sub place.
	*
	* @return the locales and localized names of this sub place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _subPlace.getNameMap();
	}

	/**
	* Returns the place ID of this sub place.
	*
	* @return the place ID of this sub place
	*/
	@Override
	public long getPlaceId() {
		return _subPlace.getPlaceId();
	}

	/**
	* Returns the primary key of this sub place.
	*
	* @return the primary key of this sub place
	*/
	@Override
	public long getPrimaryKey() {
		return _subPlace.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this sub place.
	*
	* @return the status by user ID of this sub place
	*/
	@Override
	public long getStatusByUserId() {
		return _subPlace.getStatusByUserId();
	}

	/**
	* Returns the sub place ID of this sub place.
	*
	* @return the sub place ID of this sub place
	*/
	@Override
	public long getSubPlaceId() {
		return _subPlace.getSubPlaceId();
	}

	@Override
	public void persist() {
		_subPlace.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_subPlace.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_subPlace.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_subPlace.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this sub place.
	*
	* @param description the description of this sub place
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_subPlace.setDescription(description);
	}

	/**
	* Sets the localized description of this sub place in the language.
	*
	* @param description the localized description of this sub place
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_subPlace.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this sub place in the language, and sets the default locale.
	*
	* @param description the localized description of this sub place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_subPlace.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_subPlace.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this sub place from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this sub place
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_subPlace.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this sub place from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this sub place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_subPlace.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_subPlace.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_subPlace.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_subPlace.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the name of this sub place.
	*
	* @param name the name of this sub place
	*/
	@Override
	public void setName(java.lang.String name) {
		_subPlace.setName(name);
	}

	/**
	* Sets the localized name of this sub place in the language.
	*
	* @param name the localized name of this sub place
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_subPlace.setName(name, locale);
	}

	/**
	* Sets the localized name of this sub place in the language, and sets the default locale.
	*
	* @param name the localized name of this sub place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_subPlace.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_subPlace.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this sub place from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this sub place
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_subPlace.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this sub place from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this sub place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_subPlace.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_subPlace.setNew(n);
	}

	/**
	* Sets the place ID of this sub place.
	*
	* @param placeId the place ID of this sub place
	*/
	@Override
	public void setPlaceId(long placeId) {
		_subPlace.setPlaceId(placeId);
	}

	/**
	* Sets the primary key of this sub place.
	*
	* @param primaryKey the primary key of this sub place
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_subPlace.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_subPlace.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this sub place.
	*
	* @param status the status of this sub place
	*/
	@Override
	public void setStatus(int status) {
		_subPlace.setStatus(status);
	}

	/**
	* Sets the status by user ID of this sub place.
	*
	* @param statusByUserId the status by user ID of this sub place
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_subPlace.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this sub place.
	*
	* @param statusByUserName the status by user name of this sub place
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_subPlace.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this sub place.
	*
	* @param statusByUserUuid the status by user uuid of this sub place
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_subPlace.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this sub place.
	*
	* @param statusDate the status date of this sub place
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_subPlace.setStatusDate(statusDate);
	}

	/**
	* Sets the sub place ID of this sub place.
	*
	* @param subPlaceId the sub place ID of this sub place
	*/
	@Override
	public void setSubPlaceId(long subPlaceId) {
		_subPlace.setSubPlaceId(subPlaceId);
	}

	/**
	* Sets the uuid of this sub place.
	*
	* @param uuid the uuid of this sub place
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_subPlace.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SubPlaceWrapper)) {
			return false;
		}

		SubPlaceWrapper subPlaceWrapper = (SubPlaceWrapper)obj;

		if (Objects.equals(_subPlace, subPlaceWrapper._subPlace)) {
			return true;
		}

		return false;
	}

	@Override
	public SubPlace getWrappedModel() {
		return _subPlace;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _subPlace.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _subPlace.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_subPlace.resetOriginalValues();
	}

	private final SubPlace _subPlace;
}
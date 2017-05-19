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

package eu.strasbourg.service.activity.model;

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
 * This class is a wrapper for {@link ActivityCoursePlace}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlace
 * @generated
 */
@ProviderType
public class ActivityCoursePlaceWrapper implements ActivityCoursePlace,
	ModelWrapper<ActivityCoursePlace> {
	public ActivityCoursePlaceWrapper(ActivityCoursePlace activityCoursePlace) {
		_activityCoursePlace = activityCoursePlace;
	}

	@Override
	public Class<?> getModelClass() {
		return ActivityCoursePlace.class;
	}

	@Override
	public String getModelClassName() {
		return ActivityCoursePlace.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityCoursePlaceId", getActivityCoursePlaceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("activityCourseId", getActivityCourseId());
		attributes.put("placeSIGId", getPlaceSIGId());
		attributes.put("placeName", getPlaceName());
		attributes.put("placeStreetNumber", getPlaceStreetNumber());
		attributes.put("placeStreetName", getPlaceStreetName());
		attributes.put("placeZipCode", getPlaceZipCode());
		attributes.put("placeCityId", getPlaceCityId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityCoursePlaceId = (Long)attributes.get(
				"activityCoursePlaceId");

		if (activityCoursePlaceId != null) {
			setActivityCoursePlaceId(activityCoursePlaceId);
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

		Long activityCourseId = (Long)attributes.get("activityCourseId");

		if (activityCourseId != null) {
			setActivityCourseId(activityCourseId);
		}

		String placeSIGId = (String)attributes.get("placeSIGId");

		if (placeSIGId != null) {
			setPlaceSIGId(placeSIGId);
		}

		String placeName = (String)attributes.get("placeName");

		if (placeName != null) {
			setPlaceName(placeName);
		}

		String placeStreetNumber = (String)attributes.get("placeStreetNumber");

		if (placeStreetNumber != null) {
			setPlaceStreetNumber(placeStreetNumber);
		}

		String placeStreetName = (String)attributes.get("placeStreetName");

		if (placeStreetName != null) {
			setPlaceStreetName(placeStreetName);
		}

		String placeZipCode = (String)attributes.get("placeZipCode");

		if (placeZipCode != null) {
			setPlaceZipCode(placeZipCode);
		}

		Long placeCityId = (Long)attributes.get("placeCityId");

		if (placeCityId != null) {
			setPlaceCityId(placeCityId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _activityCoursePlace.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _activityCoursePlace.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _activityCoursePlace.isNew();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _activityCoursePlace.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _activityCoursePlace.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.ActivityCoursePlace> toCacheModel() {
		return _activityCoursePlace.toCacheModel();
	}

	/**
	* Retourne le cours du lieu
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse getActivityCourse() {
		return _activityCoursePlace.getActivityCourse();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace getLiveVersion() {
		return _activityCoursePlace.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace toEscapedModel() {
		return new ActivityCoursePlaceWrapper(_activityCoursePlace.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace toUnescapedModel() {
		return new ActivityCoursePlaceWrapper(_activityCoursePlace.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.ActivityCoursePlace activityCoursePlace) {
		return _activityCoursePlace.compareTo(activityCoursePlace);
	}

	@Override
	public int hashCode() {
		return _activityCoursePlace.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityCoursePlace.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ActivityCoursePlaceWrapper((ActivityCoursePlace)_activityCoursePlace.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _activityCoursePlace.getDefaultLanguageId();
	}

	/**
	* Retourne le nom du lieu SIG
	*/
	@Override
	public java.lang.String getPlaceAlias(java.util.Locale locale) {
		return _activityCoursePlace.getPlaceAlias(locale);
	}

	/**
	* Returns the place name of this activity course place.
	*
	* @return the place name of this activity course place
	*/
	@Override
	public java.lang.String getPlaceName() {
		return _activityCoursePlace.getPlaceName();
	}

	/**
	* Returns the localized place name of this activity course place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized place name of this activity course place
	*/
	@Override
	public java.lang.String getPlaceName(java.lang.String languageId) {
		return _activityCoursePlace.getPlaceName(languageId);
	}

	/**
	* Returns the localized place name of this activity course place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized place name of this activity course place
	*/
	@Override
	public java.lang.String getPlaceName(java.lang.String languageId,
		boolean useDefault) {
		return _activityCoursePlace.getPlaceName(languageId, useDefault);
	}

	/**
	* Returns the localized place name of this activity course place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized place name of this activity course place
	*/
	@Override
	public java.lang.String getPlaceName(java.util.Locale locale) {
		return _activityCoursePlace.getPlaceName(locale);
	}

	/**
	* Returns the localized place name of this activity course place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized place name of this activity course place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPlaceName(java.util.Locale locale,
		boolean useDefault) {
		return _activityCoursePlace.getPlaceName(locale, useDefault);
	}

	@Override
	public java.lang.String getPlaceNameCurrentLanguageId() {
		return _activityCoursePlace.getPlaceNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getPlaceNameCurrentValue() {
		return _activityCoursePlace.getPlaceNameCurrentValue();
	}

	/**
	* Returns the place s i g ID of this activity course place.
	*
	* @return the place s i g ID of this activity course place
	*/
	@Override
	public java.lang.String getPlaceSIGId() {
		return _activityCoursePlace.getPlaceSIGId();
	}

	/**
	* Returns the place street name of this activity course place.
	*
	* @return the place street name of this activity course place
	*/
	@Override
	public java.lang.String getPlaceStreetName() {
		return _activityCoursePlace.getPlaceStreetName();
	}

	/**
	* Returns the place street number of this activity course place.
	*
	* @return the place street number of this activity course place
	*/
	@Override
	public java.lang.String getPlaceStreetNumber() {
		return _activityCoursePlace.getPlaceStreetNumber();
	}

	/**
	* Returns the place zip code of this activity course place.
	*
	* @return the place zip code of this activity course place
	*/
	@Override
	public java.lang.String getPlaceZipCode() {
		return _activityCoursePlace.getPlaceZipCode();
	}

	/**
	* Returns the user name of this activity course place.
	*
	* @return the user name of this activity course place
	*/
	@Override
	public java.lang.String getUserName() {
		return _activityCoursePlace.getUserName();
	}

	/**
	* Returns the user uuid of this activity course place.
	*
	* @return the user uuid of this activity course place
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _activityCoursePlace.getUserUuid();
	}

	/**
	* Returns the uuid of this activity course place.
	*
	* @return the uuid of this activity course place
	*/
	@Override
	public java.lang.String getUuid() {
		return _activityCoursePlace.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _activityCoursePlace.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _activityCoursePlace.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _activityCoursePlace.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this activity course place.
	*
	* @return the create date of this activity course place
	*/
	@Override
	public Date getCreateDate() {
		return _activityCoursePlace.getCreateDate();
	}

	/**
	* Returns the modified date of this activity course place.
	*
	* @return the modified date of this activity course place
	*/
	@Override
	public Date getModifiedDate() {
		return _activityCoursePlace.getModifiedDate();
	}

	/**
	* Retourne les horaires du cours dans le lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getActivityCourseSchedules() {
		return _activityCoursePlace.getActivityCourseSchedules();
	}

	/**
	* Retourne la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _activityCoursePlace.getCategories();
	}

	/**
	* Returns a map of the locales and localized place names of this activity course place.
	*
	* @return the locales and localized place names of this activity course place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPlaceNameMap() {
		return _activityCoursePlace.getPlaceNameMap();
	}

	/**
	* Returns the activity course ID of this activity course place.
	*
	* @return the activity course ID of this activity course place
	*/
	@Override
	public long getActivityCourseId() {
		return _activityCoursePlace.getActivityCourseId();
	}

	/**
	* Returns the activity course place ID of this activity course place.
	*
	* @return the activity course place ID of this activity course place
	*/
	@Override
	public long getActivityCoursePlaceId() {
		return _activityCoursePlace.getActivityCoursePlaceId();
	}

	/**
	* Returns the company ID of this activity course place.
	*
	* @return the company ID of this activity course place
	*/
	@Override
	public long getCompanyId() {
		return _activityCoursePlace.getCompanyId();
	}

	/**
	* Returns the group ID of this activity course place.
	*
	* @return the group ID of this activity course place
	*/
	@Override
	public long getGroupId() {
		return _activityCoursePlace.getGroupId();
	}

	/**
	* Returns the place city ID of this activity course place.
	*
	* @return the place city ID of this activity course place
	*/
	@Override
	public long getPlaceCityId() {
		return _activityCoursePlace.getPlaceCityId();
	}

	/**
	* Returns the primary key of this activity course place.
	*
	* @return the primary key of this activity course place
	*/
	@Override
	public long getPrimaryKey() {
		return _activityCoursePlace.getPrimaryKey();
	}

	/**
	* Returns the user ID of this activity course place.
	*
	* @return the user ID of this activity course place
	*/
	@Override
	public long getUserId() {
		return _activityCoursePlace.getUserId();
	}

	@Override
	public void persist() {
		_activityCoursePlace.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCoursePlace.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCoursePlace.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the activity course ID of this activity course place.
	*
	* @param activityCourseId the activity course ID of this activity course place
	*/
	@Override
	public void setActivityCourseId(long activityCourseId) {
		_activityCoursePlace.setActivityCourseId(activityCourseId);
	}

	/**
	* Sets the activity course place ID of this activity course place.
	*
	* @param activityCoursePlaceId the activity course place ID of this activity course place
	*/
	@Override
	public void setActivityCoursePlaceId(long activityCoursePlaceId) {
		_activityCoursePlace.setActivityCoursePlaceId(activityCoursePlaceId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_activityCoursePlace.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this activity course place.
	*
	* @param companyId the company ID of this activity course place
	*/
	@Override
	public void setCompanyId(long companyId) {
		_activityCoursePlace.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this activity course place.
	*
	* @param createDate the create date of this activity course place
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_activityCoursePlace.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_activityCoursePlace.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_activityCoursePlace.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_activityCoursePlace.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this activity course place.
	*
	* @param groupId the group ID of this activity course place
	*/
	@Override
	public void setGroupId(long groupId) {
		_activityCoursePlace.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this activity course place.
	*
	* @param modifiedDate the modified date of this activity course place
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_activityCoursePlace.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_activityCoursePlace.setNew(n);
	}

	/**
	* Sets the place city ID of this activity course place.
	*
	* @param placeCityId the place city ID of this activity course place
	*/
	@Override
	public void setPlaceCityId(long placeCityId) {
		_activityCoursePlace.setPlaceCityId(placeCityId);
	}

	/**
	* Sets the place name of this activity course place.
	*
	* @param placeName the place name of this activity course place
	*/
	@Override
	public void setPlaceName(java.lang.String placeName) {
		_activityCoursePlace.setPlaceName(placeName);
	}

	/**
	* Sets the localized place name of this activity course place in the language.
	*
	* @param placeName the localized place name of this activity course place
	* @param locale the locale of the language
	*/
	@Override
	public void setPlaceName(java.lang.String placeName, java.util.Locale locale) {
		_activityCoursePlace.setPlaceName(placeName, locale);
	}

	/**
	* Sets the localized place name of this activity course place in the language, and sets the default locale.
	*
	* @param placeName the localized place name of this activity course place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPlaceName(java.lang.String placeName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_activityCoursePlace.setPlaceName(placeName, locale, defaultLocale);
	}

	@Override
	public void setPlaceNameCurrentLanguageId(java.lang.String languageId) {
		_activityCoursePlace.setPlaceNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized place names of this activity course place from the map of locales and localized place names.
	*
	* @param placeNameMap the locales and localized place names of this activity course place
	*/
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, java.lang.String> placeNameMap) {
		_activityCoursePlace.setPlaceNameMap(placeNameMap);
	}

	/**
	* Sets the localized place names of this activity course place from the map of locales and localized place names, and sets the default locale.
	*
	* @param placeNameMap the locales and localized place names of this activity course place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, java.lang.String> placeNameMap,
		java.util.Locale defaultLocale) {
		_activityCoursePlace.setPlaceNameMap(placeNameMap, defaultLocale);
	}

	/**
	* Sets the place s i g ID of this activity course place.
	*
	* @param placeSIGId the place s i g ID of this activity course place
	*/
	@Override
	public void setPlaceSIGId(java.lang.String placeSIGId) {
		_activityCoursePlace.setPlaceSIGId(placeSIGId);
	}

	/**
	* Sets the place street name of this activity course place.
	*
	* @param placeStreetName the place street name of this activity course place
	*/
	@Override
	public void setPlaceStreetName(java.lang.String placeStreetName) {
		_activityCoursePlace.setPlaceStreetName(placeStreetName);
	}

	/**
	* Sets the place street number of this activity course place.
	*
	* @param placeStreetNumber the place street number of this activity course place
	*/
	@Override
	public void setPlaceStreetNumber(java.lang.String placeStreetNumber) {
		_activityCoursePlace.setPlaceStreetNumber(placeStreetNumber);
	}

	/**
	* Sets the place zip code of this activity course place.
	*
	* @param placeZipCode the place zip code of this activity course place
	*/
	@Override
	public void setPlaceZipCode(java.lang.String placeZipCode) {
		_activityCoursePlace.setPlaceZipCode(placeZipCode);
	}

	/**
	* Sets the primary key of this activity course place.
	*
	* @param primaryKey the primary key of this activity course place
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_activityCoursePlace.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_activityCoursePlace.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this activity course place.
	*
	* @param userId the user ID of this activity course place
	*/
	@Override
	public void setUserId(long userId) {
		_activityCoursePlace.setUserId(userId);
	}

	/**
	* Sets the user name of this activity course place.
	*
	* @param userName the user name of this activity course place
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_activityCoursePlace.setUserName(userName);
	}

	/**
	* Sets the user uuid of this activity course place.
	*
	* @param userUuid the user uuid of this activity course place
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_activityCoursePlace.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this activity course place.
	*
	* @param uuid the uuid of this activity course place
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_activityCoursePlace.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityCoursePlaceWrapper)) {
			return false;
		}

		ActivityCoursePlaceWrapper activityCoursePlaceWrapper = (ActivityCoursePlaceWrapper)obj;

		if (Objects.equals(_activityCoursePlace,
					activityCoursePlaceWrapper._activityCoursePlace)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _activityCoursePlace.getStagedModelType();
	}

	@Override
	public ActivityCoursePlace getWrappedModel() {
		return _activityCoursePlace;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _activityCoursePlace.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _activityCoursePlace.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_activityCoursePlace.resetOriginalValues();
	}

	private final ActivityCoursePlace _activityCoursePlace;
}
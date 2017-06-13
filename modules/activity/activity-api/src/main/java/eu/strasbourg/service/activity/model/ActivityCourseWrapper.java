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
 * This class is a wrapper for {@link ActivityCourse}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourse
 * @generated
 */
@ProviderType
public class ActivityCourseWrapper implements ActivityCourse,
	ModelWrapper<ActivityCourse> {
	public ActivityCourseWrapper(ActivityCourse activityCourse) {
		_activityCourse = activityCourse;
	}

	@Override
	public Class<?> getModelClass() {
		return ActivityCourse.class;
	}

	@Override
	public String getModelClassName() {
		return ActivityCourse.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityCourseId", getActivityCourseId());
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
		attributes.put("name", getName());
		attributes.put("arrangements", getArrangements());
		attributes.put("price", getPrice());
		attributes.put("activityId", getActivityId());
		attributes.put("serviceId", getServiceId());
		attributes.put("organizerId", getOrganizerId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityCourseId = (Long)attributes.get("activityCourseId");

		if (activityCourseId != null) {
			setActivityCourseId(activityCourseId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String arrangements = (String)attributes.get("arrangements");

		if (arrangements != null) {
			setArrangements(arrangements);
		}

		String price = (String)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
		}

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		Long organizerId = (Long)attributes.get("organizerId");

		if (organizerId != null) {
			setOrganizerId(organizerId);
		}
	}

	/**
	* Returns <code>true</code> if this activity course is approved.
	*
	* @return <code>true</code> if this activity course is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _activityCourse.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _activityCourse.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this activity course is denied.
	*
	* @return <code>true</code> if this activity course is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _activityCourse.isDenied();
	}

	/**
	* Returns <code>true</code> if this activity course is a draft.
	*
	* @return <code>true</code> if this activity course is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _activityCourse.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _activityCourse.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this activity course is expired.
	*
	* @return <code>true</code> if this activity course is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _activityCourse.isExpired();
	}

	/**
	* Returns <code>true</code> if this activity course is inactive.
	*
	* @return <code>true</code> if this activity course is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _activityCourse.isInactive();
	}

	/**
	* Returns <code>true</code> if this activity course is incomplete.
	*
	* @return <code>true</code> if this activity course is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _activityCourse.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _activityCourse.isNew();
	}

	/**
	* Returns <code>true</code> if this activity course is pending.
	*
	* @return <code>true</code> if this activity course is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _activityCourse.isPending();
	}

	/**
	* Returns <code>true</code> if this activity course is scheduled.
	*
	* @return <code>true</code> if this activity course is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _activityCourse.isScheduled();
	}

	/**
	* Retourne le service du cours
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getService() {
		return _activityCourse.getService();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _activityCourse.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _activityCourse.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.ActivityCourse> toCacheModel() {
		return _activityCourse.toCacheModel();
	}

	/**
	* Retourne l'activité du cours
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity getActivity() {
		return _activityCourse.getActivity();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse getLiveVersion() {
		return _activityCourse.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse toEscapedModel() {
		return new ActivityCourseWrapper(_activityCourse.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCourse toUnescapedModel() {
		return new ActivityCourseWrapper(_activityCourse.toUnescapedModel());
	}

	/**
	* Retourne l'organisateur du cours
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityOrganizer getActivityOrganizer() {
		return _activityCourse.getActivityOrganizer();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.ActivityCourse activityCourse) {
		return _activityCourse.compareTo(activityCourse);
	}

	/**
	* Returns the status of this activity course.
	*
	* @return the status of this activity course
	*/
	@Override
	public int getStatus() {
		return _activityCourse.getStatus();
	}

	@Override
	public int hashCode() {
		return _activityCourse.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityCourse.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ActivityCourseWrapper((ActivityCourse)_activityCourse.clone());
	}

	/**
	* Returns the arrangements of this activity course.
	*
	* @return the arrangements of this activity course
	*/
	@Override
	public java.lang.String getArrangements() {
		return _activityCourse.getArrangements();
	}

	/**
	* Returns the localized arrangements of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized arrangements of this activity course
	*/
	@Override
	public java.lang.String getArrangements(java.lang.String languageId) {
		return _activityCourse.getArrangements(languageId);
	}

	/**
	* Returns the localized arrangements of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized arrangements of this activity course
	*/
	@Override
	public java.lang.String getArrangements(java.lang.String languageId,
		boolean useDefault) {
		return _activityCourse.getArrangements(languageId, useDefault);
	}

	/**
	* Returns the localized arrangements of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized arrangements of this activity course
	*/
	@Override
	public java.lang.String getArrangements(java.util.Locale locale) {
		return _activityCourse.getArrangements(locale);
	}

	/**
	* Returns the localized arrangements of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized arrangements of this activity course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getArrangements(java.util.Locale locale,
		boolean useDefault) {
		return _activityCourse.getArrangements(locale, useDefault);
	}

	@Override
	public java.lang.String getArrangementsCurrentLanguageId() {
		return _activityCourse.getArrangementsCurrentLanguageId();
	}

	@Override
	public java.lang.String getArrangementsCurrentValue() {
		return _activityCourse.getArrangementsCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _activityCourse.getDefaultLanguageId();
	}

	/**
	* Returns the name of this activity course.
	*
	* @return the name of this activity course
	*/
	@Override
	public java.lang.String getName() {
		return _activityCourse.getName();
	}

	/**
	* Returns the localized name of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this activity course
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _activityCourse.getName(languageId);
	}

	/**
	* Returns the localized name of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this activity course
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _activityCourse.getName(languageId, useDefault);
	}

	/**
	* Returns the localized name of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this activity course
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _activityCourse.getName(locale);
	}

	/**
	* Returns the localized name of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this activity course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _activityCourse.getName(locale, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _activityCourse.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _activityCourse.getNameCurrentValue();
	}

	/**
	* Returns the price of this activity course.
	*
	* @return the price of this activity course
	*/
	@Override
	public java.lang.String getPrice() {
		return _activityCourse.getPrice();
	}

	/**
	* Returns the localized price of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized price of this activity course
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId) {
		return _activityCourse.getPrice(languageId);
	}

	/**
	* Returns the localized price of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this activity course
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId,
		boolean useDefault) {
		return _activityCourse.getPrice(languageId, useDefault);
	}

	/**
	* Returns the localized price of this activity course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized price of this activity course
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale) {
		return _activityCourse.getPrice(locale);
	}

	/**
	* Returns the localized price of this activity course in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this activity course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale, boolean useDefault) {
		return _activityCourse.getPrice(locale, useDefault);
	}

	@Override
	public java.lang.String getPriceCurrentLanguageId() {
		return _activityCourse.getPriceCurrentLanguageId();
	}

	@Override
	public java.lang.String getPriceCurrentValue() {
		return _activityCourse.getPriceCurrentValue();
	}

	/**
	* Retourne le texte à afficher pour les publics du cours
	*/
	@Override
	public java.lang.String getPublicsLabel(java.util.Locale locale) {
		return _activityCourse.getPublicsLabel(locale);
	}

	/**
	* Returns the status by user name of this activity course.
	*
	* @return the status by user name of this activity course
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _activityCourse.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this activity course.
	*
	* @return the status by user uuid of this activity course
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _activityCourse.getStatusByUserUuid();
	}

	/**
	* Retourne le texte à afficher pour les types du cours
	*/
	@Override
	public java.lang.String getTypesLabels(java.util.Locale locale) {
		return _activityCourse.getTypesLabels(locale);
	}

	/**
	* Returns the user name of this activity course.
	*
	* @return the user name of this activity course
	*/
	@Override
	public java.lang.String getUserName() {
		return _activityCourse.getUserName();
	}

	/**
	* Returns the user uuid of this activity course.
	*
	* @return the user uuid of this activity course
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _activityCourse.getUserUuid();
	}

	/**
	* Returns the uuid of this activity course.
	*
	* @return the uuid of this activity course
	*/
	@Override
	public java.lang.String getUuid() {
		return _activityCourse.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _activityCourse.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _activityCourse.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _activityCourse.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this activity course.
	*
	* @return the create date of this activity course
	*/
	@Override
	public Date getCreateDate() {
		return _activityCourse.getCreateDate();
	}

	/**
	* Returns the modified date of this activity course.
	*
	* @return the modified date of this activity course
	*/
	@Override
	public Date getModifiedDate() {
		return _activityCourse.getModifiedDate();
	}

	/**
	* Returns the status date of this activity course.
	*
	* @return the status date of this activity course
	*/
	@Override
	public Date getStatusDate() {
		return _activityCourse.getStatusDate();
	}

	/**
	* Retourne les lieux du cours
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getActivityCoursePlaces() {
		return _activityCourse.getActivityCoursePlaces();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _activityCourse.getCategories();
	}

	/**
	* Retourne les publics dui cours
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics() {
		return _activityCourse.getPublics();
	}

	/**
	* Retourne les types du cours
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes() {
		return _activityCourse.getTypes();
	}

	/**
	* Returns a map of the locales and localized arrangementses of this activity course.
	*
	* @return the locales and localized arrangementses of this activity course
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getArrangementsMap() {
		return _activityCourse.getArrangementsMap();
	}

	/**
	* Returns a map of the locales and localized names of this activity course.
	*
	* @return the locales and localized names of this activity course
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _activityCourse.getNameMap();
	}

	/**
	* Returns a map of the locales and localized prices of this activity course.
	*
	* @return the locales and localized prices of this activity course
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPriceMap() {
		return _activityCourse.getPriceMap();
	}

	/**
	* Returns the activity course ID of this activity course.
	*
	* @return the activity course ID of this activity course
	*/
	@Override
	public long getActivityCourseId() {
		return _activityCourse.getActivityCourseId();
	}

	/**
	* Returns the activity ID of this activity course.
	*
	* @return the activity ID of this activity course
	*/
	@Override
	public long getActivityId() {
		return _activityCourse.getActivityId();
	}

	/**
	* Returns the company ID of this activity course.
	*
	* @return the company ID of this activity course
	*/
	@Override
	public long getCompanyId() {
		return _activityCourse.getCompanyId();
	}

	/**
	* Returns the group ID of this activity course.
	*
	* @return the group ID of this activity course
	*/
	@Override
	public long getGroupId() {
		return _activityCourse.getGroupId();
	}

	/**
	* Returns the organizer ID of this activity course.
	*
	* @return the organizer ID of this activity course
	*/
	@Override
	public long getOrganizerId() {
		return _activityCourse.getOrganizerId();
	}

	/**
	* Returns the primary key of this activity course.
	*
	* @return the primary key of this activity course
	*/
	@Override
	public long getPrimaryKey() {
		return _activityCourse.getPrimaryKey();
	}

	/**
	* Returns the service ID of this activity course.
	*
	* @return the service ID of this activity course
	*/
	@Override
	public long getServiceId() {
		return _activityCourse.getServiceId();
	}

	/**
	* Returns the status by user ID of this activity course.
	*
	* @return the status by user ID of this activity course
	*/
	@Override
	public long getStatusByUserId() {
		return _activityCourse.getStatusByUserId();
	}

	/**
	* Returns the user ID of this activity course.
	*
	* @return the user ID of this activity course
	*/
	@Override
	public long getUserId() {
		return _activityCourse.getUserId();
	}

	@Override
	public void persist() {
		_activityCourse.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCourse.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCourse.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the activity course ID of this activity course.
	*
	* @param activityCourseId the activity course ID of this activity course
	*/
	@Override
	public void setActivityCourseId(long activityCourseId) {
		_activityCourse.setActivityCourseId(activityCourseId);
	}

	/**
	* Sets the activity ID of this activity course.
	*
	* @param activityId the activity ID of this activity course
	*/
	@Override
	public void setActivityId(long activityId) {
		_activityCourse.setActivityId(activityId);
	}

	/**
	* Sets the arrangements of this activity course.
	*
	* @param arrangements the arrangements of this activity course
	*/
	@Override
	public void setArrangements(java.lang.String arrangements) {
		_activityCourse.setArrangements(arrangements);
	}

	/**
	* Sets the localized arrangements of this activity course in the language.
	*
	* @param arrangements the localized arrangements of this activity course
	* @param locale the locale of the language
	*/
	@Override
	public void setArrangements(java.lang.String arrangements,
		java.util.Locale locale) {
		_activityCourse.setArrangements(arrangements, locale);
	}

	/**
	* Sets the localized arrangements of this activity course in the language, and sets the default locale.
	*
	* @param arrangements the localized arrangements of this activity course
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setArrangements(java.lang.String arrangements,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_activityCourse.setArrangements(arrangements, locale, defaultLocale);
	}

	@Override
	public void setArrangementsCurrentLanguageId(java.lang.String languageId) {
		_activityCourse.setArrangementsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized arrangementses of this activity course from the map of locales and localized arrangementses.
	*
	* @param arrangementsMap the locales and localized arrangementses of this activity course
	*/
	@Override
	public void setArrangementsMap(
		Map<java.util.Locale, java.lang.String> arrangementsMap) {
		_activityCourse.setArrangementsMap(arrangementsMap);
	}

	/**
	* Sets the localized arrangementses of this activity course from the map of locales and localized arrangementses, and sets the default locale.
	*
	* @param arrangementsMap the locales and localized arrangementses of this activity course
	* @param defaultLocale the default locale
	*/
	@Override
	public void setArrangementsMap(
		Map<java.util.Locale, java.lang.String> arrangementsMap,
		java.util.Locale defaultLocale) {
		_activityCourse.setArrangementsMap(arrangementsMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_activityCourse.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this activity course.
	*
	* @param companyId the company ID of this activity course
	*/
	@Override
	public void setCompanyId(long companyId) {
		_activityCourse.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this activity course.
	*
	* @param createDate the create date of this activity course
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_activityCourse.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_activityCourse.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_activityCourse.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_activityCourse.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this activity course.
	*
	* @param groupId the group ID of this activity course
	*/
	@Override
	public void setGroupId(long groupId) {
		_activityCourse.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this activity course.
	*
	* @param modifiedDate the modified date of this activity course
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_activityCourse.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this activity course.
	*
	* @param name the name of this activity course
	*/
	@Override
	public void setName(java.lang.String name) {
		_activityCourse.setName(name);
	}

	/**
	* Sets the localized name of this activity course in the language.
	*
	* @param name the localized name of this activity course
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_activityCourse.setName(name, locale);
	}

	/**
	* Sets the localized name of this activity course in the language, and sets the default locale.
	*
	* @param name the localized name of this activity course
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityCourse.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_activityCourse.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this activity course from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this activity course
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_activityCourse.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this activity course from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this activity course
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_activityCourse.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_activityCourse.setNew(n);
	}

	/**
	* Sets the organizer ID of this activity course.
	*
	* @param organizerId the organizer ID of this activity course
	*/
	@Override
	public void setOrganizerId(long organizerId) {
		_activityCourse.setOrganizerId(organizerId);
	}

	/**
	* Sets the price of this activity course.
	*
	* @param price the price of this activity course
	*/
	@Override
	public void setPrice(java.lang.String price) {
		_activityCourse.setPrice(price);
	}

	/**
	* Sets the localized price of this activity course in the language.
	*
	* @param price the localized price of this activity course
	* @param locale the locale of the language
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale) {
		_activityCourse.setPrice(price, locale);
	}

	/**
	* Sets the localized price of this activity course in the language, and sets the default locale.
	*
	* @param price the localized price of this activity course
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityCourse.setPrice(price, locale, defaultLocale);
	}

	@Override
	public void setPriceCurrentLanguageId(java.lang.String languageId) {
		_activityCourse.setPriceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized prices of this activity course from the map of locales and localized prices.
	*
	* @param priceMap the locales and localized prices of this activity course
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap) {
		_activityCourse.setPriceMap(priceMap);
	}

	/**
	* Sets the localized prices of this activity course from the map of locales and localized prices, and sets the default locale.
	*
	* @param priceMap the locales and localized prices of this activity course
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap,
		java.util.Locale defaultLocale) {
		_activityCourse.setPriceMap(priceMap, defaultLocale);
	}

	/**
	* Sets the primary key of this activity course.
	*
	* @param primaryKey the primary key of this activity course
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_activityCourse.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_activityCourse.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service ID of this activity course.
	*
	* @param serviceId the service ID of this activity course
	*/
	@Override
	public void setServiceId(long serviceId) {
		_activityCourse.setServiceId(serviceId);
	}

	/**
	* Sets the status of this activity course.
	*
	* @param status the status of this activity course
	*/
	@Override
	public void setStatus(int status) {
		_activityCourse.setStatus(status);
	}

	/**
	* Sets the status by user ID of this activity course.
	*
	* @param statusByUserId the status by user ID of this activity course
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_activityCourse.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this activity course.
	*
	* @param statusByUserName the status by user name of this activity course
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_activityCourse.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this activity course.
	*
	* @param statusByUserUuid the status by user uuid of this activity course
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_activityCourse.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this activity course.
	*
	* @param statusDate the status date of this activity course
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_activityCourse.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this activity course.
	*
	* @param userId the user ID of this activity course
	*/
	@Override
	public void setUserId(long userId) {
		_activityCourse.setUserId(userId);
	}

	/**
	* Sets the user name of this activity course.
	*
	* @param userName the user name of this activity course
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_activityCourse.setUserName(userName);
	}

	/**
	* Sets the user uuid of this activity course.
	*
	* @param userUuid the user uuid of this activity course
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_activityCourse.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this activity course.
	*
	* @param uuid the uuid of this activity course
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_activityCourse.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityCourseWrapper)) {
			return false;
		}

		ActivityCourseWrapper activityCourseWrapper = (ActivityCourseWrapper)obj;

		if (Objects.equals(_activityCourse,
					activityCourseWrapper._activityCourse)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _activityCourse.getStagedModelType();
	}

	@Override
	public ActivityCourse getWrappedModel() {
		return _activityCourse;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _activityCourse.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _activityCourse.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_activityCourse.resetOriginalValues();
	}

	private final ActivityCourse _activityCourse;
}
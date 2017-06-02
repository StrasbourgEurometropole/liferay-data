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
 * This class is a wrapper for {@link ActivityCourseSchedule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseSchedule
 * @generated
 */
@ProviderType
public class ActivityCourseScheduleWrapper implements ActivityCourseSchedule,
	ModelWrapper<ActivityCourseSchedule> {
	public ActivityCourseScheduleWrapper(
		ActivityCourseSchedule activityCourseSchedule) {
		_activityCourseSchedule = activityCourseSchedule;
	}

	@Override
	public Class<?> getModelClass() {
		return ActivityCourseSchedule.class;
	}

	@Override
	public String getModelClassName() {
		return ActivityCourseSchedule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityCourseScheduleId", getActivityCourseScheduleId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("activityCoursePlaceId", getActivityCoursePlaceId());
		attributes.put("startTime", getStartTime());
		attributes.put("endTime", getEndTime());
		attributes.put("monday", getMonday());
		attributes.put("tuesday", getTuesday());
		attributes.put("wednesday", getWednesday());
		attributes.put("thursday", getThursday());
		attributes.put("friday", getFriday());
		attributes.put("saturday", getSaturday());
		attributes.put("sunday", getSunday());
		attributes.put("comments", getComments());
		attributes.put("periodsIds", getPeriodsIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityCourseScheduleId = (Long)attributes.get(
				"activityCourseScheduleId");

		if (activityCourseScheduleId != null) {
			setActivityCourseScheduleId(activityCourseScheduleId);
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

		Long activityCoursePlaceId = (Long)attributes.get(
				"activityCoursePlaceId");

		if (activityCoursePlaceId != null) {
			setActivityCoursePlaceId(activityCoursePlaceId);
		}

		String startTime = (String)attributes.get("startTime");

		if (startTime != null) {
			setStartTime(startTime);
		}

		String endTime = (String)attributes.get("endTime");

		if (endTime != null) {
			setEndTime(endTime);
		}

		Boolean monday = (Boolean)attributes.get("monday");

		if (monday != null) {
			setMonday(monday);
		}

		Boolean tuesday = (Boolean)attributes.get("tuesday");

		if (tuesday != null) {
			setTuesday(tuesday);
		}

		Boolean wednesday = (Boolean)attributes.get("wednesday");

		if (wednesday != null) {
			setWednesday(wednesday);
		}

		Boolean thursday = (Boolean)attributes.get("thursday");

		if (thursday != null) {
			setThursday(thursday);
		}

		Boolean friday = (Boolean)attributes.get("friday");

		if (friday != null) {
			setFriday(friday);
		}

		Boolean saturday = (Boolean)attributes.get("saturday");

		if (saturday != null) {
			setSaturday(saturday);
		}

		Boolean sunday = (Boolean)attributes.get("sunday");

		if (sunday != null) {
			setSunday(sunday);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String periodsIds = (String)attributes.get("periodsIds");

		if (periodsIds != null) {
			setPeriodsIds(periodsIds);
		}
	}

	/**
	* Returns the friday of this activity course schedule.
	*
	* @return the friday of this activity course schedule
	*/
	@Override
	public boolean getFriday() {
		return _activityCourseSchedule.getFriday();
	}

	/**
	* Returns the monday of this activity course schedule.
	*
	* @return the monday of this activity course schedule
	*/
	@Override
	public boolean getMonday() {
		return _activityCourseSchedule.getMonday();
	}

	/**
	* Returns the saturday of this activity course schedule.
	*
	* @return the saturday of this activity course schedule
	*/
	@Override
	public boolean getSaturday() {
		return _activityCourseSchedule.getSaturday();
	}

	/**
	* Returns the sunday of this activity course schedule.
	*
	* @return the sunday of this activity course schedule
	*/
	@Override
	public boolean getSunday() {
		return _activityCourseSchedule.getSunday();
	}

	/**
	* Returns the thursday of this activity course schedule.
	*
	* @return the thursday of this activity course schedule
	*/
	@Override
	public boolean getThursday() {
		return _activityCourseSchedule.getThursday();
	}

	/**
	* Returns the tuesday of this activity course schedule.
	*
	* @return the tuesday of this activity course schedule
	*/
	@Override
	public boolean getTuesday() {
		return _activityCourseSchedule.getTuesday();
	}

	/**
	* Returns the wednesday of this activity course schedule.
	*
	* @return the wednesday of this activity course schedule
	*/
	@Override
	public boolean getWednesday() {
		return _activityCourseSchedule.getWednesday();
	}

	/**
	* Renvoie true si l'horaire concerne le jour passé en paramètre (jour entre
	* 0 et 6)
	*/
	@Override
	public boolean hasScheduleOnDay(int day) {
		return _activityCourseSchedule.hasScheduleOnDay(day);
	}

	@Override
	public boolean isCachedModel() {
		return _activityCourseSchedule.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _activityCourseSchedule.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is friday.
	*
	* @return <code>true</code> if this activity course schedule is friday; <code>false</code> otherwise
	*/
	@Override
	public boolean isFriday() {
		return _activityCourseSchedule.isFriday();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is monday.
	*
	* @return <code>true</code> if this activity course schedule is monday; <code>false</code> otherwise
	*/
	@Override
	public boolean isMonday() {
		return _activityCourseSchedule.isMonday();
	}

	@Override
	public boolean isNew() {
		return _activityCourseSchedule.isNew();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is saturday.
	*
	* @return <code>true</code> if this activity course schedule is saturday; <code>false</code> otherwise
	*/
	@Override
	public boolean isSaturday() {
		return _activityCourseSchedule.isSaturday();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is sunday.
	*
	* @return <code>true</code> if this activity course schedule is sunday; <code>false</code> otherwise
	*/
	@Override
	public boolean isSunday() {
		return _activityCourseSchedule.isSunday();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is thursday.
	*
	* @return <code>true</code> if this activity course schedule is thursday; <code>false</code> otherwise
	*/
	@Override
	public boolean isThursday() {
		return _activityCourseSchedule.isThursday();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is tuesday.
	*
	* @return <code>true</code> if this activity course schedule is tuesday; <code>false</code> otherwise
	*/
	@Override
	public boolean isTuesday() {
		return _activityCourseSchedule.isTuesday();
	}

	/**
	* Returns <code>true</code> if this activity course schedule is wednesday.
	*
	* @return <code>true</code> if this activity course schedule is wednesday; <code>false</code> otherwise
	*/
	@Override
	public boolean isWednesday() {
		return _activityCourseSchedule.isWednesday();
	}

	/**
	* Renvoie un tableau de 7 booléens valant true si l'horaire concerne le
	* jour, false sinon
	*/
	@Override
	public boolean[] getWeekDays() {
		return _activityCourseSchedule.getWeekDays();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _activityCourseSchedule.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _activityCourseSchedule.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.ActivityCourseSchedule> toCacheModel() {
		return _activityCourseSchedule.toCacheModel();
	}

	/**
	* Retourne le lieu de l'horaire
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCoursePlace getActivityCoursePlace() {
		return _activityCourseSchedule.getActivityCoursePlace();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.ActivityCourseSchedule getLiveVersion() {
		return _activityCourseSchedule.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCourseSchedule toEscapedModel() {
		return new ActivityCourseScheduleWrapper(_activityCourseSchedule.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.ActivityCourseSchedule toUnescapedModel() {
		return new ActivityCourseScheduleWrapper(_activityCourseSchedule.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.ActivityCourseSchedule activityCourseSchedule) {
		return _activityCourseSchedule.compareTo(activityCourseSchedule);
	}

	@Override
	public int hashCode() {
		return _activityCourseSchedule.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityCourseSchedule.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ActivityCourseScheduleWrapper((ActivityCourseSchedule)_activityCourseSchedule.clone());
	}

	/**
	* Returns the comments of this activity course schedule.
	*
	* @return the comments of this activity course schedule
	*/
	@Override
	public java.lang.String getComments() {
		return _activityCourseSchedule.getComments();
	}

	/**
	* Returns the localized comments of this activity course schedule in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized comments of this activity course schedule
	*/
	@Override
	public java.lang.String getComments(java.lang.String languageId) {
		return _activityCourseSchedule.getComments(languageId);
	}

	/**
	* Returns the localized comments of this activity course schedule in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comments of this activity course schedule
	*/
	@Override
	public java.lang.String getComments(java.lang.String languageId,
		boolean useDefault) {
		return _activityCourseSchedule.getComments(languageId, useDefault);
	}

	/**
	* Returns the localized comments of this activity course schedule in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized comments of this activity course schedule
	*/
	@Override
	public java.lang.String getComments(java.util.Locale locale) {
		return _activityCourseSchedule.getComments(locale);
	}

	/**
	* Returns the localized comments of this activity course schedule in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized comments of this activity course schedule. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getComments(java.util.Locale locale,
		boolean useDefault) {
		return _activityCourseSchedule.getComments(locale, useDefault);
	}

	@Override
	public java.lang.String getCommentsCurrentLanguageId() {
		return _activityCourseSchedule.getCommentsCurrentLanguageId();
	}

	@Override
	public java.lang.String getCommentsCurrentValue() {
		return _activityCourseSchedule.getCommentsCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _activityCourseSchedule.getDefaultLanguageId();
	}

	/**
	* Returns the end time of this activity course schedule.
	*
	* @return the end time of this activity course schedule
	*/
	@Override
	public java.lang.String getEndTime() {
		return _activityCourseSchedule.getEndTime();
	}

	/**
	* Returns the periods IDs of this activity course schedule.
	*
	* @return the periods IDs of this activity course schedule
	*/
	@Override
	public java.lang.String getPeriodsIds() {
		return _activityCourseSchedule.getPeriodsIds();
	}

	/**
	* Returns the start time of this activity course schedule.
	*
	* @return the start time of this activity course schedule
	*/
	@Override
	public java.lang.String getStartTime() {
		return _activityCourseSchedule.getStartTime();
	}

	/**
	* Returns the user name of this activity course schedule.
	*
	* @return the user name of this activity course schedule
	*/
	@Override
	public java.lang.String getUserName() {
		return _activityCourseSchedule.getUserName();
	}

	/**
	* Returns the user uuid of this activity course schedule.
	*
	* @return the user uuid of this activity course schedule
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _activityCourseSchedule.getUserUuid();
	}

	/**
	* Returns the uuid of this activity course schedule.
	*
	* @return the uuid of this activity course schedule
	*/
	@Override
	public java.lang.String getUuid() {
		return _activityCourseSchedule.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _activityCourseSchedule.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _activityCourseSchedule.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _activityCourseSchedule.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this activity course schedule.
	*
	* @return the create date of this activity course schedule
	*/
	@Override
	public Date getCreateDate() {
		return _activityCourseSchedule.getCreateDate();
	}

	/**
	* Returns the modified date of this activity course schedule.
	*
	* @return the modified date of this activity course schedule
	*/
	@Override
	public Date getModifiedDate() {
		return _activityCourseSchedule.getModifiedDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _activityCourseSchedule.getCategories();
	}

	/**
	* Returns a map of the locales and localized commentses of this activity course schedule.
	*
	* @return the locales and localized commentses of this activity course schedule
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getCommentsMap() {
		return _activityCourseSchedule.getCommentsMap();
	}

	/**
	* Returns the activity course place ID of this activity course schedule.
	*
	* @return the activity course place ID of this activity course schedule
	*/
	@Override
	public long getActivityCoursePlaceId() {
		return _activityCourseSchedule.getActivityCoursePlaceId();
	}

	/**
	* Returns the activity course schedule ID of this activity course schedule.
	*
	* @return the activity course schedule ID of this activity course schedule
	*/
	@Override
	public long getActivityCourseScheduleId() {
		return _activityCourseSchedule.getActivityCourseScheduleId();
	}

	/**
	* Returns the company ID of this activity course schedule.
	*
	* @return the company ID of this activity course schedule
	*/
	@Override
	public long getCompanyId() {
		return _activityCourseSchedule.getCompanyId();
	}

	/**
	* Returns the group ID of this activity course schedule.
	*
	* @return the group ID of this activity course schedule
	*/
	@Override
	public long getGroupId() {
		return _activityCourseSchedule.getGroupId();
	}

	/**
	* Returns the primary key of this activity course schedule.
	*
	* @return the primary key of this activity course schedule
	*/
	@Override
	public long getPrimaryKey() {
		return _activityCourseSchedule.getPrimaryKey();
	}

	/**
	* Returns the user ID of this activity course schedule.
	*
	* @return the user ID of this activity course schedule
	*/
	@Override
	public long getUserId() {
		return _activityCourseSchedule.getUserId();
	}

	@Override
	public void persist() {
		_activityCourseSchedule.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCourseSchedule.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activityCourseSchedule.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the activity course place ID of this activity course schedule.
	*
	* @param activityCoursePlaceId the activity course place ID of this activity course schedule
	*/
	@Override
	public void setActivityCoursePlaceId(long activityCoursePlaceId) {
		_activityCourseSchedule.setActivityCoursePlaceId(activityCoursePlaceId);
	}

	/**
	* Sets the activity course schedule ID of this activity course schedule.
	*
	* @param activityCourseScheduleId the activity course schedule ID of this activity course schedule
	*/
	@Override
	public void setActivityCourseScheduleId(long activityCourseScheduleId) {
		_activityCourseSchedule.setActivityCourseScheduleId(activityCourseScheduleId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_activityCourseSchedule.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this activity course schedule.
	*
	* @param comments the comments of this activity course schedule
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_activityCourseSchedule.setComments(comments);
	}

	/**
	* Sets the localized comments of this activity course schedule in the language.
	*
	* @param comments the localized comments of this activity course schedule
	* @param locale the locale of the language
	*/
	@Override
	public void setComments(java.lang.String comments, java.util.Locale locale) {
		_activityCourseSchedule.setComments(comments, locale);
	}

	/**
	* Sets the localized comments of this activity course schedule in the language, and sets the default locale.
	*
	* @param comments the localized comments of this activity course schedule
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setComments(java.lang.String comments, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activityCourseSchedule.setComments(comments, locale, defaultLocale);
	}

	@Override
	public void setCommentsCurrentLanguageId(java.lang.String languageId) {
		_activityCourseSchedule.setCommentsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized commentses of this activity course schedule from the map of locales and localized commentses.
	*
	* @param commentsMap the locales and localized commentses of this activity course schedule
	*/
	@Override
	public void setCommentsMap(
		Map<java.util.Locale, java.lang.String> commentsMap) {
		_activityCourseSchedule.setCommentsMap(commentsMap);
	}

	/**
	* Sets the localized commentses of this activity course schedule from the map of locales and localized commentses, and sets the default locale.
	*
	* @param commentsMap the locales and localized commentses of this activity course schedule
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCommentsMap(
		Map<java.util.Locale, java.lang.String> commentsMap,
		java.util.Locale defaultLocale) {
		_activityCourseSchedule.setCommentsMap(commentsMap, defaultLocale);
	}

	/**
	* Sets the company ID of this activity course schedule.
	*
	* @param companyId the company ID of this activity course schedule
	*/
	@Override
	public void setCompanyId(long companyId) {
		_activityCourseSchedule.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this activity course schedule.
	*
	* @param createDate the create date of this activity course schedule
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_activityCourseSchedule.setCreateDate(createDate);
	}

	/**
	* Sets the end time of this activity course schedule.
	*
	* @param endTime the end time of this activity course schedule
	*/
	@Override
	public void setEndTime(java.lang.String endTime) {
		_activityCourseSchedule.setEndTime(endTime);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_activityCourseSchedule.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_activityCourseSchedule.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_activityCourseSchedule.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this activity course schedule is friday.
	*
	* @param friday the friday of this activity course schedule
	*/
	@Override
	public void setFriday(boolean friday) {
		_activityCourseSchedule.setFriday(friday);
	}

	/**
	* Sets the group ID of this activity course schedule.
	*
	* @param groupId the group ID of this activity course schedule
	*/
	@Override
	public void setGroupId(long groupId) {
		_activityCourseSchedule.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this activity course schedule.
	*
	* @param modifiedDate the modified date of this activity course schedule
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_activityCourseSchedule.setModifiedDate(modifiedDate);
	}

	/**
	* Sets whether this activity course schedule is monday.
	*
	* @param monday the monday of this activity course schedule
	*/
	@Override
	public void setMonday(boolean monday) {
		_activityCourseSchedule.setMonday(monday);
	}

	@Override
	public void setNew(boolean n) {
		_activityCourseSchedule.setNew(n);
	}

	/**
	* Sets the periods IDs of this activity course schedule.
	*
	* @param periodsIds the periods IDs of this activity course schedule
	*/
	@Override
	public void setPeriodsIds(java.lang.String periodsIds) {
		_activityCourseSchedule.setPeriodsIds(periodsIds);
	}

	/**
	* Sets the primary key of this activity course schedule.
	*
	* @param primaryKey the primary key of this activity course schedule
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_activityCourseSchedule.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_activityCourseSchedule.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this activity course schedule is saturday.
	*
	* @param saturday the saturday of this activity course schedule
	*/
	@Override
	public void setSaturday(boolean saturday) {
		_activityCourseSchedule.setSaturday(saturday);
	}

	/**
	* Sets the start time of this activity course schedule.
	*
	* @param startTime the start time of this activity course schedule
	*/
	@Override
	public void setStartTime(java.lang.String startTime) {
		_activityCourseSchedule.setStartTime(startTime);
	}

	/**
	* Sets whether this activity course schedule is sunday.
	*
	* @param sunday the sunday of this activity course schedule
	*/
	@Override
	public void setSunday(boolean sunday) {
		_activityCourseSchedule.setSunday(sunday);
	}

	/**
	* Sets whether this activity course schedule is thursday.
	*
	* @param thursday the thursday of this activity course schedule
	*/
	@Override
	public void setThursday(boolean thursday) {
		_activityCourseSchedule.setThursday(thursday);
	}

	/**
	* Sets whether this activity course schedule is tuesday.
	*
	* @param tuesday the tuesday of this activity course schedule
	*/
	@Override
	public void setTuesday(boolean tuesday) {
		_activityCourseSchedule.setTuesday(tuesday);
	}

	/**
	* Sets the user ID of this activity course schedule.
	*
	* @param userId the user ID of this activity course schedule
	*/
	@Override
	public void setUserId(long userId) {
		_activityCourseSchedule.setUserId(userId);
	}

	/**
	* Sets the user name of this activity course schedule.
	*
	* @param userName the user name of this activity course schedule
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_activityCourseSchedule.setUserName(userName);
	}

	/**
	* Sets the user uuid of this activity course schedule.
	*
	* @param userUuid the user uuid of this activity course schedule
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_activityCourseSchedule.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this activity course schedule.
	*
	* @param uuid the uuid of this activity course schedule
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_activityCourseSchedule.setUuid(uuid);
	}

	/**
	* Sets whether this activity course schedule is wednesday.
	*
	* @param wednesday the wednesday of this activity course schedule
	*/
	@Override
	public void setWednesday(boolean wednesday) {
		_activityCourseSchedule.setWednesday(wednesday);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityCourseScheduleWrapper)) {
			return false;
		}

		ActivityCourseScheduleWrapper activityCourseScheduleWrapper = (ActivityCourseScheduleWrapper)obj;

		if (Objects.equals(_activityCourseSchedule,
					activityCourseScheduleWrapper._activityCourseSchedule)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _activityCourseSchedule.getStagedModelType();
	}

	@Override
	public ActivityCourseSchedule getWrappedModel() {
		return _activityCourseSchedule;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _activityCourseSchedule.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _activityCourseSchedule.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_activityCourseSchedule.resetOriginalValues();
	}

	private final ActivityCourseSchedule _activityCourseSchedule;
}
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
 * This class is a wrapper for {@link Activity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Activity
 * @generated
 */
@ProviderType
public class ActivityWrapper implements Activity, ModelWrapper<Activity> {
	public ActivityWrapper(Activity activity) {
		_activity = activity;
	}

	@Override
	public Class<?> getModelClass() {
		return Activity.class;
	}

	@Override
	public String getModelClassName() {
		return Activity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityId", getActivityId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("videosIds", getVideosIds());
		attributes.put("imageId", getImageId());
		attributes.put("imagesIds", getImagesIds());
		attributes.put("filesIds", getFilesIds());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String videosIds = (String)attributes.get("videosIds");

		if (videosIds != null) {
			setVideosIds(videosIds);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String imagesIds = (String)attributes.get("imagesIds");

		if (imagesIds != null) {
			setImagesIds(imagesIds);
		}

		String filesIds = (String)attributes.get("filesIds");

		if (filesIds != null) {
			setFilesIds(filesIds);
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
	}

	/**
	* Returns <code>true</code> if this activity is approved.
	*
	* @return <code>true</code> if this activity is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _activity.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _activity.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this activity is denied.
	*
	* @return <code>true</code> if this activity is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _activity.isDenied();
	}

	/**
	* Returns <code>true</code> if this activity is a draft.
	*
	* @return <code>true</code> if this activity is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _activity.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _activity.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this activity is expired.
	*
	* @return <code>true</code> if this activity is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _activity.isExpired();
	}

	/**
	* Returns <code>true</code> if this activity is inactive.
	*
	* @return <code>true</code> if this activity is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _activity.isInactive();
	}

	/**
	* Returns <code>true</code> if this activity is incomplete.
	*
	* @return <code>true</code> if this activity is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _activity.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _activity.isNew();
	}

	/**
	* Returns <code>true</code> if this activity is pending.
	*
	* @return <code>true</code> if this activity is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _activity.isPending();
	}

	/**
	* Returns <code>true</code> if this activity is scheduled.
	*
	* @return <code>true</code> if this activity is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _activity.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _activity.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _activity.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de l'activité
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _activity.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.Activity> toCacheModel() {
		return _activity.toCacheModel();
	}

	/**
	* Retourne la version live de cette entité
	*
	* @return
	*/
	@Override
	public eu.strasbourg.service.activity.model.Activity getLiveVersion() {
		return _activity.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.Activity toEscapedModel() {
		return new ActivityWrapper(_activity.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.Activity toUnescapedModel() {
		return new ActivityWrapper(_activity.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.activity.model.Activity activity) {
		return _activity.compareTo(activity);
	}

	/**
	* Returns the status of this activity.
	*
	* @return the status of this activity
	*/
	@Override
	public int getStatus() {
		return _activity.getStatus();
	}

	@Override
	public int hashCode() {
		return _activity.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activity.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ActivityWrapper((Activity)_activity.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _activity.getDefaultLanguageId();
	}

	/**
	* Returns the description of this activity.
	*
	* @return the description of this activity
	*/
	@Override
	public java.lang.String getDescription() {
		return _activity.getDescription();
	}

	/**
	* Returns the localized description of this activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this activity
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _activity.getDescription(languageId);
	}

	/**
	* Returns the localized description of this activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this activity
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _activity.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this activity
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _activity.getDescription(locale);
	}

	/**
	* Returns the localized description of this activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _activity.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _activity.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _activity.getDescriptionCurrentValue();
	}

	/**
	* Returns the files IDs of this activity.
	*
	* @return the files IDs of this activity
	*/
	@Override
	public java.lang.String getFilesIds() {
		return _activity.getFilesIds();
	}

	/**
	* Retourne l'URL de l'image
	*/
	@Override
	public java.lang.String getImageURL() {
		return _activity.getImageURL();
	}

	/**
	* Returns the images IDs of this activity.
	*
	* @return the images IDs of this activity
	*/
	@Override
	public java.lang.String getImagesIds() {
		return _activity.getImagesIds();
	}

	/**
	* Returns the status by user name of this activity.
	*
	* @return the status by user name of this activity
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _activity.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this activity.
	*
	* @return the status by user uuid of this activity
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _activity.getStatusByUserUuid();
	}

	/**
	* Returns the title of this activity.
	*
	* @return the title of this activity
	*/
	@Override
	public java.lang.String getTitle() {
		return _activity.getTitle();
	}

	/**
	* Returns the localized title of this activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this activity
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _activity.getTitle(languageId);
	}

	/**
	* Returns the localized title of this activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this activity
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _activity.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this activity
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _activity.getTitle(locale);
	}

	/**
	* Returns the localized title of this activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _activity.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _activity.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _activity.getTitleCurrentValue();
	}

	/**
	* Retourne le texte à afficher pour les types de l'activité
	*/
	@Override
	public java.lang.String getTypesLabel(java.util.Locale locale) {
		return _activity.getTypesLabel(locale);
	}

	/**
	* Returns the user name of this activity.
	*
	* @return the user name of this activity
	*/
	@Override
	public java.lang.String getUserName() {
		return _activity.getUserName();
	}

	/**
	* Returns the user uuid of this activity.
	*
	* @return the user uuid of this activity
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _activity.getUserUuid();
	}

	/**
	* Returns the uuid of this activity.
	*
	* @return the uuid of this activity
	*/
	@Override
	public java.lang.String getUuid() {
		return _activity.getUuid();
	}

	/**
	* Returns the videos IDs of this activity.
	*
	* @return the videos IDs of this activity
	*/
	@Override
	public java.lang.String getVideosIds() {
		return _activity.getVideosIds();
	}

	@Override
	public java.lang.String toString() {
		return _activity.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _activity.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _activity.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this activity.
	*
	* @return the create date of this activity
	*/
	@Override
	public Date getCreateDate() {
		return _activity.getCreateDate();
	}

	/**
	* Returns the modified date of this activity.
	*
	* @return the modified date of this activity
	*/
	@Override
	public Date getModifiedDate() {
		return _activity.getModifiedDate();
	}

	/**
	* Returns the status date of this activity.
	*
	* @return the status date of this activity
	*/
	@Override
	public Date getStatusDate() {
		return _activity.getStatusDate();
	}

	/**
	* Retourne les cours de l'activité
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCourses() {
		return _activity.getActivityCourses();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _activity.getCategories();
	}

	/**
	* Retourne la liste des URLs des documents
	*/
	@Override
	public java.util.List<java.lang.String> getFilesURLs() {
		return _activity.getFilesURLs();
	}

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	@Override
	public java.util.List<java.lang.String> getImagesURLs() {
		return _activity.getImagesURLs();
	}

	/**
	* Retourne les cours publiés du lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getPublishedActivityCourses() {
		return _activity.getPublishedActivityCourses();
	}

	/**
	* Retourne les types de l'activité
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes() {
		return _activity.getTypes();
	}

	/**
	* Retourne la liste des vidéos
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos() {
		return _activity.getVideos();
	}

	/**
	* Returns a map of the locales and localized descriptions of this activity.
	*
	* @return the locales and localized descriptions of this activity
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _activity.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this activity.
	*
	* @return the locales and localized titles of this activity
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _activity.getTitleMap();
	}

	/**
	* Returns the activity ID of this activity.
	*
	* @return the activity ID of this activity
	*/
	@Override
	public long getActivityId() {
		return _activity.getActivityId();
	}

	/**
	* Returns the company ID of this activity.
	*
	* @return the company ID of this activity
	*/
	@Override
	public long getCompanyId() {
		return _activity.getCompanyId();
	}

	/**
	* Returns the group ID of this activity.
	*
	* @return the group ID of this activity
	*/
	@Override
	public long getGroupId() {
		return _activity.getGroupId();
	}

	/**
	* Returns the image ID of this activity.
	*
	* @return the image ID of this activity
	*/
	@Override
	public long getImageId() {
		return _activity.getImageId();
	}

	/**
	* Returns the primary key of this activity.
	*
	* @return the primary key of this activity
	*/
	@Override
	public long getPrimaryKey() {
		return _activity.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this activity.
	*
	* @return the status by user ID of this activity
	*/
	@Override
	public long getStatusByUserId() {
		return _activity.getStatusByUserId();
	}

	/**
	* Returns the user ID of this activity.
	*
	* @return the user ID of this activity
	*/
	@Override
	public long getUserId() {
		return _activity.getUserId();
	}

	@Override
	public void persist() {
		_activity.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activity.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_activity.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the activity ID of this activity.
	*
	* @param activityId the activity ID of this activity
	*/
	@Override
	public void setActivityId(long activityId) {
		_activity.setActivityId(activityId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_activity.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this activity.
	*
	* @param companyId the company ID of this activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_activity.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this activity.
	*
	* @param createDate the create date of this activity
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_activity.setCreateDate(createDate);
	}

	/**
	* Sets the description of this activity.
	*
	* @param description the description of this activity
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_activity.setDescription(description);
	}

	/**
	* Sets the localized description of this activity in the language.
	*
	* @param description the localized description of this activity
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_activity.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this activity in the language, and sets the default locale.
	*
	* @param description the localized description of this activity
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_activity.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_activity.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this activity from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this activity
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_activity.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this activity from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this activity
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_activity.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_activity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_activity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_activity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the files IDs of this activity.
	*
	* @param filesIds the files IDs of this activity
	*/
	@Override
	public void setFilesIds(java.lang.String filesIds) {
		_activity.setFilesIds(filesIds);
	}

	/**
	* Sets the group ID of this activity.
	*
	* @param groupId the group ID of this activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_activity.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this activity.
	*
	* @param imageId the image ID of this activity
	*/
	@Override
	public void setImageId(long imageId) {
		_activity.setImageId(imageId);
	}

	/**
	* Sets the images IDs of this activity.
	*
	* @param imagesIds the images IDs of this activity
	*/
	@Override
	public void setImagesIds(java.lang.String imagesIds) {
		_activity.setImagesIds(imagesIds);
	}

	/**
	* Sets the modified date of this activity.
	*
	* @param modifiedDate the modified date of this activity
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_activity.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_activity.setNew(n);
	}

	/**
	* Sets the primary key of this activity.
	*
	* @param primaryKey the primary key of this activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_activity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_activity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this activity.
	*
	* @param status the status of this activity
	*/
	@Override
	public void setStatus(int status) {
		_activity.setStatus(status);
	}

	/**
	* Sets the status by user ID of this activity.
	*
	* @param statusByUserId the status by user ID of this activity
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_activity.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this activity.
	*
	* @param statusByUserName the status by user name of this activity
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_activity.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this activity.
	*
	* @param statusByUserUuid the status by user uuid of this activity
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_activity.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this activity.
	*
	* @param statusDate the status date of this activity
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_activity.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this activity.
	*
	* @param title the title of this activity
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_activity.setTitle(title);
	}

	/**
	* Sets the localized title of this activity in the language.
	*
	* @param title the localized title of this activity
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_activity.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this activity in the language, and sets the default locale.
	*
	* @param title the localized title of this activity
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_activity.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_activity.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this activity from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this activity
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_activity.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this activity from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this activity
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_activity.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this activity.
	*
	* @param userId the user ID of this activity
	*/
	@Override
	public void setUserId(long userId) {
		_activity.setUserId(userId);
	}

	/**
	* Sets the user name of this activity.
	*
	* @param userName the user name of this activity
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_activity.setUserName(userName);
	}

	/**
	* Sets the user uuid of this activity.
	*
	* @param userUuid the user uuid of this activity
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_activity.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this activity.
	*
	* @param uuid the uuid of this activity
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_activity.setUuid(uuid);
	}

	/**
	* Sets the videos IDs of this activity.
	*
	* @param videosIds the videos IDs of this activity
	*/
	@Override
	public void setVideosIds(java.lang.String videosIds) {
		_activity.setVideosIds(videosIds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityWrapper)) {
			return false;
		}

		ActivityWrapper activityWrapper = (ActivityWrapper)obj;

		if (Objects.equals(_activity, activityWrapper._activity)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _activity.getStagedModelType();
	}

	@Override
	public Activity getWrappedModel() {
		return _activity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _activity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _activity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_activity.resetOriginalValues();
	}

	private final Activity _activity;
}
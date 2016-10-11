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

package eu.strasbourg.service.video.model;

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
 * This class is a wrapper for {@link VideoGallery}.
 * </p>
 *
 * @author BenjaminBini
 * @see VideoGallery
 * @generated
 */
@ProviderType
public class VideoGalleryWrapper implements VideoGallery,
	ModelWrapper<VideoGallery> {
	public VideoGalleryWrapper(VideoGallery videoGallery) {
		_videoGallery = videoGallery;
	}

	@Override
	public Class<?> getModelClass() {
		return VideoGallery.class;
	}

	@Override
	public String getModelClassName() {
		return VideoGallery.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("galleryId", getGalleryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long galleryId = (Long)attributes.get("galleryId");

		if (galleryId != null) {
			setGalleryId(galleryId);
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

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Returns <code>true</code> if this video gallery is approved.
	*
	* @return <code>true</code> if this video gallery is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _videoGallery.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _videoGallery.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this video gallery is denied.
	*
	* @return <code>true</code> if this video gallery is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _videoGallery.isDenied();
	}

	/**
	* Returns <code>true</code> if this video gallery is a draft.
	*
	* @return <code>true</code> if this video gallery is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _videoGallery.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _videoGallery.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this video gallery is expired.
	*
	* @return <code>true</code> if this video gallery is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _videoGallery.isExpired();
	}

	/**
	* Returns <code>true</code> if this video gallery is inactive.
	*
	* @return <code>true</code> if this video gallery is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _videoGallery.isInactive();
	}

	/**
	* Returns <code>true</code> if this video gallery is incomplete.
	*
	* @return <code>true</code> if this video gallery is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _videoGallery.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _videoGallery.isNew();
	}

	/**
	* Returns <code>true</code> if this video gallery is pending.
	*
	* @return <code>true</code> if this video gallery is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _videoGallery.isPending();
	}

	/**
	* Returns <code>true</code> if this video gallery is scheduled.
	*
	* @return <code>true</code> if this video gallery is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _videoGallery.isScheduled();
	}

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoGallery.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _videoGallery.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.video.model.VideoGallery> toCacheModel() {
		return _videoGallery.toCacheModel();
	}

	/**
	* Renvoie la version live de la galerie, si elle existe
	*/
	@Override
	public eu.strasbourg.service.video.model.VideoGallery getLiveVersion() {
		return _videoGallery.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.video.model.VideoGallery toEscapedModel() {
		return new VideoGalleryWrapper(_videoGallery.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.video.model.VideoGallery toUnescapedModel() {
		return new VideoGalleryWrapper(_videoGallery.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.video.model.VideoGallery videoGallery) {
		return _videoGallery.compareTo(videoGallery);
	}

	/**
	* Returns the status of this video gallery.
	*
	* @return the status of this video gallery
	*/
	@Override
	public int getStatus() {
		return _videoGallery.getStatus();
	}

	@Override
	public int hashCode() {
		return _videoGallery.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _videoGallery.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this video gallery.
	*
	* @return the image ID of this video gallery
	*/
	@Override
	public java.lang.Long getImageId() {
		return _videoGallery.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new VideoGalleryWrapper((VideoGallery)_videoGallery.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _videoGallery.getDefaultLanguageId();
	}

	/**
	* Returns the description of this video gallery.
	*
	* @return the description of this video gallery
	*/
	@Override
	public java.lang.String getDescription() {
		return _videoGallery.getDescription();
	}

	/**
	* Returns the localized description of this video gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this video gallery
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _videoGallery.getDescription(languageId);
	}

	/**
	* Returns the localized description of this video gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this video gallery
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _videoGallery.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this video gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this video gallery
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _videoGallery.getDescription(locale);
	}

	/**
	* Returns the localized description of this video gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this video gallery. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _videoGallery.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _videoGallery.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _videoGallery.getDescriptionCurrentValue();
	}

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*
	* @throws PortalException
	* @throws NumberFormatException
	*/
	@Override
	public java.lang.String getImageURL() {
		return _videoGallery.getImageURL();
	}

	/**
	* Returns the status by user name of this video gallery.
	*
	* @return the status by user name of this video gallery
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _videoGallery.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this video gallery.
	*
	* @return the status by user uuid of this video gallery
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _videoGallery.getStatusByUserUuid();
	}

	/**
	* Returns the title of this video gallery.
	*
	* @return the title of this video gallery
	*/
	@Override
	public java.lang.String getTitle() {
		return _videoGallery.getTitle();
	}

	/**
	* Returns the localized title of this video gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this video gallery
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _videoGallery.getTitle(languageId);
	}

	/**
	* Returns the localized title of this video gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this video gallery
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _videoGallery.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this video gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this video gallery
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _videoGallery.getTitle(locale);
	}

	/**
	* Returns the localized title of this video gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this video gallery. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _videoGallery.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _videoGallery.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _videoGallery.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this video gallery.
	*
	* @return the user name of this video gallery
	*/
	@Override
	public java.lang.String getUserName() {
		return _videoGallery.getUserName();
	}

	/**
	* Returns the user uuid of this video gallery.
	*
	* @return the user uuid of this video gallery
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _videoGallery.getUserUuid();
	}

	/**
	* Returns the uuid of this video gallery.
	*
	* @return the uuid of this video gallery
	*/
	@Override
	public java.lang.String getUuid() {
		return _videoGallery.getUuid();
	}

	/**
	* Retourne la liste des ids des vidéos de la galerie
	*/
	@Override
	public java.lang.String getVideosIds() {
		return _videoGallery.getVideosIds();
	}

	@Override
	public java.lang.String toString() {
		return _videoGallery.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _videoGallery.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _videoGallery.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this video gallery.
	*
	* @return the create date of this video gallery
	*/
	@Override
	public Date getCreateDate() {
		return _videoGallery.getCreateDate();
	}

	/**
	* Returns the last publish date of this video gallery.
	*
	* @return the last publish date of this video gallery
	*/
	@Override
	public Date getLastPublishDate() {
		return _videoGallery.getLastPublishDate();
	}

	/**
	* Returns the modified date of this video gallery.
	*
	* @return the modified date of this video gallery
	*/
	@Override
	public Date getModifiedDate() {
		return _videoGallery.getModifiedDate();
	}

	/**
	* Returns the status date of this video gallery.
	*
	* @return the status date of this video gallery
	*/
	@Override
	public Date getStatusDate() {
		return _videoGallery.getStatusDate();
	}

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via l'AssetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _videoGallery.getCategories();
	}

	/**
	* Retourne la liste des vidéos de la galerie
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos() {
		return _videoGallery.getVideos();
	}

	/**
	* Returns a map of the locales and localized descriptions of this video gallery.
	*
	* @return the locales and localized descriptions of this video gallery
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _videoGallery.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this video gallery.
	*
	* @return the locales and localized titles of this video gallery
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _videoGallery.getTitleMap();
	}

	/**
	* Returns the company ID of this video gallery.
	*
	* @return the company ID of this video gallery
	*/
	@Override
	public long getCompanyId() {
		return _videoGallery.getCompanyId();
	}

	/**
	* Returns the gallery ID of this video gallery.
	*
	* @return the gallery ID of this video gallery
	*/
	@Override
	public long getGalleryId() {
		return _videoGallery.getGalleryId();
	}

	/**
	* Returns the group ID of this video gallery.
	*
	* @return the group ID of this video gallery
	*/
	@Override
	public long getGroupId() {
		return _videoGallery.getGroupId();
	}

	/**
	* Returns the primary key of this video gallery.
	*
	* @return the primary key of this video gallery
	*/
	@Override
	public long getPrimaryKey() {
		return _videoGallery.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this video gallery.
	*
	* @return the status by user ID of this video gallery
	*/
	@Override
	public long getStatusByUserId() {
		return _videoGallery.getStatusByUserId();
	}

	/**
	* Returns the user ID of this video gallery.
	*
	* @return the user ID of this video gallery
	*/
	@Override
	public long getUserId() {
		return _videoGallery.getUserId();
	}

	@Override
	public void persist() {
		_videoGallery.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_videoGallery.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_videoGallery.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_videoGallery.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this video gallery.
	*
	* @param companyId the company ID of this video gallery
	*/
	@Override
	public void setCompanyId(long companyId) {
		_videoGallery.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this video gallery.
	*
	* @param createDate the create date of this video gallery
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_videoGallery.setCreateDate(createDate);
	}

	/**
	* Sets the description of this video gallery.
	*
	* @param description the description of this video gallery
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_videoGallery.setDescription(description);
	}

	/**
	* Sets the localized description of this video gallery in the language.
	*
	* @param description the localized description of this video gallery
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_videoGallery.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this video gallery in the language, and sets the default locale.
	*
	* @param description the localized description of this video gallery
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_videoGallery.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_videoGallery.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this video gallery from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this video gallery
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_videoGallery.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this video gallery from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this video gallery
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_videoGallery.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_videoGallery.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_videoGallery.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_videoGallery.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gallery ID of this video gallery.
	*
	* @param galleryId the gallery ID of this video gallery
	*/
	@Override
	public void setGalleryId(long galleryId) {
		_videoGallery.setGalleryId(galleryId);
	}

	/**
	* Sets the group ID of this video gallery.
	*
	* @param groupId the group ID of this video gallery
	*/
	@Override
	public void setGroupId(long groupId) {
		_videoGallery.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this video gallery.
	*
	* @param imageId the image ID of this video gallery
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_videoGallery.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this video gallery.
	*
	* @param lastPublishDate the last publish date of this video gallery
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_videoGallery.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this video gallery.
	*
	* @param modifiedDate the modified date of this video gallery
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_videoGallery.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_videoGallery.setNew(n);
	}

	/**
	* Sets the primary key of this video gallery.
	*
	* @param primaryKey the primary key of this video gallery
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_videoGallery.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_videoGallery.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this video gallery.
	*
	* @param status the status of this video gallery
	*/
	@Override
	public void setStatus(int status) {
		_videoGallery.setStatus(status);
	}

	/**
	* Sets the status by user ID of this video gallery.
	*
	* @param statusByUserId the status by user ID of this video gallery
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_videoGallery.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this video gallery.
	*
	* @param statusByUserName the status by user name of this video gallery
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_videoGallery.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this video gallery.
	*
	* @param statusByUserUuid the status by user uuid of this video gallery
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_videoGallery.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this video gallery.
	*
	* @param statusDate the status date of this video gallery
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_videoGallery.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this video gallery.
	*
	* @param title the title of this video gallery
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_videoGallery.setTitle(title);
	}

	/**
	* Sets the localized title of this video gallery in the language.
	*
	* @param title the localized title of this video gallery
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_videoGallery.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this video gallery in the language, and sets the default locale.
	*
	* @param title the localized title of this video gallery
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_videoGallery.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_videoGallery.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this video gallery from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this video gallery
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_videoGallery.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this video gallery from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this video gallery
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_videoGallery.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this video gallery.
	*
	* @param userId the user ID of this video gallery
	*/
	@Override
	public void setUserId(long userId) {
		_videoGallery.setUserId(userId);
	}

	/**
	* Sets the user name of this video gallery.
	*
	* @param userName the user name of this video gallery
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_videoGallery.setUserName(userName);
	}

	/**
	* Sets the user uuid of this video gallery.
	*
	* @param userUuid the user uuid of this video gallery
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_videoGallery.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this video gallery.
	*
	* @param uuid the uuid of this video gallery
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_videoGallery.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VideoGalleryWrapper)) {
			return false;
		}

		VideoGalleryWrapper videoGalleryWrapper = (VideoGalleryWrapper)obj;

		if (Objects.equals(_videoGallery, videoGalleryWrapper._videoGallery)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _videoGallery.getStagedModelType();
	}

	@Override
	public VideoGallery getWrappedModel() {
		return _videoGallery;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _videoGallery.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _videoGallery.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_videoGallery.resetOriginalValues();
	}

	private final VideoGallery _videoGallery;
}
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
 * This class is a wrapper for {@link Video}.
 * </p>
 *
 * @author BenjaminBini
 * @see Video
 * @generated
 */
@ProviderType
public class VideoWrapper implements Video, ModelWrapper<Video> {
	public VideoWrapper(Video video) {
		_video = video;
	}

	@Override
	public Class<?> getModelClass() {
		return Video.class;
	}

	@Override
	public String getModelClassName() {
		return Video.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("videoId", getVideoId());
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
		attributes.put("copyright", getCopyright());
		attributes.put("origin", getOrigin());
		attributes.put("source", getSource());
		attributes.put("imageId", getImageId());
		attributes.put("transcriptionFileId", getTranscriptionFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long videoId = (Long)attributes.get("videoId");

		if (videoId != null) {
			setVideoId(videoId);
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

		String copyright = (String)attributes.get("copyright");

		if (copyright != null) {
			setCopyright(copyright);
		}

		String origin = (String)attributes.get("origin");

		if (origin != null) {
			setOrigin(origin);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		Long transcriptionFileId = (Long)attributes.get("transcriptionFileId");

		if (transcriptionFileId != null) {
			setTranscriptionFileId(transcriptionFileId);
		}
	}

	/**
	* Returns <code>true</code> if this video is approved.
	*
	* @return <code>true</code> if this video is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _video.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _video.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this video is denied.
	*
	* @return <code>true</code> if this video is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _video.isDenied();
	}

	/**
	* Returns <code>true</code> if this video is a draft.
	*
	* @return <code>true</code> if this video is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _video.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _video.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this video is expired.
	*
	* @return <code>true</code> if this video is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _video.isExpired();
	}

	/**
	* Returns <code>true</code> if this video is inactive.
	*
	* @return <code>true</code> if this video is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _video.isInactive();
	}

	/**
	* Returns <code>true</code> if this video is incomplete.
	*
	* @return <code>true</code> if this video is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _video.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _video.isNew();
	}

	/**
	* Returns <code>true</code> if this video is pending.
	*
	* @return <code>true</code> if this video is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _video.isPending();
	}

	/**
	* Returns <code>true</code> if this video is scheduled.
	*
	* @return <code>true</code> if this video is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _video.isScheduled();
	}

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _video.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _video.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.video.model.Video> toCacheModel() {
		return _video.toCacheModel();
	}

	/**
	* Retourne la version live de la vidéo si elle existe, null sinon
	*/
	@Override
	public eu.strasbourg.service.video.model.Video getLiveVersion() {
		return _video.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.video.model.Video toEscapedModel() {
		return new VideoWrapper(_video.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.video.model.Video toUnescapedModel() {
		return new VideoWrapper(_video.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.video.model.Video video) {
		return _video.compareTo(video);
	}

	/**
	* Returns the status of this video.
	*
	* @return the status of this video
	*/
	@Override
	public int getStatus() {
		return _video.getStatus();
	}

	@Override
	public int hashCode() {
		return _video.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _video.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this video.
	*
	* @return the image ID of this video
	*/
	@Override
	public java.lang.Long getImageId() {
		return _video.getImageId();
	}

	/**
	* Returns the transcription file ID of this video.
	*
	* @return the transcription file ID of this video
	*/
	@Override
	public java.lang.Long getTranscriptionFileId() {
		return _video.getTranscriptionFileId();
	}

	@Override
	public java.lang.Object clone() {
		return new VideoWrapper((Video)_video.clone());
	}

	/**
	* Returns the copyright of this video.
	*
	* @return the copyright of this video
	*/
	@Override
	public java.lang.String getCopyright() {
		return _video.getCopyright();
	}

	/**
	* Returns the localized copyright of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized copyright of this video
	*/
	@Override
	public java.lang.String getCopyright(java.lang.String languageId) {
		return _video.getCopyright(languageId);
	}

	/**
	* Returns the localized copyright of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized copyright of this video
	*/
	@Override
	public java.lang.String getCopyright(java.lang.String languageId,
		boolean useDefault) {
		return _video.getCopyright(languageId, useDefault);
	}

	/**
	* Returns the localized copyright of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized copyright of this video
	*/
	@Override
	public java.lang.String getCopyright(java.util.Locale locale) {
		return _video.getCopyright(locale);
	}

	/**
	* Returns the localized copyright of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized copyright of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getCopyright(java.util.Locale locale,
		boolean useDefault) {
		return _video.getCopyright(locale, useDefault);
	}

	@Override
	public java.lang.String getCopyrightCurrentLanguageId() {
		return _video.getCopyrightCurrentLanguageId();
	}

	@Override
	public java.lang.String getCopyrightCurrentValue() {
		return _video.getCopyrightCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _video.getDefaultLanguageId();
	}

	/**
	* Returns the description of this video.
	*
	* @return the description of this video
	*/
	@Override
	public java.lang.String getDescription() {
		return _video.getDescription();
	}

	/**
	* Returns the localized description of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this video
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _video.getDescription(languageId);
	}

	/**
	* Returns the localized description of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this video
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _video.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this video
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _video.getDescription(locale);
	}

	/**
	* Returns the localized description of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _video.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _video.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _video.getDescriptionCurrentValue();
	}

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _video.getImageURL();
	}

	/**
	* Returns the origin of this video.
	*
	* @return the origin of this video
	*/
	@Override
	public java.lang.String getOrigin() {
		return _video.getOrigin();
	}

	/**
	* Returns the localized origin of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized origin of this video
	*/
	@Override
	public java.lang.String getOrigin(java.lang.String languageId) {
		return _video.getOrigin(languageId);
	}

	/**
	* Returns the localized origin of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized origin of this video
	*/
	@Override
	public java.lang.String getOrigin(java.lang.String languageId,
		boolean useDefault) {
		return _video.getOrigin(languageId, useDefault);
	}

	/**
	* Returns the localized origin of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized origin of this video
	*/
	@Override
	public java.lang.String getOrigin(java.util.Locale locale) {
		return _video.getOrigin(locale);
	}

	/**
	* Returns the localized origin of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized origin of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getOrigin(java.util.Locale locale,
		boolean useDefault) {
		return _video.getOrigin(locale, useDefault);
	}

	@Override
	public java.lang.String getOriginCurrentLanguageId() {
		return _video.getOriginCurrentLanguageId();
	}

	@Override
	public java.lang.String getOriginCurrentValue() {
		return _video.getOriginCurrentValue();
	}

	/**
	* Retourne le code html embed du player si le champ "source" est un lien
	* vers une vidéo Dailymotion, YouTube ou Vimeo, retourne le contenu du
	* champ source sinon
	*/
	@Override
	public java.lang.String getPlayer(java.util.Locale locale) {
		return _video.getPlayer(locale);
	}

	/**
	* Returns the source of this video.
	*
	* @return the source of this video
	*/
	@Override
	public java.lang.String getSource() {
		return _video.getSource();
	}

	/**
	* Returns the localized source of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized source of this video
	*/
	@Override
	public java.lang.String getSource(java.lang.String languageId) {
		return _video.getSource(languageId);
	}

	/**
	* Returns the localized source of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized source of this video
	*/
	@Override
	public java.lang.String getSource(java.lang.String languageId,
		boolean useDefault) {
		return _video.getSource(languageId, useDefault);
	}

	/**
	* Returns the localized source of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized source of this video
	*/
	@Override
	public java.lang.String getSource(java.util.Locale locale) {
		return _video.getSource(locale);
	}

	/**
	* Returns the localized source of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized source of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSource(java.util.Locale locale,
		boolean useDefault) {
		return _video.getSource(locale, useDefault);
	}

	@Override
	public java.lang.String getSourceCurrentLanguageId() {
		return _video.getSourceCurrentLanguageId();
	}

	@Override
	public java.lang.String getSourceCurrentValue() {
		return _video.getSourceCurrentValue();
	}

	/**
	* Returns the status by user name of this video.
	*
	* @return the status by user name of this video
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _video.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this video.
	*
	* @return the status by user uuid of this video
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _video.getStatusByUserUuid();
	}

	/**
	* Returns the title of this video.
	*
	* @return the title of this video
	*/
	@Override
	public java.lang.String getTitle() {
		return _video.getTitle();
	}

	/**
	* Returns the localized title of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this video
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _video.getTitle(languageId);
	}

	/**
	* Returns the localized title of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this video
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _video.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this video in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this video
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _video.getTitle(locale);
	}

	/**
	* Returns the localized title of this video in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _video.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _video.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _video.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this video.
	*
	* @return the user name of this video
	*/
	@Override
	public java.lang.String getUserName() {
		return _video.getUserName();
	}

	/**
	* Returns the user uuid of this video.
	*
	* @return the user uuid of this video
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _video.getUserUuid();
	}

	/**
	* Returns the uuid of this video.
	*
	* @return the uuid of this video
	*/
	@Override
	public java.lang.String getUuid() {
		return _video.getUuid();
	}

	/**
	* Retourne la liste des ids des galeries de la vidéo
	*/
	@Override
	public java.lang.String getVideoGalleriesIds() {
		return _video.getVideoGalleriesIds();
	}

	@Override
	public java.lang.String toString() {
		return _video.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _video.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _video.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this video.
	*
	* @return the create date of this video
	*/
	@Override
	public Date getCreateDate() {
		return _video.getCreateDate();
	}

	/**
	* Returns the last publish date of this video.
	*
	* @return the last publish date of this video
	*/
	@Override
	public Date getLastPublishDate() {
		return _video.getLastPublishDate();
	}

	/**
	* Returns the modified date of this video.
	*
	* @return the modified date of this video
	*/
	@Override
	public Date getModifiedDate() {
		return _video.getModifiedDate();
	}

	/**
	* Returns the status date of this video.
	*
	* @return the status date of this video
	*/
	@Override
	public Date getStatusDate() {
		return _video.getStatusDate();
	}

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via
	* l'AssetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _video.getCategories();
	}

	/**
	* Renvoie la liste des galeries vidéos publiées de la
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getPublishedVideosGalleries() {
		return _video.getPublishedVideosGalleries();
	}

	/**
	* Retourne la liste des galeries de la vidéo
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries() {
		return _video.getVideoGalleries();
	}

	/**
	* Returns a map of the locales and localized copyrights of this video.
	*
	* @return the locales and localized copyrights of this video
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getCopyrightMap() {
		return _video.getCopyrightMap();
	}

	/**
	* Returns a map of the locales and localized descriptions of this video.
	*
	* @return the locales and localized descriptions of this video
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _video.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized origins of this video.
	*
	* @return the locales and localized origins of this video
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getOriginMap() {
		return _video.getOriginMap();
	}

	/**
	* Returns a map of the locales and localized sources of this video.
	*
	* @return the locales and localized sources of this video
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSourceMap() {
		return _video.getSourceMap();
	}

	/**
	* Returns a map of the locales and localized titles of this video.
	*
	* @return the locales and localized titles of this video
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _video.getTitleMap();
	}

	/**
	* Returns the company ID of this video.
	*
	* @return the company ID of this video
	*/
	@Override
	public long getCompanyId() {
		return _video.getCompanyId();
	}

	/**
	* Returns the group ID of this video.
	*
	* @return the group ID of this video
	*/
	@Override
	public long getGroupId() {
		return _video.getGroupId();
	}

	/**
	* Returns the primary key of this video.
	*
	* @return the primary key of this video
	*/
	@Override
	public long getPrimaryKey() {
		return _video.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this video.
	*
	* @return the status by user ID of this video
	*/
	@Override
	public long getStatusByUserId() {
		return _video.getStatusByUserId();
	}

	/**
	* Returns the user ID of this video.
	*
	* @return the user ID of this video
	*/
	@Override
	public long getUserId() {
		return _video.getUserId();
	}

	/**
	* Returns the video ID of this video.
	*
	* @return the video ID of this video
	*/
	@Override
	public long getVideoId() {
		return _video.getVideoId();
	}

	@Override
	public void persist() {
		_video.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_video.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_video.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_video.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this video.
	*
	* @param companyId the company ID of this video
	*/
	@Override
	public void setCompanyId(long companyId) {
		_video.setCompanyId(companyId);
	}

	/**
	* Sets the copyright of this video.
	*
	* @param copyright the copyright of this video
	*/
	@Override
	public void setCopyright(java.lang.String copyright) {
		_video.setCopyright(copyright);
	}

	/**
	* Sets the localized copyright of this video in the language.
	*
	* @param copyright the localized copyright of this video
	* @param locale the locale of the language
	*/
	@Override
	public void setCopyright(java.lang.String copyright, java.util.Locale locale) {
		_video.setCopyright(copyright, locale);
	}

	/**
	* Sets the localized copyright of this video in the language, and sets the default locale.
	*
	* @param copyright the localized copyright of this video
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCopyright(java.lang.String copyright,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_video.setCopyright(copyright, locale, defaultLocale);
	}

	@Override
	public void setCopyrightCurrentLanguageId(java.lang.String languageId) {
		_video.setCopyrightCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized copyrights of this video from the map of locales and localized copyrights.
	*
	* @param copyrightMap the locales and localized copyrights of this video
	*/
	@Override
	public void setCopyrightMap(
		Map<java.util.Locale, java.lang.String> copyrightMap) {
		_video.setCopyrightMap(copyrightMap);
	}

	/**
	* Sets the localized copyrights of this video from the map of locales and localized copyrights, and sets the default locale.
	*
	* @param copyrightMap the locales and localized copyrights of this video
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCopyrightMap(
		Map<java.util.Locale, java.lang.String> copyrightMap,
		java.util.Locale defaultLocale) {
		_video.setCopyrightMap(copyrightMap, defaultLocale);
	}

	/**
	* Sets the create date of this video.
	*
	* @param createDate the create date of this video
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_video.setCreateDate(createDate);
	}

	/**
	* Sets the description of this video.
	*
	* @param description the description of this video
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_video.setDescription(description);
	}

	/**
	* Sets the localized description of this video in the language.
	*
	* @param description the localized description of this video
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_video.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this video in the language, and sets the default locale.
	*
	* @param description the localized description of this video
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_video.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_video.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this video from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this video
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_video.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this video from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this video
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_video.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_video.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_video.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_video.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this video.
	*
	* @param groupId the group ID of this video
	*/
	@Override
	public void setGroupId(long groupId) {
		_video.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this video.
	*
	* @param imageId the image ID of this video
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_video.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this video.
	*
	* @param lastPublishDate the last publish date of this video
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_video.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this video.
	*
	* @param modifiedDate the modified date of this video
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_video.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_video.setNew(n);
	}

	/**
	* Sets the origin of this video.
	*
	* @param origin the origin of this video
	*/
	@Override
	public void setOrigin(java.lang.String origin) {
		_video.setOrigin(origin);
	}

	/**
	* Sets the localized origin of this video in the language.
	*
	* @param origin the localized origin of this video
	* @param locale the locale of the language
	*/
	@Override
	public void setOrigin(java.lang.String origin, java.util.Locale locale) {
		_video.setOrigin(origin, locale);
	}

	/**
	* Sets the localized origin of this video in the language, and sets the default locale.
	*
	* @param origin the localized origin of this video
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setOrigin(java.lang.String origin, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_video.setOrigin(origin, locale, defaultLocale);
	}

	@Override
	public void setOriginCurrentLanguageId(java.lang.String languageId) {
		_video.setOriginCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized origins of this video from the map of locales and localized origins.
	*
	* @param originMap the locales and localized origins of this video
	*/
	@Override
	public void setOriginMap(Map<java.util.Locale, java.lang.String> originMap) {
		_video.setOriginMap(originMap);
	}

	/**
	* Sets the localized origins of this video from the map of locales and localized origins, and sets the default locale.
	*
	* @param originMap the locales and localized origins of this video
	* @param defaultLocale the default locale
	*/
	@Override
	public void setOriginMap(
		Map<java.util.Locale, java.lang.String> originMap,
		java.util.Locale defaultLocale) {
		_video.setOriginMap(originMap, defaultLocale);
	}

	/**
	* Sets the primary key of this video.
	*
	* @param primaryKey the primary key of this video
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_video.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_video.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source of this video.
	*
	* @param source the source of this video
	*/
	@Override
	public void setSource(java.lang.String source) {
		_video.setSource(source);
	}

	/**
	* Sets the localized source of this video in the language.
	*
	* @param source the localized source of this video
	* @param locale the locale of the language
	*/
	@Override
	public void setSource(java.lang.String source, java.util.Locale locale) {
		_video.setSource(source, locale);
	}

	/**
	* Sets the localized source of this video in the language, and sets the default locale.
	*
	* @param source the localized source of this video
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSource(java.lang.String source, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_video.setSource(source, locale, defaultLocale);
	}

	@Override
	public void setSourceCurrentLanguageId(java.lang.String languageId) {
		_video.setSourceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized sources of this video from the map of locales and localized sources.
	*
	* @param sourceMap the locales and localized sources of this video
	*/
	@Override
	public void setSourceMap(Map<java.util.Locale, java.lang.String> sourceMap) {
		_video.setSourceMap(sourceMap);
	}

	/**
	* Sets the localized sources of this video from the map of locales and localized sources, and sets the default locale.
	*
	* @param sourceMap the locales and localized sources of this video
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSourceMap(
		Map<java.util.Locale, java.lang.String> sourceMap,
		java.util.Locale defaultLocale) {
		_video.setSourceMap(sourceMap, defaultLocale);
	}

	/**
	* Sets the status of this video.
	*
	* @param status the status of this video
	*/
	@Override
	public void setStatus(int status) {
		_video.setStatus(status);
	}

	/**
	* Sets the status by user ID of this video.
	*
	* @param statusByUserId the status by user ID of this video
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_video.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this video.
	*
	* @param statusByUserName the status by user name of this video
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_video.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this video.
	*
	* @param statusByUserUuid the status by user uuid of this video
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_video.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this video.
	*
	* @param statusDate the status date of this video
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_video.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this video.
	*
	* @param title the title of this video
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_video.setTitle(title);
	}

	/**
	* Sets the localized title of this video in the language.
	*
	* @param title the localized title of this video
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_video.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this video in the language, and sets the default locale.
	*
	* @param title the localized title of this video
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_video.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_video.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this video from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this video
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_video.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this video from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this video
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_video.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the transcription file ID of this video.
	*
	* @param transcriptionFileId the transcription file ID of this video
	*/
	@Override
	public void setTranscriptionFileId(java.lang.Long transcriptionFileId) {
		_video.setTranscriptionFileId(transcriptionFileId);
	}

	/**
	* Sets the user ID of this video.
	*
	* @param userId the user ID of this video
	*/
	@Override
	public void setUserId(long userId) {
		_video.setUserId(userId);
	}

	/**
	* Sets the user name of this video.
	*
	* @param userName the user name of this video
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_video.setUserName(userName);
	}

	/**
	* Sets the user uuid of this video.
	*
	* @param userUuid the user uuid of this video
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_video.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this video.
	*
	* @param uuid the uuid of this video
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_video.setUuid(uuid);
	}

	/**
	* Sets the video ID of this video.
	*
	* @param videoId the video ID of this video
	*/
	@Override
	public void setVideoId(long videoId) {
		_video.setVideoId(videoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VideoWrapper)) {
			return false;
		}

		VideoWrapper videoWrapper = (VideoWrapper)obj;

		if (Objects.equals(_video, videoWrapper._video)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _video.getStagedModelType();
	}

	@Override
	public Video getWrappedModel() {
		return _video;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _video.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _video.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_video.resetOriginalValues();
	}

	private final Video _video;
}
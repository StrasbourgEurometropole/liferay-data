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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Video service. Represents a row in the &quot;video_Video&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.video.model.impl.VideoModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.video.model.impl.VideoImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see Video
 * @see eu.strasbourg.service.video.model.impl.VideoImpl
 * @see eu.strasbourg.service.video.model.impl.VideoModelImpl
 * @generated
 */
@ProviderType
public interface VideoModel extends BaseModel<Video>, LocalizedModel,
	ShardedModel, StagedGroupedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a video model instance should use the {@link Video} interface instead.
	 */

	/**
	 * Returns the primary key of this video.
	 *
	 * @return the primary key of this video
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this video.
	 *
	 * @param primaryKey the primary key of this video
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this video.
	 *
	 * @return the uuid of this video
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this video.
	 *
	 * @param uuid the uuid of this video
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the video ID of this video.
	 *
	 * @return the video ID of this video
	 */
	public long getVideoId();

	/**
	 * Sets the video ID of this video.
	 *
	 * @param videoId the video ID of this video
	 */
	public void setVideoId(long videoId);

	/**
	 * Returns the group ID of this video.
	 *
	 * @return the group ID of this video
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this video.
	 *
	 * @param groupId the group ID of this video
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this video.
	 *
	 * @return the company ID of this video
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this video.
	 *
	 * @param companyId the company ID of this video
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this video.
	 *
	 * @return the user ID of this video
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this video.
	 *
	 * @param userId the user ID of this video
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this video.
	 *
	 * @return the user uuid of this video
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this video.
	 *
	 * @param userUuid the user uuid of this video
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this video.
	 *
	 * @return the user name of this video
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this video.
	 *
	 * @param userName the user name of this video
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this video.
	 *
	 * @return the create date of this video
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this video.
	 *
	 * @param createDate the create date of this video
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this video.
	 *
	 * @return the modified date of this video
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this video.
	 *
	 * @param modifiedDate the modified date of this video
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the last publish date of this video.
	 *
	 * @return the last publish date of this video
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this video.
	 *
	 * @param lastPublishDate the last publish date of this video
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this video.
	 *
	 * @return the status of this video
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this video.
	 *
	 * @param status the status of this video
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this video.
	 *
	 * @return the status by user ID of this video
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this video.
	 *
	 * @param statusByUserId the status by user ID of this video
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this video.
	 *
	 * @return the status by user uuid of this video
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this video.
	 *
	 * @param statusByUserUuid the status by user uuid of this video
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this video.
	 *
	 * @return the status by user name of this video
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this video.
	 *
	 * @param statusByUserName the status by user name of this video
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this video.
	 *
	 * @return the status date of this video
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this video.
	 *
	 * @param statusDate the status date of this video
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the title of this video.
	 *
	 * @return the title of this video
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this video
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this video
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this video
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this video.
	 *
	 * @return the locales and localized titles of this video
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this video.
	 *
	 * @param title the title of this video
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this video in the language.
	 *
	 * @param title the localized title of this video
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this video in the language, and sets the default locale.
	 *
	 * @param title the localized title of this video
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this video from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this video
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this video from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this video
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this video.
	 *
	 * @return the description of this video
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this video
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this video
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this video
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this video.
	 *
	 * @return the locales and localized descriptions of this video
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this video.
	 *
	 * @param description the description of this video
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this video in the language.
	 *
	 * @param description the localized description of this video
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this video in the language, and sets the default locale.
	 *
	 * @param description the localized description of this video
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this video from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this video
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this video from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this video
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the copyright of this video.
	 *
	 * @return the copyright of this video
	 */
	public String getCopyright();

	/**
	 * Returns the localized copyright of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized copyright of this video
	 */
	@AutoEscape
	public String getCopyright(Locale locale);

	/**
	 * Returns the localized copyright of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized copyright of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getCopyright(Locale locale, boolean useDefault);

	/**
	 * Returns the localized copyright of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized copyright of this video
	 */
	@AutoEscape
	public String getCopyright(String languageId);

	/**
	 * Returns the localized copyright of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized copyright of this video
	 */
	@AutoEscape
	public String getCopyright(String languageId, boolean useDefault);

	@AutoEscape
	public String getCopyrightCurrentLanguageId();

	@AutoEscape
	public String getCopyrightCurrentValue();

	/**
	 * Returns a map of the locales and localized copyrights of this video.
	 *
	 * @return the locales and localized copyrights of this video
	 */
	public Map<Locale, String> getCopyrightMap();

	/**
	 * Sets the copyright of this video.
	 *
	 * @param copyright the copyright of this video
	 */
	public void setCopyright(String copyright);

	/**
	 * Sets the localized copyright of this video in the language.
	 *
	 * @param copyright the localized copyright of this video
	 * @param locale the locale of the language
	 */
	public void setCopyright(String copyright, Locale locale);

	/**
	 * Sets the localized copyright of this video in the language, and sets the default locale.
	 *
	 * @param copyright the localized copyright of this video
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setCopyright(String copyright, Locale locale,
		Locale defaultLocale);

	public void setCopyrightCurrentLanguageId(String languageId);

	/**
	 * Sets the localized copyrights of this video from the map of locales and localized copyrights.
	 *
	 * @param copyrightMap the locales and localized copyrights of this video
	 */
	public void setCopyrightMap(Map<Locale, String> copyrightMap);

	/**
	 * Sets the localized copyrights of this video from the map of locales and localized copyrights, and sets the default locale.
	 *
	 * @param copyrightMap the locales and localized copyrights of this video
	 * @param defaultLocale the default locale
	 */
	public void setCopyrightMap(Map<Locale, String> copyrightMap,
		Locale defaultLocale);

	/**
	 * Returns the origin of this video.
	 *
	 * @return the origin of this video
	 */
	public String getOrigin();

	/**
	 * Returns the localized origin of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized origin of this video
	 */
	@AutoEscape
	public String getOrigin(Locale locale);

	/**
	 * Returns the localized origin of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized origin of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getOrigin(Locale locale, boolean useDefault);

	/**
	 * Returns the localized origin of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized origin of this video
	 */
	@AutoEscape
	public String getOrigin(String languageId);

	/**
	 * Returns the localized origin of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized origin of this video
	 */
	@AutoEscape
	public String getOrigin(String languageId, boolean useDefault);

	@AutoEscape
	public String getOriginCurrentLanguageId();

	@AutoEscape
	public String getOriginCurrentValue();

	/**
	 * Returns a map of the locales and localized origins of this video.
	 *
	 * @return the locales and localized origins of this video
	 */
	public Map<Locale, String> getOriginMap();

	/**
	 * Sets the origin of this video.
	 *
	 * @param origin the origin of this video
	 */
	public void setOrigin(String origin);

	/**
	 * Sets the localized origin of this video in the language.
	 *
	 * @param origin the localized origin of this video
	 * @param locale the locale of the language
	 */
	public void setOrigin(String origin, Locale locale);

	/**
	 * Sets the localized origin of this video in the language, and sets the default locale.
	 *
	 * @param origin the localized origin of this video
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setOrigin(String origin, Locale locale, Locale defaultLocale);

	public void setOriginCurrentLanguageId(String languageId);

	/**
	 * Sets the localized origins of this video from the map of locales and localized origins.
	 *
	 * @param originMap the locales and localized origins of this video
	 */
	public void setOriginMap(Map<Locale, String> originMap);

	/**
	 * Sets the localized origins of this video from the map of locales and localized origins, and sets the default locale.
	 *
	 * @param originMap the locales and localized origins of this video
	 * @param defaultLocale the default locale
	 */
	public void setOriginMap(Map<Locale, String> originMap, Locale defaultLocale);

	/**
	 * Returns the source of this video.
	 *
	 * @return the source of this video
	 */
	public String getSource();

	/**
	 * Returns the localized source of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized source of this video
	 */
	@AutoEscape
	public String getSource(Locale locale);

	/**
	 * Returns the localized source of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized source of this video. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getSource(Locale locale, boolean useDefault);

	/**
	 * Returns the localized source of this video in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized source of this video
	 */
	@AutoEscape
	public String getSource(String languageId);

	/**
	 * Returns the localized source of this video in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized source of this video
	 */
	@AutoEscape
	public String getSource(String languageId, boolean useDefault);

	@AutoEscape
	public String getSourceCurrentLanguageId();

	@AutoEscape
	public String getSourceCurrentValue();

	/**
	 * Returns a map of the locales and localized sources of this video.
	 *
	 * @return the locales and localized sources of this video
	 */
	public Map<Locale, String> getSourceMap();

	/**
	 * Sets the source of this video.
	 *
	 * @param source the source of this video
	 */
	public void setSource(String source);

	/**
	 * Sets the localized source of this video in the language.
	 *
	 * @param source the localized source of this video
	 * @param locale the locale of the language
	 */
	public void setSource(String source, Locale locale);

	/**
	 * Sets the localized source of this video in the language, and sets the default locale.
	 *
	 * @param source the localized source of this video
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setSource(String source, Locale locale, Locale defaultLocale);

	public void setSourceCurrentLanguageId(String languageId);

	/**
	 * Sets the localized sources of this video from the map of locales and localized sources.
	 *
	 * @param sourceMap the locales and localized sources of this video
	 */
	public void setSourceMap(Map<Locale, String> sourceMap);

	/**
	 * Sets the localized sources of this video from the map of locales and localized sources, and sets the default locale.
	 *
	 * @param sourceMap the locales and localized sources of this video
	 * @param defaultLocale the default locale
	 */
	public void setSourceMap(Map<Locale, String> sourceMap, Locale defaultLocale);

	/**
	 * Returns the image ID of this video.
	 *
	 * @return the image ID of this video
	 */
	public Long getImageId();

	/**
	 * Sets the image ID of this video.
	 *
	 * @param imageId the image ID of this video
	 */
	public void setImageId(Long imageId);

	/**
	 * Returns the transcription file ID of this video.
	 *
	 * @return the transcription file ID of this video
	 */
	public Long getTranscriptionFileId();

	/**
	 * Sets the transcription file ID of this video.
	 *
	 * @param transcriptionFileId the transcription file ID of this video
	 */
	public void setTranscriptionFileId(Long transcriptionFileId);

	/**
	 * Returns <code>true</code> if this video is approved.
	 *
	 * @return <code>true</code> if this video is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this video is denied.
	 *
	 * @return <code>true</code> if this video is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this video is a draft.
	 *
	 * @return <code>true</code> if this video is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this video is expired.
	 *
	 * @return <code>true</code> if this video is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this video is inactive.
	 *
	 * @return <code>true</code> if this video is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this video is incomplete.
	 *
	 * @return <code>true</code> if this video is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this video is pending.
	 *
	 * @return <code>true</code> if this video is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this video is scheduled.
	 *
	 * @return <code>true</code> if this video is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(eu.strasbourg.service.video.model.Video video);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.video.model.Video> toCacheModel();

	@Override
	public eu.strasbourg.service.video.model.Video toEscapedModel();

	@Override
	public eu.strasbourg.service.video.model.Video toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
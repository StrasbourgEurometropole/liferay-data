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
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Activity service. Represents a row in the &quot;activity_Activity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.activity.model.impl.ActivityModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.activity.model.impl.ActivityImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Activity
 * @generated
 */
@ProviderType
public interface ActivityModel
	extends BaseModel<Activity>, GroupedModel, LocalizedModel, ShardedModel,
			StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a activity model instance should use the {@link Activity} interface instead.
	 */

	/**
	 * Returns the primary key of this activity.
	 *
	 * @return the primary key of this activity
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this activity.
	 *
	 * @param primaryKey the primary key of this activity
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this activity.
	 *
	 * @return the uuid of this activity
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this activity.
	 *
	 * @param uuid the uuid of this activity
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the activity ID of this activity.
	 *
	 * @return the activity ID of this activity
	 */
	public long getActivityId();

	/**
	 * Sets the activity ID of this activity.
	 *
	 * @param activityId the activity ID of this activity
	 */
	public void setActivityId(long activityId);

	/**
	 * Returns the group ID of this activity.
	 *
	 * @return the group ID of this activity
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this activity.
	 *
	 * @param groupId the group ID of this activity
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this activity.
	 *
	 * @return the company ID of this activity
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this activity.
	 *
	 * @param companyId the company ID of this activity
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this activity.
	 *
	 * @return the user ID of this activity
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this activity.
	 *
	 * @param userId the user ID of this activity
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this activity.
	 *
	 * @return the user uuid of this activity
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this activity.
	 *
	 * @param userUuid the user uuid of this activity
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this activity.
	 *
	 * @return the user name of this activity
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this activity.
	 *
	 * @param userName the user name of this activity
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this activity.
	 *
	 * @return the create date of this activity
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this activity.
	 *
	 * @param createDate the create date of this activity
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this activity.
	 *
	 * @return the modified date of this activity
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this activity.
	 *
	 * @param modifiedDate the modified date of this activity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this activity.
	 *
	 * @return the title of this activity
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this activity
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this activity
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this activity
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this activity.
	 *
	 * @return the locales and localized titles of this activity
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this activity.
	 *
	 * @param title the title of this activity
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this activity in the language.
	 *
	 * @param title the localized title of this activity
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this activity in the language, and sets the default locale.
	 *
	 * @param title the localized title of this activity
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this activity from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this activity
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this activity from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this activity
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this activity.
	 *
	 * @return the description of this activity
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this activity
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this activity
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this activity
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this activity.
	 *
	 * @return the locales and localized descriptions of this activity
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this activity.
	 *
	 * @param description the description of this activity
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this activity in the language.
	 *
	 * @param description the localized description of this activity
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this activity in the language, and sets the default locale.
	 *
	 * @param description the localized description of this activity
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this activity from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this activity
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this activity from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this activity
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the videos IDs of this activity.
	 *
	 * @return the videos IDs of this activity
	 */
	@AutoEscape
	public String getVideosIds();

	/**
	 * Sets the videos IDs of this activity.
	 *
	 * @param videosIds the videos IDs of this activity
	 */
	public void setVideosIds(String videosIds);

	/**
	 * Returns the image ID of this activity.
	 *
	 * @return the image ID of this activity
	 */
	public long getImageId();

	/**
	 * Sets the image ID of this activity.
	 *
	 * @param imageId the image ID of this activity
	 */
	public void setImageId(long imageId);

	/**
	 * Returns the images IDs of this activity.
	 *
	 * @return the images IDs of this activity
	 */
	@AutoEscape
	public String getImagesIds();

	/**
	 * Sets the images IDs of this activity.
	 *
	 * @param imagesIds the images IDs of this activity
	 */
	public void setImagesIds(String imagesIds);

	/**
	 * Returns the files IDs of this activity.
	 *
	 * @return the files IDs of this activity
	 */
	@AutoEscape
	public String getFilesIds();

	/**
	 * Sets the files IDs of this activity.
	 *
	 * @param filesIds the files IDs of this activity
	 */
	public void setFilesIds(String filesIds);

	/**
	 * Returns the status of this activity.
	 *
	 * @return the status of this activity
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this activity.
	 *
	 * @param status the status of this activity
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this activity.
	 *
	 * @return the status by user ID of this activity
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this activity.
	 *
	 * @param statusByUserId the status by user ID of this activity
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this activity.
	 *
	 * @return the status by user uuid of this activity
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this activity.
	 *
	 * @param statusByUserUuid the status by user uuid of this activity
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this activity.
	 *
	 * @return the status by user name of this activity
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this activity.
	 *
	 * @param statusByUserName the status by user name of this activity
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this activity.
	 *
	 * @return the status date of this activity
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this activity.
	 *
	 * @param statusDate the status date of this activity
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this activity is approved.
	 *
	 * @return <code>true</code> if this activity is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this activity is denied.
	 *
	 * @return <code>true</code> if this activity is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this activity is a draft.
	 *
	 * @return <code>true</code> if this activity is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this activity is expired.
	 *
	 * @return <code>true</code> if this activity is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this activity is inactive.
	 *
	 * @return <code>true</code> if this activity is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this activity is incomplete.
	 *
	 * @return <code>true</code> if this activity is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this activity is pending.
	 *
	 * @return <code>true</code> if this activity is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this activity is scheduled.
	 *
	 * @return <code>true</code> if this activity is scheduled; <code>false</code> otherwise
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
	public int compareTo(
		eu.strasbourg.service.activity.model.Activity activity);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.activity.model.Activity>
		toCacheModel();

	@Override
	public eu.strasbourg.service.activity.model.Activity toEscapedModel();

	@Override
	public eu.strasbourg.service.activity.model.Activity toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}
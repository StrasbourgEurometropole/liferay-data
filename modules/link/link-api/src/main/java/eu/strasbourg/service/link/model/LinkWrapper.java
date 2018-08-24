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

package eu.strasbourg.service.link.model;

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
 * This class is a wrapper for {@link Link}.
 * </p>
 *
 * @author BenjaminBini
 * @see Link
 * @generated
 */
@ProviderType
public class LinkWrapper implements Link, ModelWrapper<Link> {
	public LinkWrapper(Link link) {
		_link = link;
	}

	@Override
	public Class<?> getModelClass() {
		return Link.class;
	}

	@Override
	public String getModelClassName() {
		return Link.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("linkId", getLinkId());
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
		attributes.put("URL", getURL());
		attributes.put("hoverText", getHoverText());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long linkId = (Long)attributes.get("linkId");

		if (linkId != null) {
			setLinkId(linkId);
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

		String URL = (String)attributes.get("URL");

		if (URL != null) {
			setURL(URL);
		}

		String hoverText = (String)attributes.get("hoverText");

		if (hoverText != null) {
			setHoverText(hoverText);
		}
	}

	/**
	* Returns <code>true</code> if this link is approved.
	*
	* @return <code>true</code> if this link is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _link.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _link.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this link is denied.
	*
	* @return <code>true</code> if this link is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _link.isDenied();
	}

	/**
	* Returns <code>true</code> if this link is a draft.
	*
	* @return <code>true</code> if this link is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _link.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _link.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this link is expired.
	*
	* @return <code>true</code> if this link is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _link.isExpired();
	}

	/**
	* Returns <code>true</code> if this link is inactive.
	*
	* @return <code>true</code> if this link is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _link.isInactive();
	}

	/**
	* Returns <code>true</code> if this link is incomplete.
	*
	* @return <code>true</code> if this link is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _link.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _link.isNew();
	}

	/**
	* Returns <code>true</code> if this link is pending.
	*
	* @return <code>true</code> if this link is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _link.isPending();
	}

	/**
	* Returns <code>true</code> if this link is scheduled.
	*
	* @return <code>true</code> if this link is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _link.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _link.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _link.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.link.model.Link> toCacheModel() {
		return _link.toCacheModel();
	}

	/**
	* Renvoie la version live du lien, si elle existe
	*/
	@Override
	public eu.strasbourg.service.link.model.Link getLiveVersion() {
		return _link.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.link.model.Link toEscapedModel() {
		return new LinkWrapper(_link.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.link.model.Link toUnescapedModel() {
		return new LinkWrapper(_link.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.link.model.Link link) {
		return _link.compareTo(link);
	}

	/**
	* Returns the status of this link.
	*
	* @return the status of this link
	*/
	@Override
	public int getStatus() {
		return _link.getStatus();
	}

	@Override
	public int hashCode() {
		return _link.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _link.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LinkWrapper((Link)_link.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _link.getDefaultLanguageId();
	}

	/**
	* Returns the hover text of this link.
	*
	* @return the hover text of this link
	*/
	@Override
	public java.lang.String getHoverText() {
		return _link.getHoverText();
	}

	/**
	* Returns the localized hover text of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized hover text of this link
	*/
	@Override
	public java.lang.String getHoverText(java.lang.String languageId) {
		return _link.getHoverText(languageId);
	}

	/**
	* Returns the localized hover text of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized hover text of this link
	*/
	@Override
	public java.lang.String getHoverText(java.lang.String languageId,
		boolean useDefault) {
		return _link.getHoverText(languageId, useDefault);
	}

	/**
	* Returns the localized hover text of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized hover text of this link
	*/
	@Override
	public java.lang.String getHoverText(java.util.Locale locale) {
		return _link.getHoverText(locale);
	}

	/**
	* Returns the localized hover text of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized hover text of this link. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getHoverText(java.util.Locale locale,
		boolean useDefault) {
		return _link.getHoverText(locale, useDefault);
	}

	@Override
	public java.lang.String getHoverTextCurrentLanguageId() {
		return _link.getHoverTextCurrentLanguageId();
	}

	@Override
	public java.lang.String getHoverTextCurrentValue() {
		return _link.getHoverTextCurrentValue();
	}

	/**
	* Returns the status by user name of this link.
	*
	* @return the status by user name of this link
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _link.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this link.
	*
	* @return the status by user uuid of this link
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _link.getStatusByUserUuid();
	}

	/**
	* Returns the title of this link.
	*
	* @return the title of this link
	*/
	@Override
	public java.lang.String getTitle() {
		return _link.getTitle();
	}

	/**
	* Returns the localized title of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this link
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _link.getTitle(languageId);
	}

	/**
	* Returns the localized title of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this link
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _link.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this link
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _link.getTitle(locale);
	}

	/**
	* Returns the localized title of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this link. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _link.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _link.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _link.getTitleCurrentValue();
	}

	/**
	* Returns the url of this link.
	*
	* @return the url of this link
	*/
	@Override
	public java.lang.String getURL() {
		return _link.getURL();
	}

	/**
	* Returns the localized url of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized url of this link
	*/
	@Override
	public java.lang.String getURL(java.lang.String languageId) {
		return _link.getURL(languageId);
	}

	/**
	* Returns the localized url of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized url of this link
	*/
	@Override
	public java.lang.String getURL(java.lang.String languageId,
		boolean useDefault) {
		return _link.getURL(languageId, useDefault);
	}

	/**
	* Returns the localized url of this link in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized url of this link
	*/
	@Override
	public java.lang.String getURL(java.util.Locale locale) {
		return _link.getURL(locale);
	}

	/**
	* Returns the localized url of this link in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized url of this link. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getURL(java.util.Locale locale, boolean useDefault) {
		return _link.getURL(locale, useDefault);
	}

	@Override
	public java.lang.String getURLCurrentLanguageId() {
		return _link.getURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getURLCurrentValue() {
		return _link.getURLCurrentValue();
	}

	/**
	* Returns the user name of this link.
	*
	* @return the user name of this link
	*/
	@Override
	public java.lang.String getUserName() {
		return _link.getUserName();
	}

	/**
	* Returns the user uuid of this link.
	*
	* @return the user uuid of this link
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _link.getUserUuid();
	}

	/**
	* Returns the uuid of this link.
	*
	* @return the uuid of this link
	*/
	@Override
	public java.lang.String getUuid() {
		return _link.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _link.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _link.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _link.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this link.
	*
	* @return the create date of this link
	*/
	@Override
	public Date getCreateDate() {
		return _link.getCreateDate();
	}

	/**
	* Returns the last publish date of this link.
	*
	* @return the last publish date of this link
	*/
	@Override
	public Date getLastPublishDate() {
		return _link.getLastPublishDate();
	}

	/**
	* Returns the modified date of this link.
	*
	* @return the modified date of this link
	*/
	@Override
	public Date getModifiedDate() {
		return _link.getModifiedDate();
	}

	/**
	* Returns the status date of this link.
	*
	* @return the status date of this link
	*/
	@Override
	public Date getStatusDate() {
		return _link.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _link.getCategories();
	}

	/**
	* Returns a map of the locales and localized hover texts of this link.
	*
	* @return the locales and localized hover texts of this link
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getHoverTextMap() {
		return _link.getHoverTextMap();
	}

	/**
	* Returns a map of the locales and localized titles of this link.
	*
	* @return the locales and localized titles of this link
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _link.getTitleMap();
	}

	/**
	* Returns a map of the locales and localized urls of this link.
	*
	* @return the locales and localized urls of this link
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getURLMap() {
		return _link.getURLMap();
	}

	/**
	* Returns the company ID of this link.
	*
	* @return the company ID of this link
	*/
	@Override
	public long getCompanyId() {
		return _link.getCompanyId();
	}

	/**
	* Returns the group ID of this link.
	*
	* @return the group ID of this link
	*/
	@Override
	public long getGroupId() {
		return _link.getGroupId();
	}

	/**
	* Returns the link ID of this link.
	*
	* @return the link ID of this link
	*/
	@Override
	public long getLinkId() {
		return _link.getLinkId();
	}

	/**
	* Returns the primary key of this link.
	*
	* @return the primary key of this link
	*/
	@Override
	public long getPrimaryKey() {
		return _link.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this link.
	*
	* @return the status by user ID of this link
	*/
	@Override
	public long getStatusByUserId() {
		return _link.getStatusByUserId();
	}

	/**
	* Returns the user ID of this link.
	*
	* @return the user ID of this link
	*/
	@Override
	public long getUserId() {
		return _link.getUserId();
	}

	@Override
	public void persist() {
		_link.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_link.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_link.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_link.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this link.
	*
	* @param companyId the company ID of this link
	*/
	@Override
	public void setCompanyId(long companyId) {
		_link.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this link.
	*
	* @param createDate the create date of this link
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_link.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_link.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_link.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_link.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this link.
	*
	* @param groupId the group ID of this link
	*/
	@Override
	public void setGroupId(long groupId) {
		_link.setGroupId(groupId);
	}

	/**
	* Sets the hover text of this link.
	*
	* @param hoverText the hover text of this link
	*/
	@Override
	public void setHoverText(java.lang.String hoverText) {
		_link.setHoverText(hoverText);
	}

	/**
	* Sets the localized hover text of this link in the language.
	*
	* @param hoverText the localized hover text of this link
	* @param locale the locale of the language
	*/
	@Override
	public void setHoverText(java.lang.String hoverText, java.util.Locale locale) {
		_link.setHoverText(hoverText, locale);
	}

	/**
	* Sets the localized hover text of this link in the language, and sets the default locale.
	*
	* @param hoverText the localized hover text of this link
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setHoverText(java.lang.String hoverText,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_link.setHoverText(hoverText, locale, defaultLocale);
	}

	@Override
	public void setHoverTextCurrentLanguageId(java.lang.String languageId) {
		_link.setHoverTextCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized hover texts of this link from the map of locales and localized hover texts.
	*
	* @param hoverTextMap the locales and localized hover texts of this link
	*/
	@Override
	public void setHoverTextMap(
		Map<java.util.Locale, java.lang.String> hoverTextMap) {
		_link.setHoverTextMap(hoverTextMap);
	}

	/**
	* Sets the localized hover texts of this link from the map of locales and localized hover texts, and sets the default locale.
	*
	* @param hoverTextMap the locales and localized hover texts of this link
	* @param defaultLocale the default locale
	*/
	@Override
	public void setHoverTextMap(
		Map<java.util.Locale, java.lang.String> hoverTextMap,
		java.util.Locale defaultLocale) {
		_link.setHoverTextMap(hoverTextMap, defaultLocale);
	}

	/**
	* Sets the last publish date of this link.
	*
	* @param lastPublishDate the last publish date of this link
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_link.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the link ID of this link.
	*
	* @param linkId the link ID of this link
	*/
	@Override
	public void setLinkId(long linkId) {
		_link.setLinkId(linkId);
	}

	/**
	* Sets the modified date of this link.
	*
	* @param modifiedDate the modified date of this link
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_link.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_link.setNew(n);
	}

	/**
	* Sets the primary key of this link.
	*
	* @param primaryKey the primary key of this link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_link.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_link.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this link.
	*
	* @param status the status of this link
	*/
	@Override
	public void setStatus(int status) {
		_link.setStatus(status);
	}

	/**
	* Sets the status by user ID of this link.
	*
	* @param statusByUserId the status by user ID of this link
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_link.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this link.
	*
	* @param statusByUserName the status by user name of this link
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_link.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this link.
	*
	* @param statusByUserUuid the status by user uuid of this link
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_link.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this link.
	*
	* @param statusDate the status date of this link
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_link.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this link.
	*
	* @param title the title of this link
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_link.setTitle(title);
	}

	/**
	* Sets the localized title of this link in the language.
	*
	* @param title the localized title of this link
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_link.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this link in the language, and sets the default locale.
	*
	* @param title the localized title of this link
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_link.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_link.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this link from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this link
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_link.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this link from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this link
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_link.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the url of this link.
	*
	* @param URL the url of this link
	*/
	@Override
	public void setURL(java.lang.String URL) {
		_link.setURL(URL);
	}

	/**
	* Sets the localized url of this link in the language.
	*
	* @param URL the localized url of this link
	* @param locale the locale of the language
	*/
	@Override
	public void setURL(java.lang.String URL, java.util.Locale locale) {
		_link.setURL(URL, locale);
	}

	/**
	* Sets the localized url of this link in the language, and sets the default locale.
	*
	* @param URL the localized url of this link
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setURL(java.lang.String URL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_link.setURL(URL, locale, defaultLocale);
	}

	@Override
	public void setURLCurrentLanguageId(java.lang.String languageId) {
		_link.setURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized urls of this link from the map of locales and localized urls.
	*
	* @param URLMap the locales and localized urls of this link
	*/
	@Override
	public void setURLMap(Map<java.util.Locale, java.lang.String> URLMap) {
		_link.setURLMap(URLMap);
	}

	/**
	* Sets the localized urls of this link from the map of locales and localized urls, and sets the default locale.
	*
	* @param URLMap the locales and localized urls of this link
	* @param defaultLocale the default locale
	*/
	@Override
	public void setURLMap(Map<java.util.Locale, java.lang.String> URLMap,
		java.util.Locale defaultLocale) {
		_link.setURLMap(URLMap, defaultLocale);
	}

	/**
	* Sets the user ID of this link.
	*
	* @param userId the user ID of this link
	*/
	@Override
	public void setUserId(long userId) {
		_link.setUserId(userId);
	}

	/**
	* Sets the user name of this link.
	*
	* @param userName the user name of this link
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_link.setUserName(userName);
	}

	/**
	* Sets the user uuid of this link.
	*
	* @param userUuid the user uuid of this link
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_link.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this link.
	*
	* @param uuid the uuid of this link
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_link.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LinkWrapper)) {
			return false;
		}

		LinkWrapper linkWrapper = (LinkWrapper)obj;

		if (Objects.equals(_link, linkWrapper._link)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _link.getStagedModelType();
	}

	@Override
	public Link getWrappedModel() {
		return _link;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _link.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _link.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_link.resetOriginalValues();
	}

	private final Link _link;
}
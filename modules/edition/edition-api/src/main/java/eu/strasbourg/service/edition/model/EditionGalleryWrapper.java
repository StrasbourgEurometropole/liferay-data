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

package eu.strasbourg.service.edition.model;

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
 * This class is a wrapper for {@link EditionGallery}.
 * </p>
 *
 * @author BenjaminBini
 * @see EditionGallery
 * @generated
 */
@ProviderType
public class EditionGalleryWrapper implements EditionGallery,
	ModelWrapper<EditionGallery> {
	public EditionGalleryWrapper(EditionGallery editionGallery) {
		_editionGallery = editionGallery;
	}

	@Override
	public Class<?> getModelClass() {
		return EditionGallery.class;
	}

	@Override
	public String getModelClassName() {
		return EditionGallery.class.getName();
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
		attributes.put("imageId", getImageId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("status", getStatus());

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

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the status of this edition gallery.
	*
	* @return the status of this edition gallery
	*/
	@Override
	public boolean getStatus() {
		return _editionGallery.getStatus();
	}

	/**
	* Returns <code>true</code> if this edition gallery is approved.
	*
	* @return <code>true</code> if this edition gallery is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _editionGallery.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _editionGallery.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this edition gallery is denied.
	*
	* @return <code>true</code> if this edition gallery is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _editionGallery.isDenied();
	}

	/**
	* Returns <code>true</code> if this edition gallery is a draft.
	*
	* @return <code>true</code> if this edition gallery is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _editionGallery.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _editionGallery.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this edition gallery is expired.
	*
	* @return <code>true</code> if this edition gallery is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _editionGallery.isExpired();
	}

	/**
	* Returns <code>true</code> if this edition gallery is inactive.
	*
	* @return <code>true</code> if this edition gallery is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _editionGallery.isInactive();
	}

	/**
	* Returns <code>true</code> if this edition gallery is incomplete.
	*
	* @return <code>true</code> if this edition gallery is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _editionGallery.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _editionGallery.isNew();
	}

	/**
	* Returns <code>true</code> if this edition gallery is pending.
	*
	* @return <code>true</code> if this edition gallery is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _editionGallery.isPending();
	}

	/**
	* Returns <code>true</code> if this edition gallery is scheduled.
	*
	* @return <code>true</code> if this edition gallery is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _editionGallery.isScheduled();
	}

	/**
	* Returns <code>true</code> if this edition gallery is status.
	*
	* @return <code>true</code> if this edition gallery is status; <code>false</code> otherwise
	*/
	@Override
	public boolean isStatus() {
		return _editionGallery.isStatus();
	}

	/**
	* Retourne l'AssetEntry rattach�e � cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _editionGallery.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _editionGallery.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.edition.model.EditionGallery> toCacheModel() {
		return _editionGallery.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.edition.model.EditionGallery toEscapedModel() {
		return new EditionGalleryWrapper(_editionGallery.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.edition.model.EditionGallery toUnescapedModel() {
		return new EditionGalleryWrapper(_editionGallery.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		return _editionGallery.compareTo(editionGallery);
	}

	/**
	* Returns the status of this edition gallery.
	*
	* @return the status of this edition gallery
	*/
	@Override
	public int getStatus() {
		return _editionGallery.getStatus();
	}

	@Override
	public int hashCode() {
		return _editionGallery.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _editionGallery.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this edition gallery.
	*
	* @return the image ID of this edition gallery
	*/
	@Override
	public java.lang.Long getImageId() {
		return _editionGallery.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new EditionGalleryWrapper((EditionGallery)_editionGallery.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _editionGallery.getDefaultLanguageId();
	}

	/**
	* Returns the description of this edition gallery.
	*
	* @return the description of this edition gallery
	*/
	@Override
	public java.lang.String getDescription() {
		return _editionGallery.getDescription();
	}

	/**
	* Returns the localized description of this edition gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this edition gallery
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _editionGallery.getDescription(languageId);
	}

	/**
	* Returns the localized description of this edition gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this edition gallery
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _editionGallery.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this edition gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this edition gallery
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _editionGallery.getDescription(locale);
	}

	/**
	* Returns the localized description of this edition gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this edition gallery. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _editionGallery.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _editionGallery.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _editionGallery.getDescriptionCurrentValue();
	}

	@Override
	public java.lang.String getEditionsIds() {
		return _editionGallery.getEditionsIds();
	}

	/**
	* Renvoie l'URL de l'image � partir de l'id du DLFileEntry
	*
	* @throws PortalException
	* @throws NumberFormatException
	*/
	@Override
	public java.lang.String getImageURL() {
		return _editionGallery.getImageURL();
	}

	/**
	* Returns the status by user name of this edition gallery.
	*
	* @return the status by user name of this edition gallery
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _editionGallery.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this edition gallery.
	*
	* @return the status by user uuid of this edition gallery
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _editionGallery.getStatusByUserUuid();
	}

	/**
	* Returns the title of this edition gallery.
	*
	* @return the title of this edition gallery
	*/
	@Override
	public java.lang.String getTitle() {
		return _editionGallery.getTitle();
	}

	/**
	* Returns the localized title of this edition gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this edition gallery
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _editionGallery.getTitle(languageId);
	}

	/**
	* Returns the localized title of this edition gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this edition gallery
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _editionGallery.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this edition gallery in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this edition gallery
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _editionGallery.getTitle(locale);
	}

	/**
	* Returns the localized title of this edition gallery in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this edition gallery. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _editionGallery.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _editionGallery.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _editionGallery.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this edition gallery.
	*
	* @return the user name of this edition gallery
	*/
	@Override
	public java.lang.String getUserName() {
		return _editionGallery.getUserName();
	}

	/**
	* Returns the user uuid of this edition gallery.
	*
	* @return the user uuid of this edition gallery
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _editionGallery.getUserUuid();
	}

	/**
	* Returns the uuid of this edition gallery.
	*
	* @return the uuid of this edition gallery
	*/
	@Override
	public java.lang.String getUuid() {
		return _editionGallery.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _editionGallery.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _editionGallery.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _editionGallery.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this edition gallery.
	*
	* @return the create date of this edition gallery
	*/
	@Override
	public Date getCreateDate() {
		return _editionGallery.getCreateDate();
	}

	/**
	* Returns the last publish date of this edition gallery.
	*
	* @return the last publish date of this edition gallery
	*/
	@Override
	public Date getLastPublishDate() {
		return _editionGallery.getLastPublishDate();
	}

	/**
	* Returns the modified date of this edition gallery.
	*
	* @return the modified date of this edition gallery
	*/
	@Override
	public Date getModifiedDate() {
		return _editionGallery.getModifiedDate();
	}

	/**
	* Returns the publication date of this edition gallery.
	*
	* @return the publication date of this edition gallery
	*/
	@Override
	public Date getPublicationDate() {
		return _editionGallery.getPublicationDate();
	}

	/**
	* Returns the status date of this edition gallery.
	*
	* @return the status date of this edition gallery
	*/
	@Override
	public Date getStatusDate() {
		return _editionGallery.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattach�es � cet item (via l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _editionGallery.getCategories();
	}

	@Override
	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditions() {
		return _editionGallery.getEditions();
	}

	/**
	* Returns a map of the locales and localized descriptions of this edition gallery.
	*
	* @return the locales and localized descriptions of this edition gallery
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _editionGallery.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this edition gallery.
	*
	* @return the locales and localized titles of this edition gallery
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _editionGallery.getTitleMap();
	}

	/**
	* Returns the company ID of this edition gallery.
	*
	* @return the company ID of this edition gallery
	*/
	@Override
	public long getCompanyId() {
		return _editionGallery.getCompanyId();
	}

	/**
	* Returns the gallery ID of this edition gallery.
	*
	* @return the gallery ID of this edition gallery
	*/
	@Override
	public long getGalleryId() {
		return _editionGallery.getGalleryId();
	}

	/**
	* Returns the group ID of this edition gallery.
	*
	* @return the group ID of this edition gallery
	*/
	@Override
	public long getGroupId() {
		return _editionGallery.getGroupId();
	}

	/**
	* Returns the primary key of this edition gallery.
	*
	* @return the primary key of this edition gallery
	*/
	@Override
	public long getPrimaryKey() {
		return _editionGallery.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this edition gallery.
	*
	* @return the status by user ID of this edition gallery
	*/
	@Override
	public long getStatusByUserId() {
		return _editionGallery.getStatusByUserId();
	}

	/**
	* Returns the user ID of this edition gallery.
	*
	* @return the user ID of this edition gallery
	*/
	@Override
	public long getUserId() {
		return _editionGallery.getUserId();
	}

	@Override
	public void persist() {
		_editionGallery.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_editionGallery.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_editionGallery.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_editionGallery.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this edition gallery.
	*
	* @param companyId the company ID of this edition gallery
	*/
	@Override
	public void setCompanyId(long companyId) {
		_editionGallery.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this edition gallery.
	*
	* @param createDate the create date of this edition gallery
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_editionGallery.setCreateDate(createDate);
	}

	/**
	* Sets the description of this edition gallery.
	*
	* @param description the description of this edition gallery
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_editionGallery.setDescription(description);
	}

	/**
	* Sets the localized description of this edition gallery in the language.
	*
	* @param description the localized description of this edition gallery
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_editionGallery.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this edition gallery in the language, and sets the default locale.
	*
	* @param description the localized description of this edition gallery
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_editionGallery.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_editionGallery.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this edition gallery from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this edition gallery
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_editionGallery.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this edition gallery from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this edition gallery
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_editionGallery.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_editionGallery.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_editionGallery.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_editionGallery.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gallery ID of this edition gallery.
	*
	* @param galleryId the gallery ID of this edition gallery
	*/
	@Override
	public void setGalleryId(long galleryId) {
		_editionGallery.setGalleryId(galleryId);
	}

	/**
	* Sets the group ID of this edition gallery.
	*
	* @param groupId the group ID of this edition gallery
	*/
	@Override
	public void setGroupId(long groupId) {
		_editionGallery.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this edition gallery.
	*
	* @param imageId the image ID of this edition gallery
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_editionGallery.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this edition gallery.
	*
	* @param lastPublishDate the last publish date of this edition gallery
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_editionGallery.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this edition gallery.
	*
	* @param modifiedDate the modified date of this edition gallery
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_editionGallery.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_editionGallery.setNew(n);
	}

	/**
	* Sets the primary key of this edition gallery.
	*
	* @param primaryKey the primary key of this edition gallery
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_editionGallery.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_editionGallery.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publication date of this edition gallery.
	*
	* @param publicationDate the publication date of this edition gallery
	*/
	@Override
	public void setPublicationDate(Date publicationDate) {
		_editionGallery.setPublicationDate(publicationDate);
	}

	/**
	* Sets whether this edition gallery is status.
	*
	* @param status the status of this edition gallery
	*/
	@Override
	public void setStatus(boolean status) {
		_editionGallery.setStatus(status);
	}

	/**
	* Sets the status of this edition gallery.
	*
	* @param status the status of this edition gallery
	*/
	@Override
	public void setStatus(int status) {
		_editionGallery.setStatus(status);
	}

	/**
	* Sets the status by user ID of this edition gallery.
	*
	* @param statusByUserId the status by user ID of this edition gallery
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_editionGallery.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this edition gallery.
	*
	* @param statusByUserName the status by user name of this edition gallery
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_editionGallery.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this edition gallery.
	*
	* @param statusByUserUuid the status by user uuid of this edition gallery
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_editionGallery.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this edition gallery.
	*
	* @param statusDate the status date of this edition gallery
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_editionGallery.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this edition gallery.
	*
	* @param title the title of this edition gallery
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_editionGallery.setTitle(title);
	}

	/**
	* Sets the localized title of this edition gallery in the language.
	*
	* @param title the localized title of this edition gallery
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_editionGallery.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this edition gallery in the language, and sets the default locale.
	*
	* @param title the localized title of this edition gallery
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_editionGallery.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_editionGallery.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this edition gallery from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this edition gallery
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_editionGallery.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this edition gallery from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this edition gallery
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_editionGallery.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this edition gallery.
	*
	* @param userId the user ID of this edition gallery
	*/
	@Override
	public void setUserId(long userId) {
		_editionGallery.setUserId(userId);
	}

	/**
	* Sets the user name of this edition gallery.
	*
	* @param userName the user name of this edition gallery
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_editionGallery.setUserName(userName);
	}

	/**
	* Sets the user uuid of this edition gallery.
	*
	* @param userUuid the user uuid of this edition gallery
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_editionGallery.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this edition gallery.
	*
	* @param uuid the uuid of this edition gallery
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_editionGallery.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EditionGalleryWrapper)) {
			return false;
		}

		EditionGalleryWrapper editionGalleryWrapper = (EditionGalleryWrapper)obj;

		if (Objects.equals(_editionGallery,
					editionGalleryWrapper._editionGallery)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _editionGallery.getStagedModelType();
	}

	@Override
	public EditionGallery getWrappedModel() {
		return _editionGallery;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _editionGallery.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _editionGallery.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_editionGallery.resetOriginalValues();
	}

	private final EditionGallery _editionGallery;
}
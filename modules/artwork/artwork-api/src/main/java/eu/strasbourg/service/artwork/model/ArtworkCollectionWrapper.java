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

package eu.strasbourg.service.artwork.model;

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
 * This class is a wrapper for {@link ArtworkCollection}.
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkCollection
 * @generated
 */
@ProviderType
public class ArtworkCollectionWrapper implements ArtworkCollection,
	ModelWrapper<ArtworkCollection> {
	public ArtworkCollectionWrapper(ArtworkCollection artworkCollection) {
		_artworkCollection = artworkCollection;
	}

	@Override
	public Class<?> getModelClass() {
		return ArtworkCollection.class;
	}

	@Override
	public String getModelClassName() {
		return ArtworkCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("collectionId", getCollectionId());
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
		attributes.put("contributors", getContributors());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long collectionId = (Long)attributes.get("collectionId");

		if (collectionId != null) {
			setCollectionId(collectionId);
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

		String contributors = (String)attributes.get("contributors");

		if (contributors != null) {
			setContributors(contributors);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Returns <code>true</code> if this artwork collection is approved.
	*
	* @return <code>true</code> if this artwork collection is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _artworkCollection.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _artworkCollection.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this artwork collection is denied.
	*
	* @return <code>true</code> if this artwork collection is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _artworkCollection.isDenied();
	}

	/**
	* Returns <code>true</code> if this artwork collection is a draft.
	*
	* @return <code>true</code> if this artwork collection is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _artworkCollection.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _artworkCollection.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this artwork collection is expired.
	*
	* @return <code>true</code> if this artwork collection is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _artworkCollection.isExpired();
	}

	/**
	* Returns <code>true</code> if this artwork collection is inactive.
	*
	* @return <code>true</code> if this artwork collection is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _artworkCollection.isInactive();
	}

	/**
	* Returns <code>true</code> if this artwork collection is incomplete.
	*
	* @return <code>true</code> if this artwork collection is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _artworkCollection.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _artworkCollection.isNew();
	}

	/**
	* Returns <code>true</code> if this artwork collection is pending.
	*
	* @return <code>true</code> if this artwork collection is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _artworkCollection.isPending();
	}

	/**
	* Returns <code>true</code> if this artwork collection is scheduled.
	*
	* @return <code>true</code> if this artwork collection is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _artworkCollection.isScheduled();
	}

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _artworkCollection.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _artworkCollection.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.artwork.model.ArtworkCollection> toCacheModel() {
		return _artworkCollection.toCacheModel();
	}

	/**
	* Retourne la version live de la collection, si elle existe
	*/
	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection getLiveVersion() {
		return _artworkCollection.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection toEscapedModel() {
		return new ArtworkCollectionWrapper(_artworkCollection.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.artwork.model.ArtworkCollection toUnescapedModel() {
		return new ArtworkCollectionWrapper(_artworkCollection.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		return _artworkCollection.compareTo(artworkCollection);
	}

	/**
	* Returns the status of this artwork collection.
	*
	* @return the status of this artwork collection
	*/
	@Override
	public int getStatus() {
		return _artworkCollection.getStatus();
	}

	@Override
	public int hashCode() {
		return _artworkCollection.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _artworkCollection.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this artwork collection.
	*
	* @return the image ID of this artwork collection
	*/
	@Override
	public java.lang.Long getImageId() {
		return _artworkCollection.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new ArtworkCollectionWrapper((ArtworkCollection)_artworkCollection.clone());
	}

	/**
	* Retourne la liste des ids d'oeuvres sous forme de String
	*/
	@Override
	public java.lang.String getArtworksIds() {
		return _artworkCollection.getArtworksIds();
	}

	/**
	* Returns the contributors of this artwork collection.
	*
	* @return the contributors of this artwork collection
	*/
	@Override
	public java.lang.String getContributors() {
		return _artworkCollection.getContributors();
	}

	/**
	* Returns the localized contributors of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized contributors of this artwork collection
	*/
	@Override
	public java.lang.String getContributors(java.lang.String languageId) {
		return _artworkCollection.getContributors(languageId);
	}

	/**
	* Returns the localized contributors of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized contributors of this artwork collection
	*/
	@Override
	public java.lang.String getContributors(java.lang.String languageId,
		boolean useDefault) {
		return _artworkCollection.getContributors(languageId, useDefault);
	}

	/**
	* Returns the localized contributors of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized contributors of this artwork collection
	*/
	@Override
	public java.lang.String getContributors(java.util.Locale locale) {
		return _artworkCollection.getContributors(locale);
	}

	/**
	* Returns the localized contributors of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized contributors of this artwork collection. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getContributors(java.util.Locale locale,
		boolean useDefault) {
		return _artworkCollection.getContributors(locale, useDefault);
	}

	@Override
	public java.lang.String getContributorsCurrentLanguageId() {
		return _artworkCollection.getContributorsCurrentLanguageId();
	}

	@Override
	public java.lang.String getContributorsCurrentValue() {
		return _artworkCollection.getContributorsCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _artworkCollection.getDefaultLanguageId();
	}

	/**
	* Returns the description of this artwork collection.
	*
	* @return the description of this artwork collection
	*/
	@Override
	public java.lang.String getDescription() {
		return _artworkCollection.getDescription();
	}

	/**
	* Returns the localized description of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this artwork collection
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _artworkCollection.getDescription(languageId);
	}

	/**
	* Returns the localized description of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this artwork collection
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _artworkCollection.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this artwork collection
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _artworkCollection.getDescription(locale);
	}

	/**
	* Returns the localized description of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this artwork collection. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _artworkCollection.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _artworkCollection.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _artworkCollection.getDescriptionCurrentValue();
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _artworkCollection.getImageCopyright(locale);
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _artworkCollection.getImageURL();
	}

	/**
	* Retourne la classe css correspondante à la source
	*/
	@Override
	public java.lang.String getSourceCSSClass() {
		return _artworkCollection.getSourceCSSClass();
	}

	/**
	* Returns the status by user name of this artwork collection.
	*
	* @return the status by user name of this artwork collection
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _artworkCollection.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this artwork collection.
	*
	* @return the status by user uuid of this artwork collection
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _artworkCollection.getStatusByUserUuid();
	}

	/**
	* Returns the title of this artwork collection.
	*
	* @return the title of this artwork collection
	*/
	@Override
	public java.lang.String getTitle() {
		return _artworkCollection.getTitle();
	}

	/**
	* Returns the localized title of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this artwork collection
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _artworkCollection.getTitle(languageId);
	}

	/**
	* Returns the localized title of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this artwork collection
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _artworkCollection.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this artwork collection in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this artwork collection
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _artworkCollection.getTitle(locale);
	}

	/**
	* Returns the localized title of this artwork collection in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this artwork collection. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _artworkCollection.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _artworkCollection.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _artworkCollection.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this artwork collection.
	*
	* @return the user name of this artwork collection
	*/
	@Override
	public java.lang.String getUserName() {
		return _artworkCollection.getUserName();
	}

	/**
	* Returns the user uuid of this artwork collection.
	*
	* @return the user uuid of this artwork collection
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _artworkCollection.getUserUuid();
	}

	/**
	* Returns the uuid of this artwork collection.
	*
	* @return the uuid of this artwork collection
	*/
	@Override
	public java.lang.String getUuid() {
		return _artworkCollection.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _artworkCollection.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _artworkCollection.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _artworkCollection.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this artwork collection.
	*
	* @return the create date of this artwork collection
	*/
	@Override
	public Date getCreateDate() {
		return _artworkCollection.getCreateDate();
	}

	/**
	* Returns the last publish date of this artwork collection.
	*
	* @return the last publish date of this artwork collection
	*/
	@Override
	public Date getLastPublishDate() {
		return _artworkCollection.getLastPublishDate();
	}

	/**
	* Returns the modified date of this artwork collection.
	*
	* @return the modified date of this artwork collection
	*/
	@Override
	public Date getModifiedDate() {
		return _artworkCollection.getModifiedDate();
	}

	/**
	* Returns the status date of this artwork collection.
	*
	* @return the status date of this artwork collection
	*/
	@Override
	public Date getStatusDate() {
		return _artworkCollection.getStatusDate();
	}

	/**
	* Retourne la liste des oeuvres
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworks() {
		return _artworkCollection.getArtworks();
	}

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via
	* l'AssetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _artworkCollection.getCategories();
	}

	/**
	* Retourne la liste des oeuvres publiées
	*/
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getPublishedArtworks() {
		return _artworkCollection.getPublishedArtworks();
	}

	/**
	* Retourne les sources de la collection
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getSources() {
		return _artworkCollection.getSources();
	}

	/**
	* Returns a map of the locales and localized contributorses of this artwork collection.
	*
	* @return the locales and localized contributorses of this artwork collection
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getContributorsMap() {
		return _artworkCollection.getContributorsMap();
	}

	/**
	* Returns a map of the locales and localized descriptions of this artwork collection.
	*
	* @return the locales and localized descriptions of this artwork collection
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _artworkCollection.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this artwork collection.
	*
	* @return the locales and localized titles of this artwork collection
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _artworkCollection.getTitleMap();
	}

	/**
	* Returns the collection ID of this artwork collection.
	*
	* @return the collection ID of this artwork collection
	*/
	@Override
	public long getCollectionId() {
		return _artworkCollection.getCollectionId();
	}

	/**
	* Returns the company ID of this artwork collection.
	*
	* @return the company ID of this artwork collection
	*/
	@Override
	public long getCompanyId() {
		return _artworkCollection.getCompanyId();
	}

	/**
	* Returns the group ID of this artwork collection.
	*
	* @return the group ID of this artwork collection
	*/
	@Override
	public long getGroupId() {
		return _artworkCollection.getGroupId();
	}

	/**
	* Returns the primary key of this artwork collection.
	*
	* @return the primary key of this artwork collection
	*/
	@Override
	public long getPrimaryKey() {
		return _artworkCollection.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this artwork collection.
	*
	* @return the status by user ID of this artwork collection
	*/
	@Override
	public long getStatusByUserId() {
		return _artworkCollection.getStatusByUserId();
	}

	/**
	* Returns the user ID of this artwork collection.
	*
	* @return the user ID of this artwork collection
	*/
	@Override
	public long getUserId() {
		return _artworkCollection.getUserId();
	}

	@Override
	public void persist() {
		_artworkCollection.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_artworkCollection.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_artworkCollection.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_artworkCollection.setCachedModel(cachedModel);
	}

	/**
	* Sets the collection ID of this artwork collection.
	*
	* @param collectionId the collection ID of this artwork collection
	*/
	@Override
	public void setCollectionId(long collectionId) {
		_artworkCollection.setCollectionId(collectionId);
	}

	/**
	* Sets the company ID of this artwork collection.
	*
	* @param companyId the company ID of this artwork collection
	*/
	@Override
	public void setCompanyId(long companyId) {
		_artworkCollection.setCompanyId(companyId);
	}

	/**
	* Sets the contributors of this artwork collection.
	*
	* @param contributors the contributors of this artwork collection
	*/
	@Override
	public void setContributors(java.lang.String contributors) {
		_artworkCollection.setContributors(contributors);
	}

	/**
	* Sets the localized contributors of this artwork collection in the language.
	*
	* @param contributors the localized contributors of this artwork collection
	* @param locale the locale of the language
	*/
	@Override
	public void setContributors(java.lang.String contributors,
		java.util.Locale locale) {
		_artworkCollection.setContributors(contributors, locale);
	}

	/**
	* Sets the localized contributors of this artwork collection in the language, and sets the default locale.
	*
	* @param contributors the localized contributors of this artwork collection
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContributors(java.lang.String contributors,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artworkCollection.setContributors(contributors, locale, defaultLocale);
	}

	@Override
	public void setContributorsCurrentLanguageId(java.lang.String languageId) {
		_artworkCollection.setContributorsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized contributorses of this artwork collection from the map of locales and localized contributorses.
	*
	* @param contributorsMap the locales and localized contributorses of this artwork collection
	*/
	@Override
	public void setContributorsMap(
		Map<java.util.Locale, java.lang.String> contributorsMap) {
		_artworkCollection.setContributorsMap(contributorsMap);
	}

	/**
	* Sets the localized contributorses of this artwork collection from the map of locales and localized contributorses, and sets the default locale.
	*
	* @param contributorsMap the locales and localized contributorses of this artwork collection
	* @param defaultLocale the default locale
	*/
	@Override
	public void setContributorsMap(
		Map<java.util.Locale, java.lang.String> contributorsMap,
		java.util.Locale defaultLocale) {
		_artworkCollection.setContributorsMap(contributorsMap, defaultLocale);
	}

	/**
	* Sets the create date of this artwork collection.
	*
	* @param createDate the create date of this artwork collection
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_artworkCollection.setCreateDate(createDate);
	}

	/**
	* Sets the description of this artwork collection.
	*
	* @param description the description of this artwork collection
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_artworkCollection.setDescription(description);
	}

	/**
	* Sets the localized description of this artwork collection in the language.
	*
	* @param description the localized description of this artwork collection
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_artworkCollection.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this artwork collection in the language, and sets the default locale.
	*
	* @param description the localized description of this artwork collection
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artworkCollection.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_artworkCollection.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this artwork collection from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this artwork collection
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_artworkCollection.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this artwork collection from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this artwork collection
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_artworkCollection.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_artworkCollection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_artworkCollection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_artworkCollection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this artwork collection.
	*
	* @param groupId the group ID of this artwork collection
	*/
	@Override
	public void setGroupId(long groupId) {
		_artworkCollection.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this artwork collection.
	*
	* @param imageId the image ID of this artwork collection
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_artworkCollection.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this artwork collection.
	*
	* @param lastPublishDate the last publish date of this artwork collection
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_artworkCollection.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this artwork collection.
	*
	* @param modifiedDate the modified date of this artwork collection
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_artworkCollection.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_artworkCollection.setNew(n);
	}

	/**
	* Sets the primary key of this artwork collection.
	*
	* @param primaryKey the primary key of this artwork collection
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_artworkCollection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_artworkCollection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this artwork collection.
	*
	* @param status the status of this artwork collection
	*/
	@Override
	public void setStatus(int status) {
		_artworkCollection.setStatus(status);
	}

	/**
	* Sets the status by user ID of this artwork collection.
	*
	* @param statusByUserId the status by user ID of this artwork collection
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_artworkCollection.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this artwork collection.
	*
	* @param statusByUserName the status by user name of this artwork collection
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_artworkCollection.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this artwork collection.
	*
	* @param statusByUserUuid the status by user uuid of this artwork collection
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_artworkCollection.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this artwork collection.
	*
	* @param statusDate the status date of this artwork collection
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_artworkCollection.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this artwork collection.
	*
	* @param title the title of this artwork collection
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_artworkCollection.setTitle(title);
	}

	/**
	* Sets the localized title of this artwork collection in the language.
	*
	* @param title the localized title of this artwork collection
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_artworkCollection.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this artwork collection in the language, and sets the default locale.
	*
	* @param title the localized title of this artwork collection
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_artworkCollection.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_artworkCollection.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this artwork collection from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this artwork collection
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_artworkCollection.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this artwork collection from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this artwork collection
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_artworkCollection.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this artwork collection.
	*
	* @param userId the user ID of this artwork collection
	*/
	@Override
	public void setUserId(long userId) {
		_artworkCollection.setUserId(userId);
	}

	/**
	* Sets the user name of this artwork collection.
	*
	* @param userName the user name of this artwork collection
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_artworkCollection.setUserName(userName);
	}

	/**
	* Sets the user uuid of this artwork collection.
	*
	* @param userUuid the user uuid of this artwork collection
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_artworkCollection.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this artwork collection.
	*
	* @param uuid the uuid of this artwork collection
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_artworkCollection.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtworkCollectionWrapper)) {
			return false;
		}

		ArtworkCollectionWrapper artworkCollectionWrapper = (ArtworkCollectionWrapper)obj;

		if (Objects.equals(_artworkCollection,
					artworkCollectionWrapper._artworkCollection)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _artworkCollection.getStagedModelType();
	}

	@Override
	public ArtworkCollection getWrappedModel() {
		return _artworkCollection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _artworkCollection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _artworkCollection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_artworkCollection.resetOriginalValues();
	}

	private final ArtworkCollection _artworkCollection;
}
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

package eu.strasbourg.service.agenda.model;

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
 * This class is a wrapper for {@link Manifestation}.
 * </p>
 *
 * @author BenjaminBini
 * @see Manifestation
 * @generated
 */
@ProviderType
public class ManifestationWrapper implements Manifestation,
	ModelWrapper<Manifestation> {
	public ManifestationWrapper(Manifestation manifestation) {
		_manifestation = manifestation;
	}

	@Override
	public Class<?> getModelClass() {
		return Manifestation.class;
	}

	@Override
	public String getModelClassName() {
		return Manifestation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("manifestationId", getManifestationId());
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
		attributes.put("externalImageURL", getExternalImageURL());
		attributes.put("externalImageCopyright", getExternalImageCopyright());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("source", getSource());
		attributes.put("idSource", getIdSource());
		attributes.put("publicationDate", getPublicationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long manifestationId = (Long)attributes.get("manifestationId");

		if (manifestationId != null) {
			setManifestationId(manifestationId);
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

		String externalImageURL = (String)attributes.get("externalImageURL");

		if (externalImageURL != null) {
			setExternalImageURL(externalImageURL);
		}

		String externalImageCopyright = (String)attributes.get(
				"externalImageCopyright");

		if (externalImageCopyright != null) {
			setExternalImageCopyright(externalImageCopyright);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String idSource = (String)attributes.get("idSource");

		if (idSource != null) {
			setIdSource(idSource);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}
	}

	/**
	* Returns <code>true</code> if this manifestation is approved.
	*
	* @return <code>true</code> if this manifestation is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _manifestation.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _manifestation.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this manifestation is denied.
	*
	* @return <code>true</code> if this manifestation is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _manifestation.isDenied();
	}

	/**
	* Returns <code>true</code> if this manifestation is a draft.
	*
	* @return <code>true</code> if this manifestation is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _manifestation.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _manifestation.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this manifestation is expired.
	*
	* @return <code>true</code> if this manifestation is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _manifestation.isExpired();
	}

	/**
	* Returns <code>true</code> if this manifestation is inactive.
	*
	* @return <code>true</code> if this manifestation is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _manifestation.isInactive();
	}

	/**
	* Returns <code>true</code> if this manifestation is incomplete.
	*
	* @return <code>true</code> if this manifestation is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _manifestation.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _manifestation.isNew();
	}

	/**
	* Returns <code>true</code> if this manifestation is pending.
	*
	* @return <code>true</code> if this manifestation is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _manifestation.isPending();
	}

	/**
	* Returns <code>true</code> if this manifestation is scheduled.
	*
	* @return <code>true</code> if this manifestation is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _manifestation.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattachée cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _manifestation.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _manifestation.getExpandoBridge();
	}

	/**
	* Renvoie la version JSON de la manifestation
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _manifestation.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.Manifestation> toCacheModel() {
		return _manifestation.toCacheModel();
	}

	/**
	* Renvoie la version live de la manifestation, si elle existe
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Manifestation getLiveVersion() {
		return _manifestation.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.agenda.model.Manifestation toEscapedModel() {
		return new ManifestationWrapper(_manifestation.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.Manifestation toUnescapedModel() {
		return new ManifestationWrapper(_manifestation.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		return _manifestation.compareTo(manifestation);
	}

	/**
	* Returns the status of this manifestation.
	*
	* @return the status of this manifestation
	*/
	@Override
	public int getStatus() {
		return _manifestation.getStatus();
	}

	@Override
	public int hashCode() {
		return _manifestation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _manifestation.getPrimaryKeyObj();
	}

	/**
	* Returns the image ID of this manifestation.
	*
	* @return the image ID of this manifestation
	*/
	@Override
	public java.lang.Long getImageId() {
		return _manifestation.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new ManifestationWrapper((Manifestation)_manifestation.clone());
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _manifestation.getDefaultLanguageId();
	}

	/**
	* Returns the description of this manifestation.
	*
	* @return the description of this manifestation
	*/
	@Override
	public java.lang.String getDescription() {
		return _manifestation.getDescription();
	}

	/**
	* Returns the localized description of this manifestation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this manifestation
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _manifestation.getDescription(languageId);
	}

	/**
	* Returns the localized description of this manifestation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this manifestation
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _manifestation.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this manifestation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this manifestation
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _manifestation.getDescription(locale);
	}

	/**
	* Returns the localized description of this manifestation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this manifestation. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _manifestation.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _manifestation.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _manifestation.getDescriptionCurrentValue();
	}

	/**
	* Renvoie la liste des ids des éditions de la galerie sous forme de String
	*/
	@Override
	public java.lang.String getEventsIds() {
		return _manifestation.getEventsIds();
	}

	/**
	* Returns the external image copyright of this manifestation.
	*
	* @return the external image copyright of this manifestation
	*/
	@Override
	public java.lang.String getExternalImageCopyright() {
		return _manifestation.getExternalImageCopyright();
	}

	/**
	* Returns the external image u r l of this manifestation.
	*
	* @return the external image u r l of this manifestation
	*/
	@Override
	public java.lang.String getExternalImageURL() {
		return _manifestation.getExternalImageURL();
	}

	/**
	* Returns the id source of this manifestation.
	*
	* @return the id source of this manifestation
	*/
	@Override
	public java.lang.String getIdSource() {
		return _manifestation.getIdSource();
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _manifestation.getImageCopyright(locale);
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _manifestation.getImageURL();
	}

	/**
	* Retourne la période principale de la manifestation sous forme de String
	* dans la locale passée en paramètre
	*/
	@Override
	public java.lang.String getManifestationScheduleDisplay(
		java.util.Locale locale) {
		return _manifestation.getManifestationScheduleDisplay(locale);
	}

	/**
	* Returns the source of this manifestation.
	*
	* @return the source of this manifestation
	*/
	@Override
	public java.lang.String getSource() {
		return _manifestation.getSource();
	}

	/**
	* Returns the status by user name of this manifestation.
	*
	* @return the status by user name of this manifestation
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _manifestation.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this manifestation.
	*
	* @return the status by user uuid of this manifestation
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _manifestation.getStatusByUserUuid();
	}

	/**
	* Returns the title of this manifestation.
	*
	* @return the title of this manifestation
	*/
	@Override
	public java.lang.String getTitle() {
		return _manifestation.getTitle();
	}

	/**
	* Returns the localized title of this manifestation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this manifestation
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _manifestation.getTitle(languageId);
	}

	/**
	* Returns the localized title of this manifestation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this manifestation
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _manifestation.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this manifestation in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this manifestation
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _manifestation.getTitle(locale);
	}

	/**
	* Returns the localized title of this manifestation in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this manifestation. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _manifestation.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _manifestation.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _manifestation.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this manifestation.
	*
	* @return the user name of this manifestation
	*/
	@Override
	public java.lang.String getUserName() {
		return _manifestation.getUserName();
	}

	/**
	* Returns the user uuid of this manifestation.
	*
	* @return the user uuid of this manifestation
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _manifestation.getUserUuid();
	}

	/**
	* Returns the uuid of this manifestation.
	*
	* @return the uuid of this manifestation
	*/
	@Override
	public java.lang.String getUuid() {
		return _manifestation.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _manifestation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _manifestation.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _manifestation.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this manifestation.
	*
	* @return the create date of this manifestation
	*/
	@Override
	public Date getCreateDate() {
		return _manifestation.getCreateDate();
	}

	/**
	* Returns the end date of this manifestation.
	*
	* @return the end date of this manifestation
	*/
	@Override
	public Date getEndDate() {
		return _manifestation.getEndDate();
	}

	/**
	* Returns the last publish date of this manifestation.
	*
	* @return the last publish date of this manifestation
	*/
	@Override
	public Date getLastPublishDate() {
		return _manifestation.getLastPublishDate();
	}

	/**
	* Returns the modified date of this manifestation.
	*
	* @return the modified date of this manifestation
	*/
	@Override
	public Date getModifiedDate() {
		return _manifestation.getModifiedDate();
	}

	/**
	* Returns the publication date of this manifestation.
	*
	* @return the publication date of this manifestation
	*/
	@Override
	public Date getPublicationDate() {
		return _manifestation.getPublicationDate();
	}

	/**
	* Returns the start date of this manifestation.
	*
	* @return the start date of this manifestation
	*/
	@Override
	public Date getStartDate() {
		return _manifestation.getStartDate();
	}

	/**
	* Returns the status date of this manifestation.
	*
	* @return the status date of this manifestation
	*/
	@Override
	public Date getStatusDate() {
		return _manifestation.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _manifestation.getCategories();
	}

	/**
	* Renvoie la liste des éditions de la galerie
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents() {
		return _manifestation.getEvents();
	}

	/**
	* Retourne les publics de la manifestation
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics() {
		return _manifestation.getPublics();
	}

	/**
	* Renvoie la liste des éditions publiées de la galerie
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getPublishedEvents() {
		return _manifestation.getPublishedEvents();
	}

	/**
	* Retourne les territoires de la manifestation
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getServices() {
		return _manifestation.getServices();
	}

	/**
	* Retourne les themes de la manifestation
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getThemes() {
		return _manifestation.getThemes();
	}

	/**
	* Retourne les types de la manifestation
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes() {
		return _manifestation.getTypes();
	}

	/**
	* Returns a map of the locales and localized descriptions of this manifestation.
	*
	* @return the locales and localized descriptions of this manifestation
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _manifestation.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized titles of this manifestation.
	*
	* @return the locales and localized titles of this manifestation
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _manifestation.getTitleMap();
	}

	/**
	* Returns the company ID of this manifestation.
	*
	* @return the company ID of this manifestation
	*/
	@Override
	public long getCompanyId() {
		return _manifestation.getCompanyId();
	}

	/**
	* Returns the group ID of this manifestation.
	*
	* @return the group ID of this manifestation
	*/
	@Override
	public long getGroupId() {
		return _manifestation.getGroupId();
	}

	/**
	* Returns the manifestation ID of this manifestation.
	*
	* @return the manifestation ID of this manifestation
	*/
	@Override
	public long getManifestationId() {
		return _manifestation.getManifestationId();
	}

	/**
	* Returns the primary key of this manifestation.
	*
	* @return the primary key of this manifestation
	*/
	@Override
	public long getPrimaryKey() {
		return _manifestation.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this manifestation.
	*
	* @return the status by user ID of this manifestation
	*/
	@Override
	public long getStatusByUserId() {
		return _manifestation.getStatusByUserId();
	}

	/**
	* Returns the user ID of this manifestation.
	*
	* @return the user ID of this manifestation
	*/
	@Override
	public long getUserId() {
		return _manifestation.getUserId();
	}

	@Override
	public void persist() {
		_manifestation.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_manifestation.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_manifestation.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_manifestation.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this manifestation.
	*
	* @param companyId the company ID of this manifestation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_manifestation.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this manifestation.
	*
	* @param createDate the create date of this manifestation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_manifestation.setCreateDate(createDate);
	}

	/**
	* Sets the description of this manifestation.
	*
	* @param description the description of this manifestation
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_manifestation.setDescription(description);
	}

	/**
	* Sets the localized description of this manifestation in the language.
	*
	* @param description the localized description of this manifestation
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_manifestation.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this manifestation in the language, and sets the default locale.
	*
	* @param description the localized description of this manifestation
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_manifestation.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_manifestation.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this manifestation from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this manifestation
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_manifestation.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this manifestation from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this manifestation
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_manifestation.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the end date of this manifestation.
	*
	* @param endDate the end date of this manifestation
	*/
	@Override
	public void setEndDate(Date endDate) {
		_manifestation.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_manifestation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_manifestation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_manifestation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the external image copyright of this manifestation.
	*
	* @param externalImageCopyright the external image copyright of this manifestation
	*/
	@Override
	public void setExternalImageCopyright(
		java.lang.String externalImageCopyright) {
		_manifestation.setExternalImageCopyright(externalImageCopyright);
	}

	/**
	* Sets the external image u r l of this manifestation.
	*
	* @param externalImageURL the external image u r l of this manifestation
	*/
	@Override
	public void setExternalImageURL(java.lang.String externalImageURL) {
		_manifestation.setExternalImageURL(externalImageURL);
	}

	/**
	* Sets the group ID of this manifestation.
	*
	* @param groupId the group ID of this manifestation
	*/
	@Override
	public void setGroupId(long groupId) {
		_manifestation.setGroupId(groupId);
	}

	/**
	* Sets the id source of this manifestation.
	*
	* @param idSource the id source of this manifestation
	*/
	@Override
	public void setIdSource(java.lang.String idSource) {
		_manifestation.setIdSource(idSource);
	}

	/**
	* Sets the image ID of this manifestation.
	*
	* @param imageId the image ID of this manifestation
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_manifestation.setImageId(imageId);
	}

	/**
	* Sets the last publish date of this manifestation.
	*
	* @param lastPublishDate the last publish date of this manifestation
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_manifestation.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the manifestation ID of this manifestation.
	*
	* @param manifestationId the manifestation ID of this manifestation
	*/
	@Override
	public void setManifestationId(long manifestationId) {
		_manifestation.setManifestationId(manifestationId);
	}

	/**
	* Sets the modified date of this manifestation.
	*
	* @param modifiedDate the modified date of this manifestation
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_manifestation.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_manifestation.setNew(n);
	}

	/**
	* Sets the primary key of this manifestation.
	*
	* @param primaryKey the primary key of this manifestation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_manifestation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_manifestation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publication date of this manifestation.
	*
	* @param publicationDate the publication date of this manifestation
	*/
	@Override
	public void setPublicationDate(Date publicationDate) {
		_manifestation.setPublicationDate(publicationDate);
	}

	/**
	* Sets the source of this manifestation.
	*
	* @param source the source of this manifestation
	*/
	@Override
	public void setSource(java.lang.String source) {
		_manifestation.setSource(source);
	}

	/**
	* Sets the start date of this manifestation.
	*
	* @param startDate the start date of this manifestation
	*/
	@Override
	public void setStartDate(Date startDate) {
		_manifestation.setStartDate(startDate);
	}

	/**
	* Sets the status of this manifestation.
	*
	* @param status the status of this manifestation
	*/
	@Override
	public void setStatus(int status) {
		_manifestation.setStatus(status);
	}

	/**
	* Sets the status by user ID of this manifestation.
	*
	* @param statusByUserId the status by user ID of this manifestation
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_manifestation.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this manifestation.
	*
	* @param statusByUserName the status by user name of this manifestation
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_manifestation.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this manifestation.
	*
	* @param statusByUserUuid the status by user uuid of this manifestation
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_manifestation.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this manifestation.
	*
	* @param statusDate the status date of this manifestation
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_manifestation.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this manifestation.
	*
	* @param title the title of this manifestation
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_manifestation.setTitle(title);
	}

	/**
	* Sets the localized title of this manifestation in the language.
	*
	* @param title the localized title of this manifestation
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_manifestation.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this manifestation in the language, and sets the default locale.
	*
	* @param title the localized title of this manifestation
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_manifestation.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_manifestation.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this manifestation from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this manifestation
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_manifestation.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this manifestation from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this manifestation
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_manifestation.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this manifestation.
	*
	* @param userId the user ID of this manifestation
	*/
	@Override
	public void setUserId(long userId) {
		_manifestation.setUserId(userId);
	}

	/**
	* Sets the user name of this manifestation.
	*
	* @param userName the user name of this manifestation
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_manifestation.setUserName(userName);
	}

	/**
	* Sets the user uuid of this manifestation.
	*
	* @param userUuid the user uuid of this manifestation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_manifestation.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this manifestation.
	*
	* @param uuid the uuid of this manifestation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_manifestation.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ManifestationWrapper)) {
			return false;
		}

		ManifestationWrapper manifestationWrapper = (ManifestationWrapper)obj;

		if (Objects.equals(_manifestation, manifestationWrapper._manifestation)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _manifestation.getStagedModelType();
	}

	@Override
	public Manifestation getWrappedModel() {
		return _manifestation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _manifestation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _manifestation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_manifestation.resetOriginalValues();
	}

	private final Manifestation _manifestation;
}
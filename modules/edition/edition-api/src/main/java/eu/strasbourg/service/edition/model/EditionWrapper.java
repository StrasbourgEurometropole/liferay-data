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
 * This class is a wrapper for {@link Edition}.
 * </p>
 *
 * @author BenjaminBini
 * @see Edition
 * @generated
 */
@ProviderType
public class EditionWrapper implements Edition, ModelWrapper<Edition> {

	public EditionWrapper(Edition edition) {
		_edition = edition;
	}

	@Override
	public Class<?> getModelClass() {
		return Edition.class;
	}

	@Override
	public String getModelClassName() {
		return Edition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("editionId", getEditionId());
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
		attributes.put("subtitle", getSubtitle());
		attributes.put("description", getDescription());
		attributes.put("URL", getURL());
		attributes.put("author", getAuthor());
		attributes.put("editor", getEditor());
		attributes.put("distribution", getDistribution());
		attributes.put("ISBN", getISBN());
		attributes.put("price", getPrice());
		attributes.put("availableForExchange", isAvailableForExchange());
		attributes.put("inStock", isInStock());
		attributes.put("diffusionDateYear", getDiffusionDateYear());
		attributes.put("diffusionDateMonth", getDiffusionDateMonth());
		attributes.put("pageNumber", getPageNumber());
		attributes.put("pictureNumber", getPictureNumber());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("imageId", getImageId());
		attributes.put("fileId", getFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long editionId = (Long)attributes.get("editionId");

		if (editionId != null) {
			setEditionId(editionId);
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

		String subtitle = (String)attributes.get("subtitle");

		if (subtitle != null) {
			setSubtitle(subtitle);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String URL = (String)attributes.get("URL");

		if (URL != null) {
			setURL(URL);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		String editor = (String)attributes.get("editor");

		if (editor != null) {
			setEditor(editor);
		}

		String distribution = (String)attributes.get("distribution");

		if (distribution != null) {
			setDistribution(distribution);
		}

		String ISBN = (String)attributes.get("ISBN");

		if (ISBN != null) {
			setISBN(ISBN);
		}

		String price = (String)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Boolean availableForExchange = (Boolean)attributes.get(
			"availableForExchange");

		if (availableForExchange != null) {
			setAvailableForExchange(availableForExchange);
		}

		Boolean inStock = (Boolean)attributes.get("inStock");

		if (inStock != null) {
			setInStock(inStock);
		}

		String diffusionDateYear = (String)attributes.get("diffusionDateYear");

		if (diffusionDateYear != null) {
			setDiffusionDateYear(diffusionDateYear);
		}

		String diffusionDateMonth = (String)attributes.get(
			"diffusionDateMonth");

		if (diffusionDateMonth != null) {
			setDiffusionDateMonth(diffusionDateMonth);
		}

		String pageNumber = (String)attributes.get("pageNumber");

		if (pageNumber != null) {
			setPageNumber(pageNumber);
		}

		String pictureNumber = (String)attributes.get("pictureNumber");

		if (pictureNumber != null) {
			setPictureNumber(pictureNumber);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String fileId = (String)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}
	}

	@Override
	public Object clone() {
		return new EditionWrapper((Edition)_edition.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.edition.model.Edition edition) {
		return _edition.compareTo(edition);
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _edition.getAssetEntry();
	}

	/**
	 * Returns the author of this edition.
	 *
	 * @return the author of this edition
	 */
	@Override
	public String getAuthor() {
		return _edition.getAuthor();
	}

	/**
	 * Returns the localized author of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized author of this edition
	 */
	@Override
	public String getAuthor(java.util.Locale locale) {
		return _edition.getAuthor(locale);
	}

	/**
	 * Returns the localized author of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized author of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getAuthor(java.util.Locale locale, boolean useDefault) {
		return _edition.getAuthor(locale, useDefault);
	}

	/**
	 * Returns the localized author of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized author of this edition
	 */
	@Override
	public String getAuthor(String languageId) {
		return _edition.getAuthor(languageId);
	}

	/**
	 * Returns the localized author of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized author of this edition
	 */
	@Override
	public String getAuthor(String languageId, boolean useDefault) {
		return _edition.getAuthor(languageId, useDefault);
	}

	@Override
	public String getAuthorCurrentLanguageId() {
		return _edition.getAuthorCurrentLanguageId();
	}

	@Override
	public String getAuthorCurrentValue() {
		return _edition.getAuthorCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized authors of this edition.
	 *
	 * @return the locales and localized authors of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getAuthorMap() {
		return _edition.getAuthorMap();
	}

	/**
	 * Returns the available for exchange of this edition.
	 *
	 * @return the available for exchange of this edition
	 */
	@Override
	public boolean getAvailableForExchange() {
		return _edition.getAvailableForExchange();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _edition.getAvailableLanguageIds();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _edition.getCategories();
	}

	/**
	 * Returns the company ID of this edition.
	 *
	 * @return the company ID of this edition
	 */
	@Override
	public long getCompanyId() {
		return _edition.getCompanyId();
	}

	/**
	 * Returns the create date of this edition.
	 *
	 * @return the create date of this edition
	 */
	@Override
	public Date getCreateDate() {
		return _edition.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _edition.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this edition.
	 *
	 * @return the description of this edition
	 */
	@Override
	public String getDescription() {
		return _edition.getDescription();
	}

	/**
	 * Returns the localized description of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this edition
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _edition.getDescription(locale);
	}

	/**
	 * Returns the localized description of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _edition.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this edition
	 */
	@Override
	public String getDescription(String languageId) {
		return _edition.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this edition
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _edition.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _edition.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _edition.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this edition.
	 *
	 * @return the locales and localized descriptions of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _edition.getDescriptionMap();
	}

	/**
	 * Returns the diffusion date month of this edition.
	 *
	 * @return the diffusion date month of this edition
	 */
	@Override
	public String getDiffusionDateMonth() {
		return _edition.getDiffusionDateMonth();
	}

	/**
	 * Returns the diffusion date year of this edition.
	 *
	 * @return the diffusion date year of this edition
	 */
	@Override
	public String getDiffusionDateYear() {
		return _edition.getDiffusionDateYear();
	}

	/**
	 * Returns the distribution of this edition.
	 *
	 * @return the distribution of this edition
	 */
	@Override
	public String getDistribution() {
		return _edition.getDistribution();
	}

	/**
	 * Renvoie la liste des galleries auxquelles cette édition appartient
	 */
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.EditionGallery>
		getEditionGalleries() {

		return _edition.getEditionGalleries();
	}

	/**
	 * Renvoie la liste des IDs des galleries auxquelles cette édition
	 * appartient sous forme de String
	 */
	@Override
	public String getEditionGalleriesIds() {
		return _edition.getEditionGalleriesIds();
	}

	/**
	 * Returns the edition ID of this edition.
	 *
	 * @return the edition ID of this edition
	 */
	@Override
	public long getEditionId() {
		return _edition.getEditionId();
	}

	/**
	 * Returns the editor of this edition.
	 *
	 * @return the editor of this edition
	 */
	@Override
	public String getEditor() {
		return _edition.getEditor();
	}

	/**
	 * Returns the localized editor of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized editor of this edition
	 */
	@Override
	public String getEditor(java.util.Locale locale) {
		return _edition.getEditor(locale);
	}

	/**
	 * Returns the localized editor of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized editor of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getEditor(java.util.Locale locale, boolean useDefault) {
		return _edition.getEditor(locale, useDefault);
	}

	/**
	 * Returns the localized editor of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized editor of this edition
	 */
	@Override
	public String getEditor(String languageId) {
		return _edition.getEditor(languageId);
	}

	/**
	 * Returns the localized editor of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized editor of this edition
	 */
	@Override
	public String getEditor(String languageId, boolean useDefault) {
		return _edition.getEditor(languageId, useDefault);
	}

	@Override
	public String getEditorCurrentLanguageId() {
		return _edition.getEditorCurrentLanguageId();
	}

	@Override
	public String getEditorCurrentValue() {
		return _edition.getEditorCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized editors of this edition.
	 *
	 * @return the locales and localized editors of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getEditorMap() {
		return _edition.getEditorMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _edition.getExpandoBridge();
	}

	/**
	 * Renvoie l'URL de téléchargement du fichier (que ce soit un FileEntry ou
	 * une URL externe)
	 */
	@Override
	public String getFileDownloadURL(java.util.Locale locale) {
		return _edition.getFileDownloadURL(locale);
	}

	/**
	 * Returns the file ID of this edition.
	 *
	 * @return the file ID of this edition
	 */
	@Override
	public String getFileId() {
		return _edition.getFileId();
	}

	/**
	 * Returns the localized file ID of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized file ID of this edition
	 */
	@Override
	public String getFileId(java.util.Locale locale) {
		return _edition.getFileId(locale);
	}

	/**
	 * Returns the localized file ID of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized file ID of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getFileId(java.util.Locale locale, boolean useDefault) {
		return _edition.getFileId(locale, useDefault);
	}

	/**
	 * Returns the localized file ID of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized file ID of this edition
	 */
	@Override
	public String getFileId(String languageId) {
		return _edition.getFileId(languageId);
	}

	/**
	 * Returns the localized file ID of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized file ID of this edition
	 */
	@Override
	public String getFileId(String languageId, boolean useDefault) {
		return _edition.getFileId(languageId, useDefault);
	}

	@Override
	public String getFileIdCurrentLanguageId() {
		return _edition.getFileIdCurrentLanguageId();
	}

	@Override
	public String getFileIdCurrentValue() {
		return _edition.getFileIdCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized file IDs of this edition.
	 *
	 * @return the locales and localized file IDs of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getFileIdMap() {
		return _edition.getFileIdMap();
	}

	/**
	 * Renvoie la taille du fichier sous forme de String
	 */
	@Override
	public String getFileSize(java.util.Locale locale) {
		return _edition.getFileSize(locale);
	}

	/**
	 * Retourne le nom du fichier si un fichier uploadé est lié à l'édition, le titre de l'édition sinon
	 */
	@Override
	public String getFileTitle(java.util.Locale locale) {
		return _edition.getFileTitle(locale);
	}

	/**
	 * Renovie le type du fichier sous forme de String (si c'est une FileEntry -
	 * renvoie une chaîne vide si c'est une URL externe)
	 */
	@Override
	public String getFileType(java.util.Locale locale) {
		return _edition.getFileType(locale);
	}

	/**
	 * Returns the group ID of this edition.
	 *
	 * @return the group ID of this edition
	 */
	@Override
	public long getGroupId() {
		return _edition.getGroupId();
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(java.util.Locale locale) {
		return _edition.getImageCopyright(locale);
	}

	/**
	 * Returns the image ID of this edition.
	 *
	 * @return the image ID of this edition
	 */
	@Override
	public Long getImageId() {
		return _edition.getImageId();
	}

	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return _edition.getImageURL();
	}

	/**
	 * Returns the in stock of this edition.
	 *
	 * @return the in stock of this edition
	 */
	@Override
	public boolean getInStock() {
		return _edition.getInStock();
	}

	/**
	 * Returns the isbn of this edition.
	 *
	 * @return the isbn of this edition
	 */
	@Override
	public String getISBN() {
		return _edition.getISBN();
	}

	/**
	 * Returns the last publish date of this edition.
	 *
	 * @return the last publish date of this edition
	 */
	@Override
	public Date getLastPublishDate() {
		return _edition.getLastPublishDate();
	}

	/**
	 * Renvoie la version live de l'édition, si elle existe
	 */
	@Override
	public eu.strasbourg.service.edition.model.Edition getLiveVersion() {
		return _edition.getLiveVersion();
	}

	/**
	 * Returns the modified date of this edition.
	 *
	 * @return the modified date of this edition
	 */
	@Override
	public Date getModifiedDate() {
		return _edition.getModifiedDate();
	}

	/**
	 * Returns the page number of this edition.
	 *
	 * @return the page number of this edition
	 */
	@Override
	public String getPageNumber() {
		return _edition.getPageNumber();
	}

	/**
	 * Returns the picture number of this edition.
	 *
	 * @return the picture number of this edition
	 */
	@Override
	public String getPictureNumber() {
		return _edition.getPictureNumber();
	}

	/**
	 * Returns the price of this edition.
	 *
	 * @return the price of this edition
	 */
	@Override
	public String getPrice() {
		return _edition.getPrice();
	}

	/**
	 * Returns the primary key of this edition.
	 *
	 * @return the primary key of this edition
	 */
	@Override
	public long getPrimaryKey() {
		return _edition.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _edition.getPrimaryKeyObj();
	}

	/**
	 * Returns the publication date of this edition.
	 *
	 * @return the publication date of this edition
	 */
	@Override
	public Date getPublicationDate() {
		return _edition.getPublicationDate();
	}

	/**
	 * Renvoie la liste des galeries publiées
	 */
	@Override
	public java.util.List<eu.strasbourg.service.edition.model.EditionGallery>
		getPublishedEditionGalleries() {

		return _edition.getPublishedEditionGalleries();
	}

	/**
	 * Retourne les sources de l'édition
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getSources() {

		return _edition.getSources();
	}

	/**
	 * Returns the status of this edition.
	 *
	 * @return the status of this edition
	 */
	@Override
	public int getStatus() {
		return _edition.getStatus();
	}

	/**
	 * Returns the status by user ID of this edition.
	 *
	 * @return the status by user ID of this edition
	 */
	@Override
	public long getStatusByUserId() {
		return _edition.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this edition.
	 *
	 * @return the status by user name of this edition
	 */
	@Override
	public String getStatusByUserName() {
		return _edition.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this edition.
	 *
	 * @return the status by user uuid of this edition
	 */
	@Override
	public String getStatusByUserUuid() {
		return _edition.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this edition.
	 *
	 * @return the status date of this edition
	 */
	@Override
	public Date getStatusDate() {
		return _edition.getStatusDate();
	}

	/**
	 * Returns the subtitle of this edition.
	 *
	 * @return the subtitle of this edition
	 */
	@Override
	public String getSubtitle() {
		return _edition.getSubtitle();
	}

	/**
	 * Returns the localized subtitle of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subtitle of this edition
	 */
	@Override
	public String getSubtitle(java.util.Locale locale) {
		return _edition.getSubtitle(locale);
	}

	/**
	 * Returns the localized subtitle of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getSubtitle(java.util.Locale locale, boolean useDefault) {
		return _edition.getSubtitle(locale, useDefault);
	}

	/**
	 * Returns the localized subtitle of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subtitle of this edition
	 */
	@Override
	public String getSubtitle(String languageId) {
		return _edition.getSubtitle(languageId);
	}

	/**
	 * Returns the localized subtitle of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this edition
	 */
	@Override
	public String getSubtitle(String languageId, boolean useDefault) {
		return _edition.getSubtitle(languageId, useDefault);
	}

	@Override
	public String getSubtitleCurrentLanguageId() {
		return _edition.getSubtitleCurrentLanguageId();
	}

	@Override
	public String getSubtitleCurrentValue() {
		return _edition.getSubtitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized subtitles of this edition.
	 *
	 * @return the locales and localized subtitles of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getSubtitleMap() {
		return _edition.getSubtitleMap();
	}

	/**
	 * Returns the title of this edition.
	 *
	 * @return the title of this edition
	 */
	@Override
	public String getTitle() {
		return _edition.getTitle();
	}

	/**
	 * Returns the localized title of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this edition
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return _edition.getTitle(locale);
	}

	/**
	 * Returns the localized title of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _edition.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this edition
	 */
	@Override
	public String getTitle(String languageId) {
		return _edition.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this edition
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return _edition.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _edition.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _edition.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this edition.
	 *
	 * @return the locales and localized titles of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _edition.getTitleMap();
	}

	/**
	 * Retourne les types de l'édition
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTypes() {

		return _edition.getTypes();
	}

	/**
	 * Retourne le texte à afficher pour les types de l'édition
	 */
	@Override
	public String getTypesLabels(java.util.Locale locale) {
		return _edition.getTypesLabels(locale);
	}

	/**
	 * Returns the url of this edition.
	 *
	 * @return the url of this edition
	 */
	@Override
	public String getURL() {
		return _edition.getURL();
	}

	/**
	 * Returns the localized url of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized url of this edition
	 */
	@Override
	public String getURL(java.util.Locale locale) {
		return _edition.getURL(locale);
	}

	/**
	 * Returns the localized url of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getURL(java.util.Locale locale, boolean useDefault) {
		return _edition.getURL(locale, useDefault);
	}

	/**
	 * Returns the localized url of this edition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized url of this edition
	 */
	@Override
	public String getURL(String languageId) {
		return _edition.getURL(languageId);
	}

	/**
	 * Returns the localized url of this edition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized url of this edition
	 */
	@Override
	public String getURL(String languageId, boolean useDefault) {
		return _edition.getURL(languageId, useDefault);
	}

	@Override
	public String getURLCurrentLanguageId() {
		return _edition.getURLCurrentLanguageId();
	}

	@Override
	public String getURLCurrentValue() {
		return _edition.getURLCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized urls of this edition.
	 *
	 * @return the locales and localized urls of this edition
	 */
	@Override
	public Map<java.util.Locale, String> getURLMap() {
		return _edition.getURLMap();
	}

	/**
	 * Returns the user ID of this edition.
	 *
	 * @return the user ID of this edition
	 */
	@Override
	public long getUserId() {
		return _edition.getUserId();
	}

	/**
	 * Returns the user name of this edition.
	 *
	 * @return the user name of this edition
	 */
	@Override
	public String getUserName() {
		return _edition.getUserName();
	}

	/**
	 * Returns the user uuid of this edition.
	 *
	 * @return the user uuid of this edition
	 */
	@Override
	public String getUserUuid() {
		return _edition.getUserUuid();
	}

	/**
	 * Returns the uuid of this edition.
	 *
	 * @return the uuid of this edition
	 */
	@Override
	public String getUuid() {
		return _edition.getUuid();
	}

	@Override
	public int hashCode() {
		return _edition.hashCode();
	}

	/**
	 * Returns <code>true</code> if this edition is approved.
	 *
	 * @return <code>true</code> if this edition is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _edition.isApproved();
	}

	/**
	 * Returns <code>true</code> if this edition is available for exchange.
	 *
	 * @return <code>true</code> if this edition is available for exchange; <code>false</code> otherwise
	 */
	@Override
	public boolean isAvailableForExchange() {
		return _edition.isAvailableForExchange();
	}

	@Override
	public boolean isCachedModel() {
		return _edition.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this edition is denied.
	 *
	 * @return <code>true</code> if this edition is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _edition.isDenied();
	}

	/**
	 * Returns <code>true</code> if this edition is a draft.
	 *
	 * @return <code>true</code> if this edition is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _edition.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _edition.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this edition is expired.
	 *
	 * @return <code>true</code> if this edition is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _edition.isExpired();
	}

	/**
	 * Returns <code>true</code> if this edition is inactive.
	 *
	 * @return <code>true</code> if this edition is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _edition.isInactive();
	}

	/**
	 * Returns <code>true</code> if this edition is incomplete.
	 *
	 * @return <code>true</code> if this edition is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _edition.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this edition is in stock.
	 *
	 * @return <code>true</code> if this edition is in stock; <code>false</code> otherwise
	 */
	@Override
	public boolean isInStock() {
		return _edition.isInStock();
	}

	@Override
	public boolean isNew() {
		return _edition.isNew();
	}

	/**
	 * Returns <code>true</code> if this edition is pending.
	 *
	 * @return <code>true</code> if this edition is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _edition.isPending();
	}

	/**
	 * Returns <code>true</code> if this edition is scheduled.
	 *
	 * @return <code>true</code> if this edition is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _edition.isScheduled();
	}

	@Override
	public void persist() {
		_edition.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_edition.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_edition.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the author of this edition.
	 *
	 * @param author the author of this edition
	 */
	@Override
	public void setAuthor(String author) {
		_edition.setAuthor(author);
	}

	/**
	 * Sets the localized author of this edition in the language.
	 *
	 * @param author the localized author of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setAuthor(String author, java.util.Locale locale) {
		_edition.setAuthor(author, locale);
	}

	/**
	 * Sets the localized author of this edition in the language, and sets the default locale.
	 *
	 * @param author the localized author of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAuthor(
		String author, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_edition.setAuthor(author, locale, defaultLocale);
	}

	@Override
	public void setAuthorCurrentLanguageId(String languageId) {
		_edition.setAuthorCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized authors of this edition from the map of locales and localized authors.
	 *
	 * @param authorMap the locales and localized authors of this edition
	 */
	@Override
	public void setAuthorMap(Map<java.util.Locale, String> authorMap) {
		_edition.setAuthorMap(authorMap);
	}

	/**
	 * Sets the localized authors of this edition from the map of locales and localized authors, and sets the default locale.
	 *
	 * @param authorMap the locales and localized authors of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setAuthorMap(
		Map<java.util.Locale, String> authorMap,
		java.util.Locale defaultLocale) {

		_edition.setAuthorMap(authorMap, defaultLocale);
	}

	/**
	 * Sets whether this edition is available for exchange.
	 *
	 * @param availableForExchange the available for exchange of this edition
	 */
	@Override
	public void setAvailableForExchange(boolean availableForExchange) {
		_edition.setAvailableForExchange(availableForExchange);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_edition.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this edition.
	 *
	 * @param companyId the company ID of this edition
	 */
	@Override
	public void setCompanyId(long companyId) {
		_edition.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this edition.
	 *
	 * @param createDate the create date of this edition
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_edition.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this edition.
	 *
	 * @param description the description of this edition
	 */
	@Override
	public void setDescription(String description) {
		_edition.setDescription(description);
	}

	/**
	 * Sets the localized description of this edition in the language.
	 *
	 * @param description the localized description of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_edition.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this edition in the language, and sets the default locale.
	 *
	 * @param description the localized description of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_edition.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_edition.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this edition from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this edition
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_edition.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this edition from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_edition.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the diffusion date month of this edition.
	 *
	 * @param diffusionDateMonth the diffusion date month of this edition
	 */
	@Override
	public void setDiffusionDateMonth(String diffusionDateMonth) {
		_edition.setDiffusionDateMonth(diffusionDateMonth);
	}

	/**
	 * Sets the diffusion date year of this edition.
	 *
	 * @param diffusionDateYear the diffusion date year of this edition
	 */
	@Override
	public void setDiffusionDateYear(String diffusionDateYear) {
		_edition.setDiffusionDateYear(diffusionDateYear);
	}

	/**
	 * Sets the distribution of this edition.
	 *
	 * @param distribution the distribution of this edition
	 */
	@Override
	public void setDistribution(String distribution) {
		_edition.setDistribution(distribution);
	}

	/**
	 * Sets the edition ID of this edition.
	 *
	 * @param editionId the edition ID of this edition
	 */
	@Override
	public void setEditionId(long editionId) {
		_edition.setEditionId(editionId);
	}

	/**
	 * Sets the editor of this edition.
	 *
	 * @param editor the editor of this edition
	 */
	@Override
	public void setEditor(String editor) {
		_edition.setEditor(editor);
	}

	/**
	 * Sets the localized editor of this edition in the language.
	 *
	 * @param editor the localized editor of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setEditor(String editor, java.util.Locale locale) {
		_edition.setEditor(editor, locale);
	}

	/**
	 * Sets the localized editor of this edition in the language, and sets the default locale.
	 *
	 * @param editor the localized editor of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setEditor(
		String editor, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_edition.setEditor(editor, locale, defaultLocale);
	}

	@Override
	public void setEditorCurrentLanguageId(String languageId) {
		_edition.setEditorCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized editors of this edition from the map of locales and localized editors.
	 *
	 * @param editorMap the locales and localized editors of this edition
	 */
	@Override
	public void setEditorMap(Map<java.util.Locale, String> editorMap) {
		_edition.setEditorMap(editorMap);
	}

	/**
	 * Sets the localized editors of this edition from the map of locales and localized editors, and sets the default locale.
	 *
	 * @param editorMap the locales and localized editors of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setEditorMap(
		Map<java.util.Locale, String> editorMap,
		java.util.Locale defaultLocale) {

		_edition.setEditorMap(editorMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_edition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_edition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_edition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the file ID of this edition.
	 *
	 * @param fileId the file ID of this edition
	 */
	@Override
	public void setFileId(String fileId) {
		_edition.setFileId(fileId);
	}

	/**
	 * Sets the localized file ID of this edition in the language.
	 *
	 * @param fileId the localized file ID of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setFileId(String fileId, java.util.Locale locale) {
		_edition.setFileId(fileId, locale);
	}

	/**
	 * Sets the localized file ID of this edition in the language, and sets the default locale.
	 *
	 * @param fileId the localized file ID of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFileId(
		String fileId, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_edition.setFileId(fileId, locale, defaultLocale);
	}

	@Override
	public void setFileIdCurrentLanguageId(String languageId) {
		_edition.setFileIdCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized file IDs of this edition from the map of locales and localized file IDs.
	 *
	 * @param fileIdMap the locales and localized file IDs of this edition
	 */
	@Override
	public void setFileIdMap(Map<java.util.Locale, String> fileIdMap) {
		_edition.setFileIdMap(fileIdMap);
	}

	/**
	 * Sets the localized file IDs of this edition from the map of locales and localized file IDs, and sets the default locale.
	 *
	 * @param fileIdMap the locales and localized file IDs of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFileIdMap(
		Map<java.util.Locale, String> fileIdMap,
		java.util.Locale defaultLocale) {

		_edition.setFileIdMap(fileIdMap, defaultLocale);
	}

	/**
	 * Sets the group ID of this edition.
	 *
	 * @param groupId the group ID of this edition
	 */
	@Override
	public void setGroupId(long groupId) {
		_edition.setGroupId(groupId);
	}

	/**
	 * Sets the image ID of this edition.
	 *
	 * @param imageId the image ID of this edition
	 */
	@Override
	public void setImageId(Long imageId) {
		_edition.setImageId(imageId);
	}

	/**
	 * Sets whether this edition is in stock.
	 *
	 * @param inStock the in stock of this edition
	 */
	@Override
	public void setInStock(boolean inStock) {
		_edition.setInStock(inStock);
	}

	/**
	 * Sets the isbn of this edition.
	 *
	 * @param ISBN the isbn of this edition
	 */
	@Override
	public void setISBN(String ISBN) {
		_edition.setISBN(ISBN);
	}

	/**
	 * Sets the last publish date of this edition.
	 *
	 * @param lastPublishDate the last publish date of this edition
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_edition.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this edition.
	 *
	 * @param modifiedDate the modified date of this edition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_edition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_edition.setNew(n);
	}

	/**
	 * Sets the page number of this edition.
	 *
	 * @param pageNumber the page number of this edition
	 */
	@Override
	public void setPageNumber(String pageNumber) {
		_edition.setPageNumber(pageNumber);
	}

	/**
	 * Sets the picture number of this edition.
	 *
	 * @param pictureNumber the picture number of this edition
	 */
	@Override
	public void setPictureNumber(String pictureNumber) {
		_edition.setPictureNumber(pictureNumber);
	}

	/**
	 * Sets the price of this edition.
	 *
	 * @param price the price of this edition
	 */
	@Override
	public void setPrice(String price) {
		_edition.setPrice(price);
	}

	/**
	 * Sets the primary key of this edition.
	 *
	 * @param primaryKey the primary key of this edition
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_edition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_edition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the publication date of this edition.
	 *
	 * @param publicationDate the publication date of this edition
	 */
	@Override
	public void setPublicationDate(Date publicationDate) {
		_edition.setPublicationDate(publicationDate);
	}

	/**
	 * Sets the status of this edition.
	 *
	 * @param status the status of this edition
	 */
	@Override
	public void setStatus(int status) {
		_edition.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this edition.
	 *
	 * @param statusByUserId the status by user ID of this edition
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_edition.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this edition.
	 *
	 * @param statusByUserName the status by user name of this edition
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_edition.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this edition.
	 *
	 * @param statusByUserUuid the status by user uuid of this edition
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_edition.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this edition.
	 *
	 * @param statusDate the status date of this edition
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_edition.setStatusDate(statusDate);
	}

	/**
	 * Sets the subtitle of this edition.
	 *
	 * @param subtitle the subtitle of this edition
	 */
	@Override
	public void setSubtitle(String subtitle) {
		_edition.setSubtitle(subtitle);
	}

	/**
	 * Sets the localized subtitle of this edition in the language.
	 *
	 * @param subtitle the localized subtitle of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setSubtitle(String subtitle, java.util.Locale locale) {
		_edition.setSubtitle(subtitle, locale);
	}

	/**
	 * Sets the localized subtitle of this edition in the language, and sets the default locale.
	 *
	 * @param subtitle the localized subtitle of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitle(
		String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_edition.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(String languageId) {
		_edition.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized subtitles of this edition from the map of locales and localized subtitles.
	 *
	 * @param subtitleMap the locales and localized subtitles of this edition
	 */
	@Override
	public void setSubtitleMap(Map<java.util.Locale, String> subtitleMap) {
		_edition.setSubtitleMap(subtitleMap);
	}

	/**
	 * Sets the localized subtitles of this edition from the map of locales and localized subtitles, and sets the default locale.
	 *
	 * @param subtitleMap the locales and localized subtitles of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, String> subtitleMap,
		java.util.Locale defaultLocale) {

		_edition.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	 * Sets the title of this edition.
	 *
	 * @param title the title of this edition
	 */
	@Override
	public void setTitle(String title) {
		_edition.setTitle(title);
	}

	/**
	 * Sets the localized title of this edition in the language.
	 *
	 * @param title the localized title of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_edition.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this edition in the language, and sets the default locale.
	 *
	 * @param title the localized title of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		_edition.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_edition.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this edition from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this edition
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_edition.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this edition from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		_edition.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the url of this edition.
	 *
	 * @param URL the url of this edition
	 */
	@Override
	public void setURL(String URL) {
		_edition.setURL(URL);
	}

	/**
	 * Sets the localized url of this edition in the language.
	 *
	 * @param URL the localized url of this edition
	 * @param locale the locale of the language
	 */
	@Override
	public void setURL(String URL, java.util.Locale locale) {
		_edition.setURL(URL, locale);
	}

	/**
	 * Sets the localized url of this edition in the language, and sets the default locale.
	 *
	 * @param URL the localized url of this edition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setURL(
		String URL, java.util.Locale locale, java.util.Locale defaultLocale) {

		_edition.setURL(URL, locale, defaultLocale);
	}

	@Override
	public void setURLCurrentLanguageId(String languageId) {
		_edition.setURLCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized urls of this edition from the map of locales and localized urls.
	 *
	 * @param URLMap the locales and localized urls of this edition
	 */
	@Override
	public void setURLMap(Map<java.util.Locale, String> URLMap) {
		_edition.setURLMap(URLMap);
	}

	/**
	 * Sets the localized urls of this edition from the map of locales and localized urls, and sets the default locale.
	 *
	 * @param URLMap the locales and localized urls of this edition
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setURLMap(
		Map<java.util.Locale, String> URLMap, java.util.Locale defaultLocale) {

		_edition.setURLMap(URLMap, defaultLocale);
	}

	/**
	 * Sets the user ID of this edition.
	 *
	 * @param userId the user ID of this edition
	 */
	@Override
	public void setUserId(long userId) {
		_edition.setUserId(userId);
	}

	/**
	 * Sets the user name of this edition.
	 *
	 * @param userName the user name of this edition
	 */
	@Override
	public void setUserName(String userName) {
		_edition.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this edition.
	 *
	 * @param userUuid the user uuid of this edition
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_edition.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this edition.
	 *
	 * @param uuid the uuid of this edition
	 */
	@Override
	public void setUuid(String uuid) {
		_edition.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.edition.model.Edition> toCacheModel() {

		return _edition.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.edition.model.Edition toEscapedModel() {
		return new EditionWrapper(_edition.toEscapedModel());
	}

	@Override
	public String toString() {
		return _edition.toString();
	}

	@Override
	public eu.strasbourg.service.edition.model.Edition toUnescapedModel() {
		return new EditionWrapper(_edition.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _edition.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EditionWrapper)) {
			return false;
		}

		EditionWrapper editionWrapper = (EditionWrapper)obj;

		if (Objects.equals(_edition, editionWrapper._edition)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _edition.getStagedModelType();
	}

	@Override
	public Edition getWrappedModel() {
		return _edition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _edition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _edition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_edition.resetOriginalValues();
	}

	private final Edition _edition;

}
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
		attributes.put("title", getTitle());
		attributes.put("subtitle", getSubtitle());
		attributes.put("description", getDescription());
		attributes.put("image", getImage());
		attributes.put("URL", getURL());
		attributes.put("author", getAuthor());
		attributes.put("editor", getEditor());
		attributes.put("distribution", getDistribution());
		attributes.put("ISBN", getISBN());
		attributes.put("price", getPrice());
		attributes.put("availableForExchange", getAvailableForExchange());
		attributes.put("inStock", getInStock());
		attributes.put("diffusionDate", getDiffusionDate());
		attributes.put("pageNumber", getPageNumber());
		attributes.put("pictureNumber", getPictureNumber());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("status", getStatus());
		attributes.put("galleryId", getGalleryId());

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

		String image = (String)attributes.get("image");

		if (image != null) {
			setImage(image);
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

		String diffusionDate = (String)attributes.get("diffusionDate");

		if (diffusionDate != null) {
			setDiffusionDate(diffusionDate);
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

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long galleryId = (Long)attributes.get("galleryId");

		if (galleryId != null) {
			setGalleryId(galleryId);
		}
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
	* Returns the status of this edition.
	*
	* @return the status of this edition
	*/
	@Override
	public boolean getStatus() {
		return _edition.getStatus();
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

	@Override
	public boolean isEscapedModel() {
		return _edition.isEscapedModel();
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
	* Returns <code>true</code> if this edition is status.
	*
	* @return <code>true</code> if this edition is status; <code>false</code> otherwise
	*/
	@Override
	public boolean isStatus() {
		return _edition.isStatus();
	}

	/**
	* Retourne l'AssetEntry rattach�e � cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _edition.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _edition.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.edition.model.Edition> toCacheModel() {
		return _edition.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.edition.model.Edition toEscapedModel() {
		return new EditionWrapper(_edition.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.edition.model.Edition toUnescapedModel() {
		return new EditionWrapper(_edition.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.edition.model.Edition edition) {
		return _edition.compareTo(edition);
	}

	@Override
	public int hashCode() {
		return _edition.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _edition.getPrimaryKeyObj();
	}

	/**
	* Returns the gallery ID of this edition.
	*
	* @return the gallery ID of this edition
	*/
	@Override
	public java.lang.Long getGalleryId() {
		return _edition.getGalleryId();
	}

	@Override
	public java.lang.Object clone() {
		return new EditionWrapper((Edition)_edition.clone());
	}

	/**
	* Returns the author of this edition.
	*
	* @return the author of this edition
	*/
	@Override
	public java.lang.String getAuthor() {
		return _edition.getAuthor();
	}

	/**
	* Returns the localized author of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized author of this edition
	*/
	@Override
	public java.lang.String getAuthor(java.lang.String languageId) {
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
	public java.lang.String getAuthor(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getAuthor(languageId, useDefault);
	}

	/**
	* Returns the localized author of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized author of this edition
	*/
	@Override
	public java.lang.String getAuthor(java.util.Locale locale) {
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
	public java.lang.String getAuthor(java.util.Locale locale,
		boolean useDefault) {
		return _edition.getAuthor(locale, useDefault);
	}

	@Override
	public java.lang.String getAuthorCurrentLanguageId() {
		return _edition.getAuthorCurrentLanguageId();
	}

	@Override
	public java.lang.String getAuthorCurrentValue() {
		return _edition.getAuthorCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _edition.getDefaultLanguageId();
	}

	/**
	* Returns the description of this edition.
	*
	* @return the description of this edition
	*/
	@Override
	public java.lang.String getDescription() {
		return _edition.getDescription();
	}

	/**
	* Returns the localized description of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this edition
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
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
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this edition
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
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
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _edition.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _edition.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _edition.getDescriptionCurrentValue();
	}

	/**
	* Returns the diffusion date of this edition.
	*
	* @return the diffusion date of this edition
	*/
	@Override
	public java.lang.String getDiffusionDate() {
		return _edition.getDiffusionDate();
	}

	/**
	* Returns the distribution of this edition.
	*
	* @return the distribution of this edition
	*/
	@Override
	public java.lang.String getDistribution() {
		return _edition.getDistribution();
	}

	/**
	* Returns the editor of this edition.
	*
	* @return the editor of this edition
	*/
	@Override
	public java.lang.String getEditor() {
		return _edition.getEditor();
	}

	/**
	* Returns the localized editor of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized editor of this edition
	*/
	@Override
	public java.lang.String getEditor(java.lang.String languageId) {
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
	public java.lang.String getEditor(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getEditor(languageId, useDefault);
	}

	/**
	* Returns the localized editor of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized editor of this edition
	*/
	@Override
	public java.lang.String getEditor(java.util.Locale locale) {
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
	public java.lang.String getEditor(java.util.Locale locale,
		boolean useDefault) {
		return _edition.getEditor(locale, useDefault);
	}

	@Override
	public java.lang.String getEditorCurrentLanguageId() {
		return _edition.getEditorCurrentLanguageId();
	}

	@Override
	public java.lang.String getEditorCurrentValue() {
		return _edition.getEditorCurrentValue();
	}

	/**
	* Returns the i s b n of this edition.
	*
	* @return the i s b n of this edition
	*/
	@Override
	public java.lang.String getISBN() {
		return _edition.getISBN();
	}

	/**
	* Returns the image of this edition.
	*
	* @return the image of this edition
	*/
	@Override
	public java.lang.String getImage() {
		return _edition.getImage();
	}

	/**
	* Returns the page number of this edition.
	*
	* @return the page number of this edition
	*/
	@Override
	public java.lang.String getPageNumber() {
		return _edition.getPageNumber();
	}

	/**
	* Returns the picture number of this edition.
	*
	* @return the picture number of this edition
	*/
	@Override
	public java.lang.String getPictureNumber() {
		return _edition.getPictureNumber();
	}

	/**
	* Returns the price of this edition.
	*
	* @return the price of this edition
	*/
	@Override
	public java.lang.String getPrice() {
		return _edition.getPrice();
	}

	/**
	* Returns the subtitle of this edition.
	*
	* @return the subtitle of this edition
	*/
	@Override
	public java.lang.String getSubtitle() {
		return _edition.getSubtitle();
	}

	/**
	* Returns the localized subtitle of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subtitle of this edition
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId) {
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
	public java.lang.String getSubtitle(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getSubtitle(languageId, useDefault);
	}

	/**
	* Returns the localized subtitle of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subtitle of this edition
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale) {
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
	public java.lang.String getSubtitle(java.util.Locale locale,
		boolean useDefault) {
		return _edition.getSubtitle(locale, useDefault);
	}

	@Override
	public java.lang.String getSubtitleCurrentLanguageId() {
		return _edition.getSubtitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubtitleCurrentValue() {
		return _edition.getSubtitleCurrentValue();
	}

	/**
	* Returns the title of this edition.
	*
	* @return the title of this edition
	*/
	@Override
	public java.lang.String getTitle() {
		return _edition.getTitle();
	}

	/**
	* Returns the localized title of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this edition
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
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
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this edition
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
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
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _edition.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _edition.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _edition.getTitleCurrentValue();
	}

	/**
	* Returns the u r l of this edition.
	*
	* @return the u r l of this edition
	*/
	@Override
	public java.lang.String getURL() {
		return _edition.getURL();
	}

	/**
	* Returns the localized u r l of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized u r l of this edition
	*/
	@Override
	public java.lang.String getURL(java.lang.String languageId) {
		return _edition.getURL(languageId);
	}

	/**
	* Returns the localized u r l of this edition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized u r l of this edition
	*/
	@Override
	public java.lang.String getURL(java.lang.String languageId,
		boolean useDefault) {
		return _edition.getURL(languageId, useDefault);
	}

	/**
	* Returns the localized u r l of this edition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized u r l of this edition
	*/
	@Override
	public java.lang.String getURL(java.util.Locale locale) {
		return _edition.getURL(locale);
	}

	/**
	* Returns the localized u r l of this edition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized u r l of this edition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getURL(java.util.Locale locale, boolean useDefault) {
		return _edition.getURL(locale, useDefault);
	}

	@Override
	public java.lang.String getURLCurrentLanguageId() {
		return _edition.getURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getURLCurrentValue() {
		return _edition.getURLCurrentValue();
	}

	/**
	* Returns the user name of this edition.
	*
	* @return the user name of this edition
	*/
	@Override
	public java.lang.String getUserName() {
		return _edition.getUserName();
	}

	/**
	* Returns the user uuid of this edition.
	*
	* @return the user uuid of this edition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _edition.getUserUuid();
	}

	/**
	* Returns the uuid of this edition.
	*
	* @return the uuid of this edition
	*/
	@Override
	public java.lang.String getUuid() {
		return _edition.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _edition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _edition.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _edition.getAvailableLanguageIds();
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
	* Returns the publication date of this edition.
	*
	* @return the publication date of this edition
	*/
	@Override
	public Date getPublicationDate() {
		return _edition.getPublicationDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattach�es � cet item (via l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _edition.getCategories();
	}

	/**
	* Returns a map of the locales and localized authors of this edition.
	*
	* @return the locales and localized authors of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAuthorMap() {
		return _edition.getAuthorMap();
	}

	/**
	* Returns a map of the locales and localized descriptions of this edition.
	*
	* @return the locales and localized descriptions of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _edition.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized editors of this edition.
	*
	* @return the locales and localized editors of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getEditorMap() {
		return _edition.getEditorMap();
	}

	/**
	* Returns a map of the locales and localized subtitles of this edition.
	*
	* @return the locales and localized subtitles of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSubtitleMap() {
		return _edition.getSubtitleMap();
	}

	/**
	* Returns a map of the locales and localized titles of this edition.
	*
	* @return the locales and localized titles of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _edition.getTitleMap();
	}

	/**
	* Returns a map of the locales and localized u r ls of this edition.
	*
	* @return the locales and localized u r ls of this edition
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getURLMap() {
		return _edition.getURLMap();
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
	* Returns the edition ID of this edition.
	*
	* @return the edition ID of this edition
	*/
	@Override
	public long getEditionId() {
		return _edition.getEditionId();
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
	* Returns the primary key of this edition.
	*
	* @return the primary key of this edition
	*/
	@Override
	public long getPrimaryKey() {
		return _edition.getPrimaryKey();
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
	public void setAuthor(java.lang.String author) {
		_edition.setAuthor(author);
	}

	/**
	* Sets the localized author of this edition in the language.
	*
	* @param author the localized author of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setAuthor(java.lang.String author, java.util.Locale locale) {
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
	public void setAuthor(java.lang.String author, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_edition.setAuthor(author, locale, defaultLocale);
	}

	@Override
	public void setAuthorCurrentLanguageId(java.lang.String languageId) {
		_edition.setAuthorCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized authors of this edition from the map of locales and localized authors.
	*
	* @param authorMap the locales and localized authors of this edition
	*/
	@Override
	public void setAuthorMap(Map<java.util.Locale, java.lang.String> authorMap) {
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
		Map<java.util.Locale, java.lang.String> authorMap,
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
	public void setDescription(java.lang.String description) {
		_edition.setDescription(description);
	}

	/**
	* Sets the localized description of this edition in the language.
	*
	* @param description the localized description of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
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
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_edition.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_edition.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this edition from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this edition
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
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
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_edition.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the diffusion date of this edition.
	*
	* @param diffusionDate the diffusion date of this edition
	*/
	@Override
	public void setDiffusionDate(java.lang.String diffusionDate) {
		_edition.setDiffusionDate(diffusionDate);
	}

	/**
	* Sets the distribution of this edition.
	*
	* @param distribution the distribution of this edition
	*/
	@Override
	public void setDistribution(java.lang.String distribution) {
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
	public void setEditor(java.lang.String editor) {
		_edition.setEditor(editor);
	}

	/**
	* Sets the localized editor of this edition in the language.
	*
	* @param editor the localized editor of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setEditor(java.lang.String editor, java.util.Locale locale) {
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
	public void setEditor(java.lang.String editor, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_edition.setEditor(editor, locale, defaultLocale);
	}

	@Override
	public void setEditorCurrentLanguageId(java.lang.String languageId) {
		_edition.setEditorCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized editors of this edition from the map of locales and localized editors.
	*
	* @param editorMap the locales and localized editors of this edition
	*/
	@Override
	public void setEditorMap(Map<java.util.Locale, java.lang.String> editorMap) {
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
		Map<java.util.Locale, java.lang.String> editorMap,
		java.util.Locale defaultLocale) {
		_edition.setEditorMap(editorMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_edition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_edition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_edition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gallery ID of this edition.
	*
	* @param galleryId the gallery ID of this edition
	*/
	@Override
	public void setGalleryId(java.lang.Long galleryId) {
		_edition.setGalleryId(galleryId);
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
	* Sets the i s b n of this edition.
	*
	* @param ISBN the i s b n of this edition
	*/
	@Override
	public void setISBN(java.lang.String ISBN) {
		_edition.setISBN(ISBN);
	}

	/**
	* Sets the image of this edition.
	*
	* @param image the image of this edition
	*/
	@Override
	public void setImage(java.lang.String image) {
		_edition.setImage(image);
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
	public void setPageNumber(java.lang.String pageNumber) {
		_edition.setPageNumber(pageNumber);
	}

	/**
	* Sets the picture number of this edition.
	*
	* @param pictureNumber the picture number of this edition
	*/
	@Override
	public void setPictureNumber(java.lang.String pictureNumber) {
		_edition.setPictureNumber(pictureNumber);
	}

	/**
	* Sets the price of this edition.
	*
	* @param price the price of this edition
	*/
	@Override
	public void setPrice(java.lang.String price) {
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
	* Sets whether this edition is status.
	*
	* @param status the status of this edition
	*/
	@Override
	public void setStatus(boolean status) {
		_edition.setStatus(status);
	}

	/**
	* Sets the subtitle of this edition.
	*
	* @param subtitle the subtitle of this edition
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle) {
		_edition.setSubtitle(subtitle);
	}

	/**
	* Sets the localized subtitle of this edition in the language.
	*
	* @param subtitle the localized subtitle of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale) {
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
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_edition.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(java.lang.String languageId) {
		_edition.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subtitles of this edition from the map of locales and localized subtitles.
	*
	* @param subtitleMap the locales and localized subtitles of this edition
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap) {
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
		Map<java.util.Locale, java.lang.String> subtitleMap,
		java.util.Locale defaultLocale) {
		_edition.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	* Sets the title of this edition.
	*
	* @param title the title of this edition
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_edition.setTitle(title);
	}

	/**
	* Sets the localized title of this edition in the language.
	*
	* @param title the localized title of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
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
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_edition.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_edition.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this edition from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this edition
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_edition.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this edition from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this edition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_edition.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the u r l of this edition.
	*
	* @param URL the u r l of this edition
	*/
	@Override
	public void setURL(java.lang.String URL) {
		_edition.setURL(URL);
	}

	/**
	* Sets the localized u r l of this edition in the language.
	*
	* @param URL the localized u r l of this edition
	* @param locale the locale of the language
	*/
	@Override
	public void setURL(java.lang.String URL, java.util.Locale locale) {
		_edition.setURL(URL, locale);
	}

	/**
	* Sets the localized u r l of this edition in the language, and sets the default locale.
	*
	* @param URL the localized u r l of this edition
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setURL(java.lang.String URL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_edition.setURL(URL, locale, defaultLocale);
	}

	@Override
	public void setURLCurrentLanguageId(java.lang.String languageId) {
		_edition.setURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized u r ls of this edition from the map of locales and localized u r ls.
	*
	* @param URLMap the locales and localized u r ls of this edition
	*/
	@Override
	public void setURLMap(Map<java.util.Locale, java.lang.String> URLMap) {
		_edition.setURLMap(URLMap);
	}

	/**
	* Sets the localized u r ls of this edition from the map of locales and localized u r ls, and sets the default locale.
	*
	* @param URLMap the locales and localized u r ls of this edition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setURLMap(Map<java.util.Locale, java.lang.String> URLMap,
		java.util.Locale defaultLocale) {
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
	public void setUserName(java.lang.String userName) {
		_edition.setUserName(userName);
	}

	/**
	* Sets the user uuid of this edition.
	*
	* @param userUuid the user uuid of this edition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_edition.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this edition.
	*
	* @param uuid the uuid of this edition
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_edition.setUuid(uuid);
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
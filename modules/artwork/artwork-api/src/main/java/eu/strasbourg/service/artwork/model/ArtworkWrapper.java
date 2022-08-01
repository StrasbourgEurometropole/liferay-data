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
 * This class is a wrapper for {@link Artwork}.
 * </p>
 *
 * @author BenjaminBini
 * @see Artwork
 * @generated
 */
public class ArtworkWrapper implements Artwork, ModelWrapper<Artwork> {

	public ArtworkWrapper(Artwork artwork) {
		_artwork = artwork;
	}

	@Override
	public Class<?> getModelClass() {
		return Artwork.class;
	}

	@Override
	public String getModelClassName() {
		return Artwork.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("artworkId", getArtworkId());
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
		attributes.put("technicalInformation", getTechnicalInformation());
		attributes.put("noticeLink", getNoticeLink());
		attributes.put("artistName", getArtistName());
		attributes.put("creationYear", getCreationYear());
		attributes.put("origin", getOrigin());
		attributes.put("exhibitionName", getExhibitionName());
		attributes.put("exhibitionPlace", getExhibitionPlace());
		attributes.put("loanPeriod", getLoanPeriod());
		attributes.put("linkName", getLinkName());
		attributes.put("link", getLink());
		attributes.put("imageId", getImageId());
		attributes.put("imagesIds", getImagesIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long artworkId = (Long)attributes.get("artworkId");

		if (artworkId != null) {
			setArtworkId(artworkId);
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

		String technicalInformation = (String)attributes.get(
			"technicalInformation");

		if (technicalInformation != null) {
			setTechnicalInformation(technicalInformation);
		}

		String noticeLink = (String)attributes.get("noticeLink");

		if (noticeLink != null) {
			setNoticeLink(noticeLink);
		}

		String artistName = (String)attributes.get("artistName");

		if (artistName != null) {
			setArtistName(artistName);
		}

		String creationYear = (String)attributes.get("creationYear");

		if (creationYear != null) {
			setCreationYear(creationYear);
		}

		String origin = (String)attributes.get("origin");

		if (origin != null) {
			setOrigin(origin);
		}

		String exhibitionName = (String)attributes.get("exhibitionName");

		if (exhibitionName != null) {
			setExhibitionName(exhibitionName);
		}

		String exhibitionPlace = (String)attributes.get("exhibitionPlace");

		if (exhibitionPlace != null) {
			setExhibitionPlace(exhibitionPlace);
		}

		String loanPeriod = (String)attributes.get("loanPeriod");

		if (loanPeriod != null) {
			setLoanPeriod(loanPeriod);
		}

		String linkName = (String)attributes.get("linkName");

		if (linkName != null) {
			setLinkName(linkName);
		}

		String link = (String)attributes.get("link");

		if (link != null) {
			setLink(link);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String imagesIds = (String)attributes.get("imagesIds");

		if (imagesIds != null) {
			setImagesIds(imagesIds);
		}
	}

	@Override
	public Object clone() {
		return new ArtworkWrapper((Artwork)_artwork.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.artwork.model.Artwork artwork) {
		return _artwork.compareTo(artwork);
	}

	/**
	 * Returns the artist name of this artwork.
	 *
	 * @return the artist name of this artwork
	 */
	@Override
	public String getArtistName() {
		return _artwork.getArtistName();
	}

	/**
	 * Returns the localized artist name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized artist name of this artwork
	 */
	@Override
	public String getArtistName(java.util.Locale locale) {
		return _artwork.getArtistName(locale);
	}

	/**
	 * Returns the localized artist name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized artist name of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getArtistName(java.util.Locale locale, boolean useDefault) {
		return _artwork.getArtistName(locale, useDefault);
	}

	/**
	 * Returns the localized artist name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized artist name of this artwork
	 */
	@Override
	public String getArtistName(String languageId) {
		return _artwork.getArtistName(languageId);
	}

	/**
	 * Returns the localized artist name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized artist name of this artwork
	 */
	@Override
	public String getArtistName(String languageId, boolean useDefault) {
		return _artwork.getArtistName(languageId, useDefault);
	}

	@Override
	public String getArtistNameCurrentLanguageId() {
		return _artwork.getArtistNameCurrentLanguageId();
	}

	@Override
	public String getArtistNameCurrentValue() {
		return _artwork.getArtistNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized artist names of this artwork.
	 *
	 * @return the locales and localized artist names of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getArtistNameMap() {
		return _artwork.getArtistNameMap();
	}

	/**
	 * Retourne la liste des collections d'oeuvres
	 */
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection>
		getArtworkCollections() {

		return _artwork.getArtworkCollections();
	}

	/**
	 * Retourne la liste des ids de collections d'oeuvres sous forme de String
	 */
	@Override
	public String getArtworkCollectionsIds() {
		return _artwork.getArtworkCollectionsIds();
	}

	/**
	 * Returns the artwork ID of this artwork.
	 *
	 * @return the artwork ID of this artwork
	 */
	@Override
	public long getArtworkId() {
		return _artwork.getArtworkId();
	}

	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _artwork.getAssetEntry();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _artwork.getAvailableLanguageIds();
	}

	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via
	 * l'AssetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _artwork.getCategories();
	}

	/**
	 * Returns the company ID of this artwork.
	 *
	 * @return the company ID of this artwork
	 */
	@Override
	public long getCompanyId() {
		return _artwork.getCompanyId();
	}

	/**
	 * Returns the create date of this artwork.
	 *
	 * @return the create date of this artwork
	 */
	@Override
	public Date getCreateDate() {
		return _artwork.getCreateDate();
	}

	/**
	 * Returns the creation year of this artwork.
	 *
	 * @return the creation year of this artwork
	 */
	@Override
	public String getCreationYear() {
		return _artwork.getCreationYear();
	}

	/**
	 * Returns the localized creation year of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized creation year of this artwork
	 */
	@Override
	public String getCreationYear(java.util.Locale locale) {
		return _artwork.getCreationYear(locale);
	}

	/**
	 * Returns the localized creation year of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized creation year of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getCreationYear(java.util.Locale locale, boolean useDefault) {
		return _artwork.getCreationYear(locale, useDefault);
	}

	/**
	 * Returns the localized creation year of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized creation year of this artwork
	 */
	@Override
	public String getCreationYear(String languageId) {
		return _artwork.getCreationYear(languageId);
	}

	/**
	 * Returns the localized creation year of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized creation year of this artwork
	 */
	@Override
	public String getCreationYear(String languageId, boolean useDefault) {
		return _artwork.getCreationYear(languageId, useDefault);
	}

	@Override
	public String getCreationYearCurrentLanguageId() {
		return _artwork.getCreationYearCurrentLanguageId();
	}

	@Override
	public String getCreationYearCurrentValue() {
		return _artwork.getCreationYearCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized creation years of this artwork.
	 *
	 * @return the locales and localized creation years of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getCreationYearMap() {
		return _artwork.getCreationYearMap();
	}

	@Override
	public String getDefaultLanguageId() {
		return _artwork.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this artwork.
	 *
	 * @return the description of this artwork
	 */
	@Override
	public String getDescription() {
		return _artwork.getDescription();
	}

	/**
	 * Returns the localized description of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this artwork
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _artwork.getDescription(locale);
	}

	/**
	 * Returns the localized description of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _artwork.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this artwork
	 */
	@Override
	public String getDescription(String languageId) {
		return _artwork.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this artwork
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _artwork.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _artwork.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _artwork.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this artwork.
	 *
	 * @return the locales and localized descriptions of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _artwork.getDescriptionMap();
	}

	/**
	 * Returns the exhibition name of this artwork.
	 *
	 * @return the exhibition name of this artwork
	 */
	@Override
	public String getExhibitionName() {
		return _artwork.getExhibitionName();
	}

	/**
	 * Returns the localized exhibition name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized exhibition name of this artwork
	 */
	@Override
	public String getExhibitionName(java.util.Locale locale) {
		return _artwork.getExhibitionName(locale);
	}

	/**
	 * Returns the localized exhibition name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized exhibition name of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getExhibitionName(
		java.util.Locale locale, boolean useDefault) {

		return _artwork.getExhibitionName(locale, useDefault);
	}

	/**
	 * Returns the localized exhibition name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized exhibition name of this artwork
	 */
	@Override
	public String getExhibitionName(String languageId) {
		return _artwork.getExhibitionName(languageId);
	}

	/**
	 * Returns the localized exhibition name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized exhibition name of this artwork
	 */
	@Override
	public String getExhibitionName(String languageId, boolean useDefault) {
		return _artwork.getExhibitionName(languageId, useDefault);
	}

	@Override
	public String getExhibitionNameCurrentLanguageId() {
		return _artwork.getExhibitionNameCurrentLanguageId();
	}

	@Override
	public String getExhibitionNameCurrentValue() {
		return _artwork.getExhibitionNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized exhibition names of this artwork.
	 *
	 * @return the locales and localized exhibition names of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getExhibitionNameMap() {
		return _artwork.getExhibitionNameMap();
	}

	/**
	 * Returns the exhibition place of this artwork.
	 *
	 * @return the exhibition place of this artwork
	 */
	@Override
	public String getExhibitionPlace() {
		return _artwork.getExhibitionPlace();
	}

	/**
	 * Returns the localized exhibition place of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized exhibition place of this artwork
	 */
	@Override
	public String getExhibitionPlace(java.util.Locale locale) {
		return _artwork.getExhibitionPlace(locale);
	}

	/**
	 * Returns the localized exhibition place of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized exhibition place of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getExhibitionPlace(
		java.util.Locale locale, boolean useDefault) {

		return _artwork.getExhibitionPlace(locale, useDefault);
	}

	/**
	 * Returns the localized exhibition place of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized exhibition place of this artwork
	 */
	@Override
	public String getExhibitionPlace(String languageId) {
		return _artwork.getExhibitionPlace(languageId);
	}

	/**
	 * Returns the localized exhibition place of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized exhibition place of this artwork
	 */
	@Override
	public String getExhibitionPlace(String languageId, boolean useDefault) {
		return _artwork.getExhibitionPlace(languageId, useDefault);
	}

	@Override
	public String getExhibitionPlaceCurrentLanguageId() {
		return _artwork.getExhibitionPlaceCurrentLanguageId();
	}

	@Override
	public String getExhibitionPlaceCurrentValue() {
		return _artwork.getExhibitionPlaceCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized exhibition places of this artwork.
	 *
	 * @return the locales and localized exhibition places of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getExhibitionPlaceMap() {
		return _artwork.getExhibitionPlaceMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _artwork.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this artwork.
	 *
	 * @return the group ID of this artwork
	 */
	@Override
	public long getGroupId() {
		return _artwork.getGroupId();
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(java.util.Locale locale) {
		return _artwork.getImageCopyright(locale);
	}

	/**
	 * Returns the image ID of this artwork.
	 *
	 * @return the image ID of this artwork
	 */
	@Override
	public Long getImageId() {
		return _artwork.getImageId();
	}

	/**
	 * Retourne la légende de l'image principale
	 */
	@Override
	public String getImageLegend(java.util.Locale locale) {
		return _artwork.getImageLegend(locale);
	}

	/**
	 * Retourne la légende de l'image principale suivie de son copyright
	 */
	@Override
	public String getImageLegendAndCopyright(java.util.Locale locale) {
		return _artwork.getImageLegendAndCopyright(locale);
	}

	/**
	 * Returns the images IDs of this artwork.
	 *
	 * @return the images IDs of this artwork
	 */
	@Override
	public String getImagesIds() {
		return _artwork.getImagesIds();
	}

	/**
	 * Retourne la liste des URL publiques des images additionnelles
	 */
	@Override
	public java.util.List<String> getImagesLegendsAndCopyrights(
		java.util.Locale locale) {

		return _artwork.getImagesLegendsAndCopyrights(locale);
	}

	/**
	 * Retourne la liste des URL publiques des images additionnelles
	 */
	@Override
	public java.util.List<String> getImagesURLs() {
		return _artwork.getImagesURLs();
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return _artwork.getImageURL();
	}

	/**
	 * Returns the last publish date of this artwork.
	 *
	 * @return the last publish date of this artwork
	 */
	@Override
	public Date getLastPublishDate() {
		return _artwork.getLastPublishDate();
	}

	/**
	 * Returns the link of this artwork.
	 *
	 * @return the link of this artwork
	 */
	@Override
	public String getLink() {
		return _artwork.getLink();
	}

	/**
	 * Returns the localized link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized link of this artwork
	 */
	@Override
	public String getLink(java.util.Locale locale) {
		return _artwork.getLink(locale);
	}

	/**
	 * Returns the localized link of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized link of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getLink(java.util.Locale locale, boolean useDefault) {
		return _artwork.getLink(locale, useDefault);
	}

	/**
	 * Returns the localized link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized link of this artwork
	 */
	@Override
	public String getLink(String languageId) {
		return _artwork.getLink(languageId);
	}

	/**
	 * Returns the localized link of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized link of this artwork
	 */
	@Override
	public String getLink(String languageId, boolean useDefault) {
		return _artwork.getLink(languageId, useDefault);
	}

	@Override
	public String getLinkCurrentLanguageId() {
		return _artwork.getLinkCurrentLanguageId();
	}

	@Override
	public String getLinkCurrentValue() {
		return _artwork.getLinkCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized links of this artwork.
	 *
	 * @return the locales and localized links of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getLinkMap() {
		return _artwork.getLinkMap();
	}

	/**
	 * Returns the link name of this artwork.
	 *
	 * @return the link name of this artwork
	 */
	@Override
	public String getLinkName() {
		return _artwork.getLinkName();
	}

	/**
	 * Returns the localized link name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized link name of this artwork
	 */
	@Override
	public String getLinkName(java.util.Locale locale) {
		return _artwork.getLinkName(locale);
	}

	/**
	 * Returns the localized link name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized link name of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getLinkName(java.util.Locale locale, boolean useDefault) {
		return _artwork.getLinkName(locale, useDefault);
	}

	/**
	 * Returns the localized link name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized link name of this artwork
	 */
	@Override
	public String getLinkName(String languageId) {
		return _artwork.getLinkName(languageId);
	}

	/**
	 * Returns the localized link name of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized link name of this artwork
	 */
	@Override
	public String getLinkName(String languageId, boolean useDefault) {
		return _artwork.getLinkName(languageId, useDefault);
	}

	@Override
	public String getLinkNameCurrentLanguageId() {
		return _artwork.getLinkNameCurrentLanguageId();
	}

	@Override
	public String getLinkNameCurrentValue() {
		return _artwork.getLinkNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized link names of this artwork.
	 *
	 * @return the locales and localized link names of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getLinkNameMap() {
		return _artwork.getLinkNameMap();
	}

	/**
	 * Retourne la version live de l'oeuvre, si elle existe
	 */
	@Override
	public eu.strasbourg.service.artwork.model.Artwork getLiveVersion() {
		return _artwork.getLiveVersion();
	}

	/**
	 * Returns the loan period of this artwork.
	 *
	 * @return the loan period of this artwork
	 */
	@Override
	public String getLoanPeriod() {
		return _artwork.getLoanPeriod();
	}

	/**
	 * Returns the localized loan period of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized loan period of this artwork
	 */
	@Override
	public String getLoanPeriod(java.util.Locale locale) {
		return _artwork.getLoanPeriod(locale);
	}

	/**
	 * Returns the localized loan period of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized loan period of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getLoanPeriod(java.util.Locale locale, boolean useDefault) {
		return _artwork.getLoanPeriod(locale, useDefault);
	}

	/**
	 * Returns the localized loan period of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized loan period of this artwork
	 */
	@Override
	public String getLoanPeriod(String languageId) {
		return _artwork.getLoanPeriod(languageId);
	}

	/**
	 * Returns the localized loan period of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized loan period of this artwork
	 */
	@Override
	public String getLoanPeriod(String languageId, boolean useDefault) {
		return _artwork.getLoanPeriod(languageId, useDefault);
	}

	@Override
	public String getLoanPeriodCurrentLanguageId() {
		return _artwork.getLoanPeriodCurrentLanguageId();
	}

	@Override
	public String getLoanPeriodCurrentValue() {
		return _artwork.getLoanPeriodCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized loan periods of this artwork.
	 *
	 * @return the locales and localized loan periods of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getLoanPeriodMap() {
		return _artwork.getLoanPeriodMap();
	}

	/**
	 * Returns the modified date of this artwork.
	 *
	 * @return the modified date of this artwork
	 */
	@Override
	public Date getModifiedDate() {
		return _artwork.getModifiedDate();
	}

	/**
	 * Returns the notice link of this artwork.
	 *
	 * @return the notice link of this artwork
	 */
	@Override
	public String getNoticeLink() {
		return _artwork.getNoticeLink();
	}

	/**
	 * Returns the localized notice link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized notice link of this artwork
	 */
	@Override
	public String getNoticeLink(java.util.Locale locale) {
		return _artwork.getNoticeLink(locale);
	}

	/**
	 * Returns the localized notice link of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized notice link of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getNoticeLink(java.util.Locale locale, boolean useDefault) {
		return _artwork.getNoticeLink(locale, useDefault);
	}

	/**
	 * Returns the localized notice link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized notice link of this artwork
	 */
	@Override
	public String getNoticeLink(String languageId) {
		return _artwork.getNoticeLink(languageId);
	}

	/**
	 * Returns the localized notice link of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized notice link of this artwork
	 */
	@Override
	public String getNoticeLink(String languageId, boolean useDefault) {
		return _artwork.getNoticeLink(languageId, useDefault);
	}

	@Override
	public String getNoticeLinkCurrentLanguageId() {
		return _artwork.getNoticeLinkCurrentLanguageId();
	}

	@Override
	public String getNoticeLinkCurrentValue() {
		return _artwork.getNoticeLinkCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized notice links of this artwork.
	 *
	 * @return the locales and localized notice links of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getNoticeLinkMap() {
		return _artwork.getNoticeLinkMap();
	}

	/**
	 * Returns the origin of this artwork.
	 *
	 * @return the origin of this artwork
	 */
	@Override
	public String getOrigin() {
		return _artwork.getOrigin();
	}

	/**
	 * Returns the localized origin of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized origin of this artwork
	 */
	@Override
	public String getOrigin(java.util.Locale locale) {
		return _artwork.getOrigin(locale);
	}

	/**
	 * Returns the localized origin of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized origin of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getOrigin(java.util.Locale locale, boolean useDefault) {
		return _artwork.getOrigin(locale, useDefault);
	}

	/**
	 * Returns the localized origin of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized origin of this artwork
	 */
	@Override
	public String getOrigin(String languageId) {
		return _artwork.getOrigin(languageId);
	}

	/**
	 * Returns the localized origin of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized origin of this artwork
	 */
	@Override
	public String getOrigin(String languageId, boolean useDefault) {
		return _artwork.getOrigin(languageId, useDefault);
	}

	@Override
	public String getOriginCurrentLanguageId() {
		return _artwork.getOriginCurrentLanguageId();
	}

	@Override
	public String getOriginCurrentValue() {
		return _artwork.getOriginCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized origins of this artwork.
	 *
	 * @return the locales and localized origins of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getOriginMap() {
		return _artwork.getOriginMap();
	}

	/**
	 * Returns the primary key of this artwork.
	 *
	 * @return the primary key of this artwork
	 */
	@Override
	public long getPrimaryKey() {
		return _artwork.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _artwork.getPrimaryKeyObj();
	}

	/**
	 * Retourne la liste des collections d'oeuvres publiées
	 */
	@Override
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection>
		getPublishedArtworkCollections() {

		return _artwork.getPublishedArtworkCollections();
	}

	/**
	 * Retourne la classe css correspondante à la source
	 */
	@Override
	public String getSourceCSSClass() {
		return _artwork.getSourceCSSClass();
	}

	/**
	 * Retourne les sources de l'oeuvre
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getSources() {

		return _artwork.getSources();
	}

	/**
	 * Returns the status of this artwork.
	 *
	 * @return the status of this artwork
	 */
	@Override
	public int getStatus() {
		return _artwork.getStatus();
	}

	/**
	 * Returns the status by user ID of this artwork.
	 *
	 * @return the status by user ID of this artwork
	 */
	@Override
	public long getStatusByUserId() {
		return _artwork.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this artwork.
	 *
	 * @return the status by user name of this artwork
	 */
	@Override
	public String getStatusByUserName() {
		return _artwork.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this artwork.
	 *
	 * @return the status by user uuid of this artwork
	 */
	@Override
	public String getStatusByUserUuid() {
		return _artwork.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this artwork.
	 *
	 * @return the status date of this artwork
	 */
	@Override
	public Date getStatusDate() {
		return _artwork.getStatusDate();
	}

	/**
	 * Returns the technical information of this artwork.
	 *
	 * @return the technical information of this artwork
	 */
	@Override
	public String getTechnicalInformation() {
		return _artwork.getTechnicalInformation();
	}

	/**
	 * Returns the localized technical information of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized technical information of this artwork
	 */
	@Override
	public String getTechnicalInformation(java.util.Locale locale) {
		return _artwork.getTechnicalInformation(locale);
	}

	/**
	 * Returns the localized technical information of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized technical information of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTechnicalInformation(
		java.util.Locale locale, boolean useDefault) {

		return _artwork.getTechnicalInformation(locale, useDefault);
	}

	/**
	 * Returns the localized technical information of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized technical information of this artwork
	 */
	@Override
	public String getTechnicalInformation(String languageId) {
		return _artwork.getTechnicalInformation(languageId);
	}

	/**
	 * Returns the localized technical information of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized technical information of this artwork
	 */
	@Override
	public String getTechnicalInformation(
		String languageId, boolean useDefault) {

		return _artwork.getTechnicalInformation(languageId, useDefault);
	}

	@Override
	public String getTechnicalInformationCurrentLanguageId() {
		return _artwork.getTechnicalInformationCurrentLanguageId();
	}

	@Override
	public String getTechnicalInformationCurrentValue() {
		return _artwork.getTechnicalInformationCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized technical informations of this artwork.
	 *
	 * @return the locales and localized technical informations of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getTechnicalInformationMap() {
		return _artwork.getTechnicalInformationMap();
	}

	/**
	 * Returns the title of this artwork.
	 *
	 * @return the title of this artwork
	 */
	@Override
	public String getTitle() {
		return _artwork.getTitle();
	}

	/**
	 * Returns the localized title of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this artwork
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return _artwork.getTitle(locale);
	}

	/**
	 * Returns the localized title of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this artwork. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _artwork.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this artwork in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this artwork
	 */
	@Override
	public String getTitle(String languageId) {
		return _artwork.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this artwork in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this artwork
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return _artwork.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _artwork.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _artwork.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this artwork.
	 *
	 * @return the locales and localized titles of this artwork
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _artwork.getTitleMap();
	}

	/**
	 * Returns the user ID of this artwork.
	 *
	 * @return the user ID of this artwork
	 */
	@Override
	public long getUserId() {
		return _artwork.getUserId();
	}

	/**
	 * Returns the user name of this artwork.
	 *
	 * @return the user name of this artwork
	 */
	@Override
	public String getUserName() {
		return _artwork.getUserName();
	}

	/**
	 * Returns the user uuid of this artwork.
	 *
	 * @return the user uuid of this artwork
	 */
	@Override
	public String getUserUuid() {
		return _artwork.getUserUuid();
	}

	/**
	 * Returns the uuid of this artwork.
	 *
	 * @return the uuid of this artwork
	 */
	@Override
	public String getUuid() {
		return _artwork.getUuid();
	}

	@Override
	public int hashCode() {
		return _artwork.hashCode();
	}

	/**
	 * Returns <code>true</code> if this artwork is approved.
	 *
	 * @return <code>true</code> if this artwork is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _artwork.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _artwork.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this artwork is denied.
	 *
	 * @return <code>true</code> if this artwork is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _artwork.isDenied();
	}

	/**
	 * Returns <code>true</code> if this artwork is a draft.
	 *
	 * @return <code>true</code> if this artwork is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _artwork.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _artwork.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this artwork is expired.
	 *
	 * @return <code>true</code> if this artwork is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _artwork.isExpired();
	}

	/**
	 * Returns <code>true</code> if this artwork is inactive.
	 *
	 * @return <code>true</code> if this artwork is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _artwork.isInactive();
	}

	/**
	 * Returns <code>true</code> if this artwork is incomplete.
	 *
	 * @return <code>true</code> if this artwork is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _artwork.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _artwork.isNew();
	}

	/**
	 * Returns <code>true</code> if this artwork is pending.
	 *
	 * @return <code>true</code> if this artwork is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _artwork.isPending();
	}

	/**
	 * Returns <code>true</code> if this artwork is scheduled.
	 *
	 * @return <code>true</code> if this artwork is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _artwork.isScheduled();
	}

	@Override
	public void persist() {
		_artwork.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_artwork.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_artwork.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the artist name of this artwork.
	 *
	 * @param artistName the artist name of this artwork
	 */
	@Override
	public void setArtistName(String artistName) {
		_artwork.setArtistName(artistName);
	}

	/**
	 * Sets the localized artist name of this artwork in the language.
	 *
	 * @param artistName the localized artist name of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setArtistName(String artistName, java.util.Locale locale) {
		_artwork.setArtistName(artistName, locale);
	}

	/**
	 * Sets the localized artist name of this artwork in the language, and sets the default locale.
	 *
	 * @param artistName the localized artist name of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setArtistName(
		String artistName, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setArtistName(artistName, locale, defaultLocale);
	}

	@Override
	public void setArtistNameCurrentLanguageId(String languageId) {
		_artwork.setArtistNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized artist names of this artwork from the map of locales and localized artist names.
	 *
	 * @param artistNameMap the locales and localized artist names of this artwork
	 */
	@Override
	public void setArtistNameMap(Map<java.util.Locale, String> artistNameMap) {
		_artwork.setArtistNameMap(artistNameMap);
	}

	/**
	 * Sets the localized artist names of this artwork from the map of locales and localized artist names, and sets the default locale.
	 *
	 * @param artistNameMap the locales and localized artist names of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setArtistNameMap(
		Map<java.util.Locale, String> artistNameMap,
		java.util.Locale defaultLocale) {

		_artwork.setArtistNameMap(artistNameMap, defaultLocale);
	}

	/**
	 * Sets the artwork ID of this artwork.
	 *
	 * @param artworkId the artwork ID of this artwork
	 */
	@Override
	public void setArtworkId(long artworkId) {
		_artwork.setArtworkId(artworkId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_artwork.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this artwork.
	 *
	 * @param companyId the company ID of this artwork
	 */
	@Override
	public void setCompanyId(long companyId) {
		_artwork.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this artwork.
	 *
	 * @param createDate the create date of this artwork
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_artwork.setCreateDate(createDate);
	}

	/**
	 * Sets the creation year of this artwork.
	 *
	 * @param creationYear the creation year of this artwork
	 */
	@Override
	public void setCreationYear(String creationYear) {
		_artwork.setCreationYear(creationYear);
	}

	/**
	 * Sets the localized creation year of this artwork in the language.
	 *
	 * @param creationYear the localized creation year of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setCreationYear(String creationYear, java.util.Locale locale) {
		_artwork.setCreationYear(creationYear, locale);
	}

	/**
	 * Sets the localized creation year of this artwork in the language, and sets the default locale.
	 *
	 * @param creationYear the localized creation year of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setCreationYear(
		String creationYear, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setCreationYear(creationYear, locale, defaultLocale);
	}

	@Override
	public void setCreationYearCurrentLanguageId(String languageId) {
		_artwork.setCreationYearCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized creation years of this artwork from the map of locales and localized creation years.
	 *
	 * @param creationYearMap the locales and localized creation years of this artwork
	 */
	@Override
	public void setCreationYearMap(
		Map<java.util.Locale, String> creationYearMap) {

		_artwork.setCreationYearMap(creationYearMap);
	}

	/**
	 * Sets the localized creation years of this artwork from the map of locales and localized creation years, and sets the default locale.
	 *
	 * @param creationYearMap the locales and localized creation years of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setCreationYearMap(
		Map<java.util.Locale, String> creationYearMap,
		java.util.Locale defaultLocale) {

		_artwork.setCreationYearMap(creationYearMap, defaultLocale);
	}

	/**
	 * Sets the description of this artwork.
	 *
	 * @param description the description of this artwork
	 */
	@Override
	public void setDescription(String description) {
		_artwork.setDescription(description);
	}

	/**
	 * Sets the localized description of this artwork in the language.
	 *
	 * @param description the localized description of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_artwork.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this artwork in the language, and sets the default locale.
	 *
	 * @param description the localized description of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_artwork.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this artwork from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this artwork
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_artwork.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this artwork from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_artwork.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the exhibition name of this artwork.
	 *
	 * @param exhibitionName the exhibition name of this artwork
	 */
	@Override
	public void setExhibitionName(String exhibitionName) {
		_artwork.setExhibitionName(exhibitionName);
	}

	/**
	 * Sets the localized exhibition name of this artwork in the language.
	 *
	 * @param exhibitionName the localized exhibition name of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setExhibitionName(
		String exhibitionName, java.util.Locale locale) {

		_artwork.setExhibitionName(exhibitionName, locale);
	}

	/**
	 * Sets the localized exhibition name of this artwork in the language, and sets the default locale.
	 *
	 * @param exhibitionName the localized exhibition name of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setExhibitionName(
		String exhibitionName, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setExhibitionName(exhibitionName, locale, defaultLocale);
	}

	@Override
	public void setExhibitionNameCurrentLanguageId(String languageId) {
		_artwork.setExhibitionNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized exhibition names of this artwork from the map of locales and localized exhibition names.
	 *
	 * @param exhibitionNameMap the locales and localized exhibition names of this artwork
	 */
	@Override
	public void setExhibitionNameMap(
		Map<java.util.Locale, String> exhibitionNameMap) {

		_artwork.setExhibitionNameMap(exhibitionNameMap);
	}

	/**
	 * Sets the localized exhibition names of this artwork from the map of locales and localized exhibition names, and sets the default locale.
	 *
	 * @param exhibitionNameMap the locales and localized exhibition names of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setExhibitionNameMap(
		Map<java.util.Locale, String> exhibitionNameMap,
		java.util.Locale defaultLocale) {

		_artwork.setExhibitionNameMap(exhibitionNameMap, defaultLocale);
	}

	/**
	 * Sets the exhibition place of this artwork.
	 *
	 * @param exhibitionPlace the exhibition place of this artwork
	 */
	@Override
	public void setExhibitionPlace(String exhibitionPlace) {
		_artwork.setExhibitionPlace(exhibitionPlace);
	}

	/**
	 * Sets the localized exhibition place of this artwork in the language.
	 *
	 * @param exhibitionPlace the localized exhibition place of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setExhibitionPlace(
		String exhibitionPlace, java.util.Locale locale) {

		_artwork.setExhibitionPlace(exhibitionPlace, locale);
	}

	/**
	 * Sets the localized exhibition place of this artwork in the language, and sets the default locale.
	 *
	 * @param exhibitionPlace the localized exhibition place of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setExhibitionPlace(
		String exhibitionPlace, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setExhibitionPlace(exhibitionPlace, locale, defaultLocale);
	}

	@Override
	public void setExhibitionPlaceCurrentLanguageId(String languageId) {
		_artwork.setExhibitionPlaceCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized exhibition places of this artwork from the map of locales and localized exhibition places.
	 *
	 * @param exhibitionPlaceMap the locales and localized exhibition places of this artwork
	 */
	@Override
	public void setExhibitionPlaceMap(
		Map<java.util.Locale, String> exhibitionPlaceMap) {

		_artwork.setExhibitionPlaceMap(exhibitionPlaceMap);
	}

	/**
	 * Sets the localized exhibition places of this artwork from the map of locales and localized exhibition places, and sets the default locale.
	 *
	 * @param exhibitionPlaceMap the locales and localized exhibition places of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setExhibitionPlaceMap(
		Map<java.util.Locale, String> exhibitionPlaceMap,
		java.util.Locale defaultLocale) {

		_artwork.setExhibitionPlaceMap(exhibitionPlaceMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_artwork.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_artwork.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_artwork.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this artwork.
	 *
	 * @param groupId the group ID of this artwork
	 */
	@Override
	public void setGroupId(long groupId) {
		_artwork.setGroupId(groupId);
	}

	/**
	 * Sets the image ID of this artwork.
	 *
	 * @param imageId the image ID of this artwork
	 */
	@Override
	public void setImageId(Long imageId) {
		_artwork.setImageId(imageId);
	}

	/**
	 * Sets the images IDs of this artwork.
	 *
	 * @param imagesIds the images IDs of this artwork
	 */
	@Override
	public void setImagesIds(String imagesIds) {
		_artwork.setImagesIds(imagesIds);
	}

	/**
	 * Sets the last publish date of this artwork.
	 *
	 * @param lastPublishDate the last publish date of this artwork
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_artwork.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the link of this artwork.
	 *
	 * @param link the link of this artwork
	 */
	@Override
	public void setLink(String link) {
		_artwork.setLink(link);
	}

	/**
	 * Sets the localized link of this artwork in the language.
	 *
	 * @param link the localized link of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setLink(String link, java.util.Locale locale) {
		_artwork.setLink(link, locale);
	}

	/**
	 * Sets the localized link of this artwork in the language, and sets the default locale.
	 *
	 * @param link the localized link of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLink(
		String link, java.util.Locale locale, java.util.Locale defaultLocale) {

		_artwork.setLink(link, locale, defaultLocale);
	}

	@Override
	public void setLinkCurrentLanguageId(String languageId) {
		_artwork.setLinkCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized links of this artwork from the map of locales and localized links.
	 *
	 * @param linkMap the locales and localized links of this artwork
	 */
	@Override
	public void setLinkMap(Map<java.util.Locale, String> linkMap) {
		_artwork.setLinkMap(linkMap);
	}

	/**
	 * Sets the localized links of this artwork from the map of locales and localized links, and sets the default locale.
	 *
	 * @param linkMap the locales and localized links of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLinkMap(
		Map<java.util.Locale, String> linkMap, java.util.Locale defaultLocale) {

		_artwork.setLinkMap(linkMap, defaultLocale);
	}

	/**
	 * Sets the link name of this artwork.
	 *
	 * @param linkName the link name of this artwork
	 */
	@Override
	public void setLinkName(String linkName) {
		_artwork.setLinkName(linkName);
	}

	/**
	 * Sets the localized link name of this artwork in the language.
	 *
	 * @param linkName the localized link name of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setLinkName(String linkName, java.util.Locale locale) {
		_artwork.setLinkName(linkName, locale);
	}

	/**
	 * Sets the localized link name of this artwork in the language, and sets the default locale.
	 *
	 * @param linkName the localized link name of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLinkName(
		String linkName, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setLinkName(linkName, locale, defaultLocale);
	}

	@Override
	public void setLinkNameCurrentLanguageId(String languageId) {
		_artwork.setLinkNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized link names of this artwork from the map of locales and localized link names.
	 *
	 * @param linkNameMap the locales and localized link names of this artwork
	 */
	@Override
	public void setLinkNameMap(Map<java.util.Locale, String> linkNameMap) {
		_artwork.setLinkNameMap(linkNameMap);
	}

	/**
	 * Sets the localized link names of this artwork from the map of locales and localized link names, and sets the default locale.
	 *
	 * @param linkNameMap the locales and localized link names of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLinkNameMap(
		Map<java.util.Locale, String> linkNameMap,
		java.util.Locale defaultLocale) {

		_artwork.setLinkNameMap(linkNameMap, defaultLocale);
	}

	/**
	 * Sets the loan period of this artwork.
	 *
	 * @param loanPeriod the loan period of this artwork
	 */
	@Override
	public void setLoanPeriod(String loanPeriod) {
		_artwork.setLoanPeriod(loanPeriod);
	}

	/**
	 * Sets the localized loan period of this artwork in the language.
	 *
	 * @param loanPeriod the localized loan period of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setLoanPeriod(String loanPeriod, java.util.Locale locale) {
		_artwork.setLoanPeriod(loanPeriod, locale);
	}

	/**
	 * Sets the localized loan period of this artwork in the language, and sets the default locale.
	 *
	 * @param loanPeriod the localized loan period of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLoanPeriod(
		String loanPeriod, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setLoanPeriod(loanPeriod, locale, defaultLocale);
	}

	@Override
	public void setLoanPeriodCurrentLanguageId(String languageId) {
		_artwork.setLoanPeriodCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized loan periods of this artwork from the map of locales and localized loan periods.
	 *
	 * @param loanPeriodMap the locales and localized loan periods of this artwork
	 */
	@Override
	public void setLoanPeriodMap(Map<java.util.Locale, String> loanPeriodMap) {
		_artwork.setLoanPeriodMap(loanPeriodMap);
	}

	/**
	 * Sets the localized loan periods of this artwork from the map of locales and localized loan periods, and sets the default locale.
	 *
	 * @param loanPeriodMap the locales and localized loan periods of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLoanPeriodMap(
		Map<java.util.Locale, String> loanPeriodMap,
		java.util.Locale defaultLocale) {

		_artwork.setLoanPeriodMap(loanPeriodMap, defaultLocale);
	}

	/**
	 * Sets the modified date of this artwork.
	 *
	 * @param modifiedDate the modified date of this artwork
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_artwork.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_artwork.setNew(n);
	}

	/**
	 * Sets the notice link of this artwork.
	 *
	 * @param noticeLink the notice link of this artwork
	 */
	@Override
	public void setNoticeLink(String noticeLink) {
		_artwork.setNoticeLink(noticeLink);
	}

	/**
	 * Sets the localized notice link of this artwork in the language.
	 *
	 * @param noticeLink the localized notice link of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setNoticeLink(String noticeLink, java.util.Locale locale) {
		_artwork.setNoticeLink(noticeLink, locale);
	}

	/**
	 * Sets the localized notice link of this artwork in the language, and sets the default locale.
	 *
	 * @param noticeLink the localized notice link of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNoticeLink(
		String noticeLink, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setNoticeLink(noticeLink, locale, defaultLocale);
	}

	@Override
	public void setNoticeLinkCurrentLanguageId(String languageId) {
		_artwork.setNoticeLinkCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized notice links of this artwork from the map of locales and localized notice links.
	 *
	 * @param noticeLinkMap the locales and localized notice links of this artwork
	 */
	@Override
	public void setNoticeLinkMap(Map<java.util.Locale, String> noticeLinkMap) {
		_artwork.setNoticeLinkMap(noticeLinkMap);
	}

	/**
	 * Sets the localized notice links of this artwork from the map of locales and localized notice links, and sets the default locale.
	 *
	 * @param noticeLinkMap the locales and localized notice links of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setNoticeLinkMap(
		Map<java.util.Locale, String> noticeLinkMap,
		java.util.Locale defaultLocale) {

		_artwork.setNoticeLinkMap(noticeLinkMap, defaultLocale);
	}

	/**
	 * Sets the origin of this artwork.
	 *
	 * @param origin the origin of this artwork
	 */
	@Override
	public void setOrigin(String origin) {
		_artwork.setOrigin(origin);
	}

	/**
	 * Sets the localized origin of this artwork in the language.
	 *
	 * @param origin the localized origin of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setOrigin(String origin, java.util.Locale locale) {
		_artwork.setOrigin(origin, locale);
	}

	/**
	 * Sets the localized origin of this artwork in the language, and sets the default locale.
	 *
	 * @param origin the localized origin of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setOrigin(
		String origin, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setOrigin(origin, locale, defaultLocale);
	}

	@Override
	public void setOriginCurrentLanguageId(String languageId) {
		_artwork.setOriginCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized origins of this artwork from the map of locales and localized origins.
	 *
	 * @param originMap the locales and localized origins of this artwork
	 */
	@Override
	public void setOriginMap(Map<java.util.Locale, String> originMap) {
		_artwork.setOriginMap(originMap);
	}

	/**
	 * Sets the localized origins of this artwork from the map of locales and localized origins, and sets the default locale.
	 *
	 * @param originMap the locales and localized origins of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setOriginMap(
		Map<java.util.Locale, String> originMap,
		java.util.Locale defaultLocale) {

		_artwork.setOriginMap(originMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this artwork.
	 *
	 * @param primaryKey the primary key of this artwork
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_artwork.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_artwork.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this artwork.
	 *
	 * @param status the status of this artwork
	 */
	@Override
	public void setStatus(int status) {
		_artwork.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this artwork.
	 *
	 * @param statusByUserId the status by user ID of this artwork
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_artwork.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this artwork.
	 *
	 * @param statusByUserName the status by user name of this artwork
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_artwork.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this artwork.
	 *
	 * @param statusByUserUuid the status by user uuid of this artwork
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_artwork.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this artwork.
	 *
	 * @param statusDate the status date of this artwork
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_artwork.setStatusDate(statusDate);
	}

	/**
	 * Sets the technical information of this artwork.
	 *
	 * @param technicalInformation the technical information of this artwork
	 */
	@Override
	public void setTechnicalInformation(String technicalInformation) {
		_artwork.setTechnicalInformation(technicalInformation);
	}

	/**
	 * Sets the localized technical information of this artwork in the language.
	 *
	 * @param technicalInformation the localized technical information of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setTechnicalInformation(
		String technicalInformation, java.util.Locale locale) {

		_artwork.setTechnicalInformation(technicalInformation, locale);
	}

	/**
	 * Sets the localized technical information of this artwork in the language, and sets the default locale.
	 *
	 * @param technicalInformation the localized technical information of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTechnicalInformation(
		String technicalInformation, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_artwork.setTechnicalInformation(
			technicalInformation, locale, defaultLocale);
	}

	@Override
	public void setTechnicalInformationCurrentLanguageId(String languageId) {
		_artwork.setTechnicalInformationCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized technical informations of this artwork from the map of locales and localized technical informations.
	 *
	 * @param technicalInformationMap the locales and localized technical informations of this artwork
	 */
	@Override
	public void setTechnicalInformationMap(
		Map<java.util.Locale, String> technicalInformationMap) {

		_artwork.setTechnicalInformationMap(technicalInformationMap);
	}

	/**
	 * Sets the localized technical informations of this artwork from the map of locales and localized technical informations, and sets the default locale.
	 *
	 * @param technicalInformationMap the locales and localized technical informations of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTechnicalInformationMap(
		Map<java.util.Locale, String> technicalInformationMap,
		java.util.Locale defaultLocale) {

		_artwork.setTechnicalInformationMap(
			technicalInformationMap, defaultLocale);
	}

	/**
	 * Sets the title of this artwork.
	 *
	 * @param title the title of this artwork
	 */
	@Override
	public void setTitle(String title) {
		_artwork.setTitle(title);
	}

	/**
	 * Sets the localized title of this artwork in the language.
	 *
	 * @param title the localized title of this artwork
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_artwork.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this artwork in the language, and sets the default locale.
	 *
	 * @param title the localized title of this artwork
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		_artwork.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_artwork.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this artwork from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this artwork
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_artwork.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this artwork from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this artwork
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		_artwork.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the user ID of this artwork.
	 *
	 * @param userId the user ID of this artwork
	 */
	@Override
	public void setUserId(long userId) {
		_artwork.setUserId(userId);
	}

	/**
	 * Sets the user name of this artwork.
	 *
	 * @param userName the user name of this artwork
	 */
	@Override
	public void setUserName(String userName) {
		_artwork.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this artwork.
	 *
	 * @param userUuid the user uuid of this artwork
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_artwork.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this artwork.
	 *
	 * @param uuid the uuid of this artwork
	 */
	@Override
	public void setUuid(String uuid) {
		_artwork.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.artwork.model.Artwork> toCacheModel() {

		return _artwork.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.artwork.model.Artwork toEscapedModel() {
		return new ArtworkWrapper(_artwork.toEscapedModel());
	}

	@Override
	public String toString() {
		return _artwork.toString();
	}

	@Override
	public eu.strasbourg.service.artwork.model.Artwork toUnescapedModel() {
		return new ArtworkWrapper(_artwork.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _artwork.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ArtworkWrapper)) {
			return false;
		}

		ArtworkWrapper artworkWrapper = (ArtworkWrapper)object;

		if (Objects.equals(_artwork, artworkWrapper._artwork)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _artwork.getStagedModelType();
	}

	@Override
	public Artwork getWrappedModel() {
		return _artwork;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _artwork.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _artwork.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_artwork.resetOriginalValues();
	}

	private final Artwork _artwork;

}
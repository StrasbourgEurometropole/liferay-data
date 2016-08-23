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
 * This class is a wrapper for {@link Artwork}.
 * </p>
 *
 * @author BenjaminBini
 * @see Artwork
 * @generated
 */
@ProviderType
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
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("image", getImage());
		attributes.put("images", getImages());
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
		attributes.put("status", getStatus());

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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String image = (String)attributes.get("image");

		if (image != null) {
			setImage(image);
		}

		String images = (String)attributes.get("images");

		if (images != null) {
			setImages(images);
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

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the status of this artwork.
	*
	* @return the status of this artwork
	*/
	@Override
	public boolean getStatus() {
		return _artwork.getStatus();
	}

	@Override
	public boolean isCachedModel() {
		return _artwork.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _artwork.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _artwork.isNew();
	}

	/**
	* Returns <code>true</code> if this artwork is status.
	*
	* @return <code>true</code> if this artwork is status; <code>false</code> otherwise
	*/
	@Override
	public boolean isStatus() {
		return _artwork.isStatus();
	}

	/**
	* Retourne l'AssetEntry correspondant � cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artwork.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _artwork.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.artwork.model.Artwork> toCacheModel() {
		return _artwork.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.artwork.model.Artwork toEscapedModel() {
		return new ArtworkWrapper(_artwork.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.artwork.model.Artwork toUnescapedModel() {
		return new ArtworkWrapper(_artwork.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.artwork.model.Artwork artwork) {
		return _artwork.compareTo(artwork);
	}

	@Override
	public int hashCode() {
		return _artwork.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _artwork.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ArtworkWrapper((Artwork)_artwork.clone());
	}

	/**
	* Returns the artist name of this artwork.
	*
	* @return the artist name of this artwork
	*/
	@Override
	public java.lang.String getArtistName() {
		return _artwork.getArtistName();
	}

	/**
	* Returns the localized artist name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized artist name of this artwork
	*/
	@Override
	public java.lang.String getArtistName(java.lang.String languageId) {
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
	public java.lang.String getArtistName(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getArtistName(languageId, useDefault);
	}

	/**
	* Returns the localized artist name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized artist name of this artwork
	*/
	@Override
	public java.lang.String getArtistName(java.util.Locale locale) {
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
	public java.lang.String getArtistName(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getArtistName(locale, useDefault);
	}

	@Override
	public java.lang.String getArtistNameCurrentLanguageId() {
		return _artwork.getArtistNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getArtistNameCurrentValue() {
		return _artwork.getArtistNameCurrentValue();
	}

	/**
	* Returns the creation year of this artwork.
	*
	* @return the creation year of this artwork
	*/
	@Override
	public java.lang.String getCreationYear() {
		return _artwork.getCreationYear();
	}

	/**
	* Returns the localized creation year of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized creation year of this artwork
	*/
	@Override
	public java.lang.String getCreationYear(java.lang.String languageId) {
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
	public java.lang.String getCreationYear(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getCreationYear(languageId, useDefault);
	}

	/**
	* Returns the localized creation year of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized creation year of this artwork
	*/
	@Override
	public java.lang.String getCreationYear(java.util.Locale locale) {
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
	public java.lang.String getCreationYear(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getCreationYear(locale, useDefault);
	}

	@Override
	public java.lang.String getCreationYearCurrentLanguageId() {
		return _artwork.getCreationYearCurrentLanguageId();
	}

	@Override
	public java.lang.String getCreationYearCurrentValue() {
		return _artwork.getCreationYearCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _artwork.getDefaultLanguageId();
	}

	/**
	* Returns the description of this artwork.
	*
	* @return the description of this artwork
	*/
	@Override
	public java.lang.String getDescription() {
		return _artwork.getDescription();
	}

	/**
	* Returns the localized description of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this artwork
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
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
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this artwork
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
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
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _artwork.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _artwork.getDescriptionCurrentValue();
	}

	/**
	* Returns the exhibition name of this artwork.
	*
	* @return the exhibition name of this artwork
	*/
	@Override
	public java.lang.String getExhibitionName() {
		return _artwork.getExhibitionName();
	}

	/**
	* Returns the localized exhibition name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized exhibition name of this artwork
	*/
	@Override
	public java.lang.String getExhibitionName(java.lang.String languageId) {
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
	public java.lang.String getExhibitionName(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getExhibitionName(languageId, useDefault);
	}

	/**
	* Returns the localized exhibition name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized exhibition name of this artwork
	*/
	@Override
	public java.lang.String getExhibitionName(java.util.Locale locale) {
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
	public java.lang.String getExhibitionName(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getExhibitionName(locale, useDefault);
	}

	@Override
	public java.lang.String getExhibitionNameCurrentLanguageId() {
		return _artwork.getExhibitionNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getExhibitionNameCurrentValue() {
		return _artwork.getExhibitionNameCurrentValue();
	}

	/**
	* Returns the exhibition place of this artwork.
	*
	* @return the exhibition place of this artwork
	*/
	@Override
	public java.lang.String getExhibitionPlace() {
		return _artwork.getExhibitionPlace();
	}

	/**
	* Returns the localized exhibition place of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized exhibition place of this artwork
	*/
	@Override
	public java.lang.String getExhibitionPlace(java.lang.String languageId) {
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
	public java.lang.String getExhibitionPlace(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getExhibitionPlace(languageId, useDefault);
	}

	/**
	* Returns the localized exhibition place of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized exhibition place of this artwork
	*/
	@Override
	public java.lang.String getExhibitionPlace(java.util.Locale locale) {
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
	public java.lang.String getExhibitionPlace(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getExhibitionPlace(locale, useDefault);
	}

	@Override
	public java.lang.String getExhibitionPlaceCurrentLanguageId() {
		return _artwork.getExhibitionPlaceCurrentLanguageId();
	}

	@Override
	public java.lang.String getExhibitionPlaceCurrentValue() {
		return _artwork.getExhibitionPlaceCurrentValue();
	}

	/**
	* Returns the image of this artwork.
	*
	* @return the image of this artwork
	*/
	@Override
	public java.lang.String getImage() {
		return _artwork.getImage();
	}

	/**
	* Returns the images of this artwork.
	*
	* @return the images of this artwork
	*/
	@Override
	public java.lang.String getImages() {
		return _artwork.getImages();
	}

	/**
	* Returns the link of this artwork.
	*
	* @return the link of this artwork
	*/
	@Override
	public java.lang.String getLink() {
		return _artwork.getLink();
	}

	/**
	* Returns the localized link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized link of this artwork
	*/
	@Override
	public java.lang.String getLink(java.lang.String languageId) {
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
	public java.lang.String getLink(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getLink(languageId, useDefault);
	}

	/**
	* Returns the localized link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized link of this artwork
	*/
	@Override
	public java.lang.String getLink(java.util.Locale locale) {
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
	public java.lang.String getLink(java.util.Locale locale, boolean useDefault) {
		return _artwork.getLink(locale, useDefault);
	}

	@Override
	public java.lang.String getLinkCurrentLanguageId() {
		return _artwork.getLinkCurrentLanguageId();
	}

	@Override
	public java.lang.String getLinkCurrentValue() {
		return _artwork.getLinkCurrentValue();
	}

	/**
	* Returns the link name of this artwork.
	*
	* @return the link name of this artwork
	*/
	@Override
	public java.lang.String getLinkName() {
		return _artwork.getLinkName();
	}

	/**
	* Returns the localized link name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized link name of this artwork
	*/
	@Override
	public java.lang.String getLinkName(java.lang.String languageId) {
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
	public java.lang.String getLinkName(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getLinkName(languageId, useDefault);
	}

	/**
	* Returns the localized link name of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized link name of this artwork
	*/
	@Override
	public java.lang.String getLinkName(java.util.Locale locale) {
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
	public java.lang.String getLinkName(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getLinkName(locale, useDefault);
	}

	@Override
	public java.lang.String getLinkNameCurrentLanguageId() {
		return _artwork.getLinkNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getLinkNameCurrentValue() {
		return _artwork.getLinkNameCurrentValue();
	}

	/**
	* Returns the loan period of this artwork.
	*
	* @return the loan period of this artwork
	*/
	@Override
	public java.lang.String getLoanPeriod() {
		return _artwork.getLoanPeriod();
	}

	/**
	* Returns the localized loan period of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized loan period of this artwork
	*/
	@Override
	public java.lang.String getLoanPeriod(java.lang.String languageId) {
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
	public java.lang.String getLoanPeriod(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getLoanPeriod(languageId, useDefault);
	}

	/**
	* Returns the localized loan period of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized loan period of this artwork
	*/
	@Override
	public java.lang.String getLoanPeriod(java.util.Locale locale) {
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
	public java.lang.String getLoanPeriod(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getLoanPeriod(locale, useDefault);
	}

	@Override
	public java.lang.String getLoanPeriodCurrentLanguageId() {
		return _artwork.getLoanPeriodCurrentLanguageId();
	}

	@Override
	public java.lang.String getLoanPeriodCurrentValue() {
		return _artwork.getLoanPeriodCurrentValue();
	}

	/**
	* Returns the notice link of this artwork.
	*
	* @return the notice link of this artwork
	*/
	@Override
	public java.lang.String getNoticeLink() {
		return _artwork.getNoticeLink();
	}

	/**
	* Returns the localized notice link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized notice link of this artwork
	*/
	@Override
	public java.lang.String getNoticeLink(java.lang.String languageId) {
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
	public java.lang.String getNoticeLink(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getNoticeLink(languageId, useDefault);
	}

	/**
	* Returns the localized notice link of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized notice link of this artwork
	*/
	@Override
	public java.lang.String getNoticeLink(java.util.Locale locale) {
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
	public java.lang.String getNoticeLink(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getNoticeLink(locale, useDefault);
	}

	@Override
	public java.lang.String getNoticeLinkCurrentLanguageId() {
		return _artwork.getNoticeLinkCurrentLanguageId();
	}

	@Override
	public java.lang.String getNoticeLinkCurrentValue() {
		return _artwork.getNoticeLinkCurrentValue();
	}

	/**
	* Returns the origin of this artwork.
	*
	* @return the origin of this artwork
	*/
	@Override
	public java.lang.String getOrigin() {
		return _artwork.getOrigin();
	}

	/**
	* Returns the localized origin of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized origin of this artwork
	*/
	@Override
	public java.lang.String getOrigin(java.lang.String languageId) {
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
	public java.lang.String getOrigin(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getOrigin(languageId, useDefault);
	}

	/**
	* Returns the localized origin of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized origin of this artwork
	*/
	@Override
	public java.lang.String getOrigin(java.util.Locale locale) {
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
	public java.lang.String getOrigin(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getOrigin(locale, useDefault);
	}

	@Override
	public java.lang.String getOriginCurrentLanguageId() {
		return _artwork.getOriginCurrentLanguageId();
	}

	@Override
	public java.lang.String getOriginCurrentValue() {
		return _artwork.getOriginCurrentValue();
	}

	/**
	* Returns the technical information of this artwork.
	*
	* @return the technical information of this artwork
	*/
	@Override
	public java.lang.String getTechnicalInformation() {
		return _artwork.getTechnicalInformation();
	}

	/**
	* Returns the localized technical information of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized technical information of this artwork
	*/
	@Override
	public java.lang.String getTechnicalInformation(java.lang.String languageId) {
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
	public java.lang.String getTechnicalInformation(
		java.lang.String languageId, boolean useDefault) {
		return _artwork.getTechnicalInformation(languageId, useDefault);
	}

	/**
	* Returns the localized technical information of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized technical information of this artwork
	*/
	@Override
	public java.lang.String getTechnicalInformation(java.util.Locale locale) {
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
	public java.lang.String getTechnicalInformation(java.util.Locale locale,
		boolean useDefault) {
		return _artwork.getTechnicalInformation(locale, useDefault);
	}

	@Override
	public java.lang.String getTechnicalInformationCurrentLanguageId() {
		return _artwork.getTechnicalInformationCurrentLanguageId();
	}

	@Override
	public java.lang.String getTechnicalInformationCurrentValue() {
		return _artwork.getTechnicalInformationCurrentValue();
	}

	/**
	* Returns the title of this artwork.
	*
	* @return the title of this artwork
	*/
	@Override
	public java.lang.String getTitle() {
		return _artwork.getTitle();
	}

	/**
	* Returns the localized title of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this artwork
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
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
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _artwork.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this artwork in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this artwork
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
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
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _artwork.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _artwork.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _artwork.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this artwork.
	*
	* @return the user name of this artwork
	*/
	@Override
	public java.lang.String getUserName() {
		return _artwork.getUserName();
	}

	/**
	* Returns the user uuid of this artwork.
	*
	* @return the user uuid of this artwork
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _artwork.getUserUuid();
	}

	/**
	* Returns the uuid of this artwork.
	*
	* @return the uuid of this artwork
	*/
	@Override
	public java.lang.String getUuid() {
		return _artwork.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _artwork.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _artwork.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _artwork.getAvailableLanguageIds();
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
	* Returns the modified date of this artwork.
	*
	* @return the modified date of this artwork
	*/
	@Override
	public Date getModifiedDate() {
		return _artwork.getModifiedDate();
	}

	/**
	* Retourne la liste des AssetCategory correspondant � cet item (via l'AssetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _artwork.getCategories();
	}

	/**
	* Returns a map of the locales and localized artist names of this artwork.
	*
	* @return the locales and localized artist names of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getArtistNameMap() {
		return _artwork.getArtistNameMap();
	}

	/**
	* Returns a map of the locales and localized creation years of this artwork.
	*
	* @return the locales and localized creation years of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getCreationYearMap() {
		return _artwork.getCreationYearMap();
	}

	/**
	* Returns a map of the locales and localized descriptions of this artwork.
	*
	* @return the locales and localized descriptions of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _artwork.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized exhibition names of this artwork.
	*
	* @return the locales and localized exhibition names of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getExhibitionNameMap() {
		return _artwork.getExhibitionNameMap();
	}

	/**
	* Returns a map of the locales and localized exhibition places of this artwork.
	*
	* @return the locales and localized exhibition places of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getExhibitionPlaceMap() {
		return _artwork.getExhibitionPlaceMap();
	}

	/**
	* Returns a map of the locales and localized links of this artwork.
	*
	* @return the locales and localized links of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLinkMap() {
		return _artwork.getLinkMap();
	}

	/**
	* Returns a map of the locales and localized link names of this artwork.
	*
	* @return the locales and localized link names of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLinkNameMap() {
		return _artwork.getLinkNameMap();
	}

	/**
	* Returns a map of the locales and localized loan periods of this artwork.
	*
	* @return the locales and localized loan periods of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getLoanPeriodMap() {
		return _artwork.getLoanPeriodMap();
	}

	/**
	* Returns a map of the locales and localized notice links of this artwork.
	*
	* @return the locales and localized notice links of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNoticeLinkMap() {
		return _artwork.getNoticeLinkMap();
	}

	/**
	* Returns a map of the locales and localized origins of this artwork.
	*
	* @return the locales and localized origins of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getOriginMap() {
		return _artwork.getOriginMap();
	}

	/**
	* Returns a map of the locales and localized technical informations of this artwork.
	*
	* @return the locales and localized technical informations of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTechnicalInformationMap() {
		return _artwork.getTechnicalInformationMap();
	}

	/**
	* Returns a map of the locales and localized titles of this artwork.
	*
	* @return the locales and localized titles of this artwork
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _artwork.getTitleMap();
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
	* Returns the company ID of this artwork.
	*
	* @return the company ID of this artwork
	*/
	@Override
	public long getCompanyId() {
		return _artwork.getCompanyId();
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
	* Returns the primary key of this artwork.
	*
	* @return the primary key of this artwork
	*/
	@Override
	public long getPrimaryKey() {
		return _artwork.getPrimaryKey();
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
	public void setArtistName(java.lang.String artistName) {
		_artwork.setArtistName(artistName);
	}

	/**
	* Sets the localized artist name of this artwork in the language.
	*
	* @param artistName the localized artist name of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setArtistName(java.lang.String artistName,
		java.util.Locale locale) {
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
	public void setArtistName(java.lang.String artistName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setArtistName(artistName, locale, defaultLocale);
	}

	@Override
	public void setArtistNameCurrentLanguageId(java.lang.String languageId) {
		_artwork.setArtistNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized artist names of this artwork from the map of locales and localized artist names.
	*
	* @param artistNameMap the locales and localized artist names of this artwork
	*/
	@Override
	public void setArtistNameMap(
		Map<java.util.Locale, java.lang.String> artistNameMap) {
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
		Map<java.util.Locale, java.lang.String> artistNameMap,
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
	public void setCreationYear(java.lang.String creationYear) {
		_artwork.setCreationYear(creationYear);
	}

	/**
	* Sets the localized creation year of this artwork in the language.
	*
	* @param creationYear the localized creation year of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setCreationYear(java.lang.String creationYear,
		java.util.Locale locale) {
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
	public void setCreationYear(java.lang.String creationYear,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setCreationYear(creationYear, locale, defaultLocale);
	}

	@Override
	public void setCreationYearCurrentLanguageId(java.lang.String languageId) {
		_artwork.setCreationYearCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized creation years of this artwork from the map of locales and localized creation years.
	*
	* @param creationYearMap the locales and localized creation years of this artwork
	*/
	@Override
	public void setCreationYearMap(
		Map<java.util.Locale, java.lang.String> creationYearMap) {
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
		Map<java.util.Locale, java.lang.String> creationYearMap,
		java.util.Locale defaultLocale) {
		_artwork.setCreationYearMap(creationYearMap, defaultLocale);
	}

	/**
	* Sets the description of this artwork.
	*
	* @param description the description of this artwork
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_artwork.setDescription(description);
	}

	/**
	* Sets the localized description of this artwork in the language.
	*
	* @param description the localized description of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
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
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_artwork.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this artwork from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this artwork
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
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
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_artwork.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the exhibition name of this artwork.
	*
	* @param exhibitionName the exhibition name of this artwork
	*/
	@Override
	public void setExhibitionName(java.lang.String exhibitionName) {
		_artwork.setExhibitionName(exhibitionName);
	}

	/**
	* Sets the localized exhibition name of this artwork in the language.
	*
	* @param exhibitionName the localized exhibition name of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setExhibitionName(java.lang.String exhibitionName,
		java.util.Locale locale) {
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
	public void setExhibitionName(java.lang.String exhibitionName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setExhibitionName(exhibitionName, locale, defaultLocale);
	}

	@Override
	public void setExhibitionNameCurrentLanguageId(java.lang.String languageId) {
		_artwork.setExhibitionNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized exhibition names of this artwork from the map of locales and localized exhibition names.
	*
	* @param exhibitionNameMap the locales and localized exhibition names of this artwork
	*/
	@Override
	public void setExhibitionNameMap(
		Map<java.util.Locale, java.lang.String> exhibitionNameMap) {
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
		Map<java.util.Locale, java.lang.String> exhibitionNameMap,
		java.util.Locale defaultLocale) {
		_artwork.setExhibitionNameMap(exhibitionNameMap, defaultLocale);
	}

	/**
	* Sets the exhibition place of this artwork.
	*
	* @param exhibitionPlace the exhibition place of this artwork
	*/
	@Override
	public void setExhibitionPlace(java.lang.String exhibitionPlace) {
		_artwork.setExhibitionPlace(exhibitionPlace);
	}

	/**
	* Sets the localized exhibition place of this artwork in the language.
	*
	* @param exhibitionPlace the localized exhibition place of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setExhibitionPlace(java.lang.String exhibitionPlace,
		java.util.Locale locale) {
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
	public void setExhibitionPlace(java.lang.String exhibitionPlace,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setExhibitionPlace(exhibitionPlace, locale, defaultLocale);
	}

	@Override
	public void setExhibitionPlaceCurrentLanguageId(java.lang.String languageId) {
		_artwork.setExhibitionPlaceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized exhibition places of this artwork from the map of locales and localized exhibition places.
	*
	* @param exhibitionPlaceMap the locales and localized exhibition places of this artwork
	*/
	@Override
	public void setExhibitionPlaceMap(
		Map<java.util.Locale, java.lang.String> exhibitionPlaceMap) {
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
		Map<java.util.Locale, java.lang.String> exhibitionPlaceMap,
		java.util.Locale defaultLocale) {
		_artwork.setExhibitionPlaceMap(exhibitionPlaceMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_artwork.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_artwork.setExpandoBridgeAttributes(baseModel);
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
	* Sets the image of this artwork.
	*
	* @param image the image of this artwork
	*/
	@Override
	public void setImage(java.lang.String image) {
		_artwork.setImage(image);
	}

	/**
	* Sets the images of this artwork.
	*
	* @param images the images of this artwork
	*/
	@Override
	public void setImages(java.lang.String images) {
		_artwork.setImages(images);
	}

	/**
	* Sets the link of this artwork.
	*
	* @param link the link of this artwork
	*/
	@Override
	public void setLink(java.lang.String link) {
		_artwork.setLink(link);
	}

	/**
	* Sets the localized link of this artwork in the language.
	*
	* @param link the localized link of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setLink(java.lang.String link, java.util.Locale locale) {
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
	public void setLink(java.lang.String link, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_artwork.setLink(link, locale, defaultLocale);
	}

	@Override
	public void setLinkCurrentLanguageId(java.lang.String languageId) {
		_artwork.setLinkCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized links of this artwork from the map of locales and localized links.
	*
	* @param linkMap the locales and localized links of this artwork
	*/
	@Override
	public void setLinkMap(Map<java.util.Locale, java.lang.String> linkMap) {
		_artwork.setLinkMap(linkMap);
	}

	/**
	* Sets the localized links of this artwork from the map of locales and localized links, and sets the default locale.
	*
	* @param linkMap the locales and localized links of this artwork
	* @param defaultLocale the default locale
	*/
	@Override
	public void setLinkMap(Map<java.util.Locale, java.lang.String> linkMap,
		java.util.Locale defaultLocale) {
		_artwork.setLinkMap(linkMap, defaultLocale);
	}

	/**
	* Sets the link name of this artwork.
	*
	* @param linkName the link name of this artwork
	*/
	@Override
	public void setLinkName(java.lang.String linkName) {
		_artwork.setLinkName(linkName);
	}

	/**
	* Sets the localized link name of this artwork in the language.
	*
	* @param linkName the localized link name of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setLinkName(java.lang.String linkName, java.util.Locale locale) {
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
	public void setLinkName(java.lang.String linkName, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_artwork.setLinkName(linkName, locale, defaultLocale);
	}

	@Override
	public void setLinkNameCurrentLanguageId(java.lang.String languageId) {
		_artwork.setLinkNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized link names of this artwork from the map of locales and localized link names.
	*
	* @param linkNameMap the locales and localized link names of this artwork
	*/
	@Override
	public void setLinkNameMap(
		Map<java.util.Locale, java.lang.String> linkNameMap) {
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
		Map<java.util.Locale, java.lang.String> linkNameMap,
		java.util.Locale defaultLocale) {
		_artwork.setLinkNameMap(linkNameMap, defaultLocale);
	}

	/**
	* Sets the loan period of this artwork.
	*
	* @param loanPeriod the loan period of this artwork
	*/
	@Override
	public void setLoanPeriod(java.lang.String loanPeriod) {
		_artwork.setLoanPeriod(loanPeriod);
	}

	/**
	* Sets the localized loan period of this artwork in the language.
	*
	* @param loanPeriod the localized loan period of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setLoanPeriod(java.lang.String loanPeriod,
		java.util.Locale locale) {
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
	public void setLoanPeriod(java.lang.String loanPeriod,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setLoanPeriod(loanPeriod, locale, defaultLocale);
	}

	@Override
	public void setLoanPeriodCurrentLanguageId(java.lang.String languageId) {
		_artwork.setLoanPeriodCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized loan periods of this artwork from the map of locales and localized loan periods.
	*
	* @param loanPeriodMap the locales and localized loan periods of this artwork
	*/
	@Override
	public void setLoanPeriodMap(
		Map<java.util.Locale, java.lang.String> loanPeriodMap) {
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
		Map<java.util.Locale, java.lang.String> loanPeriodMap,
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
	public void setNoticeLink(java.lang.String noticeLink) {
		_artwork.setNoticeLink(noticeLink);
	}

	/**
	* Sets the localized notice link of this artwork in the language.
	*
	* @param noticeLink the localized notice link of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setNoticeLink(java.lang.String noticeLink,
		java.util.Locale locale) {
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
	public void setNoticeLink(java.lang.String noticeLink,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setNoticeLink(noticeLink, locale, defaultLocale);
	}

	@Override
	public void setNoticeLinkCurrentLanguageId(java.lang.String languageId) {
		_artwork.setNoticeLinkCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized notice links of this artwork from the map of locales and localized notice links.
	*
	* @param noticeLinkMap the locales and localized notice links of this artwork
	*/
	@Override
	public void setNoticeLinkMap(
		Map<java.util.Locale, java.lang.String> noticeLinkMap) {
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
		Map<java.util.Locale, java.lang.String> noticeLinkMap,
		java.util.Locale defaultLocale) {
		_artwork.setNoticeLinkMap(noticeLinkMap, defaultLocale);
	}

	/**
	* Sets the origin of this artwork.
	*
	* @param origin the origin of this artwork
	*/
	@Override
	public void setOrigin(java.lang.String origin) {
		_artwork.setOrigin(origin);
	}

	/**
	* Sets the localized origin of this artwork in the language.
	*
	* @param origin the localized origin of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setOrigin(java.lang.String origin, java.util.Locale locale) {
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
	public void setOrigin(java.lang.String origin, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_artwork.setOrigin(origin, locale, defaultLocale);
	}

	@Override
	public void setOriginCurrentLanguageId(java.lang.String languageId) {
		_artwork.setOriginCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized origins of this artwork from the map of locales and localized origins.
	*
	* @param originMap the locales and localized origins of this artwork
	*/
	@Override
	public void setOriginMap(Map<java.util.Locale, java.lang.String> originMap) {
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
		Map<java.util.Locale, java.lang.String> originMap,
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
	* Sets whether this artwork is status.
	*
	* @param status the status of this artwork
	*/
	@Override
	public void setStatus(boolean status) {
		_artwork.setStatus(status);
	}

	/**
	* Sets the technical information of this artwork.
	*
	* @param technicalInformation the technical information of this artwork
	*/
	@Override
	public void setTechnicalInformation(java.lang.String technicalInformation) {
		_artwork.setTechnicalInformation(technicalInformation);
	}

	/**
	* Sets the localized technical information of this artwork in the language.
	*
	* @param technicalInformation the localized technical information of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setTechnicalInformation(java.lang.String technicalInformation,
		java.util.Locale locale) {
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
	public void setTechnicalInformation(java.lang.String technicalInformation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_artwork.setTechnicalInformation(technicalInformation, locale,
			defaultLocale);
	}

	@Override
	public void setTechnicalInformationCurrentLanguageId(
		java.lang.String languageId) {
		_artwork.setTechnicalInformationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized technical informations of this artwork from the map of locales and localized technical informations.
	*
	* @param technicalInformationMap the locales and localized technical informations of this artwork
	*/
	@Override
	public void setTechnicalInformationMap(
		Map<java.util.Locale, java.lang.String> technicalInformationMap) {
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
		Map<java.util.Locale, java.lang.String> technicalInformationMap,
		java.util.Locale defaultLocale) {
		_artwork.setTechnicalInformationMap(technicalInformationMap,
			defaultLocale);
	}

	/**
	* Sets the title of this artwork.
	*
	* @param title the title of this artwork
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_artwork.setTitle(title);
	}

	/**
	* Sets the localized title of this artwork in the language.
	*
	* @param title the localized title of this artwork
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
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
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_artwork.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_artwork.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this artwork from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this artwork
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_artwork.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this artwork from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this artwork
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
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
	public void setUserName(java.lang.String userName) {
		_artwork.setUserName(userName);
	}

	/**
	* Sets the user uuid of this artwork.
	*
	* @param userUuid the user uuid of this artwork
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_artwork.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this artwork.
	*
	* @param uuid the uuid of this artwork
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_artwork.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtworkWrapper)) {
			return false;
		}

		ArtworkWrapper artworkWrapper = (ArtworkWrapper)obj;

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
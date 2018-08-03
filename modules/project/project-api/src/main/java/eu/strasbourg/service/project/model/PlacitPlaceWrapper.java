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

package eu.strasbourg.service.project.model;

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
 * This class is a wrapper for {@link PlacitPlace}.
 * </p>
 *
 * @author Cedric Henry
 * @see PlacitPlace
 * @generated
 */
@ProviderType
public class PlacitPlaceWrapper implements PlacitPlace,
	ModelWrapper<PlacitPlace> {
	public PlacitPlaceWrapper(PlacitPlace placitPlace) {
		_placitPlace = placitPlace;
	}

	@Override
	public Class<?> getModelClass() {
		return PlacitPlace.class;
	}

	@Override
	public String getModelClassName() {
		return PlacitPlace.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("placitPlaceId", getPlacitPlaceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("placeName", getPlaceName());
		attributes.put("placeStreetNumber", getPlaceStreetNumber());
		attributes.put("placeStreetName", getPlaceStreetName());
		attributes.put("placeZipCode", getPlaceZipCode());
		attributes.put("placeCityId", getPlaceCityId());
		attributes.put("imageId", getImageId());
		attributes.put("projectId", getProjectId());
		attributes.put("participationId", getParticipationId());
		attributes.put("petitionId", getPetitionId());
		attributes.put("placeSIGId", getPlaceSIGId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long placitPlaceId = (Long)attributes.get("placitPlaceId");

		if (placitPlaceId != null) {
			setPlacitPlaceId(placitPlaceId);
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

		String placeName = (String)attributes.get("placeName");

		if (placeName != null) {
			setPlaceName(placeName);
		}

		String placeStreetNumber = (String)attributes.get("placeStreetNumber");

		if (placeStreetNumber != null) {
			setPlaceStreetNumber(placeStreetNumber);
		}

		String placeStreetName = (String)attributes.get("placeStreetName");

		if (placeStreetName != null) {
			setPlaceStreetName(placeStreetName);
		}

		String placeZipCode = (String)attributes.get("placeZipCode");

		if (placeZipCode != null) {
			setPlaceZipCode(placeZipCode);
		}

		Long placeCityId = (Long)attributes.get("placeCityId");

		if (placeCityId != null) {
			setPlaceCityId(placeCityId);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long participationId = (Long)attributes.get("participationId");

		if (participationId != null) {
			setParticipationId(participationId);
		}

		Long petitionId = (Long)attributes.get("petitionId");

		if (petitionId != null) {
			setPetitionId(petitionId);
		}

		String placeSIGId = (String)attributes.get("placeSIGId");

		if (placeSIGId != null) {
			setPlaceSIGId(placeSIGId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _placitPlace.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _placitPlace.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _placitPlace.isNew();
	}

	/**
	* Renvoie si le lieu placit est SIG ou manuel
	*
	* @return True : lieu SIG ; False : lieu manuel
	*/
	@Override
	public boolean isSIG() {
		return _placitPlace.isSIG();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _placitPlace.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _placitPlace.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.PlacitPlace> toCacheModel() {
		return _placitPlace.toCacheModel();
	}

	/**
	* Retourne la participation du lieu Placit
	*/
	@Override
	public eu.strasbourg.service.project.model.Participation getParticipation() {
		return _placitPlace.getParticipation();
	}

	@Override
	public eu.strasbourg.service.project.model.PlacitPlace toEscapedModel() {
		return new PlacitPlaceWrapper(_placitPlace.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.PlacitPlace toUnescapedModel() {
		return new PlacitPlaceWrapper(_placitPlace.toUnescapedModel());
	}

	/**
	* Retourne le projet du lieu Placit
	*/
	@Override
	public eu.strasbourg.service.project.model.Project getProject() {
		return _placitPlace.getProject();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.PlacitPlace placitPlace) {
		return _placitPlace.compareTo(placitPlace);
	}

	@Override
	public int hashCode() {
		return _placitPlace.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _placitPlace.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PlacitPlaceWrapper((PlacitPlace)_placitPlace.clone());
	}

	/**
	* Retourne l'adresse (num + rue) du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getAddress() {
		return _placitPlace.getAddress();
	}

	/**
	* Retourne la ville du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getCity(java.util.Locale locale) {
		return _placitPlace.getCity(locale);
	}

	/**
	* Retourne l'adresse complete du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getCompleteAddress(java.util.Locale locale) {
		return _placitPlace.getCompleteAddress(locale);
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _placitPlace.getDefaultLanguageId();
	}

	/**
	* Retourne l'ID de l'image du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getImageURL() {
		return _placitPlace.getImageURL();
	}

	/**
	* Retourne le nom du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getPlaceAlias(java.util.Locale locale) {
		return _placitPlace.getPlaceAlias(locale);
	}

	/**
	* Returns the place name of this placit place.
	*
	* @return the place name of this placit place
	*/
	@Override
	public java.lang.String getPlaceName() {
		return _placitPlace.getPlaceName();
	}

	/**
	* Returns the localized place name of this placit place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized place name of this placit place
	*/
	@Override
	public java.lang.String getPlaceName(java.lang.String languageId) {
		return _placitPlace.getPlaceName(languageId);
	}

	/**
	* Returns the localized place name of this placit place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized place name of this placit place
	*/
	@Override
	public java.lang.String getPlaceName(java.lang.String languageId,
		boolean useDefault) {
		return _placitPlace.getPlaceName(languageId, useDefault);
	}

	/**
	* Returns the localized place name of this placit place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized place name of this placit place
	*/
	@Override
	public java.lang.String getPlaceName(java.util.Locale locale) {
		return _placitPlace.getPlaceName(locale);
	}

	/**
	* Returns the localized place name of this placit place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized place name of this placit place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPlaceName(java.util.Locale locale,
		boolean useDefault) {
		return _placitPlace.getPlaceName(locale, useDefault);
	}

	@Override
	public java.lang.String getPlaceNameCurrentLanguageId() {
		return _placitPlace.getPlaceNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getPlaceNameCurrentValue() {
		return _placitPlace.getPlaceNameCurrentValue();
	}

	/**
	* Returns the place sig ID of this placit place.
	*
	* @return the place sig ID of this placit place
	*/
	@Override
	public java.lang.String getPlaceSIGId() {
		return _placitPlace.getPlaceSIGId();
	}

	/**
	* Returns the place street name of this placit place.
	*
	* @return the place street name of this placit place
	*/
	@Override
	public java.lang.String getPlaceStreetName() {
		return _placitPlace.getPlaceStreetName();
	}

	/**
	* Returns the place street number of this placit place.
	*
	* @return the place street number of this placit place
	*/
	@Override
	public java.lang.String getPlaceStreetNumber() {
		return _placitPlace.getPlaceStreetNumber();
	}

	/**
	* Returns the place zip code of this placit place.
	*
	* @return the place zip code of this placit place
	*/
	@Override
	public java.lang.String getPlaceZipCode() {
		return _placitPlace.getPlaceZipCode();
	}

	/**
	* Retourne le nom du lieu SIG
	*/
	@Override
	public java.lang.String getSIGPlaceAlias(java.util.Locale locale) {
		return _placitPlace.getSIGPlaceAlias(locale);
	}

	/**
	* Returns the user name of this placit place.
	*
	* @return the user name of this placit place
	*/
	@Override
	public java.lang.String getUserName() {
		return _placitPlace.getUserName();
	}

	/**
	* Returns the user uuid of this placit place.
	*
	* @return the user uuid of this placit place
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _placitPlace.getUserUuid();
	}

	/**
	* Returns the uuid of this placit place.
	*
	* @return the uuid of this placit place
	*/
	@Override
	public java.lang.String getUuid() {
		return _placitPlace.getUuid();
	}

	/**
	* Retourne le code postal du lieu SIG ou "manuel"
	*/
	@Override
	public java.lang.String getZipCode() {
		return _placitPlace.getZipCode();
	}

	@Override
	public java.lang.String toString() {
		return _placitPlace.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _placitPlace.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _placitPlace.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this placit place.
	*
	* @return the create date of this placit place
	*/
	@Override
	public Date getCreateDate() {
		return _placitPlace.getCreateDate();
	}

	/**
	* Returns the modified date of this placit place.
	*
	* @return the modified date of this placit place
	*/
	@Override
	public Date getModifiedDate() {
		return _placitPlace.getModifiedDate();
	}

	/**
	* Retourne la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _placitPlace.getCategories();
	}

	/**
	* Returns a map of the locales and localized place names of this placit place.
	*
	* @return the locales and localized place names of this placit place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPlaceNameMap() {
		return _placitPlace.getPlaceNameMap();
	}

	/**
	* Returns the company ID of this placit place.
	*
	* @return the company ID of this placit place
	*/
	@Override
	public long getCompanyId() {
		return _placitPlace.getCompanyId();
	}

	/**
	* Returns the group ID of this placit place.
	*
	* @return the group ID of this placit place
	*/
	@Override
	public long getGroupId() {
		return _placitPlace.getGroupId();
	}

	/**
	* Returns the image ID of this placit place.
	*
	* @return the image ID of this placit place
	*/
	@Override
	public long getImageId() {
		return _placitPlace.getImageId();
	}

	/**
	* Returns the participation ID of this placit place.
	*
	* @return the participation ID of this placit place
	*/
	@Override
	public long getParticipationId() {
		return _placitPlace.getParticipationId();
	}

	/**
	* Returns the petition ID of this placit place.
	*
	* @return the petition ID of this placit place
	*/
	@Override
	public long getPetitionId() {
		return _placitPlace.getPetitionId();
	}

	/**
	* Returns the place city ID of this placit place.
	*
	* @return the place city ID of this placit place
	*/
	@Override
	public long getPlaceCityId() {
		return _placitPlace.getPlaceCityId();
	}

	/**
	* Returns the placit place ID of this placit place.
	*
	* @return the placit place ID of this placit place
	*/
	@Override
	public long getPlacitPlaceId() {
		return _placitPlace.getPlacitPlaceId();
	}

	/**
	* Returns the primary key of this placit place.
	*
	* @return the primary key of this placit place
	*/
	@Override
	public long getPrimaryKey() {
		return _placitPlace.getPrimaryKey();
	}

	/**
	* Returns the project ID of this placit place.
	*
	* @return the project ID of this placit place
	*/
	@Override
	public long getProjectId() {
		return _placitPlace.getProjectId();
	}

	/**
	* Returns the user ID of this placit place.
	*
	* @return the user ID of this placit place
	*/
	@Override
	public long getUserId() {
		return _placitPlace.getUserId();
	}

	@Override
	public void persist() {
		_placitPlace.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_placitPlace.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_placitPlace.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_placitPlace.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this placit place.
	*
	* @param companyId the company ID of this placit place
	*/
	@Override
	public void setCompanyId(long companyId) {
		_placitPlace.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this placit place.
	*
	* @param createDate the create date of this placit place
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_placitPlace.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_placitPlace.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_placitPlace.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_placitPlace.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this placit place.
	*
	* @param groupId the group ID of this placit place
	*/
	@Override
	public void setGroupId(long groupId) {
		_placitPlace.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this placit place.
	*
	* @param imageId the image ID of this placit place
	*/
	@Override
	public void setImageId(long imageId) {
		_placitPlace.setImageId(imageId);
	}

	/**
	* Sets the modified date of this placit place.
	*
	* @param modifiedDate the modified date of this placit place
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_placitPlace.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_placitPlace.setNew(n);
	}

	/**
	* Sets the participation ID of this placit place.
	*
	* @param participationId the participation ID of this placit place
	*/
	@Override
	public void setParticipationId(long participationId) {
		_placitPlace.setParticipationId(participationId);
	}

	/**
	* Sets the petition ID of this placit place.
	*
	* @param petitionId the petition ID of this placit place
	*/
	@Override
	public void setPetitionId(long petitionId) {
		_placitPlace.setPetitionId(petitionId);
	}

	/**
	* Sets the place city ID of this placit place.
	*
	* @param placeCityId the place city ID of this placit place
	*/
	@Override
	public void setPlaceCityId(long placeCityId) {
		_placitPlace.setPlaceCityId(placeCityId);
	}

	/**
	* Sets the place name of this placit place.
	*
	* @param placeName the place name of this placit place
	*/
	@Override
	public void setPlaceName(java.lang.String placeName) {
		_placitPlace.setPlaceName(placeName);
	}

	/**
	* Sets the localized place name of this placit place in the language.
	*
	* @param placeName the localized place name of this placit place
	* @param locale the locale of the language
	*/
	@Override
	public void setPlaceName(java.lang.String placeName, java.util.Locale locale) {
		_placitPlace.setPlaceName(placeName, locale);
	}

	/**
	* Sets the localized place name of this placit place in the language, and sets the default locale.
	*
	* @param placeName the localized place name of this placit place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPlaceName(java.lang.String placeName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_placitPlace.setPlaceName(placeName, locale, defaultLocale);
	}

	@Override
	public void setPlaceNameCurrentLanguageId(java.lang.String languageId) {
		_placitPlace.setPlaceNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized place names of this placit place from the map of locales and localized place names.
	*
	* @param placeNameMap the locales and localized place names of this placit place
	*/
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, java.lang.String> placeNameMap) {
		_placitPlace.setPlaceNameMap(placeNameMap);
	}

	/**
	* Sets the localized place names of this placit place from the map of locales and localized place names, and sets the default locale.
	*
	* @param placeNameMap the locales and localized place names of this placit place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, java.lang.String> placeNameMap,
		java.util.Locale defaultLocale) {
		_placitPlace.setPlaceNameMap(placeNameMap, defaultLocale);
	}

	/**
	* Sets the place sig ID of this placit place.
	*
	* @param placeSIGId the place sig ID of this placit place
	*/
	@Override
	public void setPlaceSIGId(java.lang.String placeSIGId) {
		_placitPlace.setPlaceSIGId(placeSIGId);
	}

	/**
	* Sets the place street name of this placit place.
	*
	* @param placeStreetName the place street name of this placit place
	*/
	@Override
	public void setPlaceStreetName(java.lang.String placeStreetName) {
		_placitPlace.setPlaceStreetName(placeStreetName);
	}

	/**
	* Sets the place street number of this placit place.
	*
	* @param placeStreetNumber the place street number of this placit place
	*/
	@Override
	public void setPlaceStreetNumber(java.lang.String placeStreetNumber) {
		_placitPlace.setPlaceStreetNumber(placeStreetNumber);
	}

	/**
	* Sets the place zip code of this placit place.
	*
	* @param placeZipCode the place zip code of this placit place
	*/
	@Override
	public void setPlaceZipCode(java.lang.String placeZipCode) {
		_placitPlace.setPlaceZipCode(placeZipCode);
	}

	/**
	* Sets the placit place ID of this placit place.
	*
	* @param placitPlaceId the placit place ID of this placit place
	*/
	@Override
	public void setPlacitPlaceId(long placitPlaceId) {
		_placitPlace.setPlacitPlaceId(placitPlaceId);
	}

	/**
	* Sets the primary key of this placit place.
	*
	* @param primaryKey the primary key of this placit place
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_placitPlace.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_placitPlace.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this placit place.
	*
	* @param projectId the project ID of this placit place
	*/
	@Override
	public void setProjectId(long projectId) {
		_placitPlace.setProjectId(projectId);
	}

	/**
	* Sets the user ID of this placit place.
	*
	* @param userId the user ID of this placit place
	*/
	@Override
	public void setUserId(long userId) {
		_placitPlace.setUserId(userId);
	}

	/**
	* Sets the user name of this placit place.
	*
	* @param userName the user name of this placit place
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_placitPlace.setUserName(userName);
	}

	/**
	* Sets the user uuid of this placit place.
	*
	* @param userUuid the user uuid of this placit place
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_placitPlace.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this placit place.
	*
	* @param uuid the uuid of this placit place
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_placitPlace.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PlacitPlaceWrapper)) {
			return false;
		}

		PlacitPlaceWrapper placitPlaceWrapper = (PlacitPlaceWrapper)obj;

		if (Objects.equals(_placitPlace, placitPlaceWrapper._placitPlace)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _placitPlace.getStagedModelType();
	}

	@Override
	public PlacitPlace getWrappedModel() {
		return _placitPlace;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _placitPlace.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _placitPlace.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_placitPlace.resetOriginalValues();
	}

	private final PlacitPlace _placitPlace;
}
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

package eu.strasbourg.service.place.model;

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
 * This class is a wrapper for {@link Place}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Place
 * @generated
 */
@ProviderType
public class PlaceWrapper implements Place, ModelWrapper<Place> {
	public PlaceWrapper(Place place) {
		_place = place;
	}

	@Override
	public Class<?> getModelClass() {
		return Place.class;
	}

	@Override
	public String getModelClassName() {
		return Place.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("placeId", getPlaceId());
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
		attributes.put("SIGid", getSIGid());
		attributes.put("name", getName());
		attributes.put("addressComplement", getAddressComplement());
		attributes.put("addressStreet", getAddressStreet());
		attributes.put("addressDistribution", getAddressDistribution());
		attributes.put("addressZipCode", getAddressZipCode());
		attributes.put("addressCountry", getAddressCountry());
		attributes.put("mercatorX", getMercatorX());
		attributes.put("mercatorY", getMercatorY());
		attributes.put("RGF93X", getRGF93X());
		attributes.put("RGF93Y", getRGF93Y());
		attributes.put("alias", getAlias());
		attributes.put("presentation", getPresentation());
		attributes.put("serviceAndActivities", getServiceAndActivities());
		attributes.put("characteristics", getCharacteristics());
		attributes.put("subjectToPublicHoliday", getSubjectToPublicHoliday());
		attributes.put("exceptionalSchedule", getExceptionalSchedule());
		attributes.put("displayEvents", getDisplayEvents());
		attributes.put("additionalInformation", getAdditionalInformation());
		attributes.put("phone", getPhone());
		attributes.put("mail", getMail());
		attributes.put("siteURL", getSiteURL());
		attributes.put("siteLabel", getSiteLabel());
		attributes.put("facebookURL", getFacebookURL());
		attributes.put("facebookLabel", getFacebookLabel());
		attributes.put("accesMap", getAccesMap());
		attributes.put("access", getAccess());
		attributes.put("accessForDisabled", getAccessForDisabled());
		attributes.put("accessForBlind", getAccessForBlind());
		attributes.put("accessForDeaf", getAccessForDeaf());
		attributes.put("accessForWheelchair", getAccessForWheelchair());
		attributes.put("accessForElder", getAccessForElder());
		attributes.put("accessForDeficient", getAccessForDeficient());
		attributes.put("RTExternalId", getRTExternalId());
		attributes.put("occupation", getOccupation());
		attributes.put("occupationLastUpdate", getOccupationLastUpdate());
		attributes.put("imageId", getImageId());
		attributes.put("imageIds", getImageIds());
		attributes.put("videosIds", getVideosIds());
		attributes.put("priceId", getPriceId());
		attributes.put("documentsIds", getDocumentsIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long placeId = (Long)attributes.get("placeId");

		if (placeId != null) {
			setPlaceId(placeId);
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

		String SIGid = (String)attributes.get("SIGid");

		if (SIGid != null) {
			setSIGid(SIGid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String addressComplement = (String)attributes.get("addressComplement");

		if (addressComplement != null) {
			setAddressComplement(addressComplement);
		}

		String addressStreet = (String)attributes.get("addressStreet");

		if (addressStreet != null) {
			setAddressStreet(addressStreet);
		}

		String addressDistribution = (String)attributes.get(
				"addressDistribution");

		if (addressDistribution != null) {
			setAddressDistribution(addressDistribution);
		}

		String addressZipCode = (String)attributes.get("addressZipCode");

		if (addressZipCode != null) {
			setAddressZipCode(addressZipCode);
		}

		String addressCountry = (String)attributes.get("addressCountry");

		if (addressCountry != null) {
			setAddressCountry(addressCountry);
		}

		String mercatorX = (String)attributes.get("mercatorX");

		if (mercatorX != null) {
			setMercatorX(mercatorX);
		}

		String mercatorY = (String)attributes.get("mercatorY");

		if (mercatorY != null) {
			setMercatorY(mercatorY);
		}

		String RGF93X = (String)attributes.get("RGF93X");

		if (RGF93X != null) {
			setRGF93X(RGF93X);
		}

		String RGF93Y = (String)attributes.get("RGF93Y");

		if (RGF93Y != null) {
			setRGF93Y(RGF93Y);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String presentation = (String)attributes.get("presentation");

		if (presentation != null) {
			setPresentation(presentation);
		}

		String serviceAndActivities = (String)attributes.get(
				"serviceAndActivities");

		if (serviceAndActivities != null) {
			setServiceAndActivities(serviceAndActivities);
		}

		String characteristics = (String)attributes.get("characteristics");

		if (characteristics != null) {
			setCharacteristics(characteristics);
		}

		Boolean subjectToPublicHoliday = (Boolean)attributes.get(
				"subjectToPublicHoliday");

		if (subjectToPublicHoliday != null) {
			setSubjectToPublicHoliday(subjectToPublicHoliday);
		}

		String exceptionalSchedule = (String)attributes.get(
				"exceptionalSchedule");

		if (exceptionalSchedule != null) {
			setExceptionalSchedule(exceptionalSchedule);
		}

		Boolean displayEvents = (Boolean)attributes.get("displayEvents");

		if (displayEvents != null) {
			setDisplayEvents(displayEvents);
		}

		String additionalInformation = (String)attributes.get(
				"additionalInformation");

		if (additionalInformation != null) {
			setAdditionalInformation(additionalInformation);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		String siteURL = (String)attributes.get("siteURL");

		if (siteURL != null) {
			setSiteURL(siteURL);
		}

		String siteLabel = (String)attributes.get("siteLabel");

		if (siteLabel != null) {
			setSiteLabel(siteLabel);
		}

		String facebookURL = (String)attributes.get("facebookURL");

		if (facebookURL != null) {
			setFacebookURL(facebookURL);
		}

		String facebookLabel = (String)attributes.get("facebookLabel");

		if (facebookLabel != null) {
			setFacebookLabel(facebookLabel);
		}

		String accesMap = (String)attributes.get("accesMap");

		if (accesMap != null) {
			setAccesMap(accesMap);
		}

		String access = (String)attributes.get("access");

		if (access != null) {
			setAccess(access);
		}

		String accessForDisabled = (String)attributes.get("accessForDisabled");

		if (accessForDisabled != null) {
			setAccessForDisabled(accessForDisabled);
		}

		Boolean accessForBlind = (Boolean)attributes.get("accessForBlind");

		if (accessForBlind != null) {
			setAccessForBlind(accessForBlind);
		}

		Boolean accessForDeaf = (Boolean)attributes.get("accessForDeaf");

		if (accessForDeaf != null) {
			setAccessForDeaf(accessForDeaf);
		}

		Boolean accessForWheelchair = (Boolean)attributes.get(
				"accessForWheelchair");

		if (accessForWheelchair != null) {
			setAccessForWheelchair(accessForWheelchair);
		}

		Boolean accessForElder = (Boolean)attributes.get("accessForElder");

		if (accessForElder != null) {
			setAccessForElder(accessForElder);
		}

		Boolean accessForDeficient = (Boolean)attributes.get(
				"accessForDeficient");

		if (accessForDeficient != null) {
			setAccessForDeficient(accessForDeficient);
		}

		String RTExternalId = (String)attributes.get("RTExternalId");

		if (RTExternalId != null) {
			setRTExternalId(RTExternalId);
		}

		String occupation = (String)attributes.get("occupation");

		if (occupation != null) {
			setOccupation(occupation);
		}

		Date occupationLastUpdate = (Date)attributes.get("occupationLastUpdate");

		if (occupationLastUpdate != null) {
			setOccupationLastUpdate(occupationLastUpdate);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		String imageIds = (String)attributes.get("imageIds");

		if (imageIds != null) {
			setImageIds(imageIds);
		}

		String videosIds = (String)attributes.get("videosIds");

		if (videosIds != null) {
			setVideosIds(videosIds);
		}

		Long priceId = (Long)attributes.get("priceId");

		if (priceId != null) {
			setPriceId(priceId);
		}

		String documentsIds = (String)attributes.get("documentsIds");

		if (documentsIds != null) {
			setDocumentsIds(documentsIds);
		}
	}

	/**
	* Returns the display events of this place.
	*
	* @return the display events of this place
	*/
	@Override
	public boolean getDisplayEvents() {
		return _place.getDisplayEvents();
	}

	/**
	* Returns the subject to public holiday of this place.
	*
	* @return the subject to public holiday of this place
	*/
	@Override
	public boolean getSubjectToPublicHoliday() {
		return _place.getSubjectToPublicHoliday();
	}

	/**
	* Retourne true si l'événement est accessible pour au moins un type de
	* handicap
	*/
	@Override
	public boolean hasAnyAccessForDisabled() {
		return _place.hasAnyAccessForDisabled();
	}

	/**
	* Returns <code>true</code> if this place is approved.
	*
	* @return <code>true</code> if this place is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _place.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _place.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this place is denied.
	*
	* @return <code>true</code> if this place is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _place.isDenied();
	}

	/**
	* Returns <code>true</code> if this place is display events.
	*
	* @return <code>true</code> if this place is display events; <code>false</code> otherwise
	*/
	@Override
	public boolean isDisplayEvents() {
		return _place.isDisplayEvents();
	}

	/**
	* Returns <code>true</code> if this place is a draft.
	*
	* @return <code>true</code> if this place is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _place.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _place.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this place is expired.
	*
	* @return <code>true</code> if this place is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _place.isExpired();
	}

	/**
	* Returns <code>true</code> if this place is inactive.
	*
	* @return <code>true</code> if this place is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _place.isInactive();
	}

	/**
	* Returns <code>true</code> if this place is incomplete.
	*
	* @return <code>true</code> if this place is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _place.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _place.isNew();
	}

	/**
	* Returns <code>true</code> if this place is pending.
	*
	* @return <code>true</code> if this place is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _place.isPending();
	}

	/**
	* Returns <code>true</code> if this place is scheduled.
	*
	* @return <code>true</code> if this place is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _place.isScheduled();
	}

	/**
	* Returns <code>true</code> if this place is subject to public holiday.
	*
	* @return <code>true</code> if this place is subject to public holiday; <code>false</code> otherwise
	*/
	@Override
	public boolean isSubjectToPublicHoliday() {
		return _place.isSubjectToPublicHoliday();
	}

	/**
	* Retourne true si le lieu est une piscine
	*
	* @return
	*/
	@Override
	public boolean isSwimmingPool() {
		return _place.isSwimmingPool();
	}

	/**
	* Retourne la catégorie Territoire correspondant à la ville du lieu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getCityCategory() {
		return _place.getCityCategory();
	}

	/**
	* Retourne la catégorie Territoire correspondant au quartier du lieu
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getDistrictCategory() {
		return _place.getDistrictCategory();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _place.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _place.getExpandoBridge();
	}

	/**
	* Retourne la version JSON du lieu
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _place.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.place.model.Place> toCacheModel() {
		return _place.toCacheModel();
	}

	/**
	* Retourne la période par défaut
	*/
	@Override
	public eu.strasbourg.service.place.model.Period getDefaultPeriod() {
		return _place.getDefaultPeriod();
	}

	@Override
	public eu.strasbourg.service.place.model.Place toEscapedModel() {
		return new PlaceWrapper(_place.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.place.model.Place toUnescapedModel() {
		return new PlaceWrapper(_place.toUnescapedModel());
	}

	/**
	* Retourne le prix rattaché au lieu
	*/
	@Override
	public eu.strasbourg.service.place.model.Price getPrice() {
		return _place.getPrice();
	}

	/**
	* Retourne le temps réel (en gérant automatiquement le fait que ce soit une piscine ou un parking)
	*/
	@Override
	public eu.strasbourg.utils.OccupationState getRealTime() {
		return _place.getRealTime();
	}

	/**
	* Retourne le temps réel (couleur de fond,valeur)
	*
	* @param type
	(1 = piscine, 2 = parking)
	*/
	@Override
	public eu.strasbourg.utils.OccupationState getRealTime(
		java.lang.String type) {
		return _place.getRealTime(type);
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Place place) {
		return _place.compareTo(place);
	}

	/**
	* Returns the status of this place.
	*
	* @return the status of this place
	*/
	@Override
	public int getStatus() {
		return _place.getStatus();
	}

	@Override
	public int hashCode() {
		return _place.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _place.getPrimaryKeyObj();
	}

	/**
	* Returns the access for blind of this place.
	*
	* @return the access for blind of this place
	*/
	@Override
	public java.lang.Boolean getAccessForBlind() {
		return _place.getAccessForBlind();
	}

	/**
	* Returns the access for deaf of this place.
	*
	* @return the access for deaf of this place
	*/
	@Override
	public java.lang.Boolean getAccessForDeaf() {
		return _place.getAccessForDeaf();
	}

	/**
	* Returns the access for deficient of this place.
	*
	* @return the access for deficient of this place
	*/
	@Override
	public java.lang.Boolean getAccessForDeficient() {
		return _place.getAccessForDeficient();
	}

	/**
	* Returns the access for elder of this place.
	*
	* @return the access for elder of this place
	*/
	@Override
	public java.lang.Boolean getAccessForElder() {
		return _place.getAccessForElder();
	}

	/**
	* Returns the access for wheelchair of this place.
	*
	* @return the access for wheelchair of this place
	*/
	@Override
	public java.lang.Boolean getAccessForWheelchair() {
		return _place.getAccessForWheelchair();
	}

	/**
	* Vérifie si le lieu est fermé
	*/
	@Override
	public java.lang.Boolean isClosed(java.util.GregorianCalendar jourSemaine) {
		return _place.isClosed(jourSemaine);
	}

	/**
	* Vérifie si le lieu à accès au temps réel
	*
	* @throws PortalException
	*/
	@Override
	public java.lang.Boolean isEnabled()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _place.isEnabled();
	}

	@Override
	public java.lang.Object clone() {
		return new PlaceWrapper((Place)_place.clone());
	}

	/**
	* Returns the acces map of this place.
	*
	* @return the acces map of this place
	*/
	@Override
	public java.lang.String getAccesMap() {
		return _place.getAccesMap();
	}

	/**
	* Returns the localized acces map of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized acces map of this place
	*/
	@Override
	public java.lang.String getAccesMap(java.lang.String languageId) {
		return _place.getAccesMap(languageId);
	}

	/**
	* Returns the localized acces map of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized acces map of this place
	*/
	@Override
	public java.lang.String getAccesMap(java.lang.String languageId,
		boolean useDefault) {
		return _place.getAccesMap(languageId, useDefault);
	}

	/**
	* Returns the localized acces map of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized acces map of this place
	*/
	@Override
	public java.lang.String getAccesMap(java.util.Locale locale) {
		return _place.getAccesMap(locale);
	}

	/**
	* Returns the localized acces map of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized acces map of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAccesMap(java.util.Locale locale,
		boolean useDefault) {
		return _place.getAccesMap(locale, useDefault);
	}

	@Override
	public java.lang.String getAccesMapCurrentLanguageId() {
		return _place.getAccesMapCurrentLanguageId();
	}

	@Override
	public java.lang.String getAccesMapCurrentValue() {
		return _place.getAccesMapCurrentValue();
	}

	/**
	* Returns the access of this place.
	*
	* @return the access of this place
	*/
	@Override
	public java.lang.String getAccess() {
		return _place.getAccess();
	}

	/**
	* Returns the localized access of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized access of this place
	*/
	@Override
	public java.lang.String getAccess(java.lang.String languageId) {
		return _place.getAccess(languageId);
	}

	/**
	* Returns the localized access of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access of this place
	*/
	@Override
	public java.lang.String getAccess(java.lang.String languageId,
		boolean useDefault) {
		return _place.getAccess(languageId, useDefault);
	}

	/**
	* Returns the localized access of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized access of this place
	*/
	@Override
	public java.lang.String getAccess(java.util.Locale locale) {
		return _place.getAccess(locale);
	}

	/**
	* Returns the localized access of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAccess(java.util.Locale locale,
		boolean useDefault) {
		return _place.getAccess(locale, useDefault);
	}

	@Override
	public java.lang.String getAccessCurrentLanguageId() {
		return _place.getAccessCurrentLanguageId();
	}

	@Override
	public java.lang.String getAccessCurrentValue() {
		return _place.getAccessCurrentValue();
	}

	/**
	* Returns the access for disabled of this place.
	*
	* @return the access for disabled of this place
	*/
	@Override
	public java.lang.String getAccessForDisabled() {
		return _place.getAccessForDisabled();
	}

	/**
	* Returns the localized access for disabled of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized access for disabled of this place
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.lang.String languageId) {
		return _place.getAccessForDisabled(languageId);
	}

	/**
	* Returns the localized access for disabled of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access for disabled of this place
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.lang.String languageId,
		boolean useDefault) {
		return _place.getAccessForDisabled(languageId, useDefault);
	}

	/**
	* Returns the localized access for disabled of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized access for disabled of this place
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.util.Locale locale) {
		return _place.getAccessForDisabled(locale);
	}

	/**
	* Returns the localized access for disabled of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access for disabled of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.util.Locale locale,
		boolean useDefault) {
		return _place.getAccessForDisabled(locale, useDefault);
	}

	@Override
	public java.lang.String getAccessForDisabledCurrentLanguageId() {
		return _place.getAccessForDisabledCurrentLanguageId();
	}

	@Override
	public java.lang.String getAccessForDisabledCurrentValue() {
		return _place.getAccessForDisabledCurrentValue();
	}

	/**
	* Returns the additional information of this place.
	*
	* @return the additional information of this place
	*/
	@Override
	public java.lang.String getAdditionalInformation() {
		return _place.getAdditionalInformation();
	}

	/**
	* Returns the localized additional information of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized additional information of this place
	*/
	@Override
	public java.lang.String getAdditionalInformation(
		java.lang.String languageId) {
		return _place.getAdditionalInformation(languageId);
	}

	/**
	* Returns the localized additional information of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized additional information of this place
	*/
	@Override
	public java.lang.String getAdditionalInformation(
		java.lang.String languageId, boolean useDefault) {
		return _place.getAdditionalInformation(languageId, useDefault);
	}

	/**
	* Returns the localized additional information of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized additional information of this place
	*/
	@Override
	public java.lang.String getAdditionalInformation(java.util.Locale locale) {
		return _place.getAdditionalInformation(locale);
	}

	/**
	* Returns the localized additional information of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized additional information of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAdditionalInformation(java.util.Locale locale,
		boolean useDefault) {
		return _place.getAdditionalInformation(locale, useDefault);
	}

	@Override
	public java.lang.String getAdditionalInformationCurrentLanguageId() {
		return _place.getAdditionalInformationCurrentLanguageId();
	}

	@Override
	public java.lang.String getAdditionalInformationCurrentValue() {
		return _place.getAdditionalInformationCurrentValue();
	}

	/**
	* Returns the address complement of this place.
	*
	* @return the address complement of this place
	*/
	@Override
	public java.lang.String getAddressComplement() {
		return _place.getAddressComplement();
	}

	/**
	* Returns the address country of this place.
	*
	* @return the address country of this place
	*/
	@Override
	public java.lang.String getAddressCountry() {
		return _place.getAddressCountry();
	}

	/**
	* Returns the address distribution of this place.
	*
	* @return the address distribution of this place
	*/
	@Override
	public java.lang.String getAddressDistribution() {
		return _place.getAddressDistribution();
	}

	/**
	* Returns the address street of this place.
	*
	* @return the address street of this place
	*/
	@Override
	public java.lang.String getAddressStreet() {
		return _place.getAddressStreet();
	}

	/**
	* Returns the address zip code of this place.
	*
	* @return the address zip code of this place
	*/
	@Override
	public java.lang.String getAddressZipCode() {
		return _place.getAddressZipCode();
	}

	/**
	* Returns the alias of this place.
	*
	* @return the alias of this place
	*/
	@Override
	public java.lang.String getAlias() {
		return _place.getAlias();
	}

	/**
	* Returns the localized alias of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized alias of this place
	*/
	@Override
	public java.lang.String getAlias(java.lang.String languageId) {
		return _place.getAlias(languageId);
	}

	/**
	* Returns the localized alias of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized alias of this place
	*/
	@Override
	public java.lang.String getAlias(java.lang.String languageId,
		boolean useDefault) {
		return _place.getAlias(languageId, useDefault);
	}

	/**
	* Returns the localized alias of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized alias of this place
	*/
	@Override
	public java.lang.String getAlias(java.util.Locale locale) {
		return _place.getAlias(locale);
	}

	/**
	* Returns the localized alias of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized alias of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAlias(java.util.Locale locale, boolean useDefault) {
		return _place.getAlias(locale, useDefault);
	}

	@Override
	public java.lang.String getAliasCurrentLanguageId() {
		return _place.getAliasCurrentLanguageId();
	}

	@Override
	public java.lang.String getAliasCurrentValue() {
		return _place.getAliasCurrentValue();
	}

	/**
	* Returns the characteristics of this place.
	*
	* @return the characteristics of this place
	*/
	@Override
	public java.lang.String getCharacteristics() {
		return _place.getCharacteristics();
	}

	/**
	* Returns the localized characteristics of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized characteristics of this place
	*/
	@Override
	public java.lang.String getCharacteristics(java.lang.String languageId) {
		return _place.getCharacteristics(languageId);
	}

	/**
	* Returns the localized characteristics of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized characteristics of this place
	*/
	@Override
	public java.lang.String getCharacteristics(java.lang.String languageId,
		boolean useDefault) {
		return _place.getCharacteristics(languageId, useDefault);
	}

	/**
	* Returns the localized characteristics of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized characteristics of this place
	*/
	@Override
	public java.lang.String getCharacteristics(java.util.Locale locale) {
		return _place.getCharacteristics(locale);
	}

	/**
	* Returns the localized characteristics of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized characteristics of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getCharacteristics(java.util.Locale locale,
		boolean useDefault) {
		return _place.getCharacteristics(locale, useDefault);
	}

	@Override
	public java.lang.String getCharacteristicsCurrentLanguageId() {
		return _place.getCharacteristicsCurrentLanguageId();
	}

	@Override
	public java.lang.String getCharacteristicsCurrentValue() {
		return _place.getCharacteristicsCurrentValue();
	}

	/**
	* Retourne la ville
	*/
	@Override
	public java.lang.String getCity(java.util.Locale locale) {
		return _place.getCity(locale);
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _place.getDefaultLanguageId();
	}

	/**
	* Retourne le quartier
	*/
	@Override
	public java.lang.String getDistrict(java.util.Locale locale) {
		return _place.getDistrict(locale);
	}

	/**
	* Returns the documents IDs of this place.
	*
	* @return the documents IDs of this place
	*/
	@Override
	public java.lang.String getDocumentsIds() {
		return _place.getDocumentsIds();
	}

	/**
	* Returns the exceptional schedule of this place.
	*
	* @return the exceptional schedule of this place
	*/
	@Override
	public java.lang.String getExceptionalSchedule() {
		return _place.getExceptionalSchedule();
	}

	/**
	* Returns the localized exceptional schedule of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized exceptional schedule of this place
	*/
	@Override
	public java.lang.String getExceptionalSchedule(java.lang.String languageId) {
		return _place.getExceptionalSchedule(languageId);
	}

	/**
	* Returns the localized exceptional schedule of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized exceptional schedule of this place
	*/
	@Override
	public java.lang.String getExceptionalSchedule(
		java.lang.String languageId, boolean useDefault) {
		return _place.getExceptionalSchedule(languageId, useDefault);
	}

	/**
	* Returns the localized exceptional schedule of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized exceptional schedule of this place
	*/
	@Override
	public java.lang.String getExceptionalSchedule(java.util.Locale locale) {
		return _place.getExceptionalSchedule(locale);
	}

	/**
	* Returns the localized exceptional schedule of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized exceptional schedule of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getExceptionalSchedule(java.util.Locale locale,
		boolean useDefault) {
		return _place.getExceptionalSchedule(locale, useDefault);
	}

	@Override
	public java.lang.String getExceptionalScheduleCurrentLanguageId() {
		return _place.getExceptionalScheduleCurrentLanguageId();
	}

	@Override
	public java.lang.String getExceptionalScheduleCurrentValue() {
		return _place.getExceptionalScheduleCurrentValue();
	}

	/**
	* Returns the facebook label of this place.
	*
	* @return the facebook label of this place
	*/
	@Override
	public java.lang.String getFacebookLabel() {
		return _place.getFacebookLabel();
	}

	/**
	* Returns the localized facebook label of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized facebook label of this place
	*/
	@Override
	public java.lang.String getFacebookLabel(java.lang.String languageId) {
		return _place.getFacebookLabel(languageId);
	}

	/**
	* Returns the localized facebook label of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook label of this place
	*/
	@Override
	public java.lang.String getFacebookLabel(java.lang.String languageId,
		boolean useDefault) {
		return _place.getFacebookLabel(languageId, useDefault);
	}

	/**
	* Returns the localized facebook label of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized facebook label of this place
	*/
	@Override
	public java.lang.String getFacebookLabel(java.util.Locale locale) {
		return _place.getFacebookLabel(locale);
	}

	/**
	* Returns the localized facebook label of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook label of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFacebookLabel(java.util.Locale locale,
		boolean useDefault) {
		return _place.getFacebookLabel(locale, useDefault);
	}

	@Override
	public java.lang.String getFacebookLabelCurrentLanguageId() {
		return _place.getFacebookLabelCurrentLanguageId();
	}

	@Override
	public java.lang.String getFacebookLabelCurrentValue() {
		return _place.getFacebookLabelCurrentValue();
	}

	/**
	* Returns the facebook url of this place.
	*
	* @return the facebook url of this place
	*/
	@Override
	public java.lang.String getFacebookURL() {
		return _place.getFacebookURL();
	}

	/**
	* Returns the localized facebook url of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized facebook url of this place
	*/
	@Override
	public java.lang.String getFacebookURL(java.lang.String languageId) {
		return _place.getFacebookURL(languageId);
	}

	/**
	* Returns the localized facebook url of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook url of this place
	*/
	@Override
	public java.lang.String getFacebookURL(java.lang.String languageId,
		boolean useDefault) {
		return _place.getFacebookURL(languageId, useDefault);
	}

	/**
	* Returns the localized facebook url of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized facebook url of this place
	*/
	@Override
	public java.lang.String getFacebookURL(java.util.Locale locale) {
		return _place.getFacebookURL(locale);
	}

	/**
	* Returns the localized facebook url of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized facebook url of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getFacebookURL(java.util.Locale locale,
		boolean useDefault) {
		return _place.getFacebookURL(locale, useDefault);
	}

	@Override
	public java.lang.String getFacebookURLCurrentLanguageId() {
		return _place.getFacebookURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getFacebookURLCurrentValue() {
		return _place.getFacebookURLCurrentValue();
	}

	/**
	* Retourne le copyright publiques de l'image
	*/
	@Override
	public java.lang.String getImageCopyright(java.lang.Long imageId,
		java.util.Locale locale) {
		return _place.getImageCopyright(imageId, locale);
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _place.getImageCopyright(locale);
	}

	/**
	* Returns the image IDs of this place.
	*
	* @return the image IDs of this place
	*/
	@Override
	public java.lang.String getImageIds() {
		return _place.getImageIds();
	}

	/**
	* Retourne la légende publiques de l'image
	*/
	@Override
	public java.lang.String getImageLegend(java.lang.Long imageId,
		java.util.Locale locale) {
		return _place.getImageLegend(imageId, locale);
	}

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _place.getImageURL();
	}

	/**
	* Retourne l'URL publiques de l'image
	*/
	@Override
	public java.lang.String getImageURL(java.lang.Long imageId) {
		return _place.getImageURL(imageId);
	}

	/**
	* Returns the mail of this place.
	*
	* @return the mail of this place
	*/
	@Override
	public java.lang.String getMail() {
		return _place.getMail();
	}

	/**
	* Returns the mercator x of this place.
	*
	* @return the mercator x of this place
	*/
	@Override
	public java.lang.String getMercatorX() {
		return _place.getMercatorX();
	}

	/**
	* Returns the mercator y of this place.
	*
	* @return the mercator y of this place
	*/
	@Override
	public java.lang.String getMercatorY() {
		return _place.getMercatorY();
	}

	/**
	* Returns the name of this place.
	*
	* @return the name of this place
	*/
	@Override
	public java.lang.String getName() {
		return _place.getName();
	}

	/**
	* Returns the occupation of this place.
	*
	* @return the occupation of this place
	*/
	@Override
	public java.lang.String getOccupation() {
		return _place.getOccupation();
	}

	/**
	* Returns the phone of this place.
	*
	* @return the phone of this place
	*/
	@Override
	public java.lang.String getPhone() {
		return _place.getPhone();
	}

	/**
	* Returns the presentation of this place.
	*
	* @return the presentation of this place
	*/
	@Override
	public java.lang.String getPresentation() {
		return _place.getPresentation();
	}

	/**
	* Returns the localized presentation of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized presentation of this place
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId) {
		return _place.getPresentation(languageId);
	}

	/**
	* Returns the localized presentation of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this place
	*/
	@Override
	public java.lang.String getPresentation(java.lang.String languageId,
		boolean useDefault) {
		return _place.getPresentation(languageId, useDefault);
	}

	/**
	* Returns the localized presentation of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized presentation of this place
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale) {
		return _place.getPresentation(locale);
	}

	/**
	* Returns the localized presentation of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized presentation of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPresentation(java.util.Locale locale,
		boolean useDefault) {
		return _place.getPresentation(locale, useDefault);
	}

	@Override
	public java.lang.String getPresentationCurrentLanguageId() {
		return _place.getPresentationCurrentLanguageId();
	}

	@Override
	public java.lang.String getPresentationCurrentValue() {
		return _place.getPresentationCurrentValue();
	}

	/**
	* Returns the rgf93x of this place.
	*
	* @return the rgf93x of this place
	*/
	@Override
	public java.lang.String getRGF93X() {
		return _place.getRGF93X();
	}

	/**
	* Returns the rgf93y of this place.
	*
	* @return the rgf93y of this place
	*/
	@Override
	public java.lang.String getRGF93Y() {
		return _place.getRGF93Y();
	}

	/**
	* Returns the rt external ID of this place.
	*
	* @return the rt external ID of this place
	*/
	@Override
	public java.lang.String getRTExternalId() {
		return _place.getRTExternalId();
	}

	/**
	* Returns the si gid of this place.
	*
	* @return the si gid of this place
	*/
	@Override
	public java.lang.String getSIGid() {
		return _place.getSIGid();
	}

	/**
	* Renvoie la liste des IDs des ScheduleExceptions auxquelles ce lieu
	* appartient sous forme de String
	*/
	@Override
	public java.lang.String getScheduleExceptionsIds() {
		return _place.getScheduleExceptionsIds();
	}

	/**
	* Returns the service and activities of this place.
	*
	* @return the service and activities of this place
	*/
	@Override
	public java.lang.String getServiceAndActivities() {
		return _place.getServiceAndActivities();
	}

	/**
	* Returns the localized service and activities of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized service and activities of this place
	*/
	@Override
	public java.lang.String getServiceAndActivities(java.lang.String languageId) {
		return _place.getServiceAndActivities(languageId);
	}

	/**
	* Returns the localized service and activities of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized service and activities of this place
	*/
	@Override
	public java.lang.String getServiceAndActivities(
		java.lang.String languageId, boolean useDefault) {
		return _place.getServiceAndActivities(languageId, useDefault);
	}

	/**
	* Returns the localized service and activities of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized service and activities of this place
	*/
	@Override
	public java.lang.String getServiceAndActivities(java.util.Locale locale) {
		return _place.getServiceAndActivities(locale);
	}

	/**
	* Returns the localized service and activities of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized service and activities of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getServiceAndActivities(java.util.Locale locale,
		boolean useDefault) {
		return _place.getServiceAndActivities(locale, useDefault);
	}

	@Override
	public java.lang.String getServiceAndActivitiesCurrentLanguageId() {
		return _place.getServiceAndActivitiesCurrentLanguageId();
	}

	@Override
	public java.lang.String getServiceAndActivitiesCurrentValue() {
		return _place.getServiceAndActivitiesCurrentValue();
	}

	/**
	* Returns the site label of this place.
	*
	* @return the site label of this place
	*/
	@Override
	public java.lang.String getSiteLabel() {
		return _place.getSiteLabel();
	}

	/**
	* Returns the localized site label of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized site label of this place
	*/
	@Override
	public java.lang.String getSiteLabel(java.lang.String languageId) {
		return _place.getSiteLabel(languageId);
	}

	/**
	* Returns the localized site label of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site label of this place
	*/
	@Override
	public java.lang.String getSiteLabel(java.lang.String languageId,
		boolean useDefault) {
		return _place.getSiteLabel(languageId, useDefault);
	}

	/**
	* Returns the localized site label of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized site label of this place
	*/
	@Override
	public java.lang.String getSiteLabel(java.util.Locale locale) {
		return _place.getSiteLabel(locale);
	}

	/**
	* Returns the localized site label of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site label of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSiteLabel(java.util.Locale locale,
		boolean useDefault) {
		return _place.getSiteLabel(locale, useDefault);
	}

	@Override
	public java.lang.String getSiteLabelCurrentLanguageId() {
		return _place.getSiteLabelCurrentLanguageId();
	}

	@Override
	public java.lang.String getSiteLabelCurrentValue() {
		return _place.getSiteLabelCurrentValue();
	}

	/**
	* Returns the site url of this place.
	*
	* @return the site url of this place
	*/
	@Override
	public java.lang.String getSiteURL() {
		return _place.getSiteURL();
	}

	/**
	* Returns the localized site url of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized site url of this place
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId) {
		return _place.getSiteURL(languageId);
	}

	/**
	* Returns the localized site url of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this place
	*/
	@Override
	public java.lang.String getSiteURL(java.lang.String languageId,
		boolean useDefault) {
		return _place.getSiteURL(languageId, useDefault);
	}

	/**
	* Returns the localized site url of this place in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized site url of this place
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale) {
		return _place.getSiteURL(locale);
	}

	/**
	* Returns the localized site url of this place in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized site url of this place. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSiteURL(java.util.Locale locale,
		boolean useDefault) {
		return _place.getSiteURL(locale, useDefault);
	}

	@Override
	public java.lang.String getSiteURLCurrentLanguageId() {
		return _place.getSiteURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getSiteURLCurrentValue() {
		return _place.getSiteURLCurrentValue();
	}

	/**
	* Returns the status by user name of this place.
	*
	* @return the status by user name of this place
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _place.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this place.
	*
	* @return the status by user uuid of this place
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _place.getStatusByUserUuid();
	}

	/**
	* Retourne le label des types de l'événement
	*/
	@Override
	public java.lang.String getTypeLabel(java.util.Locale locale) {
		return _place.getTypeLabel(locale);
	}

	/**
	* Returns the user name of this place.
	*
	* @return the user name of this place
	*/
	@Override
	public java.lang.String getUserName() {
		return _place.getUserName();
	}

	/**
	* Returns the user uuid of this place.
	*
	* @return the user uuid of this place
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _place.getUserUuid();
	}

	/**
	* Returns the uuid of this place.
	*
	* @return the uuid of this place
	*/
	@Override
	public java.lang.String getUuid() {
		return _place.getUuid();
	}

	/**
	* Returns the videos IDs of this place.
	*
	* @return the videos IDs of this place
	*/
	@Override
	public java.lang.String getVideosIds() {
		return _place.getVideosIds();
	}

	@Override
	public java.lang.String toString() {
		return _place.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _place.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _place.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this place.
	*
	* @return the create date of this place
	*/
	@Override
	public Date getCreateDate() {
		return _place.getCreateDate();
	}

	/**
	* Returns the last publish date of this place.
	*
	* @return the last publish date of this place
	*/
	@Override
	public Date getLastPublishDate() {
		return _place.getLastPublishDate();
	}

	/**
	* Returns the modified date of this place.
	*
	* @return the modified date of this place
	*/
	@Override
	public Date getModifiedDate() {
		return _place.getModifiedDate();
	}

	/**
	* Returns the occupation last update of this place.
	*
	* @return the occupation last update of this place
	*/
	@Override
	public Date getOccupationLastUpdate() {
		return _place.getOccupationLastUpdate();
	}

	/**
	* Returns the status date of this place.
	*
	* @return the status date of this place
	*/
	@Override
	public Date getStatusDate() {
		return _place.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _place.getCategories();
	}

	/**
	* Retourne la liste des URL des documents de ce lieu
	*/
	@Override
	public java.util.List<java.lang.String> getDocumentURLs() {
		return _place.getDocumentURLs();
	}

	/**
	* Retourne une list d'évènements lié à ce lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents() {
		return _place.getEvents();
	}

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	@Override
	public java.util.List<java.lang.String> getImagesURLs() {
		return _place.getImagesURLs();
	}

	/**
	* Retourne les périodes qui ne sont pas par défaut
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Period> getNonDefaultPeriods() {
		return _place.getNonDefaultPeriods();
	}

	/**
	* Retourne les Periods du lieux
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods() {
		return _place.getPeriods();
	}

	/**
	* Retourne les horaires d'ouverture du jour
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceSchedule(
		java.util.GregorianCalendar jourSemaine, java.util.Locale locale) {
		return _place.getPlaceSchedule(jourSemaine, locale);
	}

	/**
	* Retourne les horaires des exceptions d'ouverture à partir du lundi de la
	* semaine en cours
	*
	* @param surPériode
	(false = horaires d'une journée uniquement , true = horaires
	sur une semaine)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceScheduleException(
		java.util.GregorianCalendar premierJour, java.lang.Boolean surPeriode,
		java.util.Locale locale) {
		return _place.getPlaceScheduleException(premierJour, surPeriode, locale);
	}

	/**
	* Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	* de la semaine en cours, jusqu'à dans 2 mois (pour freemarker)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceScheduleExceptionFreeMarker(
		Date dateDeb, java.lang.Boolean surPeriode, java.util.Locale locale) {
		return _place.getPlaceScheduleExceptionFreeMarker(dateDeb, surPeriode,
			locale);
	}

	/**
	* Retourne les PublicHolidays
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.PublicHoliday> getPublicHolidays() {
		return _place.getPublicHolidays();
	}

	/**
	* Retourne les sous lieux publiés du lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.SubPlace> getPublishedSubPlaces() {
		return _place.getPublishedSubPlaces();
	}

	/**
	* Retourne une map d'URL et de titre des images additionnelles et des
	* vidéos
	*
	* @throws PortalException
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getRandomContents()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _place.getRandomContents();
	}

	/**
	* Retourne les ScheduleExceptions du lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions() {
		return _place.getScheduleExceptions();
	}

	/**
	* Retourne les sous lieux du lieux
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.SubPlace> getSubPlaces() {
		return _place.getSubPlaces();
	}

	/**
	* Retourne les territoire du lieu
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories() {
		return _place.getTerritories();
	}

	/**
	* Retourne les types du lieu
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes() {
		return _place.getTypes();
	}

	/**
	* Retourne la liste des vidéos de ce lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos() {
		return _place.getVideos();
	}

	/**
	* Returns a map of the locales and localized acces maps of this place.
	*
	* @return the locales and localized acces maps of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAccesMapMap() {
		return _place.getAccesMapMap();
	}

	/**
	* Returns a map of the locales and localized access for disableds of this place.
	*
	* @return the locales and localized access for disableds of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAccessForDisabledMap() {
		return _place.getAccessForDisabledMap();
	}

	/**
	* Returns a map of the locales and localized accesses of this place.
	*
	* @return the locales and localized accesses of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAccessMap() {
		return _place.getAccessMap();
	}

	/**
	* Returns a map of the locales and localized additional informations of this place.
	*
	* @return the locales and localized additional informations of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAdditionalInformationMap() {
		return _place.getAdditionalInformationMap();
	}

	/**
	* Returns a map of the locales and localized aliases of this place.
	*
	* @return the locales and localized aliases of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAliasMap() {
		return _place.getAliasMap();
	}

	/**
	* Returns a map of the locales and localized characteristicses of this place.
	*
	* @return the locales and localized characteristicses of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getCharacteristicsMap() {
		return _place.getCharacteristicsMap();
	}

	/**
	* Retourne une map de titre et d'URL des documents de ce lieu
	*/
	@Override
	public Map<java.lang.String, java.lang.String> getDocuments() {
		return _place.getDocuments();
	}

	/**
	* Returns a map of the locales and localized exceptional schedules of this place.
	*
	* @return the locales and localized exceptional schedules of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getExceptionalScheduleMap() {
		return _place.getExceptionalScheduleMap();
	}

	/**
	* Returns a map of the locales and localized facebook labels of this place.
	*
	* @return the locales and localized facebook labels of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFacebookLabelMap() {
		return _place.getFacebookLabelMap();
	}

	/**
	* Returns a map of the locales and localized facebook urls of this place.
	*
	* @return the locales and localized facebook urls of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getFacebookURLMap() {
		return _place.getFacebookURLMap();
	}

	/**
	* Retourne une map contennant les horaires de chaque jour des 7 jours suivants "startDate" (inclus)
	*/
	@Override
	public Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getFollowingWeekSchedules(
		Date startDate, java.util.Locale locale) {
		return _place.getFollowingWeekSchedules(startDate, locale);
	}

	/**
	* Retourne une map contennant le jour et une liste de PlaceSchedule de la
	* semaine en cours
	*/
	@Override
	public Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getHoraire(
		Date dateJour, java.util.Locale locale) {
		return _place.getHoraire(dateJour, locale);
	}

	/**
	* Returns a map of the locales and localized presentations of this place.
	*
	* @return the locales and localized presentations of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPresentationMap() {
		return _place.getPresentationMap();
	}

	/**
	* Returns a map of the locales and localized service and activitieses of this place.
	*
	* @return the locales and localized service and activitieses of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getServiceAndActivitiesMap() {
		return _place.getServiceAndActivitiesMap();
	}

	/**
	* Returns a map of the locales and localized site labels of this place.
	*
	* @return the locales and localized site labels of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSiteLabelMap() {
		return _place.getSiteLabelMap();
	}

	/**
	* Returns a map of the locales and localized site urls of this place.
	*
	* @return the locales and localized site urls of this place
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSiteURLMap() {
		return _place.getSiteURLMap();
	}

	/**
	* Returns the company ID of this place.
	*
	* @return the company ID of this place
	*/
	@Override
	public long getCompanyId() {
		return _place.getCompanyId();
	}

	/**
	* Returns the group ID of this place.
	*
	* @return the group ID of this place
	*/
	@Override
	public long getGroupId() {
		return _place.getGroupId();
	}

	/**
	* Returns the image ID of this place.
	*
	* @return the image ID of this place
	*/
	@Override
	public long getImageId() {
		return _place.getImageId();
	}

	/**
	* Returns the place ID of this place.
	*
	* @return the place ID of this place
	*/
	@Override
	public long getPlaceId() {
		return _place.getPlaceId();
	}

	/**
	* Returns the price ID of this place.
	*
	* @return the price ID of this place
	*/
	@Override
	public long getPriceId() {
		return _place.getPriceId();
	}

	/**
	* Returns the primary key of this place.
	*
	* @return the primary key of this place
	*/
	@Override
	public long getPrimaryKey() {
		return _place.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this place.
	*
	* @return the status by user ID of this place
	*/
	@Override
	public long getStatusByUserId() {
		return _place.getStatusByUserId();
	}

	/**
	* Returns the user ID of this place.
	*
	* @return the user ID of this place
	*/
	@Override
	public long getUserId() {
		return _place.getUserId();
	}

	@Override
	public void persist() {
		_place.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_place.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_place.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the acces map of this place.
	*
	* @param accesMap the acces map of this place
	*/
	@Override
	public void setAccesMap(java.lang.String accesMap) {
		_place.setAccesMap(accesMap);
	}

	/**
	* Sets the localized acces map of this place in the language.
	*
	* @param accesMap the localized acces map of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setAccesMap(java.lang.String accesMap, java.util.Locale locale) {
		_place.setAccesMap(accesMap, locale);
	}

	/**
	* Sets the localized acces map of this place in the language, and sets the default locale.
	*
	* @param accesMap the localized acces map of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccesMap(java.lang.String accesMap, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_place.setAccesMap(accesMap, locale, defaultLocale);
	}

	@Override
	public void setAccesMapCurrentLanguageId(java.lang.String languageId) {
		_place.setAccesMapCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized acces maps of this place from the map of locales and localized acces maps.
	*
	* @param accesMapMap the locales and localized acces maps of this place
	*/
	@Override
	public void setAccesMapMap(
		Map<java.util.Locale, java.lang.String> accesMapMap) {
		_place.setAccesMapMap(accesMapMap);
	}

	/**
	* Sets the localized acces maps of this place from the map of locales and localized acces maps, and sets the default locale.
	*
	* @param accesMapMap the locales and localized acces maps of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccesMapMap(
		Map<java.util.Locale, java.lang.String> accesMapMap,
		java.util.Locale defaultLocale) {
		_place.setAccesMapMap(accesMapMap, defaultLocale);
	}

	/**
	* Sets the access of this place.
	*
	* @param access the access of this place
	*/
	@Override
	public void setAccess(java.lang.String access) {
		_place.setAccess(access);
	}

	/**
	* Sets the localized access of this place in the language.
	*
	* @param access the localized access of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setAccess(java.lang.String access, java.util.Locale locale) {
		_place.setAccess(access, locale);
	}

	/**
	* Sets the localized access of this place in the language, and sets the default locale.
	*
	* @param access the localized access of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccess(java.lang.String access, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_place.setAccess(access, locale, defaultLocale);
	}

	@Override
	public void setAccessCurrentLanguageId(java.lang.String languageId) {
		_place.setAccessCurrentLanguageId(languageId);
	}

	/**
	* Sets the access for blind of this place.
	*
	* @param accessForBlind the access for blind of this place
	*/
	@Override
	public void setAccessForBlind(java.lang.Boolean accessForBlind) {
		_place.setAccessForBlind(accessForBlind);
	}

	/**
	* Sets the access for deaf of this place.
	*
	* @param accessForDeaf the access for deaf of this place
	*/
	@Override
	public void setAccessForDeaf(java.lang.Boolean accessForDeaf) {
		_place.setAccessForDeaf(accessForDeaf);
	}

	/**
	* Sets the access for deficient of this place.
	*
	* @param accessForDeficient the access for deficient of this place
	*/
	@Override
	public void setAccessForDeficient(java.lang.Boolean accessForDeficient) {
		_place.setAccessForDeficient(accessForDeficient);
	}

	/**
	* Sets the access for disabled of this place.
	*
	* @param accessForDisabled the access for disabled of this place
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled) {
		_place.setAccessForDisabled(accessForDisabled);
	}

	/**
	* Sets the localized access for disabled of this place in the language.
	*
	* @param accessForDisabled the localized access for disabled of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled,
		java.util.Locale locale) {
		_place.setAccessForDisabled(accessForDisabled, locale);
	}

	/**
	* Sets the localized access for disabled of this place in the language, and sets the default locale.
	*
	* @param accessForDisabled the localized access for disabled of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setAccessForDisabled(accessForDisabled, locale, defaultLocale);
	}

	@Override
	public void setAccessForDisabledCurrentLanguageId(
		java.lang.String languageId) {
		_place.setAccessForDisabledCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized access for disableds of this place from the map of locales and localized access for disableds.
	*
	* @param accessForDisabledMap the locales and localized access for disableds of this place
	*/
	@Override
	public void setAccessForDisabledMap(
		Map<java.util.Locale, java.lang.String> accessForDisabledMap) {
		_place.setAccessForDisabledMap(accessForDisabledMap);
	}

	/**
	* Sets the localized access for disableds of this place from the map of locales and localized access for disableds, and sets the default locale.
	*
	* @param accessForDisabledMap the locales and localized access for disableds of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessForDisabledMap(
		Map<java.util.Locale, java.lang.String> accessForDisabledMap,
		java.util.Locale defaultLocale) {
		_place.setAccessForDisabledMap(accessForDisabledMap, defaultLocale);
	}

	/**
	* Sets the access for elder of this place.
	*
	* @param accessForElder the access for elder of this place
	*/
	@Override
	public void setAccessForElder(java.lang.Boolean accessForElder) {
		_place.setAccessForElder(accessForElder);
	}

	/**
	* Sets the access for wheelchair of this place.
	*
	* @param accessForWheelchair the access for wheelchair of this place
	*/
	@Override
	public void setAccessForWheelchair(java.lang.Boolean accessForWheelchair) {
		_place.setAccessForWheelchair(accessForWheelchair);
	}

	/**
	* Sets the localized accesses of this place from the map of locales and localized accesses.
	*
	* @param accessMap the locales and localized accesses of this place
	*/
	@Override
	public void setAccessMap(Map<java.util.Locale, java.lang.String> accessMap) {
		_place.setAccessMap(accessMap);
	}

	/**
	* Sets the localized accesses of this place from the map of locales and localized accesses, and sets the default locale.
	*
	* @param accessMap the locales and localized accesses of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessMap(
		Map<java.util.Locale, java.lang.String> accessMap,
		java.util.Locale defaultLocale) {
		_place.setAccessMap(accessMap, defaultLocale);
	}

	/**
	* Sets the additional information of this place.
	*
	* @param additionalInformation the additional information of this place
	*/
	@Override
	public void setAdditionalInformation(java.lang.String additionalInformation) {
		_place.setAdditionalInformation(additionalInformation);
	}

	/**
	* Sets the localized additional information of this place in the language.
	*
	* @param additionalInformation the localized additional information of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setAdditionalInformation(
		java.lang.String additionalInformation, java.util.Locale locale) {
		_place.setAdditionalInformation(additionalInformation, locale);
	}

	/**
	* Sets the localized additional information of this place in the language, and sets the default locale.
	*
	* @param additionalInformation the localized additional information of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAdditionalInformation(
		java.lang.String additionalInformation, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_place.setAdditionalInformation(additionalInformation, locale,
			defaultLocale);
	}

	@Override
	public void setAdditionalInformationCurrentLanguageId(
		java.lang.String languageId) {
		_place.setAdditionalInformationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized additional informations of this place from the map of locales and localized additional informations.
	*
	* @param additionalInformationMap the locales and localized additional informations of this place
	*/
	@Override
	public void setAdditionalInformationMap(
		Map<java.util.Locale, java.lang.String> additionalInformationMap) {
		_place.setAdditionalInformationMap(additionalInformationMap);
	}

	/**
	* Sets the localized additional informations of this place from the map of locales and localized additional informations, and sets the default locale.
	*
	* @param additionalInformationMap the locales and localized additional informations of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAdditionalInformationMap(
		Map<java.util.Locale, java.lang.String> additionalInformationMap,
		java.util.Locale defaultLocale) {
		_place.setAdditionalInformationMap(additionalInformationMap,
			defaultLocale);
	}

	/**
	* Sets the address complement of this place.
	*
	* @param addressComplement the address complement of this place
	*/
	@Override
	public void setAddressComplement(java.lang.String addressComplement) {
		_place.setAddressComplement(addressComplement);
	}

	/**
	* Sets the address country of this place.
	*
	* @param addressCountry the address country of this place
	*/
	@Override
	public void setAddressCountry(java.lang.String addressCountry) {
		_place.setAddressCountry(addressCountry);
	}

	/**
	* Sets the address distribution of this place.
	*
	* @param addressDistribution the address distribution of this place
	*/
	@Override
	public void setAddressDistribution(java.lang.String addressDistribution) {
		_place.setAddressDistribution(addressDistribution);
	}

	/**
	* Sets the address street of this place.
	*
	* @param addressStreet the address street of this place
	*/
	@Override
	public void setAddressStreet(java.lang.String addressStreet) {
		_place.setAddressStreet(addressStreet);
	}

	/**
	* Sets the address zip code of this place.
	*
	* @param addressZipCode the address zip code of this place
	*/
	@Override
	public void setAddressZipCode(java.lang.String addressZipCode) {
		_place.setAddressZipCode(addressZipCode);
	}

	/**
	* Sets the alias of this place.
	*
	* @param alias the alias of this place
	*/
	@Override
	public void setAlias(java.lang.String alias) {
		_place.setAlias(alias);
	}

	/**
	* Sets the localized alias of this place in the language.
	*
	* @param alias the localized alias of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setAlias(java.lang.String alias, java.util.Locale locale) {
		_place.setAlias(alias, locale);
	}

	/**
	* Sets the localized alias of this place in the language, and sets the default locale.
	*
	* @param alias the localized alias of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAlias(java.lang.String alias, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_place.setAlias(alias, locale, defaultLocale);
	}

	@Override
	public void setAliasCurrentLanguageId(java.lang.String languageId) {
		_place.setAliasCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized aliases of this place from the map of locales and localized aliases.
	*
	* @param aliasMap the locales and localized aliases of this place
	*/
	@Override
	public void setAliasMap(Map<java.util.Locale, java.lang.String> aliasMap) {
		_place.setAliasMap(aliasMap);
	}

	/**
	* Sets the localized aliases of this place from the map of locales and localized aliases, and sets the default locale.
	*
	* @param aliasMap the locales and localized aliases of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAliasMap(Map<java.util.Locale, java.lang.String> aliasMap,
		java.util.Locale defaultLocale) {
		_place.setAliasMap(aliasMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_place.setCachedModel(cachedModel);
	}

	/**
	* Sets the characteristics of this place.
	*
	* @param characteristics the characteristics of this place
	*/
	@Override
	public void setCharacteristics(java.lang.String characteristics) {
		_place.setCharacteristics(characteristics);
	}

	/**
	* Sets the localized characteristics of this place in the language.
	*
	* @param characteristics the localized characteristics of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setCharacteristics(java.lang.String characteristics,
		java.util.Locale locale) {
		_place.setCharacteristics(characteristics, locale);
	}

	/**
	* Sets the localized characteristics of this place in the language, and sets the default locale.
	*
	* @param characteristics the localized characteristics of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCharacteristics(java.lang.String characteristics,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setCharacteristics(characteristics, locale, defaultLocale);
	}

	@Override
	public void setCharacteristicsCurrentLanguageId(java.lang.String languageId) {
		_place.setCharacteristicsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized characteristicses of this place from the map of locales and localized characteristicses.
	*
	* @param characteristicsMap the locales and localized characteristicses of this place
	*/
	@Override
	public void setCharacteristicsMap(
		Map<java.util.Locale, java.lang.String> characteristicsMap) {
		_place.setCharacteristicsMap(characteristicsMap);
	}

	/**
	* Sets the localized characteristicses of this place from the map of locales and localized characteristicses, and sets the default locale.
	*
	* @param characteristicsMap the locales and localized characteristicses of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setCharacteristicsMap(
		Map<java.util.Locale, java.lang.String> characteristicsMap,
		java.util.Locale defaultLocale) {
		_place.setCharacteristicsMap(characteristicsMap, defaultLocale);
	}

	/**
	* Sets the company ID of this place.
	*
	* @param companyId the company ID of this place
	*/
	@Override
	public void setCompanyId(long companyId) {
		_place.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this place.
	*
	* @param createDate the create date of this place
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_place.setCreateDate(createDate);
	}

	/**
	* Sets whether this place is display events.
	*
	* @param displayEvents the display events of this place
	*/
	@Override
	public void setDisplayEvents(boolean displayEvents) {
		_place.setDisplayEvents(displayEvents);
	}

	/**
	* Sets the documents IDs of this place.
	*
	* @param documentsIds the documents IDs of this place
	*/
	@Override
	public void setDocumentsIds(java.lang.String documentsIds) {
		_place.setDocumentsIds(documentsIds);
	}

	/**
	* Sets the exceptional schedule of this place.
	*
	* @param exceptionalSchedule the exceptional schedule of this place
	*/
	@Override
	public void setExceptionalSchedule(java.lang.String exceptionalSchedule) {
		_place.setExceptionalSchedule(exceptionalSchedule);
	}

	/**
	* Sets the localized exceptional schedule of this place in the language.
	*
	* @param exceptionalSchedule the localized exceptional schedule of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setExceptionalSchedule(java.lang.String exceptionalSchedule,
		java.util.Locale locale) {
		_place.setExceptionalSchedule(exceptionalSchedule, locale);
	}

	/**
	* Sets the localized exceptional schedule of this place in the language, and sets the default locale.
	*
	* @param exceptionalSchedule the localized exceptional schedule of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setExceptionalSchedule(java.lang.String exceptionalSchedule,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setExceptionalSchedule(exceptionalSchedule, locale, defaultLocale);
	}

	@Override
	public void setExceptionalScheduleCurrentLanguageId(
		java.lang.String languageId) {
		_place.setExceptionalScheduleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized exceptional schedules of this place from the map of locales and localized exceptional schedules.
	*
	* @param exceptionalScheduleMap the locales and localized exceptional schedules of this place
	*/
	@Override
	public void setExceptionalScheduleMap(
		Map<java.util.Locale, java.lang.String> exceptionalScheduleMap) {
		_place.setExceptionalScheduleMap(exceptionalScheduleMap);
	}

	/**
	* Sets the localized exceptional schedules of this place from the map of locales and localized exceptional schedules, and sets the default locale.
	*
	* @param exceptionalScheduleMap the locales and localized exceptional schedules of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setExceptionalScheduleMap(
		Map<java.util.Locale, java.lang.String> exceptionalScheduleMap,
		java.util.Locale defaultLocale) {
		_place.setExceptionalScheduleMap(exceptionalScheduleMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_place.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_place.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_place.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the facebook label of this place.
	*
	* @param facebookLabel the facebook label of this place
	*/
	@Override
	public void setFacebookLabel(java.lang.String facebookLabel) {
		_place.setFacebookLabel(facebookLabel);
	}

	/**
	* Sets the localized facebook label of this place in the language.
	*
	* @param facebookLabel the localized facebook label of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setFacebookLabel(java.lang.String facebookLabel,
		java.util.Locale locale) {
		_place.setFacebookLabel(facebookLabel, locale);
	}

	/**
	* Sets the localized facebook label of this place in the language, and sets the default locale.
	*
	* @param facebookLabel the localized facebook label of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookLabel(java.lang.String facebookLabel,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setFacebookLabel(facebookLabel, locale, defaultLocale);
	}

	@Override
	public void setFacebookLabelCurrentLanguageId(java.lang.String languageId) {
		_place.setFacebookLabelCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized facebook labels of this place from the map of locales and localized facebook labels.
	*
	* @param facebookLabelMap the locales and localized facebook labels of this place
	*/
	@Override
	public void setFacebookLabelMap(
		Map<java.util.Locale, java.lang.String> facebookLabelMap) {
		_place.setFacebookLabelMap(facebookLabelMap);
	}

	/**
	* Sets the localized facebook labels of this place from the map of locales and localized facebook labels, and sets the default locale.
	*
	* @param facebookLabelMap the locales and localized facebook labels of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookLabelMap(
		Map<java.util.Locale, java.lang.String> facebookLabelMap,
		java.util.Locale defaultLocale) {
		_place.setFacebookLabelMap(facebookLabelMap, defaultLocale);
	}

	/**
	* Sets the facebook url of this place.
	*
	* @param facebookURL the facebook url of this place
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL) {
		_place.setFacebookURL(facebookURL);
	}

	/**
	* Sets the localized facebook url of this place in the language.
	*
	* @param facebookURL the localized facebook url of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL,
		java.util.Locale locale) {
		_place.setFacebookURL(facebookURL, locale);
	}

	/**
	* Sets the localized facebook url of this place in the language, and sets the default locale.
	*
	* @param facebookURL the localized facebook url of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookURL(java.lang.String facebookURL,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setFacebookURL(facebookURL, locale, defaultLocale);
	}

	@Override
	public void setFacebookURLCurrentLanguageId(java.lang.String languageId) {
		_place.setFacebookURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized facebook urls of this place from the map of locales and localized facebook urls.
	*
	* @param facebookURLMap the locales and localized facebook urls of this place
	*/
	@Override
	public void setFacebookURLMap(
		Map<java.util.Locale, java.lang.String> facebookURLMap) {
		_place.setFacebookURLMap(facebookURLMap);
	}

	/**
	* Sets the localized facebook urls of this place from the map of locales and localized facebook urls, and sets the default locale.
	*
	* @param facebookURLMap the locales and localized facebook urls of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFacebookURLMap(
		Map<java.util.Locale, java.lang.String> facebookURLMap,
		java.util.Locale defaultLocale) {
		_place.setFacebookURLMap(facebookURLMap, defaultLocale);
	}

	/**
	* Sets the group ID of this place.
	*
	* @param groupId the group ID of this place
	*/
	@Override
	public void setGroupId(long groupId) {
		_place.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this place.
	*
	* @param imageId the image ID of this place
	*/
	@Override
	public void setImageId(long imageId) {
		_place.setImageId(imageId);
	}

	/**
	* Sets the image IDs of this place.
	*
	* @param imageIds the image IDs of this place
	*/
	@Override
	public void setImageIds(java.lang.String imageIds) {
		_place.setImageIds(imageIds);
	}

	/**
	* Sets the last publish date of this place.
	*
	* @param lastPublishDate the last publish date of this place
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_place.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the mail of this place.
	*
	* @param mail the mail of this place
	*/
	@Override
	public void setMail(java.lang.String mail) {
		_place.setMail(mail);
	}

	/**
	* Sets the mercator x of this place.
	*
	* @param mercatorX the mercator x of this place
	*/
	@Override
	public void setMercatorX(java.lang.String mercatorX) {
		_place.setMercatorX(mercatorX);
	}

	/**
	* Sets the mercator y of this place.
	*
	* @param mercatorY the mercator y of this place
	*/
	@Override
	public void setMercatorY(java.lang.String mercatorY) {
		_place.setMercatorY(mercatorY);
	}

	/**
	* Sets the modified date of this place.
	*
	* @param modifiedDate the modified date of this place
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_place.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this place.
	*
	* @param name the name of this place
	*/
	@Override
	public void setName(java.lang.String name) {
		_place.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_place.setNew(n);
	}

	/**
	* Sets the occupation of this place.
	*
	* @param occupation the occupation of this place
	*/
	@Override
	public void setOccupation(java.lang.String occupation) {
		_place.setOccupation(occupation);
	}

	/**
	* Sets the occupation last update of this place.
	*
	* @param occupationLastUpdate the occupation last update of this place
	*/
	@Override
	public void setOccupationLastUpdate(Date occupationLastUpdate) {
		_place.setOccupationLastUpdate(occupationLastUpdate);
	}

	/**
	* Sets the phone of this place.
	*
	* @param phone the phone of this place
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_place.setPhone(phone);
	}

	/**
	* Sets the place ID of this place.
	*
	* @param placeId the place ID of this place
	*/
	@Override
	public void setPlaceId(long placeId) {
		_place.setPlaceId(placeId);
	}

	/**
	* Sets the presentation of this place.
	*
	* @param presentation the presentation of this place
	*/
	@Override
	public void setPresentation(java.lang.String presentation) {
		_place.setPresentation(presentation);
	}

	/**
	* Sets the localized presentation of this place in the language.
	*
	* @param presentation the localized presentation of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale) {
		_place.setPresentation(presentation, locale);
	}

	/**
	* Sets the localized presentation of this place in the language, and sets the default locale.
	*
	* @param presentation the localized presentation of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentation(java.lang.String presentation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setPresentation(presentation, locale, defaultLocale);
	}

	@Override
	public void setPresentationCurrentLanguageId(java.lang.String languageId) {
		_place.setPresentationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized presentations of this place from the map of locales and localized presentations.
	*
	* @param presentationMap the locales and localized presentations of this place
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap) {
		_place.setPresentationMap(presentationMap);
	}

	/**
	* Sets the localized presentations of this place from the map of locales and localized presentations, and sets the default locale.
	*
	* @param presentationMap the locales and localized presentations of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPresentationMap(
		Map<java.util.Locale, java.lang.String> presentationMap,
		java.util.Locale defaultLocale) {
		_place.setPresentationMap(presentationMap, defaultLocale);
	}

	/**
	* Sets the price ID of this place.
	*
	* @param priceId the price ID of this place
	*/
	@Override
	public void setPriceId(long priceId) {
		_place.setPriceId(priceId);
	}

	/**
	* Sets the primary key of this place.
	*
	* @param primaryKey the primary key of this place
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_place.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_place.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rgf93x of this place.
	*
	* @param RGF93X the rgf93x of this place
	*/
	@Override
	public void setRGF93X(java.lang.String RGF93X) {
		_place.setRGF93X(RGF93X);
	}

	/**
	* Sets the rgf93y of this place.
	*
	* @param RGF93Y the rgf93y of this place
	*/
	@Override
	public void setRGF93Y(java.lang.String RGF93Y) {
		_place.setRGF93Y(RGF93Y);
	}

	/**
	* Sets the rt external ID of this place.
	*
	* @param RTExternalId the rt external ID of this place
	*/
	@Override
	public void setRTExternalId(java.lang.String RTExternalId) {
		_place.setRTExternalId(RTExternalId);
	}

	/**
	* Sets the si gid of this place.
	*
	* @param SIGid the si gid of this place
	*/
	@Override
	public void setSIGid(java.lang.String SIGid) {
		_place.setSIGid(SIGid);
	}

	/**
	* Sets the service and activities of this place.
	*
	* @param serviceAndActivities the service and activities of this place
	*/
	@Override
	public void setServiceAndActivities(java.lang.String serviceAndActivities) {
		_place.setServiceAndActivities(serviceAndActivities);
	}

	/**
	* Sets the localized service and activities of this place in the language.
	*
	* @param serviceAndActivities the localized service and activities of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setServiceAndActivities(java.lang.String serviceAndActivities,
		java.util.Locale locale) {
		_place.setServiceAndActivities(serviceAndActivities, locale);
	}

	/**
	* Sets the localized service and activities of this place in the language, and sets the default locale.
	*
	* @param serviceAndActivities the localized service and activities of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setServiceAndActivities(java.lang.String serviceAndActivities,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setServiceAndActivities(serviceAndActivities, locale,
			defaultLocale);
	}

	@Override
	public void setServiceAndActivitiesCurrentLanguageId(
		java.lang.String languageId) {
		_place.setServiceAndActivitiesCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized service and activitieses of this place from the map of locales and localized service and activitieses.
	*
	* @param serviceAndActivitiesMap the locales and localized service and activitieses of this place
	*/
	@Override
	public void setServiceAndActivitiesMap(
		Map<java.util.Locale, java.lang.String> serviceAndActivitiesMap) {
		_place.setServiceAndActivitiesMap(serviceAndActivitiesMap);
	}

	/**
	* Sets the localized service and activitieses of this place from the map of locales and localized service and activitieses, and sets the default locale.
	*
	* @param serviceAndActivitiesMap the locales and localized service and activitieses of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setServiceAndActivitiesMap(
		Map<java.util.Locale, java.lang.String> serviceAndActivitiesMap,
		java.util.Locale defaultLocale) {
		_place.setServiceAndActivitiesMap(serviceAndActivitiesMap, defaultLocale);
	}

	/**
	* Sets the site label of this place.
	*
	* @param siteLabel the site label of this place
	*/
	@Override
	public void setSiteLabel(java.lang.String siteLabel) {
		_place.setSiteLabel(siteLabel);
	}

	/**
	* Sets the localized site label of this place in the language.
	*
	* @param siteLabel the localized site label of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setSiteLabel(java.lang.String siteLabel, java.util.Locale locale) {
		_place.setSiteLabel(siteLabel, locale);
	}

	/**
	* Sets the localized site label of this place in the language, and sets the default locale.
	*
	* @param siteLabel the localized site label of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteLabel(java.lang.String siteLabel,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_place.setSiteLabel(siteLabel, locale, defaultLocale);
	}

	@Override
	public void setSiteLabelCurrentLanguageId(java.lang.String languageId) {
		_place.setSiteLabelCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized site labels of this place from the map of locales and localized site labels.
	*
	* @param siteLabelMap the locales and localized site labels of this place
	*/
	@Override
	public void setSiteLabelMap(
		Map<java.util.Locale, java.lang.String> siteLabelMap) {
		_place.setSiteLabelMap(siteLabelMap);
	}

	/**
	* Sets the localized site labels of this place from the map of locales and localized site labels, and sets the default locale.
	*
	* @param siteLabelMap the locales and localized site labels of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteLabelMap(
		Map<java.util.Locale, java.lang.String> siteLabelMap,
		java.util.Locale defaultLocale) {
		_place.setSiteLabelMap(siteLabelMap, defaultLocale);
	}

	/**
	* Sets the site url of this place.
	*
	* @param siteURL the site url of this place
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL) {
		_place.setSiteURL(siteURL);
	}

	/**
	* Sets the localized site url of this place in the language.
	*
	* @param siteURL the localized site url of this place
	* @param locale the locale of the language
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale) {
		_place.setSiteURL(siteURL, locale);
	}

	/**
	* Sets the localized site url of this place in the language, and sets the default locale.
	*
	* @param siteURL the localized site url of this place
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURL(java.lang.String siteURL, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_place.setSiteURL(siteURL, locale, defaultLocale);
	}

	@Override
	public void setSiteURLCurrentLanguageId(java.lang.String languageId) {
		_place.setSiteURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized site urls of this place from the map of locales and localized site urls.
	*
	* @param siteURLMap the locales and localized site urls of this place
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap) {
		_place.setSiteURLMap(siteURLMap);
	}

	/**
	* Sets the localized site urls of this place from the map of locales and localized site urls, and sets the default locale.
	*
	* @param siteURLMap the locales and localized site urls of this place
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSiteURLMap(
		Map<java.util.Locale, java.lang.String> siteURLMap,
		java.util.Locale defaultLocale) {
		_place.setSiteURLMap(siteURLMap, defaultLocale);
	}

	/**
	* Sets the status of this place.
	*
	* @param status the status of this place
	*/
	@Override
	public void setStatus(int status) {
		_place.setStatus(status);
	}

	/**
	* Sets the status by user ID of this place.
	*
	* @param statusByUserId the status by user ID of this place
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_place.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this place.
	*
	* @param statusByUserName the status by user name of this place
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_place.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this place.
	*
	* @param statusByUserUuid the status by user uuid of this place
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_place.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this place.
	*
	* @param statusDate the status date of this place
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_place.setStatusDate(statusDate);
	}

	/**
	* Sets whether this place is subject to public holiday.
	*
	* @param subjectToPublicHoliday the subject to public holiday of this place
	*/
	@Override
	public void setSubjectToPublicHoliday(boolean subjectToPublicHoliday) {
		_place.setSubjectToPublicHoliday(subjectToPublicHoliday);
	}

	/**
	* Sets the user ID of this place.
	*
	* @param userId the user ID of this place
	*/
	@Override
	public void setUserId(long userId) {
		_place.setUserId(userId);
	}

	/**
	* Sets the user name of this place.
	*
	* @param userName the user name of this place
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_place.setUserName(userName);
	}

	/**
	* Sets the user uuid of this place.
	*
	* @param userUuid the user uuid of this place
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_place.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this place.
	*
	* @param uuid the uuid of this place
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_place.setUuid(uuid);
	}

	/**
	* Sets the videos IDs of this place.
	*
	* @param videosIds the videos IDs of this place
	*/
	@Override
	public void setVideosIds(java.lang.String videosIds) {
		_place.setVideosIds(videosIds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PlaceWrapper)) {
			return false;
		}

		PlaceWrapper placeWrapper = (PlaceWrapper)obj;

		if (Objects.equals(_place, placeWrapper._place)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _place.getStagedModelType();
	}

	@Override
	public Place getWrappedModel() {
		return _place;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _place.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _place.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_place.resetOriginalValues();
	}

	private final Place _place;
}
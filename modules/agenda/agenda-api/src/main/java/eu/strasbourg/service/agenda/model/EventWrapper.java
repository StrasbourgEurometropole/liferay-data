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
 * This class is a wrapper for {@link Event}.
 * </p>
 *
 * @author BenjaminBini
 * @see Event
 * @generated
 */
@ProviderType
public class EventWrapper implements Event, ModelWrapper<Event> {
	public EventWrapper(Event event) {
		_event = event;
	}

	@Override
	public Class<?> getModelClass() {
		return Event.class;
	}

	@Override
	public String getModelClassName() {
		return Event.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("eventId", getEventId());
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
		attributes.put("externalImageURL", getExternalImageURL());
		attributes.put("externalImageCopyright", getExternalImageCopyright());
		attributes.put("placeSIGId", getPlaceSIGId());
		attributes.put("placeName", getPlaceName());
		attributes.put("placeStreetNumber", getPlaceStreetNumber());
		attributes.put("placeStreetName", getPlaceStreetName());
		attributes.put("placeZipCode", getPlaceZipCode());
		attributes.put("placeCity", getPlaceCity());
		attributes.put("placeCountry", getPlaceCountry());
		attributes.put("access", getAccess());
		attributes.put("accessForDisabled", getAccessForDisabled());
		attributes.put("accessForBlind", getAccessForBlind());
		attributes.put("accessForDeaf", getAccessForDeaf());
		attributes.put("accessForWheelchair", getAccessForWheelchair());
		attributes.put("accessForElder", getAccessForElder());
		attributes.put("accessForDeficient", getAccessForDeficient());
		attributes.put("promoter", getPromoter());
		attributes.put("phone", getPhone());
		attributes.put("email", getEmail());
		attributes.put("websiteURL", getWebsiteURL());
		attributes.put("websiteName", getWebsiteName());
		attributes.put("free", getFree());
		attributes.put("price", getPrice());
		attributes.put("source", getSource());
		attributes.put("displayDate", getDisplayDate());
		attributes.put("scheduleComments", getScheduleComments());
		attributes.put("firstStartDate", getFirstStartDate());
		attributes.put("lastEndDate", getLastEndDate());
		attributes.put("imageId", getImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
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

		String externalImageURL = (String)attributes.get("externalImageURL");

		if (externalImageURL != null) {
			setExternalImageURL(externalImageURL);
		}

		String externalImageCopyright = (String)attributes.get(
				"externalImageCopyright");

		if (externalImageCopyright != null) {
			setExternalImageCopyright(externalImageCopyright);
		}

		String placeSIGId = (String)attributes.get("placeSIGId");

		if (placeSIGId != null) {
			setPlaceSIGId(placeSIGId);
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

		String placeCity = (String)attributes.get("placeCity");

		if (placeCity != null) {
			setPlaceCity(placeCity);
		}

		String placeCountry = (String)attributes.get("placeCountry");

		if (placeCountry != null) {
			setPlaceCountry(placeCountry);
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

		String promoter = (String)attributes.get("promoter");

		if (promoter != null) {
			setPromoter(promoter);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String websiteURL = (String)attributes.get("websiteURL");

		if (websiteURL != null) {
			setWebsiteURL(websiteURL);
		}

		String websiteName = (String)attributes.get("websiteName");

		if (websiteName != null) {
			setWebsiteName(websiteName);
		}

		Integer free = (Integer)attributes.get("free");

		if (free != null) {
			setFree(free);
		}

		String price = (String)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date displayDate = (Date)attributes.get("displayDate");

		if (displayDate != null) {
			setDisplayDate(displayDate);
		}

		String scheduleComments = (String)attributes.get("scheduleComments");

		if (scheduleComments != null) {
			setScheduleComments(scheduleComments);
		}

		Date firstStartDate = (Date)attributes.get("firstStartDate");

		if (firstStartDate != null) {
			setFirstStartDate(firstStartDate);
		}

		Date lastEndDate = (Date)attributes.get("lastEndDate");

		if (lastEndDate != null) {
			setLastEndDate(lastEndDate);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}
	}

	/**
	* Retourne true si l'événement est accessible pour au moins un type de
	* handicap
	*/
	@Override
	public boolean hasAnyAccessForDisabled() {
		return _event.hasAnyAccessForDisabled();
	}

	/**
	* Returns <code>true</code> if this event is approved.
	*
	* @return <code>true</code> if this event is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _event.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _event.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this event is denied.
	*
	* @return <code>true</code> if this event is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _event.isDenied();
	}

	/**
	* Returns <code>true</code> if this event is a draft.
	*
	* @return <code>true</code> if this event is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _event.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _event.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this event is expired.
	*
	* @return <code>true</code> if this event is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _event.isExpired();
	}

	/**
	* Returns <code>true</code> if this event is inactive.
	*
	* @return <code>true</code> if this event is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _event.isInactive();
	}

	/**
	* Returns <code>true</code> if this event is incomplete.
	*
	* @return <code>true</code> if this event is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _event.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _event.isNew();
	}

	/**
	* Returns <code>true</code> if this event is pending.
	*
	* @return <code>true</code> if this event is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _event.isPending();
	}

	/**
	* Returns <code>true</code> if this event is scheduled.
	*
	* @return <code>true</code> if this event is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _event.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _event.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _event.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.Event> toCacheModel() {
		return _event.toCacheModel();
	}

	/**
	* Retourne la version live de l'édition, si elle existe
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event getLiveVersion() {
		return _event.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event toEscapedModel() {
		return new EventWrapper(_event.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event toUnescapedModel() {
		return new EventWrapper(_event.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.agenda.model.Event event) {
		return _event.compareTo(event);
	}

	/**
	* Returns the status of this event.
	*
	* @return the status of this event
	*/
	@Override
	public int getStatus() {
		return _event.getStatus();
	}

	@Override
	public int hashCode() {
		return _event.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _event.getPrimaryKeyObj();
	}

	/**
	* Returns the access for blind of this event.
	*
	* @return the access for blind of this event
	*/
	@Override
	public java.lang.Boolean getAccessForBlind() {
		return _event.getAccessForBlind();
	}

	/**
	* Returns the access for deaf of this event.
	*
	* @return the access for deaf of this event
	*/
	@Override
	public java.lang.Boolean getAccessForDeaf() {
		return _event.getAccessForDeaf();
	}

	/**
	* Returns the access for deficient of this event.
	*
	* @return the access for deficient of this event
	*/
	@Override
	public java.lang.Boolean getAccessForDeficient() {
		return _event.getAccessForDeficient();
	}

	/**
	* Returns the access for elder of this event.
	*
	* @return the access for elder of this event
	*/
	@Override
	public java.lang.Boolean getAccessForElder() {
		return _event.getAccessForElder();
	}

	/**
	* Returns the access for wheelchair of this event.
	*
	* @return the access for wheelchair of this event
	*/
	@Override
	public java.lang.Boolean getAccessForWheelchair() {
		return _event.getAccessForWheelchair();
	}

	/**
	* Returns the free of this event.
	*
	* @return the free of this event
	*/
	@Override
	public java.lang.Integer getFree() {
		return _event.getFree();
	}

	/**
	* Returns the image ID of this event.
	*
	* @return the image ID of this event
	*/
	@Override
	public java.lang.Long getImageId() {
		return _event.getImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new EventWrapper((Event)_event.clone());
	}

	/**
	* Returns the access of this event.
	*
	* @return the access of this event
	*/
	@Override
	public java.lang.String getAccess() {
		return _event.getAccess();
	}

	/**
	* Returns the localized access of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized access of this event
	*/
	@Override
	public java.lang.String getAccess(java.lang.String languageId) {
		return _event.getAccess(languageId);
	}

	/**
	* Returns the localized access of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access of this event
	*/
	@Override
	public java.lang.String getAccess(java.lang.String languageId,
		boolean useDefault) {
		return _event.getAccess(languageId, useDefault);
	}

	/**
	* Returns the localized access of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized access of this event
	*/
	@Override
	public java.lang.String getAccess(java.util.Locale locale) {
		return _event.getAccess(locale);
	}

	/**
	* Returns the localized access of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAccess(java.util.Locale locale,
		boolean useDefault) {
		return _event.getAccess(locale, useDefault);
	}

	@Override
	public java.lang.String getAccessCurrentLanguageId() {
		return _event.getAccessCurrentLanguageId();
	}

	@Override
	public java.lang.String getAccessCurrentValue() {
		return _event.getAccessCurrentValue();
	}

	/**
	* Returns the access for disabled of this event.
	*
	* @return the access for disabled of this event
	*/
	@Override
	public java.lang.String getAccessForDisabled() {
		return _event.getAccessForDisabled();
	}

	/**
	* Returns the localized access for disabled of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized access for disabled of this event
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.lang.String languageId) {
		return _event.getAccessForDisabled(languageId);
	}

	/**
	* Returns the localized access for disabled of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access for disabled of this event
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.lang.String languageId,
		boolean useDefault) {
		return _event.getAccessForDisabled(languageId, useDefault);
	}

	/**
	* Returns the localized access for disabled of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized access for disabled of this event
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.util.Locale locale) {
		return _event.getAccessForDisabled(locale);
	}

	/**
	* Returns the localized access for disabled of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized access for disabled of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getAccessForDisabled(java.util.Locale locale,
		boolean useDefault) {
		return _event.getAccessForDisabled(locale, useDefault);
	}

	@Override
	public java.lang.String getAccessForDisabledCurrentLanguageId() {
		return _event.getAccessForDisabledCurrentLanguageId();
	}

	@Override
	public java.lang.String getAccessForDisabledCurrentValue() {
		return _event.getAccessForDisabledCurrentValue();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _event.getDefaultLanguageId();
	}

	/**
	* Returns the description of this event.
	*
	* @return the description of this event
	*/
	@Override
	public java.lang.String getDescription() {
		return _event.getDescription();
	}

	/**
	* Returns the localized description of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _event.getDescription(languageId);
	}

	/**
	* Returns the localized description of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _event.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this event
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _event.getDescription(locale);
	}

	/**
	* Returns the localized description of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _event.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _event.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _event.getDescriptionCurrentValue();
	}

	/**
	* Returns the email of this event.
	*
	* @return the email of this event
	*/
	@Override
	public java.lang.String getEmail() {
		return _event.getEmail();
	}

	/**
	* Retourne la période principale de l'événement (de la première date de
	* début à la dernière date de fin) sous forme de String dans la locale
	* passée en paramètre
	*/
	@Override
	public java.lang.String getEventScheduleDisplay(java.util.Locale locale) {
		return _event.getEventScheduleDisplay(locale);
	}

	/**
	* Returns the external image copyright of this event.
	*
	* @return the external image copyright of this event
	*/
	@Override
	public java.lang.String getExternalImageCopyright() {
		return _event.getExternalImageCopyright();
	}

	/**
	* Returns the external image u r l of this event.
	*
	* @return the external image u r l of this event
	*/
	@Override
	public java.lang.String getExternalImageURL() {
		return _event.getExternalImageURL();
	}

	/**
	* Retourne le copyright de l'image principale
	*/
	@Override
	public java.lang.String getImageCopyright(java.util.Locale locale) {
		return _event.getImageCopyright(locale);
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _event.getImageURL();
	}

	/**
	* Retourne la liste des IDs des manifestations auxquelles cette édition
	* appartient sous forme de String
	*/
	@Override
	public java.lang.String getManifestationsIds() {
		return _event.getManifestationsIds();
	}

	/**
	* Returns the phone of this event.
	*
	* @return the phone of this event
	*/
	@Override
	public java.lang.String getPhone() {
		return _event.getPhone();
	}

	/**
	* Returns the place city of this event.
	*
	* @return the place city of this event
	*/
	@Override
	public java.lang.String getPlaceCity() {
		return _event.getPlaceCity();
	}

	/**
	* Returns the place country of this event.
	*
	* @return the place country of this event
	*/
	@Override
	public java.lang.String getPlaceCountry() {
		return _event.getPlaceCountry();
	}

	/**
	* Returns the place name of this event.
	*
	* @return the place name of this event
	*/
	@Override
	public java.lang.String getPlaceName() {
		return _event.getPlaceName();
	}

	/**
	* Returns the place s i g ID of this event.
	*
	* @return the place s i g ID of this event
	*/
	@Override
	public java.lang.String getPlaceSIGId() {
		return _event.getPlaceSIGId();
	}

	/**
	* Returns the place street name of this event.
	*
	* @return the place street name of this event
	*/
	@Override
	public java.lang.String getPlaceStreetName() {
		return _event.getPlaceStreetName();
	}

	/**
	* Returns the place street number of this event.
	*
	* @return the place street number of this event
	*/
	@Override
	public java.lang.String getPlaceStreetNumber() {
		return _event.getPlaceStreetNumber();
	}

	/**
	* Returns the place zip code of this event.
	*
	* @return the place zip code of this event
	*/
	@Override
	public java.lang.String getPlaceZipCode() {
		return _event.getPlaceZipCode();
	}

	/**
	* Returns the price of this event.
	*
	* @return the price of this event
	*/
	@Override
	public java.lang.String getPrice() {
		return _event.getPrice();
	}

	/**
	* Returns the localized price of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized price of this event
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId) {
		return _event.getPrice(languageId);
	}

	/**
	* Returns the localized price of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this event
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId,
		boolean useDefault) {
		return _event.getPrice(languageId, useDefault);
	}

	/**
	* Returns the localized price of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized price of this event
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale) {
		return _event.getPrice(locale);
	}

	/**
	* Returns the localized price of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized price of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale, boolean useDefault) {
		return _event.getPrice(locale, useDefault);
	}

	@Override
	public java.lang.String getPriceCurrentLanguageId() {
		return _event.getPriceCurrentLanguageId();
	}

	@Override
	public java.lang.String getPriceCurrentValue() {
		return _event.getPriceCurrentValue();
	}

	/**
	* Returns the promoter of this event.
	*
	* @return the promoter of this event
	*/
	@Override
	public java.lang.String getPromoter() {
		return _event.getPromoter();
	}

	/**
	* Returns the schedule comments of this event.
	*
	* @return the schedule comments of this event
	*/
	@Override
	public java.lang.String getScheduleComments() {
		return _event.getScheduleComments();
	}

	/**
	* Returns the localized schedule comments of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized schedule comments of this event
	*/
	@Override
	public java.lang.String getScheduleComments(java.lang.String languageId) {
		return _event.getScheduleComments(languageId);
	}

	/**
	* Returns the localized schedule comments of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized schedule comments of this event
	*/
	@Override
	public java.lang.String getScheduleComments(java.lang.String languageId,
		boolean useDefault) {
		return _event.getScheduleComments(languageId, useDefault);
	}

	/**
	* Returns the localized schedule comments of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized schedule comments of this event
	*/
	@Override
	public java.lang.String getScheduleComments(java.util.Locale locale) {
		return _event.getScheduleComments(locale);
	}

	/**
	* Returns the localized schedule comments of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized schedule comments of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getScheduleComments(java.util.Locale locale,
		boolean useDefault) {
		return _event.getScheduleComments(locale, useDefault);
	}

	@Override
	public java.lang.String getScheduleCommentsCurrentLanguageId() {
		return _event.getScheduleCommentsCurrentLanguageId();
	}

	@Override
	public java.lang.String getScheduleCommentsCurrentValue() {
		return _event.getScheduleCommentsCurrentValue();
	}

	/**
	* Returns the source of this event.
	*
	* @return the source of this event
	*/
	@Override
	public java.lang.String getSource() {
		return _event.getSource();
	}

	/**
	* Returns the status by user name of this event.
	*
	* @return the status by user name of this event
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _event.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this event.
	*
	* @return the status by user uuid of this event
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _event.getStatusByUserUuid();
	}

	/**
	* Returns the subtitle of this event.
	*
	* @return the subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle() {
		return _event.getSubtitle();
	}

	/**
	* Returns the localized subtitle of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId) {
		return _event.getSubtitle(languageId);
	}

	/**
	* Returns the localized subtitle of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId,
		boolean useDefault) {
		return _event.getSubtitle(languageId, useDefault);
	}

	/**
	* Returns the localized subtitle of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subtitle of this event
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale) {
		return _event.getSubtitle(locale);
	}

	/**
	* Returns the localized subtitle of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized subtitle of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale,
		boolean useDefault) {
		return _event.getSubtitle(locale, useDefault);
	}

	@Override
	public java.lang.String getSubtitleCurrentLanguageId() {
		return _event.getSubtitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubtitleCurrentValue() {
		return _event.getSubtitleCurrentValue();
	}

	/**
	* Returns the title of this event.
	*
	* @return the title of this event
	*/
	@Override
	public java.lang.String getTitle() {
		return _event.getTitle();
	}

	/**
	* Returns the localized title of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _event.getTitle(languageId);
	}

	/**
	* Returns the localized title of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _event.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this event
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _event.getTitle(locale);
	}

	/**
	* Returns the localized title of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _event.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _event.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _event.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this event.
	*
	* @return the user name of this event
	*/
	@Override
	public java.lang.String getUserName() {
		return _event.getUserName();
	}

	/**
	* Returns the user uuid of this event.
	*
	* @return the user uuid of this event
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _event.getUserUuid();
	}

	/**
	* Returns the uuid of this event.
	*
	* @return the uuid of this event
	*/
	@Override
	public java.lang.String getUuid() {
		return _event.getUuid();
	}

	/**
	* Returns the website name of this event.
	*
	* @return the website name of this event
	*/
	@Override
	public java.lang.String getWebsiteName() {
		return _event.getWebsiteName();
	}

	/**
	* Returns the localized website name of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized website name of this event
	*/
	@Override
	public java.lang.String getWebsiteName(java.lang.String languageId) {
		return _event.getWebsiteName(languageId);
	}

	/**
	* Returns the localized website name of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website name of this event
	*/
	@Override
	public java.lang.String getWebsiteName(java.lang.String languageId,
		boolean useDefault) {
		return _event.getWebsiteName(languageId, useDefault);
	}

	/**
	* Returns the localized website name of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized website name of this event
	*/
	@Override
	public java.lang.String getWebsiteName(java.util.Locale locale) {
		return _event.getWebsiteName(locale);
	}

	/**
	* Returns the localized website name of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website name of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getWebsiteName(java.util.Locale locale,
		boolean useDefault) {
		return _event.getWebsiteName(locale, useDefault);
	}

	@Override
	public java.lang.String getWebsiteNameCurrentLanguageId() {
		return _event.getWebsiteNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getWebsiteNameCurrentValue() {
		return _event.getWebsiteNameCurrentValue();
	}

	/**
	* Returns the website u r l of this event.
	*
	* @return the website u r l of this event
	*/
	@Override
	public java.lang.String getWebsiteURL() {
		return _event.getWebsiteURL();
	}

	/**
	* Returns the localized website u r l of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized website u r l of this event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.lang.String languageId) {
		return _event.getWebsiteURL(languageId);
	}

	/**
	* Returns the localized website u r l of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website u r l of this event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.lang.String languageId,
		boolean useDefault) {
		return _event.getWebsiteURL(languageId, useDefault);
	}

	/**
	* Returns the localized website u r l of this event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized website u r l of this event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.util.Locale locale) {
		return _event.getWebsiteURL(locale);
	}

	/**
	* Returns the localized website u r l of this event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website u r l of this event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getWebsiteURL(java.util.Locale locale,
		boolean useDefault) {
		return _event.getWebsiteURL(locale, useDefault);
	}

	@Override
	public java.lang.String getWebsiteURLCurrentLanguageId() {
		return _event.getWebsiteURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getWebsiteURLCurrentValue() {
		return _event.getWebsiteURLCurrentValue();
	}

	@Override
	public java.lang.String toString() {
		return _event.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _event.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _event.getAvailableLanguageIds();
	}

	/**
	* Returns the create date of this event.
	*
	* @return the create date of this event
	*/
	@Override
	public Date getCreateDate() {
		return _event.getCreateDate();
	}

	/**
	* Returns the display date of this event.
	*
	* @return the display date of this event
	*/
	@Override
	public Date getDisplayDate() {
		return _event.getDisplayDate();
	}

	/**
	* Returns the first start date of this event.
	*
	* @return the first start date of this event
	*/
	@Override
	public Date getFirstStartDate() {
		return _event.getFirstStartDate();
	}

	/**
	* Returns the last end date of this event.
	*
	* @return the last end date of this event
	*/
	@Override
	public Date getLastEndDate() {
		return _event.getLastEndDate();
	}

	/**
	* Returns the last publish date of this event.
	*
	* @return the last publish date of this event
	*/
	@Override
	public Date getLastPublishDate() {
		return _event.getLastPublishDate();
	}

	/**
	* Returns the modified date of this event.
	*
	* @return the modified date of this event
	*/
	@Override
	public Date getModifiedDate() {
		return _event.getModifiedDate();
	}

	/**
	* Returns the status date of this event.
	*
	* @return the status date of this event
	*/
	@Override
	public Date getStatusDate() {
		return _event.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _event.getCategories();
	}

	/**
	* Retourne la liste des périodes auxquelles l'événement à lieu (classées
	* par date de début croissante)
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getEventPeriods() {
		return _event.getEventPeriods();
	}

	/**
	* Retourne la liste des manifestations auxquelles cette édition appartient
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations() {
		return _event.getManifestations();
	}

	/**
	* Retourne la liste des galeries publiées
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getPublishedManifestations() {
		return _event.getPublishedManifestations();
	}

	/**
	* Returns a map of the locales and localized access for disableds of this event.
	*
	* @return the locales and localized access for disableds of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAccessForDisabledMap() {
		return _event.getAccessForDisabledMap();
	}

	/**
	* Returns a map of the locales and localized accesses of this event.
	*
	* @return the locales and localized accesses of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getAccessMap() {
		return _event.getAccessMap();
	}

	/**
	* Returns a map of the locales and localized descriptions of this event.
	*
	* @return the locales and localized descriptions of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _event.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized prices of this event.
	*
	* @return the locales and localized prices of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPriceMap() {
		return _event.getPriceMap();
	}

	/**
	* Returns a map of the locales and localized schedule commentses of this event.
	*
	* @return the locales and localized schedule commentses of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getScheduleCommentsMap() {
		return _event.getScheduleCommentsMap();
	}

	/**
	* Returns a map of the locales and localized subtitles of this event.
	*
	* @return the locales and localized subtitles of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSubtitleMap() {
		return _event.getSubtitleMap();
	}

	/**
	* Returns a map of the locales and localized titles of this event.
	*
	* @return the locales and localized titles of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _event.getTitleMap();
	}

	/**
	* Returns a map of the locales and localized website names of this event.
	*
	* @return the locales and localized website names of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getWebsiteNameMap() {
		return _event.getWebsiteNameMap();
	}

	/**
	* Returns a map of the locales and localized website u r ls of this event.
	*
	* @return the locales and localized website u r ls of this event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getWebsiteURLMap() {
		return _event.getWebsiteURLMap();
	}

	/**
	* Returns the company ID of this event.
	*
	* @return the company ID of this event
	*/
	@Override
	public long getCompanyId() {
		return _event.getCompanyId();
	}

	/**
	* Returns the event ID of this event.
	*
	* @return the event ID of this event
	*/
	@Override
	public long getEventId() {
		return _event.getEventId();
	}

	/**
	* Returns the group ID of this event.
	*
	* @return the group ID of this event
	*/
	@Override
	public long getGroupId() {
		return _event.getGroupId();
	}

	/**
	* Returns the primary key of this event.
	*
	* @return the primary key of this event
	*/
	@Override
	public long getPrimaryKey() {
		return _event.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this event.
	*
	* @return the status by user ID of this event
	*/
	@Override
	public long getStatusByUserId() {
		return _event.getStatusByUserId();
	}

	/**
	* Returns the user ID of this event.
	*
	* @return the user ID of this event
	*/
	@Override
	public long getUserId() {
		return _event.getUserId();
	}

	@Override
	public void persist() {
		_event.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_event.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_event.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the access of this event.
	*
	* @param access the access of this event
	*/
	@Override
	public void setAccess(java.lang.String access) {
		_event.setAccess(access);
	}

	/**
	* Sets the localized access of this event in the language.
	*
	* @param access the localized access of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setAccess(java.lang.String access, java.util.Locale locale) {
		_event.setAccess(access, locale);
	}

	/**
	* Sets the localized access of this event in the language, and sets the default locale.
	*
	* @param access the localized access of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccess(java.lang.String access, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setAccess(access, locale, defaultLocale);
	}

	@Override
	public void setAccessCurrentLanguageId(java.lang.String languageId) {
		_event.setAccessCurrentLanguageId(languageId);
	}

	/**
	* Sets the access for blind of this event.
	*
	* @param accessForBlind the access for blind of this event
	*/
	@Override
	public void setAccessForBlind(java.lang.Boolean accessForBlind) {
		_event.setAccessForBlind(accessForBlind);
	}

	/**
	* Sets the access for deaf of this event.
	*
	* @param accessForDeaf the access for deaf of this event
	*/
	@Override
	public void setAccessForDeaf(java.lang.Boolean accessForDeaf) {
		_event.setAccessForDeaf(accessForDeaf);
	}

	/**
	* Sets the access for deficient of this event.
	*
	* @param accessForDeficient the access for deficient of this event
	*/
	@Override
	public void setAccessForDeficient(java.lang.Boolean accessForDeficient) {
		_event.setAccessForDeficient(accessForDeficient);
	}

	/**
	* Sets the access for disabled of this event.
	*
	* @param accessForDisabled the access for disabled of this event
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled) {
		_event.setAccessForDisabled(accessForDisabled);
	}

	/**
	* Sets the localized access for disabled of this event in the language.
	*
	* @param accessForDisabled the localized access for disabled of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled,
		java.util.Locale locale) {
		_event.setAccessForDisabled(accessForDisabled, locale);
	}

	/**
	* Sets the localized access for disabled of this event in the language, and sets the default locale.
	*
	* @param accessForDisabled the localized access for disabled of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessForDisabled(java.lang.String accessForDisabled,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setAccessForDisabled(accessForDisabled, locale, defaultLocale);
	}

	@Override
	public void setAccessForDisabledCurrentLanguageId(
		java.lang.String languageId) {
		_event.setAccessForDisabledCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized access for disableds of this event from the map of locales and localized access for disableds.
	*
	* @param accessForDisabledMap the locales and localized access for disableds of this event
	*/
	@Override
	public void setAccessForDisabledMap(
		Map<java.util.Locale, java.lang.String> accessForDisabledMap) {
		_event.setAccessForDisabledMap(accessForDisabledMap);
	}

	/**
	* Sets the localized access for disableds of this event from the map of locales and localized access for disableds, and sets the default locale.
	*
	* @param accessForDisabledMap the locales and localized access for disableds of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessForDisabledMap(
		Map<java.util.Locale, java.lang.String> accessForDisabledMap,
		java.util.Locale defaultLocale) {
		_event.setAccessForDisabledMap(accessForDisabledMap, defaultLocale);
	}

	/**
	* Sets the access for elder of this event.
	*
	* @param accessForElder the access for elder of this event
	*/
	@Override
	public void setAccessForElder(java.lang.Boolean accessForElder) {
		_event.setAccessForElder(accessForElder);
	}

	/**
	* Sets the access for wheelchair of this event.
	*
	* @param accessForWheelchair the access for wheelchair of this event
	*/
	@Override
	public void setAccessForWheelchair(java.lang.Boolean accessForWheelchair) {
		_event.setAccessForWheelchair(accessForWheelchair);
	}

	/**
	* Sets the localized accesses of this event from the map of locales and localized accesses.
	*
	* @param accessMap the locales and localized accesses of this event
	*/
	@Override
	public void setAccessMap(Map<java.util.Locale, java.lang.String> accessMap) {
		_event.setAccessMap(accessMap);
	}

	/**
	* Sets the localized accesses of this event from the map of locales and localized accesses, and sets the default locale.
	*
	* @param accessMap the locales and localized accesses of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAccessMap(
		Map<java.util.Locale, java.lang.String> accessMap,
		java.util.Locale defaultLocale) {
		_event.setAccessMap(accessMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_event.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this event.
	*
	* @param companyId the company ID of this event
	*/
	@Override
	public void setCompanyId(long companyId) {
		_event.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this event.
	*
	* @param createDate the create date of this event
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_event.setCreateDate(createDate);
	}

	/**
	* Sets the description of this event.
	*
	* @param description the description of this event
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_event.setDescription(description);
	}

	/**
	* Sets the localized description of this event in the language.
	*
	* @param description the localized description of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_event.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this event in the language, and sets the default locale.
	*
	* @param description the localized description of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_event.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this event from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this event
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_event.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this event from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_event.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the display date of this event.
	*
	* @param displayDate the display date of this event
	*/
	@Override
	public void setDisplayDate(Date displayDate) {
		_event.setDisplayDate(displayDate);
	}

	/**
	* Sets the email of this event.
	*
	* @param email the email of this event
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_event.setEmail(email);
	}

	/**
	* Sets the event ID of this event.
	*
	* @param eventId the event ID of this event
	*/
	@Override
	public void setEventId(long eventId) {
		_event.setEventId(eventId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_event.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_event.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_event.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the external image copyright of this event.
	*
	* @param externalImageCopyright the external image copyright of this event
	*/
	@Override
	public void setExternalImageCopyright(
		java.lang.String externalImageCopyright) {
		_event.setExternalImageCopyright(externalImageCopyright);
	}

	/**
	* Sets the external image u r l of this event.
	*
	* @param externalImageURL the external image u r l of this event
	*/
	@Override
	public void setExternalImageURL(java.lang.String externalImageURL) {
		_event.setExternalImageURL(externalImageURL);
	}

	/**
	* Sets the first start date of this event.
	*
	* @param firstStartDate the first start date of this event
	*/
	@Override
	public void setFirstStartDate(Date firstStartDate) {
		_event.setFirstStartDate(firstStartDate);
	}

	/**
	* Sets the free of this event.
	*
	* @param free the free of this event
	*/
	@Override
	public void setFree(java.lang.Integer free) {
		_event.setFree(free);
	}

	/**
	* Sets the group ID of this event.
	*
	* @param groupId the group ID of this event
	*/
	@Override
	public void setGroupId(long groupId) {
		_event.setGroupId(groupId);
	}

	/**
	* Sets the image ID of this event.
	*
	* @param imageId the image ID of this event
	*/
	@Override
	public void setImageId(java.lang.Long imageId) {
		_event.setImageId(imageId);
	}

	/**
	* Sets the last end date of this event.
	*
	* @param lastEndDate the last end date of this event
	*/
	@Override
	public void setLastEndDate(Date lastEndDate) {
		_event.setLastEndDate(lastEndDate);
	}

	/**
	* Sets the last publish date of this event.
	*
	* @param lastPublishDate the last publish date of this event
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_event.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this event.
	*
	* @param modifiedDate the modified date of this event
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_event.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_event.setNew(n);
	}

	/**
	* Sets the phone of this event.
	*
	* @param phone the phone of this event
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_event.setPhone(phone);
	}

	/**
	* Sets the place city of this event.
	*
	* @param placeCity the place city of this event
	*/
	@Override
	public void setPlaceCity(java.lang.String placeCity) {
		_event.setPlaceCity(placeCity);
	}

	/**
	* Sets the place country of this event.
	*
	* @param placeCountry the place country of this event
	*/
	@Override
	public void setPlaceCountry(java.lang.String placeCountry) {
		_event.setPlaceCountry(placeCountry);
	}

	/**
	* Sets the place name of this event.
	*
	* @param placeName the place name of this event
	*/
	@Override
	public void setPlaceName(java.lang.String placeName) {
		_event.setPlaceName(placeName);
	}

	/**
	* Sets the place s i g ID of this event.
	*
	* @param placeSIGId the place s i g ID of this event
	*/
	@Override
	public void setPlaceSIGId(java.lang.String placeSIGId) {
		_event.setPlaceSIGId(placeSIGId);
	}

	/**
	* Sets the place street name of this event.
	*
	* @param placeStreetName the place street name of this event
	*/
	@Override
	public void setPlaceStreetName(java.lang.String placeStreetName) {
		_event.setPlaceStreetName(placeStreetName);
	}

	/**
	* Sets the place street number of this event.
	*
	* @param placeStreetNumber the place street number of this event
	*/
	@Override
	public void setPlaceStreetNumber(java.lang.String placeStreetNumber) {
		_event.setPlaceStreetNumber(placeStreetNumber);
	}

	/**
	* Sets the place zip code of this event.
	*
	* @param placeZipCode the place zip code of this event
	*/
	@Override
	public void setPlaceZipCode(java.lang.String placeZipCode) {
		_event.setPlaceZipCode(placeZipCode);
	}

	/**
	* Sets the price of this event.
	*
	* @param price the price of this event
	*/
	@Override
	public void setPrice(java.lang.String price) {
		_event.setPrice(price);
	}

	/**
	* Sets the localized price of this event in the language.
	*
	* @param price the localized price of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale) {
		_event.setPrice(price, locale);
	}

	/**
	* Sets the localized price of this event in the language, and sets the default locale.
	*
	* @param price the localized price of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setPrice(price, locale, defaultLocale);
	}

	@Override
	public void setPriceCurrentLanguageId(java.lang.String languageId) {
		_event.setPriceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized prices of this event from the map of locales and localized prices.
	*
	* @param priceMap the locales and localized prices of this event
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap) {
		_event.setPriceMap(priceMap);
	}

	/**
	* Sets the localized prices of this event from the map of locales and localized prices, and sets the default locale.
	*
	* @param priceMap the locales and localized prices of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap,
		java.util.Locale defaultLocale) {
		_event.setPriceMap(priceMap, defaultLocale);
	}

	/**
	* Sets the primary key of this event.
	*
	* @param primaryKey the primary key of this event
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_event.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_event.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the promoter of this event.
	*
	* @param promoter the promoter of this event
	*/
	@Override
	public void setPromoter(java.lang.String promoter) {
		_event.setPromoter(promoter);
	}

	/**
	* Sets the schedule comments of this event.
	*
	* @param scheduleComments the schedule comments of this event
	*/
	@Override
	public void setScheduleComments(java.lang.String scheduleComments) {
		_event.setScheduleComments(scheduleComments);
	}

	/**
	* Sets the localized schedule comments of this event in the language.
	*
	* @param scheduleComments the localized schedule comments of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setScheduleComments(java.lang.String scheduleComments,
		java.util.Locale locale) {
		_event.setScheduleComments(scheduleComments, locale);
	}

	/**
	* Sets the localized schedule comments of this event in the language, and sets the default locale.
	*
	* @param scheduleComments the localized schedule comments of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setScheduleComments(java.lang.String scheduleComments,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setScheduleComments(scheduleComments, locale, defaultLocale);
	}

	@Override
	public void setScheduleCommentsCurrentLanguageId(
		java.lang.String languageId) {
		_event.setScheduleCommentsCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized schedule commentses of this event from the map of locales and localized schedule commentses.
	*
	* @param scheduleCommentsMap the locales and localized schedule commentses of this event
	*/
	@Override
	public void setScheduleCommentsMap(
		Map<java.util.Locale, java.lang.String> scheduleCommentsMap) {
		_event.setScheduleCommentsMap(scheduleCommentsMap);
	}

	/**
	* Sets the localized schedule commentses of this event from the map of locales and localized schedule commentses, and sets the default locale.
	*
	* @param scheduleCommentsMap the locales and localized schedule commentses of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setScheduleCommentsMap(
		Map<java.util.Locale, java.lang.String> scheduleCommentsMap,
		java.util.Locale defaultLocale) {
		_event.setScheduleCommentsMap(scheduleCommentsMap, defaultLocale);
	}

	/**
	* Sets the source of this event.
	*
	* @param source the source of this event
	*/
	@Override
	public void setSource(java.lang.String source) {
		_event.setSource(source);
	}

	/**
	* Sets the status of this event.
	*
	* @param status the status of this event
	*/
	@Override
	public void setStatus(int status) {
		_event.setStatus(status);
	}

	/**
	* Sets the status by user ID of this event.
	*
	* @param statusByUserId the status by user ID of this event
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_event.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this event.
	*
	* @param statusByUserName the status by user name of this event
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_event.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this event.
	*
	* @param statusByUserUuid the status by user uuid of this event
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_event.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this event.
	*
	* @param statusDate the status date of this event
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_event.setStatusDate(statusDate);
	}

	/**
	* Sets the subtitle of this event.
	*
	* @param subtitle the subtitle of this event
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle) {
		_event.setSubtitle(subtitle);
	}

	/**
	* Sets the localized subtitle of this event in the language.
	*
	* @param subtitle the localized subtitle of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale) {
		_event.setSubtitle(subtitle, locale);
	}

	/**
	* Sets the localized subtitle of this event in the language, and sets the default locale.
	*
	* @param subtitle the localized subtitle of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(java.lang.String languageId) {
		_event.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subtitles of this event from the map of locales and localized subtitles.
	*
	* @param subtitleMap the locales and localized subtitles of this event
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap) {
		_event.setSubtitleMap(subtitleMap);
	}

	/**
	* Sets the localized subtitles of this event from the map of locales and localized subtitles, and sets the default locale.
	*
	* @param subtitleMap the locales and localized subtitles of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap,
		java.util.Locale defaultLocale) {
		_event.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	* Sets the title of this event.
	*
	* @param title the title of this event
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_event.setTitle(title);
	}

	/**
	* Sets the localized title of this event in the language.
	*
	* @param title the localized title of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_event.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this event in the language, and sets the default locale.
	*
	* @param title the localized title of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_event.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_event.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this event from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this event
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_event.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this event from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_event.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this event.
	*
	* @param userId the user ID of this event
	*/
	@Override
	public void setUserId(long userId) {
		_event.setUserId(userId);
	}

	/**
	* Sets the user name of this event.
	*
	* @param userName the user name of this event
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_event.setUserName(userName);
	}

	/**
	* Sets the user uuid of this event.
	*
	* @param userUuid the user uuid of this event
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_event.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this event.
	*
	* @param uuid the uuid of this event
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_event.setUuid(uuid);
	}

	/**
	* Sets the website name of this event.
	*
	* @param websiteName the website name of this event
	*/
	@Override
	public void setWebsiteName(java.lang.String websiteName) {
		_event.setWebsiteName(websiteName);
	}

	/**
	* Sets the localized website name of this event in the language.
	*
	* @param websiteName the localized website name of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setWebsiteName(java.lang.String websiteName,
		java.util.Locale locale) {
		_event.setWebsiteName(websiteName, locale);
	}

	/**
	* Sets the localized website name of this event in the language, and sets the default locale.
	*
	* @param websiteName the localized website name of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteName(java.lang.String websiteName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setWebsiteName(websiteName, locale, defaultLocale);
	}

	@Override
	public void setWebsiteNameCurrentLanguageId(java.lang.String languageId) {
		_event.setWebsiteNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized website names of this event from the map of locales and localized website names.
	*
	* @param websiteNameMap the locales and localized website names of this event
	*/
	@Override
	public void setWebsiteNameMap(
		Map<java.util.Locale, java.lang.String> websiteNameMap) {
		_event.setWebsiteNameMap(websiteNameMap);
	}

	/**
	* Sets the localized website names of this event from the map of locales and localized website names, and sets the default locale.
	*
	* @param websiteNameMap the locales and localized website names of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteNameMap(
		Map<java.util.Locale, java.lang.String> websiteNameMap,
		java.util.Locale defaultLocale) {
		_event.setWebsiteNameMap(websiteNameMap, defaultLocale);
	}

	/**
	* Sets the website u r l of this event.
	*
	* @param websiteURL the website u r l of this event
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL) {
		_event.setWebsiteURL(websiteURL);
	}

	/**
	* Sets the localized website u r l of this event in the language.
	*
	* @param websiteURL the localized website u r l of this event
	* @param locale the locale of the language
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL,
		java.util.Locale locale) {
		_event.setWebsiteURL(websiteURL, locale);
	}

	/**
	* Sets the localized website u r l of this event in the language, and sets the default locale.
	*
	* @param websiteURL the localized website u r l of this event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_event.setWebsiteURL(websiteURL, locale, defaultLocale);
	}

	@Override
	public void setWebsiteURLCurrentLanguageId(java.lang.String languageId) {
		_event.setWebsiteURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized website u r ls of this event from the map of locales and localized website u r ls.
	*
	* @param websiteURLMap the locales and localized website u r ls of this event
	*/
	@Override
	public void setWebsiteURLMap(
		Map<java.util.Locale, java.lang.String> websiteURLMap) {
		_event.setWebsiteURLMap(websiteURLMap);
	}

	/**
	* Sets the localized website u r ls of this event from the map of locales and localized website u r ls, and sets the default locale.
	*
	* @param websiteURLMap the locales and localized website u r ls of this event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteURLMap(
		Map<java.util.Locale, java.lang.String> websiteURLMap,
		java.util.Locale defaultLocale) {
		_event.setWebsiteURLMap(websiteURLMap, defaultLocale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventWrapper)) {
			return false;
		}

		EventWrapper eventWrapper = (EventWrapper)obj;

		if (Objects.equals(_event, eventWrapper._event)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _event.getStagedModelType();
	}

	@Override
	public Event getWrappedModel() {
		return _event;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _event.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _event.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_event.resetOriginalValues();
	}

	private final Event _event;
}
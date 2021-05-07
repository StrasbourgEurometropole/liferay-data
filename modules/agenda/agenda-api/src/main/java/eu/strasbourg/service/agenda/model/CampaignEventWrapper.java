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
 * This class is a wrapper for {@link CampaignEvent}.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEvent
 * @generated
 */
@ProviderType
public class CampaignEventWrapper
	implements CampaignEvent, ModelWrapper<CampaignEvent> {

	public CampaignEventWrapper(CampaignEvent campaignEvent) {
		_campaignEvent = campaignEvent;
	}

	@Override
	public Class<?> getModelClass() {
		return CampaignEvent.class;
	}

	@Override
	public String getModelClassName() {
		return CampaignEvent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("campaignEventId", getCampaignEventId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("phone", getPhone());
		attributes.put("email", getEmail());
		attributes.put("serviceId", getServiceId());
		attributes.put("service", getService());
		attributes.put("onSiteFirstName", getOnSiteFirstName());
		attributes.put("onSiteLastName", getOnSiteLastName());
		attributes.put("onSitePhone", getOnSitePhone());
		attributes.put("title", getTitle());
		attributes.put("subtitle", getSubtitle());
		attributes.put("description", getDescription());
		attributes.put("imageId", getImageId());
		attributes.put("webImageId", getWebImageId());
		attributes.put("imageOwner", getImageOwner());
		attributes.put("manifestationsIds", getManifestationsIds());
		attributes.put("placeSIGId", getPlaceSIGId());
		attributes.put("placeName", getPlaceName());
		attributes.put("placeStreetNumber", getPlaceStreetNumber());
		attributes.put("placeStreetName", getPlaceStreetName());
		attributes.put("placeZipCode", getPlaceZipCode());
		attributes.put("placeCityId", getPlaceCityId());
		attributes.put("placeCountry", getPlaceCountry());
		attributes.put("promoter", getPromoter());
		attributes.put("publicPhone", getPublicPhone());
		attributes.put("publicEmail", getPublicEmail());
		attributes.put("websiteURL", getWebsiteURL());
		attributes.put("websiteName", getWebsiteName());
		attributes.put("free", getFree());
		attributes.put("price", getPrice());
		attributes.put("campaignId", getCampaignId());
		attributes.put("themesIds", getThemesIds());
		attributes.put("typesIds", getTypesIds());
		attributes.put("publicsIds", getPublicsIds());
		attributes.put("bookingDescription", getBookingDescription());
		attributes.put("bookingURL", getBookingURL());
		attributes.put("registration", isRegistration());
		attributes.put("maxGauge", getMaxGauge());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long campaignEventId = (Long)attributes.get("campaignEventId");

		if (campaignEventId != null) {
			setCampaignEventId(campaignEventId);
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

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		String service = (String)attributes.get("service");

		if (service != null) {
			setService(service);
		}

		String onSiteFirstName = (String)attributes.get("onSiteFirstName");

		if (onSiteFirstName != null) {
			setOnSiteFirstName(onSiteFirstName);
		}

		String onSiteLastName = (String)attributes.get("onSiteLastName");

		if (onSiteLastName != null) {
			setOnSiteLastName(onSiteLastName);
		}

		String onSitePhone = (String)attributes.get("onSitePhone");

		if (onSitePhone != null) {
			setOnSitePhone(onSitePhone);
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

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		Long webImageId = (Long)attributes.get("webImageId");

		if (webImageId != null) {
			setWebImageId(webImageId);
		}

		String imageOwner = (String)attributes.get("imageOwner");

		if (imageOwner != null) {
			setImageOwner(imageOwner);
		}

		String manifestationsIds = (String)attributes.get("manifestationsIds");

		if (manifestationsIds != null) {
			setManifestationsIds(manifestationsIds);
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

		Long placeCityId = (Long)attributes.get("placeCityId");

		if (placeCityId != null) {
			setPlaceCityId(placeCityId);
		}

		String placeCountry = (String)attributes.get("placeCountry");

		if (placeCountry != null) {
			setPlaceCountry(placeCountry);
		}

		String promoter = (String)attributes.get("promoter");

		if (promoter != null) {
			setPromoter(promoter);
		}

		String publicPhone = (String)attributes.get("publicPhone");

		if (publicPhone != null) {
			setPublicPhone(publicPhone);
		}

		String publicEmail = (String)attributes.get("publicEmail");

		if (publicEmail != null) {
			setPublicEmail(publicEmail);
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

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		String themesIds = (String)attributes.get("themesIds");

		if (themesIds != null) {
			setThemesIds(themesIds);
		}

		String typesIds = (String)attributes.get("typesIds");

		if (typesIds != null) {
			setTypesIds(typesIds);
		}

		String publicsIds = (String)attributes.get("publicsIds");

		if (publicsIds != null) {
			setPublicsIds(publicsIds);
		}

		String bookingDescription = (String)attributes.get(
			"bookingDescription");

		if (bookingDescription != null) {
			setBookingDescription(bookingDescription);
		}

		String bookingURL = (String)attributes.get("bookingURL");

		if (bookingURL != null) {
			setBookingURL(bookingURL);
		}

		Boolean registration = (Boolean)attributes.get("registration");

		if (registration != null) {
			setRegistration(registration);
		}

		Long maxGauge = (Long)attributes.get("maxGauge");

		if (maxGauge != null) {
			setMaxGauge(maxGauge);
		}
	}

	@Override
	public Object clone() {
		return new CampaignEventWrapper((CampaignEvent)_campaignEvent.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {

		return _campaignEvent.compareTo(campaignEvent);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _campaignEvent.getAvailableLanguageIds();
	}

	/**
	 * Returns the booking description of this campaign event.
	 *
	 * @return the booking description of this campaign event
	 */
	@Override
	public String getBookingDescription() {
		return _campaignEvent.getBookingDescription();
	}

	/**
	 * Returns the localized booking description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized booking description of this campaign event
	 */
	@Override
	public String getBookingDescription(java.util.Locale locale) {
		return _campaignEvent.getBookingDescription(locale);
	}

	/**
	 * Returns the localized booking description of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized booking description of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getBookingDescription(
		java.util.Locale locale, boolean useDefault) {

		return _campaignEvent.getBookingDescription(locale, useDefault);
	}

	/**
	 * Returns the localized booking description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized booking description of this campaign event
	 */
	@Override
	public String getBookingDescription(String languageId) {
		return _campaignEvent.getBookingDescription(languageId);
	}

	/**
	 * Returns the localized booking description of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized booking description of this campaign event
	 */
	@Override
	public String getBookingDescription(String languageId, boolean useDefault) {
		return _campaignEvent.getBookingDescription(languageId, useDefault);
	}

	@Override
	public String getBookingDescriptionCurrentLanguageId() {
		return _campaignEvent.getBookingDescriptionCurrentLanguageId();
	}

	@Override
	public String getBookingDescriptionCurrentValue() {
		return _campaignEvent.getBookingDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized booking descriptions of this campaign event.
	 *
	 * @return the locales and localized booking descriptions of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getBookingDescriptionMap() {
		return _campaignEvent.getBookingDescriptionMap();
	}

	/**
	 * Returns the booking url of this campaign event.
	 *
	 * @return the booking url of this campaign event
	 */
	@Override
	public String getBookingURL() {
		return _campaignEvent.getBookingURL();
	}

	@Override
	public eu.strasbourg.service.agenda.model.Campaign getCampaign() {
		return _campaignEvent.getCampaign();
	}

	/**
	 * Returns the campaign event ID of this campaign event.
	 *
	 * @return the campaign event ID of this campaign event
	 */
	@Override
	public long getCampaignEventId() {
		return _campaignEvent.getCampaignEventId();
	}

	/**
	 * Returns the campaign ID of this campaign event.
	 *
	 * @return the campaign ID of this campaign event
	 */
	@Override
	public Long getCampaignId() {
		return _campaignEvent.getCampaignId();
	}

	/**
	 * Retourne le territoire (la ville) de l'événement (si lieu manuel)
	 */
	@Override
	public String getCity() {
		return _campaignEvent.getCity();
	}

	/**
	 * Returns the company ID of this campaign event.
	 *
	 * @return the company ID of this campaign event
	 */
	@Override
	public long getCompanyId() {
		return _campaignEvent.getCompanyId();
	}

	/**
	 * Returns the create date of this campaign event.
	 *
	 * @return the create date of this campaign event
	 */
	@Override
	public Date getCreateDate() {
		return _campaignEvent.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _campaignEvent.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this campaign event.
	 *
	 * @return the description of this campaign event
	 */
	@Override
	public String getDescription() {
		return _campaignEvent.getDescription();
	}

	/**
	 * Returns the localized description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this campaign event
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _campaignEvent.getDescription(locale);
	}

	/**
	 * Returns the localized description of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this campaign event
	 */
	@Override
	public String getDescription(String languageId) {
		return _campaignEvent.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this campaign event
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _campaignEvent.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _campaignEvent.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _campaignEvent.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this campaign event.
	 *
	 * @return the locales and localized descriptions of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _campaignEvent.getDescriptionMap();
	}

	/**
	 * Returns the email of this campaign event.
	 *
	 * @return the email of this campaign event
	 */
	@Override
	public String getEmail() {
		return _campaignEvent.getEmail();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _campaignEvent.getExpandoBridge();
	}

	/**
	 * Returns the first name of this campaign event.
	 *
	 * @return the first name of this campaign event
	 */
	@Override
	public String getFirstName() {
		return _campaignEvent.getFirstName();
	}

	/**
	 * Returns the free of this campaign event.
	 *
	 * @return the free of this campaign event
	 */
	@Override
	public Integer getFree() {
		return _campaignEvent.getFree();
	}

	/**
	 * Returns the group ID of this campaign event.
	 *
	 * @return the group ID of this campaign event
	 */
	@Override
	public long getGroupId() {
		return _campaignEvent.getGroupId();
	}

	/**
	 * Returns the image ID of this campaign event.
	 *
	 * @return the image ID of this campaign event
	 */
	@Override
	public Long getImageId() {
		return _campaignEvent.getImageId();
	}

	/**
	 * Returns the image owner of this campaign event.
	 *
	 * @return the image owner of this campaign event
	 */
	@Override
	public String getImageOwner() {
		return _campaignEvent.getImageOwner();
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return _campaignEvent.getImageURL();
	}

	/**
	 * Returns the last name of this campaign event.
	 *
	 * @return the last name of this campaign event
	 */
	@Override
	public String getLastName() {
		return _campaignEvent.getLastName();
	}

	/**
	 * Returns the last publish date of this campaign event.
	 *
	 * @return the last publish date of this campaign event
	 */
	@Override
	public Date getLastPublishDate() {
		return _campaignEvent.getLastPublishDate();
	}

	/**
	 * Renvoie le dernier statut en date de l'événement
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus
		getLastStatus() {

		return _campaignEvent.getLastStatus();
	}

	/**
	 * Retourne l'objet "LegacyPlace" correspondant au lieu de l'événement, s'il
	 * existe
	 */
	@Override
	public eu.strasbourg.utils.models.LegacyPlace getLegacyPlace(
		java.util.Locale locale) {

		return _campaignEvent.getLegacyPlace(locale);
	}

	/**
	 * Retourne le label des manifestations de l'événement
	 */
	@Override
	public String getManifestationLabel(java.util.Locale locale) {
		return _campaignEvent.getManifestationLabel(locale);
	}

	/**
	 * Returns the manifestations IDs of this campaign event.
	 *
	 * @return the manifestations IDs of this campaign event
	 */
	@Override
	public String getManifestationsIds() {
		return _campaignEvent.getManifestationsIds();
	}

	/**
	 * Returns the max gauge of this campaign event.
	 *
	 * @return the max gauge of this campaign event
	 */
	@Override
	public long getMaxGauge() {
		return _campaignEvent.getMaxGauge();
	}

	/**
	 * Returns the modified date of this campaign event.
	 *
	 * @return the modified date of this campaign event
	 */
	@Override
	public Date getModifiedDate() {
		return _campaignEvent.getModifiedDate();
	}

	/**
	 * Returns the on site first name of this campaign event.
	 *
	 * @return the on site first name of this campaign event
	 */
	@Override
	public String getOnSiteFirstName() {
		return _campaignEvent.getOnSiteFirstName();
	}

	/**
	 * Returns the on site last name of this campaign event.
	 *
	 * @return the on site last name of this campaign event
	 */
	@Override
	public String getOnSiteLastName() {
		return _campaignEvent.getOnSiteLastName();
	}

	/**
	 * Returns the on site phone of this campaign event.
	 *
	 * @return the on site phone of this campaign event
	 */
	@Override
	public String getOnSitePhone() {
		return _campaignEvent.getOnSitePhone();
	}

	/**
	 * Retourne les périodes
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod>
		getPeriods() {

		return _campaignEvent.getPeriods();
	}

	/**
	 * Returns the phone of this campaign event.
	 *
	 * @return the phone of this campaign event
	 */
	@Override
	public String getPhone() {
		return _campaignEvent.getPhone();
	}

	/**
	 * Retourne le nom lieu rattaché à l'événement
	 */
	@Override
	public String getPlaceAlias(java.util.Locale locale) {
		return _campaignEvent.getPlaceAlias(locale);
	}

	/**
	 * Returns the place city ID of this campaign event.
	 *
	 * @return the place city ID of this campaign event
	 */
	@Override
	public Long getPlaceCityId() {
		return _campaignEvent.getPlaceCityId();
	}

	/**
	 * Returns the place country of this campaign event.
	 *
	 * @return the place country of this campaign event
	 */
	@Override
	public String getPlaceCountry() {
		return _campaignEvent.getPlaceCountry();
	}

	/**
	 * Returns the place name of this campaign event.
	 *
	 * @return the place name of this campaign event
	 */
	@Override
	public String getPlaceName() {
		return _campaignEvent.getPlaceName();
	}

	/**
	 * Returns the localized place name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized place name of this campaign event
	 */
	@Override
	public String getPlaceName(java.util.Locale locale) {
		return _campaignEvent.getPlaceName(locale);
	}

	/**
	 * Returns the localized place name of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized place name of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getPlaceName(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getPlaceName(locale, useDefault);
	}

	/**
	 * Returns the localized place name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized place name of this campaign event
	 */
	@Override
	public String getPlaceName(String languageId) {
		return _campaignEvent.getPlaceName(languageId);
	}

	/**
	 * Returns the localized place name of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized place name of this campaign event
	 */
	@Override
	public String getPlaceName(String languageId, boolean useDefault) {
		return _campaignEvent.getPlaceName(languageId, useDefault);
	}

	@Override
	public String getPlaceNameCurrentLanguageId() {
		return _campaignEvent.getPlaceNameCurrentLanguageId();
	}

	@Override
	public String getPlaceNameCurrentValue() {
		return _campaignEvent.getPlaceNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized place names of this campaign event.
	 *
	 * @return the locales and localized place names of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getPlaceNameMap() {
		return _campaignEvent.getPlaceNameMap();
	}

	/**
	 * Returns the place sig ID of this campaign event.
	 *
	 * @return the place sig ID of this campaign event
	 */
	@Override
	public String getPlaceSIGId() {
		return _campaignEvent.getPlaceSIGId();
	}

	/**
	 * Returns the place street name of this campaign event.
	 *
	 * @return the place street name of this campaign event
	 */
	@Override
	public String getPlaceStreetName() {
		return _campaignEvent.getPlaceStreetName();
	}

	/**
	 * Returns the place street number of this campaign event.
	 *
	 * @return the place street number of this campaign event
	 */
	@Override
	public String getPlaceStreetNumber() {
		return _campaignEvent.getPlaceStreetNumber();
	}

	/**
	 * Returns the place zip code of this campaign event.
	 *
	 * @return the place zip code of this campaign event
	 */
	@Override
	public String getPlaceZipCode() {
		return _campaignEvent.getPlaceZipCode();
	}

	/**
	 * Returns the price of this campaign event.
	 *
	 * @return the price of this campaign event
	 */
	@Override
	public String getPrice() {
		return _campaignEvent.getPrice();
	}

	/**
	 * Returns the localized price of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized price of this campaign event
	 */
	@Override
	public String getPrice(java.util.Locale locale) {
		return _campaignEvent.getPrice(locale);
	}

	/**
	 * Returns the localized price of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized price of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getPrice(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getPrice(locale, useDefault);
	}

	/**
	 * Returns the localized price of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized price of this campaign event
	 */
	@Override
	public String getPrice(String languageId) {
		return _campaignEvent.getPrice(languageId);
	}

	/**
	 * Returns the localized price of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized price of this campaign event
	 */
	@Override
	public String getPrice(String languageId, boolean useDefault) {
		return _campaignEvent.getPrice(languageId, useDefault);
	}

	@Override
	public String getPriceCurrentLanguageId() {
		return _campaignEvent.getPriceCurrentLanguageId();
	}

	@Override
	public String getPriceCurrentValue() {
		return _campaignEvent.getPriceCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized prices of this campaign event.
	 *
	 * @return the locales and localized prices of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getPriceMap() {
		return _campaignEvent.getPriceMap();
	}

	/**
	 * Returns the primary key of this campaign event.
	 *
	 * @return the primary key of this campaign event
	 */
	@Override
	public long getPrimaryKey() {
		return _campaignEvent.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _campaignEvent.getPrimaryKeyObj();
	}

	/**
	 * Returns the promoter of this campaign event.
	 *
	 * @return the promoter of this campaign event
	 */
	@Override
	public String getPromoter() {
		return _campaignEvent.getPromoter();
	}

	/**
	 * Returns the public email of this campaign event.
	 *
	 * @return the public email of this campaign event
	 */
	@Override
	public String getPublicEmail() {
		return _campaignEvent.getPublicEmail();
	}

	/**
	 * Retourne le label des publics de l'événement
	 */
	@Override
	public String getPublicLabel(java.util.Locale locale) {
		return _campaignEvent.getPublicLabel(locale);
	}

	/**
	 * Returns the public phone of this campaign event.
	 *
	 * @return the public phone of this campaign event
	 */
	@Override
	public String getPublicPhone() {
		return _campaignEvent.getPublicPhone();
	}

	/**
	 * Retourne les publics
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getPublics() {

		return _campaignEvent.getPublics();
	}

	/**
	 * Returns the publics IDs of this campaign event.
	 *
	 * @return the publics IDs of this campaign event
	 */
	@Override
	public String getPublicsIds() {
		return _campaignEvent.getPublicsIds();
	}

	/**
	 * Returns the registration of this campaign event.
	 *
	 * @return the registration of this campaign event
	 */
	@Override
	public boolean getRegistration() {
		return _campaignEvent.getRegistration();
	}

	/**
	 * Returns the service of this campaign event.
	 *
	 * @return the service of this campaign event
	 */
	@Override
	public String getService() {
		return _campaignEvent.getService();
	}

	/**
	 * Returns the service ID of this campaign event.
	 *
	 * @return the service ID of this campaign event
	 */
	@Override
	public Long getServiceId() {
		return _campaignEvent.getServiceId();
	}

	/**
	 * Retourne le nom du service
	 */
	@Override
	public String getServiceName(java.util.Locale locale) {
		return _campaignEvent.getServiceName(locale);
	}

	/**
	 * Returns the status of this campaign event.
	 *
	 * @return the status of this campaign event
	 */
	@Override
	public int getStatus() {
		return _campaignEvent.getStatus();
	}

	/**
	 * Retourne la liste de CampaignEventStatus, correspondant à l'historique
	 * des statuts de l'événement classé par ordre chronologique
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.agenda.model.CampaignEventStatus>
			getStatusHistory() {

		return _campaignEvent.getStatusHistory();
	}

	/**
	 * Returns the subtitle of this campaign event.
	 *
	 * @return the subtitle of this campaign event
	 */
	@Override
	public String getSubtitle() {
		return _campaignEvent.getSubtitle();
	}

	/**
	 * Returns the localized subtitle of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subtitle of this campaign event
	 */
	@Override
	public String getSubtitle(java.util.Locale locale) {
		return _campaignEvent.getSubtitle(locale);
	}

	/**
	 * Returns the localized subtitle of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getSubtitle(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getSubtitle(locale, useDefault);
	}

	/**
	 * Returns the localized subtitle of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subtitle of this campaign event
	 */
	@Override
	public String getSubtitle(String languageId) {
		return _campaignEvent.getSubtitle(languageId);
	}

	/**
	 * Returns the localized subtitle of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subtitle of this campaign event
	 */
	@Override
	public String getSubtitle(String languageId, boolean useDefault) {
		return _campaignEvent.getSubtitle(languageId, useDefault);
	}

	@Override
	public String getSubtitleCurrentLanguageId() {
		return _campaignEvent.getSubtitleCurrentLanguageId();
	}

	@Override
	public String getSubtitleCurrentValue() {
		return _campaignEvent.getSubtitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized subtitles of this campaign event.
	 *
	 * @return the locales and localized subtitles of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getSubtitleMap() {
		return _campaignEvent.getSubtitleMap();
	}

	/**
	 * Retourne le label des thèles de l'événement
	 */
	@Override
	public String getThemeLabel(java.util.Locale locale) {
		return _campaignEvent.getThemeLabel(locale);
	}

	/**
	 * Retourne les thèmes
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getThemes() {

		return _campaignEvent.getThemes();
	}

	/**
	 * Returns the themes IDs of this campaign event.
	 *
	 * @return the themes IDs of this campaign event
	 */
	@Override
	public String getThemesIds() {
		return _campaignEvent.getThemesIds();
	}

	/**
	 * Returns the title of this campaign event.
	 *
	 * @return the title of this campaign event
	 */
	@Override
	public String getTitle() {
		return _campaignEvent.getTitle();
	}

	/**
	 * Returns the localized title of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this campaign event
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return _campaignEvent.getTitle(locale);
	}

	/**
	 * Returns the localized title of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this campaign event
	 */
	@Override
	public String getTitle(String languageId) {
		return _campaignEvent.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this campaign event
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return _campaignEvent.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _campaignEvent.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _campaignEvent.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this campaign event.
	 *
	 * @return the locales and localized titles of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _campaignEvent.getTitleMap();
	}

	/**
	 * Retourne le label des types de l'événement
	 */
	@Override
	public String getTypeLabel(java.util.Locale locale) {
		return _campaignEvent.getTypeLabel(locale);
	}

	/**
	 * Retourne les types
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTypes() {

		return _campaignEvent.getTypes();
	}

	/**
	 * Returns the types IDs of this campaign event.
	 *
	 * @return the types IDs of this campaign event
	 */
	@Override
	public String getTypesIds() {
		return _campaignEvent.getTypesIds();
	}

	/**
	 * Returns the user ID of this campaign event.
	 *
	 * @return the user ID of this campaign event
	 */
	@Override
	public long getUserId() {
		return _campaignEvent.getUserId();
	}

	/**
	 * Returns the user name of this campaign event.
	 *
	 * @return the user name of this campaign event
	 */
	@Override
	public String getUserName() {
		return _campaignEvent.getUserName();
	}

	/**
	 * Returns the user uuid of this campaign event.
	 *
	 * @return the user uuid of this campaign event
	 */
	@Override
	public String getUserUuid() {
		return _campaignEvent.getUserUuid();
	}

	/**
	 * Returns the uuid of this campaign event.
	 *
	 * @return the uuid of this campaign event
	 */
	@Override
	public String getUuid() {
		return _campaignEvent.getUuid();
	}

	/**
	 * Returns the web image ID of this campaign event.
	 *
	 * @return the web image ID of this campaign event
	 */
	@Override
	public Long getWebImageId() {
		return _campaignEvent.getWebImageId();
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getWebImageURL() {
		return _campaignEvent.getWebImageURL();
	}

	/**
	 * Returns the website name of this campaign event.
	 *
	 * @return the website name of this campaign event
	 */
	@Override
	public String getWebsiteName() {
		return _campaignEvent.getWebsiteName();
	}

	/**
	 * Returns the localized website name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized website name of this campaign event
	 */
	@Override
	public String getWebsiteName(java.util.Locale locale) {
		return _campaignEvent.getWebsiteName(locale);
	}

	/**
	 * Returns the localized website name of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized website name of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getWebsiteName(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getWebsiteName(locale, useDefault);
	}

	/**
	 * Returns the localized website name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized website name of this campaign event
	 */
	@Override
	public String getWebsiteName(String languageId) {
		return _campaignEvent.getWebsiteName(languageId);
	}

	/**
	 * Returns the localized website name of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized website name of this campaign event
	 */
	@Override
	public String getWebsiteName(String languageId, boolean useDefault) {
		return _campaignEvent.getWebsiteName(languageId, useDefault);
	}

	@Override
	public String getWebsiteNameCurrentLanguageId() {
		return _campaignEvent.getWebsiteNameCurrentLanguageId();
	}

	@Override
	public String getWebsiteNameCurrentValue() {
		return _campaignEvent.getWebsiteNameCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized website names of this campaign event.
	 *
	 * @return the locales and localized website names of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getWebsiteNameMap() {
		return _campaignEvent.getWebsiteNameMap();
	}

	/**
	 * Returns the website url of this campaign event.
	 *
	 * @return the website url of this campaign event
	 */
	@Override
	public String getWebsiteURL() {
		return _campaignEvent.getWebsiteURL();
	}

	/**
	 * Returns the localized website url of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized website url of this campaign event
	 */
	@Override
	public String getWebsiteURL(java.util.Locale locale) {
		return _campaignEvent.getWebsiteURL(locale);
	}

	/**
	 * Returns the localized website url of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized website url of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getWebsiteURL(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getWebsiteURL(locale, useDefault);
	}

	/**
	 * Returns the localized website url of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized website url of this campaign event
	 */
	@Override
	public String getWebsiteURL(String languageId) {
		return _campaignEvent.getWebsiteURL(languageId);
	}

	/**
	 * Returns the localized website url of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized website url of this campaign event
	 */
	@Override
	public String getWebsiteURL(String languageId, boolean useDefault) {
		return _campaignEvent.getWebsiteURL(languageId, useDefault);
	}

	@Override
	public String getWebsiteURLCurrentLanguageId() {
		return _campaignEvent.getWebsiteURLCurrentLanguageId();
	}

	@Override
	public String getWebsiteURLCurrentValue() {
		return _campaignEvent.getWebsiteURLCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized website urls of this campaign event.
	 *
	 * @return the locales and localized website urls of this campaign event
	 */
	@Override
	public Map<java.util.Locale, String> getWebsiteURLMap() {
		return _campaignEvent.getWebsiteURLMap();
	}

	@Override
	public int hashCode() {
		return _campaignEvent.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _campaignEvent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _campaignEvent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _campaignEvent.isNew();
	}

	/**
	 * Returns <code>true</code> if this campaign event is registration.
	 *
	 * @return <code>true</code> if this campaign event is registration; <code>false</code> otherwise
	 */
	@Override
	public boolean isRegistration() {
		return _campaignEvent.isRegistration();
	}

	/**
	 * Retourne true si l'utilisateur est manager de la campagne à laquelle
	 * appartient l'événement
	 */
	@Override
	public boolean isUserManagerOfTheEvent(long userId) {
		return _campaignEvent.isUserManagerOfTheEvent(userId);
	}

	@Override
	public void persist() {
		_campaignEvent.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_campaignEvent.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_campaignEvent.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void sendDeleteMail()
		throws com.liferay.portal.kernel.exception.PortalException {

		_campaignEvent.sendDeleteMail();
	}

	@Override
	public void sendDeletionDeniedMail()
		throws com.liferay.portal.kernel.exception.PortalException {

		_campaignEvent.sendDeletionDeniedMail();
	}

	@Override
	public void sendStatusMail()
		throws com.liferay.portal.kernel.exception.PortalException {

		_campaignEvent.sendStatusMail();
	}

	/**
	 * Sets the booking description of this campaign event.
	 *
	 * @param bookingDescription the booking description of this campaign event
	 */
	@Override
	public void setBookingDescription(String bookingDescription) {
		_campaignEvent.setBookingDescription(bookingDescription);
	}

	/**
	 * Sets the localized booking description of this campaign event in the language.
	 *
	 * @param bookingDescription the localized booking description of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setBookingDescription(
		String bookingDescription, java.util.Locale locale) {

		_campaignEvent.setBookingDescription(bookingDescription, locale);
	}

	/**
	 * Sets the localized booking description of this campaign event in the language, and sets the default locale.
	 *
	 * @param bookingDescription the localized booking description of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setBookingDescription(
		String bookingDescription, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setBookingDescription(
			bookingDescription, locale, defaultLocale);
	}

	@Override
	public void setBookingDescriptionCurrentLanguageId(String languageId) {
		_campaignEvent.setBookingDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized booking descriptions of this campaign event from the map of locales and localized booking descriptions.
	 *
	 * @param bookingDescriptionMap the locales and localized booking descriptions of this campaign event
	 */
	@Override
	public void setBookingDescriptionMap(
		Map<java.util.Locale, String> bookingDescriptionMap) {

		_campaignEvent.setBookingDescriptionMap(bookingDescriptionMap);
	}

	/**
	 * Sets the localized booking descriptions of this campaign event from the map of locales and localized booking descriptions, and sets the default locale.
	 *
	 * @param bookingDescriptionMap the locales and localized booking descriptions of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setBookingDescriptionMap(
		Map<java.util.Locale, String> bookingDescriptionMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setBookingDescriptionMap(
			bookingDescriptionMap, defaultLocale);
	}

	/**
	 * Sets the booking url of this campaign event.
	 *
	 * @param bookingURL the booking url of this campaign event
	 */
	@Override
	public void setBookingURL(String bookingURL) {
		_campaignEvent.setBookingURL(bookingURL);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_campaignEvent.setCachedModel(cachedModel);
	}

	/**
	 * Sets the campaign event ID of this campaign event.
	 *
	 * @param campaignEventId the campaign event ID of this campaign event
	 */
	@Override
	public void setCampaignEventId(long campaignEventId) {
		_campaignEvent.setCampaignEventId(campaignEventId);
	}

	/**
	 * Sets the campaign ID of this campaign event.
	 *
	 * @param campaignId the campaign ID of this campaign event
	 */
	@Override
	public void setCampaignId(Long campaignId) {
		_campaignEvent.setCampaignId(campaignId);
	}

	/**
	 * Sets the company ID of this campaign event.
	 *
	 * @param companyId the company ID of this campaign event
	 */
	@Override
	public void setCompanyId(long companyId) {
		_campaignEvent.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this campaign event.
	 *
	 * @param createDate the create date of this campaign event
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_campaignEvent.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this campaign event.
	 *
	 * @param description the description of this campaign event
	 */
	@Override
	public void setDescription(String description) {
		_campaignEvent.setDescription(description);
	}

	/**
	 * Sets the localized description of this campaign event in the language.
	 *
	 * @param description the localized description of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_campaignEvent.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this campaign event in the language, and sets the default locale.
	 *
	 * @param description the localized description of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_campaignEvent.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this campaign event from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this campaign event
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_campaignEvent.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this campaign event from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the email of this campaign event.
	 *
	 * @param email the email of this campaign event
	 */
	@Override
	public void setEmail(String email) {
		_campaignEvent.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_campaignEvent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_campaignEvent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_campaignEvent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the first name of this campaign event.
	 *
	 * @param firstName the first name of this campaign event
	 */
	@Override
	public void setFirstName(String firstName) {
		_campaignEvent.setFirstName(firstName);
	}

	/**
	 * Sets the free of this campaign event.
	 *
	 * @param free the free of this campaign event
	 */
	@Override
	public void setFree(Integer free) {
		_campaignEvent.setFree(free);
	}

	/**
	 * Sets the group ID of this campaign event.
	 *
	 * @param groupId the group ID of this campaign event
	 */
	@Override
	public void setGroupId(long groupId) {
		_campaignEvent.setGroupId(groupId);
	}

	/**
	 * Sets the image ID of this campaign event.
	 *
	 * @param imageId the image ID of this campaign event
	 */
	@Override
	public void setImageId(Long imageId) {
		_campaignEvent.setImageId(imageId);
	}

	/**
	 * Sets the image owner of this campaign event.
	 *
	 * @param imageOwner the image owner of this campaign event
	 */
	@Override
	public void setImageOwner(String imageOwner) {
		_campaignEvent.setImageOwner(imageOwner);
	}

	/**
	 * Sets the last name of this campaign event.
	 *
	 * @param lastName the last name of this campaign event
	 */
	@Override
	public void setLastName(String lastName) {
		_campaignEvent.setLastName(lastName);
	}

	/**
	 * Sets the last publish date of this campaign event.
	 *
	 * @param lastPublishDate the last publish date of this campaign event
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_campaignEvent.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the manifestations IDs of this campaign event.
	 *
	 * @param manifestationsIds the manifestations IDs of this campaign event
	 */
	@Override
	public void setManifestationsIds(String manifestationsIds) {
		_campaignEvent.setManifestationsIds(manifestationsIds);
	}

	/**
	 * Sets the max gauge of this campaign event.
	 *
	 * @param maxGauge the max gauge of this campaign event
	 */
	@Override
	public void setMaxGauge(long maxGauge) {
		_campaignEvent.setMaxGauge(maxGauge);
	}

	/**
	 * Sets the modified date of this campaign event.
	 *
	 * @param modifiedDate the modified date of this campaign event
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_campaignEvent.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_campaignEvent.setNew(n);
	}

	/**
	 * Sets the on site first name of this campaign event.
	 *
	 * @param onSiteFirstName the on site first name of this campaign event
	 */
	@Override
	public void setOnSiteFirstName(String onSiteFirstName) {
		_campaignEvent.setOnSiteFirstName(onSiteFirstName);
	}

	/**
	 * Sets the on site last name of this campaign event.
	 *
	 * @param onSiteLastName the on site last name of this campaign event
	 */
	@Override
	public void setOnSiteLastName(String onSiteLastName) {
		_campaignEvent.setOnSiteLastName(onSiteLastName);
	}

	/**
	 * Sets the on site phone of this campaign event.
	 *
	 * @param onSitePhone the on site phone of this campaign event
	 */
	@Override
	public void setOnSitePhone(String onSitePhone) {
		_campaignEvent.setOnSitePhone(onSitePhone);
	}

	/**
	 * Sets the phone of this campaign event.
	 *
	 * @param phone the phone of this campaign event
	 */
	@Override
	public void setPhone(String phone) {
		_campaignEvent.setPhone(phone);
	}

	/**
	 * Sets the place city ID of this campaign event.
	 *
	 * @param placeCityId the place city ID of this campaign event
	 */
	@Override
	public void setPlaceCityId(Long placeCityId) {
		_campaignEvent.setPlaceCityId(placeCityId);
	}

	/**
	 * Sets the place country of this campaign event.
	 *
	 * @param placeCountry the place country of this campaign event
	 */
	@Override
	public void setPlaceCountry(String placeCountry) {
		_campaignEvent.setPlaceCountry(placeCountry);
	}

	/**
	 * Sets the place name of this campaign event.
	 *
	 * @param placeName the place name of this campaign event
	 */
	@Override
	public void setPlaceName(String placeName) {
		_campaignEvent.setPlaceName(placeName);
	}

	/**
	 * Sets the localized place name of this campaign event in the language.
	 *
	 * @param placeName the localized place name of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setPlaceName(String placeName, java.util.Locale locale) {
		_campaignEvent.setPlaceName(placeName, locale);
	}

	/**
	 * Sets the localized place name of this campaign event in the language, and sets the default locale.
	 *
	 * @param placeName the localized place name of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPlaceName(
		String placeName, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setPlaceName(placeName, locale, defaultLocale);
	}

	@Override
	public void setPlaceNameCurrentLanguageId(String languageId) {
		_campaignEvent.setPlaceNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized place names of this campaign event from the map of locales and localized place names.
	 *
	 * @param placeNameMap the locales and localized place names of this campaign event
	 */
	@Override
	public void setPlaceNameMap(Map<java.util.Locale, String> placeNameMap) {
		_campaignEvent.setPlaceNameMap(placeNameMap);
	}

	/**
	 * Sets the localized place names of this campaign event from the map of locales and localized place names, and sets the default locale.
	 *
	 * @param placeNameMap the locales and localized place names of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, String> placeNameMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setPlaceNameMap(placeNameMap, defaultLocale);
	}

	/**
	 * Sets the place sig ID of this campaign event.
	 *
	 * @param placeSIGId the place sig ID of this campaign event
	 */
	@Override
	public void setPlaceSIGId(String placeSIGId) {
		_campaignEvent.setPlaceSIGId(placeSIGId);
	}

	/**
	 * Sets the place street name of this campaign event.
	 *
	 * @param placeStreetName the place street name of this campaign event
	 */
	@Override
	public void setPlaceStreetName(String placeStreetName) {
		_campaignEvent.setPlaceStreetName(placeStreetName);
	}

	/**
	 * Sets the place street number of this campaign event.
	 *
	 * @param placeStreetNumber the place street number of this campaign event
	 */
	@Override
	public void setPlaceStreetNumber(String placeStreetNumber) {
		_campaignEvent.setPlaceStreetNumber(placeStreetNumber);
	}

	/**
	 * Sets the place zip code of this campaign event.
	 *
	 * @param placeZipCode the place zip code of this campaign event
	 */
	@Override
	public void setPlaceZipCode(String placeZipCode) {
		_campaignEvent.setPlaceZipCode(placeZipCode);
	}

	/**
	 * Sets the price of this campaign event.
	 *
	 * @param price the price of this campaign event
	 */
	@Override
	public void setPrice(String price) {
		_campaignEvent.setPrice(price);
	}

	/**
	 * Sets the localized price of this campaign event in the language.
	 *
	 * @param price the localized price of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setPrice(String price, java.util.Locale locale) {
		_campaignEvent.setPrice(price, locale);
	}

	/**
	 * Sets the localized price of this campaign event in the language, and sets the default locale.
	 *
	 * @param price the localized price of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPrice(
		String price, java.util.Locale locale, java.util.Locale defaultLocale) {

		_campaignEvent.setPrice(price, locale, defaultLocale);
	}

	@Override
	public void setPriceCurrentLanguageId(String languageId) {
		_campaignEvent.setPriceCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized prices of this campaign event from the map of locales and localized prices.
	 *
	 * @param priceMap the locales and localized prices of this campaign event
	 */
	@Override
	public void setPriceMap(Map<java.util.Locale, String> priceMap) {
		_campaignEvent.setPriceMap(priceMap);
	}

	/**
	 * Sets the localized prices of this campaign event from the map of locales and localized prices, and sets the default locale.
	 *
	 * @param priceMap the locales and localized prices of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setPriceMap(
		Map<java.util.Locale, String> priceMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setPriceMap(priceMap, defaultLocale);
	}

	/**
	 * Sets the primary key of this campaign event.
	 *
	 * @param primaryKey the primary key of this campaign event
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_campaignEvent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_campaignEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the promoter of this campaign event.
	 *
	 * @param promoter the promoter of this campaign event
	 */
	@Override
	public void setPromoter(String promoter) {
		_campaignEvent.setPromoter(promoter);
	}

	/**
	 * Sets the public email of this campaign event.
	 *
	 * @param publicEmail the public email of this campaign event
	 */
	@Override
	public void setPublicEmail(String publicEmail) {
		_campaignEvent.setPublicEmail(publicEmail);
	}

	/**
	 * Sets the public phone of this campaign event.
	 *
	 * @param publicPhone the public phone of this campaign event
	 */
	@Override
	public void setPublicPhone(String publicPhone) {
		_campaignEvent.setPublicPhone(publicPhone);
	}

	/**
	 * Sets the publics IDs of this campaign event.
	 *
	 * @param publicsIds the publics IDs of this campaign event
	 */
	@Override
	public void setPublicsIds(String publicsIds) {
		_campaignEvent.setPublicsIds(publicsIds);
	}

	/**
	 * Sets whether this campaign event is registration.
	 *
	 * @param registration the registration of this campaign event
	 */
	@Override
	public void setRegistration(boolean registration) {
		_campaignEvent.setRegistration(registration);
	}

	/**
	 * Sets the service of this campaign event.
	 *
	 * @param service the service of this campaign event
	 */
	@Override
	public void setService(String service) {
		_campaignEvent.setService(service);
	}

	/**
	 * Sets the service ID of this campaign event.
	 *
	 * @param serviceId the service ID of this campaign event
	 */
	@Override
	public void setServiceId(Long serviceId) {
		_campaignEvent.setServiceId(serviceId);
	}

	/**
	 * Sets the status of this campaign event.
	 *
	 * @param status the status of this campaign event
	 */
	@Override
	public void setStatus(int status) {
		_campaignEvent.setStatus(status);
	}

	/**
	 * Sets the subtitle of this campaign event.
	 *
	 * @param subtitle the subtitle of this campaign event
	 */
	@Override
	public void setSubtitle(String subtitle) {
		_campaignEvent.setSubtitle(subtitle);
	}

	/**
	 * Sets the localized subtitle of this campaign event in the language.
	 *
	 * @param subtitle the localized subtitle of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setSubtitle(String subtitle, java.util.Locale locale) {
		_campaignEvent.setSubtitle(subtitle, locale);
	}

	/**
	 * Sets the localized subtitle of this campaign event in the language, and sets the default locale.
	 *
	 * @param subtitle the localized subtitle of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitle(
		String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(String languageId) {
		_campaignEvent.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized subtitles of this campaign event from the map of locales and localized subtitles.
	 *
	 * @param subtitleMap the locales and localized subtitles of this campaign event
	 */
	@Override
	public void setSubtitleMap(Map<java.util.Locale, String> subtitleMap) {
		_campaignEvent.setSubtitleMap(subtitleMap);
	}

	/**
	 * Sets the localized subtitles of this campaign event from the map of locales and localized subtitles, and sets the default locale.
	 *
	 * @param subtitleMap the locales and localized subtitles of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, String> subtitleMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	 * Sets the themes IDs of this campaign event.
	 *
	 * @param themesIds the themes IDs of this campaign event
	 */
	@Override
	public void setThemesIds(String themesIds) {
		_campaignEvent.setThemesIds(themesIds);
	}

	/**
	 * Sets the title of this campaign event.
	 *
	 * @param title the title of this campaign event
	 */
	@Override
	public void setTitle(String title) {
		_campaignEvent.setTitle(title);
	}

	/**
	 * Sets the localized title of this campaign event in the language.
	 *
	 * @param title the localized title of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_campaignEvent.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this campaign event in the language, and sets the default locale.
	 *
	 * @param title the localized title of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		_campaignEvent.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_campaignEvent.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this campaign event from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this campaign event
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_campaignEvent.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this campaign event from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the types IDs of this campaign event.
	 *
	 * @param typesIds the types IDs of this campaign event
	 */
	@Override
	public void setTypesIds(String typesIds) {
		_campaignEvent.setTypesIds(typesIds);
	}

	/**
	 * Sets the user ID of this campaign event.
	 *
	 * @param userId the user ID of this campaign event
	 */
	@Override
	public void setUserId(long userId) {
		_campaignEvent.setUserId(userId);
	}

	/**
	 * Sets the user name of this campaign event.
	 *
	 * @param userName the user name of this campaign event
	 */
	@Override
	public void setUserName(String userName) {
		_campaignEvent.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this campaign event.
	 *
	 * @param userUuid the user uuid of this campaign event
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_campaignEvent.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this campaign event.
	 *
	 * @param uuid the uuid of this campaign event
	 */
	@Override
	public void setUuid(String uuid) {
		_campaignEvent.setUuid(uuid);
	}

	/**
	 * Sets the web image ID of this campaign event.
	 *
	 * @param webImageId the web image ID of this campaign event
	 */
	@Override
	public void setWebImageId(Long webImageId) {
		_campaignEvent.setWebImageId(webImageId);
	}

	/**
	 * Sets the website name of this campaign event.
	 *
	 * @param websiteName the website name of this campaign event
	 */
	@Override
	public void setWebsiteName(String websiteName) {
		_campaignEvent.setWebsiteName(websiteName);
	}

	/**
	 * Sets the localized website name of this campaign event in the language.
	 *
	 * @param websiteName the localized website name of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setWebsiteName(String websiteName, java.util.Locale locale) {
		_campaignEvent.setWebsiteName(websiteName, locale);
	}

	/**
	 * Sets the localized website name of this campaign event in the language, and sets the default locale.
	 *
	 * @param websiteName the localized website name of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWebsiteName(
		String websiteName, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setWebsiteName(websiteName, locale, defaultLocale);
	}

	@Override
	public void setWebsiteNameCurrentLanguageId(String languageId) {
		_campaignEvent.setWebsiteNameCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized website names of this campaign event from the map of locales and localized website names.
	 *
	 * @param websiteNameMap the locales and localized website names of this campaign event
	 */
	@Override
	public void setWebsiteNameMap(
		Map<java.util.Locale, String> websiteNameMap) {

		_campaignEvent.setWebsiteNameMap(websiteNameMap);
	}

	/**
	 * Sets the localized website names of this campaign event from the map of locales and localized website names, and sets the default locale.
	 *
	 * @param websiteNameMap the locales and localized website names of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWebsiteNameMap(
		Map<java.util.Locale, String> websiteNameMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setWebsiteNameMap(websiteNameMap, defaultLocale);
	}

	/**
	 * Sets the website url of this campaign event.
	 *
	 * @param websiteURL the website url of this campaign event
	 */
	@Override
	public void setWebsiteURL(String websiteURL) {
		_campaignEvent.setWebsiteURL(websiteURL);
	}

	/**
	 * Sets the localized website url of this campaign event in the language.
	 *
	 * @param websiteURL the localized website url of this campaign event
	 * @param locale the locale of the language
	 */
	@Override
	public void setWebsiteURL(String websiteURL, java.util.Locale locale) {
		_campaignEvent.setWebsiteURL(websiteURL, locale);
	}

	/**
	 * Sets the localized website url of this campaign event in the language, and sets the default locale.
	 *
	 * @param websiteURL the localized website url of this campaign event
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWebsiteURL(
		String websiteURL, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_campaignEvent.setWebsiteURL(websiteURL, locale, defaultLocale);
	}

	@Override
	public void setWebsiteURLCurrentLanguageId(String languageId) {
		_campaignEvent.setWebsiteURLCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized website urls of this campaign event from the map of locales and localized website urls.
	 *
	 * @param websiteURLMap the locales and localized website urls of this campaign event
	 */
	@Override
	public void setWebsiteURLMap(Map<java.util.Locale, String> websiteURLMap) {
		_campaignEvent.setWebsiteURLMap(websiteURLMap);
	}

	/**
	 * Sets the localized website urls of this campaign event from the map of locales and localized website urls, and sets the default locale.
	 *
	 * @param websiteURLMap the locales and localized website urls of this campaign event
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWebsiteURLMap(
		Map<java.util.Locale, String> websiteURLMap,
		java.util.Locale defaultLocale) {

		_campaignEvent.setWebsiteURLMap(websiteURLMap, defaultLocale);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.agenda.model.CampaignEvent> toCacheModel() {

		return _campaignEvent.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent toEscapedModel() {
		return new CampaignEventWrapper(_campaignEvent.toEscapedModel());
	}

	/**
	 * Retourne la version JSON de l'object
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _campaignEvent.toJSON();
	}

	@Override
	public String toString() {
		return _campaignEvent.toString();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent toUnescapedModel() {
		return new CampaignEventWrapper(_campaignEvent.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _campaignEvent.toXmlString();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus updateStatus(
			int newStatus, String comment,
			com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _campaignEvent.updateStatus(newStatus, comment, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CampaignEventWrapper)) {
			return false;
		}

		CampaignEventWrapper campaignEventWrapper = (CampaignEventWrapper)obj;

		if (Objects.equals(
				_campaignEvent, campaignEventWrapper._campaignEvent)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _campaignEvent.getStagedModelType();
	}

	@Override
	public CampaignEvent getWrappedModel() {
		return _campaignEvent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _campaignEvent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _campaignEvent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_campaignEvent.resetOriginalValues();
	}

	private final CampaignEvent _campaignEvent;

}
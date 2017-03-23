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
public class CampaignEventWrapper implements CampaignEvent,
	ModelWrapper<CampaignEvent> {
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
		attributes.put("free", getFree());
		attributes.put("price", getPrice());
		attributes.put("campaignId", getCampaignId());
		attributes.put("themeId", getThemeId());
		attributes.put("typeId", getTypeId());
		attributes.put("publicsIds", getPublicsIds());

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

		Long themeId = (Long)attributes.get("themeId");

		if (themeId != null) {
			setThemeId(themeId);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String publicsIds = (String)attributes.get("publicsIds");

		if (publicsIds != null) {
			setPublicsIds(publicsIds);
		}
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
	* Retourne true si l'utilisateur est manager de la campagne à laquelle
	* appartient l'événement
	*/
	@Override
	public boolean isUserManagerOfTheEvent(long userId) {
		return _campaignEvent.isUserManagerOfTheEvent(userId);
	}

	/**
	* Retourne la catégorie thème
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getTheme() {
		return _campaignEvent.getTheme();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _campaignEvent.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de l'object
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _campaignEvent.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.CampaignEvent> toCacheModel() {
		return _campaignEvent.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.Campaign getCampaign() {
		return _campaignEvent.getCampaign();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent toEscapedModel() {
		return new CampaignEventWrapper(_campaignEvent.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEvent toUnescapedModel() {
		return new CampaignEventWrapper(_campaignEvent.toUnescapedModel());
	}

	/**
	* Renvoie le dernier statut en date de l'événement
	*/
	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus getLastStatus() {
		return _campaignEvent.getLastStatus();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus updateStatus(
		int newStatus, java.lang.String comment,
		com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _campaignEvent.updateStatus(newStatus, comment, user);
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

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.CampaignEvent campaignEvent) {
		return _campaignEvent.compareTo(campaignEvent);
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

	@Override
	public int hashCode() {
		return _campaignEvent.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _campaignEvent.getPrimaryKeyObj();
	}

	/**
	* Returns the free of this campaign event.
	*
	* @return the free of this campaign event
	*/
	@Override
	public java.lang.Integer getFree() {
		return _campaignEvent.getFree();
	}

	/**
	* Returns the campaign ID of this campaign event.
	*
	* @return the campaign ID of this campaign event
	*/
	@Override
	public java.lang.Long getCampaignId() {
		return _campaignEvent.getCampaignId();
	}

	/**
	* Returns the image ID of this campaign event.
	*
	* @return the image ID of this campaign event
	*/
	@Override
	public java.lang.Long getImageId() {
		return _campaignEvent.getImageId();
	}

	/**
	* Returns the place city ID of this campaign event.
	*
	* @return the place city ID of this campaign event
	*/
	@Override
	public java.lang.Long getPlaceCityId() {
		return _campaignEvent.getPlaceCityId();
	}

	/**
	* Returns the service ID of this campaign event.
	*
	* @return the service ID of this campaign event
	*/
	@Override
	public java.lang.Long getServiceId() {
		return _campaignEvent.getServiceId();
	}

	/**
	* Returns the theme ID of this campaign event.
	*
	* @return the theme ID of this campaign event
	*/
	@Override
	public java.lang.Long getThemeId() {
		return _campaignEvent.getThemeId();
	}

	/**
	* Returns the type ID of this campaign event.
	*
	* @return the type ID of this campaign event
	*/
	@Override
	public java.lang.Long getTypeId() {
		return _campaignEvent.getTypeId();
	}

	/**
	* Returns the web image ID of this campaign event.
	*
	* @return the web image ID of this campaign event
	*/
	@Override
	public java.lang.Long getWebImageId() {
		return _campaignEvent.getWebImageId();
	}

	@Override
	public java.lang.Object clone() {
		return new CampaignEventWrapper((CampaignEvent)_campaignEvent.clone());
	}

	/**
	* Retourne le territoire (la ville) de l'événement (si lieu manuel)
	*/
	@Override
	public java.lang.String getCity() {
		return _campaignEvent.getCity();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _campaignEvent.getDefaultLanguageId();
	}

	/**
	* Returns the description of this campaign event.
	*
	* @return the description of this campaign event
	*/
	@Override
	public java.lang.String getDescription() {
		return _campaignEvent.getDescription();
	}

	/**
	* Returns the localized description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this campaign event
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
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
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getDescription(languageId, useDefault);
	}

	/**
	* Returns the localized description of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this campaign event
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
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
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _campaignEvent.getDescription(locale, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _campaignEvent.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _campaignEvent.getDescriptionCurrentValue();
	}

	/**
	* Returns the email of this campaign event.
	*
	* @return the email of this campaign event
	*/
	@Override
	public java.lang.String getEmail() {
		return _campaignEvent.getEmail();
	}

	/**
	* Returns the first name of this campaign event.
	*
	* @return the first name of this campaign event
	*/
	@Override
	public java.lang.String getFirstName() {
		return _campaignEvent.getFirstName();
	}

	/**
	* Returns the image owner of this campaign event.
	*
	* @return the image owner of this campaign event
	*/
	@Override
	public java.lang.String getImageOwner() {
		return _campaignEvent.getImageOwner();
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getImageURL() {
		return _campaignEvent.getImageURL();
	}

	/**
	* Returns the last name of this campaign event.
	*
	* @return the last name of this campaign event
	*/
	@Override
	public java.lang.String getLastName() {
		return _campaignEvent.getLastName();
	}

	/**
	* Returns the manifestations IDs of this campaign event.
	*
	* @return the manifestations IDs of this campaign event
	*/
	@Override
	public java.lang.String getManifestationsIds() {
		return _campaignEvent.getManifestationsIds();
	}

	/**
	* Returns the on site first name of this campaign event.
	*
	* @return the on site first name of this campaign event
	*/
	@Override
	public java.lang.String getOnSiteFirstName() {
		return _campaignEvent.getOnSiteFirstName();
	}

	/**
	* Returns the on site last name of this campaign event.
	*
	* @return the on site last name of this campaign event
	*/
	@Override
	public java.lang.String getOnSiteLastName() {
		return _campaignEvent.getOnSiteLastName();
	}

	/**
	* Returns the on site phone of this campaign event.
	*
	* @return the on site phone of this campaign event
	*/
	@Override
	public java.lang.String getOnSitePhone() {
		return _campaignEvent.getOnSitePhone();
	}

	/**
	* Returns the phone of this campaign event.
	*
	* @return the phone of this campaign event
	*/
	@Override
	public java.lang.String getPhone() {
		return _campaignEvent.getPhone();
	}

	/**
	* Returns the place country of this campaign event.
	*
	* @return the place country of this campaign event
	*/
	@Override
	public java.lang.String getPlaceCountry() {
		return _campaignEvent.getPlaceCountry();
	}

	/**
	* Returns the place name of this campaign event.
	*
	* @return the place name of this campaign event
	*/
	@Override
	public java.lang.String getPlaceName() {
		return _campaignEvent.getPlaceName();
	}

	/**
	* Returns the localized place name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized place name of this campaign event
	*/
	@Override
	public java.lang.String getPlaceName(java.lang.String languageId) {
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
	public java.lang.String getPlaceName(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getPlaceName(languageId, useDefault);
	}

	/**
	* Returns the localized place name of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized place name of this campaign event
	*/
	@Override
	public java.lang.String getPlaceName(java.util.Locale locale) {
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
	public java.lang.String getPlaceName(java.util.Locale locale,
		boolean useDefault) {
		return _campaignEvent.getPlaceName(locale, useDefault);
	}

	@Override
	public java.lang.String getPlaceNameCurrentLanguageId() {
		return _campaignEvent.getPlaceNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getPlaceNameCurrentValue() {
		return _campaignEvent.getPlaceNameCurrentValue();
	}

	/**
	* Returns the place s i g ID of this campaign event.
	*
	* @return the place s i g ID of this campaign event
	*/
	@Override
	public java.lang.String getPlaceSIGId() {
		return _campaignEvent.getPlaceSIGId();
	}

	/**
	* Returns the place street name of this campaign event.
	*
	* @return the place street name of this campaign event
	*/
	@Override
	public java.lang.String getPlaceStreetName() {
		return _campaignEvent.getPlaceStreetName();
	}

	/**
	* Returns the place street number of this campaign event.
	*
	* @return the place street number of this campaign event
	*/
	@Override
	public java.lang.String getPlaceStreetNumber() {
		return _campaignEvent.getPlaceStreetNumber();
	}

	/**
	* Returns the place zip code of this campaign event.
	*
	* @return the place zip code of this campaign event
	*/
	@Override
	public java.lang.String getPlaceZipCode() {
		return _campaignEvent.getPlaceZipCode();
	}

	/**
	* Returns the price of this campaign event.
	*
	* @return the price of this campaign event
	*/
	@Override
	public java.lang.String getPrice() {
		return _campaignEvent.getPrice();
	}

	/**
	* Returns the localized price of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized price of this campaign event
	*/
	@Override
	public java.lang.String getPrice(java.lang.String languageId) {
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
	public java.lang.String getPrice(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getPrice(languageId, useDefault);
	}

	/**
	* Returns the localized price of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized price of this campaign event
	*/
	@Override
	public java.lang.String getPrice(java.util.Locale locale) {
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
	public java.lang.String getPrice(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getPrice(locale, useDefault);
	}

	@Override
	public java.lang.String getPriceCurrentLanguageId() {
		return _campaignEvent.getPriceCurrentLanguageId();
	}

	@Override
	public java.lang.String getPriceCurrentValue() {
		return _campaignEvent.getPriceCurrentValue();
	}

	/**
	* Returns the promoter of this campaign event.
	*
	* @return the promoter of this campaign event
	*/
	@Override
	public java.lang.String getPromoter() {
		return _campaignEvent.getPromoter();
	}

	/**
	* Returns the public email of this campaign event.
	*
	* @return the public email of this campaign event
	*/
	@Override
	public java.lang.String getPublicEmail() {
		return _campaignEvent.getPublicEmail();
	}

	/**
	* Returns the public phone of this campaign event.
	*
	* @return the public phone of this campaign event
	*/
	@Override
	public java.lang.String getPublicPhone() {
		return _campaignEvent.getPublicPhone();
	}

	/**
	* Returns the publics IDs of this campaign event.
	*
	* @return the publics IDs of this campaign event
	*/
	@Override
	public java.lang.String getPublicsIds() {
		return _campaignEvent.getPublicsIds();
	}

	/**
	* Returns the service of this campaign event.
	*
	* @return the service of this campaign event
	*/
	@Override
	public java.lang.String getService() {
		return _campaignEvent.getService();
	}

	/**
	* Retourne le nom du service
	*/
	@Override
	public java.lang.String getServiceName(java.util.Locale locale) {
		return _campaignEvent.getServiceName(locale);
	}

	/**
	* Returns the subtitle of this campaign event.
	*
	* @return the subtitle of this campaign event
	*/
	@Override
	public java.lang.String getSubtitle() {
		return _campaignEvent.getSubtitle();
	}

	/**
	* Returns the localized subtitle of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized subtitle of this campaign event
	*/
	@Override
	public java.lang.String getSubtitle(java.lang.String languageId) {
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
	public java.lang.String getSubtitle(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getSubtitle(languageId, useDefault);
	}

	/**
	* Returns the localized subtitle of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized subtitle of this campaign event
	*/
	@Override
	public java.lang.String getSubtitle(java.util.Locale locale) {
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
	public java.lang.String getSubtitle(java.util.Locale locale,
		boolean useDefault) {
		return _campaignEvent.getSubtitle(locale, useDefault);
	}

	@Override
	public java.lang.String getSubtitleCurrentLanguageId() {
		return _campaignEvent.getSubtitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getSubtitleCurrentValue() {
		return _campaignEvent.getSubtitleCurrentValue();
	}

	/**
	* Returns the title of this campaign event.
	*
	* @return the title of this campaign event
	*/
	@Override
	public java.lang.String getTitle() {
		return _campaignEvent.getTitle();
	}

	/**
	* Returns the localized title of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this campaign event
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
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
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getTitle(languageId, useDefault);
	}

	/**
	* Returns the localized title of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this campaign event
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
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
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _campaignEvent.getTitle(locale, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _campaignEvent.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _campaignEvent.getTitleCurrentValue();
	}

	/**
	* Returns the user name of this campaign event.
	*
	* @return the user name of this campaign event
	*/
	@Override
	public java.lang.String getUserName() {
		return _campaignEvent.getUserName();
	}

	/**
	* Returns the user uuid of this campaign event.
	*
	* @return the user uuid of this campaign event
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _campaignEvent.getUserUuid();
	}

	/**
	* Returns the uuid of this campaign event.
	*
	* @return the uuid of this campaign event
	*/
	@Override
	public java.lang.String getUuid() {
		return _campaignEvent.getUuid();
	}

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	@Override
	public java.lang.String getWebImageURL() {
		return _campaignEvent.getWebImageURL();
	}

	/**
	* Returns the website u r l of this campaign event.
	*
	* @return the website u r l of this campaign event
	*/
	@Override
	public java.lang.String getWebsiteURL() {
		return _campaignEvent.getWebsiteURL();
	}

	/**
	* Returns the localized website u r l of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized website u r l of this campaign event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.lang.String languageId) {
		return _campaignEvent.getWebsiteURL(languageId);
	}

	/**
	* Returns the localized website u r l of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website u r l of this campaign event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.lang.String languageId,
		boolean useDefault) {
		return _campaignEvent.getWebsiteURL(languageId, useDefault);
	}

	/**
	* Returns the localized website u r l of this campaign event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized website u r l of this campaign event
	*/
	@Override
	public java.lang.String getWebsiteURL(java.util.Locale locale) {
		return _campaignEvent.getWebsiteURL(locale);
	}

	/**
	* Returns the localized website u r l of this campaign event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized website u r l of this campaign event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getWebsiteURL(java.util.Locale locale,
		boolean useDefault) {
		return _campaignEvent.getWebsiteURL(locale, useDefault);
	}

	@Override
	public java.lang.String getWebsiteURLCurrentLanguageId() {
		return _campaignEvent.getWebsiteURLCurrentLanguageId();
	}

	@Override
	public java.lang.String getWebsiteURLCurrentValue() {
		return _campaignEvent.getWebsiteURLCurrentValue();
	}

	@Override
	public java.lang.String toString() {
		return _campaignEvent.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _campaignEvent.toXmlString();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _campaignEvent.getAvailableLanguageIds();
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
	* Returns the modified date of this campaign event.
	*
	* @return the modified date of this campaign event
	*/
	@Override
	public Date getModifiedDate() {
		return _campaignEvent.getModifiedDate();
	}

	/**
	* Retourne les périodes
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getPeriods() {
		return _campaignEvent.getPeriods();
	}

	/**
	* Retourne la liste de CampaignEventStatus, correspondant à l'historique
	* des statuts de l'événement classé par ordre chronologique
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CampaignEventStatus> getStatusHistory() {
		return _campaignEvent.getStatusHistory();
	}

	/**
	* Returns a map of the locales and localized descriptions of this campaign event.
	*
	* @return the locales and localized descriptions of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _campaignEvent.getDescriptionMap();
	}

	/**
	* Returns a map of the locales and localized place names of this campaign event.
	*
	* @return the locales and localized place names of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPlaceNameMap() {
		return _campaignEvent.getPlaceNameMap();
	}

	/**
	* Returns a map of the locales and localized prices of this campaign event.
	*
	* @return the locales and localized prices of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getPriceMap() {
		return _campaignEvent.getPriceMap();
	}

	/**
	* Returns a map of the locales and localized subtitles of this campaign event.
	*
	* @return the locales and localized subtitles of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getSubtitleMap() {
		return _campaignEvent.getSubtitleMap();
	}

	/**
	* Returns a map of the locales and localized titles of this campaign event.
	*
	* @return the locales and localized titles of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _campaignEvent.getTitleMap();
	}

	/**
	* Returns a map of the locales and localized website u r ls of this campaign event.
	*
	* @return the locales and localized website u r ls of this campaign event
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getWebsiteURLMap() {
		return _campaignEvent.getWebsiteURLMap();
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
	* Returns the company ID of this campaign event.
	*
	* @return the company ID of this campaign event
	*/
	@Override
	public long getCompanyId() {
		return _campaignEvent.getCompanyId();
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
	* Returns the primary key of this campaign event.
	*
	* @return the primary key of this campaign event
	*/
	@Override
	public long getPrimaryKey() {
		return _campaignEvent.getPrimaryKey();
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
	public void setCampaignId(java.lang.Long campaignId) {
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
	public void setDescription(java.lang.String description) {
		_campaignEvent.setDescription(description);
	}

	/**
	* Sets the localized description of this campaign event in the language.
	*
	* @param description the localized description of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
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
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_campaignEvent.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this campaign event from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this campaign event
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
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
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_campaignEvent.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the email of this campaign event.
	*
	* @param email the email of this campaign event
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_campaignEvent.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_campaignEvent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_campaignEvent.setExpandoBridgeAttributes(baseModel);
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
	public void setFirstName(java.lang.String firstName) {
		_campaignEvent.setFirstName(firstName);
	}

	/**
	* Sets the free of this campaign event.
	*
	* @param free the free of this campaign event
	*/
	@Override
	public void setFree(java.lang.Integer free) {
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
	public void setImageId(java.lang.Long imageId) {
		_campaignEvent.setImageId(imageId);
	}

	/**
	* Sets the image owner of this campaign event.
	*
	* @param imageOwner the image owner of this campaign event
	*/
	@Override
	public void setImageOwner(java.lang.String imageOwner) {
		_campaignEvent.setImageOwner(imageOwner);
	}

	/**
	* Sets the last name of this campaign event.
	*
	* @param lastName the last name of this campaign event
	*/
	@Override
	public void setLastName(java.lang.String lastName) {
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
	public void setManifestationsIds(java.lang.String manifestationsIds) {
		_campaignEvent.setManifestationsIds(manifestationsIds);
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
	public void setOnSiteFirstName(java.lang.String onSiteFirstName) {
		_campaignEvent.setOnSiteFirstName(onSiteFirstName);
	}

	/**
	* Sets the on site last name of this campaign event.
	*
	* @param onSiteLastName the on site last name of this campaign event
	*/
	@Override
	public void setOnSiteLastName(java.lang.String onSiteLastName) {
		_campaignEvent.setOnSiteLastName(onSiteLastName);
	}

	/**
	* Sets the on site phone of this campaign event.
	*
	* @param onSitePhone the on site phone of this campaign event
	*/
	@Override
	public void setOnSitePhone(java.lang.String onSitePhone) {
		_campaignEvent.setOnSitePhone(onSitePhone);
	}

	/**
	* Sets the phone of this campaign event.
	*
	* @param phone the phone of this campaign event
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_campaignEvent.setPhone(phone);
	}

	/**
	* Sets the place city ID of this campaign event.
	*
	* @param placeCityId the place city ID of this campaign event
	*/
	@Override
	public void setPlaceCityId(java.lang.Long placeCityId) {
		_campaignEvent.setPlaceCityId(placeCityId);
	}

	/**
	* Sets the place country of this campaign event.
	*
	* @param placeCountry the place country of this campaign event
	*/
	@Override
	public void setPlaceCountry(java.lang.String placeCountry) {
		_campaignEvent.setPlaceCountry(placeCountry);
	}

	/**
	* Sets the place name of this campaign event.
	*
	* @param placeName the place name of this campaign event
	*/
	@Override
	public void setPlaceName(java.lang.String placeName) {
		_campaignEvent.setPlaceName(placeName);
	}

	/**
	* Sets the localized place name of this campaign event in the language.
	*
	* @param placeName the localized place name of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setPlaceName(java.lang.String placeName, java.util.Locale locale) {
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
	public void setPlaceName(java.lang.String placeName,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_campaignEvent.setPlaceName(placeName, locale, defaultLocale);
	}

	@Override
	public void setPlaceNameCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setPlaceNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized place names of this campaign event from the map of locales and localized place names.
	*
	* @param placeNameMap the locales and localized place names of this campaign event
	*/
	@Override
	public void setPlaceNameMap(
		Map<java.util.Locale, java.lang.String> placeNameMap) {
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
		Map<java.util.Locale, java.lang.String> placeNameMap,
		java.util.Locale defaultLocale) {
		_campaignEvent.setPlaceNameMap(placeNameMap, defaultLocale);
	}

	/**
	* Sets the place s i g ID of this campaign event.
	*
	* @param placeSIGId the place s i g ID of this campaign event
	*/
	@Override
	public void setPlaceSIGId(java.lang.String placeSIGId) {
		_campaignEvent.setPlaceSIGId(placeSIGId);
	}

	/**
	* Sets the place street name of this campaign event.
	*
	* @param placeStreetName the place street name of this campaign event
	*/
	@Override
	public void setPlaceStreetName(java.lang.String placeStreetName) {
		_campaignEvent.setPlaceStreetName(placeStreetName);
	}

	/**
	* Sets the place street number of this campaign event.
	*
	* @param placeStreetNumber the place street number of this campaign event
	*/
	@Override
	public void setPlaceStreetNumber(java.lang.String placeStreetNumber) {
		_campaignEvent.setPlaceStreetNumber(placeStreetNumber);
	}

	/**
	* Sets the place zip code of this campaign event.
	*
	* @param placeZipCode the place zip code of this campaign event
	*/
	@Override
	public void setPlaceZipCode(java.lang.String placeZipCode) {
		_campaignEvent.setPlaceZipCode(placeZipCode);
	}

	/**
	* Sets the price of this campaign event.
	*
	* @param price the price of this campaign event
	*/
	@Override
	public void setPrice(java.lang.String price) {
		_campaignEvent.setPrice(price);
	}

	/**
	* Sets the localized price of this campaign event in the language.
	*
	* @param price the localized price of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setPrice(java.lang.String price, java.util.Locale locale) {
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
	public void setPrice(java.lang.String price, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_campaignEvent.setPrice(price, locale, defaultLocale);
	}

	@Override
	public void setPriceCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setPriceCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized prices of this campaign event from the map of locales and localized prices.
	*
	* @param priceMap the locales and localized prices of this campaign event
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap) {
		_campaignEvent.setPriceMap(priceMap);
	}

	/**
	* Sets the localized prices of this campaign event from the map of locales and localized prices, and sets the default locale.
	*
	* @param priceMap the locales and localized prices of this campaign event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setPriceMap(Map<java.util.Locale, java.lang.String> priceMap,
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
	public void setPromoter(java.lang.String promoter) {
		_campaignEvent.setPromoter(promoter);
	}

	/**
	* Sets the public email of this campaign event.
	*
	* @param publicEmail the public email of this campaign event
	*/
	@Override
	public void setPublicEmail(java.lang.String publicEmail) {
		_campaignEvent.setPublicEmail(publicEmail);
	}

	/**
	* Sets the public phone of this campaign event.
	*
	* @param publicPhone the public phone of this campaign event
	*/
	@Override
	public void setPublicPhone(java.lang.String publicPhone) {
		_campaignEvent.setPublicPhone(publicPhone);
	}

	/**
	* Sets the publics IDs of this campaign event.
	*
	* @param publicsIds the publics IDs of this campaign event
	*/
	@Override
	public void setPublicsIds(java.lang.String publicsIds) {
		_campaignEvent.setPublicsIds(publicsIds);
	}

	/**
	* Sets the service of this campaign event.
	*
	* @param service the service of this campaign event
	*/
	@Override
	public void setService(java.lang.String service) {
		_campaignEvent.setService(service);
	}

	/**
	* Sets the service ID of this campaign event.
	*
	* @param serviceId the service ID of this campaign event
	*/
	@Override
	public void setServiceId(java.lang.Long serviceId) {
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
	public void setSubtitle(java.lang.String subtitle) {
		_campaignEvent.setSubtitle(subtitle);
	}

	/**
	* Sets the localized subtitle of this campaign event in the language.
	*
	* @param subtitle the localized subtitle of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale) {
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
	public void setSubtitle(java.lang.String subtitle, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_campaignEvent.setSubtitle(subtitle, locale, defaultLocale);
	}

	@Override
	public void setSubtitleCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setSubtitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized subtitles of this campaign event from the map of locales and localized subtitles.
	*
	* @param subtitleMap the locales and localized subtitles of this campaign event
	*/
	@Override
	public void setSubtitleMap(
		Map<java.util.Locale, java.lang.String> subtitleMap) {
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
		Map<java.util.Locale, java.lang.String> subtitleMap,
		java.util.Locale defaultLocale) {
		_campaignEvent.setSubtitleMap(subtitleMap, defaultLocale);
	}

	/**
	* Sets the theme ID of this campaign event.
	*
	* @param themeId the theme ID of this campaign event
	*/
	@Override
	public void setThemeId(java.lang.Long themeId) {
		_campaignEvent.setThemeId(themeId);
	}

	/**
	* Sets the title of this campaign event.
	*
	* @param title the title of this campaign event
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_campaignEvent.setTitle(title);
	}

	/**
	* Sets the localized title of this campaign event in the language.
	*
	* @param title the localized title of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
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
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_campaignEvent.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this campaign event from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this campaign event
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap) {
		_campaignEvent.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this campaign event from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this campaign event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_campaignEvent.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the type ID of this campaign event.
	*
	* @param typeId the type ID of this campaign event
	*/
	@Override
	public void setTypeId(java.lang.Long typeId) {
		_campaignEvent.setTypeId(typeId);
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
	public void setUserName(java.lang.String userName) {
		_campaignEvent.setUserName(userName);
	}

	/**
	* Sets the user uuid of this campaign event.
	*
	* @param userUuid the user uuid of this campaign event
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_campaignEvent.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this campaign event.
	*
	* @param uuid the uuid of this campaign event
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_campaignEvent.setUuid(uuid);
	}

	/**
	* Sets the web image ID of this campaign event.
	*
	* @param webImageId the web image ID of this campaign event
	*/
	@Override
	public void setWebImageId(java.lang.Long webImageId) {
		_campaignEvent.setWebImageId(webImageId);
	}

	/**
	* Sets the website u r l of this campaign event.
	*
	* @param websiteURL the website u r l of this campaign event
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL) {
		_campaignEvent.setWebsiteURL(websiteURL);
	}

	/**
	* Sets the localized website u r l of this campaign event in the language.
	*
	* @param websiteURL the localized website u r l of this campaign event
	* @param locale the locale of the language
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL,
		java.util.Locale locale) {
		_campaignEvent.setWebsiteURL(websiteURL, locale);
	}

	/**
	* Sets the localized website u r l of this campaign event in the language, and sets the default locale.
	*
	* @param websiteURL the localized website u r l of this campaign event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteURL(java.lang.String websiteURL,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_campaignEvent.setWebsiteURL(websiteURL, locale, defaultLocale);
	}

	@Override
	public void setWebsiteURLCurrentLanguageId(java.lang.String languageId) {
		_campaignEvent.setWebsiteURLCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized website u r ls of this campaign event from the map of locales and localized website u r ls.
	*
	* @param websiteURLMap the locales and localized website u r ls of this campaign event
	*/
	@Override
	public void setWebsiteURLMap(
		Map<java.util.Locale, java.lang.String> websiteURLMap) {
		_campaignEvent.setWebsiteURLMap(websiteURLMap);
	}

	/**
	* Sets the localized website u r ls of this campaign event from the map of locales and localized website u r ls, and sets the default locale.
	*
	* @param websiteURLMap the locales and localized website u r ls of this campaign event
	* @param defaultLocale the default locale
	*/
	@Override
	public void setWebsiteURLMap(
		Map<java.util.Locale, java.lang.String> websiteURLMap,
		java.util.Locale defaultLocale) {
		_campaignEvent.setWebsiteURLMap(websiteURLMap, defaultLocale);
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

		if (Objects.equals(_campaignEvent, campaignEventWrapper._campaignEvent)) {
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
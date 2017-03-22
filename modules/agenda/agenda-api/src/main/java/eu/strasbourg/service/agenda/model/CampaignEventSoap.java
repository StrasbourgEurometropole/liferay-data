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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class CampaignEventSoap implements Serializable {
	public static CampaignEventSoap toSoapModel(CampaignEvent model) {
		CampaignEventSoap soapModel = new CampaignEventSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCampaignEventId(model.getCampaignEventId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setLastName(model.getLastName());
		soapModel.setPhone(model.getPhone());
		soapModel.setEmail(model.getEmail());
		soapModel.setServiceId(model.getServiceId());
		soapModel.setService(model.getService());
		soapModel.setOnSiteFirstName(model.getOnSiteFirstName());
		soapModel.setOnSiteLastName(model.getOnSiteLastName());
		soapModel.setOnSitePhone(model.getOnSitePhone());
		soapModel.setTitle(model.getTitle());
		soapModel.setSubtitle(model.getSubtitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setImageId(model.getImageId());
		soapModel.setWebImageId(model.getWebImageId());
		soapModel.setImageOwner(model.getImageOwner());
		soapModel.setManifestationsIds(model.getManifestationsIds());
		soapModel.setPlaceSIGId(model.getPlaceSIGId());
		soapModel.setPlaceName(model.getPlaceName());
		soapModel.setPlaceStreetNumber(model.getPlaceStreetNumber());
		soapModel.setPlaceStreetName(model.getPlaceStreetName());
		soapModel.setPlaceZipCode(model.getPlaceZipCode());
		soapModel.setPlaceCityId(model.getPlaceCityId());
		soapModel.setPlaceCountry(model.getPlaceCountry());
		soapModel.setPromoter(model.getPromoter());
		soapModel.setPublicPhone(model.getPublicPhone());
		soapModel.setPublicEmail(model.getPublicEmail());
		soapModel.setWebsiteURL(model.getWebsiteURL());
		soapModel.setFree(model.getFree());
		soapModel.setPrice(model.getPrice());
		soapModel.setCampaignId(model.getCampaignId());
		soapModel.setThemeId(model.getThemeId());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setPublicsIds(model.getPublicsIds());

		return soapModel;
	}

	public static CampaignEventSoap[] toSoapModels(CampaignEvent[] models) {
		CampaignEventSoap[] soapModels = new CampaignEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CampaignEventSoap[][] toSoapModels(CampaignEvent[][] models) {
		CampaignEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CampaignEventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CampaignEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CampaignEventSoap[] toSoapModels(List<CampaignEvent> models) {
		List<CampaignEventSoap> soapModels = new ArrayList<CampaignEventSoap>(models.size());

		for (CampaignEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CampaignEventSoap[soapModels.size()]);
	}

	public CampaignEventSoap() {
	}

	public long getPrimaryKey() {
		return _campaignEventId;
	}

	public void setPrimaryKey(long pk) {
		setCampaignEventId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCampaignEventId() {
		return _campaignEventId;
	}

	public void setCampaignEventId(long campaignEventId) {
		_campaignEventId = campaignEventId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public Long getServiceId() {
		return _serviceId;
	}

	public void setServiceId(Long serviceId) {
		_serviceId = serviceId;
	}

	public String getService() {
		return _service;
	}

	public void setService(String service) {
		_service = service;
	}

	public String getOnSiteFirstName() {
		return _onSiteFirstName;
	}

	public void setOnSiteFirstName(String onSiteFirstName) {
		_onSiteFirstName = onSiteFirstName;
	}

	public String getOnSiteLastName() {
		return _onSiteLastName;
	}

	public void setOnSiteLastName(String onSiteLastName) {
		_onSiteLastName = onSiteLastName;
	}

	public String getOnSitePhone() {
		return _onSitePhone;
	}

	public void setOnSitePhone(String onSitePhone) {
		_onSitePhone = onSitePhone;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getSubtitle() {
		return _subtitle;
	}

	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	public Long getWebImageId() {
		return _webImageId;
	}

	public void setWebImageId(Long webImageId) {
		_webImageId = webImageId;
	}

	public String getImageOwner() {
		return _imageOwner;
	}

	public void setImageOwner(String imageOwner) {
		_imageOwner = imageOwner;
	}

	public String getManifestationsIds() {
		return _manifestationsIds;
	}

	public void setManifestationsIds(String manifestationsIds) {
		_manifestationsIds = manifestationsIds;
	}

	public String getPlaceSIGId() {
		return _placeSIGId;
	}

	public void setPlaceSIGId(String placeSIGId) {
		_placeSIGId = placeSIGId;
	}

	public String getPlaceName() {
		return _placeName;
	}

	public void setPlaceName(String placeName) {
		_placeName = placeName;
	}

	public String getPlaceStreetNumber() {
		return _placeStreetNumber;
	}

	public void setPlaceStreetNumber(String placeStreetNumber) {
		_placeStreetNumber = placeStreetNumber;
	}

	public String getPlaceStreetName() {
		return _placeStreetName;
	}

	public void setPlaceStreetName(String placeStreetName) {
		_placeStreetName = placeStreetName;
	}

	public String getPlaceZipCode() {
		return _placeZipCode;
	}

	public void setPlaceZipCode(String placeZipCode) {
		_placeZipCode = placeZipCode;
	}

	public Long getPlaceCityId() {
		return _placeCityId;
	}

	public void setPlaceCityId(Long placeCityId) {
		_placeCityId = placeCityId;
	}

	public String getPlaceCountry() {
		return _placeCountry;
	}

	public void setPlaceCountry(String placeCountry) {
		_placeCountry = placeCountry;
	}

	public String getPromoter() {
		return _promoter;
	}

	public void setPromoter(String promoter) {
		_promoter = promoter;
	}

	public String getPublicPhone() {
		return _publicPhone;
	}

	public void setPublicPhone(String publicPhone) {
		_publicPhone = publicPhone;
	}

	public String getPublicEmail() {
		return _publicEmail;
	}

	public void setPublicEmail(String publicEmail) {
		_publicEmail = publicEmail;
	}

	public String getWebsiteURL() {
		return _websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		_websiteURL = websiteURL;
	}

	public Integer getFree() {
		return _free;
	}

	public void setFree(Integer free) {
		_free = free;
	}

	public String getPrice() {
		return _price;
	}

	public void setPrice(String price) {
		_price = price;
	}

	public Long getCampaignId() {
		return _campaignId;
	}

	public void setCampaignId(Long campaignId) {
		_campaignId = campaignId;
	}

	public Long getThemeId() {
		return _themeId;
	}

	public void setThemeId(Long themeId) {
		_themeId = themeId;
	}

	public Long getTypeId() {
		return _typeId;
	}

	public void setTypeId(Long typeId) {
		_typeId = typeId;
	}

	public String getPublicsIds() {
		return _publicsIds;
	}

	public void setPublicsIds(String publicsIds) {
		_publicsIds = publicsIds;
	}

	private String _uuid;
	private long _campaignEventId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _lastPublishDate;
	private int _status;
	private String _firstName;
	private String _lastName;
	private String _phone;
	private String _email;
	private Long _serviceId;
	private String _service;
	private String _onSiteFirstName;
	private String _onSiteLastName;
	private String _onSitePhone;
	private String _title;
	private String _subtitle;
	private String _description;
	private Long _imageId;
	private Long _webImageId;
	private String _imageOwner;
	private String _manifestationsIds;
	private String _placeSIGId;
	private String _placeName;
	private String _placeStreetNumber;
	private String _placeStreetName;
	private String _placeZipCode;
	private Long _placeCityId;
	private String _placeCountry;
	private String _promoter;
	private String _publicPhone;
	private String _publicEmail;
	private String _websiteURL;
	private Integer _free;
	private String _price;
	private Long _campaignId;
	private Long _themeId;
	private Long _typeId;
	private String _publicsIds;
}
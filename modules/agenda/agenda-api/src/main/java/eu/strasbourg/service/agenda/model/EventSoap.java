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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.EventServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.EventServiceSoap
 * @generated
 */
@ProviderType
public class EventSoap implements Serializable {
	public static EventSoap toSoapModel(Event model) {
		EventSoap soapModel = new EventSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEventId(model.getEventId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setSubtitle(model.getSubtitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setExternalImageURL(model.getExternalImageURL());
		soapModel.setExternalImageCopyright(model.getExternalImageCopyright());
		soapModel.setPlaceSIGId(model.getPlaceSIGId());
		soapModel.setPlaceName(model.getPlaceName());
		soapModel.setPlaceStreetNumber(model.getPlaceStreetNumber());
		soapModel.setPlaceStreetName(model.getPlaceStreetName());
		soapModel.setPlaceZipCode(model.getPlaceZipCode());
		soapModel.setPlaceCity(model.getPlaceCity());
		soapModel.setPlaceCountry(model.getPlaceCountry());
		soapModel.setAccess(model.getAccess());
		soapModel.setAccessForDisabled(model.getAccessForDisabled());
		soapModel.setAccessForBlind(model.getAccessForBlind());
		soapModel.setAccessForDeaf(model.getAccessForDeaf());
		soapModel.setAccessForWheelchair(model.getAccessForWheelchair());
		soapModel.setAccessForElder(model.getAccessForElder());
		soapModel.setAccessForDeficient(model.getAccessForDeficient());
		soapModel.setPromoter(model.getPromoter());
		soapModel.setPhone(model.getPhone());
		soapModel.setEmail(model.getEmail());
		soapModel.setWebsiteURL(model.getWebsiteURL());
		soapModel.setWebsiteName(model.getWebsiteName());
		soapModel.setFree(model.getFree());
		soapModel.setPrice(model.getPrice());
		soapModel.setSource(model.getSource());
		soapModel.setDisplayDate(model.getDisplayDate());
		soapModel.setScheduleComments(model.getScheduleComments());
		soapModel.setFirstStartDate(model.getFirstStartDate());
		soapModel.setLastEndDate(model.getLastEndDate());
		soapModel.setImageId(model.getImageId());

		return soapModel;
	}

	public static EventSoap[] toSoapModels(Event[] models) {
		EventSoap[] soapModels = new EventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventSoap[][] toSoapModels(Event[][] models) {
		EventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventSoap[] toSoapModels(List<Event> models) {
		List<EventSoap> soapModels = new ArrayList<EventSoap>(models.size());

		for (Event model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventSoap[soapModels.size()]);
	}

	public EventSoap() {
	}

	public long getPrimaryKey() {
		return _eventId;
	}

	public void setPrimaryKey(long pk) {
		setEventId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
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

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
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

	public String getExternalImageURL() {
		return _externalImageURL;
	}

	public void setExternalImageURL(String externalImageURL) {
		_externalImageURL = externalImageURL;
	}

	public String getExternalImageCopyright() {
		return _externalImageCopyright;
	}

	public void setExternalImageCopyright(String externalImageCopyright) {
		_externalImageCopyright = externalImageCopyright;
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

	public String getPlaceCity() {
		return _placeCity;
	}

	public void setPlaceCity(String placeCity) {
		_placeCity = placeCity;
	}

	public String getPlaceCountry() {
		return _placeCountry;
	}

	public void setPlaceCountry(String placeCountry) {
		_placeCountry = placeCountry;
	}

	public String getAccess() {
		return _access;
	}

	public void setAccess(String access) {
		_access = access;
	}

	public String getAccessForDisabled() {
		return _accessForDisabled;
	}

	public void setAccessForDisabled(String accessForDisabled) {
		_accessForDisabled = accessForDisabled;
	}

	public Boolean getAccessForBlind() {
		return _accessForBlind;
	}

	public void setAccessForBlind(Boolean accessForBlind) {
		_accessForBlind = accessForBlind;
	}

	public Boolean getAccessForDeaf() {
		return _accessForDeaf;
	}

	public void setAccessForDeaf(Boolean accessForDeaf) {
		_accessForDeaf = accessForDeaf;
	}

	public Boolean getAccessForWheelchair() {
		return _accessForWheelchair;
	}

	public void setAccessForWheelchair(Boolean accessForWheelchair) {
		_accessForWheelchair = accessForWheelchair;
	}

	public Boolean getAccessForElder() {
		return _accessForElder;
	}

	public void setAccessForElder(Boolean accessForElder) {
		_accessForElder = accessForElder;
	}

	public Boolean getAccessForDeficient() {
		return _accessForDeficient;
	}

	public void setAccessForDeficient(Boolean accessForDeficient) {
		_accessForDeficient = accessForDeficient;
	}

	public String getPromoter() {
		return _promoter;
	}

	public void setPromoter(String promoter) {
		_promoter = promoter;
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

	public String getWebsiteURL() {
		return _websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		_websiteURL = websiteURL;
	}

	public String getWebsiteName() {
		return _websiteName;
	}

	public void setWebsiteName(String websiteName) {
		_websiteName = websiteName;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getDisplayDate() {
		return _displayDate;
	}

	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	public String getScheduleComments() {
		return _scheduleComments;
	}

	public void setScheduleComments(String scheduleComments) {
		_scheduleComments = scheduleComments;
	}

	public Date getFirstStartDate() {
		return _firstStartDate;
	}

	public void setFirstStartDate(Date firstStartDate) {
		_firstStartDate = firstStartDate;
	}

	public Date getLastEndDate() {
		return _lastEndDate;
	}

	public void setLastEndDate(Date lastEndDate) {
		_lastEndDate = lastEndDate;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	private String _uuid;
	private long _eventId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _subtitle;
	private String _description;
	private String _externalImageURL;
	private String _externalImageCopyright;
	private String _placeSIGId;
	private String _placeName;
	private String _placeStreetNumber;
	private String _placeStreetName;
	private String _placeZipCode;
	private String _placeCity;
	private String _placeCountry;
	private String _access;
	private String _accessForDisabled;
	private Boolean _accessForBlind;
	private Boolean _accessForDeaf;
	private Boolean _accessForWheelchair;
	private Boolean _accessForElder;
	private Boolean _accessForDeficient;
	private String _promoter;
	private String _phone;
	private String _email;
	private String _websiteURL;
	private String _websiteName;
	private Integer _free;
	private String _price;
	private String _source;
	private Date _displayDate;
	private String _scheduleComments;
	private Date _firstStartDate;
	private Date _lastEndDate;
	private Long _imageId;
}
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.place.service.http.PlaceServiceSoap}.
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.http.PlaceServiceSoap
 * @generated
 */
@ProviderType
public class PlaceSoap implements Serializable {
	public static PlaceSoap toSoapModel(Place model) {
		PlaceSoap soapModel = new PlaceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPlaceId(model.getPlaceId());
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
		soapModel.setSIGid(model.getSIGid());
		soapModel.setName(model.getName());
		soapModel.setAddressComplement(model.getAddressComplement());
		soapModel.setAddressStreet(model.getAddressStreet());
		soapModel.setAddressDistribution(model.getAddressDistribution());
		soapModel.setAddressZipCode(model.getAddressZipCode());
		soapModel.setAddressCountry(model.getAddressCountry());
		soapModel.setMercatorX(model.getMercatorX());
		soapModel.setMercatorY(model.getMercatorY());
		soapModel.setRGF93X(model.getRGF93X());
		soapModel.setRGF93Y(model.getRGF93Y());
		soapModel.setAlias(model.getAlias());
		soapModel.setPresentation(model.getPresentation());
		soapModel.setServiceAndActivities(model.getServiceAndActivities());
		soapModel.setCharacteristics(model.getCharacteristics());
		soapModel.setSubjectToPublicHoliday(model.getSubjectToPublicHoliday());
		soapModel.setExceptionalSchedule(model.getExceptionalSchedule());
		soapModel.setDisplayEvents(model.getDisplayEvents());
		soapModel.setAdditionalInformation(model.getAdditionalInformation());
		soapModel.setPhone(model.getPhone());
		soapModel.setMail(model.getMail());
		soapModel.setSiteURL(model.getSiteURL());
		soapModel.setSiteLabel(model.getSiteLabel());
		soapModel.setFacebookURL(model.getFacebookURL());
		soapModel.setFacebookLabel(model.getFacebookLabel());
		soapModel.setAccesMap(model.getAccesMap());
		soapModel.setAccess(model.getAccess());
		soapModel.setAccessForDisabled(model.getAccessForDisabled());
		soapModel.setAccessForBlind(model.getAccessForBlind());
		soapModel.setAccessForDeaf(model.getAccessForDeaf());
		soapModel.setAccessForWheelchair(model.getAccessForWheelchair());
		soapModel.setAccessForElder(model.getAccessForElder());
		soapModel.setAccessForDeficient(model.getAccessForDeficient());
		soapModel.setRTExternalId(model.getRTExternalId());
		soapModel.setOccupation(model.getOccupation());
		soapModel.setOccupationLastUpdate(model.getOccupationLastUpdate());
		soapModel.setImageId(model.getImageId());
		soapModel.setImageIds(model.getImageIds());
		soapModel.setVideosIds(model.getVideosIds());
		soapModel.setPriceId(model.getPriceId());
		soapModel.setDocumentsIds(model.getDocumentsIds());

		return soapModel;
	}

	public static PlaceSoap[] toSoapModels(Place[] models) {
		PlaceSoap[] soapModels = new PlaceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PlaceSoap[][] toSoapModels(Place[][] models) {
		PlaceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PlaceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PlaceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PlaceSoap[] toSoapModels(List<Place> models) {
		List<PlaceSoap> soapModels = new ArrayList<PlaceSoap>(models.size());

		for (Place model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PlaceSoap[soapModels.size()]);
	}

	public PlaceSoap() {
	}

	public long getPrimaryKey() {
		return _placeId;
	}

	public void setPrimaryKey(long pk) {
		setPlaceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPlaceId() {
		return _placeId;
	}

	public void setPlaceId(long placeId) {
		_placeId = placeId;
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

	public String getSIGid() {
		return _SIGid;
	}

	public void setSIGid(String SIGid) {
		_SIGid = SIGid;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getAddressComplement() {
		return _addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		_addressComplement = addressComplement;
	}

	public String getAddressStreet() {
		return _addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		_addressStreet = addressStreet;
	}

	public String getAddressDistribution() {
		return _addressDistribution;
	}

	public void setAddressDistribution(String addressDistribution) {
		_addressDistribution = addressDistribution;
	}

	public String getAddressZipCode() {
		return _addressZipCode;
	}

	public void setAddressZipCode(String addressZipCode) {
		_addressZipCode = addressZipCode;
	}

	public String getAddressCountry() {
		return _addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		_addressCountry = addressCountry;
	}

	public String getMercatorX() {
		return _mercatorX;
	}

	public void setMercatorX(String mercatorX) {
		_mercatorX = mercatorX;
	}

	public String getMercatorY() {
		return _mercatorY;
	}

	public void setMercatorY(String mercatorY) {
		_mercatorY = mercatorY;
	}

	public String getRGF93X() {
		return _RGF93X;
	}

	public void setRGF93X(String RGF93X) {
		_RGF93X = RGF93X;
	}

	public String getRGF93Y() {
		return _RGF93Y;
	}

	public void setRGF93Y(String RGF93Y) {
		_RGF93Y = RGF93Y;
	}

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
	}

	public String getPresentation() {
		return _presentation;
	}

	public void setPresentation(String presentation) {
		_presentation = presentation;
	}

	public String getServiceAndActivities() {
		return _serviceAndActivities;
	}

	public void setServiceAndActivities(String serviceAndActivities) {
		_serviceAndActivities = serviceAndActivities;
	}

	public String getCharacteristics() {
		return _characteristics;
	}

	public void setCharacteristics(String characteristics) {
		_characteristics = characteristics;
	}

	public boolean getSubjectToPublicHoliday() {
		return _subjectToPublicHoliday;
	}

	public boolean isSubjectToPublicHoliday() {
		return _subjectToPublicHoliday;
	}

	public void setSubjectToPublicHoliday(boolean subjectToPublicHoliday) {
		_subjectToPublicHoliday = subjectToPublicHoliday;
	}

	public String getExceptionalSchedule() {
		return _exceptionalSchedule;
	}

	public void setExceptionalSchedule(String exceptionalSchedule) {
		_exceptionalSchedule = exceptionalSchedule;
	}

	public boolean getDisplayEvents() {
		return _displayEvents;
	}

	public boolean isDisplayEvents() {
		return _displayEvents;
	}

	public void setDisplayEvents(boolean displayEvents) {
		_displayEvents = displayEvents;
	}

	public String getAdditionalInformation() {
		return _additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		_additionalInformation = additionalInformation;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public String getMail() {
		return _mail;
	}

	public void setMail(String mail) {
		_mail = mail;
	}

	public String getSiteURL() {
		return _siteURL;
	}

	public void setSiteURL(String siteURL) {
		_siteURL = siteURL;
	}

	public String getSiteLabel() {
		return _siteLabel;
	}

	public void setSiteLabel(String siteLabel) {
		_siteLabel = siteLabel;
	}

	public String getFacebookURL() {
		return _facebookURL;
	}

	public void setFacebookURL(String facebookURL) {
		_facebookURL = facebookURL;
	}

	public String getFacebookLabel() {
		return _facebookLabel;
	}

	public void setFacebookLabel(String facebookLabel) {
		_facebookLabel = facebookLabel;
	}

	public String getAccesMap() {
		return _accesMap;
	}

	public void setAccesMap(String accesMap) {
		_accesMap = accesMap;
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

	public String getRTExternalId() {
		return _RTExternalId;
	}

	public void setRTExternalId(String RTExternalId) {
		_RTExternalId = RTExternalId;
	}

	public String getOccupation() {
		return _occupation;
	}

	public void setOccupation(String occupation) {
		_occupation = occupation;
	}

	public Date getOccupationLastUpdate() {
		return _occupationLastUpdate;
	}

	public void setOccupationLastUpdate(Date occupationLastUpdate) {
		_occupationLastUpdate = occupationLastUpdate;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public String getImageIds() {
		return _imageIds;
	}

	public void setImageIds(String imageIds) {
		_imageIds = imageIds;
	}

	public String getVideosIds() {
		return _videosIds;
	}

	public void setVideosIds(String videosIds) {
		_videosIds = videosIds;
	}

	public long getPriceId() {
		return _priceId;
	}

	public void setPriceId(long priceId) {
		_priceId = priceId;
	}

	public String getDocumentsIds() {
		return _documentsIds;
	}

	public void setDocumentsIds(String documentsIds) {
		_documentsIds = documentsIds;
	}

	private String _uuid;
	private long _placeId;
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
	private String _SIGid;
	private String _name;
	private String _addressComplement;
	private String _addressStreet;
	private String _addressDistribution;
	private String _addressZipCode;
	private String _addressCountry;
	private String _mercatorX;
	private String _mercatorY;
	private String _RGF93X;
	private String _RGF93Y;
	private String _alias;
	private String _presentation;
	private String _serviceAndActivities;
	private String _characteristics;
	private boolean _subjectToPublicHoliday;
	private String _exceptionalSchedule;
	private boolean _displayEvents;
	private String _additionalInformation;
	private String _phone;
	private String _mail;
	private String _siteURL;
	private String _siteLabel;
	private String _facebookURL;
	private String _facebookLabel;
	private String _accesMap;
	private String _access;
	private String _accessForDisabled;
	private Boolean _accessForBlind;
	private Boolean _accessForDeaf;
	private Boolean _accessForWheelchair;
	private Boolean _accessForElder;
	private Boolean _accessForDeficient;
	private String _RTExternalId;
	private String _occupation;
	private Date _occupationLastUpdate;
	private long _imageId;
	private String _imageIds;
	private String _videosIds;
	private long _priceId;
	private String _documentsIds;
}
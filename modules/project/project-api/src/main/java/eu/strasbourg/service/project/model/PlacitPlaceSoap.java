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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.PlacitPlaceServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.PlacitPlaceServiceSoap
 * @generated
 */
@ProviderType
public class PlacitPlaceSoap implements Serializable {
	public static PlacitPlaceSoap toSoapModel(PlacitPlace model) {
		PlacitPlaceSoap soapModel = new PlacitPlaceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPlacitPlaceId(model.getPlacitPlaceId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPlaceName(model.getPlaceName());
		soapModel.setPlaceStreetNumber(model.getPlaceStreetNumber());
		soapModel.setPlaceStreetName(model.getPlaceStreetName());
		soapModel.setPlaceZipCode(model.getPlaceZipCode());
		soapModel.setPlaceCityId(model.getPlaceCityId());
		soapModel.setImageId(model.getImageId());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setParticipationId(model.getParticipationId());
		soapModel.setPetitionId(model.getPetitionId());
		soapModel.setPlaceSIGId(model.getPlaceSIGId());

		return soapModel;
	}

	public static PlacitPlaceSoap[] toSoapModels(PlacitPlace[] models) {
		PlacitPlaceSoap[] soapModels = new PlacitPlaceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PlacitPlaceSoap[][] toSoapModels(PlacitPlace[][] models) {
		PlacitPlaceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PlacitPlaceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PlacitPlaceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PlacitPlaceSoap[] toSoapModels(List<PlacitPlace> models) {
		List<PlacitPlaceSoap> soapModels = new ArrayList<PlacitPlaceSoap>(models.size());

		for (PlacitPlace model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PlacitPlaceSoap[soapModels.size()]);
	}

	public PlacitPlaceSoap() {
	}

	public long getPrimaryKey() {
		return _placitPlaceId;
	}

	public void setPrimaryKey(long pk) {
		setPlacitPlaceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPlacitPlaceId() {
		return _placitPlaceId;
	}

	public void setPlacitPlaceId(long placitPlaceId) {
		_placitPlaceId = placitPlaceId;
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

	public long getPlaceCityId() {
		return _placeCityId;
	}

	public void setPlaceCityId(long placeCityId) {
		_placeCityId = placeCityId;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public long getParticipationId() {
		return _participationId;
	}

	public void setParticipationId(long participationId) {
		_participationId = participationId;
	}

	public long getPetitionId() {
		return _petitionId;
	}

	public void setPetitionId(long petitionId) {
		_petitionId = petitionId;
	}

	public String getPlaceSIGId() {
		return _placeSIGId;
	}

	public void setPlaceSIGId(String placeSIGId) {
		_placeSIGId = placeSIGId;
	}

	private String _uuid;
	private long _placitPlaceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _placeName;
	private String _placeStreetNumber;
	private String _placeStreetName;
	private String _placeZipCode;
	private long _placeCityId;
	private long _imageId;
	private long _projectId;
	private long _participationId;
	private long _petitionId;
	private String _placeSIGId;
}
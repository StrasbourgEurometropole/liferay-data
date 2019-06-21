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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class ArretSoap implements Serializable {
	public static ArretSoap toSoapModel(Arret model) {
		ArretSoap soapModel = new ArretSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setArretId(model.getArretId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStopId(model.getStopId());
		soapModel.setTitle(model.getTitle());
		soapModel.setCode(model.getCode());
		soapModel.setLatitude(model.getLatitude());
		soapModel.setLongitude(model.getLongitude());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static ArretSoap[] toSoapModels(Arret[] models) {
		ArretSoap[] soapModels = new ArretSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ArretSoap[][] toSoapModels(Arret[][] models) {
		ArretSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ArretSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ArretSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ArretSoap[] toSoapModels(List<Arret> models) {
		List<ArretSoap> soapModels = new ArrayList<ArretSoap>(models.size());

		for (Arret model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ArretSoap[soapModels.size()]);
	}

	public ArretSoap() {
	}

	public long getPrimaryKey() {
		return _arretId;
	}

	public void setPrimaryKey(long pk) {
		setArretId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getArretId() {
		return _arretId;
	}

	public void setArretId(long arretId) {
		_arretId = arretId;
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

	public String getStopId() {
		return _stopId;
	}

	public void setStopId(String stopId) {
		_stopId = stopId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getLatitude() {
		return _latitude;
	}

	public void setLatitude(String latitude) {
		_latitude = latitude;
	}

	public String getLongitude() {
		return _longitude;
	}

	public void setLongitude(String longitude) {
		_longitude = longitude;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _uuid;
	private long _arretId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _stopId;
	private String _title;
	private String _code;
	private String _latitude;
	private String _longitude;
	private String _type;
}
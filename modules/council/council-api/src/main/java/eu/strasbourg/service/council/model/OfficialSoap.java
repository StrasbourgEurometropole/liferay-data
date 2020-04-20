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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OfficialSoap implements Serializable {
	public static OfficialSoap toSoapModel(Official model) {
		OfficialSoap soapModel = new OfficialSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOfficialId(model.getOfficialId());
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
		soapModel.setEmail(model.getEmail());
		soapModel.setFirstname(model.getFirstname());
		soapModel.setLastname(model.getLastname());
		soapModel.setIsMunicipal(model.getIsMunicipal());
		soapModel.setIsEurometropolitan(model.getIsEurometropolitan());
		soapModel.setIsActive(model.getIsActive());

		return soapModel;
	}

	public static OfficialSoap[] toSoapModels(Official[] models) {
		OfficialSoap[] soapModels = new OfficialSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OfficialSoap[][] toSoapModels(Official[][] models) {
		OfficialSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OfficialSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OfficialSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OfficialSoap[] toSoapModels(List<Official> models) {
		List<OfficialSoap> soapModels = new ArrayList<OfficialSoap>(models.size());

		for (Official model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OfficialSoap[soapModels.size()]);
	}

	public OfficialSoap() {
	}

	public long getPrimaryKey() {
		return _officialId;
	}

	public void setPrimaryKey(long pk) {
		setOfficialId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getOfficialId() {
		return _officialId;
	}

	public void setOfficialId(long officialId) {
		_officialId = officialId;
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

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		_firstname = firstname;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		_lastname = lastname;
	}

	public boolean getIsMunicipal() {
		return _isMunicipal;
	}

	public boolean isIsMunicipal() {
		return _isMunicipal;
	}

	public void setIsMunicipal(boolean isMunicipal) {
		_isMunicipal = isMunicipal;
	}

	public boolean getIsEurometropolitan() {
		return _isEurometropolitan;
	}

	public boolean isIsEurometropolitan() {
		return _isEurometropolitan;
	}

	public void setIsEurometropolitan(boolean isEurometropolitan) {
		_isEurometropolitan = isEurometropolitan;
	}

	public boolean getIsActive() {
		return _isActive;
	}

	public boolean isIsActive() {
		return _isActive;
	}

	public void setIsActive(boolean isActive) {
		_isActive = isActive;
	}

	private String _uuid;
	private long _officialId;
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
	private String _email;
	private String _firstname;
	private String _lastname;
	private boolean _isMunicipal;
	private boolean _isEurometropolitan;
	private boolean _isActive;
}
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

package eu.strasbourg.service.official.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.official.service.http.OfficialServiceSoap}.
 *
 * @author AngeliqueZUNINO
 * @see eu.strasbourg.service.official.service.http.OfficialServiceSoap
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
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setGender(model.getGender());
		soapModel.setLastName(model.getLastName());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setThematicDelegation(model.getThematicDelegation());
		soapModel.setMissions(model.getMissions());
		soapModel.setWasMinister(model.getWasMinister());
		soapModel.setContact(model.getContact());
		soapModel.setOrderDeputyMayor(model.getOrderDeputyMayor());
		soapModel.setOrderVicePresident(model.getOrderVicePresident());
		soapModel.setImageId(model.getImageId());

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

	public int getGender() {
		return _gender;
	}

	public void setGender(int gender) {
		_gender = gender;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getThematicDelegation() {
		return _thematicDelegation;
	}

	public void setThematicDelegation(String thematicDelegation) {
		_thematicDelegation = thematicDelegation;
	}

	public String getMissions() {
		return _missions;
	}

	public void setMissions(String missions) {
		_missions = missions;
	}

	public boolean getWasMinister() {
		return _wasMinister;
	}

	public boolean isWasMinister() {
		return _wasMinister;
	}

	public void setWasMinister(boolean wasMinister) {
		_wasMinister = wasMinister;
	}

	public String getContact() {
		return _contact;
	}

	public void setContact(String contact) {
		_contact = contact;
	}

	public int getOrderDeputyMayor() {
		return _orderDeputyMayor;
	}

	public void setOrderDeputyMayor(int orderDeputyMayor) {
		_orderDeputyMayor = orderDeputyMayor;
	}

	public int getOrderVicePresident() {
		return _orderVicePresident;
	}

	public void setOrderVicePresident(int orderVicePresident) {
		_orderVicePresident = orderVicePresident;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	private String _uuid;
	private long _officialId;
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
	private int _gender;
	private String _lastName;
	private String _firstName;
	private String _thematicDelegation;
	private String _missions;
	private boolean _wasMinister;
	private String _contact;
	private int _orderDeputyMayor;
	private int _orderVicePresident;
	private Long _imageId;
}
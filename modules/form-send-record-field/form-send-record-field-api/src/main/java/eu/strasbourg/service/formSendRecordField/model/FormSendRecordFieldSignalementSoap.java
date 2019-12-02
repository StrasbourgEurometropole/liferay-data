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

package eu.strasbourg.service.formSendRecordField.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.formSendRecordField.service.http.FormSendRecordFieldSignalementServiceSoap}.
 *
 * @author Angélique Zunino
 * @see eu.strasbourg.service.formSendRecordField.service.http.FormSendRecordFieldSignalementServiceSoap
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementSoap implements Serializable {
	public static FormSendRecordFieldSignalementSoap toSoapModel(
		FormSendRecordFieldSignalement model) {
		FormSendRecordFieldSignalementSoap soapModel = new FormSendRecordFieldSignalementSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSignalementId(model.getSignalementId());
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
		soapModel.setFormSendRecordFieldId(model.getFormSendRecordFieldId());
		soapModel.setPublikId(model.getPublikId());

		return soapModel;
	}

	public static FormSendRecordFieldSignalementSoap[] toSoapModels(
		FormSendRecordFieldSignalement[] models) {
		FormSendRecordFieldSignalementSoap[] soapModels = new FormSendRecordFieldSignalementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FormSendRecordFieldSignalementSoap[][] toSoapModels(
		FormSendRecordFieldSignalement[][] models) {
		FormSendRecordFieldSignalementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FormSendRecordFieldSignalementSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FormSendRecordFieldSignalementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FormSendRecordFieldSignalementSoap[] toSoapModels(
		List<FormSendRecordFieldSignalement> models) {
		List<FormSendRecordFieldSignalementSoap> soapModels = new ArrayList<FormSendRecordFieldSignalementSoap>(models.size());

		for (FormSendRecordFieldSignalement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FormSendRecordFieldSignalementSoap[soapModels.size()]);
	}

	public FormSendRecordFieldSignalementSoap() {
	}

	public long getPrimaryKey() {
		return _signalementId;
	}

	public void setPrimaryKey(long pk) {
		setSignalementId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSignalementId() {
		return _signalementId;
	}

	public void setSignalementId(long signalementId) {
		_signalementId = signalementId;
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

	public long getFormSendRecordFieldId() {
		return _formSendRecordFieldId;
	}

	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_formSendRecordFieldId = formSendRecordFieldId;
	}

	public String getPublikId() {
		return _publikId;
	}

	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	private String _uuid;
	private long _signalementId;
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
	private long _formSendRecordFieldId;
	private String _publikId;
}
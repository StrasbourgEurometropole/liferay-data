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
 * This class is used by SOAP remote services.
 *
 * @author Angélique Zunino
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSoap implements Serializable {

	public static FormSendRecordFieldSoap toSoapModel(
		FormSendRecordField model) {

		FormSendRecordFieldSoap soapModel = new FormSendRecordFieldSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setFormSendRecordFieldId(model.getFormSendRecordFieldId());
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
		soapModel.setResponse(model.getResponse());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setContentId(model.getContentId());
		soapModel.setInstanceId(model.getInstanceId());
		soapModel.setResponseUserId(model.getResponseUserId());

		return soapModel;
	}

	public static FormSendRecordFieldSoap[] toSoapModels(
		FormSendRecordField[] models) {

		FormSendRecordFieldSoap[] soapModels =
			new FormSendRecordFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FormSendRecordFieldSoap[][] toSoapModels(
		FormSendRecordField[][] models) {

		FormSendRecordFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new FormSendRecordFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FormSendRecordFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FormSendRecordFieldSoap[] toSoapModels(
		List<FormSendRecordField> models) {

		List<FormSendRecordFieldSoap> soapModels =
			new ArrayList<FormSendRecordFieldSoap>(models.size());

		for (FormSendRecordField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new FormSendRecordFieldSoap[soapModels.size()]);
	}

	public FormSendRecordFieldSoap() {
	}

	public long getPrimaryKey() {
		return _formSendRecordFieldId;
	}

	public void setPrimaryKey(long pk) {
		setFormSendRecordFieldId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFormSendRecordFieldId() {
		return _formSendRecordFieldId;
	}

	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_formSendRecordFieldId = formSendRecordFieldId;
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

	public String getResponse() {
		return _response;
	}

	public void setResponse(String response) {
		_response = response;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public String getInstanceId() {
		return _instanceId;
	}

	public void setInstanceId(String instanceId) {
		_instanceId = instanceId;
	}

	public long getResponseUserId() {
		return _responseUserId;
	}

	public void setResponseUserId(long responseUserId) {
		_responseUserId = responseUserId;
	}

	private String _uuid;
	private long _formSendRecordFieldId;
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
	private String _response;
	private long _assetEntryId;
	private long _contentId;
	private String _instanceId;
	private long _responseUserId;

}
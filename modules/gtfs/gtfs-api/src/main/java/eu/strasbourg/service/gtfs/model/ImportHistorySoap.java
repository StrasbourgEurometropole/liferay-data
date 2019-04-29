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
public class ImportHistorySoap implements Serializable {
	public static ImportHistorySoap toSoapModel(ImportHistory model) {
		ImportHistorySoap soapModel = new ImportHistorySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setImportHistoryId(model.getImportHistoryId());
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
		soapModel.setImportResult(model.getImportResult());
		soapModel.setImportOpertations(model.getImportOpertations());
		soapModel.setErrorDescription(model.getErrorDescription());
		soapModel.setErrorStackTrace(model.getErrorStackTrace());

		return soapModel;
	}

	public static ImportHistorySoap[] toSoapModels(ImportHistory[] models) {
		ImportHistorySoap[] soapModels = new ImportHistorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImportHistorySoap[][] toSoapModels(ImportHistory[][] models) {
		ImportHistorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImportHistorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImportHistorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImportHistorySoap[] toSoapModels(List<ImportHistory> models) {
		List<ImportHistorySoap> soapModels = new ArrayList<ImportHistorySoap>(models.size());

		for (ImportHistory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImportHistorySoap[soapModels.size()]);
	}

	public ImportHistorySoap() {
	}

	public long getPrimaryKey() {
		return _importHistoryId;
	}

	public void setPrimaryKey(long pk) {
		setImportHistoryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getImportHistoryId() {
		return _importHistoryId;
	}

	public void setImportHistoryId(long importHistoryId) {
		_importHistoryId = importHistoryId;
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

	public int getImportResult() {
		return _importResult;
	}

	public void setImportResult(int importResult) {
		_importResult = importResult;
	}

	public String getImportOpertations() {
		return _importOpertations;
	}

	public void setImportOpertations(String importOpertations) {
		_importOpertations = importOpertations;
	}

	public String getErrorDescription() {
		return _errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		_errorDescription = errorDescription;
	}

	public String getErrorStackTrace() {
		return _errorStackTrace;
	}

	public void setErrorStackTrace(String errorStackTrace) {
		_errorStackTrace = errorStackTrace;
	}

	private String _uuid;
	private long _importHistoryId;
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
	private int _importResult;
	private String _importOpertations;
	private String _errorDescription;
	private String _errorStackTrace;
}
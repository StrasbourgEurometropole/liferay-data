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
public class ImportReportSoap implements Serializable {
	public static ImportReportSoap toSoapModel(ImportReport model) {
		ImportReportSoap soapModel = new ImportReportSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setReportId(model.getReportId());
		soapModel.setProvider(model.getProvider());
		soapModel.setFilename(model.getFilename());
		soapModel.setStatus(model.getStatus());
		soapModel.setGlobalErrorCause(model.getGlobalErrorCause());
		soapModel.setNewEventsCount(model.getNewEventsCount());
		soapModel.setModifiedEventsCount(model.getModifiedEventsCount());
		soapModel.setErrorEventsCount(model.getErrorEventsCount());
		soapModel.setNewManifestationsCount(model.getNewManifestationsCount());
		soapModel.setModifiedManifestationsCount(model.getModifiedManifestationsCount());
		soapModel.setErrorManifestationsCount(model.getErrorManifestationsCount());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static ImportReportSoap[] toSoapModels(ImportReport[] models) {
		ImportReportSoap[] soapModels = new ImportReportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImportReportSoap[][] toSoapModels(ImportReport[][] models) {
		ImportReportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImportReportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImportReportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImportReportSoap[] toSoapModels(List<ImportReport> models) {
		List<ImportReportSoap> soapModels = new ArrayList<ImportReportSoap>(models.size());

		for (ImportReport model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImportReportSoap[soapModels.size()]);
	}

	public ImportReportSoap() {
	}

	public long getPrimaryKey() {
		return _reportId;
	}

	public void setPrimaryKey(long pk) {
		setReportId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getReportId() {
		return _reportId;
	}

	public void setReportId(long reportId) {
		_reportId = reportId;
	}

	public String getProvider() {
		return _provider;
	}

	public void setProvider(String provider) {
		_provider = provider;
	}

	public String getFilename() {
		return _filename;
	}

	public void setFilename(String filename) {
		_filename = filename;
	}

	public long getStatus() {
		return _status;
	}

	public void setStatus(long status) {
		_status = status;
	}

	public String getGlobalErrorCause() {
		return _globalErrorCause;
	}

	public void setGlobalErrorCause(String globalErrorCause) {
		_globalErrorCause = globalErrorCause;
	}

	public long getNewEventsCount() {
		return _newEventsCount;
	}

	public void setNewEventsCount(long newEventsCount) {
		_newEventsCount = newEventsCount;
	}

	public long getModifiedEventsCount() {
		return _modifiedEventsCount;
	}

	public void setModifiedEventsCount(long modifiedEventsCount) {
		_modifiedEventsCount = modifiedEventsCount;
	}

	public long getErrorEventsCount() {
		return _errorEventsCount;
	}

	public void setErrorEventsCount(long errorEventsCount) {
		_errorEventsCount = errorEventsCount;
	}

	public long getNewManifestationsCount() {
		return _newManifestationsCount;
	}

	public void setNewManifestationsCount(long newManifestationsCount) {
		_newManifestationsCount = newManifestationsCount;
	}

	public long getModifiedManifestationsCount() {
		return _modifiedManifestationsCount;
	}

	public void setModifiedManifestationsCount(long modifiedManifestationsCount) {
		_modifiedManifestationsCount = modifiedManifestationsCount;
	}

	public long getErrorManifestationsCount() {
		return _errorManifestationsCount;
	}

	public void setErrorManifestationsCount(long errorManifestationsCount) {
		_errorManifestationsCount = errorManifestationsCount;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	private String _uuid;
	private long _reportId;
	private String _provider;
	private String _filename;
	private long _status;
	private String _globalErrorCause;
	private long _newEventsCount;
	private long _modifiedEventsCount;
	private long _errorEventsCount;
	private long _newManifestationsCount;
	private long _modifiedManifestationsCount;
	private long _errorManifestationsCount;
	private Date _startDate;
	private Date _endDate;
}
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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class ImportReportLineSoap implements Serializable {
	public static ImportReportLineSoap toSoapModel(ImportReportLine model) {
		ImportReportLineSoap soapModel = new ImportReportLineSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLineId(model.getLineId());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());
		soapModel.setLog(model.getLog());
		soapModel.setEntityName(model.getEntityName());
		soapModel.setEntityExternalId(model.getEntityExternalId());
		soapModel.setEntityId(model.getEntityId());
		soapModel.setReportId(model.getReportId());

		return soapModel;
	}

	public static ImportReportLineSoap[] toSoapModels(ImportReportLine[] models) {
		ImportReportLineSoap[] soapModels = new ImportReportLineSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImportReportLineSoap[][] toSoapModels(
		ImportReportLine[][] models) {
		ImportReportLineSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImportReportLineSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImportReportLineSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImportReportLineSoap[] toSoapModels(
		List<ImportReportLine> models) {
		List<ImportReportLineSoap> soapModels = new ArrayList<ImportReportLineSoap>(models.size());

		for (ImportReportLine model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImportReportLineSoap[soapModels.size()]);
	}

	public ImportReportLineSoap() {
	}

	public long getPrimaryKey() {
		return _lineId;
	}

	public void setPrimaryKey(long pk) {
		setLineId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLineId() {
		return _lineId;
	}

	public void setLineId(long lineId) {
		_lineId = lineId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getStatus() {
		return _status;
	}

	public void setStatus(long status) {
		_status = status;
	}

	public String getLog() {
		return _log;
	}

	public void setLog(String log) {
		_log = log;
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String entityName) {
		_entityName = entityName;
	}

	public String getEntityExternalId() {
		return _entityExternalId;
	}

	public void setEntityExternalId(String entityExternalId) {
		_entityExternalId = entityExternalId;
	}

	public long getEntityId() {
		return _entityId;
	}

	public void setEntityId(long entityId) {
		_entityId = entityId;
	}

	public long getReportId() {
		return _reportId;
	}

	public void setReportId(long reportId) {
		_reportId = reportId;
	}

	private String _uuid;
	private long _lineId;
	private String _type;
	private long _status;
	private String _log;
	private String _entityName;
	private String _entityExternalId;
	private long _entityId;
	private long _reportId;
}
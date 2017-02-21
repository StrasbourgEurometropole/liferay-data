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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ImportReportLine}.
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportLine
 * @generated
 */
@ProviderType
public class ImportReportLineWrapper implements ImportReportLine,
	ModelWrapper<ImportReportLine> {
	public ImportReportLineWrapper(ImportReportLine importReportLine) {
		_importReportLine = importReportLine;
	}

	@Override
	public Class<?> getModelClass() {
		return ImportReportLine.class;
	}

	@Override
	public String getModelClassName() {
		return ImportReportLine.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("lineId", getLineId());
		attributes.put("type", getType());
		attributes.put("status", getStatus());
		attributes.put("log", getLog());
		attributes.put("entityName", getEntityName());
		attributes.put("entityExternalId", getEntityExternalId());
		attributes.put("entityId", getEntityId());
		attributes.put("reportId", getReportId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long lineId = (Long)attributes.get("lineId");

		if (lineId != null) {
			setLineId(lineId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String log = (String)attributes.get("log");

		if (log != null) {
			setLog(log);
		}

		String entityName = (String)attributes.get("entityName");

		if (entityName != null) {
			setEntityName(entityName);
		}

		String entityExternalId = (String)attributes.get("entityExternalId");

		if (entityExternalId != null) {
			setEntityExternalId(entityExternalId);
		}

		Long entityId = (Long)attributes.get("entityId");

		if (entityId != null) {
			setEntityId(entityId);
		}

		Long reportId = (Long)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
		}
	}

	@Override
	public boolean hasError() {
		return _importReportLine.hasError();
	}

	@Override
	public boolean isCachedModel() {
		return _importReportLine.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _importReportLine.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _importReportLine.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _importReportLine.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.ImportReportLine> toCacheModel() {
		return _importReportLine.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine toEscapedModel() {
		return new ImportReportLineWrapper(_importReportLine.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine toUnescapedModel() {
		return new ImportReportLineWrapper(_importReportLine.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return _importReportLine.compareTo(importReportLine);
	}

	@Override
	public int hashCode() {
		return _importReportLine.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _importReportLine.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ImportReportLineWrapper((ImportReportLine)_importReportLine.clone());
	}

	/**
	* Returns the entity external ID of this import report line.
	*
	* @return the entity external ID of this import report line
	*/
	@Override
	public java.lang.String getEntityExternalId() {
		return _importReportLine.getEntityExternalId();
	}

	/**
	* Returns the entity name of this import report line.
	*
	* @return the entity name of this import report line
	*/
	@Override
	public java.lang.String getEntityName() {
		return _importReportLine.getEntityName();
	}

	/**
	* Returns the log of this import report line.
	*
	* @return the log of this import report line
	*/
	@Override
	public java.lang.String getLog() {
		return _importReportLine.getLog();
	}

	/**
	* Returns the type of this import report line.
	*
	* @return the type of this import report line
	*/
	@Override
	public java.lang.String getType() {
		return _importReportLine.getType();
	}

	/**
	* Returns the uuid of this import report line.
	*
	* @return the uuid of this import report line
	*/
	@Override
	public java.lang.String getUuid() {
		return _importReportLine.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _importReportLine.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _importReportLine.toXmlString();
	}

	/**
	* Returns the entity ID of this import report line.
	*
	* @return the entity ID of this import report line
	*/
	@Override
	public long getEntityId() {
		return _importReportLine.getEntityId();
	}

	/**
	* Returns the line ID of this import report line.
	*
	* @return the line ID of this import report line
	*/
	@Override
	public long getLineId() {
		return _importReportLine.getLineId();
	}

	/**
	* Returns the primary key of this import report line.
	*
	* @return the primary key of this import report line
	*/
	@Override
	public long getPrimaryKey() {
		return _importReportLine.getPrimaryKey();
	}

	/**
	* Returns the report ID of this import report line.
	*
	* @return the report ID of this import report line
	*/
	@Override
	public long getReportId() {
		return _importReportLine.getReportId();
	}

	/**
	* Returns the status of this import report line.
	*
	* @return the status of this import report line
	*/
	@Override
	public long getStatus() {
		return _importReportLine.getStatus();
	}

	@Override
	public void error(java.lang.String cause) {
		_importReportLine.error(cause);
	}

	@Override
	public void persist() {
		_importReportLine.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_importReportLine.setCachedModel(cachedModel);
	}

	/**
	* Sets the entity external ID of this import report line.
	*
	* @param entityExternalId the entity external ID of this import report line
	*/
	@Override
	public void setEntityExternalId(java.lang.String entityExternalId) {
		_importReportLine.setEntityExternalId(entityExternalId);
	}

	/**
	* Sets the entity ID of this import report line.
	*
	* @param entityId the entity ID of this import report line
	*/
	@Override
	public void setEntityId(long entityId) {
		_importReportLine.setEntityId(entityId);
	}

	/**
	* Sets the entity name of this import report line.
	*
	* @param entityName the entity name of this import report line
	*/
	@Override
	public void setEntityName(java.lang.String entityName) {
		_importReportLine.setEntityName(entityName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_importReportLine.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_importReportLine.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_importReportLine.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the line ID of this import report line.
	*
	* @param lineId the line ID of this import report line
	*/
	@Override
	public void setLineId(long lineId) {
		_importReportLine.setLineId(lineId);
	}

	/**
	* Sets the log of this import report line.
	*
	* @param log the log of this import report line
	*/
	@Override
	public void setLog(java.lang.String log) {
		_importReportLine.setLog(log);
	}

	@Override
	public void setNew(boolean n) {
		_importReportLine.setNew(n);
	}

	/**
	* Sets the primary key of this import report line.
	*
	* @param primaryKey the primary key of this import report line
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_importReportLine.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_importReportLine.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report ID of this import report line.
	*
	* @param reportId the report ID of this import report line
	*/
	@Override
	public void setReportId(long reportId) {
		_importReportLine.setReportId(reportId);
	}

	/**
	* Sets the status of this import report line.
	*
	* @param status the status of this import report line
	*/
	@Override
	public void setStatus(long status) {
		_importReportLine.setStatus(status);
	}

	/**
	* Sets the type of this import report line.
	*
	* @param type the type of this import report line
	*/
	@Override
	public void setType(java.lang.String type) {
		_importReportLine.setType(type);
	}

	/**
	* Sets the uuid of this import report line.
	*
	* @param uuid the uuid of this import report line
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_importReportLine.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportReportLineWrapper)) {
			return false;
		}

		ImportReportLineWrapper importReportLineWrapper = (ImportReportLineWrapper)obj;

		if (Objects.equals(_importReportLine,
					importReportLineWrapper._importReportLine)) {
			return true;
		}

		return false;
	}

	@Override
	public ImportReportLine getWrappedModel() {
		return _importReportLine;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _importReportLine.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _importReportLine.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_importReportLine.resetOriginalValues();
	}

	private final ImportReportLine _importReportLine;
}
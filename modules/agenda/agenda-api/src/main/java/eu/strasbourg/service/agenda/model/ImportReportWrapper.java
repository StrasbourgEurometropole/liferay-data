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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ImportReport}.
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReport
 * @generated
 */
@ProviderType
public class ImportReportWrapper implements ImportReport,
	ModelWrapper<ImportReport> {
	public ImportReportWrapper(ImportReport importReport) {
		_importReport = importReport;
	}

	@Override
	public Class<?> getModelClass() {
		return ImportReport.class;
	}

	@Override
	public String getModelClassName() {
		return ImportReport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("reportId", getReportId());
		attributes.put("provider", getProvider());
		attributes.put("filename", getFilename());
		attributes.put("status", getStatus());
		attributes.put("globalErrorCause", getGlobalErrorCause());
		attributes.put("newEventsCount", getNewEventsCount());
		attributes.put("modifiedEventsCount", getModifiedEventsCount());
		attributes.put("errorEventsCount", getErrorEventsCount());
		attributes.put("newManifestationsCount", getNewManifestationsCount());
		attributes.put("modifiedManifestationsCount",
			getModifiedManifestationsCount());
		attributes.put("errorManifestationsCount", getErrorManifestationsCount());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long reportId = (Long)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
		}

		String provider = (String)attributes.get("provider");

		if (provider != null) {
			setProvider(provider);
		}

		String filename = (String)attributes.get("filename");

		if (filename != null) {
			setFilename(filename);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String globalErrorCause = (String)attributes.get("globalErrorCause");

		if (globalErrorCause != null) {
			setGlobalErrorCause(globalErrorCause);
		}

		Long newEventsCount = (Long)attributes.get("newEventsCount");

		if (newEventsCount != null) {
			setNewEventsCount(newEventsCount);
		}

		Long modifiedEventsCount = (Long)attributes.get("modifiedEventsCount");

		if (modifiedEventsCount != null) {
			setModifiedEventsCount(modifiedEventsCount);
		}

		Long errorEventsCount = (Long)attributes.get("errorEventsCount");

		if (errorEventsCount != null) {
			setErrorEventsCount(errorEventsCount);
		}

		Long newManifestationsCount = (Long)attributes.get(
				"newManifestationsCount");

		if (newManifestationsCount != null) {
			setNewManifestationsCount(newManifestationsCount);
		}

		Long modifiedManifestationsCount = (Long)attributes.get(
				"modifiedManifestationsCount");

		if (modifiedManifestationsCount != null) {
			setModifiedManifestationsCount(modifiedManifestationsCount);
		}

		Long errorManifestationsCount = (Long)attributes.get(
				"errorManifestationsCount");

		if (errorManifestationsCount != null) {
			setErrorManifestationsCount(errorManifestationsCount);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _importReport.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _importReport.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _importReport.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _importReport.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.ImportReport> toCacheModel() {
		return _importReport.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReport toEscapedModel() {
		return new ImportReportWrapper(_importReport.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReport toUnescapedModel() {
		return new ImportReportWrapper(_importReport.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.ImportReport importReport) {
		return _importReport.compareTo(importReport);
	}

	@Override
	public int hashCode() {
		return _importReport.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _importReport.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ImportReportWrapper((ImportReport)_importReport.clone());
	}

	/**
	* Returns the filename of this import report.
	*
	* @return the filename of this import report
	*/
	@Override
	public java.lang.String getFilename() {
		return _importReport.getFilename();
	}

	/**
	* Returns the global error cause of this import report.
	*
	* @return the global error cause of this import report
	*/
	@Override
	public java.lang.String getGlobalErrorCause() {
		return _importReport.getGlobalErrorCause();
	}

	/**
	* Returns the provider of this import report.
	*
	* @return the provider of this import report
	*/
	@Override
	public java.lang.String getProvider() {
		return _importReport.getProvider();
	}

	/**
	* Returns the uuid of this import report.
	*
	* @return the uuid of this import report
	*/
	@Override
	public java.lang.String getUuid() {
		return _importReport.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _importReport.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _importReport.toXmlString();
	}

	/**
	* Returns the end date of this import report.
	*
	* @return the end date of this import report
	*/
	@Override
	public Date getEndDate() {
		return _importReport.getEndDate();
	}

	/**
	* Returns the start date of this import report.
	*
	* @return the start date of this import report
	*/
	@Override
	public Date getStartDate() {
		return _importReport.getStartDate();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getErrorEventsLines() {
		return _importReport.getErrorEventsLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getErrorManifestationsLines() {
		return _importReport.getErrorManifestationsLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getLines() {
		return _importReport.getLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getModifiedEventsLines() {
		return _importReport.getModifiedEventsLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getModifiedManifestationsLines() {
		return _importReport.getModifiedManifestationsLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getNewEventsLines() {
		return _importReport.getNewEventsLines();
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getNewManifestationsLines() {
		return _importReport.getNewManifestationsLines();
	}

	/**
	* Returns the error events count of this import report.
	*
	* @return the error events count of this import report
	*/
	@Override
	public long getErrorEventsCount() {
		return _importReport.getErrorEventsCount();
	}

	/**
	* Returns the error manifestations count of this import report.
	*
	* @return the error manifestations count of this import report
	*/
	@Override
	public long getErrorManifestationsCount() {
		return _importReport.getErrorManifestationsCount();
	}

	/**
	* Returns the modified events count of this import report.
	*
	* @return the modified events count of this import report
	*/
	@Override
	public long getModifiedEventsCount() {
		return _importReport.getModifiedEventsCount();
	}

	/**
	* Returns the modified manifestations count of this import report.
	*
	* @return the modified manifestations count of this import report
	*/
	@Override
	public long getModifiedManifestationsCount() {
		return _importReport.getModifiedManifestationsCount();
	}

	/**
	* Returns the new events count of this import report.
	*
	* @return the new events count of this import report
	*/
	@Override
	public long getNewEventsCount() {
		return _importReport.getNewEventsCount();
	}

	/**
	* Returns the new manifestations count of this import report.
	*
	* @return the new manifestations count of this import report
	*/
	@Override
	public long getNewManifestationsCount() {
		return _importReport.getNewManifestationsCount();
	}

	/**
	* Returns the primary key of this import report.
	*
	* @return the primary key of this import report
	*/
	@Override
	public long getPrimaryKey() {
		return _importReport.getPrimaryKey();
	}

	/**
	* Returns the report ID of this import report.
	*
	* @return the report ID of this import report
	*/
	@Override
	public long getReportId() {
		return _importReport.getReportId();
	}

	/**
	* Returns the status of this import report.
	*
	* @return the status of this import report
	*/
	@Override
	public long getStatus() {
		return _importReport.getStatus();
	}

	@Override
	public void globalError(java.lang.String cause) {
		_importReport.globalError(cause);
	}

	@Override
	public void incrementErrorEvents() {
		_importReport.incrementErrorEvents();
	}

	@Override
	public void incrementErrorManifestations() {
		_importReport.incrementErrorManifestations();
	}

	@Override
	public void incrementModifiedEvents() {
		_importReport.incrementModifiedEvents();
	}

	@Override
	public void incrementModifiedManifestations() {
		_importReport.incrementModifiedManifestations();
	}

	@Override
	public void incrementNewEvents() {
		_importReport.incrementNewEvents();
	}

	@Override
	public void incrementNewManifestations() {
		_importReport.incrementNewManifestations();
	}

	@Override
	public void persist() {
		_importReport.persist();
	}

	@Override
	public void sendMail() {
		_importReport.sendMail();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_importReport.setCachedModel(cachedModel);
	}

	/**
	* Sets the end date of this import report.
	*
	* @param endDate the end date of this import report
	*/
	@Override
	public void setEndDate(Date endDate) {
		_importReport.setEndDate(endDate);
	}

	/**
	* Sets the error events count of this import report.
	*
	* @param errorEventsCount the error events count of this import report
	*/
	@Override
	public void setErrorEventsCount(long errorEventsCount) {
		_importReport.setErrorEventsCount(errorEventsCount);
	}

	/**
	* Sets the error manifestations count of this import report.
	*
	* @param errorManifestationsCount the error manifestations count of this import report
	*/
	@Override
	public void setErrorManifestationsCount(long errorManifestationsCount) {
		_importReport.setErrorManifestationsCount(errorManifestationsCount);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_importReport.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_importReport.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_importReport.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the filename of this import report.
	*
	* @param filename the filename of this import report
	*/
	@Override
	public void setFilename(java.lang.String filename) {
		_importReport.setFilename(filename);
	}

	/**
	* Sets the global error cause of this import report.
	*
	* @param globalErrorCause the global error cause of this import report
	*/
	@Override
	public void setGlobalErrorCause(java.lang.String globalErrorCause) {
		_importReport.setGlobalErrorCause(globalErrorCause);
	}

	/**
	* Sets the modified events count of this import report.
	*
	* @param modifiedEventsCount the modified events count of this import report
	*/
	@Override
	public void setModifiedEventsCount(long modifiedEventsCount) {
		_importReport.setModifiedEventsCount(modifiedEventsCount);
	}

	/**
	* Sets the modified manifestations count of this import report.
	*
	* @param modifiedManifestationsCount the modified manifestations count of this import report
	*/
	@Override
	public void setModifiedManifestationsCount(long modifiedManifestationsCount) {
		_importReport.setModifiedManifestationsCount(modifiedManifestationsCount);
	}

	@Override
	public void setNew(boolean n) {
		_importReport.setNew(n);
	}

	/**
	* Sets the new events count of this import report.
	*
	* @param newEventsCount the new events count of this import report
	*/
	@Override
	public void setNewEventsCount(long newEventsCount) {
		_importReport.setNewEventsCount(newEventsCount);
	}

	/**
	* Sets the new manifestations count of this import report.
	*
	* @param newManifestationsCount the new manifestations count of this import report
	*/
	@Override
	public void setNewManifestationsCount(long newManifestationsCount) {
		_importReport.setNewManifestationsCount(newManifestationsCount);
	}

	/**
	* Sets the primary key of this import report.
	*
	* @param primaryKey the primary key of this import report
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_importReport.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_importReport.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the provider of this import report.
	*
	* @param provider the provider of this import report
	*/
	@Override
	public void setProvider(java.lang.String provider) {
		_importReport.setProvider(provider);
	}

	/**
	* Sets the report ID of this import report.
	*
	* @param reportId the report ID of this import report
	*/
	@Override
	public void setReportId(long reportId) {
		_importReport.setReportId(reportId);
	}

	/**
	* Sets the start date of this import report.
	*
	* @param startDate the start date of this import report
	*/
	@Override
	public void setStartDate(Date startDate) {
		_importReport.setStartDate(startDate);
	}

	/**
	* Sets the status of this import report.
	*
	* @param status the status of this import report
	*/
	@Override
	public void setStatus(long status) {
		_importReport.setStatus(status);
	}

	/**
	* Sets the uuid of this import report.
	*
	* @param uuid the uuid of this import report
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_importReport.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportReportWrapper)) {
			return false;
		}

		ImportReportWrapper importReportWrapper = (ImportReportWrapper)obj;

		if (Objects.equals(_importReport, importReportWrapper._importReport)) {
			return true;
		}

		return false;
	}

	@Override
	public ImportReport getWrappedModel() {
		return _importReport;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _importReport.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _importReport.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_importReport.resetOriginalValues();
	}

	private final ImportReport _importReport;
}
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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ImportHistory}.
 * </p>
 *
 * @author Cedric Henry
 * @see ImportHistory
 * @generated
 */
@ProviderType
public class ImportHistoryWrapper implements ImportHistory,
	ModelWrapper<ImportHistory> {
	public ImportHistoryWrapper(ImportHistory importHistory) {
		_importHistory = importHistory;
	}

	@Override
	public Class<?> getModelClass() {
		return ImportHistory.class;
	}

	@Override
	public String getModelClassName() {
		return ImportHistory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("importHistoryId", getImportHistoryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("importResult", getImportResult());
		attributes.put("importOpertations", getImportOpertations());
		attributes.put("errorDescription", getErrorDescription());
		attributes.put("errorStackTrace", getErrorStackTrace());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long importHistoryId = (Long)attributes.get("importHistoryId");

		if (importHistoryId != null) {
			setImportHistoryId(importHistoryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Integer importResult = (Integer)attributes.get("importResult");

		if (importResult != null) {
			setImportResult(importResult);
		}

		String importOpertations = (String)attributes.get("importOpertations");

		if (importOpertations != null) {
			setImportOpertations(importOpertations);
		}

		String errorDescription = (String)attributes.get("errorDescription");

		if (errorDescription != null) {
			setErrorDescription(errorDescription);
		}

		String errorStackTrace = (String)attributes.get("errorStackTrace");

		if (errorStackTrace != null) {
			setErrorStackTrace(errorStackTrace);
		}
	}

	/**
	* Returns <code>true</code> if this import history is approved.
	*
	* @return <code>true</code> if this import history is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _importHistory.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _importHistory.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this import history is denied.
	*
	* @return <code>true</code> if this import history is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _importHistory.isDenied();
	}

	/**
	* Returns <code>true</code> if this import history is a draft.
	*
	* @return <code>true</code> if this import history is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _importHistory.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _importHistory.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this import history is expired.
	*
	* @return <code>true</code> if this import history is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _importHistory.isExpired();
	}

	/**
	* Returns <code>true</code> if this import history is inactive.
	*
	* @return <code>true</code> if this import history is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _importHistory.isInactive();
	}

	/**
	* Returns <code>true</code> if this import history is incomplete.
	*
	* @return <code>true</code> if this import history is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _importHistory.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _importHistory.isNew();
	}

	/**
	* Returns <code>true</code> if this import history is pending.
	*
	* @return <code>true</code> if this import history is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _importHistory.isPending();
	}

	/**
	* Returns <code>true</code> if this import history is scheduled.
	*
	* @return <code>true</code> if this import history is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _importHistory.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _importHistory.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.ImportHistory> toCacheModel() {
		return _importHistory.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory toEscapedModel() {
		return new ImportHistoryWrapper(_importHistory.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory toUnescapedModel() {
		return new ImportHistoryWrapper(_importHistory.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return _importHistory.compareTo(importHistory);
	}

	/**
	* Returns the import result of this import history.
	*
	* @return the import result of this import history
	*/
	@Override
	public int getImportResult() {
		return _importHistory.getImportResult();
	}

	/**
	* Returns the status of this import history.
	*
	* @return the status of this import history
	*/
	@Override
	public int getStatus() {
		return _importHistory.getStatus();
	}

	@Override
	public int hashCode() {
		return _importHistory.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _importHistory.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ImportHistoryWrapper((ImportHistory)_importHistory.clone());
	}

	/**
	* Returns the error description of this import history.
	*
	* @return the error description of this import history
	*/
	@Override
	public java.lang.String getErrorDescription() {
		return _importHistory.getErrorDescription();
	}

	/**
	* Returns the error stack trace of this import history.
	*
	* @return the error stack trace of this import history
	*/
	@Override
	public java.lang.String getErrorStackTrace() {
		return _importHistory.getErrorStackTrace();
	}

	/**
	* Returns the import opertations of this import history.
	*
	* @return the import opertations of this import history
	*/
	@Override
	public java.lang.String getImportOpertations() {
		return _importHistory.getImportOpertations();
	}

	/**
	* Returns the status by user name of this import history.
	*
	* @return the status by user name of this import history
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _importHistory.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this import history.
	*
	* @return the status by user uuid of this import history
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _importHistory.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this import history.
	*
	* @return the user name of this import history
	*/
	@Override
	public java.lang.String getUserName() {
		return _importHistory.getUserName();
	}

	/**
	* Returns the user uuid of this import history.
	*
	* @return the user uuid of this import history
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _importHistory.getUserUuid();
	}

	/**
	* Returns the uuid of this import history.
	*
	* @return the uuid of this import history
	*/
	@Override
	public java.lang.String getUuid() {
		return _importHistory.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _importHistory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _importHistory.toXmlString();
	}

	/**
	* Returns the create date of this import history.
	*
	* @return the create date of this import history
	*/
	@Override
	public Date getCreateDate() {
		return _importHistory.getCreateDate();
	}

	/**
	* Returns the modified date of this import history.
	*
	* @return the modified date of this import history
	*/
	@Override
	public Date getModifiedDate() {
		return _importHistory.getModifiedDate();
	}

	/**
	* Returns the status date of this import history.
	*
	* @return the status date of this import history
	*/
	@Override
	public Date getStatusDate() {
		return _importHistory.getStatusDate();
	}

	/**
	* Returns the company ID of this import history.
	*
	* @return the company ID of this import history
	*/
	@Override
	public long getCompanyId() {
		return _importHistory.getCompanyId();
	}

	/**
	* Returns the group ID of this import history.
	*
	* @return the group ID of this import history
	*/
	@Override
	public long getGroupId() {
		return _importHistory.getGroupId();
	}

	/**
	* Returns the import history ID of this import history.
	*
	* @return the import history ID of this import history
	*/
	@Override
	public long getImportHistoryId() {
		return _importHistory.getImportHistoryId();
	}

	/**
	* Returns the primary key of this import history.
	*
	* @return the primary key of this import history
	*/
	@Override
	public long getPrimaryKey() {
		return _importHistory.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this import history.
	*
	* @return the status by user ID of this import history
	*/
	@Override
	public long getStatusByUserId() {
		return _importHistory.getStatusByUserId();
	}

	/**
	* Returns the user ID of this import history.
	*
	* @return the user ID of this import history
	*/
	@Override
	public long getUserId() {
		return _importHistory.getUserId();
	}

	@Override
	public void persist() {
		_importHistory.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_importHistory.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this import history.
	*
	* @param companyId the company ID of this import history
	*/
	@Override
	public void setCompanyId(long companyId) {
		_importHistory.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this import history.
	*
	* @param createDate the create date of this import history
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_importHistory.setCreateDate(createDate);
	}

	/**
	* Sets the error description of this import history.
	*
	* @param errorDescription the error description of this import history
	*/
	@Override
	public void setErrorDescription(java.lang.String errorDescription) {
		_importHistory.setErrorDescription(errorDescription);
	}

	/**
	* Sets the error stack trace of this import history.
	*
	* @param errorStackTrace the error stack trace of this import history
	*/
	@Override
	public void setErrorStackTrace(java.lang.String errorStackTrace) {
		_importHistory.setErrorStackTrace(errorStackTrace);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_importHistory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_importHistory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_importHistory.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this import history.
	*
	* @param groupId the group ID of this import history
	*/
	@Override
	public void setGroupId(long groupId) {
		_importHistory.setGroupId(groupId);
	}

	/**
	* Sets the import history ID of this import history.
	*
	* @param importHistoryId the import history ID of this import history
	*/
	@Override
	public void setImportHistoryId(long importHistoryId) {
		_importHistory.setImportHistoryId(importHistoryId);
	}

	/**
	* Sets the import opertations of this import history.
	*
	* @param importOpertations the import opertations of this import history
	*/
	@Override
	public void setImportOpertations(java.lang.String importOpertations) {
		_importHistory.setImportOpertations(importOpertations);
	}

	/**
	* Sets the import result of this import history.
	*
	* @param importResult the import result of this import history
	*/
	@Override
	public void setImportResult(int importResult) {
		_importHistory.setImportResult(importResult);
	}

	/**
	* Sets the modified date of this import history.
	*
	* @param modifiedDate the modified date of this import history
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_importHistory.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_importHistory.setNew(n);
	}

	/**
	* Sets the primary key of this import history.
	*
	* @param primaryKey the primary key of this import history
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_importHistory.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_importHistory.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this import history.
	*
	* @param status the status of this import history
	*/
	@Override
	public void setStatus(int status) {
		_importHistory.setStatus(status);
	}

	/**
	* Sets the status by user ID of this import history.
	*
	* @param statusByUserId the status by user ID of this import history
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_importHistory.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this import history.
	*
	* @param statusByUserName the status by user name of this import history
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_importHistory.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this import history.
	*
	* @param statusByUserUuid the status by user uuid of this import history
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_importHistory.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this import history.
	*
	* @param statusDate the status date of this import history
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_importHistory.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this import history.
	*
	* @param userId the user ID of this import history
	*/
	@Override
	public void setUserId(long userId) {
		_importHistory.setUserId(userId);
	}

	/**
	* Sets the user name of this import history.
	*
	* @param userName the user name of this import history
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_importHistory.setUserName(userName);
	}

	/**
	* Sets the user uuid of this import history.
	*
	* @param userUuid the user uuid of this import history
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_importHistory.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this import history.
	*
	* @param uuid the uuid of this import history
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_importHistory.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportHistoryWrapper)) {
			return false;
		}

		ImportHistoryWrapper importHistoryWrapper = (ImportHistoryWrapper)obj;

		if (Objects.equals(_importHistory, importHistoryWrapper._importHistory)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _importHistory.getStagedModelType();
	}

	@Override
	public ImportHistory getWrappedModel() {
		return _importHistory;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _importHistory.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _importHistory.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_importHistory.resetOriginalValues();
	}

	private final ImportHistory _importHistory;
}
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
 * This class is a wrapper for {@link ImportHistoric}.
 * </p>
 *
 * @author Cedric Henry
 * @see ImportHistoric
 * @generated
 */
@ProviderType
public class ImportHistoricWrapper implements ImportHistoric,
	ModelWrapper<ImportHistoric> {
	public ImportHistoricWrapper(ImportHistoric importHistoric) {
		_importHistoric = importHistoric;
	}

	@Override
	public Class<?> getModelClass() {
		return ImportHistoric.class;
	}

	@Override
	public String getModelClassName() {
		return ImportHistoric.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("importHistoricId", getImportHistoricId());
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
		attributes.put("result", getResult());
		attributes.put("operations", getOperations());
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

		Long importHistoricId = (Long)attributes.get("importHistoricId");

		if (importHistoricId != null) {
			setImportHistoricId(importHistoricId);
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

		Integer result = (Integer)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		String operations = (String)attributes.get("operations");

		if (operations != null) {
			setOperations(operations);
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
	* Returns <code>true</code> if this import historic is approved.
	*
	* @return <code>true</code> if this import historic is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _importHistoric.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _importHistoric.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this import historic is denied.
	*
	* @return <code>true</code> if this import historic is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _importHistoric.isDenied();
	}

	/**
	* Returns <code>true</code> if this import historic is a draft.
	*
	* @return <code>true</code> if this import historic is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _importHistoric.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _importHistoric.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this import historic is expired.
	*
	* @return <code>true</code> if this import historic is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _importHistoric.isExpired();
	}

	/**
	* Returns <code>true</code> if this import historic is inactive.
	*
	* @return <code>true</code> if this import historic is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _importHistoric.isInactive();
	}

	/**
	* Returns <code>true</code> if this import historic is incomplete.
	*
	* @return <code>true</code> if this import historic is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _importHistoric.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _importHistoric.isNew();
	}

	/**
	* Returns <code>true</code> if this import historic is pending.
	*
	* @return <code>true</code> if this import historic is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _importHistoric.isPending();
	}

	/**
	* Returns <code>true</code> if this import historic is scheduled.
	*
	* @return <code>true</code> if this import historic is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _importHistoric.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _importHistoric.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _importHistoric.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.ImportHistoric> toCacheModel() {
		return _importHistoric.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistoric toEscapedModel() {
		return new ImportHistoricWrapper(_importHistoric.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistoric toUnescapedModel() {
		return new ImportHistoricWrapper(_importHistoric.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric) {
		return _importHistoric.compareTo(importHistoric);
	}

	/**
	* Returns the result of this import historic.
	*
	* @return the result of this import historic
	*/
	@Override
	public int getResult() {
		return _importHistoric.getResult();
	}

	/**
	* Returns the status of this import historic.
	*
	* @return the status of this import historic
	*/
	@Override
	public int getStatus() {
		return _importHistoric.getStatus();
	}

	@Override
	public int hashCode() {
		return _importHistoric.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _importHistoric.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ImportHistoricWrapper((ImportHistoric)_importHistoric.clone());
	}

	/**
	* Returns the error description of this import historic.
	*
	* @return the error description of this import historic
	*/
	@Override
	public java.lang.String getErrorDescription() {
		return _importHistoric.getErrorDescription();
	}

	/**
	* Returns the error stack trace of this import historic.
	*
	* @return the error stack trace of this import historic
	*/
	@Override
	public java.lang.String getErrorStackTrace() {
		return _importHistoric.getErrorStackTrace();
	}

	/**
	* Returns the operations of this import historic.
	*
	* @return the operations of this import historic
	*/
	@Override
	public java.lang.String getOperations() {
		return _importHistoric.getOperations();
	}

	/**
	* Renvoie le label affichable du resultat de l'import
	*
	* @return
	*/
	@Override
	public java.lang.String getResultLabel() {
		return _importHistoric.getResultLabel();
	}

	/**
	* Returns the status by user name of this import historic.
	*
	* @return the status by user name of this import historic
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _importHistoric.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this import historic.
	*
	* @return the status by user uuid of this import historic
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _importHistoric.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this import historic.
	*
	* @return the user name of this import historic
	*/
	@Override
	public java.lang.String getUserName() {
		return _importHistoric.getUserName();
	}

	/**
	* Returns the user uuid of this import historic.
	*
	* @return the user uuid of this import historic
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _importHistoric.getUserUuid();
	}

	/**
	* Returns the uuid of this import historic.
	*
	* @return the uuid of this import historic
	*/
	@Override
	public java.lang.String getUuid() {
		return _importHistoric.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _importHistoric.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _importHistoric.toXmlString();
	}

	/**
	* Returns the create date of this import historic.
	*
	* @return the create date of this import historic
	*/
	@Override
	public Date getCreateDate() {
		return _importHistoric.getCreateDate();
	}

	/**
	* Returns the modified date of this import historic.
	*
	* @return the modified date of this import historic
	*/
	@Override
	public Date getModifiedDate() {
		return _importHistoric.getModifiedDate();
	}

	/**
	* Returns the status date of this import historic.
	*
	* @return the status date of this import historic
	*/
	@Override
	public Date getStatusDate() {
		return _importHistoric.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _importHistoric.getCategories();
	}

	/**
	* Returns the company ID of this import historic.
	*
	* @return the company ID of this import historic
	*/
	@Override
	public long getCompanyId() {
		return _importHistoric.getCompanyId();
	}

	/**
	* Returns the group ID of this import historic.
	*
	* @return the group ID of this import historic
	*/
	@Override
	public long getGroupId() {
		return _importHistoric.getGroupId();
	}

	/**
	* Returns the import historic ID of this import historic.
	*
	* @return the import historic ID of this import historic
	*/
	@Override
	public long getImportHistoricId() {
		return _importHistoric.getImportHistoricId();
	}

	/**
	* Returns the primary key of this import historic.
	*
	* @return the primary key of this import historic
	*/
	@Override
	public long getPrimaryKey() {
		return _importHistoric.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this import historic.
	*
	* @return the status by user ID of this import historic
	*/
	@Override
	public long getStatusByUserId() {
		return _importHistoric.getStatusByUserId();
	}

	/**
	* Returns the user ID of this import historic.
	*
	* @return the user ID of this import historic
	*/
	@Override
	public long getUserId() {
		return _importHistoric.getUserId();
	}

	@Override
	public void persist() {
		_importHistoric.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_importHistoric.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this import historic.
	*
	* @param companyId the company ID of this import historic
	*/
	@Override
	public void setCompanyId(long companyId) {
		_importHistoric.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this import historic.
	*
	* @param createDate the create date of this import historic
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_importHistoric.setCreateDate(createDate);
	}

	/**
	* Sets the error description of this import historic.
	*
	* @param errorDescription the error description of this import historic
	*/
	@Override
	public void setErrorDescription(java.lang.String errorDescription) {
		_importHistoric.setErrorDescription(errorDescription);
	}

	/**
	* Sets the error stack trace of this import historic.
	*
	* @param errorStackTrace the error stack trace of this import historic
	*/
	@Override
	public void setErrorStackTrace(java.lang.String errorStackTrace) {
		_importHistoric.setErrorStackTrace(errorStackTrace);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_importHistoric.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_importHistoric.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_importHistoric.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this import historic.
	*
	* @param groupId the group ID of this import historic
	*/
	@Override
	public void setGroupId(long groupId) {
		_importHistoric.setGroupId(groupId);
	}

	/**
	* Sets the import historic ID of this import historic.
	*
	* @param importHistoricId the import historic ID of this import historic
	*/
	@Override
	public void setImportHistoricId(long importHistoricId) {
		_importHistoric.setImportHistoricId(importHistoricId);
	}

	/**
	* Sets the modified date of this import historic.
	*
	* @param modifiedDate the modified date of this import historic
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_importHistoric.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_importHistoric.setNew(n);
	}

	/**
	* Sets the operations of this import historic.
	*
	* @param operations the operations of this import historic
	*/
	@Override
	public void setOperations(java.lang.String operations) {
		_importHistoric.setOperations(operations);
	}

	/**
	* Sets the primary key of this import historic.
	*
	* @param primaryKey the primary key of this import historic
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_importHistoric.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_importHistoric.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result of this import historic.
	*
	* @param result the result of this import historic
	*/
	@Override
	public void setResult(int result) {
		_importHistoric.setResult(result);
	}

	/**
	* Sets the status of this import historic.
	*
	* @param status the status of this import historic
	*/
	@Override
	public void setStatus(int status) {
		_importHistoric.setStatus(status);
	}

	/**
	* Sets the status by user ID of this import historic.
	*
	* @param statusByUserId the status by user ID of this import historic
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_importHistoric.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this import historic.
	*
	* @param statusByUserName the status by user name of this import historic
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_importHistoric.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this import historic.
	*
	* @param statusByUserUuid the status by user uuid of this import historic
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_importHistoric.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this import historic.
	*
	* @param statusDate the status date of this import historic
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_importHistoric.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this import historic.
	*
	* @param userId the user ID of this import historic
	*/
	@Override
	public void setUserId(long userId) {
		_importHistoric.setUserId(userId);
	}

	/**
	* Sets the user name of this import historic.
	*
	* @param userName the user name of this import historic
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_importHistoric.setUserName(userName);
	}

	/**
	* Sets the user uuid of this import historic.
	*
	* @param userUuid the user uuid of this import historic
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_importHistoric.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this import historic.
	*
	* @param uuid the uuid of this import historic
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_importHistoric.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportHistoricWrapper)) {
			return false;
		}

		ImportHistoricWrapper importHistoricWrapper = (ImportHistoricWrapper)obj;

		if (Objects.equals(_importHistoric,
					importHistoricWrapper._importHistoric)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _importHistoric.getStagedModelType();
	}

	@Override
	public ImportHistoric getWrappedModel() {
		return _importHistoric;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _importHistoric.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _importHistoric.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_importHistoric.resetOriginalValues();
	}

	private final ImportHistoric _importHistoric;
}
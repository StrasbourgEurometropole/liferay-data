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

package eu.strasbourg.service.oidc.model;

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
 * This class is a wrapper for {@link AnonymisationHistoric}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoric
 * @generated
 */
public class AnonymisationHistoricWrapper
	implements AnonymisationHistoric, ModelWrapper<AnonymisationHistoric> {

	public AnonymisationHistoricWrapper(
		AnonymisationHistoric anonymisationHistoric) {

		_anonymisationHistoric = anonymisationHistoric;
	}

	@Override
	public Class<?> getModelClass() {
		return AnonymisationHistoric.class;
	}

	@Override
	public String getModelClassName() {
		return AnonymisationHistoric.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("anonymisationHistoricId", getAnonymisationHistoricId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("result", getResult());
		attributes.put("operations", getOperations());
		attributes.put("errorDescription", getErrorDescription());
		attributes.put("errorStackTrace", getErrorStackTrace());
		attributes.put("startDate", getStartDate());
		attributes.put("finishDate", getFinishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long anonymisationHistoricId = (Long)attributes.get(
			"anonymisationHistoricId");

		if (anonymisationHistoricId != null) {
			setAnonymisationHistoricId(anonymisationHistoricId);
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

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
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

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date finishDate = (Date)attributes.get("finishDate");

		if (finishDate != null) {
			setFinishDate(finishDate);
		}
	}

	/**
	 * Ajout d'une ligne dans le resultat de l'anonymisation
	 *
	 * @return
	 */
	@Override
	public void addNewOperation(String operation) {
		_anonymisationHistoric.addNewOperation(operation);
	}

	@Override
	public Object clone() {
		return new AnonymisationHistoricWrapper(
			(AnonymisationHistoric)_anonymisationHistoric.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric
			anonymisationHistoric) {

		return _anonymisationHistoric.compareTo(anonymisationHistoric);
	}

	/**
	 * Returns the anonymisation historic ID of this anonymisation historic.
	 *
	 * @return the anonymisation historic ID of this anonymisation historic
	 */
	@Override
	public long getAnonymisationHistoricId() {
		return _anonymisationHistoric.getAnonymisationHistoricId();
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _anonymisationHistoric.getAssetEntry();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _anonymisationHistoric.getCategories();
	}

	/**
	 * Returns the company ID of this anonymisation historic.
	 *
	 * @return the company ID of this anonymisation historic
	 */
	@Override
	public long getCompanyId() {
		return _anonymisationHistoric.getCompanyId();
	}

	/**
	 * Returns the create date of this anonymisation historic.
	 *
	 * @return the create date of this anonymisation historic
	 */
	@Override
	public Date getCreateDate() {
		return _anonymisationHistoric.getCreateDate();
	}

	/**
	 * Returns the error description of this anonymisation historic.
	 *
	 * @return the error description of this anonymisation historic
	 */
	@Override
	public String getErrorDescription() {
		return _anonymisationHistoric.getErrorDescription();
	}

	/**
	 * Returns the error stack trace of this anonymisation historic.
	 *
	 * @return the error stack trace of this anonymisation historic
	 */
	@Override
	public String getErrorStackTrace() {
		return _anonymisationHistoric.getErrorStackTrace();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _anonymisationHistoric.getExpandoBridge();
	}

	/**
	 * Returns the finish date of this anonymisation historic.
	 *
	 * @return the finish date of this anonymisation historic
	 */
	@Override
	public Date getFinishDate() {
		return _anonymisationHistoric.getFinishDate();
	}

	/**
	 * Returns the group ID of this anonymisation historic.
	 *
	 * @return the group ID of this anonymisation historic
	 */
	@Override
	public long getGroupId() {
		return _anonymisationHistoric.getGroupId();
	}

	/**
	 * Returns the last publish date of this anonymisation historic.
	 *
	 * @return the last publish date of this anonymisation historic
	 */
	@Override
	public Date getLastPublishDate() {
		return _anonymisationHistoric.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this anonymisation historic.
	 *
	 * @return the modified date of this anonymisation historic
	 */
	@Override
	public Date getModifiedDate() {
		return _anonymisationHistoric.getModifiedDate();
	}

	/**
	 * Returns the operations of this anonymisation historic.
	 *
	 * @return the operations of this anonymisation historic
	 */
	@Override
	public String getOperations() {
		return _anonymisationHistoric.getOperations();
	}

	/**
	 * Returns the primary key of this anonymisation historic.
	 *
	 * @return the primary key of this anonymisation historic
	 */
	@Override
	public long getPrimaryKey() {
		return _anonymisationHistoric.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _anonymisationHistoric.getPrimaryKeyObj();
	}

	/**
	 * Returns the result of this anonymisation historic.
	 *
	 * @return the result of this anonymisation historic
	 */
	@Override
	public int getResult() {
		return _anonymisationHistoric.getResult();
	}

	/**
	 * Renvoie le label affichable du resultat de l'anonymisation
	 *
	 * @return
	 */
	@Override
	public String getResultLabel() {
		return _anonymisationHistoric.getResultLabel();
	}

	/**
	 * Returns the start date of this anonymisation historic.
	 *
	 * @return the start date of this anonymisation historic
	 */
	@Override
	public Date getStartDate() {
		return _anonymisationHistoric.getStartDate();
	}

	/**
	 * Returns the status of this anonymisation historic.
	 *
	 * @return the status of this anonymisation historic
	 */
	@Override
	public int getStatus() {
		return _anonymisationHistoric.getStatus();
	}

	/**
	 * Returns the status by user ID of this anonymisation historic.
	 *
	 * @return the status by user ID of this anonymisation historic
	 */
	@Override
	public long getStatusByUserId() {
		return _anonymisationHistoric.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this anonymisation historic.
	 *
	 * @return the status by user name of this anonymisation historic
	 */
	@Override
	public String getStatusByUserName() {
		return _anonymisationHistoric.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this anonymisation historic.
	 *
	 * @return the status by user uuid of this anonymisation historic
	 */
	@Override
	public String getStatusByUserUuid() {
		return _anonymisationHistoric.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this anonymisation historic.
	 *
	 * @return the status date of this anonymisation historic
	 */
	@Override
	public Date getStatusDate() {
		return _anonymisationHistoric.getStatusDate();
	}

	/**
	 * Returns the user ID of this anonymisation historic.
	 *
	 * @return the user ID of this anonymisation historic
	 */
	@Override
	public long getUserId() {
		return _anonymisationHistoric.getUserId();
	}

	/**
	 * Returns the user name of this anonymisation historic.
	 *
	 * @return the user name of this anonymisation historic
	 */
	@Override
	public String getUserName() {
		return _anonymisationHistoric.getUserName();
	}

	/**
	 * Returns the user uuid of this anonymisation historic.
	 *
	 * @return the user uuid of this anonymisation historic
	 */
	@Override
	public String getUserUuid() {
		return _anonymisationHistoric.getUserUuid();
	}

	/**
	 * Returns the uuid of this anonymisation historic.
	 *
	 * @return the uuid of this anonymisation historic
	 */
	@Override
	public String getUuid() {
		return _anonymisationHistoric.getUuid();
	}

	@Override
	public int hashCode() {
		return _anonymisationHistoric.hashCode();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is approved.
	 *
	 * @return <code>true</code> if this anonymisation historic is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _anonymisationHistoric.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _anonymisationHistoric.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is denied.
	 *
	 * @return <code>true</code> if this anonymisation historic is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _anonymisationHistoric.isDenied();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is a draft.
	 *
	 * @return <code>true</code> if this anonymisation historic is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _anonymisationHistoric.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _anonymisationHistoric.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is expired.
	 *
	 * @return <code>true</code> if this anonymisation historic is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _anonymisationHistoric.isExpired();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is inactive.
	 *
	 * @return <code>true</code> if this anonymisation historic is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _anonymisationHistoric.isInactive();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is incomplete.
	 *
	 * @return <code>true</code> if this anonymisation historic is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _anonymisationHistoric.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _anonymisationHistoric.isNew();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is pending.
	 *
	 * @return <code>true</code> if this anonymisation historic is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _anonymisationHistoric.isPending();
	}

	/**
	 * Returns <code>true</code> if this anonymisation historic is scheduled.
	 *
	 * @return <code>true</code> if this anonymisation historic is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _anonymisationHistoric.isScheduled();
	}

	@Override
	public void persist() {
		_anonymisationHistoric.persist();
	}

	/**
	 * Envoi du mail d'import
	 */
	@Override
	public void sendMail() {
		_anonymisationHistoric.sendMail();
	}

	/**
	 * Sets the anonymisation historic ID of this anonymisation historic.
	 *
	 * @param anonymisationHistoricId the anonymisation historic ID of this anonymisation historic
	 */
	@Override
	public void setAnonymisationHistoricId(long anonymisationHistoricId) {
		_anonymisationHistoric.setAnonymisationHistoricId(
			anonymisationHistoricId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_anonymisationHistoric.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this anonymisation historic.
	 *
	 * @param companyId the company ID of this anonymisation historic
	 */
	@Override
	public void setCompanyId(long companyId) {
		_anonymisationHistoric.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this anonymisation historic.
	 *
	 * @param createDate the create date of this anonymisation historic
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_anonymisationHistoric.setCreateDate(createDate);
	}

	/**
	 * Sets the error description of this anonymisation historic.
	 *
	 * @param errorDescription the error description of this anonymisation historic
	 */
	@Override
	public void setErrorDescription(String errorDescription) {
		_anonymisationHistoric.setErrorDescription(errorDescription);
	}

	/**
	 * Sets the error stack trace of this anonymisation historic.
	 *
	 * @param errorStackTrace the error stack trace of this anonymisation historic
	 */
	@Override
	public void setErrorStackTrace(String errorStackTrace) {
		_anonymisationHistoric.setErrorStackTrace(errorStackTrace);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_anonymisationHistoric.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_anonymisationHistoric.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_anonymisationHistoric.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the finish date of this anonymisation historic.
	 *
	 * @param finishDate the finish date of this anonymisation historic
	 */
	@Override
	public void setFinishDate(Date finishDate) {
		_anonymisationHistoric.setFinishDate(finishDate);
	}

	/**
	 * Sets the group ID of this anonymisation historic.
	 *
	 * @param groupId the group ID of this anonymisation historic
	 */
	@Override
	public void setGroupId(long groupId) {
		_anonymisationHistoric.setGroupId(groupId);
	}

	/**
	 * Sets the last publish date of this anonymisation historic.
	 *
	 * @param lastPublishDate the last publish date of this anonymisation historic
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_anonymisationHistoric.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this anonymisation historic.
	 *
	 * @param modifiedDate the modified date of this anonymisation historic
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_anonymisationHistoric.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_anonymisationHistoric.setNew(n);
	}

	/**
	 * Sets the operations of this anonymisation historic.
	 *
	 * @param operations the operations of this anonymisation historic
	 */
	@Override
	public void setOperations(String operations) {
		_anonymisationHistoric.setOperations(operations);
	}

	/**
	 * Sets the primary key of this anonymisation historic.
	 *
	 * @param primaryKey the primary key of this anonymisation historic
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_anonymisationHistoric.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_anonymisationHistoric.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the result of this anonymisation historic.
	 *
	 * @param result the result of this anonymisation historic
	 */
	@Override
	public void setResult(int result) {
		_anonymisationHistoric.setResult(result);
	}

	/**
	 * Sets the start date of this anonymisation historic.
	 *
	 * @param startDate the start date of this anonymisation historic
	 */
	@Override
	public void setStartDate(Date startDate) {
		_anonymisationHistoric.setStartDate(startDate);
	}

	/**
	 * Sets the status of this anonymisation historic.
	 *
	 * @param status the status of this anonymisation historic
	 */
	@Override
	public void setStatus(int status) {
		_anonymisationHistoric.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this anonymisation historic.
	 *
	 * @param statusByUserId the status by user ID of this anonymisation historic
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_anonymisationHistoric.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this anonymisation historic.
	 *
	 * @param statusByUserName the status by user name of this anonymisation historic
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_anonymisationHistoric.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this anonymisation historic.
	 *
	 * @param statusByUserUuid the status by user uuid of this anonymisation historic
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_anonymisationHistoric.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this anonymisation historic.
	 *
	 * @param statusDate the status date of this anonymisation historic
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_anonymisationHistoric.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this anonymisation historic.
	 *
	 * @param userId the user ID of this anonymisation historic
	 */
	@Override
	public void setUserId(long userId) {
		_anonymisationHistoric.setUserId(userId);
	}

	/**
	 * Sets the user name of this anonymisation historic.
	 *
	 * @param userName the user name of this anonymisation historic
	 */
	@Override
	public void setUserName(String userName) {
		_anonymisationHistoric.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this anonymisation historic.
	 *
	 * @param userUuid the user uuid of this anonymisation historic
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_anonymisationHistoric.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this anonymisation historic.
	 *
	 * @param uuid the uuid of this anonymisation historic
	 */
	@Override
	public void setUuid(String uuid) {
		_anonymisationHistoric.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric>
			toCacheModel() {

		return _anonymisationHistoric.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric
		toEscapedModel() {

		return new AnonymisationHistoricWrapper(
			_anonymisationHistoric.toEscapedModel());
	}

	@Override
	public String toString() {
		return _anonymisationHistoric.toString();
	}

	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric
		toUnescapedModel() {

		return new AnonymisationHistoricWrapper(
			_anonymisationHistoric.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _anonymisationHistoric.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnonymisationHistoricWrapper)) {
			return false;
		}

		AnonymisationHistoricWrapper anonymisationHistoricWrapper =
			(AnonymisationHistoricWrapper)object;

		if (Objects.equals(
				_anonymisationHistoric,
				anonymisationHistoricWrapper._anonymisationHistoric)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _anonymisationHistoric.getStagedModelType();
	}

	@Override
	public AnonymisationHistoric getWrappedModel() {
		return _anonymisationHistoric;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _anonymisationHistoric.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _anonymisationHistoric.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_anonymisationHistoric.resetOriginalValues();
	}

	private final AnonymisationHistoric _anonymisationHistoric;

}
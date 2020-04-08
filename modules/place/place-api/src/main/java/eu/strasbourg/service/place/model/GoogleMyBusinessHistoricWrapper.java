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

package eu.strasbourg.service.place.model;

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
 * This class is a wrapper for {@link GoogleMyBusinessHistoric}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoric
 * @generated
 */
@ProviderType
public class GoogleMyBusinessHistoricWrapper
	implements GoogleMyBusinessHistoric,
			   ModelWrapper<GoogleMyBusinessHistoric> {

	public GoogleMyBusinessHistoricWrapper(
		GoogleMyBusinessHistoric googleMyBusinessHistoric) {

		_googleMyBusinessHistoric = googleMyBusinessHistoric;
	}

	@Override
	public Class<?> getModelClass() {
		return GoogleMyBusinessHistoric.class;
	}

	@Override
	public String getModelClassName() {
		return GoogleMyBusinessHistoric.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put(
			"googleMyBusinessHistoricId", getGoogleMyBusinessHistoricId());
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

		Long googleMyBusinessHistoricId = (Long)attributes.get(
			"googleMyBusinessHistoricId");

		if (googleMyBusinessHistoricId != null) {
			setGoogleMyBusinessHistoricId(googleMyBusinessHistoricId);
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
	 * Ajout d'une ligne dans le resultat de google mybusiness
	 *
	 * @return
	 */
	@Override
	public void addNewOperation(String operation) {
		_googleMyBusinessHistoric.addNewOperation(operation);
	}

	@Override
	public Object clone() {
		return new GoogleMyBusinessHistoricWrapper(
			(GoogleMyBusinessHistoric)_googleMyBusinessHistoric.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
			googleMyBusinessHistoric) {

		return _googleMyBusinessHistoric.compareTo(googleMyBusinessHistoric);
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _googleMyBusinessHistoric.getAssetEntry();
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories() {

		return _googleMyBusinessHistoric.getCategories();
	}

	/**
	 * Returns the company ID of this google my business historic.
	 *
	 * @return the company ID of this google my business historic
	 */
	@Override
	public long getCompanyId() {
		return _googleMyBusinessHistoric.getCompanyId();
	}

	/**
	 * Returns the create date of this google my business historic.
	 *
	 * @return the create date of this google my business historic
	 */
	@Override
	public Date getCreateDate() {
		return _googleMyBusinessHistoric.getCreateDate();
	}

	/**
	 * Returns the error description of this google my business historic.
	 *
	 * @return the error description of this google my business historic
	 */
	@Override
	public String getErrorDescription() {
		return _googleMyBusinessHistoric.getErrorDescription();
	}

	/**
	 * Returns the error stack trace of this google my business historic.
	 *
	 * @return the error stack trace of this google my business historic
	 */
	@Override
	public String getErrorStackTrace() {
		return _googleMyBusinessHistoric.getErrorStackTrace();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _googleMyBusinessHistoric.getExpandoBridge();
	}

	/**
	 * Returns the finish date of this google my business historic.
	 *
	 * @return the finish date of this google my business historic
	 */
	@Override
	public Date getFinishDate() {
		return _googleMyBusinessHistoric.getFinishDate();
	}

	/**
	 * Returns the google my business historic ID of this google my business historic.
	 *
	 * @return the google my business historic ID of this google my business historic
	 */
	@Override
	public long getGoogleMyBusinessHistoricId() {
		return _googleMyBusinessHistoric.getGoogleMyBusinessHistoricId();
	}

	/**
	 * Returns the group ID of this google my business historic.
	 *
	 * @return the group ID of this google my business historic
	 */
	@Override
	public long getGroupId() {
		return _googleMyBusinessHistoric.getGroupId();
	}

	/**
	 * Returns the last publish date of this google my business historic.
	 *
	 * @return the last publish date of this google my business historic
	 */
	@Override
	public Date getLastPublishDate() {
		return _googleMyBusinessHistoric.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this google my business historic.
	 *
	 * @return the modified date of this google my business historic
	 */
	@Override
	public Date getModifiedDate() {
		return _googleMyBusinessHistoric.getModifiedDate();
	}

	/**
	 * Returns the operations of this google my business historic.
	 *
	 * @return the operations of this google my business historic
	 */
	@Override
	public String getOperations() {
		return _googleMyBusinessHistoric.getOperations();
	}

	/**
	 * Returns the primary key of this google my business historic.
	 *
	 * @return the primary key of this google my business historic
	 */
	@Override
	public long getPrimaryKey() {
		return _googleMyBusinessHistoric.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _googleMyBusinessHistoric.getPrimaryKeyObj();
	}

	/**
	 * Returns the result of this google my business historic.
	 *
	 * @return the result of this google my business historic
	 */
	@Override
	public int getResult() {
		return _googleMyBusinessHistoric.getResult();
	}

	/**
	 * Renvoie le label affichable du resultat de google mybusiness
	 *
	 * @return
	 */
	@Override
	public String getResultLabel() {
		return _googleMyBusinessHistoric.getResultLabel();
	}

	/**
	 * Returns the start date of this google my business historic.
	 *
	 * @return the start date of this google my business historic
	 */
	@Override
	public Date getStartDate() {
		return _googleMyBusinessHistoric.getStartDate();
	}

	/**
	 * Returns the status of this google my business historic.
	 *
	 * @return the status of this google my business historic
	 */
	@Override
	public int getStatus() {
		return _googleMyBusinessHistoric.getStatus();
	}

	/**
	 * Returns the status by user ID of this google my business historic.
	 *
	 * @return the status by user ID of this google my business historic
	 */
	@Override
	public long getStatusByUserId() {
		return _googleMyBusinessHistoric.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this google my business historic.
	 *
	 * @return the status by user name of this google my business historic
	 */
	@Override
	public String getStatusByUserName() {
		return _googleMyBusinessHistoric.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this google my business historic.
	 *
	 * @return the status by user uuid of this google my business historic
	 */
	@Override
	public String getStatusByUserUuid() {
		return _googleMyBusinessHistoric.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this google my business historic.
	 *
	 * @return the status date of this google my business historic
	 */
	@Override
	public Date getStatusDate() {
		return _googleMyBusinessHistoric.getStatusDate();
	}

	/**
	 * Returns the user ID of this google my business historic.
	 *
	 * @return the user ID of this google my business historic
	 */
	@Override
	public long getUserId() {
		return _googleMyBusinessHistoric.getUserId();
	}

	/**
	 * Returns the user name of this google my business historic.
	 *
	 * @return the user name of this google my business historic
	 */
	@Override
	public String getUserName() {
		return _googleMyBusinessHistoric.getUserName();
	}

	/**
	 * Returns the user uuid of this google my business historic.
	 *
	 * @return the user uuid of this google my business historic
	 */
	@Override
	public String getUserUuid() {
		return _googleMyBusinessHistoric.getUserUuid();
	}

	/**
	 * Returns the uuid of this google my business historic.
	 *
	 * @return the uuid of this google my business historic
	 */
	@Override
	public String getUuid() {
		return _googleMyBusinessHistoric.getUuid();
	}

	@Override
	public int hashCode() {
		return _googleMyBusinessHistoric.hashCode();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is approved.
	 *
	 * @return <code>true</code> if this google my business historic is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return _googleMyBusinessHistoric.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _googleMyBusinessHistoric.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is denied.
	 *
	 * @return <code>true</code> if this google my business historic is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return _googleMyBusinessHistoric.isDenied();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is a draft.
	 *
	 * @return <code>true</code> if this google my business historic is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return _googleMyBusinessHistoric.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _googleMyBusinessHistoric.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is expired.
	 *
	 * @return <code>true</code> if this google my business historic is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return _googleMyBusinessHistoric.isExpired();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is inactive.
	 *
	 * @return <code>true</code> if this google my business historic is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return _googleMyBusinessHistoric.isInactive();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is incomplete.
	 *
	 * @return <code>true</code> if this google my business historic is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return _googleMyBusinessHistoric.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _googleMyBusinessHistoric.isNew();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is pending.
	 *
	 * @return <code>true</code> if this google my business historic is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return _googleMyBusinessHistoric.isPending();
	}

	/**
	 * Returns <code>true</code> if this google my business historic is scheduled.
	 *
	 * @return <code>true</code> if this google my business historic is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return _googleMyBusinessHistoric.isScheduled();
	}

	@Override
	public void persist() {
		_googleMyBusinessHistoric.persist();
	}

	/**
	 * Envoi du mail d'envoi
	 */
	@Override
	public void sendMail() {
		_googleMyBusinessHistoric.sendMail();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_googleMyBusinessHistoric.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this google my business historic.
	 *
	 * @param companyId the company ID of this google my business historic
	 */
	@Override
	public void setCompanyId(long companyId) {
		_googleMyBusinessHistoric.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this google my business historic.
	 *
	 * @param createDate the create date of this google my business historic
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_googleMyBusinessHistoric.setCreateDate(createDate);
	}

	/**
	 * Sets the error description of this google my business historic.
	 *
	 * @param errorDescription the error description of this google my business historic
	 */
	@Override
	public void setErrorDescription(String errorDescription) {
		_googleMyBusinessHistoric.setErrorDescription(errorDescription);
	}

	/**
	 * Sets the error stack trace of this google my business historic.
	 *
	 * @param errorStackTrace the error stack trace of this google my business historic
	 */
	@Override
	public void setErrorStackTrace(String errorStackTrace) {
		_googleMyBusinessHistoric.setErrorStackTrace(errorStackTrace);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_googleMyBusinessHistoric.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_googleMyBusinessHistoric.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_googleMyBusinessHistoric.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the finish date of this google my business historic.
	 *
	 * @param finishDate the finish date of this google my business historic
	 */
	@Override
	public void setFinishDate(Date finishDate) {
		_googleMyBusinessHistoric.setFinishDate(finishDate);
	}

	/**
	 * Sets the google my business historic ID of this google my business historic.
	 *
	 * @param googleMyBusinessHistoricId the google my business historic ID of this google my business historic
	 */
	@Override
	public void setGoogleMyBusinessHistoricId(long googleMyBusinessHistoricId) {
		_googleMyBusinessHistoric.setGoogleMyBusinessHistoricId(
			googleMyBusinessHistoricId);
	}

	/**
	 * Sets the group ID of this google my business historic.
	 *
	 * @param groupId the group ID of this google my business historic
	 */
	@Override
	public void setGroupId(long groupId) {
		_googleMyBusinessHistoric.setGroupId(groupId);
	}

	/**
	 * Sets the last publish date of this google my business historic.
	 *
	 * @param lastPublishDate the last publish date of this google my business historic
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_googleMyBusinessHistoric.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this google my business historic.
	 *
	 * @param modifiedDate the modified date of this google my business historic
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_googleMyBusinessHistoric.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_googleMyBusinessHistoric.setNew(n);
	}

	/**
	 * Sets the operations of this google my business historic.
	 *
	 * @param operations the operations of this google my business historic
	 */
	@Override
	public void setOperations(String operations) {
		_googleMyBusinessHistoric.setOperations(operations);
	}

	/**
	 * Sets the primary key of this google my business historic.
	 *
	 * @param primaryKey the primary key of this google my business historic
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_googleMyBusinessHistoric.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_googleMyBusinessHistoric.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the result of this google my business historic.
	 *
	 * @param result the result of this google my business historic
	 */
	@Override
	public void setResult(int result) {
		_googleMyBusinessHistoric.setResult(result);
	}

	/**
	 * Sets the start date of this google my business historic.
	 *
	 * @param startDate the start date of this google my business historic
	 */
	@Override
	public void setStartDate(Date startDate) {
		_googleMyBusinessHistoric.setStartDate(startDate);
	}

	/**
	 * Sets the status of this google my business historic.
	 *
	 * @param status the status of this google my business historic
	 */
	@Override
	public void setStatus(int status) {
		_googleMyBusinessHistoric.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this google my business historic.
	 *
	 * @param statusByUserId the status by user ID of this google my business historic
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_googleMyBusinessHistoric.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this google my business historic.
	 *
	 * @param statusByUserName the status by user name of this google my business historic
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_googleMyBusinessHistoric.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this google my business historic.
	 *
	 * @param statusByUserUuid the status by user uuid of this google my business historic
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_googleMyBusinessHistoric.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this google my business historic.
	 *
	 * @param statusDate the status date of this google my business historic
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		_googleMyBusinessHistoric.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this google my business historic.
	 *
	 * @param userId the user ID of this google my business historic
	 */
	@Override
	public void setUserId(long userId) {
		_googleMyBusinessHistoric.setUserId(userId);
	}

	/**
	 * Sets the user name of this google my business historic.
	 *
	 * @param userName the user name of this google my business historic
	 */
	@Override
	public void setUserName(String userName) {
		_googleMyBusinessHistoric.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this google my business historic.
	 *
	 * @param userUuid the user uuid of this google my business historic
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_googleMyBusinessHistoric.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this google my business historic.
	 *
	 * @param uuid the uuid of this google my business historic
	 */
	@Override
	public void setUuid(String uuid) {
		_googleMyBusinessHistoric.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.place.model.GoogleMyBusinessHistoric>
			toCacheModel() {

		return _googleMyBusinessHistoric.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		toEscapedModel() {

		return new GoogleMyBusinessHistoricWrapper(
			_googleMyBusinessHistoric.toEscapedModel());
	}

	@Override
	public String toString() {
		return _googleMyBusinessHistoric.toString();
	}

	@Override
	public eu.strasbourg.service.place.model.GoogleMyBusinessHistoric
		toUnescapedModel() {

		return new GoogleMyBusinessHistoricWrapper(
			_googleMyBusinessHistoric.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _googleMyBusinessHistoric.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GoogleMyBusinessHistoricWrapper)) {
			return false;
		}

		GoogleMyBusinessHistoricWrapper googleMyBusinessHistoricWrapper =
			(GoogleMyBusinessHistoricWrapper)obj;

		if (Objects.equals(
				_googleMyBusinessHistoric,
				googleMyBusinessHistoricWrapper._googleMyBusinessHistoric)) {

			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _googleMyBusinessHistoric.getStagedModelType();
	}

	@Override
	public GoogleMyBusinessHistoric getWrappedModel() {
		return _googleMyBusinessHistoric;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _googleMyBusinessHistoric.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _googleMyBusinessHistoric.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_googleMyBusinessHistoric.resetOriginalValues();
	}

	private final GoogleMyBusinessHistoric _googleMyBusinessHistoric;

}
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
 * This class is a wrapper for {@link FormSendRecordFieldSignalement}.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalement
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementWrapper
	implements FormSendRecordFieldSignalement,
		ModelWrapper<FormSendRecordFieldSignalement> {
	public FormSendRecordFieldSignalementWrapper(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		_formSendRecordFieldSignalement = formSendRecordFieldSignalement;
	}

	@Override
	public Class<?> getModelClass() {
		return FormSendRecordFieldSignalement.class;
	}

	@Override
	public String getModelClassName() {
		return FormSendRecordFieldSignalement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("signalementId", getSignalementId());
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
		attributes.put("formSendRecordFieldId", getFormSendRecordFieldId());
		attributes.put("publikId", getPublikId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long signalementId = (Long)attributes.get("signalementId");

		if (signalementId != null) {
			setSignalementId(signalementId);
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

		Long formSendRecordFieldId = (Long)attributes.get(
				"formSendRecordFieldId");

		if (formSendRecordFieldId != null) {
			setFormSendRecordFieldId(formSendRecordFieldId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is approved.
	*
	* @return <code>true</code> if this form send record field signalement is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _formSendRecordFieldSignalement.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _formSendRecordFieldSignalement.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is denied.
	*
	* @return <code>true</code> if this form send record field signalement is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _formSendRecordFieldSignalement.isDenied();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is a draft.
	*
	* @return <code>true</code> if this form send record field signalement is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _formSendRecordFieldSignalement.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _formSendRecordFieldSignalement.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is expired.
	*
	* @return <code>true</code> if this form send record field signalement is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _formSendRecordFieldSignalement.isExpired();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is inactive.
	*
	* @return <code>true</code> if this form send record field signalement is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _formSendRecordFieldSignalement.isInactive();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is incomplete.
	*
	* @return <code>true</code> if this form send record field signalement is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _formSendRecordFieldSignalement.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _formSendRecordFieldSignalement.isNew();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is pending.
	*
	* @return <code>true</code> if this form send record field signalement is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _formSendRecordFieldSignalement.isPending();
	}

	/**
	* Returns <code>true</code> if this form send record field signalement is scheduled.
	*
	* @return <code>true</code> if this form send record field signalement is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _formSendRecordFieldSignalement.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _formSendRecordFieldSignalement.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> toCacheModel() {
		return _formSendRecordFieldSignalement.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement toEscapedModel() {
		return new FormSendRecordFieldSignalementWrapper(_formSendRecordFieldSignalement.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement toUnescapedModel() {
		return new FormSendRecordFieldSignalementWrapper(_formSendRecordFieldSignalement.toUnescapedModel());
	}

	/**
	* Retourne l'utilisateur auteur du signalement
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getSignalementAuthor() {
		return _formSendRecordFieldSignalement.getSignalementAuthor();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		return _formSendRecordFieldSignalement.compareTo(formSendRecordFieldSignalement);
	}

	/**
	* Returns the status of this form send record field signalement.
	*
	* @return the status of this form send record field signalement
	*/
	@Override
	public int getStatus() {
		return _formSendRecordFieldSignalement.getStatus();
	}

	@Override
	public int hashCode() {
		return _formSendRecordFieldSignalement.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formSendRecordFieldSignalement.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FormSendRecordFieldSignalementWrapper((FormSendRecordFieldSignalement)_formSendRecordFieldSignalement.clone());
	}

	@Override
	public java.lang.String getCategorieName() {
		return _formSendRecordFieldSignalement.getCategorieName();
	}

	/**
	* Returns the publik ID of this form send record field signalement.
	*
	* @return the publik ID of this form send record field signalement
	*/
	@Override
	public java.lang.String getPublikId() {
		return _formSendRecordFieldSignalement.getPublikId();
	}

	/**
	* Retourne le nom de l'auteur du signalement
	*/
	@Override
	public java.lang.String getSignalementAuthorLabel() {
		return _formSendRecordFieldSignalement.getSignalementAuthorLabel();
	}

	/**
	* Returns the status by user name of this form send record field signalement.
	*
	* @return the status by user name of this form send record field signalement
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _formSendRecordFieldSignalement.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this form send record field signalement.
	*
	* @return the status by user uuid of this form send record field signalement
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _formSendRecordFieldSignalement.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this form send record field signalement.
	*
	* @return the user name of this form send record field signalement
	*/
	@Override
	public java.lang.String getUserName() {
		return _formSendRecordFieldSignalement.getUserName();
	}

	/**
	* Returns the user uuid of this form send record field signalement.
	*
	* @return the user uuid of this form send record field signalement
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _formSendRecordFieldSignalement.getUserUuid();
	}

	/**
	* Returns the uuid of this form send record field signalement.
	*
	* @return the uuid of this form send record field signalement
	*/
	@Override
	public java.lang.String getUuid() {
		return _formSendRecordFieldSignalement.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _formSendRecordFieldSignalement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _formSendRecordFieldSignalement.toXmlString();
	}

	/**
	* Returns the create date of this form send record field signalement.
	*
	* @return the create date of this form send record field signalement
	*/
	@Override
	public Date getCreateDate() {
		return _formSendRecordFieldSignalement.getCreateDate();
	}

	/**
	* Returns the modified date of this form send record field signalement.
	*
	* @return the modified date of this form send record field signalement
	*/
	@Override
	public Date getModifiedDate() {
		return _formSendRecordFieldSignalement.getModifiedDate();
	}

	/**
	* Returns the status date of this form send record field signalement.
	*
	* @return the status date of this form send record field signalement
	*/
	@Override
	public Date getStatusDate() {
		return _formSendRecordFieldSignalement.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategoriesByAssetEntry() {
		return _formSendRecordFieldSignalement.getCategoriesByAssetEntry();
	}

	/**
	* Returns the company ID of this form send record field signalement.
	*
	* @return the company ID of this form send record field signalement
	*/
	@Override
	public long getCompanyId() {
		return _formSendRecordFieldSignalement.getCompanyId();
	}

	/**
	* Returns the form send record field ID of this form send record field signalement.
	*
	* @return the form send record field ID of this form send record field signalement
	*/
	@Override
	public long getFormSendRecordFieldId() {
		return _formSendRecordFieldSignalement.getFormSendRecordFieldId();
	}

	/**
	* Returns the group ID of this form send record field signalement.
	*
	* @return the group ID of this form send record field signalement
	*/
	@Override
	public long getGroupId() {
		return _formSendRecordFieldSignalement.getGroupId();
	}

	/**
	* Returns the primary key of this form send record field signalement.
	*
	* @return the primary key of this form send record field signalement
	*/
	@Override
	public long getPrimaryKey() {
		return _formSendRecordFieldSignalement.getPrimaryKey();
	}

	/**
	* Returns the signalement ID of this form send record field signalement.
	*
	* @return the signalement ID of this form send record field signalement
	*/
	@Override
	public long getSignalementId() {
		return _formSendRecordFieldSignalement.getSignalementId();
	}

	/**
	* Returns the status by user ID of this form send record field signalement.
	*
	* @return the status by user ID of this form send record field signalement
	*/
	@Override
	public long getStatusByUserId() {
		return _formSendRecordFieldSignalement.getStatusByUserId();
	}

	/**
	* Returns the user ID of this form send record field signalement.
	*
	* @return the user ID of this form send record field signalement
	*/
	@Override
	public long getUserId() {
		return _formSendRecordFieldSignalement.getUserId();
	}

	@Override
	public void persist() {
		_formSendRecordFieldSignalement.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_formSendRecordFieldSignalement.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this form send record field signalement.
	*
	* @param companyId the company ID of this form send record field signalement
	*/
	@Override
	public void setCompanyId(long companyId) {
		_formSendRecordFieldSignalement.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this form send record field signalement.
	*
	* @param createDate the create date of this form send record field signalement
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_formSendRecordFieldSignalement.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_formSendRecordFieldSignalement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_formSendRecordFieldSignalement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_formSendRecordFieldSignalement.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the form send record field ID of this form send record field signalement.
	*
	* @param formSendRecordFieldId the form send record field ID of this form send record field signalement
	*/
	@Override
	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_formSendRecordFieldSignalement.setFormSendRecordFieldId(formSendRecordFieldId);
	}

	/**
	* Sets the group ID of this form send record field signalement.
	*
	* @param groupId the group ID of this form send record field signalement
	*/
	@Override
	public void setGroupId(long groupId) {
		_formSendRecordFieldSignalement.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this form send record field signalement.
	*
	* @param modifiedDate the modified date of this form send record field signalement
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_formSendRecordFieldSignalement.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_formSendRecordFieldSignalement.setNew(n);
	}

	/**
	* Sets the primary key of this form send record field signalement.
	*
	* @param primaryKey the primary key of this form send record field signalement
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_formSendRecordFieldSignalement.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_formSendRecordFieldSignalement.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik ID of this form send record field signalement.
	*
	* @param publikId the publik ID of this form send record field signalement
	*/
	@Override
	public void setPublikId(java.lang.String publikId) {
		_formSendRecordFieldSignalement.setPublikId(publikId);
	}

	/**
	* Sets the signalement ID of this form send record field signalement.
	*
	* @param signalementId the signalement ID of this form send record field signalement
	*/
	@Override
	public void setSignalementId(long signalementId) {
		_formSendRecordFieldSignalement.setSignalementId(signalementId);
	}

	/**
	* Sets the status of this form send record field signalement.
	*
	* @param status the status of this form send record field signalement
	*/
	@Override
	public void setStatus(int status) {
		_formSendRecordFieldSignalement.setStatus(status);
	}

	/**
	* Sets the status by user ID of this form send record field signalement.
	*
	* @param statusByUserId the status by user ID of this form send record field signalement
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_formSendRecordFieldSignalement.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this form send record field signalement.
	*
	* @param statusByUserName the status by user name of this form send record field signalement
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_formSendRecordFieldSignalement.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this form send record field signalement.
	*
	* @param statusByUserUuid the status by user uuid of this form send record field signalement
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_formSendRecordFieldSignalement.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this form send record field signalement.
	*
	* @param statusDate the status date of this form send record field signalement
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_formSendRecordFieldSignalement.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this form send record field signalement.
	*
	* @param userId the user ID of this form send record field signalement
	*/
	@Override
	public void setUserId(long userId) {
		_formSendRecordFieldSignalement.setUserId(userId);
	}

	/**
	* Sets the user name of this form send record field signalement.
	*
	* @param userName the user name of this form send record field signalement
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_formSendRecordFieldSignalement.setUserName(userName);
	}

	/**
	* Sets the user uuid of this form send record field signalement.
	*
	* @param userUuid the user uuid of this form send record field signalement
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_formSendRecordFieldSignalement.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this form send record field signalement.
	*
	* @param uuid the uuid of this form send record field signalement
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_formSendRecordFieldSignalement.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormSendRecordFieldSignalementWrapper)) {
			return false;
		}

		FormSendRecordFieldSignalementWrapper formSendRecordFieldSignalementWrapper =
			(FormSendRecordFieldSignalementWrapper)obj;

		if (Objects.equals(_formSendRecordFieldSignalement,
					formSendRecordFieldSignalementWrapper._formSendRecordFieldSignalement)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _formSendRecordFieldSignalement.getStagedModelType();
	}

	@Override
	public FormSendRecordFieldSignalement getWrappedModel() {
		return _formSendRecordFieldSignalement;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _formSendRecordFieldSignalement.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _formSendRecordFieldSignalement.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_formSendRecordFieldSignalement.resetOriginalValues();
	}

	private final FormSendRecordFieldSignalement _formSendRecordFieldSignalement;
}
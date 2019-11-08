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
 * This class is a wrapper for {@link FormSendRecordField}.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordField
 * @generated
 */
@ProviderType
public class FormSendRecordFieldWrapper implements FormSendRecordField,
	ModelWrapper<FormSendRecordField> {
	public FormSendRecordFieldWrapper(FormSendRecordField formSendRecordField) {
		_formSendRecordField = formSendRecordField;
	}

	@Override
	public Class<?> getModelClass() {
		return FormSendRecordField.class;
	}

	@Override
	public String getModelClassName() {
		return FormSendRecordField.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("formSendRecordFieldId", getFormSendRecordFieldId());
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
		attributes.put("response", getResponse());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("contentId", getContentId());
		attributes.put("instanceId", getInstanceId());
		attributes.put("responseUserId", getResponseUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long formSendRecordFieldId = (Long)attributes.get(
				"formSendRecordFieldId");

		if (formSendRecordFieldId != null) {
			setFormSendRecordFieldId(formSendRecordFieldId);
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

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long contentId = (Long)attributes.get("contentId");

		if (contentId != null) {
			setContentId(contentId);
		}

		String instanceId = (String)attributes.get("instanceId");

		if (instanceId != null) {
			setInstanceId(instanceId);
		}

		Long responseUserId = (Long)attributes.get("responseUserId");

		if (responseUserId != null) {
			setResponseUserId(responseUserId);
		}
	}

	/**
	* Returns <code>true</code> if this form send record field is approved.
	*
	* @return <code>true</code> if this form send record field is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _formSendRecordField.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _formSendRecordField.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this form send record field is denied.
	*
	* @return <code>true</code> if this form send record field is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _formSendRecordField.isDenied();
	}

	/**
	* Returns <code>true</code> if this form send record field is a draft.
	*
	* @return <code>true</code> if this form send record field is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _formSendRecordField.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _formSendRecordField.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this form send record field is expired.
	*
	* @return <code>true</code> if this form send record field is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _formSendRecordField.isExpired();
	}

	/**
	* Returns <code>true</code> if this form send record field is inactive.
	*
	* @return <code>true</code> if this form send record field is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _formSendRecordField.isInactive();
	}

	/**
	* Returns <code>true</code> if this form send record field is incomplete.
	*
	* @return <code>true</code> if this form send record field is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _formSendRecordField.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _formSendRecordField.isNew();
	}

	/**
	* Returns <code>true</code> if this form send record field is pending.
	*
	* @return <code>true</code> if this form send record field is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _formSendRecordField.isPending();
	}

	/**
	* Returns <code>true</code> if this form send record field is scheduled.
	*
	* @return <code>true</code> if this form send record field is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _formSendRecordField.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _formSendRecordField.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _formSendRecordField.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> toCacheModel() {
		return _formSendRecordField.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField toEscapedModel() {
		return new FormSendRecordFieldWrapper(_formSendRecordField.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField toUnescapedModel() {
		return new FormSendRecordFieldWrapper(_formSendRecordField.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField) {
		return _formSendRecordField.compareTo(formSendRecordField);
	}

	/**
	* Retourne le nombre de dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public int getNbDislikes() {
		return _formSendRecordField.getNbDislikes();
	}

	/**
	* Retourne le nombre de likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public int getNbLikes() {
		return _formSendRecordField.getNbLikes();
	}

	/**
	* Returns the status of this form send record field.
	*
	* @return the status of this form send record field
	*/
	@Override
	public int getStatus() {
		return _formSendRecordField.getStatus();
	}

	@Override
	public int hashCode() {
		return _formSendRecordField.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formSendRecordField.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FormSendRecordFieldWrapper((FormSendRecordField)_formSendRecordField.clone());
	}

	/**
	* Returns the instance ID of this form send record field.
	*
	* @return the instance ID of this form send record field
	*/
	@Override
	public java.lang.String getInstanceId() {
		return _formSendRecordField.getInstanceId();
	}

	/**
	* Returns the response of this form send record field.
	*
	* @return the response of this form send record field
	*/
	@Override
	public java.lang.String getResponse() {
		return _formSendRecordField.getResponse();
	}

	/**
	* Returns the response user uuid of this form send record field.
	*
	* @return the response user uuid of this form send record field
	*/
	@Override
	public java.lang.String getResponseUserUuid() {
		return _formSendRecordField.getResponseUserUuid();
	}

	/**
	* Returns the status by user name of this form send record field.
	*
	* @return the status by user name of this form send record field
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _formSendRecordField.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this form send record field.
	*
	* @return the status by user uuid of this form send record field
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _formSendRecordField.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this form send record field.
	*
	* @return the user name of this form send record field
	*/
	@Override
	public java.lang.String getUserName() {
		return _formSendRecordField.getUserName();
	}

	/**
	* Returns the user uuid of this form send record field.
	*
	* @return the user uuid of this form send record field
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _formSendRecordField.getUserUuid();
	}

	/**
	* Returns the uuid of this form send record field.
	*
	* @return the uuid of this form send record field
	*/
	@Override
	public java.lang.String getUuid() {
		return _formSendRecordField.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _formSendRecordField.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _formSendRecordField.toXmlString();
	}

	/**
	* Returns the create date of this form send record field.
	*
	* @return the create date of this form send record field
	*/
	@Override
	public Date getCreateDate() {
		return _formSendRecordField.getCreateDate();
	}

	/**
	* Returns the modified date of this form send record field.
	*
	* @return the modified date of this form send record field
	*/
	@Override
	public Date getModifiedDate() {
		return _formSendRecordField.getModifiedDate();
	}

	/**
	* Returns the status date of this form send record field.
	*
	* @return the status date of this form send record field
	*/
	@Override
	public Date getStatusDate() {
		return _formSendRecordField.getStatusDate();
	}

	/**
	* Retourne la liste des likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getLikes() {
		return _formSendRecordField.getLikes();
	}

	/**
	* Returns the asset entry ID of this form send record field.
	*
	* @return the asset entry ID of this form send record field
	*/
	@Override
	public long getAssetEntryId() {
		return _formSendRecordField.getAssetEntryId();
	}

	/**
	* Returns the company ID of this form send record field.
	*
	* @return the company ID of this form send record field
	*/
	@Override
	public long getCompanyId() {
		return _formSendRecordField.getCompanyId();
	}

	/**
	* Returns the content ID of this form send record field.
	*
	* @return the content ID of this form send record field
	*/
	@Override
	public long getContentId() {
		return _formSendRecordField.getContentId();
	}

	/**
	* Returns the form send record field ID of this form send record field.
	*
	* @return the form send record field ID of this form send record field
	*/
	@Override
	public long getFormSendRecordFieldId() {
		return _formSendRecordField.getFormSendRecordFieldId();
	}

	/**
	* Returns the group ID of this form send record field.
	*
	* @return the group ID of this form send record field
	*/
	@Override
	public long getGroupId() {
		return _formSendRecordField.getGroupId();
	}

	/**
	* Returns the primary key of this form send record field.
	*
	* @return the primary key of this form send record field
	*/
	@Override
	public long getPrimaryKey() {
		return _formSendRecordField.getPrimaryKey();
	}

	/**
	* Returns the response user ID of this form send record field.
	*
	* @return the response user ID of this form send record field
	*/
	@Override
	public long getResponseUserId() {
		return _formSendRecordField.getResponseUserId();
	}

	/**
	* Returns the status by user ID of this form send record field.
	*
	* @return the status by user ID of this form send record field
	*/
	@Override
	public long getStatusByUserId() {
		return _formSendRecordField.getStatusByUserId();
	}

	/**
	* Returns the user ID of this form send record field.
	*
	* @return the user ID of this form send record field
	*/
	@Override
	public long getUserId() {
		return _formSendRecordField.getUserId();
	}

	@Override
	public void persist() {
		_formSendRecordField.persist();
	}

	/**
	* Sets the asset entry ID of this form send record field.
	*
	* @param assetEntryId the asset entry ID of this form send record field
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_formSendRecordField.setAssetEntryId(assetEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_formSendRecordField.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this form send record field.
	*
	* @param companyId the company ID of this form send record field
	*/
	@Override
	public void setCompanyId(long companyId) {
		_formSendRecordField.setCompanyId(companyId);
	}

	/**
	* Sets the content ID of this form send record field.
	*
	* @param contentId the content ID of this form send record field
	*/
	@Override
	public void setContentId(long contentId) {
		_formSendRecordField.setContentId(contentId);
	}

	/**
	* Sets the create date of this form send record field.
	*
	* @param createDate the create date of this form send record field
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_formSendRecordField.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_formSendRecordField.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_formSendRecordField.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_formSendRecordField.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the form send record field ID of this form send record field.
	*
	* @param formSendRecordFieldId the form send record field ID of this form send record field
	*/
	@Override
	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_formSendRecordField.setFormSendRecordFieldId(formSendRecordFieldId);
	}

	/**
	* Sets the group ID of this form send record field.
	*
	* @param groupId the group ID of this form send record field
	*/
	@Override
	public void setGroupId(long groupId) {
		_formSendRecordField.setGroupId(groupId);
	}

	/**
	* Sets the instance ID of this form send record field.
	*
	* @param instanceId the instance ID of this form send record field
	*/
	@Override
	public void setInstanceId(java.lang.String instanceId) {
		_formSendRecordField.setInstanceId(instanceId);
	}

	/**
	* Sets the modified date of this form send record field.
	*
	* @param modifiedDate the modified date of this form send record field
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_formSendRecordField.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_formSendRecordField.setNew(n);
	}

	/**
	* Sets the primary key of this form send record field.
	*
	* @param primaryKey the primary key of this form send record field
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_formSendRecordField.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_formSendRecordField.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the response of this form send record field.
	*
	* @param response the response of this form send record field
	*/
	@Override
	public void setResponse(java.lang.String response) {
		_formSendRecordField.setResponse(response);
	}

	/**
	* Sets the response user ID of this form send record field.
	*
	* @param responseUserId the response user ID of this form send record field
	*/
	@Override
	public void setResponseUserId(long responseUserId) {
		_formSendRecordField.setResponseUserId(responseUserId);
	}

	/**
	* Sets the response user uuid of this form send record field.
	*
	* @param responseUserUuid the response user uuid of this form send record field
	*/
	@Override
	public void setResponseUserUuid(java.lang.String responseUserUuid) {
		_formSendRecordField.setResponseUserUuid(responseUserUuid);
	}

	/**
	* Sets the status of this form send record field.
	*
	* @param status the status of this form send record field
	*/
	@Override
	public void setStatus(int status) {
		_formSendRecordField.setStatus(status);
	}

	/**
	* Sets the status by user ID of this form send record field.
	*
	* @param statusByUserId the status by user ID of this form send record field
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_formSendRecordField.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this form send record field.
	*
	* @param statusByUserName the status by user name of this form send record field
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_formSendRecordField.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this form send record field.
	*
	* @param statusByUserUuid the status by user uuid of this form send record field
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_formSendRecordField.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this form send record field.
	*
	* @param statusDate the status date of this form send record field
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_formSendRecordField.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this form send record field.
	*
	* @param userId the user ID of this form send record field
	*/
	@Override
	public void setUserId(long userId) {
		_formSendRecordField.setUserId(userId);
	}

	/**
	* Sets the user name of this form send record field.
	*
	* @param userName the user name of this form send record field
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_formSendRecordField.setUserName(userName);
	}

	/**
	* Sets the user uuid of this form send record field.
	*
	* @param userUuid the user uuid of this form send record field
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_formSendRecordField.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this form send record field.
	*
	* @param uuid the uuid of this form send record field
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_formSendRecordField.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormSendRecordFieldWrapper)) {
			return false;
		}

		FormSendRecordFieldWrapper formSendRecordFieldWrapper = (FormSendRecordFieldWrapper)obj;

		if (Objects.equals(_formSendRecordField,
					formSendRecordFieldWrapper._formSendRecordField)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _formSendRecordField.getStagedModelType();
	}

	@Override
	public FormSendRecordField getWrappedModel() {
		return _formSendRecordField;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _formSendRecordField.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _formSendRecordField.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_formSendRecordField.resetOriginalValues();
	}

	private final FormSendRecordField _formSendRecordField;
}
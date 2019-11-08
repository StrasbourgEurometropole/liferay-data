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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the FormSendRecordField service. Represents a row in the &quot;FormSendRecordField_FormSendRecordField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl}.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordField
 * @see eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl
 * @see eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl
 * @generated
 */
@ProviderType
public interface FormSendRecordFieldModel extends BaseModel<FormSendRecordField>,
	GroupedModel, ShardedModel, StagedAuditedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a form send record field model instance should use the {@link FormSendRecordField} interface instead.
	 */

	/**
	 * Returns the primary key of this form send record field.
	 *
	 * @return the primary key of this form send record field
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this form send record field.
	 *
	 * @param primaryKey the primary key of this form send record field
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this form send record field.
	 *
	 * @return the uuid of this form send record field
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this form send record field.
	 *
	 * @param uuid the uuid of this form send record field
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the form send record field ID of this form send record field.
	 *
	 * @return the form send record field ID of this form send record field
	 */
	public long getFormSendRecordFieldId();

	/**
	 * Sets the form send record field ID of this form send record field.
	 *
	 * @param formSendRecordFieldId the form send record field ID of this form send record field
	 */
	public void setFormSendRecordFieldId(long formSendRecordFieldId);

	/**
	 * Returns the group ID of this form send record field.
	 *
	 * @return the group ID of this form send record field
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this form send record field.
	 *
	 * @param groupId the group ID of this form send record field
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this form send record field.
	 *
	 * @return the company ID of this form send record field
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this form send record field.
	 *
	 * @param companyId the company ID of this form send record field
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this form send record field.
	 *
	 * @return the user ID of this form send record field
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this form send record field.
	 *
	 * @param userId the user ID of this form send record field
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this form send record field.
	 *
	 * @return the user uuid of this form send record field
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this form send record field.
	 *
	 * @param userUuid the user uuid of this form send record field
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this form send record field.
	 *
	 * @return the user name of this form send record field
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this form send record field.
	 *
	 * @param userName the user name of this form send record field
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this form send record field.
	 *
	 * @return the create date of this form send record field
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this form send record field.
	 *
	 * @param createDate the create date of this form send record field
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this form send record field.
	 *
	 * @return the modified date of this form send record field
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this form send record field.
	 *
	 * @param modifiedDate the modified date of this form send record field
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the status of this form send record field.
	 *
	 * @return the status of this form send record field
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this form send record field.
	 *
	 * @param status the status of this form send record field
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this form send record field.
	 *
	 * @return the status by user ID of this form send record field
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this form send record field.
	 *
	 * @param statusByUserId the status by user ID of this form send record field
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this form send record field.
	 *
	 * @return the status by user uuid of this form send record field
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this form send record field.
	 *
	 * @param statusByUserUuid the status by user uuid of this form send record field
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this form send record field.
	 *
	 * @return the status by user name of this form send record field
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this form send record field.
	 *
	 * @param statusByUserName the status by user name of this form send record field
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this form send record field.
	 *
	 * @return the status date of this form send record field
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this form send record field.
	 *
	 * @param statusDate the status date of this form send record field
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the response of this form send record field.
	 *
	 * @return the response of this form send record field
	 */
	@AutoEscape
	public String getResponse();

	/**
	 * Sets the response of this form send record field.
	 *
	 * @param response the response of this form send record field
	 */
	public void setResponse(String response);

	/**
	 * Returns the asset entry ID of this form send record field.
	 *
	 * @return the asset entry ID of this form send record field
	 */
	public long getAssetEntryId();

	/**
	 * Sets the asset entry ID of this form send record field.
	 *
	 * @param assetEntryId the asset entry ID of this form send record field
	 */
	public void setAssetEntryId(long assetEntryId);

	/**
	 * Returns the content ID of this form send record field.
	 *
	 * @return the content ID of this form send record field
	 */
	public long getContentId();

	/**
	 * Sets the content ID of this form send record field.
	 *
	 * @param contentId the content ID of this form send record field
	 */
	public void setContentId(long contentId);

	/**
	 * Returns the instance ID of this form send record field.
	 *
	 * @return the instance ID of this form send record field
	 */
	@AutoEscape
	public String getInstanceId();

	/**
	 * Sets the instance ID of this form send record field.
	 *
	 * @param instanceId the instance ID of this form send record field
	 */
	public void setInstanceId(String instanceId);

	/**
	 * Returns the response user ID of this form send record field.
	 *
	 * @return the response user ID of this form send record field
	 */
	public long getResponseUserId();

	/**
	 * Sets the response user ID of this form send record field.
	 *
	 * @param responseUserId the response user ID of this form send record field
	 */
	public void setResponseUserId(long responseUserId);

	/**
	 * Returns the response user uuid of this form send record field.
	 *
	 * @return the response user uuid of this form send record field
	 */
	public String getResponseUserUuid();

	/**
	 * Sets the response user uuid of this form send record field.
	 *
	 * @param responseUserUuid the response user uuid of this form send record field
	 */
	public void setResponseUserUuid(String responseUserUuid);

	/**
	 * Returns <code>true</code> if this form send record field is approved.
	 *
	 * @return <code>true</code> if this form send record field is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this form send record field is denied.
	 *
	 * @return <code>true</code> if this form send record field is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this form send record field is a draft.
	 *
	 * @return <code>true</code> if this form send record field is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this form send record field is expired.
	 *
	 * @return <code>true</code> if this form send record field is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this form send record field is inactive.
	 *
	 * @return <code>true</code> if this form send record field is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this form send record field is incomplete.
	 *
	 * @return <code>true</code> if this form send record field is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this form send record field is pending.
	 *
	 * @return <code>true</code> if this form send record field is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this form send record field is scheduled.
	 *
	 * @return <code>true</code> if this form send record field is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> toCacheModel();

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField toEscapedModel();

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
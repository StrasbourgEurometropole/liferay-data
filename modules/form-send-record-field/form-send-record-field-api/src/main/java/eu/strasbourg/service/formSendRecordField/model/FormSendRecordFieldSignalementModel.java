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
 * The base model interface for the FormSendRecordFieldSignalement service. Represents a row in the &quot;formSendRecordField_FormSendRecordFieldSignalement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementImpl</code>.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalement
 * @generated
 */
@ProviderType
public interface FormSendRecordFieldSignalementModel
	extends BaseModel<FormSendRecordFieldSignalement>, GroupedModel,
			ShardedModel, StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a form send record field signalement model instance should use the {@link FormSendRecordFieldSignalement} interface instead.
	 */

	/**
	 * Returns the primary key of this form send record field signalement.
	 *
	 * @return the primary key of this form send record field signalement
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this form send record field signalement.
	 *
	 * @param primaryKey the primary key of this form send record field signalement
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this form send record field signalement.
	 *
	 * @return the uuid of this form send record field signalement
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this form send record field signalement.
	 *
	 * @param uuid the uuid of this form send record field signalement
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the signalement ID of this form send record field signalement.
	 *
	 * @return the signalement ID of this form send record field signalement
	 */
	public long getSignalementId();

	/**
	 * Sets the signalement ID of this form send record field signalement.
	 *
	 * @param signalementId the signalement ID of this form send record field signalement
	 */
	public void setSignalementId(long signalementId);

	/**
	 * Returns the group ID of this form send record field signalement.
	 *
	 * @return the group ID of this form send record field signalement
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this form send record field signalement.
	 *
	 * @param groupId the group ID of this form send record field signalement
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this form send record field signalement.
	 *
	 * @return the company ID of this form send record field signalement
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this form send record field signalement.
	 *
	 * @param companyId the company ID of this form send record field signalement
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this form send record field signalement.
	 *
	 * @return the user ID of this form send record field signalement
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this form send record field signalement.
	 *
	 * @param userId the user ID of this form send record field signalement
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this form send record field signalement.
	 *
	 * @return the user uuid of this form send record field signalement
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this form send record field signalement.
	 *
	 * @param userUuid the user uuid of this form send record field signalement
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this form send record field signalement.
	 *
	 * @return the user name of this form send record field signalement
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this form send record field signalement.
	 *
	 * @param userName the user name of this form send record field signalement
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this form send record field signalement.
	 *
	 * @return the create date of this form send record field signalement
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this form send record field signalement.
	 *
	 * @param createDate the create date of this form send record field signalement
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this form send record field signalement.
	 *
	 * @return the modified date of this form send record field signalement
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this form send record field signalement.
	 *
	 * @param modifiedDate the modified date of this form send record field signalement
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the status of this form send record field signalement.
	 *
	 * @return the status of this form send record field signalement
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this form send record field signalement.
	 *
	 * @param status the status of this form send record field signalement
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this form send record field signalement.
	 *
	 * @return the status by user ID of this form send record field signalement
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this form send record field signalement.
	 *
	 * @param statusByUserId the status by user ID of this form send record field signalement
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this form send record field signalement.
	 *
	 * @return the status by user uuid of this form send record field signalement
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this form send record field signalement.
	 *
	 * @param statusByUserUuid the status by user uuid of this form send record field signalement
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this form send record field signalement.
	 *
	 * @return the status by user name of this form send record field signalement
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this form send record field signalement.
	 *
	 * @param statusByUserName the status by user name of this form send record field signalement
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this form send record field signalement.
	 *
	 * @return the status date of this form send record field signalement
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this form send record field signalement.
	 *
	 * @param statusDate the status date of this form send record field signalement
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the form send record field ID of this form send record field signalement.
	 *
	 * @return the form send record field ID of this form send record field signalement
	 */
	public long getFormSendRecordFieldId();

	/**
	 * Sets the form send record field ID of this form send record field signalement.
	 *
	 * @param formSendRecordFieldId the form send record field ID of this form send record field signalement
	 */
	public void setFormSendRecordFieldId(long formSendRecordFieldId);

	/**
	 * Returns the publik ID of this form send record field signalement.
	 *
	 * @return the publik ID of this form send record field signalement
	 */
	@AutoEscape
	public String getPublikId();

	/**
	 * Sets the publik ID of this form send record field signalement.
	 *
	 * @param publikId the publik ID of this form send record field signalement
	 */
	public void setPublikId(String publikId);

	/**
	 * Returns <code>true</code> if this form send record field signalement is approved.
	 *
	 * @return <code>true</code> if this form send record field signalement is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this form send record field signalement is denied.
	 *
	 * @return <code>true</code> if this form send record field signalement is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this form send record field signalement is a draft.
	 *
	 * @return <code>true</code> if this form send record field signalement is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this form send record field signalement is expired.
	 *
	 * @return <code>true</code> if this form send record field signalement is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this form send record field signalement is inactive.
	 *
	 * @return <code>true</code> if this form send record field signalement is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this form send record field signalement is incomplete.
	 *
	 * @return <code>true</code> if this form send record field signalement is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this form send record field signalement is pending.
	 *
	 * @return <code>true</code> if this form send record field signalement is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this form send record field signalement is scheduled.
	 *
	 * @return <code>true</code> if this form send record field signalement is scheduled; <code>false</code> otherwise
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
		eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement formSendRecordFieldSignalement);

	@Override
	public int hashCode();

	@Override
	public CacheModel
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement> toCacheModel();

	@Override
	public eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement toEscapedModel();

	@Override
	public eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}
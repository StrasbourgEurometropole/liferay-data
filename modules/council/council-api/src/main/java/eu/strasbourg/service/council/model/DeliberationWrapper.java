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

package eu.strasbourg.service.council.model;

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
 * This class is a wrapper for {@link Deliberation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Deliberation
 * @generated
 */
@ProviderType
public class DeliberationWrapper implements Deliberation,
	ModelWrapper<Deliberation> {
	public DeliberationWrapper(Deliberation deliberation) {
		_deliberation = deliberation;
	}

	@Override
	public Class<?> getModelClass() {
		return Deliberation.class;
	}

	@Override
	public String getModelClassName() {
		return Deliberation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("deliberationId", getDeliberationId());
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
		attributes.put("title", getTitle());
		attributes.put("order", getOrder());
		attributes.put("text", getText());
		attributes.put("docId", getDocId());
		attributes.put("status", getStatus());
		attributes.put("sessionId", getSessionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long deliberationId = (Long)attributes.get("deliberationId");

		if (deliberationId != null) {
			setDeliberationId(deliberationId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}

		String docId = (String)attributes.get("docId");

		if (docId != null) {
			setDocId(docId);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	/**
	* Returns <code>true</code> if this deliberation is approved.
	*
	* @return <code>true</code> if this deliberation is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _deliberation.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _deliberation.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this deliberation is denied.
	*
	* @return <code>true</code> if this deliberation is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _deliberation.isDenied();
	}

	/**
	* Returns <code>true</code> if this deliberation is a draft.
	*
	* @return <code>true</code> if this deliberation is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _deliberation.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _deliberation.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this deliberation is expired.
	*
	* @return <code>true</code> if this deliberation is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _deliberation.isExpired();
	}

	/**
	* Returns <code>true</code> if this deliberation is inactive.
	*
	* @return <code>true</code> if this deliberation is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _deliberation.isInactive();
	}

	/**
	* Returns <code>true</code> if this deliberation is incomplete.
	*
	* @return <code>true</code> if this deliberation is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _deliberation.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _deliberation.isNew();
	}

	/**
	* Returns <code>true</code> if this deliberation is pending.
	*
	* @return <code>true</code> if this deliberation is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _deliberation.isPending();
	}

	/**
	* Returns <code>true</code> if this deliberation is scheduled.
	*
	* @return <code>true</code> if this deliberation is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _deliberation.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deliberation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.council.model.Deliberation> toCacheModel() {
		return _deliberation.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Deliberation toEscapedModel() {
		return new DeliberationWrapper(_deliberation.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.council.model.Deliberation toUnescapedModel() {
		return new DeliberationWrapper(_deliberation.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.council.model.Deliberation deliberation) {
		return _deliberation.compareTo(deliberation);
	}

	/**
	* Returns the order of this deliberation.
	*
	* @return the order of this deliberation
	*/
	@Override
	public int getOrder() {
		return _deliberation.getOrder();
	}

	/**
	* Returns the status of this deliberation.
	*
	* @return the status of this deliberation
	*/
	@Override
	public int getStatus() {
		return _deliberation.getStatus();
	}

	@Override
	public int hashCode() {
		return _deliberation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deliberation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DeliberationWrapper((Deliberation)_deliberation.clone());
	}

	/**
	* Returns the doc ID of this deliberation.
	*
	* @return the doc ID of this deliberation
	*/
	@Override
	public java.lang.String getDocId() {
		return _deliberation.getDocId();
	}

	/**
	* Returns the status of this deliberation.
	*
	* @return the status of this deliberation
	*/
	@Override
	public java.lang.String getStatus() {
		return _deliberation.getStatus();
	}

	/**
	* Returns the status by user name of this deliberation.
	*
	* @return the status by user name of this deliberation
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _deliberation.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this deliberation.
	*
	* @return the status by user uuid of this deliberation
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _deliberation.getStatusByUserUuid();
	}

	/**
	* Returns the text of this deliberation.
	*
	* @return the text of this deliberation
	*/
	@Override
	public java.lang.String getText() {
		return _deliberation.getText();
	}

	/**
	* Returns the title of this deliberation.
	*
	* @return the title of this deliberation
	*/
	@Override
	public java.lang.String getTitle() {
		return _deliberation.getTitle();
	}

	/**
	* Returns the user name of this deliberation.
	*
	* @return the user name of this deliberation
	*/
	@Override
	public java.lang.String getUserName() {
		return _deliberation.getUserName();
	}

	/**
	* Returns the user uuid of this deliberation.
	*
	* @return the user uuid of this deliberation
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _deliberation.getUserUuid();
	}

	/**
	* Returns the uuid of this deliberation.
	*
	* @return the uuid of this deliberation
	*/
	@Override
	public java.lang.String getUuid() {
		return _deliberation.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _deliberation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _deliberation.toXmlString();
	}

	/**
	* Returns the create date of this deliberation.
	*
	* @return the create date of this deliberation
	*/
	@Override
	public Date getCreateDate() {
		return _deliberation.getCreateDate();
	}

	/**
	* Returns the modified date of this deliberation.
	*
	* @return the modified date of this deliberation
	*/
	@Override
	public Date getModifiedDate() {
		return _deliberation.getModifiedDate();
	}

	/**
	* Returns the status date of this deliberation.
	*
	* @return the status date of this deliberation
	*/
	@Override
	public Date getStatusDate() {
		return _deliberation.getStatusDate();
	}

	/**
	* Returns the company ID of this deliberation.
	*
	* @return the company ID of this deliberation
	*/
	@Override
	public long getCompanyId() {
		return _deliberation.getCompanyId();
	}

	/**
	* Returns the deliberation ID of this deliberation.
	*
	* @return the deliberation ID of this deliberation
	*/
	@Override
	public long getDeliberationId() {
		return _deliberation.getDeliberationId();
	}

	/**
	* Returns the group ID of this deliberation.
	*
	* @return the group ID of this deliberation
	*/
	@Override
	public long getGroupId() {
		return _deliberation.getGroupId();
	}

	/**
	* Returns the primary key of this deliberation.
	*
	* @return the primary key of this deliberation
	*/
	@Override
	public long getPrimaryKey() {
		return _deliberation.getPrimaryKey();
	}

	/**
	* Returns the session ID of this deliberation.
	*
	* @return the session ID of this deliberation
	*/
	@Override
	public long getSessionId() {
		return _deliberation.getSessionId();
	}

	/**
	* Returns the status by user ID of this deliberation.
	*
	* @return the status by user ID of this deliberation
	*/
	@Override
	public long getStatusByUserId() {
		return _deliberation.getStatusByUserId();
	}

	/**
	* Returns the user ID of this deliberation.
	*
	* @return the user ID of this deliberation
	*/
	@Override
	public long getUserId() {
		return _deliberation.getUserId();
	}

	@Override
	public void persist() {
		_deliberation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deliberation.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this deliberation.
	*
	* @param companyId the company ID of this deliberation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_deliberation.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this deliberation.
	*
	* @param createDate the create date of this deliberation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_deliberation.setCreateDate(createDate);
	}

	/**
	* Sets the deliberation ID of this deliberation.
	*
	* @param deliberationId the deliberation ID of this deliberation
	*/
	@Override
	public void setDeliberationId(long deliberationId) {
		_deliberation.setDeliberationId(deliberationId);
	}

	/**
	* Sets the doc ID of this deliberation.
	*
	* @param docId the doc ID of this deliberation
	*/
	@Override
	public void setDocId(java.lang.String docId) {
		_deliberation.setDocId(docId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deliberation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deliberation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deliberation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this deliberation.
	*
	* @param groupId the group ID of this deliberation
	*/
	@Override
	public void setGroupId(long groupId) {
		_deliberation.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this deliberation.
	*
	* @param modifiedDate the modified date of this deliberation
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deliberation.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deliberation.setNew(n);
	}

	/**
	* Sets the order of this deliberation.
	*
	* @param order the order of this deliberation
	*/
	@Override
	public void setOrder(int order) {
		_deliberation.setOrder(order);
	}

	/**
	* Sets the primary key of this deliberation.
	*
	* @param primaryKey the primary key of this deliberation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_deliberation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deliberation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this deliberation.
	*
	* @param sessionId the session ID of this deliberation
	*/
	@Override
	public void setSessionId(long sessionId) {
		_deliberation.setSessionId(sessionId);
	}

	/**
	* Sets the status of this deliberation.
	*
	* @param status the status of this deliberation
	*/
	@Override
	public void setStatus(int status) {
		_deliberation.setStatus(status);
	}

	/**
	* Sets the status of this deliberation.
	*
	* @param status the status of this deliberation
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_deliberation.setStatus(status);
	}

	/**
	* Sets the status by user ID of this deliberation.
	*
	* @param statusByUserId the status by user ID of this deliberation
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_deliberation.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this deliberation.
	*
	* @param statusByUserName the status by user name of this deliberation
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_deliberation.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this deliberation.
	*
	* @param statusByUserUuid the status by user uuid of this deliberation
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_deliberation.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this deliberation.
	*
	* @param statusDate the status date of this deliberation
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_deliberation.setStatusDate(statusDate);
	}

	/**
	* Sets the text of this deliberation.
	*
	* @param text the text of this deliberation
	*/
	@Override
	public void setText(java.lang.String text) {
		_deliberation.setText(text);
	}

	/**
	* Sets the title of this deliberation.
	*
	* @param title the title of this deliberation
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_deliberation.setTitle(title);
	}

	/**
	* Sets the user ID of this deliberation.
	*
	* @param userId the user ID of this deliberation
	*/
	@Override
	public void setUserId(long userId) {
		_deliberation.setUserId(userId);
	}

	/**
	* Sets the user name of this deliberation.
	*
	* @param userName the user name of this deliberation
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_deliberation.setUserName(userName);
	}

	/**
	* Sets the user uuid of this deliberation.
	*
	* @param userUuid the user uuid of this deliberation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_deliberation.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this deliberation.
	*
	* @param uuid the uuid of this deliberation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_deliberation.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliberationWrapper)) {
			return false;
		}

		DeliberationWrapper deliberationWrapper = (DeliberationWrapper)obj;

		if (Objects.equals(_deliberation, deliberationWrapper._deliberation)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _deliberation.getStagedModelType();
	}

	@Override
	public Deliberation getWrappedModel() {
		return _deliberation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deliberation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deliberation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deliberation.resetOriginalValues();
	}

	private final Deliberation _deliberation;
}
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
 * This class is a wrapper for {@link Session}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Session
 * @generated
 */
@ProviderType
public class SessionWrapper implements Session, ModelWrapper<Session> {
	public SessionWrapper(Session session) {
		_session = session;
	}

	@Override
	public Class<?> getModelClass() {
		return Session.class;
	}

	@Override
	public String getModelClassName() {
		return Session.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sessionId", getSessionId());
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
		attributes.put("date", getDate());
		attributes.put("type", getType());
		attributes.put("docId", getDocId());
		attributes.put("docReportId", getDocReportId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
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

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String docId = (String)attributes.get("docId");

		if (docId != null) {
			setDocId(docId);
		}

		String docReportId = (String)attributes.get("docReportId");

		if (docReportId != null) {
			setDocReportId(docReportId);
		}
	}

	/**
	* Returns <code>true</code> if this session is approved.
	*
	* @return <code>true</code> if this session is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _session.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _session.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this session is denied.
	*
	* @return <code>true</code> if this session is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _session.isDenied();
	}

	/**
	* Returns <code>true</code> if this session is a draft.
	*
	* @return <code>true</code> if this session is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _session.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _session.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this session is expired.
	*
	* @return <code>true</code> if this session is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _session.isExpired();
	}

	/**
	* Returns <code>true</code> if this session is inactive.
	*
	* @return <code>true</code> if this session is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _session.isInactive();
	}

	/**
	* Returns <code>true</code> if this session is incomplete.
	*
	* @return <code>true</code> if this session is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _session.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _session.isNew();
	}

	/**
	* Returns <code>true</code> if this session is pending.
	*
	* @return <code>true</code> if this session is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _session.isPending();
	}

	/**
	* Returns <code>true</code> if this session is scheduled.
	*
	* @return <code>true</code> if this session is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _session.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _session.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.council.model.Session> toCacheModel() {
		return _session.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Session toEscapedModel() {
		return new SessionWrapper(_session.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.council.model.Session toUnescapedModel() {
		return new SessionWrapper(_session.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.council.model.Session session) {
		return _session.compareTo(session);
	}

	/**
	* Returns the status of this session.
	*
	* @return the status of this session
	*/
	@Override
	public int getStatus() {
		return _session.getStatus();
	}

	@Override
	public int hashCode() {
		return _session.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _session.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SessionWrapper((Session)_session.clone());
	}

	/**
	* Returns the doc ID of this session.
	*
	* @return the doc ID of this session
	*/
	@Override
	public java.lang.String getDocId() {
		return _session.getDocId();
	}

	/**
	* Returns the doc report ID of this session.
	*
	* @return the doc report ID of this session
	*/
	@Override
	public java.lang.String getDocReportId() {
		return _session.getDocReportId();
	}

	/**
	* Returns the status by user name of this session.
	*
	* @return the status by user name of this session
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _session.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this session.
	*
	* @return the status by user uuid of this session
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _session.getStatusByUserUuid();
	}

	/**
	* Returns the title of this session.
	*
	* @return the title of this session
	*/
	@Override
	public java.lang.String getTitle() {
		return _session.getTitle();
	}

	/**
	* Returns the type of this session.
	*
	* @return the type of this session
	*/
	@Override
	public java.lang.String getType() {
		return _session.getType();
	}

	/**
	* Returns the user name of this session.
	*
	* @return the user name of this session
	*/
	@Override
	public java.lang.String getUserName() {
		return _session.getUserName();
	}

	/**
	* Returns the user uuid of this session.
	*
	* @return the user uuid of this session
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _session.getUserUuid();
	}

	/**
	* Returns the uuid of this session.
	*
	* @return the uuid of this session
	*/
	@Override
	public java.lang.String getUuid() {
		return _session.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _session.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _session.toXmlString();
	}

	/**
	* Returns the create date of this session.
	*
	* @return the create date of this session
	*/
	@Override
	public Date getCreateDate() {
		return _session.getCreateDate();
	}

	/**
	* Returns the date of this session.
	*
	* @return the date of this session
	*/
	@Override
	public Date getDate() {
		return _session.getDate();
	}

	/**
	* Returns the modified date of this session.
	*
	* @return the modified date of this session
	*/
	@Override
	public Date getModifiedDate() {
		return _session.getModifiedDate();
	}

	/**
	* Returns the status date of this session.
	*
	* @return the status date of this session
	*/
	@Override
	public Date getStatusDate() {
		return _session.getStatusDate();
	}

	/**
	* Returns the company ID of this session.
	*
	* @return the company ID of this session
	*/
	@Override
	public long getCompanyId() {
		return _session.getCompanyId();
	}

	/**
	* Returns the group ID of this session.
	*
	* @return the group ID of this session
	*/
	@Override
	public long getGroupId() {
		return _session.getGroupId();
	}

	/**
	* Returns the primary key of this session.
	*
	* @return the primary key of this session
	*/
	@Override
	public long getPrimaryKey() {
		return _session.getPrimaryKey();
	}

	/**
	* Returns the session ID of this session.
	*
	* @return the session ID of this session
	*/
	@Override
	public long getSessionId() {
		return _session.getSessionId();
	}

	/**
	* Returns the status by user ID of this session.
	*
	* @return the status by user ID of this session
	*/
	@Override
	public long getStatusByUserId() {
		return _session.getStatusByUserId();
	}

	/**
	* Returns the user ID of this session.
	*
	* @return the user ID of this session
	*/
	@Override
	public long getUserId() {
		return _session.getUserId();
	}

	@Override
	public void persist() {
		_session.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_session.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this session.
	*
	* @param companyId the company ID of this session
	*/
	@Override
	public void setCompanyId(long companyId) {
		_session.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this session.
	*
	* @param createDate the create date of this session
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_session.setCreateDate(createDate);
	}

	/**
	* Sets the date of this session.
	*
	* @param date the date of this session
	*/
	@Override
	public void setDate(Date date) {
		_session.setDate(date);
	}

	/**
	* Sets the doc ID of this session.
	*
	* @param docId the doc ID of this session
	*/
	@Override
	public void setDocId(java.lang.String docId) {
		_session.setDocId(docId);
	}

	/**
	* Sets the doc report ID of this session.
	*
	* @param docReportId the doc report ID of this session
	*/
	@Override
	public void setDocReportId(java.lang.String docReportId) {
		_session.setDocReportId(docReportId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_session.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_session.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_session.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this session.
	*
	* @param groupId the group ID of this session
	*/
	@Override
	public void setGroupId(long groupId) {
		_session.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this session.
	*
	* @param modifiedDate the modified date of this session
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_session.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_session.setNew(n);
	}

	/**
	* Sets the primary key of this session.
	*
	* @param primaryKey the primary key of this session
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_session.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_session.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the session ID of this session.
	*
	* @param sessionId the session ID of this session
	*/
	@Override
	public void setSessionId(long sessionId) {
		_session.setSessionId(sessionId);
	}

	/**
	* Sets the status of this session.
	*
	* @param status the status of this session
	*/
	@Override
	public void setStatus(int status) {
		_session.setStatus(status);
	}

	/**
	* Sets the status by user ID of this session.
	*
	* @param statusByUserId the status by user ID of this session
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_session.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this session.
	*
	* @param statusByUserName the status by user name of this session
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_session.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this session.
	*
	* @param statusByUserUuid the status by user uuid of this session
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_session.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this session.
	*
	* @param statusDate the status date of this session
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_session.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this session.
	*
	* @param title the title of this session
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_session.setTitle(title);
	}

	/**
	* Sets the type of this session.
	*
	* @param type the type of this session
	*/
	@Override
	public void setType(java.lang.String type) {
		_session.setType(type);
	}

	/**
	* Sets the user ID of this session.
	*
	* @param userId the user ID of this session
	*/
	@Override
	public void setUserId(long userId) {
		_session.setUserId(userId);
	}

	/**
	* Sets the user name of this session.
	*
	* @param userName the user name of this session
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_session.setUserName(userName);
	}

	/**
	* Sets the user uuid of this session.
	*
	* @param userUuid the user uuid of this session
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_session.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this session.
	*
	* @param uuid the uuid of this session
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_session.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SessionWrapper)) {
			return false;
		}

		SessionWrapper sessionWrapper = (SessionWrapper)obj;

		if (Objects.equals(_session, sessionWrapper._session)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _session.getStagedModelType();
	}

	@Override
	public Session getWrappedModel() {
		return _session;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _session.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _session.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_session.resetOriginalValues();
	}

	private final Session _session;
}
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

package eu.strasbourg.service.project.model;

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
 * This class is a wrapper for {@link Participation}.
 * </p>
 *
 * @author Cedric Henry
 * @see Participation
 * @generated
 */
@ProviderType
public class ParticipationWrapper implements Participation,
	ModelWrapper<Participation> {
	public ParticipationWrapper(Participation participation) {
		_participation = participation;
	}

	@Override
	public Class<?> getModelClass() {
		return Participation.class;
	}

	@Override
	public String getModelClassName() {
		return Participation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("participationId", getParticipationId());
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
		attributes.put("author", getAuthor());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long participationId = (Long)attributes.get("participationId");

		if (participationId != null) {
			setParticipationId(participationId);
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

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	/**
	* Returns <code>true</code> if this participation is approved.
	*
	* @return <code>true</code> if this participation is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _participation.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _participation.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this participation is denied.
	*
	* @return <code>true</code> if this participation is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _participation.isDenied();
	}

	/**
	* Returns <code>true</code> if this participation is a draft.
	*
	* @return <code>true</code> if this participation is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _participation.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _participation.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this participation is expired.
	*
	* @return <code>true</code> if this participation is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _participation.isExpired();
	}

	/**
	* Returns <code>true</code> if this participation is inactive.
	*
	* @return <code>true</code> if this participation is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _participation.isInactive();
	}

	/**
	* Returns <code>true</code> if this participation is incomplete.
	*
	* @return <code>true</code> if this participation is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _participation.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _participation.isNew();
	}

	/**
	* Returns <code>true</code> if this participation is pending.
	*
	* @return <code>true</code> if this participation is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _participation.isPending();
	}

	/**
	* Returns <code>true</code> if this participation is scheduled.
	*
	* @return <code>true</code> if this participation is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _participation.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _participation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.Participation> toCacheModel() {
		return _participation.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.Participation toEscapedModel() {
		return new ParticipationWrapper(_participation.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.Participation toUnescapedModel() {
		return new ParticipationWrapper(_participation.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.Participation participation) {
		return _participation.compareTo(participation);
	}

	/**
	* Returns the status of this participation.
	*
	* @return the status of this participation
	*/
	@Override
	public int getStatus() {
		return _participation.getStatus();
	}

	@Override
	public int hashCode() {
		return _participation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _participation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ParticipationWrapper((Participation)_participation.clone());
	}

	/**
	* Returns the author of this participation.
	*
	* @return the author of this participation
	*/
	@Override
	public java.lang.String getAuthor() {
		return _participation.getAuthor();
	}

	/**
	* Returns the status by user name of this participation.
	*
	* @return the status by user name of this participation
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _participation.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this participation.
	*
	* @return the status by user uuid of this participation
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _participation.getStatusByUserUuid();
	}

	/**
	* Returns the title of this participation.
	*
	* @return the title of this participation
	*/
	@Override
	public java.lang.String getTitle() {
		return _participation.getTitle();
	}

	/**
	* Returns the user name of this participation.
	*
	* @return the user name of this participation
	*/
	@Override
	public java.lang.String getUserName() {
		return _participation.getUserName();
	}

	/**
	* Returns the user uuid of this participation.
	*
	* @return the user uuid of this participation
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _participation.getUserUuid();
	}

	/**
	* Returns the uuid of this participation.
	*
	* @return the uuid of this participation
	*/
	@Override
	public java.lang.String getUuid() {
		return _participation.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _participation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _participation.toXmlString();
	}

	/**
	* Returns the create date of this participation.
	*
	* @return the create date of this participation
	*/
	@Override
	public Date getCreateDate() {
		return _participation.getCreateDate();
	}

	/**
	* Returns the expiration date of this participation.
	*
	* @return the expiration date of this participation
	*/
	@Override
	public Date getExpirationDate() {
		return _participation.getExpirationDate();
	}

	/**
	* Returns the modified date of this participation.
	*
	* @return the modified date of this participation
	*/
	@Override
	public Date getModifiedDate() {
		return _participation.getModifiedDate();
	}

	/**
	* Returns the publication date of this participation.
	*
	* @return the publication date of this participation
	*/
	@Override
	public Date getPublicationDate() {
		return _participation.getPublicationDate();
	}

	/**
	* Returns the status date of this participation.
	*
	* @return the status date of this participation
	*/
	@Override
	public Date getStatusDate() {
		return _participation.getStatusDate();
	}

	/**
	* Returns the company ID of this participation.
	*
	* @return the company ID of this participation
	*/
	@Override
	public long getCompanyId() {
		return _participation.getCompanyId();
	}

	/**
	* Returns the group ID of this participation.
	*
	* @return the group ID of this participation
	*/
	@Override
	public long getGroupId() {
		return _participation.getGroupId();
	}

	/**
	* Returns the participation ID of this participation.
	*
	* @return the participation ID of this participation
	*/
	@Override
	public long getParticipationId() {
		return _participation.getParticipationId();
	}

	/**
	* Returns the primary key of this participation.
	*
	* @return the primary key of this participation
	*/
	@Override
	public long getPrimaryKey() {
		return _participation.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this participation.
	*
	* @return the status by user ID of this participation
	*/
	@Override
	public long getStatusByUserId() {
		return _participation.getStatusByUserId();
	}

	/**
	* Returns the user ID of this participation.
	*
	* @return the user ID of this participation
	*/
	@Override
	public long getUserId() {
		return _participation.getUserId();
	}

	@Override
	public void persist() {
		_participation.persist();
	}

	/**
	* Sets the author of this participation.
	*
	* @param author the author of this participation
	*/
	@Override
	public void setAuthor(java.lang.String author) {
		_participation.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_participation.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this participation.
	*
	* @param companyId the company ID of this participation
	*/
	@Override
	public void setCompanyId(long companyId) {
		_participation.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this participation.
	*
	* @param createDate the create date of this participation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_participation.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_participation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_participation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_participation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration date of this participation.
	*
	* @param expirationDate the expiration date of this participation
	*/
	@Override
	public void setExpirationDate(Date expirationDate) {
		_participation.setExpirationDate(expirationDate);
	}

	/**
	* Sets the group ID of this participation.
	*
	* @param groupId the group ID of this participation
	*/
	@Override
	public void setGroupId(long groupId) {
		_participation.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this participation.
	*
	* @param modifiedDate the modified date of this participation
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_participation.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_participation.setNew(n);
	}

	/**
	* Sets the participation ID of this participation.
	*
	* @param participationId the participation ID of this participation
	*/
	@Override
	public void setParticipationId(long participationId) {
		_participation.setParticipationId(participationId);
	}

	/**
	* Sets the primary key of this participation.
	*
	* @param primaryKey the primary key of this participation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_participation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_participation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publication date of this participation.
	*
	* @param publicationDate the publication date of this participation
	*/
	@Override
	public void setPublicationDate(Date publicationDate) {
		_participation.setPublicationDate(publicationDate);
	}

	/**
	* Sets the status of this participation.
	*
	* @param status the status of this participation
	*/
	@Override
	public void setStatus(int status) {
		_participation.setStatus(status);
	}

	/**
	* Sets the status by user ID of this participation.
	*
	* @param statusByUserId the status by user ID of this participation
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_participation.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this participation.
	*
	* @param statusByUserName the status by user name of this participation
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_participation.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this participation.
	*
	* @param statusByUserUuid the status by user uuid of this participation
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_participation.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this participation.
	*
	* @param statusDate the status date of this participation
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_participation.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this participation.
	*
	* @param title the title of this participation
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_participation.setTitle(title);
	}

	/**
	* Sets the user ID of this participation.
	*
	* @param userId the user ID of this participation
	*/
	@Override
	public void setUserId(long userId) {
		_participation.setUserId(userId);
	}

	/**
	* Sets the user name of this participation.
	*
	* @param userName the user name of this participation
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_participation.setUserName(userName);
	}

	/**
	* Sets the user uuid of this participation.
	*
	* @param userUuid the user uuid of this participation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_participation.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this participation.
	*
	* @param uuid the uuid of this participation
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_participation.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ParticipationWrapper)) {
			return false;
		}

		ParticipationWrapper participationWrapper = (ParticipationWrapper)obj;

		if (Objects.equals(_participation, participationWrapper._participation)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _participation.getStagedModelType();
	}

	@Override
	public Participation getWrappedModel() {
		return _participation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _participation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _participation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_participation.resetOriginalValues();
	}

	private final Participation _participation;
}
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
 * This class is a wrapper for {@link Vote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Vote
 * @generated
 */
@ProviderType
public class VoteWrapper implements Vote, ModelWrapper<Vote> {
	public VoteWrapper(Vote vote) {
		_vote = vote;
	}

	@Override
	public Class<?> getModelClass() {
		return Vote.class;
	}

	@Override
	public String getModelClassName() {
		return Vote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("voteId", getVoteId());
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
		attributes.put("officialId", getOfficialId());
		attributes.put("deliberationId", getDeliberationId());
		attributes.put("officialProcurationId", getOfficialProcurationId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long voteId = (Long)attributes.get("voteId");

		if (voteId != null) {
			setVoteId(voteId);
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

		Long officialId = (Long)attributes.get("officialId");

		if (officialId != null) {
			setOfficialId(officialId);
		}

		Long deliberationId = (Long)attributes.get("deliberationId");

		if (deliberationId != null) {
			setDeliberationId(deliberationId);
		}

		Long officialProcurationId = (Long)attributes.get(
				"officialProcurationId");

		if (officialProcurationId != null) {
			setOfficialProcurationId(officialProcurationId);
		}
	}

	/**
	* Returns <code>true</code> if this vote is approved.
	*
	* @return <code>true</code> if this vote is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _vote.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _vote.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this vote is denied.
	*
	* @return <code>true</code> if this vote is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _vote.isDenied();
	}

	/**
	* Returns <code>true</code> if this vote is a draft.
	*
	* @return <code>true</code> if this vote is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _vote.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _vote.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this vote is expired.
	*
	* @return <code>true</code> if this vote is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _vote.isExpired();
	}

	/**
	* Returns <code>true</code> if this vote is inactive.
	*
	* @return <code>true</code> if this vote is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _vote.isInactive();
	}

	/**
	* Returns <code>true</code> if this vote is incomplete.
	*
	* @return <code>true</code> if this vote is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _vote.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _vote.isNew();
	}

	/**
	* Returns <code>true</code> if this vote is pending.
	*
	* @return <code>true</code> if this vote is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _vote.isPending();
	}

	/**
	* Returns <code>true</code> if this vote is scheduled.
	*
	* @return <code>true</code> if this vote is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _vote.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vote.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.council.model.Vote> toCacheModel() {
		return _vote.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Vote toEscapedModel() {
		return new VoteWrapper(_vote.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.council.model.Vote toUnescapedModel() {
		return new VoteWrapper(_vote.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.council.model.Vote vote) {
		return _vote.compareTo(vote);
	}

	/**
	* Returns the status of this vote.
	*
	* @return the status of this vote
	*/
	@Override
	public int getStatus() {
		return _vote.getStatus();
	}

	@Override
	public int hashCode() {
		return _vote.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vote.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new VoteWrapper((Vote)_vote.clone());
	}

	/**
	* Returns the status by user name of this vote.
	*
	* @return the status by user name of this vote
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _vote.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this vote.
	*
	* @return the status by user uuid of this vote
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _vote.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this vote.
	*
	* @return the user name of this vote
	*/
	@Override
	public java.lang.String getUserName() {
		return _vote.getUserName();
	}

	/**
	* Returns the user uuid of this vote.
	*
	* @return the user uuid of this vote
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _vote.getUserUuid();
	}

	/**
	* Returns the uuid of this vote.
	*
	* @return the uuid of this vote
	*/
	@Override
	public java.lang.String getUuid() {
		return _vote.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _vote.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _vote.toXmlString();
	}

	/**
	* Returns the create date of this vote.
	*
	* @return the create date of this vote
	*/
	@Override
	public Date getCreateDate() {
		return _vote.getCreateDate();
	}

	/**
	* Returns the modified date of this vote.
	*
	* @return the modified date of this vote
	*/
	@Override
	public Date getModifiedDate() {
		return _vote.getModifiedDate();
	}

	/**
	* Returns the status date of this vote.
	*
	* @return the status date of this vote
	*/
	@Override
	public Date getStatusDate() {
		return _vote.getStatusDate();
	}

	/**
	* Returns the company ID of this vote.
	*
	* @return the company ID of this vote
	*/
	@Override
	public long getCompanyId() {
		return _vote.getCompanyId();
	}

	/**
	* Returns the deliberation ID of this vote.
	*
	* @return the deliberation ID of this vote
	*/
	@Override
	public long getDeliberationId() {
		return _vote.getDeliberationId();
	}

	/**
	* Returns the group ID of this vote.
	*
	* @return the group ID of this vote
	*/
	@Override
	public long getGroupId() {
		return _vote.getGroupId();
	}

	/**
	* Returns the official ID of this vote.
	*
	* @return the official ID of this vote
	*/
	@Override
	public long getOfficialId() {
		return _vote.getOfficialId();
	}

	/**
	* Returns the official procuration ID of this vote.
	*
	* @return the official procuration ID of this vote
	*/
	@Override
	public long getOfficialProcurationId() {
		return _vote.getOfficialProcurationId();
	}

	/**
	* Returns the primary key of this vote.
	*
	* @return the primary key of this vote
	*/
	@Override
	public long getPrimaryKey() {
		return _vote.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this vote.
	*
	* @return the status by user ID of this vote
	*/
	@Override
	public long getStatusByUserId() {
		return _vote.getStatusByUserId();
	}

	/**
	* Returns the user ID of this vote.
	*
	* @return the user ID of this vote
	*/
	@Override
	public long getUserId() {
		return _vote.getUserId();
	}

	/**
	* Returns the vote ID of this vote.
	*
	* @return the vote ID of this vote
	*/
	@Override
	public long getVoteId() {
		return _vote.getVoteId();
	}

	@Override
	public void persist() {
		_vote.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vote.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this vote.
	*
	* @param companyId the company ID of this vote
	*/
	@Override
	public void setCompanyId(long companyId) {
		_vote.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this vote.
	*
	* @param createDate the create date of this vote
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_vote.setCreateDate(createDate);
	}

	/**
	* Sets the deliberation ID of this vote.
	*
	* @param deliberationId the deliberation ID of this vote
	*/
	@Override
	public void setDeliberationId(long deliberationId) {
		_vote.setDeliberationId(deliberationId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vote.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vote.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vote.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this vote.
	*
	* @param groupId the group ID of this vote
	*/
	@Override
	public void setGroupId(long groupId) {
		_vote.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this vote.
	*
	* @param modifiedDate the modified date of this vote
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vote.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vote.setNew(n);
	}

	/**
	* Sets the official ID of this vote.
	*
	* @param officialId the official ID of this vote
	*/
	@Override
	public void setOfficialId(long officialId) {
		_vote.setOfficialId(officialId);
	}

	/**
	* Sets the official procuration ID of this vote.
	*
	* @param officialProcurationId the official procuration ID of this vote
	*/
	@Override
	public void setOfficialProcurationId(long officialProcurationId) {
		_vote.setOfficialProcurationId(officialProcurationId);
	}

	/**
	* Sets the primary key of this vote.
	*
	* @param primaryKey the primary key of this vote
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_vote.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vote.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this vote.
	*
	* @param status the status of this vote
	*/
	@Override
	public void setStatus(int status) {
		_vote.setStatus(status);
	}

	/**
	* Sets the status by user ID of this vote.
	*
	* @param statusByUserId the status by user ID of this vote
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_vote.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this vote.
	*
	* @param statusByUserName the status by user name of this vote
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_vote.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this vote.
	*
	* @param statusByUserUuid the status by user uuid of this vote
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_vote.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this vote.
	*
	* @param statusDate the status date of this vote
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_vote.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this vote.
	*
	* @param userId the user ID of this vote
	*/
	@Override
	public void setUserId(long userId) {
		_vote.setUserId(userId);
	}

	/**
	* Sets the user name of this vote.
	*
	* @param userName the user name of this vote
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_vote.setUserName(userName);
	}

	/**
	* Sets the user uuid of this vote.
	*
	* @param userUuid the user uuid of this vote
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_vote.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this vote.
	*
	* @param uuid the uuid of this vote
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_vote.setUuid(uuid);
	}

	/**
	* Sets the vote ID of this vote.
	*
	* @param voteId the vote ID of this vote
	*/
	@Override
	public void setVoteId(long voteId) {
		_vote.setVoteId(voteId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VoteWrapper)) {
			return false;
		}

		VoteWrapper voteWrapper = (VoteWrapper)obj;

		if (Objects.equals(_vote, voteWrapper._vote)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _vote.getStagedModelType();
	}

	@Override
	public Vote getWrappedModel() {
		return _vote;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vote.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vote.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vote.resetOriginalValues();
	}

	private final Vote _vote;
}
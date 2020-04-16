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
		attributes.put("createDate", getCreateDate());
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
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

	@Override
	public boolean isCachedModel() {
		return _vote.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vote.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vote.isNew();
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
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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Vote service. Represents a row in the &quot;council_Vote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.council.model.impl.VoteModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.council.model.impl.VoteImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Vote
 * @see eu.strasbourg.service.council.model.impl.VoteImpl
 * @see eu.strasbourg.service.council.model.impl.VoteModelImpl
 * @generated
 */
@ProviderType
public interface VoteModel extends BaseModel<Vote>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a vote model instance should use the {@link Vote} interface instead.
	 */

	/**
	 * Returns the primary key of this vote.
	 *
	 * @return the primary key of this vote
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this vote.
	 *
	 * @param primaryKey the primary key of this vote
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this vote.
	 *
	 * @return the uuid of this vote
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this vote.
	 *
	 * @param uuid the uuid of this vote
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the vote ID of this vote.
	 *
	 * @return the vote ID of this vote
	 */
	public long getVoteId();

	/**
	 * Sets the vote ID of this vote.
	 *
	 * @param voteId the vote ID of this vote
	 */
	public void setVoteId(long voteId);

	/**
	 * Returns the group ID of this vote.
	 *
	 * @return the group ID of this vote
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this vote.
	 *
	 * @param groupId the group ID of this vote
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this vote.
	 *
	 * @return the company ID of this vote
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this vote.
	 *
	 * @param companyId the company ID of this vote
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this vote.
	 *
	 * @return the create date of this vote
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this vote.
	 *
	 * @param createDate the create date of this vote
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the result of this vote.
	 *
	 * @return the result of this vote
	 */
	@AutoEscape
	public String getResult();

	/**
	 * Sets the result of this vote.
	 *
	 * @param result the result of this vote
	 */
	public void setResult(String result);

	/**
	 * Returns the official ID of this vote.
	 *
	 * @return the official ID of this vote
	 */
	public long getOfficialId();

	/**
	 * Sets the official ID of this vote.
	 *
	 * @param officialId the official ID of this vote
	 */
	public void setOfficialId(long officialId);

	/**
	 * Returns the deliberation ID of this vote.
	 *
	 * @return the deliberation ID of this vote
	 */
	public long getDeliberationId();

	/**
	 * Sets the deliberation ID of this vote.
	 *
	 * @param deliberationId the deliberation ID of this vote
	 */
	public void setDeliberationId(long deliberationId);

	/**
	 * Returns the official procuration ID of this vote.
	 *
	 * @return the official procuration ID of this vote
	 */
	public long getOfficialProcurationId();

	/**
	 * Sets the official procuration ID of this vote.
	 *
	 * @param officialProcurationId the official procuration ID of this vote
	 */
	public void setOfficialProcurationId(long officialProcurationId);

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
	public int compareTo(eu.strasbourg.service.council.model.Vote vote);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.council.model.Vote> toCacheModel();

	@Override
	public eu.strasbourg.service.council.model.Vote toEscapedModel();

	@Override
	public eu.strasbourg.service.council.model.Vote toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
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

import eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the OfficialTypeCouncil service. Represents a row in the &quot;council_OfficialTypeCouncil&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.council.model.impl.OfficialTypeCouncilImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncil
 * @see eu.strasbourg.service.council.model.impl.OfficialTypeCouncilImpl
 * @see eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl
 * @generated
 */
@ProviderType
public interface OfficialTypeCouncilModel extends BaseModel<OfficialTypeCouncil>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a official type council model instance should use the {@link OfficialTypeCouncil} interface instead.
	 */

	/**
	 * Returns the primary key of this official type council.
	 *
	 * @return the primary key of this official type council
	 */
	public OfficialTypeCouncilPK getPrimaryKey();

	/**
	 * Sets the primary key of this official type council.
	 *
	 * @param primaryKey the primary key of this official type council
	 */
	public void setPrimaryKey(OfficialTypeCouncilPK primaryKey);

	/**
	 * Returns the uuid of this official type council.
	 *
	 * @return the uuid of this official type council
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this official type council.
	 *
	 * @param uuid the uuid of this official type council
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the official ID of this official type council.
	 *
	 * @return the official ID of this official type council
	 */
	public long getOfficialId();

	/**
	 * Sets the official ID of this official type council.
	 *
	 * @param officialId the official ID of this official type council
	 */
	public void setOfficialId(long officialId);

	/**
	 * Returns the type ID of this official type council.
	 *
	 * @return the type ID of this official type council
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this official type council.
	 *
	 * @param typeId the type ID of this official type council
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the group ID of this official type council.
	 *
	 * @return the group ID of this official type council
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this official type council.
	 *
	 * @param groupId the group ID of this official type council
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this official type council.
	 *
	 * @return the company ID of this official type council
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this official type council.
	 *
	 * @param companyId the company ID of this official type council
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this official type council.
	 *
	 * @return the create date of this official type council
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this official type council.
	 *
	 * @param createDate the create date of this official type council
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the result of this official type council.
	 *
	 * @return the result of this official type council
	 */
	@AutoEscape
	public String getResult();

	/**
	 * Sets the result of this official type council.
	 *
	 * @param result the result of this official type council
	 */
	public void setResult(String result);

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
		eu.strasbourg.service.council.model.OfficialTypeCouncil officialTypeCouncil);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.council.model.OfficialTypeCouncil> toCacheModel();

	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil toEscapedModel();

	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
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
 * This class is a wrapper for {@link OfficialTypeCouncil}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncil
 * @generated
 */
@ProviderType
public class OfficialTypeCouncilWrapper
	implements OfficialTypeCouncil, ModelWrapper<OfficialTypeCouncil> {

	public OfficialTypeCouncilWrapper(OfficialTypeCouncil officialTypeCouncil) {
		_officialTypeCouncil = officialTypeCouncil;
	}

	@Override
	public Class<?> getModelClass() {
		return OfficialTypeCouncil.class;
	}

	@Override
	public String getModelClassName() {
		return OfficialTypeCouncil.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("officialId", getOfficialId());
		attributes.put("typeId", getTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("result", getResult());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long officialId = (Long)attributes.get("officialId");

		if (officialId != null) {
			setOfficialId(officialId);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
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

		String result = (String)attributes.get("result");

		if (result != null) {
			setResult(result);
		}
	}

	@Override
	public Object clone() {
		return new OfficialTypeCouncilWrapper(
			(OfficialTypeCouncil)_officialTypeCouncil.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.council.model.OfficialTypeCouncil
			officialTypeCouncil) {

		return _officialTypeCouncil.compareTo(officialTypeCouncil);
	}

	/**
	 * Returns the company ID of this official type council.
	 *
	 * @return the company ID of this official type council
	 */
	@Override
	public long getCompanyId() {
		return _officialTypeCouncil.getCompanyId();
	}

	/**
	 * Returns the create date of this official type council.
	 *
	 * @return the create date of this official type council
	 */
	@Override
	public Date getCreateDate() {
		return _officialTypeCouncil.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _officialTypeCouncil.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this official type council.
	 *
	 * @return the group ID of this official type council
	 */
	@Override
	public long getGroupId() {
		return _officialTypeCouncil.getGroupId();
	}

	/**
	 * Returns the official ID of this official type council.
	 *
	 * @return the official ID of this official type council
	 */
	@Override
	public long getOfficialId() {
		return _officialTypeCouncil.getOfficialId();
	}

	/**
	 * Returns the primary key of this official type council.
	 *
	 * @return the primary key of this official type council
	 */
	@Override
	public
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			getPrimaryKey() {

		return _officialTypeCouncil.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _officialTypeCouncil.getPrimaryKeyObj();
	}

	/**
	 * Returns the result of this official type council.
	 *
	 * @return the result of this official type council
	 */
	@Override
	public String getResult() {
		return _officialTypeCouncil.getResult();
	}

	/**
	 * Returns the type ID of this official type council.
	 *
	 * @return the type ID of this official type council
	 */
	@Override
	public long getTypeId() {
		return _officialTypeCouncil.getTypeId();
	}

	/**
	 * Returns the uuid of this official type council.
	 *
	 * @return the uuid of this official type council
	 */
	@Override
	public String getUuid() {
		return _officialTypeCouncil.getUuid();
	}

	@Override
	public int hashCode() {
		return _officialTypeCouncil.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _officialTypeCouncil.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _officialTypeCouncil.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _officialTypeCouncil.isNew();
	}

	@Override
	public void persist() {
		_officialTypeCouncil.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_officialTypeCouncil.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this official type council.
	 *
	 * @param companyId the company ID of this official type council
	 */
	@Override
	public void setCompanyId(long companyId) {
		_officialTypeCouncil.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this official type council.
	 *
	 * @param createDate the create date of this official type council
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_officialTypeCouncil.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_officialTypeCouncil.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_officialTypeCouncil.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_officialTypeCouncil.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this official type council.
	 *
	 * @param groupId the group ID of this official type council
	 */
	@Override
	public void setGroupId(long groupId) {
		_officialTypeCouncil.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_officialTypeCouncil.setNew(n);
	}

	/**
	 * Sets the official ID of this official type council.
	 *
	 * @param officialId the official ID of this official type council
	 */
	@Override
	public void setOfficialId(long officialId) {
		_officialTypeCouncil.setOfficialId(officialId);
	}

	/**
	 * Sets the primary key of this official type council.
	 *
	 * @param primaryKey the primary key of this official type council
	 */
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			primaryKey) {

		_officialTypeCouncil.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_officialTypeCouncil.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the result of this official type council.
	 *
	 * @param result the result of this official type council
	 */
	@Override
	public void setResult(String result) {
		_officialTypeCouncil.setResult(result);
	}

	/**
	 * Sets the type ID of this official type council.
	 *
	 * @param typeId the type ID of this official type council
	 */
	@Override
	public void setTypeId(long typeId) {
		_officialTypeCouncil.setTypeId(typeId);
	}

	/**
	 * Sets the uuid of this official type council.
	 *
	 * @param uuid the uuid of this official type council
	 */
	@Override
	public void setUuid(String uuid) {
		_officialTypeCouncil.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			toCacheModel() {

		return _officialTypeCouncil.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		toEscapedModel() {

		return new OfficialTypeCouncilWrapper(
			_officialTypeCouncil.toEscapedModel());
	}

	@Override
	public String toString() {
		return _officialTypeCouncil.toString();
	}

	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		toUnescapedModel() {

		return new OfficialTypeCouncilWrapper(
			_officialTypeCouncil.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _officialTypeCouncil.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficialTypeCouncilWrapper)) {
			return false;
		}

		OfficialTypeCouncilWrapper officialTypeCouncilWrapper =
			(OfficialTypeCouncilWrapper)obj;

		if (Objects.equals(
				_officialTypeCouncil,
				officialTypeCouncilWrapper._officialTypeCouncil)) {

			return true;
		}

		return false;
	}

	@Override
	public OfficialTypeCouncil getWrappedModel() {
		return _officialTypeCouncil;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _officialTypeCouncil.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _officialTypeCouncil.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_officialTypeCouncil.resetOriginalValues();
	}

	private final OfficialTypeCouncil _officialTypeCouncil;

}
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

package eu.strasbourg.service.strasbourg.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Strasbourg}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Strasbourg
 * @generated
 */
@ProviderType
public class StrasbourgWrapper implements Strasbourg, ModelWrapper<Strasbourg> {
	public StrasbourgWrapper(Strasbourg strasbourg) {
		_strasbourg = strasbourg;
	}

	@Override
	public Class<?> getModelClass() {
		return Strasbourg.class;
	}

	@Override
	public String getModelClassName() {
		return Strasbourg.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _strasbourg.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _strasbourg.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _strasbourg.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _strasbourg.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.strasbourg.model.Strasbourg> toCacheModel() {
		return _strasbourg.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg toEscapedModel() {
		return new StrasbourgWrapper(_strasbourg.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg toUnescapedModel() {
		return new StrasbourgWrapper(_strasbourg.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.strasbourg.model.Strasbourg strasbourg) {
		return _strasbourg.compareTo(strasbourg);
	}

	@Override
	public int hashCode() {
		return _strasbourg.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _strasbourg.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StrasbourgWrapper((Strasbourg)_strasbourg.clone());
	}

	/**
	* Returns the uuid of this strasbourg.
	*
	* @return the uuid of this strasbourg
	*/
	@Override
	public java.lang.String getUuid() {
		return _strasbourg.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _strasbourg.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _strasbourg.toXmlString();
	}

	/**
	* Returns the ID of this strasbourg.
	*
	* @return the ID of this strasbourg
	*/
	@Override
	public long getId() {
		return _strasbourg.getId();
	}

	/**
	* Returns the primary key of this strasbourg.
	*
	* @return the primary key of this strasbourg
	*/
	@Override
	public long getPrimaryKey() {
		return _strasbourg.getPrimaryKey();
	}

	@Override
	public void persist() {
		_strasbourg.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_strasbourg.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_strasbourg.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_strasbourg.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_strasbourg.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this strasbourg.
	*
	* @param id the ID of this strasbourg
	*/
	@Override
	public void setId(long id) {
		_strasbourg.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_strasbourg.setNew(n);
	}

	/**
	* Sets the primary key of this strasbourg.
	*
	* @param primaryKey the primary key of this strasbourg
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_strasbourg.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_strasbourg.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this strasbourg.
	*
	* @param uuid the uuid of this strasbourg
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_strasbourg.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrasbourgWrapper)) {
			return false;
		}

		StrasbourgWrapper strasbourgWrapper = (StrasbourgWrapper)obj;

		if (Objects.equals(_strasbourg, strasbourgWrapper._strasbourg)) {
			return true;
		}

		return false;
	}

	@Override
	public Strasbourg getWrappedModel() {
		return _strasbourg;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _strasbourg.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _strasbourg.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_strasbourg.resetOriginalValues();
	}

	private final Strasbourg _strasbourg;
}
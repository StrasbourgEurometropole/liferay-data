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

package eu.strasbourg.service.objtp.model;

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
 * This class is a wrapper for {@link ObjectCategory}.
 * </p>
 *
 * @author JeremyZwickert
 * @see ObjectCategory
 * @generated
 */
@ProviderType
public class ObjectCategoryWrapper implements ObjectCategory,
	ModelWrapper<ObjectCategory> {
	public ObjectCategoryWrapper(ObjectCategory objectCategory) {
		_objectCategory = objectCategory;
	}

	@Override
	public Class<?> getModelClass() {
		return ObjectCategory.class;
	}

	@Override
	public String getModelClassName() {
		return ObjectCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("code", getCode());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _objectCategory.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _objectCategory.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _objectCategory.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _objectCategory.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.objtp.model.ObjectCategory> toCacheModel() {
		return _objectCategory.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory toEscapedModel() {
		return new ObjectCategoryWrapper(_objectCategory.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory toUnescapedModel() {
		return new ObjectCategoryWrapper(_objectCategory.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return _objectCategory.compareTo(objectCategory);
	}

	@Override
	public int hashCode() {
		return _objectCategory.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _objectCategory.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ObjectCategoryWrapper((ObjectCategory)_objectCategory.clone());
	}

	/**
	* Returns the code of this object category.
	*
	* @return the code of this object category
	*/
	@Override
	public java.lang.String getCode() {
		return _objectCategory.getCode();
	}

	/**
	* Returns the name of this object category.
	*
	* @return the name of this object category
	*/
	@Override
	public java.lang.String getName() {
		return _objectCategory.getName();
	}

	/**
	* Returns the primary key of this object category.
	*
	* @return the primary key of this object category
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _objectCategory.getPrimaryKey();
	}

	@Override
	public java.lang.String toString() {
		return _objectCategory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _objectCategory.toXmlString();
	}

	@Override
	public void persist() {
		_objectCategory.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_objectCategory.setCachedModel(cachedModel);
	}

	/**
	* Sets the code of this object category.
	*
	* @param code the code of this object category
	*/
	@Override
	public void setCode(java.lang.String code) {
		_objectCategory.setCode(code);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_objectCategory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_objectCategory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_objectCategory.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the name of this object category.
	*
	* @param name the name of this object category
	*/
	@Override
	public void setName(java.lang.String name) {
		_objectCategory.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_objectCategory.setNew(n);
	}

	/**
	* Sets the primary key of this object category.
	*
	* @param primaryKey the primary key of this object category
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_objectCategory.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_objectCategory.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ObjectCategoryWrapper)) {
			return false;
		}

		ObjectCategoryWrapper objectCategoryWrapper = (ObjectCategoryWrapper)obj;

		if (Objects.equals(_objectCategory,
					objectCategoryWrapper._objectCategory)) {
			return true;
		}

		return false;
	}

	@Override
	public ObjectCategory getWrappedModel() {
		return _objectCategory;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _objectCategory.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _objectCategory.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_objectCategory.resetOriginalValues();
	}

	private final ObjectCategory _objectCategory;
}
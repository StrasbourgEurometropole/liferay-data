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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link FoundObject}.
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObject
 * @generated
 */
@ProviderType
public class FoundObjectWrapper implements FoundObject,
	ModelWrapper<FoundObject> {
	public FoundObjectWrapper(FoundObject foundObject) {
		_foundObject = foundObject;
	}

	@Override
	public Class<?> getModelClass() {
		return FoundObject.class;
	}

	@Override
	public String getModelClassName() {
		return FoundObject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("number", getNumber());
		attributes.put("date", getDate());
		attributes.put("imageUrl", getImageUrl());
		attributes.put("categoryCode", getCategoryCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		String imageUrl = (String)attributes.get("imageUrl");

		if (imageUrl != null) {
			setImageUrl(imageUrl);
		}

		String categoryCode = (String)attributes.get("categoryCode");

		if (categoryCode != null) {
			setCategoryCode(categoryCode);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _foundObject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _foundObject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _foundObject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _foundObject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.objtp.model.FoundObject> toCacheModel() {
		return _foundObject.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.objtp.model.FoundObject toEscapedModel() {
		return new FoundObjectWrapper(_foundObject.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.objtp.model.FoundObject toUnescapedModel() {
		return new FoundObjectWrapper(_foundObject.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.objtp.model.FoundObject foundObject) {
		return _foundObject.compareTo(foundObject);
	}

	@Override
	public int hashCode() {
		return _foundObject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _foundObject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FoundObjectWrapper((FoundObject)_foundObject.clone());
	}

	/**
	* Returns the category code of this found object.
	*
	* @return the category code of this found object
	*/
	@Override
	public java.lang.String getCategoryCode() {
		return _foundObject.getCategoryCode();
	}

	/**
	* Returns the image url of this found object.
	*
	* @return the image url of this found object
	*/
	@Override
	public java.lang.String getImageUrl() {
		return _foundObject.getImageUrl();
	}

	/**
	* Returns the number of this found object.
	*
	* @return the number of this found object
	*/
	@Override
	public java.lang.String getNumber() {
		return _foundObject.getNumber();
	}

	/**
	* Returns the primary key of this found object.
	*
	* @return the primary key of this found object
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _foundObject.getPrimaryKey();
	}

	@Override
	public java.lang.String toString() {
		return _foundObject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _foundObject.toXmlString();
	}

	/**
	* Returns the date of this found object.
	*
	* @return the date of this found object
	*/
	@Override
	public Date getDate() {
		return _foundObject.getDate();
	}

	@Override
	public void persist() {
		_foundObject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_foundObject.setCachedModel(cachedModel);
	}

	/**
	* Sets the category code of this found object.
	*
	* @param categoryCode the category code of this found object
	*/
	@Override
	public void setCategoryCode(java.lang.String categoryCode) {
		_foundObject.setCategoryCode(categoryCode);
	}

	/**
	* Sets the date of this found object.
	*
	* @param date the date of this found object
	*/
	@Override
	public void setDate(Date date) {
		_foundObject.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_foundObject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_foundObject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_foundObject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the image url of this found object.
	*
	* @param imageUrl the image url of this found object
	*/
	@Override
	public void setImageUrl(java.lang.String imageUrl) {
		_foundObject.setImageUrl(imageUrl);
	}

	@Override
	public void setNew(boolean n) {
		_foundObject.setNew(n);
	}

	/**
	* Sets the number of this found object.
	*
	* @param number the number of this found object
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_foundObject.setNumber(number);
	}

	/**
	* Sets the primary key of this found object.
	*
	* @param primaryKey the primary key of this found object
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_foundObject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_foundObject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FoundObjectWrapper)) {
			return false;
		}

		FoundObjectWrapper foundObjectWrapper = (FoundObjectWrapper)obj;

		if (Objects.equals(_foundObject, foundObjectWrapper._foundObject)) {
			return true;
		}

		return false;
	}

	@Override
	public FoundObject getWrappedModel() {
		return _foundObject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _foundObject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _foundObject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_foundObject.resetOriginalValues();
	}

	private final FoundObject _foundObject;
}
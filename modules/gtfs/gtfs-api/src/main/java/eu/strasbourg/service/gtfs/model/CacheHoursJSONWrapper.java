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

package eu.strasbourg.service.gtfs.model;

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
 * This class is a wrapper for {@link CacheHoursJSON}.
 * </p>
 *
 * @author Cedric Henry
 * @see CacheHoursJSON
 * @generated
 */
@ProviderType
public class CacheHoursJSONWrapper
	implements CacheHoursJSON, ModelWrapper<CacheHoursJSON> {

	public CacheHoursJSONWrapper(CacheHoursJSON cacheHoursJSON) {
		_cacheHoursJSON = cacheHoursJSON;
	}

	@Override
	public Class<?> getModelClass() {
		return CacheHoursJSON.class;
	}

	@Override
	public String getModelClassName() {
		return CacheHoursJSON.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("stopCode", getStopCode());
		attributes.put("type", getType());
		attributes.put("jsonHour", getJsonHour());
		attributes.put("creationDate", getCreationDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String stopCode = (String)attributes.get("stopCode");

		if (stopCode != null) {
			setStopCode(stopCode);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String jsonHour = (String)attributes.get("jsonHour");

		if (jsonHour != null) {
			setJsonHour(jsonHour);
		}

		Date creationDate = (Date)attributes.get("creationDate");

		if (creationDate != null) {
			setCreationDate(creationDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public Object clone() {
		return new CacheHoursJSONWrapper(
			(CacheHoursJSON)_cacheHoursJSON.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.gtfs.model.CacheHoursJSON cacheHoursJSON) {

		return _cacheHoursJSON.compareTo(cacheHoursJSON);
	}

	/**
	 * Returns the creation date of this cache hours json.
	 *
	 * @return the creation date of this cache hours json
	 */
	@Override
	public Date getCreationDate() {
		return _cacheHoursJSON.getCreationDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cacheHoursJSON.getExpandoBridge();
	}

	/**
	 * Returns the json hour of this cache hours json.
	 *
	 * @return the json hour of this cache hours json
	 */
	@Override
	public String getJsonHour() {
		return _cacheHoursJSON.getJsonHour();
	}

	/**
	 * Returns the modified date of this cache hours json.
	 *
	 * @return the modified date of this cache hours json
	 */
	@Override
	public Date getModifiedDate() {
		return _cacheHoursJSON.getModifiedDate();
	}

	/**
	 * Returns the primary key of this cache hours json.
	 *
	 * @return the primary key of this cache hours json
	 */
	@Override
	public eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
		getPrimaryKey() {

		return _cacheHoursJSON.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cacheHoursJSON.getPrimaryKeyObj();
	}

	/**
	 * Returns the stop code of this cache hours json.
	 *
	 * @return the stop code of this cache hours json
	 */
	@Override
	public String getStopCode() {
		return _cacheHoursJSON.getStopCode();
	}

	/**
	 * Returns the type of this cache hours json.
	 *
	 * @return the type of this cache hours json
	 */
	@Override
	public int getType() {
		return _cacheHoursJSON.getType();
	}

	/**
	 * Returns the uuid of this cache hours json.
	 *
	 * @return the uuid of this cache hours json
	 */
	@Override
	public String getUuid() {
		return _cacheHoursJSON.getUuid();
	}

	@Override
	public int hashCode() {
		return _cacheHoursJSON.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cacheHoursJSON.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cacheHoursJSON.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cacheHoursJSON.isNew();
	}

	@Override
	public void persist() {
		_cacheHoursJSON.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cacheHoursJSON.setCachedModel(cachedModel);
	}

	/**
	 * Sets the creation date of this cache hours json.
	 *
	 * @param creationDate the creation date of this cache hours json
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		_cacheHoursJSON.setCreationDate(creationDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cacheHoursJSON.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cacheHoursJSON.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cacheHoursJSON.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the json hour of this cache hours json.
	 *
	 * @param jsonHour the json hour of this cache hours json
	 */
	@Override
	public void setJsonHour(String jsonHour) {
		_cacheHoursJSON.setJsonHour(jsonHour);
	}

	/**
	 * Sets the modified date of this cache hours json.
	 *
	 * @param modifiedDate the modified date of this cache hours json
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cacheHoursJSON.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cacheHoursJSON.setNew(n);
	}

	/**
	 * Sets the primary key of this cache hours json.
	 *
	 * @param primaryKey the primary key of this cache hours json
	 */
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
			primaryKey) {

		_cacheHoursJSON.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cacheHoursJSON.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the stop code of this cache hours json.
	 *
	 * @param stopCode the stop code of this cache hours json
	 */
	@Override
	public void setStopCode(String stopCode) {
		_cacheHoursJSON.setStopCode(stopCode);
	}

	/**
	 * Sets the type of this cache hours json.
	 *
	 * @param type the type of this cache hours json
	 */
	@Override
	public void setType(int type) {
		_cacheHoursJSON.setType(type);
	}

	/**
	 * Sets the uuid of this cache hours json.
	 *
	 * @param uuid the uuid of this cache hours json
	 */
	@Override
	public void setUuid(String uuid) {
		_cacheHoursJSON.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.gtfs.model.CacheHoursJSON> toCacheModel() {

		return _cacheHoursJSON.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CacheHoursJSON toEscapedModel() {
		return new CacheHoursJSONWrapper(_cacheHoursJSON.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cacheHoursJSON.toString();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CacheHoursJSON toUnescapedModel() {
		return new CacheHoursJSONWrapper(_cacheHoursJSON.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cacheHoursJSON.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheHoursJSONWrapper)) {
			return false;
		}

		CacheHoursJSONWrapper cacheHoursJSONWrapper =
			(CacheHoursJSONWrapper)obj;

		if (Objects.equals(
				_cacheHoursJSON, cacheHoursJSONWrapper._cacheHoursJSON)) {

			return true;
		}

		return false;
	}

	@Override
	public CacheHoursJSON getWrappedModel() {
		return _cacheHoursJSON;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cacheHoursJSON.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cacheHoursJSON.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cacheHoursJSON.resetOriginalValues();
	}

	private final CacheHoursJSON _cacheHoursJSON;

}
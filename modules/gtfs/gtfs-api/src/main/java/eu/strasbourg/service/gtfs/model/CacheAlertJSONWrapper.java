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
 * This class is a wrapper for {@link CacheAlertJSON}.
 * </p>
 *
 * @author Cedric Henry
 * @see CacheAlertJSON
 * @generated
 */
public class CacheAlertJSONWrapper
	implements CacheAlertJSON, ModelWrapper<CacheAlertJSON> {

	public CacheAlertJSONWrapper(CacheAlertJSON cacheAlertJSON) {
		_cacheAlertJSON = cacheAlertJSON;
	}

	@Override
	public Class<?> getModelClass() {
		return CacheAlertJSON.class;
	}

	@Override
	public String getModelClassName() {
		return CacheAlertJSON.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("cacheId", getCacheId());
		attributes.put("jsonAlert", getJsonAlert());
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

		Long cacheId = (Long)attributes.get("cacheId");

		if (cacheId != null) {
			setCacheId(cacheId);
		}

		String jsonAlert = (String)attributes.get("jsonAlert");

		if (jsonAlert != null) {
			setJsonAlert(jsonAlert);
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
		return new CacheAlertJSONWrapper(
			(CacheAlertJSON)_cacheAlertJSON.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.gtfs.model.CacheAlertJSON cacheAlertJSON) {

		return _cacheAlertJSON.compareTo(cacheAlertJSON);
	}

	/**
	 * Returns the cache ID of this cache alert json.
	 *
	 * @return the cache ID of this cache alert json
	 */
	@Override
	public long getCacheId() {
		return _cacheAlertJSON.getCacheId();
	}

	/**
	 * Returns the creation date of this cache alert json.
	 *
	 * @return the creation date of this cache alert json
	 */
	@Override
	public Date getCreationDate() {
		return _cacheAlertJSON.getCreationDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cacheAlertJSON.getExpandoBridge();
	}

	/**
	 * Returns the json alert of this cache alert json.
	 *
	 * @return the json alert of this cache alert json
	 */
	@Override
	public String getJsonAlert() {
		return _cacheAlertJSON.getJsonAlert();
	}

	/**
	 * Returns the modified date of this cache alert json.
	 *
	 * @return the modified date of this cache alert json
	 */
	@Override
	public Date getModifiedDate() {
		return _cacheAlertJSON.getModifiedDate();
	}

	/**
	 * Returns the primary key of this cache alert json.
	 *
	 * @return the primary key of this cache alert json
	 */
	@Override
	public long getPrimaryKey() {
		return _cacheAlertJSON.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cacheAlertJSON.getPrimaryKeyObj();
	}

	/**
	 * Returns the uuid of this cache alert json.
	 *
	 * @return the uuid of this cache alert json
	 */
	@Override
	public String getUuid() {
		return _cacheAlertJSON.getUuid();
	}

	@Override
	public int hashCode() {
		return _cacheAlertJSON.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cacheAlertJSON.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cacheAlertJSON.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cacheAlertJSON.isNew();
	}

	@Override
	public void persist() {
		_cacheAlertJSON.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cacheAlertJSON.setCachedModel(cachedModel);
	}

	/**
	 * Sets the cache ID of this cache alert json.
	 *
	 * @param cacheId the cache ID of this cache alert json
	 */
	@Override
	public void setCacheId(long cacheId) {
		_cacheAlertJSON.setCacheId(cacheId);
	}

	/**
	 * Sets the creation date of this cache alert json.
	 *
	 * @param creationDate the creation date of this cache alert json
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		_cacheAlertJSON.setCreationDate(creationDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_cacheAlertJSON.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cacheAlertJSON.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cacheAlertJSON.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the json alert of this cache alert json.
	 *
	 * @param jsonAlert the json alert of this cache alert json
	 */
	@Override
	public void setJsonAlert(String jsonAlert) {
		_cacheAlertJSON.setJsonAlert(jsonAlert);
	}

	/**
	 * Sets the modified date of this cache alert json.
	 *
	 * @param modifiedDate the modified date of this cache alert json
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cacheAlertJSON.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cacheAlertJSON.setNew(n);
	}

	/**
	 * Sets the primary key of this cache alert json.
	 *
	 * @param primaryKey the primary key of this cache alert json
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_cacheAlertJSON.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cacheAlertJSON.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the uuid of this cache alert json.
	 *
	 * @param uuid the uuid of this cache alert json
	 */
	@Override
	public void setUuid(String uuid) {
		_cacheAlertJSON.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.gtfs.model.CacheAlertJSON> toCacheModel() {

		return _cacheAlertJSON.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CacheAlertJSON toEscapedModel() {
		return new CacheAlertJSONWrapper(_cacheAlertJSON.toEscapedModel());
	}

	@Override
	public String toString() {
		return _cacheAlertJSON.toString();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CacheAlertJSON toUnescapedModel() {
		return new CacheAlertJSONWrapper(_cacheAlertJSON.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _cacheAlertJSON.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CacheAlertJSONWrapper)) {
			return false;
		}

		CacheAlertJSONWrapper cacheAlertJSONWrapper =
			(CacheAlertJSONWrapper)object;

		if (Objects.equals(
				_cacheAlertJSON, cacheAlertJSONWrapper._cacheAlertJSON)) {

			return true;
		}

		return false;
	}

	@Override
	public CacheAlertJSON getWrappedModel() {
		return _cacheAlertJSON;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cacheAlertJSON.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cacheAlertJSON.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cacheAlertJSON.resetOriginalValues();
	}

	private final CacheAlertJSON _cacheAlertJSON;

}
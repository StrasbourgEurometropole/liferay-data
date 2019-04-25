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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Stop}.
 * </p>
 *
 * @author Cedric Henry
 * @see Stop
 * @generated
 */
@ProviderType
public class StopWrapper implements Stop, ModelWrapper<Stop> {
	public StopWrapper(Stop stop) {
		_stop = stop;
	}

	@Override
	public Class<?> getModelClass() {
		return Stop.class;
	}

	@Override
	public String getModelClassName() {
		return Stop.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("stop_id", getStop_id());
		attributes.put("stop_code", getStop_code());
		attributes.put("stop_lat", getStop_lat());
		attributes.put("stop_lon", getStop_lon());
		attributes.put("stop_name", getStop_name());
		attributes.put("stop_url", getStop_url());
		attributes.put("stop_desc", getStop_desc());

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

		String stop_id = (String)attributes.get("stop_id");

		if (stop_id != null) {
			setStop_id(stop_id);
		}

		String stop_code = (String)attributes.get("stop_code");

		if (stop_code != null) {
			setStop_code(stop_code);
		}

		Long stop_lat = (Long)attributes.get("stop_lat");

		if (stop_lat != null) {
			setStop_lat(stop_lat);
		}

		Long stop_lon = (Long)attributes.get("stop_lon");

		if (stop_lon != null) {
			setStop_lon(stop_lon);
		}

		String stop_name = (String)attributes.get("stop_name");

		if (stop_name != null) {
			setStop_name(stop_name);
		}

		String stop_url = (String)attributes.get("stop_url");

		if (stop_url != null) {
			setStop_url(stop_url);
		}

		String stop_desc = (String)attributes.get("stop_desc");

		if (stop_desc != null) {
			setStop_desc(stop_desc);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _stop.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stop.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stop.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stop.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Stop> toCacheModel() {
		return _stop.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Stop toEscapedModel() {
		return new StopWrapper(_stop.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Stop toUnescapedModel() {
		return new StopWrapper(_stop.toUnescapedModel());
	}

	/**
	* Returns the primary key of this stop.
	*
	* @return the primary key of this stop
	*/
	@Override
	public eu.strasbourg.service.gtfs.service.persistence.StopPK getPrimaryKey() {
		return _stop.getPrimaryKey();
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Stop stop) {
		return _stop.compareTo(stop);
	}

	@Override
	public int hashCode() {
		return _stop.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stop.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StopWrapper((Stop)_stop.clone());
	}

	/**
	* Returns the stop_code of this stop.
	*
	* @return the stop_code of this stop
	*/
	@Override
	public java.lang.String getStop_code() {
		return _stop.getStop_code();
	}

	/**
	* Returns the stop_desc of this stop.
	*
	* @return the stop_desc of this stop
	*/
	@Override
	public java.lang.String getStop_desc() {
		return _stop.getStop_desc();
	}

	/**
	* Returns the stop_id of this stop.
	*
	* @return the stop_id of this stop
	*/
	@Override
	public java.lang.String getStop_id() {
		return _stop.getStop_id();
	}

	/**
	* Returns the stop_name of this stop.
	*
	* @return the stop_name of this stop
	*/
	@Override
	public java.lang.String getStop_name() {
		return _stop.getStop_name();
	}

	/**
	* Returns the stop_url of this stop.
	*
	* @return the stop_url of this stop
	*/
	@Override
	public java.lang.String getStop_url() {
		return _stop.getStop_url();
	}

	/**
	* Returns the uuid of this stop.
	*
	* @return the uuid of this stop
	*/
	@Override
	public java.lang.String getUuid() {
		return _stop.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _stop.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _stop.toXmlString();
	}

	/**
	* Returns the ID of this stop.
	*
	* @return the ID of this stop
	*/
	@Override
	public long getId() {
		return _stop.getId();
	}

	/**
	* Returns the stop_lat of this stop.
	*
	* @return the stop_lat of this stop
	*/
	@Override
	public long getStop_lat() {
		return _stop.getStop_lat();
	}

	/**
	* Returns the stop_lon of this stop.
	*
	* @return the stop_lon of this stop
	*/
	@Override
	public long getStop_lon() {
		return _stop.getStop_lon();
	}

	@Override
	public void persist() {
		_stop.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stop.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stop.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stop.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stop.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this stop.
	*
	* @param id the ID of this stop
	*/
	@Override
	public void setId(long id) {
		_stop.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_stop.setNew(n);
	}

	/**
	* Sets the primary key of this stop.
	*
	* @param primaryKey the primary key of this stop
	*/
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.gtfs.service.persistence.StopPK primaryKey) {
		_stop.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stop.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the stop_code of this stop.
	*
	* @param stop_code the stop_code of this stop
	*/
	@Override
	public void setStop_code(java.lang.String stop_code) {
		_stop.setStop_code(stop_code);
	}

	/**
	* Sets the stop_desc of this stop.
	*
	* @param stop_desc the stop_desc of this stop
	*/
	@Override
	public void setStop_desc(java.lang.String stop_desc) {
		_stop.setStop_desc(stop_desc);
	}

	/**
	* Sets the stop_id of this stop.
	*
	* @param stop_id the stop_id of this stop
	*/
	@Override
	public void setStop_id(java.lang.String stop_id) {
		_stop.setStop_id(stop_id);
	}

	/**
	* Sets the stop_lat of this stop.
	*
	* @param stop_lat the stop_lat of this stop
	*/
	@Override
	public void setStop_lat(long stop_lat) {
		_stop.setStop_lat(stop_lat);
	}

	/**
	* Sets the stop_lon of this stop.
	*
	* @param stop_lon the stop_lon of this stop
	*/
	@Override
	public void setStop_lon(long stop_lon) {
		_stop.setStop_lon(stop_lon);
	}

	/**
	* Sets the stop_name of this stop.
	*
	* @param stop_name the stop_name of this stop
	*/
	@Override
	public void setStop_name(java.lang.String stop_name) {
		_stop.setStop_name(stop_name);
	}

	/**
	* Sets the stop_url of this stop.
	*
	* @param stop_url the stop_url of this stop
	*/
	@Override
	public void setStop_url(java.lang.String stop_url) {
		_stop.setStop_url(stop_url);
	}

	/**
	* Sets the uuid of this stop.
	*
	* @param uuid the uuid of this stop
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_stop.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StopWrapper)) {
			return false;
		}

		StopWrapper stopWrapper = (StopWrapper)obj;

		if (Objects.equals(_stop, stopWrapper._stop)) {
			return true;
		}

		return false;
	}

	@Override
	public Stop getWrappedModel() {
		return _stop;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stop.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stop.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stop.resetOriginalValues();
	}

	private final Stop _stop;
}
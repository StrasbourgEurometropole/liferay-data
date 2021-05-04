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
 * This class is a wrapper for {@link StopTime}.
 * </p>
 *
 * @author Cedric Henry
 * @see StopTime
 * @generated
 */
@ProviderType
public class StopTimeWrapper implements StopTime, ModelWrapper<StopTime> {

	public StopTimeWrapper(StopTime stopTime) {
		_stopTime = stopTime;
	}

	@Override
	public Class<?> getModelClass() {
		return StopTime.class;
	}

	@Override
	public String getModelClassName() {
		return StopTime.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("trip_id", getTrip_id());
		attributes.put("stop_id", getStop_id());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String trip_id = (String)attributes.get("trip_id");

		if (trip_id != null) {
			setTrip_id(trip_id);
		}

		String stop_id = (String)attributes.get("stop_id");

		if (stop_id != null) {
			setStop_id(stop_id);
		}
	}

	@Override
	public Object clone() {
		return new StopTimeWrapper((StopTime)_stopTime.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.StopTime stopTime) {
		return _stopTime.compareTo(stopTime);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stopTime.getExpandoBridge();
	}

	/**
	 * Returns the ID of this stop time.
	 *
	 * @return the ID of this stop time
	 */
	@Override
	public long getId() {
		return _stopTime.getId();
	}

	/**
	 * Returns the primary key of this stop time.
	 *
	 * @return the primary key of this stop time
	 */
	@Override
	public long getPrimaryKey() {
		return _stopTime.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stopTime.getPrimaryKeyObj();
	}

	/**
	 * Returns the stop_id of this stop time.
	 *
	 * @return the stop_id of this stop time
	 */
	@Override
	public String getStop_id() {
		return _stopTime.getStop_id();
	}

	/**
	 * Returns the trip_id of this stop time.
	 *
	 * @return the trip_id of this stop time
	 */
	@Override
	public String getTrip_id() {
		return _stopTime.getTrip_id();
	}

	@Override
	public int hashCode() {
		return _stopTime.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stopTime.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _stopTime.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stopTime.isNew();
	}

	@Override
	public void persist() {
		_stopTime.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stopTime.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_stopTime.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stopTime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stopTime.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the ID of this stop time.
	 *
	 * @param id the ID of this stop time
	 */
	@Override
	public void setId(long id) {
		_stopTime.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_stopTime.setNew(n);
	}

	/**
	 * Sets the primary key of this stop time.
	 *
	 * @param primaryKey the primary key of this stop time
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_stopTime.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stopTime.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the stop_id of this stop time.
	 *
	 * @param stop_id the stop_id of this stop time
	 */
	@Override
	public void setStop_id(String stop_id) {
		_stopTime.setStop_id(stop_id);
	}

	/**
	 * Sets the trip_id of this stop time.
	 *
	 * @param trip_id the trip_id of this stop time
	 */
	@Override
	public void setTrip_id(String trip_id) {
		_stopTime.setTrip_id(trip_id);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.gtfs.model.StopTime> toCacheModel() {

		return _stopTime.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toEscapedModel() {
		return new StopTimeWrapper(_stopTime.toEscapedModel());
	}

	@Override
	public String toString() {
		return _stopTime.toString();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toUnescapedModel() {
		return new StopTimeWrapper(_stopTime.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _stopTime.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StopTimeWrapper)) {
			return false;
		}

		StopTimeWrapper stopTimeWrapper = (StopTimeWrapper)obj;

		if (Objects.equals(_stopTime, stopTimeWrapper._stopTime)) {
			return true;
		}

		return false;
	}

	@Override
	public StopTime getWrappedModel() {
		return _stopTime;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stopTime.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stopTime.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stopTime.resetOriginalValues();
	}

	private final StopTime _stopTime;

}
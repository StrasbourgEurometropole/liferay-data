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

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("trip_id", getTrip_id());
		attributes.put("arrival_time", getArrival_time());
		attributes.put("departure_time", getDeparture_time());
		attributes.put("stop_id", getStop_id());
		attributes.put("stop_sequence", getStop_sequence());
		attributes.put("pickup_type", getPickup_type());
		attributes.put("drop_off_type", getDrop_off_type());

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

		String trip_id = (String)attributes.get("trip_id");

		if (trip_id != null) {
			setTrip_id(trip_id);
		}

		Date arrival_time = (Date)attributes.get("arrival_time");

		if (arrival_time != null) {
			setArrival_time(arrival_time);
		}

		Date departure_time = (Date)attributes.get("departure_time");

		if (departure_time != null) {
			setDeparture_time(departure_time);
		}

		String stop_id = (String)attributes.get("stop_id");

		if (stop_id != null) {
			setStop_id(stop_id);
		}

		Integer stop_sequence = (Integer)attributes.get("stop_sequence");

		if (stop_sequence != null) {
			setStop_sequence(stop_sequence);
		}

		String pickup_type = (String)attributes.get("pickup_type");

		if (pickup_type != null) {
			setPickup_type(pickup_type);
		}

		String drop_off_type = (String)attributes.get("drop_off_type");

		if (drop_off_type != null) {
			setDrop_off_type(drop_off_type);
		}
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
	public ExpandoBridge getExpandoBridge() {
		return _stopTime.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.StopTime> toCacheModel() {
		return _stopTime.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toEscapedModel() {
		return new StopTimeWrapper(_stopTime.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toUnescapedModel() {
		return new StopTimeWrapper(_stopTime.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.StopTime stopTime) {
		return _stopTime.compareTo(stopTime);
	}

	/**
	* Returns the stop_sequence of this stop time.
	*
	* @return the stop_sequence of this stop time
	*/
	@Override
	public int getStop_sequence() {
		return _stopTime.getStop_sequence();
	}

	@Override
	public int hashCode() {
		return _stopTime.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stopTime.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StopTimeWrapper((StopTime)_stopTime.clone());
	}

	/**
	* Returns the drop_off_type of this stop time.
	*
	* @return the drop_off_type of this stop time
	*/
	@Override
	public java.lang.String getDrop_off_type() {
		return _stopTime.getDrop_off_type();
	}

	/**
	* Returns the pickup_type of this stop time.
	*
	* @return the pickup_type of this stop time
	*/
	@Override
	public java.lang.String getPickup_type() {
		return _stopTime.getPickup_type();
	}

	/**
	* Returns the stop_id of this stop time.
	*
	* @return the stop_id of this stop time
	*/
	@Override
	public java.lang.String getStop_id() {
		return _stopTime.getStop_id();
	}

	/**
	* Returns the trip_id of this stop time.
	*
	* @return the trip_id of this stop time
	*/
	@Override
	public java.lang.String getTrip_id() {
		return _stopTime.getTrip_id();
	}

	/**
	* Returns the uuid of this stop time.
	*
	* @return the uuid of this stop time
	*/
	@Override
	public java.lang.String getUuid() {
		return _stopTime.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _stopTime.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _stopTime.toXmlString();
	}

	/**
	* Returns the arrival_time of this stop time.
	*
	* @return the arrival_time of this stop time
	*/
	@Override
	public Date getArrival_time() {
		return _stopTime.getArrival_time();
	}

	/**
	* Returns the departure_time of this stop time.
	*
	* @return the departure_time of this stop time
	*/
	@Override
	public Date getDeparture_time() {
		return _stopTime.getDeparture_time();
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
	public void persist() {
		_stopTime.persist();
	}

	/**
	* Sets the arrival_time of this stop time.
	*
	* @param arrival_time the arrival_time of this stop time
	*/
	@Override
	public void setArrival_time(Date arrival_time) {
		_stopTime.setArrival_time(arrival_time);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stopTime.setCachedModel(cachedModel);
	}

	/**
	* Sets the departure_time of this stop time.
	*
	* @param departure_time the departure_time of this stop time
	*/
	@Override
	public void setDeparture_time(Date departure_time) {
		_stopTime.setDeparture_time(departure_time);
	}

	/**
	* Sets the drop_off_type of this stop time.
	*
	* @param drop_off_type the drop_off_type of this stop time
	*/
	@Override
	public void setDrop_off_type(java.lang.String drop_off_type) {
		_stopTime.setDrop_off_type(drop_off_type);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stopTime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stopTime.setExpandoBridgeAttributes(baseModel);
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
	* Sets the pickup_type of this stop time.
	*
	* @param pickup_type the pickup_type of this stop time
	*/
	@Override
	public void setPickup_type(java.lang.String pickup_type) {
		_stopTime.setPickup_type(pickup_type);
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
	public void setStop_id(java.lang.String stop_id) {
		_stopTime.setStop_id(stop_id);
	}

	/**
	* Sets the stop_sequence of this stop time.
	*
	* @param stop_sequence the stop_sequence of this stop time
	*/
	@Override
	public void setStop_sequence(int stop_sequence) {
		_stopTime.setStop_sequence(stop_sequence);
	}

	/**
	* Sets the trip_id of this stop time.
	*
	* @param trip_id the trip_id of this stop time
	*/
	@Override
	public void setTrip_id(java.lang.String trip_id) {
		_stopTime.setTrip_id(trip_id);
	}

	/**
	* Sets the uuid of this stop time.
	*
	* @param uuid the uuid of this stop time
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_stopTime.setUuid(uuid);
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
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
 * This class is a wrapper for {@link Trip}.
 * </p>
 *
 * @author Cedric Henry
 * @see Trip
 * @generated
 */
@ProviderType
public class TripWrapper implements Trip, ModelWrapper<Trip> {
	public TripWrapper(Trip trip) {
		_trip = trip;
	}

	@Override
	public Class<?> getModelClass() {
		return Trip.class;
	}

	@Override
	public String getModelClassName() {
		return Trip.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("route_id", getRoute_id());
		attributes.put("service_id", getService_id());
		attributes.put("trip_id", getTrip_id());
		attributes.put("trip_headsign", getTrip_headsign());
		attributes.put("direction_id", getDirection_id());
		attributes.put("block_id", getBlock_id());

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

		String route_id = (String)attributes.get("route_id");

		if (route_id != null) {
			setRoute_id(route_id);
		}

		String service_id = (String)attributes.get("service_id");

		if (service_id != null) {
			setService_id(service_id);
		}

		String trip_id = (String)attributes.get("trip_id");

		if (trip_id != null) {
			setTrip_id(trip_id);
		}

		String trip_headsign = (String)attributes.get("trip_headsign");

		if (trip_headsign != null) {
			setTrip_headsign(trip_headsign);
		}

		Boolean direction_id = (Boolean)attributes.get("direction_id");

		if (direction_id != null) {
			setDirection_id(direction_id);
		}

		Integer block_id = (Integer)attributes.get("block_id");

		if (block_id != null) {
			setBlock_id(block_id);
		}
	}

	/**
	* Returns the direction_id of this trip.
	*
	* @return the direction_id of this trip
	*/
	@Override
	public boolean getDirection_id() {
		return _trip.getDirection_id();
	}

	@Override
	public boolean isCachedModel() {
		return _trip.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this trip is direction_id.
	*
	* @return <code>true</code> if this trip is direction_id; <code>false</code> otherwise
	*/
	@Override
	public boolean isDirection_id() {
		return _trip.isDirection_id();
	}

	@Override
	public boolean isEscapedModel() {
		return _trip.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _trip.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _trip.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Trip> toCacheModel() {
		return _trip.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Trip toEscapedModel() {
		return new TripWrapper(_trip.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Trip toUnescapedModel() {
		return new TripWrapper(_trip.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Trip trip) {
		return _trip.compareTo(trip);
	}

	/**
	* Returns the block_id of this trip.
	*
	* @return the block_id of this trip
	*/
	@Override
	public int getBlock_id() {
		return _trip.getBlock_id();
	}

	@Override
	public int hashCode() {
		return _trip.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _trip.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TripWrapper((Trip)_trip.clone());
	}

	/**
	* Returns the route_id of this trip.
	*
	* @return the route_id of this trip
	*/
	@Override
	public java.lang.String getRoute_id() {
		return _trip.getRoute_id();
	}

	/**
	* Returns the service_id of this trip.
	*
	* @return the service_id of this trip
	*/
	@Override
	public java.lang.String getService_id() {
		return _trip.getService_id();
	}

	/**
	* Returns the trip_headsign of this trip.
	*
	* @return the trip_headsign of this trip
	*/
	@Override
	public java.lang.String getTrip_headsign() {
		return _trip.getTrip_headsign();
	}

	/**
	* Returns the trip_id of this trip.
	*
	* @return the trip_id of this trip
	*/
	@Override
	public java.lang.String getTrip_id() {
		return _trip.getTrip_id();
	}

	/**
	* Returns the uuid of this trip.
	*
	* @return the uuid of this trip
	*/
	@Override
	public java.lang.String getUuid() {
		return _trip.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _trip.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _trip.toXmlString();
	}

	/**
	* Returns the ID of this trip.
	*
	* @return the ID of this trip
	*/
	@Override
	public long getId() {
		return _trip.getId();
	}

	/**
	* Returns the primary key of this trip.
	*
	* @return the primary key of this trip
	*/
	@Override
	public long getPrimaryKey() {
		return _trip.getPrimaryKey();
	}

	@Override
	public void persist() {
		_trip.persist();
	}

	/**
	* Sets the block_id of this trip.
	*
	* @param block_id the block_id of this trip
	*/
	@Override
	public void setBlock_id(int block_id) {
		_trip.setBlock_id(block_id);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_trip.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this trip is direction_id.
	*
	* @param direction_id the direction_id of this trip
	*/
	@Override
	public void setDirection_id(boolean direction_id) {
		_trip.setDirection_id(direction_id);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_trip.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_trip.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_trip.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this trip.
	*
	* @param id the ID of this trip
	*/
	@Override
	public void setId(long id) {
		_trip.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_trip.setNew(n);
	}

	/**
	* Sets the primary key of this trip.
	*
	* @param primaryKey the primary key of this trip
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_trip.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_trip.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the route_id of this trip.
	*
	* @param route_id the route_id of this trip
	*/
	@Override
	public void setRoute_id(java.lang.String route_id) {
		_trip.setRoute_id(route_id);
	}

	/**
	* Sets the service_id of this trip.
	*
	* @param service_id the service_id of this trip
	*/
	@Override
	public void setService_id(java.lang.String service_id) {
		_trip.setService_id(service_id);
	}

	/**
	* Sets the trip_headsign of this trip.
	*
	* @param trip_headsign the trip_headsign of this trip
	*/
	@Override
	public void setTrip_headsign(java.lang.String trip_headsign) {
		_trip.setTrip_headsign(trip_headsign);
	}

	/**
	* Sets the trip_id of this trip.
	*
	* @param trip_id the trip_id of this trip
	*/
	@Override
	public void setTrip_id(java.lang.String trip_id) {
		_trip.setTrip_id(trip_id);
	}

	/**
	* Sets the uuid of this trip.
	*
	* @param uuid the uuid of this trip
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_trip.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TripWrapper)) {
			return false;
		}

		TripWrapper tripWrapper = (TripWrapper)obj;

		if (Objects.equals(_trip, tripWrapper._trip)) {
			return true;
		}

		return false;
	}

	@Override
	public Trip getWrappedModel() {
		return _trip;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _trip.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _trip.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_trip.resetOriginalValues();
	}

	private final Trip _trip;
}
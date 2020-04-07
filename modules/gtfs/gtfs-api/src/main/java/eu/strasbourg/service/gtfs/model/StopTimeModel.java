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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the StopTime service. Represents a row in the &quot;gtfs_StopTime&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.gtfs.model.impl.StopTimeImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see StopTime
 * @see eu.strasbourg.service.gtfs.model.impl.StopTimeImpl
 * @see eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl
 * @generated
 */
@ProviderType
public interface StopTimeModel extends BaseModel<StopTime> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a stop time model instance should use the {@link StopTime} interface instead.
	 */

	/**
	 * Returns the primary key of this stop time.
	 *
	 * @return the primary key of this stop time
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this stop time.
	 *
	 * @param primaryKey the primary key of this stop time
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this stop time.
	 *
	 * @return the uuid of this stop time
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this stop time.
	 *
	 * @param uuid the uuid of this stop time
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the ID of this stop time.
	 *
	 * @return the ID of this stop time
	 */
	public long getId();

	/**
	 * Sets the ID of this stop time.
	 *
	 * @param id the ID of this stop time
	 */
	public void setId(long id);

	/**
	 * Returns the trip_id of this stop time.
	 *
	 * @return the trip_id of this stop time
	 */
	@AutoEscape
	public String getTrip_id();

	/**
	 * Sets the trip_id of this stop time.
	 *
	 * @param trip_id the trip_id of this stop time
	 */
	public void setTrip_id(String trip_id);

	/**
	 * Returns the arrival_time of this stop time.
	 *
	 * @return the arrival_time of this stop time
	 */
	public Date getArrival_time();

	/**
	 * Sets the arrival_time of this stop time.
	 *
	 * @param arrival_time the arrival_time of this stop time
	 */
	public void setArrival_time(Date arrival_time);

	/**
	 * Returns the departure_time of this stop time.
	 *
	 * @return the departure_time of this stop time
	 */
	public Date getDeparture_time();

	/**
	 * Sets the departure_time of this stop time.
	 *
	 * @param departure_time the departure_time of this stop time
	 */
	public void setDeparture_time(Date departure_time);

	/**
	 * Returns the stop_id of this stop time.
	 *
	 * @return the stop_id of this stop time
	 */
	@AutoEscape
	public String getStop_id();

	/**
	 * Sets the stop_id of this stop time.
	 *
	 * @param stop_id the stop_id of this stop time
	 */
	public void setStop_id(String stop_id);

	/**
	 * Returns the stop_sequence of this stop time.
	 *
	 * @return the stop_sequence of this stop time
	 */
	public int getStop_sequence();

	/**
	 * Sets the stop_sequence of this stop time.
	 *
	 * @param stop_sequence the stop_sequence of this stop time
	 */
	public void setStop_sequence(int stop_sequence);

	/**
	 * Returns the pickup_type of this stop time.
	 *
	 * @return the pickup_type of this stop time
	 */
	@AutoEscape
	public String getPickup_type();

	/**
	 * Sets the pickup_type of this stop time.
	 *
	 * @param pickup_type the pickup_type of this stop time
	 */
	public void setPickup_type(String pickup_type);

	/**
	 * Returns the drop_off_type of this stop time.
	 *
	 * @return the drop_off_type of this stop time
	 */
	@AutoEscape
	public String getDrop_off_type();

	/**
	 * Sets the drop_off_type of this stop time.
	 *
	 * @param drop_off_type the drop_off_type of this stop time
	 */
	public void setDrop_off_type(String drop_off_type);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.StopTime stopTime);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.gtfs.model.StopTime> toCacheModel();

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toEscapedModel();

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
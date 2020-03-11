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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class StopTimeSoap implements Serializable {
	public static StopTimeSoap toSoapModel(StopTime model) {
		StopTimeSoap soapModel = new StopTimeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setTrip_id(model.getTrip_id());
		soapModel.setArrival_time(model.getArrival_time());
		soapModel.setDeparture_time(model.getDeparture_time());
		soapModel.setStop_id(model.getStop_id());
		soapModel.setStop_sequence(model.getStop_sequence());
		soapModel.setPickup_type(model.getPickup_type());
		soapModel.setDrop_off_type(model.getDrop_off_type());

		return soapModel;
	}

	public static StopTimeSoap[] toSoapModels(StopTime[] models) {
		StopTimeSoap[] soapModels = new StopTimeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StopTimeSoap[][] toSoapModels(StopTime[][] models) {
		StopTimeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StopTimeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StopTimeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StopTimeSoap[] toSoapModels(List<StopTime> models) {
		List<StopTimeSoap> soapModels = new ArrayList<StopTimeSoap>(models.size());

		for (StopTime model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StopTimeSoap[soapModels.size()]);
	}

	public StopTimeSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getTrip_id() {
		return _trip_id;
	}

	public void setTrip_id(String trip_id) {
		_trip_id = trip_id;
	}

	public Date getArrival_time() {
		return _arrival_time;
	}

	public void setArrival_time(Date arrival_time) {
		_arrival_time = arrival_time;
	}

	public Date getDeparture_time() {
		return _departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		_departure_time = departure_time;
	}

	public String getStop_id() {
		return _stop_id;
	}

	public void setStop_id(String stop_id) {
		_stop_id = stop_id;
	}

	public int getStop_sequence() {
		return _stop_sequence;
	}

	public void setStop_sequence(int stop_sequence) {
		_stop_sequence = stop_sequence;
	}

	public String getPickup_type() {
		return _pickup_type;
	}

	public void setPickup_type(String pickup_type) {
		_pickup_type = pickup_type;
	}

	public String getDrop_off_type() {
		return _drop_off_type;
	}

	public void setDrop_off_type(String drop_off_type) {
		_drop_off_type = drop_off_type;
	}

	private String _uuid;
	private long _id;
	private String _trip_id;
	private Date _arrival_time;
	private Date _departure_time;
	private String _stop_id;
	private int _stop_sequence;
	private String _pickup_type;
	private String _drop_off_type;
}
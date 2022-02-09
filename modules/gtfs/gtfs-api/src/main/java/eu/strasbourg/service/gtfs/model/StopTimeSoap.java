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

		soapModel.setId(model.getId());
		soapModel.setTrip_id(model.getTrip_id());
		soapModel.setStop_id(model.getStop_id());

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
		List<StopTimeSoap> soapModels = new ArrayList<StopTimeSoap>(
			models.size());

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

	public String getStop_id() {
		return _stop_id;
	}

	public void setStop_id(String stop_id) {
		_stop_id = stop_id;
	}

	private long _id;
	private String _trip_id;
	private String _stop_id;

}
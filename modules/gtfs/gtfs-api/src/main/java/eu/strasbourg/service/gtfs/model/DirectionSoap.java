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
public class DirectionSoap implements Serializable {
	public static DirectionSoap toSoapModel(Direction model) {
		DirectionSoap soapModel = new DirectionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDirectionId(model.getDirectionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setTripId(model.getTripId());
		soapModel.setStopId(model.getStopId());
		soapModel.setRouteId(model.getRouteId());
		soapModel.setDestinationName(model.getDestinationName());

		return soapModel;
	}

	public static DirectionSoap[] toSoapModels(Direction[] models) {
		DirectionSoap[] soapModels = new DirectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DirectionSoap[][] toSoapModels(Direction[][] models) {
		DirectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DirectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DirectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DirectionSoap[] toSoapModels(List<Direction> models) {
		List<DirectionSoap> soapModels = new ArrayList<DirectionSoap>(models.size());

		for (Direction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DirectionSoap[soapModels.size()]);
	}

	public DirectionSoap() {
	}

	public long getPrimaryKey() {
		return _directionId;
	}

	public void setPrimaryKey(long pk) {
		setDirectionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDirectionId() {
		return _directionId;
	}

	public void setDirectionId(long directionId) {
		_directionId = directionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getTripId() {
		return _tripId;
	}

	public void setTripId(String tripId) {
		_tripId = tripId;
	}

	public String getStopId() {
		return _stopId;
	}

	public void setStopId(String stopId) {
		_stopId = stopId;
	}

	public String getRouteId() {
		return _routeId;
	}

	public void setRouteId(String routeId) {
		_routeId = routeId;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	private String _uuid;
	private long _directionId;
	private long _groupId;
	private long _companyId;
	private String _tripId;
	private String _stopId;
	private String _routeId;
	private String _destinationName;
}
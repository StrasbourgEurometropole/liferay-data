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

import eu.strasbourg.service.gtfs.service.persistence.StopPK;

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
public class StopSoap implements Serializable {
	public static StopSoap toSoapModel(Stop model) {
		StopSoap soapModel = new StopSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setStop_id(model.getStop_id());
		soapModel.setStop_code(model.getStop_code());
		soapModel.setStop_lat(model.getStop_lat());
		soapModel.setStop_lon(model.getStop_lon());
		soapModel.setStop_name(model.getStop_name());
		soapModel.setStop_url(model.getStop_url());
		soapModel.setStop_desc(model.getStop_desc());

		return soapModel;
	}

	public static StopSoap[] toSoapModels(Stop[] models) {
		StopSoap[] soapModels = new StopSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StopSoap[][] toSoapModels(Stop[][] models) {
		StopSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StopSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StopSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StopSoap[] toSoapModels(List<Stop> models) {
		List<StopSoap> soapModels = new ArrayList<StopSoap>(models.size());

		for (Stop model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StopSoap[soapModels.size()]);
	}

	public StopSoap() {
	}

	public StopPK getPrimaryKey() {
		return new StopPK(_id, _stop_id);
	}

	public void setPrimaryKey(StopPK pk) {
		setId(pk.id);
		setStop_id(pk.stop_id);
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

	public String getStop_id() {
		return _stop_id;
	}

	public void setStop_id(String stop_id) {
		_stop_id = stop_id;
	}

	public String getStop_code() {
		return _stop_code;
	}

	public void setStop_code(String stop_code) {
		_stop_code = stop_code;
	}

	public long getStop_lat() {
		return _stop_lat;
	}

	public void setStop_lat(long stop_lat) {
		_stop_lat = stop_lat;
	}

	public long getStop_lon() {
		return _stop_lon;
	}

	public void setStop_lon(long stop_lon) {
		_stop_lon = stop_lon;
	}

	public String getStop_name() {
		return _stop_name;
	}

	public void setStop_name(String stop_name) {
		_stop_name = stop_name;
	}

	public String getStop_url() {
		return _stop_url;
	}

	public void setStop_url(String stop_url) {
		_stop_url = stop_url;
	}

	public String getStop_desc() {
		return _stop_desc;
	}

	public void setStop_desc(String stop_desc) {
		_stop_desc = stop_desc;
	}

	private String _uuid;
	private long _id;
	private String _stop_id;
	private String _stop_code;
	private long _stop_lat;
	private long _stop_lon;
	private String _stop_name;
	private String _stop_url;
	private String _stop_desc;
}
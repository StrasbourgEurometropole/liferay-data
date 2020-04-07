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
public class RouteSoap implements Serializable {
	public static RouteSoap toSoapModel(Route model) {
		RouteSoap soapModel = new RouteSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setRoute_id(model.getRoute_id());
		soapModel.setRoute_short_name(model.getRoute_short_name());
		soapModel.setRoute_long_name(model.getRoute_long_name());
		soapModel.setRoute_desc(model.getRoute_desc());
		soapModel.setRoute_type(model.getRoute_type());
		soapModel.setRoute_color(model.getRoute_color());
		soapModel.setRoute_text_color(model.getRoute_text_color());

		return soapModel;
	}

	public static RouteSoap[] toSoapModels(Route[] models) {
		RouteSoap[] soapModels = new RouteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RouteSoap[][] toSoapModels(Route[][] models) {
		RouteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RouteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RouteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RouteSoap[] toSoapModels(List<Route> models) {
		List<RouteSoap> soapModels = new ArrayList<RouteSoap>(models.size());

		for (Route model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RouteSoap[soapModels.size()]);
	}

	public RouteSoap() {
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

	public String getRoute_id() {
		return _route_id;
	}

	public void setRoute_id(String route_id) {
		_route_id = route_id;
	}

	public String getRoute_short_name() {
		return _route_short_name;
	}

	public void setRoute_short_name(String route_short_name) {
		_route_short_name = route_short_name;
	}

	public String getRoute_long_name() {
		return _route_long_name;
	}

	public void setRoute_long_name(String route_long_name) {
		_route_long_name = route_long_name;
	}

	public String getRoute_desc() {
		return _route_desc;
	}

	public void setRoute_desc(String route_desc) {
		_route_desc = route_desc;
	}

	public int getRoute_type() {
		return _route_type;
	}

	public void setRoute_type(int route_type) {
		_route_type = route_type;
	}

	public String getRoute_color() {
		return _route_color;
	}

	public void setRoute_color(String route_color) {
		_route_color = route_color;
	}

	public String getRoute_text_color() {
		return _route_text_color;
	}

	public void setRoute_text_color(String route_text_color) {
		_route_text_color = route_text_color;
	}

	private String _uuid;
	private long _id;
	private String _route_id;
	private String _route_short_name;
	private String _route_long_name;
	private String _route_desc;
	private int _route_type;
	private String _route_color;
	private String _route_text_color;
}
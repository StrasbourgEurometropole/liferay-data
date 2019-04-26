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
public class AgencySoap implements Serializable {
	public static AgencySoap toSoapModel(Agency model) {
		AgencySoap soapModel = new AgencySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setAgency_name(model.getAgency_name());
		soapModel.setAgency_url(model.getAgency_url());
		soapModel.setAgency_timezone(model.getAgency_timezone());
		soapModel.setAgency_phone(model.getAgency_phone());
		soapModel.setAgency_fare_url(model.getAgency_fare_url());
		soapModel.setAgency_lang(model.getAgency_lang());

		return soapModel;
	}

	public static AgencySoap[] toSoapModels(Agency[] models) {
		AgencySoap[] soapModels = new AgencySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AgencySoap[][] toSoapModels(Agency[][] models) {
		AgencySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AgencySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AgencySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AgencySoap[] toSoapModels(List<Agency> models) {
		List<AgencySoap> soapModels = new ArrayList<AgencySoap>(models.size());

		for (Agency model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AgencySoap[soapModels.size()]);
	}

	public AgencySoap() {
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

	public String getAgency_name() {
		return _agency_name;
	}

	public void setAgency_name(String agency_name) {
		_agency_name = agency_name;
	}

	public boolean getAgency_url() {
		return _agency_url;
	}

	public boolean isAgency_url() {
		return _agency_url;
	}

	public void setAgency_url(boolean agency_url) {
		_agency_url = agency_url;
	}

	public boolean getAgency_timezone() {
		return _agency_timezone;
	}

	public boolean isAgency_timezone() {
		return _agency_timezone;
	}

	public void setAgency_timezone(boolean agency_timezone) {
		_agency_timezone = agency_timezone;
	}

	public boolean getAgency_phone() {
		return _agency_phone;
	}

	public boolean isAgency_phone() {
		return _agency_phone;
	}

	public void setAgency_phone(boolean agency_phone) {
		_agency_phone = agency_phone;
	}

	public boolean getAgency_fare_url() {
		return _agency_fare_url;
	}

	public boolean isAgency_fare_url() {
		return _agency_fare_url;
	}

	public void setAgency_fare_url(boolean agency_fare_url) {
		_agency_fare_url = agency_fare_url;
	}

	public boolean getAgency_lang() {
		return _agency_lang;
	}

	public boolean isAgency_lang() {
		return _agency_lang;
	}

	public void setAgency_lang(boolean agency_lang) {
		_agency_lang = agency_lang;
	}

	private String _uuid;
	private long _id;
	private String _agency_name;
	private boolean _agency_url;
	private boolean _agency_timezone;
	private boolean _agency_phone;
	private boolean _agency_fare_url;
	private boolean _agency_lang;
}
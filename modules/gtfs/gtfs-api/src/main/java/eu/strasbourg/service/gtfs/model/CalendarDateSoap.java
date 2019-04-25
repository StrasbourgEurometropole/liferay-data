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
public class CalendarDateSoap implements Serializable {
	public static CalendarDateSoap toSoapModel(CalendarDate model) {
		CalendarDateSoap soapModel = new CalendarDateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setService_id(model.getService_id());
		soapModel.setDate(model.getDate());
		soapModel.setException_type(model.getException_type());

		return soapModel;
	}

	public static CalendarDateSoap[] toSoapModels(CalendarDate[] models) {
		CalendarDateSoap[] soapModels = new CalendarDateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarDateSoap[][] toSoapModels(CalendarDate[][] models) {
		CalendarDateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarDateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarDateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarDateSoap[] toSoapModels(List<CalendarDate> models) {
		List<CalendarDateSoap> soapModels = new ArrayList<CalendarDateSoap>(models.size());

		for (CalendarDate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarDateSoap[soapModels.size()]);
	}

	public CalendarDateSoap() {
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

	public String getService_id() {
		return _service_id;
	}

	public void setService_id(String service_id) {
		_service_id = service_id;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public int getException_type() {
		return _exception_type;
	}

	public void setException_type(int exception_type) {
		_exception_type = exception_type;
	}

	private String _uuid;
	private long _id;
	private String _service_id;
	private Date _date;
	private int _exception_type;
}
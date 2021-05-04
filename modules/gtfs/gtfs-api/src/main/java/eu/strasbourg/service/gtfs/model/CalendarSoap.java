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
public class CalendarSoap implements Serializable {

	public static CalendarSoap toSoapModel(Calendar model) {
		CalendarSoap soapModel = new CalendarSoap();

		soapModel.setId(model.getId());
		soapModel.setService_id(model.getService_id());
		soapModel.setStart_date(model.getStart_date());
		soapModel.setEnd_date(model.getEnd_date());

		return soapModel;
	}

	public static CalendarSoap[] toSoapModels(Calendar[] models) {
		CalendarSoap[] soapModels = new CalendarSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarSoap[][] toSoapModels(Calendar[][] models) {
		CalendarSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarSoap[] toSoapModels(List<Calendar> models) {
		List<CalendarSoap> soapModels = new ArrayList<CalendarSoap>(
			models.size());

		for (Calendar model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarSoap[soapModels.size()]);
	}

	public CalendarSoap() {
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

	public String getService_id() {
		return _service_id;
	}

	public void setService_id(String service_id) {
		_service_id = service_id;
	}

	public Date getStart_date() {
		return _start_date;
	}

	public void setStart_date(Date start_date) {
		_start_date = start_date;
	}

	public Date getEnd_date() {
		return _end_date;
	}

	public void setEnd_date(Date end_date) {
		_end_date = end_date;
	}

	private long _id;
	private String _service_id;
	private Date _start_date;
	private Date _end_date;

}
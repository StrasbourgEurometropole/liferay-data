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

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setService_id(model.getService_id());
		soapModel.setMonday(model.getMonday());
		soapModel.setTuesday(model.getTuesday());
		soapModel.setWednesday(model.getWednesday());
		soapModel.setThursday(model.getThursday());
		soapModel.setFriday(model.getFriday());
		soapModel.setSaturday(model.getSaturday());
		soapModel.setSunday(model.getSunday());
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
		List<CalendarSoap> soapModels = new ArrayList<CalendarSoap>(models.size());

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

	public boolean getMonday() {
		return _monday;
	}

	public boolean isMonday() {
		return _monday;
	}

	public void setMonday(boolean monday) {
		_monday = monday;
	}

	public boolean getTuesday() {
		return _tuesday;
	}

	public boolean isTuesday() {
		return _tuesday;
	}

	public void setTuesday(boolean tuesday) {
		_tuesday = tuesday;
	}

	public boolean getWednesday() {
		return _wednesday;
	}

	public boolean isWednesday() {
		return _wednesday;
	}

	public void setWednesday(boolean wednesday) {
		_wednesday = wednesday;
	}

	public boolean getThursday() {
		return _thursday;
	}

	public boolean isThursday() {
		return _thursday;
	}

	public void setThursday(boolean thursday) {
		_thursday = thursday;
	}

	public boolean getFriday() {
		return _friday;
	}

	public boolean isFriday() {
		return _friday;
	}

	public void setFriday(boolean friday) {
		_friday = friday;
	}

	public boolean getSaturday() {
		return _saturday;
	}

	public boolean isSaturday() {
		return _saturday;
	}

	public void setSaturday(boolean saturday) {
		_saturday = saturday;
	}

	public boolean getSunday() {
		return _sunday;
	}

	public boolean isSunday() {
		return _sunday;
	}

	public void setSunday(boolean sunday) {
		_sunday = sunday;
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

	private String _uuid;
	private long _id;
	private String _service_id;
	private boolean _monday;
	private boolean _tuesday;
	private boolean _wednesday;
	private boolean _thursday;
	private boolean _friday;
	private boolean _saturday;
	private boolean _sunday;
	private Date _start_date;
	private Date _end_date;
}
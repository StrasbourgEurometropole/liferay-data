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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class PublicHolidaySoap implements Serializable {
	public static PublicHolidaySoap toSoapModel(PublicHoliday model) {
		PublicHolidaySoap soapModel = new PublicHolidaySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPublicHolidayId(model.getPublicHolidayId());
		soapModel.setName(model.getName());
		soapModel.setDate(model.getDate());
		soapModel.setRecurrent(model.getRecurrent());

		return soapModel;
	}

	public static PublicHolidaySoap[] toSoapModels(PublicHoliday[] models) {
		PublicHolidaySoap[] soapModels = new PublicHolidaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PublicHolidaySoap[][] toSoapModels(PublicHoliday[][] models) {
		PublicHolidaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PublicHolidaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new PublicHolidaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PublicHolidaySoap[] toSoapModels(List<PublicHoliday> models) {
		List<PublicHolidaySoap> soapModels = new ArrayList<PublicHolidaySoap>(models.size());

		for (PublicHoliday model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PublicHolidaySoap[soapModels.size()]);
	}

	public PublicHolidaySoap() {
	}

	public long getPrimaryKey() {
		return _publicHolidayId;
	}

	public void setPrimaryKey(long pk) {
		setPublicHolidayId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPublicHolidayId() {
		return _publicHolidayId;
	}

	public void setPublicHolidayId(long publicHolidayId) {
		_publicHolidayId = publicHolidayId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public boolean getRecurrent() {
		return _recurrent;
	}

	public boolean isRecurrent() {
		return _recurrent;
	}

	public void setRecurrent(boolean recurrent) {
		_recurrent = recurrent;
	}

	private String _uuid;
	private long _publicHolidayId;
	private String _name;
	private Date _date;
	private boolean _recurrent;
}
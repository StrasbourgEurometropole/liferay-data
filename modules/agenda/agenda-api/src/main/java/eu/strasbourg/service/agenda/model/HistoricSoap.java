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

package eu.strasbourg.service.agenda.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
public class HistoricSoap implements Serializable {

	public static HistoricSoap toSoapModel(Historic model) {
		HistoricSoap soapModel = new HistoricSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEventId(model.getEventId());
		soapModel.setTitle(model.getTitle());
		soapModel.setSuppressionDate(model.getSuppressionDate());

		return soapModel;
	}

	public static HistoricSoap[] toSoapModels(Historic[] models) {
		HistoricSoap[] soapModels = new HistoricSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistoricSoap[][] toSoapModels(Historic[][] models) {
		HistoricSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistoricSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistoricSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistoricSoap[] toSoapModels(List<Historic> models) {
		List<HistoricSoap> soapModels = new ArrayList<HistoricSoap>(
			models.size());

		for (Historic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistoricSoap[soapModels.size()]);
	}

	public HistoricSoap() {
	}

	public long getPrimaryKey() {
		return _eventId;
	}

	public void setPrimaryKey(long pk) {
		setEventId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public Date getSuppressionDate() {
		return _suppressionDate;
	}

	public void setSuppressionDate(Date suppressionDate) {
		_suppressionDate = suppressionDate;
	}

	private String _uuid;
	private long _eventId;
	private String _title;
	private Date _suppressionDate;

}
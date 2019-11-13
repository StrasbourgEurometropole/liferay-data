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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.AgendaExportPeriodServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.AgendaExportPeriodServiceSoap
 * @generated
 */
@ProviderType
public class AgendaExportPeriodSoap implements Serializable {
	public static AgendaExportPeriodSoap toSoapModel(AgendaExportPeriod model) {
		AgendaExportPeriodSoap soapModel = new AgendaExportPeriodSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAgendaExportPeriodId(model.getAgendaExportPeriodId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setAgendaExportId(model.getAgendaExportId());

		return soapModel;
	}

	public static AgendaExportPeriodSoap[] toSoapModels(
		AgendaExportPeriod[] models) {
		AgendaExportPeriodSoap[] soapModels = new AgendaExportPeriodSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AgendaExportPeriodSoap[][] toSoapModels(
		AgendaExportPeriod[][] models) {
		AgendaExportPeriodSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AgendaExportPeriodSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AgendaExportPeriodSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AgendaExportPeriodSoap[] toSoapModels(
		List<AgendaExportPeriod> models) {
		List<AgendaExportPeriodSoap> soapModels = new ArrayList<AgendaExportPeriodSoap>(models.size());

		for (AgendaExportPeriod model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AgendaExportPeriodSoap[soapModels.size()]);
	}

	public AgendaExportPeriodSoap() {
	}

	public long getPrimaryKey() {
		return _agendaExportPeriodId;
	}

	public void setPrimaryKey(long pk) {
		setAgendaExportPeriodId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAgendaExportPeriodId() {
		return _agendaExportPeriodId;
	}

	public void setAgendaExportPeriodId(long agendaExportPeriodId) {
		_agendaExportPeriodId = agendaExportPeriodId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getAgendaExportId() {
		return _agendaExportId;
	}

	public void setAgendaExportId(long agendaExportId) {
		_agendaExportId = agendaExportId;
	}

	private String _uuid;
	private long _agendaExportPeriodId;
	private Date _startDate;
	private Date _endDate;
	private long _agendaExportId;
}
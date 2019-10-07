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
public class AlertSoap implements Serializable {
	public static AlertSoap toSoapModel(Alert model) {
		AlertSoap soapModel = new AlertSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAlertId(model.getAlertId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setArretId(model.getArretId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setLigneAndDirection(model.getLigneAndDirection());
		soapModel.setPerturbation(model.getPerturbation());

		return soapModel;
	}

	public static AlertSoap[] toSoapModels(Alert[] models) {
		AlertSoap[] soapModels = new AlertSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AlertSoap[][] toSoapModels(Alert[][] models) {
		AlertSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AlertSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AlertSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AlertSoap[] toSoapModels(List<Alert> models) {
		List<AlertSoap> soapModels = new ArrayList<AlertSoap>(models.size());

		for (Alert model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AlertSoap[soapModels.size()]);
	}

	public AlertSoap() {
	}

	public long getPrimaryKey() {
		return _alertId;
	}

	public void setPrimaryKey(long pk) {
		setAlertId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAlertId() {
		return _alertId;
	}

	public void setAlertId(long alertId) {
		_alertId = alertId;
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

	public long getArretId() {
		return _arretId;
	}

	public void setArretId(long arretId) {
		_arretId = arretId;
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

	public String getLigneAndDirection() {
		return _ligneAndDirection;
	}

	public void setLigneAndDirection(String ligneAndDirection) {
		_ligneAndDirection = ligneAndDirection;
	}

	public String getPerturbation() {
		return _perturbation;
	}

	public void setPerturbation(String perturbation) {
		_perturbation = perturbation;
	}

	private String _uuid;
	private long _alertId;
	private long _groupId;
	private long _companyId;
	private long _arretId;
	private Date _startDate;
	private Date _endDate;
	private String _ligneAndDirection;
	private String _perturbation;
}
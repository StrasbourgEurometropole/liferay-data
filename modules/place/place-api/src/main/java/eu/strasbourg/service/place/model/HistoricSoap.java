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
public class HistoricSoap implements Serializable {

	public static HistoricSoap toSoapModel(Historic model) {
		HistoricSoap soapModel = new HistoricSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSigId(model.getSigId());
		soapModel.setName(model.getName());
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

	public String getPrimaryKey() {
		return _sigId;
	}

	public void setPrimaryKey(String pk) {
		setSigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getSigId() {
		return _sigId;
	}

	public void setSigId(String sigId) {
		_sigId = sigId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Date getSuppressionDate() {
		return _suppressionDate;
	}

	public void setSuppressionDate(Date suppressionDate) {
		_suppressionDate = suppressionDate;
	}

	private String _uuid;
	private String _sigId;
	private String _name;
	private Date _suppressionDate;

}
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

package eu.strasbourg.service.strasbourg.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.strasbourg.service.http.StrasbourgServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.strasbourg.service.http.StrasbourgServiceSoap
 * @generated
 */
@ProviderType
public class StrasbourgSoap implements Serializable {
	public static StrasbourgSoap toSoapModel(Strasbourg model) {
		StrasbourgSoap soapModel = new StrasbourgSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());

		return soapModel;
	}

	public static StrasbourgSoap[] toSoapModels(Strasbourg[] models) {
		StrasbourgSoap[] soapModels = new StrasbourgSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StrasbourgSoap[][] toSoapModels(Strasbourg[][] models) {
		StrasbourgSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StrasbourgSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StrasbourgSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StrasbourgSoap[] toSoapModels(List<Strasbourg> models) {
		List<StrasbourgSoap> soapModels = new ArrayList<StrasbourgSoap>(models.size());

		for (Strasbourg model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StrasbourgSoap[soapModels.size()]);
	}

	public StrasbourgSoap() {
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

	private String _uuid;
	private long _id;
}
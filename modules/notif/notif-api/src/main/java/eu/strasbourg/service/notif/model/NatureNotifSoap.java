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

package eu.strasbourg.service.notif.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NatureNotifSoap implements Serializable {

	public static NatureNotifSoap toSoapModel(NatureNotif model) {
		NatureNotifSoap soapModel = new NatureNotifSoap();

		soapModel.setNatureId(model.getNatureId());
		soapModel.setServiceId(model.getServiceId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static NatureNotifSoap[] toSoapModels(NatureNotif[] models) {
		NatureNotifSoap[] soapModels = new NatureNotifSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NatureNotifSoap[][] toSoapModels(NatureNotif[][] models) {
		NatureNotifSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NatureNotifSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NatureNotifSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NatureNotifSoap[] toSoapModels(List<NatureNotif> models) {
		List<NatureNotifSoap> soapModels = new ArrayList<NatureNotifSoap>(
			models.size());

		for (NatureNotif model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NatureNotifSoap[soapModels.size()]);
	}

	public NatureNotifSoap() {
	}

	public long getPrimaryKey() {
		return _natureId;
	}

	public void setPrimaryKey(long pk) {
		setNatureId(pk);
	}

	public long getNatureId() {
		return _natureId;
	}

	public void setNatureId(long natureId) {
		_natureId = natureId;
	}

	public long getServiceId() {
		return _serviceId;
	}

	public void setServiceId(long serviceId) {
		_serviceId = serviceId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _natureId;
	private long _serviceId;
	private String _name;

}
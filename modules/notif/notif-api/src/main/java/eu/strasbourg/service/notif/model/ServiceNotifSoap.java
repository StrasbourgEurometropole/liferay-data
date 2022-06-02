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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ServiceNotifSoap implements Serializable {

	public static ServiceNotifSoap toSoapModel(ServiceNotif model) {
		ServiceNotifSoap soapModel = new ServiceNotifSoap();

		soapModel.setServiceId(model.getServiceId());
		soapModel.setOrganisationId(model.getOrganisationId());
		soapModel.setName(model.getName());
		soapModel.setPictoId(model.getPictoId());
		soapModel.setCsmapSubscriptionLabel(model.getCsmapSubscriptionLabel());
		soapModel.setCsmapSubscriptionMandatory(
			model.isCsmapSubscriptionMandatory());
		soapModel.setCsmapTopic(model.getCsmapTopic());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static ServiceNotifSoap[] toSoapModels(ServiceNotif[] models) {
		ServiceNotifSoap[] soapModels = new ServiceNotifSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceNotifSoap[][] toSoapModels(ServiceNotif[][] models) {
		ServiceNotifSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceNotifSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceNotifSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceNotifSoap[] toSoapModels(List<ServiceNotif> models) {
		List<ServiceNotifSoap> soapModels = new ArrayList<ServiceNotifSoap>(
			models.size());

		for (ServiceNotif model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceNotifSoap[soapModels.size()]);
	}

	public ServiceNotifSoap() {
	}

	public long getPrimaryKey() {
		return _serviceId;
	}

	public void setPrimaryKey(long pk) {
		setServiceId(pk);
	}

	public long getServiceId() {
		return _serviceId;
	}

	public void setServiceId(long serviceId) {
		_serviceId = serviceId;
	}

	public long getOrganisationId() {
		return _organisationId;
	}

	public void setOrganisationId(long organisationId) {
		_organisationId = organisationId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getPictoId() {
		return _pictoId;
	}

	public void setPictoId(long pictoId) {
		_pictoId = pictoId;
	}

	public String getCsmapSubscriptionLabel() {
		return _csmapSubscriptionLabel;
	}

	public void setCsmapSubscriptionLabel(String csmapSubscriptionLabel) {
		_csmapSubscriptionLabel = csmapSubscriptionLabel;
	}

	public boolean getCsmapSubscriptionMandatory() {
		return _csmapSubscriptionMandatory;
	}

	public boolean isCsmapSubscriptionMandatory() {
		return _csmapSubscriptionMandatory;
	}

	public void setCsmapSubscriptionMandatory(
		boolean csmapSubscriptionMandatory) {

		_csmapSubscriptionMandatory = csmapSubscriptionMandatory;
	}

	public String getCsmapTopic() {
		return _csmapTopic;
	}

	public void setCsmapTopic(String csmapTopic) {
		_csmapTopic = csmapTopic;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private long _serviceId;
	private long _organisationId;
	private String _name;
	private long _pictoId;
	private String _csmapSubscriptionLabel;
	private boolean _csmapSubscriptionMandatory;
	private String _csmapTopic;
	private Date _createDate;
	private Date _modifiedDate;

}
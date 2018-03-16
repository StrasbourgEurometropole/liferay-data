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

package eu.strasbourg.service.notification.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.notification.service.http.NotificationServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.notification.service.http.NotificationServiceSoap
 * @generated
 */
@ProviderType
public class NotificationSoap implements Serializable {
	public static NotificationSoap toSoapModel(Notification model) {
		NotificationSoap soapModel = new NotificationSoap();

		soapModel.setNotificationId(model.getNotificationId());
		soapModel.setTitle(model.getTitle());
		soapModel.setUrl(model.getUrl());
		soapModel.setAutomatic(model.getAutomatic());
		soapModel.setSingleUser(model.getSingleUser());
		soapModel.setSingleUserId(model.getSingleUserId());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setTypeId(model.getTypeId());

		return soapModel;
	}

	public static NotificationSoap[] toSoapModels(Notification[] models) {
		NotificationSoap[] soapModels = new NotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[][] toSoapModels(Notification[][] models) {
		NotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[] toSoapModels(List<Notification> models) {
		List<NotificationSoap> soapModels = new ArrayList<NotificationSoap>(models.size());

		for (Notification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationSoap[soapModels.size()]);
	}

	public NotificationSoap() {
	}

	public long getPrimaryKey() {
		return _notificationId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationId(pk);
	}

	public long getNotificationId() {
		return _notificationId;
	}

	public void setNotificationId(long notificationId) {
		_notificationId = notificationId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public boolean getAutomatic() {
		return _automatic;
	}

	public boolean isAutomatic() {
		return _automatic;
	}

	public void setAutomatic(boolean automatic) {
		_automatic = automatic;
	}

	public boolean getSingleUser() {
		return _singleUser;
	}

	public boolean isSingleUser() {
		return _singleUser;
	}

	public void setSingleUser(boolean singleUser) {
		_singleUser = singleUser;
	}

	public String getSingleUserId() {
		return _singleUserId;
	}

	public void setSingleUserId(String singleUserId) {
		_singleUserId = singleUserId;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	private long _notificationId;
	private String _title;
	private String _url;
	private boolean _automatic;
	private boolean _singleUser;
	private String _singleUserId;
	private Date _publicationDate;
	private Date _expirationDate;
	private int _status;
	private long _typeId;
}
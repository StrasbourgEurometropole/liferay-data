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

import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class UserNotificationStatusSoap implements Serializable {
	public static UserNotificationStatusSoap toSoapModel(
		UserNotificationStatus model) {
		UserNotificationStatusSoap soapModel = new UserNotificationStatusSoap();

		soapModel.setNotificationId(model.getNotificationId());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setRead(model.getRead());

		return soapModel;
	}

	public static UserNotificationStatusSoap[] toSoapModels(
		UserNotificationStatus[] models) {
		UserNotificationStatusSoap[] soapModels = new UserNotificationStatusSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationStatusSoap[][] toSoapModels(
		UserNotificationStatus[][] models) {
		UserNotificationStatusSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserNotificationStatusSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserNotificationStatusSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationStatusSoap[] toSoapModels(
		List<UserNotificationStatus> models) {
		List<UserNotificationStatusSoap> soapModels = new ArrayList<UserNotificationStatusSoap>(models.size());

		for (UserNotificationStatus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserNotificationStatusSoap[soapModels.size()]);
	}

	public UserNotificationStatusSoap() {
	}

	public UserNotificationStatusPK getPrimaryKey() {
		return new UserNotificationStatusPK(_notificationId, _publikUserId);
	}

	public void setPrimaryKey(UserNotificationStatusPK pk) {
		setNotificationId(pk.notificationId);
		setPublikUserId(pk.publikUserId);
	}

	public long getNotificationId() {
		return _notificationId;
	}

	public void setNotificationId(long notificationId) {
		_notificationId = notificationId;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	private long _notificationId;
	private String _publikUserId;
	private boolean _read;
}
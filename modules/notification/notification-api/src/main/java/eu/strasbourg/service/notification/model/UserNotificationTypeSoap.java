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

import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK;

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
public class UserNotificationTypeSoap implements Serializable {
	public static UserNotificationTypeSoap toSoapModel(
		UserNotificationType model) {
		UserNotificationTypeSoap soapModel = new UserNotificationTypeSoap();

		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setTypeId(model.getTypeId());

		return soapModel;
	}

	public static UserNotificationTypeSoap[] toSoapModels(
		UserNotificationType[] models) {
		UserNotificationTypeSoap[] soapModels = new UserNotificationTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationTypeSoap[][] toSoapModels(
		UserNotificationType[][] models) {
		UserNotificationTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserNotificationTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserNotificationTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationTypeSoap[] toSoapModels(
		List<UserNotificationType> models) {
		List<UserNotificationTypeSoap> soapModels = new ArrayList<UserNotificationTypeSoap>(models.size());

		for (UserNotificationType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserNotificationTypeSoap[soapModels.size()]);
	}

	public UserNotificationTypeSoap() {
	}

	public UserNotificationTypePK getPrimaryKey() {
		return new UserNotificationTypePK(_publikUserId, _typeId);
	}

	public void setPrimaryKey(UserNotificationTypePK pk) {
		setPublikUserId(pk.publikUserId);
		setTypeId(pk.typeId);
	}

	public long getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(long publikUserId) {
		_publikUserId = publikUserId;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	private long _publikUserId;
	private long _typeId;
}
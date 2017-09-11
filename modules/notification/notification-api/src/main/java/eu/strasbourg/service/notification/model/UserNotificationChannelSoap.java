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

import eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK;

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
public class UserNotificationChannelSoap implements Serializable {
	public static UserNotificationChannelSoap toSoapModel(
		UserNotificationChannel model) {
		UserNotificationChannelSoap soapModel = new UserNotificationChannelSoap();

		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setChannelId(model.getChannelId());

		return soapModel;
	}

	public static UserNotificationChannelSoap[] toSoapModels(
		UserNotificationChannel[] models) {
		UserNotificationChannelSoap[] soapModels = new UserNotificationChannelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationChannelSoap[][] toSoapModels(
		UserNotificationChannel[][] models) {
		UserNotificationChannelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserNotificationChannelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserNotificationChannelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserNotificationChannelSoap[] toSoapModels(
		List<UserNotificationChannel> models) {
		List<UserNotificationChannelSoap> soapModels = new ArrayList<UserNotificationChannelSoap>(models.size());

		for (UserNotificationChannel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserNotificationChannelSoap[soapModels.size()]);
	}

	public UserNotificationChannelSoap() {
	}

	public UserNotificationChannelPK getPrimaryKey() {
		return new UserNotificationChannelPK(_publikUserId, _channelId);
	}

	public void setPrimaryKey(UserNotificationChannelPK pk) {
		setPublikUserId(pk.publikUserId);
		setChannelId(pk.channelId);
	}

	public long getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(long publikUserId) {
		_publikUserId = publikUserId;
	}

	public long getChannelId() {
		return _channelId;
	}

	public void setChannelId(long channelId) {
		_channelId = channelId;
	}

	private long _publikUserId;
	private long _channelId;
}
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

package eu.strasbourg.service.interest.model;

import aQute.bnd.annotation.ProviderType;

import eu.strasbourg.service.interest.service.persistence.UserInterestPK;

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
public class UserInterestSoap implements Serializable {
	public static UserInterestSoap toSoapModel(UserInterest model) {
		UserInterestSoap soapModel = new UserInterestSoap();

		soapModel.setInterestId(model.getInterestId());
		soapModel.setPublikUserId(model.getPublikUserId());

		return soapModel;
	}

	public static UserInterestSoap[] toSoapModels(UserInterest[] models) {
		UserInterestSoap[] soapModels = new UserInterestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserInterestSoap[][] toSoapModels(UserInterest[][] models) {
		UserInterestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserInterestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserInterestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserInterestSoap[] toSoapModels(List<UserInterest> models) {
		List<UserInterestSoap> soapModels = new ArrayList<UserInterestSoap>(models.size());

		for (UserInterest model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserInterestSoap[soapModels.size()]);
	}

	public UserInterestSoap() {
	}

	public UserInterestPK getPrimaryKey() {
		return new UserInterestPK(_interestId, _publikUserId);
	}

	public void setPrimaryKey(UserInterestPK pk) {
		setInterestId(pk.interestId);
		setPublikUserId(pk.publikUserId);
	}

	public long getInterestId() {
		return _interestId;
	}

	public void setInterestId(long interestId) {
		_interestId = interestId;
	}

	public long getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(long publikUserId) {
		_publikUserId = publikUserId;
	}

	private long _interestId;
	private long _publikUserId;
}
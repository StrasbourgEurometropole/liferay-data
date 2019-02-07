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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.InitiativeHelpServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.InitiativeHelpServiceSoap
 * @generated
 */
@ProviderType
public class InitiativeHelpSoap implements Serializable {
	public static InitiativeHelpSoap toSoapModel(InitiativeHelp model) {
		InitiativeHelpSoap soapModel = new InitiativeHelpSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setInitiativeHelpId(model.getInitiativeHelpId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setInitiativeId(model.getInitiativeId());
		soapModel.setHelpTypes(model.getHelpTypes());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setMessage(model.getMessage());
		soapModel.setHelpDisplay(model.getHelpDisplay());

		return soapModel;
	}

	public static InitiativeHelpSoap[] toSoapModels(InitiativeHelp[] models) {
		InitiativeHelpSoap[] soapModels = new InitiativeHelpSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InitiativeHelpSoap[][] toSoapModels(InitiativeHelp[][] models) {
		InitiativeHelpSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InitiativeHelpSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InitiativeHelpSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InitiativeHelpSoap[] toSoapModels(List<InitiativeHelp> models) {
		List<InitiativeHelpSoap> soapModels = new ArrayList<InitiativeHelpSoap>(models.size());

		for (InitiativeHelp model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InitiativeHelpSoap[soapModels.size()]);
	}

	public InitiativeHelpSoap() {
	}

	public long getPrimaryKey() {
		return _initiativeHelpId;
	}

	public void setPrimaryKey(long pk) {
		setInitiativeHelpId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getInitiativeHelpId() {
		return _initiativeHelpId;
	}

	public void setInitiativeHelpId(long initiativeHelpId) {
		_initiativeHelpId = initiativeHelpId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
	}

	public long getInitiativeId() {
		return _initiativeId;
	}

	public void setInitiativeId(long initiativeId) {
		_initiativeId = initiativeId;
	}

	public String getHelpTypes() {
		return _helpTypes;
	}

	public void setHelpTypes(String helpTypes) {
		_helpTypes = helpTypes;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public boolean getHelpDisplay() {
		return _helpDisplay;
	}

	public boolean isHelpDisplay() {
		return _helpDisplay;
	}

	public void setHelpDisplay(boolean helpDisplay) {
		_helpDisplay = helpDisplay;
	}

	private String _uuid;
	private long _initiativeHelpId;
	private Date _createDate;
	private String _publikUserId;
	private long _initiativeId;
	private String _helpTypes;
	private long _groupId;
	private String _message;
	private boolean _helpDisplay;
}
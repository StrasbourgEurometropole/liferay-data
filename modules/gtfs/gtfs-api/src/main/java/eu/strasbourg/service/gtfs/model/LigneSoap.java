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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.gtfs.service.http.LigneServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.http.LigneServiceSoap
 * @generated
 */
@ProviderType
public class LigneSoap implements Serializable {
	public static LigneSoap toSoapModel(Ligne model) {
		LigneSoap soapModel = new LigneSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLigneId(model.getLigneId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setRouteId(model.getRouteId());
		soapModel.setShortName(model.getShortName());
		soapModel.setTitle(model.getTitle());
		soapModel.setType(model.getType());
		soapModel.setBackgroundColor(model.getBackgroundColor());
		soapModel.setTextColor(model.getTextColor());

		return soapModel;
	}

	public static LigneSoap[] toSoapModels(Ligne[] models) {
		LigneSoap[] soapModels = new LigneSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LigneSoap[][] toSoapModels(Ligne[][] models) {
		LigneSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LigneSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LigneSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LigneSoap[] toSoapModels(List<Ligne> models) {
		List<LigneSoap> soapModels = new ArrayList<LigneSoap>(models.size());

		for (Ligne model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LigneSoap[soapModels.size()]);
	}

	public LigneSoap() {
	}

	public long getPrimaryKey() {
		return _ligneId;
	}

	public void setPrimaryKey(long pk) {
		setLigneId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLigneId() {
		return _ligneId;
	}

	public void setLigneId(long ligneId) {
		_ligneId = ligneId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getRouteId() {
		return _routeId;
	}

	public void setRouteId(String routeId) {
		_routeId = routeId;
	}

	public String getShortName() {
		return _shortName;
	}

	public void setShortName(String shortName) {
		_shortName = shortName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getBackgroundColor() {
		return _backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		_backgroundColor = backgroundColor;
	}

	public String getTextColor() {
		return _textColor;
	}

	public void setTextColor(String textColor) {
		_textColor = textColor;
	}

	private String _uuid;
	private long _ligneId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _routeId;
	private String _shortName;
	private String _title;
	private int _type;
	private String _backgroundColor;
	private String _textColor;
}
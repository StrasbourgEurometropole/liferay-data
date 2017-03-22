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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.CampaignEventStatusServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.CampaignEventStatusServiceSoap
 * @generated
 */
@ProviderType
public class CampaignEventStatusSoap implements Serializable {
	public static CampaignEventStatusSoap toSoapModel(CampaignEventStatus model) {
		CampaignEventStatusSoap soapModel = new CampaignEventStatusSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setStatusId(model.getStatusId());
		soapModel.setStatus(model.getStatus());
		soapModel.setComment(model.getComment());
		soapModel.setDeletionDenied(model.getDeletionDenied());
		soapModel.setDate(model.getDate());
		soapModel.setCampaignEventId(model.getCampaignEventId());
		soapModel.setPreviousStatusId(model.getPreviousStatusId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());

		return soapModel;
	}

	public static CampaignEventStatusSoap[] toSoapModels(
		CampaignEventStatus[] models) {
		CampaignEventStatusSoap[] soapModels = new CampaignEventStatusSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CampaignEventStatusSoap[][] toSoapModels(
		CampaignEventStatus[][] models) {
		CampaignEventStatusSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CampaignEventStatusSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CampaignEventStatusSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CampaignEventStatusSoap[] toSoapModels(
		List<CampaignEventStatus> models) {
		List<CampaignEventStatusSoap> soapModels = new ArrayList<CampaignEventStatusSoap>(models.size());

		for (CampaignEventStatus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CampaignEventStatusSoap[soapModels.size()]);
	}

	public CampaignEventStatusSoap() {
	}

	public long getPrimaryKey() {
		return _statusId;
	}

	public void setPrimaryKey(long pk) {
		setStatusId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getStatusId() {
		return _statusId;
	}

	public void setStatusId(long statusId) {
		_statusId = statusId;
	}

	public Integer getStatus() {
		return _status;
	}

	public void setStatus(Integer status) {
		_status = status;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Boolean getDeletionDenied() {
		return _deletionDenied;
	}

	public void setDeletionDenied(Boolean deletionDenied) {
		_deletionDenied = deletionDenied;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public long getCampaignEventId() {
		return _campaignEventId;
	}

	public void setCampaignEventId(long campaignEventId) {
		_campaignEventId = campaignEventId;
	}

	public long getPreviousStatusId() {
		return _previousStatusId;
	}

	public void setPreviousStatusId(long previousStatusId) {
		_previousStatusId = previousStatusId;
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

	private String _uuid;
	private long _statusId;
	private Integer _status;
	private String _comment;
	private Boolean _deletionDenied;
	private Date _date;
	private long _campaignEventId;
	private long _previousStatusId;
	private long _userId;
	private String _userName;
}
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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.agenda.service.http.AgendaExportServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.http.AgendaExportServiceSoap
 * @generated
 */
@ProviderType
public class AgendaExportSoap implements Serializable {
	public static AgendaExportSoap toSoapModel(AgendaExport model) {
		AgendaExportSoap soapModel = new AgendaExportSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAgendaExportId(model.getAgendaExportId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setLanguage(model.getLanguage());
		soapModel.setEventCategories(model.getEventCategories());

		return soapModel;
	}

	public static AgendaExportSoap[] toSoapModels(AgendaExport[] models) {
		AgendaExportSoap[] soapModels = new AgendaExportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AgendaExportSoap[][] toSoapModels(AgendaExport[][] models) {
		AgendaExportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AgendaExportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AgendaExportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AgendaExportSoap[] toSoapModels(List<AgendaExport> models) {
		List<AgendaExportSoap> soapModels = new ArrayList<AgendaExportSoap>(models.size());

		for (AgendaExport model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AgendaExportSoap[soapModels.size()]);
	}

	public AgendaExportSoap() {
	}

	public long getPrimaryKey() {
		return _agendaExportId;
	}

	public void setPrimaryKey(long pk) {
		setAgendaExportId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAgendaExportId() {
		return _agendaExportId;
	}

	public void setAgendaExportId(long agendaExportId) {
		_agendaExportId = agendaExportId;
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

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getLanguage() {
		return _language;
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public String getEventCategories() {
		return _eventCategories;
	}

	public void setEventCategories(String eventCategories) {
		_eventCategories = eventCategories;
	}

	private String _uuid;
	private long _agendaExportId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _language;
	private String _eventCategories;
}
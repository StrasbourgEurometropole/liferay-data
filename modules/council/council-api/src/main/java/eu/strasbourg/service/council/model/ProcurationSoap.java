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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.council.service.http.ProcurationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ProcurationSoap implements Serializable {

	public static ProcurationSoap toSoapModel(Procuration model) {
		ProcurationSoap soapModel = new ProcurationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setProcurationId(model.getProcurationId());
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
		soapModel.setOfficialVotersId(model.getOfficialVotersId());
		soapModel.setOfficialUnavailableId(model.getOfficialUnavailableId());
		soapModel.setCouncilSessionId(model.getCouncilSessionId());
		soapModel.setIsAbsent(model.isIsAbsent());
		soapModel.setProcurationMode(model.getProcurationMode());
		soapModel.setPresential(model.getPresential());
		soapModel.setIsAfterVote(model.isIsAfterVote());
		soapModel.setStartHour(model.getStartHour());
		soapModel.setEndHour(model.getEndHour());
		soapModel.setStartDelib(model.getStartDelib());
		soapModel.setEndDelib(model.getEndDelib());
		soapModel.setOtherProcurationMode(model.getOtherProcurationMode());

		return soapModel;
	}

	public static ProcurationSoap[] toSoapModels(Procuration[] models) {
		ProcurationSoap[] soapModels = new ProcurationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProcurationSoap[][] toSoapModels(Procuration[][] models) {
		ProcurationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProcurationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProcurationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProcurationSoap[] toSoapModels(List<Procuration> models) {
		List<ProcurationSoap> soapModels = new ArrayList<ProcurationSoap>(
			models.size());

		for (Procuration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProcurationSoap[soapModels.size()]);
	}

	public ProcurationSoap() {
	}

	public long getPrimaryKey() {
		return _procurationId;
	}

	public void setPrimaryKey(long pk) {
		setProcurationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProcurationId() {
		return _procurationId;
	}

	public void setProcurationId(long procurationId) {
		_procurationId = procurationId;
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

	public long getOfficialVotersId() {
		return _officialVotersId;
	}

	public void setOfficialVotersId(long officialVotersId) {
		_officialVotersId = officialVotersId;
	}

	public long getOfficialUnavailableId() {
		return _officialUnavailableId;
	}

	public void setOfficialUnavailableId(long officialUnavailableId) {
		_officialUnavailableId = officialUnavailableId;
	}

	public long getCouncilSessionId() {
		return _councilSessionId;
	}

	public void setCouncilSessionId(long councilSessionId) {
		_councilSessionId = councilSessionId;
	}

	public boolean getIsAbsent() {
		return _isAbsent;
	}

	public boolean isIsAbsent() {
		return _isAbsent;
	}

	public void setIsAbsent(boolean isAbsent) {
		_isAbsent = isAbsent;
	}

	public int getProcurationMode() {
		return _procurationMode;
	}

	public void setProcurationMode(int procurationMode) {
		_procurationMode = procurationMode;
	}

	public int getPresential() {
		return _presential;
	}

	public void setPresential(int presential) {
		_presential = presential;
	}

	public boolean getIsAfterVote() {
		return _isAfterVote;
	}

	public boolean isIsAfterVote() {
		return _isAfterVote;
	}

	public void setIsAfterVote(boolean isAfterVote) {
		_isAfterVote = isAfterVote;
	}

	public Date getStartHour() {
		return _startHour;
	}

	public void setStartHour(Date startHour) {
		_startHour = startHour;
	}

	public Date getEndHour() {
		return _endHour;
	}

	public void setEndHour(Date endHour) {
		_endHour = endHour;
	}

	public long getStartDelib() {
		return _startDelib;
	}

	public void setStartDelib(long startDelib) {
		_startDelib = startDelib;
	}

	public long getEndDelib() {
		return _endDelib;
	}

	public void setEndDelib(long endDelib) {
		_endDelib = endDelib;
	}

	public String getOtherProcurationMode() {
		return _otherProcurationMode;
	}

	public void setOtherProcurationMode(String otherProcurationMode) {
		_otherProcurationMode = otherProcurationMode;
	}

	private String _uuid;
	private long _procurationId;
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
	private long _officialVotersId;
	private long _officialUnavailableId;
	private long _councilSessionId;
	private boolean _isAbsent;
	private int _procurationMode;
	private int _presential;
	private boolean _isAfterVote;
	private Date _startHour;
	private Date _endHour;
	private long _startDelib;
	private long _endDelib;
	private String _otherProcurationMode;

}
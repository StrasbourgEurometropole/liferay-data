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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.council.service.http.VoteServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.council.service.http.VoteServiceSoap
 * @generated
 */
@ProviderType
public class VoteSoap implements Serializable {
	public static VoteSoap toSoapModel(Vote model) {
		VoteSoap soapModel = new VoteSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setVoteId(model.getVoteId());
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
		soapModel.setOfficialId(model.getOfficialId());
		soapModel.setDeliberationId(model.getDeliberationId());
		soapModel.setOfficialProcurationId(model.getOfficialProcurationId());

		return soapModel;
	}

	public static VoteSoap[] toSoapModels(Vote[] models) {
		VoteSoap[] soapModels = new VoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VoteSoap[][] toSoapModels(Vote[][] models) {
		VoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VoteSoap[] toSoapModels(List<Vote> models) {
		List<VoteSoap> soapModels = new ArrayList<VoteSoap>(models.size());

		for (Vote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VoteSoap[soapModels.size()]);
	}

	public VoteSoap() {
	}

	public long getPrimaryKey() {
		return _voteId;
	}

	public void setPrimaryKey(long pk) {
		setVoteId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getVoteId() {
		return _voteId;
	}

	public void setVoteId(long voteId) {
		_voteId = voteId;
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

	public long getOfficialId() {
		return _officialId;
	}

	public void setOfficialId(long officialId) {
		_officialId = officialId;
	}

	public long getDeliberationId() {
		return _deliberationId;
	}

	public void setDeliberationId(long deliberationId) {
		_deliberationId = deliberationId;
	}

	public long getOfficialProcurationId() {
		return _officialProcurationId;
	}

	public void setOfficialProcurationId(long officialProcurationId) {
		_officialProcurationId = officialProcurationId;
	}

	private String _uuid;
	private long _voteId;
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
	private long _officialId;
	private long _deliberationId;
	private long _officialProcurationId;
}
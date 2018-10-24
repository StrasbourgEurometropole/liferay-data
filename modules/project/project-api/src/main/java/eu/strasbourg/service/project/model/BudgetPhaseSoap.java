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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.BudgetPhaseServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.BudgetPhaseServiceSoap
 * @generated
 */
@ProviderType
public class BudgetPhaseSoap implements Serializable {
	public static BudgetPhaseSoap toSoapModel(BudgetPhase model) {
		BudgetPhaseSoap soapModel = new BudgetPhaseSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setBudgetPhaseId(model.getBudgetPhaseId());
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
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setNumberOfVote(model.getNumberOfVote());
		soapModel.setIsActive(model.getIsActive());
		soapModel.setBeginDate(model.getBeginDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setBeginVoteDate(model.getBeginVoteDate());
		soapModel.setEndVoteDate(model.getEndVoteDate());

		return soapModel;
	}

	public static BudgetPhaseSoap[] toSoapModels(BudgetPhase[] models) {
		BudgetPhaseSoap[] soapModels = new BudgetPhaseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BudgetPhaseSoap[][] toSoapModels(BudgetPhase[][] models) {
		BudgetPhaseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BudgetPhaseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BudgetPhaseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BudgetPhaseSoap[] toSoapModels(List<BudgetPhase> models) {
		List<BudgetPhaseSoap> soapModels = new ArrayList<BudgetPhaseSoap>(models.size());

		for (BudgetPhase model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BudgetPhaseSoap[soapModels.size()]);
	}

	public BudgetPhaseSoap() {
	}

	public long getPrimaryKey() {
		return _budgetPhaseId;
	}

	public void setPrimaryKey(long pk) {
		setBudgetPhaseId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getBudgetPhaseId() {
		return _budgetPhaseId;
	}

	public void setBudgetPhaseId(long budgetPhaseId) {
		_budgetPhaseId = budgetPhaseId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getNumberOfVote() {
		return _numberOfVote;
	}

	public void setNumberOfVote(long numberOfVote) {
		_numberOfVote = numberOfVote;
	}

	public boolean getIsActive() {
		return _isActive;
	}

	public boolean isIsActive() {
		return _isActive;
	}

	public void setIsActive(boolean isActive) {
		_isActive = isActive;
	}

	public Date getBeginDate() {
		return _beginDate;
	}

	public void setBeginDate(Date beginDate) {
		_beginDate = beginDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getBeginVoteDate() {
		return _beginVoteDate;
	}

	public void setBeginVoteDate(Date beginVoteDate) {
		_beginVoteDate = beginVoteDate;
	}

	public Date getEndVoteDate() {
		return _endVoteDate;
	}

	public void setEndVoteDate(Date endVoteDate) {
		_endVoteDate = endVoteDate;
	}

	private String _uuid;
	private long _budgetPhaseId;
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
	private String _name;
	private String _description;
	private long _numberOfVote;
	private boolean _isActive;
	private Date _beginDate;
	private Date _endDate;
	private Date _beginVoteDate;
	private Date _endVoteDate;
}
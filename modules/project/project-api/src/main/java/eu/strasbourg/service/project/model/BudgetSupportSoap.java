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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.BudgetSupportServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.BudgetSupportServiceSoap
 * @generated
 */
@ProviderType
public class BudgetSupportSoap implements Serializable {
	public static BudgetSupportSoap toSoapModel(BudgetSupport model) {
		BudgetSupportSoap soapModel = new BudgetSupportSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setBudgetSupportId(model.getBudgetSupportId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setCitoyenLastName(model.getCitoyenLastName());
		soapModel.setCitoyenFirstname(model.getCitoyenFirstname());
		soapModel.setCitoyenBirthday(model.getCitoyenBirthday());
		soapModel.setCitoyenAddress(model.getCitoyenAddress());
		soapModel.setCitoyenMail(model.getCitoyenMail());
		soapModel.setCitoyenPostalCode(model.getCitoyenPostalCode());
		soapModel.setCitoyenMobilePhone(model.getCitoyenMobilePhone());
		soapModel.setCitoyenPhone(model.getCitoyenPhone());
		soapModel.setCitoyenCity(model.getCitoyenCity());
		soapModel.setPublikUserId(model.getPublikUserId());
		soapModel.setBudgetParticipatifId(model.getBudgetParticipatifId());

		return soapModel;
	}

	public static BudgetSupportSoap[] toSoapModels(BudgetSupport[] models) {
		BudgetSupportSoap[] soapModels = new BudgetSupportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BudgetSupportSoap[][] toSoapModels(BudgetSupport[][] models) {
		BudgetSupportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BudgetSupportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BudgetSupportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BudgetSupportSoap[] toSoapModels(List<BudgetSupport> models) {
		List<BudgetSupportSoap> soapModels = new ArrayList<BudgetSupportSoap>(models.size());

		for (BudgetSupport model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BudgetSupportSoap[soapModels.size()]);
	}

	public BudgetSupportSoap() {
	}

	public long getPrimaryKey() {
		return _budgetSupportId;
	}

	public void setPrimaryKey(long pk) {
		setBudgetSupportId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getBudgetSupportId() {
		return _budgetSupportId;
	}

	public void setBudgetSupportId(long budgetSupportId) {
		_budgetSupportId = budgetSupportId;
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

	public String getCitoyenLastName() {
		return _citoyenLastName;
	}

	public void setCitoyenLastName(String citoyenLastName) {
		_citoyenLastName = citoyenLastName;
	}

	public String getCitoyenFirstname() {
		return _citoyenFirstname;
	}

	public void setCitoyenFirstname(String citoyenFirstname) {
		_citoyenFirstname = citoyenFirstname;
	}

	public Date getCitoyenBirthday() {
		return _citoyenBirthday;
	}

	public void setCitoyenBirthday(Date citoyenBirthday) {
		_citoyenBirthday = citoyenBirthday;
	}

	public String getCitoyenAddress() {
		return _citoyenAddress;
	}

	public void setCitoyenAddress(String citoyenAddress) {
		_citoyenAddress = citoyenAddress;
	}

	public String getCitoyenMail() {
		return _citoyenMail;
	}

	public void setCitoyenMail(String citoyenMail) {
		_citoyenMail = citoyenMail;
	}

	public long getCitoyenPostalCode() {
		return _citoyenPostalCode;
	}

	public void setCitoyenPostalCode(long citoyenPostalCode) {
		_citoyenPostalCode = citoyenPostalCode;
	}

	public String getCitoyenMobilePhone() {
		return _citoyenMobilePhone;
	}

	public void setCitoyenMobilePhone(String citoyenMobilePhone) {
		_citoyenMobilePhone = citoyenMobilePhone;
	}

	public String getCitoyenPhone() {
		return _citoyenPhone;
	}

	public void setCitoyenPhone(String citoyenPhone) {
		_citoyenPhone = citoyenPhone;
	}

	public String getCitoyenCity() {
		return _citoyenCity;
	}

	public void setCitoyenCity(String citoyenCity) {
		_citoyenCity = citoyenCity;
	}

	public String getPublikUserId() {
		return _publikUserId;
	}

	public void setPublikUserId(String publikUserId) {
		_publikUserId = publikUserId;
	}

	public long getBudgetParticipatifId() {
		return _budgetParticipatifId;
	}

	public void setBudgetParticipatifId(long budgetParticipatifId) {
		_budgetParticipatifId = budgetParticipatifId;
	}

	private String _uuid;
	private long _budgetSupportId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _citoyenLastName;
	private String _citoyenFirstname;
	private Date _citoyenBirthday;
	private String _citoyenAddress;
	private String _citoyenMail;
	private long _citoyenPostalCode;
	private String _citoyenMobilePhone;
	private String _citoyenPhone;
	private String _citoyenCity;
	private String _publikUserId;
	private long _budgetParticipatifId;
}
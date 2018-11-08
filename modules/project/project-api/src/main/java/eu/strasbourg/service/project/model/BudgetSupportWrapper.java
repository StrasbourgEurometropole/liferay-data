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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link BudgetSupport}.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetSupport
 * @generated
 */
@ProviderType
public class BudgetSupportWrapper implements BudgetSupport,
	ModelWrapper<BudgetSupport> {
	public BudgetSupportWrapper(BudgetSupport budgetSupport) {
		_budgetSupport = budgetSupport;
	}

	@Override
	public Class<?> getModelClass() {
		return BudgetSupport.class;
	}

	@Override
	public String getModelClassName() {
		return BudgetSupport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("budgetSupportId", getBudgetSupportId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("citoyenLastName", getCitoyenLastName());
		attributes.put("citoyenFirstname", getCitoyenFirstname());
		attributes.put("citoyenBirthday", getCitoyenBirthday());
		attributes.put("citoyenAddress", getCitoyenAddress());
		attributes.put("citoyenMail", getCitoyenMail());
		attributes.put("citoyenPostalCode", getCitoyenPostalCode());
		attributes.put("citoyenMobilePhone", getCitoyenMobilePhone());
		attributes.put("citoyenPhone", getCitoyenPhone());
		attributes.put("citoyenCity", getCitoyenCity());
		attributes.put("citoyenSignatureDate", getCitoyenSignatureDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("budgetParticipatifId", getBudgetParticipatifId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long budgetSupportId = (Long)attributes.get("budgetSupportId");

		if (budgetSupportId != null) {
			setBudgetSupportId(budgetSupportId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String citoyenLastName = (String)attributes.get("citoyenLastName");

		if (citoyenLastName != null) {
			setCitoyenLastName(citoyenLastName);
		}

		String citoyenFirstname = (String)attributes.get("citoyenFirstname");

		if (citoyenFirstname != null) {
			setCitoyenFirstname(citoyenFirstname);
		}

		Date citoyenBirthday = (Date)attributes.get("citoyenBirthday");

		if (citoyenBirthday != null) {
			setCitoyenBirthday(citoyenBirthday);
		}

		String citoyenAddress = (String)attributes.get("citoyenAddress");

		if (citoyenAddress != null) {
			setCitoyenAddress(citoyenAddress);
		}

		String citoyenMail = (String)attributes.get("citoyenMail");

		if (citoyenMail != null) {
			setCitoyenMail(citoyenMail);
		}

		Long citoyenPostalCode = (Long)attributes.get("citoyenPostalCode");

		if (citoyenPostalCode != null) {
			setCitoyenPostalCode(citoyenPostalCode);
		}

		String citoyenMobilePhone = (String)attributes.get("citoyenMobilePhone");

		if (citoyenMobilePhone != null) {
			setCitoyenMobilePhone(citoyenMobilePhone);
		}

		String citoyenPhone = (String)attributes.get("citoyenPhone");

		if (citoyenPhone != null) {
			setCitoyenPhone(citoyenPhone);
		}

		String citoyenCity = (String)attributes.get("citoyenCity");

		if (citoyenCity != null) {
			setCitoyenCity(citoyenCity);
		}

		Date citoyenSignatureDate = (Date)attributes.get("citoyenSignatureDate");

		if (citoyenSignatureDate != null) {
			setCitoyenSignatureDate(citoyenSignatureDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long budgetParticipatifId = (Long)attributes.get("budgetParticipatifId");

		if (budgetParticipatifId != null) {
			setBudgetParticipatifId(budgetParticipatifId);
		}
	}

	/**
	* Returns <code>true</code> if this budget support is approved.
	*
	* @return <code>true</code> if this budget support is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _budgetSupport.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _budgetSupport.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this budget support is denied.
	*
	* @return <code>true</code> if this budget support is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _budgetSupport.isDenied();
	}

	/**
	* Returns <code>true</code> if this budget support is a draft.
	*
	* @return <code>true</code> if this budget support is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _budgetSupport.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _budgetSupport.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this budget support is expired.
	*
	* @return <code>true</code> if this budget support is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _budgetSupport.isExpired();
	}

	/**
	* Returns <code>true</code> if this budget support is inactive.
	*
	* @return <code>true</code> if this budget support is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _budgetSupport.isInactive();
	}

	/**
	* Returns <code>true</code> if this budget support is incomplete.
	*
	* @return <code>true</code> if this budget support is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _budgetSupport.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _budgetSupport.isNew();
	}

	/**
	* Returns <code>true</code> if this budget support is pending.
	*
	* @return <code>true</code> if this budget support is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _budgetSupport.isPending();
	}

	/**
	* Returns <code>true</code> if this budget support is scheduled.
	*
	* @return <code>true</code> if this budget support is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _budgetSupport.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _budgetSupport.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.BudgetSupport> toCacheModel() {
		return _budgetSupport.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetSupport toEscapedModel() {
		return new BudgetSupportWrapper(_budgetSupport.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.BudgetSupport toUnescapedModel() {
		return new BudgetSupportWrapper(_budgetSupport.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.BudgetSupport budgetSupport) {
		return _budgetSupport.compareTo(budgetSupport);
	}

	/**
	* Returns the status of this budget support.
	*
	* @return the status of this budget support
	*/
	@Override
	public int getStatus() {
		return _budgetSupport.getStatus();
	}

	@Override
	public int hashCode() {
		return _budgetSupport.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _budgetSupport.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BudgetSupportWrapper((BudgetSupport)_budgetSupport.clone());
	}

	/**
	* Returns the citoyen address of this budget support.
	*
	* @return the citoyen address of this budget support
	*/
	@Override
	public java.lang.String getCitoyenAddress() {
		return _budgetSupport.getCitoyenAddress();
	}

	/**
	* Returns the citoyen city of this budget support.
	*
	* @return the citoyen city of this budget support
	*/
	@Override
	public java.lang.String getCitoyenCity() {
		return _budgetSupport.getCitoyenCity();
	}

	/**
	* Returns the citoyen firstname of this budget support.
	*
	* @return the citoyen firstname of this budget support
	*/
	@Override
	public java.lang.String getCitoyenFirstname() {
		return _budgetSupport.getCitoyenFirstname();
	}

	/**
	* Returns the citoyen last name of this budget support.
	*
	* @return the citoyen last name of this budget support
	*/
	@Override
	public java.lang.String getCitoyenLastName() {
		return _budgetSupport.getCitoyenLastName();
	}

	/**
	* Returns the citoyen mail of this budget support.
	*
	* @return the citoyen mail of this budget support
	*/
	@Override
	public java.lang.String getCitoyenMail() {
		return _budgetSupport.getCitoyenMail();
	}

	/**
	* Returns the citoyen mobile phone of this budget support.
	*
	* @return the citoyen mobile phone of this budget support
	*/
	@Override
	public java.lang.String getCitoyenMobilePhone() {
		return _budgetSupport.getCitoyenMobilePhone();
	}

	/**
	* Returns the citoyen phone of this budget support.
	*
	* @return the citoyen phone of this budget support
	*/
	@Override
	public java.lang.String getCitoyenPhone() {
		return _budgetSupport.getCitoyenPhone();
	}

	/**
	* Returns the publik user ID of this budget support.
	*
	* @return the publik user ID of this budget support
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _budgetSupport.getPublikUserId();
	}

	/**
	* Returns the status by user name of this budget support.
	*
	* @return the status by user name of this budget support
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _budgetSupport.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this budget support.
	*
	* @return the status by user uuid of this budget support
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _budgetSupport.getStatusByUserUuid();
	}

	/**
	* Returns the uuid of this budget support.
	*
	* @return the uuid of this budget support
	*/
	@Override
	public java.lang.String getUuid() {
		return _budgetSupport.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _budgetSupport.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _budgetSupport.toXmlString();
	}

	/**
	* Returns the citoyen birthday of this budget support.
	*
	* @return the citoyen birthday of this budget support
	*/
	@Override
	public Date getCitoyenBirthday() {
		return _budgetSupport.getCitoyenBirthday();
	}

	/**
	* Returns the citoyen signature date of this budget support.
	*
	* @return the citoyen signature date of this budget support
	*/
	@Override
	public Date getCitoyenSignatureDate() {
		return _budgetSupport.getCitoyenSignatureDate();
	}

	/**
	* Returns the create date of this budget support.
	*
	* @return the create date of this budget support
	*/
	@Override
	public Date getCreateDate() {
		return _budgetSupport.getCreateDate();
	}

	/**
	* Returns the modified date of this budget support.
	*
	* @return the modified date of this budget support
	*/
	@Override
	public Date getModifiedDate() {
		return _budgetSupport.getModifiedDate();
	}

	/**
	* Returns the status date of this budget support.
	*
	* @return the status date of this budget support
	*/
	@Override
	public Date getStatusDate() {
		return _budgetSupport.getStatusDate();
	}

	/**
	* Returns the budget participatif ID of this budget support.
	*
	* @return the budget participatif ID of this budget support
	*/
	@Override
	public long getBudgetParticipatifId() {
		return _budgetSupport.getBudgetParticipatifId();
	}

	/**
	* Returns the budget support ID of this budget support.
	*
	* @return the budget support ID of this budget support
	*/
	@Override
	public long getBudgetSupportId() {
		return _budgetSupport.getBudgetSupportId();
	}

	/**
	* Returns the citoyen postal code of this budget support.
	*
	* @return the citoyen postal code of this budget support
	*/
	@Override
	public long getCitoyenPostalCode() {
		return _budgetSupport.getCitoyenPostalCode();
	}

	/**
	* Returns the company ID of this budget support.
	*
	* @return the company ID of this budget support
	*/
	@Override
	public long getCompanyId() {
		return _budgetSupport.getCompanyId();
	}

	/**
	* Returns the group ID of this budget support.
	*
	* @return the group ID of this budget support
	*/
	@Override
	public long getGroupId() {
		return _budgetSupport.getGroupId();
	}

	/**
	* Returns the primary key of this budget support.
	*
	* @return the primary key of this budget support
	*/
	@Override
	public long getPrimaryKey() {
		return _budgetSupport.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this budget support.
	*
	* @return the status by user ID of this budget support
	*/
	@Override
	public long getStatusByUserId() {
		return _budgetSupport.getStatusByUserId();
	}

	@Override
	public void persist() {
		_budgetSupport.persist();
	}

	/**
	* Sets the budget participatif ID of this budget support.
	*
	* @param budgetParticipatifId the budget participatif ID of this budget support
	*/
	@Override
	public void setBudgetParticipatifId(long budgetParticipatifId) {
		_budgetSupport.setBudgetParticipatifId(budgetParticipatifId);
	}

	/**
	* Sets the budget support ID of this budget support.
	*
	* @param budgetSupportId the budget support ID of this budget support
	*/
	@Override
	public void setBudgetSupportId(long budgetSupportId) {
		_budgetSupport.setBudgetSupportId(budgetSupportId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_budgetSupport.setCachedModel(cachedModel);
	}

	/**
	* Sets the citoyen address of this budget support.
	*
	* @param citoyenAddress the citoyen address of this budget support
	*/
	@Override
	public void setCitoyenAddress(java.lang.String citoyenAddress) {
		_budgetSupport.setCitoyenAddress(citoyenAddress);
	}

	/**
	* Sets the citoyen birthday of this budget support.
	*
	* @param citoyenBirthday the citoyen birthday of this budget support
	*/
	@Override
	public void setCitoyenBirthday(Date citoyenBirthday) {
		_budgetSupport.setCitoyenBirthday(citoyenBirthday);
	}

	/**
	* Sets the citoyen city of this budget support.
	*
	* @param citoyenCity the citoyen city of this budget support
	*/
	@Override
	public void setCitoyenCity(java.lang.String citoyenCity) {
		_budgetSupport.setCitoyenCity(citoyenCity);
	}

	/**
	* Sets the citoyen firstname of this budget support.
	*
	* @param citoyenFirstname the citoyen firstname of this budget support
	*/
	@Override
	public void setCitoyenFirstname(java.lang.String citoyenFirstname) {
		_budgetSupport.setCitoyenFirstname(citoyenFirstname);
	}

	/**
	* Sets the citoyen last name of this budget support.
	*
	* @param citoyenLastName the citoyen last name of this budget support
	*/
	@Override
	public void setCitoyenLastName(java.lang.String citoyenLastName) {
		_budgetSupport.setCitoyenLastName(citoyenLastName);
	}

	/**
	* Sets the citoyen mail of this budget support.
	*
	* @param citoyenMail the citoyen mail of this budget support
	*/
	@Override
	public void setCitoyenMail(java.lang.String citoyenMail) {
		_budgetSupport.setCitoyenMail(citoyenMail);
	}

	/**
	* Sets the citoyen mobile phone of this budget support.
	*
	* @param citoyenMobilePhone the citoyen mobile phone of this budget support
	*/
	@Override
	public void setCitoyenMobilePhone(java.lang.String citoyenMobilePhone) {
		_budgetSupport.setCitoyenMobilePhone(citoyenMobilePhone);
	}

	/**
	* Sets the citoyen phone of this budget support.
	*
	* @param citoyenPhone the citoyen phone of this budget support
	*/
	@Override
	public void setCitoyenPhone(java.lang.String citoyenPhone) {
		_budgetSupport.setCitoyenPhone(citoyenPhone);
	}

	/**
	* Sets the citoyen postal code of this budget support.
	*
	* @param citoyenPostalCode the citoyen postal code of this budget support
	*/
	@Override
	public void setCitoyenPostalCode(long citoyenPostalCode) {
		_budgetSupport.setCitoyenPostalCode(citoyenPostalCode);
	}

	/**
	* Sets the citoyen signature date of this budget support.
	*
	* @param citoyenSignatureDate the citoyen signature date of this budget support
	*/
	@Override
	public void setCitoyenSignatureDate(Date citoyenSignatureDate) {
		_budgetSupport.setCitoyenSignatureDate(citoyenSignatureDate);
	}

	/**
	* Sets the company ID of this budget support.
	*
	* @param companyId the company ID of this budget support
	*/
	@Override
	public void setCompanyId(long companyId) {
		_budgetSupport.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this budget support.
	*
	* @param createDate the create date of this budget support
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_budgetSupport.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_budgetSupport.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_budgetSupport.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_budgetSupport.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this budget support.
	*
	* @param groupId the group ID of this budget support
	*/
	@Override
	public void setGroupId(long groupId) {
		_budgetSupport.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this budget support.
	*
	* @param modifiedDate the modified date of this budget support
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_budgetSupport.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_budgetSupport.setNew(n);
	}

	/**
	* Sets the primary key of this budget support.
	*
	* @param primaryKey the primary key of this budget support
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_budgetSupport.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_budgetSupport.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this budget support.
	*
	* @param publikUserId the publik user ID of this budget support
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_budgetSupport.setPublikUserId(publikUserId);
	}

	/**
	* Sets the status of this budget support.
	*
	* @param status the status of this budget support
	*/
	@Override
	public void setStatus(int status) {
		_budgetSupport.setStatus(status);
	}

	/**
	* Sets the status by user ID of this budget support.
	*
	* @param statusByUserId the status by user ID of this budget support
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_budgetSupport.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this budget support.
	*
	* @param statusByUserName the status by user name of this budget support
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_budgetSupport.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this budget support.
	*
	* @param statusByUserUuid the status by user uuid of this budget support
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_budgetSupport.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this budget support.
	*
	* @param statusDate the status date of this budget support
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_budgetSupport.setStatusDate(statusDate);
	}

	/**
	* Sets the uuid of this budget support.
	*
	* @param uuid the uuid of this budget support
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_budgetSupport.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BudgetSupportWrapper)) {
			return false;
		}

		BudgetSupportWrapper budgetSupportWrapper = (BudgetSupportWrapper)obj;

		if (Objects.equals(_budgetSupport, budgetSupportWrapper._budgetSupport)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _budgetSupport.getStagedModelType();
	}

	@Override
	public BudgetSupport getWrappedModel() {
		return _budgetSupport;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _budgetSupport.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _budgetSupport.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_budgetSupport.resetOriginalValues();
	}

	private final BudgetSupport _budgetSupport;
}
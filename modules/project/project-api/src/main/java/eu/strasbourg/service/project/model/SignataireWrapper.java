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
 * This class is a wrapper for {@link Signataire}.
 * </p>
 *
 * @author Cedric Henry
 * @see Signataire
 * @generated
 */
@ProviderType
public class SignataireWrapper implements Signataire, ModelWrapper<Signataire> {
	public SignataireWrapper(Signataire signataire) {
		_signataire = signataire;
	}

	@Override
	public Class<?> getModelClass() {
		return Signataire.class;
	}

	@Override
	public String getModelClassName() {
		return Signataire.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("signataireId", getSignataireId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("signataireName", getSignataireName());
		attributes.put("signataireFirstname", getSignataireFirstname());
		attributes.put("birthday", getBirthday());
		attributes.put("address", getAddress());
		attributes.put("mail", getMail());
		attributes.put("postalCode", getPostalCode());
		attributes.put("mobilePhone", getMobilePhone());
		attributes.put("phone", getPhone());
		attributes.put("city", getCity());
		attributes.put("signatureDate", getSignatureDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("petitionId", getPetitionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long signataireId = (Long)attributes.get("signataireId");

		if (signataireId != null) {
			setSignataireId(signataireId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
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

		String signataireName = (String)attributes.get("signataireName");

		if (signataireName != null) {
			setSignataireName(signataireName);
		}

		String signataireFirstname = (String)attributes.get(
				"signataireFirstname");

		if (signataireFirstname != null) {
			setSignataireFirstname(signataireFirstname);
		}

		Date birthday = (Date)attributes.get("birthday");

		if (birthday != null) {
			setBirthday(birthday);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		Long postalCode = (Long)attributes.get("postalCode");

		if (postalCode != null) {
			setPostalCode(postalCode);
		}

		String mobilePhone = (String)attributes.get("mobilePhone");

		if (mobilePhone != null) {
			setMobilePhone(mobilePhone);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		Date signatureDate = (Date)attributes.get("signatureDate");

		if (signatureDate != null) {
			setSignatureDate(signatureDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long petitionId = (Long)attributes.get("petitionId");

		if (petitionId != null) {
			setPetitionId(petitionId);
		}
	}

	/**
	* Returns <code>true</code> if this signataire is approved.
	*
	* @return <code>true</code> if this signataire is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _signataire.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _signataire.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this signataire is denied.
	*
	* @return <code>true</code> if this signataire is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _signataire.isDenied();
	}

	/**
	* Returns <code>true</code> if this signataire is a draft.
	*
	* @return <code>true</code> if this signataire is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _signataire.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _signataire.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this signataire is expired.
	*
	* @return <code>true</code> if this signataire is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _signataire.isExpired();
	}

	/**
	* Returns <code>true</code> if this signataire is inactive.
	*
	* @return <code>true</code> if this signataire is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _signataire.isInactive();
	}

	/**
	* Returns <code>true</code> if this signataire is incomplete.
	*
	* @return <code>true</code> if this signataire is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _signataire.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _signataire.isNew();
	}

	/**
	* Returns <code>true</code> if this signataire is pending.
	*
	* @return <code>true</code> if this signataire is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _signataire.isPending();
	}

	/**
	* Returns <code>true</code> if this signataire is scheduled.
	*
	* @return <code>true</code> if this signataire is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _signataire.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _signataire.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.Signataire> toCacheModel() {
		return _signataire.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.Signataire toEscapedModel() {
		return new SignataireWrapper(_signataire.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.Signataire toUnescapedModel() {
		return new SignataireWrapper(_signataire.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.Signataire signataire) {
		return _signataire.compareTo(signataire);
	}

	/**
	* Returns the status of this signataire.
	*
	* @return the status of this signataire
	*/
	@Override
	public int getStatus() {
		return _signataire.getStatus();
	}

	@Override
	public int hashCode() {
		return _signataire.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _signataire.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SignataireWrapper((Signataire)_signataire.clone());
	}

	/**
	* Returns the address of this signataire.
	*
	* @return the address of this signataire
	*/
	@Override
	public java.lang.String getAddress() {
		return _signataire.getAddress();
	}

	/**
	* Returns the city of this signataire.
	*
	* @return the city of this signataire
	*/
	@Override
	public java.lang.String getCity() {
		return _signataire.getCity();
	}

	/**
	* Returns the mail of this signataire.
	*
	* @return the mail of this signataire
	*/
	@Override
	public java.lang.String getMail() {
		return _signataire.getMail();
	}

	/**
	* Returns the mobile phone of this signataire.
	*
	* @return the mobile phone of this signataire
	*/
	@Override
	public java.lang.String getMobilePhone() {
		return _signataire.getMobilePhone();
	}

	/**
	* Returns the phone of this signataire.
	*
	* @return the phone of this signataire
	*/
	@Override
	public java.lang.String getPhone() {
		return _signataire.getPhone();
	}

	/**
	* Returns the publik user ID of this signataire.
	*
	* @return the publik user ID of this signataire
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _signataire.getPublikUserId();
	}

	/**
	* Returns the signataire firstname of this signataire.
	*
	* @return the signataire firstname of this signataire
	*/
	@Override
	public java.lang.String getSignataireFirstname() {
		return _signataire.getSignataireFirstname();
	}

	/**
	* Returns the signataire name of this signataire.
	*
	* @return the signataire name of this signataire
	*/
	@Override
	public java.lang.String getSignataireName() {
		return _signataire.getSignataireName();
	}

	/**
	* Returns the status by user name of this signataire.
	*
	* @return the status by user name of this signataire
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _signataire.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this signataire.
	*
	* @return the status by user uuid of this signataire
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _signataire.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this signataire.
	*
	* @return the user name of this signataire
	*/
	@Override
	public java.lang.String getUserName() {
		return _signataire.getUserName();
	}

	/**
	* Returns the user uuid of this signataire.
	*
	* @return the user uuid of this signataire
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _signataire.getUserUuid();
	}

	/**
	* Returns the uuid of this signataire.
	*
	* @return the uuid of this signataire
	*/
	@Override
	public java.lang.String getUuid() {
		return _signataire.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _signataire.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _signataire.toXmlString();
	}

	/**
	* Returns the birthday of this signataire.
	*
	* @return the birthday of this signataire
	*/
	@Override
	public Date getBirthday() {
		return _signataire.getBirthday();
	}

	/**
	* Returns the create date of this signataire.
	*
	* @return the create date of this signataire
	*/
	@Override
	public Date getCreateDate() {
		return _signataire.getCreateDate();
	}

	/**
	* Returns the modified date of this signataire.
	*
	* @return the modified date of this signataire
	*/
	@Override
	public Date getModifiedDate() {
		return _signataire.getModifiedDate();
	}

	/**
	* Returns the signature date of this signataire.
	*
	* @return the signature date of this signataire
	*/
	@Override
	public Date getSignatureDate() {
		return _signataire.getSignatureDate();
	}

	/**
	* Returns the status date of this signataire.
	*
	* @return the status date of this signataire
	*/
	@Override
	public Date getStatusDate() {
		return _signataire.getStatusDate();
	}

	/**
	* Returns the company ID of this signataire.
	*
	* @return the company ID of this signataire
	*/
	@Override
	public long getCompanyId() {
		return _signataire.getCompanyId();
	}

	/**
	* Returns the group ID of this signataire.
	*
	* @return the group ID of this signataire
	*/
	@Override
	public long getGroupId() {
		return _signataire.getGroupId();
	}

	/**
	* Returns the petition ID of this signataire.
	*
	* @return the petition ID of this signataire
	*/
	@Override
	public long getPetitionId() {
		return _signataire.getPetitionId();
	}

	/**
	* Returns the postal code of this signataire.
	*
	* @return the postal code of this signataire
	*/
	@Override
	public long getPostalCode() {
		return _signataire.getPostalCode();
	}

	/**
	* Returns the primary key of this signataire.
	*
	* @return the primary key of this signataire
	*/
	@Override
	public long getPrimaryKey() {
		return _signataire.getPrimaryKey();
	}

	/**
	* Returns the signataire ID of this signataire.
	*
	* @return the signataire ID of this signataire
	*/
	@Override
	public long getSignataireId() {
		return _signataire.getSignataireId();
	}

	/**
	* Returns the status by user ID of this signataire.
	*
	* @return the status by user ID of this signataire
	*/
	@Override
	public long getStatusByUserId() {
		return _signataire.getStatusByUserId();
	}

	/**
	* Returns the user ID of this signataire.
	*
	* @return the user ID of this signataire
	*/
	@Override
	public long getUserId() {
		return _signataire.getUserId();
	}

	@Override
	public void persist() {
		_signataire.persist();
	}

	/**
	* Sets the address of this signataire.
	*
	* @param address the address of this signataire
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_signataire.setAddress(address);
	}

	/**
	* Sets the birthday of this signataire.
	*
	* @param birthday the birthday of this signataire
	*/
	@Override
	public void setBirthday(Date birthday) {
		_signataire.setBirthday(birthday);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_signataire.setCachedModel(cachedModel);
	}

	/**
	* Sets the city of this signataire.
	*
	* @param city the city of this signataire
	*/
	@Override
	public void setCity(java.lang.String city) {
		_signataire.setCity(city);
	}

	/**
	* Sets the company ID of this signataire.
	*
	* @param companyId the company ID of this signataire
	*/
	@Override
	public void setCompanyId(long companyId) {
		_signataire.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this signataire.
	*
	* @param createDate the create date of this signataire
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_signataire.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_signataire.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_signataire.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_signataire.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this signataire.
	*
	* @param groupId the group ID of this signataire
	*/
	@Override
	public void setGroupId(long groupId) {
		_signataire.setGroupId(groupId);
	}

	/**
	* Sets the mail of this signataire.
	*
	* @param mail the mail of this signataire
	*/
	@Override
	public void setMail(java.lang.String mail) {
		_signataire.setMail(mail);
	}

	/**
	* Sets the mobile phone of this signataire.
	*
	* @param mobilePhone the mobile phone of this signataire
	*/
	@Override
	public void setMobilePhone(java.lang.String mobilePhone) {
		_signataire.setMobilePhone(mobilePhone);
	}

	/**
	* Sets the modified date of this signataire.
	*
	* @param modifiedDate the modified date of this signataire
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_signataire.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_signataire.setNew(n);
	}

	/**
	* Sets the petition ID of this signataire.
	*
	* @param petitionId the petition ID of this signataire
	*/
	@Override
	public void setPetitionId(long petitionId) {
		_signataire.setPetitionId(petitionId);
	}

	/**
	* Sets the phone of this signataire.
	*
	* @param phone the phone of this signataire
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_signataire.setPhone(phone);
	}

	/**
	* Sets the postal code of this signataire.
	*
	* @param postalCode the postal code of this signataire
	*/
	@Override
	public void setPostalCode(long postalCode) {
		_signataire.setPostalCode(postalCode);
	}

	/**
	* Sets the primary key of this signataire.
	*
	* @param primaryKey the primary key of this signataire
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_signataire.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_signataire.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this signataire.
	*
	* @param publikUserId the publik user ID of this signataire
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_signataire.setPublikUserId(publikUserId);
	}

	/**
	* Sets the signataire firstname of this signataire.
	*
	* @param signataireFirstname the signataire firstname of this signataire
	*/
	@Override
	public void setSignataireFirstname(java.lang.String signataireFirstname) {
		_signataire.setSignataireFirstname(signataireFirstname);
	}

	/**
	* Sets the signataire ID of this signataire.
	*
	* @param signataireId the signataire ID of this signataire
	*/
	@Override
	public void setSignataireId(long signataireId) {
		_signataire.setSignataireId(signataireId);
	}

	/**
	* Sets the signataire name of this signataire.
	*
	* @param signataireName the signataire name of this signataire
	*/
	@Override
	public void setSignataireName(java.lang.String signataireName) {
		_signataire.setSignataireName(signataireName);
	}

	/**
	* Sets the signature date of this signataire.
	*
	* @param signatureDate the signature date of this signataire
	*/
	@Override
	public void setSignatureDate(Date signatureDate) {
		_signataire.setSignatureDate(signatureDate);
	}

	/**
	* Sets the status of this signataire.
	*
	* @param status the status of this signataire
	*/
	@Override
	public void setStatus(int status) {
		_signataire.setStatus(status);
	}

	/**
	* Sets the status by user ID of this signataire.
	*
	* @param statusByUserId the status by user ID of this signataire
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_signataire.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this signataire.
	*
	* @param statusByUserName the status by user name of this signataire
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_signataire.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this signataire.
	*
	* @param statusByUserUuid the status by user uuid of this signataire
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_signataire.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this signataire.
	*
	* @param statusDate the status date of this signataire
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_signataire.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this signataire.
	*
	* @param userId the user ID of this signataire
	*/
	@Override
	public void setUserId(long userId) {
		_signataire.setUserId(userId);
	}

	/**
	* Sets the user name of this signataire.
	*
	* @param userName the user name of this signataire
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_signataire.setUserName(userName);
	}

	/**
	* Sets the user uuid of this signataire.
	*
	* @param userUuid the user uuid of this signataire
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_signataire.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this signataire.
	*
	* @param uuid the uuid of this signataire
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_signataire.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SignataireWrapper)) {
			return false;
		}

		SignataireWrapper signataireWrapper = (SignataireWrapper)obj;

		if (Objects.equals(_signataire, signataireWrapper._signataire)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _signataire.getStagedModelType();
	}

	@Override
	public Signataire getWrappedModel() {
		return _signataire;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _signataire.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _signataire.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_signataire.resetOriginalValues();
	}

	private final Signataire _signataire;
}
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
 * This class is a wrapper for {@link Official}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Official
 * @generated
 */
@ProviderType
public class OfficialWrapper implements Official, ModelWrapper<Official> {
	public OfficialWrapper(Official official) {
		_official = official;
	}

	@Override
	public Class<?> getModelClass() {
		return Official.class;
	}

	@Override
	public String getModelClassName() {
		return Official.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("officialId", getOfficialId());
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
		attributes.put("email", getEmail());
		attributes.put("firstname", getFirstname());
		attributes.put("lastname", getLastname());
		attributes.put("isMunicipal", getIsMunicipal());
		attributes.put("isEurometropolitan", getIsEurometropolitan());
		attributes.put("isActive", getIsActive());
		attributes.put("lastActivity", getLastActivity());
		attributes.put("lastSignInDeviceInfo", getLastSignInDeviceInfo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long officialId = (Long)attributes.get("officialId");

		if (officialId != null) {
			setOfficialId(officialId);
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

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String firstname = (String)attributes.get("firstname");

		if (firstname != null) {
			setFirstname(firstname);
		}

		String lastname = (String)attributes.get("lastname");

		if (lastname != null) {
			setLastname(lastname);
		}

		Boolean isMunicipal = (Boolean)attributes.get("isMunicipal");

		if (isMunicipal != null) {
			setIsMunicipal(isMunicipal);
		}

		Boolean isEurometropolitan = (Boolean)attributes.get(
				"isEurometropolitan");

		if (isEurometropolitan != null) {
			setIsEurometropolitan(isEurometropolitan);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		Date lastActivity = (Date)attributes.get("lastActivity");

		if (lastActivity != null) {
			setLastActivity(lastActivity);
		}

		String lastSignInDeviceInfo = (String)attributes.get(
				"lastSignInDeviceInfo");

		if (lastSignInDeviceInfo != null) {
			setLastSignInDeviceInfo(lastSignInDeviceInfo);
		}
	}

	/**
	* Returns the is active of this official.
	*
	* @return the is active of this official
	*/
	@Override
	public boolean getIsActive() {
		return _official.getIsActive();
	}

	/**
	* Returns the is eurometropolitan of this official.
	*
	* @return the is eurometropolitan of this official
	*/
	@Override
	public boolean getIsEurometropolitan() {
		return _official.getIsEurometropolitan();
	}

	/**
	* Returns the is municipal of this official.
	*
	* @return the is municipal of this official
	*/
	@Override
	public boolean getIsMunicipal() {
		return _official.getIsMunicipal();
	}

	/**
	* Returns <code>true</code> if this official is approved.
	*
	* @return <code>true</code> if this official is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _official.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _official.isCachedModel();
	}

	/**
	* Renvoie le statut de connection de l'utilisateur
	*
	* @return True si la dernière connection date de moins de 15sec
	*/
	@Override
	public boolean isConnected() {
		return _official.isConnected();
	}

	/**
	* Returns <code>true</code> if this official is denied.
	*
	* @return <code>true</code> if this official is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _official.isDenied();
	}

	/**
	* Returns <code>true</code> if this official is a draft.
	*
	* @return <code>true</code> if this official is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _official.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _official.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this official is expired.
	*
	* @return <code>true</code> if this official is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _official.isExpired();
	}

	/**
	* Returns <code>true</code> if this official is inactive.
	*
	* @return <code>true</code> if this official is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _official.isInactive();
	}

	/**
	* Returns <code>true</code> if this official is incomplete.
	*
	* @return <code>true</code> if this official is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _official.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this official is is active.
	*
	* @return <code>true</code> if this official is is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsActive() {
		return _official.isIsActive();
	}

	/**
	* Returns <code>true</code> if this official is is eurometropolitan.
	*
	* @return <code>true</code> if this official is is eurometropolitan; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsEurometropolitan() {
		return _official.isIsEurometropolitan();
	}

	/**
	* Returns <code>true</code> if this official is is municipal.
	*
	* @return <code>true</code> if this official is is municipal; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsMunicipal() {
		return _official.isIsMunicipal();
	}

	@Override
	public boolean isNew() {
		return _official.isNew();
	}

	/**
	* Renvoie si l'electeur est noté absent pour la session données
	*/
	@Override
	public boolean isNotedAbsent(long councilSessionId) {
		return _official.isNotedAbsent(councilSessionId);
	}

	/**
	* Returns <code>true</code> if this official is pending.
	*
	* @return <code>true</code> if this official is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _official.isPending();
	}

	/**
	* Returns <code>true</code> if this official is scheduled.
	*
	* @return <code>true</code> if this official is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _official.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _official.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _official.getExpandoBridge();
	}

	/**
	* Renvoie l'élu au format JSON
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _official.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.council.model.Official> toCacheModel() {
		return _official.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Official toEscapedModel() {
		return new OfficialWrapper(_official.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.council.model.Official toUnescapedModel() {
		return new OfficialWrapper(_official.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.council.model.Official official) {
		return _official.compareTo(official);
	}

	/**
	* Returns the status of this official.
	*
	* @return the status of this official
	*/
	@Override
	public int getStatus() {
		return _official.getStatus();
	}

	@Override
	public int hashCode() {
		return _official.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _official.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OfficialWrapper((Official)_official.clone());
	}

	/**
	* Returns the email of this official.
	*
	* @return the email of this official
	*/
	@Override
	public java.lang.String getEmail() {
		return _official.getEmail();
	}

	/**
	* Returns the firstname of this official.
	*
	* @return the firstname of this official
	*/
	@Override
	public java.lang.String getFirstname() {
		return _official.getFirstname();
	}

	/**
	* Renvoie le nom de complet au format "Prénom NOM"
	*/
	@Override
	public java.lang.String getFullName() {
		return _official.getFullName();
	}

	/**
	* Returns the last sign in device info of this official.
	*
	* @return the last sign in device info of this official
	*/
	@Override
	public java.lang.String getLastSignInDeviceInfo() {
		return _official.getLastSignInDeviceInfo();
	}

	/**
	* Returns the lastname of this official.
	*
	* @return the lastname of this official
	*/
	@Override
	public java.lang.String getLastname() {
		return _official.getLastname();
	}

	/**
	* Returns the status by user name of this official.
	*
	* @return the status by user name of this official
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _official.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this official.
	*
	* @return the status by user uuid of this official
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _official.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this official.
	*
	* @return the user name of this official
	*/
	@Override
	public java.lang.String getUserName() {
		return _official.getUserName();
	}

	/**
	* Returns the user uuid of this official.
	*
	* @return the user uuid of this official
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _official.getUserUuid();
	}

	/**
	* Returns the uuid of this official.
	*
	* @return the uuid of this official
	*/
	@Override
	public java.lang.String getUuid() {
		return _official.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _official.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _official.toXmlString();
	}

	/**
	* Returns the create date of this official.
	*
	* @return the create date of this official
	*/
	@Override
	public Date getCreateDate() {
		return _official.getCreateDate();
	}

	/**
	* Returns the last activity of this official.
	*
	* @return the last activity of this official
	*/
	@Override
	public Date getLastActivity() {
		return _official.getLastActivity();
	}

	/**
	* Returns the modified date of this official.
	*
	* @return the modified date of this official
	*/
	@Override
	public Date getModifiedDate() {
		return _official.getModifiedDate();
	}

	/**
	* Returns the status date of this official.
	*
	* @return the status date of this official
	*/
	@Override
	public Date getStatusDate() {
		return _official.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _official.getCategories();
	}

	/**
	* Renvoie les types de conseil rattachés à cet élu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Type> getCouncilTypes() {
		return _official.getCouncilTypes();
	}

	/**
	* Returns the company ID of this official.
	*
	* @return the company ID of this official
	*/
	@Override
	public long getCompanyId() {
		return _official.getCompanyId();
	}

	/**
	* Returns the group ID of this official.
	*
	* @return the group ID of this official
	*/
	@Override
	public long getGroupId() {
		return _official.getGroupId();
	}

	/**
	* Returns the official ID of this official.
	*
	* @return the official ID of this official
	*/
	@Override
	public long getOfficialId() {
		return _official.getOfficialId();
	}

	/**
	* Returns the primary key of this official.
	*
	* @return the primary key of this official
	*/
	@Override
	public long getPrimaryKey() {
		return _official.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this official.
	*
	* @return the status by user ID of this official
	*/
	@Override
	public long getStatusByUserId() {
		return _official.getStatusByUserId();
	}

	/**
	* Returns the user ID of this official.
	*
	* @return the user ID of this official
	*/
	@Override
	public long getUserId() {
		return _official.getUserId();
	}

	@Override
	public void persist() {
		_official.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_official.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this official.
	*
	* @param companyId the company ID of this official
	*/
	@Override
	public void setCompanyId(long companyId) {
		_official.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this official.
	*
	* @param createDate the create date of this official
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_official.setCreateDate(createDate);
	}

	/**
	* Sets the email of this official.
	*
	* @param email the email of this official
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_official.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_official.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_official.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_official.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the firstname of this official.
	*
	* @param firstname the firstname of this official
	*/
	@Override
	public void setFirstname(java.lang.String firstname) {
		_official.setFirstname(firstname);
	}

	/**
	* Sets the group ID of this official.
	*
	* @param groupId the group ID of this official
	*/
	@Override
	public void setGroupId(long groupId) {
		_official.setGroupId(groupId);
	}

	/**
	* Sets whether this official is is active.
	*
	* @param isActive the is active of this official
	*/
	@Override
	public void setIsActive(boolean isActive) {
		_official.setIsActive(isActive);
	}

	/**
	* Sets whether this official is is eurometropolitan.
	*
	* @param isEurometropolitan the is eurometropolitan of this official
	*/
	@Override
	public void setIsEurometropolitan(boolean isEurometropolitan) {
		_official.setIsEurometropolitan(isEurometropolitan);
	}

	/**
	* Sets whether this official is is municipal.
	*
	* @param isMunicipal the is municipal of this official
	*/
	@Override
	public void setIsMunicipal(boolean isMunicipal) {
		_official.setIsMunicipal(isMunicipal);
	}

	/**
	* Sets the last activity of this official.
	*
	* @param lastActivity the last activity of this official
	*/
	@Override
	public void setLastActivity(Date lastActivity) {
		_official.setLastActivity(lastActivity);
	}

	/**
	* Sets the last sign in device info of this official.
	*
	* @param lastSignInDeviceInfo the last sign in device info of this official
	*/
	@Override
	public void setLastSignInDeviceInfo(java.lang.String lastSignInDeviceInfo) {
		_official.setLastSignInDeviceInfo(lastSignInDeviceInfo);
	}

	/**
	* Sets the lastname of this official.
	*
	* @param lastname the lastname of this official
	*/
	@Override
	public void setLastname(java.lang.String lastname) {
		_official.setLastname(lastname);
	}

	/**
	* Sets the modified date of this official.
	*
	* @param modifiedDate the modified date of this official
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_official.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_official.setNew(n);
	}

	/**
	* Sets the official ID of this official.
	*
	* @param officialId the official ID of this official
	*/
	@Override
	public void setOfficialId(long officialId) {
		_official.setOfficialId(officialId);
	}

	/**
	* Sets the primary key of this official.
	*
	* @param primaryKey the primary key of this official
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_official.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_official.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this official.
	*
	* @param status the status of this official
	*/
	@Override
	public void setStatus(int status) {
		_official.setStatus(status);
	}

	/**
	* Sets the status by user ID of this official.
	*
	* @param statusByUserId the status by user ID of this official
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_official.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this official.
	*
	* @param statusByUserName the status by user name of this official
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_official.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this official.
	*
	* @param statusByUserUuid the status by user uuid of this official
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_official.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this official.
	*
	* @param statusDate the status date of this official
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_official.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this official.
	*
	* @param userId the user ID of this official
	*/
	@Override
	public void setUserId(long userId) {
		_official.setUserId(userId);
	}

	/**
	* Sets the user name of this official.
	*
	* @param userName the user name of this official
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_official.setUserName(userName);
	}

	/**
	* Sets the user uuid of this official.
	*
	* @param userUuid the user uuid of this official
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_official.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this official.
	*
	* @param uuid the uuid of this official
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_official.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfficialWrapper)) {
			return false;
		}

		OfficialWrapper officialWrapper = (OfficialWrapper)obj;

		if (Objects.equals(_official, officialWrapper._official)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _official.getStagedModelType();
	}

	@Override
	public Official getWrappedModel() {
		return _official;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _official.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _official.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_official.resetOriginalValues();
	}

	private final Official _official;
}
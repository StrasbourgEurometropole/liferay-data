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

package eu.strasbourg.service.activity.model;

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
 * This class is a wrapper for {@link Practice}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Practice
 * @generated
 */
@ProviderType
public class PracticeWrapper implements Practice, ModelWrapper<Practice> {
	public PracticeWrapper(Practice practice) {
		_practice = practice;
	}

	@Override
	public Class<?> getModelClass() {
		return Practice.class;
	}

	@Override
	public String getModelClassName() {
		return Practice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("practiceId", getPracticeId());
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
		attributes.put("associationId", getAssociationId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long practiceId = (Long)attributes.get("practiceId");

		if (practiceId != null) {
			setPracticeId(practiceId);
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

		Long associationId = (Long)attributes.get("associationId");

		if (associationId != null) {
			setAssociationId(associationId);
		}
	}

	/**
	* Returns <code>true</code> if this practice is approved.
	*
	* @return <code>true</code> if this practice is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _practice.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _practice.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this practice is denied.
	*
	* @return <code>true</code> if this practice is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _practice.isDenied();
	}

	/**
	* Returns <code>true</code> if this practice is a draft.
	*
	* @return <code>true</code> if this practice is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _practice.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _practice.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this practice is expired.
	*
	* @return <code>true</code> if this practice is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _practice.isExpired();
	}

	/**
	* Returns <code>true</code> if this practice is inactive.
	*
	* @return <code>true</code> if this practice is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _practice.isInactive();
	}

	/**
	* Returns <code>true</code> if this practice is incomplete.
	*
	* @return <code>true</code> if this practice is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _practice.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _practice.isNew();
	}

	/**
	* Returns <code>true</code> if this practice is pending.
	*
	* @return <code>true</code> if this practice is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _practice.isPending();
	}

	/**
	* Returns <code>true</code> if this practice is scheduled.
	*
	* @return <code>true</code> if this practice is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _practice.isScheduled();
	}

	/**
	* Retourne la pratique de l'association
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetCategory getPractice() {
		return _practice.getPractice();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _practice.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _practice.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.Practice> toCacheModel() {
		return _practice.toCacheModel();
	}

	/**
	* Retourne les activités de l'association
	*/
	@Override
	public eu.strasbourg.service.activity.model.Association getAssociation() {
		return _practice.getAssociation();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.Practice getLiveVersion() {
		return _practice.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.Practice toEscapedModel() {
		return new PracticeWrapper(_practice.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.Practice toUnescapedModel() {
		return new PracticeWrapper(_practice.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.activity.model.Practice practice) {
		return _practice.compareTo(practice);
	}

	/**
	* Returns the status of this practice.
	*
	* @return the status of this practice
	*/
	@Override
	public int getStatus() {
		return _practice.getStatus();
	}

	@Override
	public int hashCode() {
		return _practice.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _practice.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PracticeWrapper((Practice)_practice.clone());
	}

	/**
	* Returns the status by user name of this practice.
	*
	* @return the status by user name of this practice
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _practice.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this practice.
	*
	* @return the status by user uuid of this practice
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _practice.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this practice.
	*
	* @return the user name of this practice
	*/
	@Override
	public java.lang.String getUserName() {
		return _practice.getUserName();
	}

	/**
	* Returns the user uuid of this practice.
	*
	* @return the user uuid of this practice
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _practice.getUserUuid();
	}

	/**
	* Returns the uuid of this practice.
	*
	* @return the uuid of this practice
	*/
	@Override
	public java.lang.String getUuid() {
		return _practice.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _practice.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _practice.toXmlString();
	}

	/**
	* Returns the create date of this practice.
	*
	* @return the create date of this practice
	*/
	@Override
	public Date getCreateDate() {
		return _practice.getCreateDate();
	}

	/**
	* Returns the modified date of this practice.
	*
	* @return the modified date of this practice
	*/
	@Override
	public Date getModifiedDate() {
		return _practice.getModifiedDate();
	}

	/**
	* Returns the status date of this practice.
	*
	* @return the status date of this practice
	*/
	@Override
	public Date getStatusDate() {
		return _practice.getStatusDate();
	}

	/**
	* Retourne l'accessibilité de l'association
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getAccessibilities() {
		return _practice.getAccessibilities();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _practice.getCategories();
	}

	/**
	* Retourne les quartiers de l'association
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistricts() {
		return _practice.getDistricts();
	}

	/**
	* Retourne les publics de l'association
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics() {
		return _practice.getPublics();
	}

	/**
	* Returns the association ID of this practice.
	*
	* @return the association ID of this practice
	*/
	@Override
	public long getAssociationId() {
		return _practice.getAssociationId();
	}

	/**
	* Returns the company ID of this practice.
	*
	* @return the company ID of this practice
	*/
	@Override
	public long getCompanyId() {
		return _practice.getCompanyId();
	}

	/**
	* Returns the group ID of this practice.
	*
	* @return the group ID of this practice
	*/
	@Override
	public long getGroupId() {
		return _practice.getGroupId();
	}

	/**
	* Returns the practice ID of this practice.
	*
	* @return the practice ID of this practice
	*/
	@Override
	public long getPracticeId() {
		return _practice.getPracticeId();
	}

	/**
	* Returns the primary key of this practice.
	*
	* @return the primary key of this practice
	*/
	@Override
	public long getPrimaryKey() {
		return _practice.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this practice.
	*
	* @return the status by user ID of this practice
	*/
	@Override
	public long getStatusByUserId() {
		return _practice.getStatusByUserId();
	}

	/**
	* Returns the user ID of this practice.
	*
	* @return the user ID of this practice
	*/
	@Override
	public long getUserId() {
		return _practice.getUserId();
	}

	@Override
	public void persist() {
		_practice.persist();
	}

	/**
	* Sets the association ID of this practice.
	*
	* @param associationId the association ID of this practice
	*/
	@Override
	public void setAssociationId(long associationId) {
		_practice.setAssociationId(associationId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_practice.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this practice.
	*
	* @param companyId the company ID of this practice
	*/
	@Override
	public void setCompanyId(long companyId) {
		_practice.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this practice.
	*
	* @param createDate the create date of this practice
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_practice.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_practice.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_practice.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_practice.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this practice.
	*
	* @param groupId the group ID of this practice
	*/
	@Override
	public void setGroupId(long groupId) {
		_practice.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this practice.
	*
	* @param modifiedDate the modified date of this practice
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_practice.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_practice.setNew(n);
	}

	/**
	* Sets the practice ID of this practice.
	*
	* @param practiceId the practice ID of this practice
	*/
	@Override
	public void setPracticeId(long practiceId) {
		_practice.setPracticeId(practiceId);
	}

	/**
	* Sets the primary key of this practice.
	*
	* @param primaryKey the primary key of this practice
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_practice.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_practice.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this practice.
	*
	* @param status the status of this practice
	*/
	@Override
	public void setStatus(int status) {
		_practice.setStatus(status);
	}

	/**
	* Sets the status by user ID of this practice.
	*
	* @param statusByUserId the status by user ID of this practice
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_practice.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this practice.
	*
	* @param statusByUserName the status by user name of this practice
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_practice.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this practice.
	*
	* @param statusByUserUuid the status by user uuid of this practice
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_practice.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this practice.
	*
	* @param statusDate the status date of this practice
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_practice.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this practice.
	*
	* @param userId the user ID of this practice
	*/
	@Override
	public void setUserId(long userId) {
		_practice.setUserId(userId);
	}

	/**
	* Sets the user name of this practice.
	*
	* @param userName the user name of this practice
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_practice.setUserName(userName);
	}

	/**
	* Sets the user uuid of this practice.
	*
	* @param userUuid the user uuid of this practice
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_practice.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this practice.
	*
	* @param uuid the uuid of this practice
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_practice.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PracticeWrapper)) {
			return false;
		}

		PracticeWrapper practiceWrapper = (PracticeWrapper)obj;

		if (Objects.equals(_practice, practiceWrapper._practice)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _practice.getStagedModelType();
	}

	@Override
	public Practice getWrappedModel() {
		return _practice;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _practice.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _practice.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_practice.resetOriginalValues();
	}

	private final Practice _practice;
}
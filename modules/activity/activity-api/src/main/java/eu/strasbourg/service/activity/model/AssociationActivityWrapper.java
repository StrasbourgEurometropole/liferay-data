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
 * This class is a wrapper for {@link AssociationActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivity
 * @generated
 */
@ProviderType
public class AssociationActivityWrapper implements AssociationActivity,
	ModelWrapper<AssociationActivity> {
	public AssociationActivityWrapper(AssociationActivity associationActivity) {
		_associationActivity = associationActivity;
	}

	@Override
	public Class<?> getModelClass() {
		return AssociationActivity.class;
	}

	@Override
	public String getModelClassName() {
		return AssociationActivity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("associationActivityId", getAssociationActivityId());
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

		Long associationActivityId = (Long)attributes.get(
				"associationActivityId");

		if (associationActivityId != null) {
			setAssociationActivityId(associationActivityId);
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
	* Returns <code>true</code> if this association activity is approved.
	*
	* @return <code>true</code> if this association activity is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _associationActivity.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _associationActivity.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this association activity is denied.
	*
	* @return <code>true</code> if this association activity is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _associationActivity.isDenied();
	}

	/**
	* Returns <code>true</code> if this association activity is a draft.
	*
	* @return <code>true</code> if this association activity is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _associationActivity.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _associationActivity.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this association activity is expired.
	*
	* @return <code>true</code> if this association activity is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _associationActivity.isExpired();
	}

	/**
	* Returns <code>true</code> if this association activity is inactive.
	*
	* @return <code>true</code> if this association activity is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _associationActivity.isInactive();
	}

	/**
	* Returns <code>true</code> if this association activity is incomplete.
	*
	* @return <code>true</code> if this association activity is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _associationActivity.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _associationActivity.isNew();
	}

	/**
	* Returns <code>true</code> if this association activity is pending.
	*
	* @return <code>true</code> if this association activity is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _associationActivity.isPending();
	}

	/**
	* Returns <code>true</code> if this association activity is scheduled.
	*
	* @return <code>true</code> if this association activity is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _associationActivity.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _associationActivity.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _associationActivity.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.activity.model.AssociationActivity> toCacheModel() {
		return _associationActivity.toCacheModel();
	}

	/**
	* Retourne l'association de l'activité
	*/
	@Override
	public eu.strasbourg.service.activity.model.Association getAssociation() {
		return _associationActivity.getAssociation();
	}

	/**
	* Retourne la version live de cette entité
	*/
	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity getLiveVersion() {
		return _associationActivity.getLiveVersion();
	}

	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity toEscapedModel() {
		return new AssociationActivityWrapper(_associationActivity.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.activity.model.AssociationActivity toUnescapedModel() {
		return new AssociationActivityWrapper(_associationActivity.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.activity.model.AssociationActivity associationActivity) {
		return _associationActivity.compareTo(associationActivity);
	}

	/**
	* Returns the status of this association activity.
	*
	* @return the status of this association activity
	*/
	@Override
	public int getStatus() {
		return _associationActivity.getStatus();
	}

	@Override
	public int hashCode() {
		return _associationActivity.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _associationActivity.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssociationActivityWrapper((AssociationActivity)_associationActivity.clone());
	}

	/**
	* Retourne le label de l'activité de l'association
	*/
	@Override
	public java.lang.String getActivityLabel(java.util.Locale locale) {
		return _associationActivity.getActivityLabel(locale);
	}

	/**
	* Returns the status by user name of this association activity.
	*
	* @return the status by user name of this association activity
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _associationActivity.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this association activity.
	*
	* @return the status by user uuid of this association activity
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _associationActivity.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this association activity.
	*
	* @return the user name of this association activity
	*/
	@Override
	public java.lang.String getUserName() {
		return _associationActivity.getUserName();
	}

	/**
	* Returns the user uuid of this association activity.
	*
	* @return the user uuid of this association activity
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _associationActivity.getUserUuid();
	}

	/**
	* Returns the uuid of this association activity.
	*
	* @return the uuid of this association activity
	*/
	@Override
	public java.lang.String getUuid() {
		return _associationActivity.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _associationActivity.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _associationActivity.toXmlString();
	}

	/**
	* Returns the create date of this association activity.
	*
	* @return the create date of this association activity
	*/
	@Override
	public Date getCreateDate() {
		return _associationActivity.getCreateDate();
	}

	/**
	* Returns the modified date of this association activity.
	*
	* @return the modified date of this association activity
	*/
	@Override
	public Date getModifiedDate() {
		return _associationActivity.getModifiedDate();
	}

	/**
	* Returns the status date of this association activity.
	*
	* @return the status date of this association activity
	*/
	@Override
	public Date getStatusDate() {
		return _associationActivity.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _associationActivity.getCategories();
	}

	/**
	* Returns the association activity ID of this association activity.
	*
	* @return the association activity ID of this association activity
	*/
	@Override
	public long getAssociationActivityId() {
		return _associationActivity.getAssociationActivityId();
	}

	/**
	* Returns the association ID of this association activity.
	*
	* @return the association ID of this association activity
	*/
	@Override
	public long getAssociationId() {
		return _associationActivity.getAssociationId();
	}

	/**
	* Returns the company ID of this association activity.
	*
	* @return the company ID of this association activity
	*/
	@Override
	public long getCompanyId() {
		return _associationActivity.getCompanyId();
	}

	/**
	* Returns the group ID of this association activity.
	*
	* @return the group ID of this association activity
	*/
	@Override
	public long getGroupId() {
		return _associationActivity.getGroupId();
	}

	/**
	* Returns the primary key of this association activity.
	*
	* @return the primary key of this association activity
	*/
	@Override
	public long getPrimaryKey() {
		return _associationActivity.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this association activity.
	*
	* @return the status by user ID of this association activity
	*/
	@Override
	public long getStatusByUserId() {
		return _associationActivity.getStatusByUserId();
	}

	/**
	* Returns the user ID of this association activity.
	*
	* @return the user ID of this association activity
	*/
	@Override
	public long getUserId() {
		return _associationActivity.getUserId();
	}

	@Override
	public void persist() {
		_associationActivity.persist();
	}

	/**
	* Sets the association activity ID of this association activity.
	*
	* @param associationActivityId the association activity ID of this association activity
	*/
	@Override
	public void setAssociationActivityId(long associationActivityId) {
		_associationActivity.setAssociationActivityId(associationActivityId);
	}

	/**
	* Sets the association ID of this association activity.
	*
	* @param associationId the association ID of this association activity
	*/
	@Override
	public void setAssociationId(long associationId) {
		_associationActivity.setAssociationId(associationId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_associationActivity.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this association activity.
	*
	* @param companyId the company ID of this association activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_associationActivity.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this association activity.
	*
	* @param createDate the create date of this association activity
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_associationActivity.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_associationActivity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_associationActivity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_associationActivity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this association activity.
	*
	* @param groupId the group ID of this association activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_associationActivity.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this association activity.
	*
	* @param modifiedDate the modified date of this association activity
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_associationActivity.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_associationActivity.setNew(n);
	}

	/**
	* Sets the primary key of this association activity.
	*
	* @param primaryKey the primary key of this association activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_associationActivity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_associationActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this association activity.
	*
	* @param status the status of this association activity
	*/
	@Override
	public void setStatus(int status) {
		_associationActivity.setStatus(status);
	}

	/**
	* Sets the status by user ID of this association activity.
	*
	* @param statusByUserId the status by user ID of this association activity
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_associationActivity.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this association activity.
	*
	* @param statusByUserName the status by user name of this association activity
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_associationActivity.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this association activity.
	*
	* @param statusByUserUuid the status by user uuid of this association activity
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_associationActivity.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this association activity.
	*
	* @param statusDate the status date of this association activity
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_associationActivity.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this association activity.
	*
	* @param userId the user ID of this association activity
	*/
	@Override
	public void setUserId(long userId) {
		_associationActivity.setUserId(userId);
	}

	/**
	* Sets the user name of this association activity.
	*
	* @param userName the user name of this association activity
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_associationActivity.setUserName(userName);
	}

	/**
	* Sets the user uuid of this association activity.
	*
	* @param userUuid the user uuid of this association activity
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_associationActivity.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this association activity.
	*
	* @param uuid the uuid of this association activity
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_associationActivity.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssociationActivityWrapper)) {
			return false;
		}

		AssociationActivityWrapper associationActivityWrapper = (AssociationActivityWrapper)obj;

		if (Objects.equals(_associationActivity,
					associationActivityWrapper._associationActivity)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _associationActivity.getStagedModelType();
	}

	@Override
	public AssociationActivity getWrappedModel() {
		return _associationActivity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _associationActivity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _associationActivity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_associationActivity.resetOriginalValues();
	}

	private final AssociationActivity _associationActivity;
}
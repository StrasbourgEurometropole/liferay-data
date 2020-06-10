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
 * This class is a wrapper for {@link Type}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Type
 * @generated
 */
@ProviderType
public class TypeWrapper implements Type, ModelWrapper<Type> {
	public TypeWrapper(Type type) {
		_type = type;
	}

	@Override
	public Class<?> getModelClass() {
		return Type.class;
	}

	@Override
	public String getModelClassName() {
		return Type.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("typeId", getTypeId());
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
		attributes.put("title", getTitle());
		attributes.put("titleLong", getTitleLong());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String titleLong = (String)attributes.get("titleLong");

		if (titleLong != null) {
			setTitleLong(titleLong);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	/**
	* Returns <code>true</code> if this type is approved.
	*
	* @return <code>true</code> if this type is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _type.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _type.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this type is denied.
	*
	* @return <code>true</code> if this type is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _type.isDenied();
	}

	/**
	* Returns <code>true</code> if this type is a draft.
	*
	* @return <code>true</code> if this type is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _type.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _type.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this type is expired.
	*
	* @return <code>true</code> if this type is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _type.isExpired();
	}

	/**
	* Returns <code>true</code> if this type is inactive.
	*
	* @return <code>true</code> if this type is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _type.isInactive();
	}

	/**
	* Returns <code>true</code> if this type is incomplete.
	*
	* @return <code>true</code> if this type is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _type.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _type.isNew();
	}

	/**
	* Returns <code>true</code> if this type is pending.
	*
	* @return <code>true</code> if this type is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _type.isPending();
	}

	/**
	* Returns <code>true</code> if this type is scheduled.
	*
	* @return <code>true</code> if this type is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _type.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _type.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _type.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.council.model.Type> toCacheModel() {
		return _type.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.council.model.Type toEscapedModel() {
		return new TypeWrapper(_type.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.council.model.Type toUnescapedModel() {
		return new TypeWrapper(_type.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.council.model.Type type) {
		return _type.compareTo(type);
	}

	/**
	* Returns the status of this type.
	*
	* @return the status of this type
	*/
	@Override
	public int getStatus() {
		return _type.getStatus();
	}

	@Override
	public int hashCode() {
		return _type.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _type.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TypeWrapper((Type)_type.clone());
	}

	/**
	* Returns the status by user name of this type.
	*
	* @return the status by user name of this type
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _type.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this type.
	*
	* @return the status by user uuid of this type
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _type.getStatusByUserUuid();
	}

	/**
	* Returns the title of this type.
	*
	* @return the title of this type
	*/
	@Override
	public java.lang.String getTitle() {
		return _type.getTitle();
	}

	/**
	* Returns the title long of this type.
	*
	* @return the title long of this type
	*/
	@Override
	public java.lang.String getTitleLong() {
		return _type.getTitleLong();
	}

	/**
	* Returns the user name of this type.
	*
	* @return the user name of this type
	*/
	@Override
	public java.lang.String getUserName() {
		return _type.getUserName();
	}

	/**
	* Returns the user uuid of this type.
	*
	* @return the user uuid of this type
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _type.getUserUuid();
	}

	/**
	* Returns the uuid of this type.
	*
	* @return the uuid of this type
	*/
	@Override
	public java.lang.String getUuid() {
		return _type.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _type.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _type.toXmlString();
	}

	/**
	* Returns the create date of this type.
	*
	* @return the create date of this type
	*/
	@Override
	public Date getCreateDate() {
		return _type.getCreateDate();
	}

	/**
	* Returns the modified date of this type.
	*
	* @return the modified date of this type
	*/
	@Override
	public Date getModifiedDate() {
		return _type.getModifiedDate();
	}

	/**
	* Returns the status date of this type.
	*
	* @return the status date of this type
	*/
	@Override
	public Date getStatusDate() {
		return _type.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _type.getCategories();
	}

	/**
	* Returns the company ID of this type.
	*
	* @return the company ID of this type
	*/
	@Override
	public long getCompanyId() {
		return _type.getCompanyId();
	}

	/**
	* Returns the group ID of this type.
	*
	* @return the group ID of this type
	*/
	@Override
	public long getGroupId() {
		return _type.getGroupId();
	}

	/**
	* Returns the primary key of this type.
	*
	* @return the primary key of this type
	*/
	@Override
	public long getPrimaryKey() {
		return _type.getPrimaryKey();
	}

	/**
	* Returns the role ID of this type.
	*
	* @return the role ID of this type
	*/
	@Override
	public long getRoleId() {
		return _type.getRoleId();
	}

	/**
	* Returns the status by user ID of this type.
	*
	* @return the status by user ID of this type
	*/
	@Override
	public long getStatusByUserId() {
		return _type.getStatusByUserId();
	}

	/**
	* Returns the type ID of this type.
	*
	* @return the type ID of this type
	*/
	@Override
	public long getTypeId() {
		return _type.getTypeId();
	}

	/**
	* Returns the user ID of this type.
	*
	* @return the user ID of this type
	*/
	@Override
	public long getUserId() {
		return _type.getUserId();
	}

	@Override
	public void persist() {
		_type.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_type.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this type.
	*
	* @param companyId the company ID of this type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_type.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this type.
	*
	* @param createDate the create date of this type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_type.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_type.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_type.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_type.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this type.
	*
	* @param groupId the group ID of this type
	*/
	@Override
	public void setGroupId(long groupId) {
		_type.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this type.
	*
	* @param modifiedDate the modified date of this type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_type.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_type.setNew(n);
	}

	/**
	* Sets the primary key of this type.
	*
	* @param primaryKey the primary key of this type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_type.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_type.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role ID of this type.
	*
	* @param roleId the role ID of this type
	*/
	@Override
	public void setRoleId(long roleId) {
		_type.setRoleId(roleId);
	}

	/**
	* Sets the status of this type.
	*
	* @param status the status of this type
	*/
	@Override
	public void setStatus(int status) {
		_type.setStatus(status);
	}

	/**
	* Sets the status by user ID of this type.
	*
	* @param statusByUserId the status by user ID of this type
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_type.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this type.
	*
	* @param statusByUserName the status by user name of this type
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_type.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this type.
	*
	* @param statusByUserUuid the status by user uuid of this type
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_type.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this type.
	*
	* @param statusDate the status date of this type
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_type.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this type.
	*
	* @param title the title of this type
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_type.setTitle(title);
	}

	/**
	* Sets the title long of this type.
	*
	* @param titleLong the title long of this type
	*/
	@Override
	public void setTitleLong(java.lang.String titleLong) {
		_type.setTitleLong(titleLong);
	}

	/**
	* Sets the type ID of this type.
	*
	* @param typeId the type ID of this type
	*/
	@Override
	public void setTypeId(long typeId) {
		_type.setTypeId(typeId);
	}

	/**
	* Sets the user ID of this type.
	*
	* @param userId the user ID of this type
	*/
	@Override
	public void setUserId(long userId) {
		_type.setUserId(userId);
	}

	/**
	* Sets the user name of this type.
	*
	* @param userName the user name of this type
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_type.setUserName(userName);
	}

	/**
	* Sets the user uuid of this type.
	*
	* @param userUuid the user uuid of this type
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_type.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this type.
	*
	* @param uuid the uuid of this type
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_type.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TypeWrapper)) {
			return false;
		}

		TypeWrapper typeWrapper = (TypeWrapper)obj;

		if (Objects.equals(_type, typeWrapper._type)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _type.getStagedModelType();
	}

	@Override
	public Type getWrappedModel() {
		return _type;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _type.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _type.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_type.resetOriginalValues();
	}

	private final Type _type;
}
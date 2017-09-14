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

package eu.strasbourg.service.notification.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserNotificationType}.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationType
 * @generated
 */
@ProviderType
public class UserNotificationTypeWrapper implements UserNotificationType,
	ModelWrapper<UserNotificationType> {
	public UserNotificationTypeWrapper(
		UserNotificationType userNotificationType) {
		_userNotificationType = userNotificationType;
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationType.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("publikUserId", getPublikUserId());
		attributes.put("typeId", getTypeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _userNotificationType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userNotificationType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userNotificationType.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userNotificationType.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.notification.model.UserNotificationType> toCacheModel() {
		return _userNotificationType.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType toEscapedModel() {
		return new UserNotificationTypeWrapper(_userNotificationType.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationType toUnescapedModel() {
		return new UserNotificationTypeWrapper(_userNotificationType.toUnescapedModel());
	}

	/**
	* Returns the primary key of this user notification type.
	*
	* @return the primary key of this user notification type
	*/
	@Override
	public eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK getPrimaryKey() {
		return _userNotificationType.getPrimaryKey();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notification.model.UserNotificationType userNotificationType) {
		return _userNotificationType.compareTo(userNotificationType);
	}

	@Override
	public int hashCode() {
		return _userNotificationType.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userNotificationType.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserNotificationTypeWrapper((UserNotificationType)_userNotificationType.clone());
	}

	/**
	* Returns the publik user ID of this user notification type.
	*
	* @return the publik user ID of this user notification type
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _userNotificationType.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _userNotificationType.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userNotificationType.toXmlString();
	}

	/**
	* Returns the type ID of this user notification type.
	*
	* @return the type ID of this user notification type
	*/
	@Override
	public long getTypeId() {
		return _userNotificationType.getTypeId();
	}

	@Override
	public void persist() {
		_userNotificationType.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userNotificationType.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userNotificationType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userNotificationType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userNotificationType.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_userNotificationType.setNew(n);
	}

	/**
	* Sets the primary key of this user notification type.
	*
	* @param primaryKey the primary key of this user notification type
	*/
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK primaryKey) {
		_userNotificationType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userNotificationType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this user notification type.
	*
	* @param publikUserId the publik user ID of this user notification type
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_userNotificationType.setPublikUserId(publikUserId);
	}

	/**
	* Sets the type ID of this user notification type.
	*
	* @param typeId the type ID of this user notification type
	*/
	@Override
	public void setTypeId(long typeId) {
		_userNotificationType.setTypeId(typeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationTypeWrapper)) {
			return false;
		}

		UserNotificationTypeWrapper userNotificationTypeWrapper = (UserNotificationTypeWrapper)obj;

		if (Objects.equals(_userNotificationType,
					userNotificationTypeWrapper._userNotificationType)) {
			return true;
		}

		return false;
	}

	@Override
	public UserNotificationType getWrappedModel() {
		return _userNotificationType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userNotificationType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userNotificationType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userNotificationType.resetOriginalValues();
	}

	private final UserNotificationType _userNotificationType;
}
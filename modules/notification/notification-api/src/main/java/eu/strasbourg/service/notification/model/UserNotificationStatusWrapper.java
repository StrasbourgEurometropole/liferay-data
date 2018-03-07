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
 * This class is a wrapper for {@link UserNotificationStatus}.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationStatus
 * @generated
 */
@ProviderType
public class UserNotificationStatusWrapper implements UserNotificationStatus,
	ModelWrapper<UserNotificationStatus> {
	public UserNotificationStatusWrapper(
		UserNotificationStatus userNotificationStatus) {
		_userNotificationStatus = userNotificationStatus;
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationStatus.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationStatus.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("notificationId", getNotificationId());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("read", getRead());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long notificationId = (Long)attributes.get("notificationId");

		if (notificationId != null) {
			setNotificationId(notificationId);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}
	}

	/**
	* Returns the read of this user notification status.
	*
	* @return the read of this user notification status
	*/
	@Override
	public boolean getRead() {
		return _userNotificationStatus.getRead();
	}

	@Override
	public boolean isCachedModel() {
		return _userNotificationStatus.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userNotificationStatus.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userNotificationStatus.isNew();
	}

	/**
	* Returns <code>true</code> if this user notification status is read.
	*
	* @return <code>true</code> if this user notification status is read; <code>false</code> otherwise
	*/
	@Override
	public boolean isRead() {
		return _userNotificationStatus.isRead();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userNotificationStatus.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de l'objet
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _userNotificationStatus.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.notification.model.UserNotificationStatus> toCacheModel() {
		return _userNotificationStatus.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notification.model.Notification getNotification() {
		return _userNotificationStatus.getNotification();
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus toEscapedModel() {
		return new UserNotificationStatusWrapper(_userNotificationStatus.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationStatus toUnescapedModel() {
		return new UserNotificationStatusWrapper(_userNotificationStatus.toUnescapedModel());
	}

	/**
	* Returns the primary key of this user notification status.
	*
	* @return the primary key of this user notification status
	*/
	@Override
	public eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK getPrimaryKey() {
		return _userNotificationStatus.getPrimaryKey();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notification.model.UserNotificationStatus userNotificationStatus) {
		return _userNotificationStatus.compareTo(userNotificationStatus);
	}

	@Override
	public int hashCode() {
		return _userNotificationStatus.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userNotificationStatus.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserNotificationStatusWrapper((UserNotificationStatus)_userNotificationStatus.clone());
	}

	/**
	* Returns the publik user ID of this user notification status.
	*
	* @return the publik user ID of this user notification status
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _userNotificationStatus.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _userNotificationStatus.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userNotificationStatus.toXmlString();
	}

	/**
	* Returns the notification ID of this user notification status.
	*
	* @return the notification ID of this user notification status
	*/
	@Override
	public long getNotificationId() {
		return _userNotificationStatus.getNotificationId();
	}

	@Override
	public void persist() {
		_userNotificationStatus.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userNotificationStatus.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userNotificationStatus.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userNotificationStatus.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userNotificationStatus.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_userNotificationStatus.setNew(n);
	}

	/**
	* Sets the notification ID of this user notification status.
	*
	* @param notificationId the notification ID of this user notification status
	*/
	@Override
	public void setNotificationId(long notificationId) {
		_userNotificationStatus.setNotificationId(notificationId);
	}

	/**
	* Sets the primary key of this user notification status.
	*
	* @param primaryKey the primary key of this user notification status
	*/
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK primaryKey) {
		_userNotificationStatus.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userNotificationStatus.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this user notification status.
	*
	* @param publikUserId the publik user ID of this user notification status
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_userNotificationStatus.setPublikUserId(publikUserId);
	}

	/**
	* Sets whether this user notification status is read.
	*
	* @param read the read of this user notification status
	*/
	@Override
	public void setRead(boolean read) {
		_userNotificationStatus.setRead(read);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationStatusWrapper)) {
			return false;
		}

		UserNotificationStatusWrapper userNotificationStatusWrapper = (UserNotificationStatusWrapper)obj;

		if (Objects.equals(_userNotificationStatus,
					userNotificationStatusWrapper._userNotificationStatus)) {
			return true;
		}

		return false;
	}

	@Override
	public UserNotificationStatus getWrappedModel() {
		return _userNotificationStatus;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userNotificationStatus.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userNotificationStatus.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userNotificationStatus.resetOriginalValues();
	}

	private final UserNotificationStatus _userNotificationStatus;
}
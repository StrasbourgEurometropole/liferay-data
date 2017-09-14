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
 * This class is a wrapper for {@link UserNotificationChannel}.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationChannel
 * @generated
 */
@ProviderType
public class UserNotificationChannelWrapper implements UserNotificationChannel,
	ModelWrapper<UserNotificationChannel> {
	public UserNotificationChannelWrapper(
		UserNotificationChannel userNotificationChannel) {
		_userNotificationChannel = userNotificationChannel;
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationChannel.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationChannel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("publikUserId", getPublikUserId());
		attributes.put("channelId", getChannelId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long channelId = (Long)attributes.get("channelId");

		if (channelId != null) {
			setChannelId(channelId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _userNotificationChannel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userNotificationChannel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userNotificationChannel.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userNotificationChannel.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.notification.model.UserNotificationChannel> toCacheModel() {
		return _userNotificationChannel.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationChannel toEscapedModel() {
		return new UserNotificationChannelWrapper(_userNotificationChannel.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.notification.model.UserNotificationChannel toUnescapedModel() {
		return new UserNotificationChannelWrapper(_userNotificationChannel.toUnescapedModel());
	}

	/**
	* Returns the primary key of this user notification channel.
	*
	* @return the primary key of this user notification channel
	*/
	@Override
	public eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK getPrimaryKey() {
		return _userNotificationChannel.getPrimaryKey();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notification.model.UserNotificationChannel userNotificationChannel) {
		return _userNotificationChannel.compareTo(userNotificationChannel);
	}

	@Override
	public int hashCode() {
		return _userNotificationChannel.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userNotificationChannel.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserNotificationChannelWrapper((UserNotificationChannel)_userNotificationChannel.clone());
	}

	/**
	* Returns the publik user ID of this user notification channel.
	*
	* @return the publik user ID of this user notification channel
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _userNotificationChannel.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _userNotificationChannel.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userNotificationChannel.toXmlString();
	}

	/**
	* Returns the channel ID of this user notification channel.
	*
	* @return the channel ID of this user notification channel
	*/
	@Override
	public long getChannelId() {
		return _userNotificationChannel.getChannelId();
	}

	@Override
	public void persist() {
		_userNotificationChannel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userNotificationChannel.setCachedModel(cachedModel);
	}

	/**
	* Sets the channel ID of this user notification channel.
	*
	* @param channelId the channel ID of this user notification channel
	*/
	@Override
	public void setChannelId(long channelId) {
		_userNotificationChannel.setChannelId(channelId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userNotificationChannel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userNotificationChannel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userNotificationChannel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_userNotificationChannel.setNew(n);
	}

	/**
	* Sets the primary key of this user notification channel.
	*
	* @param primaryKey the primary key of this user notification channel
	*/
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK primaryKey) {
		_userNotificationChannel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userNotificationChannel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this user notification channel.
	*
	* @param publikUserId the publik user ID of this user notification channel
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_userNotificationChannel.setPublikUserId(publikUserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationChannelWrapper)) {
			return false;
		}

		UserNotificationChannelWrapper userNotificationChannelWrapper = (UserNotificationChannelWrapper)obj;

		if (Objects.equals(_userNotificationChannel,
					userNotificationChannelWrapper._userNotificationChannel)) {
			return true;
		}

		return false;
	}

	@Override
	public UserNotificationChannel getWrappedModel() {
		return _userNotificationChannel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userNotificationChannel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userNotificationChannel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userNotificationChannel.resetOriginalValues();
	}

	private final UserNotificationChannel _userNotificationChannel;
}
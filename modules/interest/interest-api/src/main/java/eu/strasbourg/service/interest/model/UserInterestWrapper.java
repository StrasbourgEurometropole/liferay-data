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

package eu.strasbourg.service.interest.model;

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
 * This class is a wrapper for {@link UserInterest}.
 * </p>
 *
 * @author BenjaminBini
 * @see UserInterest
 * @generated
 */
@ProviderType
public class UserInterestWrapper implements UserInterest,
	ModelWrapper<UserInterest> {
	public UserInterestWrapper(UserInterest userInterest) {
		_userInterest = userInterest;
	}

	@Override
	public Class<?> getModelClass() {
		return UserInterest.class;
	}

	@Override
	public String getModelClassName() {
		return UserInterest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("interestId", getInterestId());
		attributes.put("publikUserId", getPublikUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long interestId = (Long)attributes.get("interestId");

		if (interestId != null) {
			setInterestId(interestId);
		}

		Long publikUserId = (Long)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _userInterest.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userInterest.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userInterest.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userInterest.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.interest.model.UserInterest> toCacheModel() {
		return _userInterest.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.interest.model.Interest getInterest() {
		return _userInterest.getInterest();
	}

	@Override
	public eu.strasbourg.service.interest.model.UserInterest toEscapedModel() {
		return new UserInterestWrapper(_userInterest.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.interest.model.UserInterest toUnescapedModel() {
		return new UserInterestWrapper(_userInterest.toUnescapedModel());
	}

	/**
	* Returns the primary key of this user interest.
	*
	* @return the primary key of this user interest
	*/
	@Override
	public eu.strasbourg.service.interest.service.persistence.UserInterestPK getPrimaryKey() {
		return _userInterest.getPrimaryKey();
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.interest.model.UserInterest userInterest) {
		return _userInterest.compareTo(userInterest);
	}

	@Override
	public int hashCode() {
		return _userInterest.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userInterest.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserInterestWrapper((UserInterest)_userInterest.clone());
	}

	/**
	* Returns the publik user uuid of this user interest.
	*
	* @return the publik user uuid of this user interest
	*/
	@Override
	public java.lang.String getPublikUserUuid() {
		return _userInterest.getPublikUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _userInterest.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userInterest.toXmlString();
	}

	/**
	* Returns the interest ID of this user interest.
	*
	* @return the interest ID of this user interest
	*/
	@Override
	public long getInterestId() {
		return _userInterest.getInterestId();
	}

	/**
	* Returns the publik user ID of this user interest.
	*
	* @return the publik user ID of this user interest
	*/
	@Override
	public long getPublikUserId() {
		return _userInterest.getPublikUserId();
	}

	@Override
	public void persist() {
		_userInterest.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userInterest.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userInterest.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userInterest.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userInterest.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the interest ID of this user interest.
	*
	* @param interestId the interest ID of this user interest
	*/
	@Override
	public void setInterestId(long interestId) {
		_userInterest.setInterestId(interestId);
	}

	@Override
	public void setNew(boolean n) {
		_userInterest.setNew(n);
	}

	/**
	* Sets the primary key of this user interest.
	*
	* @param primaryKey the primary key of this user interest
	*/
	@Override
	public void setPrimaryKey(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK primaryKey) {
		_userInterest.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userInterest.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this user interest.
	*
	* @param publikUserId the publik user ID of this user interest
	*/
	@Override
	public void setPublikUserId(long publikUserId) {
		_userInterest.setPublikUserId(publikUserId);
	}

	/**
	* Sets the publik user uuid of this user interest.
	*
	* @param publikUserUuid the publik user uuid of this user interest
	*/
	@Override
	public void setPublikUserUuid(java.lang.String publikUserUuid) {
		_userInterest.setPublikUserUuid(publikUserUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserInterestWrapper)) {
			return false;
		}

		UserInterestWrapper userInterestWrapper = (UserInterestWrapper)obj;

		if (Objects.equals(_userInterest, userInterestWrapper._userInterest)) {
			return true;
		}

		return false;
	}

	@Override
	public UserInterest getWrappedModel() {
		return _userInterest;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userInterest.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userInterest.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userInterest.resetOriginalValues();
	}

	private final UserInterest _userInterest;
}
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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link InitiativeHelp}.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeHelp
 * @generated
 */
@ProviderType
public class InitiativeHelpWrapper implements InitiativeHelp,
	ModelWrapper<InitiativeHelp> {
	public InitiativeHelpWrapper(InitiativeHelp initiativeHelp) {
		_initiativeHelp = initiativeHelp;
	}

	@Override
	public Class<?> getModelClass() {
		return InitiativeHelp.class;
	}

	@Override
	public String getModelClassName() {
		return InitiativeHelp.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("initiativeHelpId", getInitiativeHelpId());
		attributes.put("createDate", getCreateDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("initiativeId", getInitiativeId());
		attributes.put("groupId", getGroupId());
		attributes.put("message", getMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long initiativeHelpId = (Long)attributes.get("initiativeHelpId");

		if (initiativeHelpId != null) {
			setInitiativeHelpId(initiativeHelpId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long initiativeId = (Long)attributes.get("initiativeId");

		if (initiativeId != null) {
			setInitiativeId(initiativeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _initiativeHelp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _initiativeHelp.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _initiativeHelp.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _initiativeHelp.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.InitiativeHelp> toCacheModel() {
		return _initiativeHelp.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp toEscapedModel() {
		return new InitiativeHelpWrapper(_initiativeHelp.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp toUnescapedModel() {
		return new InitiativeHelpWrapper(_initiativeHelp.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {
		return _initiativeHelp.compareTo(initiativeHelp);
	}

	@Override
	public int hashCode() {
		return _initiativeHelp.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _initiativeHelp.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new InitiativeHelpWrapper((InitiativeHelp)_initiativeHelp.clone());
	}

	/**
	* Returns the message of this initiative help.
	*
	* @return the message of this initiative help
	*/
	@Override
	public java.lang.String getMessage() {
		return _initiativeHelp.getMessage();
	}

	/**
	* Returns the publik user ID of this initiative help.
	*
	* @return the publik user ID of this initiative help
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _initiativeHelp.getPublikUserId();
	}

	@Override
	public java.lang.String toString() {
		return _initiativeHelp.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _initiativeHelp.toXmlString();
	}

	/**
	* Returns the create date of this initiative help.
	*
	* @return the create date of this initiative help
	*/
	@Override
	public Date getCreateDate() {
		return _initiativeHelp.getCreateDate();
	}

	/**
	* Returns the group ID of this initiative help.
	*
	* @return the group ID of this initiative help
	*/
	@Override
	public long getGroupId() {
		return _initiativeHelp.getGroupId();
	}

	/**
	* Returns the initiative help ID of this initiative help.
	*
	* @return the initiative help ID of this initiative help
	*/
	@Override
	public long getInitiativeHelpId() {
		return _initiativeHelp.getInitiativeHelpId();
	}

	/**
	* Returns the initiative ID of this initiative help.
	*
	* @return the initiative ID of this initiative help
	*/
	@Override
	public long getInitiativeId() {
		return _initiativeHelp.getInitiativeId();
	}

	/**
	* Returns the primary key of this initiative help.
	*
	* @return the primary key of this initiative help
	*/
	@Override
	public long getPrimaryKey() {
		return _initiativeHelp.getPrimaryKey();
	}

	@Override
	public void persist() {
		_initiativeHelp.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_initiativeHelp.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this initiative help.
	*
	* @param createDate the create date of this initiative help
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_initiativeHelp.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_initiativeHelp.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_initiativeHelp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_initiativeHelp.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this initiative help.
	*
	* @param groupId the group ID of this initiative help
	*/
	@Override
	public void setGroupId(long groupId) {
		_initiativeHelp.setGroupId(groupId);
	}

	/**
	* Sets the initiative help ID of this initiative help.
	*
	* @param initiativeHelpId the initiative help ID of this initiative help
	*/
	@Override
	public void setInitiativeHelpId(long initiativeHelpId) {
		_initiativeHelp.setInitiativeHelpId(initiativeHelpId);
	}

	/**
	* Sets the initiative ID of this initiative help.
	*
	* @param initiativeId the initiative ID of this initiative help
	*/
	@Override
	public void setInitiativeId(long initiativeId) {
		_initiativeHelp.setInitiativeId(initiativeId);
	}

	/**
	* Sets the message of this initiative help.
	*
	* @param message the message of this initiative help
	*/
	@Override
	public void setMessage(java.lang.String message) {
		_initiativeHelp.setMessage(message);
	}

	@Override
	public void setNew(boolean n) {
		_initiativeHelp.setNew(n);
	}

	/**
	* Sets the primary key of this initiative help.
	*
	* @param primaryKey the primary key of this initiative help
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_initiativeHelp.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_initiativeHelp.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this initiative help.
	*
	* @param publikUserId the publik user ID of this initiative help
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_initiativeHelp.setPublikUserId(publikUserId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InitiativeHelpWrapper)) {
			return false;
		}

		InitiativeHelpWrapper initiativeHelpWrapper = (InitiativeHelpWrapper)obj;

		if (Objects.equals(_initiativeHelp,
					initiativeHelpWrapper._initiativeHelp)) {
			return true;
		}

		return false;
	}

	@Override
	public InitiativeHelp getWrappedModel() {
		return _initiativeHelp;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _initiativeHelp.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _initiativeHelp.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_initiativeHelp.resetOriginalValues();
	}

	private final InitiativeHelp _initiativeHelp;
}
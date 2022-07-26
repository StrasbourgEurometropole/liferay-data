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
public class InitiativeHelpWrapper
	implements InitiativeHelp, ModelWrapper<InitiativeHelp> {

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

		attributes.put("uuid", getUuid());
		attributes.put("initiativeHelpId", getInitiativeHelpId());
		attributes.put("createDate", getCreateDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("initiativeId", getInitiativeId());
		attributes.put("helpTypes", getHelpTypes());
		attributes.put("groupId", getGroupId());
		attributes.put("message", getMessage());
		attributes.put("helpDisplay", isHelpDisplay());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

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

		String helpTypes = (String)attributes.get("helpTypes");

		if (helpTypes != null) {
			setHelpTypes(helpTypes);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Boolean helpDisplay = (Boolean)attributes.get("helpDisplay");

		if (helpDisplay != null) {
			setHelpDisplay(helpDisplay);
		}
	}

	@Override
	public Object clone() {
		return new InitiativeHelpWrapper(
			(InitiativeHelp)_initiativeHelp.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {

		return _initiativeHelp.compareTo(initiativeHelp);
	}

	/**
	 * Retourne l'utilisateur Publik depositaire
	 *
	 * @return
	 */
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getAuthor() {
		return _initiativeHelp.getAuthor();
	}

	/**
	 * Retourne l'URL de l'image de l'utilisateur
	 */
	@Override
	public String getAuthorImageURL() {
		return _initiativeHelp.getAuthorImageURL();
	}

	/**
	 * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
	 */
	@Override
	public String getAuthorLabel() {
		return _initiativeHelp.getAuthorLabel();
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _initiativeHelp.getExpandoBridge();
	}

	/**
	 * Retourne le message d'accompagnement sans les balises et autres fioritures
	 *
	 * @return
	 */
	@Override
	public String getFormatedMessage() {
		return _initiativeHelp.getFormatedMessage();
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
	 * Returns the help display of this initiative help.
	 *
	 * @return the help display of this initiative help
	 */
	@Override
	public boolean getHelpDisplay() {
		return _initiativeHelp.getHelpDisplay();
	}

	/**
	 * Returns the help types of this initiative help.
	 *
	 * @return the help types of this initiative help
	 */
	@Override
	public String getHelpTypes() {
		return _initiativeHelp.getHelpTypes();
	}

	/**
	 * Retourne l'initiative de l'aide
	 *
	 * @return
	 * @throws PortalException
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative getInitiative() {
		return _initiativeHelp.getInitiative();
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
	 * Returns the message of this initiative help.
	 *
	 * @return the message of this initiative help
	 */
	@Override
	public String getMessage() {
		return _initiativeHelp.getMessage();
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
	public Serializable getPrimaryKeyObj() {
		return _initiativeHelp.getPrimaryKeyObj();
	}

	/**
	 * Returns the publik user ID of this initiative help.
	 *
	 * @return the publik user ID of this initiative help
	 */
	@Override
	public String getPublikUserId() {
		return _initiativeHelp.getPublikUserId();
	}

	/**
	 * Retourne le label des types d'aide
	 */
	@Override
	public String getTypesLabel() {
		return _initiativeHelp.getTypesLabel();
	}

	/**
	 * Returns the uuid of this initiative help.
	 *
	 * @return the uuid of this initiative help
	 */
	@Override
	public String getUuid() {
		return _initiativeHelp.getUuid();
	}

	@Override
	public int hashCode() {
		return _initiativeHelp.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _initiativeHelp.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _initiativeHelp.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this initiative help is help display.
	 *
	 * @return <code>true</code> if this initiative help is help display; <code>false</code> otherwise
	 */
	@Override
	public boolean isHelpDisplay() {
		return _initiativeHelp.isHelpDisplay();
	}

	@Override
	public boolean isNew() {
		return _initiativeHelp.isNew();
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
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_initiativeHelp.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_initiativeHelp.setExpandoBridgeAttributes(expandoBridge);
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
	 * Sets whether this initiative help is help display.
	 *
	 * @param helpDisplay the help display of this initiative help
	 */
	@Override
	public void setHelpDisplay(boolean helpDisplay) {
		_initiativeHelp.setHelpDisplay(helpDisplay);
	}

	/**
	 * Sets the help types of this initiative help.
	 *
	 * @param helpTypes the help types of this initiative help
	 */
	@Override
	public void setHelpTypes(String helpTypes) {
		_initiativeHelp.setHelpTypes(helpTypes);
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
	public void setMessage(String message) {
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
	public void setPublikUserId(String publikUserId) {
		_initiativeHelp.setPublikUserId(publikUserId);
	}

	/**
	 * Sets the uuid of this initiative help.
	 *
	 * @param uuid the uuid of this initiative help
	 */
	@Override
	public void setUuid(String uuid) {
		_initiativeHelp.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.project.model.InitiativeHelp> toCacheModel() {

		return _initiativeHelp.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp toEscapedModel() {
		return new InitiativeHelpWrapper(_initiativeHelp.toEscapedModel());
	}

	@Override
	public String toString() {
		return _initiativeHelp.toString();
	}

	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		toUnescapedModel() {

		return new InitiativeHelpWrapper(_initiativeHelp.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _initiativeHelp.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof InitiativeHelpWrapper)) {
			return false;
		}

		InitiativeHelpWrapper initiativeHelpWrapper =
			(InitiativeHelpWrapper)object;

		if (Objects.equals(
				_initiativeHelp, initiativeHelpWrapper._initiativeHelp)) {

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
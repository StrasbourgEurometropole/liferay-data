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

package eu.strasbourg.service.agenda.model;

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
 * This class is a wrapper for {@link CampaignEventStatus}.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventStatus
 * @generated
 */
@ProviderType
public class CampaignEventStatusWrapper implements CampaignEventStatus,
	ModelWrapper<CampaignEventStatus> {
	public CampaignEventStatusWrapper(CampaignEventStatus campaignEventStatus) {
		_campaignEventStatus = campaignEventStatus;
	}

	@Override
	public Class<?> getModelClass() {
		return CampaignEventStatus.class;
	}

	@Override
	public String getModelClassName() {
		return CampaignEventStatus.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("statusId", getStatusId());
		attributes.put("status", getStatus());
		attributes.put("comment", getComment());
		attributes.put("deletionDenied", getDeletionDenied());
		attributes.put("date", getDate());
		attributes.put("campaignEventId", getCampaignEventId());
		attributes.put("previousStatusId", getPreviousStatusId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long statusId = (Long)attributes.get("statusId");

		if (statusId != null) {
			setStatusId(statusId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Boolean deletionDenied = (Boolean)attributes.get("deletionDenied");

		if (deletionDenied != null) {
			setDeletionDenied(deletionDenied);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Long campaignEventId = (Long)attributes.get("campaignEventId");

		if (campaignEventId != null) {
			setCampaignEventId(campaignEventId);
		}

		Long previousStatusId = (Long)attributes.get("previousStatusId");

		if (previousStatusId != null) {
			setPreviousStatusId(previousStatusId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _campaignEventStatus.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _campaignEventStatus.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _campaignEventStatus.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _campaignEventStatus.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.agenda.model.CampaignEventStatus> toCacheModel() {
		return _campaignEventStatus.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus getPreviousStatus() {
		return _campaignEventStatus.getPreviousStatus();
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus toEscapedModel() {
		return new CampaignEventStatusWrapper(_campaignEventStatus.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.agenda.model.CampaignEventStatus toUnescapedModel() {
		return new CampaignEventStatusWrapper(_campaignEventStatus.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.agenda.model.CampaignEventStatus campaignEventStatus) {
		return _campaignEventStatus.compareTo(campaignEventStatus);
	}

	@Override
	public int hashCode() {
		return _campaignEventStatus.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _campaignEventStatus.getPrimaryKeyObj();
	}

	/**
	* Returns the deletion denied of this campaign event status.
	*
	* @return the deletion denied of this campaign event status
	*/
	@Override
	public java.lang.Boolean getDeletionDenied() {
		return _campaignEventStatus.getDeletionDenied();
	}

	/**
	* Returns the status of this campaign event status.
	*
	* @return the status of this campaign event status
	*/
	@Override
	public java.lang.Integer getStatus() {
		return _campaignEventStatus.getStatus();
	}

	@Override
	public java.lang.Object clone() {
		return new CampaignEventStatusWrapper((CampaignEventStatus)_campaignEventStatus.clone());
	}

	/**
	* Returns the comment of this campaign event status.
	*
	* @return the comment of this campaign event status
	*/
	@Override
	public java.lang.String getComment() {
		return _campaignEventStatus.getComment();
	}

	/**
	* Retourne le label correspondant au statut
	*/
	@Override
	public java.lang.String getStatusLabel() {
		return _campaignEventStatus.getStatusLabel();
	}

	/**
	* Returns the user name of this campaign event status.
	*
	* @return the user name of this campaign event status
	*/
	@Override
	public java.lang.String getUserName() {
		return _campaignEventStatus.getUserName();
	}

	/**
	* Returns the user uuid of this campaign event status.
	*
	* @return the user uuid of this campaign event status
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _campaignEventStatus.getUserUuid();
	}

	/**
	* Returns the uuid of this campaign event status.
	*
	* @return the uuid of this campaign event status
	*/
	@Override
	public java.lang.String getUuid() {
		return _campaignEventStatus.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _campaignEventStatus.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _campaignEventStatus.toXmlString();
	}

	/**
	* Returns the date of this campaign event status.
	*
	* @return the date of this campaign event status
	*/
	@Override
	public Date getDate() {
		return _campaignEventStatus.getDate();
	}

	/**
	* Returns the campaign event ID of this campaign event status.
	*
	* @return the campaign event ID of this campaign event status
	*/
	@Override
	public long getCampaignEventId() {
		return _campaignEventStatus.getCampaignEventId();
	}

	/**
	* Returns the previous status ID of this campaign event status.
	*
	* @return the previous status ID of this campaign event status
	*/
	@Override
	public long getPreviousStatusId() {
		return _campaignEventStatus.getPreviousStatusId();
	}

	/**
	* Returns the primary key of this campaign event status.
	*
	* @return the primary key of this campaign event status
	*/
	@Override
	public long getPrimaryKey() {
		return _campaignEventStatus.getPrimaryKey();
	}

	/**
	* Returns the status ID of this campaign event status.
	*
	* @return the status ID of this campaign event status
	*/
	@Override
	public long getStatusId() {
		return _campaignEventStatus.getStatusId();
	}

	/**
	* Returns the user ID of this campaign event status.
	*
	* @return the user ID of this campaign event status
	*/
	@Override
	public long getUserId() {
		return _campaignEventStatus.getUserId();
	}

	@Override
	public void persist() {
		_campaignEventStatus.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_campaignEventStatus.setCachedModel(cachedModel);
	}

	/**
	* Sets the campaign event ID of this campaign event status.
	*
	* @param campaignEventId the campaign event ID of this campaign event status
	*/
	@Override
	public void setCampaignEventId(long campaignEventId) {
		_campaignEventStatus.setCampaignEventId(campaignEventId);
	}

	/**
	* Sets the comment of this campaign event status.
	*
	* @param comment the comment of this campaign event status
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_campaignEventStatus.setComment(comment);
	}

	/**
	* Sets the date of this campaign event status.
	*
	* @param date the date of this campaign event status
	*/
	@Override
	public void setDate(Date date) {
		_campaignEventStatus.setDate(date);
	}

	/**
	* Sets the deletion denied of this campaign event status.
	*
	* @param deletionDenied the deletion denied of this campaign event status
	*/
	@Override
	public void setDeletionDenied(java.lang.Boolean deletionDenied) {
		_campaignEventStatus.setDeletionDenied(deletionDenied);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_campaignEventStatus.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_campaignEventStatus.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_campaignEventStatus.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_campaignEventStatus.setNew(n);
	}

	/**
	* Sets the previous status ID of this campaign event status.
	*
	* @param previousStatusId the previous status ID of this campaign event status
	*/
	@Override
	public void setPreviousStatusId(long previousStatusId) {
		_campaignEventStatus.setPreviousStatusId(previousStatusId);
	}

	/**
	* Sets the primary key of this campaign event status.
	*
	* @param primaryKey the primary key of this campaign event status
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_campaignEventStatus.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_campaignEventStatus.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this campaign event status.
	*
	* @param status the status of this campaign event status
	*/
	@Override
	public void setStatus(java.lang.Integer status) {
		_campaignEventStatus.setStatus(status);
	}

	/**
	* Sets the status ID of this campaign event status.
	*
	* @param statusId the status ID of this campaign event status
	*/
	@Override
	public void setStatusId(long statusId) {
		_campaignEventStatus.setStatusId(statusId);
	}

	/**
	* Sets the user ID of this campaign event status.
	*
	* @param userId the user ID of this campaign event status
	*/
	@Override
	public void setUserId(long userId) {
		_campaignEventStatus.setUserId(userId);
	}

	/**
	* Sets the user name of this campaign event status.
	*
	* @param userName the user name of this campaign event status
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_campaignEventStatus.setUserName(userName);
	}

	/**
	* Sets the user uuid of this campaign event status.
	*
	* @param userUuid the user uuid of this campaign event status
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_campaignEventStatus.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this campaign event status.
	*
	* @param uuid the uuid of this campaign event status
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_campaignEventStatus.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CampaignEventStatusWrapper)) {
			return false;
		}

		CampaignEventStatusWrapper campaignEventStatusWrapper = (CampaignEventStatusWrapper)obj;

		if (Objects.equals(_campaignEventStatus,
					campaignEventStatusWrapper._campaignEventStatus)) {
			return true;
		}

		return false;
	}

	@Override
	public CampaignEventStatus getWrappedModel() {
		return _campaignEventStatus;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _campaignEventStatus.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _campaignEventStatus.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_campaignEventStatus.resetOriginalValues();
	}

	private final CampaignEventStatus _campaignEventStatus;
}
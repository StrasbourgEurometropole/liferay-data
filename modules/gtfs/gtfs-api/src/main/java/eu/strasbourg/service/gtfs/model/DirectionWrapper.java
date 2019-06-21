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

package eu.strasbourg.service.gtfs.model;

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
 * This class is a wrapper for {@link Direction}.
 * </p>
 *
 * @author Cedric Henry
 * @see Direction
 * @generated
 */
@ProviderType
public class DirectionWrapper implements Direction, ModelWrapper<Direction> {
	public DirectionWrapper(Direction direction) {
		_direction = direction;
	}

	@Override
	public Class<?> getModelClass() {
		return Direction.class;
	}

	@Override
	public String getModelClassName() {
		return Direction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("directionId", getDirectionId());
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
		attributes.put("stopId", getStopId());
		attributes.put("routeId", getRouteId());
		attributes.put("destinationName", getDestinationName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long directionId = (Long)attributes.get("directionId");

		if (directionId != null) {
			setDirectionId(directionId);
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

		String stopId = (String)attributes.get("stopId");

		if (stopId != null) {
			setStopId(stopId);
		}

		String routeId = (String)attributes.get("routeId");

		if (routeId != null) {
			setRouteId(routeId);
		}

		String destinationName = (String)attributes.get("destinationName");

		if (destinationName != null) {
			setDestinationName(destinationName);
		}
	}

	/**
	* Returns <code>true</code> if this direction is approved.
	*
	* @return <code>true</code> if this direction is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _direction.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _direction.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this direction is denied.
	*
	* @return <code>true</code> if this direction is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _direction.isDenied();
	}

	/**
	* Returns <code>true</code> if this direction is a draft.
	*
	* @return <code>true</code> if this direction is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _direction.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _direction.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this direction is expired.
	*
	* @return <code>true</code> if this direction is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _direction.isExpired();
	}

	/**
	* Returns <code>true</code> if this direction is inactive.
	*
	* @return <code>true</code> if this direction is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _direction.isInactive();
	}

	/**
	* Returns <code>true</code> if this direction is incomplete.
	*
	* @return <code>true</code> if this direction is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _direction.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _direction.isNew();
	}

	/**
	* Returns <code>true</code> if this direction is pending.
	*
	* @return <code>true</code> if this direction is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _direction.isPending();
	}

	/**
	* Returns <code>true</code> if this direction is scheduled.
	*
	* @return <code>true</code> if this direction is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _direction.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _direction.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Direction> toCacheModel() {
		return _direction.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Direction toEscapedModel() {
		return new DirectionWrapper(_direction.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Direction toUnescapedModel() {
		return new DirectionWrapper(_direction.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Direction direction) {
		return _direction.compareTo(direction);
	}

	/**
	* Returns the status of this direction.
	*
	* @return the status of this direction
	*/
	@Override
	public int getStatus() {
		return _direction.getStatus();
	}

	@Override
	public int hashCode() {
		return _direction.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _direction.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DirectionWrapper((Direction)_direction.clone());
	}

	/**
	* Returns the destination name of this direction.
	*
	* @return the destination name of this direction
	*/
	@Override
	public java.lang.String getDestinationName() {
		return _direction.getDestinationName();
	}

	/**
	* Returns the route ID of this direction.
	*
	* @return the route ID of this direction
	*/
	@Override
	public java.lang.String getRouteId() {
		return _direction.getRouteId();
	}

	/**
	* Returns the status by user name of this direction.
	*
	* @return the status by user name of this direction
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _direction.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this direction.
	*
	* @return the status by user uuid of this direction
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _direction.getStatusByUserUuid();
	}

	/**
	* Returns the stop ID of this direction.
	*
	* @return the stop ID of this direction
	*/
	@Override
	public java.lang.String getStopId() {
		return _direction.getStopId();
	}

	/**
	* Returns the user name of this direction.
	*
	* @return the user name of this direction
	*/
	@Override
	public java.lang.String getUserName() {
		return _direction.getUserName();
	}

	/**
	* Returns the user uuid of this direction.
	*
	* @return the user uuid of this direction
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _direction.getUserUuid();
	}

	/**
	* Returns the uuid of this direction.
	*
	* @return the uuid of this direction
	*/
	@Override
	public java.lang.String getUuid() {
		return _direction.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _direction.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _direction.toXmlString();
	}

	/**
	* Returns the create date of this direction.
	*
	* @return the create date of this direction
	*/
	@Override
	public Date getCreateDate() {
		return _direction.getCreateDate();
	}

	/**
	* Returns the modified date of this direction.
	*
	* @return the modified date of this direction
	*/
	@Override
	public Date getModifiedDate() {
		return _direction.getModifiedDate();
	}

	/**
	* Returns the status date of this direction.
	*
	* @return the status date of this direction
	*/
	@Override
	public Date getStatusDate() {
		return _direction.getStatusDate();
	}

	/**
	* Returns the company ID of this direction.
	*
	* @return the company ID of this direction
	*/
	@Override
	public long getCompanyId() {
		return _direction.getCompanyId();
	}

	/**
	* Returns the direction ID of this direction.
	*
	* @return the direction ID of this direction
	*/
	@Override
	public long getDirectionId() {
		return _direction.getDirectionId();
	}

	/**
	* Returns the group ID of this direction.
	*
	* @return the group ID of this direction
	*/
	@Override
	public long getGroupId() {
		return _direction.getGroupId();
	}

	/**
	* Returns the primary key of this direction.
	*
	* @return the primary key of this direction
	*/
	@Override
	public long getPrimaryKey() {
		return _direction.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this direction.
	*
	* @return the status by user ID of this direction
	*/
	@Override
	public long getStatusByUserId() {
		return _direction.getStatusByUserId();
	}

	/**
	* Returns the user ID of this direction.
	*
	* @return the user ID of this direction
	*/
	@Override
	public long getUserId() {
		return _direction.getUserId();
	}

	@Override
	public void persist() {
		_direction.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_direction.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this direction.
	*
	* @param companyId the company ID of this direction
	*/
	@Override
	public void setCompanyId(long companyId) {
		_direction.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this direction.
	*
	* @param createDate the create date of this direction
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_direction.setCreateDate(createDate);
	}

	/**
	* Sets the destination name of this direction.
	*
	* @param destinationName the destination name of this direction
	*/
	@Override
	public void setDestinationName(java.lang.String destinationName) {
		_direction.setDestinationName(destinationName);
	}

	/**
	* Sets the direction ID of this direction.
	*
	* @param directionId the direction ID of this direction
	*/
	@Override
	public void setDirectionId(long directionId) {
		_direction.setDirectionId(directionId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_direction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_direction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_direction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this direction.
	*
	* @param groupId the group ID of this direction
	*/
	@Override
	public void setGroupId(long groupId) {
		_direction.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this direction.
	*
	* @param modifiedDate the modified date of this direction
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_direction.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_direction.setNew(n);
	}

	/**
	* Sets the primary key of this direction.
	*
	* @param primaryKey the primary key of this direction
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_direction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_direction.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the route ID of this direction.
	*
	* @param routeId the route ID of this direction
	*/
	@Override
	public void setRouteId(java.lang.String routeId) {
		_direction.setRouteId(routeId);
	}

	/**
	* Sets the status of this direction.
	*
	* @param status the status of this direction
	*/
	@Override
	public void setStatus(int status) {
		_direction.setStatus(status);
	}

	/**
	* Sets the status by user ID of this direction.
	*
	* @param statusByUserId the status by user ID of this direction
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_direction.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this direction.
	*
	* @param statusByUserName the status by user name of this direction
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_direction.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this direction.
	*
	* @param statusByUserUuid the status by user uuid of this direction
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_direction.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this direction.
	*
	* @param statusDate the status date of this direction
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_direction.setStatusDate(statusDate);
	}

	/**
	* Sets the stop ID of this direction.
	*
	* @param stopId the stop ID of this direction
	*/
	@Override
	public void setStopId(java.lang.String stopId) {
		_direction.setStopId(stopId);
	}

	/**
	* Sets the user ID of this direction.
	*
	* @param userId the user ID of this direction
	*/
	@Override
	public void setUserId(long userId) {
		_direction.setUserId(userId);
	}

	/**
	* Sets the user name of this direction.
	*
	* @param userName the user name of this direction
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_direction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this direction.
	*
	* @param userUuid the user uuid of this direction
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_direction.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this direction.
	*
	* @param uuid the uuid of this direction
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_direction.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DirectionWrapper)) {
			return false;
		}

		DirectionWrapper directionWrapper = (DirectionWrapper)obj;

		if (Objects.equals(_direction, directionWrapper._direction)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _direction.getStagedModelType();
	}

	@Override
	public Direction getWrappedModel() {
		return _direction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _direction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _direction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_direction.resetOriginalValues();
	}

	private final Direction _direction;
}
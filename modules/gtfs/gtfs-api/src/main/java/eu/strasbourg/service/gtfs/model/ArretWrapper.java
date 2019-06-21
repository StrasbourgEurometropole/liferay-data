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
 * This class is a wrapper for {@link Arret}.
 * </p>
 *
 * @author Cedric Henry
 * @see Arret
 * @generated
 */
@ProviderType
public class ArretWrapper implements Arret, ModelWrapper<Arret> {
	public ArretWrapper(Arret arret) {
		_arret = arret;
	}

	@Override
	public Class<?> getModelClass() {
		return Arret.class;
	}

	@Override
	public String getModelClassName() {
		return Arret.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("arretId", getArretId());
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
		attributes.put("title", getTitle());
		attributes.put("code", getCode());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long arretId = (Long)attributes.get("arretId");

		if (arretId != null) {
			setArretId(arretId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String latitude = (String)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		String longitude = (String)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns <code>true</code> if this arret is approved.
	*
	* @return <code>true</code> if this arret is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _arret.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _arret.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this arret is denied.
	*
	* @return <code>true</code> if this arret is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _arret.isDenied();
	}

	/**
	* Returns <code>true</code> if this arret is a draft.
	*
	* @return <code>true</code> if this arret is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _arret.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _arret.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this arret is expired.
	*
	* @return <code>true</code> if this arret is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _arret.isExpired();
	}

	/**
	* Returns <code>true</code> if this arret is inactive.
	*
	* @return <code>true</code> if this arret is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _arret.isInactive();
	}

	/**
	* Returns <code>true</code> if this arret is incomplete.
	*
	* @return <code>true</code> if this arret is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _arret.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _arret.isNew();
	}

	/**
	* Returns <code>true</code> if this arret is pending.
	*
	* @return <code>true</code> if this arret is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _arret.isPending();
	}

	/**
	* Returns <code>true</code> if this arret is scheduled.
	*
	* @return <code>true</code> if this arret is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _arret.isScheduled();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _arret.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Arret> toCacheModel() {
		return _arret.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Arret toEscapedModel() {
		return new ArretWrapper(_arret.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Arret toUnescapedModel() {
		return new ArretWrapper(_arret.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Arret arret) {
		return _arret.compareTo(arret);
	}

	/**
	* Returns the status of this arret.
	*
	* @return the status of this arret
	*/
	@Override
	public int getStatus() {
		return _arret.getStatus();
	}

	@Override
	public int hashCode() {
		return _arret.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _arret.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ArretWrapper((Arret)_arret.clone());
	}

	/**
	* Returns the code of this arret.
	*
	* @return the code of this arret
	*/
	@Override
	public java.lang.String getCode() {
		return _arret.getCode();
	}

	/**
	* Returns the latitude of this arret.
	*
	* @return the latitude of this arret
	*/
	@Override
	public java.lang.String getLatitude() {
		return _arret.getLatitude();
	}

	/**
	* Returns the longitude of this arret.
	*
	* @return the longitude of this arret
	*/
	@Override
	public java.lang.String getLongitude() {
		return _arret.getLongitude();
	}

	/**
	* Returns the status by user name of this arret.
	*
	* @return the status by user name of this arret
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _arret.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this arret.
	*
	* @return the status by user uuid of this arret
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _arret.getStatusByUserUuid();
	}

	/**
	* Returns the stop ID of this arret.
	*
	* @return the stop ID of this arret
	*/
	@Override
	public java.lang.String getStopId() {
		return _arret.getStopId();
	}

	/**
	* Returns the title of this arret.
	*
	* @return the title of this arret
	*/
	@Override
	public java.lang.String getTitle() {
		return _arret.getTitle();
	}

	/**
	* Returns the type of this arret.
	*
	* @return the type of this arret
	*/
	@Override
	public java.lang.String getType() {
		return _arret.getType();
	}

	/**
	* Returns the user name of this arret.
	*
	* @return the user name of this arret
	*/
	@Override
	public java.lang.String getUserName() {
		return _arret.getUserName();
	}

	/**
	* Returns the user uuid of this arret.
	*
	* @return the user uuid of this arret
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _arret.getUserUuid();
	}

	/**
	* Returns the uuid of this arret.
	*
	* @return the uuid of this arret
	*/
	@Override
	public java.lang.String getUuid() {
		return _arret.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _arret.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _arret.toXmlString();
	}

	/**
	* Returns the create date of this arret.
	*
	* @return the create date of this arret
	*/
	@Override
	public Date getCreateDate() {
		return _arret.getCreateDate();
	}

	/**
	* Returns the modified date of this arret.
	*
	* @return the modified date of this arret
	*/
	@Override
	public Date getModifiedDate() {
		return _arret.getModifiedDate();
	}

	/**
	* Returns the status date of this arret.
	*
	* @return the status date of this arret
	*/
	@Override
	public Date getStatusDate() {
		return _arret.getStatusDate();
	}

	/**
	* Returns the arret ID of this arret.
	*
	* @return the arret ID of this arret
	*/
	@Override
	public long getArretId() {
		return _arret.getArretId();
	}

	/**
	* Returns the company ID of this arret.
	*
	* @return the company ID of this arret
	*/
	@Override
	public long getCompanyId() {
		return _arret.getCompanyId();
	}

	/**
	* Returns the group ID of this arret.
	*
	* @return the group ID of this arret
	*/
	@Override
	public long getGroupId() {
		return _arret.getGroupId();
	}

	/**
	* Returns the primary key of this arret.
	*
	* @return the primary key of this arret
	*/
	@Override
	public long getPrimaryKey() {
		return _arret.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this arret.
	*
	* @return the status by user ID of this arret
	*/
	@Override
	public long getStatusByUserId() {
		return _arret.getStatusByUserId();
	}

	/**
	* Returns the user ID of this arret.
	*
	* @return the user ID of this arret
	*/
	@Override
	public long getUserId() {
		return _arret.getUserId();
	}

	@Override
	public void persist() {
		_arret.persist();
	}

	/**
	* Sets the arret ID of this arret.
	*
	* @param arretId the arret ID of this arret
	*/
	@Override
	public void setArretId(long arretId) {
		_arret.setArretId(arretId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_arret.setCachedModel(cachedModel);
	}

	/**
	* Sets the code of this arret.
	*
	* @param code the code of this arret
	*/
	@Override
	public void setCode(java.lang.String code) {
		_arret.setCode(code);
	}

	/**
	* Sets the company ID of this arret.
	*
	* @param companyId the company ID of this arret
	*/
	@Override
	public void setCompanyId(long companyId) {
		_arret.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this arret.
	*
	* @param createDate the create date of this arret
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_arret.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_arret.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_arret.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_arret.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this arret.
	*
	* @param groupId the group ID of this arret
	*/
	@Override
	public void setGroupId(long groupId) {
		_arret.setGroupId(groupId);
	}

	/**
	* Sets the latitude of this arret.
	*
	* @param latitude the latitude of this arret
	*/
	@Override
	public void setLatitude(java.lang.String latitude) {
		_arret.setLatitude(latitude);
	}

	/**
	* Sets the longitude of this arret.
	*
	* @param longitude the longitude of this arret
	*/
	@Override
	public void setLongitude(java.lang.String longitude) {
		_arret.setLongitude(longitude);
	}

	/**
	* Sets the modified date of this arret.
	*
	* @param modifiedDate the modified date of this arret
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_arret.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_arret.setNew(n);
	}

	/**
	* Sets the primary key of this arret.
	*
	* @param primaryKey the primary key of this arret
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_arret.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_arret.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this arret.
	*
	* @param status the status of this arret
	*/
	@Override
	public void setStatus(int status) {
		_arret.setStatus(status);
	}

	/**
	* Sets the status by user ID of this arret.
	*
	* @param statusByUserId the status by user ID of this arret
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_arret.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this arret.
	*
	* @param statusByUserName the status by user name of this arret
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_arret.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this arret.
	*
	* @param statusByUserUuid the status by user uuid of this arret
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_arret.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this arret.
	*
	* @param statusDate the status date of this arret
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_arret.setStatusDate(statusDate);
	}

	/**
	* Sets the stop ID of this arret.
	*
	* @param stopId the stop ID of this arret
	*/
	@Override
	public void setStopId(java.lang.String stopId) {
		_arret.setStopId(stopId);
	}

	/**
	* Sets the title of this arret.
	*
	* @param title the title of this arret
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_arret.setTitle(title);
	}

	/**
	* Sets the type of this arret.
	*
	* @param type the type of this arret
	*/
	@Override
	public void setType(java.lang.String type) {
		_arret.setType(type);
	}

	/**
	* Sets the user ID of this arret.
	*
	* @param userId the user ID of this arret
	*/
	@Override
	public void setUserId(long userId) {
		_arret.setUserId(userId);
	}

	/**
	* Sets the user name of this arret.
	*
	* @param userName the user name of this arret
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_arret.setUserName(userName);
	}

	/**
	* Sets the user uuid of this arret.
	*
	* @param userUuid the user uuid of this arret
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_arret.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this arret.
	*
	* @param uuid the uuid of this arret
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_arret.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArretWrapper)) {
			return false;
		}

		ArretWrapper arretWrapper = (ArretWrapper)obj;

		if (Objects.equals(_arret, arretWrapper._arret)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _arret.getStagedModelType();
	}

	@Override
	public Arret getWrappedModel() {
		return _arret;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _arret.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _arret.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_arret.resetOriginalValues();
	}

	private final Arret _arret;
}
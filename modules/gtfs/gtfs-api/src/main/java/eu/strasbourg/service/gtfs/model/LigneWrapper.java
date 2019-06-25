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
 * This class is a wrapper for {@link Ligne}.
 * </p>
 *
 * @author Cedric Henry
 * @see Ligne
 * @generated
 */
@ProviderType
public class LigneWrapper implements Ligne, ModelWrapper<Ligne> {
	public LigneWrapper(Ligne ligne) {
		_ligne = ligne;
	}

	@Override
	public Class<?> getModelClass() {
		return Ligne.class;
	}

	@Override
	public String getModelClassName() {
		return Ligne.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ligneId", getLigneId());
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
		attributes.put("routeId", getRouteId());
		attributes.put("shortName", getShortName());
		attributes.put("title", getTitle());
		attributes.put("type", getType());
		attributes.put("textColor", getTextColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ligneId = (Long)attributes.get("ligneId");

		if (ligneId != null) {
			setLigneId(ligneId);
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

		String routeId = (String)attributes.get("routeId");

		if (routeId != null) {
			setRouteId(routeId);
		}

		String shortName = (String)attributes.get("shortName");

		if (shortName != null) {
			setShortName(shortName);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String textColor = (String)attributes.get("textColor");

		if (textColor != null) {
			setTextColor(textColor);
		}
	}

	/**
	* Returns <code>true</code> if this ligne is approved.
	*
	* @return <code>true</code> if this ligne is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _ligne.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _ligne.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ligne is denied.
	*
	* @return <code>true</code> if this ligne is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _ligne.isDenied();
	}

	/**
	* Returns <code>true</code> if this ligne is a draft.
	*
	* @return <code>true</code> if this ligne is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _ligne.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _ligne.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this ligne is expired.
	*
	* @return <code>true</code> if this ligne is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _ligne.isExpired();
	}

	/**
	* Returns <code>true</code> if this ligne is inactive.
	*
	* @return <code>true</code> if this ligne is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _ligne.isInactive();
	}

	/**
	* Returns <code>true</code> if this ligne is incomplete.
	*
	* @return <code>true</code> if this ligne is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _ligne.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _ligne.isNew();
	}

	/**
	* Returns <code>true</code> if this ligne is pending.
	*
	* @return <code>true</code> if this ligne is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _ligne.isPending();
	}

	/**
	* Returns <code>true</code> if this ligne is scheduled.
	*
	* @return <code>true</code> if this ligne is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _ligne.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _ligne.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ligne.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Ligne> toCacheModel() {
		return _ligne.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Ligne toEscapedModel() {
		return new LigneWrapper(_ligne.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Ligne toUnescapedModel() {
		return new LigneWrapper(_ligne.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligne.compareTo(ligne);
	}

	/**
	* Returns the status of this ligne.
	*
	* @return the status of this ligne
	*/
	@Override
	public int getStatus() {
		return _ligne.getStatus();
	}

	@Override
	public int hashCode() {
		return _ligne.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ligne.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LigneWrapper((Ligne)_ligne.clone());
	}

	/**
	* Returns the route ID of this ligne.
	*
	* @return the route ID of this ligne
	*/
	@Override
	public java.lang.String getRouteId() {
		return _ligne.getRouteId();
	}

	/**
	* Returns the short name of this ligne.
	*
	* @return the short name of this ligne
	*/
	@Override
	public java.lang.String getShortName() {
		return _ligne.getShortName();
	}

	/**
	* Returns the status by user name of this ligne.
	*
	* @return the status by user name of this ligne
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _ligne.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this ligne.
	*
	* @return the status by user uuid of this ligne
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _ligne.getStatusByUserUuid();
	}

	/**
	* Returns the text color of this ligne.
	*
	* @return the text color of this ligne
	*/
	@Override
	public java.lang.String getTextColor() {
		return _ligne.getTextColor();
	}

	/**
	* Returns the title of this ligne.
	*
	* @return the title of this ligne
	*/
	@Override
	public java.lang.String getTitle() {
		return _ligne.getTitle();
	}

	/**
	* Returns the type of this ligne.
	*
	* @return the type of this ligne
	*/
	@Override
	public java.lang.String getType() {
		return _ligne.getType();
	}

	/**
	* Returns the user name of this ligne.
	*
	* @return the user name of this ligne
	*/
	@Override
	public java.lang.String getUserName() {
		return _ligne.getUserName();
	}

	/**
	* Returns the user uuid of this ligne.
	*
	* @return the user uuid of this ligne
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ligne.getUserUuid();
	}

	/**
	* Returns the uuid of this ligne.
	*
	* @return the uuid of this ligne
	*/
	@Override
	public java.lang.String getUuid() {
		return _ligne.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ligne.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ligne.toXmlString();
	}

	/**
	* Returns the create date of this ligne.
	*
	* @return the create date of this ligne
	*/
	@Override
	public Date getCreateDate() {
		return _ligne.getCreateDate();
	}

	/**
	* Returns the modified date of this ligne.
	*
	* @return the modified date of this ligne
	*/
	@Override
	public Date getModifiedDate() {
		return _ligne.getModifiedDate();
	}

	/**
	* Returns the status date of this ligne.
	*
	* @return the status date of this ligne
	*/
	@Override
	public Date getStatusDate() {
		return _ligne.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _ligne.getCategories();
	}

	/**
	* Renvoie la liste des Directions de cette ligne
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Direction> getDirections() {
		return _ligne.getDirections();
	}

	/**
	* Returns the company ID of this ligne.
	*
	* @return the company ID of this ligne
	*/
	@Override
	public long getCompanyId() {
		return _ligne.getCompanyId();
	}

	/**
	* Returns the group ID of this ligne.
	*
	* @return the group ID of this ligne
	*/
	@Override
	public long getGroupId() {
		return _ligne.getGroupId();
	}

	/**
	* Returns the ligne ID of this ligne.
	*
	* @return the ligne ID of this ligne
	*/
	@Override
	public long getLigneId() {
		return _ligne.getLigneId();
	}

	/**
	* Returns the primary key of this ligne.
	*
	* @return the primary key of this ligne
	*/
	@Override
	public long getPrimaryKey() {
		return _ligne.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this ligne.
	*
	* @return the status by user ID of this ligne
	*/
	@Override
	public long getStatusByUserId() {
		return _ligne.getStatusByUserId();
	}

	/**
	* Returns the user ID of this ligne.
	*
	* @return the user ID of this ligne
	*/
	@Override
	public long getUserId() {
		return _ligne.getUserId();
	}

	@Override
	public void persist() {
		_ligne.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ligne.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this ligne.
	*
	* @param companyId the company ID of this ligne
	*/
	@Override
	public void setCompanyId(long companyId) {
		_ligne.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this ligne.
	*
	* @param createDate the create date of this ligne
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ligne.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ligne.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ligne.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ligne.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this ligne.
	*
	* @param groupId the group ID of this ligne
	*/
	@Override
	public void setGroupId(long groupId) {
		_ligne.setGroupId(groupId);
	}

	/**
	* Sets the ligne ID of this ligne.
	*
	* @param ligneId the ligne ID of this ligne
	*/
	@Override
	public void setLigneId(long ligneId) {
		_ligne.setLigneId(ligneId);
	}

	/**
	* Sets the modified date of this ligne.
	*
	* @param modifiedDate the modified date of this ligne
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ligne.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ligne.setNew(n);
	}

	/**
	* Sets the primary key of this ligne.
	*
	* @param primaryKey the primary key of this ligne
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ligne.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ligne.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the route ID of this ligne.
	*
	* @param routeId the route ID of this ligne
	*/
	@Override
	public void setRouteId(java.lang.String routeId) {
		_ligne.setRouteId(routeId);
	}

	/**
	* Sets the short name of this ligne.
	*
	* @param shortName the short name of this ligne
	*/
	@Override
	public void setShortName(java.lang.String shortName) {
		_ligne.setShortName(shortName);
	}

	/**
	* Sets the status of this ligne.
	*
	* @param status the status of this ligne
	*/
	@Override
	public void setStatus(int status) {
		_ligne.setStatus(status);
	}

	/**
	* Sets the status by user ID of this ligne.
	*
	* @param statusByUserId the status by user ID of this ligne
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_ligne.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this ligne.
	*
	* @param statusByUserName the status by user name of this ligne
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_ligne.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this ligne.
	*
	* @param statusByUserUuid the status by user uuid of this ligne
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_ligne.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this ligne.
	*
	* @param statusDate the status date of this ligne
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_ligne.setStatusDate(statusDate);
	}

	/**
	* Sets the text color of this ligne.
	*
	* @param textColor the text color of this ligne
	*/
	@Override
	public void setTextColor(java.lang.String textColor) {
		_ligne.setTextColor(textColor);
	}

	/**
	* Sets the title of this ligne.
	*
	* @param title the title of this ligne
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_ligne.setTitle(title);
	}

	/**
	* Sets the type of this ligne.
	*
	* @param type the type of this ligne
	*/
	@Override
	public void setType(java.lang.String type) {
		_ligne.setType(type);
	}

	/**
	* Sets the user ID of this ligne.
	*
	* @param userId the user ID of this ligne
	*/
	@Override
	public void setUserId(long userId) {
		_ligne.setUserId(userId);
	}

	/**
	* Sets the user name of this ligne.
	*
	* @param userName the user name of this ligne
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ligne.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ligne.
	*
	* @param userUuid the user uuid of this ligne
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ligne.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this ligne.
	*
	* @param uuid the uuid of this ligne
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_ligne.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LigneWrapper)) {
			return false;
		}

		LigneWrapper ligneWrapper = (LigneWrapper)obj;

		if (Objects.equals(_ligne, ligneWrapper._ligne)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _ligne.getStagedModelType();
	}

	@Override
	public Ligne getWrappedModel() {
		return _ligne;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ligne.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ligne.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ligne.resetOriginalValues();
	}

	private final Ligne _ligne;
}
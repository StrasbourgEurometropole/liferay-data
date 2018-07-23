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

package eu.strasbourg.service.comment.model;

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
 * This class is a wrapper for {@link Signalement}.
 * </p>
 *
 * @author Romain Vergnais
 * @see Signalement
 * @generated
 */
@ProviderType
public class SignalementWrapper implements Signalement,
	ModelWrapper<Signalement> {
	public SignalementWrapper(Signalement signalement) {
		_signalement = signalement;
	}

	@Override
	public Class<?> getModelClass() {
		return Signalement.class;
	}

	@Override
	public String getModelClassName() {
		return Signalement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("signalementId", getSignalementId());
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
		attributes.put("commentId", getCommentId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long signalementId = (Long)attributes.get("signalementId");

		if (signalementId != null) {
			setSignalementId(signalementId);
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

		Long commentId = (Long)attributes.get("commentId");

		if (commentId != null) {
			setCommentId(commentId);
		}
	}

	/**
	* Returns <code>true</code> if this signalement is approved.
	*
	* @return <code>true</code> if this signalement is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _signalement.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _signalement.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this signalement is denied.
	*
	* @return <code>true</code> if this signalement is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _signalement.isDenied();
	}

	/**
	* Returns <code>true</code> if this signalement is a draft.
	*
	* @return <code>true</code> if this signalement is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _signalement.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _signalement.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this signalement is expired.
	*
	* @return <code>true</code> if this signalement is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _signalement.isExpired();
	}

	/**
	* Returns <code>true</code> if this signalement is inactive.
	*
	* @return <code>true</code> if this signalement is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _signalement.isInactive();
	}

	/**
	* Returns <code>true</code> if this signalement is incomplete.
	*
	* @return <code>true</code> if this signalement is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _signalement.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _signalement.isNew();
	}

	/**
	* Returns <code>true</code> if this signalement is pending.
	*
	* @return <code>true</code> if this signalement is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _signalement.isPending();
	}

	/**
	* Returns <code>true</code> if this signalement is scheduled.
	*
	* @return <code>true</code> if this signalement is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _signalement.isScheduled();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _signalement.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _signalement.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.comment.model.Signalement> toCacheModel() {
		return _signalement.toCacheModel();
	}

	/**
	* méthode qui permet de récupérer le commmentaire lié au signalement.
	*
	* @return le commentaire.
	*/
	@Override
	public eu.strasbourg.service.comment.model.Comment getComment() {
		return _signalement.getComment();
	}

	@Override
	public eu.strasbourg.service.comment.model.Signalement toEscapedModel() {
		return new SignalementWrapper(_signalement.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.comment.model.Signalement toUnescapedModel() {
		return new SignalementWrapper(_signalement.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.comment.model.Signalement signalement) {
		return _signalement.compareTo(signalement);
	}

	/**
	* Returns the status of this signalement.
	*
	* @return the status of this signalement
	*/
	@Override
	public int getStatus() {
		return _signalement.getStatus();
	}

	@Override
	public int hashCode() {
		return _signalement.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _signalement.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SignalementWrapper((Signalement)_signalement.clone());
	}

	/**
	* méthode qui permet de récupérer le commmentaire lié au signalement.
	*
	* @return le commentaire.
	*/
	@Override
	public java.lang.String getCommentContent() {
		return _signalement.getCommentContent();
	}

	/**
	* Returns the status by user name of this signalement.
	*
	* @return the status by user name of this signalement
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _signalement.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this signalement.
	*
	* @return the status by user uuid of this signalement
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _signalement.getStatusByUserUuid();
	}

	/**
	* Returns the user name of this signalement.
	*
	* @return the user name of this signalement
	*/
	@Override
	public java.lang.String getUserName() {
		return _signalement.getUserName();
	}

	/**
	* Returns the user uuid of this signalement.
	*
	* @return the user uuid of this signalement
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _signalement.getUserUuid();
	}

	/**
	* Returns the uuid of this signalement.
	*
	* @return the uuid of this signalement
	*/
	@Override
	public java.lang.String getUuid() {
		return _signalement.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _signalement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _signalement.toXmlString();
	}

	/**
	* Returns the create date of this signalement.
	*
	* @return the create date of this signalement
	*/
	@Override
	public Date getCreateDate() {
		return _signalement.getCreateDate();
	}

	/**
	* Returns the modified date of this signalement.
	*
	* @return the modified date of this signalement
	*/
	@Override
	public Date getModifiedDate() {
		return _signalement.getModifiedDate();
	}

	/**
	* Returns the status date of this signalement.
	*
	* @return the status date of this signalement
	*/
	@Override
	public Date getStatusDate() {
		return _signalement.getStatusDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _signalement.getCategories();
	}

	/**
	* Returns the comment ID of this signalement.
	*
	* @return the comment ID of this signalement
	*/
	@Override
	public long getCommentId() {
		return _signalement.getCommentId();
	}

	/**
	* Returns the company ID of this signalement.
	*
	* @return the company ID of this signalement
	*/
	@Override
	public long getCompanyId() {
		return _signalement.getCompanyId();
	}

	/**
	* Returns the group ID of this signalement.
	*
	* @return the group ID of this signalement
	*/
	@Override
	public long getGroupId() {
		return _signalement.getGroupId();
	}

	/**
	* Returns the primary key of this signalement.
	*
	* @return the primary key of this signalement
	*/
	@Override
	public long getPrimaryKey() {
		return _signalement.getPrimaryKey();
	}

	/**
	* Returns the signalement ID of this signalement.
	*
	* @return the signalement ID of this signalement
	*/
	@Override
	public long getSignalementId() {
		return _signalement.getSignalementId();
	}

	/**
	* Returns the status by user ID of this signalement.
	*
	* @return the status by user ID of this signalement
	*/
	@Override
	public long getStatusByUserId() {
		return _signalement.getStatusByUserId();
	}

	/**
	* Returns the user ID of this signalement.
	*
	* @return the user ID of this signalement
	*/
	@Override
	public long getUserId() {
		return _signalement.getUserId();
	}

	@Override
	public void persist() {
		_signalement.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_signalement.setCachedModel(cachedModel);
	}

	/**
	* Sets the comment ID of this signalement.
	*
	* @param commentId the comment ID of this signalement
	*/
	@Override
	public void setCommentId(long commentId) {
		_signalement.setCommentId(commentId);
	}

	/**
	* Sets the company ID of this signalement.
	*
	* @param companyId the company ID of this signalement
	*/
	@Override
	public void setCompanyId(long companyId) {
		_signalement.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this signalement.
	*
	* @param createDate the create date of this signalement
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_signalement.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_signalement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_signalement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_signalement.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this signalement.
	*
	* @param groupId the group ID of this signalement
	*/
	@Override
	public void setGroupId(long groupId) {
		_signalement.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this signalement.
	*
	* @param modifiedDate the modified date of this signalement
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_signalement.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_signalement.setNew(n);
	}

	/**
	* Sets the primary key of this signalement.
	*
	* @param primaryKey the primary key of this signalement
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_signalement.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_signalement.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the signalement ID of this signalement.
	*
	* @param signalementId the signalement ID of this signalement
	*/
	@Override
	public void setSignalementId(long signalementId) {
		_signalement.setSignalementId(signalementId);
	}

	/**
	* Sets the status of this signalement.
	*
	* @param status the status of this signalement
	*/
	@Override
	public void setStatus(int status) {
		_signalement.setStatus(status);
	}

	/**
	* Sets the status by user ID of this signalement.
	*
	* @param statusByUserId the status by user ID of this signalement
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_signalement.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this signalement.
	*
	* @param statusByUserName the status by user name of this signalement
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_signalement.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this signalement.
	*
	* @param statusByUserUuid the status by user uuid of this signalement
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_signalement.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this signalement.
	*
	* @param statusDate the status date of this signalement
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_signalement.setStatusDate(statusDate);
	}

	/**
	* Sets the user ID of this signalement.
	*
	* @param userId the user ID of this signalement
	*/
	@Override
	public void setUserId(long userId) {
		_signalement.setUserId(userId);
	}

	/**
	* Sets the user name of this signalement.
	*
	* @param userName the user name of this signalement
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_signalement.setUserName(userName);
	}

	/**
	* Sets the user uuid of this signalement.
	*
	* @param userUuid the user uuid of this signalement
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_signalement.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this signalement.
	*
	* @param uuid the uuid of this signalement
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_signalement.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SignalementWrapper)) {
			return false;
		}

		SignalementWrapper signalementWrapper = (SignalementWrapper)obj;

		if (Objects.equals(_signalement, signalementWrapper._signalement)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _signalement.getStagedModelType();
	}

	@Override
	public Signalement getWrappedModel() {
		return _signalement;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _signalement.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _signalement.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_signalement.resetOriginalValues();
	}

	private final Signalement _signalement;
}
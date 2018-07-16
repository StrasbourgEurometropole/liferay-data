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

package eu.strasbourg.service.like.model;

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
 * This class is a wrapper for {@link Like}.
 * </p>
 *
 * @author Cedric Henry
 * @see Like
 * @generated
 */
@ProviderType
public class LikeWrapper implements Like, ModelWrapper<Like> {
	public LikeWrapper(Like like) {
		_like = like;
	}

	@Override
	public Class<?> getModelClass() {
		return Like.class;
	}

	@Override
	public String getModelClassName() {
		return Like.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("likeId", getLikeId());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("title", getTitle());
		attributes.put("isDislike", getIsDislike());
		attributes.put("typeId", getTypeId());
		attributes.put("entityId", getEntityId());
		attributes.put("entityGroupId", getEntityGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long likeId = (Long)attributes.get("likeId");

		if (likeId != null) {
			setLikeId(likeId);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Boolean isDislike = (Boolean)attributes.get("isDislike");

		if (isDislike != null) {
			setIsDislike(isDislike);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long entityId = (Long)attributes.get("entityId");

		if (entityId != null) {
			setEntityId(entityId);
		}

		Long entityGroupId = (Long)attributes.get("entityGroupId");

		if (entityGroupId != null) {
			setEntityGroupId(entityGroupId);
		}
	}

	/**
	* Returns the is dislike of this like.
	*
	* @return the is dislike of this like
	*/
	@Override
	public boolean getIsDislike() {
		return _like.getIsDislike();
	}

	@Override
	public boolean isCachedModel() {
		return _like.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _like.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this like is is dislike.
	*
	* @return <code>true</code> if this like is is dislike; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsDislike() {
		return _like.isIsDislike();
	}

	@Override
	public boolean isNew() {
		return _like.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _like.getExpandoBridge();
	}

	/**
	* Retourne la version JSON d'un favoris
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _like.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.like.model.Like> toCacheModel() {
		return _like.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.like.model.Like toEscapedModel() {
		return new LikeWrapper(_like.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.like.model.Like toUnescapedModel() {
		return new LikeWrapper(_like.toUnescapedModel());
	}

	@Override
	public eu.strasbourg.service.like.model.LikeType getLikeType() {
		return _like.getLikeType();
	}

	@Override
	public int compareTo(eu.strasbourg.service.like.model.Like like) {
		return _like.compareTo(like);
	}

	@Override
	public int hashCode() {
		return _like.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _like.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LikeWrapper((Like)_like.clone());
	}

	/**
	* Returns the publik user ID of this like.
	*
	* @return the publik user ID of this like
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _like.getPublikUserId();
	}

	/**
	* Returns the title of this like.
	*
	* @return the title of this like
	*/
	@Override
	public java.lang.String getTitle() {
		return _like.getTitle();
	}

	@Override
	public java.lang.String toString() {
		return _like.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _like.toXmlString();
	}

	/**
	* Returns the entity group ID of this like.
	*
	* @return the entity group ID of this like
	*/
	@Override
	public long getEntityGroupId() {
		return _like.getEntityGroupId();
	}

	/**
	* Returns the entity ID of this like.
	*
	* @return the entity ID of this like
	*/
	@Override
	public long getEntityId() {
		return _like.getEntityId();
	}

	/**
	* Returns the like ID of this like.
	*
	* @return the like ID of this like
	*/
	@Override
	public long getLikeId() {
		return _like.getLikeId();
	}

	/**
	* Returns the primary key of this like.
	*
	* @return the primary key of this like
	*/
	@Override
	public long getPrimaryKey() {
		return _like.getPrimaryKey();
	}

	/**
	* Returns the type ID of this like.
	*
	* @return the type ID of this like
	*/
	@Override
	public long getTypeId() {
		return _like.getTypeId();
	}

	@Override
	public void persist() {
		_like.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_like.setCachedModel(cachedModel);
	}

	/**
	* Sets the entity group ID of this like.
	*
	* @param entityGroupId the entity group ID of this like
	*/
	@Override
	public void setEntityGroupId(long entityGroupId) {
		_like.setEntityGroupId(entityGroupId);
	}

	/**
	* Sets the entity ID of this like.
	*
	* @param entityId the entity ID of this like
	*/
	@Override
	public void setEntityId(long entityId) {
		_like.setEntityId(entityId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_like.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_like.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_like.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this like is is dislike.
	*
	* @param isDislike the is dislike of this like
	*/
	@Override
	public void setIsDislike(boolean isDislike) {
		_like.setIsDislike(isDislike);
	}

	/**
	* Sets the like ID of this like.
	*
	* @param likeId the like ID of this like
	*/
	@Override
	public void setLikeId(long likeId) {
		_like.setLikeId(likeId);
	}

	@Override
	public void setNew(boolean n) {
		_like.setNew(n);
	}

	/**
	* Sets the primary key of this like.
	*
	* @param primaryKey the primary key of this like
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_like.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_like.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this like.
	*
	* @param publikUserId the publik user ID of this like
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_like.setPublikUserId(publikUserId);
	}

	/**
	* Sets the title of this like.
	*
	* @param title the title of this like
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_like.setTitle(title);
	}

	/**
	* Sets the type ID of this like.
	*
	* @param typeId the type ID of this like
	*/
	@Override
	public void setTypeId(long typeId) {
		_like.setTypeId(typeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LikeWrapper)) {
			return false;
		}

		LikeWrapper likeWrapper = (LikeWrapper)obj;

		if (Objects.equals(_like, likeWrapper._like)) {
			return true;
		}

		return false;
	}

	@Override
	public Like getWrappedModel() {
		return _like;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _like.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _like.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_like.resetOriginalValues();
	}

	private final Like _like;
}
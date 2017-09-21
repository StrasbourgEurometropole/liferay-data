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

package eu.strasbourg.service.favorite.model;

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
 * This class is a wrapper for {@link Favorite}.
 * </p>
 *
 * @author BenjaminBini
 * @see Favorite
 * @generated
 */
@ProviderType
public class FavoriteWrapper implements Favorite, ModelWrapper<Favorite> {
	public FavoriteWrapper(Favorite favorite) {
		_favorite = favorite;
	}

	@Override
	public Class<?> getModelClass() {
		return Favorite.class;
	}

	@Override
	public String getModelClassName() {
		return Favorite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("favoriteId", getFavoriteId());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("title", getTitle());
		attributes.put("url", getUrl());
		attributes.put("typeId", getTypeId());
		attributes.put("entityId", getEntityId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long favoriteId = (Long)attributes.get("favoriteId");

		if (favoriteId != null) {
			setFavoriteId(favoriteId);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long entityId = (Long)attributes.get("entityId");

		if (entityId != null) {
			setEntityId(entityId);
		}
	}

	@Override
	public boolean hasAssetEntry() {
		return _favorite.hasAssetEntry();
	}

	@Override
	public boolean isCachedModel() {
		return _favorite.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _favorite.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _favorite.isNew();
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _favorite.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _favorite.getExpandoBridge();
	}

	/**
	* Retourne la version JSON d'un favoris
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _favorite.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.favorite.model.Favorite> toCacheModel() {
		return _favorite.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.favorite.model.Favorite toEscapedModel() {
		return new FavoriteWrapper(_favorite.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.favorite.model.Favorite toUnescapedModel() {
		return new FavoriteWrapper(_favorite.toUnescapedModel());
	}

	@Override
	public eu.strasbourg.service.favorite.model.FavoriteType getFavoriteType() {
		return _favorite.getFavoriteType();
	}

	@Override
	public int compareTo(eu.strasbourg.service.favorite.model.Favorite favorite) {
		return _favorite.compareTo(favorite);
	}

	@Override
	public int hashCode() {
		return _favorite.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _favorite.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FavoriteWrapper((Favorite)_favorite.clone());
	}

	/**
	* Returns the publik user ID of this favorite.
	*
	* @return the publik user ID of this favorite
	*/
	@Override
	public java.lang.String getPublikUserId() {
		return _favorite.getPublikUserId();
	}

	/**
	* Returns the title of this favorite.
	*
	* @return the title of this favorite
	*/
	@Override
	public java.lang.String getTitle() {
		return _favorite.getTitle();
	}

	/**
	* Returns the url of this favorite.
	*
	* @return the url of this favorite
	*/
	@Override
	public java.lang.String getUrl() {
		return _favorite.getUrl();
	}

	@Override
	public java.lang.String toString() {
		return _favorite.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _favorite.toXmlString();
	}

	/**
	* Returns the entity ID of this favorite.
	*
	* @return the entity ID of this favorite
	*/
	@Override
	public long getEntityId() {
		return _favorite.getEntityId();
	}

	/**
	* Returns the favorite ID of this favorite.
	*
	* @return the favorite ID of this favorite
	*/
	@Override
	public long getFavoriteId() {
		return _favorite.getFavoriteId();
	}

	/**
	* Returns the primary key of this favorite.
	*
	* @return the primary key of this favorite
	*/
	@Override
	public long getPrimaryKey() {
		return _favorite.getPrimaryKey();
	}

	/**
	* Returns the type ID of this favorite.
	*
	* @return the type ID of this favorite
	*/
	@Override
	public long getTypeId() {
		return _favorite.getTypeId();
	}

	@Override
	public void persist() {
		_favorite.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_favorite.setCachedModel(cachedModel);
	}

	/**
	* Sets the entity ID of this favorite.
	*
	* @param entityId the entity ID of this favorite
	*/
	@Override
	public void setEntityId(long entityId) {
		_favorite.setEntityId(entityId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_favorite.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_favorite.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_favorite.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the favorite ID of this favorite.
	*
	* @param favoriteId the favorite ID of this favorite
	*/
	@Override
	public void setFavoriteId(long favoriteId) {
		_favorite.setFavoriteId(favoriteId);
	}

	@Override
	public void setNew(boolean n) {
		_favorite.setNew(n);
	}

	/**
	* Sets the primary key of this favorite.
	*
	* @param primaryKey the primary key of this favorite
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_favorite.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_favorite.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik user ID of this favorite.
	*
	* @param publikUserId the publik user ID of this favorite
	*/
	@Override
	public void setPublikUserId(java.lang.String publikUserId) {
		_favorite.setPublikUserId(publikUserId);
	}

	/**
	* Sets the title of this favorite.
	*
	* @param title the title of this favorite
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_favorite.setTitle(title);
	}

	/**
	* Sets the type ID of this favorite.
	*
	* @param typeId the type ID of this favorite
	*/
	@Override
	public void setTypeId(long typeId) {
		_favorite.setTypeId(typeId);
	}

	/**
	* Sets the url of this favorite.
	*
	* @param url the url of this favorite
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_favorite.setUrl(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FavoriteWrapper)) {
			return false;
		}

		FavoriteWrapper favoriteWrapper = (FavoriteWrapper)obj;

		if (Objects.equals(_favorite, favoriteWrapper._favorite)) {
			return true;
		}

		return false;
	}

	@Override
	public Favorite getWrappedModel() {
		return _favorite;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _favorite.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _favorite.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_favorite.resetOriginalValues();
	}

	private final Favorite _favorite;
}
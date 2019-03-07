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

package eu.strasbourg.service.oidc.model;

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
 * This class is a wrapper for {@link PublikUser}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUser
 * @generated
 */
@ProviderType
public class PublikUserWrapper implements PublikUser, ModelWrapper<PublikUser> {
	public PublikUserWrapper(PublikUser publikUser) {
		_publikUser = publikUser;
	}

	@Override
	public Class<?> getModelClass() {
		return PublikUser.class;
	}

	@Override
	public String getModelClassName() {
		return PublikUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("publikUserLiferayId", getPublikUserLiferayId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("publikId", getPublikId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("email", getEmail());
		attributes.put("mapConfig", getMapConfig());
		attributes.put("displayConfig", getDisplayConfig());
		attributes.put("pactSignature", getPactSignature());
		attributes.put("banishDate", getBanishDate());
		attributes.put("banishDescription", getBanishDescription());
		attributes.put("imageURL", getImageURL());
		attributes.put("pactDisplay", getPactDisplay());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long publikUserLiferayId = (Long)attributes.get("publikUserLiferayId");

		if (publikUserLiferayId != null) {
			setPublikUserLiferayId(publikUserLiferayId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String mapConfig = (String)attributes.get("mapConfig");

		if (mapConfig != null) {
			setMapConfig(mapConfig);
		}

		String displayConfig = (String)attributes.get("displayConfig");

		if (displayConfig != null) {
			setDisplayConfig(displayConfig);
		}

		Date pactSignature = (Date)attributes.get("pactSignature");

		if (pactSignature != null) {
			setPactSignature(pactSignature);
		}

		Date banishDate = (Date)attributes.get("banishDate");

		if (banishDate != null) {
			setBanishDate(banishDate);
		}

		String banishDescription = (String)attributes.get("banishDescription");

		if (banishDescription != null) {
			setBanishDescription(banishDescription);
		}

		String imageURL = (String)attributes.get("imageURL");

		if (imageURL != null) {
			setImageURL(imageURL);
		}

		Boolean pactDisplay = (Boolean)attributes.get("pactDisplay");

		if (pactDisplay != null) {
			setPactDisplay(pactDisplay);
		}
	}

	/**
	* Returns the pact display of this publik user.
	*
	* @return the pact display of this publik user
	*/
	@Override
	public boolean getPactDisplay() {
		return _publikUser.getPactDisplay();
	}

	/**
	* L'utilisateur est-il en perdiode de bannissement ?
	*/
	@Override
	public boolean isBanned() {
		return _publikUser.isBanned();
	}

	@Override
	public boolean isCachedModel() {
		return _publikUser.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _publikUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _publikUser.isNew();
	}

	/**
	* Returns <code>true</code> if this publik user is pact display.
	*
	* @return <code>true</code> if this publik user is pact display; <code>false</code> otherwise
	*/
	@Override
	public boolean isPactDisplay() {
		return _publikUser.isPactDisplay();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _publikUser.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.oidc.model.PublikUser> toCacheModel() {
		return _publikUser.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser toEscapedModel() {
		return new PublikUserWrapper(_publikUser.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser toUnescapedModel() {
		return new PublikUserWrapper(_publikUser.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.oidc.model.PublikUser publikUser) {
		return _publikUser.compareTo(publikUser);
	}

	@Override
	public int hashCode() {
		return _publikUser.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _publikUser.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PublikUserWrapper((PublikUser)_publikUser.clone());
	}

	/**
	* Returns the access token of this publik user.
	*
	* @return the access token of this publik user
	*/
	@Override
	public java.lang.String getAccessToken() {
		return _publikUser.getAccessToken();
	}

	/**
	* Returns the banish description of this publik user.
	*
	* @return the banish description of this publik user
	*/
	@Override
	public java.lang.String getBanishDescription() {
		return _publikUser.getBanishDescription();
	}

	/**
	* Returns the display config of this publik user.
	*
	* @return the display config of this publik user
	*/
	@Override
	public java.lang.String getDisplayConfig() {
		return _publikUser.getDisplayConfig();
	}

	/**
	* Returns the email of this publik user.
	*
	* @return the email of this publik user
	*/
	@Override
	public java.lang.String getEmail() {
		return _publikUser.getEmail();
	}

	/**
	* Returns the first name of this publik user.
	*
	* @return the first name of this publik user
	*/
	@Override
	public java.lang.String getFirstName() {
		return _publikUser.getFirstName();
	}

	/**
	* Returns the image url of this publik user.
	*
	* @return the image url of this publik user
	*/
	@Override
	public java.lang.String getImageURL() {
		return _publikUser.getImageURL();
	}

	/**
	* Retourne l'image de profil ou le substitut
	*/
	@Override
	public java.lang.String getImageURLOrSurrogate() {
		return _publikUser.getImageURLOrSurrogate();
	}

	/**
	* Returns the last name of this publik user.
	*
	* @return the last name of this publik user
	*/
	@Override
	public java.lang.String getLastName() {
		return _publikUser.getLastName();
	}

	/**
	* Returns the map config of this publik user.
	*
	* @return the map config of this publik user
	*/
	@Override
	public java.lang.String getMapConfig() {
		return _publikUser.getMapConfig();
	}

	/**
	* Returns the publik ID of this publik user.
	*
	* @return the publik ID of this publik user
	*/
	@Override
	public java.lang.String getPublikId() {
		return _publikUser.getPublikId();
	}

	/**
	* Returns the user name of this publik user.
	*
	* @return the user name of this publik user
	*/
	@Override
	public java.lang.String getUserName() {
		return _publikUser.getUserName();
	}

	/**
	* Returns the user uuid of this publik user.
	*
	* @return the user uuid of this publik user
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _publikUser.getUserUuid();
	}

	/**
	* Returns the uuid of this publik user.
	*
	* @return the uuid of this publik user
	*/
	@Override
	public java.lang.String getUuid() {
		return _publikUser.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _publikUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _publikUser.toXmlString();
	}

	/**
	* Returns the banish date of this publik user.
	*
	* @return the banish date of this publik user
	*/
	@Override
	public Date getBanishDate() {
		return _publikUser.getBanishDate();
	}

	/**
	* Returns the create date of this publik user.
	*
	* @return the create date of this publik user
	*/
	@Override
	public Date getCreateDate() {
		return _publikUser.getCreateDate();
	}

	/**
	* Returns the modified date of this publik user.
	*
	* @return the modified date of this publik user
	*/
	@Override
	public Date getModifiedDate() {
		return _publikUser.getModifiedDate();
	}

	/**
	* Returns the pact signature of this publik user.
	*
	* @return the pact signature of this publik user
	*/
	@Override
	public Date getPactSignature() {
		return _publikUser.getPactSignature();
	}

	/**
	* Returns the primary key of this publik user.
	*
	* @return the primary key of this publik user
	*/
	@Override
	public long getPrimaryKey() {
		return _publikUser.getPrimaryKey();
	}

	/**
	* Returns the publik user liferay ID of this publik user.
	*
	* @return the publik user liferay ID of this publik user
	*/
	@Override
	public long getPublikUserLiferayId() {
		return _publikUser.getPublikUserLiferayId();
	}

	/**
	* Returns the user ID of this publik user.
	*
	* @return the user ID of this publik user
	*/
	@Override
	public long getUserId() {
		return _publikUser.getUserId();
	}

	@Override
	public void persist() {
		_publikUser.persist();
	}

	/**
	* Sets the access token of this publik user.
	*
	* @param accessToken the access token of this publik user
	*/
	@Override
	public void setAccessToken(java.lang.String accessToken) {
		_publikUser.setAccessToken(accessToken);
	}

	/**
	* Sets the banish date of this publik user.
	*
	* @param banishDate the banish date of this publik user
	*/
	@Override
	public void setBanishDate(Date banishDate) {
		_publikUser.setBanishDate(banishDate);
	}

	/**
	* Sets the banish description of this publik user.
	*
	* @param banishDescription the banish description of this publik user
	*/
	@Override
	public void setBanishDescription(java.lang.String banishDescription) {
		_publikUser.setBanishDescription(banishDescription);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_publikUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this publik user.
	*
	* @param createDate the create date of this publik user
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_publikUser.setCreateDate(createDate);
	}

	/**
	* Sets the display config of this publik user.
	*
	* @param displayConfig the display config of this publik user
	*/
	@Override
	public void setDisplayConfig(java.lang.String displayConfig) {
		_publikUser.setDisplayConfig(displayConfig);
	}

	/**
	* Sets the email of this publik user.
	*
	* @param email the email of this publik user
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_publikUser.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_publikUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_publikUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_publikUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the first name of this publik user.
	*
	* @param firstName the first name of this publik user
	*/
	@Override
	public void setFirstName(java.lang.String firstName) {
		_publikUser.setFirstName(firstName);
	}

	/**
	* Sets the image url of this publik user.
	*
	* @param imageURL the image url of this publik user
	*/
	@Override
	public void setImageURL(java.lang.String imageURL) {
		_publikUser.setImageURL(imageURL);
	}

	/**
	* Sets the last name of this publik user.
	*
	* @param lastName the last name of this publik user
	*/
	@Override
	public void setLastName(java.lang.String lastName) {
		_publikUser.setLastName(lastName);
	}

	/**
	* Sets the map config of this publik user.
	*
	* @param mapConfig the map config of this publik user
	*/
	@Override
	public void setMapConfig(java.lang.String mapConfig) {
		_publikUser.setMapConfig(mapConfig);
	}

	/**
	* Sets the modified date of this publik user.
	*
	* @param modifiedDate the modified date of this publik user
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_publikUser.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_publikUser.setNew(n);
	}

	/**
	* Sets whether this publik user is pact display.
	*
	* @param pactDisplay the pact display of this publik user
	*/
	@Override
	public void setPactDisplay(boolean pactDisplay) {
		_publikUser.setPactDisplay(pactDisplay);
	}

	/**
	* Sets the pact signature of this publik user.
	*
	* @param pactSignature the pact signature of this publik user
	*/
	@Override
	public void setPactSignature(Date pactSignature) {
		_publikUser.setPactSignature(pactSignature);
	}

	/**
	* Sets the primary key of this publik user.
	*
	* @param primaryKey the primary key of this publik user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_publikUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_publikUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the publik ID of this publik user.
	*
	* @param publikId the publik ID of this publik user
	*/
	@Override
	public void setPublikId(java.lang.String publikId) {
		_publikUser.setPublikId(publikId);
	}

	/**
	* Sets the publik user liferay ID of this publik user.
	*
	* @param publikUserLiferayId the publik user liferay ID of this publik user
	*/
	@Override
	public void setPublikUserLiferayId(long publikUserLiferayId) {
		_publikUser.setPublikUserLiferayId(publikUserLiferayId);
	}

	/**
	* Sets the user ID of this publik user.
	*
	* @param userId the user ID of this publik user
	*/
	@Override
	public void setUserId(long userId) {
		_publikUser.setUserId(userId);
	}

	/**
	* Sets the user name of this publik user.
	*
	* @param userName the user name of this publik user
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_publikUser.setUserName(userName);
	}

	/**
	* Sets the user uuid of this publik user.
	*
	* @param userUuid the user uuid of this publik user
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_publikUser.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this publik user.
	*
	* @param uuid the uuid of this publik user
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_publikUser.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublikUserWrapper)) {
			return false;
		}

		PublikUserWrapper publikUserWrapper = (PublikUserWrapper)obj;

		if (Objects.equals(_publikUser, publikUserWrapper._publikUser)) {
			return true;
		}

		return false;
	}

	@Override
	public PublikUser getWrappedModel() {
		return _publikUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _publikUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _publikUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_publikUser.resetOriginalValues();
	}

	private final PublikUser _publikUser;
}
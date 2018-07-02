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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PublikUser service. Represents a row in the &quot;publik_PublikUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.oidc.model.impl.PublikUserImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUser
 * @see eu.strasbourg.service.oidc.model.impl.PublikUserImpl
 * @see eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl
 * @generated
 */
@ProviderType
public interface PublikUserModel extends BaseModel<PublikUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a publik user model instance should use the {@link PublikUser} interface instead.
	 */

	/**
	 * Returns the primary key of this publik user.
	 *
	 * @return the primary key of this publik user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this publik user.
	 *
	 * @param primaryKey the primary key of this publik user
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this publik user.
	 *
	 * @return the uuid of this publik user
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this publik user.
	 *
	 * @param uuid the uuid of this publik user
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the publik user liferay ID of this publik user.
	 *
	 * @return the publik user liferay ID of this publik user
	 */
	public long getPublikUserLiferayId();

	/**
	 * Sets the publik user liferay ID of this publik user.
	 *
	 * @param publikUserLiferayId the publik user liferay ID of this publik user
	 */
	public void setPublikUserLiferayId(long publikUserLiferayId);

	/**
	 * Returns the create date of this publik user.
	 *
	 * @return the create date of this publik user
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this publik user.
	 *
	 * @param createDate the create date of this publik user
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this publik user.
	 *
	 * @return the modified date of this publik user
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this publik user.
	 *
	 * @param modifiedDate the modified date of this publik user
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the publik ID of this publik user.
	 *
	 * @return the publik ID of this publik user
	 */
	@AutoEscape
	public String getPublikId();

	/**
	 * Sets the publik ID of this publik user.
	 *
	 * @param publikId the publik ID of this publik user
	 */
	public void setPublikId(String publikId);

	/**
	 * Returns the access token of this publik user.
	 *
	 * @return the access token of this publik user
	 */
	@AutoEscape
	public String getAccessToken();

	/**
	 * Sets the access token of this publik user.
	 *
	 * @param accessToken the access token of this publik user
	 */
	public void setAccessToken(String accessToken);

	/**
	 * Returns the first name of this publik user.
	 *
	 * @return the first name of this publik user
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this publik user.
	 *
	 * @param firstName the first name of this publik user
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the last name of this publik user.
	 *
	 * @return the last name of this publik user
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this publik user.
	 *
	 * @param lastName the last name of this publik user
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the email of this publik user.
	 *
	 * @return the email of this publik user
	 */
	@AutoEscape
	public String getEmail();

	/**
	 * Sets the email of this publik user.
	 *
	 * @param email the email of this publik user
	 */
	public void setEmail(String email);

	/**
	 * Returns the map config of this publik user.
	 *
	 * @return the map config of this publik user
	 */
	@AutoEscape
	public String getMapConfig();

	/**
	 * Sets the map config of this publik user.
	 *
	 * @param mapConfig the map config of this publik user
	 */
	public void setMapConfig(String mapConfig);

	/**
	 * Returns the display config of this publik user.
	 *
	 * @return the display config of this publik user
	 */
	@AutoEscape
	public String getDisplayConfig();

	/**
	 * Sets the display config of this publik user.
	 *
	 * @param displayConfig the display config of this publik user
	 */
	public void setDisplayConfig(String displayConfig);

	/**
	 * Returns the pact signature of this publik user.
	 *
	 * @return the pact signature of this publik user
	 */
	public Date getPactSignature();

	/**
	 * Sets the pact signature of this publik user.
	 *
	 * @param pactSignature the pact signature of this publik user
	 */
	public void setPactSignature(Date pactSignature);

	/**
	 * Returns the banish date of this publik user.
	 *
	 * @return the banish date of this publik user
	 */
	public Date getBanishDate();

	/**
	 * Sets the banish date of this publik user.
	 *
	 * @param banishDate the banish date of this publik user
	 */
	public void setBanishDate(Date banishDate);

	/**
	 * Returns the banish description of this publik user.
	 *
	 * @return the banish description of this publik user
	 */
	@AutoEscape
	public String getBanishDescription();

	/**
	 * Sets the banish description of this publik user.
	 *
	 * @param banishDescription the banish description of this publik user
	 */
	public void setBanishDescription(String banishDescription);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(eu.strasbourg.service.oidc.model.PublikUser publikUser);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.oidc.model.PublikUser> toCacheModel();

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser toEscapedModel();

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
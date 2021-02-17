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

package eu.strasbourg.service.csmap.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link RefreshToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RefreshToken
 * @generated
 */
@ProviderType
public class RefreshTokenWrapper
	extends BaseModelWrapper<RefreshToken>
	implements RefreshToken, ModelWrapper<RefreshToken> {

	public RefreshTokenWrapper(RefreshToken refreshToken) {
		super(refreshToken);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("refreshTokenId", getRefreshTokenId());
		attributes.put("createDate", getCreateDate());
		attributes.put("value", getValue());
		attributes.put("publikId", getPublikId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long refreshTokenId = (Long)attributes.get("refreshTokenId");

		if (refreshTokenId != null) {
			setRefreshTokenId(refreshTokenId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}
	}

	/**
	 * Returns the create date of this refresh token.
	 *
	 * @return the create date of this refresh token
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the primary key of this refresh token.
	 *
	 * @return the primary key of this refresh token
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publik ID of this refresh token.
	 *
	 * @return the publik ID of this refresh token
	 */
	@Override
	public String getPublikId() {
		return model.getPublikId();
	}

	/**
	 * Returns the refresh token ID of this refresh token.
	 *
	 * @return the refresh token ID of this refresh token
	 */
	@Override
	public long getRefreshTokenId() {
		return model.getRefreshTokenId();
	}

	/**
	 * Returns the uuid of this refresh token.
	 *
	 * @return the uuid of this refresh token
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the value of this refresh token.
	 *
	 * @return the value of this refresh token
	 */
	@Override
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this refresh token.
	 *
	 * @param createDate the create date of this refresh token
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the primary key of this refresh token.
	 *
	 * @param primaryKey the primary key of this refresh token
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publik ID of this refresh token.
	 *
	 * @param publikId the publik ID of this refresh token
	 */
	@Override
	public void setPublikId(String publikId) {
		model.setPublikId(publikId);
	}

	/**
	 * Sets the refresh token ID of this refresh token.
	 *
	 * @param refreshTokenId the refresh token ID of this refresh token
	 */
	@Override
	public void setRefreshTokenId(long refreshTokenId) {
		model.setRefreshTokenId(refreshTokenId);
	}

	/**
	 * Sets the uuid of this refresh token.
	 *
	 * @param uuid the uuid of this refresh token
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the value of this refresh token.
	 *
	 * @param value the value of this refresh token
	 */
	@Override
	public void setValue(String value) {
		model.setValue(value);
	}

	@Override
	protected RefreshTokenWrapper wrap(RefreshToken refreshToken) {
		return new RefreshTokenWrapper(refreshToken);
	}

}
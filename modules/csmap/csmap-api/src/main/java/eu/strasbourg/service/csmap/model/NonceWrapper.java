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
 * This class is a wrapper for {@link Nonce}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Nonce
 * @generated
 */
@ProviderType
public class NonceWrapper
	extends BaseModelWrapper<Nonce> implements Nonce, ModelWrapper<Nonce> {

	public NonceWrapper(Nonce nonce) {
		super(nonce);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("nonceId", getNonceId());
		attributes.put("value", getValue());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long nonceId = (Long)attributes.get("nonceId");

		if (nonceId != null) {
			setNonceId(nonceId);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	/**
	 * Returns the expiration date of this nonce.
	 *
	 * @return the expiration date of this nonce
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the nonce ID of this nonce.
	 *
	 * @return the nonce ID of this nonce
	 */
	@Override
	public long getNonceId() {
		return model.getNonceId();
	}

	/**
	 * Returns the primary key of this nonce.
	 *
	 * @return the primary key of this nonce
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this nonce.
	 *
	 * @return the uuid of this nonce
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the value of this nonce.
	 *
	 * @return the value of this nonce
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
	 * Sets the expiration date of this nonce.
	 *
	 * @param expirationDate the expiration date of this nonce
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the nonce ID of this nonce.
	 *
	 * @param nonceId the nonce ID of this nonce
	 */
	@Override
	public void setNonceId(long nonceId) {
		model.setNonceId(nonceId);
	}

	/**
	 * Sets the primary key of this nonce.
	 *
	 * @param primaryKey the primary key of this nonce
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this nonce.
	 *
	 * @param uuid the uuid of this nonce
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the value of this nonce.
	 *
	 * @param value the value of this nonce
	 */
	@Override
	public void setValue(String value) {
		model.setValue(value);
	}

	@Override
	protected NonceWrapper wrap(Nonce nonce) {
		return new NonceWrapper(nonce);
	}

}
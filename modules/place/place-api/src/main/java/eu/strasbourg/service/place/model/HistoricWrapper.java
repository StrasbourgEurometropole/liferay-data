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

package eu.strasbourg.service.place.model;

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
 * This class is a wrapper for {@link Historic}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see Historic
 * @generated
 */
public class HistoricWrapper implements Historic, ModelWrapper<Historic> {

	public HistoricWrapper(Historic historic) {
		_historic = historic;
	}

	@Override
	public Class<?> getModelClass() {
		return Historic.class;
	}

	@Override
	public String getModelClassName() {
		return Historic.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sigId", getSigId());
		attributes.put("name", getName());
		attributes.put("suppressionDate", getSuppressionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String sigId = (String)attributes.get("sigId");

		if (sigId != null) {
			setSigId(sigId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date suppressionDate = (Date)attributes.get("suppressionDate");

		if (suppressionDate != null) {
			setSuppressionDate(suppressionDate);
		}
	}

	@Override
	public Object clone() {
		return new HistoricWrapper((Historic)_historic.clone());
	}

	@Override
	public int compareTo(eu.strasbourg.service.place.model.Historic historic) {
		return _historic.compareTo(historic);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _historic.getExpandoBridge();
	}

	/**
	 * Returns the name of this historic.
	 *
	 * @return the name of this historic
	 */
	@Override
	public String getName() {
		return _historic.getName();
	}

	/**
	 * Returns the primary key of this historic.
	 *
	 * @return the primary key of this historic
	 */
	@Override
	public String getPrimaryKey() {
		return _historic.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _historic.getPrimaryKeyObj();
	}

	/**
	 * Returns the sig ID of this historic.
	 *
	 * @return the sig ID of this historic
	 */
	@Override
	public String getSigId() {
		return _historic.getSigId();
	}

	/**
	 * Returns the suppression date of this historic.
	 *
	 * @return the suppression date of this historic
	 */
	@Override
	public Date getSuppressionDate() {
		return _historic.getSuppressionDate();
	}

	/**
	 * Returns the uuid of this historic.
	 *
	 * @return the uuid of this historic
	 */
	@Override
	public String getUuid() {
		return _historic.getUuid();
	}

	@Override
	public int hashCode() {
		return _historic.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _historic.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _historic.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _historic.isNew();
	}

	@Override
	public void persist() {
		_historic.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_historic.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_historic.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_historic.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_historic.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the name of this historic.
	 *
	 * @param name the name of this historic
	 */
	@Override
	public void setName(String name) {
		_historic.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_historic.setNew(n);
	}

	/**
	 * Sets the primary key of this historic.
	 *
	 * @param primaryKey the primary key of this historic
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		_historic.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_historic.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the sig ID of this historic.
	 *
	 * @param sigId the sig ID of this historic
	 */
	@Override
	public void setSigId(String sigId) {
		_historic.setSigId(sigId);
	}

	/**
	 * Sets the suppression date of this historic.
	 *
	 * @param suppressionDate the suppression date of this historic
	 */
	@Override
	public void setSuppressionDate(Date suppressionDate) {
		_historic.setSuppressionDate(suppressionDate);
	}

	/**
	 * Sets the uuid of this historic.
	 *
	 * @param uuid the uuid of this historic
	 */
	@Override
	public void setUuid(String uuid) {
		_historic.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.place.model.Historic> toCacheModel() {

		return _historic.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.Historic toEscapedModel() {
		return new HistoricWrapper(_historic.toEscapedModel());
	}

	@Override
	public String toString() {
		return _historic.toString();
	}

	@Override
	public eu.strasbourg.service.place.model.Historic toUnescapedModel() {
		return new HistoricWrapper(_historic.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _historic.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HistoricWrapper)) {
			return false;
		}

		HistoricWrapper historicWrapper = (HistoricWrapper)object;

		if (Objects.equals(_historic, historicWrapper._historic)) {
			return true;
		}

		return false;
	}

	@Override
	public Historic getWrappedModel() {
		return _historic;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _historic.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _historic.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_historic.resetOriginalValues();
	}

	private final Historic _historic;

}
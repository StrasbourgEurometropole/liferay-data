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
 * This class is a wrapper for {@link CsmapCacheJson}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see CsmapCacheJson
 * @generated
 */
@ProviderType
public class CsmapCacheJsonWrapper
	implements CsmapCacheJson, ModelWrapper<CsmapCacheJson> {

	public CsmapCacheJsonWrapper(CsmapCacheJson csmapCacheJson) {
		_csmapCacheJson = csmapCacheJson;
	}

	@Override
	public Class<?> getModelClass() {
		return CsmapCacheJson.class;
	}

	@Override
	public String getModelClassName() {
		return CsmapCacheJson.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sigId", getSigId());
		attributes.put("jsonLieu", getJsonLieu());
		attributes.put("jsonHoraire", getJsonHoraire());
		attributes.put("createPlace", getCreatePlace());
		attributes.put("modifiedPlace", getModifiedPlace());
		attributes.put("isActive", isIsActive());

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

		String jsonLieu = (String)attributes.get("jsonLieu");

		if (jsonLieu != null) {
			setJsonLieu(jsonLieu);
		}

		String jsonHoraire = (String)attributes.get("jsonHoraire");

		if (jsonHoraire != null) {
			setJsonHoraire(jsonHoraire);
		}

		Date createPlace = (Date)attributes.get("createPlace");

		if (createPlace != null) {
			setCreatePlace(createPlace);
		}

		Date modifiedPlace = (Date)attributes.get("modifiedPlace");

		if (modifiedPlace != null) {
			setModifiedPlace(modifiedPlace);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}
	}

	@Override
	public Object clone() {
		return new CsmapCacheJsonWrapper(
			(CsmapCacheJson)_csmapCacheJson.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.place.model.CsmapCacheJson csmapCacheJson) {

		return _csmapCacheJson.compareTo(csmapCacheJson);
	}

	/**
	 * Returns the create place of this csmap cache json.
	 *
	 * @return the create place of this csmap cache json
	 */
	@Override
	public Date getCreatePlace() {
		return _csmapCacheJson.getCreatePlace();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _csmapCacheJson.getExpandoBridge();
	}

	/**
	 * Returns the is active of this csmap cache json.
	 *
	 * @return the is active of this csmap cache json
	 */
	@Override
	public boolean getIsActive() {
		return _csmapCacheJson.getIsActive();
	}

	/**
	 * Returns the json horaire of this csmap cache json.
	 *
	 * @return the json horaire of this csmap cache json
	 */
	@Override
	public String getJsonHoraire() {
		return _csmapCacheJson.getJsonHoraire();
	}

	/**
	 * Returns the json lieu of this csmap cache json.
	 *
	 * @return the json lieu of this csmap cache json
	 */
	@Override
	public String getJsonLieu() {
		return _csmapCacheJson.getJsonLieu();
	}

	/**
	 * Returns the modified place of this csmap cache json.
	 *
	 * @return the modified place of this csmap cache json
	 */
	@Override
	public Date getModifiedPlace() {
		return _csmapCacheJson.getModifiedPlace();
	}

	/**
	 * Returns the primary key of this csmap cache json.
	 *
	 * @return the primary key of this csmap cache json
	 */
	@Override
	public String getPrimaryKey() {
		return _csmapCacheJson.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _csmapCacheJson.getPrimaryKeyObj();
	}

	/**
	 * Returns the sig ID of this csmap cache json.
	 *
	 * @return the sig ID of this csmap cache json
	 */
	@Override
	public String getSigId() {
		return _csmapCacheJson.getSigId();
	}

	/**
	 * Returns the uuid of this csmap cache json.
	 *
	 * @return the uuid of this csmap cache json
	 */
	@Override
	public String getUuid() {
		return _csmapCacheJson.getUuid();
	}

	@Override
	public int hashCode() {
		return _csmapCacheJson.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _csmapCacheJson.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _csmapCacheJson.isEscapedModel();
	}

	/**
	 * Returns <code>true</code> if this csmap cache json is is active.
	 *
	 * @return <code>true</code> if this csmap cache json is is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsActive() {
		return _csmapCacheJson.isIsActive();
	}

	@Override
	public boolean isNew() {
		return _csmapCacheJson.isNew();
	}

	@Override
	public void persist() {
		_csmapCacheJson.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_csmapCacheJson.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create place of this csmap cache json.
	 *
	 * @param createPlace the create place of this csmap cache json
	 */
	@Override
	public void setCreatePlace(Date createPlace) {
		_csmapCacheJson.setCreatePlace(createPlace);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_csmapCacheJson.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_csmapCacheJson.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_csmapCacheJson.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets whether this csmap cache json is is active.
	 *
	 * @param isActive the is active of this csmap cache json
	 */
	@Override
	public void setIsActive(boolean isActive) {
		_csmapCacheJson.setIsActive(isActive);
	}

	/**
	 * Sets the json horaire of this csmap cache json.
	 *
	 * @param jsonHoraire the json horaire of this csmap cache json
	 */
	@Override
	public void setJsonHoraire(String jsonHoraire) {
		_csmapCacheJson.setJsonHoraire(jsonHoraire);
	}

	/**
	 * Sets the json lieu of this csmap cache json.
	 *
	 * @param jsonLieu the json lieu of this csmap cache json
	 */
	@Override
	public void setJsonLieu(String jsonLieu) {
		_csmapCacheJson.setJsonLieu(jsonLieu);
	}

	/**
	 * Sets the modified place of this csmap cache json.
	 *
	 * @param modifiedPlace the modified place of this csmap cache json
	 */
	@Override
	public void setModifiedPlace(Date modifiedPlace) {
		_csmapCacheJson.setModifiedPlace(modifiedPlace);
	}

	@Override
	public void setNew(boolean n) {
		_csmapCacheJson.setNew(n);
	}

	/**
	 * Sets the primary key of this csmap cache json.
	 *
	 * @param primaryKey the primary key of this csmap cache json
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		_csmapCacheJson.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_csmapCacheJson.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the sig ID of this csmap cache json.
	 *
	 * @param sigId the sig ID of this csmap cache json
	 */
	@Override
	public void setSigId(String sigId) {
		_csmapCacheJson.setSigId(sigId);
	}

	/**
	 * Sets the uuid of this csmap cache json.
	 *
	 * @param uuid the uuid of this csmap cache json
	 */
	@Override
	public void setUuid(String uuid) {
		_csmapCacheJson.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.place.model.CsmapCacheJson> toCacheModel() {

		return _csmapCacheJson.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.place.model.CsmapCacheJson toEscapedModel() {
		return new CsmapCacheJsonWrapper(_csmapCacheJson.toEscapedModel());
	}

	@Override
	public String toString() {
		return _csmapCacheJson.toString();
	}

	@Override
	public eu.strasbourg.service.place.model.CsmapCacheJson toUnescapedModel() {
		return new CsmapCacheJsonWrapper(_csmapCacheJson.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _csmapCacheJson.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CsmapCacheJsonWrapper)) {
			return false;
		}

		CsmapCacheJsonWrapper csmapCacheJsonWrapper =
			(CsmapCacheJsonWrapper)obj;

		if (Objects.equals(
				_csmapCacheJson, csmapCacheJsonWrapper._csmapCacheJson)) {

			return true;
		}

		return false;
	}

	@Override
	public CsmapCacheJson getWrappedModel() {
		return _csmapCacheJson;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _csmapCacheJson.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _csmapCacheJson.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_csmapCacheJson.resetOriginalValues();
	}

	private final CsmapCacheJson _csmapCacheJson;

}
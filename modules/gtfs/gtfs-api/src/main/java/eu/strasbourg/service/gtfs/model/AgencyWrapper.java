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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Agency}.
 * </p>
 *
 * @author Cedric Henry
 * @see Agency
 * @generated
 */
@ProviderType
public class AgencyWrapper implements Agency, ModelWrapper<Agency> {
	public AgencyWrapper(Agency agency) {
		_agency = agency;
	}

	@Override
	public Class<?> getModelClass() {
		return Agency.class;
	}

	@Override
	public String getModelClassName() {
		return Agency.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("agency_name", getAgency_name());
		attributes.put("agency_url", getAgency_url());
		attributes.put("agency_timezone", getAgency_timezone());
		attributes.put("agency_phone", getAgency_phone());
		attributes.put("agency_fare_url", getAgency_fare_url());
		attributes.put("agency_lang", getAgency_lang());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String agency_name = (String)attributes.get("agency_name");

		if (agency_name != null) {
			setAgency_name(agency_name);
		}

		Boolean agency_url = (Boolean)attributes.get("agency_url");

		if (agency_url != null) {
			setAgency_url(agency_url);
		}

		Boolean agency_timezone = (Boolean)attributes.get("agency_timezone");

		if (agency_timezone != null) {
			setAgency_timezone(agency_timezone);
		}

		Boolean agency_phone = (Boolean)attributes.get("agency_phone");

		if (agency_phone != null) {
			setAgency_phone(agency_phone);
		}

		Boolean agency_fare_url = (Boolean)attributes.get("agency_fare_url");

		if (agency_fare_url != null) {
			setAgency_fare_url(agency_fare_url);
		}

		Boolean agency_lang = (Boolean)attributes.get("agency_lang");

		if (agency_lang != null) {
			setAgency_lang(agency_lang);
		}
	}

	/**
	* Returns the agency_fare_url of this agency.
	*
	* @return the agency_fare_url of this agency
	*/
	@Override
	public boolean getAgency_fare_url() {
		return _agency.getAgency_fare_url();
	}

	/**
	* Returns the agency_lang of this agency.
	*
	* @return the agency_lang of this agency
	*/
	@Override
	public boolean getAgency_lang() {
		return _agency.getAgency_lang();
	}

	/**
	* Returns the agency_phone of this agency.
	*
	* @return the agency_phone of this agency
	*/
	@Override
	public boolean getAgency_phone() {
		return _agency.getAgency_phone();
	}

	/**
	* Returns the agency_timezone of this agency.
	*
	* @return the agency_timezone of this agency
	*/
	@Override
	public boolean getAgency_timezone() {
		return _agency.getAgency_timezone();
	}

	/**
	* Returns the agency_url of this agency.
	*
	* @return the agency_url of this agency
	*/
	@Override
	public boolean getAgency_url() {
		return _agency.getAgency_url();
	}

	/**
	* Returns <code>true</code> if this agency is agency_fare_url.
	*
	* @return <code>true</code> if this agency is agency_fare_url; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgency_fare_url() {
		return _agency.isAgency_fare_url();
	}

	/**
	* Returns <code>true</code> if this agency is agency_lang.
	*
	* @return <code>true</code> if this agency is agency_lang; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgency_lang() {
		return _agency.isAgency_lang();
	}

	/**
	* Returns <code>true</code> if this agency is agency_phone.
	*
	* @return <code>true</code> if this agency is agency_phone; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgency_phone() {
		return _agency.isAgency_phone();
	}

	/**
	* Returns <code>true</code> if this agency is agency_timezone.
	*
	* @return <code>true</code> if this agency is agency_timezone; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgency_timezone() {
		return _agency.isAgency_timezone();
	}

	/**
	* Returns <code>true</code> if this agency is agency_url.
	*
	* @return <code>true</code> if this agency is agency_url; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgency_url() {
		return _agency.isAgency_url();
	}

	@Override
	public boolean isCachedModel() {
		return _agency.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _agency.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _agency.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _agency.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.gtfs.model.Agency> toCacheModel() {
		return _agency.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Agency toEscapedModel() {
		return new AgencyWrapper(_agency.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Agency toUnescapedModel() {
		return new AgencyWrapper(_agency.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.gtfs.model.Agency agency) {
		return _agency.compareTo(agency);
	}

	@Override
	public int hashCode() {
		return _agency.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _agency.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AgencyWrapper((Agency)_agency.clone());
	}

	/**
	* Returns the agency_name of this agency.
	*
	* @return the agency_name of this agency
	*/
	@Override
	public java.lang.String getAgency_name() {
		return _agency.getAgency_name();
	}

	/**
	* Returns the uuid of this agency.
	*
	* @return the uuid of this agency
	*/
	@Override
	public java.lang.String getUuid() {
		return _agency.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _agency.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _agency.toXmlString();
	}

	/**
	* Returns the ID of this agency.
	*
	* @return the ID of this agency
	*/
	@Override
	public long getId() {
		return _agency.getId();
	}

	/**
	* Returns the primary key of this agency.
	*
	* @return the primary key of this agency
	*/
	@Override
	public long getPrimaryKey() {
		return _agency.getPrimaryKey();
	}

	@Override
	public void persist() {
		_agency.persist();
	}

	/**
	* Sets whether this agency is agency_fare_url.
	*
	* @param agency_fare_url the agency_fare_url of this agency
	*/
	@Override
	public void setAgency_fare_url(boolean agency_fare_url) {
		_agency.setAgency_fare_url(agency_fare_url);
	}

	/**
	* Sets whether this agency is agency_lang.
	*
	* @param agency_lang the agency_lang of this agency
	*/
	@Override
	public void setAgency_lang(boolean agency_lang) {
		_agency.setAgency_lang(agency_lang);
	}

	/**
	* Sets the agency_name of this agency.
	*
	* @param agency_name the agency_name of this agency
	*/
	@Override
	public void setAgency_name(java.lang.String agency_name) {
		_agency.setAgency_name(agency_name);
	}

	/**
	* Sets whether this agency is agency_phone.
	*
	* @param agency_phone the agency_phone of this agency
	*/
	@Override
	public void setAgency_phone(boolean agency_phone) {
		_agency.setAgency_phone(agency_phone);
	}

	/**
	* Sets whether this agency is agency_timezone.
	*
	* @param agency_timezone the agency_timezone of this agency
	*/
	@Override
	public void setAgency_timezone(boolean agency_timezone) {
		_agency.setAgency_timezone(agency_timezone);
	}

	/**
	* Sets whether this agency is agency_url.
	*
	* @param agency_url the agency_url of this agency
	*/
	@Override
	public void setAgency_url(boolean agency_url) {
		_agency.setAgency_url(agency_url);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_agency.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_agency.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_agency.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_agency.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this agency.
	*
	* @param id the ID of this agency
	*/
	@Override
	public void setId(long id) {
		_agency.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_agency.setNew(n);
	}

	/**
	* Sets the primary key of this agency.
	*
	* @param primaryKey the primary key of this agency
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_agency.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_agency.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this agency.
	*
	* @param uuid the uuid of this agency
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_agency.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgencyWrapper)) {
			return false;
		}

		AgencyWrapper agencyWrapper = (AgencyWrapper)obj;

		if (Objects.equals(_agency, agencyWrapper._agency)) {
			return true;
		}

		return false;
	}

	@Override
	public Agency getWrappedModel() {
		return _agency;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _agency.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _agency.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_agency.resetOriginalValues();
	}

	private final Agency _agency;
}
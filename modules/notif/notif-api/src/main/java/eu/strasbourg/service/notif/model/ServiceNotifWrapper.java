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

package eu.strasbourg.service.notif.model;

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
 * This class is a wrapper for {@link ServiceNotif}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceNotif
 * @generated
 */
@ProviderType
public class ServiceNotifWrapper
	implements ServiceNotif, ModelWrapper<ServiceNotif> {

	public ServiceNotifWrapper(ServiceNotif serviceNotif) {
		_serviceNotif = serviceNotif;
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceNotif.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceNotif.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("serviceId", getServiceId());
		attributes.put("organisationId", getOrganisationId());
		attributes.put("name", getName());
		attributes.put("pictoId", getPictoId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long serviceId = (Long)attributes.get("serviceId");

		if (serviceId != null) {
			setServiceId(serviceId);
		}

		Long organisationId = (Long)attributes.get("organisationId");

		if (organisationId != null) {
			setOrganisationId(organisationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long pictoId = (Long)attributes.get("pictoId");

		if (pictoId != null) {
			setPictoId(pictoId);
		}
	}

	@Override
	public Object clone() {
		return new ServiceNotifWrapper((ServiceNotif)_serviceNotif.clone());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.notif.model.ServiceNotif serviceNotif) {

		return _serviceNotif.compareTo(serviceNotif);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _serviceNotif.getExpandoBridge();
	}

	/**
	 * Retourne les Messages du service
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Message>
		getMessages() {

		return _serviceNotif.getMessages();
	}

	/**
	 * Returns the name of this service notif.
	 *
	 * @return the name of this service notif
	 */
	@Override
	public String getName() {
		return _serviceNotif.getName();
	}

	/**
	 * Retourne les Natures du service
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.NatureNotif>
		getNatures() {

		return _serviceNotif.getNatures();
	}

	/**
	 * Returns the organisation ID of this service notif.
	 *
	 * @return the organisation ID of this service notif
	 */
	@Override
	public long getOrganisationId() {
		return _serviceNotif.getOrganisationId();
	}

	/**
	 * Returns the picto ID of this service notif.
	 *
	 * @return the picto ID of this service notif
	 */
	@Override
	public long getPictoId() {
		return _serviceNotif.getPictoId();
	}

	/**
	 * Returns the primary key of this service notif.
	 *
	 * @return the primary key of this service notif
	 */
	@Override
	public long getPrimaryKey() {
		return _serviceNotif.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceNotif.getPrimaryKeyObj();
	}

	/**
	 * Returns the service ID of this service notif.
	 *
	 * @return the service ID of this service notif
	 */
	@Override
	public long getServiceId() {
		return _serviceNotif.getServiceId();
	}

	@Override
	public int hashCode() {
		return _serviceNotif.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _serviceNotif.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _serviceNotif.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _serviceNotif.isNew();
	}

	@Override
	public void persist() {
		_serviceNotif.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_serviceNotif.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_serviceNotif.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_serviceNotif.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_serviceNotif.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the name of this service notif.
	 *
	 * @param name the name of this service notif
	 */
	@Override
	public void setName(String name) {
		_serviceNotif.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_serviceNotif.setNew(n);
	}

	/**
	 * Sets the organisation ID of this service notif.
	 *
	 * @param organisationId the organisation ID of this service notif
	 */
	@Override
	public void setOrganisationId(long organisationId) {
		_serviceNotif.setOrganisationId(organisationId);
	}

	/**
	 * Sets the picto ID of this service notif.
	 *
	 * @param pictoId the picto ID of this service notif
	 */
	@Override
	public void setPictoId(long pictoId) {
		_serviceNotif.setPictoId(pictoId);
	}

	/**
	 * Sets the primary key of this service notif.
	 *
	 * @param primaryKey the primary key of this service notif
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_serviceNotif.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_serviceNotif.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the service ID of this service notif.
	 *
	 * @param serviceId the service ID of this service notif
	 */
	@Override
	public void setServiceId(long serviceId) {
		_serviceNotif.setServiceId(serviceId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<eu.strasbourg.service.notif.model.ServiceNotif> toCacheModel() {

		return _serviceNotif.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.notif.model.ServiceNotif toEscapedModel() {
		return new ServiceNotifWrapper(_serviceNotif.toEscapedModel());
	}

	@Override
	public String toString() {
		return _serviceNotif.toString();
	}

	@Override
	public eu.strasbourg.service.notif.model.ServiceNotif toUnescapedModel() {
		return new ServiceNotifWrapper(_serviceNotif.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _serviceNotif.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceNotifWrapper)) {
			return false;
		}

		ServiceNotifWrapper serviceNotifWrapper = (ServiceNotifWrapper)obj;

		if (Objects.equals(_serviceNotif, serviceNotifWrapper._serviceNotif)) {
			return true;
		}

		return false;
	}

	@Override
	public ServiceNotif getWrappedModel() {
		return _serviceNotif;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _serviceNotif.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _serviceNotif.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_serviceNotif.resetOriginalValues();
	}

	private final ServiceNotif _serviceNotif;

}
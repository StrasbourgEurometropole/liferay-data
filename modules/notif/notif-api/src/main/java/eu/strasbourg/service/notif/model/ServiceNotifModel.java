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
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ServiceNotif service. Represents a row in the &quot;notif_ServiceNotif&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>eu.strasbourg.service.notif.model.impl.ServiceNotifModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>eu.strasbourg.service.notif.model.impl.ServiceNotifImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceNotif
 * @generated
 */
@ProviderType
public interface ServiceNotifModel extends BaseModel<ServiceNotif> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a service notif model instance should use the {@link ServiceNotif} interface instead.
	 */

	/**
	 * Returns the primary key of this service notif.
	 *
	 * @return the primary key of this service notif
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this service notif.
	 *
	 * @param primaryKey the primary key of this service notif
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the service ID of this service notif.
	 *
	 * @return the service ID of this service notif
	 */
	public long getServiceId();

	/**
	 * Sets the service ID of this service notif.
	 *
	 * @param serviceId the service ID of this service notif
	 */
	public void setServiceId(long serviceId);

	/**
	 * Returns the organisation ID of this service notif.
	 *
	 * @return the organisation ID of this service notif
	 */
	public long getOrganisationId();

	/**
	 * Sets the organisation ID of this service notif.
	 *
	 * @param organisationId the organisation ID of this service notif
	 */
	public void setOrganisationId(long organisationId);

	/**
	 * Returns the name of this service notif.
	 *
	 * @return the name of this service notif
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this service notif.
	 *
	 * @param name the name of this service notif
	 */
	public void setName(String name);

	/**
	 * Returns the picto ID of this service notif.
	 *
	 * @return the picto ID of this service notif
	 */
	public long getPictoId();

	/**
	 * Sets the picto ID of this service notif.
	 *
	 * @param pictoId the picto ID of this service notif
	 */
	public void setPictoId(long pictoId);

	/**
	 * Returns the csmap subscription label of this service notif.
	 *
	 * @return the csmap subscription label of this service notif
	 */
	@AutoEscape
	public String getCsmapSubscriptionLabel();

	/**
	 * Sets the csmap subscription label of this service notif.
	 *
	 * @param csmapSubscriptionLabel the csmap subscription label of this service notif
	 */
	public void setCsmapSubscriptionLabel(String csmapSubscriptionLabel);

	/**
	 * Returns the csmap subscription mandatory of this service notif.
	 *
	 * @return the csmap subscription mandatory of this service notif
	 */
	public boolean getCsmapSubscriptionMandatory();

	/**
	 * Returns <code>true</code> if this service notif is csmap subscription mandatory.
	 *
	 * @return <code>true</code> if this service notif is csmap subscription mandatory; <code>false</code> otherwise
	 */
	public boolean isCsmapSubscriptionMandatory();

	/**
	 * Sets whether this service notif is csmap subscription mandatory.
	 *
	 * @param csmapSubscriptionMandatory the csmap subscription mandatory of this service notif
	 */
	public void setCsmapSubscriptionMandatory(
		boolean csmapSubscriptionMandatory);

	/**
	 * Returns the csmap topic of this service notif.
	 *
	 * @return the csmap topic of this service notif
	 */
	@AutoEscape
	public String getCsmapTopic();

	/**
	 * Sets the csmap topic of this service notif.
	 *
	 * @param csmapTopic the csmap topic of this service notif
	 */
	public void setCsmapTopic(String csmapTopic);

	/**
	 * Returns the create date of this service notif.
	 *
	 * @return the create date of this service notif
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this service notif.
	 *
	 * @param createDate the create date of this service notif
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this service notif.
	 *
	 * @return the modified date of this service notif
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this service notif.
	 *
	 * @param modifiedDate the modified date of this service notif
	 */
	public void setModifiedDate(Date modifiedDate);

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
	public int compareTo(
		eu.strasbourg.service.notif.model.ServiceNotif serviceNotif);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.notif.model.ServiceNotif>
		toCacheModel();

	@Override
	public eu.strasbourg.service.notif.model.ServiceNotif toEscapedModel();

	@Override
	public eu.strasbourg.service.notif.model.ServiceNotif toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}
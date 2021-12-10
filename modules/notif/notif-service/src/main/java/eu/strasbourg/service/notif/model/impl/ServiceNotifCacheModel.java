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

package eu.strasbourg.service.notif.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.notif.model.ServiceNotif;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ServiceNotif in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ServiceNotifCacheModel
	implements CacheModel<ServiceNotif>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceNotifCacheModel)) {
			return false;
		}

		ServiceNotifCacheModel serviceNotifCacheModel =
			(ServiceNotifCacheModel)obj;

		if (serviceId == serviceNotifCacheModel.serviceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, serviceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{serviceId=");
		sb.append(serviceId);
		sb.append(", organisationId=");
		sb.append(organisationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", pictoId=");
		sb.append(pictoId);
		sb.append(", csmapSubscriptionLabel=");
		sb.append(csmapSubscriptionLabel);
		sb.append(", csmapSubscriptionMandatory=");
		sb.append(csmapSubscriptionMandatory);
		sb.append(", csmapTopic=");
		sb.append(csmapTopic);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ServiceNotif toEntityModel() {
		ServiceNotifImpl serviceNotifImpl = new ServiceNotifImpl();

		serviceNotifImpl.setServiceId(serviceId);
		serviceNotifImpl.setOrganisationId(organisationId);

		if (name == null) {
			serviceNotifImpl.setName("");
		}
		else {
			serviceNotifImpl.setName(name);
		}

		serviceNotifImpl.setPictoId(pictoId);

		if (csmapSubscriptionLabel == null) {
			serviceNotifImpl.setCsmapSubscriptionLabel("");
		}
		else {
			serviceNotifImpl.setCsmapSubscriptionLabel(csmapSubscriptionLabel);
		}

		serviceNotifImpl.setCsmapSubscriptionMandatory(
			csmapSubscriptionMandatory);

		if (csmapTopic == null) {
			serviceNotifImpl.setCsmapTopic("");
		}
		else {
			serviceNotifImpl.setCsmapTopic(csmapTopic);
		}

		if (createDate == Long.MIN_VALUE) {
			serviceNotifImpl.setCreateDate(null);
		}
		else {
			serviceNotifImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			serviceNotifImpl.setModifiedDate(null);
		}
		else {
			serviceNotifImpl.setModifiedDate(new Date(modifiedDate));
		}

		serviceNotifImpl.resetOriginalValues();

		return serviceNotifImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		serviceId = objectInput.readLong();

		organisationId = objectInput.readLong();
		name = objectInput.readUTF();

		pictoId = objectInput.readLong();
		csmapSubscriptionLabel = objectInput.readUTF();

		csmapSubscriptionMandatory = objectInput.readBoolean();
		csmapTopic = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(serviceId);

		objectOutput.writeLong(organisationId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(pictoId);

		if (csmapSubscriptionLabel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(csmapSubscriptionLabel);
		}

		objectOutput.writeBoolean(csmapSubscriptionMandatory);

		if (csmapTopic == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(csmapTopic);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public long serviceId;
	public long organisationId;
	public String name;
	public long pictoId;
	public String csmapSubscriptionLabel;
	public boolean csmapSubscriptionMandatory;
	public String csmapTopic;
	public long createDate;
	public long modifiedDate;

}
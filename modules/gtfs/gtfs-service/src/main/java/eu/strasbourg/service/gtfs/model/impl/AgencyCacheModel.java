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

package eu.strasbourg.service.gtfs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.gtfs.model.Agency;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Agency in entity cache.
 *
 * @author Cedric Henry
 * @see Agency
 * @generated
 */
@ProviderType
public class AgencyCacheModel implements CacheModel<Agency>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgencyCacheModel)) {
			return false;
		}

		AgencyCacheModel agencyCacheModel = (AgencyCacheModel)obj;

		if (id == agencyCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", agency_name=");
		sb.append(agency_name);
		sb.append(", agency_url=");
		sb.append(agency_url);
		sb.append(", agency_timezone=");
		sb.append(agency_timezone);
		sb.append(", agency_phone=");
		sb.append(agency_phone);
		sb.append(", agency_fare_url=");
		sb.append(agency_fare_url);
		sb.append(", agency_lang=");
		sb.append(agency_lang);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Agency toEntityModel() {
		AgencyImpl agencyImpl = new AgencyImpl();

		if (uuid == null) {
			agencyImpl.setUuid(StringPool.BLANK);
		}
		else {
			agencyImpl.setUuid(uuid);
		}

		agencyImpl.setId(id);

		if (agency_name == null) {
			agencyImpl.setAgency_name(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_name(agency_name);
		}

		agencyImpl.setAgency_url(agency_url);
		agencyImpl.setAgency_timezone(agency_timezone);
		agencyImpl.setAgency_phone(agency_phone);
		agencyImpl.setAgency_fare_url(agency_fare_url);
		agencyImpl.setAgency_lang(agency_lang);

		agencyImpl.resetOriginalValues();

		return agencyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		agency_name = objectInput.readUTF();

		agency_url = objectInput.readBoolean();

		agency_timezone = objectInput.readBoolean();

		agency_phone = objectInput.readBoolean();

		agency_fare_url = objectInput.readBoolean();

		agency_lang = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(id);

		if (agency_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_name);
		}

		objectOutput.writeBoolean(agency_url);

		objectOutput.writeBoolean(agency_timezone);

		objectOutput.writeBoolean(agency_phone);

		objectOutput.writeBoolean(agency_fare_url);

		objectOutput.writeBoolean(agency_lang);
	}

	public String uuid;
	public long id;
	public String agency_name;
	public boolean agency_url;
	public boolean agency_timezone;
	public boolean agency_phone;
	public boolean agency_fare_url;
	public boolean agency_lang;
}
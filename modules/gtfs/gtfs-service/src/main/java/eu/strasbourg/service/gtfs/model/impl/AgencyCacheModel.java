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

		if (agency_url == null) {
			agencyImpl.setAgency_url(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_url(agency_url);
		}

		if (agency_timezone == null) {
			agencyImpl.setAgency_timezone(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_timezone(agency_timezone);
		}

		if (agency_phone == null) {
			agencyImpl.setAgency_phone(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_phone(agency_phone);
		}

		if (agency_fare_url == null) {
			agencyImpl.setAgency_fare_url(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_fare_url(agency_fare_url);
		}

		if (agency_lang == null) {
			agencyImpl.setAgency_lang(StringPool.BLANK);
		}
		else {
			agencyImpl.setAgency_lang(agency_lang);
		}

		agencyImpl.resetOriginalValues();

		return agencyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		agency_name = objectInput.readUTF();
		agency_url = objectInput.readUTF();
		agency_timezone = objectInput.readUTF();
		agency_phone = objectInput.readUTF();
		agency_fare_url = objectInput.readUTF();
		agency_lang = objectInput.readUTF();
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

		if (agency_url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_url);
		}

		if (agency_timezone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_timezone);
		}

		if (agency_phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_phone);
		}

		if (agency_fare_url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_fare_url);
		}

		if (agency_lang == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(agency_lang);
		}
	}

	public String uuid;
	public long id;
	public String agency_name;
	public String agency_url;
	public String agency_timezone;
	public String agency_phone;
	public String agency_fare_url;
	public String agency_lang;
}
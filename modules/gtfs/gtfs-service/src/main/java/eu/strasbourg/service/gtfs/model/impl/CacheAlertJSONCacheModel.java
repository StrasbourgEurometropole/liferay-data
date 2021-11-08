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

import eu.strasbourg.service.gtfs.model.CacheAlertJSON;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CacheAlertJSON in entity cache.
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class CacheAlertJSONCacheModel
	implements CacheModel<CacheAlertJSON>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheAlertJSONCacheModel)) {
			return false;
		}

		CacheAlertJSONCacheModel cacheAlertJSONCacheModel =
			(CacheAlertJSONCacheModel)obj;

		if (cacheId == cacheAlertJSONCacheModel.cacheId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cacheId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", cacheId=");
		sb.append(cacheId);
		sb.append(", jsonAlert=");
		sb.append(jsonAlert);
		sb.append(", creationDate=");
		sb.append(creationDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CacheAlertJSON toEntityModel() {
		CacheAlertJSONImpl cacheAlertJSONImpl = new CacheAlertJSONImpl();

		if (uuid == null) {
			cacheAlertJSONImpl.setUuid("");
		}
		else {
			cacheAlertJSONImpl.setUuid(uuid);
		}

		cacheAlertJSONImpl.setCacheId(cacheId);

		if (jsonAlert == null) {
			cacheAlertJSONImpl.setJsonAlert("");
		}
		else {
			cacheAlertJSONImpl.setJsonAlert(jsonAlert);
		}

		if (creationDate == Long.MIN_VALUE) {
			cacheAlertJSONImpl.setCreationDate(null);
		}
		else {
			cacheAlertJSONImpl.setCreationDate(new Date(creationDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cacheAlertJSONImpl.setModifiedDate(null);
		}
		else {
			cacheAlertJSONImpl.setModifiedDate(new Date(modifiedDate));
		}

		cacheAlertJSONImpl.resetOriginalValues();

		return cacheAlertJSONImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		cacheId = objectInput.readLong();
		jsonAlert = objectInput.readUTF();
		creationDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(cacheId);

		if (jsonAlert == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jsonAlert);
		}

		objectOutput.writeLong(creationDate);
		objectOutput.writeLong(modifiedDate);
	}

	public String uuid;
	public long cacheId;
	public String jsonAlert;
	public long creationDate;
	public long modifiedDate;

}
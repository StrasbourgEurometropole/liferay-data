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

package eu.strasbourg.service.place.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.place.model.CacheJson;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CacheJson in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
@ProviderType
public class CacheJsonCacheModel
	implements CacheModel<CacheJson>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheJsonCacheModel)) {
			return false;
		}

		CacheJsonCacheModel cacheJsonCacheModel = (CacheJsonCacheModel)obj;

		if (sigId.equals(cacheJsonCacheModel.sigId)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", sigId=");
		sb.append(sigId);
		sb.append(", jsonLieu=");
		sb.append(jsonLieu);
		sb.append(", jsonHoraire=");
		sb.append(jsonHoraire);
		sb.append(", createPlace=");
		sb.append(createPlace);
		sb.append(", modifiedPlace=");
		sb.append(modifiedPlace);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CacheJson toEntityModel() {
		CacheJsonImpl cacheJsonImpl = new CacheJsonImpl();

		if (uuid == null) {
			cacheJsonImpl.setUuid("");
		}
		else {
			cacheJsonImpl.setUuid(uuid);
		}

		if (sigId == null) {
			cacheJsonImpl.setSigId("");
		}
		else {
			cacheJsonImpl.setSigId(sigId);
		}

		if (jsonLieu == null) {
			cacheJsonImpl.setJsonLieu("");
		}
		else {
			cacheJsonImpl.setJsonLieu(jsonLieu);
		}

		if (jsonHoraire == null) {
			cacheJsonImpl.setJsonHoraire("");
		}
		else {
			cacheJsonImpl.setJsonHoraire(jsonHoraire);
		}

		if (createPlace == Long.MIN_VALUE) {
			cacheJsonImpl.setCreatePlace(null);
		}
		else {
			cacheJsonImpl.setCreatePlace(new Date(createPlace));
		}

		if (modifiedPlace == Long.MIN_VALUE) {
			cacheJsonImpl.setModifiedPlace(null);
		}
		else {
			cacheJsonImpl.setModifiedPlace(new Date(modifiedPlace));
		}

		cacheJsonImpl.setIsActive(isActive);

		cacheJsonImpl.resetOriginalValues();

		return cacheJsonImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		sigId = objectInput.readUTF();
		jsonLieu = objectInput.readUTF();
		jsonHoraire = objectInput.readUTF();
		createPlace = objectInput.readLong();
		modifiedPlace = objectInput.readLong();

		isActive = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (sigId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sigId);
		}

		if (jsonLieu == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jsonLieu);
		}

		if (jsonHoraire == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jsonHoraire);
		}

		objectOutput.writeLong(createPlace);
		objectOutput.writeLong(modifiedPlace);

		objectOutput.writeBoolean(isActive);
	}

	public String uuid;
	public String sigId;
	public String jsonLieu;
	public String jsonHoraire;
	public long createPlace;
	public long modifiedPlace;
	public boolean isActive;

}
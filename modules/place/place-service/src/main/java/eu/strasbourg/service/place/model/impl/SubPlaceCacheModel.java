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
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.place.model.SubPlace;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SubPlace in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see SubPlace
 * @generated
 */
@ProviderType
public class SubPlaceCacheModel implements CacheModel<SubPlace>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SubPlaceCacheModel)) {
			return false;
		}

		SubPlaceCacheModel subPlaceCacheModel = (SubPlaceCacheModel)obj;

		if (subPlaceId == subPlaceCacheModel.subPlaceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, subPlaceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", subPlaceId=");
		sb.append(subPlaceId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", placeId=");
		sb.append(placeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SubPlace toEntityModel() {
		SubPlaceImpl subPlaceImpl = new SubPlaceImpl();

		if (uuid == null) {
			subPlaceImpl.setUuid(StringPool.BLANK);
		}
		else {
			subPlaceImpl.setUuid(uuid);
		}

		subPlaceImpl.setSubPlaceId(subPlaceId);

		if (name == null) {
			subPlaceImpl.setName(StringPool.BLANK);
		}
		else {
			subPlaceImpl.setName(name);
		}

		if (description == null) {
			subPlaceImpl.setDescription(StringPool.BLANK);
		}
		else {
			subPlaceImpl.setDescription(description);
		}

		subPlaceImpl.setPlaceId(placeId);

		subPlaceImpl.resetOriginalValues();

		return subPlaceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		subPlaceId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		placeId = objectInput.readLong();
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

		objectOutput.writeLong(subPlaceId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(placeId);
	}

	public String uuid;
	public long subPlaceId;
	public String name;
	public String description;
	public long placeId;
}
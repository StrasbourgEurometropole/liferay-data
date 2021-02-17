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

package eu.strasbourg.service.csmap.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import eu.strasbourg.service.csmap.model.Nonce;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Nonce in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class NonceCacheModel implements CacheModel<Nonce>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NonceCacheModel)) {
			return false;
		}

		NonceCacheModel nonceCacheModel = (NonceCacheModel)obj;

		if (nonceId == nonceCacheModel.nonceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nonceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", nonceId=");
		sb.append(nonceId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Nonce toEntityModel() {
		NonceImpl nonceImpl = new NonceImpl();

		if (uuid == null) {
			nonceImpl.setUuid("");
		}
		else {
			nonceImpl.setUuid(uuid);
		}

		nonceImpl.setNonceId(nonceId);

		if (createDate == Long.MIN_VALUE) {
			nonceImpl.setCreateDate(null);
		}
		else {
			nonceImpl.setCreateDate(new Date(createDate));
		}

		if (value == null) {
			nonceImpl.setValue("");
		}
		else {
			nonceImpl.setValue(value);
		}

		nonceImpl.resetOriginalValues();

		return nonceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		nonceId = objectInput.readLong();
		createDate = objectInput.readLong();
		value = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(nonceId);
		objectOutput.writeLong(createDate);

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}
	}

	public String uuid;
	public long nonceId;
	public long createDate;
	public String value;

}
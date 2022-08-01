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

import eu.strasbourg.service.csmap.model.BaseNonce;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BaseNonce in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BaseNonceCacheModel
	implements CacheModel<BaseNonce>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BaseNonceCacheModel)) {
			return false;
		}

		BaseNonceCacheModel baseNonceCacheModel = (BaseNonceCacheModel)object;

		if (baseNonceId == baseNonceCacheModel.baseNonceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, baseNonceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{baseNonceId=");
		sb.append(baseNonceId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BaseNonce toEntityModel() {
		BaseNonceImpl baseNonceImpl = new BaseNonceImpl();

		baseNonceImpl.setBaseNonceId(baseNonceId);

		if (createDate == Long.MIN_VALUE) {
			baseNonceImpl.setCreateDate(null);
		}
		else {
			baseNonceImpl.setCreateDate(new Date(createDate));
		}

		if (value == null) {
			baseNonceImpl.setValue("");
		}
		else {
			baseNonceImpl.setValue(value);
		}

		baseNonceImpl.resetOriginalValues();

		return baseNonceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		baseNonceId = objectInput.readLong();
		createDate = objectInput.readLong();
		value = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(baseNonceId);
		objectOutput.writeLong(createDate);

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}
	}

	public long baseNonceId;
	public long createDate;
	public String value;

}
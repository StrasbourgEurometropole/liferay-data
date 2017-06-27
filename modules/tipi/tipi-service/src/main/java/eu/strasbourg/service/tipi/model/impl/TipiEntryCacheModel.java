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

package eu.strasbourg.service.tipi.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.tipi.model.TipiEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TipiEntry in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see TipiEntry
 * @generated
 */
@ProviderType
public class TipiEntryCacheModel implements CacheModel<TipiEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TipiEntryCacheModel)) {
			return false;
		}

		TipiEntryCacheModel tipiEntryCacheModel = (TipiEntryCacheModel)obj;

		if (id == tipiEntryCacheModel.id) {
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
		sb.append(", date=");
		sb.append(date);
		sb.append(", total=");
		sb.append(total);
		sb.append(", paidCount=");
		sb.append(paidCount);
		sb.append(", refusedCount=");
		sb.append(refusedCount);
		sb.append(", canceledCount=");
		sb.append(canceledCount);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TipiEntry toEntityModel() {
		TipiEntryImpl tipiEntryImpl = new TipiEntryImpl();

		if (uuid == null) {
			tipiEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			tipiEntryImpl.setUuid(uuid);
		}

		tipiEntryImpl.setId(id);

		if (date == Long.MIN_VALUE) {
			tipiEntryImpl.setDate(null);
		}
		else {
			tipiEntryImpl.setDate(new Date(date));
		}

		tipiEntryImpl.setTotal(total);
		tipiEntryImpl.setPaidCount(paidCount);
		tipiEntryImpl.setRefusedCount(refusedCount);
		tipiEntryImpl.setCanceledCount(canceledCount);

		if (type == null) {
			tipiEntryImpl.setType(StringPool.BLANK);
		}
		else {
			tipiEntryImpl.setType(type);
		}

		tipiEntryImpl.resetOriginalValues();

		return tipiEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		date = objectInput.readLong();

		total = objectInput.readInt();

		paidCount = objectInput.readInt();

		refusedCount = objectInput.readInt();

		canceledCount = objectInput.readInt();
		type = objectInput.readUTF();
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
		objectOutput.writeLong(date);

		objectOutput.writeInt(total);

		objectOutput.writeInt(paidCount);

		objectOutput.writeInt(refusedCount);

		objectOutput.writeInt(canceledCount);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public String uuid;
	public long id;
	public long date;
	public int total;
	public int paidCount;
	public int refusedCount;
	public int canceledCount;
	public String type;
}
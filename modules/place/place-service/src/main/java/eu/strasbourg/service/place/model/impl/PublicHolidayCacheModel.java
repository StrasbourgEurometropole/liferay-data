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

import eu.strasbourg.service.place.model.PublicHoliday;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PublicHoliday in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see PublicHoliday
 * @generated
 */
@ProviderType
public class PublicHolidayCacheModel implements CacheModel<PublicHoliday>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublicHolidayCacheModel)) {
			return false;
		}

		PublicHolidayCacheModel publicHolidayCacheModel = (PublicHolidayCacheModel)obj;

		if (publicHolidayId == publicHolidayCacheModel.publicHolidayId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, publicHolidayId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", publicHolidayId=");
		sb.append(publicHolidayId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", date=");
		sb.append(date);
		sb.append(", recurrent=");
		sb.append(recurrent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PublicHoliday toEntityModel() {
		PublicHolidayImpl publicHolidayImpl = new PublicHolidayImpl();

		if (uuid == null) {
			publicHolidayImpl.setUuid(StringPool.BLANK);
		}
		else {
			publicHolidayImpl.setUuid(uuid);
		}

		publicHolidayImpl.setPublicHolidayId(publicHolidayId);

		if (name == null) {
			publicHolidayImpl.setName(StringPool.BLANK);
		}
		else {
			publicHolidayImpl.setName(name);
		}

		if (date == Long.MIN_VALUE) {
			publicHolidayImpl.setDate(null);
		}
		else {
			publicHolidayImpl.setDate(new Date(date));
		}

		publicHolidayImpl.setRecurrent(recurrent);

		publicHolidayImpl.resetOriginalValues();

		return publicHolidayImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		publicHolidayId = objectInput.readLong();
		name = objectInput.readUTF();
		date = objectInput.readLong();

		recurrent = objectInput.readBoolean();
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

		objectOutput.writeLong(publicHolidayId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(date);

		objectOutput.writeBoolean(recurrent);
	}

	public String uuid;
	public long publicHolidayId;
	public String name;
	public long date;
	public boolean recurrent;
}
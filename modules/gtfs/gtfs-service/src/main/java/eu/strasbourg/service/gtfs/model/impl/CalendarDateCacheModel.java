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

import eu.strasbourg.service.gtfs.model.CalendarDate;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CalendarDate in entity cache.
 *
 * @author Cedric Henry
 * @see CalendarDate
 * @generated
 */
@ProviderType
public class CalendarDateCacheModel implements CacheModel<CalendarDate>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CalendarDateCacheModel)) {
			return false;
		}

		CalendarDateCacheModel calendarDateCacheModel = (CalendarDateCacheModel)obj;

		if (id == calendarDateCacheModel.id) {
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
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", service_id=");
		sb.append(service_id);
		sb.append(", date=");
		sb.append(date);
		sb.append(", exception_type=");
		sb.append(exception_type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CalendarDate toEntityModel() {
		CalendarDateImpl calendarDateImpl = new CalendarDateImpl();

		if (uuid == null) {
			calendarDateImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarDateImpl.setUuid(uuid);
		}

		calendarDateImpl.setId(id);

		if (service_id == null) {
			calendarDateImpl.setService_id(StringPool.BLANK);
		}
		else {
			calendarDateImpl.setService_id(service_id);
		}

		if (date == Long.MIN_VALUE) {
			calendarDateImpl.setDate(null);
		}
		else {
			calendarDateImpl.setDate(new Date(date));
		}

		calendarDateImpl.setException_type(exception_type);

		calendarDateImpl.resetOriginalValues();

		return calendarDateImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		service_id = objectInput.readUTF();
		date = objectInput.readLong();

		exception_type = objectInput.readInt();
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

		if (service_id == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(service_id);
		}

		objectOutput.writeLong(date);

		objectOutput.writeInt(exception_type);
	}

	public String uuid;
	public long id;
	public String service_id;
	public long date;
	public int exception_type;
}
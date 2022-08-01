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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.gtfs.model.StopTime;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StopTime in entity cache.
 *
 * @author Cedric Henry
 * @generated
 */
public class StopTimeCacheModel
	implements CacheModel<StopTime>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof StopTimeCacheModel)) {
			return false;
		}

		StopTimeCacheModel stopTimeCacheModel = (StopTimeCacheModel)object;

		if (id == stopTimeCacheModel.id) {
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
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", trip_id=");
		sb.append(trip_id);
		sb.append(", arrival_time=");
		sb.append(arrival_time);
		sb.append(", departure_time=");
		sb.append(departure_time);
		sb.append(", stop_id=");
		sb.append(stop_id);
		sb.append(", stop_sequence=");
		sb.append(stop_sequence);
		sb.append(", pickup_type=");
		sb.append(pickup_type);
		sb.append(", drop_off_type=");
		sb.append(drop_off_type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StopTime toEntityModel() {
		StopTimeImpl stopTimeImpl = new StopTimeImpl();

		if (uuid == null) {
			stopTimeImpl.setUuid("");
		}
		else {
			stopTimeImpl.setUuid(uuid);
		}

		stopTimeImpl.setId(id);

		if (trip_id == null) {
			stopTimeImpl.setTrip_id("");
		}
		else {
			stopTimeImpl.setTrip_id(trip_id);
		}

		if (arrival_time == Long.MIN_VALUE) {
			stopTimeImpl.setArrival_time(null);
		}
		else {
			stopTimeImpl.setArrival_time(new Date(arrival_time));
		}

		if (departure_time == Long.MIN_VALUE) {
			stopTimeImpl.setDeparture_time(null);
		}
		else {
			stopTimeImpl.setDeparture_time(new Date(departure_time));
		}

		if (stop_id == null) {
			stopTimeImpl.setStop_id("");
		}
		else {
			stopTimeImpl.setStop_id(stop_id);
		}

		stopTimeImpl.setStop_sequence(stop_sequence);

		if (pickup_type == null) {
			stopTimeImpl.setPickup_type("");
		}
		else {
			stopTimeImpl.setPickup_type(pickup_type);
		}

		if (drop_off_type == null) {
			stopTimeImpl.setDrop_off_type("");
		}
		else {
			stopTimeImpl.setDrop_off_type(drop_off_type);
		}

		stopTimeImpl.resetOriginalValues();

		return stopTimeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		trip_id = objectInput.readUTF();
		arrival_time = objectInput.readLong();
		departure_time = objectInput.readLong();
		stop_id = objectInput.readUTF();

		stop_sequence = objectInput.readInt();
		pickup_type = objectInput.readUTF();
		drop_off_type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(id);

		if (trip_id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trip_id);
		}

		objectOutput.writeLong(arrival_time);
		objectOutput.writeLong(departure_time);

		if (stop_id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stop_id);
		}

		objectOutput.writeInt(stop_sequence);

		if (pickup_type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(pickup_type);
		}

		if (drop_off_type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(drop_off_type);
		}
	}

	public String uuid;
	public long id;
	public String trip_id;
	public long arrival_time;
	public long departure_time;
	public String stop_id;
	public int stop_sequence;
	public String pickup_type;
	public String drop_off_type;

}
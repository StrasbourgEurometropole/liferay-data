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

import eu.strasbourg.service.gtfs.model.StopTime;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StopTime in entity cache.
 *
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class StopTimeCacheModel
	implements CacheModel<StopTime>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StopTimeCacheModel)) {
			return false;
		}

		StopTimeCacheModel stopTimeCacheModel = (StopTimeCacheModel)obj;

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
		StringBundler sb = new StringBundler(7);

		sb.append("{id=");
		sb.append(id);
		sb.append(", trip_id=");
		sb.append(trip_id);
		sb.append(", stop_id=");
		sb.append(stop_id);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StopTime toEntityModel() {
		StopTimeImpl stopTimeImpl = new StopTimeImpl();

		stopTimeImpl.setId(id);

		if (trip_id == null) {
			stopTimeImpl.setTrip_id("");
		}
		else {
			stopTimeImpl.setTrip_id(trip_id);
		}

		if (stop_id == null) {
			stopTimeImpl.setStop_id("");
		}
		else {
			stopTimeImpl.setStop_id(stop_id);
		}

		stopTimeImpl.resetOriginalValues();

		return stopTimeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		trip_id = objectInput.readUTF();
		stop_id = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(id);

		if (trip_id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(trip_id);
		}

		if (stop_id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stop_id);
		}
	}

	public long id;
	public String trip_id;
	public String stop_id;

}
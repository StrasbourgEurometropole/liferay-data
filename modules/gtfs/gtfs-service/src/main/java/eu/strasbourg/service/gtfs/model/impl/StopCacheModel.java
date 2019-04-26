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

import eu.strasbourg.service.gtfs.model.Stop;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Stop in entity cache.
 *
 * @author Cedric Henry
 * @see Stop
 * @generated
 */
@ProviderType
public class StopCacheModel implements CacheModel<Stop>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StopCacheModel)) {
			return false;
		}

		StopCacheModel stopCacheModel = (StopCacheModel)obj;

		if (id == stopCacheModel.id) {
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
		sb.append(", stop_id=");
		sb.append(stop_id);
		sb.append(", stop_code=");
		sb.append(stop_code);
		sb.append(", stop_lat=");
		sb.append(stop_lat);
		sb.append(", stop_lon=");
		sb.append(stop_lon);
		sb.append(", stop_name=");
		sb.append(stop_name);
		sb.append(", stop_url=");
		sb.append(stop_url);
		sb.append(", stop_desc=");
		sb.append(stop_desc);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Stop toEntityModel() {
		StopImpl stopImpl = new StopImpl();

		if (uuid == null) {
			stopImpl.setUuid(StringPool.BLANK);
		}
		else {
			stopImpl.setUuid(uuid);
		}

		stopImpl.setId(id);

		if (stop_id == null) {
			stopImpl.setStop_id(StringPool.BLANK);
		}
		else {
			stopImpl.setStop_id(stop_id);
		}

		if (stop_code == null) {
			stopImpl.setStop_code(StringPool.BLANK);
		}
		else {
			stopImpl.setStop_code(stop_code);
		}

		stopImpl.setStop_lat(stop_lat);
		stopImpl.setStop_lon(stop_lon);

		if (stop_name == null) {
			stopImpl.setStop_name(StringPool.BLANK);
		}
		else {
			stopImpl.setStop_name(stop_name);
		}

		if (stop_url == null) {
			stopImpl.setStop_url(StringPool.BLANK);
		}
		else {
			stopImpl.setStop_url(stop_url);
		}

		if (stop_desc == null) {
			stopImpl.setStop_desc(StringPool.BLANK);
		}
		else {
			stopImpl.setStop_desc(stop_desc);
		}

		stopImpl.resetOriginalValues();

		return stopImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		stop_id = objectInput.readUTF();
		stop_code = objectInput.readUTF();

		stop_lat = objectInput.readLong();

		stop_lon = objectInput.readLong();
		stop_name = objectInput.readUTF();
		stop_url = objectInput.readUTF();
		stop_desc = objectInput.readUTF();
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

		if (stop_id == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stop_id);
		}

		if (stop_code == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stop_code);
		}

		objectOutput.writeLong(stop_lat);

		objectOutput.writeLong(stop_lon);

		if (stop_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stop_name);
		}

		if (stop_url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stop_url);
		}

		if (stop_desc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stop_desc);
		}
	}

	public String uuid;
	public long id;
	public String stop_id;
	public String stop_code;
	public long stop_lat;
	public long stop_lon;
	public String stop_name;
	public String stop_url;
	public String stop_desc;
}
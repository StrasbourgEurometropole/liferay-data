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

import eu.strasbourg.service.gtfs.model.Direction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Direction in entity cache.
 *
 * @author Cedric Henry
 * @see Direction
 * @generated
 */
@ProviderType
public class DirectionCacheModel implements CacheModel<Direction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DirectionCacheModel)) {
			return false;
		}

		DirectionCacheModel directionCacheModel = (DirectionCacheModel)obj;

		if (directionId == directionCacheModel.directionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, directionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", directionId=");
		sb.append(directionId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", tripId=");
		sb.append(tripId);
		sb.append(", stopId=");
		sb.append(stopId);
		sb.append(", routeId=");
		sb.append(routeId);
		sb.append(", destinationName=");
		sb.append(destinationName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Direction toEntityModel() {
		DirectionImpl directionImpl = new DirectionImpl();

		if (uuid == null) {
			directionImpl.setUuid(StringPool.BLANK);
		}
		else {
			directionImpl.setUuid(uuid);
		}

		directionImpl.setDirectionId(directionId);
		directionImpl.setGroupId(groupId);
		directionImpl.setCompanyId(companyId);

		if (tripId == null) {
			directionImpl.setTripId(StringPool.BLANK);
		}
		else {
			directionImpl.setTripId(tripId);
		}

		if (stopId == null) {
			directionImpl.setStopId(StringPool.BLANK);
		}
		else {
			directionImpl.setStopId(stopId);
		}

		if (routeId == null) {
			directionImpl.setRouteId(StringPool.BLANK);
		}
		else {
			directionImpl.setRouteId(routeId);
		}

		if (destinationName == null) {
			directionImpl.setDestinationName(StringPool.BLANK);
		}
		else {
			directionImpl.setDestinationName(destinationName);
		}

		directionImpl.resetOriginalValues();

		return directionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		directionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		tripId = objectInput.readUTF();
		stopId = objectInput.readUTF();
		routeId = objectInput.readUTF();
		destinationName = objectInput.readUTF();
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

		objectOutput.writeLong(directionId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (tripId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tripId);
		}

		if (stopId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stopId);
		}

		if (routeId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(routeId);
		}

		if (destinationName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(destinationName);
		}
	}

	public String uuid;
	public long directionId;
	public long groupId;
	public long companyId;
	public String tripId;
	public String stopId;
	public String routeId;
	public String destinationName;
}
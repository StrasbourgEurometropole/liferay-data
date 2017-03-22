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

import eu.strasbourg.service.place.model.Period;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Period in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see Period
 * @generated
 */
@ProviderType
public class PeriodCacheModel implements CacheModel<Period>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PeriodCacheModel)) {
			return false;
		}

		PeriodCacheModel periodCacheModel = (PeriodCacheModel)obj;

		if (periodId == periodCacheModel.periodId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, periodId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", periodId=");
		sb.append(periodId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", defaultPeriod=");
		sb.append(defaultPeriod);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", linkLabel=");
		sb.append(linkLabel);
		sb.append(", linkURL=");
		sb.append(linkURL);
		sb.append(", alwaysOpen=");
		sb.append(alwaysOpen);
		sb.append(", placeId=");
		sb.append(placeId);
		sb.append(", subPlaceId=");
		sb.append(subPlaceId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Period toEntityModel() {
		PeriodImpl periodImpl = new PeriodImpl();

		if (uuid == null) {
			periodImpl.setUuid(StringPool.BLANK);
		}
		else {
			periodImpl.setUuid(uuid);
		}

		periodImpl.setPeriodId(periodId);

		if (name == null) {
			periodImpl.setName(StringPool.BLANK);
		}
		else {
			periodImpl.setName(name);
		}

		periodImpl.setDefaultPeriod(defaultPeriod);

		if (startDate == Long.MIN_VALUE) {
			periodImpl.setStartDate(null);
		}
		else {
			periodImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			periodImpl.setEndDate(null);
		}
		else {
			periodImpl.setEndDate(new Date(endDate));
		}

		if (linkLabel == null) {
			periodImpl.setLinkLabel(StringPool.BLANK);
		}
		else {
			periodImpl.setLinkLabel(linkLabel);
		}

		if (linkURL == null) {
			periodImpl.setLinkURL(StringPool.BLANK);
		}
		else {
			periodImpl.setLinkURL(linkURL);
		}

		periodImpl.setAlwaysOpen(alwaysOpen);
		periodImpl.setPlaceId(placeId);
		periodImpl.setSubPlaceId(subPlaceId);

		periodImpl.resetOriginalValues();

		return periodImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		periodId = objectInput.readLong();
		name = objectInput.readUTF();

		defaultPeriod = objectInput.readBoolean();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		linkLabel = objectInput.readUTF();
		linkURL = objectInput.readUTF();

		alwaysOpen = objectInput.readBoolean();

		placeId = objectInput.readLong();

		subPlaceId = objectInput.readLong();
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

		objectOutput.writeLong(periodId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(defaultPeriod);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (linkLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(linkLabel);
		}

		if (linkURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(linkURL);
		}

		objectOutput.writeBoolean(alwaysOpen);

		objectOutput.writeLong(placeId);

		objectOutput.writeLong(subPlaceId);
	}

	public String uuid;
	public long periodId;
	public String name;
	public boolean defaultPeriod;
	public long startDate;
	public long endDate;
	public String linkLabel;
	public String linkURL;
	public boolean alwaysOpen;
	public long placeId;
	public long subPlaceId;
}
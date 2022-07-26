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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.place.model.Price;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Price in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @generated
 */
public class PriceCacheModel implements CacheModel<Price>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PriceCacheModel)) {
			return false;
		}

		PriceCacheModel priceCacheModel = (PriceCacheModel)object;

		if (priceId == priceCacheModel.priceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, priceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", priceId=");
		sb.append(priceId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", priceDescription=");
		sb.append(priceDescription);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Price toEntityModel() {
		PriceImpl priceImpl = new PriceImpl();

		if (uuid == null) {
			priceImpl.setUuid("");
		}
		else {
			priceImpl.setUuid(uuid);
		}

		priceImpl.setPriceId(priceId);
		priceImpl.setStatus(status);
		priceImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			priceImpl.setStatusByUserName("");
		}
		else {
			priceImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			priceImpl.setStatusDate(null);
		}
		else {
			priceImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			priceImpl.setTitle("");
		}
		else {
			priceImpl.setTitle(title);
		}

		if (priceDescription == null) {
			priceImpl.setPriceDescription("");
		}
		else {
			priceImpl.setPriceDescription(priceDescription);
		}

		priceImpl.resetOriginalValues();

		return priceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		priceId = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		title = objectInput.readUTF();
		priceDescription = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(priceId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (priceDescription == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(priceDescription);
		}
	}

	public String uuid;
	public long priceId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String title;
	public String priceDescription;

}
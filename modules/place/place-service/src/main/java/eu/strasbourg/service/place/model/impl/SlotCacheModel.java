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

import eu.strasbourg.service.place.model.Slot;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Slot in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see Slot
 * @generated
 */
@ProviderType
public class SlotCacheModel implements CacheModel<Slot>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SlotCacheModel)) {
			return false;
		}

		SlotCacheModel slotCacheModel = (SlotCacheModel)obj;

		if (slotId == slotCacheModel.slotId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, slotId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", slotId=");
		sb.append(slotId);
		sb.append(", dayOfWeek=");
		sb.append(dayOfWeek);
		sb.append(", startHour=");
		sb.append(startHour);
		sb.append(", endHour=");
		sb.append(endHour);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", periodId=");
		sb.append(periodId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Slot toEntityModel() {
		SlotImpl slotImpl = new SlotImpl();

		if (uuid == null) {
			slotImpl.setUuid(StringPool.BLANK);
		}
		else {
			slotImpl.setUuid(uuid);
		}

		slotImpl.setSlotId(slotId);
		slotImpl.setDayOfWeek(dayOfWeek);

		if (startHour == null) {
			slotImpl.setStartHour(StringPool.BLANK);
		}
		else {
			slotImpl.setStartHour(startHour);
		}

		if (endHour == null) {
			slotImpl.setEndHour(StringPool.BLANK);
		}
		else {
			slotImpl.setEndHour(endHour);
		}

		if (comment == null) {
			slotImpl.setComment(StringPool.BLANK);
		}
		else {
			slotImpl.setComment(comment);
		}

		slotImpl.setPeriodId(periodId);

		slotImpl.resetOriginalValues();

		return slotImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		slotId = objectInput.readLong();

		dayOfWeek = objectInput.readLong();
		startHour = objectInput.readUTF();
		endHour = objectInput.readUTF();
		comment = objectInput.readUTF();

		periodId = objectInput.readLong();
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

		objectOutput.writeLong(slotId);

		objectOutput.writeLong(dayOfWeek);

		if (startHour == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startHour);
		}

		if (endHour == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endHour);
		}

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeLong(periodId);
	}

	public String uuid;
	public long slotId;
	public long dayOfWeek;
	public String startHour;
	public String endHour;
	public String comment;
	public long periodId;
}
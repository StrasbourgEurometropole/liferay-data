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

package eu.strasbourg.service.agenda.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.agenda.model.EventPeriod;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EventPeriod in entity cache.
 *
 * @author BenjaminBini
 * @see EventPeriod
 * @generated
 */
@ProviderType
public class EventPeriodCacheModel implements CacheModel<EventPeriod>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventPeriodCacheModel)) {
			return false;
		}

		EventPeriodCacheModel eventPeriodCacheModel = (EventPeriodCacheModel)obj;

		if (eventPeriodId == eventPeriodCacheModel.eventPeriodId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventPeriodId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eventPeriodId=");
		sb.append(eventPeriodId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", timeDetail=");
		sb.append(timeDetail);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", campaignEventId=");
		sb.append(campaignEventId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EventPeriod toEntityModel() {
		EventPeriodImpl eventPeriodImpl = new EventPeriodImpl();

		if (uuid == null) {
			eventPeriodImpl.setUuid(StringPool.BLANK);
		}
		else {
			eventPeriodImpl.setUuid(uuid);
		}

		eventPeriodImpl.setEventPeriodId(eventPeriodId);

		if (startDate == Long.MIN_VALUE) {
			eventPeriodImpl.setStartDate(null);
		}
		else {
			eventPeriodImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			eventPeriodImpl.setEndDate(null);
		}
		else {
			eventPeriodImpl.setEndDate(new Date(endDate));
		}

		if (timeDetail == null) {
			eventPeriodImpl.setTimeDetail(StringPool.BLANK);
		}
		else {
			eventPeriodImpl.setTimeDetail(timeDetail);
		}

		eventPeriodImpl.setEventId(eventId);
		eventPeriodImpl.setCampaignEventId(campaignEventId);

		eventPeriodImpl.resetOriginalValues();

		return eventPeriodImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		eventPeriodId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		timeDetail = objectInput.readUTF();

		eventId = objectInput.readLong();

		campaignEventId = objectInput.readLong();
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

		objectOutput.writeLong(eventPeriodId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (timeDetail == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(timeDetail);
		}

		objectOutput.writeLong(eventId);

		objectOutput.writeLong(campaignEventId);
	}

	public String uuid;
	public long eventPeriodId;
	public long startDate;
	public long endDate;
	public String timeDetail;
	public long eventId;
	public long campaignEventId;
}
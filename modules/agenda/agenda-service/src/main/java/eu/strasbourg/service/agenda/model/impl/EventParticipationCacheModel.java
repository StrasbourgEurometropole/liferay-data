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

import eu.strasbourg.service.agenda.model.EventParticipation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EventParticipation in entity cache.
 *
 * @author BenjaminBini
 * @see EventParticipation
 * @generated
 */
@ProviderType
public class EventParticipationCacheModel implements CacheModel<EventParticipation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventParticipationCacheModel)) {
			return false;
		}

		EventParticipationCacheModel eventParticipationCacheModel = (EventParticipationCacheModel)obj;

		if (eventParticipationId == eventParticipationCacheModel.eventParticipationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventParticipationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{eventParticipationId=");
		sb.append(eventParticipationId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EventParticipation toEntityModel() {
		EventParticipationImpl eventParticipationImpl = new EventParticipationImpl();

		eventParticipationImpl.setEventParticipationId(eventParticipationId);

		if (createDate == Long.MIN_VALUE) {
			eventParticipationImpl.setCreateDate(null);
		}
		else {
			eventParticipationImpl.setCreateDate(new Date(createDate));
		}

		if (publikUserId == null) {
			eventParticipationImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			eventParticipationImpl.setPublikUserId(publikUserId);
		}

		eventParticipationImpl.setEventId(eventId);
		eventParticipationImpl.setGroupId(groupId);

		eventParticipationImpl.resetOriginalValues();

		return eventParticipationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventParticipationId = objectInput.readLong();
		createDate = objectInput.readLong();
		publikUserId = objectInput.readUTF();

		eventId = objectInput.readLong();

		groupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(eventParticipationId);
		objectOutput.writeLong(createDate);

		if (publikUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		objectOutput.writeLong(eventId);

		objectOutput.writeLong(groupId);
	}

	public long eventParticipationId;
	public long createDate;
	public String publikUserId;
	public long eventId;
	public long groupId;
}
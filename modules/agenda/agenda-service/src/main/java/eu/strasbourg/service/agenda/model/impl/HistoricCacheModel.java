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

import eu.strasbourg.service.agenda.model.Historic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Historic in entity cache.
 *
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class HistoricCacheModel
	implements CacheModel<Historic>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistoricCacheModel)) {
			return false;
		}

		HistoricCacheModel historicCacheModel = (HistoricCacheModel)obj;

		if (eventId == historicCacheModel.eventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", suppressionDate=");
		sb.append(suppressionDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Historic toEntityModel() {
		HistoricImpl historicImpl = new HistoricImpl();

		if (uuid == null) {
			historicImpl.setUuid("");
		}
		else {
			historicImpl.setUuid(uuid);
		}

		historicImpl.setEventId(eventId);

		if (title == null) {
			historicImpl.setTitle("");
		}
		else {
			historicImpl.setTitle(title);
		}

		if (suppressionDate == Long.MIN_VALUE) {
			historicImpl.setSuppressionDate(null);
		}
		else {
			historicImpl.setSuppressionDate(new Date(suppressionDate));
		}

		historicImpl.resetOriginalValues();

		return historicImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		eventId = objectInput.readLong();
		title = objectInput.readUTF();
		suppressionDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(eventId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(suppressionDate);
	}

	public String uuid;
	public long eventId;
	public String title;
	public long suppressionDate;

}
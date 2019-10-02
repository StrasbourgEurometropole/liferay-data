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

import eu.strasbourg.service.agenda.model.AgendaExportPeriod;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AgendaExportPeriod in entity cache.
 *
 * @author BenjaminBini
 * @see AgendaExportPeriod
 * @generated
 */
@ProviderType
public class AgendaExportPeriodCacheModel implements CacheModel<AgendaExportPeriod>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AgendaExportPeriodCacheModel)) {
			return false;
		}

		AgendaExportPeriodCacheModel agendaExportPeriodCacheModel = (AgendaExportPeriodCacheModel)obj;

		if (agendaExportPeriodId == agendaExportPeriodCacheModel.agendaExportPeriodId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, agendaExportPeriodId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", agendaExportPeriodId=");
		sb.append(agendaExportPeriodId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", agendaExportId=");
		sb.append(agendaExportId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AgendaExportPeriod toEntityModel() {
		AgendaExportPeriodImpl agendaExportPeriodImpl = new AgendaExportPeriodImpl();

		if (uuid == null) {
			agendaExportPeriodImpl.setUuid(StringPool.BLANK);
		}
		else {
			agendaExportPeriodImpl.setUuid(uuid);
		}

		agendaExportPeriodImpl.setAgendaExportPeriodId(agendaExportPeriodId);

		if (startDate == Long.MIN_VALUE) {
			agendaExportPeriodImpl.setStartDate(null);
		}
		else {
			agendaExportPeriodImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			agendaExportPeriodImpl.setEndDate(null);
		}
		else {
			agendaExportPeriodImpl.setEndDate(new Date(endDate));
		}

		agendaExportPeriodImpl.setAgendaExportId(agendaExportId);

		agendaExportPeriodImpl.resetOriginalValues();

		return agendaExportPeriodImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		agendaExportPeriodId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		agendaExportId = objectInput.readLong();
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

		objectOutput.writeLong(agendaExportPeriodId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeLong(agendaExportId);
	}

	public String uuid;
	public long agendaExportPeriodId;
	public long startDate;
	public long endDate;
	public long agendaExportId;
}
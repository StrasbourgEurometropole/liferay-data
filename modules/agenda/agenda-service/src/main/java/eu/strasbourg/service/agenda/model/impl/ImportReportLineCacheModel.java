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

import eu.strasbourg.service.agenda.model.ImportReportLine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImportReportLine in entity cache.
 *
 * @author BenjaminBini
 * @see ImportReportLine
 * @generated
 */
@ProviderType
public class ImportReportLineCacheModel implements CacheModel<ImportReportLine>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportReportLineCacheModel)) {
			return false;
		}

		ImportReportLineCacheModel importReportLineCacheModel = (ImportReportLineCacheModel)obj;

		if (lineId == importReportLineCacheModel.lineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", lineId=");
		sb.append(lineId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append(", log=");
		sb.append(log);
		sb.append(", entityName=");
		sb.append(entityName);
		sb.append(", entityExternalId=");
		sb.append(entityExternalId);
		sb.append(", entityId=");
		sb.append(entityId);
		sb.append(", reportId=");
		sb.append(reportId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImportReportLine toEntityModel() {
		ImportReportLineImpl importReportLineImpl = new ImportReportLineImpl();

		if (uuid == null) {
			importReportLineImpl.setUuid(StringPool.BLANK);
		}
		else {
			importReportLineImpl.setUuid(uuid);
		}

		importReportLineImpl.setLineId(lineId);

		if (type == null) {
			importReportLineImpl.setType(StringPool.BLANK);
		}
		else {
			importReportLineImpl.setType(type);
		}

		importReportLineImpl.setStatus(status);

		if (log == null) {
			importReportLineImpl.setLog(StringPool.BLANK);
		}
		else {
			importReportLineImpl.setLog(log);
		}

		if (entityName == null) {
			importReportLineImpl.setEntityName(StringPool.BLANK);
		}
		else {
			importReportLineImpl.setEntityName(entityName);
		}

		if (entityExternalId == null) {
			importReportLineImpl.setEntityExternalId(StringPool.BLANK);
		}
		else {
			importReportLineImpl.setEntityExternalId(entityExternalId);
		}

		importReportLineImpl.setEntityId(entityId);
		importReportLineImpl.setReportId(reportId);

		importReportLineImpl.resetOriginalValues();

		return importReportLineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		lineId = objectInput.readLong();
		type = objectInput.readUTF();

		status = objectInput.readLong();
		log = objectInput.readUTF();
		entityName = objectInput.readUTF();
		entityExternalId = objectInput.readUTF();

		entityId = objectInput.readLong();

		reportId = objectInput.readLong();
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

		objectOutput.writeLong(lineId);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(status);

		if (log == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(log);
		}

		if (entityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityName);
		}

		if (entityExternalId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityExternalId);
		}

		objectOutput.writeLong(entityId);

		objectOutput.writeLong(reportId);
	}

	public String uuid;
	public long lineId;
	public String type;
	public long status;
	public String log;
	public String entityName;
	public String entityExternalId;
	public long entityId;
	public long reportId;
}
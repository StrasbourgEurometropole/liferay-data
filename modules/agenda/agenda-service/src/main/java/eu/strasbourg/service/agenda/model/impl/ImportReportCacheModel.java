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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.agenda.model.ImportReport;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImportReport in entity cache.
 *
 * @author BenjaminBini
 * @generated
 */
public class ImportReportCacheModel
	implements CacheModel<ImportReport>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ImportReportCacheModel)) {
			return false;
		}

		ImportReportCacheModel importReportCacheModel =
			(ImportReportCacheModel)object;

		if (reportId == importReportCacheModel.reportId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, reportId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", reportId=");
		sb.append(reportId);
		sb.append(", provider=");
		sb.append(provider);
		sb.append(", filename=");
		sb.append(filename);
		sb.append(", status=");
		sb.append(status);
		sb.append(", globalErrorCause=");
		sb.append(globalErrorCause);
		sb.append(", newEventsCount=");
		sb.append(newEventsCount);
		sb.append(", modifiedEventsCount=");
		sb.append(modifiedEventsCount);
		sb.append(", errorEventsCount=");
		sb.append(errorEventsCount);
		sb.append(", unmodifiedEventsCount=");
		sb.append(unmodifiedEventsCount);
		sb.append(", deletedEventsCount=");
		sb.append(deletedEventsCount);
		sb.append(", newManifestationsCount=");
		sb.append(newManifestationsCount);
		sb.append(", modifiedManifestationsCount=");
		sb.append(modifiedManifestationsCount);
		sb.append(", errorManifestationsCount=");
		sb.append(errorManifestationsCount);
		sb.append(", unmodifiedManifestationsCount=");
		sb.append(unmodifiedManifestationsCount);
		sb.append(", deletedManifestationsCount=");
		sb.append(deletedManifestationsCount);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImportReport toEntityModel() {
		ImportReportImpl importReportImpl = new ImportReportImpl();

		if (uuid == null) {
			importReportImpl.setUuid("");
		}
		else {
			importReportImpl.setUuid(uuid);
		}

		importReportImpl.setReportId(reportId);

		if (provider == null) {
			importReportImpl.setProvider("");
		}
		else {
			importReportImpl.setProvider(provider);
		}

		if (filename == null) {
			importReportImpl.setFilename("");
		}
		else {
			importReportImpl.setFilename(filename);
		}

		importReportImpl.setStatus(status);

		if (globalErrorCause == null) {
			importReportImpl.setGlobalErrorCause("");
		}
		else {
			importReportImpl.setGlobalErrorCause(globalErrorCause);
		}

		importReportImpl.setNewEventsCount(newEventsCount);
		importReportImpl.setModifiedEventsCount(modifiedEventsCount);
		importReportImpl.setErrorEventsCount(errorEventsCount);
		importReportImpl.setUnmodifiedEventsCount(unmodifiedEventsCount);
		importReportImpl.setDeletedEventsCount(deletedEventsCount);
		importReportImpl.setNewManifestationsCount(newManifestationsCount);
		importReportImpl.setModifiedManifestationsCount(
			modifiedManifestationsCount);
		importReportImpl.setErrorManifestationsCount(errorManifestationsCount);
		importReportImpl.setUnmodifiedManifestationsCount(
			unmodifiedManifestationsCount);
		importReportImpl.setDeletedManifestationsCount(
			deletedManifestationsCount);

		if (startDate == Long.MIN_VALUE) {
			importReportImpl.setStartDate(null);
		}
		else {
			importReportImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			importReportImpl.setEndDate(null);
		}
		else {
			importReportImpl.setEndDate(new Date(endDate));
		}

		importReportImpl.resetOriginalValues();

		return importReportImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		reportId = objectInput.readLong();
		provider = objectInput.readUTF();
		filename = objectInput.readUTF();

		status = objectInput.readLong();
		globalErrorCause = objectInput.readUTF();

		newEventsCount = objectInput.readLong();

		modifiedEventsCount = objectInput.readLong();

		errorEventsCount = objectInput.readLong();

		unmodifiedEventsCount = objectInput.readLong();

		deletedEventsCount = objectInput.readLong();

		newManifestationsCount = objectInput.readLong();

		modifiedManifestationsCount = objectInput.readLong();

		errorManifestationsCount = objectInput.readLong();

		unmodifiedManifestationsCount = objectInput.readLong();

		deletedManifestationsCount = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(reportId);

		if (provider == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(provider);
		}

		if (filename == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(filename);
		}

		objectOutput.writeLong(status);

		if (globalErrorCause == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(globalErrorCause);
		}

		objectOutput.writeLong(newEventsCount);

		objectOutput.writeLong(modifiedEventsCount);

		objectOutput.writeLong(errorEventsCount);

		objectOutput.writeLong(unmodifiedEventsCount);

		objectOutput.writeLong(deletedEventsCount);

		objectOutput.writeLong(newManifestationsCount);

		objectOutput.writeLong(modifiedManifestationsCount);

		objectOutput.writeLong(errorManifestationsCount);

		objectOutput.writeLong(unmodifiedManifestationsCount);

		objectOutput.writeLong(deletedManifestationsCount);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
	}

	public String uuid;
	public long reportId;
	public String provider;
	public String filename;
	public long status;
	public String globalErrorCause;
	public long newEventsCount;
	public long modifiedEventsCount;
	public long errorEventsCount;
	public long unmodifiedEventsCount;
	public long deletedEventsCount;
	public long newManifestationsCount;
	public long modifiedManifestationsCount;
	public long errorManifestationsCount;
	public long unmodifiedManifestationsCount;
	public long deletedManifestationsCount;
	public long startDate;
	public long endDate;

}
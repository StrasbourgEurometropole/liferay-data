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

import eu.strasbourg.service.gtfs.model.ImportHistory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImportHistory in entity cache.
 *
 * @author Cedric Henry
 * @see ImportHistory
 * @generated
 */
@ProviderType
public class ImportHistoryCacheModel implements CacheModel<ImportHistory>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImportHistoryCacheModel)) {
			return false;
		}

		ImportHistoryCacheModel importHistoryCacheModel = (ImportHistoryCacheModel)obj;

		if (importHistoryId == importHistoryCacheModel.importHistoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, importHistoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", importHistoryId=");
		sb.append(importHistoryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", importResult=");
		sb.append(importResult);
		sb.append(", importOpertations=");
		sb.append(importOpertations);
		sb.append(", errorDescription=");
		sb.append(errorDescription);
		sb.append(", errorStackTrace=");
		sb.append(errorStackTrace);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImportHistory toEntityModel() {
		ImportHistoryImpl importHistoryImpl = new ImportHistoryImpl();

		if (uuid == null) {
			importHistoryImpl.setUuid(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setUuid(uuid);
		}

		importHistoryImpl.setImportHistoryId(importHistoryId);
		importHistoryImpl.setGroupId(groupId);
		importHistoryImpl.setCompanyId(companyId);
		importHistoryImpl.setUserId(userId);

		if (userName == null) {
			importHistoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			importHistoryImpl.setCreateDate(null);
		}
		else {
			importHistoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			importHistoryImpl.setModifiedDate(null);
		}
		else {
			importHistoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		importHistoryImpl.setStatus(status);
		importHistoryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			importHistoryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			importHistoryImpl.setStatusDate(null);
		}
		else {
			importHistoryImpl.setStatusDate(new Date(statusDate));
		}

		importHistoryImpl.setImportResult(importResult);

		if (importOpertations == null) {
			importHistoryImpl.setImportOpertations(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setImportOpertations(importOpertations);
		}

		if (errorDescription == null) {
			importHistoryImpl.setErrorDescription(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setErrorDescription(errorDescription);
		}

		if (errorStackTrace == null) {
			importHistoryImpl.setErrorStackTrace(StringPool.BLANK);
		}
		else {
			importHistoryImpl.setErrorStackTrace(errorStackTrace);
		}

		importHistoryImpl.resetOriginalValues();

		return importHistoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		importHistoryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		importResult = objectInput.readInt();
		importOpertations = objectInput.readUTF();
		errorDescription = objectInput.readUTF();
		errorStackTrace = objectInput.readUTF();
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

		objectOutput.writeLong(importHistoryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeInt(importResult);

		if (importOpertations == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(importOpertations);
		}

		if (errorDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorDescription);
		}

		if (errorStackTrace == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorStackTrace);
		}
	}

	public String uuid;
	public long importHistoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public int importResult;
	public String importOpertations;
	public String errorDescription;
	public String errorStackTrace;
}
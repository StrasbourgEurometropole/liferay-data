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

import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GoogleMyBusinessHistoric in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoric
 * @generated
 */
@ProviderType
public class GoogleMyBusinessHistoricCacheModel implements CacheModel<GoogleMyBusinessHistoric>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GoogleMyBusinessHistoricCacheModel)) {
			return false;
		}

		GoogleMyBusinessHistoricCacheModel googleMyBusinessHistoricCacheModel = (GoogleMyBusinessHistoricCacheModel)obj;

		if (googleMyBusinessHistoricId == googleMyBusinessHistoricCacheModel.googleMyBusinessHistoricId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, googleMyBusinessHistoricId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", googleMyBusinessHistoricId=");
		sb.append(googleMyBusinessHistoricId);
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
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", result=");
		sb.append(result);
		sb.append(", operations=");
		sb.append(operations);
		sb.append(", errorDescription=");
		sb.append(errorDescription);
		sb.append(", errorStackTrace=");
		sb.append(errorStackTrace);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", finishDate=");
		sb.append(finishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GoogleMyBusinessHistoric toEntityModel() {
		GoogleMyBusinessHistoricImpl googleMyBusinessHistoricImpl = new GoogleMyBusinessHistoricImpl();

		if (uuid == null) {
			googleMyBusinessHistoricImpl.setUuid(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setUuid(uuid);
		}

		googleMyBusinessHistoricImpl.setGoogleMyBusinessHistoricId(googleMyBusinessHistoricId);
		googleMyBusinessHistoricImpl.setGroupId(groupId);
		googleMyBusinessHistoricImpl.setCompanyId(companyId);
		googleMyBusinessHistoricImpl.setUserId(userId);

		if (userName == null) {
			googleMyBusinessHistoricImpl.setUserName(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setCreateDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setModifiedDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setLastPublishDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setLastPublishDate(new Date(
					lastPublishDate));
		}

		googleMyBusinessHistoricImpl.setStatus(status);
		googleMyBusinessHistoricImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			googleMyBusinessHistoricImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setStatusDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setStatusDate(new Date(statusDate));
		}

		googleMyBusinessHistoricImpl.setResult(result);

		if (operations == null) {
			googleMyBusinessHistoricImpl.setOperations(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setOperations(operations);
		}

		if (errorDescription == null) {
			googleMyBusinessHistoricImpl.setErrorDescription(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setErrorDescription(errorDescription);
		}

		if (errorStackTrace == null) {
			googleMyBusinessHistoricImpl.setErrorStackTrace(StringPool.BLANK);
		}
		else {
			googleMyBusinessHistoricImpl.setErrorStackTrace(errorStackTrace);
		}

		if (startDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setStartDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setStartDate(new Date(startDate));
		}

		if (finishDate == Long.MIN_VALUE) {
			googleMyBusinessHistoricImpl.setFinishDate(null);
		}
		else {
			googleMyBusinessHistoricImpl.setFinishDate(new Date(finishDate));
		}

		googleMyBusinessHistoricImpl.resetOriginalValues();

		return googleMyBusinessHistoricImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		googleMyBusinessHistoricId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		result = objectInput.readInt();
		operations = objectInput.readUTF();
		errorDescription = objectInput.readUTF();
		errorStackTrace = objectInput.readUTF();
		startDate = objectInput.readLong();
		finishDate = objectInput.readLong();
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

		objectOutput.writeLong(googleMyBusinessHistoricId);

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
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeInt(result);

		if (operations == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operations);
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

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(finishDate);
	}

	public String uuid;
	public long googleMyBusinessHistoricId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public int result;
	public String operations;
	public String errorDescription;
	public String errorStackTrace;
	public long startDate;
	public long finishDate;
}
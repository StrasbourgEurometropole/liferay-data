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

package eu.strasbourg.service.council.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.council.model.Session;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Session in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Session
 * @generated
 */
@ProviderType
public class SessionCacheModel implements CacheModel<Session>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SessionCacheModel)) {
			return false;
		}

		SessionCacheModel sessionCacheModel = (SessionCacheModel)obj;

		if (sessionId == sessionCacheModel.sessionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sessionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", sessionId=");
		sb.append(sessionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", date=");
		sb.append(date);
		sb.append(", type=");
		sb.append(type);
		sb.append(", docId=");
		sb.append(docId);
		sb.append(", docReportId=");
		sb.append(docReportId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Session toEntityModel() {
		SessionImpl sessionImpl = new SessionImpl();

		if (uuid == null) {
			sessionImpl.setUuid(StringPool.BLANK);
		}
		else {
			sessionImpl.setUuid(uuid);
		}

		sessionImpl.setSessionId(sessionId);
		sessionImpl.setGroupId(groupId);
		sessionImpl.setCompanyId(companyId);
		sessionImpl.setUserId(userId);

		if (userName == null) {
			sessionImpl.setUserName(StringPool.BLANK);
		}
		else {
			sessionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			sessionImpl.setCreateDate(null);
		}
		else {
			sessionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			sessionImpl.setModifiedDate(null);
		}
		else {
			sessionImpl.setModifiedDate(new Date(modifiedDate));
		}

		sessionImpl.setStatus(status);
		sessionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			sessionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			sessionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			sessionImpl.setStatusDate(null);
		}
		else {
			sessionImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			sessionImpl.setTitle(StringPool.BLANK);
		}
		else {
			sessionImpl.setTitle(title);
		}

		if (date == Long.MIN_VALUE) {
			sessionImpl.setDate(null);
		}
		else {
			sessionImpl.setDate(new Date(date));
		}

		if (type == null) {
			sessionImpl.setType(StringPool.BLANK);
		}
		else {
			sessionImpl.setType(type);
		}

		if (docId == null) {
			sessionImpl.setDocId(StringPool.BLANK);
		}
		else {
			sessionImpl.setDocId(docId);
		}

		if (docReportId == null) {
			sessionImpl.setDocReportId(StringPool.BLANK);
		}
		else {
			sessionImpl.setDocReportId(docReportId);
		}

		sessionImpl.resetOriginalValues();

		return sessionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		sessionId = objectInput.readLong();

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
		title = objectInput.readUTF();
		date = objectInput.readLong();
		type = objectInput.readUTF();
		docId = objectInput.readUTF();
		docReportId = objectInput.readUTF();
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

		objectOutput.writeLong(sessionId);

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

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(date);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (docId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(docId);
		}

		if (docReportId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(docReportId);
		}
	}

	public String uuid;
	public long sessionId;
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
	public String title;
	public long date;
	public String type;
	public String docId;
	public String docReportId;
}
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

import eu.strasbourg.service.council.model.Deliberation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Deliberation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Deliberation
 * @generated
 */
@ProviderType
public class DeliberationCacheModel implements CacheModel<Deliberation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeliberationCacheModel)) {
			return false;
		}

		DeliberationCacheModel deliberationCacheModel = (DeliberationCacheModel)obj;

		if (deliberationId == deliberationCacheModel.deliberationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliberationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", deliberationId=");
		sb.append(deliberationId);
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
		sb.append(", order=");
		sb.append(order);
		sb.append(", text=");
		sb.append(text);
		sb.append(", stage=");
		sb.append(stage);
		sb.append(", councilSessionId=");
		sb.append(councilSessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Deliberation toEntityModel() {
		DeliberationImpl deliberationImpl = new DeliberationImpl();

		if (uuid == null) {
			deliberationImpl.setUuid(StringPool.BLANK);
		}
		else {
			deliberationImpl.setUuid(uuid);
		}

		deliberationImpl.setDeliberationId(deliberationId);
		deliberationImpl.setGroupId(groupId);
		deliberationImpl.setCompanyId(companyId);
		deliberationImpl.setUserId(userId);

		if (userName == null) {
			deliberationImpl.setUserName(StringPool.BLANK);
		}
		else {
			deliberationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			deliberationImpl.setCreateDate(null);
		}
		else {
			deliberationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deliberationImpl.setModifiedDate(null);
		}
		else {
			deliberationImpl.setModifiedDate(new Date(modifiedDate));
		}

		deliberationImpl.setStatus(status);
		deliberationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			deliberationImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			deliberationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			deliberationImpl.setStatusDate(null);
		}
		else {
			deliberationImpl.setStatusDate(new Date(statusDate));
		}

		if (title == null) {
			deliberationImpl.setTitle(StringPool.BLANK);
		}
		else {
			deliberationImpl.setTitle(title);
		}

		deliberationImpl.setOrder(order);

		if (text == null) {
			deliberationImpl.setText(StringPool.BLANK);
		}
		else {
			deliberationImpl.setText(text);
		}

		if (stage == null) {
			deliberationImpl.setStage(StringPool.BLANK);
		}
		else {
			deliberationImpl.setStage(stage);
		}

		deliberationImpl.setCouncilSessionId(councilSessionId);

		deliberationImpl.resetOriginalValues();

		return deliberationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		deliberationId = objectInput.readLong();

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

		order = objectInput.readInt();
		text = objectInput.readUTF();
		stage = objectInput.readUTF();

		councilSessionId = objectInput.readLong();
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

		objectOutput.writeLong(deliberationId);

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

		objectOutput.writeInt(order);

		if (text == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(text);
		}

		if (stage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stage);
		}

		objectOutput.writeLong(councilSessionId);
	}

	public String uuid;
	public long deliberationId;
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
	public int order;
	public String text;
	public String stage;
	public long councilSessionId;
}
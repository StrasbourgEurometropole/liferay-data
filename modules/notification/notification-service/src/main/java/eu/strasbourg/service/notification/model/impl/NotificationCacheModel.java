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

package eu.strasbourg.service.notification.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.notification.model.Notification;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Notification in entity cache.
 *
 * @author BenjaminBini
 * @see Notification
 * @generated
 */
@ProviderType
public class NotificationCacheModel implements CacheModel<Notification>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationCacheModel)) {
			return false;
		}

		NotificationCacheModel notificationCacheModel = (NotificationCacheModel)obj;

		if (notificationId == notificationCacheModel.notificationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{notificationId=");
		sb.append(notificationId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", url=");
		sb.append(url);
		sb.append(", automatic=");
		sb.append(automatic);
		sb.append(", singleUser=");
		sb.append(singleUser);
		sb.append(", singleUserId=");
		sb.append(singleUserId);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Notification toEntityModel() {
		NotificationImpl notificationImpl = new NotificationImpl();

		notificationImpl.setNotificationId(notificationId);

		if (title == null) {
			notificationImpl.setTitle(StringPool.BLANK);
		}
		else {
			notificationImpl.setTitle(title);
		}

		if (description == null) {
			notificationImpl.setDescription(StringPool.BLANK);
		}
		else {
			notificationImpl.setDescription(description);
		}

		if (url == null) {
			notificationImpl.setUrl(StringPool.BLANK);
		}
		else {
			notificationImpl.setUrl(url);
		}

		notificationImpl.setAutomatic(automatic);
		notificationImpl.setSingleUser(singleUser);
		notificationImpl.setSingleUserId(singleUserId);

		if (publicationDate == Long.MIN_VALUE) {
			notificationImpl.setPublicationDate(null);
		}
		else {
			notificationImpl.setPublicationDate(new Date(publicationDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			notificationImpl.setExpirationDate(null);
		}
		else {
			notificationImpl.setExpirationDate(new Date(expirationDate));
		}

		notificationImpl.setStatus(status);
		notificationImpl.setTypeId(typeId);

		notificationImpl.resetOriginalValues();

		return notificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notificationId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		url = objectInput.readUTF();

		automatic = objectInput.readBoolean();

		singleUser = objectInput.readBoolean();

		singleUserId = objectInput.readLong();
		publicationDate = objectInput.readLong();
		expirationDate = objectInput.readLong();

		status = objectInput.readInt();

		typeId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notificationId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeBoolean(automatic);

		objectOutput.writeBoolean(singleUser);

		objectOutput.writeLong(singleUserId);
		objectOutput.writeLong(publicationDate);
		objectOutput.writeLong(expirationDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(typeId);
	}

	public long notificationId;
	public String title;
	public String description;
	public String url;
	public boolean automatic;
	public boolean singleUser;
	public long singleUserId;
	public long publicationDate;
	public long expirationDate;
	public int status;
	public long typeId;
}
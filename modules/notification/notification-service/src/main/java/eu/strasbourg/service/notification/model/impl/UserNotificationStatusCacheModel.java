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

import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserNotificationStatus in entity cache.
 *
 * @author BenjaminBini
 * @see UserNotificationStatus
 * @generated
 */
@ProviderType
public class UserNotificationStatusCacheModel implements CacheModel<UserNotificationStatus>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationStatusCacheModel)) {
			return false;
		}

		UserNotificationStatusCacheModel userNotificationStatusCacheModel = (UserNotificationStatusCacheModel)obj;

		if (userNotificationStatusPK.equals(
					userNotificationStatusCacheModel.userNotificationStatusPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userNotificationStatusPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{notificationId=");
		sb.append(notificationId);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", read=");
		sb.append(read);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserNotificationStatus toEntityModel() {
		UserNotificationStatusImpl userNotificationStatusImpl = new UserNotificationStatusImpl();

		userNotificationStatusImpl.setNotificationId(notificationId);
		userNotificationStatusImpl.setPublikUserId(publikUserId);
		userNotificationStatusImpl.setRead(read);

		userNotificationStatusImpl.resetOriginalValues();

		return userNotificationStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		notificationId = objectInput.readLong();

		publikUserId = objectInput.readLong();

		read = objectInput.readBoolean();

		userNotificationStatusPK = new UserNotificationStatusPK(notificationId,
				publikUserId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(notificationId);

		objectOutput.writeLong(publikUserId);

		objectOutput.writeBoolean(read);
	}

	public long notificationId;
	public long publikUserId;
	public boolean read;
	public transient UserNotificationStatusPK userNotificationStatusPK;
}
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

import eu.strasbourg.service.notification.model.UserNotificationChannel;
import eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserNotificationChannel in entity cache.
 *
 * @author BenjaminBini
 * @see UserNotificationChannel
 * @generated
 */
@ProviderType
public class UserNotificationChannelCacheModel implements CacheModel<UserNotificationChannel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationChannelCacheModel)) {
			return false;
		}

		UserNotificationChannelCacheModel userNotificationChannelCacheModel = (UserNotificationChannelCacheModel)obj;

		if (userNotificationChannelPK.equals(
					userNotificationChannelCacheModel.userNotificationChannelPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userNotificationChannelPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{publikUserId=");
		sb.append(publikUserId);
		sb.append(", channelId=");
		sb.append(channelId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserNotificationChannel toEntityModel() {
		UserNotificationChannelImpl userNotificationChannelImpl = new UserNotificationChannelImpl();

		if (publikUserId == null) {
			userNotificationChannelImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			userNotificationChannelImpl.setPublikUserId(publikUserId);
		}

		userNotificationChannelImpl.setChannelId(channelId);

		userNotificationChannelImpl.resetOriginalValues();

		return userNotificationChannelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		publikUserId = objectInput.readUTF();

		channelId = objectInput.readLong();

		userNotificationChannelPK = new UserNotificationChannelPK(publikUserId,
				channelId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (publikUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		objectOutput.writeLong(channelId);
	}

	public String publikUserId;
	public long channelId;
	public transient UserNotificationChannelPK userNotificationChannelPK;
}
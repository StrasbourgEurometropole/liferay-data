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

import eu.strasbourg.service.notification.model.UserNotificationType;
import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserNotificationType in entity cache.
 *
 * @author BenjaminBini
 * @see UserNotificationType
 * @generated
 */
@ProviderType
public class UserNotificationTypeCacheModel implements CacheModel<UserNotificationType>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationTypeCacheModel)) {
			return false;
		}

		UserNotificationTypeCacheModel userNotificationTypeCacheModel = (UserNotificationTypeCacheModel)obj;

		if (userNotificationTypePK.equals(
					userNotificationTypeCacheModel.userNotificationTypePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userNotificationTypePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{publikUserId=");
		sb.append(publikUserId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserNotificationType toEntityModel() {
		UserNotificationTypeImpl userNotificationTypeImpl = new UserNotificationTypeImpl();

		if (publikUserId == null) {
			userNotificationTypeImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			userNotificationTypeImpl.setPublikUserId(publikUserId);
		}

		userNotificationTypeImpl.setTypeId(typeId);

		userNotificationTypeImpl.resetOriginalValues();

		return userNotificationTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		publikUserId = objectInput.readUTF();

		typeId = objectInput.readLong();

		userNotificationTypePK = new UserNotificationTypePK(publikUserId, typeId);
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

		objectOutput.writeLong(typeId);
	}

	public String publikUserId;
	public long typeId;
	public transient UserNotificationTypePK userNotificationTypePK;
}
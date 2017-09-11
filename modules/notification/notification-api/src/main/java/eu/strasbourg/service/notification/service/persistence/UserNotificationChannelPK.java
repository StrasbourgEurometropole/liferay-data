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

package eu.strasbourg.service.notification.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author BenjaminBini
 * @generated
 */
@ProviderType
public class UserNotificationChannelPK implements Comparable<UserNotificationChannelPK>,
	Serializable {
	public long publikUserId;
	public long channelId;

	public UserNotificationChannelPK() {
	}

	public UserNotificationChannelPK(long publikUserId, long channelId) {
		this.publikUserId = publikUserId;
		this.channelId = channelId;
	}

	public long getPublikUserId() {
		return publikUserId;
	}

	public void setPublikUserId(long publikUserId) {
		this.publikUserId = publikUserId;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	@Override
	public int compareTo(UserNotificationChannelPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (publikUserId < pk.publikUserId) {
			value = -1;
		}
		else if (publikUserId > pk.publikUserId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (channelId < pk.channelId) {
			value = -1;
		}
		else if (channelId > pk.channelId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserNotificationChannelPK)) {
			return false;
		}

		UserNotificationChannelPK pk = (UserNotificationChannelPK)obj;

		if ((publikUserId == pk.publikUserId) && (channelId == pk.channelId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, publikUserId);
		hashCode = HashUtil.hash(hashCode, channelId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("publikUserId");
		sb.append(StringPool.EQUAL);
		sb.append(publikUserId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("channelId");
		sb.append(StringPool.EQUAL);
		sb.append(channelId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}
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
public class UserNotificationTypePK implements Comparable<UserNotificationTypePK>,
	Serializable {
	public long publikUserId;
	public long typeId;

	public UserNotificationTypePK() {
	}

	public UserNotificationTypePK(long publikUserId, long typeId) {
		this.publikUserId = publikUserId;
		this.typeId = typeId;
	}

	public long getPublikUserId() {
		return publikUserId;
	}

	public void setPublikUserId(long publikUserId) {
		this.publikUserId = publikUserId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	@Override
	public int compareTo(UserNotificationTypePK pk) {
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

		if (typeId < pk.typeId) {
			value = -1;
		}
		else if (typeId > pk.typeId) {
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

		if (!(obj instanceof UserNotificationTypePK)) {
			return false;
		}

		UserNotificationTypePK pk = (UserNotificationTypePK)obj;

		if ((publikUserId == pk.publikUserId) && (typeId == pk.typeId)) {
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
		hashCode = HashUtil.hash(hashCode, typeId);

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
		sb.append("typeId");
		sb.append(StringPool.EQUAL);
		sb.append(typeId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}
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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class StopPK implements Comparable<StopPK>, Serializable {
	public long id;
	public String stop_id;

	public StopPK() {
	}

	public StopPK(long id, String stop_id) {
		this.id = id;
		this.stop_id = stop_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	@Override
	public int compareTo(StopPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (id < pk.id) {
			value = -1;
		}
		else if (id > pk.id) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = stop_id.compareTo(pk.stop_id);

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

		if (!(obj instanceof StopPK)) {
			return false;
		}

		StopPK pk = (StopPK)obj;

		if ((id == pk.id) && (stop_id.equals(pk.stop_id))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, id);
		hashCode = HashUtil.hash(hashCode, stop_id);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("id");
		sb.append(StringPool.EQUAL);
		sb.append(id);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("stop_id");
		sb.append(StringPool.EQUAL);
		sb.append(stop_id);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}
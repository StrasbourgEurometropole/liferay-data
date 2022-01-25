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

import java.io.Serializable;

/**
 * @author Cedric Henry
 * @generated
 */
@ProviderType
public class CacheHoursJSONPK
	implements Comparable<CacheHoursJSONPK>, Serializable {

	public String stopCode;
	public int type;

	public CacheHoursJSONPK() {
	}

	public CacheHoursJSONPK(String stopCode, int type) {
		this.stopCode = stopCode;
		this.type = type;
	}

	public String getStopCode() {
		return stopCode;
	}

	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int compareTo(CacheHoursJSONPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = stopCode.compareTo(pk.stopCode);

		if (value != 0) {
			return value;
		}

		if (type < pk.type) {
			value = -1;
		}
		else if (type > pk.type) {
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

		if (!(obj instanceof CacheHoursJSONPK)) {
			return false;
		}

		CacheHoursJSONPK pk = (CacheHoursJSONPK)obj;

		if (stopCode.equals(pk.stopCode) && (type == pk.type)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, stopCode);
		hashCode = HashUtil.hash(hashCode, type);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("stopCode=");

		sb.append(stopCode);
		sb.append(", type=");

		sb.append(type);

		sb.append("}");

		return sb.toString();
	}

}
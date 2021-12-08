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

package eu.strasbourg.service.csmap.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import eu.strasbourg.service.csmap.model.CacheAgendaJson;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing CacheAgendaJson in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CacheAgendaJsonCacheModel
	implements CacheModel<CacheAgendaJson>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheAgendaJsonCacheModel)) {
			return false;
		}

		CacheAgendaJsonCacheModel cacheAgendaJsonCacheModel =
			(CacheAgendaJsonCacheModel)obj;

		if (cacheId == cacheAgendaJsonCacheModel.cacheId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cacheId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{cacheId=");
		sb.append(cacheId);
		sb.append(", json=");
		sb.append(json);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CacheAgendaJson toEntityModel() {
		CacheAgendaJsonImpl cacheAgendaJsonImpl = new CacheAgendaJsonImpl();

		cacheAgendaJsonImpl.setCacheId(cacheId);

		if (json == null) {
			cacheAgendaJsonImpl.setJson("");
		}
		else {
			cacheAgendaJsonImpl.setJson(json);
		}

		cacheAgendaJsonImpl.resetOriginalValues();

		return cacheAgendaJsonImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cacheId = objectInput.readLong();
		json = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(cacheId);

		if (json == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(json);
		}
	}

	public long cacheId;
	public String json;

}
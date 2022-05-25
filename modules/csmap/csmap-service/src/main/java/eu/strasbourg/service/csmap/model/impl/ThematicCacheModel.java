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

import eu.strasbourg.service.csmap.model.Thematic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Thematic in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ThematicCacheModel
	implements CacheModel<Thematic>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ThematicCacheModel)) {
			return false;
		}

		ThematicCacheModel thematicCacheModel = (ThematicCacheModel)object;

		if (thematicId == thematicCacheModel.thematicId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, thematicId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", thematicId=");
		sb.append(thematicId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", favorite=");
		sb.append(favorite);
		sb.append(", topics=");
		sb.append(topics);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Thematic toEntityModel() {
		ThematicImpl thematicImpl = new ThematicImpl();

		if (uuid == null) {
			thematicImpl.setUuid("");
		}
		else {
			thematicImpl.setUuid(uuid);
		}

		thematicImpl.setThematicId(thematicId);

		if (name == null) {
			thematicImpl.setName("");
		}
		else {
			thematicImpl.setName(name);
		}

		if (favorite == null) {
			thematicImpl.setFavorite("");
		}
		else {
			thematicImpl.setFavorite(favorite);
		}

		if (topics == null) {
			thematicImpl.setTopics("");
		}
		else {
			thematicImpl.setTopics(topics);
		}

		thematicImpl.resetOriginalValues();

		return thematicImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		thematicId = objectInput.readLong();
		name = objectInput.readUTF();
		favorite = objectInput.readUTF();
		topics = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(thematicId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (favorite == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(favorite);
		}

		if (topics == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(topics);
		}
	}

	public String uuid;
	public long thematicId;
	public String name;
	public String favorite;
	public String topics;

}
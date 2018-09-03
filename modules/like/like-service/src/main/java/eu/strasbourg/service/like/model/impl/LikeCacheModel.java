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

package eu.strasbourg.service.like.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.like.model.Like;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Like in entity cache.
 *
 * @author Cedric Henry
 * @see Like
 * @generated
 */
@ProviderType
public class LikeCacheModel implements CacheModel<Like>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LikeCacheModel)) {
			return false;
		}

		LikeCacheModel likeCacheModel = (LikeCacheModel)obj;

		if (likeId == likeCacheModel.likeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, likeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{likeId=");
		sb.append(likeId);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", isDislike=");
		sb.append(isDislike);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", entityId=");
		sb.append(entityId);
		sb.append(", entityGroupId=");
		sb.append(entityGroupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Like toEntityModel() {
		LikeImpl likeImpl = new LikeImpl();

		likeImpl.setLikeId(likeId);

		if (publikUserId == null) {
			likeImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			likeImpl.setPublikUserId(publikUserId);
		}

		if (title == null) {
			likeImpl.setTitle(StringPool.BLANK);
		}
		else {
			likeImpl.setTitle(title);
		}

		likeImpl.setIsDislike(isDislike);
		likeImpl.setTypeId(typeId);
		likeImpl.setEntityId(entityId);
		likeImpl.setEntityGroupId(entityGroupId);

		likeImpl.resetOriginalValues();

		return likeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		likeId = objectInput.readLong();
		publikUserId = objectInput.readUTF();
		title = objectInput.readUTF();

		isDislike = objectInput.readBoolean();

		typeId = objectInput.readLong();

		entityId = objectInput.readLong();

		entityGroupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(likeId);

		if (publikUserId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikUserId);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeBoolean(isDislike);

		objectOutput.writeLong(typeId);

		objectOutput.writeLong(entityId);

		objectOutput.writeLong(entityGroupId);
	}

	public long likeId;
	public String publikUserId;
	public String title;
	public boolean isDislike;
	public long typeId;
	public long entityId;
	public long entityGroupId;
}
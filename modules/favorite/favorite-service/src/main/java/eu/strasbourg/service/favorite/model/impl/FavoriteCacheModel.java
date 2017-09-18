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

package eu.strasbourg.service.favorite.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.favorite.model.Favorite;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Favorite in entity cache.
 *
 * @author BenjaminBini
 * @see Favorite
 * @generated
 */
@ProviderType
public class FavoriteCacheModel implements CacheModel<Favorite>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FavoriteCacheModel)) {
			return false;
		}

		FavoriteCacheModel favoriteCacheModel = (FavoriteCacheModel)obj;

		if (favoriteId == favoriteCacheModel.favoriteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, favoriteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{favoriteId=");
		sb.append(favoriteId);
		sb.append(", publikUserId=");
		sb.append(publikUserId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", url=");
		sb.append(url);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", entityId=");
		sb.append(entityId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Favorite toEntityModel() {
		FavoriteImpl favoriteImpl = new FavoriteImpl();

		favoriteImpl.setFavoriteId(favoriteId);

		if (publikUserId == null) {
			favoriteImpl.setPublikUserId(StringPool.BLANK);
		}
		else {
			favoriteImpl.setPublikUserId(publikUserId);
		}

		if (title == null) {
			favoriteImpl.setTitle(StringPool.BLANK);
		}
		else {
			favoriteImpl.setTitle(title);
		}

		if (url == null) {
			favoriteImpl.setUrl(StringPool.BLANK);
		}
		else {
			favoriteImpl.setUrl(url);
		}

		favoriteImpl.setTypeId(typeId);
		favoriteImpl.setEntityId(entityId);

		favoriteImpl.resetOriginalValues();

		return favoriteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		favoriteId = objectInput.readLong();
		publikUserId = objectInput.readUTF();
		title = objectInput.readUTF();
		url = objectInput.readUTF();

		typeId = objectInput.readLong();

		entityId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(favoriteId);

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

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(typeId);

		objectOutput.writeLong(entityId);
	}

	public long favoriteId;
	public String publikUserId;
	public String title;
	public String url;
	public long typeId;
	public long entityId;
}
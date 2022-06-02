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

import eu.strasbourg.service.csmap.model.PlaceCategories;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlaceCategories in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlaceCategoriesCacheModel
	implements CacheModel<PlaceCategories>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PlaceCategoriesCacheModel)) {
			return false;
		}

		PlaceCategoriesCacheModel placeCategoriesCacheModel =
			(PlaceCategoriesCacheModel)object;

		if (placeCategoriesId == placeCategoriesCacheModel.placeCategoriesId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, placeCategoriesId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", placeCategoriesId=");
		sb.append(placeCategoriesId);
		sb.append(", categoriesIds=");
		sb.append(categoriesIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PlaceCategories toEntityModel() {
		PlaceCategoriesImpl placeCategoriesImpl = new PlaceCategoriesImpl();

		if (uuid == null) {
			placeCategoriesImpl.setUuid("");
		}
		else {
			placeCategoriesImpl.setUuid(uuid);
		}

		placeCategoriesImpl.setPlaceCategoriesId(placeCategoriesId);

		if (categoriesIds == null) {
			placeCategoriesImpl.setCategoriesIds("");
		}
		else {
			placeCategoriesImpl.setCategoriesIds(categoriesIds);
		}

		placeCategoriesImpl.resetOriginalValues();

		return placeCategoriesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		placeCategoriesId = objectInput.readLong();
		categoriesIds = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(placeCategoriesId);

		if (categoriesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoriesIds);
		}
	}

	public String uuid;
	public long placeCategoriesId;
	public String categoriesIds;

}
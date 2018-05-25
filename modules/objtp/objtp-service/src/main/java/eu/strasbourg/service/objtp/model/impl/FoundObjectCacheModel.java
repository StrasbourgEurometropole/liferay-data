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

package eu.strasbourg.service.objtp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.objtp.model.FoundObject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FoundObject in entity cache.
 *
 * @author JeremyZwickert
 * @see FoundObject
 * @generated
 */
@ProviderType
public class FoundObjectCacheModel implements CacheModel<FoundObject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FoundObjectCacheModel)) {
			return false;
		}

		FoundObjectCacheModel foundObjectCacheModel = (FoundObjectCacheModel)obj;

		if (number.equals(foundObjectCacheModel.number)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, number);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{number=");
		sb.append(number);
		sb.append(", date=");
		sb.append(date);
		sb.append(", imageUrl=");
		sb.append(imageUrl);
		sb.append(", categoryCode=");
		sb.append(categoryCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FoundObject toEntityModel() {
		FoundObjectImpl foundObjectImpl = new FoundObjectImpl();

		if (number == null) {
			foundObjectImpl.setNumber(StringPool.BLANK);
		}
		else {
			foundObjectImpl.setNumber(number);
		}

		if (date == Long.MIN_VALUE) {
			foundObjectImpl.setDate(null);
		}
		else {
			foundObjectImpl.setDate(new Date(date));
		}

		if (imageUrl == null) {
			foundObjectImpl.setImageUrl(StringPool.BLANK);
		}
		else {
			foundObjectImpl.setImageUrl(imageUrl);
		}

		if (categoryCode == null) {
			foundObjectImpl.setCategoryCode(StringPool.BLANK);
		}
		else {
			foundObjectImpl.setCategoryCode(categoryCode);
		}

		foundObjectImpl.resetOriginalValues();

		return foundObjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		number = objectInput.readUTF();
		date = objectInput.readLong();
		imageUrl = objectInput.readUTF();
		categoryCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (number == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(number);
		}

		objectOutput.writeLong(date);

		if (imageUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imageUrl);
		}

		if (categoryCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(categoryCode);
		}
	}

	public String number;
	public long date;
	public String imageUrl;
	public String categoryCode;
}
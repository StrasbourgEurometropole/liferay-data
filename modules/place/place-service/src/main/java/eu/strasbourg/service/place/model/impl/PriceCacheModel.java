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

package eu.strasbourg.service.place.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.place.model.Price;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Price in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see Price
 * @generated
 */
@ProviderType
public class PriceCacheModel implements CacheModel<Price>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceCacheModel)) {
			return false;
		}

		PriceCacheModel priceCacheModel = (PriceCacheModel)obj;

		if (priceId == priceCacheModel.priceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, priceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", priceId=");
		sb.append(priceId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Price toEntityModel() {
		PriceImpl priceImpl = new PriceImpl();

		if (uuid == null) {
			priceImpl.setUuid(StringPool.BLANK);
		}
		else {
			priceImpl.setUuid(uuid);
		}

		priceImpl.setPriceId(priceId);

		if (title == null) {
			priceImpl.setTitle(StringPool.BLANK);
		}
		else {
			priceImpl.setTitle(title);
		}

		if (price == null) {
			priceImpl.setPrice(StringPool.BLANK);
		}
		else {
			priceImpl.setPrice(price);
		}

		priceImpl.resetOriginalValues();

		return priceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		priceId = objectInput.readLong();
		title = objectInput.readUTF();
		price = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(priceId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (price == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(price);
		}
	}

	public String uuid;
	public long priceId;
	public String title;
	public String price;
}
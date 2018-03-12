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

package eu.strasbourg.service.oidc.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.oidc.model.PublikUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PublikUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PublikUser
 * @generated
 */
@ProviderType
public class PublikUserCacheModel implements CacheModel<PublikUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublikUserCacheModel)) {
			return false;
		}

		PublikUserCacheModel publikUserCacheModel = (PublikUserCacheModel)obj;

		if (publikUserLiferayId == publikUserCacheModel.publikUserLiferayId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, publikUserLiferayId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", publikUserLiferayId=");
		sb.append(publikUserLiferayId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", mapConfig=");
		sb.append(mapConfig);
		sb.append(", displayConfig=");
		sb.append(displayConfig);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PublikUser toEntityModel() {
		PublikUserImpl publikUserImpl = new PublikUserImpl();

		if (uuid == null) {
			publikUserImpl.setUuid(StringPool.BLANK);
		}
		else {
			publikUserImpl.setUuid(uuid);
		}

		publikUserImpl.setPublikUserLiferayId(publikUserLiferayId);

		if (createDate == Long.MIN_VALUE) {
			publikUserImpl.setCreateDate(null);
		}
		else {
			publikUserImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			publikUserImpl.setModifiedDate(null);
		}
		else {
			publikUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (publikId == null) {
			publikUserImpl.setPublikId(StringPool.BLANK);
		}
		else {
			publikUserImpl.setPublikId(publikId);
		}

		if (accessToken == null) {
			publikUserImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			publikUserImpl.setAccessToken(accessToken);
		}

		if (firstName == null) {
			publikUserImpl.setFirstName(StringPool.BLANK);
		}
		else {
			publikUserImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			publikUserImpl.setLastName(StringPool.BLANK);
		}
		else {
			publikUserImpl.setLastName(lastName);
		}

		if (email == null) {
			publikUserImpl.setEmail(StringPool.BLANK);
		}
		else {
			publikUserImpl.setEmail(email);
		}

		if (mapConfig == null) {
			publikUserImpl.setMapConfig(StringPool.BLANK);
		}
		else {
			publikUserImpl.setMapConfig(mapConfig);
		}

		if (displayConfig == null) {
			publikUserImpl.setDisplayConfig(StringPool.BLANK);
		}
		else {
			publikUserImpl.setDisplayConfig(displayConfig);
		}

		publikUserImpl.resetOriginalValues();

		return publikUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		publikUserLiferayId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		publikId = objectInput.readUTF();
		accessToken = objectInput.readUTF();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		email = objectInput.readUTF();
		mapConfig = objectInput.readUTF();
		displayConfig = objectInput.readUTF();
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

		objectOutput.writeLong(publikUserLiferayId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		if (accessToken == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accessToken);
		}

		if (firstName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (mapConfig == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mapConfig);
		}

		if (displayConfig == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(displayConfig);
		}
	}

	public String uuid;
	public long publikUserLiferayId;
	public long createDate;
	public long modifiedDate;
	public String publikId;
	public String accessToken;
	public String firstName;
	public String lastName;
	public String email;
	public String mapConfig;
	public String displayConfig;
}
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

package eu.strasbourg.service.gtfs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.gtfs.model.Ligne;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Ligne in entity cache.
 *
 * @author Cedric Henry
 * @see Ligne
 * @generated
 */
@ProviderType
public class LigneCacheModel implements CacheModel<Ligne>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LigneCacheModel)) {
			return false;
		}

		LigneCacheModel ligneCacheModel = (LigneCacheModel)obj;

		if (ligneId == ligneCacheModel.ligneId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ligneId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ligneId=");
		sb.append(ligneId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", routeId=");
		sb.append(routeId);
		sb.append(", shortName=");
		sb.append(shortName);
		sb.append(", title=");
		sb.append(title);
		sb.append(", type=");
		sb.append(type);
		sb.append(", backgroundColor=");
		sb.append(backgroundColor);
		sb.append(", textColor=");
		sb.append(textColor);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Ligne toEntityModel() {
		LigneImpl ligneImpl = new LigneImpl();

		if (uuid == null) {
			ligneImpl.setUuid(StringPool.BLANK);
		}
		else {
			ligneImpl.setUuid(uuid);
		}

		ligneImpl.setLigneId(ligneId);
		ligneImpl.setGroupId(groupId);
		ligneImpl.setCompanyId(companyId);
		ligneImpl.setUserId(userId);

		if (userName == null) {
			ligneImpl.setUserName(StringPool.BLANK);
		}
		else {
			ligneImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ligneImpl.setCreateDate(null);
		}
		else {
			ligneImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ligneImpl.setModifiedDate(null);
		}
		else {
			ligneImpl.setModifiedDate(new Date(modifiedDate));
		}

		ligneImpl.setStatus(status);
		ligneImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			ligneImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			ligneImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			ligneImpl.setStatusDate(null);
		}
		else {
			ligneImpl.setStatusDate(new Date(statusDate));
		}

		if (routeId == null) {
			ligneImpl.setRouteId(StringPool.BLANK);
		}
		else {
			ligneImpl.setRouteId(routeId);
		}

		if (shortName == null) {
			ligneImpl.setShortName(StringPool.BLANK);
		}
		else {
			ligneImpl.setShortName(shortName);
		}

		if (title == null) {
			ligneImpl.setTitle(StringPool.BLANK);
		}
		else {
			ligneImpl.setTitle(title);
		}

		ligneImpl.setType(type);

		if (backgroundColor == null) {
			ligneImpl.setBackgroundColor(StringPool.BLANK);
		}
		else {
			ligneImpl.setBackgroundColor(backgroundColor);
		}

		if (textColor == null) {
			ligneImpl.setTextColor(StringPool.BLANK);
		}
		else {
			ligneImpl.setTextColor(textColor);
		}

		ligneImpl.resetOriginalValues();

		return ligneImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		ligneId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		routeId = objectInput.readUTF();
		shortName = objectInput.readUTF();
		title = objectInput.readUTF();

		type = objectInput.readInt();
		backgroundColor = objectInput.readUTF();
		textColor = objectInput.readUTF();
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

		objectOutput.writeLong(ligneId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (routeId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(routeId);
		}

		if (shortName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortName);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(type);

		if (backgroundColor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(backgroundColor);
		}

		if (textColor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(textColor);
		}
	}

	public String uuid;
	public long ligneId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String routeId;
	public String shortName;
	public String title;
	public int type;
	public String backgroundColor;
	public String textColor;
}
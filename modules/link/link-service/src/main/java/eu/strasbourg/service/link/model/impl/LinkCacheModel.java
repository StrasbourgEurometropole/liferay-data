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

package eu.strasbourg.service.link.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.link.model.Link;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Link in entity cache.
 *
 * @author BenjaminBini
 * @see Link
 * @generated
 */
@ProviderType
public class LinkCacheModel implements CacheModel<Link>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LinkCacheModel)) {
			return false;
		}

		LinkCacheModel linkCacheModel = (LinkCacheModel)obj;

		if (linkId == linkCacheModel.linkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, linkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", linkId=");
		sb.append(linkId);
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
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", URL=");
		sb.append(URL);
		sb.append(", hoverText=");
		sb.append(hoverText);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Link toEntityModel() {
		LinkImpl linkImpl = new LinkImpl();

		if (uuid == null) {
			linkImpl.setUuid(StringPool.BLANK);
		}
		else {
			linkImpl.setUuid(uuid);
		}

		linkImpl.setLinkId(linkId);
		linkImpl.setGroupId(groupId);
		linkImpl.setCompanyId(companyId);
		linkImpl.setUserId(userId);

		if (userName == null) {
			linkImpl.setUserName(StringPool.BLANK);
		}
		else {
			linkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			linkImpl.setCreateDate(null);
		}
		else {
			linkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			linkImpl.setModifiedDate(null);
		}
		else {
			linkImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			linkImpl.setLastPublishDate(null);
		}
		else {
			linkImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		if (title == null) {
			linkImpl.setTitle(StringPool.BLANK);
		}
		else {
			linkImpl.setTitle(title);
		}

		if (URL == null) {
			linkImpl.setURL(StringPool.BLANK);
		}
		else {
			linkImpl.setURL(URL);
		}

		if (hoverText == null) {
			linkImpl.setHoverText(StringPool.BLANK);
		}
		else {
			linkImpl.setHoverText(hoverText);
		}

		linkImpl.resetOriginalValues();

		return linkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		linkId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
		title = objectInput.readUTF();
		URL = objectInput.readUTF();
		hoverText = objectInput.readUTF();
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

		objectOutput.writeLong(linkId);

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
		objectOutput.writeLong(lastPublishDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (URL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(URL);
		}

		if (hoverText == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hoverText);
		}
	}

	public String uuid;
	public long linkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public String title;
	public String URL;
	public String hoverText;
}
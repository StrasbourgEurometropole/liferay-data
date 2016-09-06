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

package eu.strasbourg.service.edition.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.edition.model.Edition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Edition in entity cache.
 *
 * @author BenjaminBini
 * @see Edition
 * @generated
 */
@ProviderType
public class EditionCacheModel implements CacheModel<Edition>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EditionCacheModel)) {
			return false;
		}

		EditionCacheModel editionCacheModel = (EditionCacheModel)obj;

		if (editionId == editionCacheModel.editionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, editionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", editionId=");
		sb.append(editionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", subtitle=");
		sb.append(subtitle);
		sb.append(", description=");
		sb.append(description);
		sb.append(", URL=");
		sb.append(URL);
		sb.append(", author=");
		sb.append(author);
		sb.append(", editor=");
		sb.append(editor);
		sb.append(", distribution=");
		sb.append(distribution);
		sb.append(", ISBN=");
		sb.append(ISBN);
		sb.append(", price=");
		sb.append(price);
		sb.append(", availableForExchange=");
		sb.append(availableForExchange);
		sb.append(", inStock=");
		sb.append(inStock);
		sb.append(", diffusionDate=");
		sb.append(diffusionDate);
		sb.append(", pageNumber=");
		sb.append(pageNumber);
		sb.append(", pictureNumber=");
		sb.append(pictureNumber);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Edition toEntityModel() {
		EditionImpl editionImpl = new EditionImpl();

		if (uuid == null) {
			editionImpl.setUuid(StringPool.BLANK);
		}
		else {
			editionImpl.setUuid(uuid);
		}

		editionImpl.setEditionId(editionId);
		editionImpl.setGroupId(groupId);
		editionImpl.setCompanyId(companyId);
		editionImpl.setUserId(userId);

		if (userName == null) {
			editionImpl.setUserName(StringPool.BLANK);
		}
		else {
			editionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			editionImpl.setCreateDate(null);
		}
		else {
			editionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			editionImpl.setModifiedDate(null);
		}
		else {
			editionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			editionImpl.setTitle(StringPool.BLANK);
		}
		else {
			editionImpl.setTitle(title);
		}

		if (subtitle == null) {
			editionImpl.setSubtitle(StringPool.BLANK);
		}
		else {
			editionImpl.setSubtitle(subtitle);
		}

		if (description == null) {
			editionImpl.setDescription(StringPool.BLANK);
		}
		else {
			editionImpl.setDescription(description);
		}

		if (URL == null) {
			editionImpl.setURL(StringPool.BLANK);
		}
		else {
			editionImpl.setURL(URL);
		}

		if (author == null) {
			editionImpl.setAuthor(StringPool.BLANK);
		}
		else {
			editionImpl.setAuthor(author);
		}

		if (editor == null) {
			editionImpl.setEditor(StringPool.BLANK);
		}
		else {
			editionImpl.setEditor(editor);
		}

		if (distribution == null) {
			editionImpl.setDistribution(StringPool.BLANK);
		}
		else {
			editionImpl.setDistribution(distribution);
		}

		if (ISBN == null) {
			editionImpl.setISBN(StringPool.BLANK);
		}
		else {
			editionImpl.setISBN(ISBN);
		}

		if (price == null) {
			editionImpl.setPrice(StringPool.BLANK);
		}
		else {
			editionImpl.setPrice(price);
		}

		editionImpl.setAvailableForExchange(availableForExchange);
		editionImpl.setInStock(inStock);

		if (diffusionDate == null) {
			editionImpl.setDiffusionDate(StringPool.BLANK);
		}
		else {
			editionImpl.setDiffusionDate(diffusionDate);
		}

		if (pageNumber == null) {
			editionImpl.setPageNumber(StringPool.BLANK);
		}
		else {
			editionImpl.setPageNumber(pageNumber);
		}

		if (pictureNumber == null) {
			editionImpl.setPictureNumber(StringPool.BLANK);
		}
		else {
			editionImpl.setPictureNumber(pictureNumber);
		}

		if (publicationDate == Long.MIN_VALUE) {
			editionImpl.setPublicationDate(null);
		}
		else {
			editionImpl.setPublicationDate(new Date(publicationDate));
		}

		editionImpl.setStatus(status);
		editionImpl.setImageId(imageId);

		if (fileId == null) {
			editionImpl.setFileId(StringPool.BLANK);
		}
		else {
			editionImpl.setFileId(fileId);
		}

		editionImpl.resetOriginalValues();

		return editionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		editionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		subtitle = objectInput.readUTF();
		description = objectInput.readUTF();
		URL = objectInput.readUTF();
		author = objectInput.readUTF();
		editor = objectInput.readUTF();
		distribution = objectInput.readUTF();
		ISBN = objectInput.readUTF();
		price = objectInput.readUTF();

		availableForExchange = objectInput.readBoolean();

		inStock = objectInput.readBoolean();
		diffusionDate = objectInput.readUTF();
		pageNumber = objectInput.readUTF();
		pictureNumber = objectInput.readUTF();
		publicationDate = objectInput.readLong();

		status = objectInput.readBoolean();

		imageId = objectInput.readLong();
		fileId = objectInput.readUTF();
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

		objectOutput.writeLong(editionId);

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

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (subtitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subtitle);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (URL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(URL);
		}

		if (author == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(author);
		}

		if (editor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(editor);
		}

		if (distribution == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(distribution);
		}

		if (ISBN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ISBN);
		}

		if (price == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(price);
		}

		objectOutput.writeBoolean(availableForExchange);

		objectOutput.writeBoolean(inStock);

		if (diffusionDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(diffusionDate);
		}

		if (pageNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pageNumber);
		}

		if (pictureNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pictureNumber);
		}

		objectOutput.writeLong(publicationDate);

		objectOutput.writeBoolean(status);

		objectOutput.writeLong(imageId);

		if (fileId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileId);
		}
	}

	public String uuid;
	public long editionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String subtitle;
	public String description;
	public String URL;
	public String author;
	public String editor;
	public String distribution;
	public String ISBN;
	public String price;
	public boolean availableForExchange;
	public boolean inStock;
	public String diffusionDate;
	public String pageNumber;
	public String pictureNumber;
	public long publicationDate;
	public boolean status;
	public long imageId;
	public String fileId;
}
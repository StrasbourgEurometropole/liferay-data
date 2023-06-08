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

package eu.strasbourg.service.comment.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.comment.model.Comment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Comment in entity cache.
 *
 * @author Romain Vergnais
 * @generated
 */
public class CommentCacheModel implements CacheModel<Comment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommentCacheModel)) {
			return false;
		}

		CommentCacheModel commentCacheModel = (CommentCacheModel)object;

		if (commentId == commentCacheModel.commentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commentId=");
		sb.append(commentId);
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
		sb.append(", text=");
		sb.append(text);
		sb.append(", level=");
		sb.append(level);
		sb.append(", userQuality=");
		sb.append(userQuality);
		sb.append(", modifiedByUserDate=");
		sb.append(modifiedByUserDate);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", parentCommentId=");
		sb.append(parentCommentId);
		sb.append(", urlProjectCommentaire=");
		sb.append(urlProjectCommentaire);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Comment toEntityModel() {
		CommentImpl commentImpl = new CommentImpl();

		if (uuid == null) {
			commentImpl.setUuid("");
		}
		else {
			commentImpl.setUuid(uuid);
		}

		commentImpl.setCommentId(commentId);
		commentImpl.setGroupId(groupId);
		commentImpl.setCompanyId(companyId);
		commentImpl.setUserId(userId);

		if (userName == null) {
			commentImpl.setUserName("");
		}
		else {
			commentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commentImpl.setCreateDate(null);
		}
		else {
			commentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commentImpl.setModifiedDate(null);
		}
		else {
			commentImpl.setModifiedDate(new Date(modifiedDate));
		}

		commentImpl.setStatus(status);
		commentImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			commentImpl.setStatusByUserName("");
		}
		else {
			commentImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			commentImpl.setStatusDate(null);
		}
		else {
			commentImpl.setStatusDate(new Date(statusDate));
		}

		if (text == null) {
			commentImpl.setText("");
		}
		else {
			commentImpl.setText(text);
		}

		commentImpl.setLevel(level);

		if (userQuality == null) {
			commentImpl.setUserQuality("");
		}
		else {
			commentImpl.setUserQuality(userQuality);
		}

		if (modifiedByUserDate == Long.MIN_VALUE) {
			commentImpl.setModifiedByUserDate(null);
		}
		else {
			commentImpl.setModifiedByUserDate(new Date(modifiedByUserDate));
		}

		commentImpl.setAssetEntryId(assetEntryId);

		if (publikId == null) {
			commentImpl.setPublikId("");
		}
		else {
			commentImpl.setPublikId(publikId);
		}

		commentImpl.setParentCommentId(parentCommentId);

		if (urlProjectCommentaire == null) {
			commentImpl.setUrlProjectCommentaire("");
		}
		else {
			commentImpl.setUrlProjectCommentaire(urlProjectCommentaire);
		}

		commentImpl.resetOriginalValues();

		return commentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		commentId = objectInput.readLong();

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
		text = (String)objectInput.readObject();

		level = objectInput.readInt();
		userQuality = objectInput.readUTF();
		modifiedByUserDate = objectInput.readLong();

		assetEntryId = objectInput.readLong();
		publikId = objectInput.readUTF();

		parentCommentId = objectInput.readLong();
		urlProjectCommentaire = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(commentId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (text == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(text);
		}

		objectOutput.writeInt(level);

		if (userQuality == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userQuality);
		}

		objectOutput.writeLong(modifiedByUserDate);

		objectOutput.writeLong(assetEntryId);

		if (publikId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		objectOutput.writeLong(parentCommentId);

		if (urlProjectCommentaire == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(urlProjectCommentaire);
		}
	}

	public String uuid;
	public long commentId;
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
	public String text;
	public int level;
	public String userQuality;
	public long modifiedByUserDate;
	public long assetEntryId;
	public String publikId;
	public long parentCommentId;
	public String urlProjectCommentaire;

}
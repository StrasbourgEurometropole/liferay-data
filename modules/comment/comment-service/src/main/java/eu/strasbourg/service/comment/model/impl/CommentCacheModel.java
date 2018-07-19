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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

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
 * @see Comment
 * @generated
 */
@ProviderType
public class CommentCacheModel implements CacheModel<Comment>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommentCacheModel)) {
			return false;
		}

		CommentCacheModel commentCacheModel = (CommentCacheModel)obj;

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
		StringBundler sb = new StringBundler(37);

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
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", level=");
		sb.append(level);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", publikId=");
		sb.append(publikId);
<<<<<<< HEAD
		sb.append(", parentCommentId=");
		sb.append(parentCommentId);
=======
		sb.append(", urlProjectCommentaire=");
		sb.append(urlProjectCommentaire);
		sb.append(", like=");
		sb.append(like);
		sb.append(", dislike=");
		sb.append(dislike);
>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Comment toEntityModel() {
		CommentImpl commentImpl = new CommentImpl();

		if (uuid == null) {
			commentImpl.setUuid(StringPool.BLANK);
		}
		else {
			commentImpl.setUuid(uuid);
		}

		commentImpl.setCommentId(commentId);
		commentImpl.setGroupId(groupId);
		commentImpl.setCompanyId(companyId);
		commentImpl.setUserId(userId);

		if (userName == null) {
			commentImpl.setUserName(StringPool.BLANK);
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
			commentImpl.setStatusByUserName(StringPool.BLANK);
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

		if (comment == null) {
			commentImpl.setComment(StringPool.BLANK);
		}
		else {
			commentImpl.setComment(comment);
		}

		commentImpl.setLevel(level);
		commentImpl.setAssetEntryId(assetEntryId);

		if (publikId == null) {
			commentImpl.setPublikId(StringPool.BLANK);
		}
		else {
			commentImpl.setPublikId(publikId);
		}

<<<<<<< HEAD
		commentImpl.setParentCommentId(parentCommentId);
=======
		if (urlProjectCommentaire == null) {
			commentImpl.setUrlProjectCommentaire(StringPool.BLANK);
		}
		else {
			commentImpl.setUrlProjectCommentaire(urlProjectCommentaire);
		}

		commentImpl.setLike(like);
		commentImpl.setDislike(dislike);
>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477

		commentImpl.resetOriginalValues();

		return commentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
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
		comment = objectInput.readUTF();

		level = objectInput.readInt();

		assetEntryId = objectInput.readLong();
		publikId = objectInput.readUTF();
		urlProjectCommentaire = objectInput.readUTF();

		parentCommentId = objectInput.readLong();
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

		objectOutput.writeLong(commentId);

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

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeInt(level);

		objectOutput.writeLong(assetEntryId);

		if (publikId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publikId);
		}

<<<<<<< HEAD
		objectOutput.writeLong(parentCommentId);
=======
		if (urlProjectCommentaire == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(urlProjectCommentaire);
		}

		objectOutput.writeLong(like);

		objectOutput.writeLong(dislike);
>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477
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
	public String comment;
	public int level;
	public long assetEntryId;
	public String publikId;
<<<<<<< HEAD
	public long parentCommentId;
=======
	public String urlProjectCommentaire;
	public long like;
	public long dislike;
>>>>>>> 275f1d6a9e647c62843a36553e9957c0bfd6b477
}
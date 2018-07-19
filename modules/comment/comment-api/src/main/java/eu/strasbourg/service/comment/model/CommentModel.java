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

package eu.strasbourg.service.comment.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Comment service. Represents a row in the &quot;comment_Comment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link eu.strasbourg.service.comment.model.impl.CommentModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link eu.strasbourg.service.comment.model.impl.CommentImpl}.
 * </p>
 *
 * @author Romain Vergnais
 * @see Comment
 * @see eu.strasbourg.service.comment.model.impl.CommentImpl
 * @see eu.strasbourg.service.comment.model.impl.CommentModelImpl
 * @generated
 */
@ProviderType
public interface CommentModel extends BaseModel<Comment>, GroupedModel,
	ShardedModel, StagedAuditedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a comment model instance should use the {@link Comment} interface instead.
	 */

	/**
	 * Returns the primary key of this comment.
	 *
	 * @return the primary key of this comment
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this comment.
	 *
	 * @param primaryKey the primary key of this comment
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this comment.
	 *
	 * @return the uuid of this comment
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this comment.
	 *
	 * @param uuid the uuid of this comment
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the comment ID of this comment.
	 *
	 * @return the comment ID of this comment
	 */
	public long getCommentId();

	/**
	 * Sets the comment ID of this comment.
	 *
	 * @param commentId the comment ID of this comment
	 */
	public void setCommentId(long commentId);

	/**
	 * Returns the group ID of this comment.
	 *
	 * @return the group ID of this comment
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this comment.
	 *
	 * @param groupId the group ID of this comment
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this comment.
	 *
	 * @return the company ID of this comment
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this comment.
	 *
	 * @param companyId the company ID of this comment
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this comment.
	 *
	 * @return the user ID of this comment
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this comment.
	 *
	 * @param userId the user ID of this comment
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this comment.
	 *
	 * @return the user uuid of this comment
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this comment.
	 *
	 * @param userUuid the user uuid of this comment
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this comment.
	 *
	 * @return the user name of this comment
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this comment.
	 *
	 * @param userName the user name of this comment
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this comment.
	 *
	 * @return the create date of this comment
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this comment.
	 *
	 * @param createDate the create date of this comment
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this comment.
	 *
	 * @return the modified date of this comment
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this comment.
	 *
	 * @param modifiedDate the modified date of this comment
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the status of this comment.
	 *
	 * @return the status of this comment
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this comment.
	 *
	 * @param status the status of this comment
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this comment.
	 *
	 * @return the status by user ID of this comment
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this comment.
	 *
	 * @param statusByUserId the status by user ID of this comment
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this comment.
	 *
	 * @return the status by user uuid of this comment
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this comment.
	 *
	 * @param statusByUserUuid the status by user uuid of this comment
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this comment.
	 *
	 * @return the status by user name of this comment
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this comment.
	 *
	 * @param statusByUserName the status by user name of this comment
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this comment.
	 *
	 * @return the status date of this comment
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this comment.
	 *
	 * @param statusDate the status date of this comment
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the url project commentaire of this comment.
	 *
	 * @return the url project commentaire of this comment
	 */
	@AutoEscape
	public String getUrlProjectCommentaire();

	/**
	 * Sets the url project commentaire of this comment.
	 *
	 * @param urlProjectCommentaire the url project commentaire of this comment
	 */
	public void setUrlProjectCommentaire(String urlProjectCommentaire);

	/**
	 * Returns the comment of this comment.
	 *
	 * @return the comment of this comment
	 */
	@AutoEscape
	public String getComment();

	/**
	 * Sets the comment of this comment.
	 *
	 * @param comment the comment of this comment
	 */
	public void setComment(String comment);

	/**
	 * Returns the asset entry ID of this comment.
	 *
	 * @return the asset entry ID of this comment
	 */
	public long getAssetEntryId();

	/**
	 * Sets the asset entry ID of this comment.
	 *
	 * @param assetEntryId the asset entry ID of this comment
	 */
	public void setAssetEntryId(long assetEntryId);

	/**
	 * Returns the publik ID of this comment.
	 *
	 * @return the publik ID of this comment
	 */
	@AutoEscape
	public String getPublikId();

	/**
	 * Sets the publik ID of this comment.
	 *
	 * @param publikId the publik ID of this comment
	 */
	public void setPublikId(String publikId);

	/**
	 * Returns the like of this comment.
	 *
	 * @return the like of this comment
	 */
	public long getLike();

	/**
	 * Sets the like of this comment.
	 *
	 * @param like the like of this comment
	 */
	public void setLike(long like);

	/**
	 * Returns the dislike of this comment.
	 *
	 * @return the dislike of this comment
	 */
	public long getDislike();

	/**
	 * Sets the dislike of this comment.
	 *
	 * @param dislike the dislike of this comment
	 */
	public void setDislike(long dislike);

	/**
	 * Returns <code>true</code> if this comment is approved.
	 *
	 * @return <code>true</code> if this comment is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this comment is denied.
	 *
	 * @return <code>true</code> if this comment is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this comment is a draft.
	 *
	 * @return <code>true</code> if this comment is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this comment is expired.
	 *
	 * @return <code>true</code> if this comment is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this comment is inactive.
	 *
	 * @return <code>true</code> if this comment is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this comment is incomplete.
	 *
	 * @return <code>true</code> if this comment is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this comment is pending.
	 *
	 * @return <code>true</code> if this comment is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this comment is scheduled.
	 *
	 * @return <code>true</code> if this comment is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(eu.strasbourg.service.comment.model.Comment comment);

	@Override
	public int hashCode();

	@Override
	public CacheModel<eu.strasbourg.service.comment.model.Comment> toCacheModel();

	@Override
	public eu.strasbourg.service.comment.model.Comment toEscapedModel();

	@Override
	public eu.strasbourg.service.comment.model.Comment toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Comment}.
 * </p>
 *
 * @author Romain Vergnais
 * @see Comment
 * @generated
 */
@ProviderType
public class CommentWrapper implements Comment, ModelWrapper<Comment> {
	public CommentWrapper(Comment comment) {
		_comment = comment;
	}

	@Override
	public Class<?> getModelClass() {
		return Comment.class;
	}

	@Override
	public String getModelClassName() {
		return Comment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("commentId", getCommentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("comment", getComment());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long commentId = (Long)attributes.get("commentId");

		if (commentId != null) {
			setCommentId(commentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _comment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _comment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _comment.isNew();
	}

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _comment.getAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _comment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.comment.model.Comment> toCacheModel() {
		return _comment.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.comment.model.Comment toEscapedModel() {
		return new CommentWrapper(_comment.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.comment.model.Comment toUnescapedModel() {
		return new CommentWrapper(_comment.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.comment.model.Comment comment) {
		return _comment.compareTo(comment);
	}

	@Override
	public int hashCode() {
		return _comment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _comment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CommentWrapper((Comment)_comment.clone());
	}

	/**
	* Returns the comment of this comment.
	*
	* @return the comment of this comment
	*/
	@Override
	public java.lang.String getComment() {
		return _comment.getComment();
	}

	/**
	* Returns the user name of this comment.
	*
	* @return the user name of this comment
	*/
	@Override
	public java.lang.String getUserName() {
		return _comment.getUserName();
	}

	/**
	* Returns the user uuid of this comment.
	*
	* @return the user uuid of this comment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _comment.getUserUuid();
	}

	/**
	* Returns the uuid of this comment.
	*
	* @return the uuid of this comment
	*/
	@Override
	public java.lang.String getUuid() {
		return _comment.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _comment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _comment.toXmlString();
	}

	/**
	* Returns the create date of this comment.
	*
	* @return the create date of this comment
	*/
	@Override
	public Date getCreateDate() {
		return _comment.getCreateDate();
	}

	/**
	* Returns the modified date of this comment.
	*
	* @return the modified date of this comment
	*/
	@Override
	public Date getModifiedDate() {
		return _comment.getModifiedDate();
	}

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories() {
		return _comment.getCategories();
	}

	/**
	* Returns the comment ID of this comment.
	*
	* @return the comment ID of this comment
	*/
	@Override
	public long getCommentId() {
		return _comment.getCommentId();
	}

	/**
	* Returns the company ID of this comment.
	*
	* @return the company ID of this comment
	*/
	@Override
	public long getCompanyId() {
		return _comment.getCompanyId();
	}

	/**
	* Returns the group ID of this comment.
	*
	* @return the group ID of this comment
	*/
	@Override
	public long getGroupId() {
		return _comment.getGroupId();
	}

	/**
	* Returns the primary key of this comment.
	*
	* @return the primary key of this comment
	*/
	@Override
	public long getPrimaryKey() {
		return _comment.getPrimaryKey();
	}

	/**
	* Returns the user ID of this comment.
	*
	* @return the user ID of this comment
	*/
	@Override
	public long getUserId() {
		return _comment.getUserId();
	}

	@Override
	public void persist() {
		_comment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_comment.setCachedModel(cachedModel);
	}

	/**
	* Sets the comment of this comment.
	*
	* @param comment the comment of this comment
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_comment.setComment(comment);
	}

	/**
	* Sets the comment ID of this comment.
	*
	* @param commentId the comment ID of this comment
	*/
	@Override
	public void setCommentId(long commentId) {
		_comment.setCommentId(commentId);
	}

	/**
	* Sets the company ID of this comment.
	*
	* @param companyId the company ID of this comment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_comment.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this comment.
	*
	* @param createDate the create date of this comment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_comment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_comment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_comment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_comment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this comment.
	*
	* @param groupId the group ID of this comment
	*/
	@Override
	public void setGroupId(long groupId) {
		_comment.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this comment.
	*
	* @param modifiedDate the modified date of this comment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_comment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_comment.setNew(n);
	}

	/**
	* Sets the primary key of this comment.
	*
	* @param primaryKey the primary key of this comment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_comment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_comment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this comment.
	*
	* @param userId the user ID of this comment
	*/
	@Override
	public void setUserId(long userId) {
		_comment.setUserId(userId);
	}

	/**
	* Sets the user name of this comment.
	*
	* @param userName the user name of this comment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_comment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this comment.
	*
	* @param userUuid the user uuid of this comment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_comment.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this comment.
	*
	* @param uuid the uuid of this comment
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_comment.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommentWrapper)) {
			return false;
		}

		CommentWrapper commentWrapper = (CommentWrapper)obj;

		if (Objects.equals(_comment, commentWrapper._comment)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _comment.getStagedModelType();
	}

	@Override
	public Comment getWrappedModel() {
		return _comment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _comment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _comment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_comment.resetOriginalValues();
	}

	private final Comment _comment;
}
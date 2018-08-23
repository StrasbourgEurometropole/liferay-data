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
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("comment", getComment());
		attributes.put("level", getLevel());
		attributes.put("userQuality", getUserQuality());
		attributes.put("modifiedByUserDate", getModifiedByUserDate());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("publikId", getPublikId());
		attributes.put("parentCommentId", getParentCommentId());
		attributes.put("urlProjectCommentaire", getUrlProjectCommentaire());

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

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Integer level = (Integer)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}

		String userQuality = (String)attributes.get("userQuality");

		if (userQuality != null) {
			setUserQuality(userQuality);
		}

		Date modifiedByUserDate = (Date)attributes.get("modifiedByUserDate");

		if (modifiedByUserDate != null) {
			setModifiedByUserDate(modifiedByUserDate);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		Long parentCommentId = (Long)attributes.get("parentCommentId");

		if (parentCommentId != null) {
			setParentCommentId(parentCommentId);
		}

		String urlProjectCommentaire = (String)attributes.get(
				"urlProjectCommentaire");

		if (urlProjectCommentaire != null) {
			setUrlProjectCommentaire(urlProjectCommentaire);
		}
	}

	/**
	* Returns <code>true</code> if this comment is approved.
	*
	* @return <code>true</code> if this comment is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _comment.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _comment.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this comment is denied.
	*
	* @return <code>true</code> if this comment is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _comment.isDenied();
	}

	/**
	* Returns <code>true</code> if this comment is a draft.
	*
	* @return <code>true</code> if this comment is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _comment.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _comment.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this comment is expired.
	*
	* @return <code>true</code> if this comment is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _comment.isExpired();
	}

	/**
	* Returns <code>true</code> if this comment is inactive.
	*
	* @return <code>true</code> if this comment is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _comment.isInactive();
	}

	/**
	* Returns <code>true</code> if this comment is incomplete.
	*
	* @return <code>true</code> if this comment is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _comment.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _comment.isNew();
	}

	/**
	* Returns <code>true</code> if this comment is pending.
	*
	* @return <code>true</code> if this comment is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _comment.isPending();
	}

	/**
	* Returns <code>true</code> if this comment is scheduled.
	*
	* @return <code>true</code> if this comment is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _comment.isScheduled();
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
	public eu.strasbourg.service.oidc.model.PublikUser getPublikUser() {
		return _comment.getPublikUser();
	}

	@Override
	public int compareTo(eu.strasbourg.service.comment.model.Comment comment) {
		return _comment.compareTo(comment);
	}

	/**
	* méthode qui renvoie le nombre de signalement pour un commentaire.
	*
	* @return le nombre de signalement en int.
	*/
	@Override
	public int getCountSignalements() {
		return _comment.getCountSignalements();
	}

	/**
	* Returns the level of this comment.
	*
	* @return the level of this comment
	*/
	@Override
	public int getLevel() {
		return _comment.getLevel();
	}

	/**
	* Retourne le nombre de dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public int getNbDislikes() {
		return _comment.getNbDislikes();
	}

	/**
	* Retourne le nombre de likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public int getNbLikes() {
		return _comment.getNbLikes();
	}

	/**
	* Retourne le nombre de likes/dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public int getNbLikesDislikes() {
		return _comment.getNbLikesDislikes();
	}

	/**
	* Returns the status of this comment.
	*
	* @return the status of this comment
	*/
	@Override
	public int getStatus() {
		return _comment.getStatus();
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

	@Override
	public java.lang.String getAssetEntryTitle() {
		return _comment.getAssetEntryTitle();
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
	* méthode permettant de retourner le nom de l'utilisateur en entier.
	*
	* @return le nom de l'utilisateur.
	*/
	@Override
	public java.lang.String getFullPublikUserName() {
		return _comment.getFullPublikUserName();
	}

	/**
	* Returns the publik ID of this comment.
	*
	* @return the publik ID of this comment
	*/
	@Override
	public java.lang.String getPublikId() {
		return _comment.getPublikId();
	}

	@Override
	public java.lang.String getPublikUserName() {
		return _comment.getPublikUserName();
	}

	/**
	* Returns the status by user name of this comment.
	*
	* @return the status by user name of this comment
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _comment.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this comment.
	*
	* @return the status by user uuid of this comment
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _comment.getStatusByUserUuid();
	}

	@Override
	public java.lang.String getTypeAssetEntry() {
		return _comment.getTypeAssetEntry();
	}

	/**
	* Returns the url project commentaire of this comment.
	*
	* @return the url project commentaire of this comment
	*/
	@Override
	public java.lang.String getUrlProjectCommentaire() {
		return _comment.getUrlProjectCommentaire();
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
	* Returns the user quality of this comment.
	*
	* @return the user quality of this comment
	*/
	@Override
	public java.lang.String getUserQuality() {
		return _comment.getUserQuality();
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
	* Returns the modified by user date of this comment.
	*
	* @return the modified by user date of this comment
	*/
	@Override
	public Date getModifiedByUserDate() {
		return _comment.getModifiedByUserDate();
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
	* Returns the status date of this comment.
	*
	* @return the status date of this comment
	*/
	@Override
	public Date getStatusDate() {
		return _comment.getStatusDate();
	}

	/**
	* méthode qui renvoie la liste des signalements d'un commentaire.
	*
	* @return la liste des signalements
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> findSignalements() {
		return _comment.findSignalements();
	}

	/**
	* Retourne la liste des commentaires enfants de l'item
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Comment> getApprovedChildComments() {
		return _comment.getApprovedChildComments();
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
	* Retourne la liste des dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getDislikes() {
		return _comment.getDislikes();
	}

	/**
	* Retourne la liste des likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getLikes() {
		return _comment.getLikes();
	}

	/**
	* Retourne la liste des like/dislike de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getLikesDislikes() {
		return _comment.getLikesDislikes();
	}

	/**
	* Returns the asset entry ID of this comment.
	*
	* @return the asset entry ID of this comment
	*/
	@Override
	public long getAssetEntryId() {
		return _comment.getAssetEntryId();
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
	* Returns the parent comment ID of this comment.
	*
	* @return the parent comment ID of this comment
	*/
	@Override
	public long getParentCommentId() {
		return _comment.getParentCommentId();
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
	* Returns the status by user ID of this comment.
	*
	* @return the status by user ID of this comment
	*/
	@Override
	public long getStatusByUserId() {
		return _comment.getStatusByUserId();
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

	/**
	* Sets the asset entry ID of this comment.
	*
	* @param assetEntryId the asset entry ID of this comment
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_comment.setAssetEntryId(assetEntryId);
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
	* Sets the level of this comment.
	*
	* @param level the level of this comment
	*/
	@Override
	public void setLevel(int level) {
		_comment.setLevel(level);
	}

	/**
	* Sets the modified by user date of this comment.
	*
	* @param modifiedByUserDate the modified by user date of this comment
	*/
	@Override
	public void setModifiedByUserDate(Date modifiedByUserDate) {
		_comment.setModifiedByUserDate(modifiedByUserDate);
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
	* Sets the parent comment ID of this comment.
	*
	* @param parentCommentId the parent comment ID of this comment
	*/
	@Override
	public void setParentCommentId(long parentCommentId) {
		_comment.setParentCommentId(parentCommentId);
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
	* Sets the publik ID of this comment.
	*
	* @param publikId the publik ID of this comment
	*/
	@Override
	public void setPublikId(java.lang.String publikId) {
		_comment.setPublikId(publikId);
	}

	/**
	* Sets the status of this comment.
	*
	* @param status the status of this comment
	*/
	@Override
	public void setStatus(int status) {
		_comment.setStatus(status);
	}

	/**
	* Sets the status by user ID of this comment.
	*
	* @param statusByUserId the status by user ID of this comment
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_comment.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this comment.
	*
	* @param statusByUserName the status by user name of this comment
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_comment.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this comment.
	*
	* @param statusByUserUuid the status by user uuid of this comment
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_comment.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this comment.
	*
	* @param statusDate the status date of this comment
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_comment.setStatusDate(statusDate);
	}

	/**
	* Sets the url project commentaire of this comment.
	*
	* @param urlProjectCommentaire the url project commentaire of this comment
	*/
	@Override
	public void setUrlProjectCommentaire(java.lang.String urlProjectCommentaire) {
		_comment.setUrlProjectCommentaire(urlProjectCommentaire);
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
	* Sets the user quality of this comment.
	*
	* @param userQuality the user quality of this comment
	*/
	@Override
	public void setUserQuality(java.lang.String userQuality) {
		_comment.setUserQuality(userQuality);
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
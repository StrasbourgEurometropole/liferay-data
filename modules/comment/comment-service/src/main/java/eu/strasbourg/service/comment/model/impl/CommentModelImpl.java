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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.CommentModel;
import eu.strasbourg.service.comment.model.CommentSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Comment service. Represents a row in the &quot;comment_Comment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommentImpl}.
 * </p>
 *
 * @author Romain Vergnais
 * @see CommentImpl
 * @see Comment
 * @see CommentModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommentModelImpl extends BaseModelImpl<Comment>
	implements CommentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a comment model instance should use the {@link Comment} interface instead.
	 */
	public static final String TABLE_NAME = "comment_Comment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "commentId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP },
			{ "comment_", Types.CLOB },
			{ "assetEntryId", Types.BIGINT },
			{ "publikId", Types.VARCHAR },
			{ "urlProjectCommentaire", Types.VARCHAR },
			{ "like_", Types.BIGINT },
			{ "dislike", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("comment_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlProjectCommentaire", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("like_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dislike", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table comment_Comment (uuid_ VARCHAR(75) null,commentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,comment_ TEXT null,assetEntryId LONG,publikId VARCHAR(75) null,urlProjectCommentaire STRING null,like_ LONG,dislike LONG)";
	public static final String TABLE_SQL_DROP = "drop table comment_Comment";
	public static final String ORDER_BY_JPQL = " ORDER BY comment.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY comment_Comment.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.comment.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.comment.model.Comment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.comment.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.comment.model.Comment"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.comment.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.comment.model.Comment"),
			true);
	public static final long ASSETENTRYID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long STATUS_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Comment toModel(CommentSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Comment model = new CommentImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommentId(soapModel.getCommentId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setComment(soapModel.getComment());
		model.setAssetEntryId(soapModel.getAssetEntryId());
		model.setPublikId(soapModel.getPublikId());
		model.setUrlProjectCommentaire(soapModel.getUrlProjectCommentaire());
		model.setLike(soapModel.getLike());
		model.setDislike(soapModel.getDislike());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Comment> toModels(CommentSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Comment> models = new ArrayList<Comment>(soapModels.length);

		for (CommentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.comment.service.util.ServiceProps.get(
				"lock.expiration.time.eu.strasbourg.service.comment.model.Comment"));

	public CommentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("publikId", getPublikId());
		attributes.put("urlProjectCommentaire", getUrlProjectCommentaire());
		attributes.put("like", getLike());
		attributes.put("dislike", getDislike());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		String urlProjectCommentaire = (String)attributes.get(
				"urlProjectCommentaire");

		if (urlProjectCommentaire != null) {
			setUrlProjectCommentaire(urlProjectCommentaire);
		}

		Long like = (Long)attributes.get("like");

		if (like != null) {
			setLike(like);
		}

		Long dislike = (Long)attributes.get("dislike");

		if (dislike != null) {
			setDislike(dislike);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getCommentId() {
		return _commentId;
	}

	@Override
	public void setCommentId(long commentId) {
		_commentId = commentId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getComment() {
		if (_comment == null) {
			return StringPool.BLANK;
		}
		else {
			return _comment;
		}
	}

	@Override
	public void setComment(String comment) {
		_comment = comment;
	}

	@JSON
	@Override
	public long getAssetEntryId() {
		return _assetEntryId;
	}

	@Override
	public void setAssetEntryId(long assetEntryId) {
		_columnBitmask |= ASSETENTRYID_COLUMN_BITMASK;

		if (!_setOriginalAssetEntryId) {
			_setOriginalAssetEntryId = true;

			_originalAssetEntryId = _assetEntryId;
		}

		_assetEntryId = assetEntryId;
	}

	public long getOriginalAssetEntryId() {
		return _originalAssetEntryId;
	}

	@JSON
	@Override
	public String getPublikId() {
		if (_publikId == null) {
			return StringPool.BLANK;
		}
		else {
			return _publikId;
		}
	}

	@Override
	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	@JSON
	@Override
	public String getUrlProjectCommentaire() {
		if (_urlProjectCommentaire == null) {
			return StringPool.BLANK;
		}
		else {
			return _urlProjectCommentaire;
		}
	}

	@Override
	public void setUrlProjectCommentaire(String urlProjectCommentaire) {
		_urlProjectCommentaire = urlProjectCommentaire;
	}

	@JSON
	@Override
	public long getLike() {
		return _like;
	}

	@Override
	public void setLike(long like) {
		_like = like;
	}

	@JSON
	@Override
	public long getDislike() {
		return _dislike;
	}

	@Override
	public void setDislike(long dislike) {
		_dislike = dislike;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Comment.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Comment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Comment toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Comment)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommentImpl commentImpl = new CommentImpl();

		commentImpl.setUuid(getUuid());
		commentImpl.setCommentId(getCommentId());
		commentImpl.setGroupId(getGroupId());
		commentImpl.setCompanyId(getCompanyId());
		commentImpl.setUserId(getUserId());
		commentImpl.setUserName(getUserName());
		commentImpl.setCreateDate(getCreateDate());
		commentImpl.setModifiedDate(getModifiedDate());
		commentImpl.setStatus(getStatus());
		commentImpl.setStatusByUserId(getStatusByUserId());
		commentImpl.setStatusByUserName(getStatusByUserName());
		commentImpl.setStatusDate(getStatusDate());
		commentImpl.setComment(getComment());
		commentImpl.setAssetEntryId(getAssetEntryId());
		commentImpl.setPublikId(getPublikId());
		commentImpl.setUrlProjectCommentaire(getUrlProjectCommentaire());
		commentImpl.setLike(getLike());
		commentImpl.setDislike(getDislike());

		commentImpl.resetOriginalValues();

		return commentImpl;
	}

	@Override
	public int compareTo(Comment comment) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), comment.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Comment)) {
			return false;
		}

		Comment comment = (Comment)obj;

		long primaryKey = comment.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommentModelImpl commentModelImpl = this;

		commentModelImpl._originalUuid = commentModelImpl._uuid;

		commentModelImpl._originalGroupId = commentModelImpl._groupId;

		commentModelImpl._setOriginalGroupId = false;

		commentModelImpl._originalCompanyId = commentModelImpl._companyId;

		commentModelImpl._setOriginalCompanyId = false;

		commentModelImpl._setModifiedDate = false;

		commentModelImpl._originalStatus = commentModelImpl._status;

		commentModelImpl._setOriginalStatus = false;

		commentModelImpl._originalAssetEntryId = commentModelImpl._assetEntryId;

		commentModelImpl._setOriginalAssetEntryId = false;

		commentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Comment> toCacheModel() {
		CommentCacheModel commentCacheModel = new CommentCacheModel();

		commentCacheModel.uuid = getUuid();

		String uuid = commentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commentCacheModel.uuid = null;
		}

		commentCacheModel.commentId = getCommentId();

		commentCacheModel.groupId = getGroupId();

		commentCacheModel.companyId = getCompanyId();

		commentCacheModel.userId = getUserId();

		commentCacheModel.userName = getUserName();

		String userName = commentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commentCacheModel.createDate = createDate.getTime();
		}
		else {
			commentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commentCacheModel.status = getStatus();

		commentCacheModel.statusByUserId = getStatusByUserId();

		commentCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = commentCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			commentCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			commentCacheModel.statusDate = statusDate.getTime();
		}
		else {
			commentCacheModel.statusDate = Long.MIN_VALUE;
		}

		commentCacheModel.comment = getComment();

		String comment = commentCacheModel.comment;

		if ((comment != null) && (comment.length() == 0)) {
			commentCacheModel.comment = null;
		}

		commentCacheModel.assetEntryId = getAssetEntryId();

		commentCacheModel.publikId = getPublikId();

		String publikId = commentCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			commentCacheModel.publikId = null;
		}

		commentCacheModel.urlProjectCommentaire = getUrlProjectCommentaire();

		String urlProjectCommentaire = commentCacheModel.urlProjectCommentaire;

		if ((urlProjectCommentaire != null) &&
				(urlProjectCommentaire.length() == 0)) {
			commentCacheModel.urlProjectCommentaire = null;
		}

		commentCacheModel.like = getLike();

		commentCacheModel.dislike = getDislike();

		return commentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", commentId=");
		sb.append(getCommentId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append(", publikId=");
		sb.append(getPublikId());
		sb.append(", urlProjectCommentaire=");
		sb.append(getUrlProjectCommentaire());
		sb.append(", like=");
		sb.append(getLike());
		sb.append(", dislike=");
		sb.append(getDislike());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.comment.model.Comment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commentId</column-name><column-value><![CDATA[");
		sb.append(getCommentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publikId</column-name><column-value><![CDATA[");
		sb.append(getPublikId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>urlProjectCommentaire</column-name><column-value><![CDATA[");
		sb.append(getUrlProjectCommentaire());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>like</column-name><column-value><![CDATA[");
		sb.append(getLike());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dislike</column-name><column-value><![CDATA[");
		sb.append(getDislike());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Comment.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Comment.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _commentId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _comment;
	private long _assetEntryId;
	private long _originalAssetEntryId;
	private boolean _setOriginalAssetEntryId;
	private String _publikId;
	private String _urlProjectCommentaire;
	private long _like;
	private long _dislike;
	private long _columnBitmask;
	private Comment _escapedModel;
}
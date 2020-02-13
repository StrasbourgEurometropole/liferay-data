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
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.CommentModel;
import eu.strasbourg.service.comment.model.CommentSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Comment service. Represents a row in the &quot;comment_Comment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommentImpl}.
 * </p>
 *
 * @author Romain Vergnais
 * @see CommentImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommentModelImpl
	extends BaseModelImpl<Comment> implements CommentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a comment model instance should use the <code>Comment</code> interface instead.
	 */
	public static final String TABLE_NAME = "comment_Comment";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"commentId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"text_", Types.VARCHAR}, {"level", Types.INTEGER},
		{"userQuality", Types.VARCHAR}, {"modifiedByUserDate", Types.TIMESTAMP},
		{"assetEntryId", Types.BIGINT}, {"publikId", Types.VARCHAR},
		{"parentCommentId", Types.BIGINT},
		{"urlProjectCommentaire", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

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
		TABLE_COLUMNS_MAP.put("text_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("level", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("userQuality", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modifiedByUserDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("parentCommentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("urlProjectCommentaire", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table comment_Comment (uuid_ VARCHAR(75) null,commentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,text_ VARCHAR(75) null,level INTEGER,userQuality VARCHAR(75) null,modifiedByUserDate DATE null,assetEntryId LONG,publikId VARCHAR(75) null,parentCommentId LONG,urlProjectCommentaire STRING null)";

	public static final String TABLE_SQL_DROP = "drop table comment_Comment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY comment.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY comment_Comment.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.comment.model.Comment"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.comment.model.Comment"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.comment.model.Comment"),
		true);

	public static final long ASSETENTRYID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long LEVEL_COLUMN_BITMASK = 8L;

	public static final long PARENTCOMMENTID_COLUMN_BITMASK = 16L;

	public static final long PUBLIKID_COLUMN_BITMASK = 32L;

	public static final long STATUS_COLUMN_BITMASK = 64L;

	public static final long UUID_COLUMN_BITMASK = 128L;

	public static final long CREATEDATE_COLUMN_BITMASK = 256L;

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
		model.setText(soapModel.getText());
		model.setLevel(soapModel.getLevel());
		model.setUserQuality(soapModel.getUserQuality());
		model.setModifiedByUserDate(soapModel.getModifiedByUserDate());
		model.setAssetEntryId(soapModel.getAssetEntryId());
		model.setPublikId(soapModel.getPublikId());
		model.setParentCommentId(soapModel.getParentCommentId());
		model.setUrlProjectCommentaire(soapModel.getUrlProjectCommentaire());

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

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
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

		Map<String, Function<Comment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Comment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Comment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Comment)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Comment, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Comment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Comment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Comment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Comment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Comment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Comment.class.getClassLoader(), Comment.class, ModelWrapper.class);

		try {
			Constructor<Comment> constructor =
				(Constructor<Comment>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Comment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Comment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Comment, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Comment, Object>>();
		Map<String, BiConsumer<Comment, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Comment, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object uuid) {
					comment.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"commentId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getCommentId();
				}

			});
		attributeSetterBiConsumers.put(
			"commentId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object commentId) {
					comment.setCommentId((Long)commentId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object groupId) {
					comment.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object companyId) {
					comment.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object userId) {
					comment.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object userName) {
					comment.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object createDate) {
					comment.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object modifiedDate) {
					comment.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object status) {
					comment.setStatus((Integer)status);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object statusByUserId) {
					comment.setStatusByUserId((Long)statusByUserId);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object statusByUserName) {
					comment.setStatusByUserName((String)statusByUserName);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object statusDate) {
					comment.setStatusDate((Date)statusDate);
				}

			});
		attributeGetterFunctions.put(
			"text",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getText();
				}

			});
		attributeSetterBiConsumers.put(
			"text",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object text) {
					comment.setText((String)text);
				}

			});
		attributeGetterFunctions.put(
			"level",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getLevel();
				}

			});
		attributeSetterBiConsumers.put(
			"level",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object level) {
					comment.setLevel((Integer)level);
				}

			});
		attributeGetterFunctions.put(
			"userQuality",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getUserQuality();
				}

			});
		attributeSetterBiConsumers.put(
			"userQuality",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object userQuality) {
					comment.setUserQuality((String)userQuality);
				}

			});
		attributeGetterFunctions.put(
			"modifiedByUserDate",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getModifiedByUserDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedByUserDate",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object modifiedByUserDate) {
					comment.setModifiedByUserDate((Date)modifiedByUserDate);
				}

			});
		attributeGetterFunctions.put(
			"assetEntryId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getAssetEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"assetEntryId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object assetEntryId) {
					comment.setAssetEntryId((Long)assetEntryId);
				}

			});
		attributeGetterFunctions.put(
			"publikId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getPublikId();
				}

			});
		attributeSetterBiConsumers.put(
			"publikId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object publikId) {
					comment.setPublikId((String)publikId);
				}

			});
		attributeGetterFunctions.put(
			"parentCommentId",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getParentCommentId();
				}

			});
		attributeSetterBiConsumers.put(
			"parentCommentId",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(Comment comment, Object parentCommentId) {
					comment.setParentCommentId((Long)parentCommentId);
				}

			});
		attributeGetterFunctions.put(
			"urlProjectCommentaire",
			new Function<Comment, Object>() {

				@Override
				public Object apply(Comment comment) {
					return comment.getUrlProjectCommentaire();
				}

			});
		attributeSetterBiConsumers.put(
			"urlProjectCommentaire",
			new BiConsumer<Comment, Object>() {

				@Override
				public void accept(
					Comment comment, Object urlProjectCommentaire) {

					comment.setUrlProjectCommentaire(
						(String)urlProjectCommentaire);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

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
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
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
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
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
	public String getText() {
		if (_text == null) {
			return "";
		}
		else {
			return _text;
		}
	}

	@Override
	public void setText(String text) {
		_text = text;
	}

	@JSON
	@Override
	public int getLevel() {
		return _level;
	}

	@Override
	public void setLevel(int level) {
		_columnBitmask |= LEVEL_COLUMN_BITMASK;

		if (!_setOriginalLevel) {
			_setOriginalLevel = true;

			_originalLevel = _level;
		}

		_level = level;
	}

	public int getOriginalLevel() {
		return _originalLevel;
	}

	@JSON
	@Override
	public String getUserQuality() {
		if (_userQuality == null) {
			return "";
		}
		else {
			return _userQuality;
		}
	}

	@Override
	public void setUserQuality(String userQuality) {
		_userQuality = userQuality;
	}

	@JSON
	@Override
	public Date getModifiedByUserDate() {
		return _modifiedByUserDate;
	}

	@Override
	public void setModifiedByUserDate(Date modifiedByUserDate) {
		_modifiedByUserDate = modifiedByUserDate;
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
			return "";
		}
		else {
			return _publikId;
		}
	}

	@Override
	public void setPublikId(String publikId) {
		_columnBitmask |= PUBLIKID_COLUMN_BITMASK;

		if (_originalPublikId == null) {
			_originalPublikId = _publikId;
		}

		_publikId = publikId;
	}

	public String getOriginalPublikId() {
		return GetterUtil.getString(_originalPublikId);
	}

	@JSON
	@Override
	public long getParentCommentId() {
		return _parentCommentId;
	}

	@Override
	public void setParentCommentId(long parentCommentId) {
		_columnBitmask |= PARENTCOMMENTID_COLUMN_BITMASK;

		if (!_setOriginalParentCommentId) {
			_setOriginalParentCommentId = true;

			_originalParentCommentId = _parentCommentId;
		}

		_parentCommentId = parentCommentId;
	}

	public long getOriginalParentCommentId() {
		return _originalParentCommentId;
	}

	@JSON
	@Override
	public String getUrlProjectCommentaire() {
		if (_urlProjectCommentaire == null) {
			return "";
		}
		else {
			return _urlProjectCommentaire;
		}
	}

	@Override
	public void setUrlProjectCommentaire(String urlProjectCommentaire) {
		_urlProjectCommentaire = urlProjectCommentaire;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Comment.class.getName()));
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
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Comment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Comment toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
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
		commentImpl.setText(getText());
		commentImpl.setLevel(getLevel());
		commentImpl.setUserQuality(getUserQuality());
		commentImpl.setModifiedByUserDate(getModifiedByUserDate());
		commentImpl.setAssetEntryId(getAssetEntryId());
		commentImpl.setPublikId(getPublikId());
		commentImpl.setParentCommentId(getParentCommentId());
		commentImpl.setUrlProjectCommentaire(getUrlProjectCommentaire());

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

		commentModelImpl._originalLevel = commentModelImpl._level;

		commentModelImpl._setOriginalLevel = false;

		commentModelImpl._originalAssetEntryId = commentModelImpl._assetEntryId;

		commentModelImpl._setOriginalAssetEntryId = false;

		commentModelImpl._originalPublikId = commentModelImpl._publikId;

		commentModelImpl._originalParentCommentId =
			commentModelImpl._parentCommentId;

		commentModelImpl._setOriginalParentCommentId = false;

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

		commentCacheModel.text = getText();

		String text = commentCacheModel.text;

		if ((text != null) && (text.length() == 0)) {
			commentCacheModel.text = null;
		}

		commentCacheModel.level = getLevel();

		commentCacheModel.userQuality = getUserQuality();

		String userQuality = commentCacheModel.userQuality;

		if ((userQuality != null) && (userQuality.length() == 0)) {
			commentCacheModel.userQuality = null;
		}

		Date modifiedByUserDate = getModifiedByUserDate();

		if (modifiedByUserDate != null) {
			commentCacheModel.modifiedByUserDate = modifiedByUserDate.getTime();
		}
		else {
			commentCacheModel.modifiedByUserDate = Long.MIN_VALUE;
		}

		commentCacheModel.assetEntryId = getAssetEntryId();

		commentCacheModel.publikId = getPublikId();

		String publikId = commentCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			commentCacheModel.publikId = null;
		}

		commentCacheModel.parentCommentId = getParentCommentId();

		commentCacheModel.urlProjectCommentaire = getUrlProjectCommentaire();

		String urlProjectCommentaire = commentCacheModel.urlProjectCommentaire;

		if ((urlProjectCommentaire != null) &&
			(urlProjectCommentaire.length() == 0)) {

			commentCacheModel.urlProjectCommentaire = null;
		}

		return commentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Comment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Comment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Comment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Comment)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Comment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Comment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Comment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Comment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Comment>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

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
	private String _text;
	private int _level;
	private int _originalLevel;
	private boolean _setOriginalLevel;
	private String _userQuality;
	private Date _modifiedByUserDate;
	private long _assetEntryId;
	private long _originalAssetEntryId;
	private boolean _setOriginalAssetEntryId;
	private String _publikId;
	private String _originalPublikId;
	private long _parentCommentId;
	private long _originalParentCommentId;
	private boolean _setOriginalParentCommentId;
	private String _urlProjectCommentaire;
	private long _columnBitmask;
	private Comment _escapedModel;

}
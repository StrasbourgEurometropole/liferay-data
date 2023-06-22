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

import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.model.SignalementModel;
import eu.strasbourg.service.comment.model.SignalementSoap;

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
 * The base model implementation for the Signalement service. Represents a row in the &quot;comment_Signalement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SignalementModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SignalementImpl}.
 * </p>
 *
 * @author Romain Vergnais
 * @see SignalementImpl
 * @generated
 */
@JSON(strict = true)
public class SignalementModelImpl
	extends BaseModelImpl<Signalement> implements SignalementModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a signalement model instance should use the <code>Signalement</code> interface instead.
	 */
	public static final String TABLE_NAME = "comment_Signalement";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"signalementId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"commentId", Types.BIGINT}, {"publikId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signalementId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("commentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table comment_Signalement (uuid_ VARCHAR(75) null,signalementId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,commentId LONG,publikId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table comment_Signalement";

	public static final String ORDER_BY_JPQL =
		" ORDER BY signalement.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY comment_Signalement.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.comment.model.Signalement"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.comment.model.Signalement"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.comment.model.Signalement"),
		true);

	public static final long COMMENTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long PUBLIKID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long CREATEDATE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Signalement toModel(SignalementSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Signalement model = new SignalementImpl();

		model.setUuid(soapModel.getUuid());
		model.setSignalementId(soapModel.getSignalementId());
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
		model.setCommentId(soapModel.getCommentId());
		model.setPublikId(soapModel.getPublikId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Signalement> toModels(SignalementSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Signalement> models = new ArrayList<Signalement>(
			soapModels.length);

		for (SignalementSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.comment.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.comment.model.Signalement"));

	public SignalementModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _signalementId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSignalementId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _signalementId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Signalement.class;
	}

	@Override
	public String getModelClassName() {
		return Signalement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Signalement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Signalement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Signalement, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Signalement)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Signalement, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Signalement, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Signalement)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Signalement, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Signalement, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Signalement>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Signalement.class.getClassLoader(), Signalement.class,
			ModelWrapper.class);

		try {
			Constructor<Signalement> constructor =
				(Constructor<Signalement>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Signalement, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Signalement, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Signalement, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Signalement, Object>>();
		Map<String, BiConsumer<Signalement, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Signalement, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(Signalement signalement, Object uuidObject) {
					signalement.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"signalementId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getSignalementId();
				}

			});
		attributeSetterBiConsumers.put(
			"signalementId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object signalementIdObject) {

					signalement.setSignalementId((Long)signalementIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object groupIdObject) {

					signalement.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object companyIdObject) {

					signalement.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object userIdObject) {

					signalement.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object userNameObject) {

					signalement.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object createDateObject) {

					signalement.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object modifiedDateObject) {

					signalement.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object statusObject) {

					signalement.setStatus((Integer)statusObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object statusByUserIdObject) {

					signalement.setStatusByUserId((Long)statusByUserIdObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object statusByUserNameObject) {

					signalement.setStatusByUserName(
						(String)statusByUserNameObject);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object statusDateObject) {

					signalement.setStatusDate((Date)statusDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commentId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getCommentId();
				}

			});
		attributeSetterBiConsumers.put(
			"commentId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object commentIdObject) {

					signalement.setCommentId((Long)commentIdObject);
				}

			});
		attributeGetterFunctions.put(
			"publikId",
			new Function<Signalement, Object>() {

				@Override
				public Object apply(Signalement signalement) {
					return signalement.getPublikId();
				}

			});
		attributeSetterBiConsumers.put(
			"publikId",
			new BiConsumer<Signalement, Object>() {

				@Override
				public void accept(
					Signalement signalement, Object publikIdObject) {

					signalement.setPublikId((String)publikIdObject);
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
	public long getSignalementId() {
		return _signalementId;
	}

	@Override
	public void setSignalementId(long signalementId) {
		_signalementId = signalementId;
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
		catch (PortalException portalException) {
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
		_status = status;
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
		catch (PortalException portalException) {
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
	public long getCommentId() {
		return _commentId;
	}

	@Override
	public void setCommentId(long commentId) {
		_columnBitmask |= COMMENTID_COLUMN_BITMASK;

		if (!_setOriginalCommentId) {
			_setOriginalCommentId = true;

			_originalCommentId = _commentId;
		}

		_commentId = commentId;
	}

	public long getOriginalCommentId() {
		return _originalCommentId;
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Signalement.class.getName()));
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
			getCompanyId(), Signalement.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Signalement toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Signalement>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SignalementImpl signalementImpl = new SignalementImpl();

		signalementImpl.setUuid(getUuid());
		signalementImpl.setSignalementId(getSignalementId());
		signalementImpl.setGroupId(getGroupId());
		signalementImpl.setCompanyId(getCompanyId());
		signalementImpl.setUserId(getUserId());
		signalementImpl.setUserName(getUserName());
		signalementImpl.setCreateDate(getCreateDate());
		signalementImpl.setModifiedDate(getModifiedDate());
		signalementImpl.setStatus(getStatus());
		signalementImpl.setStatusByUserId(getStatusByUserId());
		signalementImpl.setStatusByUserName(getStatusByUserName());
		signalementImpl.setStatusDate(getStatusDate());
		signalementImpl.setCommentId(getCommentId());
		signalementImpl.setPublikId(getPublikId());

		signalementImpl.resetOriginalValues();

		return signalementImpl;
	}

	@Override
	public int compareTo(Signalement signalement) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), signalement.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Signalement)) {
			return false;
		}

		Signalement signalement = (Signalement)object;

		long primaryKey = signalement.getPrimaryKey();

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
		SignalementModelImpl signalementModelImpl = this;

		signalementModelImpl._originalUuid = signalementModelImpl._uuid;

		signalementModelImpl._originalGroupId = signalementModelImpl._groupId;

		signalementModelImpl._setOriginalGroupId = false;

		signalementModelImpl._originalCompanyId =
			signalementModelImpl._companyId;

		signalementModelImpl._setOriginalCompanyId = false;

		signalementModelImpl._setModifiedDate = false;

		signalementModelImpl._originalCommentId =
			signalementModelImpl._commentId;

		signalementModelImpl._setOriginalCommentId = false;

		signalementModelImpl._originalPublikId = signalementModelImpl._publikId;

		signalementModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Signalement> toCacheModel() {
		SignalementCacheModel signalementCacheModel =
			new SignalementCacheModel();

		signalementCacheModel.uuid = getUuid();

		String uuid = signalementCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			signalementCacheModel.uuid = null;
		}

		signalementCacheModel.signalementId = getSignalementId();

		signalementCacheModel.groupId = getGroupId();

		signalementCacheModel.companyId = getCompanyId();

		signalementCacheModel.userId = getUserId();

		signalementCacheModel.userName = getUserName();

		String userName = signalementCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			signalementCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			signalementCacheModel.createDate = createDate.getTime();
		}
		else {
			signalementCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			signalementCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			signalementCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		signalementCacheModel.status = getStatus();

		signalementCacheModel.statusByUserId = getStatusByUserId();

		signalementCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = signalementCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			signalementCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			signalementCacheModel.statusDate = statusDate.getTime();
		}
		else {
			signalementCacheModel.statusDate = Long.MIN_VALUE;
		}

		signalementCacheModel.commentId = getCommentId();

		signalementCacheModel.publikId = getPublikId();

		String publikId = signalementCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			signalementCacheModel.publikId = null;
		}

		return signalementCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Signalement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Signalement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Signalement, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Signalement)this));
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
		Map<String, Function<Signalement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Signalement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Signalement, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Signalement)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Signalement>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _signalementId;
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
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _commentId;
	private long _originalCommentId;
	private boolean _setOriginalCommentId;
	private String _publikId;
	private String _originalPublikId;
	private long _columnBitmask;
	private Signalement _escapedModel;

}
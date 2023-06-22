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

package eu.strasbourg.service.council.model.impl;

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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.model.TypeModel;
import eu.strasbourg.service.council.model.TypeSoap;

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
 * The base model implementation for the Type service. Represents a row in the &quot;council_Type&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>TypeModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TypeImpl
 * @generated
 */
@JSON(strict = true)
public class TypeModelImpl extends BaseModelImpl<Type> implements TypeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a type model instance should use the <code>Type</code> interface instead.
	 */
	public static final String TABLE_NAME = "council_Type";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"typeId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"title", Types.VARCHAR}, {"titleLong", Types.VARCHAR},
		{"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("titleLong", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table council_Type (uuid_ VARCHAR(75) null,typeId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title VARCHAR(75) null,titleLong VARCHAR(75) null,roleId LONG)";

	public static final String TABLE_SQL_DROP = "drop table council_Type";

	public static final String ORDER_BY_JPQL = " ORDER BY type.title ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY council_Type.title ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.council.model.Type"),
		false);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.council.model.Type"),
		false);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.council.model.Type"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long ROLEID_COLUMN_BITMASK = 4L;

	public static final long TITLE_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Type toModel(TypeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Type model = new TypeImpl();

		model.setUuid(soapModel.getUuid());
		model.setTypeId(soapModel.getTypeId());
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
		model.setTitle(soapModel.getTitle());
		model.setTitleLong(soapModel.getTitleLong());
		model.setRoleId(soapModel.getRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Type> toModels(TypeSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Type> models = new ArrayList<Type>(soapModels.length);

		for (TypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.council.model.Type"));

	public TypeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _typeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTypeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _typeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Type.class;
	}

	@Override
	public String getModelClassName() {
		return Type.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Type, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Type, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Type, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Type)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Type, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Type, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Type)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Type, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Type, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Type>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Type.class.getClassLoader(), Type.class, ModelWrapper.class);

		try {
			Constructor<Type> constructor =
				(Constructor<Type>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Type, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Type, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Type, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Type, Object>>();
		Map<String, BiConsumer<Type, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Type, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object uuidObject) {
					type.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"typeId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getTypeId();
				}

			});
		attributeSetterBiConsumers.put(
			"typeId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object typeIdObject) {
					type.setTypeId((Long)typeIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object groupIdObject) {
					type.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object companyIdObject) {
					type.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object userIdObject) {
					type.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object userNameObject) {
					type.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object createDateObject) {
					type.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object modifiedDateObject) {
					type.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object statusObject) {
					type.setStatus((Integer)statusObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object statusByUserIdObject) {
					type.setStatusByUserId((Long)statusByUserIdObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object statusByUserNameObject) {
					type.setStatusByUserName((String)statusByUserNameObject);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object statusDateObject) {
					type.setStatusDate((Date)statusDateObject);
				}

			});
		attributeGetterFunctions.put(
			"title",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getTitle();
				}

			});
		attributeSetterBiConsumers.put(
			"title",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object titleObject) {
					type.setTitle((String)titleObject);
				}

			});
		attributeGetterFunctions.put(
			"titleLong",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getTitleLong();
				}

			});
		attributeSetterBiConsumers.put(
			"titleLong",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object titleLongObject) {
					type.setTitleLong((String)titleLongObject);
				}

			});
		attributeGetterFunctions.put(
			"roleId",
			new Function<Type, Object>() {

				@Override
				public Object apply(Type type) {
					return type.getRoleId();
				}

			});
		attributeSetterBiConsumers.put(
			"roleId",
			new BiConsumer<Type, Object>() {

				@Override
				public void accept(Type type, Object roleIdObject) {
					type.setRoleId((Long)roleIdObject);
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
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		_typeId = typeId;
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
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_columnBitmask = -1L;

		if (_originalTitle == null) {
			_originalTitle = _title;
		}

		_title = title;
	}

	public String getOriginalTitle() {
		return GetterUtil.getString(_originalTitle);
	}

	@JSON
	@Override
	public String getTitleLong() {
		if (_titleLong == null) {
			return "";
		}
		else {
			return _titleLong;
		}
	}

	@Override
	public void setTitleLong(String titleLong) {
		_titleLong = titleLong;
	}

	@JSON
	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Type.class.getName()));
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
			getCompanyId(), Type.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Type toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Type>
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
		TypeImpl typeImpl = new TypeImpl();

		typeImpl.setUuid(getUuid());
		typeImpl.setTypeId(getTypeId());
		typeImpl.setGroupId(getGroupId());
		typeImpl.setCompanyId(getCompanyId());
		typeImpl.setUserId(getUserId());
		typeImpl.setUserName(getUserName());
		typeImpl.setCreateDate(getCreateDate());
		typeImpl.setModifiedDate(getModifiedDate());
		typeImpl.setStatus(getStatus());
		typeImpl.setStatusByUserId(getStatusByUserId());
		typeImpl.setStatusByUserName(getStatusByUserName());
		typeImpl.setStatusDate(getStatusDate());
		typeImpl.setTitle(getTitle());
		typeImpl.setTitleLong(getTitleLong());
		typeImpl.setRoleId(getRoleId());

		typeImpl.resetOriginalValues();

		return typeImpl;
	}

	@Override
	public int compareTo(Type type) {
		int value = 0;

		value = getTitle().compareToIgnoreCase(type.getTitle());

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

		if (!(object instanceof Type)) {
			return false;
		}

		Type type = (Type)object;

		long primaryKey = type.getPrimaryKey();

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
		TypeModelImpl typeModelImpl = this;

		typeModelImpl._originalUuid = typeModelImpl._uuid;

		typeModelImpl._originalGroupId = typeModelImpl._groupId;

		typeModelImpl._setOriginalGroupId = false;

		typeModelImpl._originalCompanyId = typeModelImpl._companyId;

		typeModelImpl._setOriginalCompanyId = false;

		typeModelImpl._setModifiedDate = false;

		typeModelImpl._originalTitle = typeModelImpl._title;

		typeModelImpl._originalRoleId = typeModelImpl._roleId;

		typeModelImpl._setOriginalRoleId = false;

		typeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Type> toCacheModel() {
		TypeCacheModel typeCacheModel = new TypeCacheModel();

		typeCacheModel.uuid = getUuid();

		String uuid = typeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			typeCacheModel.uuid = null;
		}

		typeCacheModel.typeId = getTypeId();

		typeCacheModel.groupId = getGroupId();

		typeCacheModel.companyId = getCompanyId();

		typeCacheModel.userId = getUserId();

		typeCacheModel.userName = getUserName();

		String userName = typeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			typeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			typeCacheModel.createDate = createDate.getTime();
		}
		else {
			typeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			typeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			typeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		typeCacheModel.status = getStatus();

		typeCacheModel.statusByUserId = getStatusByUserId();

		typeCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = typeCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			typeCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			typeCacheModel.statusDate = statusDate.getTime();
		}
		else {
			typeCacheModel.statusDate = Long.MIN_VALUE;
		}

		typeCacheModel.title = getTitle();

		String title = typeCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			typeCacheModel.title = null;
		}

		typeCacheModel.titleLong = getTitleLong();

		String titleLong = typeCacheModel.titleLong;

		if ((titleLong != null) && (titleLong.length() == 0)) {
			typeCacheModel.titleLong = null;
		}

		typeCacheModel.roleId = getRoleId();

		return typeCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Type, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Type, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Type, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Type)this));
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
		Map<String, Function<Type, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Type, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Type, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Type)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Type>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _typeId;
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
	private String _title;
	private String _originalTitle;
	private String _titleLong;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _columnBitmask;
	private Type _escapedModel;

}
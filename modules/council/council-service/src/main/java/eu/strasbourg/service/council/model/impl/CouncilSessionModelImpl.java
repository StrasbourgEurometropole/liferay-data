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

import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.CouncilSessionModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CouncilSession service. Represents a row in the &quot;council_CouncilSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CouncilSessionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CouncilSessionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CouncilSessionImpl
 * @generated
 */
public class CouncilSessionModelImpl
	extends BaseModelImpl<CouncilSession> implements CouncilSessionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a council session model instance should use the <code>CouncilSession</code> interface instead.
	 */
	public static final String TABLE_NAME = "council_CouncilSession";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"councilSessionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"title", Types.VARCHAR}, {"date_", Types.TIMESTAMP},
		{"lastDelibProcessed", Types.BIGINT},
		{"officialLeaderId", Types.BIGINT}, {"typeId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("councilSessionId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("date_", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastDelibProcessed", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("officialLeaderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("typeId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table council_CouncilSession (uuid_ VARCHAR(75) null,councilSessionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title VARCHAR(75) null,date_ DATE null,lastDelibProcessed LONG,officialLeaderId LONG,typeId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table council_CouncilSession";

	public static final String ORDER_BY_JPQL =
		" ORDER BY councilSession.title ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY council_CouncilSession.title ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.council.model.CouncilSession"),
		false);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.council.model.CouncilSession"),
		false);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.council.model.CouncilSession"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long DATE_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long TITLE_COLUMN_BITMASK = 8L;

	public static final long TYPEID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.council.model.CouncilSession"));

	public CouncilSessionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _councilSessionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCouncilSessionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _councilSessionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CouncilSession.class;
	}

	@Override
	public String getModelClassName() {
		return CouncilSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CouncilSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CouncilSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CouncilSession, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CouncilSession)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CouncilSession, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CouncilSession, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CouncilSession)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CouncilSession, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CouncilSession, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CouncilSession>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CouncilSession.class.getClassLoader(), CouncilSession.class,
			ModelWrapper.class);

		try {
			Constructor<CouncilSession> constructor =
				(Constructor<CouncilSession>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CouncilSession, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CouncilSession, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CouncilSession, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CouncilSession, Object>>();
		Map<String, BiConsumer<CouncilSession, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CouncilSession, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object uuidObject) {

					councilSession.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"councilSessionId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getCouncilSessionId();
				}

			});
		attributeSetterBiConsumers.put(
			"councilSessionId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession,
					Object councilSessionIdObject) {

					councilSession.setCouncilSessionId(
						(Long)councilSessionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object groupIdObject) {

					councilSession.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object companyIdObject) {

					councilSession.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object userIdObject) {

					councilSession.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object userNameObject) {

					councilSession.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object createDateObject) {

					councilSession.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object modifiedDateObject) {

					councilSession.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object statusObject) {

					councilSession.setStatus((Integer)statusObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession,
					Object statusByUserIdObject) {

					councilSession.setStatusByUserId(
						(Long)statusByUserIdObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession,
					Object statusByUserNameObject) {

					councilSession.setStatusByUserName(
						(String)statusByUserNameObject);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object statusDateObject) {

					councilSession.setStatusDate((Date)statusDateObject);
				}

			});
		attributeGetterFunctions.put(
			"title",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getTitle();
				}

			});
		attributeSetterBiConsumers.put(
			"title",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object titleObject) {

					councilSession.setTitle((String)titleObject);
				}

			});
		attributeGetterFunctions.put(
			"date",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getDate();
				}

			});
		attributeSetterBiConsumers.put(
			"date",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object dateObject) {

					councilSession.setDate((Date)dateObject);
				}

			});
		attributeGetterFunctions.put(
			"lastDelibProcessed",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getLastDelibProcessed();
				}

			});
		attributeSetterBiConsumers.put(
			"lastDelibProcessed",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession,
					Object lastDelibProcessedObject) {

					councilSession.setLastDelibProcessed(
						(Long)lastDelibProcessedObject);
				}

			});
		attributeGetterFunctions.put(
			"officialLeaderId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getOfficialLeaderId();
				}

			});
		attributeSetterBiConsumers.put(
			"officialLeaderId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession,
					Object officialLeaderIdObject) {

					councilSession.setOfficialLeaderId(
						(Long)officialLeaderIdObject);
				}

			});
		attributeGetterFunctions.put(
			"typeId",
			new Function<CouncilSession, Object>() {

				@Override
				public Object apply(CouncilSession councilSession) {
					return councilSession.getTypeId();
				}

			});
		attributeSetterBiConsumers.put(
			"typeId",
			new BiConsumer<CouncilSession, Object>() {

				@Override
				public void accept(
					CouncilSession councilSession, Object typeIdObject) {

					councilSession.setTypeId((Long)typeIdObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getCouncilSessionId() {
		return _councilSessionId;
	}

	@Override
	public void setCouncilSessionId(long councilSessionId) {
		_councilSessionId = councilSessionId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

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

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

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

	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public void setDate(Date date) {
		_columnBitmask |= DATE_COLUMN_BITMASK;

		if (_originalDate == null) {
			_originalDate = _date;
		}

		_date = date;
	}

	public Date getOriginalDate() {
		return _originalDate;
	}

	@Override
	public long getLastDelibProcessed() {
		return _lastDelibProcessed;
	}

	@Override
	public void setLastDelibProcessed(long lastDelibProcessed) {
		_lastDelibProcessed = lastDelibProcessed;
	}

	@Override
	public long getOfficialLeaderId() {
		return _officialLeaderId;
	}

	@Override
	public void setOfficialLeaderId(long officialLeaderId) {
		_officialLeaderId = officialLeaderId;
	}

	@Override
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		_columnBitmask |= TYPEID_COLUMN_BITMASK;

		if (!_setOriginalTypeId) {
			_setOriginalTypeId = true;

			_originalTypeId = _typeId;
		}

		_typeId = typeId;
	}

	public long getOriginalTypeId() {
		return _originalTypeId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CouncilSession.class.getName()));
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
			getCompanyId(), CouncilSession.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CouncilSession toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CouncilSession>
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
		CouncilSessionImpl councilSessionImpl = new CouncilSessionImpl();

		councilSessionImpl.setUuid(getUuid());
		councilSessionImpl.setCouncilSessionId(getCouncilSessionId());
		councilSessionImpl.setGroupId(getGroupId());
		councilSessionImpl.setCompanyId(getCompanyId());
		councilSessionImpl.setUserId(getUserId());
		councilSessionImpl.setUserName(getUserName());
		councilSessionImpl.setCreateDate(getCreateDate());
		councilSessionImpl.setModifiedDate(getModifiedDate());
		councilSessionImpl.setStatus(getStatus());
		councilSessionImpl.setStatusByUserId(getStatusByUserId());
		councilSessionImpl.setStatusByUserName(getStatusByUserName());
		councilSessionImpl.setStatusDate(getStatusDate());
		councilSessionImpl.setTitle(getTitle());
		councilSessionImpl.setDate(getDate());
		councilSessionImpl.setLastDelibProcessed(getLastDelibProcessed());
		councilSessionImpl.setOfficialLeaderId(getOfficialLeaderId());
		councilSessionImpl.setTypeId(getTypeId());

		councilSessionImpl.resetOriginalValues();

		return councilSessionImpl;
	}

	@Override
	public int compareTo(CouncilSession councilSession) {
		int value = 0;

		value = getTitle().compareTo(councilSession.getTitle());

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

		if (!(object instanceof CouncilSession)) {
			return false;
		}

		CouncilSession councilSession = (CouncilSession)object;

		long primaryKey = councilSession.getPrimaryKey();

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
		CouncilSessionModelImpl councilSessionModelImpl = this;

		councilSessionModelImpl._originalUuid = councilSessionModelImpl._uuid;

		councilSessionModelImpl._originalGroupId =
			councilSessionModelImpl._groupId;

		councilSessionModelImpl._setOriginalGroupId = false;

		councilSessionModelImpl._originalCompanyId =
			councilSessionModelImpl._companyId;

		councilSessionModelImpl._setOriginalCompanyId = false;

		councilSessionModelImpl._setModifiedDate = false;

		councilSessionModelImpl._originalTitle = councilSessionModelImpl._title;

		councilSessionModelImpl._originalDate = councilSessionModelImpl._date;

		councilSessionModelImpl._originalTypeId =
			councilSessionModelImpl._typeId;

		councilSessionModelImpl._setOriginalTypeId = false;

		councilSessionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CouncilSession> toCacheModel() {
		CouncilSessionCacheModel councilSessionCacheModel =
			new CouncilSessionCacheModel();

		councilSessionCacheModel.uuid = getUuid();

		String uuid = councilSessionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			councilSessionCacheModel.uuid = null;
		}

		councilSessionCacheModel.councilSessionId = getCouncilSessionId();

		councilSessionCacheModel.groupId = getGroupId();

		councilSessionCacheModel.companyId = getCompanyId();

		councilSessionCacheModel.userId = getUserId();

		councilSessionCacheModel.userName = getUserName();

		String userName = councilSessionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			councilSessionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			councilSessionCacheModel.createDate = createDate.getTime();
		}
		else {
			councilSessionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			councilSessionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			councilSessionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		councilSessionCacheModel.status = getStatus();

		councilSessionCacheModel.statusByUserId = getStatusByUserId();

		councilSessionCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = councilSessionCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			councilSessionCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			councilSessionCacheModel.statusDate = statusDate.getTime();
		}
		else {
			councilSessionCacheModel.statusDate = Long.MIN_VALUE;
		}

		councilSessionCacheModel.title = getTitle();

		String title = councilSessionCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			councilSessionCacheModel.title = null;
		}

		Date date = getDate();

		if (date != null) {
			councilSessionCacheModel.date = date.getTime();
		}
		else {
			councilSessionCacheModel.date = Long.MIN_VALUE;
		}

		councilSessionCacheModel.lastDelibProcessed = getLastDelibProcessed();

		councilSessionCacheModel.officialLeaderId = getOfficialLeaderId();

		councilSessionCacheModel.typeId = getTypeId();

		return councilSessionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CouncilSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CouncilSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CouncilSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CouncilSession)this));
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
		Map<String, Function<CouncilSession, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CouncilSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CouncilSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CouncilSession)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CouncilSession>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _councilSessionId;
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
	private Date _date;
	private Date _originalDate;
	private long _lastDelibProcessed;
	private long _officialLeaderId;
	private long _typeId;
	private long _originalTypeId;
	private boolean _setOriginalTypeId;
	private long _columnBitmask;
	private CouncilSession _escapedModel;

}
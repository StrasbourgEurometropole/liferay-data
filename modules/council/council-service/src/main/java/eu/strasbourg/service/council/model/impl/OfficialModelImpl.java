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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialModel;
import eu.strasbourg.service.council.model.OfficialSoap;

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
 * The base model implementation for the Official service. Represents a row in the &quot;council_Official&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>OfficialModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OfficialImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class OfficialModelImpl
	extends BaseModelImpl<Official> implements OfficialModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a official model instance should use the <code>Official</code> interface instead.
	 */
	public static final String TABLE_NAME = "council_Official";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"officialId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"email", Types.VARCHAR}, {"firstname", Types.VARCHAR},
		{"lastname", Types.VARCHAR}, {"isMunicipal", Types.BOOLEAN},
		{"isEurometropolitan", Types.BOOLEAN}, {"isActive", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("officialId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("firstname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("isMunicipal", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("isEurometropolitan", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("isActive", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table council_Official (uuid_ VARCHAR(75) null,officialId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,email VARCHAR(75) null,firstname VARCHAR(75) null,lastname VARCHAR(75) null,isMunicipal BOOLEAN,isEurometropolitan BOOLEAN,isActive BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table council_Official";

	public static final String ORDER_BY_JPQL =
		" ORDER BY official.lastname ASC, official.firstname ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY council_Official.lastname ASC, council_Official.firstname ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.council.model.Official"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.council.model.Official"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.council.model.Official"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EMAIL_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long ISACTIVE_COLUMN_BITMASK = 8L;

	public static final long ISEUROMETROPOLITAN_COLUMN_BITMASK = 16L;

	public static final long ISMUNICIPAL_COLUMN_BITMASK = 32L;

	public static final long UUID_COLUMN_BITMASK = 64L;

	public static final long LASTNAME_COLUMN_BITMASK = 128L;

	public static final long FIRSTNAME_COLUMN_BITMASK = 256L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Official toModel(OfficialSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Official model = new OfficialImpl();

		model.setUuid(soapModel.getUuid());
		model.setOfficialId(soapModel.getOfficialId());
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
		model.setEmail(soapModel.getEmail());
		model.setFirstname(soapModel.getFirstname());
		model.setLastname(soapModel.getLastname());
		model.setIsMunicipal(soapModel.isIsMunicipal());
		model.setIsEurometropolitan(soapModel.isIsEurometropolitan());
		model.setIsActive(soapModel.isIsActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Official> toModels(OfficialSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Official> models = new ArrayList<Official>(soapModels.length);

		for (OfficialSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.council.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.council.model.Official"));

	public OfficialModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _officialId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOfficialId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _officialId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Official.class;
	}

	@Override
	public String getModelClassName() {
		return Official.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Official, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Official, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Official, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Official)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Official, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Official, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Official)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Official, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Official, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Official>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Official.class.getClassLoader(), Official.class,
			ModelWrapper.class);

		try {
			Constructor<Official> constructor =
				(Constructor<Official>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Official, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Official, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Official, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Official, Object>>();
		Map<String, BiConsumer<Official, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Official, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object uuid) {
					official.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"officialId",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getOfficialId();
				}

			});
		attributeSetterBiConsumers.put(
			"officialId",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object officialId) {
					official.setOfficialId((Long)officialId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object groupId) {
					official.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object companyId) {
					official.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object userId) {
					official.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object userName) {
					official.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object createDate) {
					official.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object modifiedDate) {
					official.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object status) {
					official.setStatus((Integer)status);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object statusByUserId) {
					official.setStatusByUserId((Long)statusByUserId);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object statusByUserName) {
					official.setStatusByUserName((String)statusByUserName);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object statusDate) {
					official.setStatusDate((Date)statusDate);
				}

			});
		attributeGetterFunctions.put(
			"email",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getEmail();
				}

			});
		attributeSetterBiConsumers.put(
			"email",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object email) {
					official.setEmail((String)email);
				}

			});
		attributeGetterFunctions.put(
			"firstname",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getFirstname();
				}

			});
		attributeSetterBiConsumers.put(
			"firstname",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object firstname) {
					official.setFirstname((String)firstname);
				}

			});
		attributeGetterFunctions.put(
			"lastname",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getLastname();
				}

			});
		attributeSetterBiConsumers.put(
			"lastname",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object lastname) {
					official.setLastname((String)lastname);
				}

			});
		attributeGetterFunctions.put(
			"isMunicipal",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getIsMunicipal();
				}

			});
		attributeSetterBiConsumers.put(
			"isMunicipal",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object isMunicipal) {
					official.setIsMunicipal((Boolean)isMunicipal);
				}

			});
		attributeGetterFunctions.put(
			"isEurometropolitan",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getIsEurometropolitan();
				}

			});
		attributeSetterBiConsumers.put(
			"isEurometropolitan",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(
					Official official, Object isEurometropolitan) {

					official.setIsEurometropolitan((Boolean)isEurometropolitan);
				}

			});
		attributeGetterFunctions.put(
			"isActive",
			new Function<Official, Object>() {

				@Override
				public Object apply(Official official) {
					return official.getIsActive();
				}

			});
		attributeSetterBiConsumers.put(
			"isActive",
			new BiConsumer<Official, Object>() {

				@Override
				public void accept(Official official, Object isActive) {
					official.setIsActive((Boolean)isActive);
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
	public long getOfficialId() {
		return _officialId;
	}

	@Override
	public void setOfficialId(long officialId) {
		_officialId = officialId;
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
	public String getEmail() {
		if (_email == null) {
			return "";
		}
		else {
			return _email;
		}
	}

	@Override
	public void setEmail(String email) {
		_columnBitmask |= EMAIL_COLUMN_BITMASK;

		if (_originalEmail == null) {
			_originalEmail = _email;
		}

		_email = email;
	}

	public String getOriginalEmail() {
		return GetterUtil.getString(_originalEmail);
	}

	@JSON
	@Override
	public String getFirstname() {
		if (_firstname == null) {
			return "";
		}
		else {
			return _firstname;
		}
	}

	@Override
	public void setFirstname(String firstname) {
		_columnBitmask = -1L;

		_firstname = firstname;
	}

	@JSON
	@Override
	public String getLastname() {
		if (_lastname == null) {
			return "";
		}
		else {
			return _lastname;
		}
	}

	@Override
	public void setLastname(String lastname) {
		_columnBitmask = -1L;

		_lastname = lastname;
	}

	@JSON
	@Override
	public boolean getIsMunicipal() {
		return _isMunicipal;
	}

	@JSON
	@Override
	public boolean isIsMunicipal() {
		return _isMunicipal;
	}

	@Override
	public void setIsMunicipal(boolean isMunicipal) {
		_columnBitmask |= ISMUNICIPAL_COLUMN_BITMASK;

		if (!_setOriginalIsMunicipal) {
			_setOriginalIsMunicipal = true;

			_originalIsMunicipal = _isMunicipal;
		}

		_isMunicipal = isMunicipal;
	}

	public boolean getOriginalIsMunicipal() {
		return _originalIsMunicipal;
	}

	@JSON
	@Override
	public boolean getIsEurometropolitan() {
		return _isEurometropolitan;
	}

	@JSON
	@Override
	public boolean isIsEurometropolitan() {
		return _isEurometropolitan;
	}

	@Override
	public void setIsEurometropolitan(boolean isEurometropolitan) {
		_columnBitmask |= ISEUROMETROPOLITAN_COLUMN_BITMASK;

		if (!_setOriginalIsEurometropolitan) {
			_setOriginalIsEurometropolitan = true;

			_originalIsEurometropolitan = _isEurometropolitan;
		}

		_isEurometropolitan = isEurometropolitan;
	}

	public boolean getOriginalIsEurometropolitan() {
		return _originalIsEurometropolitan;
	}

	@JSON
	@Override
	public boolean getIsActive() {
		return _isActive;
	}

	@JSON
	@Override
	public boolean isIsActive() {
		return _isActive;
	}

	@Override
	public void setIsActive(boolean isActive) {
		_columnBitmask |= ISACTIVE_COLUMN_BITMASK;

		if (!_setOriginalIsActive) {
			_setOriginalIsActive = true;

			_originalIsActive = _isActive;
		}

		_isActive = isActive;
	}

	public boolean getOriginalIsActive() {
		return _originalIsActive;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Official.class.getName()));
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
			getCompanyId(), Official.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Official toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OfficialImpl officialImpl = new OfficialImpl();

		officialImpl.setUuid(getUuid());
		officialImpl.setOfficialId(getOfficialId());
		officialImpl.setGroupId(getGroupId());
		officialImpl.setCompanyId(getCompanyId());
		officialImpl.setUserId(getUserId());
		officialImpl.setUserName(getUserName());
		officialImpl.setCreateDate(getCreateDate());
		officialImpl.setModifiedDate(getModifiedDate());
		officialImpl.setStatus(getStatus());
		officialImpl.setStatusByUserId(getStatusByUserId());
		officialImpl.setStatusByUserName(getStatusByUserName());
		officialImpl.setStatusDate(getStatusDate());
		officialImpl.setEmail(getEmail());
		officialImpl.setFirstname(getFirstname());
		officialImpl.setLastname(getLastname());
		officialImpl.setIsMunicipal(isIsMunicipal());
		officialImpl.setIsEurometropolitan(isIsEurometropolitan());
		officialImpl.setIsActive(isIsActive());

		officialImpl.resetOriginalValues();

		return officialImpl;
	}

	@Override
	public int compareTo(Official official) {
		int value = 0;

		value = getLastname().compareToIgnoreCase(official.getLastname());

		if (value != 0) {
			return value;
		}

		value = getFirstname().compareToIgnoreCase(official.getFirstname());

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

		if (!(obj instanceof Official)) {
			return false;
		}

		Official official = (Official)obj;

		long primaryKey = official.getPrimaryKey();

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
		OfficialModelImpl officialModelImpl = this;

		officialModelImpl._originalUuid = officialModelImpl._uuid;

		officialModelImpl._originalGroupId = officialModelImpl._groupId;

		officialModelImpl._setOriginalGroupId = false;

		officialModelImpl._originalCompanyId = officialModelImpl._companyId;

		officialModelImpl._setOriginalCompanyId = false;

		officialModelImpl._setModifiedDate = false;

		officialModelImpl._originalEmail = officialModelImpl._email;

		officialModelImpl._originalIsMunicipal = officialModelImpl._isMunicipal;

		officialModelImpl._setOriginalIsMunicipal = false;

		officialModelImpl._originalIsEurometropolitan =
			officialModelImpl._isEurometropolitan;

		officialModelImpl._setOriginalIsEurometropolitan = false;

		officialModelImpl._originalIsActive = officialModelImpl._isActive;

		officialModelImpl._setOriginalIsActive = false;

		officialModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Official> toCacheModel() {
		OfficialCacheModel officialCacheModel = new OfficialCacheModel();

		officialCacheModel.uuid = getUuid();

		String uuid = officialCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			officialCacheModel.uuid = null;
		}

		officialCacheModel.officialId = getOfficialId();

		officialCacheModel.groupId = getGroupId();

		officialCacheModel.companyId = getCompanyId();

		officialCacheModel.userId = getUserId();

		officialCacheModel.userName = getUserName();

		String userName = officialCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			officialCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			officialCacheModel.createDate = createDate.getTime();
		}
		else {
			officialCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			officialCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			officialCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		officialCacheModel.status = getStatus();

		officialCacheModel.statusByUserId = getStatusByUserId();

		officialCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = officialCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			officialCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			officialCacheModel.statusDate = statusDate.getTime();
		}
		else {
			officialCacheModel.statusDate = Long.MIN_VALUE;
		}

		officialCacheModel.email = getEmail();

		String email = officialCacheModel.email;

		if ((email != null) && (email.length() == 0)) {
			officialCacheModel.email = null;
		}

		officialCacheModel.firstname = getFirstname();

		String firstname = officialCacheModel.firstname;

		if ((firstname != null) && (firstname.length() == 0)) {
			officialCacheModel.firstname = null;
		}

		officialCacheModel.lastname = getLastname();

		String lastname = officialCacheModel.lastname;

		if ((lastname != null) && (lastname.length() == 0)) {
			officialCacheModel.lastname = null;
		}

		officialCacheModel.isMunicipal = isIsMunicipal();

		officialCacheModel.isEurometropolitan = isIsEurometropolitan();

		officialCacheModel.isActive = isIsActive();

		return officialCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Official, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Official, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Official, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Official)this));
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
		Map<String, Function<Official, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Official, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Official, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Official)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Official>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private String _uuid;
	private String _originalUuid;
	private long _officialId;
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
	private String _email;
	private String _originalEmail;
	private String _firstname;
	private String _lastname;
	private boolean _isMunicipal;
	private boolean _originalIsMunicipal;
	private boolean _setOriginalIsMunicipal;
	private boolean _isEurometropolitan;
	private boolean _originalIsEurometropolitan;
	private boolean _setOriginalIsEurometropolitan;
	private boolean _isActive;
	private boolean _originalIsActive;
	private boolean _setOriginalIsActive;
	private long _columnBitmask;
	private Official _escapedModel;

}
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

package eu.strasbourg.service.formSendRecordField.model.impl;

import aQute.bnd.annotation.ProviderType;

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

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldModel;

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
 * The base model implementation for the FormSendRecordField service. Represents a row in the &quot;formSendRecordField_FormSendRecordField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>FormSendRecordFieldModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FormSendRecordFieldImpl}.
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldImpl
 * @generated
 */
@ProviderType
public class FormSendRecordFieldModelImpl
	extends BaseModelImpl<FormSendRecordField>
	implements FormSendRecordFieldModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a form send record field model instance should use the <code>FormSendRecordField</code> interface instead.
	 */
	public static final String TABLE_NAME =
		"formSendRecordField_FormSendRecordField";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"formSendRecordFieldId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}, {"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR}, {"statusDate", Types.TIMESTAMP},
		{"response", Types.CLOB}, {"assetEntryId", Types.BIGINT},
		{"contentId", Types.BIGINT}, {"instanceId", Types.VARCHAR},
		{"responseUserId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formSendRecordFieldId", Types.BIGINT);
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
		TABLE_COLUMNS_MAP.put("response", Types.CLOB);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("instanceId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("responseUserId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table formSendRecordField_FormSendRecordField (uuid_ VARCHAR(75) null,formSendRecordFieldId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,response TEXT null,assetEntryId LONG,contentId LONG,instanceId VARCHAR(75) null,responseUserId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table formSendRecordField_FormSendRecordField";

	public static final String ORDER_BY_JPQL =
		" ORDER BY formSendRecordField.formSendRecordFieldId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY formSendRecordField_FormSendRecordField.formSendRecordFieldId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordField"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordField"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.formSendRecordField.model.FormSendRecordField"),
		true);

	public static final long ASSETENTRYID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long CONTENTID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long INSTANCEID_COLUMN_BITMASK = 16L;

	public static final long STATUS_COLUMN_BITMASK = 32L;

	public static final long UUID_COLUMN_BITMASK = 64L;

	public static final long FORMSENDRECORDFIELDID_COLUMN_BITMASK = 128L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.formSendRecordField.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.formSendRecordField.model.FormSendRecordField"));

	public FormSendRecordFieldModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _formSendRecordFieldId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFormSendRecordFieldId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formSendRecordFieldId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FormSendRecordField.class;
	}

	@Override
	public String getModelClassName() {
		return FormSendRecordField.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FormSendRecordField, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FormSendRecordField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FormSendRecordField, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FormSendRecordField)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FormSendRecordField, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FormSendRecordField, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FormSendRecordField)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FormSendRecordField, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FormSendRecordField, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FormSendRecordField>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FormSendRecordField.class.getClassLoader(),
			FormSendRecordField.class, ModelWrapper.class);

		try {
			Constructor<FormSendRecordField> constructor =
				(Constructor<FormSendRecordField>)proxyClass.getConstructor(
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

	private static final Map<String, Function<FormSendRecordField, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<FormSendRecordField, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<FormSendRecordField, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<FormSendRecordField, Object>>();
		Map<String, BiConsumer<FormSendRecordField, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<FormSendRecordField, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object uuid) {

					formSendRecordField.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"formSendRecordFieldId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getFormSendRecordFieldId();
				}

			});
		attributeSetterBiConsumers.put(
			"formSendRecordFieldId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object formSendRecordFieldId) {

					formSendRecordField.setFormSendRecordFieldId(
						(Long)formSendRecordFieldId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object groupId) {

					formSendRecordField.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object companyId) {

					formSendRecordField.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object userId) {

					formSendRecordField.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object userName) {

					formSendRecordField.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object createDate) {

					formSendRecordField.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object modifiedDate) {

					formSendRecordField.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object status) {

					formSendRecordField.setStatus((Integer)status);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object statusByUserId) {

					formSendRecordField.setStatusByUserId((Long)statusByUserId);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object statusByUserName) {

					formSendRecordField.setStatusByUserName(
						(String)statusByUserName);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object statusDate) {

					formSendRecordField.setStatusDate((Date)statusDate);
				}

			});
		attributeGetterFunctions.put(
			"response",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getResponse();
				}

			});
		attributeSetterBiConsumers.put(
			"response",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object response) {

					formSendRecordField.setResponse((String)response);
				}

			});
		attributeGetterFunctions.put(
			"assetEntryId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getAssetEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"assetEntryId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object assetEntryId) {

					formSendRecordField.setAssetEntryId((Long)assetEntryId);
				}

			});
		attributeGetterFunctions.put(
			"contentId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getContentId();
				}

			});
		attributeSetterBiConsumers.put(
			"contentId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField, Object contentId) {

					formSendRecordField.setContentId((Long)contentId);
				}

			});
		attributeGetterFunctions.put(
			"instanceId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getInstanceId();
				}

			});
		attributeSetterBiConsumers.put(
			"instanceId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object instanceId) {

					formSendRecordField.setInstanceId((String)instanceId);
				}

			});
		attributeGetterFunctions.put(
			"responseUserId",
			new Function<FormSendRecordField, Object>() {

				@Override
				public Object apply(FormSendRecordField formSendRecordField) {
					return formSendRecordField.getResponseUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"responseUserId",
			new BiConsumer<FormSendRecordField, Object>() {

				@Override
				public void accept(
					FormSendRecordField formSendRecordField,
					Object responseUserId) {

					formSendRecordField.setResponseUserId((Long)responseUserId);
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
	public long getFormSendRecordFieldId() {
		return _formSendRecordFieldId;
	}

	@Override
	public void setFormSendRecordFieldId(long formSendRecordFieldId) {
		_formSendRecordFieldId = formSendRecordFieldId;
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
		catch (PortalException pe) {
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
	public String getResponse() {
		if (_response == null) {
			return "";
		}
		else {
			return _response;
		}
	}

	@Override
	public void setResponse(String response) {
		_response = response;
	}

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

	@Override
	public long getContentId() {
		return _contentId;
	}

	@Override
	public void setContentId(long contentId) {
		_columnBitmask |= CONTENTID_COLUMN_BITMASK;

		if (!_setOriginalContentId) {
			_setOriginalContentId = true;

			_originalContentId = _contentId;
		}

		_contentId = contentId;
	}

	public long getOriginalContentId() {
		return _originalContentId;
	}

	@Override
	public String getInstanceId() {
		if (_instanceId == null) {
			return "";
		}
		else {
			return _instanceId;
		}
	}

	@Override
	public void setInstanceId(String instanceId) {
		_columnBitmask |= INSTANCEID_COLUMN_BITMASK;

		if (_originalInstanceId == null) {
			_originalInstanceId = _instanceId;
		}

		_instanceId = instanceId;
	}

	public String getOriginalInstanceId() {
		return GetterUtil.getString(_originalInstanceId);
	}

	@Override
	public long getResponseUserId() {
		return _responseUserId;
	}

	@Override
	public void setResponseUserId(long responseUserId) {
		_responseUserId = responseUserId;
	}

	@Override
	public String getResponseUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getResponseUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setResponseUserUuid(String responseUserUuid) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(FormSendRecordField.class.getName()));
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
			getCompanyId(), FormSendRecordField.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FormSendRecordField toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		FormSendRecordFieldImpl formSendRecordFieldImpl =
			new FormSendRecordFieldImpl();

		formSendRecordFieldImpl.setUuid(getUuid());
		formSendRecordFieldImpl.setFormSendRecordFieldId(
			getFormSendRecordFieldId());
		formSendRecordFieldImpl.setGroupId(getGroupId());
		formSendRecordFieldImpl.setCompanyId(getCompanyId());
		formSendRecordFieldImpl.setUserId(getUserId());
		formSendRecordFieldImpl.setUserName(getUserName());
		formSendRecordFieldImpl.setCreateDate(getCreateDate());
		formSendRecordFieldImpl.setModifiedDate(getModifiedDate());
		formSendRecordFieldImpl.setStatus(getStatus());
		formSendRecordFieldImpl.setStatusByUserId(getStatusByUserId());
		formSendRecordFieldImpl.setStatusByUserName(getStatusByUserName());
		formSendRecordFieldImpl.setStatusDate(getStatusDate());
		formSendRecordFieldImpl.setResponse(getResponse());
		formSendRecordFieldImpl.setAssetEntryId(getAssetEntryId());
		formSendRecordFieldImpl.setContentId(getContentId());
		formSendRecordFieldImpl.setInstanceId(getInstanceId());
		formSendRecordFieldImpl.setResponseUserId(getResponseUserId());

		formSendRecordFieldImpl.resetOriginalValues();

		return formSendRecordFieldImpl;
	}

	@Override
	public int compareTo(FormSendRecordField formSendRecordField) {
		long primaryKey = formSendRecordField.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormSendRecordField)) {
			return false;
		}

		FormSendRecordField formSendRecordField = (FormSendRecordField)obj;

		long primaryKey = formSendRecordField.getPrimaryKey();

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
		FormSendRecordFieldModelImpl formSendRecordFieldModelImpl = this;

		formSendRecordFieldModelImpl._originalUuid =
			formSendRecordFieldModelImpl._uuid;

		formSendRecordFieldModelImpl._originalGroupId =
			formSendRecordFieldModelImpl._groupId;

		formSendRecordFieldModelImpl._setOriginalGroupId = false;

		formSendRecordFieldModelImpl._originalCompanyId =
			formSendRecordFieldModelImpl._companyId;

		formSendRecordFieldModelImpl._setOriginalCompanyId = false;

		formSendRecordFieldModelImpl._setModifiedDate = false;

		formSendRecordFieldModelImpl._originalStatus =
			formSendRecordFieldModelImpl._status;

		formSendRecordFieldModelImpl._setOriginalStatus = false;

		formSendRecordFieldModelImpl._originalAssetEntryId =
			formSendRecordFieldModelImpl._assetEntryId;

		formSendRecordFieldModelImpl._setOriginalAssetEntryId = false;

		formSendRecordFieldModelImpl._originalContentId =
			formSendRecordFieldModelImpl._contentId;

		formSendRecordFieldModelImpl._setOriginalContentId = false;

		formSendRecordFieldModelImpl._originalInstanceId =
			formSendRecordFieldModelImpl._instanceId;

		formSendRecordFieldModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<FormSendRecordField> toCacheModel() {
		FormSendRecordFieldCacheModel formSendRecordFieldCacheModel =
			new FormSendRecordFieldCacheModel();

		formSendRecordFieldCacheModel.uuid = getUuid();

		String uuid = formSendRecordFieldCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			formSendRecordFieldCacheModel.uuid = null;
		}

		formSendRecordFieldCacheModel.formSendRecordFieldId =
			getFormSendRecordFieldId();

		formSendRecordFieldCacheModel.groupId = getGroupId();

		formSendRecordFieldCacheModel.companyId = getCompanyId();

		formSendRecordFieldCacheModel.userId = getUserId();

		formSendRecordFieldCacheModel.userName = getUserName();

		String userName = formSendRecordFieldCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			formSendRecordFieldCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			formSendRecordFieldCacheModel.createDate = createDate.getTime();
		}
		else {
			formSendRecordFieldCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			formSendRecordFieldCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			formSendRecordFieldCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		formSendRecordFieldCacheModel.status = getStatus();

		formSendRecordFieldCacheModel.statusByUserId = getStatusByUserId();

		formSendRecordFieldCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName =
			formSendRecordFieldCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			formSendRecordFieldCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			formSendRecordFieldCacheModel.statusDate = statusDate.getTime();
		}
		else {
			formSendRecordFieldCacheModel.statusDate = Long.MIN_VALUE;
		}

		formSendRecordFieldCacheModel.response = getResponse();

		String response = formSendRecordFieldCacheModel.response;

		if ((response != null) && (response.length() == 0)) {
			formSendRecordFieldCacheModel.response = null;
		}

		formSendRecordFieldCacheModel.assetEntryId = getAssetEntryId();

		formSendRecordFieldCacheModel.contentId = getContentId();

		formSendRecordFieldCacheModel.instanceId = getInstanceId();

		String instanceId = formSendRecordFieldCacheModel.instanceId;

		if ((instanceId != null) && (instanceId.length() == 0)) {
			formSendRecordFieldCacheModel.instanceId = null;
		}

		formSendRecordFieldCacheModel.responseUserId = getResponseUserId();

		return formSendRecordFieldCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FormSendRecordField, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FormSendRecordField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FormSendRecordField, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((FormSendRecordField)this));
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
		Map<String, Function<FormSendRecordField, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FormSendRecordField, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FormSendRecordField, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((FormSendRecordField)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, FormSendRecordField>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private String _uuid;
	private String _originalUuid;
	private long _formSendRecordFieldId;
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
	private String _response;
	private long _assetEntryId;
	private long _originalAssetEntryId;
	private boolean _setOriginalAssetEntryId;
	private long _contentId;
	private long _originalContentId;
	private boolean _setOriginalContentId;
	private String _instanceId;
	private String _originalInstanceId;
	private long _responseUserId;
	private long _columnBitmask;
	private FormSendRecordField _escapedModel;

}
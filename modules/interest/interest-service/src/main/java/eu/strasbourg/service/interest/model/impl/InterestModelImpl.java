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

package eu.strasbourg.service.interest.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.model.InterestModel;
import eu.strasbourg.service.interest.model.InterestSoap;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Interest service. Represents a row in the &quot;interest_Interest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>InterestModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link InterestImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see InterestImpl
 * @generated
 */
@JSON(strict = true)
public class InterestModelImpl
	extends BaseModelImpl<Interest> implements InterestModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a interest model instance should use the <code>Interest</code> interface instead.
	 */
	public static final String TABLE_NAME = "interest_Interest";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"interestId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"lastPublishDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}, {"title", Types.VARCHAR},
		{"description", Types.CLOB}, {"typeId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("interestId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("typeId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table interest_Interest (uuid_ VARCHAR(75) null,interestId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,title STRING null,description TEXT null,typeId LONG)";

	public static final String TABLE_SQL_DROP = "drop table interest_Interest";

	public static final String ORDER_BY_JPQL =
		" ORDER BY interest.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY interest_Interest.modifiedDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.interest.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.interest.model.Interest"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.interest.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.interest.model.Interest"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.interest.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.interest.model.Interest"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Interest toModel(InterestSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Interest model = new InterestImpl();

		model.setUuid(soapModel.getUuid());
		model.setInterestId(soapModel.getInterestId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setTypeId(soapModel.getTypeId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Interest> toModels(InterestSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Interest> models = new ArrayList<Interest>(soapModels.length);

		for (InterestSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.interest.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.interest.model.Interest"));

	public InterestModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _interestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInterestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _interestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Interest.class;
	}

	@Override
	public String getModelClassName() {
		return Interest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Interest, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Interest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Interest, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Interest)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Interest, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Interest, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Interest)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Interest, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Interest, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Interest>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Interest.class.getClassLoader(), Interest.class,
			ModelWrapper.class);

		try {
			Constructor<Interest> constructor =
				(Constructor<Interest>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Interest, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Interest, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Interest, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Interest, Object>>();
		Map<String, BiConsumer<Interest, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Interest, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object uuidObject) {
					interest.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"interestId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getInterestId();
				}

			});
		attributeSetterBiConsumers.put(
			"interestId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object interestIdObject) {
					interest.setInterestId((Long)interestIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object groupIdObject) {
					interest.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object companyIdObject) {
					interest.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object userIdObject) {
					interest.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object userNameObject) {
					interest.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object createDateObject) {
					interest.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(
					Interest interest, Object modifiedDateObject) {

					interest.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(
					Interest interest, Object lastPublishDateObject) {

					interest.setLastPublishDate((Date)lastPublishDateObject);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object statusObject) {
					interest.setStatus((Integer)statusObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getStatusByUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(
					Interest interest, Object statusByUserIdObject) {

					interest.setStatusByUserId((Long)statusByUserIdObject);
				}

			});
		attributeGetterFunctions.put(
			"statusByUserName",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getStatusByUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"statusByUserName",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(
					Interest interest, Object statusByUserNameObject) {

					interest.setStatusByUserName(
						(String)statusByUserNameObject);
				}

			});
		attributeGetterFunctions.put(
			"statusDate",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getStatusDate();
				}

			});
		attributeSetterBiConsumers.put(
			"statusDate",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object statusDateObject) {
					interest.setStatusDate((Date)statusDateObject);
				}

			});
		attributeGetterFunctions.put(
			"title",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getTitle();
				}

			});
		attributeSetterBiConsumers.put(
			"title",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object titleObject) {
					interest.setTitle((String)titleObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(
					Interest interest, Object descriptionObject) {

					interest.setDescription((String)descriptionObject);
				}

			});
		attributeGetterFunctions.put(
			"typeId",
			new Function<Interest, Object>() {

				@Override
				public Object apply(Interest interest) {
					return interest.getTypeId();
				}

			});
		attributeSetterBiConsumers.put(
			"typeId",
			new BiConsumer<Interest, Object>() {

				@Override
				public void accept(Interest interest, Object typeIdObject) {
					interest.setTypeId((Long)typeIdObject);
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
	public long getInterestId() {
		return _interestId;
	}

	@Override
	public void setInterestId(long interestId) {
		_interestId = interestId;
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

		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getTitle(), languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(
				LocalizationUtil.updateLocalization(
					getTitle(), "Title", title, languageId, defaultLanguageId));
		}
		else {
			setTitle(
				LocalizationUtil.removeLocalization(
					getTitle(), "Title", languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitleMap(
		Map<Locale, String> titleMap, Locale defaultLocale) {

		if (titleMap == null) {
			return;
		}

		setTitle(
			LocalizationUtil.updateLocalization(
				titleMap, getTitle(), "Title",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Interest.class.getName()));
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
			getCompanyId(), Interest.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			Interest.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Interest toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Interest>
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
		InterestImpl interestImpl = new InterestImpl();

		interestImpl.setUuid(getUuid());
		interestImpl.setInterestId(getInterestId());
		interestImpl.setGroupId(getGroupId());
		interestImpl.setCompanyId(getCompanyId());
		interestImpl.setUserId(getUserId());
		interestImpl.setUserName(getUserName());
		interestImpl.setCreateDate(getCreateDate());
		interestImpl.setModifiedDate(getModifiedDate());
		interestImpl.setLastPublishDate(getLastPublishDate());
		interestImpl.setStatus(getStatus());
		interestImpl.setStatusByUserId(getStatusByUserId());
		interestImpl.setStatusByUserName(getStatusByUserName());
		interestImpl.setStatusDate(getStatusDate());
		interestImpl.setTitle(getTitle());
		interestImpl.setDescription(getDescription());
		interestImpl.setTypeId(getTypeId());

		interestImpl.resetOriginalValues();

		return interestImpl;
	}

	@Override
	public int compareTo(Interest interest) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), interest.getModifiedDate());

		value = value * -1;

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

		if (!(object instanceof Interest)) {
			return false;
		}

		Interest interest = (Interest)object;

		long primaryKey = interest.getPrimaryKey();

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
		InterestModelImpl interestModelImpl = this;

		interestModelImpl._originalUuid = interestModelImpl._uuid;

		interestModelImpl._originalGroupId = interestModelImpl._groupId;

		interestModelImpl._setOriginalGroupId = false;

		interestModelImpl._originalCompanyId = interestModelImpl._companyId;

		interestModelImpl._setOriginalCompanyId = false;

		interestModelImpl._setModifiedDate = false;

		interestModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Interest> toCacheModel() {
		InterestCacheModel interestCacheModel = new InterestCacheModel();

		interestCacheModel.uuid = getUuid();

		String uuid = interestCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			interestCacheModel.uuid = null;
		}

		interestCacheModel.interestId = getInterestId();

		interestCacheModel.groupId = getGroupId();

		interestCacheModel.companyId = getCompanyId();

		interestCacheModel.userId = getUserId();

		interestCacheModel.userName = getUserName();

		String userName = interestCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			interestCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			interestCacheModel.createDate = createDate.getTime();
		}
		else {
			interestCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			interestCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			interestCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			interestCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			interestCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		interestCacheModel.status = getStatus();

		interestCacheModel.statusByUserId = getStatusByUserId();

		interestCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = interestCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			interestCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			interestCacheModel.statusDate = statusDate.getTime();
		}
		else {
			interestCacheModel.statusDate = Long.MIN_VALUE;
		}

		interestCacheModel.title = getTitle();

		String title = interestCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			interestCacheModel.title = null;
		}

		interestCacheModel.description = getDescription();

		String description = interestCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			interestCacheModel.description = null;
		}

		interestCacheModel.typeId = getTypeId();

		return interestCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Interest, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Interest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Interest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Interest)this));
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
		Map<String, Function<Interest, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Interest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Interest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Interest)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Interest>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _interestId;
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
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private long _typeId;
	private long _columnBitmask;
	private Interest _escapedModel;

}
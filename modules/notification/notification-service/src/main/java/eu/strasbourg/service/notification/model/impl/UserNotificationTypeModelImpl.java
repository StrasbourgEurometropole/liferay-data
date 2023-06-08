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

package eu.strasbourg.service.notification.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.notification.model.UserNotificationType;
import eu.strasbourg.service.notification.model.UserNotificationTypeModel;
import eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the UserNotificationType service. Represents a row in the &quot;notification_UserNotificationType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserNotificationTypeModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserNotificationTypeImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationTypeImpl
 * @generated
 */
public class UserNotificationTypeModelImpl
	extends BaseModelImpl<UserNotificationType>
	implements UserNotificationTypeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user notification type model instance should use the <code>UserNotificationType</code> interface instead.
	 */
	public static final String TABLE_NAME = "notification_UserNotificationType";

	public static final Object[][] TABLE_COLUMNS = {
		{"publikUserId", Types.VARCHAR}, {"typeId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("publikUserId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table notification_UserNotificationType (publikUserId VARCHAR(75) not null,typeId LONG not null,primary key (publikUserId, typeId))";

	public static final String TABLE_SQL_DROP =
		"drop table notification_UserNotificationType";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userNotificationType.id.publikUserId ASC, userNotificationType.id.typeId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY notification_UserNotificationType.publikUserId ASC, notification_UserNotificationType.typeId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.notification.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.notification.model.UserNotificationType"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.notification.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.notification.model.UserNotificationType"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.notification.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.notification.model.UserNotificationType"),
		true);

	public static final long PUBLIKUSERID_COLUMN_BITMASK = 1L;

	public static final long TYPEID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.notification.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.notification.model.UserNotificationType"));

	public UserNotificationTypeModelImpl() {
	}

	@Override
	public UserNotificationTypePK getPrimaryKey() {
		return new UserNotificationTypePK(_publikUserId, _typeId);
	}

	@Override
	public void setPrimaryKey(UserNotificationTypePK primaryKey) {
		setPublikUserId(primaryKey.publikUserId);
		setTypeId(primaryKey.typeId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new UserNotificationTypePK(_publikUserId, _typeId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((UserNotificationTypePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return UserNotificationType.class;
	}

	@Override
	public String getModelClassName() {
		return UserNotificationType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserNotificationType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserNotificationType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserNotificationType, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserNotificationType)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserNotificationType, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserNotificationType, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserNotificationType)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserNotificationType, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserNotificationType, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserNotificationType>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserNotificationType.class.getClassLoader(),
			UserNotificationType.class, ModelWrapper.class);

		try {
			Constructor<UserNotificationType> constructor =
				(Constructor<UserNotificationType>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserNotificationType, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserNotificationType, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserNotificationType, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<UserNotificationType, Object>>();
		Map<String, BiConsumer<UserNotificationType, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<UserNotificationType, ?>>();

		attributeGetterFunctions.put(
			"publikUserId",
			new Function<UserNotificationType, Object>() {

				@Override
				public Object apply(UserNotificationType userNotificationType) {
					return userNotificationType.getPublikUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"publikUserId",
			new BiConsumer<UserNotificationType, Object>() {

				@Override
				public void accept(
					UserNotificationType userNotificationType,
					Object publikUserIdObject) {

					userNotificationType.setPublikUserId(
						(String)publikUserIdObject);
				}

			});
		attributeGetterFunctions.put(
			"typeId",
			new Function<UserNotificationType, Object>() {

				@Override
				public Object apply(UserNotificationType userNotificationType) {
					return userNotificationType.getTypeId();
				}

			});
		attributeSetterBiConsumers.put(
			"typeId",
			new BiConsumer<UserNotificationType, Object>() {

				@Override
				public void accept(
					UserNotificationType userNotificationType,
					Object typeIdObject) {

					userNotificationType.setTypeId((Long)typeIdObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getPublikUserId() {
		if (_publikUserId == null) {
			return "";
		}
		else {
			return _publikUserId;
		}
	}

	@Override
	public void setPublikUserId(String publikUserId) {
		_columnBitmask |= PUBLIKUSERID_COLUMN_BITMASK;

		if (_originalPublikUserId == null) {
			_originalPublikUserId = _publikUserId;
		}

		_publikUserId = publikUserId;
	}

	public String getOriginalPublikUserId() {
		return GetterUtil.getString(_originalPublikUserId);
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public UserNotificationType toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserNotificationType>
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
		UserNotificationTypeImpl userNotificationTypeImpl =
			new UserNotificationTypeImpl();

		userNotificationTypeImpl.setPublikUserId(getPublikUserId());
		userNotificationTypeImpl.setTypeId(getTypeId());

		userNotificationTypeImpl.resetOriginalValues();

		return userNotificationTypeImpl;
	}

	@Override
	public int compareTo(UserNotificationType userNotificationType) {
		UserNotificationTypePK primaryKey =
			userNotificationType.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserNotificationType)) {
			return false;
		}

		UserNotificationType userNotificationType =
			(UserNotificationType)object;

		UserNotificationTypePK primaryKey =
			userNotificationType.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		UserNotificationTypeModelImpl userNotificationTypeModelImpl = this;

		userNotificationTypeModelImpl._originalPublikUserId =
			userNotificationTypeModelImpl._publikUserId;

		userNotificationTypeModelImpl._originalTypeId =
			userNotificationTypeModelImpl._typeId;

		userNotificationTypeModelImpl._setOriginalTypeId = false;

		userNotificationTypeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserNotificationType> toCacheModel() {
		UserNotificationTypeCacheModel userNotificationTypeCacheModel =
			new UserNotificationTypeCacheModel();

		userNotificationTypeCacheModel.userNotificationTypePK = getPrimaryKey();

		userNotificationTypeCacheModel.publikUserId = getPublikUserId();

		String publikUserId = userNotificationTypeCacheModel.publikUserId;

		if ((publikUserId != null) && (publikUserId.length() == 0)) {
			userNotificationTypeCacheModel.publikUserId = null;
		}

		userNotificationTypeCacheModel.typeId = getTypeId();

		return userNotificationTypeCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserNotificationType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserNotificationType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserNotificationType, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((UserNotificationType)this));
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
		Map<String, Function<UserNotificationType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserNotificationType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserNotificationType, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((UserNotificationType)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserNotificationType>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _publikUserId;
	private String _originalPublikUserId;
	private long _typeId;
	private long _originalTypeId;
	private boolean _setOriginalTypeId;
	private long _columnBitmask;
	private UserNotificationType _escapedModel;

}
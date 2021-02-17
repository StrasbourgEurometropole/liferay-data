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

package eu.strasbourg.service.csmap.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.model.RefreshTokenModel;

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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the RefreshToken service. Represents a row in the &quot;csmap_RefreshToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>RefreshTokenModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RefreshTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenImpl
 * @generated
 */
@ProviderType
public class RefreshTokenModelImpl
	extends BaseModelImpl<RefreshToken> implements RefreshTokenModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a refresh token model instance should use the <code>RefreshToken</code> interface instead.
	 */
	public static final String TABLE_NAME = "csmap_RefreshToken";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"refreshTokenId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"value", Types.VARCHAR},
		{"publikId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("refreshTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("publikId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table csmap_RefreshToken (uuid_ VARCHAR(75) null,refreshTokenId LONG not null primary key,createDate DATE null,value VARCHAR(255) null,publikId VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table csmap_RefreshToken";

	public static final String ORDER_BY_JPQL =
		" ORDER BY refreshToken.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY csmap_RefreshToken.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long PUBLIKID_COLUMN_BITMASK = 1L;

	public static final long UUID_COLUMN_BITMASK = 2L;

	public static final long VALUE_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public RefreshTokenModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _refreshTokenId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRefreshTokenId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _refreshTokenId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RefreshToken.class;
	}

	@Override
	public String getModelClassName() {
		return RefreshToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RefreshToken, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RefreshToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RefreshToken, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RefreshToken)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RefreshToken, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RefreshToken, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RefreshToken)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RefreshToken, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RefreshToken, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RefreshToken>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RefreshToken.class.getClassLoader(), RefreshToken.class,
			ModelWrapper.class);

		try {
			Constructor<RefreshToken> constructor =
				(Constructor<RefreshToken>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RefreshToken, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RefreshToken, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RefreshToken, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<RefreshToken, Object>>();
		Map<String, BiConsumer<RefreshToken, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<RefreshToken, ?>>();

		attributeGetterFunctions.put("uuid", RefreshToken::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<RefreshToken, String>)RefreshToken::setUuid);
		attributeGetterFunctions.put(
			"refreshTokenId", RefreshToken::getRefreshTokenId);
		attributeSetterBiConsumers.put(
			"refreshTokenId",
			(BiConsumer<RefreshToken, Long>)RefreshToken::setRefreshTokenId);
		attributeGetterFunctions.put("createDate", RefreshToken::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<RefreshToken, Date>)RefreshToken::setCreateDate);
		attributeGetterFunctions.put("value", RefreshToken::getValue);
		attributeSetterBiConsumers.put(
			"value", (BiConsumer<RefreshToken, String>)RefreshToken::setValue);
		attributeGetterFunctions.put("publikId", RefreshToken::getPublikId);
		attributeSetterBiConsumers.put(
			"publikId",
			(BiConsumer<RefreshToken, String>)RefreshToken::setPublikId);

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
	public long getRefreshTokenId() {
		return _refreshTokenId;
	}

	@Override
	public void setRefreshTokenId(long refreshTokenId) {
		_refreshTokenId = refreshTokenId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		_columnBitmask |= VALUE_COLUMN_BITMASK;

		if (_originalValue == null) {
			_originalValue = _value;
		}

		_value = value;
	}

	public String getOriginalValue() {
		return GetterUtil.getString(_originalValue);
	}

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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, RefreshToken.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RefreshToken toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		RefreshTokenImpl refreshTokenImpl = new RefreshTokenImpl();

		refreshTokenImpl.setUuid(getUuid());
		refreshTokenImpl.setRefreshTokenId(getRefreshTokenId());
		refreshTokenImpl.setCreateDate(getCreateDate());
		refreshTokenImpl.setValue(getValue());
		refreshTokenImpl.setPublikId(getPublikId());

		refreshTokenImpl.resetOriginalValues();

		return refreshTokenImpl;
	}

	@Override
	public int compareTo(RefreshToken refreshToken) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), refreshToken.getCreateDate());

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

		if (!(obj instanceof RefreshToken)) {
			return false;
		}

		RefreshToken refreshToken = (RefreshToken)obj;

		long primaryKey = refreshToken.getPrimaryKey();

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
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		RefreshTokenModelImpl refreshTokenModelImpl = this;

		refreshTokenModelImpl._originalUuid = refreshTokenModelImpl._uuid;

		refreshTokenModelImpl._originalValue = refreshTokenModelImpl._value;

		refreshTokenModelImpl._originalPublikId =
			refreshTokenModelImpl._publikId;

		refreshTokenModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<RefreshToken> toCacheModel() {
		RefreshTokenCacheModel refreshTokenCacheModel =
			new RefreshTokenCacheModel();

		refreshTokenCacheModel.uuid = getUuid();

		String uuid = refreshTokenCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			refreshTokenCacheModel.uuid = null;
		}

		refreshTokenCacheModel.refreshTokenId = getRefreshTokenId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			refreshTokenCacheModel.createDate = createDate.getTime();
		}
		else {
			refreshTokenCacheModel.createDate = Long.MIN_VALUE;
		}

		refreshTokenCacheModel.value = getValue();

		String value = refreshTokenCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			refreshTokenCacheModel.value = null;
		}

		refreshTokenCacheModel.publikId = getPublikId();

		String publikId = refreshTokenCacheModel.publikId;

		if ((publikId != null) && (publikId.length() == 0)) {
			refreshTokenCacheModel.publikId = null;
		}

		return refreshTokenCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RefreshToken, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RefreshToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RefreshToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RefreshToken)this));
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
		Map<String, Function<RefreshToken, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RefreshToken, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RefreshToken, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RefreshToken)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, RefreshToken>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _refreshTokenId;
	private Date _createDate;
	private String _value;
	private String _originalValue;
	private String _publikId;
	private String _originalPublikId;
	private long _columnBitmask;
	private RefreshToken _escapedModel;

}
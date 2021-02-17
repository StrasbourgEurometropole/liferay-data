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

import eu.strasbourg.service.csmap.model.Nonce;
import eu.strasbourg.service.csmap.model.NonceModel;

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
 * The base model implementation for the Nonce service. Represents a row in the &quot;csmap_Nonce&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>NonceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NonceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NonceImpl
 * @generated
 */
@ProviderType
public class NonceModelImpl extends BaseModelImpl<Nonce> implements NonceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a nonce model instance should use the <code>Nonce</code> interface instead.
	 */
	public static final String TABLE_NAME = "csmap_Nonce";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"nonceId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("nonceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table csmap_Nonce (uuid_ VARCHAR(75) null,nonceId LONG not null primary key,createDate DATE null,value VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP = "drop table csmap_Nonce";

	public static final String ORDER_BY_JPQL = " ORDER BY nonce.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY csmap_Nonce.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long UUID_COLUMN_BITMASK = 1L;

	public static final long VALUE_COLUMN_BITMASK = 2L;

	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public NonceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _nonceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNonceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _nonceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Nonce.class;
	}

	@Override
	public String getModelClassName() {
		return Nonce.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Nonce, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Nonce, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Nonce, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Nonce)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Nonce, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Nonce, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Nonce)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Nonce, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Nonce, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Nonce>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Nonce.class.getClassLoader(), Nonce.class, ModelWrapper.class);

		try {
			Constructor<Nonce> constructor =
				(Constructor<Nonce>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Nonce, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Nonce, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Nonce, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Nonce, Object>>();
		Map<String, BiConsumer<Nonce, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Nonce, ?>>();

		attributeGetterFunctions.put("uuid", Nonce::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Nonce, String>)Nonce::setUuid);
		attributeGetterFunctions.put("nonceId", Nonce::getNonceId);
		attributeSetterBiConsumers.put(
			"nonceId", (BiConsumer<Nonce, Long>)Nonce::setNonceId);
		attributeGetterFunctions.put("createDate", Nonce::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Nonce, Date>)Nonce::setCreateDate);
		attributeGetterFunctions.put("value", Nonce::getValue);
		attributeSetterBiConsumers.put(
			"value", (BiConsumer<Nonce, String>)Nonce::setValue);

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
	public long getNonceId() {
		return _nonceId;
	}

	@Override
	public void setNonceId(long nonceId) {
		_nonceId = nonceId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Nonce.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Nonce toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		NonceImpl nonceImpl = new NonceImpl();

		nonceImpl.setUuid(getUuid());
		nonceImpl.setNonceId(getNonceId());
		nonceImpl.setCreateDate(getCreateDate());
		nonceImpl.setValue(getValue());

		nonceImpl.resetOriginalValues();

		return nonceImpl;
	}

	@Override
	public int compareTo(Nonce nonce) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), nonce.getCreateDate());

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

		if (!(obj instanceof Nonce)) {
			return false;
		}

		Nonce nonce = (Nonce)obj;

		long primaryKey = nonce.getPrimaryKey();

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
		NonceModelImpl nonceModelImpl = this;

		nonceModelImpl._originalUuid = nonceModelImpl._uuid;

		nonceModelImpl._originalValue = nonceModelImpl._value;

		nonceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Nonce> toCacheModel() {
		NonceCacheModel nonceCacheModel = new NonceCacheModel();

		nonceCacheModel.uuid = getUuid();

		String uuid = nonceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			nonceCacheModel.uuid = null;
		}

		nonceCacheModel.nonceId = getNonceId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			nonceCacheModel.createDate = createDate.getTime();
		}
		else {
			nonceCacheModel.createDate = Long.MIN_VALUE;
		}

		nonceCacheModel.value = getValue();

		String value = nonceCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			nonceCacheModel.value = null;
		}

		return nonceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Nonce, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Nonce, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Nonce, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Nonce)this));
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
		Map<String, Function<Nonce, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Nonce, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Nonce, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Nonce)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Nonce>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _nonceId;
	private Date _createDate;
	private String _value;
	private String _originalValue;
	private long _columnBitmask;
	private Nonce _escapedModel;

}
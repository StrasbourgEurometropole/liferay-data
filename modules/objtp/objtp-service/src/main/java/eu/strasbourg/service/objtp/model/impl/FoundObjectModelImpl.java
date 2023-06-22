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

package eu.strasbourg.service.objtp.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.model.FoundObjectModel;
import eu.strasbourg.service.objtp.model.FoundObjectSoap;

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
 * The base model implementation for the FoundObject service. Represents a row in the &quot;objtp_FoundObject&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FoundObjectModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FoundObjectImpl}.
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectImpl
 * @generated
 */
@JSON(strict = true)
public class FoundObjectModelImpl
	extends BaseModelImpl<FoundObject> implements FoundObjectModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a found object model instance should use the <code>FoundObject</code> interface instead.
	 */
	public static final String TABLE_NAME = "objtp_FoundObject";

	public static final Object[][] TABLE_COLUMNS = {
		{"number_", Types.VARCHAR}, {"date_", Types.TIMESTAMP},
		{"imageUrl", Types.VARCHAR}, {"categoryCode", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("number_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("date_", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("imageUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("categoryCode", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table objtp_FoundObject (number_ VARCHAR(75) not null primary key,date_ DATE null,imageUrl VARCHAR(75) null,categoryCode VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table objtp_FoundObject";

	public static final String ORDER_BY_JPQL = " ORDER BY foundObject.date ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY objtp_FoundObject.date_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.objtp.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.objtp.model.FoundObject"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.objtp.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.objtp.model.FoundObject"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.objtp.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.objtp.model.FoundObject"),
		true);

	public static final long CATEGORYCODE_COLUMN_BITMASK = 1L;

	public static final long DATE_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static FoundObject toModel(FoundObjectSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		FoundObject model = new FoundObjectImpl();

		model.setNumber(soapModel.getNumber());
		model.setDate(soapModel.getDate());
		model.setImageUrl(soapModel.getImageUrl());
		model.setCategoryCode(soapModel.getCategoryCode());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<FoundObject> toModels(FoundObjectSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<FoundObject> models = new ArrayList<FoundObject>(
			soapModels.length);

		for (FoundObjectSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.objtp.service.util.ServiceProps.get(
			"lock.expiration.time.eu.strasbourg.service.objtp.model.FoundObject"));

	public FoundObjectModelImpl() {
	}

	@Override
	public String getPrimaryKey() {
		return _number;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setNumber(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _number;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return FoundObject.class;
	}

	@Override
	public String getModelClassName() {
		return FoundObject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FoundObject, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FoundObject, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FoundObject, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FoundObject)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FoundObject, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FoundObject, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FoundObject)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FoundObject, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FoundObject, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FoundObject>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FoundObject.class.getClassLoader(), FoundObject.class,
			ModelWrapper.class);

		try {
			Constructor<FoundObject> constructor =
				(Constructor<FoundObject>)proxyClass.getConstructor(
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

	private static final Map<String, Function<FoundObject, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<FoundObject, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<FoundObject, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<FoundObject, Object>>();
		Map<String, BiConsumer<FoundObject, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<FoundObject, ?>>();

		attributeGetterFunctions.put(
			"number",
			new Function<FoundObject, Object>() {

				@Override
				public Object apply(FoundObject foundObject) {
					return foundObject.getNumber();
				}

			});
		attributeSetterBiConsumers.put(
			"number",
			new BiConsumer<FoundObject, Object>() {

				@Override
				public void accept(
					FoundObject foundObject, Object numberObject) {

					foundObject.setNumber((String)numberObject);
				}

			});
		attributeGetterFunctions.put(
			"date",
			new Function<FoundObject, Object>() {

				@Override
				public Object apply(FoundObject foundObject) {
					return foundObject.getDate();
				}

			});
		attributeSetterBiConsumers.put(
			"date",
			new BiConsumer<FoundObject, Object>() {

				@Override
				public void accept(FoundObject foundObject, Object dateObject) {
					foundObject.setDate((Date)dateObject);
				}

			});
		attributeGetterFunctions.put(
			"imageUrl",
			new Function<FoundObject, Object>() {

				@Override
				public Object apply(FoundObject foundObject) {
					return foundObject.getImageUrl();
				}

			});
		attributeSetterBiConsumers.put(
			"imageUrl",
			new BiConsumer<FoundObject, Object>() {

				@Override
				public void accept(
					FoundObject foundObject, Object imageUrlObject) {

					foundObject.setImageUrl((String)imageUrlObject);
				}

			});
		attributeGetterFunctions.put(
			"categoryCode",
			new Function<FoundObject, Object>() {

				@Override
				public Object apply(FoundObject foundObject) {
					return foundObject.getCategoryCode();
				}

			});
		attributeSetterBiConsumers.put(
			"categoryCode",
			new BiConsumer<FoundObject, Object>() {

				@Override
				public void accept(
					FoundObject foundObject, Object categoryCodeObject) {

					foundObject.setCategoryCode((String)categoryCodeObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getNumber() {
		if (_number == null) {
			return "";
		}
		else {
			return _number;
		}
	}

	@Override
	public void setNumber(String number) {
		_number = number;
	}

	@JSON
	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public void setDate(Date date) {
		_columnBitmask = -1L;

		_date = date;
	}

	@JSON
	@Override
	public String getImageUrl() {
		if (_imageUrl == null) {
			return "";
		}
		else {
			return _imageUrl;
		}
	}

	@Override
	public void setImageUrl(String imageUrl) {
		_imageUrl = imageUrl;
	}

	@JSON
	@Override
	public String getCategoryCode() {
		if (_categoryCode == null) {
			return "";
		}
		else {
			return _categoryCode;
		}
	}

	@Override
	public void setCategoryCode(String categoryCode) {
		_columnBitmask |= CATEGORYCODE_COLUMN_BITMASK;

		if (_originalCategoryCode == null) {
			_originalCategoryCode = _categoryCode;
		}

		_categoryCode = categoryCode;
	}

	public String getOriginalCategoryCode() {
		return GetterUtil.getString(_originalCategoryCode);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public FoundObject toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FoundObject>
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
		FoundObjectImpl foundObjectImpl = new FoundObjectImpl();

		foundObjectImpl.setNumber(getNumber());
		foundObjectImpl.setDate(getDate());
		foundObjectImpl.setImageUrl(getImageUrl());
		foundObjectImpl.setCategoryCode(getCategoryCode());

		foundObjectImpl.resetOriginalValues();

		return foundObjectImpl;
	}

	@Override
	public int compareTo(FoundObject foundObject) {
		int value = 0;

		value = DateUtil.compareTo(getDate(), foundObject.getDate());

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

		if (!(object instanceof FoundObject)) {
			return false;
		}

		FoundObject foundObject = (FoundObject)object;

		String primaryKey = foundObject.getPrimaryKey();

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
		FoundObjectModelImpl foundObjectModelImpl = this;

		foundObjectModelImpl._originalCategoryCode =
			foundObjectModelImpl._categoryCode;

		foundObjectModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<FoundObject> toCacheModel() {
		FoundObjectCacheModel foundObjectCacheModel =
			new FoundObjectCacheModel();

		foundObjectCacheModel.number = getNumber();

		String number = foundObjectCacheModel.number;

		if ((number != null) && (number.length() == 0)) {
			foundObjectCacheModel.number = null;
		}

		Date date = getDate();

		if (date != null) {
			foundObjectCacheModel.date = date.getTime();
		}
		else {
			foundObjectCacheModel.date = Long.MIN_VALUE;
		}

		foundObjectCacheModel.imageUrl = getImageUrl();

		String imageUrl = foundObjectCacheModel.imageUrl;

		if ((imageUrl != null) && (imageUrl.length() == 0)) {
			foundObjectCacheModel.imageUrl = null;
		}

		foundObjectCacheModel.categoryCode = getCategoryCode();

		String categoryCode = foundObjectCacheModel.categoryCode;

		if ((categoryCode != null) && (categoryCode.length() == 0)) {
			foundObjectCacheModel.categoryCode = null;
		}

		return foundObjectCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FoundObject, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FoundObject, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FoundObject, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((FoundObject)this));
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
		Map<String, Function<FoundObject, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FoundObject, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FoundObject, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((FoundObject)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, FoundObject>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _number;
	private Date _date;
	private String _imageUrl;
	private String _categoryCode;
	private String _originalCategoryCode;
	private long _columnBitmask;
	private FoundObject _escapedModel;

}
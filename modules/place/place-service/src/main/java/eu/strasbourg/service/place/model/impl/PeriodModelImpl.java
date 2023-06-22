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

package eu.strasbourg.service.place.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.PeriodModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Period service. Represents a row in the &quot;place_Period&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PeriodModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PeriodImpl}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PeriodImpl
 * @generated
 */
public class PeriodModelImpl
	extends BaseModelImpl<Period> implements PeriodModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a period model instance should use the <code>Period</code> interface instead.
	 */
	public static final String TABLE_NAME = "place_Period";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"periodId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"defaultPeriod", Types.BOOLEAN},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP},
		{"alwaysOpen", Types.BOOLEAN}, {"RTGreenThreshold", Types.BIGINT},
		{"RTOrangeThreshold", Types.BIGINT}, {"RTRedThreshold", Types.BIGINT},
		{"RTMaxThreshold", Types.BIGINT}, {"placeId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("periodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("defaultPeriod", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("alwaysOpen", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("RTGreenThreshold", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("RTOrangeThreshold", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("RTRedThreshold", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("RTMaxThreshold", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("placeId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table place_Period (uuid_ VARCHAR(75) null,periodId LONG not null primary key,name STRING null,defaultPeriod BOOLEAN,startDate DATE null,endDate DATE null,alwaysOpen BOOLEAN,RTGreenThreshold LONG,RTOrangeThreshold LONG,RTRedThreshold LONG,RTMaxThreshold LONG,placeId LONG)";

	public static final String TABLE_SQL_DROP = "drop table place_Period";

	public static final String ORDER_BY_JPQL = " ORDER BY period.periodId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY place_Period.periodId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.place.model.Period"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.place.model.Period"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.place.model.Period"),
		true);

	public static final long PLACEID_COLUMN_BITMASK = 1L;

	public static final long UUID_COLUMN_BITMASK = 2L;

	public static final long PERIODID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"lock.expiration.time.eu.strasbourg.service.place.model.Period"));

	public PeriodModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _periodId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPeriodId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _periodId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Period.class;
	}

	@Override
	public String getModelClassName() {
		return Period.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Period, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Period, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Period, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Period)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Period, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Period, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Period)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Period, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Period, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Period>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Period.class.getClassLoader(), Period.class, ModelWrapper.class);

		try {
			Constructor<Period> constructor =
				(Constructor<Period>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Period, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Period, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Period, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Period, Object>>();
		Map<String, BiConsumer<Period, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Period, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object uuidObject) {
					period.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"periodId",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getPeriodId();
				}

			});
		attributeSetterBiConsumers.put(
			"periodId",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object periodIdObject) {
					period.setPeriodId((Long)periodIdObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object nameObject) {
					period.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"defaultPeriod",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getDefaultPeriod();
				}

			});
		attributeSetterBiConsumers.put(
			"defaultPeriod",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object defaultPeriodObject) {
					period.setDefaultPeriod((Boolean)defaultPeriodObject);
				}

			});
		attributeGetterFunctions.put(
			"startDate",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getStartDate();
				}

			});
		attributeSetterBiConsumers.put(
			"startDate",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object startDateObject) {
					period.setStartDate((Date)startDateObject);
				}

			});
		attributeGetterFunctions.put(
			"endDate",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getEndDate();
				}

			});
		attributeSetterBiConsumers.put(
			"endDate",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object endDateObject) {
					period.setEndDate((Date)endDateObject);
				}

			});
		attributeGetterFunctions.put(
			"alwaysOpen",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getAlwaysOpen();
				}

			});
		attributeSetterBiConsumers.put(
			"alwaysOpen",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object alwaysOpenObject) {
					period.setAlwaysOpen((Boolean)alwaysOpenObject);
				}

			});
		attributeGetterFunctions.put(
			"RTGreenThreshold",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getRTGreenThreshold();
				}

			});
		attributeSetterBiConsumers.put(
			"RTGreenThreshold",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(
					Period period, Object RTGreenThresholdObject) {

					period.setRTGreenThreshold((Long)RTGreenThresholdObject);
				}

			});
		attributeGetterFunctions.put(
			"RTOrangeThreshold",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getRTOrangeThreshold();
				}

			});
		attributeSetterBiConsumers.put(
			"RTOrangeThreshold",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(
					Period period, Object RTOrangeThresholdObject) {

					period.setRTOrangeThreshold((Long)RTOrangeThresholdObject);
				}

			});
		attributeGetterFunctions.put(
			"RTRedThreshold",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getRTRedThreshold();
				}

			});
		attributeSetterBiConsumers.put(
			"RTRedThreshold",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object RTRedThresholdObject) {
					period.setRTRedThreshold((Long)RTRedThresholdObject);
				}

			});
		attributeGetterFunctions.put(
			"RTMaxThreshold",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getRTMaxThreshold();
				}

			});
		attributeSetterBiConsumers.put(
			"RTMaxThreshold",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object RTMaxThresholdObject) {
					period.setRTMaxThreshold((Long)RTMaxThresholdObject);
				}

			});
		attributeGetterFunctions.put(
			"placeId",
			new Function<Period, Object>() {

				@Override
				public Object apply(Period period) {
					return period.getPlaceId();
				}

			});
		attributeSetterBiConsumers.put(
			"placeId",
			new BiConsumer<Period, Object>() {

				@Override
				public void accept(Period period, Object placeIdObject) {
					period.setPlaceId((Long)placeIdObject);
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
	public long getPeriodId() {
		return _periodId;
	}

	@Override
	public void setPeriodId(long periodId) {
		_periodId = periodId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public Boolean getDefaultPeriod() {
		return _defaultPeriod;
	}

	@Override
	public void setDefaultPeriod(Boolean defaultPeriod) {
		_defaultPeriod = defaultPeriod;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@Override
	public Boolean getAlwaysOpen() {
		return _alwaysOpen;
	}

	@Override
	public void setAlwaysOpen(Boolean alwaysOpen) {
		_alwaysOpen = alwaysOpen;
	}

	@Override
	public long getRTGreenThreshold() {
		return _RTGreenThreshold;
	}

	@Override
	public void setRTGreenThreshold(long RTGreenThreshold) {
		_RTGreenThreshold = RTGreenThreshold;
	}

	@Override
	public long getRTOrangeThreshold() {
		return _RTOrangeThreshold;
	}

	@Override
	public void setRTOrangeThreshold(long RTOrangeThreshold) {
		_RTOrangeThreshold = RTOrangeThreshold;
	}

	@Override
	public long getRTRedThreshold() {
		return _RTRedThreshold;
	}

	@Override
	public void setRTRedThreshold(long RTRedThreshold) {
		_RTRedThreshold = RTRedThreshold;
	}

	@Override
	public long getRTMaxThreshold() {
		return _RTMaxThreshold;
	}

	@Override
	public void setRTMaxThreshold(long RTMaxThreshold) {
		_RTMaxThreshold = RTMaxThreshold;
	}

	@Override
	public long getPlaceId() {
		return _placeId;
	}

	@Override
	public void setPlaceId(long placeId) {
		_columnBitmask |= PLACEID_COLUMN_BITMASK;

		if (!_setOriginalPlaceId) {
			_setOriginalPlaceId = true;

			_originalPlaceId = _placeId;
		}

		_placeId = placeId;
	}

	public long getOriginalPlaceId() {
		return _originalPlaceId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Period.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
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
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			Period.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Period toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Period>
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
		PeriodImpl periodImpl = new PeriodImpl();

		periodImpl.setUuid(getUuid());
		periodImpl.setPeriodId(getPeriodId());
		periodImpl.setName(getName());
		periodImpl.setDefaultPeriod(getDefaultPeriod());
		periodImpl.setStartDate(getStartDate());
		periodImpl.setEndDate(getEndDate());
		periodImpl.setAlwaysOpen(getAlwaysOpen());
		periodImpl.setRTGreenThreshold(getRTGreenThreshold());
		periodImpl.setRTOrangeThreshold(getRTOrangeThreshold());
		periodImpl.setRTRedThreshold(getRTRedThreshold());
		periodImpl.setRTMaxThreshold(getRTMaxThreshold());
		periodImpl.setPlaceId(getPlaceId());

		periodImpl.resetOriginalValues();

		return periodImpl;
	}

	@Override
	public int compareTo(Period period) {
		long primaryKey = period.getPrimaryKey();

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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Period)) {
			return false;
		}

		Period period = (Period)object;

		long primaryKey = period.getPrimaryKey();

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
		PeriodModelImpl periodModelImpl = this;

		periodModelImpl._originalUuid = periodModelImpl._uuid;

		periodModelImpl._originalPlaceId = periodModelImpl._placeId;

		periodModelImpl._setOriginalPlaceId = false;

		periodModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Period> toCacheModel() {
		PeriodCacheModel periodCacheModel = new PeriodCacheModel();

		periodCacheModel.uuid = getUuid();

		String uuid = periodCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			periodCacheModel.uuid = null;
		}

		periodCacheModel.periodId = getPeriodId();

		periodCacheModel.name = getName();

		String name = periodCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			periodCacheModel.name = null;
		}

		Boolean defaultPeriod = getDefaultPeriod();

		if (defaultPeriod != null) {
			periodCacheModel.defaultPeriod = defaultPeriod;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			periodCacheModel.startDate = startDate.getTime();
		}
		else {
			periodCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			periodCacheModel.endDate = endDate.getTime();
		}
		else {
			periodCacheModel.endDate = Long.MIN_VALUE;
		}

		Boolean alwaysOpen = getAlwaysOpen();

		if (alwaysOpen != null) {
			periodCacheModel.alwaysOpen = alwaysOpen;
		}

		periodCacheModel.RTGreenThreshold = getRTGreenThreshold();

		periodCacheModel.RTOrangeThreshold = getRTOrangeThreshold();

		periodCacheModel.RTRedThreshold = getRTRedThreshold();

		periodCacheModel.RTMaxThreshold = getRTMaxThreshold();

		periodCacheModel.placeId = getPlaceId();

		return periodCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Period, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Period, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Period, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Period)this));
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
		Map<String, Function<Period, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Period, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Period, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Period)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Period>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _periodId;
	private String _name;
	private String _nameCurrentLanguageId;
	private Boolean _defaultPeriod;
	private Date _startDate;
	private Date _endDate;
	private Boolean _alwaysOpen;
	private long _RTGreenThreshold;
	private long _RTOrangeThreshold;
	private long _RTRedThreshold;
	private long _RTMaxThreshold;
	private long _placeId;
	private long _originalPlaceId;
	private boolean _setOriginalPlaceId;
	private long _columnBitmask;
	private Period _escapedModel;

}
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

import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SlotModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Slot service. Represents a row in the &quot;place_Slot&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SlotModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SlotImpl}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SlotImpl
 * @generated
 */
public class SlotModelImpl extends BaseModelImpl<Slot> implements SlotModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a slot model instance should use the <code>Slot</code> interface instead.
	 */
	public static final String TABLE_NAME = "place_Slot";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"slotId", Types.BIGINT},
		{"dayOfWeek", Types.BIGINT}, {"startHour", Types.VARCHAR},
		{"endHour", Types.VARCHAR}, {"comment_", Types.VARCHAR},
		{"periodId", Types.BIGINT}, {"subPlaceId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("slotId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dayOfWeek", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("startHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("endHour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("comment_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("periodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("subPlaceId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table place_Slot (uuid_ VARCHAR(75) null,slotId LONG not null primary key,dayOfWeek LONG,startHour VARCHAR(75) null,endHour VARCHAR(75) null,comment_ STRING null,periodId LONG,subPlaceId LONG)";

	public static final String TABLE_SQL_DROP = "drop table place_Slot";

	public static final String ORDER_BY_JPQL = " ORDER BY slot.slotId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY place_Slot.slotId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.place.model.Slot"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.place.model.Slot"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.place.model.Slot"),
		true);

	public static final long PERIODID_COLUMN_BITMASK = 1L;

	public static final long SUBPLACEID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long SLOTID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.place.service.util.PropsUtil.get(
			"lock.expiration.time.eu.strasbourg.service.place.model.Slot"));

	public SlotModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _slotId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSlotId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _slotId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Slot.class;
	}

	@Override
	public String getModelClassName() {
		return Slot.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Slot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Slot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Slot, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Slot)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Slot, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Slot, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Slot)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Slot, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Slot, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Slot>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Slot.class.getClassLoader(), Slot.class, ModelWrapper.class);

		try {
			Constructor<Slot> constructor =
				(Constructor<Slot>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Slot, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Slot, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Slot, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Slot, Object>>();
		Map<String, BiConsumer<Slot, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Slot, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object uuidObject) {
					slot.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"slotId",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getSlotId();
				}

			});
		attributeSetterBiConsumers.put(
			"slotId",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object slotIdObject) {
					slot.setSlotId((Long)slotIdObject);
				}

			});
		attributeGetterFunctions.put(
			"dayOfWeek",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getDayOfWeek();
				}

			});
		attributeSetterBiConsumers.put(
			"dayOfWeek",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object dayOfWeekObject) {
					slot.setDayOfWeek((Long)dayOfWeekObject);
				}

			});
		attributeGetterFunctions.put(
			"startHour",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getStartHour();
				}

			});
		attributeSetterBiConsumers.put(
			"startHour",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object startHourObject) {
					slot.setStartHour((String)startHourObject);
				}

			});
		attributeGetterFunctions.put(
			"endHour",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getEndHour();
				}

			});
		attributeSetterBiConsumers.put(
			"endHour",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object endHourObject) {
					slot.setEndHour((String)endHourObject);
				}

			});
		attributeGetterFunctions.put(
			"comment",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getComment();
				}

			});
		attributeSetterBiConsumers.put(
			"comment",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object commentObject) {
					slot.setComment((String)commentObject);
				}

			});
		attributeGetterFunctions.put(
			"periodId",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getPeriodId();
				}

			});
		attributeSetterBiConsumers.put(
			"periodId",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object periodIdObject) {
					slot.setPeriodId((Long)periodIdObject);
				}

			});
		attributeGetterFunctions.put(
			"subPlaceId",
			new Function<Slot, Object>() {

				@Override
				public Object apply(Slot slot) {
					return slot.getSubPlaceId();
				}

			});
		attributeSetterBiConsumers.put(
			"subPlaceId",
			new BiConsumer<Slot, Object>() {

				@Override
				public void accept(Slot slot, Object subPlaceIdObject) {
					slot.setSubPlaceId((Long)subPlaceIdObject);
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
	public long getSlotId() {
		return _slotId;
	}

	@Override
	public void setSlotId(long slotId) {
		_slotId = slotId;
	}

	@Override
	public long getDayOfWeek() {
		return _dayOfWeek;
	}

	@Override
	public void setDayOfWeek(long dayOfWeek) {
		_dayOfWeek = dayOfWeek;
	}

	@Override
	public String getStartHour() {
		if (_startHour == null) {
			return "";
		}
		else {
			return _startHour;
		}
	}

	@Override
	public void setStartHour(String startHour) {
		_startHour = startHour;
	}

	@Override
	public String getEndHour() {
		if (_endHour == null) {
			return "";
		}
		else {
			return _endHour;
		}
	}

	@Override
	public void setEndHour(String endHour) {
		_endHour = endHour;
	}

	@Override
	public String getComment() {
		if (_comment == null) {
			return "";
		}
		else {
			return _comment;
		}
	}

	@Override
	public String getComment(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getComment(languageId);
	}

	@Override
	public String getComment(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getComment(languageId, useDefault);
	}

	@Override
	public String getComment(String languageId) {
		return LocalizationUtil.getLocalization(getComment(), languageId);
	}

	@Override
	public String getComment(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getComment(), languageId, useDefault);
	}

	@Override
	public String getCommentCurrentLanguageId() {
		return _commentCurrentLanguageId;
	}

	@JSON
	@Override
	public String getCommentCurrentValue() {
		Locale locale = getLocale(_commentCurrentLanguageId);

		return getComment(locale);
	}

	@Override
	public Map<Locale, String> getCommentMap() {
		return LocalizationUtil.getLocalizationMap(getComment());
	}

	@Override
	public void setComment(String comment) {
		_comment = comment;
	}

	@Override
	public void setComment(String comment, Locale locale) {
		setComment(comment, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setComment(
		String comment, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(comment)) {
			setComment(
				LocalizationUtil.updateLocalization(
					getComment(), "Comment", comment, languageId,
					defaultLanguageId));
		}
		else {
			setComment(
				LocalizationUtil.removeLocalization(
					getComment(), "Comment", languageId));
		}
	}

	@Override
	public void setCommentCurrentLanguageId(String languageId) {
		_commentCurrentLanguageId = languageId;
	}

	@Override
	public void setCommentMap(Map<Locale, String> commentMap) {
		setCommentMap(commentMap, LocaleUtil.getDefault());
	}

	@Override
	public void setCommentMap(
		Map<Locale, String> commentMap, Locale defaultLocale) {

		if (commentMap == null) {
			return;
		}

		setComment(
			LocalizationUtil.updateLocalization(
				commentMap, getComment(), "Comment",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public long getPeriodId() {
		return _periodId;
	}

	@Override
	public void setPeriodId(long periodId) {
		_columnBitmask |= PERIODID_COLUMN_BITMASK;

		if (!_setOriginalPeriodId) {
			_setOriginalPeriodId = true;

			_originalPeriodId = _periodId;
		}

		_periodId = periodId;
	}

	public long getOriginalPeriodId() {
		return _originalPeriodId;
	}

	@Override
	public long getSubPlaceId() {
		return _subPlaceId;
	}

	@Override
	public void setSubPlaceId(long subPlaceId) {
		_columnBitmask |= SUBPLACEID_COLUMN_BITMASK;

		if (!_setOriginalSubPlaceId) {
			_setOriginalSubPlaceId = true;

			_originalSubPlaceId = _subPlaceId;
		}

		_subPlaceId = subPlaceId;
	}

	public long getOriginalSubPlaceId() {
		return _originalSubPlaceId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Slot.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> commentMap = getCommentMap();

		for (Map.Entry<Locale, String> entry : commentMap.entrySet()) {
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
		String xml = getComment();

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
			Slot.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String comment = getComment(defaultLocale);

		if (Validator.isNull(comment)) {
			setComment(getComment(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setComment(getComment(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Slot toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Slot>
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
		SlotImpl slotImpl = new SlotImpl();

		slotImpl.setUuid(getUuid());
		slotImpl.setSlotId(getSlotId());
		slotImpl.setDayOfWeek(getDayOfWeek());
		slotImpl.setStartHour(getStartHour());
		slotImpl.setEndHour(getEndHour());
		slotImpl.setComment(getComment());
		slotImpl.setPeriodId(getPeriodId());
		slotImpl.setSubPlaceId(getSubPlaceId());

		slotImpl.resetOriginalValues();

		return slotImpl;
	}

	@Override
	public int compareTo(Slot slot) {
		long primaryKey = slot.getPrimaryKey();

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

		if (!(object instanceof Slot)) {
			return false;
		}

		Slot slot = (Slot)object;

		long primaryKey = slot.getPrimaryKey();

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
		SlotModelImpl slotModelImpl = this;

		slotModelImpl._originalUuid = slotModelImpl._uuid;

		slotModelImpl._originalPeriodId = slotModelImpl._periodId;

		slotModelImpl._setOriginalPeriodId = false;

		slotModelImpl._originalSubPlaceId = slotModelImpl._subPlaceId;

		slotModelImpl._setOriginalSubPlaceId = false;

		slotModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Slot> toCacheModel() {
		SlotCacheModel slotCacheModel = new SlotCacheModel();

		slotCacheModel.uuid = getUuid();

		String uuid = slotCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			slotCacheModel.uuid = null;
		}

		slotCacheModel.slotId = getSlotId();

		slotCacheModel.dayOfWeek = getDayOfWeek();

		slotCacheModel.startHour = getStartHour();

		String startHour = slotCacheModel.startHour;

		if ((startHour != null) && (startHour.length() == 0)) {
			slotCacheModel.startHour = null;
		}

		slotCacheModel.endHour = getEndHour();

		String endHour = slotCacheModel.endHour;

		if ((endHour != null) && (endHour.length() == 0)) {
			slotCacheModel.endHour = null;
		}

		slotCacheModel.comment = getComment();

		String comment = slotCacheModel.comment;

		if ((comment != null) && (comment.length() == 0)) {
			slotCacheModel.comment = null;
		}

		slotCacheModel.periodId = getPeriodId();

		slotCacheModel.subPlaceId = getSubPlaceId();

		return slotCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Slot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Slot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Slot, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Slot)this));
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
		Map<String, Function<Slot, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Slot, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Slot, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Slot)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Slot>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _slotId;
	private long _dayOfWeek;
	private String _startHour;
	private String _endHour;
	private String _comment;
	private String _commentCurrentLanguageId;
	private long _periodId;
	private long _originalPeriodId;
	private boolean _setOriginalPeriodId;
	private long _subPlaceId;
	private long _originalSubPlaceId;
	private boolean _setOriginalSubPlaceId;
	private long _columnBitmask;
	private Slot _escapedModel;

}
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

package eu.strasbourg.service.agenda.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.agenda.model.ImportReport;
import eu.strasbourg.service.agenda.model.ImportReportModel;

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
 * The base model implementation for the ImportReport service. Represents a row in the &quot;agenda_ImportReport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ImportReportModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImportReportImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportImpl
 * @generated
 */
public class ImportReportModelImpl
	extends BaseModelImpl<ImportReport> implements ImportReportModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a import report model instance should use the <code>ImportReport</code> interface instead.
	 */
	public static final String TABLE_NAME = "agenda_ImportReport";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"reportId", Types.BIGINT},
		{"provider", Types.VARCHAR}, {"filename", Types.VARCHAR},
		{"status", Types.BIGINT}, {"globalErrorCause", Types.VARCHAR},
		{"newEventsCount", Types.BIGINT}, {"modifiedEventsCount", Types.BIGINT},
		{"errorEventsCount", Types.BIGINT},
		{"unmodifiedEventsCount", Types.BIGINT},
		{"deletedEventsCount", Types.BIGINT},
		{"newManifestationsCount", Types.BIGINT},
		{"modifiedManifestationsCount", Types.BIGINT},
		{"errorManifestationsCount", Types.BIGINT},
		{"unmodifiedManifestationsCount", Types.BIGINT},
		{"deletedManifestationsCount", Types.BIGINT},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("provider", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("filename", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("globalErrorCause", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("newEventsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedEventsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("errorEventsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("unmodifiedEventsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("deletedEventsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("newManifestationsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedManifestationsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("errorManifestationsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("unmodifiedManifestationsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("deletedManifestationsCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table agenda_ImportReport (uuid_ VARCHAR(75) null,reportId LONG not null primary key,provider VARCHAR(75) null,filename VARCHAR(75) null,status LONG,globalErrorCause VARCHAR(75) null,newEventsCount LONG,modifiedEventsCount LONG,errorEventsCount LONG,unmodifiedEventsCount LONG,deletedEventsCount LONG,newManifestationsCount LONG,modifiedManifestationsCount LONG,errorManifestationsCount LONG,unmodifiedManifestationsCount LONG,deletedManifestationsCount LONG,startDate DATE null,endDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table agenda_ImportReport";

	public static final String ORDER_BY_JPQL =
		" ORDER BY importReport.reportId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY agenda_ImportReport.reportId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.agenda.service.util.PropsUtil.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.agenda.model.ImportReport"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.agenda.service.util.PropsUtil.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.agenda.model.ImportReport"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.service.agenda.service.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.eu.strasbourg.service.agenda.model.ImportReport"),
		true);

	public static final long UUID_COLUMN_BITMASK = 1L;

	public static final long REPORTID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.service.agenda.service.util.PropsUtil.get(
			"lock.expiration.time.eu.strasbourg.service.agenda.model.ImportReport"));

	public ImportReportModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _reportId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReportId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reportId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ImportReport.class;
	}

	@Override
	public String getModelClassName() {
		return ImportReport.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ImportReport, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ImportReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ImportReport, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ImportReport)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ImportReport, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ImportReport, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ImportReport)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ImportReport, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ImportReport, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ImportReport>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ImportReport.class.getClassLoader(), ImportReport.class,
			ModelWrapper.class);

		try {
			Constructor<ImportReport> constructor =
				(Constructor<ImportReport>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ImportReport, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ImportReport, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ImportReport, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ImportReport, Object>>();
		Map<String, BiConsumer<ImportReport, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ImportReport, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object uuidObject) {

					importReport.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"reportId",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getReportId();
				}

			});
		attributeSetterBiConsumers.put(
			"reportId",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object reportIdObject) {

					importReport.setReportId((Long)reportIdObject);
				}

			});
		attributeGetterFunctions.put(
			"provider",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getProvider();
				}

			});
		attributeSetterBiConsumers.put(
			"provider",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object providerObject) {

					importReport.setProvider((String)providerObject);
				}

			});
		attributeGetterFunctions.put(
			"filename",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getFilename();
				}

			});
		attributeSetterBiConsumers.put(
			"filename",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object filenameObject) {

					importReport.setFilename((String)filenameObject);
				}

			});
		attributeGetterFunctions.put(
			"status",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getStatus();
				}

			});
		attributeSetterBiConsumers.put(
			"status",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object statusObject) {

					importReport.setStatus((Long)statusObject);
				}

			});
		attributeGetterFunctions.put(
			"globalErrorCause",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getGlobalErrorCause();
				}

			});
		attributeSetterBiConsumers.put(
			"globalErrorCause",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object globalErrorCauseObject) {

					importReport.setGlobalErrorCause(
						(String)globalErrorCauseObject);
				}

			});
		attributeGetterFunctions.put(
			"newEventsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getNewEventsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"newEventsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object newEventsCountObject) {

					importReport.setNewEventsCount((Long)newEventsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedEventsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getModifiedEventsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedEventsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object modifiedEventsCountObject) {

					importReport.setModifiedEventsCount(
						(Long)modifiedEventsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"errorEventsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getErrorEventsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"errorEventsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object errorEventsCountObject) {

					importReport.setErrorEventsCount(
						(Long)errorEventsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"unmodifiedEventsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getUnmodifiedEventsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"unmodifiedEventsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object unmodifiedEventsCountObject) {

					importReport.setUnmodifiedEventsCount(
						(Long)unmodifiedEventsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"deletedEventsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getDeletedEventsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"deletedEventsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object deletedEventsCountObject) {

					importReport.setDeletedEventsCount(
						(Long)deletedEventsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"newManifestationsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getNewManifestationsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"newManifestationsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object newManifestationsCountObject) {

					importReport.setNewManifestationsCount(
						(Long)newManifestationsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedManifestationsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getModifiedManifestationsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedManifestationsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object modifiedManifestationsCountObject) {

					importReport.setModifiedManifestationsCount(
						(Long)modifiedManifestationsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"errorManifestationsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getErrorManifestationsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"errorManifestationsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object errorManifestationsCountObject) {

					importReport.setErrorManifestationsCount(
						(Long)errorManifestationsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"unmodifiedManifestationsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getUnmodifiedManifestationsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"unmodifiedManifestationsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object unmodifiedManifestationsCountObject) {

					importReport.setUnmodifiedManifestationsCount(
						(Long)unmodifiedManifestationsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"deletedManifestationsCount",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getDeletedManifestationsCount();
				}

			});
		attributeSetterBiConsumers.put(
			"deletedManifestationsCount",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport,
					Object deletedManifestationsCountObject) {

					importReport.setDeletedManifestationsCount(
						(Long)deletedManifestationsCountObject);
				}

			});
		attributeGetterFunctions.put(
			"startDate",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getStartDate();
				}

			});
		attributeSetterBiConsumers.put(
			"startDate",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object startDateObject) {

					importReport.setStartDate((Date)startDateObject);
				}

			});
		attributeGetterFunctions.put(
			"endDate",
			new Function<ImportReport, Object>() {

				@Override
				public Object apply(ImportReport importReport) {
					return importReport.getEndDate();
				}

			});
		attributeSetterBiConsumers.put(
			"endDate",
			new BiConsumer<ImportReport, Object>() {

				@Override
				public void accept(
					ImportReport importReport, Object endDateObject) {

					importReport.setEndDate((Date)endDateObject);
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
	public long getReportId() {
		return _reportId;
	}

	@Override
	public void setReportId(long reportId) {
		_reportId = reportId;
	}

	@Override
	public String getProvider() {
		if (_provider == null) {
			return "";
		}
		else {
			return _provider;
		}
	}

	@Override
	public void setProvider(String provider) {
		_provider = provider;
	}

	@Override
	public String getFilename() {
		if (_filename == null) {
			return "";
		}
		else {
			return _filename;
		}
	}

	@Override
	public void setFilename(String filename) {
		_filename = filename;
	}

	@Override
	public long getStatus() {
		return _status;
	}

	@Override
	public void setStatus(long status) {
		_status = status;
	}

	@Override
	public String getGlobalErrorCause() {
		if (_globalErrorCause == null) {
			return "";
		}
		else {
			return _globalErrorCause;
		}
	}

	@Override
	public void setGlobalErrorCause(String globalErrorCause) {
		_globalErrorCause = globalErrorCause;
	}

	@Override
	public long getNewEventsCount() {
		return _newEventsCount;
	}

	@Override
	public void setNewEventsCount(long newEventsCount) {
		_newEventsCount = newEventsCount;
	}

	@Override
	public long getModifiedEventsCount() {
		return _modifiedEventsCount;
	}

	@Override
	public void setModifiedEventsCount(long modifiedEventsCount) {
		_modifiedEventsCount = modifiedEventsCount;
	}

	@Override
	public long getErrorEventsCount() {
		return _errorEventsCount;
	}

	@Override
	public void setErrorEventsCount(long errorEventsCount) {
		_errorEventsCount = errorEventsCount;
	}

	@Override
	public long getUnmodifiedEventsCount() {
		return _unmodifiedEventsCount;
	}

	@Override
	public void setUnmodifiedEventsCount(long unmodifiedEventsCount) {
		_unmodifiedEventsCount = unmodifiedEventsCount;
	}

	@Override
	public long getDeletedEventsCount() {
		return _deletedEventsCount;
	}

	@Override
	public void setDeletedEventsCount(long deletedEventsCount) {
		_deletedEventsCount = deletedEventsCount;
	}

	@Override
	public long getNewManifestationsCount() {
		return _newManifestationsCount;
	}

	@Override
	public void setNewManifestationsCount(long newManifestationsCount) {
		_newManifestationsCount = newManifestationsCount;
	}

	@Override
	public long getModifiedManifestationsCount() {
		return _modifiedManifestationsCount;
	}

	@Override
	public void setModifiedManifestationsCount(
		long modifiedManifestationsCount) {

		_modifiedManifestationsCount = modifiedManifestationsCount;
	}

	@Override
	public long getErrorManifestationsCount() {
		return _errorManifestationsCount;
	}

	@Override
	public void setErrorManifestationsCount(long errorManifestationsCount) {
		_errorManifestationsCount = errorManifestationsCount;
	}

	@Override
	public long getUnmodifiedManifestationsCount() {
		return _unmodifiedManifestationsCount;
	}

	@Override
	public void setUnmodifiedManifestationsCount(
		long unmodifiedManifestationsCount) {

		_unmodifiedManifestationsCount = unmodifiedManifestationsCount;
	}

	@Override
	public long getDeletedManifestationsCount() {
		return _deletedManifestationsCount;
	}

	@Override
	public void setDeletedManifestationsCount(long deletedManifestationsCount) {
		_deletedManifestationsCount = deletedManifestationsCount;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, ImportReport.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ImportReport toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ImportReport>
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
		ImportReportImpl importReportImpl = new ImportReportImpl();

		importReportImpl.setUuid(getUuid());
		importReportImpl.setReportId(getReportId());
		importReportImpl.setProvider(getProvider());
		importReportImpl.setFilename(getFilename());
		importReportImpl.setStatus(getStatus());
		importReportImpl.setGlobalErrorCause(getGlobalErrorCause());
		importReportImpl.setNewEventsCount(getNewEventsCount());
		importReportImpl.setModifiedEventsCount(getModifiedEventsCount());
		importReportImpl.setErrorEventsCount(getErrorEventsCount());
		importReportImpl.setUnmodifiedEventsCount(getUnmodifiedEventsCount());
		importReportImpl.setDeletedEventsCount(getDeletedEventsCount());
		importReportImpl.setNewManifestationsCount(getNewManifestationsCount());
		importReportImpl.setModifiedManifestationsCount(
			getModifiedManifestationsCount());
		importReportImpl.setErrorManifestationsCount(
			getErrorManifestationsCount());
		importReportImpl.setUnmodifiedManifestationsCount(
			getUnmodifiedManifestationsCount());
		importReportImpl.setDeletedManifestationsCount(
			getDeletedManifestationsCount());
		importReportImpl.setStartDate(getStartDate());
		importReportImpl.setEndDate(getEndDate());

		importReportImpl.resetOriginalValues();

		return importReportImpl;
	}

	@Override
	public int compareTo(ImportReport importReport) {
		long primaryKey = importReport.getPrimaryKey();

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

		if (!(object instanceof ImportReport)) {
			return false;
		}

		ImportReport importReport = (ImportReport)object;

		long primaryKey = importReport.getPrimaryKey();

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
		ImportReportModelImpl importReportModelImpl = this;

		importReportModelImpl._originalUuid = importReportModelImpl._uuid;

		importReportModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ImportReport> toCacheModel() {
		ImportReportCacheModel importReportCacheModel =
			new ImportReportCacheModel();

		importReportCacheModel.uuid = getUuid();

		String uuid = importReportCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			importReportCacheModel.uuid = null;
		}

		importReportCacheModel.reportId = getReportId();

		importReportCacheModel.provider = getProvider();

		String provider = importReportCacheModel.provider;

		if ((provider != null) && (provider.length() == 0)) {
			importReportCacheModel.provider = null;
		}

		importReportCacheModel.filename = getFilename();

		String filename = importReportCacheModel.filename;

		if ((filename != null) && (filename.length() == 0)) {
			importReportCacheModel.filename = null;
		}

		importReportCacheModel.status = getStatus();

		importReportCacheModel.globalErrorCause = getGlobalErrorCause();

		String globalErrorCause = importReportCacheModel.globalErrorCause;

		if ((globalErrorCause != null) && (globalErrorCause.length() == 0)) {
			importReportCacheModel.globalErrorCause = null;
		}

		importReportCacheModel.newEventsCount = getNewEventsCount();

		importReportCacheModel.modifiedEventsCount = getModifiedEventsCount();

		importReportCacheModel.errorEventsCount = getErrorEventsCount();

		importReportCacheModel.unmodifiedEventsCount =
			getUnmodifiedEventsCount();

		importReportCacheModel.deletedEventsCount = getDeletedEventsCount();

		importReportCacheModel.newManifestationsCount =
			getNewManifestationsCount();

		importReportCacheModel.modifiedManifestationsCount =
			getModifiedManifestationsCount();

		importReportCacheModel.errorManifestationsCount =
			getErrorManifestationsCount();

		importReportCacheModel.unmodifiedManifestationsCount =
			getUnmodifiedManifestationsCount();

		importReportCacheModel.deletedManifestationsCount =
			getDeletedManifestationsCount();

		Date startDate = getStartDate();

		if (startDate != null) {
			importReportCacheModel.startDate = startDate.getTime();
		}
		else {
			importReportCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			importReportCacheModel.endDate = endDate.getTime();
		}
		else {
			importReportCacheModel.endDate = Long.MIN_VALUE;
		}

		return importReportCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ImportReport, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ImportReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ImportReport, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ImportReport)this));
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
		Map<String, Function<ImportReport, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ImportReport, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ImportReport, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ImportReport)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ImportReport>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _reportId;
	private String _provider;
	private String _filename;
	private long _status;
	private String _globalErrorCause;
	private long _newEventsCount;
	private long _modifiedEventsCount;
	private long _errorEventsCount;
	private long _unmodifiedEventsCount;
	private long _deletedEventsCount;
	private long _newManifestationsCount;
	private long _modifiedManifestationsCount;
	private long _errorManifestationsCount;
	private long _unmodifiedManifestationsCount;
	private long _deletedManifestationsCount;
	private Date _startDate;
	private Date _endDate;
	private long _columnBitmask;
	private ImportReport _escapedModel;

}
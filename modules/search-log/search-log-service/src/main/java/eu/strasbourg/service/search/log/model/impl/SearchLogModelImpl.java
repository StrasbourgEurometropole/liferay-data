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

package eu.strasbourg.service.search.log.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.search.log.model.SearchLog;
import eu.strasbourg.service.search.log.model.SearchLogModel;
import eu.strasbourg.service.search.log.model.SearchLogSoap;

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
 * The base model implementation for the SearchLog service. Represents a row in the &quot;search_SearchLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SearchLogModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SearchLogImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see SearchLogImpl
 * @generated
 */
@JSON(strict = true)
public class SearchLogModelImpl
	extends BaseModelImpl<SearchLog> implements SearchLogModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a search log model instance should use the <code>SearchLog</code> interface instead.
	 */
	public static final String TABLE_NAME = "search_SearchLog";

	public static final Object[][] TABLE_COLUMNS = {
		{"searchLogId", Types.BIGINT}, {"keywords", Types.VARCHAR},
		{"searchTime", Types.BIGINT}, {"resultCount", Types.BIGINT},
		{"result1ClassId", Types.BIGINT}, {"result1ClassPK", Types.BIGINT},
		{"result1Title", Types.VARCHAR}, {"result2ClassId", Types.BIGINT},
		{"result2ClassPK", Types.BIGINT}, {"result2Title", Types.VARCHAR},
		{"result3ClassId", Types.BIGINT}, {"result3ClassPK", Types.BIGINT},
		{"result3Title", Types.VARCHAR}, {"userTargetClassId", Types.BIGINT},
		{"userTargetClassPK", Types.BIGINT}, {"userTargetTitle", Types.VARCHAR},
		{"groupId", Types.BIGINT}, {"layoutId", Types.BIGINT},
		{"layoutFriendlyURL", Types.VARCHAR}, {"language", Types.VARCHAR},
		{"date_", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("searchLogId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("keywords", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("searchTime", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("resultCount", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result1ClassId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result1ClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result1Title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("result2ClassId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result2ClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result2Title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("result3ClassId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result3ClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result3Title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userTargetClassId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userTargetClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userTargetTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutFriendlyURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("language", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("date_", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table search_SearchLog (searchLogId LONG not null primary key,keywords VARCHAR(400) null,searchTime LONG,resultCount LONG,result1ClassId LONG,result1ClassPK LONG,result1Title VARCHAR(800) null,result2ClassId LONG,result2ClassPK LONG,result2Title VARCHAR(800) null,result3ClassId LONG,result3ClassPK LONG,result3Title VARCHAR(800) null,userTargetClassId LONG,userTargetClassPK LONG,userTargetTitle VARCHAR(800) null,groupId LONG,layoutId LONG,layoutFriendlyURL VARCHAR(75) null,language VARCHAR(75) null,date_ DATE null)";

	public static final String TABLE_SQL_DROP = "drop table search_SearchLog";

	public static final String ORDER_BY_JPQL = " ORDER BY searchLog.date DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY search_SearchLog.date_ DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.model.search.log.service.util.PropsUtil.get(
			"value.object.entity.cache.enabled.eu.strasbourg.service.search.log.model.SearchLog"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		eu.strasbourg.model.search.log.service.util.PropsUtil.get(
			"value.object.finder.cache.enabled.eu.strasbourg.service.search.log.model.SearchLog"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SearchLog toModel(SearchLogSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SearchLog model = new SearchLogImpl();

		model.setSearchLogId(soapModel.getSearchLogId());
		model.setKeywords(soapModel.getKeywords());
		model.setSearchTime(soapModel.getSearchTime());
		model.setResultCount(soapModel.getResultCount());
		model.setResult1ClassId(soapModel.getResult1ClassId());
		model.setResult1ClassPK(soapModel.getResult1ClassPK());
		model.setResult1Title(soapModel.getResult1Title());
		model.setResult2ClassId(soapModel.getResult2ClassId());
		model.setResult2ClassPK(soapModel.getResult2ClassPK());
		model.setResult2Title(soapModel.getResult2Title());
		model.setResult3ClassId(soapModel.getResult3ClassId());
		model.setResult3ClassPK(soapModel.getResult3ClassPK());
		model.setResult3Title(soapModel.getResult3Title());
		model.setUserTargetClassId(soapModel.getUserTargetClassId());
		model.setUserTargetClassPK(soapModel.getUserTargetClassPK());
		model.setUserTargetTitle(soapModel.getUserTargetTitle());
		model.setGroupId(soapModel.getGroupId());
		model.setLayoutId(soapModel.getLayoutId());
		model.setLayoutFriendlyURL(soapModel.getLayoutFriendlyURL());
		model.setLanguage(soapModel.getLanguage());
		model.setDate(soapModel.getDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SearchLog> toModels(SearchLogSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SearchLog> models = new ArrayList<SearchLog>(soapModels.length);

		for (SearchLogSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		eu.strasbourg.model.search.log.service.util.PropsUtil.get(
			"lock.expiration.time.eu.strasbourg.service.search.log.model.SearchLog"));

	public SearchLogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _searchLogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSearchLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _searchLogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SearchLog.class;
	}

	@Override
	public String getModelClassName() {
		return SearchLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SearchLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SearchLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SearchLog, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((SearchLog)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SearchLog, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SearchLog, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SearchLog)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SearchLog, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SearchLog, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SearchLog>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SearchLog.class.getClassLoader(), SearchLog.class,
			ModelWrapper.class);

		try {
			Constructor<SearchLog> constructor =
				(Constructor<SearchLog>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SearchLog, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SearchLog, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SearchLog, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SearchLog, Object>>();
		Map<String, BiConsumer<SearchLog, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SearchLog, ?>>();

		attributeGetterFunctions.put(
			"searchLogId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getSearchLogId();
				}

			});
		attributeSetterBiConsumers.put(
			"searchLogId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object searchLogIdObject) {

					searchLog.setSearchLogId((Long)searchLogIdObject);
				}

			});
		attributeGetterFunctions.put(
			"keywords",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getKeywords();
				}

			});
		attributeSetterBiConsumers.put(
			"keywords",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(SearchLog searchLog, Object keywordsObject) {
					searchLog.setKeywords((String)keywordsObject);
				}

			});
		attributeGetterFunctions.put(
			"searchTime",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getSearchTime();
				}

			});
		attributeSetterBiConsumers.put(
			"searchTime",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object searchTimeObject) {

					searchLog.setSearchTime((Long)searchTimeObject);
				}

			});
		attributeGetterFunctions.put(
			"resultCount",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResultCount();
				}

			});
		attributeSetterBiConsumers.put(
			"resultCount",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object resultCountObject) {

					searchLog.setResultCount((Long)resultCountObject);
				}

			});
		attributeGetterFunctions.put(
			"result1ClassId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult1ClassId();
				}

			});
		attributeSetterBiConsumers.put(
			"result1ClassId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result1ClassIdObject) {

					searchLog.setResult1ClassId((Long)result1ClassIdObject);
				}

			});
		attributeGetterFunctions.put(
			"result1ClassPK",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult1ClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"result1ClassPK",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result1ClassPKObject) {

					searchLog.setResult1ClassPK((Long)result1ClassPKObject);
				}

			});
		attributeGetterFunctions.put(
			"result1Title",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult1Title();
				}

			});
		attributeSetterBiConsumers.put(
			"result1Title",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result1TitleObject) {

					searchLog.setResult1Title((String)result1TitleObject);
				}

			});
		attributeGetterFunctions.put(
			"result2ClassId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult2ClassId();
				}

			});
		attributeSetterBiConsumers.put(
			"result2ClassId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result2ClassIdObject) {

					searchLog.setResult2ClassId((Long)result2ClassIdObject);
				}

			});
		attributeGetterFunctions.put(
			"result2ClassPK",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult2ClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"result2ClassPK",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result2ClassPKObject) {

					searchLog.setResult2ClassPK((Long)result2ClassPKObject);
				}

			});
		attributeGetterFunctions.put(
			"result2Title",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult2Title();
				}

			});
		attributeSetterBiConsumers.put(
			"result2Title",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result2TitleObject) {

					searchLog.setResult2Title((String)result2TitleObject);
				}

			});
		attributeGetterFunctions.put(
			"result3ClassId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult3ClassId();
				}

			});
		attributeSetterBiConsumers.put(
			"result3ClassId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result3ClassIdObject) {

					searchLog.setResult3ClassId((Long)result3ClassIdObject);
				}

			});
		attributeGetterFunctions.put(
			"result3ClassPK",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult3ClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"result3ClassPK",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result3ClassPKObject) {

					searchLog.setResult3ClassPK((Long)result3ClassPKObject);
				}

			});
		attributeGetterFunctions.put(
			"result3Title",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getResult3Title();
				}

			});
		attributeSetterBiConsumers.put(
			"result3Title",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object result3TitleObject) {

					searchLog.setResult3Title((String)result3TitleObject);
				}

			});
		attributeGetterFunctions.put(
			"userTargetClassId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getUserTargetClassId();
				}

			});
		attributeSetterBiConsumers.put(
			"userTargetClassId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object userTargetClassIdObject) {

					searchLog.setUserTargetClassId(
						(Long)userTargetClassIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userTargetClassPK",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getUserTargetClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"userTargetClassPK",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object userTargetClassPKObject) {

					searchLog.setUserTargetClassPK(
						(Long)userTargetClassPKObject);
				}

			});
		attributeGetterFunctions.put(
			"userTargetTitle",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getUserTargetTitle();
				}

			});
		attributeSetterBiConsumers.put(
			"userTargetTitle",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object userTargetTitleObject) {

					searchLog.setUserTargetTitle((String)userTargetTitleObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(SearchLog searchLog, Object groupIdObject) {
					searchLog.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"layoutId",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getLayoutId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutId",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(SearchLog searchLog, Object layoutIdObject) {
					searchLog.setLayoutId((Long)layoutIdObject);
				}

			});
		attributeGetterFunctions.put(
			"layoutFriendlyURL",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getLayoutFriendlyURL();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutFriendlyURL",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(
					SearchLog searchLog, Object layoutFriendlyURLObject) {

					searchLog.setLayoutFriendlyURL(
						(String)layoutFriendlyURLObject);
				}

			});
		attributeGetterFunctions.put(
			"language",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getLanguage();
				}

			});
		attributeSetterBiConsumers.put(
			"language",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(SearchLog searchLog, Object languageObject) {
					searchLog.setLanguage((String)languageObject);
				}

			});
		attributeGetterFunctions.put(
			"date",
			new Function<SearchLog, Object>() {

				@Override
				public Object apply(SearchLog searchLog) {
					return searchLog.getDate();
				}

			});
		attributeSetterBiConsumers.put(
			"date",
			new BiConsumer<SearchLog, Object>() {

				@Override
				public void accept(SearchLog searchLog, Object dateObject) {
					searchLog.setDate((Date)dateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getSearchLogId() {
		return _searchLogId;
	}

	@Override
	public void setSearchLogId(long searchLogId) {
		_searchLogId = searchLogId;
	}

	@JSON
	@Override
	public String getKeywords() {
		if (_keywords == null) {
			return "";
		}
		else {
			return _keywords;
		}
	}

	@Override
	public void setKeywords(String keywords) {
		_keywords = keywords;
	}

	@JSON
	@Override
	public long getSearchTime() {
		return _searchTime;
	}

	@Override
	public void setSearchTime(long searchTime) {
		_searchTime = searchTime;
	}

	@JSON
	@Override
	public long getResultCount() {
		return _resultCount;
	}

	@Override
	public void setResultCount(long resultCount) {
		_resultCount = resultCount;
	}

	@JSON
	@Override
	public long getResult1ClassId() {
		return _result1ClassId;
	}

	@Override
	public void setResult1ClassId(long result1ClassId) {
		_result1ClassId = result1ClassId;
	}

	@JSON
	@Override
	public long getResult1ClassPK() {
		return _result1ClassPK;
	}

	@Override
	public void setResult1ClassPK(long result1ClassPK) {
		_result1ClassPK = result1ClassPK;
	}

	@JSON
	@Override
	public String getResult1Title() {
		if (_result1Title == null) {
			return "";
		}
		else {
			return _result1Title;
		}
	}

	@Override
	public void setResult1Title(String result1Title) {
		_result1Title = result1Title;
	}

	@JSON
	@Override
	public long getResult2ClassId() {
		return _result2ClassId;
	}

	@Override
	public void setResult2ClassId(long result2ClassId) {
		_result2ClassId = result2ClassId;
	}

	@JSON
	@Override
	public long getResult2ClassPK() {
		return _result2ClassPK;
	}

	@Override
	public void setResult2ClassPK(long result2ClassPK) {
		_result2ClassPK = result2ClassPK;
	}

	@JSON
	@Override
	public String getResult2Title() {
		if (_result2Title == null) {
			return "";
		}
		else {
			return _result2Title;
		}
	}

	@Override
	public void setResult2Title(String result2Title) {
		_result2Title = result2Title;
	}

	@JSON
	@Override
	public long getResult3ClassId() {
		return _result3ClassId;
	}

	@Override
	public void setResult3ClassId(long result3ClassId) {
		_result3ClassId = result3ClassId;
	}

	@JSON
	@Override
	public long getResult3ClassPK() {
		return _result3ClassPK;
	}

	@Override
	public void setResult3ClassPK(long result3ClassPK) {
		_result3ClassPK = result3ClassPK;
	}

	@JSON
	@Override
	public String getResult3Title() {
		if (_result3Title == null) {
			return "";
		}
		else {
			return _result3Title;
		}
	}

	@Override
	public void setResult3Title(String result3Title) {
		_result3Title = result3Title;
	}

	@JSON
	@Override
	public long getUserTargetClassId() {
		return _userTargetClassId;
	}

	@Override
	public void setUserTargetClassId(long userTargetClassId) {
		_userTargetClassId = userTargetClassId;
	}

	@JSON
	@Override
	public long getUserTargetClassPK() {
		return _userTargetClassPK;
	}

	@Override
	public void setUserTargetClassPK(long userTargetClassPK) {
		_userTargetClassPK = userTargetClassPK;
	}

	@JSON
	@Override
	public String getUserTargetTitle() {
		if (_userTargetTitle == null) {
			return "";
		}
		else {
			return _userTargetTitle;
		}
	}

	@Override
	public void setUserTargetTitle(String userTargetTitle) {
		_userTargetTitle = userTargetTitle;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public Long getLayoutId() {
		return _layoutId;
	}

	@Override
	public void setLayoutId(Long layoutId) {
		_layoutId = layoutId;
	}

	@JSON
	@Override
	public String getLayoutFriendlyURL() {
		if (_layoutFriendlyURL == null) {
			return "";
		}
		else {
			return _layoutFriendlyURL;
		}
	}

	@Override
	public void setLayoutFriendlyURL(String layoutFriendlyURL) {
		_layoutFriendlyURL = layoutFriendlyURL;
	}

	@JSON
	@Override
	public String getLanguage() {
		if (_language == null) {
			return "";
		}
		else {
			return _language;
		}
	}

	@Override
	public void setLanguage(String language) {
		_language = language;
	}

	@JSON
	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public void setDate(Date date) {
		_date = date;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, SearchLog.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SearchLog toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SearchLog>
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
		SearchLogImpl searchLogImpl = new SearchLogImpl();

		searchLogImpl.setSearchLogId(getSearchLogId());
		searchLogImpl.setKeywords(getKeywords());
		searchLogImpl.setSearchTime(getSearchTime());
		searchLogImpl.setResultCount(getResultCount());
		searchLogImpl.setResult1ClassId(getResult1ClassId());
		searchLogImpl.setResult1ClassPK(getResult1ClassPK());
		searchLogImpl.setResult1Title(getResult1Title());
		searchLogImpl.setResult2ClassId(getResult2ClassId());
		searchLogImpl.setResult2ClassPK(getResult2ClassPK());
		searchLogImpl.setResult2Title(getResult2Title());
		searchLogImpl.setResult3ClassId(getResult3ClassId());
		searchLogImpl.setResult3ClassPK(getResult3ClassPK());
		searchLogImpl.setResult3Title(getResult3Title());
		searchLogImpl.setUserTargetClassId(getUserTargetClassId());
		searchLogImpl.setUserTargetClassPK(getUserTargetClassPK());
		searchLogImpl.setUserTargetTitle(getUserTargetTitle());
		searchLogImpl.setGroupId(getGroupId());
		searchLogImpl.setLayoutId(getLayoutId());
		searchLogImpl.setLayoutFriendlyURL(getLayoutFriendlyURL());
		searchLogImpl.setLanguage(getLanguage());
		searchLogImpl.setDate(getDate());

		searchLogImpl.resetOriginalValues();

		return searchLogImpl;
	}

	@Override
	public int compareTo(SearchLog searchLog) {
		int value = 0;

		value = DateUtil.compareTo(getDate(), searchLog.getDate());

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

		if (!(object instanceof SearchLog)) {
			return false;
		}

		SearchLog searchLog = (SearchLog)object;

		long primaryKey = searchLog.getPrimaryKey();

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
	}

	@Override
	public CacheModel<SearchLog> toCacheModel() {
		SearchLogCacheModel searchLogCacheModel = new SearchLogCacheModel();

		searchLogCacheModel.searchLogId = getSearchLogId();

		searchLogCacheModel.keywords = getKeywords();

		String keywords = searchLogCacheModel.keywords;

		if ((keywords != null) && (keywords.length() == 0)) {
			searchLogCacheModel.keywords = null;
		}

		searchLogCacheModel.searchTime = getSearchTime();

		searchLogCacheModel.resultCount = getResultCount();

		searchLogCacheModel.result1ClassId = getResult1ClassId();

		searchLogCacheModel.result1ClassPK = getResult1ClassPK();

		searchLogCacheModel.result1Title = getResult1Title();

		String result1Title = searchLogCacheModel.result1Title;

		if ((result1Title != null) && (result1Title.length() == 0)) {
			searchLogCacheModel.result1Title = null;
		}

		searchLogCacheModel.result2ClassId = getResult2ClassId();

		searchLogCacheModel.result2ClassPK = getResult2ClassPK();

		searchLogCacheModel.result2Title = getResult2Title();

		String result2Title = searchLogCacheModel.result2Title;

		if ((result2Title != null) && (result2Title.length() == 0)) {
			searchLogCacheModel.result2Title = null;
		}

		searchLogCacheModel.result3ClassId = getResult3ClassId();

		searchLogCacheModel.result3ClassPK = getResult3ClassPK();

		searchLogCacheModel.result3Title = getResult3Title();

		String result3Title = searchLogCacheModel.result3Title;

		if ((result3Title != null) && (result3Title.length() == 0)) {
			searchLogCacheModel.result3Title = null;
		}

		searchLogCacheModel.userTargetClassId = getUserTargetClassId();

		searchLogCacheModel.userTargetClassPK = getUserTargetClassPK();

		searchLogCacheModel.userTargetTitle = getUserTargetTitle();

		String userTargetTitle = searchLogCacheModel.userTargetTitle;

		if ((userTargetTitle != null) && (userTargetTitle.length() == 0)) {
			searchLogCacheModel.userTargetTitle = null;
		}

		searchLogCacheModel.groupId = getGroupId();

		Long layoutId = getLayoutId();

		if (layoutId != null) {
			searchLogCacheModel.layoutId = layoutId;
		}

		searchLogCacheModel.layoutFriendlyURL = getLayoutFriendlyURL();

		String layoutFriendlyURL = searchLogCacheModel.layoutFriendlyURL;

		if ((layoutFriendlyURL != null) && (layoutFriendlyURL.length() == 0)) {
			searchLogCacheModel.layoutFriendlyURL = null;
		}

		searchLogCacheModel.language = getLanguage();

		String language = searchLogCacheModel.language;

		if ((language != null) && (language.length() == 0)) {
			searchLogCacheModel.language = null;
		}

		Date date = getDate();

		if (date != null) {
			searchLogCacheModel.date = date.getTime();
		}
		else {
			searchLogCacheModel.date = Long.MIN_VALUE;
		}

		return searchLogCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SearchLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SearchLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SearchLog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SearchLog)this));
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
		Map<String, Function<SearchLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SearchLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SearchLog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SearchLog)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SearchLog>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _searchLogId;
	private String _keywords;
	private long _searchTime;
	private long _resultCount;
	private long _result1ClassId;
	private long _result1ClassPK;
	private String _result1Title;
	private long _result2ClassId;
	private long _result2ClassPK;
	private String _result2Title;
	private long _result3ClassId;
	private long _result3ClassPK;
	private String _result3Title;
	private long _userTargetClassId;
	private long _userTargetClassPK;
	private String _userTargetTitle;
	private long _groupId;
	private Long _layoutId;
	private String _layoutFriendlyURL;
	private String _language;
	private Date _date;
	private SearchLog _escapedModel;

}
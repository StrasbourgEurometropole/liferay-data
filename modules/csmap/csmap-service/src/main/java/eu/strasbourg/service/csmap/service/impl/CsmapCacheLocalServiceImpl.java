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

package eu.strasbourg.service.csmap.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.base.CsmapCacheLocalServiceBaseImpl;
import eu.strasbourg.service.csmap.utils.ApiCsmapUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the csmap cache local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCacheLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.CsmapCache",
	service = AopService.class
)
public class CsmapCacheLocalServiceImpl extends CsmapCacheLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.CsmapCacheLocalServiceUtil</code>.
	 */

	private final Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public CsmapCache fetchByCodeCache(long codeCache) {
		return csmapCachePersistence.fetchByCodeCache(codeCache);
	}

	@Override
	public List<CsmapCache> findLastProcessNotSuccess() {
		return csmapCachePersistence.findByLastProcessNotSuccess(false);
	}

	@Override
	public CsmapCache compareJsons(CsmapCache cache, JSONObject json, Date date) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (!mapper.readTree(cache.getCacheJson()).equals(mapper.readTree(json.toString()))) {
			cache.setCacheJson(json.toString());
			cache.setModifiedDate(date);
		}
		return cache;
	}

	@Override
	public CsmapCache createCsmapCache(long codeCache, String json, Date date) {
		long id = _counterLocalService.increment();
		CsmapCache cache = csmapCachePersistence.create(id);
		cache.setCodeCache(codeCache);
		cache.setCacheJson(String.valueOf(json));
		cache.setModifiedDate(date);
		return cache;
	}

	@Override
	public JSONObject getJsonVide() {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
		JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
		JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
		json.put("ADD", jsonAjout);
		json.put("UPDATE", jsonModif);
		json.put("DELETE", jsonSuppr);
		return json;
	}

	@Override
	public void generateCsmapCache(long codeCache) {
		Date date = new Date(System.currentTimeMillis());
		CsmapCache cache = null;
		try {
			cache = fetchByCodeCache(codeCache);
			JSONObject json;

			if (codeCache == CodeCacheEnum.AGENDA.getId()) {
				json = ApiCsmapUtil.getAgenda();
				if (Validator.isNull(cache)) {
					cache = createCsmapCache(codeCache, String.valueOf(json), date);
				} else {
					cache = compareJsons(cache, json, date);
				}
			} else if (codeCache == CodeCacheEnum.CATEGORIES.getId()) {
				json = ApiCsmapUtil.getCategories("0","");
				if (Validator.isNull(cache)) {
					cache = createCsmapCache(codeCache, String.valueOf(json), date);
				} else {
					cache = compareJsons(cache, json, date);
				}
			} else if(codeCache == CodeCacheEnum.EVENT.getId()) {
				if (Validator.isNull(cache)) {
					cache = createCsmapCache(codeCache, String.valueOf(ApiCsmapUtil.getEvents("0")), date);
				} else {
					if(cache.getModifiedDate().before(getLastModifiedEvent())){
						cache.setCacheJson(String.valueOf(ApiCsmapUtil.getEvents("0")));
						cache.setModifiedDate(date);
					}
				}
			}
			cache.setIsLastProcessSuccess(true);
		}
		catch(Exception e){
			log.error(e);
			if(Validator.isNotNull(cache))
				cache.setIsLastProcessSuccess(false);
		}
		if(Validator.isNotNull(cache)) {
			cache.setProcessedDate(date);
			updateCsmapCache(cache);
		}
	}

	@Override
	public Date getLastModifiedEvent(){
		// Permet la récupération de toutes les catégories entières
		Session session = csmapCachePersistence.getCurrentSession();

		SQLQuery query_cacheJson = session.createSQLQuery(this.query_cacheJson);
		SQLQuery query_historic = session.createSQLQuery(this.query_historic);
		Date cacheJsonDate = (Date) query_cacheJson.iterateNext();
		Date historicDate = (Date) query_historic.iterateNext();
		if(cacheJsonDate.before(historicDate)){
			return historicDate;
		} else {
			return cacheJsonDate;
		}
	}

	private String query_cacheJson = "SELECT modifiedEvent FROM agenda_CacheJson order by modifiedEvent desc limit 1";
	private String query_historic = "SELECT suppressionDate FROM agenda_Historic order by suppressionDate desc limit 1";

	@Reference(unbind = "-")
	protected void setCounterLocalService(CounterLocalService counterLocalService) {
		_counterLocalService = counterLocalService;
	}

	private CounterLocalService _counterLocalService;
}
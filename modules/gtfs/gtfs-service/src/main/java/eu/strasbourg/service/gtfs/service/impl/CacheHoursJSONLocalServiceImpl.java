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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.gtfs.model.CacheHoursJSON;
import eu.strasbourg.service.gtfs.service.ArretServiceUtil;
import eu.strasbourg.service.gtfs.service.CacheHoursJSONLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.base.CacheHoursJSONLocalServiceBaseImpl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The implementation of the cache hours json local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.gtfs.service.CacheHoursJSONLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see CacheHoursJSONLocalServiceBaseImpl
 */
public class CacheHoursJSONLocalServiceImpl
	extends CacheHoursJSONLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.gtfs.service.CacheHoursJSONLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.gtfs.service.CacheHoursJSONLocalServiceUtil</code>.
	 */

	/**
	 * Met à jour le jsonHour du stop
	 */
	@Override
	public String getJsonHour(String stopCode) {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		Date now = new Date();
		boolean toRegenerated = false;
		CacheHoursJSON cache = this.fetchCacheHoursJSON(stopCode);
		if (Validator.isNull(cache)) {
			cache = this.createCacheHoursJSON(stopCode);
			cache.setCreationDate(now);
			cache.setModifiedDate(now);
			toRegenerated = true;
		}
		// on vérifie si la date de modification est > à 2 min
		Date modifiedDate = cache.getModifiedDate();
		long difference = now.getTime() - modifiedDate.getTime();
		long diff = TimeUnit.SECONDS.convert(difference, TimeUnit.MILLISECONDS);
		if(diff > 120 )
			toRegenerated = true;

		if(toRegenerated){
			try{
				JSONArray arretsRealTime = ArretServiceUtil.getArretRealTime(stopCode);
				JSONArray schedulesJSON = JSONFactoryUtil.createJSONArray();
				if (arretsRealTime != null) {
					for (int i = 0; i < arretsRealTime.length(); i++) {
						JSONObject arretRealTime = arretsRealTime.getJSONObject(i);
						JSONObject monitoredVehicleJourney = arretRealTime.getJSONObject("MonitoredVehicleJourney");
						JSONObject monitoredCall = monitoredVehicleJourney.getJSONObject("MonitoredCall");
						JSONObject scheduleJSON = JSONFactoryUtil.createJSONObject();
						scheduleJSON.put("lineNumber", monitoredVehicleJourney.getString("LineRef"));
						scheduleJSON.put("destinationName", monitoredVehicleJourney.getString("DestinationName"));
						scheduleJSON.put("departureTime", monitoredCall.getString("ExpectedDepartureTime"));
						schedulesJSON.put(scheduleJSON);
					}
				}
				json.put("schedules", schedulesJSON);
			} catch (Exception e) {
				log.error(e);
				json.put("error", e.getMessage());
			}finally {
				cache.setJsonHour(json.toString());
				cache.setModifiedDate(now);
				//TODO trouver la solution au problème d'insersion en BDD
//				this.updateCacheHoursJSON(cache);
				CacheHoursJSONLocalServiceUtil.updateCacheHoursJSON(cache);
			}
		}

		return cache.getJsonHour();
	}

	private final Log log = LogFactoryUtil.getLog(this.getClass());
}
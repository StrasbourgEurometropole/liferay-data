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
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.gtfs.model.CacheAlertJSON;
import eu.strasbourg.service.gtfs.service.base.CacheAlertJSONLocalServiceBaseImpl;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.IOException;
import java.util.Date;

/**
 * The implementation of the cache alert json local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.gtfs.service.CacheAlertJSONLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see CacheAlertJSONLocalServiceBaseImpl
 */
public class CacheAlertJSONLocalServiceImpl
	extends CacheAlertJSONLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.gtfs.service.CacheAlertJSONLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.gtfs.service.CacheAlertJSONLocalServiceUtil</code>.
	 */

	/**
	 * Met à jour le jsonAlert
	 */
	@Override
	public void updateJsonAlert() {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		try{
			// Recuperation des constantes de requetage de l'API19f7805f-0b98-4451-aa1b-96939a844dfe
			String urlSearch = StrasbourgPropsUtil.getCTSServiceRealTimeURL();
			String basicAuthUser = StrasbourgPropsUtil.getCTSServiceRealTimeToken();
			String basicAuthPwd = "";

			// Construction de l'URL
			String url = urlSearch + "general-message";

			// Envoie de la requete
			JSONObject response = JSONHelper.readJsonFromURL(url, basicAuthUser, basicAuthPwd);

			// Traitement de la reponse
			JSONArray generalMessageDeliveries = response.getJSONObject("ServiceDelivery").getJSONArray("GeneralMessageDelivery");
			json = alertCSMapJSON(generalMessageDeliveries);
		} catch(IOException | JSONException e){
			log.error(e);
			json.put("error", e.getMessage());
		}finally {
			CacheAlertJSON cache = this.cacheAlertJSONLocalService.getCacheAlertJSONs(-1, -1).get(0);
			if (Validator.isNotNull(cache)) {
				cache.setJsonAlert(json.toString());
				cache.setModifiedDate(new Date());
				this.updateCacheAlertJSON(cache);
			}
		}
	}

	private JSONObject alertCSMapJSON(JSONArray generalMessageDeliveries) {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for(int generalMessageDeliveriesIndex = 0; generalMessageDeliveriesIndex < generalMessageDeliveries.length(); generalMessageDeliveriesIndex++){
			JSONObject generalMessageDelivery = generalMessageDeliveries.getJSONObject(generalMessageDeliveriesIndex);
			JSONArray infoMessages = generalMessageDelivery.getJSONArray("InfoMessage");
			for(int infoMessagesIndex = 0; infoMessagesIndex < infoMessages.length(); infoMessagesIndex++){
				JSONObject jsonAlert = JSONFactoryUtil.createJSONObject();
				JSONObject infoMessage = infoMessages.getJSONObject(infoMessagesIndex);
				JSONObject content = infoMessage.getJSONObject("Content");
				jsonAlert.put("linesNumber", content.getJSONArray("ImpactedLineRef"));
				jsonAlert.put("startDate", content.getString("ImpactStartDateTime"));
				jsonAlert.put("endDate", content.getString("ImpactEndDateTime"));
				JSONObject title = JSONFactoryUtil.createJSONObject();
				JSONObject period = JSONFactoryUtil.createJSONObject();
				JSONObject details = JSONFactoryUtil.createJSONObject();
				JSONArray messages = content.getJSONArray("Message");
				for(int messagesIndex = 0; messagesIndex < messages.length(); messagesIndex++){
					JSONObject message = messages.getJSONObject(messagesIndex);
					String messageZoneRef = message.getString("MessageZoneRef");
					JSONArray messageTexts = message.getJSONArray("MessageText");
					if(messageZoneRef.equals("title")){
						getJSONValue(title, messageTexts);
					}
					if(messageZoneRef.equals("period")){
						getJSONValue(period,messageTexts);
					}
					if(messageZoneRef.equals("details")){
						getJSONValue(details,messageTexts);
					}
				}
				jsonAlert.put("title", title);
				jsonAlert.put("period", period);
				jsonAlert.put("details", details);
				jsonArray.put(jsonAlert);
			}
		}
		json.put("alerts", jsonArray);
		return json;
	}

	private void getJSONValue(JSONObject object, JSONArray array){
		for(int i = 0; i < array.length(); i++){
			JSONObject messageTitle = array.getJSONObject(i);
			if(messageTitle.getString("Lang").equals("FR")){
				String periodFR = messageTitle.getString("Value");
				object.put("fr_FR", periodFR);
			}
		}
	}

	private final Log log = LogFactoryUtil.getLog(this.getClass());
}
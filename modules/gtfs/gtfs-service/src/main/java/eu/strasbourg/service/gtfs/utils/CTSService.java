package eu.strasbourg.service.gtfs.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.io.IOException;

import eu.strasbourg.service.gtfs.service.impl.LigneLocalServiceImpl;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class CTSService {
	
	public static final String STOP_MONITORING_FUNCTION = "stop-monitoring";
	public static final String STOP_MONITORING_PARAM_REF = "MonitoringRef";
	public static final String STOP_MONITORING_PARAM_NB_VISIT = "MaximumStopVisits";
	public static final String STOP_MONITORING_PARAM_VEHICLE_MODE = "VehicleMode";
	
	public final static Log log = LogFactoryUtil.getLog(LigneLocalServiceImpl.class);

	/**
	 * Retourne les prochains passages d'un arret
	 * @param stopCode code SMS de l'arret (ex: "275c" pour l'arret de tram Homme de fer)
	 * @param type type de l'arret (0 -> tram, 3 -> bus)
	 */
	public static JSONArray stopMonitoring(String stopCode, int type) {
		String params = STOP_MONITORING_PARAM_REF + "=" + HtmlUtil.escapeURL(stopCode)
				+ "&" + STOP_MONITORING_PARAM_NB_VISIT + "=" + 12;

		if (type == 0)
			params += "&" + STOP_MONITORING_PARAM_VEHICLE_MODE + "=tram";
		else
			params += "&" + STOP_MONITORING_PARAM_VEHICLE_MODE + "=bus";

		return getStopMonitoring(params);
	}

	/**
	 * Retourne les prochains passages d'un arret
	 * @param stopCode code SMS de l'arret (ex: "275c" pour l'arret de tram Homme de fer)
	 */
	public static JSONArray stopMonitoring(String stopCode) {
		String params = STOP_MONITORING_PARAM_REF + "=" + HtmlUtil.escapeURL(stopCode)
				+ "&" + STOP_MONITORING_PARAM_NB_VISIT + "=" + 12;

		return getStopMonitoring(params);
	}

	/**
	 * Retourne les prochains passages d'un arret
	 * @param params paramètres de l'URL voulus
	 */
	private static JSONArray getStopMonitoring(String params) {
		try {
			// Recuperation des constantes de requetage de l'API19f7805f-0b98-4451-aa1b-96939a844dfe
			String urlSearch = StrasbourgPropsUtil.getCTSServiceRealTimeURL();
			String basicAuthUser = StrasbourgPropsUtil.getCTSServiceRealTimeToken();
			String basicAuthPwd = "";

			// Construction de l'URL
			String url = urlSearch + STOP_MONITORING_FUNCTION + "?" + params;

			JSONObject response = JSONHelper.readJsonFromURL(url, basicAuthUser, basicAuthPwd);

			// Traitement de la reponse
			JSONObject serviceDelivery = response.getJSONObject("ServiceDelivery");
			JSONArray stopMonitoringDelivery = serviceDelivery.getJSONArray("StopMonitoringDelivery");
			if (stopMonitoringDelivery.length() > 0) {
				return stopMonitoringDelivery.getJSONObject(0).getJSONArray("MonitoredStopVisit");
			} else {
				return JSONFactoryUtil.createJSONArray();
			}
		} catch (JSONException | IOException e) {
			log.error(e);
			return JSONFactoryUtil.createJSONArray();
		}
	}
	
}

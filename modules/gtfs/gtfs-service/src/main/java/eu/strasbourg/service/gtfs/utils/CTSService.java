package eu.strasbourg.service.gtfs.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;

import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class CTSService {
	
	public static final String STOP_MONITORING_FUNCTION = "stop-monitoring";
	public static final String STOP_MONITORING_PARAM_REF = "MonitoringRef";
	
	/**
     * Retourne les prochains passages d'un arret
     * @param stopCode code SMS de l'arret (ex: "275c" pour l'arret de tram Homme de fer)
     */
    public JSONArray stopMonitoring(String stopCode) throws Exception { 
    	// Recuperation des constantes de requetage de l'API
        String urlSearch = StrasbourgPropsUtil.getCTSServiceRealTimeURL();
        String basicAuthUser = StrasbourgPropsUtil.getCTSServiceRealTimeToken();
        String basicAuthPwd = "";
        
        // Construction de l'URL
        String url = urlSearch + STOP_MONITORING_FUNCTION + "?" +
        		HtmlUtil.escapeURL(STOP_MONITORING_PARAM_REF + "=" + stopCode);
        
        // Envoie de la requete
        JSONObject serviceDelivery = JSONHelper.readJsonFromURL(url, basicAuthUser, basicAuthPwd);
        
        // Traitement de la reponse
        JSONArray stopMonitoringDelivery = serviceDelivery.getJSONArray("StopMonitoringDelivery");
        if (stopMonitoringDelivery.length() > 1) {
        	JSONArray monitoredStopVisit = stopMonitoringDelivery.getJSONArray(0);
        	return monitoredStopVisit;
        } else {
        	return JSONFactoryUtil.createJSONArray();
        }
    }
	
}

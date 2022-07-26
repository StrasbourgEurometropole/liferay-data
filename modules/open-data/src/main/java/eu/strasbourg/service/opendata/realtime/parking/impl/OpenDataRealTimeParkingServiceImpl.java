package eu.strasbourg.service.opendata.realtime.parking.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.opendata.realtime.parking.OpenDataRealTimeParkingService;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Quentin MAYER
 */
@Component(immediate = true, property = {}, service = OpenDataRealTimeParkingService.class)
public class OpenDataRealTimeParkingServiceImpl implements OpenDataRealTimeParkingService {

    /**
     * Récupère l'URL du endPoint pour les adresses
     */
    private String getURL() {
        String openDataBaseURL = StrasbourgPropsUtil.getOpenDataBaseURL();
        String url = openDataBaseURL + StrasbourgPropsUtil.getOpenDataParking();

        return url;
    }

    /**
     * Récupère le json de la réponse de l'URL
     */
    private JSONArray getRecord(String url) throws Exception {
        JSONObject response = JSONHelper.readJsonFromURL(url);
        JSONArray records = response.getJSONArray("records");

        return records;
    }

    @Override
    public JSONArray getParkingJSON() throws Exception {
        String url = getURL();
        JSONArray records = getRecord(url);
        return records;
    }

    private Log log = LogFactoryUtil.getLog(this.getClass());
}
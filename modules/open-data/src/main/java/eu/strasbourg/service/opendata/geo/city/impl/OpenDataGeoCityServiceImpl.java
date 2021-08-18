package eu.strasbourg.service.opendata.geo.city.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import eu.strasbourg.service.opendata.geo.city.OpenDataGeoCityService;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Angélique ZUNINO CHAMPOUGNY
 */
@Component(immediate = true, property = {}, service = OpenDataGeoCityService.class)
public class OpenDataGeoCityServiceImpl implements OpenDataGeoCityService {

    private Log log = LogFactoryUtil.getLog(this.getClass());

    /**
     * Récupère l'URL du endPoint pour les quartiers
     */
    private String getCityURL() {
        String openDataBaseURL = StrasbourgPropsUtil.getOpenDataBaseURL();
        String cityURL = openDataBaseURL + StrasbourgPropsUtil.getOpenDataCities();

        return cityURL;
    }

    /**
     * Récupère le json de la réponse de l'URL
     */
    private JSONArray getRecord(String url) throws Exception {
        JSONObject response = JSONHelper.readJsonFromURL(url);
        JSONArray records = response.getJSONArray("records");

        return records;
    }

    /**
     * Retourne les coordonnées d'un quartier
     */
    @Override
    public JSONObject getCoordinatesForNumCom(String number) throws Exception {
        JSONObject coordinatesZone = JSONFactoryUtil.createJSONObject();
        String url = getCityURL() + "&refine.num_com=" + HtmlUtil.escapeURL(number);
        JSONArray records = getRecord(url);
        if (records.length() > 0) {
            JSONObject fields = records.getJSONObject(0).getJSONObject("fields");
            coordinatesZone = fields.getJSONObject("geo_shape");
        }

        return coordinatesZone;
    }
}
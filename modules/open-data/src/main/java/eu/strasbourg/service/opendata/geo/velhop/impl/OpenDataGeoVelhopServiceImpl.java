package eu.strasbourg.service.opendata.geo.velhop.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.opendata.geo.velhop.OpenDataGeoVelhopService;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Angélique ZUNINO CHAMPOUGNY
 */
@Component(immediate = true, property = {}, service = OpenDataGeoVelhopService.class)
public class OpenDataGeoVelhopServiceImpl implements OpenDataGeoVelhopService {

    private Log log = LogFactoryUtil.getLog(this.getClass());

    /**
     * Récupère l'URL du endPoint pour les quartiers
     */
    private String getVelhopURL() {
        String openDataBaseURL = StrasbourgPropsUtil.getOpenDataBaseURL();
        String velhopURL = openDataBaseURL + StrasbourgPropsUtil.getOpenDataVelhops();

        return velhopURL;
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
     * Retourne les information d'une station velhop
     */
    @Override
    public JSONObject getVelhopInformationById(String id) throws Exception {
        JSONObject information = JSONFactoryUtil.createJSONObject();
        String url = getVelhopURL() + "&refine.id=" + id;
        JSONArray records = getRecord(url);
        if (records.length() > 0) {
            information = records.getJSONObject(0).getJSONObject("fields");
        }

        return information;
    }
}
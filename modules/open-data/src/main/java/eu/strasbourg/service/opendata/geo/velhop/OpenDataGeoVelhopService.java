package eu.strasbourg.service.opendata.geo.velhop;

import com.liferay.portal.kernel.json.JSONObject;

public interface OpenDataGeoVelhopService {
    public JSONObject getVelhopInformationById(String id) throws Exception;
}

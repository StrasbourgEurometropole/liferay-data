package eu.strasbourg.service.opendata.geo.city;

import com.liferay.portal.kernel.json.JSONObject;

public interface OpenDataGeoCityService {
    public JSONObject getCoordinatesForNumCom(String number) throws Exception;
}

package eu.strasbourg.service.opendata.geo.address;

import com.liferay.portal.kernel.json.JSONArray;

public interface OpenDataGeoAddressService {
    public JSONArray getCoordinateForAddress(String address) throws Exception;
}

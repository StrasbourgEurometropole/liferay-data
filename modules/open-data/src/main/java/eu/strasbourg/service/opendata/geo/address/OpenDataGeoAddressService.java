package eu.strasbourg.service.opendata.geo.address;

import com.liferay.portal.kernel.json.JSONArray;

public interface OpenDataGeoAddressService {
    public JSONArray getCoordinateForAddress(String address, String zipCode, String city) throws Exception;
    public JSONArray getCoordinateForAddress(String address, String zipCode, String city, int timeOut) throws Exception;
}

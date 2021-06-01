package eu.strasbourg.service.opendata.geo.district;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONObject;

public interface OpenDataGeoDistrictService {
    public AssetCategory getDistrictByAddress(String address, String zipCode, String city) throws Exception;
    public JSONObject getCoordinatesForSigId(String sigID) throws Exception;
    public String getSigIdForCoordinates(String x, String y) throws Exception;
}

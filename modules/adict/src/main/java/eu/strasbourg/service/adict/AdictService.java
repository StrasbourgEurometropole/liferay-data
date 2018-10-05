package eu.strasbourg.service.adict;

import java.io.IOException;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

public interface AdictService {
	public List<Street> searchStreetNumbers(String query);
	public List<Street> searchStreetNumbers(String query, String city);
	public List<SectorType> getSectorTypes();
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String[] sectorTypes) throws Exception;
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String sectorType) throws Exception;
	public JSONArray getCoordinateForAddress(String address) throws Exception;
	public AssetCategory getDistrictByAddress(String address) throws Exception;
	public List<String> getSchoolsByAddress(String address) throws Exception;
	public JSONObject getCoordinatesForZone(String x, String y, String sectorType);
	public JSONObject getCoordinatesForDistrict();
	public JSONObject getCoordinatesForDistrict(String sigID);
	public JSONObject getTraffic();
	public JSONObject getAlerts();
}

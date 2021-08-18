package eu.strasbourg.service.adict;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

public interface AdictService {
	public List<Street> searchStreetNumbers(String query);
	public List<Street> searchStreetNumbers(String query, String city);
	public List<SectorType> getSectorTypes();
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String[] sectorTypes) throws Exception;
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String sectorType) throws Exception;
	public JSONArray getCoordinateForAddress(String address) throws Exception;
	public List<String> getSchoolsByAddress(String address) throws Exception;
	public JSONObject getTraffic();
	public JSONObject getAlerts();
}

package eu.strasbourg.service.poi;

import com.liferay.portal.kernel.json.JSONObject;

public interface PoiService {
	
	public JSONObject getPois(String idInterests);

	public JSONObject getFavoritesPois(String userId);
}

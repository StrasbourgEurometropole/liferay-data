package eu.strasbourg.service.poi;

import com.liferay.portal.kernel.json.JSONObject;

public interface PoiService {
	
	public JSONObject getPois(String idInterests, long groupId);

	public JSONObject getFavoritesPois(String userId, long groupId);
}

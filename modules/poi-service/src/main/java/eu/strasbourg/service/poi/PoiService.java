package eu.strasbourg.service.poi;

import com.liferay.portal.kernel.json.JSONObject;

public interface PoiService {

	int getPoisCategoryCount(long idCategory, String prefilters, String tags, long groupId, String classNames,
							 boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId);

	int getPoisInterestCount(long idInterest, long groupId, String classNames, String localeId, long globalGroupId);

	int getFavoritesPoisCount(String userId, long groupId, String classNames);

	JSONObject getPois(String idInterests, String idCategories,
							  String prefilters, String tags, long groupId,
							  String classNames, boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId);
	
	JSONObject getFavoritesPois(String userId, long groupId);

	JSONObject getFavoritesPois(String userId, long groupId, String typesContenu);

	JSONObject getFavoritesPois(String userId, long groupId, String classNames, String LocaleId);

}

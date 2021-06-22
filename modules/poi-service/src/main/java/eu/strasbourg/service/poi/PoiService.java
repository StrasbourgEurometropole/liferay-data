package eu.strasbourg.service.poi;

import com.liferay.portal.kernel.json.JSONObject;

public interface PoiService {

	//AngelTODO à réintégrer un fois que la gestion du territoire et des coordonnées de tous les events physiques sans exception sera faite
	/*int getPoisCategoryCount(long idCategory, String prefilters, String tags, long groupId, String classNames,
							 boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId);

	int getPoisInterestCount(long idInterest, long groupId, String classNames, String localeId, long globalGroupId);

	int getFavoritesPoisCount(String userId, long groupId, String classNames);*/

	JSONObject getPois(String idInterests, String idCategories, String vocabulariesEmptyIds,
							  String prefilters, String tags, long groupId,
							  String classNames, boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId);
	
	JSONObject getFavoritesPois(String userId, long groupId, String classNames, String LocaleId);

}

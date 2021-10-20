package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

public class WSTransport {

    public static JSONObject getJSONValue(JSONObject object, JSONArray array){
        for(int i = 0; i < array.length(); i++){
            JSONObject messageTitle = array.getJSONObject(i);
            if(messageTitle.getString("Lang").equals("FR")){
                String periodFR = messageTitle.getString("Value");
                object.put(WSConstants.JSON_LANGUAGE_FRANCE, periodFR);
            }
        }
        return object;
    }
}

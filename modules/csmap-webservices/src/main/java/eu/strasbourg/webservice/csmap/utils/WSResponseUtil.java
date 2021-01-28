package eu.strasbourg.webservice.csmap.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

public class WSResponseUtil {

    public static JSONObject generateResponse(int responseCode, String errorDescription) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        editResponseCode(jsonResponse, responseCode);
        editErrorDescription(jsonResponse, errorDescription);
        return jsonResponse;
    }

    public static JSONObject initializeResponse() {
        return generateResponse(201, "");
    }

    public static JSONObject editResponseCode(JSONObject jsonResponse, int responseCode) {
        jsonResponse.put(WSConstants.JSON_RESPONSE_CODE, responseCode);
        return jsonResponse;
    }

    public static JSONObject editErrorDescription(JSONObject jsonResponse, String errorDescription) {
        jsonResponse.put(WSConstants.JSON_ERROR_DESCRIPTION, errorDescription);
        return jsonResponse;
    }

}

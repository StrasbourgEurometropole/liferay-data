package eu.strasbourg.webservice.csmap.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

public class WSResponseUtil {

    public static JSONObject initializeResponse() {
        return generateResponse(201, null);
    }

    public static JSONObject initializeError(String error) {
        return generateResponse(400, error);
    }

    public static JSONObject generateResponse(int responseCode, String errorDescription) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        editResponseCode(jsonResponse, responseCode);
        if(Validator.isNotNull(errorDescription))
            editErrorDescription(jsonResponse, errorDescription);
        return jsonResponse;
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

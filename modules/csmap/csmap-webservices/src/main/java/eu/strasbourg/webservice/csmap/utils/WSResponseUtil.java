package eu.strasbourg.webservice.csmap.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

import javax.ws.rs.core.Response;

public class WSResponseUtil {

    public static Response buildErrorResponse(int statusCode, String errorDescription) {
        Response response;
        JSONObject json = JSONFactoryUtil.createJSONObject();

        editJsonResponseCode(json, statusCode);
        editJsonErrorDescription(json, errorDescription);

        if (statusCode < 500)
            response = Response.status(statusCode).entity(json.toString()).build();
        else
            response = Response.serverError().entity(json.toString()).build();

        return response;
    }

    public static Response buildOkResponse(JSONArray jsonArray) {
        return buildOkResponse (jsonArray, 200);
    }

    public static Response buildOkResponse(JSONArray jsonArray, int responseCode) {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put(WSConstants.JSON_RESPONSE, jsonArray);
        return buildOkResponse (json, responseCode);
    }

    public static Response buildOkResponse(JSONObject json) {
        return buildOkResponse(json, 200);
    }

    public static Response buildOkResponse(JSONObject json, int responseCode) {
        editJsonResponseCode(json, responseCode);
        return Response.ok(json.toString()).build();
    }

    public static void editJsonResponseCode(JSONObject json, int responseCode) {
        json.put(WSConstants.JSON_RESPONSE_CODE, responseCode);
    }

    public static void editJsonErrorDescription(JSONObject json, String errorDescription) {
        json.put(WSConstants.JSON_ERROR_DESCRIPTION, errorDescription);
    }

}

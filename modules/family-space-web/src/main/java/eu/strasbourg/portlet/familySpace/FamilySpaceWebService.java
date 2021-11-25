package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FamilySpaceWebService {

	public static FamilySpaceResponse getResponse(String publikInternalId) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		FamilySpaceResponse familySpaceResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getFamilySpaceWebServiceURL() + publikInternalId;
			// url = "https://webservices.strasbourg.eu/wdmiam/InfosFamilles.svc/IdentifiantOpenId/";
			// url +=  "00e112a761d24a1ca57798e8867c97bc";
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
			//if(httpConn.getContentType().contains("application/json")) {
				JSONObject jsonResponse = PasserelleHelper.readJson(httpConn);
				familySpaceResponse = new FamilySpaceResponse(jsonResponse);
			//}
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

		return familySpaceResponse;
	}

}

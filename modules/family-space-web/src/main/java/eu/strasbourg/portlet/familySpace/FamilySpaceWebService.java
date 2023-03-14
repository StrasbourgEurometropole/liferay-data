package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FamilySpaceWebService {

	public static FamilySpaceResponse getResponse(String publikInternalId) {
		return getResponse(publikInternalId, StrasbourgPropsUtil.getWebServiceDefaultTimeout()) ;
	}

	public static FamilySpaceResponse getResponse(String publikInternalId, int timeOut) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		FamilySpaceResponse familySpaceResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getFamilySpaceWebServiceURL() + publikInternalId;
			// url = "https://webservices.strasbourg.eu/wdmiam/InfosFamilles.svc/IdentifiantOpenId/";
			// url +=  "00e112a761d24a1ca57798e8867c97bc";
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url, timeOut);
			//if(httpConn.getContentType().contains("application/json")) {
			JSONObject jsonResponse = PasserelleHelper.readJson(httpConn);
			familySpaceResponse = new FamilySpaceResponse(jsonResponse);
			//}
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage(), ex);
		}

		return familySpaceResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(FamilySpaceWebService.class.getName());

}

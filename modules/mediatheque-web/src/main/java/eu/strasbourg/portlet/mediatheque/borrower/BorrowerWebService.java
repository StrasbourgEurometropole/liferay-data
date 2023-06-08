package eu.strasbourg.portlet.mediatheque.borrower;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.portlet.mediatheque.dissociate.DissociateWebService;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class BorrowerWebService {

	public static BorrowerResponse getResponse(String publikInternalId) {
		return getResponse(publikInternalId, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
	}

	public static BorrowerResponse getResponse(String publikInternalId, int timeOut) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("sub", publikInternalId);

		StringBuilder postData = new StringBuilder();
		try {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			_log.error(e.getMessage() + " : publikInternalId -> " + publikInternalId);
		}
		BorrowerResponse borrowerResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeBorrower() + "?" + postData;
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url,timeOut);
			if(httpConn.getContentType().contains("application/xml")) {
				String xmlResponse = PasserelleHelper.readXML(httpConn);
				borrowerResponse = new BorrowerResponse(xmlResponse);
			}
			if(httpConn.getContentType().contains("application/json")) {
				JSONObject jsonResponse = PasserelleHelper.readJson(httpConn);
				borrowerResponse = new BorrowerResponse(jsonResponse);
			}
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage(), ex);
		}

		return borrowerResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(BorrowerWebService.class.getName());

}

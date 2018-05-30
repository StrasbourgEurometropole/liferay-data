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

import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class BorrowerWebService {

	public static BorrowerResponse getResponse(String publikInternalId, PortletRequest resourceRequest) {

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
			e.printStackTrace();
		}
		BorrowerResponse borrowerResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeBorrower() + "?" + postData;
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
			if(httpConn.getContentType().contains("application/xml")) {
				String xmlResponse = PasserelleHelper.readXML(httpConn);
				borrowerResponse = new BorrowerResponse(xmlResponse);
			}
			if(httpConn.getContentType().contains("application/json")) {
				JSONObject jsonResponse = PasserelleHelper.readJson(httpConn);
				borrowerResponse = new BorrowerResponse(jsonResponse);
			}
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

		return borrowerResponse;
	}

}

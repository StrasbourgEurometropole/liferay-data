package eu.strasbourg.portlet.mediatheque.association;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class AssociationWebService {

	public static AssociationResponse getResponse(String sub, String borrowerId, String urlMail) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("borrower_id", borrowerId);
		params.put("sub", sub);
		params.put("url_mail", urlMail);

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
			_log.error(e.getMessage() + " : sub -> " + sub + ", borrowerId -> " + borrowerId + ", urlMail -> " + urlMail);
		}
		AssociationResponse associationResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeAssociation() + "?" + postData;
			JSONObject jsonResponse = PasserelleHelper.readJsonFromURL(url);
			associationResponse = new AssociationResponse(jsonResponse);
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage() + " : " + StrasbourgPropsUtil.getMediathequeAssociation() + "?" + postData);
		}

		return associationResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(AssociationWebService.class.getName());

}

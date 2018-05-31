package eu.strasbourg.portlet.validation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class ValidationWebService {

	public static ValidationResponse getResponse(String sub, String code) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("code", code);
		params.put("sub", sub);

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
		ValidationResponse validationResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeValidation() + "?" + postData;
			JSONObject jsonResponse = PasserelleHelper.readJsonFromURL(url);
			validationResponse = new ValidationResponse(jsonResponse);
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

		return validationResponse;
	}

}

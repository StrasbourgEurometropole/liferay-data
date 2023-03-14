package eu.strasbourg.portlet.validation;

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
import eu.strasbourg.utils.constants.VocabularyNames;

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
			_log.error(e.getMessage());
		}
		ValidationResponse validationResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeValidation() + "?" + postData;
			JSONObject jsonResponse = PasserelleHelper.readJsonFromURL(url);
			validationResponse = new ValidationResponse(jsonResponse);
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage() + " : " + StrasbourgPropsUtil.getMediathequeValidation() + "?" + postData);
		}

		return validationResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(ValidationWebService.class.getName());

}

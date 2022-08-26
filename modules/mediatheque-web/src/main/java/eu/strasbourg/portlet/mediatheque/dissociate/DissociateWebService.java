package eu.strasbourg.portlet.mediatheque.dissociate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.portlet.mediatheque.association.AssociationWebService;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class DissociateWebService {

	public static DissociateResponse getResponse(String sub) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
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
			_log.error(e.getMessage() + " : sub -> " + sub);
		}
		DissociateResponse dissociateResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getMediathequeDestroy() + "?" + postData;
			JSONObject jsonResponse = PasserelleHelper.readJsonFromURL(url);
			dissociateResponse = new DissociateResponse(jsonResponse);
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage() + " : " + StrasbourgPropsUtil.getMediathequeDestroy() + "?" + postData);
		}

		return dissociateResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(DissociateWebService.class.getName());

}

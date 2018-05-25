package eu.strasbourg.portlet.graveyard.portlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class GraveyardWebServiceClient {

	public static GraveyardResponse getResponse(String name, String firstName, Date birthDateStart, Date birthDateEnd,
			Date deathDateStart, Date deathDateEnd, String deathPlace, String graveyard) {
		GraveyardResponse graveyardResponse = new GraveyardResponse();

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("prenom", firstName);
		params.put("nom", name);
		if (Validator.isNotNull(deathDateStart)) {
			params.put("date_debut", new SimpleDateFormat("dd/MM/yyyy").format(deathDateStart));
		} else {
			params.put("date_debut", "");
		}
		if (Validator.isNotNull(deathDateEnd)) {
			params.put("date_fin", new SimpleDateFormat("dd/MM/yyyy").format(deathDateEnd));
		} else {
			params.put("date_fin", "");
		}
		if (Validator.isNotNull(birthDateStart)) {
			params.put("date_naissance_debut", new SimpleDateFormat("dd/MM/yyyy").format(birthDateStart));
		} else {
			params.put("date_naissance_debut", "");
		}
		if (Validator.isNotNull(deathDateEnd)) {
			params.put("date_naissance_fin", new SimpleDateFormat("dd/MM/yyyy").format(birthDateEnd));
		} else {
			params.put("date_naissance_fin", "");
		}
		params.put("cimetieres", graveyard);

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
		JSONObject jsonResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getGraveyardURL() + "?" + postData;
			jsonResponse = PasserelleHelper.readJsonFromURL(url);
			graveyardResponse = new GraveyardResponse(jsonResponse);
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

		return graveyardResponse;
	}
}

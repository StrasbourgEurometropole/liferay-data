package eu.strasbourg.portlet.felec.portlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class FelecWebServiceClient {

	private static String felecURL = StrasbourgPropsUtil.getFelecURL();
	

	public static FelecResponse getResponse(String name, String firstName,
		Date birthDate, String birthPlace) {
		FelecResponse felecResponse = new FelecResponse();
		try {
			String type = "application/x-www-form-urlencoded";

			Map<String, Object> params = new LinkedHashMap<String, Object>();
			params.put("nom", name);
			params.put("prenom", firstName);
			params.put("datnai",
				new SimpleDateFormat("yyyyMMdd").format(birthDate));
			params.put("pays", "");
			params.put("lieu", birthPlace);
			params.put("nom_strict", "O");
			params.put("prenom_strict", "N");
			params.put("premier_prenom", "O");
			params.put("format_datnai", "AAAAMMJJ");
			params.put("pays_strict", "N");
			params.put("pays_obligatoire", "N");
			params.put("lieu_strict", "N");
			params.put("paramcasse", "N");
			params.put("paramaccent", "N");

			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder
					.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			URL u = new URL(felecURL);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
			conn.setReadTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", type);
			conn.setRequestProperty("Content-Length",
				String.valueOf(postDataBytes.length));

			OutputStream os = conn.getOutputStream();
			os.write(postDataBytes);
			Reader in = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int c; (c = in.read()) >= 0;)
				sb.append((char) c);
			String response = sb.toString();
			JSONObject jsonResponse = JSONFactoryUtil.createJSONObject(response)
				.getJSONObject("cwsTabElecRetour");
			felecResponse = new FelecResponse(jsonResponse);
		} catch (Exception ex) {
			_log.error(ex.getMessage(), ex);
		}

		return felecResponse;
	}

	private static final Log _log = LogFactoryUtil.getLog(FelecWebServiceClient.class.getName());
}

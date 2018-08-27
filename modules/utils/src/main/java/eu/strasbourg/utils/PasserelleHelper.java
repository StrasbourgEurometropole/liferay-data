package eu.strasbourg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class PasserelleHelper {
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static HttpURLConnection readFromURL(String URL)
			throws IOException, JSONException {
		HttpURLConnection httpConn = (HttpURLConnection) new URL(URL).openConnection();
		httpConn.setConnectTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
		httpConn.setReadTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
		return httpConn;
	}

	public static JSONObject readJsonFromURL(String URL)
			throws IOException, JSONException {
		HttpURLConnection httpConn =  readFromURL(URL);
		JSONObject json = readJson(httpConn);
		
		return json;
	}

	public static JSONObject readJson(HttpURLConnection httpConn)
			throws IOException, JSONException {
		InputStream is;
		if (httpConn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
			is = httpConn.getInputStream();
		} else {
			/* error from server */
			is = httpConn.getErrorStream();
		}
		
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONFactoryUtil.createJSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static String readXML(HttpURLConnection httpConn)
			throws IOException, JSONException {
		InputStream is;
		if (httpConn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
			is = httpConn.getInputStream();
		} else {
			/* error from server */
			is = httpConn.getErrorStream();
		}
		
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String xmlText = readAll(rd);
			return xmlText;
		} finally {
			is.close();
		}
	}

}

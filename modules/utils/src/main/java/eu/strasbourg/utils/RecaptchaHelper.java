package eu.strasbourg.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class RecaptchaHelper {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = StrasbourgPropsUtil.getRecaptchaSecretKey();
	private final static String USER_AGENT = "Mozilla/5.0";

	public static boolean verify(String gRecaptchaResponse) {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {	
			return false;
		}

		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject json = JSONFactoryUtil
				.createJSONObject(response.toString());

			return json.getBoolean("success");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
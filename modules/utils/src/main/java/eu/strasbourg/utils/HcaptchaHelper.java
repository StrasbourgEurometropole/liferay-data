package eu.strasbourg.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HcaptchaHelper {

	public static final String url = "https://hcaptcha.com/siteverify";
	public static final String secret = StrasbourgPropsUtil.getHcaptchaSecretKey();

	public static boolean verify(String token) {
		if (token == null || "".equals(token)) {
			return false;
		}

		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String postParams = "secret=" + secret + "&response="
				+ token;

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
			_log.error(e);
			return false;
		}
	}
	
	private static final Log _log = LogFactoryUtil.getLog(HcaptchaHelper.class.getName());
}
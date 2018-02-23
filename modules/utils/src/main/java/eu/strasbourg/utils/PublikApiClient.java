package eu.strasbourg.utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HttpUtil;

public class PublikApiClient {

	/**
	 * Retourne les demandes en cours d'un utilisateur
	 * 
	 * @param userPublikId
	 *            Identifiant Publik de l'utilisateur
	 * @param includeDrafts
	 *            true pour inclure les formulaires "brouillon" de l'utilisateur
	 * @return
	 */
	public static JSONObject getUserForms(String userPublikId, boolean includeDrafts) {
		String endpoint = "/api/user/forms";
		String queryString = "NameID=" + userPublikId;
		if (includeDrafts) {
			queryString += "&include-drafts=true";
		}
		String url = getSignedUrl(endpoint, queryString);
		try {
			return JSONHelper.readJsonFromURL(url);
		} catch (Exception ex) {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	/**
	 * Signe l'URL avant d'appeler le web service
	 * 
	 * @param endpoint
	 *            Endpoint à appeler (doit commencer par "/")
	 * @param queryString
	 *            Query string contenant les paramètres à passer au endpoint
	 * @return L'URL signée (via un paramètre "signature" dans la query string)
	 *         à appeler
	 */
	private static String getSignedUrl(String endpoint, String queryString) {
		String baseUrl = StrasbourgPropsUtil.getPublikApiBase();
		String origin = StrasbourgPropsUtil.getPublikApiOrigin();
		String key = StrasbourgPropsUtil.getPublikApiKey();
		LocalDateTime ld = LocalDateTime.now(Clock.systemUTC());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String timestamp = formatter.format(ld);
		if (queryString.length() > 0) {
			queryString += "&";
		}
		String queryStringToSign = queryString + "algo=sha256&timestamp=" + timestamp + "&orig=" + origin;

		Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secretKey);
		} catch (Exception e) {
			return "";
		}
		String signature = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(queryStringToSign.getBytes()));
		String signedQueryString = queryString + "algo=sha256&timestamp=" + timestamp + "&orig=" + origin
				+ "&signature=" + HttpUtil.encodeURL(signature);

		return baseUrl + endpoint + "?" + signedQueryString;
	}

	/**
	 * Retourne les informations d'un utilisateur Publik sous la forme d'un
	 * objet JSON
	 * 
	 * @param userId
	 *            Identifiant Publik de l'utilisateur
	 * @return Objet JSON content les informations de l'utilisateur
	 */
	public static JSONObject getUserDetails(String userId) {
		String baseUrl = "https://connexion-strasbourg.test.entrouvert.org";
		String endpoint = "/api/users/";
		try {
			JSONObject responseObject = JSONHelper.readJsonFromURL(baseUrl + endpoint + userId,
					StrasbourgPropsUtil.getPublikClientId(), StrasbourgPropsUtil.getPublikClientSecret());
			responseObject.remove("password");
			return responseObject;
		} catch (Exception ex) {
			return JSONFactoryUtil.createJSONObject();
		}

	}

}

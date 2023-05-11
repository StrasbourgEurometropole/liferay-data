package eu.strasbourg.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class PublikApiClient {

	/**
	 * Retourne les demandes en cours d'un utilisateur
	 *
	 * @param userPublikId
	 *            Identifiant Publik de l'utilisateur
	 * @param includeDrafts
	 *            true pour inclure les formulaires "brouillon" de l'utilisateur
	 */
	public static JSONObject getUserForms(String userPublikId, boolean includeDrafts) {
		return getUserForms(userPublikId, includeDrafts, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
	}

	/**
	 * Retourne les demandes en cours d'un utilisateur
	 *
	 * @param userPublikId
	 *            Identifiant Publik de l'utilisateur
	 * @param includeDrafts
	 *            true pour inclure les formulaires "brouillon" de l'utilisateur
	 */
	public static JSONObject getUserForms(String userPublikId, boolean includeDrafts, int timeOut) {
		String endpoint = "/api/user/forms";
		String queryString = "NameID=" + userPublikId;
		if (includeDrafts) {
			queryString += "&include-drafts=true";
		}
		String url = getSignedUrl(endpoint, queryString);
		try {
			return JSONHelper.readJsonFromURL(url,timeOut);
		} catch (Exception ex) {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	/**
	 * Retourne les candidatures
	 *
	 * @param userPublikId
	 *            Identifiant Publik de l'utilisateur
	 */
	public static JSONObject getUserAppications(String userPublikId) {
		String endpoint = "/api/forms/candidature-a-une-offre-d-emploi/list";
		endpoint = "/api/user/forms";
		String queryString = "NameID=" + userPublikId + "&full=on";
		String url = getSignedUrl(endpoint, queryString);
		try {
			return JSONHelper.readJsonFromURL(url,StrasbourgPropsUtil.getWebServiceDefaultTimeout());
		} catch (Exception ex) {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	/**
	 * Retourne les utilisateur supprimés
	 *
	 * @param userPublikIds
	 *            Identifiant Publik des utilisateurs
	 */
	public static JSONObject getUsersDeleted(JSONArray userPublikIds) {
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

		String baseUrl = StrasbourgPropsUtil.getPublikIssuer();
		String endpoint = StrasbourgPropsUtil.getPublikApiSynchronization();
		String basicAuthUser = StrasbourgPropsUtil.getPublikClientId();
		String basicAuthPassword = StrasbourgPropsUtil.getPublikClientSecret();

		try {

			URL u = new URL(baseUrl + endpoint);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setConnectTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
			conn.setReadTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
			if (basicAuthUser != null && basicAuthPassword != null) {
				String encoded = Base64.getEncoder()
						.encodeToString((basicAuthUser + ":" + basicAuthPassword).getBytes(Charset.forName("UTF-8")));
				conn.setRequestProperty("Authorization", "Basic " + encoded);
			}

			JSONObject json = JSONFactoryUtil.createJSONObject();
			json.put("known_uuids", userPublikIds);
			String jsonInputString = json.toJSONString();

			OutputStream os = conn.getOutputStream();
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);

			InputStream is;
			if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
				is = conn.getInputStream();
			} else {
				/* error from server */
				is = conn.getErrorStream();
			}
			Reader in = new BufferedReader(	new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int c; (c = in.read()) >= 0;)
				sb.append((char) c);
			String response = sb.toString();
			jsonResponse = JSONFactoryUtil.createJSONObject(response);
		} catch (Exception ex) {
			_log.error(ex.getMessage(), ex);
		}

		return jsonResponse;
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
			return getUserDetails(userId, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
	}

	/**
	 * Retourne les informations d'un utilisateur Publik sous la forme d'un
	 * objet JSON
	 *
	 * @param userId
	 *            Identifiant Publik de l'utilisateur
	 * @return Objet JSON content les informations de l'utilisateur
	 */
	public static JSONObject getUserDetails(String userId, int timeOut) {
		String baseUrl = StrasbourgPropsUtil.getPublikIssuer();
		String endpoint = "api/users/";
		try {
			JSONObject responseObject = JSONHelper.readJsonFromURL(baseUrl + endpoint + userId +"/",
					StrasbourgPropsUtil.getPublikUserName(), StrasbourgPropsUtil.getPublikPassword(), timeOut);
			responseObject.remove("password");
			return responseObject;
		} catch (Exception ex) {
			return JSONFactoryUtil.createJSONObject();
		}

	}

	/**
	 * Enregistre l'adresse d'un utilisateur Publik 
	 *
	 * @param userId
	 *            Identifiant Publik de l'utilisateur
	 * @param lastName
	 *            Nom de l'utilisateur
	 * @param address
	 *            adresse de l'utilisateur
	 * @param zipCode
	 *            CP de l'utilisateur
	 * @param city
	 *            ville de l'utilisateur
	 * @return Objet JSON content les informations de l'utilisateur
	 */
	public static boolean setUserDetails(String userId, String lastName, String address, String zipCode, String city) {
		String baseUrl = StrasbourgPropsUtil.getPublikIssuer();
		String endpoint = "api/users/";
		try {
			JSONHelper.put(baseUrl + endpoint + userId + "/", lastName, address, zipCode, city,
					StrasbourgPropsUtil.getPublikUserName(), StrasbourgPropsUtil.getPublikPassword());
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * Enregistre l'adresse, la date de naissance et les numéro de tel d'un utilisateur Publik
	 *
	 * @param userId
	 *            Identifiant Publik de l'utilisateur
	 * @param lastName
	 *            Nom de l'utilisateur
	 * @param address
	 *            adresse de l'utilisateur
	 * @param zipCode
	 *            CP de l'utilisateur
	 * @param city
	 *            ville de l'utilisateur
	 * @param dateNaiss
	 *            date de naissance de l'utilisateur
	 * @param phoneNumber
	 *            téléphone fixe de l'utilisateur
	 * @param cellNumber
	 *            téléphone portable de l'utilisateur
	 * @return Objet JSON content les informations de l'utilisateur
	 */
	public static boolean setAllUserDetails(String userId, String lastName, String address, String zipCode, String city,
											String dateNaiss, String phoneNumber, String cellNumber) {
		String baseUrl = StrasbourgPropsUtil.getPublikIssuer();
		String endpoint = "api/users/";
		try {
			JSONHelper.put(baseUrl + endpoint + userId + "/", lastName, address, zipCode, city, phoneNumber, cellNumber, dateNaiss,
					StrasbourgPropsUtil.getPublikUserName(), StrasbourgPropsUtil.getPublikPassword());
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * Retourne l'url de l'image de profil d'un utilisateur
	 *
	 * @param userId Publik id
	 * @return URL
	 */
	public static String getUserPhoto(String userId) {
		if (userId != null && !userId.equals("")) {
			JSONObject jsonUser = PublikApiClient.getUserDetails(userId);
			return jsonUser != null ? jsonUser.getString("photo") : "";
		} else {
			return "";
		}
	}
	private static final Log _log = LogFactoryUtil.getLog(PublikApiClient.class.getName());

}

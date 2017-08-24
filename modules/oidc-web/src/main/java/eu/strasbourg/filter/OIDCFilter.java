package eu.strasbourg.filter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(
	immediate = true,
	property = { "dispatcher=FORWARD", "dispatcher=REQUEST", "url-pattern=/*",
		"servlet-context-name=", "servlet-filter-name=SSO Publik" },
	service = Filter.class)
public class OIDCFilter extends BaseFilter {
	private static final Log LOG = LogFactoryUtil.getLog(OIDCFilter.class);

	private String loggedInAttribute = "publik_logged_in";
	private String internalIdAttribute = "publik_internal_id";
	private String accessTokenAttribute = "publik_access_token";
	private String givenNameAttribute = "publik_given_name";
	private String familyNameAttribute = "publik_family_name";
	private String lastVisitedAttribute = "last_visited";

	@Override
	protected Log getLog() {
		return LOG;
	}

	@Override
	protected void processFilter(HttpServletRequest request,
		HttpServletResponse response, FilterChain filterChain)
		throws Exception {
		String auth = ParamUtil.getString(request, "auth");
		String code = ParamUtil.getString(request, "code");
		String lastVisited = SessionParamUtil.getString(request,
			lastVisitedAttribute);

		// Auth dans la requête mais pas code : on redirige l'utilisateur vers
		// publik
		if (code.length() == 0 && auth.equals("publik")) {
			redirectToLogin(request, response);
			return;
		}

		// "code" dans la requête ?
		if (code.length() > 0) {
			saveUserInfoInSession(request, response, filterChain, code);

			// Si on a "last_visited" dans la session, on redirige l'utilisateur
			// vers cette page
			if (lastVisited.length() > 0) {
				request.getSession().setAttribute(lastVisitedAttribute, null);
				response.sendRedirect(lastVisited);
				return;
			}

		}
		// Si on a ni de code ni d'auth à ce moment là c'est qu'on est pas dans
		// le processus de connexion, on peut donc vider l'attribut de session
		// last_visited
		if (lastVisited.length() > 0) {
			request.getSession().setAttribute(lastVisitedAttribute, null);
		}

		System.out.println("Publik user info");
		System.out.println("Token : "
			+ request.getSession().getAttribute(accessTokenAttribute));
		System.out
			.println(request.getSession().getAttribute(familyNameAttribute));
		System.out
			.println(request.getSession().getAttribute(givenNameAttribute));
		System.out
			.println(request.getSession().getAttribute(internalIdAttribute));
		System.out.println();

		super.processFilter(request, response, filterChain);
	}

	/**
	 * Redirige l'utilisateur vers la page de connexion de publik et enregistre
	 * sa dernière page visitée dans la session (attribut last_visited)
	 */
	private void redirectToLogin(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		request.getSession().setAttribute(lastVisitedAttribute,
			request.getRequestURL().toString());
		response.sendRedirect(
			"https://connexion-strasbourg.test.entrouvert.org/idp/oidc/authorize/?scope=openid%20profile&response_type=code&client_id=05f5f6a1-1e99-4909-8ac7-3fb41e606b08&redirect_uri=http://localhost:8080");
	}

	/**
	 * Essaye de récupérer les infos utilisateurs, si cela fonctionne,
	 * enregistre les attributs suivants dans la session : publik_access_token,
	 * publik_logged_in (true), publik_internal_id, publik_family_name,
	 * publik_given_name
	 */
	private void saveUserInfoInSession(HttpServletRequest request,
		HttpServletResponse response, FilterChain filterChain, String code)
		throws Exception {

		// Si oui, on récupère un token et les infos de l'utilisateur
		String authURL = StrasbourgPropsUtil.getPublikTokenURL();

		HttpURLConnection connection = (HttpURLConnection) new URL(authURL)
			.openConnection();

		connection.setRequestMethod("POST");

		// Authentification
		String username = StrasbourgPropsUtil.getPublikClientId();
		String password = StrasbourgPropsUtil.getPublikClientSecret();
		String encoded = Base64.getEncoder().encodeToString(
			(username + ":" + password).getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encoded);

		// Paramètres
		String parameters = "grant_type=authorization_code&code=" + code
			+ "&redirect_uri=" + PortalUtil.getPortalURL(request);
		byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length",
			Integer.toString(postDataLength));
		try (DataOutputStream wr = new DataOutputStream(
			connection.getOutputStream())) {
			wr.write(postData);
		}

		// Résultat
		String accessToken = "";
		try {
			InputStream is = connection.getInputStream();
			JSONObject tokenJson = readJsonFromInputStream(is);
			accessToken = tokenJson.getString("access_token");
			request.getSession().setAttribute(accessTokenAttribute,
				accessToken);
		} catch (Exception exception) {
			super.processFilter(request, response, filterChain);
			return;
		}

		if (Validator.isNotNull(accessToken)) {
			// Requête pour les infos utilisateurs
			String userInfoURL = StrasbourgPropsUtil.getPublikUserInfoURL();
			HttpURLConnection userInfoConnection = (HttpURLConnection) new URL(
				userInfoURL).openConnection();
			userInfoConnection.setRequestProperty("Authorization",
				"Bearer " + accessToken);

			// Résultat
			try {
				InputStream userInfoIs = userInfoConnection.getInputStream();
				JSONObject userInfoJson = readJsonFromInputStream(userInfoIs);
				String familyName = userInfoJson.getString("family_name");
				String givenName = userInfoJson.getString("given_name");
				String publikInternalId = userInfoJson.getString("sub");
				request.getSession().setAttribute(familyNameAttribute,
					familyName);
				request.getSession().setAttribute(givenNameAttribute,
					givenName);
				request.getSession().setAttribute(internalIdAttribute,
					publikInternalId);
				request.getSession().setAttribute(loggedInAttribute, true);
			} catch (Exception ex) {
			}
		}
	}

	private JSONObject readJsonFromInputStream(InputStream is)
		throws IOException, JSONException {
		try {
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONFactoryUtil.createJSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}

package eu.strasbourg.filter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(immediate = true, property = { "dispatcher=FORWARD", "dispatcher=REQUEST", "url-pattern=/*",
		"servlet-context-name=", "servlet-filter-name=SSO Publik" }, service = Filter.class)
public class OIDCFilter extends BaseFilter {
	private static final Log LOG = LogFactoryUtil.getLog(OIDCFilter.class);

	private String loggedInAttribute = "publik_logged_in";
	private String internalIdAttribute = "publik_internal_id";
	private String accessTokenAttribute = "publik_access_token";
	private String givenNameAttribute = "publik_given_name";
	private String familyNameAttribute = "publik_family_name";
	private String emailAttribute = "publik_email";
	String familyName;
	String givenName;
	String internalId;
	String accessToken;
	String email;

	@Override
	protected Log getLog() {
		return LOG;
	}

	@Override
	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws Exception {
		if (request.getServletPath() != null && request.getServletPath().startsWith("/o")) {
			filterChain.doFilter(request, response);
			return;
		}

		boolean isAlreadyLoggedIn = SessionParamUtil.getBoolean(request, loggedInAttribute);
		String code = ParamUtil.getString(request, "code");
		boolean wantsToLogout = ParamUtil.getBoolean(request, "logout");

		// Dans le cas où l'utilisateur est connecté
		if (isAlreadyLoggedIn) {
			// S'il souhaite se déconnecter, on le déconnecte
			if (wantsToLogout) {
				logout(request, response);
				return;
			}
			// Si son jwt n'est plus valide, on le déconnecte
			String jwtFromCookies = this.getCookieValue(request, "jwt");
			if (jwtFromCookies == null || jwtFromCookies.equals("")) {
				logout(request, response);
				return;
			} else {
				boolean isJwtValid = JWTUtils.checkJWT(jwtFromCookies, StrasbourgPropsUtil.getInternalSecret(),
						StrasbourgPropsUtil.getInternalIssuer());
				if (!isJwtValid) {
					logout(request, response);
					return;
				}
			}
		} else {

			// Sinon on vérifie s'il y a un paramètre "auth" dans la requête
			String auth = ParamUtil.getString(request, "auth");

			// Si c'est le cas on démarre le process OIDC
			if (auth.equals("publik") && code.length() == 0) {
				// On renvoie l'utilisateur vers la page de connexion de publik
				redirectToLogin(request, response);
				return;
			}

			// S'il y a "code" dans la requête : l'utilisateur revient du
			// process OIDC
			if (code.length() > 0) {
				// On récupère alors le JWT et l'access token via une requête
				// vers le provider
				LOG.info("Code received, requestion tokens");
				JSONObject json = sendTokenRequest(request, response, filterChain, code);
				if (json == null) {
					LOG.info("Token empty");
					super.processFilter(request, response, filterChain);
					return;
				}
				// On récupère l'access token
				accessToken = json.getString("access_token");
				LOG.info("Got access token");

				// Ainsi que l'id token sous la forme d'un jwt
				String jwt = json.getString("id_token");
				LOG.info("Got JWT");

				// On vérifie sa validité
				boolean isJwtValid = JWTUtils.checkJWT(jwt, StrasbourgPropsUtil.getPublikClientSecret(),
						StrasbourgPropsUtil.getPublikIssuer());
				if (isJwtValid) {
					LOG.info("Valid JWT");

					// Le jwt est valide, on extrait les données
					givenName = JWTUtils.getJWTClaim(jwt, "given_name", StrasbourgPropsUtil.getPublikClientSecret(),
							StrasbourgPropsUtil.getPublikIssuer());
					familyName = JWTUtils.getJWTClaim(jwt, "family_name", StrasbourgPropsUtil.getPublikClientSecret(),
							StrasbourgPropsUtil.getPublikIssuer());
					internalId = JWTUtils.getJWTClaim(jwt, "sub", StrasbourgPropsUtil.getPublikClientSecret(),
							StrasbourgPropsUtil.getPublikIssuer());
					email = JWTUtils.getJWTClaim(jwt, "email", StrasbourgPropsUtil.getPublikClientSecret(),
							StrasbourgPropsUtil.getPublikIssuer());

					// On crée un nouveau jwt signé internalement pour y mettre
					// l'id utilisateur
					String internalJwtToken = JWTUtils.createJWT(internalId, 60 * 60 * 24);
					// On l'enregistre dans un cookie
					createCookie(request, response, "jwt", internalJwtToken);

					// On met les infos de l'utilisateur dans la session
					putUserInfoInSession(request);

					// Et on update la base
					updateUserInfoInDatabase();
				}
			}

		}

		// Si on n'est pas connecté mais qu'on a un jwt dans les cookies, on
		// doit le vérifier et connecter l'utilisateur s'il est valide
		if (!isAlreadyLoggedIn && !wantsToLogout) {
			String jwtFromCookies = this.getCookieValue(request, "jwt");
			if (jwtFromCookies != null && !jwtFromCookies.equals("")) {
				boolean isJwtValid = JWTUtils.checkJWT(jwtFromCookies, StrasbourgPropsUtil.getInternalSecret(),
						StrasbourgPropsUtil.getInternalIssuer());
				if (isJwtValid) {
					// On récupère l'identifiant utilisateur dans le jwt
					internalId = JWTUtils.getJWTClaim(jwtFromCookies, "sub", StrasbourgPropsUtil.getInternalSecret(),
							StrasbourgPropsUtil.getInternalIssuer());

					// Et les autres données en base
					PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(this.internalId);
					givenName = user.getFirstName();
					familyName = user.getLastName();
					email = user.getEmail();

					// On les met dans la session
					putUserInfoInSession(request);
				}
			}
		}

		// Si un attribut "state" se trouve dans la query string, on redirige
		// vers la page correspondante
		String redirectURL = ParamUtil.getString(request, "state");
		if (Validator.isUrl(redirectURL, false)) {
			LOG.info("State parameter : redirect to " + redirectURL);
			response.sendRedirect(redirectURL);
			return;
		}

		super.processFilter(request, response, filterChain);
	}

	/**
	 * Redirige l'utilisateur vers la page de connexion de publik et enregistre
	 * sa dernière page visitée dans la session (attribut last_visited)
	 */
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(
				StrasbourgPropsUtil.getPublikAuthorizeURL() + "&state=" + request.getRequestURL().toString());
	}

	/**
	 * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id
	 * token
	 */
	private JSONObject sendTokenRequest(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, String code) throws IOException {
		// Si oui, on récupère un token et les infos de l'utilisateur
		String authURL = StrasbourgPropsUtil.getPublikTokenURL();

		HttpURLConnection connection = (HttpURLConnection) new URL(authURL).openConnection();

		connection.setRequestMethod("POST");

		// Authentification
		String username = StrasbourgPropsUtil.getPublikClientId();
		String password = StrasbourgPropsUtil.getPublikClientSecret();
		String encoded = Base64.getEncoder()
				.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encoded);

		// Paramètres
		String parameters = "grant_type=authorization_code&code=" + code + "&redirect_uri="
				+ StrasbourgPropsUtil.getURL();
		byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");

		connection.setRequestProperty("Content-Type", ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
			wr.write(postData);
		}

		// Résultat
		try {
			InputStream is = connection.getInputStream();
			JSONObject json = readJsonFromInputStream(is);
			return json;
		} catch (Exception ex) {
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(connection.getErrorStream(), Charset.forName("UTF-8")));
			String str = readAll(rd);
			System.out.println(str);
			return null;
		}

	}

	/**
	 * Enregistrement dans la session de toutes les infos sur l'utilisateur
	 */
	private void putUserInfoInSession(HttpServletRequest request) {
		request.getSession().setAttribute(loggedInAttribute, true);
		request.getSession().setAttribute(accessTokenAttribute, accessToken);
		request.getSession().setAttribute(familyNameAttribute, familyName);
		request.getSession().setAttribute(givenNameAttribute, givenName);
		request.getSession().setAttribute(internalIdAttribute, internalId);
		request.getSession().setAttribute(emailAttribute, email);
	}

	/**
	 * Logout a user
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// Set logout to false in session and delete other attributes
		request.getSession().setAttribute(loggedInAttribute, false);
		request.getSession().setAttribute(accessTokenAttribute, null);
		request.getSession().setAttribute(familyNameAttribute, null);
		request.getSession().setAttribute(givenNameAttribute, null);
		request.getSession().setAttribute(internalIdAttribute, null);
		request.getSession().setAttribute(emailAttribute, null);

		createCookie(request, response, "jwt", "");
		try {
			response.sendRedirect(StrasbourgPropsUtil.getPublikLogoutURL() + "?post_logout_redirect_uri="
					+ StrasbourgPropsUtil.getURL() + "&state=" + request.getRequestURL().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre ou update l'utilisateur en base
	 */
	private void updateUserInfoInDatabase() {
		if (internalId != null && internalId.length() > 0) {
			PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(this.internalId);
			if (user == null) {
				user = PublikUserLocalServiceUtil.createPublikUser();
				user.setPublikId(internalId);
			}
			user.setAccessToken(accessToken);
			user.setFirstName(givenName);
			user.setLastName(familyName);
			user.setEmail(email);
			PublikUserLocalServiceUtil.updatePublikUser(user);
		}
	}

	private void createCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		String url = request.getRequestURL().toString();
		String domain = getMainDomain(url);
		cookie.setDomain(domain);
		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	private String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookieValue = cookie.getValue();
				}
			}
		}
		return cookieValue;
	}

	private JSONObject readJsonFromInputStream(InputStream is) throws IOException, JSONException {
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
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

	/**
	 * Si le paramètre passé est une url contenant un nom de domaine (pas
	 * localhost), on renvoie le nom de domaine sans le sous domaine
	 */
	private String getMainDomain(String urlString) {
		URL url = null;
		String domainString = null;
		try {
			url = new URL(urlString);
			String[] domainNameParts = url.getHost().split("\\.");
			domainString = domainNameParts[domainNameParts.length - 1];
			if (domainNameParts.length > 1) {
				domainString = "." + domainNameParts[domainNameParts.length - 2] + "." + domainString;
			}

		} catch (MalformedURLException e) {
		}

		return domainString;
	}

}

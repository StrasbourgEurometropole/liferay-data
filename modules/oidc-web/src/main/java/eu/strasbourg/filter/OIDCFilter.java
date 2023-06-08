package eu.strasbourg.filter;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component(immediate = true, property = {"dispatcher=FORWARD", "dispatcher=REQUEST", "url-pattern=/*",
        "servlet-context-name=", "servlet-filter-name=SSO Publik"}, service = Filter.class)
public class OIDCFilter extends BaseFilter {
    private static final Log LOG = LogFactoryUtil.getLog(OIDCFilter.class);

    private String loggedInAttribute = "publik_logged_in";
    private String internalIdAttribute = "publik_internal_id";
    private String accessTokenAttribute = "publik_access_token";
    private String givenNameAttribute = "publik_given_name";
    private String familyNameAttribute = "publik_family_name";
    private String emailAttribute = "publik_email";
    private String hasPactSignedAttribute = "has_pact_signed";
    private String isBanishAttribute = "is_banish";
    private String photoAttribute = "photo";


    @Override
    protected Log getLog() {
        return LOG;
    }

    @Override
    protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws Exception {

        String familyName;
        String givenName;
        String internalId;
        String accessToken = null;
        boolean hasPactSigned = false;
        String email;
        boolean isBanish = false;
        String photo;

        if (request.getServletPath() != null && request.getServletPath().startsWith("/o")) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isAlreadyLoggedIn = SessionParamUtil.getBoolean(request, loggedInAttribute);
        String code = ParamUtil.getString(request, "code");
        boolean wantsToLogout = ParamUtil.getBoolean(request, "logout");
        String logoutOrigin = ParamUtil.getString(request, "origin");
        if (wantsToLogout && logoutOrigin.equals("publik")) {
            logout(request, response);
            response.sendRedirect("https://connexion.strasbourg.eu/static/authentic2/images/ok.png");
            return;
        }

        // Dans le cas où l'utilisateur est connecté
        if (isAlreadyLoggedIn) {
            // S'il souhaite se déconnecter, on le déconnecte
            if (wantsToLogout) {
                LOG.debug("Logout because of request parameter");
                logout(request, response);
                redirectToIdPLogout(request, response);
                return;
            }
            // On fait des vérifications sur le JWT provenant des cookies
            String jwtFromCookies = this.getCookieValue(request, "jwt_" + StrasbourgPropsUtil.getEnvironment());
            // S'il est vide, on force la déconnexion
            if (jwtFromCookies == null || jwtFromCookies.equals("")) {
                LOG.debug("Logout because no JWT");
                logout(request, response);
                redirectToIdPLogout(request, response);
                return;
            } else {
                boolean isJwtValid = JWTUtils.checkJWT(jwtFromCookies, StrasbourgPropsUtil.getInternalSecret(),
                        StrasbourgPropsUtil.getInternalIssuer());
                if (!isJwtValid) { // S'il n'est plus valide, on force également la déconnexion
                    LOG.debug("Logout because of invalid JWT");
                    logout(request, response);
                } else {
                    // On vérifie aussi le jwt enregistré en session
                    // S'il ne correspond pas au même utilisateur que celui dans les cookies
                    // on force le flag isAlreadyLoggedIn à false
                    // Cela permet plus loin de bien exécuter le code mettant en session les données provenant du JWT
                    String internalIdFromCookie = JWTUtils.getJWTClaim(jwtFromCookies, "sub", StrasbourgPropsUtil.getInternalSecret(),
                            StrasbourgPropsUtil.getInternalIssuer());
                    String internalIdFromSession = SessionParamUtil.getString(request, internalIdAttribute);
                    if (!internalIdFromCookie.equals("") && !internalIdFromCookie.equals(internalIdFromSession)) {
                        isAlreadyLoggedIn = false;
                        request.getSession().invalidate();
                        LOG.debug("SESSION INVALIDATION - id from cookie: " + internalIdFromCookie + " - id from session: " + internalIdFromSession);
                    }
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
                LOG.debug("Code received, requesting tokens");
                JSONObject json = sendTokenRequest(request, code);
                if (json == null) {
                    LOG.debug("Token empty");
                    super.processFilter(request, response, filterChain);
                    return;
                }
                // On récupère l'access token
                accessToken = json.getString("access_token");
                LOG.debug("Got access token");

                // Ainsi que l'id token sous la forme d'un jwt
                String jwt = json.getString("id_token");
                LOG.debug("Got JWT");

                // On vérifie sa validité
                boolean isJwtValid = JWTUtils.checkJWT(jwt, StrasbourgPropsUtil.getPublikClientSecret(),
                        StrasbourgPropsUtil.getPublikIssuer());
                if (isJwtValid) {
                    LOG.debug("Valid JWT");

                    // Le jwt est valide, on extrait les données
                    givenName = JWTUtils.getJWTClaim(jwt, "given_name", StrasbourgPropsUtil.getPublikClientSecret(),
                            StrasbourgPropsUtil.getPublikIssuer());
                    familyName = JWTUtils.getJWTClaim(jwt, "family_name", StrasbourgPropsUtil.getPublikClientSecret(),
                            StrasbourgPropsUtil.getPublikIssuer());
                    internalId = JWTUtils.getJWTClaim(jwt, "sub", StrasbourgPropsUtil.getPublikClientSecret(),
                            StrasbourgPropsUtil.getPublikIssuer());
                    email = JWTUtils.getJWTClaim(jwt, "email", StrasbourgPropsUtil.getPublikClientSecret(),
                            StrasbourgPropsUtil.getPublikIssuer());
                    photo = PublikApiClient.getUserPhoto(internalId);
                    String accordPlacit = JWTUtils.getJWTClaim(jwt, "accord_placit",
                            StrasbourgPropsUtil.getPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
                    String listingPlacit = JWTUtils.getJWTClaim(jwt, "accord_placit_listing",
                            StrasbourgPropsUtil.getPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
                    LOG.debug(accordPlacit);
                    LOG.debug(listingPlacit);

                    // Recuperation des donnees inherantes a la plateforme participative
                    PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
                    if (user != null) {
                        hasPactSigned = user.getPactSignature() != null ? true : false;
                        isBanish = user.isBanned();
                    }else
                        hasPactSigned = accordPlacit != null && accordPlacit.equals("true") ? true : false;

                    // On crée un nouveau jwt signé internalement pour y mettre
                    // l'id utilisateur
                    String internalJwtToken = JWTUtils.createJWT(
                            internalId, 60 * 60 * 24,
                            StrasbourgPropsUtil.getInternalSecret());
                    // On l'enregistre dans un cookie
                    createCookie(request, response, "jwt_" + StrasbourgPropsUtil.getEnvironment(), internalJwtToken);

                    // On met les infos de l'utilisateur dans la session
                    putUserInfoInSession(request, accessToken, familyName, givenName,
                            internalId, email, hasPactSigned, isBanish, photo);

                    // Et on update la base
                    PublikUserLocalServiceUtil.updateUserInfoInDatabase(internalId, accessToken, givenName,
                            familyName, email, photo, accordPlacit, listingPlacit);
                }
            }

        }

        // Si on n'est pas connecté mais qu'on a un jwt dans les cookies, on
        // doit le vérifier et connecter l'utilisateur s'il est valide
        if (!isAlreadyLoggedIn && !wantsToLogout) {
            String jwtFromCookies = this.getCookieValue(request, "jwt_" + StrasbourgPropsUtil.getEnvironment());
            if (jwtFromCookies != null && !jwtFromCookies.equals("")) {
                boolean isJwtValid = JWTUtils.checkJWT(jwtFromCookies, StrasbourgPropsUtil.getInternalSecret(),
                        StrasbourgPropsUtil.getInternalIssuer());
                if (isJwtValid) {
                    // On récupère l'identifiant utilisateur dans le jwt
                    internalId = JWTUtils.getJWTClaim(jwtFromCookies, "sub", StrasbourgPropsUtil.getInternalSecret(),
                            StrasbourgPropsUtil.getInternalIssuer());

                    // Et les autres données en base
                    PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
                    if (user != null) {
                        givenName = user.getFirstName();
                        familyName = user.getLastName();
                        email = user.getEmail();
                        hasPactSigned = user.getPactSignature() != null ? true : false;
                        isBanish = user.isBanned();
                        photo = user.getImageURL();

                        // On les met dans la session
                        putUserInfoInSession(request, accessToken, familyName, givenName, internalId,
                                email, hasPactSigned, isBanish, photo);
                    }
                }
            }
        }

        // Si un attribut "state" se trouve dans la query string, on redirige
        // vers la page correspondante
        String redirectURL = ParamUtil.getString(request, "state");
        if (Validator.isUrl(redirectURL, true)) {
            LOG.debug("State parameter : redirect to " + redirectURL);
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

                StrasbourgPropsUtil.getPublikAuthorizeURL()
                        + "&redirect_uri=" + this.getDomainRoot(request)
                        + "&state=" + getProtocoledRequestURL(request));
    }

    /**
     * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id
     * token
     */
    private JSONObject sendTokenRequest(HttpServletRequest request, String code) throws IOException {
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
                + this.getDomainRoot(request);
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
            return JSONHelper.readJsonFromInputStream(is);
        } catch (Exception ex) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream(), Charset.forName("UTF-8")));
            String str = JSONHelper.readAll(rd);
            System.out.println(str);
            return null;
        }

    }

    /**
     * Enregistrement dans la session de toutes les infos sur l'utilisateur
     */
    private void putUserInfoInSession(HttpServletRequest request, String accessToken, String familyName, String givenName,
                                      String internalId, String email, boolean hasPactSigned, boolean isBanish, String photo) {
        request.getSession().setAttribute(loggedInAttribute, true);
        request.getSession().setAttribute(accessTokenAttribute, accessToken);
        request.getSession().setAttribute(familyNameAttribute, familyName);
        request.getSession().setAttribute(givenNameAttribute, givenName);
        request.getSession().setAttribute(internalIdAttribute, internalId);
        request.getSession().setAttribute(emailAttribute, email);
        request.getSession().setAttribute(hasPactSignedAttribute, hasPactSigned);
        request.getSession().setAttribute(isBanishAttribute, isBanish);
        request.getSession().setAttribute(photoAttribute, photo);
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
        request.getSession().setAttribute(hasPactSignedAttribute, null);
        request.getSession().setAttribute(isBanishAttribute, null);
        request.getSession().setAttribute(photoAttribute, null);

        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");

        createCookie(request, response, "jwt_" + StrasbourgPropsUtil.getEnvironment(), "");
    }

    /**
     * Redirect the user to OIDC provider logout URL
     */
    private void redirectToIdPLogout(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(StrasbourgPropsUtil.getPublikLogoutURL()
                    + "?post_logout_redirect_uri=" + this.getDomainRoot(request)
                    + "&state=" + URLEncoder.encode(getProtocoledRequestURL(request), "UTF-8"));
        } catch (IOException e) {
            _log.error(e.getMessage(), e);
        }
    }

    private void createCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        String url = request.getRequestURL().toString();
        String domain = getMainDomain(url);
        cookie.setDomain(domain);
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

    /**
     * Si le paramètre passé est une url contenant un nom de domaine (pas
     * localhost), on renvoie le nom de domaine sans le sous domaine
     */
    private String getMainDomain(String urlString) {
        String domainString = null;
        try {
            URL url = new URL(urlString);
            String[] domainNameParts = url.getHost().split("\\.");
            domainString = domainNameParts[domainNameParts.length - 1];
            if (domainNameParts.length > 1) {
                domainString = "." + domainNameParts[domainNameParts.length - 2] + "." + domainString;
            }

        } catch (MalformedURLException ignored) {
        }

        return domainString;
    }

    private String getDomainRoot(HttpServletRequest request) {
        return getProtocoledRequestURL(request)
                .replace(request.getPathInfo(), "")
                .replace(request.getServletPath(), "");
    }

    /**
     * Transforme l'URL de la requête pour correspondre au protocole renseigné dans le portal ext
     * @param request request
     * @return L'url retravaillé par le portal ext
     */
    private String getProtocoledRequestURL(HttpServletRequest request) {
        return PortalUtil.getPortalURL(request) + request.getRequestURI();
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

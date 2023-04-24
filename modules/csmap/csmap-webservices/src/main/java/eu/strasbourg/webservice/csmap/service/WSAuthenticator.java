package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException;
import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.BaseNonce;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.service.BaseNonceLocalService;
import eu.strasbourg.service.csmap.service.RefreshTokenLocalService;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.ServiceContextHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.exception.auth.BaseNonceCreationFailedException;
import eu.strasbourg.webservice.csmap.exception.auth.BaseNonceExpiredException;
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenCreationFailedException;
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenExpiredException;
import eu.strasbourg.webservice.csmap.utils.WSTokenUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.core.HttpHeaders;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 * Service s'occuppant de valider l'authentification entre l'application CSMAP et Authentik
 */
@Component(
        immediate = true,
        service = WSAuthenticator.class
)
public class WSAuthenticator {

    /**
     * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id token
     */
    public JSONObject sendTokenRequest(String code, int timeOut) throws IOException {
        // Récupération des URL/URI configurables
        String authURL = StrasbourgPropsUtil.getPublikTokenURL();
        String redirectURI = WSConstants.REDIRECT_URI;

        // Initialisation de la requête
        HttpURLConnection connection = (HttpURLConnection) new URL(authURL).openConnection();
        connection.setConnectTimeout(timeOut);
        connection.setReadTimeout(timeOut);
        connection.setRequestMethod("POST");

        // Authentification
        String username = StrasbourgPropsUtil.getCSMAPPublikClientId();
        String password = StrasbourgPropsUtil.getCSMAPPublikClientSecret();
        String encoded = Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        connection.setRequestProperty("Authorization", "Basic " + encoded);

        // Paramètres
        String parameters = "grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirectURI;
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
            return WSTokenUtil.readJsonFromInputStream(is);
        } catch (Exception ex) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            String str = WSTokenUtil.readAll(rd);
            _log.error(str);
            return null;
        }
    }

    /**
     * Génère un refreshToken
     * @return Valeur d'un refreshToken
     */
    public String generateRefreshToken() {
        return WSTokenUtil.generateRandomToken(WSConstants.TOKEN_LENGTH);
    }

    /**
     * Enregistre un refresh token pour un utilisateur Publik à partir d'une valeur de refrehToken
     * @param publikId ID de l'utilisateur à qui générer le refresh token
     */
    public RefreshToken saveRefreshTokenForUser(String publikId, String refreshTokenValue) throws RefreshTokenCreationFailedException {
        try {
            ServiceContext sc = ServiceContextHelper.generateGlobalServiceContext();

            RefreshToken refreshToken = refreshTokenLocalService.createRefreshToken(sc);

            refreshToken.setCreateDate(new Date());
            refreshToken.setPublikId(publikId);
            refreshToken.setValue(WSTokenUtil.hashToken(refreshTokenValue));

            return refreshTokenLocalService.updateRefreshToken(refreshToken, sc);
        } catch (PortalException | NoSuchAlgorithmException e) {
            throw new RefreshTokenCreationFailedException(e);
        }
    }

    /**
     * Génére et enregistre un BaseNonce
     */
    public BaseNonce generateAndSaveBaseNonce() throws BaseNonceCreationFailedException {
        try {
            ServiceContext sc = ServiceContextHelper.generateGlobalServiceContext();

            BaseNonce baseNonce = baseNonceLocalService.createBaseNonce(sc);

            baseNonce.setCreateDate(new Date());
            baseNonce.setValue(WSTokenUtil.generateRandomToken(WSConstants.BASE_NONCE_LENGTH));

            return baseNonceLocalService.updateBaseNonce(baseNonce, sc);
        } catch (PortalException e) {
            throw new BaseNonceCreationFailedException(e);
        }
    }


    /**
     * Recherche le refresh token en base, vérifie sa validité
     * Supprime le refresh token trouvé si ce dernier n'est plus valide
     *
     * @param refreshTokenValue la valeur du refresh token (le token en lui-même et non l'objet du service)
     * @return le refresh token valide trouvé en base
     * @throws NoSuchRefreshTokenException Le refresh token n'existe pas en base
     * @throws RefreshTokenExpiredException Le refresh token trouvé en base est expiré
     */
    public RefreshToken controlRefreshToken(String refreshTokenValue)
            throws NoSuchRefreshTokenException, RefreshTokenExpiredException, NoSuchAlgorithmException {
        RefreshToken refreshToken = refreshTokenLocalService.fetchByValue(WSTokenUtil.hashToken(refreshTokenValue));

        if (Validator.isNull(refreshToken))
            throw new NoSuchRefreshTokenException(refreshTokenValue);

        if (!WSTokenUtil.isRefreshTokensDateValid(refreshToken.getCreateDate(),
                StrasbourgPropsUtil.getCSMAPRefreshTokenNbValidityDays())) {
            refreshTokenLocalService.removeRefreshToken(refreshToken.getRefreshTokenId());
            throw new RefreshTokenExpiredException(refreshTokenValue);
        }

        return refreshToken;
    }

    /**
     * Recherche le baseNonce en base, vérifie sa validité
     *
     * @param baseNonceValue la valeur du baseNonce (le baseNonce en lui-même et non l'objet du service)
     * @return le baseNonce valide trouvé en base
     * @throws NoSuchBaseNonceException Le baseNonce n'existe pas en base
     * @throws BaseNonceExpiredException Le baseNonce trouvé en base est expiré
     */
    public BaseNonce controlBaseNonce(String baseNonceValue)
            throws NoSuchBaseNonceException, BaseNonceExpiredException {
        BaseNonce baseNonce = baseNonceLocalService.fetchByValue(baseNonceValue);

        if (Validator.isNull(baseNonce))
            throw new NoSuchBaseNonceException(WSConstants.ERROR_NO_SUCH_BASE_NONCE +" : " +  baseNonceValue);

        if (!WSTokenUtil.isBaseNonceDateValid(baseNonce.getCreateDate(),
                WSConstants.BASE_NONCE_VALIDITY_SECONDS)) {
            throw new BaseNonceExpiredException(baseNonceValue);
        }

        return baseNonce;
    }

    public boolean checkNonce(String idToken, String baseNonce, String codeVerifier) {
        boolean result = false;

        try {
            String codeChallenge = WSTokenUtil.hashCodeVerifier(codeVerifier);
            String nonce = baseNonce + codeChallenge;

            String codeTochallenge = JWTUtils.getJWTClaim(idToken, WSConstants.NONCE,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());

            if(nonce.equals(codeTochallenge)) {
                result = true;
            }

        } catch (NoSuchAlgorithmException e) {
            _log.error("Algorithme inexistant lors du hashe du code_verifier en code_challenge");
            return false;
        }

        return result;
    }

    /**
     * Suppression du base Nonce
     * @param baseNonce base nonce à supprimer
     */
    public void deleteBaseNonce(BaseNonce baseNonce) {
        baseNonceLocalService.deleteBaseNonce(baseNonce);
    }

    /**
     * Vérifie la validité du JWT_CSM contenu dans le Header du requête et retourne l'utilisateur Publik associé
     * @note À utiliser au début de toutes les fonctions du WS ncéssitant une authentification
     *
     * @param httpHeaders Header de la requête HTTP accèdant à un service nécessitant une authentification
     * @return L'utilisateur Publik accèdant au service
     * @throws NoJWTInHeaderException Le JWT n'est pas présent dans le Header HTTP
     * @throws InvalidJWTException Le JWT trouvé n'est pas valide
     * @throws NoSubInJWTException Le JWT n'a pas de paramètre sub renseigné
     * @throws NoSuchPublikUserException Le "sub" renseigné de correspond à aucun utilisateur Publik
     */
    public PublikUser validateUserInJWTHeader(HttpHeaders httpHeaders)
            throws NoJWTInHeaderException, InvalidJWTException, NoSubInJWTException, NoSuchPublikUserException {
        // Le JWT est renseigné ?
        String jwt = httpHeaders.getHeaderString(WSConstants.JWT_HEADER_NAME);
        if (Validator.isNull(jwt))
            throw new NoJWTInHeaderException();

        // Le JWT est valide ?
        boolean isJwtValid = JWTUtils.checkJWT(jwt, StrasbourgPropsUtil.getCSMAPInternalSecret(),
                StrasbourgPropsUtil.getInternalIssuer(), WSConstants.JWT_VALIDITY_LEEWAY);
        if (!isJwtValid)
            throw new InvalidJWTException();

        // L'identifiant utilisateur Publik (sub) est renseigné ?
        String publikId = JWTUtils.getJWTClaim(jwt, WSConstants.SUB,
                StrasbourgPropsUtil.getCSMAPInternalSecret(), StrasbourgPropsUtil.getInternalIssuer());
        if (Validator.isNull(publikId))
            throw new NoSubInJWTException();

        PublikUser publikUser = publikUserLocalService.getByPublikUserId(publikId);
        if (Validator.isNull(publikUser))
            throw new NoSuchPublikUserException();

        return publikUser;
    }

    /**
     * Met à jour un utilisateur Publik ou le crée s'il n'existe pas en base
     *
     * @param jwt JWT reçu lors de la demande de token à Authentik (appelé "id_token")
     */
    public void updateUserFromAuthentikInDatabase(String jwt) {
        String givenName = JWTUtils.getJWTClaim(jwt, WSConstants.GIVEN_NAME,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String familyName = JWTUtils.getJWTClaim(jwt, WSConstants.FAMILY_NAME,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String internalId = JWTUtils.getJWTClaim(jwt, WSConstants.SUB,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String email = JWTUtils.getJWTClaim(jwt, WSConstants.EMAIL,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String accordPlacit = JWTUtils.getJWTClaim(jwt, WSConstants.ACCORD_PLACIT,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String listingPlacit = JWTUtils.getJWTClaim(jwt, WSConstants.LISTING_PLACIT,
                StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());
        String photo = PublikApiClient.getUserPhoto(internalId);
        // Envoi null car pour csmap on ne modifie pas l'accessToken
        publikUserLocalService.updateUserInfoInDatabase(
                internalId, null, givenName, familyName, email, photo, accordPlacit, listingPlacit);
    }

    @Reference(unbind = "-")
    protected void setRefreshTokenLocalService(RefreshTokenLocalService refreshTokenLocalService) {
        this.refreshTokenLocalService = refreshTokenLocalService;
    }

    @Reference(unbind = "-")
    protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
        this.publikUserLocalService = publikUserLocalService;
    }

    @Reference
    protected BaseNonceLocalService baseNonceLocalService;

    @Reference
    protected RefreshTokenLocalService refreshTokenLocalService;

    @Reference
    protected PublikUserLocalService publikUserLocalService;

    private static final Log _log = LogFactoryUtil.getLog(WSAuthenticator.class);

}

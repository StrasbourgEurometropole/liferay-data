package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.service.RefreshTokenLocalService;
import eu.strasbourg.utils.ServiceContextHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.RefreshTokenExpiredException;
import eu.strasbourg.webservice.csmap.exception.RefreshTokenCreationFailedException;
import eu.strasbourg.webservice.csmap.utils.WSTokenUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * Service s'occuppant de valider l'authentification entre l'application CSMAP et Authentik
 */
@Component(
        immediate = true,
        property = {},
        service = WSAuthenticator.class
)
public class WSAuthenticator {

    /**
     * Envoi de la requête vers l'IdP afin de récupérer un access token et l'id token
     */
    public JSONObject sendTokenRequest(String code) throws IOException {
        // Récupération des URL/URI configurables
        String authURL = StrasbourgPropsUtil.getPublikTokenURL();
        String redirectURI = WSConstants.REDIRECT_URI;

        // Initialisation de la requête
        HttpURLConnection connection = (HttpURLConnection) new URL(authURL).openConnection();
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
            System.out.println(str);
            return null;
        }

    }

    /**
     * Génére et enregistre un refresh token pour un utilisateur Publik
     * @param publikId ID de l'utilisateur à qui générer le refresh token
     */
    public RefreshToken generateAndSaveRefreshTokenForUser(String publikId) throws RefreshTokenCreationFailedException {
        try {
            ServiceContext sc = ServiceContextHelper.generateGlobalServiceContext();

            RefreshToken refreshToken = refreshTokenLocalService.createRefreshToken(sc);

            refreshToken.setCreateDate(new Date());
            refreshToken.setPublikId(publikId);
            refreshToken.setValue(WSTokenUtil.generateRandomToken(WSConstants.TOKEN_LENGTH));

            return refreshTokenLocalService.updateRefreshToken(refreshToken, sc);
        } catch (PortalException e) {
            throw new RefreshTokenCreationFailedException(e);
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
            throws NoSuchRefreshTokenException, RefreshTokenExpiredException {
        RefreshToken refreshToken = refreshTokenLocalService.fetchByValue(refreshTokenValue);

        if (Validator.isNull(refreshToken))
            throw new NoSuchRefreshTokenException();

        if (!WSTokenUtil.isRefreshTokensDateValid(refreshToken.getCreateDate(),
                StrasbourgPropsUtil.getCSMAPRefreshTokenNbValidityDays())) {
            refreshTokenLocalService.removeRefreshToken(refreshToken.getRefreshTokenId());
            throw new RefreshTokenExpiredException();
        }

        return refreshToken;
    }

    @Reference(unbind = "-")
    protected void setRefreshTokenLocalService(RefreshTokenLocalService refreshTokenLocalService) {
        this.refreshTokenLocalService = refreshTokenLocalService;
    }

    @Reference
    protected RefreshTokenLocalService refreshTokenLocalService;

}

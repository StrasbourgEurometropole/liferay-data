package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.exception.PwdEncryptorException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException;
import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.BaseNonce;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.service.RefreshTokenLocalServiceUtil;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.ProcedureHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.exception.NoUserFormException;
import eu.strasbourg.utils.models.Procedure;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.exception.auth.AuthenticationFailedException;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.auth.BaseNonceCreationFailedException;
import eu.strasbourg.webservice.csmap.exception.auth.BaseNonceExpiredException;
import eu.strasbourg.webservice.csmap.exception.auth.InvalidNonceException;
import eu.strasbourg.webservice.csmap.exception.auth.NoCodeVerifierException;
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenExpiredException;
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenCreationFailedException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import eu.strasbourg.webservice.csmap.utils.WSTokenUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author angelique.champougny
 * @author jeremy.zwickert
 * @author cédric.henry
 */
@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_AUTH_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_AUTH_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class AuthApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-base-nonce")
    /**
     * Créer un NONCE en BDD
     */
    public Response getBaseNonce() {
        JSONObject jsonResponse =JSONFactoryUtil.createJSONObject();
        try {

            BaseNonce baseNonce = authenticator.generateAndSaveBaseNonce();
            jsonResponse.put(WSConstants.JSON_BASE_NONCE, baseNonce.getValue());

        } catch (BaseNonceCreationFailedException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @GET
    @Produces("application/json")
    @Path("/authentication/{code}/{baseNonce}/{codeVerifier}")
    public Response authentication(
            @PathParam("code") String code,
            @PathParam("baseNonce") String baseNonce,
            @PathParam("codeVerifier") String codeVerifier) {

        JSONObject jsonResponse =JSONFactoryUtil.createJSONObject();

        try {
            if(Validator.isNull(codeVerifier))
                throw new NoCodeVerifierException();
            BaseNonce validBaseNonce = authenticator.controlBaseNonce(baseNonce);

            JSONObject authentikJSON = authenticator.sendTokenRequest(code, WSConstants.TIMEOUT);

            if (Validator.isNull(authentikJSON))
                throw new AuthenticationFailedException();

            String authentikJWT = authentikJSON.getString(WSConstants.ID_TOKEN);

            boolean isJwtValid = JWTUtils.checkJWT(
                    authentikJWT,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                    StrasbourgPropsUtil.getPublikIssuer());

            if (!isJwtValid)
                throw new InvalidJWTException();

            boolean isNonceValid = authenticator.checkNonce(
                    authentikJWT,
                    baseNonce,
                    codeVerifier);

            if (!isNonceValid)
                throw new InvalidNonceException();

            String sub = JWTUtils.getJWTClaim(authentikJWT, WSConstants.SUB,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());

            authenticator.updateUserFromAuthentikInDatabase(authentikJWT);

            String csmapJWT = JWTUtils.createJWT(
                    sub, WSConstants.JWT_VALIDITY_SECONDS,
                    StrasbourgPropsUtil.getCSMAPInternalSecret());
            // On génère la valeur non hashée du RefreshToken
            String refreshTokenValue = authenticator.generateRefreshToken();
            // On sauvegarde le refreshToken en base. Il est hashé par la méthode avant enregistrement en BDD
            authenticator.saveRefreshTokenForUser(sub, refreshTokenValue);

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);
            // C'est la valeur non hashée du refreshToken qui est renvoyé à l'application
            jsonResponse.put(WSConstants.JSON_REFRESH_TOKEN, refreshTokenValue);

            // On supprime maintenant le Base Nonce en BDD pusiqu'il n'est utilisable qu'une seule fois
            authenticator.deleteBaseNonce(validBaseNonce);

        } catch (InvalidJWTException | IOException | AuthenticationFailedException | BaseNonceExpiredException | NoSuchBaseNonceException | NoCodeVerifierException | InvalidNonceException e) {
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (RefreshTokenCreationFailedException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @POST
    @Produces("application/json")
    @Path("/get-new-jwt")
    public Response getNewJWT(
            @FormParam("refreshToken") String refreshTokenvalue) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            RefreshToken validRefreshToken = authenticator.controlRefreshToken(refreshTokenvalue);

            String csmapJWT = JWTUtils.createJWT(
                    validRefreshToken.getPublikId(), WSConstants.JWT_VALIDITY_SECONDS,
                    StrasbourgPropsUtil.getCSMAPInternalSecret());

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);

        } catch (NoSuchRefreshTokenException | RefreshTokenExpiredException e) {
            log.info(e.getMessage());
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("No Algorithm found");
            return WSResponseUtil.buildErrorResponse(401, "No Algorithm found");
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @GET
    @Produces("application/json")
    @Path("/logout")
    public Response logout(
            @Context HttpHeaders httpHeaders) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);
            List<RefreshToken> refreshTokens = RefreshTokenLocalServiceUtil.getByPublikId(publikUser.getPublikId());
            for(RefreshToken refreshToken : refreshTokens) {
                RefreshTokenLocalServiceUtil.removeRefreshToken(refreshToken.getRefreshTokenId());
            }
        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (NoSuchRefreshTokenException e) {
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @POST
    @Produces("application/json")
    @Path("/logout")
    public Response logout(
            @FormParam("refreshToken") String refreshTokenvalue) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        try {
            RefreshToken refreshToken = RefreshTokenLocalServiceUtil.fetchByValue(WSTokenUtil.hashToken(refreshTokenvalue));
            RefreshTokenLocalServiceUtil.removeRefreshToken(refreshToken.getRefreshTokenId());
        } catch (NullPointerException e) {
            return WSResponseUtil.buildErrorResponse(400, "RefreshToken is invalid");
        } catch (NoSuchRefreshTokenException e) {
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("No Algorithm found");
            return WSResponseUtil.buildErrorResponse(401, "No Algorithm found");
        }
        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @Reference(unbind = "-")
    protected void setWSAuthenticator(WSAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Reference
    protected WSAuthenticator authenticator;

}

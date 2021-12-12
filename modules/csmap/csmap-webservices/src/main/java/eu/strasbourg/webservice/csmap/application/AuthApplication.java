package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
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
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenExpiredException;
import eu.strasbourg.webservice.csmap.exception.auth.RefreshTokenCreationFailedException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
    @Path("/authentication/{code}")
    public Response authentication(
            @PathParam("code") String code) {
        JSONObject jsonResponse =JSONFactoryUtil.createJSONObject();

        try {

            JSONObject authentikJSON = authenticator.sendTokenRequest(code);

            if (Validator.isNull(authentikJSON))
                throw new AuthenticationFailedException();

            String authentikJWT = authentikJSON.getString(WSConstants.ID_TOKEN);
            String accessToken = authentikJSON.getString(WSConstants.ACCESS_TOKEN);

            boolean isJwtValid = JWTUtils.checkJWT(
                    authentikJWT,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                    StrasbourgPropsUtil.getPublikIssuer());

            if (!isJwtValid)
                throw new InvalidJWTException();

            String sub = JWTUtils.getJWTClaim(authentikJWT, WSConstants.SUB,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(), StrasbourgPropsUtil.getPublikIssuer());

            authenticator.updateUserFromAuthentikInDatabase(authentikJWT, accessToken);

            String csmapJWT = JWTUtils.createJWT(
                    sub, WSConstants.JWT_VALIDITY_SECONDS,
                    StrasbourgPropsUtil.getCSMAPInternalSecret());
            RefreshToken refreshToken = authenticator.generateAndSaveRefreshTokenForUser(sub);

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);
            jsonResponse.put(WSConstants.JSON_REFRESH_TOKEN, refreshToken.getValue());

        } catch (InvalidJWTException | IOException | AuthenticationFailedException e) {
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
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @GET
    @Produces("application/json")
    @Path("/get-new-jwt/{refreshToken}")
    /**
     * Utilisé comme chemin de transition/retrocomaptibilité avec le POST
     */
    public Response getLegacyNewJWT(
            @PathParam("refreshToken") String refreshTokenvalue) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            RefreshToken validRefreshToken = authenticator.controlRefreshToken(refreshTokenvalue);

            String csmapJWT = JWTUtils.createJWT(
                    validRefreshToken.getPublikId(), WSConstants.JWT_VALIDITY_SECONDS,
                    StrasbourgPropsUtil.getCSMAPInternalSecret());

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);

        } catch (NoSuchRefreshTokenException | RefreshTokenExpiredException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
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
            List<RefreshToken> refreshTokens = RefreshTokenLocalServiceUtil.getRefreshTokens(-1,-1).stream().filter(t -> t.getPublikId().equals(publikUser.getPublikId())).collect(Collectors.toList());
            for(RefreshToken refreshToken : refreshTokens) {
                RefreshTokenLocalServiceUtil.removeRefreshToken(refreshToken.getRefreshTokenId());
            }
        } catch (NoJWTInHeaderException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e.getMessage());
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
            RefreshTokenLocalServiceUtil.removeRefreshToken(RefreshTokenLocalServiceUtil.fetchByValue(refreshTokenvalue).getRefreshTokenId());
        } catch (NullPointerException e) {
            return WSResponseUtil.buildErrorResponse(400, "RefreshToken is invalid");
        } catch (NoSuchRefreshTokenException e) {
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
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

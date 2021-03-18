package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.authentication.AuthenticationFailedException;
import eu.strasbourg.webservice.csmap.exception.jwt.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.refreshtoken.RefreshTokenExpiredException;
import eu.strasbourg.webservice.csmap.exception.refreshtoken.RefreshTokenCreationFailedException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

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
    public String authentication(
            @PathParam("code") String code) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        try {

            JSONObject authentikJSON = authenticator.sendTokenRequest(code);

            if (Validator.isNull(authentikJSON))
                throw new AuthenticationFailedException(WSConstants.ERROR_AUTHENTICATION);

            String authentikJWT = authentikJSON.getString(WSConstants.ID_TOKEN);
            String accessToken = authentikJSON.getString(WSConstants.ACCESS_TOKEN);

            boolean isJwtValid = JWTUtils.checkJWT(
                    authentikJWT,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                    StrasbourgPropsUtil.getPublikIssuer());

            if (!isJwtValid)
                throw new InvalidJWTException(WSConstants.ERROR_INVALID_TOKEN);

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
            jsonResponse = WSResponseUtil.initializeError(e.getMessage());
            log.error(e);
        } catch (RefreshTokenCreationFailedException e) {
            jsonResponse = WSResponseUtil.initializeServerError(e.getMessage());
            log.error(e);
        }

        return jsonResponse.toString();
    }

    @GET
    @Produces("application/json")
    @Path("/get-new-jwt/{refreshToken}")
    public String getNewJWT(
            @PathParam("refreshToken") String refreshTokenvalue) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        try {
            RefreshToken validRefreshToken = authenticator.controlRefreshToken(refreshTokenvalue);

            String csmapJWT = JWTUtils.createJWT(
                    validRefreshToken.getPublikId(), WSConstants.JWT_VALIDITY_SECONDS,
                    StrasbourgPropsUtil.getCSMAPInternalSecret());

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);

        } catch (NoSuchRefreshTokenException | RefreshTokenExpiredException e) {
            jsonResponse = WSResponseUtil.initializeServerError(e.getMessage());
            log.error(e.getMessage() + " : " + refreshTokenvalue);
        }

        return jsonResponse.toString();
    }

    @Reference(unbind = "-")
    protected void setWSAuthenticator(WSAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Reference
    protected WSAuthenticator authenticator;

}

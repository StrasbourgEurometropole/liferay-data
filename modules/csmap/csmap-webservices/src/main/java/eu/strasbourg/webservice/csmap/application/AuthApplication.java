package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("/authentication/{code}")
    public String authentication(
            @PathParam("code") String code) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        try {

            JSONObject authentikJSON = authenticator.sendTokenRequest(code);

            String authentikJWT = authentikJSON.getString("id_token");

            boolean isJwtValid = JWTUtils.checkJWT(
                    authentikJWT,
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                    StrasbourgPropsUtil.getPublikIssuer());

            if (!isJwtValid)
                throw new InvalidJWTException();

            String sub = JWTUtils.getJWTClaim(
                    authentikJWT, "sub",
                    StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                    StrasbourgPropsUtil.getPublikIssuer());

            String csmapJWT = JWTUtils.createJWT(sub, 3600);

            // TODO : Implement refresh token creation

            jsonResponse.put(WSConstants.JSON_JWT_CSM, csmapJWT);
            jsonResponse.put(WSConstants.JSON_REFRESH_TOKEN, "");

        } catch (InvalidJWTException e) {
            jsonResponse = WSResponseUtil.initializeServerError("Invalid token receives during authentication : " + e);
        } catch (IOException e) {
            jsonResponse = WSResponseUtil.initializeServerError("An error occurs during Authentik authentication : " + e);
        }

        return jsonResponse.toString();
    }

    @GET
    @Path("/get-new-jwt/{refreshToken}")
    public String getNewJWT(
            @PathParam("refreshToken") String refreshToken) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        jsonResponse.put("TODO", "Implement getNewJWT");

        jsonResponse.put(WSConstants.JSON_JWT_CSM, "");

        return jsonResponse.toString();
    }

    @Reference
    protected WSAuthenticator authenticator;

}

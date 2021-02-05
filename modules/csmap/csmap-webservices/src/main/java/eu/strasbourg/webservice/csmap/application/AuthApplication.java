package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
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
    @Path("/get-nonce")
    public String getNonce() {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        String nonce = StringUtil.randomString(32);
        jsonResponse.put("nonce", nonce);

        return jsonResponse.toString();
    }

    @GET
    @Path("/authentication/{code}")
    public String authentication(
            @PathParam("code") String code) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        jsonResponse.put("TODO", "Implement authentication");

        jsonResponse.put(WSConstants.JSON_JWT_CSM, "");
        jsonResponse.put(WSConstants.JSON_REFRESH_TOKEN, "");

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

}

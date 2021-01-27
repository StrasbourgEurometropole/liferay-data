package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/csmap-ws/auth",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=CSMAP.Auth.Rest",
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
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("TODO", "Implement getNonce");
        return json.toString();
    }

    @GET
    @Path("/authentication/{code}/{nonce}")
    public String authentication(
            @PathParam("code") String code,
            @PathParam("nonce") String nonce) {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("TODO", "Implement authentication");
        json.put("arg[code]", code);
        json.put("arg[nonce]", nonce);
        return json.toString();
    }

    @GET
    @Path("/get-new-jwt/{refreshToken}")
    public String getNewJWT(
            @PathParam("refreshToken") String refreshToken) {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("TODO", "Implement getNewJWT");
        json.put("arg[refreshToken]", refreshToken);
        return json.toString();
    }

}

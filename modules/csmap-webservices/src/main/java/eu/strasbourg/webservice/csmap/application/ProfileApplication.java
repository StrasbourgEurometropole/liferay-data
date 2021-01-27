package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/csmap-ws/profile",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=CSMAP.Profile.Rest",
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class ProfileApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    @GET
    @Path("/get-profile")
    public String getProfile() {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("TODO", "Implement getProfile");
        return json.toString();
    }

}

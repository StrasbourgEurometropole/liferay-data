package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.jwt.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.jwt.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.jwt.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.Collections;
import java.util.Set;

/**
 * @author angelique.champougny
 * @author jeremy.zwickert
 * @author cédric.henry
 */
@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_PROFILE_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_PROFILE_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class ProfileApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Path("/get-profile")
    public String getProfile(
            @Context HttpHeaders httpHeaders) {
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On récupère le user
            JSONObject jsonPublikUser = PublikApiClient.getUserDetails(publikUser.getPublikId());

            if (Validator.isNotNull(publikUser)) {
                if (Validator.isNotNull(jsonPublikUser.getString("last_name")))
                    jsonResponse.put(WSConstants.JSON_LAST_NAME, jsonPublikUser.getString("last_name"));

                if (Validator.isNotNull(jsonPublikUser.getString("first_name")))
                    jsonResponse.put(WSConstants.JSON_FIRST_NAME, jsonPublikUser.getString("first_name"));

                if (Validator.isNotNull(jsonPublikUser.getString("last_name")))
                    jsonResponse.put(WSConstants.JSON_EMAIL, jsonPublikUser.getString("email"));

                if (Validator.isNotNull(jsonPublikUser.getString("address")))
                    jsonResponse.put(WSConstants.JSON_ADDRESS, jsonPublikUser.getString("address"));

                if (Validator.isNotNull(jsonPublikUser.getString("zipcode")))
                    jsonResponse.put(WSConstants.JSON_POSTAL_CODE, jsonPublikUser.getString("zipcode"));

                if (Validator.isNotNull(jsonPublikUser.getString("city")))
                    jsonResponse.put(WSConstants.JSON_CITY, jsonPublikUser.getString("city"));

                if (Validator.isNotNull(jsonPublikUser.getString("photo")))
                    jsonResponse.put(WSConstants.JSON_IMAGE_URL, jsonPublikUser.getString("photo"));
            }
        } catch (NoJWTInHeaderException | InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            jsonResponse = WSResponseUtil.initializeError(e.getMessage());
            log.error(e);
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

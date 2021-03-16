package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.JWTUtils;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
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

    @GET
    @Path("/get-profile")
    public String getProfile(
            @Context HttpHeaders httpHeaders) {

        // On vérifie que le JWT est renseigné
        String jwt = httpHeaders.getHeaderString("jwt");
        if (Validator.isNull(jwt))
            return WSResponseUtil.initializeError("Il manque le paramètre JWT").toString();

        // On vérifie sa validité
        boolean isJwtValid = JWTUtils.checkJWT(jwt, StrasbourgPropsUtil.getCSMAPPublikClientSecret(),
                StrasbourgPropsUtil.getPublikIssuer());
        if (!isJwtValid)
            return WSResponseUtil.initializeError("JWT invalide").toString();

        // On récupère l'identifiant utilisateur dans le jwt
        String internalId = JWTUtils.getJWTClaim(jwt, "sub", StrasbourgPropsUtil.getPublikClientSecret(),
                StrasbourgPropsUtil.getPublikIssuer());
        if(Validator.isNull(internalId))
            return WSResponseUtil.initializeError("InternalID introuvable").toString();

        // On récupère le user
        JSONObject jsonResponse = WSResponseUtil.initializeResponse();
        JSONObject publikUser = PublikApiClient.getUserDetails(internalId);
        if(Validator.isNotNull(publikUser)) {
            if (Validator.isNotNull(publikUser.getString("last_name")))
                jsonResponse.put(WSConstants.JSON_LAST_NAME, publikUser.getString("last_name"));

            if (Validator.isNotNull(publikUser.getString("first_name")))
                jsonResponse.put(WSConstants.JSON_FIRST_NAME, publikUser.getString("first_name"));

            if (Validator.isNotNull(publikUser.getString("last_name")))
                jsonResponse.put(WSConstants.JSON_EMAIL, publikUser.getString("email"));

            if (Validator.isNotNull(publikUser.getString("address")))
                jsonResponse.put(WSConstants.JSON_ADDRESS, publikUser.getString("address"));

            if (Validator.isNotNull(publikUser.getString("zipcode")))
                jsonResponse.put(WSConstants.JSON_POSTAL_CODE, publikUser.getString("zipcode"));

            if (Validator.isNotNull(publikUser.getString("city")))
                jsonResponse.put(WSConstants.JSON_CITY, publikUser.getString("city"));

            if (Validator.isNotNull(publikUser.getString("photo")))
                jsonResponse.put(WSConstants.JSON_IMAGE_URL, publikUser.getString("photo"));
        }

        return jsonResponse.toString();
    }

}

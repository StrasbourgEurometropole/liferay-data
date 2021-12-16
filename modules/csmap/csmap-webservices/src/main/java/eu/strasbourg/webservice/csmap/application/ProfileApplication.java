package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.opendata.geo.district.OpenDataGeoDistrictService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.service.WSProfile;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
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
    @Produces("application/json")
    @Path("get-profile")
    public Response getProfile(
            @Context HttpHeaders httpHeaders) {
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On récupère le user
            JSONObject jsonPublikUser = PublikApiClient.getUserDetails(publikUser.getPublikId());

            if (Validator.isNotNull(publikUser)) {
                if (Validator.isNotNull(jsonPublikUser.getString("last_name")))
                    jsonResponse.put(WSConstants.JSON_LAST_NAME, jsonPublikUser.getString("last_name"));
                else
                    return WSResponseUtil.buildErrorResponse(500, "last-name introuvable");

                if (Validator.isNotNull(jsonPublikUser.getString("first_name")))
                    jsonResponse.put(WSConstants.JSON_FIRST_NAME, jsonPublikUser.getString("first_name"));
                else
                    return WSResponseUtil.buildErrorResponse(500, "first-name introuvable");

                if (Validator.isNotNull(jsonPublikUser.getString("email")))
                    jsonResponse.put(WSConstants.JSON_EMAIL, jsonPublikUser.getString("email"));
                else
                    return WSResponseUtil.buildErrorResponse(500, "email introuvable");

                if (Validator.isNotNull(jsonPublikUser.getString("address")))
                    jsonResponse.put(WSConstants.JSON_ADDRESS, jsonPublikUser.getString("address"));

                if (Validator.isNotNull(jsonPublikUser.getString("zipcode")))
                    jsonResponse.put(WSConstants.JSON_POSTAL_CODE, jsonPublikUser.getString("zipcode"));

                if (Validator.isNotNull(jsonPublikUser.getString("city")))
                    jsonResponse.put(WSConstants.JSON_CITY, jsonPublikUser.getString("city"));

                if (Validator.isNotNull(jsonPublikUser.getString("photo")))
                    jsonResponse.put(WSConstants.JSON_IMAGE_URL, jsonPublikUser.getString("photo"));

                String district = getDistrict(jsonPublikUser.getString("address"),
                        jsonPublikUser.getString("zipcode"), jsonPublikUser.getString("city"));
                if (Validator.isNotNull(district))
                    jsonResponse.put(WSConstants.JSON_DISTRICT, district);
            }
        } catch (NoJWTInHeaderException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    @POST
    @Produces("application/json")
    @Path("save-profile-picture")
    public Response saveProfilePicture(@Context HttpHeaders httpHeaders, @FormParam("profile_picture") String profilePicture) {

        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);
            String userPublikId = publikUser.getPublikId();

            jsonResponse = WSProfile.sendRequest(profilePicture, userPublikId);
            int httpResponseCode = (int)jsonResponse.get("code");
            String httpResponseMessage = (String)jsonResponse.get("message");
            if (httpResponseCode == 200) {
                return WSResponseUtil.buildOkResponse(jsonResponse);
            } else {
                log.error(httpResponseMessage);
                return WSResponseUtil.buildErrorResponse(httpResponseCode, httpResponseMessage);
            }

        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException | NoSubInJWTException | NoSuchPublikUserException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
    }

    // récupération du code du quartier de l'utilisateur
    public String getDistrict(String address, String zipCode, String city) {
        if (city.toLowerCase().equals("strasbourg")) {
            try {
                AssetCategory district = openDataGeoDistrictService.getDistrictByAddress(address, zipCode, city);
                if (Validator.isNotNull(district))
                    return AssetVocabularyHelper.getExternalId(district);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Reference(unbind = "-")
    protected void setWSAuthenticator(WSAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Reference
    protected WSAuthenticator authenticator;

    private OpenDataGeoDistrictService openDataGeoDistrictService;

    @Reference(unbind = "-")
    public void setOpenDataGeoDistrictService(OpenDataGeoDistrictService openDataGeoDistrictService) {
        this.openDataGeoDistrictService = openDataGeoDistrictService;
    }

}

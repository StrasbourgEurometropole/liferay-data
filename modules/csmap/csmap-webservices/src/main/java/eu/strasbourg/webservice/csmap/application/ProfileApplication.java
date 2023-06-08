package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    private final String stringJson = "json";
    private final String stringResponse = "response";

    @GET
    @Produces("application/json")
    @Path("get-profile")
    public Response getProfile(
            @Context HttpHeaders httpHeaders) {
        Response response;
        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On récupère le user
            JSONObject jsonPublikUser = PublikApiClient.getUserDetails(publikUser.getPublikId(), WSConstants.TIMEOUT);

            // On vérifie que le json contient bien modified qui est la date de derniere modification du profile
            if (Validator.isNotNull(jsonPublikUser.getString("modified"))) {
                try {
                    // On recupere la date en string puis on parse
                    // Attention c'est sensible a la casse si la date n'a pas le meme format qu'ecrit ca casse
                    // Si ca fonctionne on le transforme ensuite en date
                    String str = jsonPublikUser.getString("modified");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
                    LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

                    // Si le user n'a pas de date ou que sa date est differente de la date de modif du profile on rentre dans la boucle

                    if (Validator.isNull(publikUser.getModifiedDateJSON()) || !publikUser.getModifiedDateJSON().equals(date)) {
                        // Genere le json et la response
                        // Si le json est vide alors on ne fait rien
                        Map<String, Object> map = profileCSMapJSON(jsonPublikUser);
                        String jsonValue = (String) map.get(stringJson);
                        if(Validator.isNotNull(jsonValue)){
                            publikUser.setCsmapJSON(jsonValue);
                            publikUser.setModifiedDateJSON(date);
                            PublikUserLocalServiceUtil.updatePublikUser(publikUser);
                        }
                        response = (Response) map.get(stringResponse);
                    } else {
                        // La date est la meme que la modified date du profile donc on envoie ce qu'on a enregistré
                        response = WSResponseUtil.buildOkResponse(JSONFactoryUtil.createJSONObject(publikUser.getCsmapJSON()));
                    }
                } catch (Exception e){
                    // Si le parse ne fonctionne pas on genere la response et l'envoie
                    Map<String, Object> map = profileCSMapJSON(jsonPublikUser);
                    response = (Response) map.get(stringResponse);
                }
            } else {
                // Si pas de modified date du profile on genere la response
                Map<String, Object> map = profileCSMapJSON(jsonPublikUser);
                response = (Response) map.get(stringResponse);
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
        return response;
    }

    @GET
    @Produces("application/json")
    @Path("get-topics")
    public Response getTopics(
            @Context HttpHeaders httpHeaders) {
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On vérifi que le topic a déjà été utilisé
            boolean isNeverSaved = true;

            if (Validator.isNotNull(publikUser.getLastUpdateTimeTopics())) {
                isNeverSaved = false;
            }
            json.put(WSConstants.JSON_SAVED, isNeverSaved);

            // On récupère les topics
            String topics = publikUser.getTopicsFCM();
            JSONArray topicsJSON = JSONFactoryUtil.createJSONArray();
            if (Validator.isNotNull(topics)) {
                for (String topic : topics.split(",")){
                    if(Validator.isNotNull(topic))
                        topicsJSON.put(topic);
                }
            }
            json.put(WSConstants.JSON_TOPICS, topicsJSON);

            // Si pas de topic, retour 201
            if (Validator.isNull(topics) && !isNeverSaved) {
                return WSResponseUtil.buildOkResponse(json, 201);
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
        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("save-profile-picture")
    public Response saveProfilePicture(@Context HttpHeaders httpHeaders, @FormParam("profile_picture") String profilePicture) {

        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);
            String userPublikId = publikUser.getPublikId();

            jsonResponse = WSProfile.sendRequest(profilePicture, userPublikId, WSConstants.TIMEOUT);
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

    @POST
    @Produces("application/json")
    @Path("save-topics/{last_update_time}")
    public Response saveTopics(
            @Context HttpHeaders httpHeaders,
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("topics") String topics
    ) {

        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            long lastUpdateTimeBDD = publikUser.getLastUpdateTimeTopics();
            if(lastUpdateTimeLong > lastUpdateTimeBDD){
                publikUser.setTopicsFCM(topics);
                publikUser.setLastUpdateTimeTopics(lastUpdateTimeLong);
                publikUserLocalService.updatePublikUser(publikUser);
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

        return WSResponseUtil.buildOkResponse(jsonResponse);
    }

    // Renvoi une map avec obligatoirement response et json.
    public Map<String, Object> profileCSMapJSON(JSONObject jsonPublikUser){
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(jsonPublikUser.getString("last_name")))
            jsonResponse.put(WSConstants.JSON_LAST_NAME, jsonPublikUser.getString("last_name"));
        else {
            map.put(stringResponse, WSResponseUtil.buildErrorResponse(500, "last-name introuvable"));
            map.put(stringJson, "");
        }

        if (Validator.isNotNull(jsonPublikUser.getString("first_name")))
            jsonResponse.put(WSConstants.JSON_FIRST_NAME, jsonPublikUser.getString("first_name"));
        else {
            map.put(stringResponse, WSResponseUtil.buildErrorResponse(500, "first-name introuvable"));
            map.put(stringJson, "");
        }

        if (Validator.isNotNull(jsonPublikUser.getString("email")))
            jsonResponse.put(WSConstants.JSON_EMAIL, jsonPublikUser.getString("email"));
        else {
            map.put(stringResponse, WSResponseUtil.buildErrorResponse(500, "email introuvable"));
            map.put(stringJson, "");
        }

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

        if(!map.containsKey(stringResponse)) {
            map.put(stringResponse, WSResponseUtil.buildOkResponse(jsonResponse));
        }
        if(!map.containsKey(stringJson)) {
            map.put(stringJson, jsonResponse.toString());
        }
        return map;
    }

    // récupération du code du quartier de l'utilisateur
    public String getDistrict(String address, String zipCode, String city) {
        if (city.toLowerCase().equals("strasbourg")) {
            try {
                AssetCategory district = openDataGeoDistrictService.getDistrictByAddress(address, zipCode, city, WSConstants.TIMEOUT);
                if (Validator.isNotNull(district))
                    return AssetVocabularyHelper.getExternalId(district);
            } catch (Exception e) {
                _log.error(e.getMessage() + " : address -> " + address + ", zipCode -> " + zipCode + ", city -> " + city);
            }
        }

        return null;
    }

    @Reference
    protected CsmapCacheLocalService csmapCacheLocalService;

    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        this.csmapCacheLocalService = csmapCacheLocalService;
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

    @Reference
    protected PublikUserLocalService publikUserLocalService;

    @Reference(unbind = "-")
    protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
        this.publikUserLocalService = publikUserLocalService;
    }

    private Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

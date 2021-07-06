package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
import eu.strasbourg.webservice.csmap.service.WSFavorite;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_FAVORITE_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_FAVORITE_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class FavoriteApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @POST
    @Produces("application/json")
    @Path("/get-favorites")
    public Response getFavorites(
            @Context HttpHeaders httpHeaders,
            @FormParam("ids_favorite") String idsFavorite) {
        return getFavorites(httpHeaders, "0", idsFavorite);
    }

    @POST
    @Produces("application/json")
    @Path("/get-favorites/{last_update_time}")
    public Response getFavorites(
            @Context HttpHeaders httpHeaders,
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_favorite") String idsFavorite) {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }
        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);

            // On vérifie que les ids sont renseignés
            if (Validator.isNull(idsFavorite)) {
                idsFavorite = "";
            }
            List<Favorite> favorites = FavoriteLocalServiceUtil.getCSMapFavoriteByPublikUser(publikUser.getPublikId());

            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (Favorite favorite : favorites) {
                if (lastUpdateTime.before(favorite.getCreateDate())) {
                    if (!idsFavorite.contains(String.valueOf(favorite.getFavoriteId()))) {
                        jsonAjout.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
                    }
                }
                else if (lastUpdateTime.before(favorite.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            if (Validator.isNotNull(idsFavorite)) {
                for (String idFavorite : idsFavorite.split(",")) {
                    Favorite favorite = FavoriteLocalServiceUtil.fetchFavorite(Long.parseLong(idFavorite));
                    if (Validator.isNull(favorite)) {
                        jsonSuppr.put(idFavorite);
                    }
                }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);

        } catch (NoJWTInHeaderException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        }  catch (Exception e){
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/save-favorites")
    public Response saveFavorites(
            @Context HttpHeaders httpHeaders,
            @FormParam("content") String content) {
        JSONArray json = JSONFactoryUtil.createJSONArray();
        try {
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);
            JSONObject jsonFavorites = JSONFactoryUtil.createJSONObject(content);
            JSONArray jsonAdds = jsonFavorites.getJSONArray("ADD");
            if (jsonAdds != null && jsonAdds.length() != 0) {
                for (int j = 0; j < jsonAdds.length(); j++) {
                    JSONObject jsonAdd = jsonAdds.getJSONObject(j);
                    Favorite favorite;
                    String csmapIdFavorite = jsonAdd.getString("csmapId");
                    String titleFavorite = jsonAdd.getString("title");
                    long typeFavorite = jsonAdd.getLong("type");
                    String elementIdFavorite = jsonAdd.getString("elementId");
                    int orderFavorite = jsonAdd.getInt("order");
                    String contentFavorite = jsonAdd.getString("content");
                    List<Favorite> favoriteExist;
                    // Verifie si le favori n'existe pas déjà, s'il existe on l'update
                    if(Validator.isNull(elementIdFavorite)){
                        if(Validator.isNull(contentFavorite)){
                            favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite,0,publikUser.getPublikId(),null);
                        }else{
                            favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite,0,publikUser.getPublikId(),contentFavorite);
                        }
                    }else {
                        long elementIdForSearch;
                        if (typeFavorite == FavoriteType.PLACE.getId()) {
                            elementIdForSearch = PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId();
                        } else if(typeFavorite == FavoriteType.ARRET.getId()) {
                            elementIdForSearch = ArretLocalServiceUtil.getByStopId(elementIdFavorite).getArretId();
                        } else {
                            elementIdForSearch = Long.parseLong(elementIdFavorite);
                        }
                        if (Validator.isNull(contentFavorite)) {
                            favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite, elementIdForSearch, publikUser.getPublikId(), null);
                        } else {
                            favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite, elementIdForSearch, publikUser.getPublikId(), contentFavorite);
                        }
                    }
                    if(Validator.isNull(favoriteExist) || favoriteExist.isEmpty()) {
                        favorite = WSFavorite.createOrUpdateFavorite(null, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                    } else{
                        favorite = WSFavorite.createOrUpdateFavorite(favoriteExist.get(0), titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                    }
                    JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                    jsonResult.put("favoriteId", favorite.getFavoriteId());
                    jsonResult.put("csmapId", csmapIdFavorite);
                    json.put(jsonResult);
                }
            }
            JSONArray jsonUpdates = jsonFavorites.getJSONArray("UPDATE");
            if (jsonUpdates != null && jsonUpdates.length() != 0) {
                for (int j = 0; j < jsonUpdates.length(); j++) {
                    JSONObject jsonUpdate = jsonUpdates.getJSONObject(j);
                    String csmapIdFavorite = jsonUpdate.getString("csmapId");
                    long idFavorite = jsonUpdate.getLong("favoriteId");
                    String titleFavorite = jsonUpdate.getString("title");
                    long typeFavorite = jsonUpdate.getLong("type");
                    String elementIdFavorite = jsonUpdate.getString("elementId");
                    int orderFavorite = jsonUpdate.getInt("order");
                    String contentFavorite = jsonUpdate.getString("content");
                    Favorite favorite = FavoriteLocalServiceUtil.fetchFavorite(idFavorite);
                    boolean isNew = false;
                    // Verifie si le favori existe, s'il n'existe pas on le recree
                    if(Validator.isNull(favorite)){
                        favorite = WSFavorite.createOrUpdateFavorite(null, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                        isNew =true;
                    } else{
                        favorite = WSFavorite.createOrUpdateFavorite(favorite, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                    }
                    if(isNew){
                        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                        jsonResult.put("favoriteId", favorite.getFavoriteId());
                        jsonResult.put("csmapId", csmapIdFavorite);
                        json.put(jsonResult);
                    }
                }
            }
            JSONArray jsonDeletes = jsonFavorites.getJSONArray("DELETE");
            if (jsonDeletes != null && jsonDeletes.length() != 0) {
                for (int j = 0; j < jsonDeletes.length(); j++) {
                    JSONObject jsonDelete = jsonDeletes.getJSONObject(j);
                    long idFavorite = jsonDelete.getLong("favoriteId");
                    FavoriteLocalServiceUtil.deleteFavorite(idFavorite);
                }
            }
            if(jsonAdds.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (NoJWTInHeaderException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @Reference(unbind = "-")
    protected void setWSAuthenticator(WSAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Reference
    protected WSAuthenticator authenticator;

    @Reference(unbind = "-")
    protected void setFavoriteLocalService(FavoriteLocalService favoriteLocalService) {
        this.favoriteLocalService = favoriteLocalService;
    }

    @Reference
    protected eu.strasbourg.service.favorite.service.FavoriteLocalService favoriteLocalService;
}

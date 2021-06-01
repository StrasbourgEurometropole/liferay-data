package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.service.WSAuthenticator;
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
import java.util.Locale;
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
                if (lastUpdateTime.before(favorite.getCreateDate()))
                    jsonAjout.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
                else if (lastUpdateTime.before(favorite.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            if (Validator.isNotNull(idsFavorite)) {
                for (String idFavorite : idsFavorite.split(",")) {
                    Favorite favorite = FavoriteLocalServiceUtil.fetchFavorite(Long.valueOf(idFavorite));
                    if (Validator.isNull(favorite)) {
                        jsonSuppr.put(idFavorite);
                    }
                }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

        } catch (NoJWTInHeaderException e) {
            log.error(e.getMessage());
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        }  catch (Exception e){
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
                    Favorite favorite = FavoriteLocalServiceUtil.createFavorite();
                    String csmapIdFavorite = jsonAdd.getString("csmapId");
                    String titleFavorite = jsonAdd.getString("title");
                    Long typeFavorite = jsonAdd.getLong("type");
                    String elementIdFavorite = jsonAdd.getString("elementId");
                    Integer orderFavorite = jsonAdd.getInt("order");
                    String contentFavorite = jsonAdd.getString("content");
                    favorite.setTitle(titleFavorite);
                    favorite.setPublikUserId(publikUser.getPublikId());
                    favorite.setTypeId(typeFavorite);
                    if(typeFavorite == FavoriteType.PLACE.getId()) {
                        favorite.setEntityId(PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId());
                        if(favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())){
                            favorite.setUrl(StrasbourgPropsUtil.getURL() + "/lieu/-/entity/sig/" + elementIdFavorite);
                        }
                    } else if(typeFavorite == FavoriteType.EVENT.getId() || typeFavorite == FavoriteType.ARRET.getId()) {
                        favorite.setEntityId(Long.valueOf(elementIdFavorite));
                        if(favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())){
                            if(typeFavorite == FavoriteType.EVENT.getId()) {
                                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/evenement/-/entity/id/" + elementIdFavorite);
                            }
                            else if(typeFavorite == FavoriteType.ARRET.getId()) {
                                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/arret/-/entity/id/" + elementIdFavorite);
                            }
                        }
                    }
                    favorite.setOrder(orderFavorite);
                    favorite.setContent(contentFavorite);
                    Date date = new Date(System.currentTimeMillis());
                    favorite.setCreateDate(date);
                    favorite.setModifiedDate(date);
                    FavoriteLocalServiceUtil.updateFavorite(favorite);
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
                    Long idFavorite = jsonUpdate.getLong("favoriteId");
                    Favorite favorite = FavoriteLocalServiceUtil.fetchFavorite(idFavorite);
                    String titleFavorite = jsonUpdate.getString("title");
                    Long typeFavorite = jsonUpdate.getLong("type");
                    String elementIdFavorite = jsonUpdate.getString("elementId");
                    Integer orderFavorite = jsonUpdate.getInt("order");
                    String contentFavorite = jsonUpdate.getString("content");
                    favorite.setTitle(titleFavorite);
                    favorite.setPublikUserId(publikUser.getPublikId());
                    favorite.setTypeId(typeFavorite);
                    if(typeFavorite == FavoriteType.PLACE.getId()) {
                        favorite.setEntityId(PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId());
                        if(favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())){
                            favorite.setUrl(StrasbourgPropsUtil.getURL() + "/lieu/-/entity/sig/" + elementIdFavorite);
                        }
                    } else if(typeFavorite == FavoriteType.EVENT.getId() || typeFavorite == FavoriteType.ARRET.getId()) {
                        favorite.setEntityId(Long.valueOf(elementIdFavorite));
                        if(favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())){
                            if(typeFavorite == FavoriteType.EVENT.getId()) {
                                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/evenement/-/entity/id/" + elementIdFavorite);
                            }
                            else if(typeFavorite == FavoriteType.ARRET.getId()) {
                                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/arret/-/entity/id/" + elementIdFavorite);
                            }
                        }
                    }
                    favorite.setOrder(orderFavorite);
                    favorite.setContent(contentFavorite);
                    Date date = new Date(System.currentTimeMillis());
                    favorite.setModifiedDate(date);
                    FavoriteLocalServiceUtil.updateFavorite(favorite);
                }
            }
            JSONArray jsonDeletes = jsonFavorites.getJSONArray("DELETE");
            if (jsonDeletes != null && jsonDeletes.length() != 0) {
                for (int j = 0; j < jsonDeletes.length(); j++) {
                    JSONObject jsonDelete = jsonDeletes.getJSONObject(j);
                    Long idFavorite = jsonDelete.getLong("favoriteId");
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
}

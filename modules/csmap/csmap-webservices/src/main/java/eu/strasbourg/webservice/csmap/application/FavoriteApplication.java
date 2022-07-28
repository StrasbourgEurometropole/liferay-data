package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.InvalidJWTException;
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
import java.util.ArrayList;
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
            List<Favorite> favorites = favoriteLocalService.getCSMapFavoriteByPublikUser(publikUser.getPublikId());

            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (Favorite favorite : favorites) {
                // Le catch permet d'éviter les erreurs si l'entité en favoris n'existe plus
                try {
                    if (lastUpdateTime.before(favorite.getCreateDate())) {
                        // Empèche de renvoyer dans le add si le user l'a déjà
                        if (!idsFavorite.contains(String.valueOf(favorite.getFavoriteId()))) {
                            jsonAjout.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
                        }
                    }
                    else if (lastUpdateTime.before(favorite.getModifiedDate())) {
                        jsonModif.put(CSMapJSonHelper.favoritesCSMapJSON(favorite));
                    }
                } catch (NullPointerException e) {
                    log.error(e);
                }
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            if (Validator.isNotNull(idsFavorite) && idsFavorite != "") {
                for (String idFavorite : idsFavorite.split(",")) {
                    Favorite favorite = favoriteLocalService.fetchFavorite(Long.parseLong(idFavorite));
                    if (Validator.isNull(favorite)) {
                        jsonSuppr.put(idFavorite);
                    }
                }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);

        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        }  catch (Exception e){
            log.error(e);
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
            // Vérifie la validité de l'utilisateur faisant appel au service
            PublikUser publikUser = authenticator.validateUserInJWTHeader(httpHeaders);
            //On récupère les favoris à partir du JSON récupéré en paramètre
            JSONObject jsonFavorites = JSONFactoryUtil.createJSONObject(content);
            List<Long> favoriteIds = new ArrayList<>();
            JSONArray jsonAdds = jsonFavorites.getJSONArray("ADD");
            JSONArray jsonUpdates = jsonFavorites.getJSONArray("UPDATE");
            if (jsonUpdates != null && jsonUpdates.length() != 0) {
                // on récupère la liste des id de favoris à modifier, cette liste servira à la vérification utilisateur
                for (Object update : jsonUpdates) {
                    JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(update.toString());
                    long favoriteId = jsonUpdate.getLong("favoriteId");
                    favoriteIds.add(favoriteId);
                }
            }
            JSONArray jsonDeletes = jsonFavorites.getJSONArray("DELETE");
            if (jsonDeletes != null && jsonDeletes.length() != 0) {
                // on récupère la liste des id de favoris à supprimer, cette liste servira à la vérification utilisateur
                for (Object delete : jsonDeletes) {
                    JSONObject jsonDelete = JSONFactoryUtil.createJSONObject(delete.toString());
                    long favoriteId = jsonDelete.getLong("favoriteId");
                    favoriteIds.add(favoriteId);
                }
            }
            // si on a des favoris à modifier ou supprimer
            if(Validator.isNotNull(favoriteIds) && favoriteIds.size() > 0) {
                // on récupère tous les favoris de la liste
                List<Favorite> favorites = favoriteLocalService.getCSMapFavoriteById(favoriteIds);
                // On vérifie que l'utilisateur lié aux favoris à Modifier/Supprimer est bien le même utilisateur qui en a fait la demande
                Favorite favorite = favorites.stream().filter(f -> !f.getPublikUserId().equals(publikUser.getPublikId())).findFirst().orElse(null);
                // Si au moins 1 favori n'appartient pas à l'utilisateur de l'appel, on arrête tout ici
                if (Validator.isNotNull(favorite)) {
                    log.error("Le favori : " + favorite.getFavoriteId() + " n'appartient pas a cet utilisateur : " + publikUser.getPublikId());
                    return WSResponseUtil.buildErrorResponse(403, "Le favori : " + favorite.getFavoriteId() + " n'appartient pas a cet utilisateur : " + publikUser.getPublikId());
                }
            }
            // Ajout des favoris
            if (jsonAdds != null && jsonAdds.length() != 0) {
                for(Object add : jsonAdds){
                    // Récupère les données
                    JSONObject jsonAdd = JSONFactoryUtil.createJSONObject(add.toString());
                    Favorite favoriteToAdd;
                    String csmapIdFavorite = jsonAdd.getString("csmapId");
                    String titleFavorite = jsonAdd.getString("title");
                    long typeFavorite = jsonAdd.getLong("type");
                    String elementIdFavorite = jsonAdd.getString("elementId");
                    int orderFavorite = jsonAdd.getInt("order");
                    String contentFavorite = jsonAdd.getString("content");
                    List<Favorite> favoriteExist = null;
                    // Verifie si l'entite existe encore
                    try {
                        // Verifie si le favori n'existe pas déjà, s'il existe on l'update
                        if(Validator.isNull(elementIdFavorite)){
                            // On vérifie dans un premier temps avec entityId à 0, car les favoris CSMap (carte et recherche) ne font pas référence à un objet Liferay
                            // on les retrouve grâce au "content" qui représente le JSON explicitant la recherche ou le filtre de carte
                            if(Validator.isNull(contentFavorite)){
                                favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite,0,publikUser.getPublikId(),null);
                            }else{
                                favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite,0,publikUser.getPublikId(),contentFavorite);
                            }
                        }else {
                            // si on a un elementId, on recherche un objet Liferay (lieu, evenement ou arret)
                            long elementIdForSearch;

                            // Selon l'objet liferay, on fait une conversion car CSMap n'a pas tjrs l'id technique Liferay
                            // A noter : Le risque de NullPointerException est délibérement mis en place. C'est catché et des règles s'appliquent
                            if (typeFavorite == FavoriteType.PLACE.getId()) {
                                //Pour les lieux, CSMap possède le SIGId, on fait donc la conversion de SIGid vers PlaceId
                                elementIdForSearch = PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId();
                            } else if(typeFavorite == FavoriteType.ARRET.getId()) {
                                //Pour les arrêt, CSMap possède le stopId, on fait donc la conversion de StopId vers ArretId
                                elementIdForSearch = ArretLocalServiceUtil.getByStopId(elementIdFavorite).getArretId();
                            } else if(typeFavorite == FavoriteType.EVENT.getId()) {
                                // Pour les événements, elementId = eventId
                                long entityId = Long.parseLong(elementIdFavorite);
                                elementIdForSearch = EventLocalServiceUtil.fetchEvent(entityId).getEventId();
                            } else {
                                // Sinon on le récupère en tant que tel (pas censé arriver)
                                elementIdForSearch = Long.parseLong(elementIdFavorite);
                            }

                            if (Validator.isNull(contentFavorite)) {
                                // On cherche un favori existant déjà pour cet élément Liferay (pas de content pour nos objets)
                                favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite, elementIdForSearch, publikUser.getPublikId(), null);
                            } else {
                                // Cas qui normalement ne doit pas arriver, si un elementId c'est un objet liferay qui n'a pas de content, et inversement
                                favoriteExist = favoriteLocalService.getByTypeIdAndEntityIdAndPublikUserIdAndContent(typeFavorite, elementIdForSearch, publikUser.getPublikId(), contentFavorite);
                            }
                        }
                        if(Validator.isNull(favoriteExist) || favoriteExist.isEmpty()) {
                            // Si c'est bien un nouveau favori, on l'ajoute dans la BDD
                            favoriteToAdd = WSFavorite.createOrUpdateFavorite(null, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                        } else{
                            // Si un favori existe déjà, on modifie le favori existant
                            favoriteToAdd = WSFavorite.createOrUpdateFavorite(favoriteExist.get(0), titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                        }
                        // On renvoie à CSMap le lien entre l'ID de notre favori en base et l'ID qu'ils ont sur CSMap
                        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                        jsonResult.put("favoriteId", favoriteToAdd.getFavoriteId());
                        jsonResult.put("csmapId", csmapIdFavorite);
                        json.put(jsonResult);
                    } catch (NullPointerException e){
                        // On arrive ici si on a pas retrouvé les élements (Evenement, Arrêt, Lieux) dans notre Base de Donnée
                        if(Validator.isNotNull(favoriteExist) && !favoriteExist.isEmpty()) {
                            // Si on des favoris qui existaient, on les supprime car de toute façon l'objet n'existe plus
                            for(Favorite fav : favoriteExist)
                                favoriteLocalService.deleteFavorite(fav);
                        }
                        // On renvoie un couplage d'ID avec favoriteId à 0
                        // QUand l'appli reçoit notre favori à 0, il supprime le favori chez eux
                        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                        jsonResult.put("favoriteId", 0);
                        jsonResult.put("csmapId", csmapIdFavorite);
                        json.put(jsonResult);
                    }
                }
            }
            // Update des favoris
            if (jsonUpdates != null && jsonUpdates.length() != 0) {
                for(Object update : jsonUpdates){
                    // Récupère les données
                    JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(update.toString());
                    String csmapIdFavorite = jsonUpdate.getString("csmapId");
                    long idFavorite = jsonUpdate.getLong("favoriteId");
                    String titleFavorite = jsonUpdate.getString("title");
                    long typeFavorite = jsonUpdate.getLong("type");
                    String elementIdFavorite = jsonUpdate.getString("elementId");
                    int orderFavorite = jsonUpdate.getInt("order");
                    String contentFavorite = jsonUpdate.getString("content");
                    // Récupère le favori censé exister
                    Favorite favoriteToUpdate = favoriteLocalService.fetchFavorite(idFavorite);
                    boolean isNew = false;
                    // Verifie si l'entite existe encore
                    try {
                        // SI on retrouve pas l'entité lié (Evenement, Lieu, Arret), on va dans le NullPointerException où la procédure est gérée
                        if (typeFavorite == FavoriteType.PLACE.getId()) {
                            PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId();
                        } else if(typeFavorite == FavoriteType.ARRET.getId()) {
                            ArretLocalServiceUtil.getByStopId(elementIdFavorite).getArretId();
                        } else if(typeFavorite == FavoriteType.EVENT.getId()) {
                            long entityId = Long.parseLong(elementIdFavorite);
                            EventLocalServiceUtil.fetchEvent(entityId).getEventId();
                        }
                        // Verifie si le favori existe, s'il n'existe pas on le recree
                        if(Validator.isNull(favoriteToUpdate)){
                            favoriteToUpdate = WSFavorite.createOrUpdateFavorite(null, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                            isNew =true;
                        } else{
                            favoriteToUpdate = WSFavorite.createOrUpdateFavorite(favoriteToUpdate, titleFavorite, publikUser, typeFavorite, elementIdFavorite, orderFavorite, contentFavorite);
                        }

                        if(isNew){
                            // Si on a crée le favori, on renvoie le couplage d'ID entre notre base et celle de l'appli
                            // Comme ça ils peuvent saisir dans l'appli le couplage
                            JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                            jsonResult.put("favoriteId", favoriteToUpdate.getFavoriteId());
                            jsonResult.put("csmapId", csmapIdFavorite);
                            json.put(jsonResult);
                        }
                    } catch (NullPointerException e){
                        // On arrive ici si on a pas retrouvé les élements (Evenement, Arrêt, Lieux) dans notre Base de Donnée
                        if(Validator.isNotNull(favoriteToUpdate)) {
                            // Si on a favori qui existait, on le supprime car de toute façon l'objet n'existe plus
                            favoriteLocalService.deleteFavorite(favoriteToUpdate);
                        }
                        // On renvoie un couplage d'ID avec favoriteId à 0
                        // QUand l'appli reçoit notre favori à 0, il supprime le favori chez eux
                        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
                        jsonResult.put("favoriteId", 0);
                        jsonResult.put("csmapId", csmapIdFavorite);
                        json.put(jsonResult);
                    }
                }
            }
            // Suppression des favoris
            if (jsonDeletes != null && jsonDeletes.length() != 0) {
                for(Object delete : jsonDeletes){
                    // Récupère le favori avec l'id
                    JSONObject jsonDelete = JSONFactoryUtil.createJSONObject(delete.toString());
                    long idFavorite = jsonDelete.getLong("favoriteId");
                    Favorite favoriteToDelete = favoriteLocalService.fetchFavorite(idFavorite);
                    // Si on le trouve, on le supprime
                    // Si on le trouve pas, c'est qu'il n'existe plus, donc ça revient au même que de l'avoir supprimé
                    if (Validator.isNotNull(favoriteToDelete)) {
                        favoriteLocalService.deleteFavorite(idFavorite);
                    }
                }
            }
            if(json.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (NoJWTInHeaderException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(400, e.getMessage());
        } catch (InvalidJWTException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(401, e.getMessage());
        } catch (Exception e) {
            log.error(e);
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

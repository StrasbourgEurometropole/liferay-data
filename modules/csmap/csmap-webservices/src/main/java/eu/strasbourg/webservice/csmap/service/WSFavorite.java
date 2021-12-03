package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.UriHelper;

import java.util.Date;
import java.util.Locale;

public class WSFavorite {

    private static Log log = LogFactoryUtil.getLog(WSFavorite.class);

    static public Favorite createOrUpdateFavorite(Favorite favorite, String titleFavorite, PublikUser publikUser, long typeFavorite, String elementIdFavorite, int orderFavorite, String contentFavorite){
        boolean isNew = false;
        if(Validator.isNull(favorite)){
            favorite = FavoriteLocalServiceUtil.createFavorite();
            isNew = true;
        }
        favorite.setTitle(titleFavorite);
        favorite.setPublikUserId(publikUser.getPublikId());
        favorite.setTypeId(typeFavorite);
        if (typeFavorite == FavoriteType.PLACE.getId()) {
            favorite.setEntityId(PlaceLocalServiceUtil.getPlaceBySIGId(elementIdFavorite).getPlaceId());
            if (favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())) {
                try {
                    Place place = PlaceLocalServiceUtil.getPlace(Long.parseLong(elementIdFavorite));
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/lieu/-/entity/sig/" + elementIdFavorite + "/" + place.getNormalizedAlias(Locale.FRANCE));
                } catch (PortalException e) {
                    log.error(e);
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/lieu/-/entity/sig/" + elementIdFavorite);
                }
            }
        } else if (typeFavorite == FavoriteType.ARRET.getId()) {
            favorite.setEntityId(ArretLocalServiceUtil.getByStopId(elementIdFavorite).getArretId());
            if (favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())) {
                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/arret/-/entity/id/" + favorite.getEntityId());
            }
        } else if (typeFavorite == FavoriteType.EVENT.getId()) {
            favorite.setEntityId(Long.parseLong(elementIdFavorite));
            if (favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())) {
                try {
                    Event event = EventLocalServiceUtil.getEvent(Long.parseLong(elementIdFavorite));
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/evenement/-/entity/id/" + elementIdFavorite + "/" + event.getNormalizedTitle(Locale.FRANCE));
                } catch (PortalException e) {
                    log.error(e);
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/evenement/-/entity/id/" + elementIdFavorite);
                }
            }
        }
        favorite.setOrder(orderFavorite);
        if(Validator.isNull(contentFavorite)){
            favorite.setContent(null);
        } else{
            favorite.setContent(contentFavorite);
        }
        Date date = new Date(System.currentTimeMillis());
        if(isNew){
            favorite.setCreateDate(date);
        }
        favorite.setModifiedDate(date);
        FavoriteLocalServiceUtil.updateFavorite(favorite);
        return favorite;
    }
}

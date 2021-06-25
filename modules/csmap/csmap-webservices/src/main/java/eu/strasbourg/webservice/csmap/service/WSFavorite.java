package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.util.Date;

public class WSFavorite {
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
                favorite.setUrl(StrasbourgPropsUtil.getURL() + "/lieu/-/entity/sig/" + elementIdFavorite);
            }
        } else if (typeFavorite == FavoriteType.EVENT.getId() || typeFavorite == FavoriteType.ARRET.getId()) {
            favorite.setEntityId(Long.parseLong(elementIdFavorite));
            if (favorite.getUrl().isEmpty() || Validator.isNull(favorite.getUrl())) {
                if (typeFavorite == FavoriteType.EVENT.getId()) {
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/evenement/-/entity/id/" + elementIdFavorite);
                } else if (typeFavorite == FavoriteType.ARRET.getId()) {
                    favorite.setUrl(StrasbourgPropsUtil.getURL() + "/arret/-/entity/id/" + elementIdFavorite);
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

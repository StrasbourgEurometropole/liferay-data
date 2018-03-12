package eu.strasbourg.listener.assetEntry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.service.favorite.service.persistence.FavoritePersistence;

/**
 * @author jeremy.zwickert
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class AssetEntryListener extends BaseModelListener<AssetEntry>  {

	/**
	 *  A la suppression d'un asset entry, on supprime les favoris pouvant exister dessus
	 */
	@Override
	public void onBeforeRemove(AssetEntry model) throws ModelListenerException 
	{
		String className = model.getClassName();
		if(className.endsWith("model.Place")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.PLACE.getId());
		}
		else if(className.endsWith("model.Event")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.EVENT.getId());
		}
		else if(className.endsWith("model.Edition")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.EDITION.getId());
		}
		else if(className.endsWith("model.Activity")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.ACTIVITY.getId());
		}
		else if(className.endsWith("model.ActivityCourse")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.COURSE.getId());
		}
		else if(className.endsWith("model.Manifestation")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.MANIFESTATION.getId());
		}
		else if(className.endsWith("model.EditionGallery")) {
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(model.getClassPK(), FavoriteType.GALLERY.getId());
		}
		 
	}
	
	private FavoriteLocalService _favoriteLocalService;
	
	@Reference(unbind = "-")
	protected void setFavoriteLocalService(
			FavoriteLocalService favoriteLocalService) {

		_favoriteLocalService = favoriteLocalService;
	}	
}

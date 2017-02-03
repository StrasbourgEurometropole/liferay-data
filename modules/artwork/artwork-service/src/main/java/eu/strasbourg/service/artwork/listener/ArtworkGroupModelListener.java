package eu.strasbourg.service.artwork.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class ArtworkGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
   
		// Oeuvres
		List<Artwork> artworks = ArtworkLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Artwork artwork : artworks) {
			try {
				ArtworkLocalServiceUtil.removeArtwork(artwork.getArtworkId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
		// Collections d'oeuvres
		List<ArtworkCollection> collections = ArtworkCollectionLocalServiceUtil.getByGroupId(model.getGroupId());
		for (ArtworkCollection artworksCollection : collections) {
			try {
				ArtworkCollectionLocalServiceUtil.removeArtworkCollection(artworksCollection.getCollectionId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
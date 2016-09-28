package eu.strasbourg.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class GroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    	// Liens
		List<Link> links = LinkLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Link link : links) {
			try {
				LinkLocalServiceUtil.removeLink(link.getLinkId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		// Editions
		List<Edition> editions = EditionLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Edition edition : editions) {
			try {
				EditionLocalServiceUtil.removeEdition(edition.getEditionId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		// Galeries d'éditions
		List<EditionGallery> galleries = EditionGalleryLocalServiceUtil.getByGroupId(model.getGroupId());
		for (EditionGallery gallery : galleries) {
			try {
				EditionGalleryLocalServiceUtil.removeGallery(gallery.getGalleryId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		
		// Oeuvres
		List<Artwork> artworks = ArtworkLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Artwork artwork : artworks) {
			try {
				ArtworkLocalServiceUtil.removeArtwork(artwork.getArtworkId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		// Collections d'oeuvres
		List<ArtworkCollection> collections = ArtworkCollectionLocalServiceUtil.getByGroupId(model.getGroupId());
		for (ArtworkCollection artworksCollection : collections) {
			try {
				ArtworkCollectionLocalServiceUtil.removeArtworkCollection(artworksCollection.getCollectionId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
	}

}
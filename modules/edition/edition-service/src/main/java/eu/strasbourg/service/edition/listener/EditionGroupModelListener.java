package eu.strasbourg.service.edition.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class EditionGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Editions
		List<Edition> editions = EditionLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Edition edition : editions) {
			try {
				EditionLocalServiceUtil.removeEdition(edition.getEditionId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
		// Galeries d'éditions
		List<EditionGallery> galleries = EditionGalleryLocalServiceUtil.getByGroupId(model.getGroupId());
		for (EditionGallery gallery : galleries) {
			try {
				EditionGalleryLocalServiceUtil.removeGallery(gallery.getGalleryId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
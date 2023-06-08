package eu.strasbourg.service.video.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class VideoGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Videos
		List<Video> videos = VideoLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Video video : videos) {
			try {
				VideoLocalServiceUtil.removeVideo(video.getVideoId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
		// Galeries d'éditions
		List<VideoGallery> galleries = VideoGalleryLocalServiceUtil.getByGroupId(model.getGroupId());
		for (VideoGallery gallery : galleries) {
			try {
				VideoGalleryLocalServiceUtil.removeGallery(gallery.getGalleryId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
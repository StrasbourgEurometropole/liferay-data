package eu.strasbourg.portlet.video.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewGalleriesDisplayContext
	extends ViewListBaseDisplayContext<VideoGallery> {
	private List<VideoGallery> _galleries;

	public ViewGalleriesDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(VideoGallery.class, request, response);
	}

	public List<VideoGallery> getGalleries() throws PortalException {
		if (this._galleries == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<VideoGallery> results = new ArrayList<VideoGallery>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					VideoGallery gallery = VideoGalleryLocalServiceUtil.fetchVideoGallery(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (gallery != null) {
						results.add(gallery);
					}
				}
			}
			this._galleries = results;
		}
		return this._galleries;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.VIDEO_BO, StrasbourgPortletKeys.VIDEO_BO,
			actionId);
	}

}

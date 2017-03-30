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

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewVideosDisplayContext
	extends ViewListBaseDisplayContext<Video> {
	private List<Video> _videos;

	public ViewVideosDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Video.class, request, response);
	}

	public List<Video> getVideos() throws PortalException {
		if (this._videos == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Video> results = new ArrayList<Video>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Video video = VideoLocalServiceUtil.fetchVideo(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (video != null) {
						results.add(video);
					}
				}
			}
			this._videos = results;
		}
		return this._videos;
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

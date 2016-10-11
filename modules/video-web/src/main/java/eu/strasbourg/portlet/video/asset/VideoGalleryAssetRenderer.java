package eu.strasbourg.portlet.video.asset;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class VideoGalleryAssetRenderer extends BaseJSPAssetRenderer<VideoGallery> {
	
	public static final String TYPE = "videoGallery";
	private VideoGallery _entry;
	
	public VideoGalleryAssetRenderer(VideoGallery entry) {
		_entry = entry;
	}
	
	@Override
	public VideoGallery getAssetObject() {
		return _entry;
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _entry.getUuid();
	}

	@Override
	public String getClassName() {
		return VideoGallery.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getGalleryId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return _entry.getDescription(portletRequest.getLocale());
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle(locale);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/video-gallery/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean include(HttpServletRequest request,
		HttpServletResponse response, String template) throws Exception {

		request.setAttribute("entry", this._entry);
		request.setAttribute("detailPortletName", StrasbourgPortletKeys.ENTITY_DETAIL_WEB);
		
		return super.include(request, response, template);
	}

	public VideoGallery getGallery() {
		return this._entry;
	}

}

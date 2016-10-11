package eu.strasbourg.portlet.video.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_WEB},
	service = AssetRendererFactory.class
)
public class VideoGalleryAssetRendererFactory extends BaseAssetRendererFactory<VideoGallery> {
	
	public static final String TYPE = "videoGallery";

	public VideoGalleryAssetRendererFactory() {
		setClassName(VideoGallery.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.VIDEO_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<VideoGallery> getAssetRenderer(long classPK, int type)
		throws PortalException {
		VideoGallery entry = _videoGalleryLocalService.getVideoGallery(classPK);

		VideoGalleryAssetRenderer videoGalleryAssetRenderer =
			new VideoGalleryAssetRenderer(entry);

		videoGalleryAssetRenderer.setAssetRendererType(type);

		return videoGalleryAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private VideoGalleryLocalService _videoGalleryLocalService;

	@Reference(unbind = "-")
	protected void setVideoGalleryLocalService(VideoGalleryLocalService videoGalleryLocalService) {
		_videoGalleryLocalService = videoGalleryLocalService;
	}
}

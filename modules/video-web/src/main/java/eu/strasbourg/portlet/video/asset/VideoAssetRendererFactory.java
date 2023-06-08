package eu.strasbourg.portlet.video.asset;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_WEB},
	service = AssetRendererFactory.class
)
public class VideoAssetRendererFactory extends BaseAssetRendererFactory<Video> {
	
	public static final String TYPE = "video";

	public VideoAssetRendererFactory() {
		setClassName(Video.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.VIDEO_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Video> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Video entry = _videoLocalService.getVideo(classPK);

		VideoAssetRenderer videoAssetRenderer =
			new VideoAssetRenderer(entry);

		videoAssetRenderer.setAssetRendererType(type);

		return videoAssetRenderer;
	}

	/**
	 * Notes : surcharge de la méthode pour enlever le préfix du className "model.resource" non présent sur les modules
	 * 			custom en 7.0
	 */
	@Override
	public String getTypeName(Locale locale) {
		String key = getClassName();

		String value = LanguageUtil.get(locale, key, null);

		String portletId = getPortletId();

		if ((value == null) && (portletId != null)) {
			PortletBag portletBag = PortletBagPool.get(portletId);

			ResourceBundle resourceBundle = portletBag.getResourceBundle(
					locale);

			if (resourceBundle != null) {
				value = ResourceBundleUtil.getString(resourceBundle, key);
			}
		}

		if (value == null) {
			value = getClassName();
		}

		return value;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	private VideoLocalService _videoLocalService;

	@Reference(unbind = "-")
	protected void setVideoLocalService(VideoLocalService videoLocalService) {
		_videoLocalService = videoLocalService;
	}

}

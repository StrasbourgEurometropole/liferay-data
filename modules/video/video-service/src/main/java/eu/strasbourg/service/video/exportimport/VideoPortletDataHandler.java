package eu.strasbourg.service.video.exportimport;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.model.impl.VideoGalleryImpl;
import eu.strasbourg.service.video.model.impl.VideoImpl;
import eu.strasbourg.service.video.service.VideoGalleryLocalService;
import eu.strasbourg.service.video.service.VideoLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_BO },
	service = PortletDataHandler.class)
public class VideoPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "strasbourg-video";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Video entity", true,
				false, null, Video.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE, "Video Gallery entity",
				true, false, null, VideoGallery.class.getName()));

		XStreamAliasRegistryUtil.register(VideoImpl.class, "Video");
		XStreamAliasRegistryUtil.register(VideoGalleryImpl.class,
			"VideoGallery");
	}

	@Override
	protected String doExportData(PortletDataContext portletDataContext,
		String portletId, PortletPreferences portletPreferences)
		throws Exception {
		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute("group-id",
			String.valueOf(portletDataContext.getScopeGroupId()));

		// Si la checkbox correspondant au type à exporté est décochée, on ne
		// fait rien
		if (portletDataContext.getBooleanParameter(NAMESPACE, "Video entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._videoLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Video Gallery entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._videoGalleryLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences, String data) throws Exception {

		Element galleriesElement = portletDataContext
			.getImportDataGroupElement(VideoGallery.class);

		List<Element> galleriesElements = galleriesElement.elements();
		for (Element galleryElement : galleriesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				galleryElement);
		}

		Element videosElement = portletDataContext
			.getImportDataGroupElement(Video.class);

		List<Element> videosElements = videosElement.elements();
		for (Element videoElement : videosElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				videoElement);
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setVideoLocalService(VideoLocalService videoLocalService) {
		this._videoLocalService = videoLocalService;
	}

	private VideoLocalService _videoLocalService;

	@Reference(unbind = "-")
	protected void setVideoGalleryLocalService(
		VideoGalleryLocalService videoGalleryLocalService) {
		this._videoGalleryLocalService = videoGalleryLocalService;
	}

	private VideoGalleryLocalService _videoGalleryLocalService;

}

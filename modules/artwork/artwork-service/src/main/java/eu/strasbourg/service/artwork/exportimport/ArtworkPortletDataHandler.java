package eu.strasbourg.service.artwork.exportimport;

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

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.model.impl.ArtworkCollectionImpl;
import eu.strasbourg.service.artwork.model.impl.ArtworkImpl;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO },
	service = PortletDataHandler.class)
public class ArtworkPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "strasbourg-gallery";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Artwork entity", true,
				false, null, Artwork.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE,
				"Artwork Collection entity", true, false, null,
				ArtworkCollection.class.getName()));

		XStreamAliasRegistryUtil.register(ArtworkImpl.class, "Artwork");
		XStreamAliasRegistryUtil.register(ArtworkCollectionImpl.class,
			"ArtworkCollection");
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
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Artwork entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._galleryLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Artwork Collection entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._galleryCollectionLocalService
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
			.getImportDataGroupElement(ArtworkCollection.class);

		List<Element> galleriesElements = galleriesElement.elements();
		for (Element galleryElement : galleriesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				galleryElement);
		}

		Element gallerysElement = portletDataContext
			.getImportDataGroupElement(Artwork.class);

		List<Element> gallerysElements = gallerysElement.elements();
		for (Element galleryElement : gallerysElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				galleryElement);
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setArtworkLocalService(
		ArtworkLocalService galleryLocalService) {
		this._galleryLocalService = galleryLocalService;
	}

	private ArtworkLocalService _galleryLocalService;

	@Reference(unbind = "-")
	protected void setArtworkCollectionLocalService(
		ArtworkCollectionLocalService galleryCollectionLocalService) {
		this._galleryCollectionLocalService = galleryCollectionLocalService;
	}

	private ArtworkCollectionLocalService _galleryCollectionLocalService;

}

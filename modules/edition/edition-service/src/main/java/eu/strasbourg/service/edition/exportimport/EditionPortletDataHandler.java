package eu.strasbourg.service.edition.exportimport;

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

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.model.impl.EditionGalleryImpl;
import eu.strasbourg.service.edition.model.impl.EditionImpl;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.service.edition.service.EditionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.EDITION_BO },
	service = PortletDataHandler.class)
public class EditionPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "strasbourg-edition";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Edition entity", true,
				false, null, Edition.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE, "Edition Gallery entity",
				true, false, null, EditionGallery.class.getName()));

		XStreamAliasRegistryUtil.register(EditionImpl.class, "Edition");
		XStreamAliasRegistryUtil.register(EditionGalleryImpl.class,
			"EditionGallery");
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
			"Edition entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._editionLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getScopeGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Edition Gallery entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._editionGalleryLocalService
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
			.getImportDataGroupElement(EditionGallery.class);

		List<Element> galleriesElements = galleriesElement.elements();
		for (Element galleryElement : galleriesElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				galleryElement);
		}

		Element editionsElement = portletDataContext
			.getImportDataGroupElement(Edition.class);

		List<Element> editionsElements = editionsElement.elements();
		for (Element editionElement : editionsElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				editionElement);
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setEditionLocalService(
		EditionLocalService editionLocalService) {
		this._editionLocalService = editionLocalService;
	}

	private EditionLocalService _editionLocalService;

	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(
		EditionGalleryLocalService editionGalleryLocalService) {
		this._editionGalleryLocalService = editionGalleryLocalService;
	}

	private EditionGalleryLocalService _editionGalleryLocalService;

}

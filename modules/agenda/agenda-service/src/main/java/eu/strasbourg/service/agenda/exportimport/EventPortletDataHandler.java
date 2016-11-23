package eu.strasbourg.service.agenda.exportimport;

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

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.model.impl.ManifestationImpl;
import eu.strasbourg.service.agenda.model.impl.EventImpl;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO },
	service = PortletDataHandler.class)
public class EventPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "strasbourg-agenda";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Event entity", true,
				false, null, Event.class.getName()),
			new PortletDataHandlerBoolean(NAMESPACE, "Manifestation entity",
				true, false, null, Manifestation.class.getName()));

		XStreamAliasRegistryUtil.register(EventImpl.class, "Event");
		XStreamAliasRegistryUtil.register(ManifestationImpl.class,
			"Manifestation");
	}

	@Override
	protected String doExportData(PortletDataContext portletDataContext,
		String portletId, PortletPreferences portletPreferences)
		throws Exception {
		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute("group-id",
			String.valueOf(portletDataContext.getCompanyGroupId()));

		// Si la checkbox correspondant au type à exporté est décochée, on ne
		// fait rien
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Event entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._eventLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getCompanyGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}
		if (portletDataContext.getBooleanParameter(NAMESPACE,
			"Event Manifestation entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this._manifestationLocalService
				.getExportActionableDynamicQuery(portletDataContext);
			entryActionableDynamicQuery
				.setGroupId(portletDataContext.getCompanyGroupId()); // ?
			entryActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences, String data) throws Exception {

		Element manifestationsElement = portletDataContext
			.getImportDataGroupElement(Manifestation.class);

		List<Element> manifestationsElements = manifestationsElement.elements();
		for (Element manifestationElement : manifestationsElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				manifestationElement);
		}

		Element eventsElement = portletDataContext
			.getImportDataGroupElement(Event.class);

		List<Element> eventsElements = eventsElement.elements();
		for (Element eventElement : eventsElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				eventElement);
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setEventLocalService(
		EventLocalService eventLocalService) {
		this._eventLocalService = eventLocalService;
	}

	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setManifestationLocalService(
		ManifestationLocalService manifestationLocalService) {
		this._manifestationLocalService = manifestationLocalService;
	}

	private ManifestationLocalService _manifestationLocalService;

}

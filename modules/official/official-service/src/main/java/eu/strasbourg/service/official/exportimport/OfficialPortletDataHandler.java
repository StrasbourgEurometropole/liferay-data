package eu.strasbourg.service.official.exportimport;

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

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.model.impl.OfficialImpl;
import eu.strasbourg.service.official.service.OfficialLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.EDITION_BO },
	service = PortletDataHandler.class)
public class OfficialPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "strasbourg-official";

	public static final String SCHEMA_VERSION = "1.0.0";

	@Reference
	private OfficialLocalService officialLocalService;

	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}

	@Activate
	protected void activate() {
		setExportControls(
			new PortletDataHandlerBoolean(NAMESPACE, "Official entity", true,
				false, null, Official.class.getName()));

		XStreamAliasRegistryUtil.register(OfficialImpl.class, "Official");
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
			"Official entity")) {
			ExportActionableDynamicQuery entryActionableDynamicQuery = this.officialLocalService
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

		Element editionsElement = portletDataContext
			.getImportDataGroupElement(Official.class);

		List<Element> editionsElements = editionsElement.elements();
		for (Element editionElement : editionsElements) {
			StagedModelDataHandlerUtil.importStagedModel(portletDataContext,
				editionElement);
		}

		return null;
	}

}

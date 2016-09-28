package eu.strasbourg.service.link.exportimport;

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

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.model.impl.LinkImpl;
import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.LINK_WEB,
		"javax.portlet.name=" + StrasbourgPortletKeys.LINK_BO
	},
	service = PortletDataHandler.class
)
public class LinkPortletDataHandler extends BasePortletDataHandler {
	
	public static final String NAMESPACE = "strasbourg-link";

	public static final String SCHEMA_VERSION = "1.0.0";
	
	@Override
	public String getSchemaVersion() {
		return SCHEMA_VERSION;
	}
	
	@Activate
	protected void activate() {
		//setDeletionSystemEventStagedModelTypes(
		//	new StagedModelType(Link.class));
		
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "Link entity", true, false, null,
				Link.class.getName()));
		setImportControls(getExportControls());
		
		XStreamAliasRegistryUtil.register(LinkImpl.class, "Link");
	}

	@Override
	protected PortletPreferences doDeleteData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) throws Exception {
		
		/*if (portletDataContext.addPrimaryKey(LinkPortletDataHandler.class, "deleteData")) {
			return portletPreferences;
		}
		
		this._stagedModelRepository.deleteStagedModels(portletDataContext);
		
		return portletPreferences;
		*/
		return super.doDeleteData(portletDataContext, portletId, portletPreferences);
	}

	@Override
	protected String doExportData(PortletDataContext portletDataContext,
		String portletId, PortletPreferences portletPreferences)
		throws Exception {
		Element rootElement = addExportDataRootElement(portletDataContext);
		
		// Si la checkbox correspondant au type à exporté est décochée, on ne fait rien
		if (!portletDataContext.getBooleanParameter(NAMESPACE, "Link entity")) {
			return getExportDataRootElementString(rootElement);
		}
		
		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ExportActionableDynamicQuery entryActionableDynamicQuery =
			this._linkLocalService.getExportActionableDynamicQuery(portletDataContext);
		entryActionableDynamicQuery.setGroupId(portletDataContext.getScopeGroupId()); // ?
		entryActionableDynamicQuery.performActions();
		
		return getExportDataRootElementString(rootElement);		
	}

	@Override
	protected PortletPreferences doImportData(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences, String data) throws Exception {
		if (!portletDataContext.getBooleanParameter(NAMESPACE, "Link entity")) {
			return null;
		}

		Element entriesElement = portletDataContext.getImportDataGroupElement(
			Link.class);

		List<Element> entryElements = entriesElement.elements();
				for (Element entryElement : entryElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, entryElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
		PortletDataContext portletDataContext,
		PortletPreferences portletPreferences) throws Exception {
		/*
		ActionableDynamicQuery entryExportActionableDynamicQuery =
		
			this._stagedModelRepository.
				getExportActionableDynamicQuery(portletDataContext);

		entryExportActionableDynamicQuery.performCount();
		*/
		super.doPrepareManifestSummary(portletDataContext, portletPreferences);
	}
	

	@Reference(unbind = "-")
	protected void setLinkLocalService(LinkLocalService linkLocalService) {
		this._linkLocalService = linkLocalService;
	}

	private LinkLocalService _linkLocalService;

}

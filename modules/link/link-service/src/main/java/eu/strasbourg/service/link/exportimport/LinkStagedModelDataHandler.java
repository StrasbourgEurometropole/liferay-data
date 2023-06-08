package eu.strasbourg.service.link.exportimport;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalService;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class LinkStagedModelDataHandler
	extends BaseStagedModelDataHandler<Link> {

	public static final String[] CLASS_NAMES = { Link.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Link link = fetchStagedModelByUuidAndGroupId(uuid, groupId);
		if (link != null) {
			deleteStagedModel(link);
		}
	}

	@Override
	public void deleteStagedModel(Link stagedModel) throws PortalException {
		_linkLocalService.removeLink(stagedModel.getLinkId());
	}

	@Override
	public List<Link> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._linkLocalService.getLinksByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Link stagedModel) throws Exception {
		
		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);
		
		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Link stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getScopeGroupId());
		sc.setUserId(userId);
		Link importedLink = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Link existingLink = this._linkLocalService.fetchLinkByUuidAndGroupId(stagedModel.getUuid(), portletDataContext.getScopeGroupId());
			
			if (existingLink == null) {
				importedLink = this._linkLocalService.createLink(sc);
			} else {
				importedLink = existingLink;
			}
			
		} else {
			importedLink = this._linkLocalService.createLink(sc);
		}
		importedLink.setTitle(stagedModel.getTitle());
		importedLink.setURL(stagedModel.getURL());
		importedLink.setHoverText(stagedModel.getHoverText());
		importedLink.setUuid(stagedModel.getUuid());
		importedLink.setStatus(stagedModel.getStatus());
		this._linkLocalService.updateLink(importedLink, sc);
		
		portletDataContext.importClassedModel(stagedModel, importedLink);
	}

	@Reference(unbind = "-")
	protected void setLinkLocalService(LinkLocalService linkLocalService) {
		this._linkLocalService = linkLocalService;
	}

	private LinkLocalService _linkLocalService;

}

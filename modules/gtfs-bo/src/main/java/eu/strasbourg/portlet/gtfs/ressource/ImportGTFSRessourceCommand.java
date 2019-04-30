package eu.strasbourg.portlet.gtfs.ressource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.gtfs.service.ImportHistoricLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_BO,
		"mvc.command.name=importGTFS"
	},
	service = MVCResourceCommand.class
)
public class ImportGTFSRessourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		_log.info("Import start");
		
		_log.info(this._importHistoricLocalService.doImportGTFS());
		
		return true;
	}
	
	private ImportHistoricLocalService _importHistoricLocalService;
	
	@Reference(unbind = "-")
	protected void setImportHistoricLocalService(ImportHistoricLocalService importHistoricLocalService) {
		_importHistoricLocalService = importHistoricLocalService;
	}
	
	/**
	 * le log
	 */
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

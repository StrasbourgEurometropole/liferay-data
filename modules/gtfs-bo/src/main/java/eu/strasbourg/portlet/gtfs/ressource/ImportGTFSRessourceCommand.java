package eu.strasbourg.portlet.gtfs.ressource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalService;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil;
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
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
		try {
			// Changement du groupId du contexte de la requête pour effectuer les actions dans Global
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());
			
			// Creation de l'entree d'historique d'import
			ImportHistoric importHistoric = ImportHistoricLocalServiceUtil.createImportHistoric(sc);
			
			// Effectue l'import
			this._importHistoricLocalService.doImportGTFS(sc, importHistoric);
			
			// Sauvegarde de l'entree d'historique d'import
			this._importHistoricLocalService.updateImportHistoric(importHistoric, sc);
			
			// Envoie du mail de rapport
			importHistoric.sendMail();
		
		} catch (PortalException e) {
			_log.info(e);
		}
		
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

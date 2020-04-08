package eu.strasbourg.service.gtfs.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;

import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import org.osgi.service.component.annotations.*;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalService;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil;

@Component(
		immediate = true, 
		service = ImportGTFSMessageListener.class
)
public class ImportGTFSMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Création du trigger "Tous les jours à 3h"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, null, null, "0 0 3 * * ?");

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				listenerClass, trigger);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start importing GTFS");
		
		// Creation du contexte de la requete pour effectuer les actions dans Global
		ServiceContext sc = new ServiceContext();
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			sc.setCompanyId(defaultCompany.getCompanyId());
			sc.setScopeGroupId(defaultCompany.getGroup().getGroupId());
			sc.setUserId(UserLocalServiceUtil.getDefaultUserId(sc.getCompanyId()));
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			sc.setModifiedDate(new Date());
		} catch (PortalException e) {
			log.error(e);
		}
				
		// Creation de l'entree d'historique d'import
		ImportHistoric importHistoric = ImportHistoricLocalServiceUtil.createImportHistoric(sc);
		
		// Effectue l'import
		this._importHistoricLocalService.doImportGTFS(sc, importHistoric);
		
		// Sauvegarde de l'entree d'historique d'import
		this._importHistoricLocalService.updateImportHistoric(importHistoric, sc);
		
		// Envoie du mail de rapport
		importHistoric.sendMail();
		
		log.info("Finish exporting GTFS");
	}
	
	@Reference(unbind = "-")
    protected void setImportHistoricLocalService(ImportHistoricLocalService importHistoricLocalService) {
		_importHistoricLocalService = importHistoricLocalService;
    }

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
			SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private ImportHistoricLocalService _importHistoricLocalService;
	private TriggerFactory _triggerFactory;
	private Log log = LogFactoryUtil.getLog(this.getClass());

}

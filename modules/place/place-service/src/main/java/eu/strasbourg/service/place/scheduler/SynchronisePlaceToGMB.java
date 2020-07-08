package eu.strasbourg.service.place.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.*;

import java.util.Date;


/**
 * Synchronise les horaires des lieux
 */
@Component(immediate = true, service = SynchronisePlaceToGMB.class)
public class SynchronisePlaceToGMB extends BaseMessageListener {

    @Activate
    @Modified
    protected void activate() {
        String listenerClass = getClass().getName();

        // Création du trigger "Tous les jours à 3h45"
        Trigger trigger = _triggerFactory.createTrigger(
                listenerClass, listenerClass, null, null,
                "0 45 3 * * ?");

        SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
                listenerClass, trigger);

        _schedulerEngineHelper.register(
                this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
    }

    @Deactivate
    protected void deactivate() {
        _schedulerEngineHelper.unregister(this);
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        log.info("Start synchronise");
        //on vérifi qu'on a le droit de faire la synchronisation
        if(Boolean.parseBoolean(StrasbourgPropsUtil.getGMBActivated())) {
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
            
            // Creation de l'entree d'historique de synchronisation
            GoogleMyBusinessHistoric googleMyBusinessHistoric = this._gmbHistoricLocalService.createGoogleMyBusinessHistoric(sc);

            // Effectue la synchronisation
            this._gmbHistoricLocalService.doSynchronisation(sc, googleMyBusinessHistoric);

            // Sauvegarde de l'entree
            this._gmbHistoricLocalService.updateGoogleMyBusinessHistoric(googleMyBusinessHistoric, sc);

            // Envoie du mail de rapport
            googleMyBusinessHistoric.sendMail();
        }else{
            log.info("La synchronisation des lieux est désactivée - Fin du traitement ");
        }

        log.info("Finish synchronise");
    }

    @Reference(unbind = "-")
    protected void setGoogleMyBusinessHistoricLocalService(GoogleMyBusinessHistoricLocalService gmbHistoricLocalService) {
        _gmbHistoricLocalService = gmbHistoricLocalService;
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
    private GoogleMyBusinessHistoricLocalService _gmbHistoricLocalService;
    private TriggerFactory _triggerFactory;
    private Log log = LogFactoryUtil.getLog(this.getClass());

}
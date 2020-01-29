package eu.strasbourg.service.place.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.*;


/**
 * Synchronise les horaires des lieux
 */
@Component(immediate = true, service = SynchronisePlaceToGMB.class)
public class SynchronisePlaceToGMB extends BaseSchedulerEntryMessageListener {

    @Activate
    @Modified
    protected void activate() {
        // Tous les jours a 4h
        schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(
                getEventListenerClass(), getEventListenerClass(), "0 0 4 * * ?"));
        _schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
    }

    @Deactivate
    protected void deactivate() {
        _schedulerEngineHelper.unregister(this);
    }

    private Log _log = LogFactoryUtil.getLog(SynchronisePlaceToGMB.class);

    @Override
    protected void doReceive(Message message) throws Exception {
        this._log.info("Start synchronise");
        //on vérifi qu'on a le droit de faire la synchronisation
        if(Boolean.parseBoolean(StrasbourgPropsUtil.getGMBActivated())) {
            // Creation du contexte de la requete pour effectuer les actions dans Global
            ServiceContext sc = new ServiceContext();

            // Creation de l'entree d'historique de synchronisation
            GoogleMyBusinessHistoric googleMyBusinessHistoric = this._gmbHistoricLocalService.createGoogleMyBusinessHistoric(sc);

            // Effectue la synchronisation
            this._gmbHistoricLocalService.doSynchronisation(sc, googleMyBusinessHistoric);

            // Sauvegarde de l'entree
            this._gmbHistoricLocalService.updateGoogleMyBusinessHistoric(googleMyBusinessHistoric, sc);

            // Envoie du mail de rapport
            googleMyBusinessHistoric.sendMail();
        }

        this._log.info("Finish synchronise");
    }

    @Reference(unbind = "-")
    protected void setGoogleMyBusinessHistoricLocalService(GoogleMyBusinessHistoricLocalService gmbHistoricLocalService) {
        _gmbHistoricLocalService = gmbHistoricLocalService;
    }

    @Reference(unbind = "-")
    private volatile SchedulerEngineHelper _schedulerEngineHelper;

    private GoogleMyBusinessHistoricLocalService _gmbHistoricLocalService;

}
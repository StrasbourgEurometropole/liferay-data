package eu.strasbourg.service.agenda.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;

import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.service.place.service.PlaceLocalService;

import java.util.Calendar;
import java.util.Date;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = CheckEventMessageListener.class)
public class CheckEventMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Toutes les 15 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				15, TimeUnit.MINUTE);

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
		_eventLocalService.checkEvents();
		_eventLocalService.unpublishPastEvents();
		_eventLocalService.deleteOldUnpublishedEvents();
		_manifestationLocalService.checkManifestations();
		_manifestationLocalService.unpublishPastManifestations();
		_manifestationLocalService.deleteOldUnpublishedManifestations();
	}

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	@Reference(unbind = "-")
	protected void setEventManifestationLocalService(
		ManifestationLocalService manifestationLocalService) {
		_manifestationLocalService = manifestationLocalService;
	}

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
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
	private EventLocalService _eventLocalService;
	private ManifestationLocalService _manifestationLocalService;
	private PlaceLocalService _placeLocalService;
	private TriggerFactory _triggerFactory;
}

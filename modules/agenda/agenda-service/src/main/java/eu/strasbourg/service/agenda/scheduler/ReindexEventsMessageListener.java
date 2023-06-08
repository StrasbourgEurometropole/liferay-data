package eu.strasbourg.service.agenda.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.service.place.service.PlaceLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;

import java.util.Calendar;
import java.util.Date;

/**
 * Réindexe les événements et les manifestations. Utile car on souhaite que
 * seules les dates futures de l'événement soient indexées.
 */
@Component(immediate = true, service = ReindexEventsMessageListener.class)
public class ReindexEventsMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Toutes les 2 heures"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				2, TimeUnit.HOUR);

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
		long companyId = PortalUtil.getDefaultCompanyId();
		String companyIdString = String.valueOf(companyId);
		String[] companyIdStringArray = new String[] { companyIdString };
		this._log.info("Start reindexing events and manifestations");
		Indexer<Event> eventIndexer = IndexerRegistryUtil
			.getIndexer(Event.class);
		if (eventIndexer != null) {
			try {
				eventIndexer.reindex(companyIdStringArray);
			} catch (Exception ex) {
				this._log.warn("Fail to reindex events");
			}
		}
		Indexer<Manifestation> manifestationIndexer = IndexerRegistryUtil
			.getIndexer(Manifestation.class);
		if (manifestationIndexer != null) {
			try {
				manifestationIndexer.reindex(companyIdStringArray);
			} catch (Exception ex) {
				this._log.warn("Fail to reindex events");
			}
		}
		this._log.info("Finish reindexing events and manifestations");
	}

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	@Reference(unbind = "-")
	protected void setManifestationLocalService(ManifestationLocalService manifestationLocalService) {
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
	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}

package eu.strasbourg.service.agenda.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.ImportReportLocalService;
import eu.strasbourg.service.place.service.PlaceLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Importe automatiquement les événements et les manifestations des fichiers
 * JSON présents dans le dossier d'import.
 */
@Component(immediate = true, service = ImportEventsMessageListener.class)
public class ImportEventsMessageListener
		extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		log.info("Start import events scheduler activation");

		String listenerClass = getClass().getName();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime firstTrigger = now.withHour(1).withMinute(30).withSecond(0);
		LocalDateTime secondTrigger = now.withHour(13).withMinute(30).withSecond(0);
		LocalDateTime thirdTrigger = now.plusDays(1).withHour(1).withMinute(30).withSecond(0);
		// Création du trigger "Tous à 1h30 ou 13h30 toutes les 12 heures"
		Trigger trigger;
		if(now.isBefore(firstTrigger)){
			trigger = _triggerFactory.createTrigger(
					listenerClass,
					listenerClass,
					java.util.Date
							.from(firstTrigger.atZone(ZoneId.systemDefault())
									.toInstant()),
					null,
					12, TimeUnit.HOUR);
		} else if(now.isBefore(secondTrigger)) {
			trigger = _triggerFactory.createTrigger(
					listenerClass,
					listenerClass,
					java.util.Date
							.from(secondTrigger.atZone(ZoneId.systemDefault())
									.toInstant()),
					null,
					12, TimeUnit.HOUR);
		} else {
			trigger = _triggerFactory.createTrigger(
					listenerClass,
					listenerClass,
					java.util.Date
							.from(thirdTrigger.atZone(ZoneId.systemDefault())
									.toInstant()),
					null,
					12, TimeUnit.HOUR);
		}

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				listenerClass, trigger);

		_schedulerEngineHelper.register(
				this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);

		log.info("Finish import events scheduler activation");
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start importing events");
		// Import des événements =
		_eventLocalService.doImport();
		log.info("Finish importing events");
	}

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
	}

	@Reference(unbind = "-")
	protected void setImportReportLocalService(ImportReportLocalService importReportLocalService) {
		_importReportLocalService = importReportLocalService;
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
	private PlaceLocalService _placeLocalService;
	private ImportReportLocalService _importReportLocalService;
	private TriggerFactory _triggerFactory;

	private Log log = LogFactoryUtil.getLog(this.getClass());
}

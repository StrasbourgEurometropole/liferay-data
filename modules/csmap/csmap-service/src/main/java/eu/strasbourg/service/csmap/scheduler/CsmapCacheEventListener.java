package eu.strasbourg.service.csmap.scheduler;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import org.osgi.service.component.annotations.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = CsmapCacheEventListener.class)
public class CsmapCacheEventListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Toutes les 2 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				2, TimeUnit.MINUTE);

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
		long codeCache = CodeCacheEnum.EVENT.getId();
		CsmapCache cacheEvent = _csmapCacheLocalService.findByCodeCache(codeCache);
		if(Validator.isNull(cacheEvent)){
			long id = _counterLocalService.increment();
			cacheEvent = _csmapCacheLocalService.createCsmapCache(id);
			cacheEvent.setCodeCache(codeCache);
		}
	}

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	@Reference(unbind = "-")
	protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
		_csmapCacheLocalService = csmapCacheLocalService;
	}

	@Reference(unbind = "-")
	protected void setCounterLocalService(CounterLocalService counterLocalService) {
		_counterLocalService = counterLocalService;
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
	private CsmapCacheLocalService _csmapCacheLocalService;
	private CounterLocalService _counterLocalService;
	private TriggerFactory _triggerFactory;
}

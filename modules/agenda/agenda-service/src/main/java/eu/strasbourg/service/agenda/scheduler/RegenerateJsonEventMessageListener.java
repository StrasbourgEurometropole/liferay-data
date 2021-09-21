package eu.strasbourg.service.agenda.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import eu.strasbourg.service.agenda.service.CacheJsonLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Calendar;
import java.util.Date;

/**
 * remettre à jour le JSON des caches actifs dont regeneratedDate <= aujourd’hui – 30j
 */
@Component(immediate = true, service = RegenerateJsonEventMessageListener.class)
public class RegenerateJsonEventMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Tous les jours à XXh"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				"0 30 4 * * ?");

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
		log.info("Start updateJsonEvent");
		_cacheJsonLocalService.updateJsonEvent();
		log.info("Finish updateJsonEvent");
	}

	@Reference(unbind = "-")
	protected void setCacheJsonLocalService(CacheJsonLocalService cacheJsonLocalService) {

		_cacheJsonLocalService = cacheJsonLocalService;
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
	private CacheJsonLocalService _cacheJsonLocalService;
	private TriggerFactory _triggerFactory;
	private Log log = LogFactoryUtil.getLog(this.getClass());
}

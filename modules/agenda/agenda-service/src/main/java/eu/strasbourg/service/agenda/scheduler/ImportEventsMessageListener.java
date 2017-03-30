package eu.strasbourg.service.agenda.scheduler;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

import eu.strasbourg.service.agenda.service.EventLocalService;

public class ImportEventsMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		// Tous les jours à 4h
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(
			getEventListenerClass(), getEventListenerClass(), "0 0 4 1/1 * ? *"));
		schedulerEngineHelper.register(this, schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start importing events");
		eventLocalService.doImport();
		log.info("Finish importing events");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper schedulerEngineHelper;

	@Reference(unbind = "-")
	private EventLocalService eventLocalService;

	private Log log = LogFactoryUtil.getLog(this.getClass());
}

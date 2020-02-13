package eu.strasbourg.service.notification.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.notification.service.NotificationLocalService;
import org.osgi.service.component.annotations.*;

/**
 * Publie toutes les notifications non publiées dont la date de publication a
 * été dépassée, dépublie ceux dont la date de dépublication a été dépassée et
 * supprime les plus anciennes
 */
@Component(immediate = true, service = CheckNotificationMessageListener.class)
public class CheckNotificationMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Création du trigger "Toutes les 5 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, null, null,
				5, TimeUnit.MINUTE);

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
		_notificationLocalService.publishRelevantNotifications();
		_notificationLocalService.unpublishPastNotifications();
		_notificationLocalService.deleteOldUnpublishedNotifications();
	}

	@Reference(unbind = "-")
	protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {
		_notificationLocalService = notificationLocalService;
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
	private NotificationLocalService _notificationLocalService;
	private TriggerFactory _triggerFactory;

}

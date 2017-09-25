package eu.strasbourg.service.notification.scheduler;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

import eu.strasbourg.service.notification.service.NotificationLocalService;

/**
 * Publie toutes les notifications non publiées dont la date de publication a
 * été dépassée, dépublie ceux dont la date de dépublication a été dépassée et
 * supprime les plus anciennes
 */
@Component(immediate = true, service = CheckNotificationMessageListener.class)
public class CheckNotificationMessageListener extends BaseSchedulerEntryMessageListener {

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper schedulerEngineHelper;

	private NotificationLocalService notificationLocalService;

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				5, TimeUnit.MINUTE));

		schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		notificationLocalService.publishRelevantNotifications();
		notificationLocalService.unpublishPastNotifications();
		notificationLocalService.deleteOldUnpublishedNotifications();
	}

	@Reference(unbind = "-")
	protected void setNotificationLocalService(NotificationLocalService notificationLocalService) {
		this.notificationLocalService = notificationLocalService;
	}

}

package eu.strasbourg.service.oidc.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import org.osgi.service.component.annotations.*;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = PublikUsersAnonymized.class)
public class PublikUsersAnonymized extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 2, TimeUnit.MINUTE));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	private Log log = LogFactoryUtil.getLog(PublikUsersAnonymized.class);

	@Override
	protected void doReceive(Message message) throws Exception {
//		log.info("Start anonymisation");
//		_publikUserLocalService.anonymizedUsers();
//		log.info("Finish anonymisation");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	@Reference(unbind = "-")
	private PublikUserLocalService _publikUserLocalService;

}

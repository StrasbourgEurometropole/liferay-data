package eu.strasbourg.service.project.scheduler;

import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.service.project.service.PetitionLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * Modifie le statut des participations
 * @author cedric.henry
 */
@Component(
		immediate = true,
		service = CheckProjectMessageListener.class
)
public class CheckProjectMessageListener extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(getEventListenerClass(),
				getEventListenerClass(), 60, TimeUnit.MINUTE));

		_schedulerEngineHelper.register(this, schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
		_participationLocalService.updateAllParticipationsStatus();
		_petitionLocalService.updateAllPetitionsStatus();
	}

    @Reference(unbind = "-")
    protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
        _participationLocalService = participationLocalService;
    }

    @Reference(unbind = "-")
    protected void setPetitionLocalService(PetitionLocalService petitionLocalService) {
        _petitionLocalService = petitionLocalService;
    }

    @Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private ParticipationLocalService _participationLocalService;
	private PetitionLocalService _petitionLocalService;
	
}

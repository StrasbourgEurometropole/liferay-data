package eu.strasbourg.service.agenda.scheduler;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

import eu.strasbourg.service.agenda.service.CampaignLocalService;

@Component(immediate = true, service = CheckEventMessageListener.class)
public class ExportCampaignsMessageListener
		extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		// Tous les jours à 2h
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(
			getEventListenerClass(), getEventListenerClass(), "0 0 2 1/1 * ? *"));
		schedulerEngineHelper.register(this, schedulerEntryImpl,
				DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		this.log.info("Start exporting campaigns");
		campaignLocalService.exportCampaigns();
		this.log.info("Finish exporting campaigns");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper schedulerEngineHelper;
	
	@Reference(unbind = "-")
	private CampaignLocalService campaignLocalService;

	private final Log log = LogFactoryUtil.getLog(this.getClass());
}

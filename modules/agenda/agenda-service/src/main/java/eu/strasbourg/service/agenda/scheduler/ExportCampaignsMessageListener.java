package eu.strasbourg.service.agenda.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;

import eu.strasbourg.service.agenda.service.CampaignLocalService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Exporte automatiquement les campagnes au format JSON et les place dans le dossier d'import.
 */
@Component(immediate = true, service = ExportCampaignsMessageListener.class)
public class ExportCampaignsMessageListener
		extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Call service to be sure they are "awake"
		this._campaignLocalService.getClass();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime firstTrigger = now.withHour(1).withMinute(25).withSecond(0);
		LocalDateTime secondTrigger = now.withHour(13).withMinute(25).withSecond(0);
		LocalDateTime thirdTrigger = now.plusDays(1).withHour(1).withMinute(25).withSecond(0);
		// Création du trigger "Tous à 1h25 ou 13h25 toutes les 12 heures"
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
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		this.log.info("Start exporting campaigns");
		_campaignLocalService.exportCampaigns();
		this.log.info("Finish exporting campaigns");
	}

	@Reference(unbind = "-")
	protected void setCampaignLocalService(CampaignLocalService campaignLocalService) {
		_campaignLocalService = campaignLocalService;
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
	private CampaignLocalService _campaignLocalService;
	private TriggerFactory _triggerFactory;
	private final Log log = LogFactoryUtil.getLog(this.getClass());
}

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

		// Maintenant + 2 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date twoMinutesFromNow = now.getTime();

		// Création du trigger "Tous les jours à 1h45"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, twoMinutesFromNow, null,
				"0 45 1 * * ?");

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

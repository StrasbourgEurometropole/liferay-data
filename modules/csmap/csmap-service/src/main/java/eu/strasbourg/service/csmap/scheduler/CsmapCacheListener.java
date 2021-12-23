package eu.strasbourg.service.csmap.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import org.osgi.service.component.annotations.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = CsmapCacheListener.class)
public class CsmapCacheListener
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
	protected void doReceive(Message message) {
		this.log.info("Start csmap caching");
		this.log.info("Start csmap event caching");
		_csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.EVENT.getId());
		this.log.info("End csmap event caching");
		this.log.info("Start csmap agenda caching");
		_csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.AGENDA.getId());
		this.log.info("End csmap agenda caching");
		this.log.info("Start csmap categories caching");
		_csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.CATEGORIES.getId());
		this.log.info("End csmap categories caching");
		this.log.info("End csmap caching");
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

	@Reference(unbind = "-")
	protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
		_csmapCacheLocalService = csmapCacheLocalService;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private CsmapCacheLocalService _csmapCacheLocalService;
	private final Log log = LogFactoryUtil.getLog(this.getClass());
}

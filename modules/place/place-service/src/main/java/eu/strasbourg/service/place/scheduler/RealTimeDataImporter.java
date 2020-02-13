package eu.strasbourg.service.place.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import org.osgi.service.component.annotations.*;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = RealTimeDataImporter.class)
public class RealTimeDataImporter extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Création du trigger "Toutes les 2 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, null, null,
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
	protected void doReceive(Message message) throws Exception {
	    _placeLocalService.updateRealTime();
	}

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
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
	private PlaceLocalService _placeLocalService;
	private TriggerFactory _triggerFactory;

}

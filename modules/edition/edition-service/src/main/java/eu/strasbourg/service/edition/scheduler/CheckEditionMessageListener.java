package eu.strasbourg.service.edition.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;

import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.service.edition.service.EditionLocalService;

@Component(immediate = true, service = CheckEditionMessageListener.class)
public class CheckEditionMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Création du trigger "Toutes les 15 minutes"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, null, null,
				15, TimeUnit.MINUTE);

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
		_editionLocalService.checkEditions();
		_editionGalleryLocalService.checkGalleries();
	}

	@Reference(unbind = "-")
	protected void setEditionLocalService(EditionLocalService editionLocalService) {
		_editionLocalService = editionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(EditionGalleryLocalService editionGalleryLocalService) {
		_editionGalleryLocalService = editionGalleryLocalService;
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
	private EditionLocalService _editionLocalService;
	private EditionGalleryLocalService _editionGalleryLocalService;
	private TriggerFactory _triggerFactory;
}

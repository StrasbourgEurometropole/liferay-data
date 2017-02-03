package eu.strasbourg.service.edition.scheduler;

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

import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.service.edition.service.EditionLocalService;

@Component(immediate = true, service = CheckEditionMessageListener.class)
public class CheckEditionMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(getEventListenerClass(),
				getEventListenerClass(), 15, TimeUnit.MINUTE));

		_schedulerEngineHelper.register(this, schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);
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
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private EditionLocalService _editionLocalService;
	private EditionGalleryLocalService _editionGalleryLocalService;

}

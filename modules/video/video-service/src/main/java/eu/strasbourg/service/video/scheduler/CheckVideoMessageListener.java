package eu.strasbourg.service.video.scheduler;

import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;

import eu.strasbourg.service.video.service.VideoGalleryLocalService;
import eu.strasbourg.service.video.service.VideoLocalService;

@Component(immediate = true, service = CheckVideoMessageListener.class)
public class CheckVideoMessageListener
	extends BaseMessageListener {

		@Activate
	@Modified
	protected void activate() {
			_schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(getClass().getName(),
					getClass().getName(), 15, TimeUnit.MINUTE));

		_schedulerEngineHelper.register(this, _schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		_videoLocalService.checkVideos();
		_videoGalleryLocalService.checkGalleries();
	}

	@Reference(unbind = "-")
	protected void setVideoLocalService(VideoLocalService videoLocalService) {
		_videoLocalService = videoLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setVideoGalleryLocalService(VideoGalleryLocalService videoGalleryLocalService) {
		_videoGalleryLocalService = videoGalleryLocalService;
	}

//	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private VideoLocalService _videoLocalService;
	private VideoGalleryLocalService _videoGalleryLocalService;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
}

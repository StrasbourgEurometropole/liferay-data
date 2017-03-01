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
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;

@Component(immediate = true, service = CheckEventMessageListener.class)
public class ReindexEventsMessageListener
		extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(),
						getEventListenerClass(), 2, TimeUnit.HOUR));

		_schedulerEngineHelper.register(this, schedulerEntryImpl,
				DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();
		String companyIdString = String.valueOf(companyId);
		String[] companyIdStringArray = new String[] { companyIdString };
		this._log.info("Start reindexing events and manifestations");
		Indexer<Event> eventIndexer = IndexerRegistryUtil
				.getIndexer(Event.class);
		if (eventIndexer != null) {
			eventIndexer.reindex(companyIdStringArray);
		}
		Indexer<Manifestation> manifestationIndexer = IndexerRegistryUtil
				.getIndexer(Manifestation.class);
		if (manifestationIndexer != null) {
			manifestationIndexer.reindex(companyIdStringArray);
		}
		IndexerRegistryUtil.getIndexer(Manifestation.class)
				.reindex(companyIdStringArray);
		this._log.info("Finish reindexing events and manifestations");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}

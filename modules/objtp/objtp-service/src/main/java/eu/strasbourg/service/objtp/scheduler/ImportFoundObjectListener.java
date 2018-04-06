package eu.strasbourg.service.objtp.scheduler;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

import eu.strasbourg.service.objtp.service.FoundObjectLocalService;
import eu.strasbourg.service.objtp.service.ObjectCategoryLocalService;

/**
 * Importe automatiquement les objets trouvés et les catégories associées
 * JSON présents dans le dossier d'import.
 */
@Component(immediate = true, service = ImportFoundObjectListener.class)
public class ImportFoundObjectListener extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		// Tous les jours à 4h
		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(getEventListenerClass(),
				getEventListenerClass(), "0 0 4 * * ?"));
		schedulerEngineHelper.register(this, schedulerEntryImpl,
			DestinationNames.SCHEDULER_DISPATCH);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		log.info("Start importing objtp");
		objectCategoryLocalService.doImport();
		foundObjectLocalService.doImport();
		log.info("Finish importing objtp");
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper schedulerEngineHelper;

	@Reference(unbind = "-")
	private FoundObjectLocalService foundObjectLocalService;
	@Reference(unbind = "-")
	private ObjectCategoryLocalService objectCategoryLocalService;

	private Log log = LogFactoryUtil.getLog(this.getClass());
	
}

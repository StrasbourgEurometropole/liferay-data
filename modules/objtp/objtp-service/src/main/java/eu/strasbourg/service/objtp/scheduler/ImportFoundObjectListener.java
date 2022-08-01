package eu.strasbourg.service.objtp.scheduler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.scheduler.*;
import eu.strasbourg.utils.constants.GlobalConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;

import eu.strasbourg.service.objtp.service.FoundObjectLocalService;

/**
 * Importe automatiquement les objets trouvés et les catégories associées
 * JSON présents dans le dossier d'import.
 */
@Component(
		immediate = true,
		service = ImportFoundObjectListener.class
)
public class ImportFoundObjectListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Tous les jours à 5h"
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow,
				null, "0 0 5 * * ?", TimeZone.getTimeZone(GlobalConstants.TIMEZONE));

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(this.getClass().getName(), trigger);

		_schedulerEngineHelper.register(this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Override
	protected void doReceive(Message message) throws JSONException, PortalException, IOException, ParseException {
		log.info("Start importing objtp");
		_foundObjectLocalService.doImport();
		log.info("Finish importing objtp");
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(unbind = "-")
	protected void setFoundObjectLocalService(FoundObjectLocalService foundObjectLocalService) {
		_foundObjectLocalService = foundObjectLocalService;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private FoundObjectLocalService _foundObjectLocalService;

	private Log log = LogFactoryUtil.getLog(this.getClass());
	
}

package eu.strasbourg.service.ejob.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.office.exporter.api.OffersCsvExporter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Export les offres au format CSV
 */
@Component(immediate = true, service = ExportOffreMessageListener.class)
public class ExportOffreMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Tous les jours à ??
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
//				"0 45 1 * * ?");
				5, TimeUnit.MINUTE);

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
		// Recuperation des offres concernées et non exportées (isExported=1)
		// les offres non concernées sont les stages, les apprentissages et les offres externes (isExported=0)
		List<Offer> offers = _offerLocalService.findOffersNotExported();

		// on ne prend pas les stages ni les apprentissages
		// on ne prend que les offres dont la date du jour est comprise  entre le début et la fin de la date de publication
		Date now = new Date();
		offers = offers.stream()
				.filter(o -> !o.getOfferTypeRecrutement().getName().equals("Stage") && !o.getOfferTypeRecrutement().getName().equals("Apprentissage"))
				.filter(o -> o.getPublicationStartDate().before(now) && o.getPublicationEndDate().after(now))
				.collect(Collectors.toList());

		if(_offersCsvExporter.exportOffers(offers)){
			// on change la valeur de isExported pour les offres
			for (Offer offer : offers) {
				offer.setIsExported(2);
				_offerLocalService.updateOffer(offer);
			}
		}
	}

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		this._offerLocalService = offerLocalService;
	}

	@Reference(unbind = "-")
	public void setOffersCsvExporter(OffersCsvExporter offersCsvExporter) {
		this._offersCsvExporter = offersCsvExporter;
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
	private OfferLocalService _offerLocalService;
	private OffersCsvExporter _offersCsvExporter;
	private TriggerFactory _triggerFactory;
}

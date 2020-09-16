package eu.strasbourg.service.ejob.scheduler;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.AlertLocalService;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.office.exporter.api.OffersCsvExporter;
import eu.strasbourg.utils.SearchHelper;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Export les offres au format CSV
 */
@Component(immediate = true, service = OfferMessageListener.class)
public class OfferMessageListener
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
		"0 5 1 * * ?");

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
		LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		Date now = Timestamp.valueOf(today);

		// Export TOTEM
		// Recuperation des offres concernées et non exportées (isExported=1)
		List<Offer> offersNotExported = _offerLocalService.findOffersNotExported();

		// on ne prend pas les stages ni les apprentissages
		// on ne prend que les offres dont la date du jour est comprise  entre le début et la fin de la date de publication
		offersNotExported = offersNotExported.stream()
				.filter(o -> !o.getOfferTypeRecrutement().getName().equals("Stage") && !o.getOfferTypeRecrutement().getName().equals("Apprentissage"))
				.filter(o -> o.getPublicationStartDate().compareTo(now) <= 0 && o.getPublicationEndDate().after(now))
				.collect(Collectors.toList());

		if(_offersCsvExporter.exportOffers(offersNotExported)){
			// on change la valeur de isExported pour les offres
			for (Offer offer : offersNotExported) {
				offer.setIsExported(2);
				_offerLocalService.updateOffer(offer);
			}
		}



		// Envoi mail aux partenaires
		// Recuperation des offres a envoyer
		List<Offer> offersExterne = _offerLocalService.findOffersNotSent();

		// on ne prend que les offres externes
		// on ne prend que les offres ayant au moins une adresse mail
		// on ne prend que les offres dont la date du jour est comprise  entre le début et la fin de la date de publication
		offersExterne = offersExterne.stream()
				.filter(o -> !o.getTypePublication().getName().equals("Interne uniquement"))
				.filter(o -> Validator.isNotNull(o.getEmails()))
				.filter(o -> o.getPublicationStartDate().compareTo(now) <= 0 && o.getPublicationEndDate().after(now))
				.collect(Collectors.toList());
		// on envoi un mail aux partenaires
		for (Offer offer : offersExterne) {
			if (offer.sendMail()) {
				offer.setEmailPartnerSent(1);
				_offerLocalService.updateOffer(offer);
			}
		}



		// Envoi mail des offres aux utilisateurs
		// ClassNames de l'offre
		String classNames = Offer.class.getName();

		List<Alert> alerts = _alertLocalService.getAlerts(-1, -1);
		for (Alert alert : alerts) {
			// Mots clés
			String keywords = alert.getKeyWord();

			// Catégories de l'offre
			List<Long> filterCategoriesIds = new ArrayList<>();
			for (AssetCategory categ : alert.getCategories()) {
				filterCategoriesIds.add(categ.getCategoryId());
			}
			long[] categoriesIds = filterCategoriesIds.stream().mapToLong(l -> l).toArray();

			// Locale
			Locale locale = LocaleUtil.fromLanguageId(alert.getLanguage());

			Hits hits = SearchHelper.getOfferWebServiceSearchHits(classNames, categoriesIds,
					keywords, locale);

			if (hits != null) {
				List<Offer> offersToSend = new ArrayList<>();
				for (Document document : hits.getDocs()) {
					Offer offer = _offerLocalService.fetchOffer(Long.parseLong(document.get(Field.ENTRY_CLASS_PK)));
					if (Validator.isNotNull(offer)) {
						offersToSend.add(offer);
					}
				}

				// on ne garde que les offres qui n'ont pas encore été envoyées (emailSend=0)
				// qui sont ouvertes (publicationStartDate) et qui ne sont pas finies
				offersToSend = offersToSend.stream()
						.filter(o -> o.getEmailSend() == 0)
						.filter(o -> o.getPublicationStartDate().compareTo(now) <= 0 && o.getPublicationEndDate().after(now))
						.collect(Collectors.toList());

				if(offersToSend.size() > 0) {
					if (alert.sendMail(offersToSend)) {
						for (Offer offer : offersToSend) {
							offer.setEmailSend(1);
							_offerLocalService.updateOffer(offer);
						}
					}
				}
			}
		}
	}

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		this._offerLocalService = offerLocalService;
	}

	@Reference(unbind = "-")
	public void setAlertLocalService(AlertLocalService alertLocalService) {
		this._alertLocalService = alertLocalService;
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
	private AlertLocalService _alertLocalService;
	private OffersCsvExporter _offersCsvExporter;
	private TriggerFactory _triggerFactory;
}

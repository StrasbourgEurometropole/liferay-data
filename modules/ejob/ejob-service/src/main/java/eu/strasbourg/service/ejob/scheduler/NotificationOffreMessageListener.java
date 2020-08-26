package eu.strasbourg.service.ejob.scheduler;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.AlertLocalService;
import eu.strasbourg.service.ejob.service.OfferLocalService;
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
 * Envoi un email aux utilisateurs ayant créé une alerte sur des offres
 */
@Component(immediate = true, service = NotificationOffreMessageListener.class)
public class NotificationOffreMessageListener
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
				"0 45 1 * * ?");
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
		// ClassNames de l'offre
		String classNames = Offer.class.getName();

		// Champ date
		LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		Date today = Timestamp.valueOf(now);

		List<Alert> alerts = _alertLocalService.getAlerts(-1, -1);
		for (Alert alert : alerts) {
			// Mots clés
			String keywords = alert.getKeyWord();

			// Catégories de l'offre
			List<Long> filterCategoriesIds = new ArrayList<Long>();
			for (AssetCategory categ : alert.getCategories()) {
				filterCategoriesIds.add(categ.getCategoryId());
			}
			long[] categoriesIds = filterCategoriesIds.stream().mapToLong(l -> l).toArray();

			// Locale
			Locale locale = LocaleUtil.fromLanguageId(alert.getLanguage());

			Hits hits = SearchHelper.getOfferWebServiceSearchHits(classNames, categoriesIds,
					keywords, locale);

			if (hits != null) {
				int i = 0;
				for (float s : hits.getScores()) {
					_log.info(GetterUtil.getString(hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
					i++;
				}

				List<Offer> offers = new ArrayList<Offer>();
				for (Document document : hits.getDocs()) {
					Offer offer = _offerLocalService.fetchOffer(Long.parseLong(document.get(Field.ENTRY_CLASS_PK)));
					if (Validator.isNotNull(offer)) {
						offers.add(offer);
					}
				}

				// on ne garde que les offres qui n'ont pas encore été envoyées (emailSend=0) qui sont ouvertes (publicationStartDate) et qui ne sont pas finies
				offers = offers.stream()
						.filter(o -> o.getEmailSend() == 0)
						.filter(o -> o.getPublicationStartDate().compareTo(today) <= 0)
						.filter(o -> o.getPublicationEndDate().after(today))
						.collect(Collectors.toList());

				if(offers.size() > 0)
					if(alert.sendMail(offers)){
						for (Offer offer : offers) {
							offer.setEmailSend(1);
							_offerLocalService.updateOffer(offer);
						}
					}
			}
		}
	}

	@Reference(unbind = "-")
	public void setAlertLocalService(AlertLocalService alertLocalService) {
		this._alertLocalService = alertLocalService;
	}

	@Reference(unbind = "-")
	public void setOfferLocalService(OfferLocalService offerLocalService) {
		this._offerLocalService = offerLocalService;
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
	private AlertLocalService _alertLocalService;
	private OfferLocalService _offerLocalService;
	private TriggerFactory _triggerFactory;
	private static Log _log = LogFactoryUtil.getLog(NotificationOffreMessageListener.class);
}

package eu.strasbourg.service.place.scheduler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.place.MairieStateSOAPClient;
import eu.strasbourg.service.place.ParkingStateClient;
import eu.strasbourg.service.place.PoolStateSOAPClient;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * Passe au statut "APPROVED" tous les événements et les manifestations dont la
 * publication a été programmée et dont la date de publication est désormais
 * dépassée
 */
@Component(immediate = true, service = RealTimeDataImporter.class)
public class RealTimeDataImporter extends BaseSchedulerEntryMessageListener {

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 2, TimeUnit.MINUTE));

		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	private Log log = LogFactoryUtil.getLog(RealTimeDataImporter.class);

	@Override
	protected void doReceive(Message message) throws Exception {
		// System.out.println("Start import of places real time data");

		// Récupère tous les lieux ayant un externalId
		List<Place> places = _placeLocalService.getPlaces(-1, -1);
		List<Place> placesWithRT = places.stream().filter(p -> Validator.isNotNull(p.getRTExternalId()))
				.collect(Collectors.toList());

		// On boucle sur les lieux ayant du temps réel configuré
		for (Place place : placesWithRT) {
			// System.out.println("Place : " + place.getAlias(Locale.FRANCE));
			// S'ils n'ont pas de type, on set le type correctement
			if (Validator.isNull(place.getRTType())) {
				// System.out.println("Set of type");
				for (AssetCategory type : place.getTypes()) {
					String typeSigId = AssetVocabularyHelper.getCategoryProperty(type.getCategoryId(), "SIG");
					if (typeSigId.toLowerCase().equals("cat_06_05")) { // Piscines
						place.setRTType("1");
						// System.out.println("Type 1");
					} else if (typeSigId.toLowerCase().equals("cat_12_07")) { // Mairies
						place.setRTType("3");
						// System.out.println("Type 3");
					} else { // Parkings
						place.setRTType("2");
						// System.out.println("Type 2");
					}
				}
			}

			// On récupère les données temps réel
			switch (place.getRTType()) {
			case "1":
				try {
					long poolOccupation = PoolStateSOAPClient.getOccupation(place);
					place.setRTOccupation(poolOccupation);
				} catch (Exception ex) {
					log.error("Can not update real time data for 'piscine'");
				}
				break;

			case "2":
				try {
					JSONObject parkingData = ParkingStateClient.getOccupationState(place.getRTExternalId());
					String status = parkingData.getString("ds");
					long capacity = Long.parseLong(parkingData.getString("dt"));
					long available = Long.parseLong(parkingData.getString("df"));
					place.setRTAvailable(available);
					place.setRTOccupation(capacity - available);
					place.setRTCapacity(capacity);
					place.setRTStatus(status);
				} catch (Exception ex) {
					log.error("Can not update real time data for 'parking'");
				}
				break;

			case "3":
				try {
					long occupation = MairieStateSOAPClient.getWaitingTime(place.getRTExternalId());
					place.setRTOccupation(occupation);
				} catch (Exception ex) {
					ex.printStackTrace();
					log.error("Can not update real time data for 'mairie'");
				}
				break;
			}
			place.setRTEnabled(true);
			place.setRTLastUpdate(new Date());
			_placeLocalService.updatePlace(place);

			/*
			 * System.out.println("Enabled : " + place.getRTEnabled());
			 * System.out.println("Occupation : " + place.getRTOccupation());
			 * System.out.println("Available : " + place.getRTAvailable());
			 * System.out.println("Capacity : " + place.getRTCapacity());
			 * System.out.println("Status : " + place.getRTStatus());
			 */
		}

	}

	@Reference(unbind = "-")
	protected void setEventLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
	}

	@Reference(unbind = "-")
	private volatile SchedulerEngineHelper _schedulerEngineHelper;

	private PlaceLocalService _placeLocalService;

}

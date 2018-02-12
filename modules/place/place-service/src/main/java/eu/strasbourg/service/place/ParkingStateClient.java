package eu.strasbourg.service.place;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.OccupationState;

/**
 * @author 01i454
 */
public class ParkingStateClient {

	static public long getOccupation(Place parking) {
		return ParkingStateClient.getOccupation(parking.getRTExternalId());
	}

	static public long getOccupation(String parkingCode) {
		long occupation = -1;

		try {
			JSONObject parkingJSON = ParkingStateClient
					.getJSONObject(parkingCode);
			if (parkingJSON != null) {
				long capacity = Long.parseLong(parkingJSON.getString("dt"));
				long available = Long.parseLong(parkingJSON.getString("df"));
				occupation = capacity - available;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return occupation;
	}

	static public long getCapacity(Place parking) {
		return ParkingStateClient.getCapacity(parking.getRTExternalId());
	}

	static public long getCapacity(String parkingCode) {
		long capacity = -1;
		try {
			JSONObject parkingJSON = ParkingStateClient
					.getJSONObject(parkingCode);
			if (parkingJSON != null) {
				capacity = Long.parseLong(parkingJSON.getString("dt"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return capacity;
	}

	static public OccupationState getOccupationState(Place parking) {
		return ParkingStateClient.getOccupationState(parking.getRTExternalId());
	}

	static public OccupationState getOccupationState(String parkingCode) {
		OccupationState state = OccupationState.NOT_AVAILABLE;
		try {
			JSONObject parkingJSON = ParkingStateClient
					.getJSONObject(parkingCode);
			if (parkingJSON != null) {
				String status = parkingJSON.getString("ds");
				long capacity = Long.parseLong(parkingJSON.getString("dt"));
				long available = Long.parseLong(parkingJSON.getString("df"));
				switch (status) {
				case "status_1":
					state = OccupationState.OPEN;
					state.setAvailable(""+available);
					state.setCapacity(""+capacity);
					break;
				case "status_2":
					state = OccupationState.FULL;
					break;
				case "status_3":
					state = OccupationState.NOT_AVAILABLE;
					break;
				case "status_4":
					state = OccupationState.CLOSED;
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return state;
	}

	static private JSONObject getJSONObject(String parkingCode)
			throws Exception {
		JSONObject parking = null;
		JSONObject json = JSONHelper.readJsonFromURL("http://carto.strasmap.eu/remote.amf.json/Parking.status");
		JSONArray mainArray = json.getJSONArray("s");
		for (int i = 0; i < mainArray.length(); i++) {
			JSONObject object = mainArray.getJSONObject(i);
			if (object.getString("id").equals(parkingCode)) {
				parking = object;
				break;
			}
		}

		return parking;
	}
}
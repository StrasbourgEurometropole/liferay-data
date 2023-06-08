package eu.strasbourg.service.place;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
			_log.error(ex.getMessage() + " : " + parkingCode);
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
			_log.error(ex.getMessage() + " : " + parkingCode);
		}

		return capacity;
	}

	static public JSONObject getOccupationState(Place parking, JSONArray parkingJsonArray) {
		return ParkingStateClient.getOccupationState(parking.getRTExternalId(), parkingJsonArray);
	}
	
	static public JSONObject getOccupationState(String parkingCode, JSONArray parkingJsonArray) {
		try {
			return ParkingStateClient
					.getJSONObject(parkingCode,parkingJsonArray);
		} catch (Exception e) {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	static public OccupationState g(String parkingCode) {
		OccupationState state = OccupationState.NOT_AVAILABLE;
		try {
			JSONObject parkingJSON = ParkingStateClient
					.getJSONObject(parkingCode);
			if (parkingJSON != null) {
				String status = parkingJSON.getString("ds");
				long capacity = Long.parseLong(parkingJSON.getString("dt"));
				long available = Long.parseLong(parkingJSON.getString("df"));
				switch (status) {
				case "0":
					state = OccupationState.NOT_AVAILABLE;
					break;
				case "1":
					state = OccupationState.OPEN;
					state.setAvailable(""+available);
					state.setCapacity(""+capacity);
					break;
				case "2":
					state = OccupationState.CLOSED;
					break;
				case "3":
					state = OccupationState.FULL;
				}
			}
		} catch (Exception ex) {
			_log.error(ex.getMessage() + " : " + parkingCode);
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

	static private JSONObject getJSONObject(String parkingCode, JSONArray parkingJsonArray)
			throws Exception {
		JSONObject parking = null;
		for (int i = 0; i < parkingJsonArray.length(); i++) {
			JSONObject object = parkingJsonArray.getJSONObject(i);
			JSONObject fields = object.getJSONObject("fields");
			if (fields.getString("ident").equals(parkingCode)) {
				parking = fields;
				break;
			}
		}

		return parking;
	}

	private static final Log _log = LogFactoryUtil.getLog(ParkingStateClient.class.getName());
}
package eu.strasbourg.service.opendata.realtime.parking;

import com.liferay.portal.kernel.json.JSONArray;

public interface OpenDataRealTimeParkingService {
    public JSONArray getParkingJSON() throws Exception;
}

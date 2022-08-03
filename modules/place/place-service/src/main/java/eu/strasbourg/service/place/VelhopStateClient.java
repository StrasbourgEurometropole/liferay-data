package eu.strasbourg.service.place;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.service.opendata.geo.velhop.OpenDataGeoVelhopService;
import eu.strasbourg.service.opendata.geo.velhop.OpenDataGeoVelhopServiceTracker;
import eu.strasbourg.service.place.model.Place;

/**
 * @author 01i454
 */
public class VelhopStateClient {

	public static JSONObject getOccupationState(Place velhop) {
		try {
			return getOpenDataGeoVelhopService().getVelhopInformationById(velhop.getRTExternalId());
		} catch (Exception e) {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	private static OpenDataGeoVelhopService openDataGeoVelhopService;
	private static OpenDataGeoVelhopServiceTracker openDataGeoVelhopServiceTracker;

	private static OpenDataGeoVelhopService getOpenDataGeoVelhopService() {
		if (openDataGeoVelhopService == null) {
			openDataGeoVelhopServiceTracker = new OpenDataGeoVelhopServiceTracker(VelhopStateClient.class);
			openDataGeoVelhopServiceTracker.open();
			openDataGeoVelhopService = openDataGeoVelhopServiceTracker.getService();
		}
		return openDataGeoVelhopService;
	}
}
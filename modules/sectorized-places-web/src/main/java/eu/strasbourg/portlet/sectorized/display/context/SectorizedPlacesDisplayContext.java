package eu.strasbourg.portlet.sectorized.display.context;

import java.util.HashMap;
import java.util.Map;

import eu.strasbourg.service.place.model.Place;

public class SectorizedPlacesDisplayContext {

	public Map<String, Object> getContextObjects(Place place) {
		Map<String, Object> contextObjects = new HashMap<String, Object>();
		contextObjects.put("entry", place);
		return contextObjects;
	}
}

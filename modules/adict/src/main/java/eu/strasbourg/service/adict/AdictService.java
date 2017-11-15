package eu.strasbourg.service.adict;

import java.util.List;

public interface AdictService {
	public List<Street> searchStreetNumbers(String query);
	public List<Street> searchStreetNumbers(String query, String city);
	public List<SectorType> getSectorTypes();
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String[] sectorTypes);
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String sectorType);
}

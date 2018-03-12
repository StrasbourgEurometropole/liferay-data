package eu.strasbourg.service.adict.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.SectorType;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true, property = {}, service = AdictService.class)
public class AdictServiceImpl implements AdictService {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	/**
	 * Retourne la liste des rues correspondant à la chaîne de caractères
	 * "query"
	 */
	@Override
	public List<Street> searchStreetNumbers(String query) {
		List<Street> streets = new ArrayList<Street>();

		query = HtmlUtil.escapeURL(query);
		try {

			String adictBaseURL = StrasbourgPropsUtil.getAdictBaseURL();
			JSONObject wsResponse = JSONHelper.readJsonFromURL(adictBaseURL + query);
			JSONArray features = wsResponse.getJSONArray("features");
			for (int i = 0; i < features.length(); i++) {
				JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
				if (properties.getString("type").equals("housenumber")) {
					String id = properties.getString("id");
					String label = properties.getString("label");
					String city = properties.getString("city");
					String x = features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates")
							.getString(0);
					String y = features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates")
							.getString(1);

					Street street = new Street(id, label, city, x, y);
					streets.add(street);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}

		return streets;
	}

	/**
	 * Retourne la liste des rues correspondant à la chaîne de caractères
	 * "query" et appartenant à la ville "city"
	 */
	@Override
	public List<Street> searchStreetNumbers(String query, String city) {
		// Si la requête ne termine pas par la ville, on l'ajoute
		if (!query.endsWith(city)) {
			query += " " + city;
		}
		return this.searchStreetNumbers(query).stream()
				.filter(s -> Validator.isNull(city) || s.getCity().toLowerCase().equals(city.toLowerCase()))
				.collect(Collectors.toList());
	}

	/**
	 * Retourne la liste des types de secteurs
	 */
	@Override
	public List<SectorType> getSectorTypes() {
		List<SectorType> types = new ArrayList<SectorType>();
		try {
			String adictSectorTypesBaseURL = StrasbourgPropsUtil.getAdictSectorTypesBaseURL();
			JSONArray wsResponse = JSONHelper.readJsonArrayFromURL(adictSectorTypesBaseURL);
			for (int i = 0; i < wsResponse.length(); i++) {
				String id = wsResponse.getJSONObject(i).getString("id");
				String name = wsResponse.getJSONObject(i).getString("typename");
				SectorType type = new SectorType(id, name);
				types.add(type);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return types;
	}

	/**
	 * Retourne la liste des ID SIG des lieux des secteurs "sectorTypes" pour
	 * les coordonnées "x" et "y"
	 */
	@Override
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String[] sectorTypes) {
		List<String> sigIds = new ArrayList<String>();
		for (String sectorType : sectorTypes) {
			sigIds.addAll(this.getSectorizedPlaceIdsForCoordinates(x, y, sectorType));
		}
		return sigIds;
	}

	/**
	 * Retourne l'ID SIG du lieu du secteur "sectorTypes pour les coordonnées
	 * "x" et "y"
	 */
	@Override
	public List<String> getSectorizedPlaceIdsForCoordinates(String x, String y, String sectorType) {
		List<String> sigIds = new ArrayList<String>();
		try {
			x = HtmlUtil.escape(x);
			y = HtmlUtil.escape(y);
			sectorType = HtmlUtil.escape(sectorType);
			String adictSectorBaseURL = StrasbourgPropsUtil.getAdictSectorBaseURL();
			JSONObject wsResponse = JSONHelper.readJsonFromURL(
					adictSectorBaseURL + "&x=" + x + "&y=" + y + "&srid=4326&sector_type=" + sectorType);
			JSONArray features = wsResponse.getJSONArray("features");
			for (int i = 0; i < features.length(); i++) {
				JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
				String sigId = properties.getString("description");
				if (Validator.isNotNull("description")) {
					sigIds.add(sigId);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}

		return sigIds;
	}

	/**
	 * Retourne les coordonnées d'une adresse en JSon
	 */
	@Override
	public JSONArray getCoordinateForAddress(String address) {
		JSONArray coordinates = null;
		try {
			String urlSearch = StrasbourgPropsUtil.getAdictBaseURL();
			String url = urlSearch + HtmlUtil.escapeURL(address);
			JSONObject addresses = JSONHelper.readJsonFromURL(url);
			JSONArray features = addresses.getJSONArray("features");
			if (features.length() > 0) {
				JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
				coordinates = geometry.getJSONArray("coordinates");
			}
		} catch (Exception e) {
			log.error(e);
		}
		return coordinates;
	}

}
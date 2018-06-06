package eu.strasbourg.portlet.zone.action;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.ZONE_WEB,
		"mvc.command.name=searchSector" }, service = MVCActionCommand.class)
public class ZoneAction implements MVCActionCommand {

	@Reference
	private AdictService adictService;

	/**
	 * Fonction appelée lors du clic sur le bouton rechercher
	 */
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		// Permet de remonter la hiérarchie des Request
		HttpServletRequest httpRequest = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

		// Requête de recherche
		String query = ParamUtil.getString(httpRequest, "query");
		request.setAttribute("myQuery", query);

		// Requête vide
		if (Validator.isNull(query)) {
			SessionErrors.add(request, "address-required");
			return false;
		}
		// Récupération des rues correspondantes à la requête
		String city = "Strasbourg";
		List<Street> streets = adictService.searchStreetNumbers(query, city);
		if (streets.size() == 0) {
			SessionErrors.add(request, "no-result");
			return false;
		} else if (streets.size() > 1) {
			SessionErrors.add(request, "too-many-results");
			return false;
		}

		// Une seule rue récupérée, on peut désormais chercher la zone
		// correspondante
		Street street = streets.get(0);
		String secteurType = "zone_stationnement_resident";
		JSONObject json = adictService.getCoordinatesForZone(street.getX(), street.getY(), secteurType);
		
		// on récupère le numéro de la zone
		JSONArray features = json.getJSONArray("features");
		String number ="";
		if (features.length() > 0) {
			JSONObject properties = features.getJSONObject(0).getJSONObject("properties");
			number = properties.getString("id");
		}

		request.setAttribute("address", street);
		request.setAttribute("json", json);
		request.setAttribute("number", number);
		request.setAttribute("streetName", street.getLabel());

		return true;
	}

}

package eu.strasbourg.portlet.place.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewPlacesDisplayContext extends ViewListBaseDisplayContext<Place> {

	public ViewPlacesDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Place.class, request, response);
	}

	public List<Place> getPlaces() throws PortalException {
		if (this._places == null) {
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			// Création de la liste d'objet
			List<Place> results = new ArrayList<Place>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Place place = PlaceLocalServiceUtil.fetchPlace(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (place != null) {
						results.add(place);
					}
				}
			}
			this._places = results;
		}
		return this._places;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PLACE_BO, StrasbourgPortletKeys.PLACE_BO,
			actionId);
	}


	private List<Place> _places;

}

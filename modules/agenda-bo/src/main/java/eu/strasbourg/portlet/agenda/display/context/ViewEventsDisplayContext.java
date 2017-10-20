package eu.strasbourg.portlet.agenda.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewEventsDisplayContext extends ViewListBaseDisplayContext<Event> {

	public ViewEventsDisplayContext(RenderRequest request, RenderResponse response) {
		super(Event.class, request, response);
	}

	public List<Event> getEvents() throws PortalException {
		if (this._events == null) {
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			// Création de la liste d'objet
			List<Event> results = new ArrayList<Event>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Event event = EventLocalServiceUtil
							.fetchEvent(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (event != null) {
						results.add(event);
					}
				}
			}
			this._events = results;
		}
		return this._events;
	}

	/**
	 * Retourne la liste des événements correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<Event> getAllEvents() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		List<Event> results = new ArrayList<Event>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Event event = EventLocalServiceUtil.fetchEvent(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (event != null) {
					results.add(event);
				} else {
					System.out.println("ERREUR, type = " + GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)));
				}
			}
		}
		return results;
	}

	public String getAllEventIds() throws PortalException {
		String eventIds = "";
		for (Event event : this.getAllEvents()) {
			if (eventIds.length() > 0) {
				eventIds += ",";
			}
			eventIds += event.getEventId();
		}
		return eventIds;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(this._themeDisplay.getScopeGroupId(),
				StrasbourgPortletKeys.AGENDA_BO, StrasbourgPortletKeys.AGENDA_BO, actionId);
	}

	private List<Event> _events;

}

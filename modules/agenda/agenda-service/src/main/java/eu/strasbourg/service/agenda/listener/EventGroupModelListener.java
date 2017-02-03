package eu.strasbourg.service.agenda.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class EventGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Events
		List<Event> events = EventLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Event event : events) {
			try {
				EventLocalServiceUtil.removeEvent(event.getEventId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
		// Manifestations
		List<Manifestation> manifestations = ManifestationLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Manifestation manifestation : manifestations) {
			try {
				ManifestationLocalServiceUtil.removeManifestation(manifestation.getManifestationId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
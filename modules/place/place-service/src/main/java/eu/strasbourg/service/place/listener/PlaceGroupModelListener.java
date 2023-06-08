package eu.strasbourg.service.place.listener;


import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;

@Component(
	immediate = true,
	service = ModelListener.class
)
public class PlaceGroupModelListener extends BaseModelListener<Group> {
	
	/**
	 *  A la suppression d'un groupe, on supprime les entités rattachées à ce groupe
	 */
	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
    			
		// Places
		List<Place> places = PlaceLocalServiceUtil.getByGroupId(model.getGroupId());
		for (Place place : places) {
			try {
				PlaceLocalServiceUtil.removePlace(place.getPlaceId());
			} catch (PortalException e) {
				_log.error(e);
			}
		}

	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
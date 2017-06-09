package eu.strasbourg.portlet.activity.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewActivityOrganizersDisplayContext
	extends ViewListBaseDisplayContext<ActivityOrganizer> {
	private List<ActivityOrganizer> activityOrganizers;

	public ViewActivityOrganizersDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(ActivityOrganizer.class, request, response);
	}

	public List<ActivityOrganizer> getActivityOrganizers() throws PortalException {
		if (this.activityOrganizers == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<ActivityOrganizer> results = new ArrayList<ActivityOrganizer>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					ActivityOrganizer activityOrganizer = ActivityOrganizerLocalServiceUtil.fetchActivityOrganizer(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (activityOrganizer != null) {
						results.add(activityOrganizer);
					}
				}
			}
			this.activityOrganizers = results;
		}
		return this.activityOrganizers;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.ACTIVITY_BO,
			StrasbourgPortletKeys.ACTIVITY_BO, actionId);
	}

}

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

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewActivitiesDisplayContext
	extends ViewListBaseDisplayContext<Activity> {
	private List<Activity> _activities;

	public ViewActivitiesDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Activity.class, request, response);
	}

	public List<Activity> getActivities() throws PortalException {
		if (this._activities == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Activity> results = new ArrayList<Activity>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Activity activity = ActivityLocalServiceUtil.fetchActivity(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (activity != null) {
						results.add(activity);
					} else {
						System.out.println("ERREUR, type = " + GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)));
					}
				}
			}
			this._activities = results;
		}
		return this._activities;
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

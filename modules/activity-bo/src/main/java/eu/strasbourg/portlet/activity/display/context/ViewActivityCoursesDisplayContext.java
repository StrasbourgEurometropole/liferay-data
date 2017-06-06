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

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewActivityCoursesDisplayContext
	extends ViewListBaseDisplayContext<ActivityCourse> {
	private List<ActivityCourse> activityCourses;

	public ViewActivityCoursesDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(ActivityCourse.class, request, response);
	}

	public List<ActivityCourse> getActivityCourses() throws PortalException {
		if (this.activityCourses == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<ActivityCourse> results = new ArrayList<ActivityCourse>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					ActivityCourse activityCourse = ActivityCourseLocalServiceUtil.fetchActivityCourse(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (activityCourse != null) {
						results.add(activityCourse);
					}
				}
			}
			this.activityCourses = results;
		}
		return this.activityCourses;
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

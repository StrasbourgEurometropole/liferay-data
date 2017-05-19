package eu.strasbourg.portlet.activity.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditActivityOrganizerDisplayContext {

	private ActivityOrganizer activityOrganizer;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;

	public EditActivityOrganizerDisplayContext(RenderRequest request,
		RenderResponse response) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public ActivityOrganizer getActivityOrganizer() throws PortalException {
		long activityOrganizerId = ParamUtil.getLong(request, "activityOrganizerId");
		if (activityOrganizer == null && activityOrganizerId > 0) {
			activityOrganizer = ActivityOrganizerLocalServiceUtil
				.getActivityOrganizer(activityOrganizerId);
		}
		return activityOrganizer;
	}

	/**
	 * Retourne l'entité éditée
	 */
	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil
			.getAvailableLocales(themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return themeDisplay.getPermissionChecker().hasPermission(
			this.themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.ACTIVITY_BO,
			StrasbourgPortletKeys.ACTIVITY_BO, actionId);
	}

}

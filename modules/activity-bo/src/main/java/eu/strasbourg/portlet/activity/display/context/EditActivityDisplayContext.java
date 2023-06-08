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

import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditActivityDisplayContext {

	private Activity activity;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;

	public EditActivityDisplayContext(RenderRequest request,
		RenderResponse response) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	/**
	 * Retourne l'entité éditée
	 */
	public Activity getActivity() throws PortalException {
		long activityId = ParamUtil.getLong(request, "activityId");
		if (activity == null && activityId > 0) {
			activity = ActivityLocalServiceUtil.getActivity(activityId);
		}
		return activity;
	}

	/**
	 * Retourne la liste des locales disponibles
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

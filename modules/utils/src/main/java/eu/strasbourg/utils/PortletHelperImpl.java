package eu.strasbourg.utils;

import javax.portlet.Portlet;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.theme.ThemeDisplay;

import eu.strasbourg.utils.api.PortletHelperService;

@Component(
    immediate = true,
    service = PortletHelperService.class
)
public class PortletHelperImpl implements PortletHelperService {

	/**
	 * Retourne le titre du portlet configuré dans la configuration Look And
	 * Feel s'il existe et si "utiliser le titre personnalisé" est coché, sinon
	 * à partir de la clé de traduction passée en paramètre
	 */
	public String getPortletTitle(String key, PortletRequest request) {
		return PortletHelper.getPortletTitle(key, request);
	}

	/**
	 * Retourne un boolean indiquant si le portlet doit être affiché ou non en fonction des
	 * préférences de l'utilisateur dans le portlet user display configuration
	 */
	public boolean isPortletDisplayedOnDashboard(ThemeDisplay themeDisplay, String cssClassNames) {
		return PortletHelper.isPortletDisplayedOnDashboard(themeDisplay, cssClassNames);
	}

	public boolean showDeleteButtonOnDashboard(ThemeDisplay themeDisplay, String portletId) {
		return PortletHelper.showDeleteButtonOnDashboard(themeDisplay, portletId);
	}

	/**
	 * Retourne un boolean indiquant si le portlet doit être replié ou non en fonction des
	 * préférences de l'utilisateur dans le portlet user display configuration
	 */
	public boolean isPortletFoldedOnDashboard(ThemeDisplay themeDisplay, String cssClassNames) {
		return PortletHelper.isPortletFoldedOnDashboard(themeDisplay, cssClassNames);
	}

	public boolean showRetractableButtonOnDashboard(ThemeDisplay themeDisplay, String portletId) {
		return PortletHelper.showRetractableButtonOnDashboard(themeDisplay, portletId);
	}

	public void showPortlet(String portletId) {
		PortletHelper.showPortlet(portletId);
	}


	public void hidePortlet(String portletId) {
		PortletHelper.hidePortlet(portletId);
	}

	public void togglePortlet(String portletId, boolean show) {
		PortletHelper.togglePortlet(portletId, show);
	}
}

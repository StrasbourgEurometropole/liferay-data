package eu.strasbourg.utils.api;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletRequest;

/**
 * Classe de service qui peut être utilisée dans les templates L'implémentation
 * appelle des fonctions statiques présentes dans les classes Helper classiques
 * 
 * @author Benjamin Bini
 *
 */
@ProviderType
public interface PortletHelperService {
	public String getPortletTitle(String key, PortletRequest request);
	public boolean isPortletDisplayedOnDashboard(ThemeDisplay themeDisplay, String cssClassNames);
	public boolean showDeleteButtonOnDashboard(ThemeDisplay themeDisplay, String portletId);
	public boolean isPortletFoldedOnDashboard(ThemeDisplay themeDisplay, String cssClassNames);
	public boolean showRetractableButtonOnDashboard(ThemeDisplay themeDisplay, String portletId);
	public void showPortlet(String portletId);
	public void hidePortlet(String portletId);
	public void togglePortlet(String portletId, boolean show);
	public boolean isUserAuthorizedToConsultInternOffer(String typePublication);
}

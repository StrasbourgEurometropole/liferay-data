package eu.strasbourg.utils.api;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.theme.ThemeDisplay;

import aQute.bnd.annotation.ProviderType;

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
	public boolean hiddenDashboardPortlet(ThemeDisplay themeDisplay, String cssClassNames);
}

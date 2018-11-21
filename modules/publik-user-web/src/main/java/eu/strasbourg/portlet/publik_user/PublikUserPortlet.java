package eu.strasbourg.portlet.publik_user;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.publik_user.display.context.PublikUserDisplayContext;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Publik User",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + StrasbourgPortletKeys.PUBLIK_USER_WEB,
		"javax.portlet.init-param.view-template=/publik-user-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"}, service = Portlet.class)

public class PublikUserPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
					   RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);

			// On set le DisplayContext
			PublikUserDisplayContext dc = new PublikUserDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);

			// Recuperation de l'URL de "base" du site
			String homeURL = "/";
			if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty() || themeDisplay.getScopeGroup().isStagingGroup()) {
				homeURL = "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL() + "/";
			}
			renderRequest.setAttribute("homeURL", homeURL);

			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
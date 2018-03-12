package eu.strasbourg.portlet.favorites;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.favorites.configuration.FavoritesConfiguration;
import eu.strasbourg.portlet.favorites.display.context.FavoritesDisplayContext;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name= Favoris",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.FAVORITES_VIEWER_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false"
	},
	service = Portlet.class
)
public class FavoritesViewerWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			
			FavoritesConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FavoritesConfiguration.class);		
			
			
			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "default";
			}
			String showAllURL = configuration.showAllURL();
			if(Validator.isNull(showAllURL)){
				showAllURL = "#";
			}
			renderRequest.setAttribute("showAllURL", showAllURL);
			
			
			FavoritesDisplayContext dc = new FavoritesDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			
			include("/templates/" + template + ".jsp", renderRequest, renderResponse);
			
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
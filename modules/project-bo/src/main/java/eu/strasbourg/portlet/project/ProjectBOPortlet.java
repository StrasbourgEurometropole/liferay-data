package eu.strasbourg.portlet.project;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.project.display.context.EditProjectDisplayContext;
import eu.strasbourg.portlet.project.display.context.ViewProjectsDisplayContext;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author cedric.henry
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/project-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/project-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/project-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProjectBOPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		
		renderResponse.setTitle("Projets");
		
		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editProject")) {
			EditProjectDisplayContext dc = new EditProjectDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);		
		} else {
			ViewProjectsDisplayContext dc = new ViewProjectsDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);
		}
		
		super.render(renderRequest, renderResponse);
	}

}
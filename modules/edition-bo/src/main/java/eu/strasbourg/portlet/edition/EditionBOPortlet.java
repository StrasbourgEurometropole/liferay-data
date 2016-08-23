package eu.strasbourg.portlet.edition;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.edition.display.context.EditEditionDisplayContext;
import eu.strasbourg.portlet.edition.display.context.EditGalleryDisplayContext;
import eu.strasbourg.portlet.edition.display.context.ViewEditionsDisplayContext;
import eu.strasbourg.portlet.edition.display.context.ViewGalleriesDisplayContext;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/edition-bo-main.js",
		"javax.portlet.display-name=Editions",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/edition-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EditionBOPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		
		renderResponse.setTitle("Editions");
		
		// If we are on an "add" page, we set a return URL and show the "back" button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// If we are on the Edition edition page, we add the corresponding display context
		if (cmd.equals("editEdition")) {
			EditEditionDisplayContext dc = new EditEditionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editGallery")) {
			EditGalleryDisplayContext dc = new EditGalleryDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("galleries")) {
			ViewGalleriesDisplayContext dc = new ViewGalleriesDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);			
		} else { // Else, we are on the edition list page
			ViewEditionsDisplayContext dc = new ViewEditionsDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);
		}
		
		super.render(renderRequest, renderResponse);
	}	
	
}
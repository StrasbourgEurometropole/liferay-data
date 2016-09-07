package eu.strasbourg.portlet.artwork;

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

import eu.strasbourg.portlet.artwork.display.context.EditArtworkDisplayContext;
import eu.strasbourg.portlet.artwork.display.context.EditCollectionDisplayContext;
import eu.strasbourg.portlet.artwork.display.context.ViewArtworksDisplayContext;
import eu.strasbourg.portlet.artwork.display.context.ViewCollectionsDisplayContext;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/artwork-bo-main.js",
		"javax.portlet.display-name=Oeuvres",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/artwork-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ArtworkBOPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		
		renderResponse.setTitle("Oeuvres");
		
		// If we are on an "add" page, we set a return URL and show the "back" button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// If we are on the Artwork edition page, we add the corresponding display context
		if (cmd.equals("editArtwork")) {
			EditArtworkDisplayContext dc = new EditArtworkDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editCollection")) {
			EditCollectionDisplayContext dc = new EditCollectionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("collections")) {
			ViewCollectionsDisplayContext dc = new ViewCollectionsDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);			
		} else { // Else, we are on the artwork list page
			ViewArtworksDisplayContext dc = new ViewArtworksDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);
		}
		
		super.render(renderRequest, renderResponse);
	}	
	
}
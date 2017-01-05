package eu.strasbourg.portlet.video;

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

import eu.strasbourg.portlet.video.display.context.EditVideoDisplayContext;
import eu.strasbourg.portlet.video.display.context.EditGalleryDisplayContext;
import eu.strasbourg.portlet.video.display.context.ViewVideosDisplayContext;
import eu.strasbourg.portlet.video.display.context.ViewGalleriesDisplayContext;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/video-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/video-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/video-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class VideoBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");

		renderResponse.setTitle("Videos");

		// On affiche un bouton retour si on se trouve sur une page d'édition
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// On set le displayContext selon la page sur laquelle on se trouve
		if (cmd.equals("editVideo")) {
			EditVideoDisplayContext dc = new EditVideoDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editGallery")) {
			EditGalleryDisplayContext dc = new EditGalleryDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("galleries")) {
			ViewGalleriesDisplayContext dc = new ViewGalleriesDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the video list page
			ViewVideosDisplayContext dc = new ViewVideosDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		super.render(renderRequest, renderResponse);
	}

}
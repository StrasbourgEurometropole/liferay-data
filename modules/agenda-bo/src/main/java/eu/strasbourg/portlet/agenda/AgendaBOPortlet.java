package eu.strasbourg.portlet.agenda;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.agenda.display.context.EditEventDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.EditManifestationDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.ViewEventsDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.ViewManifestationsDisplayContext;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.header-portlet-css=/css/vendors/daterangepicker.css",
		"com.liferay.portlet.header-portlet-css=/css/agenda-bo-main.css",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/agenda-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class AgendaBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");

		renderResponse.setTitle("Events");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// If we are on the Event event page, we add the corresponding
		// display context
		if (cmd.equals("editEvent")) {
			EditEventDisplayContext dc = new EditEventDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editManifestation")) {
			EditManifestationDisplayContext dc = new EditManifestationDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("manifestations")) {
			ViewManifestationsDisplayContext dc = new ViewManifestationsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the event list page
			ViewEventsDisplayContext dc = new ViewEventsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}
		
		super.render(renderRequest, renderResponse);
	}
}
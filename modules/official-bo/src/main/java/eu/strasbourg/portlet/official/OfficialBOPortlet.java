package eu.strasbourg.portlet.official;

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

import eu.strasbourg.portlet.official.display.context.EditOfficialDisplayContext;
import eu.strasbourg.portlet.official.display.context.ViewOfficialsDisplayContext;


@Component(
	immediate = true,
	property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/official-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/official-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/official-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class OfficialBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");

		renderResponse.setTitle("Officials");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// If we are on the Edition edition page, we add the corresponding
		// display context
		if (cmd.equals("editOfficial")) {
			EditOfficialDisplayContext dc = new EditOfficialDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the edition list page
			ViewOfficialsDisplayContext dc = new ViewOfficialsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		super.render(renderRequest, renderResponse);
	}

}
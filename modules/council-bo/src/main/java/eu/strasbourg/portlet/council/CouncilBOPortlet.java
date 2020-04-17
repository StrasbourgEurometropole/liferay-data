package eu.strasbourg.portlet.council;

import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.portlet.council.display.context.EditCouncilSessionDisplayContext;
import eu.strasbourg.portlet.council.display.context.EditDeliberationDisplayContext;

import eu.strasbourg.portlet.council.display.context.ViewCouncilSessionsDisplayContext;
import eu.strasbourg.portlet.council.display.context.ViewDeliberationsDisplayContext;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/council-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class CouncilBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		renderResponse.setTitle("CouncilSessions");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}

		// If we are on the Session, we add the corresponding
		// display context
		if (cmd.equals("editCouncilSession") || mvcPath.equals("/council-bo-edit-session.jsp")) {
			EditCouncilSessionDisplayContext dc = new EditCouncilSessionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editDeliberation") || mvcPath.equals("/council-bo-edit-deliberation.jsp")) {
			EditDeliberationDisplayContext dc = new EditDeliberationDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("deliberations")) {
			ViewDeliberationsDisplayContext dc = new ViewDeliberationsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the event list page
			ViewCouncilSessionsDisplayContext dc = new ViewCouncilSessionsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}

}
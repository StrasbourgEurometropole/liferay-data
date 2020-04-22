package eu.strasbourg.portlet.council;

import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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
		"com.liferay.portlet.footer-portlet-javascript=/js/council-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/council-bo-main.css",
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
		if (cmd.equals("editCouncilSession") || mvcPath.equals("/council-bo-edit-council-session.jsp")) {
			EditCouncilSessionDisplayContext dc = new EditCouncilSessionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editDeliberation") || mvcPath.equals("/council-bo-edit-deliberation.jsp")) {
			EditDeliberationDisplayContext dc = new EditDeliberationDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("deliberations")) {

			HttpServletRequest originalRequest = PortalUtil.getHttpServletRequest(renderRequest);
			// Récupère la catégorie de conseil sélectionné
			String categoryCouncilId = ParamUtil.getString(renderRequest, "categoryToAdd");
			// Récupère la catégorie de conseil en session
			Object sessionObject = originalRequest.getSession().getAttribute("categoryCouncilId");
			String sessionCategoryCouncilId = null;
			if(!Validator.isNull(sessionObject)) {
				sessionCategoryCouncilId = sessionObject.toString();
			}

			String categoryId = null;
			// Si aucun conseil sélectionné, on prend celui de la session
			if(Validator.isNull(categoryCouncilId)) {
				categoryId=sessionCategoryCouncilId;
			}
			// Si on a sélectionné une catégorie différente à celle de la session, on prend la nouvelle et on l'enregistre en session
			else if (!categoryCouncilId.equals(sessionCategoryCouncilId)) {
				categoryId = categoryCouncilId;
				originalRequest.getSession().setAttribute("categoryCouncilId", categoryCouncilId);
			}

			ViewDeliberationsDisplayContext dc = new ViewDeliberationsDisplayContext(renderRequest, renderResponse, categoryId);
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
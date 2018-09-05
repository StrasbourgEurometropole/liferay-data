package eu.strasbourg.portlet.project;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.project.display.context.EditInitiativeDisplayContext;
import eu.strasbourg.portlet.project.display.context.EditParticipationDisplayContext;
import eu.strasbourg.portlet.project.display.context.EditPetitionDisplayContext;
import eu.strasbourg.portlet.project.display.context.EditProjectDisplayContext;
import eu.strasbourg.portlet.project.display.context.ViewInitiativesDisplayContext;
import eu.strasbourg.portlet.project.display.context.ViewParticipationsDisplayContext;
import eu.strasbourg.portlet.project.display.context.ViewPetitionsDisplayContext;
import eu.strasbourg.portlet.project.display.context.ViewProjectsDisplayContext;

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
		String tab = ParamUtil.getString(renderRequest, "tab");
		Boolean fromAjaxProject = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxProject"));
		Boolean fromAjaxParticipation = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxParticipation"));
		Boolean fromAjaxPetition = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxPetition"));
		String title = PortalUtil.getPortletTitle(renderRequest);
		
		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editProject") || fromAjaxProject) {
			EditProjectDisplayContext dc = new EditProjectDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);	
			title = "projects";
		} else if (cmd.equals("editParticipation") || fromAjaxParticipation) {
			EditParticipationDisplayContext dc = new EditParticipationDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "participations";
		} else if (cmd.equals("editPetition") || fromAjaxPetition) {
			EditPetitionDisplayContext dc = new EditPetitionDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "Petitions";
		} else if (cmd.equals("editInitiative") || fromAjaxPetition) {
			EditInitiativeDisplayContext dc = new EditInitiativeDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "Initiatives";		
		} else if (tab.equals("participations")) {
			ViewParticipationsDisplayContext dc = new ViewParticipationsDisplayContext(renderRequest, renderResponse); 
			renderRequest.setAttribute("dc", dc);
			title = "participations";
		} else if (tab.equals("petitions")){
			ViewPetitionsDisplayContext dc = new ViewPetitionsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "petitions";
		} else if (tab.equals("initiatives")){
			ViewInitiativesDisplayContext dc = new ViewInitiativesDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "initiatives";
		}
		else { // Else, we are on the projects list page
			ViewProjectsDisplayContext dc = new ViewProjectsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "projects";
		}
		
		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());
		
		super.render(renderRequest, renderResponse);
		
		title = LanguageUtil.get(PortalUtil.getHttpServletRequest(renderRequest), title);
		renderResponse.setTitle(title);
	}

}
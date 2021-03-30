package eu.strasbourg.portlet.help;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.help.constants.HelpBOConstants;
import eu.strasbourg.portlet.help.context.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author angelique.champougny
 */
@Component(
	immediate = true,
		property = {
			"javax.portlet.version=3.0",
			"com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.footer-portlet-javascript=/js/help-bo-main.js",
			"com.liferay.portlet.header-portlet-css=/css/help-bo-main.css",
			"com.liferay.portlet.single-page-application=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/help-bo-view.jsp",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
	service = Portlet.class
)
public class HelpBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
					   RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		// Recuperation des données de la requete de page
		String cmd = ParamUtil.getString(renderRequest, HelpBOConstants.PARAM_CMD);
		String tab = ParamUtil.getString(renderRequest, HelpBOConstants.PARAM_TAB);
		String mvcPath = ParamUtil.getString(renderRequest, HelpBOConstants.PARAM_MVC_PATH);
		String title = HelpBOConstants.HEADER_TITLE;

		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, HelpBOConstants.PARAM_RETURN_URL);
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
			renderRequest.setAttribute(HelpBOConstants.PARAM_RETURN_URL, returnURL);
		}

		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editHelpProposal") || mvcPath.equals("/help-bo-edit-help-proposal.jsp")) {
			EditHelpDisplayContext dc = new EditHelpDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editHelpRequest") || mvcPath.equals("/help-bo-edit-help-request.jsp")) {
			EditHelpRequestDisplayContext dc = new EditHelpRequestDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if(cmd.equals("viewProposalHelpRequests") || mvcPath.equals("/help-bo-view-proposal-help-requests.jsp")) {
			ViewProposalHelpRequestsDisplayContext dc = new ViewProposalHelpRequestsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if(cmd.equals("viewSeekerHelpRequests") || mvcPath.equals("/help-bo-view-seeker-help-requests.jsp")) {
			ViewSeekerHelpRequestsDisplayContext dc = new ViewSeekerHelpRequestsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if(tab.equals("helpSeekers")) {
			ViewHelpSeekersDisplayContext dc = new ViewHelpSeekersDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if(tab.equals("helpRequests")) {
			ViewHelpRequestsDisplayContext dc = new ViewHelpRequestsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the main list page
				title = "helpProposals";
				ViewHelpsDisplayContext dc = new ViewHelpsDisplayContext(renderRequest, renderResponse);
				renderRequest.setAttribute("dc", dc);
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);

		title = LanguageUtil.get(PortalUtil.getHttpServletRequest(renderRequest), title);
		renderResponse.setTitle(title);
	}

}
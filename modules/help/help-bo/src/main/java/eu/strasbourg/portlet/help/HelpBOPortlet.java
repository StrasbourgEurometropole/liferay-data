package eu.strasbourg.portlet.help;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.help.context.EditHelpDisplayContext;
import eu.strasbourg.portlet.help.context.ViewHelpsDisplayContext;
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
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		String title = PortalUtil.getPortletTitle(renderRequest);

		// Verification des requetes issues d'un champ repetable
		Boolean fromAjaxHelp = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxHelp"));

		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editHelpProposal") || mvcPath.equals("/help-bo-edit-help-proposal.jsp") || fromAjaxHelp) {
			EditHelpDisplayContext dc = new EditHelpDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "helpProposal";
		}else { // Else, we are on the projects list page
				ViewHelpsDisplayContext dc = new ViewHelpsDisplayContext(renderRequest, renderResponse);
				renderRequest.setAttribute("dc", dc);
				title = "helps";
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);

		title = LanguageUtil.get(PortalUtil.getHttpServletRequest(renderRequest), title);
		renderResponse.setTitle(title);
	}
}
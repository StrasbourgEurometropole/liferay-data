package eu.strasbourg.portlet.gtfs;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.portlet.gtfs.display.context.EditImportHistoricDisplayContext;
import eu.strasbourg.portlet.gtfs.display.context.ViewImportHistoricsDisplayContext;

/**
 * @author cedric.henry
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/gtfs-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/gtfs-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/gtfs-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GTFSBOPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		// Recuperation des données de la requete de page
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		String title = PortalUtil.getPortletTitle(renderRequest);
		
		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editImportHistoric") || mvcPath.equals("/gtfs-bo-edit-import-historic.jsp")) {
			EditImportHistoricDisplayContext dc = new EditImportHistoricDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "import-historics";
		} else { // Else, we are on the import project list page
			ViewImportHistoricsDisplayContext dc = new ViewImportHistoricsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "import-historics";
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());
		
		super.render(renderRequest, renderResponse);
		
		title = LanguageUtil.get(PortalUtil.getHttpServletRequest(renderRequest), title);
		renderResponse.setTitle(title);
	}
	
}
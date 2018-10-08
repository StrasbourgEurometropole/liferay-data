package eu.strasbourg.portlet.oidc;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.oidc.display.context.EditPublikUserDisplayContext;
import eu.strasbourg.portlet.oidc.display.context.ViewPublikUsersDisplayContext;
import eu.strasbourg.service.office.exporter.api.CommentsXlsxExporter;
import eu.strasbourg.service.office.exporter.api.HistoricPublikUserTextExporter;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author cedric.henry
 */
@Component(immediate = true, property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/oidc-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/oidc-bo-main.css",
		"com.liferay.portlet.single-page-application=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/oidc-bo-view.jsp", "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class OIDCBOPortlet extends MVCPortlet {

	private HistoricPublikUserTextExporter historicPublikUserTextExporter;
	
	@Reference(unbind = "-")
	public void setHistoricPublikUserTextExporter(HistoricPublikUserTextExporter historicPublikUserTextExporter) {
		this.historicPublikUserTextExporter = historicPublikUserTextExporter;
	}

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		
		renderResponse.setTitle("Utilisateurs Publik");
		
		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}
		
		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editPublikUser")) {
			EditPublikUserDisplayContext dc = new EditPublikUserDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);		
		} else { // Else, we are on the publik users list page
			// on veut anonymiser les actions de l'utilisateur
			if (cmd.equals("anonymysedInfos")) {
				long publikUserLiferayId = ParamUtil.getLong(renderRequest, "publikUserLiferayId");
				anonymysedInfos(publikUserLiferayId);
			}
			ViewPublikUsersDisplayContext dc;
			try {
				dc = new ViewPublikUsersDisplayContext(renderRequest, renderResponse);
				renderRequest.setAttribute("dc", dc);
			} catch (PortalException e) {
				_log.error(e);
			} 
		}
		
		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());
		
		super.render(renderRequest, renderResponse);
	}
	
	public void anonymysedInfos(long publikUserId) {
		
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
package eu.strasbourg.portlet.ejob;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.ejob.display.context.EditOfferDisplayContext;
import eu.strasbourg.portlet.ejob.display.context.ViewOfferDisplayContext;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author quentin.mayer
 */
@Component(
	immediate = true,
	property = {
			"com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.footer-portlet-javascript=/js/ejob-bo-main.js",
			"com.liferay.portlet.header-portlet-css=/css/ejob-bo-main.css",
			"com.liferay.portlet.layout-cacheable=true",
			"com.liferay.portlet.single-page-application=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/ejob-bo-view.jsp",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class EjobBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		// Verification des requetes issues d'un champ repetable
		Boolean fromAjaxEmail = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxEmail"));
		Boolean fromAjaxGradeRange = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjaxGradeRange"));

		renderResponse.setTitle("EJob");

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
		if (cmd.equals("editOffer") || mvcPath.equals("/ejob-bo-edit-offer.jsp") || fromAjaxEmail || fromAjaxGradeRange) {
			EditOfferDisplayContext dc = new EditOfferDisplayContext(renderRequest);
			renderRequest.setAttribute("dc", dc);
		} else{
			ViewOfferDisplayContext dc = new ViewOfferDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}
}
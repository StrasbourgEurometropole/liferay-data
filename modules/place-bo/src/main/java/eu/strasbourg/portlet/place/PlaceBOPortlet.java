package eu.strasbourg.portlet.place;

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

import eu.strasbourg.portlet.place.display.context.EditPlaceDisplayContext;
import eu.strasbourg.portlet.place.display.context.EditPriceDisplayContext;
import eu.strasbourg.portlet.place.display.context.EditPublicHolidayDisplayContext;
import eu.strasbourg.portlet.place.display.context.EditSubPlaceDisplayContext;
import eu.strasbourg.portlet.place.display.context.ViewPlacesDisplayContext;
import eu.strasbourg.portlet.place.display.context.ViewPricesDisplayContext;
import eu.strasbourg.portlet.place.display.context.ViewPublicHolidaysDisplayContext;
import eu.strasbourg.portlet.place.display.context.ViewSubPlacesDisplayContext;

@Component(immediate = true, property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/place-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/place-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/place-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PlaceBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");

		renderResponse.setTitle("Places");

		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editPlace")) {
			EditPlaceDisplayContext dc = new EditPlaceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editPrice")) {
			EditPriceDisplayContext dc = new EditPriceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editPublicHoliday")) {
			EditPublicHolidayDisplayContext dc = new EditPublicHolidayDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editSubPlace")) {
			EditSubPlaceDisplayContext dc = new EditSubPlaceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("prices")) {
			ViewPricesDisplayContext dc = new ViewPricesDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("publicHolidays")) {
			ViewPublicHolidaysDisplayContext dc = new ViewPublicHolidaysDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("subPlaces")) {
			ViewSubPlacesDisplayContext dc = new ViewSubPlacesDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the places list page
			ViewPlacesDisplayContext dc = new ViewPlacesDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}
		
		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}

}
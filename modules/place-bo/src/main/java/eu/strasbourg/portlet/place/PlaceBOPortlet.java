package eu.strasbourg.portlet.place;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.place.display.context.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

@Component(immediate = true, property = {
		"javax.portlet.version=3.0",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/place-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/place-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/place-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PlaceBOPortlet extends MVCPortlet {

	public ThemeDisplay _themeDisplay;
	public ServiceContext _serviceContext;

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse)
			throws IOException, PortletException {

		_themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = _themeDisplay.getPortletDisplay();

		try {
			_serviceContext = ServiceContextFactory.getInstance(renderRequest);
		} catch (PortalException e) {
			_log.error(e.getMessage() + " : " + renderRequest);
		}

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}

		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editPlace") || mvcPath.equals("/place-bo-edit-place.jsp")) {
			EditPlaceDisplayContext dc = new EditPlaceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editPrice") || mvcPath.equals("/place-bo-edit-price.jsp")) {
			EditPriceDisplayContext dc = new EditPriceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editPublicHoliday") || mvcPath.equals("/place-bo-edit-public-holiday.jsp")) {
			EditPublicHolidayDisplayContext dc = new EditPublicHolidayDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editSubPlace") || mvcPath.equals("/place-bo-edit-subplace.jsp")) {
			EditSubPlaceDisplayContext dc = new EditSubPlaceDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editGoogle")) {
			EditGoogleDisplayContext dc = new EditGoogleDisplayContext(
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
		} else if (tab.equals("google")) {
			ViewGoogleDisplayContext dc = new ViewGoogleDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("token")) {
			if (cmd.equals("saveRefreshToken")) {
				// met à jour le refreshToken
				// il suffit de faire un ajout unique avec comme valeurs obligatoire
				// className = "" , classPK = 0, type = 98
				String refreshToken = ParamUtil.getString(renderRequest, "refresh-token");
				TicketLocalServiceUtil.addDistinctTicket(_themeDisplay.getCompanyId(),"",0,98,
						refreshToken,null, _serviceContext);
			}
			ViewTokenDisplayContext dc = new ViewTokenDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the places list page
			ViewPlacesDisplayContext dc = new ViewPlacesDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}
		
		// Admin ou pas
		renderRequest.setAttribute("isAdmin", _themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
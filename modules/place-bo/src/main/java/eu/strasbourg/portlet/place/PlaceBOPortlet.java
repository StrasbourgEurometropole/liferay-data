package eu.strasbourg.portlet.place;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Ticket;
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
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

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
			e.printStackTrace();
		}

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
			} else if (cmd.equals("updateAccesToken")) {
				JSONObject json = getJSONAccesToken();
				String error = json.getString("error");
				if (Validator.isNotNull(error)) {
					String description = json.getString("error_description");
				}else {
					String accessToken = json.getString("access_token");
					// ajout de l'accesToken en ticket unique
					// il suffit de faire un ajout unique avec comme valeurs obligatoire
					// className = "" , classPK = 0, type = 99
					TicketLocalServiceUtil.addDistinctTicket(_themeDisplay.getCompanyId(), "", 0, 99,
							accessToken, null, _serviceContext);
				}
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

	public JSONObject getJSONAccesToken(){
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		//récupère le refreshToken (className = "" , classPK = 0, type = 98)
		Ticket ticket = TicketLocalServiceUtil.getTickets(-1, -1).stream()
				.filter(t -> t.getClassName().equals("") && t.getClassPK() == 0 && t.getType() == 98).findFirst().get();
		if(ticket != null) {
			StringBuilder postData = new StringBuilder();
			try {
				Map<String, Object> params = new LinkedHashMap<String, Object>();
				params.put("refresh_token", ticket.getExtraInfo());
				params.put("client_id", StrasbourgPropsUtil.getGMBClientId());
				params.put("client_secret", StrasbourgPropsUtil.getGMBSecretCode());
				params.put("grant_type", "refresh_token");
				for (Map.Entry<String, Object> param : params.entrySet()) {
					if (postData.length() != 0)
						postData.append('&');
					postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
					postData.append('=');
					postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
				}
				// On récupère le JSON
				String url = StrasbourgPropsUtil.getGMBAccessTokenURL() + "?" + postData;
				HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
				jsonResponse = PasserelleHelper.readJson(httpConn);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return jsonResponse;
	}

}
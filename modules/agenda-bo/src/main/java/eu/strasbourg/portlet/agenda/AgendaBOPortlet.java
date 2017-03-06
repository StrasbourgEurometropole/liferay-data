package eu.strasbourg.portlet.agenda;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.agenda.display.context.EditCampaignDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.EditEventDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.EditManifestationDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.ViewCampaignsDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.ViewEventsDisplayContext;
import eu.strasbourg.portlet.agenda.display.context.ViewManifestationsDisplayContext;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.header-portlet-css=/css/vendors/daterangepicker.css",
		"com.liferay.portlet.header-portlet-css=/css/agenda-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/agenda-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class AgendaBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");

		renderResponse.setTitle("Events");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// If we are on the Event event page, we add the corresponding
		// display context
		if (cmd.equals("editEvent")) {
			EditEventDisplayContext dc = new EditEventDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editManifestation")) {
			EditManifestationDisplayContext dc = new EditManifestationDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("editCampaign")) {
			EditCampaignDisplayContext dc = new EditCampaignDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("manifestations")) {
			ViewManifestationsDisplayContext dc = new ViewManifestationsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("campaigns")) {
			ViewCampaignsDisplayContext dc = new ViewCampaignsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else { // Else, we are on the event list page
			ViewEventsDisplayContext dc = new ViewEventsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

		// Le dossier d'import des événements
		renderRequest.setAttribute("importPath",
			StrasbourgPropsUtil.getAgendaImportDirectory());
		
		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}

	/**
	 * Gestion de l'autocomplétion des anciens lieux, en attendant d'avoir les
	 * lieux intégrés
	 */
	@Override
	public void serveResource(ResourceRequest request,
		ResourceResponse response) throws PortletException, IOException {
		response.setContentType("text/javascript");

		JSONObject json;
		String name = ParamUtil.getString(request, "name");
		try {
			String url = StrasbourgPropsUtil.getLegacyPlaceApiAutocompleteUrl();
			url = url.replace("[NAME]", name);
			json = JSONHelper.readJsonFromURL(url);
			response.getWriter().write(json.toString());
		} catch (JSONException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
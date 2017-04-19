package eu.strasbourg.portlet.agenda.portlet;

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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.portlet.agenda.portlet.display.context.EditCampaignEventDisplayContext;
import eu.strasbourg.portlet.agenda.portlet.display.context.ViewCampaignEventsDisplayContext;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/vendors/daterangepicker.css",
		"com.liferay.portlet.header-portlet-css=/css/campaign.css",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.single-page-application=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=campaign-web",
		"javax.portlet.display-name=agenda-campaign-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/campaign-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class AgendaCampaignPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		if (mvcPath.equals("/campaign-edit.jsp")) {
			EditCampaignEventDisplayContext dc = new EditCampaignEventDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else {
			ViewCampaignEventsDisplayContext dc = new ViewCampaignEventsDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}

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
		}
	}
}
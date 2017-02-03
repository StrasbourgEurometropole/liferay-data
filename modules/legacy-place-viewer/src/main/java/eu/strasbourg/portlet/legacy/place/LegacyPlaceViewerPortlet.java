package eu.strasbourg.portlet.legacy.place;

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
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.legacy.place.configuration.LegacyPlaceViewerConfiguration;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.models.LegacyPlace;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.LegacyPlaceViewerConfiguration",
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=legacy-place-viewer-portlet",
		"com.liferay.portlet.footer-portlet-javascript=https://www.google.com/recaptcha/api.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.autocomplete.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/legacy-place-main.js",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/legacy-place-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class LegacyPlaceViewerPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			LegacyPlaceViewerConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					LegacyPlaceViewerConfiguration.class);

			// Lieu
			if (Validator.isNotNull(configuration.SIGId())) {
				LegacyPlace place = LegacyPlace.fromSIGId(configuration.SIGId(), themeDisplay.getLocale());
				renderRequest.setAttribute("place", place);
			}
			
			// Clé recaptcha
			renderRequest.setAttribute("recaptchaSecret", StrasbourgPropsUtil.getRecaptchaPublicKey());

		} catch (ConfigurationException e) {
			_log.error(e);
		}

		super.render(renderRequest, renderResponse);
	}

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
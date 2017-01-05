package eu.strasbourg.portlet.legacy.place.configuration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.LEGACY_PLACE_VIEWER },
	service = ConfigurationAction.class)
public class LegacyPlaceViewerConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/legacy-place-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String SIGId = ParamUtil.getString(actionRequest, "SIGId");
		setPreference(actionRequest, "SIGId", SIGId);
		
		String placeName = ParamUtil.getString(actionRequest, "chosenPlaceName");
		setPreference(actionRequest, "placeName", placeName);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			LegacyPlaceViewerConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					LegacyPlaceViewerConfiguration.class);

			request.setAttribute("SIGId",
				ParamUtil.getString(request, "SIGId", configuration.SIGId()));
			
			request.setAttribute("placeName",
				ParamUtil.getString(request, "chosenPlaceName", configuration.placeName()));
			
			
			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	private List<LegacyPlace> getMuseums() {
		List<LegacyPlace> result = new ArrayList<LegacyPlace>();
		try {
			JSONObject json = JSONHelper.readJsonFromURL(
				"http://preprod.strasbourg.eu/Cus-all-hook/api/jsonws/?cusplaceasset/getJsonByCategory/category/Cat_16_10");
			JSONArray array = json.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) { 
				JSONObject placeJSON = array.getJSONObject(i);
				LegacyPlace place = LegacyPlace.fromJSONObject(placeJSON);
				result.add(place);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		return result;
	}
	**/

}

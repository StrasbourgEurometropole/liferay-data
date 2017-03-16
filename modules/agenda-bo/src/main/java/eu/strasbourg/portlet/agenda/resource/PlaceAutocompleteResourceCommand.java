package eu.strasbourg.portlet.agenda.resource;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Gestion de l'autocomplétion des anciens lieux, en attendant d'avoir les
 * lieux intégrés
 */
@Component(
	immediate = true,
    property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
        "mvc.command.name=autocomplete"
    },
    service = MVCResourceCommand.class
)
public class PlaceAutocompleteResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest,
		ResourceResponse resourceResponse) throws PortletException {
		resourceResponse.setContentType("text/javascript");

		JSONObject json;
		String name = ParamUtil.getString(resourceRequest, "name");
		try {
			String url = StrasbourgPropsUtil.getLegacyPlaceApiAutocompleteUrl();
			url = url.replace("[NAME]", name);
			json = JSONHelper.readJsonFromURL(url);
			resourceResponse.getWriter().write(json.toString());
		} catch (Exception e) {
			_log.error(e);
		}
		return true;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}

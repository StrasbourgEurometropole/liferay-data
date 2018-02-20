package eu.strasbourg.portlet.map;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.map.configuration.MapConfiguration;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Autour de moi",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/map-view.jsp",
		"javax.portlet.init-param.config-template=/map-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MapPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			// Récupération de la configuration
			MapConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MapConfiguration.class);

			//Récupération des id des centres d'intérêts actifs
			String interestsIdsString = configuration.interestsIds();
			List<Long> interestIds = Arrays.stream(interestsIdsString.split(","))
					.map(i -> Long.parseLong(i))
					.collect(Collectors.toList());
			
			//Récupération de tous les centres d'intérêts actifs avec le statut publié
			List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
					.filter(i -> i.getStatus() == 0 && interestIds.contains(i.getInterestId())).collect(Collectors.toList());
			
			request.setAttribute("interests", interests);
			request.setAttribute("interestsCheckedIds", configuration.interestsDefaultsIds());
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
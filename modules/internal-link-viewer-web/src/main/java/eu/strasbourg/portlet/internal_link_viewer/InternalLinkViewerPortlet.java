package eu.strasbourg.portlet.internal_link_viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.internal_link_viewer.configuration.InternalLinkViewerConfiguration;
import eu.strasbourg.utils.PortletHelper;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=internal-link-viewer-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/internal-link-view.jsp",
		"javax.portlet.init-param.config-template=/internal-link-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class InternalLinkViewerPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletPreferences preferences = renderRequest.getPreferences();
		
		// Titre
		String customPortletTitle = preferences.getValue("portletSetupTitle_" + themeDisplay.getLocale(), "Liens internes");
		renderRequest.setAttribute("customPortletTitle", customPortletTitle);
		
		// Titre spécifique à Strasbourg.eu
		String strasbourgPortletTitle = PortletHelper.getPortletTitle("eu.read-also", renderRequest);
		renderRequest.setAttribute("strasbourgPortletTitle", strasbourgPortletTitle);
		
		try {
			InternalLinkViewerConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					InternalLinkViewerConfiguration.class);

			List<Layout> layouts = new ArrayList<Layout>();
			String layoutsUuids = configuration.linksUuids();
			for (String uuid : layoutsUuids.split(",")) {
				if (Validator.isNotNull(uuid)) {
					Layout layout = LayoutLocalServiceUtil
						.fetchLayoutByUuidAndGroupId(uuid,
							themeDisplay.getScopeGroupId(), false);
					if (layout != null) {
						layouts.add(layout);
					}
				}
			}
			renderRequest.setAttribute("selectedLayouts", layouts);
			
			// Template
			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "default";
			}
			renderRequest.setAttribute("template", template);
		} catch (ConfigurationException e) {
			_log.error(e);
		}

		super.render(renderRequest, renderResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
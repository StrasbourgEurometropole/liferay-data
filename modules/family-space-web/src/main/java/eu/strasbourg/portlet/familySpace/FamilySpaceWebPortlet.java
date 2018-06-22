package eu.strasbourg.portlet.familySpace;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.utils.PortletHelper;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.familySpace.configuration.FamilySpaceConfiguration;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.display-name=Espace famille", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/family-space-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/family-space-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.FAMILY_SPACE_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FamilySpaceWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse response) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			FamilySpaceConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(FamilySpaceConfiguration.class);
			
			String familySpaceURL = configuration.familySpaceURL();
			if (Validator.isNull(familySpaceURL)) {
				familySpaceURL = "#";
			}
			renderRequest.setAttribute("familySpaceURL", familySpaceURL);
			renderRequest.setAttribute("showDeleteButton", PortletHelper.showDeleteButtonOnDashboard(themeDisplay,
					themeDisplay.getPortletDisplay().getId()));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		super.render(renderRequest, response);
	}
}
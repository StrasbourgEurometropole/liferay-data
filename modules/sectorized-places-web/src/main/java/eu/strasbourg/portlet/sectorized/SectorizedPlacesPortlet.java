package eu.strasbourg.portlet.sectorized;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.sectorized.configuration.SectorizedPlacesConfiguration;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.sectorized.configuration.SectorizedPlacesConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=sectorized-places-portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SectorizedPlacesPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			SectorizedPlacesConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(SectorizedPlacesConfiguration.class);

			// Template sélectionné
			String template = Validator.isNotNull(configuration.template()) ? configuration.template() : "default";

			// Utilisation uniquement des rues de Strasbourg
			renderRequest.setAttribute("forceStrasbourg", configuration.forceStrasbourg());
			
			// Clé recaptcha
			String recaptchaKey = StrasbourgPropsUtil.getRecaptchaPublicKey();
			renderRequest.setAttribute("recaptchaKey", recaptchaKey);

			include("/templates/" + template + ".jsp", renderRequest, renderResponse);

		} catch (ConfigurationException e) {
			log.error(e);
		}
	}
	
	@Reference
	private AdictService adictService;

}
package eu.strasbourg.portlet.validation_address;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.display-name=Validation d'adresse", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/validation-address-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/validation-address-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.VALIDATION_ADDRESS_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ValidationAddressWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		ValidationAddressDisplayContext dc = new ValidationAddressDisplayContext(themeDisplay, request,adictService);
		
		request.setAttribute("dc", dc);
		
		// titre personnalisable
		request.setAttribute("title", PortletHelper.getPortletTitle("validation-address", request));
		super.render(request, response);
	}

	@Reference
	private AdictService adictService;
}
package eu.strasbourg.portlet.page_header;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	property = { "com.liferay.portlet.display-category=category.Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=page-header-portlet",
		"com.liferay.portlet.header-portlet-css=/css/page-header-default-style.css",
		"javax.portlet.display-name=Entête de page",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/page-header-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class PageHeaderPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		Layout layout = themeDisplay.getLayout();
		ExpandoBridge expandoBridge = layout.getExpandoBridge();

		String layoutName = layout.getName(renderRequest.getLocale());
		String layoutImage = expandoBridge.getAttribute("image").toString();
		String layoutDescription = layout
			.getDescription(renderRequest.getLocale());

		renderRequest.setAttribute("layoutName", layoutName);
		renderRequest.setAttribute("layoutImage", layoutImage);
		renderRequest.setAttribute("layoutDescription", layoutDescription);
		try {
			PageHeaderConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(PageHeaderConfiguration.class);
			renderRequest.setAttribute("displayShareButtons",
				configuration.displayShareButtons());
			renderRequest.setAttribute("displayImage",
				configuration.displayImage());
			renderRequest.setAttribute("alternativeTheme",
				configuration.alternativeTheme());
			renderRequest.setAttribute("imageCredit",
				configuration.imageCredit());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		super.render(renderRequest, renderResponse);
	}

}
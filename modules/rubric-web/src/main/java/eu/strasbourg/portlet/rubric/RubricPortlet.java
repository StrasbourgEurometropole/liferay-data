package eu.strasbourg.portlet.rubric;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.RubricConfiguration",
	property = { "com.liferay.portlet.display-category=category.Strasbourg", "com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=rubric-portlet",
		"javax.portlet.display-name=Rubrique", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/rubric-view.jsp",
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class RubricPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request,
		RenderResponse response) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List<Layout> layouts = themeDisplay.getLayout().getChildren();
		request.setAttribute("childrenLayouts", layouts);
		
		super.render(request, response);
	}
	
	
	
}
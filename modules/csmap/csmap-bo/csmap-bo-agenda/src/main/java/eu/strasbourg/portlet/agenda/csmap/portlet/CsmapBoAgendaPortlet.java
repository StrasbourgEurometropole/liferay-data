package eu.strasbourg.portlet.agenda.csmap.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.agenda.csmap.display.context.EditCsmapPrincipalAgendaDisplayContext;
import eu.strasbourg.portlet.agenda.csmap.display.context.EditCsmapThematiqueAgendaDisplayContext;
import eu.strasbourg.portlet.agenda.csmap.display.context.ViewCsmapAgendaThematiqueDisplayContext;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author quentin.mayer
 */
@Component(
	immediate = true,
	property = {
			"javax.portlet.version=3.0",
			"com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.layout-cacheable=true",
			"javax.portlet.display-name=CsmapBoAgenda",
			"com.liferay.portlet.single-page-application=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA,
			"javax.portlet.init-param.view-template=/csmap-bo-agenda-view.jsp",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CsmapBoAgendaPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		renderResponse.setTitle("CSmapAgendaCategories");

		// If we are on an "add" page, we set a return URL and show the "back"
		// button
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL);
		}

		// display context}
		 if (cmd.equals("editAgendaThematique") || mvcPath.equals("/csmap-bo-agenda-edit-thematique.jsp")) {
			EditCsmapThematiqueAgendaDisplayContext dc = new EditCsmapThematiqueAgendaDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (tab.equals("agendaThematique")) {
			ViewCsmapAgendaThematiqueDisplayContext dc = new ViewCsmapAgendaThematiqueDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else {
			EditCsmapPrincipalAgendaDisplayContext dc = new EditCsmapPrincipalAgendaDisplayContext();
			renderRequest.setAttribute("dc", dc);
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}
}
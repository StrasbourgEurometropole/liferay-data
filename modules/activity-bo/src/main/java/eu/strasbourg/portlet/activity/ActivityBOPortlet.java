package eu.strasbourg.portlet.activity;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.activity.display.context.EditActivityCourseDisplayContext;
import eu.strasbourg.portlet.activity.display.context.EditActivityDisplayContext;
import eu.strasbourg.portlet.activity.display.context.EditActivityOrganizerDisplayContext;
import eu.strasbourg.portlet.activity.display.context.ViewActivitiesDisplayContext;
import eu.strasbourg.portlet.activity.display.context.ViewActivityCoursesDisplayContext;
import eu.strasbourg.portlet.activity.display.context.ViewActivityOrganizersDisplayContext;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/activity-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/activity-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/activity-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class ActivityBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		// Si on est sur une page d'édition, on ajoute un bouton "retour"
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// On set le display contexte selon la page sur laquelle on est
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		String tab = ParamUtil.getString(renderRequest, "tab");
		Boolean fromAjax = GetterUtil.getBoolean(renderRequest.getAttribute("fromAjax"));
		String title = PortalUtil.getPortletTitle(renderRequest);
		if (mvcPath.equals("/activity-bo-edit-activity.jsp")) {
			EditActivityDisplayContext dc = new EditActivityDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activity";
		} else if (mvcPath.equals("/activity-bo-edit-course.jsp") || fromAjax) {
			EditActivityCourseDisplayContext dc = new EditActivityCourseDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activity-course";
		} else if (mvcPath.equals("/activity-bo-edit-organizer.jsp")) {
			EditActivityOrganizerDisplayContext dc = new EditActivityOrganizerDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activity-organizer";
		} else if (tab.equals("activityCourses")) {
			ViewActivityCoursesDisplayContext dc = new ViewActivityCoursesDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activity-courses";
		} else if (tab.equals("activityOrganizers")) {
			ViewActivityOrganizersDisplayContext dc = new ViewActivityOrganizersDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activity-organizers";
		} else {
			ViewActivitiesDisplayContext dc = new ViewActivitiesDisplayContext(
				renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			title = "activities";
		}
		super.render(renderRequest, renderResponse);

		title = LanguageUtil
			.get(PortalUtil.getHttpServletRequest(renderRequest), title);
		renderResponse.setTitle(title);

	}

}
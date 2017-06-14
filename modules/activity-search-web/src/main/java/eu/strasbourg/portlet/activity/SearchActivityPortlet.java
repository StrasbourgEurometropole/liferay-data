package eu.strasbourg.portlet.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.activity.display.context.SearchActivityDisplayContext;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.service.ActivityLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.single-page-application=false",
		"com.liferay.portlet.css-class-wrapper=search-activity-portlet",
		"com.liferay.portlet.header-portlet-css=/css/search-activity-main.css",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-activity-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class SearchActivityPortlet extends MVCPortlet {

	private ActivityLocalService activityLocalService;

	@Reference(unbind = "-")
	public void setActivityLocalService(
		ActivityLocalService activityLocalService) {
		this.activityLocalService = activityLocalService;
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		List<Activity> allActivities = activityLocalService.getActivities(-1,
			-1);
		request.setAttribute("allActivities", allActivities);

		request.setAttribute("dc", new SearchActivityDisplayContext(request));

		// Application display templates
		PortletPreferences preferences = request.getPreferences();
		String displayStyle = GetterUtil
			.getString(preferences.getValue("displayStyle", StringPool.BLANK));
		long displayStyleGroupId = GetterUtil
			.getLong(preferences.getValue("displayStyleGroupId", null), 0);
		request.setAttribute("displayStyle", displayStyle);
		request.setAttribute("displayStyleGroupId", displayStyleGroupId);
		request.setAttribute("templateEntries", new ArrayList<Object>());

		super.render(request, response);
	}

}
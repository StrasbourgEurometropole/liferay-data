package eu.strasbourg.portlet.activity.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB },
	service = TemplateHandler.class)
public class ActivityCourseDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return ActivityCourse.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Cours d'activite";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.ACTIVITY_WEB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		long classPK, String language, Locale locale) throws Exception {
		Map<String, TemplateVariableGroup> templateVariableGroups = super.getTemplateVariableGroups(
			classPK, language, locale);

		TemplateVariableGroup fieldsTemplateVariableGroup = templateVariableGroups
			.get("fields");
		fieldsTemplateVariableGroup.empty();

		fieldsTemplateVariableGroup.addCollectionVariable("Activities",
			List.class, PortletDisplayTemplateManager.ENTRIES, "ActivityCourse",
			ActivityCourse.class, "currentActivityCourse", "getTitle(locale)");

		fieldsTemplateVariableGroup.addVariable("ActivityCourse",
			ActivityCourse.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class,
			"isFeatured");

		return templateVariableGroups;
	}
}
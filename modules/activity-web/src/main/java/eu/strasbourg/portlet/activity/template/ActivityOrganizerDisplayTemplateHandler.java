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
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB }, service = TemplateHandler.class)
public class ActivityOrganizerDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return ActivityOrganizer.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Organisateur d'activite";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.ACTIVITY_WEB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(long classPK, String language, Locale locale)
			throws Exception {
		Map<String, TemplateVariableGroup> templateVariableGroups = super.getTemplateVariableGroups(classPK, language,
				locale);

		TemplateVariableGroup fieldsTemplateVariableGroup = templateVariableGroups.get("fields");
		fieldsTemplateVariableGroup.empty();

		fieldsTemplateVariableGroup.addCollectionVariable("Activities", List.class,
				PortletDisplayTemplateManager.ENTRIES, "ActivityOrganizer", ActivityOrganizer.class,
				"currentActivityOrganizer", "getTitle(locale)");

		fieldsTemplateVariableGroup.addVariable("ActivityOrganizer", ActivityCourse.class, "entry");

		return templateVariableGroups;
	}
}
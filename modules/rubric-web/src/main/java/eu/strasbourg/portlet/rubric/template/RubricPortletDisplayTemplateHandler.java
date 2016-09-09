package eu.strasbourg.portlet.rubric.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.RUBRIC_WEB
    },
	service = TemplateHandler.class
)
public class RubricPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return Layout.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Rubrique";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.RUBRIC_WEB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		long classPK, String language, Locale locale) throws Exception {
		Map<String, TemplateVariableGroup> templateVariableGroups = super.getTemplateVariableGroups(
			classPK, language, locale);
		
		
		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");
		fieldsTemplateVariableGroup.empty();
		
		//fieldsTemplateVariableGroup.addVariable("test-var-label", String.class, "testVar");
		
		fieldsTemplateVariableGroup.addCollectionVariable(
			"pages", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"page", Layout.class, "currentPage", "getName(locale)");
		
		return templateVariableGroups;
	}
}
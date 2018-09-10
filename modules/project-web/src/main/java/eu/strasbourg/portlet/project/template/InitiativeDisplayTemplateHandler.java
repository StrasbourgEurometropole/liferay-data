package eu.strasbourg.portlet.project.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB
	    },
		service = TemplateHandler.class
	)
public class InitiativeDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {
	@Override
	public String getClassName() {
		return Initiative.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Initiative";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.PROJECT_WEB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		long classPK, String language, Locale locale) throws Exception {
		Map<String, TemplateVariableGroup> templateVariableGroups = super.getTemplateVariableGroups(
			classPK, language, locale);
		
		
		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");
		fieldsTemplateVariableGroup.empty();
		
		fieldsTemplateVariableGroup.addCollectionVariable(
			"Initiatives", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"Initiative", Initiative.class, "currentInitiative", "getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Initiative", Initiative.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}

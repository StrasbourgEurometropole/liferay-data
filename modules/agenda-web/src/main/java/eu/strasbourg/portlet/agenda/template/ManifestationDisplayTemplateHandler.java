package eu.strasbourg.portlet.agenda.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_WEB
    },
	service = TemplateHandler.class
)
public class ManifestationDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return Manifestation.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Manifestation";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.AGENDA_WEB;
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
			"Manifestations", List.class, PortletDisplayTemplateManager.ENTRIES,
			"Manifestation", Manifestation.class, "currentManifestation", "getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Manifestation", Manifestation.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}
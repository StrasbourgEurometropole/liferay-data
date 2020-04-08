package eu.strasbourg.portlet.gtfs.template;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_WEB
    },
	service = TemplateHandler.class
)
public class ArretDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return Arret.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Arret";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.GTFS_WEB;
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
			"Arrets", List.class,
				PortletDisplayTemplateManager.ENTRIES,
			"Arret",
				Arret.class,
				"currentArret",
				"getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Arret", Arret.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		return templateVariableGroups;
	}

}

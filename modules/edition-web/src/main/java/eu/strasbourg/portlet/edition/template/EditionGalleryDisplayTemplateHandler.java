package eu.strasbourg.portlet.edition.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.EDITION_WEB
    },
	service = TemplateHandler.class
)
public class EditionGalleryDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return EditionGallery.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Galerie d'Editions";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.EDITION_WEB;
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
			"Galeries d'editions", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"Galerie d'edition", EditionGallery.class, "currentGallery", "getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Galerie d'edition", EditionGallery.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}
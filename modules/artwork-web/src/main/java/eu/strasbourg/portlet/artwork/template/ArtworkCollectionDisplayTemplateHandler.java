package eu.strasbourg.portlet.artwork.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_WEB
    },
	service = TemplateHandler.class
)
public class ArtworkCollectionDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return ArtworkCollection.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Collection d'Oeuvres";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.ARTWORK_WEB;
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
			"Collections d'oeuvres", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"Collection d'oeuvres", ArtworkCollection.class, "currentCollection", "getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Collection d'oeuvres", ArtworkCollection.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}
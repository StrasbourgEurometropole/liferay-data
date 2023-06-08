package eu.strasbourg.portlet.project.template;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB
        },
        service = TemplateHandler.class
)
public class PetitionDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {
    @Override
    public String getClassName() {
        return Petition.class.getName();
    }

    @Override
    public String getName(Locale locale) {
        return "Template Petition";
    }

    @Override
    public String getResourceName() {
        return StrasbourgPortletKeys.PROJECT_WEB;
    }

    @Override
    public Map<String, TemplateVariableGroup> getTemplateVariableGroups(long classPK, String language, Locale locale) throws Exception {
        Map<String, TemplateVariableGroup> templateVariableGroups =
                super.getTemplateVariableGroups(
                classPK, language, locale);
        TemplateVariableGroup fieldsTemplateVariableGroup =
                templateVariableGroups.get("fields");
        fieldsTemplateVariableGroup.empty();

        fieldsTemplateVariableGroup.addCollectionVariable(
                "Petitions", List.class,
                PortletDisplayTemplateManager.ENTRIES,
                "Petition",Petition.class,
                "currentPetition",
                "getTitle(locale)");
        fieldsTemplateVariableGroup.addVariable("Petition",Petition.class,"entry");
        fieldsTemplateVariableGroup.addVariable("Featured",Boolean.class,"isFeatured");
        return templateVariableGroups;
    }
}

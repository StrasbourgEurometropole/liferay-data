package eu.strasbourg.portlet.help.template;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_WEB
        },
        service = TemplateHandler.class
)
public class HelpRequestDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {
    @Override
    public String getClassName() {
        return HelpRequest.class.getName();
    }

    @Override
    public String getName(Locale locale) {
        return "Template Demande d'aide";
    }

    @Override
    public String getResourceName() {
        return StrasbourgPortletKeys.HELP_WEB;
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
                "HelpRequests", List.class, PortletDisplayTemplateManager.ENTRIES,
                "HelpRequest", HelpRequest.class, "currentHelpRequest", "getMessage(locale)");

        fieldsTemplateVariableGroup.addVariable("HelpRequest", HelpRequest.class, "entry");
        // Pas utile ?
        // fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");

        return templateVariableGroups;
    }
}
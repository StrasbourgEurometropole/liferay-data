package eu.strasbourg.portlet.activity.template;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB }, service = TemplateHandler.class)
public class AssociationActivityDisplayTemplateHandler extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return AssociationActivity.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template AssociationActivity";
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
				PortletDisplayTemplateManager.ENTRIES, "AssociationActivity", AssociationActivity.class,
				"currentAssociationActivity", "getTitle(locale)");

		fieldsTemplateVariableGroup.addVariable("AssociationActivity", AssociationActivity.class, "entry");

		return templateVariableGroups;
	}
}
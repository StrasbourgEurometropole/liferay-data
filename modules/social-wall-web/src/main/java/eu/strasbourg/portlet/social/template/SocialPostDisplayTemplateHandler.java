package eu.strasbourg.portlet.social.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.SOCIAL_WALL_WEB
    },
	service = TemplateHandler.class
)
public class SocialPostDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {
	
	@Override
	public String getClassName() {
		return SocialPost.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Social Post";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.SOCIAL_WALL_WEB;
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
			"Posts", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"Post", SocialPost.class, "currentPost", "getContent()");
		
		
		return templateVariableGroups;
	}
}
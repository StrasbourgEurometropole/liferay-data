package eu.strasbourg.portlet.video.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_WEB
    },
	service = TemplateHandler.class
)
public class VideoPortletDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return Video.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Video";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.VIDEO_WEB;
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
			"Videos", List.class, PortletDisplayTemplateManager.ENTRIES, 
			"Video", Video.class, "currentVideo", "getTitle(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Video", Video.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}
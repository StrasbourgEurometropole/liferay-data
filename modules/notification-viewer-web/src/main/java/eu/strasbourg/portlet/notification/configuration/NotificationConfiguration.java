package eu.strasbourg.portlet.notification.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.notification.configuration.NotificationConfiguration", localization = "content/Language", name = "portlet.notification.configuration.name")
public interface NotificationConfiguration {
	
	@Meta.AD(name="showAllURL", deflt = "", required = false)
	public String showAllURL();
	
	@Meta.AD(name="template", deflt = "", required = false)
	public String template();
}
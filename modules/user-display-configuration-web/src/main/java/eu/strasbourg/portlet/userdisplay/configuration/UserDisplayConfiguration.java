package eu.strasbourg.portlet.userdisplay.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.userdisplay.UserDisplayConfiguration",
	localization = "content/Language",
	name = "portlet.userdisplay.configuration.name")
public interface UserDisplayConfiguration {

	@Meta.AD(name="", deflt = "", required = false)
	public String adminConfig();
	
}

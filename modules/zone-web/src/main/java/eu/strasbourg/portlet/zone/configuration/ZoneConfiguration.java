package eu.strasbourg.portlet.zone.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.zone.configuration.ZoneConfiguration",
	localization = "content/Language",
	name = "portlet.zone.configuration.name")
public interface ZoneConfiguration {

	@Meta.AD(name = "text", deflt = "", required = false)
	public String textXML();
	
		
}

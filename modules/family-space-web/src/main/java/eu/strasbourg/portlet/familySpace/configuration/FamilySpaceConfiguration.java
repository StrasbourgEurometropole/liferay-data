package eu.strasbourg.portlet.familySpace.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
		category = "Strasbourg", 
		scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.familySpace.configuration.FamilySpaceConfiguration", 
	localization = "content/Language", 
	name = "portlet.familySpace.configuration.name")
public interface FamilySpaceConfiguration {

	@Meta.AD(name = "familySpaceURL", deflt = "", required = false)
	public String familySpaceURL();

}

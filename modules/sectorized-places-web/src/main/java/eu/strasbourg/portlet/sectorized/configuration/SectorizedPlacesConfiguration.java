package eu.strasbourg.portlet.sectorized.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.sectorized.configuration.SectorizedPlacesConfiguration",
	localization = "content/Language",
	name = "portlet.sectorized.configuration.name")
public interface SectorizedPlacesConfiguration {
	
	@Meta.AD(name="template", deflt = "", required = false)
	public String template();

	@Meta.AD(name="types", deflt = "", required = false)
	public String[] types();
	
	@Meta.AD(name="forceStrasbourg", deflt = "", required = false)
	public boolean forceStrasbourg();
	
		
}

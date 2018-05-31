package eu.strasbourg.portlet.graveyard.portlet.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.graveyard.configuration.GraveyardConfiguration", 
localization = "content/Language", name = "portlet.graveyard.configuration.name")
public interface GraveyardConfiguration {

	@Meta.AD(name = "contactURL", deflt = "", required = false)
	public String contactURL();

}

package eu.strasbourg.portlet.demarches.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.demarches.configuration.DemarchesConfiguration", 
localization = "content/Language", name = "portlet.demarches.configuration.name")
public interface DemarchesConfiguration {


	@Meta.AD(name = "url", deflt = "#", required = false)
	public String url();

}

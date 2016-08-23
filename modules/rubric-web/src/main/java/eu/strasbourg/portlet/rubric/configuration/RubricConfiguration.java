package eu.strasbourg.portlet.rubric.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.rubric.configuration.RubricConfiguration",
	localization = "content/Language",
	name = "portlet.rubric.configuration.name")
public interface RubricConfiguration {

}
